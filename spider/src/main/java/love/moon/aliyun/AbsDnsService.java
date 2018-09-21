package love.moon.aliyun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static com.aliyuncs.auth.AcsURLEncoder.percentEncode;
import static love.moon.util.DateUtil.getISO8601Time;

public class AbsDnsService {
    public static final Logger LOG = LoggerFactory.getLogger(AbsDnsService.class);
    public static final String DOMAIN_NAME = "ndong.top";
    public static final String SERVER_URL = "alidns.aliyuncs.com";
    private static final String ACCESS_KEY = "LTAIRZD5P9FcVFCu";
    private static final String ACCESS_SECRT = "WftELoH5b2CekWNbhXfsMBjyLiyq5p";
    private static final String SIG_METHOD = "HMAC-SHA1";
    private static final String SIG_VER = "1.0";
    private static final String ALIYUN_API_VER = "2015-01-09";

    // 这些是请求的固定参数
    protected Map<String, String> GetPublicParams() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("Format", "xml");
        parameters.put("Version", ALIYUN_API_VER);
        parameters.put("SignatureNonce", UUID.randomUUID().toString());
        parameters.put("SignatureMethod", SIG_METHOD);
        parameters.put("SignatureVersion", SIG_VER);
        parameters.put("AccessKeyId", ACCESS_KEY);
        parameters.put("Format", "JSON");
        parameters.put("Timestamp", getISO8601Time());

        return parameters;
    }


    protected String getRequestUrl(String HttpMethod, String host,
                                   Map<String, String> params) {
        final String SEPARATOR = "&";

        // param sort, Upper and Lower not equal
        String[] sortedKeys = params.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);

        // create stringToSign str
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(HttpMethod).append(SEPARATOR);

        try {
            stringToSign.append(percentEncode("/")).append(SEPARATOR);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String key : sortedKeys) {
            // encode key, value
            try {
                canonicalizedQueryString.append(SEPARATOR)
                        .append(percentEncode(key)).append("=")
                        .append(percentEncode(params.get(key)));
            } catch (UnsupportedEncodingException e) {
                LOG.error(e.getMessage(),e);
            }
        }

        // encode canonicalizedQueryString
        try {
            stringToSign.append(percentEncode(canonicalizedQueryString.toString().
                    substring(1)));
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(),e);
        }

        StringBuilder httpRequest = new StringBuilder();
        try {
            String signature = GetSignature(stringToSign.toString());
            httpRequest.append("http://");
            httpRequest.append(host);
            httpRequest.append("/?");
            httpRequest.append(canonicalizedQueryString.toString().substring(1));
            httpRequest.append("&Signature=");
            httpRequest.append(percentEncode(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpRequest.toString();
    }

    // 通过API文档可以查询具体的签名机制
    private static String GetSignature(String stringToSign) {
        // signature test
        final String ALGORITHM = "HmacSHA1";
        final String ENCODING = "UTF-8";
        String key = ACCESS_SECRT + "&";
        Mac mac = null;
        byte[] signData = null;

        try {
            mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(
                    key.getBytes(ENCODING), ALGORITHM));
            signData = mac.doFinal(
                    stringToSign.getBytes(ENCODING));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(signData);
    }
}
