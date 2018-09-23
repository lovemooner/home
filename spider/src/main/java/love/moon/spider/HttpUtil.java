package love.moon.spider;

import love.moon.common.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;


public class HttpUtil {

    public static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

    private static final class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    private static HttpsURLConnection getHttpsURLConnection(String uri, String method) throws IOException {
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SSLSocketFactory ssf = ctx.getSocketFactory();

        URL url = new URL(uri);
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setSSLSocketFactory(ssf);
        httpsConn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        httpsConn.setRequestMethod(method);
        httpsConn.setDoInput(true);
        httpsConn.setDoOutput(true);
        return httpsConn;
    }

    private static byte[] getBytesFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] kb = new byte[1024];
        int len;
        while ((len = is.read(kb)) != -1) {
            baos.write(kb, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        baos.close();
        is.close();
        return bytes;
    }

    private static void setBytesToStream(OutputStream os, byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        byte[] kb = new byte[1024];
        int len;
        while ((len = bais.read(kb)) != -1) {
            os.write(kb, 0, len);
        }
        os.flush();
        os.close();
        bais.close();
    }

    public static byte[] doGet(String uri) throws IOException {
        HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "GET");
        return getBytesFromStream(httpsConn.getInputStream());
    }

    public static byte[] doPost(String uri, String data) throws IOException {
        HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "POST");
        setBytesToStream(httpsConn.getOutputStream(), data.getBytes());
        return getBytesFromStream(httpsConn.getInputStream());
    }

    public static HttpResponse sendGet(String url) throws IOException {
        String result = "";
        HttpResponse response = new HttpResponse();
        URL realURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realURL.openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
        conn.connect();
        Map<String, List<String>> map = conn.getHeaderFields();
        for (String s : map.keySet()) {
//            System.out.println(s + "-->" + map.get(s));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += "\n" + line;
        }
        response.setContent(result);
        response.setCode(conn.getResponseCode());
        return response;
    }

    public static String sendGet1(String url) throws Exception {
        HttpResponse response = sendGet(url);
        if (response.getCode() == 200) {
            return response.getContent();
        } else {
            throw  new Exception("Fetch failed,code:"+ response.getCode()+",url"+url);
        }
    }

    public static void downloadPicture(String url, String filename) throws IOException {

        FileOutputStream fileOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new URL(url).openStream());
            fileOutputStream = new FileOutputStream(new File(Constants.SHARE_PATH + filename));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context = output.toByteArray();
            fileOutputStream.write(output.toByteArray());
        } finally {
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }


    }

    public static void main(String[] args) {
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538154970&di=4ec93cd028984cee6996fbf0b56be0d6&imgtype=jpg&er=1&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F150911%2F139-150911103203.jpg";
        String fileName = "test.jpg";
        try {
            downloadPicture(url, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}