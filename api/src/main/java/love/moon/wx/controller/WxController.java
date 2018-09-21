package love.moon.wx.controller;

import love.moon.wx.service.IWxService;
import love.moon.wx.service.impl.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("")
public class WxController {

    private Logger LOG = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private WxService wxService;

    @RequestMapping(value = "/checkSignature", method = RequestMethod.GET)
    public  void checkSignature(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("receive checkSignature request");
        PrintWriter out = null;
        try {
            //微信加密签名
            String signature = request.getParameter("signature");
            //时间戳
            String timestamp = request.getParameter("timestamp");
            //随机数
            String nonce = request.getParameter("nonce");
            //随机字符串
            String echostr = request.getParameter("echostr");
            if (!wxService.checkSignature(signature, timestamp, nonce)) {
                LOG.warn("check signature failed");
                return ;
            }
            out = response.getWriter();
            out.print(echostr);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String index(HttpServletRequest request) {
        LOG.info("LOG:read to start Thread");
       return "hi";
    }


}
