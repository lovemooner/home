package love.moon.wx.service.impl;

import love.moon.common.HttpResponse;
import love.moon.common.Result;
import love.moon.util.ConcurrentHelper;
import love.moon.util.HttpUtil;
import love.moon.util.JsonUtil;
import love.moon.util.StringUtil;
import love.moon.wx.pojo.*;
import love.moon.wx.pojo.menu.*;
import love.moon.wx.pojo.msg.KWMsgTemplate;
import love.moon.wx.pojo.msg.KWTemplateDTO;
import love.moon.wx.pojo.qr.QRActionInfo;
import love.moon.wx.pojo.qr.QRType;
import love.moon.wx.service.IConfigService;
import love.moon.wx.service.IWxService;
import love.moon.wx.service.MemCacheAdapter;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component("wxService")
public class WxService implements IWxService {

    @Autowired
    private IConfigService configService;

    public static final Logger LOG = LoggerFactory.getLogger(WxService.class);

    /**
     * 获取 access_token
     * 并发访问时，每隔一段时间自动尝试请求一次
     *
     * @param appId
     * @param secret
     * @return
     * @throws Exception
     */
    private String getAccessToken(String appId, String secret) throws Exception {
        int tryingCount = 0;
        String access_token = null;
        do {
            access_token = doGetAccessToken(appId, secret);
            if (StringUtil.isNotEmpty(access_token)) {
                return access_token;
            }
            Thread.sleep(WXConstant.GET_ACCESS_TRYING_EXPIRY);
            tryingCount++;
            LOG.debug("wx:the " + tryingCount + " times,try to get access_token,and appId is {}", appId);
        } while (tryingCount < 10); //尝试10次
        return null;
    }

    private String doGetAccessToken(String appId, String secret) throws IOException {
        try {
            if (StringUtil.isEmpty(appId) || StringUtil.isEmpty(secret)) {
                LOG.error("wx:illegal param");
                return null;
            }
            String access_token = StringUtil.valueOf(MemCacheAdapter.get(WXConstant.KEY_PREFIX_ACCESS_TOKEN + appId));
            if (StringUtil.isNotEmpty(access_token)) {
                return access_token;
            }
            if (!ConcurrentHelper.lock(ConcurrentScene.WX_GET_ACCESS_TOKEN, appId)) {
                return null;
            }
            LOG.info("wx:begin to get platform access_token");
            String url = WXConstant.URL_ACCESS_TOKEN;
            url = url.replace("{APPID}", appId).replace("{SECRET}", secret);
            HttpResponse response = HttpUtil.sendGet(url);
            String accessTokenJson = response.getContent();
            AccessToken accessToken = JsonUtil.jsonToObj(accessTokenJson, AccessToken.class);
            if (StringUtil.isNotEmpty(accessToken.getErrcode())) {
                LOG.error("wx:get access_token error,errMsg is {}", accessToken.getErrmsg());
                return null;
            }
            access_token = accessToken.getAccess_token();
            LOG.info("wx:get platform access_token success,and access_token is {}", access_token);
            MemCacheAdapter.set(WXConstant.KEY_PREFIX_ACCESS_TOKEN + appId, access_token, new Date(System.currentTimeMillis() + WXConstant.M_EXPIRE_ACCESS_TOKEN));
            return access_token;
        } finally {
            ConcurrentHelper.release(ConcurrentScene.WX_GET_ACCESS_TOKEN, appId);
        }
    }

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{WXConstant.token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 创建永久二维码
     *
     * @param sceneId
     * @return
     * @throws Exception
     */
    public  GetQRResult createLimitQRCode(String publicNo, String sceneId) throws Exception {
        return doCreateQRCode(publicNo, null, QRType.QR_LIMIT_SCENE, sceneId);
    }

    /**
     * 创建临时二维码
     *
     * @param expireSeconds
     * @param sceneId
     * @return
     * @throws Exception
     */
    public  GetQRResult createTempQRCode(String publicNo, Long expireSeconds, String sceneId) throws Exception {
        return doCreateQRCode(publicNo, expireSeconds, QRType.QR_SCENE, sceneId);
    }

    private  GetQRResult doCreateQRCode(String publicNo, Long expireSeconds, QRType scene, String sceneId) throws Exception {
        if (scene == null || sceneId == null) {
            throw new Exception("illegal param");
        }
        String accessToken = getAccessTokenByPublicNo(publicNo);
        if (StringUtil.isEmpty(accessToken)) {
            throw new Exception("can't get access_token,method=createQRCode");
        }
        String url = WXConstant.URL_CREATE_QR_CODE;
        url = url.replace("{ACCESS_TOKEN}", accessToken);
        QRActionInfo actionInfo = new QRActionInfo();
        actionInfo.setExpire_seconds(StringUtil.valueOf(expireSeconds));
        actionInfo.setAction_name(scene);
        Map<String, String> infoMap = new HashMap<String, String>();
        infoMap.put("scene_id", sceneId);
        actionInfo.getAction_info().put("scene", infoMap);
        HttpResponse response = HttpUtil.sendPost(url, JsonUtil.objectToJson(actionInfo));
        String content = response.getContent();
        return JsonUtil.jsonToObj(content, GetQRResult.class);
    }

    public KWMsgTemplate getRemindTemplate(String publicNo, String toUser, String vehicleNo, String appoint, String date, String shopMobile) throws Exception {
        KWTemplateDTO templateDTO = getCachedKWTemplate(publicNo, WXConstant.TEMPLATE_TITLE_APPOINT_REMIND);
        if (templateDTO == null) {
            LOG.error("can't get getRemindTemplate,publicNo is {}", publicNo);
            return null;
        }
        KWMsgTemplate msgTemplate = templateDTO.toWXKWMsgTemplate();
        msgTemplate.setFirst(templateDTO.getFirst().replace("{vehicleNo}", vehicleNo), templateDTO.getFirstColor());
        msgTemplate.setRemark(templateDTO.getRemark().replace("{shopMobile}", StringUtil.valueOf(shopMobile)), templateDTO.getRemarkColor());
        msgTemplate.setKeyword1(appoint, templateDTO.getKeyword1Color());
        msgTemplate.setKeyword2(date, templateDTO.getKeyword2Color());
        msgTemplate.setTouser(toUser);
        msgTemplate.setTopcolor(templateDTO.getTopColor());
        return msgTemplate;
    }


    public KWTemplateDTO getCachedKWTemplate(String publicNo, String template_name) {
        return null;
    }

    public  String getAccessTokenByPublicNo(String publicNo) throws Exception {
        //todo query db
        return getAccessToken(WXConstant.APP_ID, WXConstant.SECRET);
    }


    /**
     * 创建菜单
     *
     * @return
     * @throws Exception
     * @throws org.dom4j.DocumentException
     */
    public Result createMenu(String publicNo) throws Exception {
        Result result = new Result();
        String accessToken = getAccessTokenByPublicNo(publicNo);
        if (StringUtil.isEmpty(accessToken)) {
            return null;
        }
        String menu_delete_url = WXConstant.URL_DELETE_MENU.replace("{ACCESS_TOKEN}", accessToken);
        LOG.info("delete menu");
        HttpResponse response = HttpUtil.sendPost(menu_delete_url);
        ErrCode errCode = JsonUtil.jsonToObj(response.getContent(), ErrCode.class);
        if (!ErrCode.SUCCESS.equals(errCode.getErrcode())) {
            return new Result(false, "delete menu failed msg is " + errCode.getErrmsg());
        }
        String menu_create_url = WXConstant.URL_CREATE_MENU.replace("{ACCESS_TOKEN}", accessToken);
        String menu = getMenu(publicNo);
        LOG.info("create menu,menu={}", menu);
        response = HttpUtil.sendPost(menu_create_url, menu);
        errCode = JsonUtil.jsonToObj(response.getContent(), ErrCode.class);
        if (ErrCode.SUCCESS.equals(errCode.getErrcode())) {
            return new Result(true, "create menu success!");
        } else {
            return new Result(false, "create menu failed,msg=" + errCode.getErrmsg());
        }
    }

    /**
     * 获取 jsApiTicket
     * 并发访问时，每隔一段时间自动尝试请求一次
     *
     * @param appId
     * @param secret
     * @return
     * @throws Exception
     */
    private String getJsApiTicket(String appId, String secret) throws Exception {
        int tryingCount = 0;
        String ticket = null;
        do {
            ticket = doGetJsApiTicket(appId, secret);
            if (StringUtil.isNotEmpty(ticket)) {
                return ticket;
            }
            Thread.sleep(WXConstant.GET_ACCESS_TRYING_EXPIRY);
            tryingCount++;
            LOG.debug("wx:the " + tryingCount + " times,try to get jsApiTicket,and appId is {}", appId);
        } while (tryingCount < 10); //尝试10次
        return null;
    }


    private String doGetJsApiTicket(String appId, String secret) throws Exception {
        try {
            if (StringUtil.isEmpty(appId) || StringUtil.isEmpty(secret)) {
                LOG.error("wx:illegal param");
                return null;
            }
            String accessToken = getAccessToken(appId, secret);
            if (StringUtil.isEmpty(accessToken)) return null;
            String jsApiTicket = StringUtil.valueOf(MemCacheAdapter.get(WXConstant.KEY_PREFIX_JS_API_TICKET + appId));
            if (StringUtil.isNotEmpty(jsApiTicket)) {
                return jsApiTicket;
            }
            if (!ConcurrentHelper.lock(ConcurrentScene.WX_GET_JS_API_TICKET, appId)) {
                return null;
            }
            LOG.debug("wx:begin to get platform jsApiTicket");
            String url = WXConstant.URL_JS_API_TICKET;
            url = url.replace("{ACCESS_TOKEN}", accessToken);
            HttpResponse response = HttpUtil.sendGet(url);
            String ticketJson = response.getContent();
            JSApiTicket ticket = JsonUtil.jsonToObj(ticketJson, JSApiTicket.class);
            jsApiTicket = ticket.getTicket();
            if (!WXConstant.SUCCESS.equals(ticket.getErrcode())) {
                LOG.error("wx:get jsApiTicket error,errMsg is {}", ticket.getErrmsg());
                return null;
            }
            LOG.debug("wx:get platform jsApiTicket({}) success", jsApiTicket);
            MemCacheAdapter.set(WXConstant.KEY_PREFIX_JS_API_TICKET + appId, jsApiTicket, new Date(System.currentTimeMillis() + WXConstant.M_EXPIRE_ACCESS_TOKEN));
            return jsApiTicket;
        } finally {
            ConcurrentHelper.release(ConcurrentScene.WX_GET_JS_API_TICKET, appId);
        }
    }


    /**
     * 微信OAuth2.0授权登录让微信用户使用微信身份安全登录第三方应用或网站
     * 与基础支持中的access_token不同
     *
     * @param appId
     * @param secret
     * @param code
     * @return
     * @throws Exception
     */
    public static OAuthAccessToken getOAuthAccessToken(String appId, String secret, String code) throws Exception {
        String url = WXConstant.URL_OAUTH_ACCESS_TOKEN;
        url = url.replace("{APP_ID}", appId).replace("{SECRET}", secret).replace("{CODE}", code);
        HttpResponse response = HttpUtil.sendGet(url);
        String accessTokenJson = response.getContent();
        OAuthAccessToken accessToken = JsonUtil.jsonToObj(accessTokenJson, OAuthAccessToken.class);
        if (StringUtil.isNotEmpty(accessToken.getErrcode())) {
            LOG.error("wx:get oauth_access_token error,errCode is {} and errMsg is {}", accessToken.getErrcode(), accessToken.getErrmsg());
            return null;
        }
        LOG.info("get oauth_access_token success,openId is {}", accessToken.getOpenid());
        return accessToken;
    }


    /**
     * 读取xml生成菜单json字符串
     *
     * @return
     * @throws Exception
     */
    public String getMenu(String publicNo) throws Exception {
        File file = new File("wx_menu.xml");
        SAXReader reader = new SAXReader();
        Document doc = reader.read(file);
        Element root = doc.getRootElement();
        List elements = root.elements();
        if (elements.size() == 0) {
            throw new Exception("wx:menu xml content is empty!");
        }
        //assemble menu
        String appId = WXConstant.APP_ID;
        List<Button> buttons = new ArrayList<Button>();
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element elem = (Element) it.next();
            if (elem.elements().size() > 0) {
                //xml中<sub_button>
                Element subRoot = (Element) elem.elements().get(0);
                //设置二级子菜单
                List<Element> subElements = subRoot.elements();
                List<Button> subButtons = new ArrayList<Button>();
                for (int i = 0; i < subElements.size(); i++) {
                    Element subElem = subElements.get(i);
                    subButtons.add(createButton(subElem, publicNo, appId));
                }
                //complexButton 对应xml中<sub_button>
                ComplexButton complexButton = new ComplexButton();
                complexButton.setName(elem.attributeValue("name"));
                complexButton.setSub_button(subButtons.toArray(new Button[subButtons.size()]));
                buttons.add(complexButton);
            } else {
                buttons.add(createButton(elem, publicNo, appId));
            }
        }

        Menu menu = new Menu();
        menu.setButton(buttons.toArray(new Button[buttons.size()]));
        return JsonUtil.objectCHToJson(menu);
    }

    /**
     * 生成短号
     *
     * @param url
     * @return
     * @throws Exception
     */
    public  String getShortUrl(String publicNo, String url) throws Exception {
        String accessToken = getAccessTokenByPublicNo(publicNo);
        if (StringUtil.isEmpty(accessToken)) {
            return null;
        }
        String gen_short_url = WXConstant.URL_GET_SHORT.replace("{ACCESS_TOKEN}", accessToken);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("action", "long2short");
        paramsMap.put("long_url", url);
        HttpResponse response = HttpUtil.sendPost(gen_short_url, JsonUtil.objectToJson(paramsMap));
        GetShortUrlResult result = JsonUtil.jsonToObj(response.getContent(), GetShortUrlResult.class);
        if (ErrCode.SUCCESS.equals(result.getErrcode())) {
            return result.getShort_url();
        } else {
            LOG.error("get short url exception,", result.getErrmsg());
            return null;
        }
    }


    private Button createButton(Element elem, String publicNo, String appId) throws Exception {
        String type = elem.attributeValue("type");
        if (WXConstant.MENU_TYPE_CLICK.equals(type)) {
            ClickButton button = new ClickButton();
            button.setName(elem.attributeValue("name"));
            button.setKey(elem.attributeValue("key"));
            return button;
        } else if (WXConstant.MENU_TYPE_VIEW.equals(type)) {
            ViewButton button = new ViewButton();
            button.setName(elem.attributeValue("name"));
            String text = elem.getTextTrim();
//            text = text.replace("{APP_ID}", appId).replace("{PUBLIC_NO}", publicNo).replace("{EVN_DOMAIN}", getEvnDomain());
            button.setUrl(text);
            return button;
        }
        throw new Exception("wx:menu xml has grammar error!");
    }

    /**
     * 获取密钥
     *
     * @return
     */
//    public static byte[] getSecretKey() {
//        byte[] sKeyByte = (byte[]) MemCacheAdapter.get(WXConstant.WX_ACCOUNT_SECRET_KEY);
//        if (sKeyByte == null || sKeyByte.length == 0) {
//            DataInputStream dis = null;
//            try {
//                IConfigService configService = ServiceManager.getService(IConfigService.class);
//                String s_k_path = configService.getConfig("wx_cfg_path", ShopConstant.BC_SHOP_ID);
//                if (CommonUtil.isDevMode()) {
//                    s_k_path = "d:\\tomcat\\key\\wx\\";
//                }
//                File file = new File(s_k_path + "s_k_file");
//                FileInputStream fis = new FileInputStream(file);
//                dis = new DataInputStream(fis);
//                sKeyByte = new byte[(int) file.length()];
//                dis.readFully(sKeyByte);
//                MemCacheAdapter.set(WXConstant.WX_ACCOUNT_SECRET_KEY, sKeyByte);
//            } catch (Exception e) {
//                LOG.error(e.getMessage(), e);
//            } finally {
//                if (dis == null) return null;
//                try {
//                    dis.close();
//                } catch (IOException e) {
//                    LOG.error(e.getMessage(), e);
//                }
//            }
//        }
//        return sKeyByte;
//    }


    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }


    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}