package love.moon.wx;

//import com.bcgogo.api.RescueDTO;
//import com.bcgogo.api.response.HttpResponse;
//import com.bcgogo.common.CommonUtil;
//import com.bcgogo.common.Result;
//import com.bcgogo.config.cache.BcgogoConcurrentController;
//import com.bcgogo.config.dto.juhe.VehicleViolateRegulationRecordDTO;
//import com.bcgogo.config.model.WXImageLib;
//import com.bcgogo.config.service.IConfigService;
//import com.bcgogo.config.util.MemCacheAdapter;
//import com.bcgogo.constant.Constant;
//import com.bcgogo.enums.ConcurrentScene;
//import com.bcgogo.enums.OrderTypes;
//import com.bcgogo.service.ServiceManager;
//import com.bcgogo.user.model.wx.WXShopAccount;
//import com.bcgogo.user.service.wx.impl.WXAccountService;
//import com.bcgogo.utils.*;
//import com.bcgogo.wx.*;
//import com.bcgogo.wx.action.WXUserAction;
//import com.bcgogo.wx.menu.*;
//import com.bcgogo.wx.message.template.WXKWMsgTemplate;
//import com.bcgogo.wx.message.template.WXMsgTemplate;
//import com.bcgogo.wx.qr.GetQRResult;
//import com.bcgogo.wx.qr.QRActionInfo;
//import com.bcgogo.wx.qr.QRType;
//import com.bcgogo.wx.security.WXBizMsgCrypt;
//import com.bcgogo.wx.user.WXAccountDTO;
//import com.bcgogo.wx.user.WXKWTemplateDTO;
import love.moon.common.Result;
import love.moon.wx.pojo.*;
import love.moon.wx.service.MemCacheAdapter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//

/**
 * Created by IntelliJ IDEA.
 * User: ndong
 * Date: 14-8-11
 * Time: 下午3:27
 * To change this template use File | Settings | File Templates.
 */
public class WXHelper {
  public static final Logger LOG = LoggerFactory.getLogger(WXHelper.class);


//
//  public static OAuthAccessToken getMirrorOAuthAccessToken(String code) throws Exception {
//    if(StringUtil.isEmpty(code)) return null;
//    return getOAuthAccessToken(WXConstant.MIRROR_APP_ID, WXConstant.MIRROR_SECRET, code);
//  }
//
//  public static OAuthAccessToken getOAuthAccessToken(String publicNo, String code) throws Exception {
//    WXAccountDTO accountDTO = ServiceManager.getService(WXAccountService.class).getDecryptedWXAccountByPublicNo(publicNo);
//    if (accountDTO == null || StringUtil.isEmpty(accountDTO.getAppId()) || StringUtil.isEmpty(accountDTO.getSecret())) {
//      LOG.error("wx:getOAuthAccessToken , account is exception and publicNo {} ", publicNo);
//      return null;
//    }
//    return getOAuthAccessToken(accountDTO.getAppId(), accountDTO.getSecret(), code);
//  }
//
//



//
//
//  public static String getAccessTokenByPublicNo(String publicNo) throws Exception {
//    IWXAccountService accountService = ServiceManager.getService(WXAccountService.class);
//    WXAccountDTO accountDTO = accountService.getDecryptedWXAccountByPublicNo(publicNo);
//    return getAccessToken(accountDTO.getAppId(), accountDTO.getSecret());
//  }
//
//  public static String getAccessTokenByShopId(Long shopId) throws Exception {
//    IWXAccountService accountService = ServiceManager.getService(IWXAccountService.class);
//    WXAccountDTO accountDTO = accountService.getDecryptedWXAccountByShopId(shopId);
//    //判断是否店铺自己接入的的公共号
//    accountDTO = accountDTO == null ? accountService.getDefaultWXAccount() : accountDTO;
//    return getAccessToken(accountDTO.getAppId(), accountDTO.getSecret());
//  }
//
//  public static String getJsApiTicketByPublicNo(String publicNo) throws Exception {
//    IWXAccountService accountService = ServiceManager.getService(WXAccountService.class);
//    WXAccountDTO accountDTO = accountService.getDecryptedWXAccountByPublicNo(publicNo);
//    return getJsApiTicket(accountDTO.getAppId(), accountDTO.getSecret());
//  }





  /**
   * 获取wx_image_lib中图片的url
   *
   * @param name
   * @return
   */
//  public static String getWXImageLibShortUrl(String name) {
//    if (StringUtil.isEmpty(name)) return null;
//    String key = WXConstant.WX_IMAGE_LIB_PREFIX + name;
//    String shortUrl = StringUtil.valueOf(MemCacheAdapter.get(key));
//    IConfigService configService = ServiceManager.getService(IConfigService.class);
//    if (StringUtil.isEmpty(shortUrl)) {
//      WXImageLib imageLib = configService.getWXImageLib(name);
//      shortUrl = imageLib != null ? imageLib.getShortUrl() : null;
//      if (StringUtil.isNotEmpty(shortUrl)) {
//        MemCacheAdapter.set(key, shortUrl, new Date(System.currentTimeMillis() + WXConstant.M_EXPIRE_WX_IMAGE_LIB));
//      }
//    }
//    return shortUrl;
//  }
//
//  public static boolean validateWXShopAccount(Long shopId) {
//    WXShopAccount shopAccount = ServiceManager.getService(WXAccountService.class).getWXShopAccountByShopId(shopId);
//    if (shopAccount == null) {
//      LOG.error("wx:sendConsumeMsg failed,shopAccount isn't exist,shopId is {}", shopId);
//      return false;
//    }
//    if (shopAccount.getBalance() <= 0D && System.currentTimeMillis() > shopAccount.getExpireDate()) {
//      LOG.debug("wx:sendConsumeMsg stop,shopAccount's balance isn't enough,shopId is {}", shopId);
//      return false;
//    }
//    return true;
//  }



  /**
   * 绑定车辆的url
   *
   * @param openId
   * @return
   */
//  public static String vehicleBindUrl(String openId) {
//    String url = WXHelper.getEvnDomain() + WXConstant.TO_VEHICLE_BIND_URL;
//    url = url.replace("{OPENID}", StringUtil.valueOf(openId));
//    return url;
//  }
//
//  public static String vehicleEditUrl(Long wUserVehicleId) {
//    String url = WXHelper.getEvnDomain() + WXConstant.TO_VEHICLE_EDIT_URL;
//    return url.replace("{U_V_ID}", new BigInteger(StringUtil.valueOf(wUserVehicleId)).toString(36));
//  }
//
//  public static String articleDetail(Long id) {
//    String url = WXHelper.getEvnDomain() + WXConstant.TO_ARTICLE_DETAIL_URL;
//    return url.replace("{id}", new BigInteger(StringUtil.valueOf(id)).toString(36));
//  }
//


//
//  private void parseMenu(Document doc){
//    Element root = doc.getRootElement();
//      List elements = root.elements();
//      if (elements.size() == 0) {
//        throw new Exception("wx:menu xml content is empty!");
//      }
//      List<Button> buttons=new ArrayList<Button>();
//      Iterator it = elements.iterator();
//      while (it.hasNext()){
//        Element elem = (Element) it.next();
//        //菜单类型
//        String type=elem.attributeValue("type");
//        if(WXConstant.MENU_TYPE_CLICK.equals(type)){
//          ClickButton button=new ClickButton();
//          button.setName(elem.attributeValue("name"));
//          button.setKey(elem.attributeValue("key"));
//          buttons.add(button);
//        }else if(WXConstant.MENU_TYPE_VIEW.equals(type)){
//          ViewButton button=new ViewButton();
//          button.setName(elem.attributeValue("name"));
//          String text=elem.getTextTrim();
//          text=text.replace("{APP_ID}",accountDTO.getAppId()).replace("{PUBLIC_NO}",accountDTO.getPublicNo()).replace("{EVN_DOMAIN}",getEvnDomain());
//          button.setUrl(text);
//          buttons.add(button);
//        }else {
//          List<Element> complexElements=elem.elements();
//          if(CollectionUtil.isEmpty(complexElements))  throw new Exception("menu xml content error!");
//          Element subElement=complexElements.get(0);
//          List<Element> subElements=subElement.elements();
//          ComplexButton complexButton=new ComplexButton();
//          complexButton.setName(elem.attributeValue("name"));
//          //设置子菜单
//          List<Button> subButtons=new ArrayList<Button>();
//          for(int i=0;i<subElements.size();i++){
//            Element subElem=subElements.get(i);
//            String subType=subElem.attributeValue("type");
//            if(WXConstant.MENU_TYPE_CLICK.equals(subType)){
//              ClickButton button=new ClickButton();
//              button.setName(subElem.attributeValue("name"));
//              button.setKey(subElem.attributeValue("key"));
//              subButtons.add(button);
//            }else if(WXConstant.MENU_TYPE_VIEW.equals(subType)){
//              ViewButton button=new ViewButton();
//              button.setName(subElem.attributeValue("name"));
//              String text=subElem.getTextTrim();
//              text=text.replace("{APP_ID}",accountDTO.getAppId()).replace("{PUBLIC_NO}",accountDTO.getPublicNo()).replace("{EVN_DOMAIN}",getEvnDomain());
//              button.setUrl(text);
//              subButtons.add(button);
//            }
//          }
//          complexButton.setSub_button(subButtons.toArray(new Button[subButtons.size()]));
//          buttons.add(complexButton);
//        }
//      }
//  }



  /**
   * 处理前台输入的欢迎词到数据库标准字符
   *
   * @param htmlStr
   * @return
   */
  public static String handleWelcomeWordFromHtml(String htmlStr) {
    if (StringUtils.isBlank(htmlStr)) return null;
    String regEx_img = "<img.*?src=.*?>"; //图片
    Matcher img_matcher = Pattern.compile(regEx_img).matcher(htmlStr);
    while (img_matcher.find()) {
      String img = img_matcher.group();
      Matcher code_matcher = Pattern.compile("code=\".*?\"").matcher(img);
      if (code_matcher.find()) {
        String code = code_matcher.group().replace("code=", "").replace("\"", "");
        code = "{" + code + "}";
        htmlStr = htmlStr.replaceAll(img, code);
      }
    }
    htmlStr = htmlStr.replaceAll("\\s{4,}", "");
    return htmlStr;
  }

  /**
   * 处理数据库的欢迎词到前台显示
   *
   * @param welcomeWord
   * @return
   */
  public static String handleWelcomeWordToHtml(String welcomeWord) {
    if (StringUtils.isBlank(welcomeWord)) return null;
    String regEx_emotion = "\\{.*?\\}"; //数据库中表情结构 如{/微笑}
    Matcher emotion_matcher = Pattern.compile(regEx_emotion).matcher(welcomeWord);
    while (emotion_matcher.find()) {
      String org_emotion = emotion_matcher.group();
      String emotion = org_emotion.replace("{", "").replace("}", "");
      emotion = "<img src=\"./images/emotion" + emotion + ".gif\" code=\"" + emotion + "\">";
      welcomeWord = welcomeWord.replace(org_emotion, emotion);
    }
    return welcomeWord;
  }


  /**
   * 处理成微信发送的标准字符
   *
   * @param welcomeWord
   * @return
   */
  public static String toStandardWelcomeWord(String welcomeWord) {
    if (StringUtils.isBlank(welcomeWord)) return null;
    welcomeWord = welcomeWord.replace("{", "");
    welcomeWord = welcomeWord.replace("}", "");
    welcomeWord = welcomeWord.replace("<br>", "\n");
    welcomeWord = welcomeWord.replaceAll("&nbsp;", " ");
    return welcomeWord;
  }




}