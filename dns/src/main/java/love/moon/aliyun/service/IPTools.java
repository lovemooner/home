package love.moon.aliyun.service;


import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPTools {


    public static String getMyIP(String server) {
        String result=null;
        try {
            HttpResponse response= HttpUtil.sendBrowserGet(server);
            if(response.getCode()==200){
                result=response.getContent();
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String regEx = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(result);
        while (m.find()) {
            String ipStr = m.group();
            return ipStr;
        }
        return null;

    }



    }
