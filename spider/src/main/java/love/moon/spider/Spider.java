package love.moon.spider;

import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;

import java.io.IOException;

public class Spider {

    public static void main(String[] args) {
//        String url="https://www.782cf.com/index/home.html";
        String url="https://www.cnblogs.com/lichmama/p/6780298.html";
        try {
            HttpResponse response= HttpUtil.sendGet(url);
            System.out.println(response.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
