import love.moon.aliyun.service.IDnsService;
import love.moon.aliyun.service.impl.DnsService;
import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static love.moon.util.HttpUtil.getCharsetName;

public class Test {

    public static void main(String[] args) throws IOException {
//        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
//        System.setProperty("http.proxyHost", "cn-proxy.jp.oracle.com");
//        System.setProperty("http.proxyPort", "80");
        IDnsService dnsService = new DnsService();
//        String url = dnsService.describeDomainRecords();
        String ip = "202.106.0.201";
        Long recordId=4070531277964288l;
        boolean result = updateDomainRecord(ip,recordId);
    }

    public static boolean updateDomainRecord(String newIP, Long recordId) {
        try {
            IDnsService dnsService = new DnsService();
            String updateUrl = dnsService.getUpdateDomainRecordUrl(newIP, recordId);
           String response = sendGet1(updateUrl);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static HttpResponse sendGet(String url) throws IOException {
        String result = "";
        HttpResponse response = new HttpResponse();
        URL realURL = new URL(url);
        URLConnection conn = realURL.openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
        conn.connect();
        Map<String, List<String>> map = conn.getHeaderFields();
        for (String s : map.keySet()) {
            System.out.println(s + "-->" + map.get(s));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += "\n" + line;
        }
        response.setContent(result);

        return response;
    }

    public static String sendGet1(String url) {
        HttpURLConnection con = null;
        try {
            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            // con.setRequestProperty("User-Agent", USER_AGENT);
            //                    Map<String,String> propertys=new HashMap<String, String>();
//                    propertys.put("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("User-Agent", "curl/7.29.0");
            int responseCode = con.getResponseCode();
            InputStream is= con.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String inputLine;
            StringBuffer sb = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
//                con.disconnect();
            }
        }
        return null;

    }

    /**
     * 发送GET请求
     *
     * @param urlString URL地址
     * @return 响应对象
     * @throws IOException
     */
    public static HttpResponse sendBrowserGet(String urlString)
            throws IOException {
        Map<String, String> propertys = new HashMap<String, String>();
        propertys.put("accept", "image/webp,image/apng,image/*,*/*;q=0.8");
        propertys.put("connection", "keep-alive");
        propertys.put("Content-Type", "application/json; charset=UTF-8");
        propertys.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        propertys.put("Accept-Encoding", "gzip, deflate, br");
        propertys.put("user-agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        return HttpUtil.send(urlString, "GET", null, propertys);
    }


    public static HttpResponse send(String urlString, String method) throws IOException {
        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setRequestProperty("Charset", getCharsetName());

        return getContent(urlString, urlConnection);
    }

    private static HttpResponse getContent(String urlString, HttpURLConnection urlConnection) throws IOException {
        HttpResponse httpResponse = new HttpResponse();

        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, getCharsetName()));
            Vector<String> contentVector = new Vector();
            httpResponse.setContentCollection(contentVector);
            StringBuffer temp = new StringBuffer();

            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                contentVector.add(line);
                temp.append(line);
            }

            bufferedReader.close();
            httpResponse.setUrlString(urlString);
            int port = urlConnection.getURL().getDefaultPort();
            httpResponse.setDefaultPort(port);
            httpResponse.setFile(urlConnection.getURL().getFile());
            httpResponse.setHost(urlConnection.getURL().getHost());
            httpResponse.setPath(urlConnection.getURL().getPath());
            httpResponse.setPort(urlConnection.getURL().getPort());
            httpResponse.setProtocol(urlConnection.getURL().getProtocol());
            httpResponse.setQuery(urlConnection.getURL().getQuery());
            httpResponse.setRef(urlConnection.getURL().getRef());
            httpResponse.setUserInfo(urlConnection.getURL().getUserInfo());
            httpResponse.setContent(temp.toString());
            httpResponse.setContentEncoding(getCharsetName());
            httpResponse.setCode(urlConnection.getResponseCode());
            httpResponse.setCookie(urlConnection.getHeaderField("set-cookie"));
            httpResponse.setMessage(urlConnection.getResponseMessage());
            httpResponse.setContentType(urlConnection.getContentType());
            httpResponse.setMethod(urlConnection.getRequestMethod());
            httpResponse.setConnectTimeout(urlConnection.getConnectTimeout());
            httpResponse.setReadTimeout(urlConnection.getReadTimeout());
            HttpResponse var9 = httpResponse;
            return var9;
        } catch (IOException var13) {
            throw var13;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }
    }
}
