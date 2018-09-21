import love.moon.HttpUtil;
import love.moon.common.HttpResponse;

import java.io.IOException;

public class HttpUtilTest {

    public static void main(String[] args) {
        String url="https://www.782cf.com/index/home.html";
//        String url="https://www.baidu.com";
        try {
//            byte[] bytes=  HttpUtil.doGet(url);
//            System.out.println(new String(bytes));
            HttpResponse response=HttpUtil.sendGet(url);
            System.out.println(response.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
