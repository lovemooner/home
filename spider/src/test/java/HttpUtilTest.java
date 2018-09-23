import love.moon.spider.HttpUtil;
import love.moon.common.HttpResponse;

import java.io.IOException;

public class HttpUtilTest {

    public static void main(String[] args) {
//        String url="https://www.782cf.com/enter/pc.html";
        String url="https://www.782cf.com/tupian/list-%E8%87%AA%E6%8B%8D%E5%81%B7%E6%8B%8D.html";
        try {
//            byte[] bytes=  HttpUtil.doGet(url);
//            System.out.println(new String(bytes));
            HttpResponse response=HttpUtil.sendGet(url);
            System.out.println(response.getCode());
            System.out.println(response.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
