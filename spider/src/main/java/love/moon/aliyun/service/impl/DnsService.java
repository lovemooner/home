package love.moon.aliyun.service.impl;

import love.moon.aliyun.AbsDnsService;
import love.moon.aliyun.pojo.GetResult;
import love.moon.aliyun.pojo.Record;
import love.moon.aliyun.service.IDnsService;
import love.moon.common.HttpResponse;
import love.moon.util.HttpUtil;
import love.moon.util.JsonUtil;
import love.moon.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class DnsService extends AbsDnsService implements IDnsService {
    public static final Logger LOG = LoggerFactory.getLogger(DnsService.class);

    public String describeDomains() {
        Map<String, String> parameters = GetPublicParams();
        parameters.put("Action", "DescribeDomains");
        parameters.put("DomainName", DOMAIN_NAME);
        parameters.put("PageNumber", "1");
        parameters.put("PageSize", "20");
        return getRequestUrl("GET", SERVER_URL, parameters);
    }

    public Long getTargetRecordId()   {
        HttpResponse response = null;
        try {
            response = HttpUtil.sendGet(getDescribeDomainRecordsUrl());
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
            return null;
        }
        GetResult result = JsonUtil.jsonToObj(response.getContent(), GetResult.class);
        List<Record> records = result.getDomainRecords().getRecord();
        for (Record record : records) {
            if ("@".equals(record.getRR())) {
                return record.getRecordId();
            }
        }
        return null;
    }


    public boolean updateDomainRecord(String newIP, Long recordId) {

        if(recordId==null|| StringUtil.isEmpty(newIP)) {
            return false;
        }

        try {
            LOG.info("Start update Domain Record ");
            String updateUrl = getUpdateDomainRecordUrl(newIP, recordId);
            HttpResponse response = sendGet(updateUrl);
            if (response.getCode() == 200) {
                LOG.info("Aliyun-updateDomainRecord succcess,newIP:{}", newIP);
                return true;
            } else {
                LOG.error("updateDomainRecord failed,code:{}", response.getCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static HttpResponse sendGet(String url) throws IOException {
        String result = "";
        HttpResponse response = new HttpResponse();
        URL realURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realURL.openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
        int responseCode = conn.getResponseCode();
        Map<String, List<String>> map = conn.getHeaderFields();
        response.setCode(responseCode);
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
            response.setContent(result);
        }
        return response;
    }


    /**
     * 修改解析记录
     *
     * @return
     */
    public String getUpdateDomainRecordUrl(String newIP, Long recordId) {
        Map<String, String> parameters = GetPublicParams();
        parameters.put("Action", "UpdateDomainRecord");
        parameters.put("RecordId", String.valueOf(recordId));
        parameters.put("RR", "@");
        parameters.put("Type", "A");
        parameters.put("Value", newIP);
        parameters.put("TTL", "600");
        parameters.put("Line", "default");
        return getRequestUrl("GET", SERVER_URL, parameters);
    }

    /**
     * 获取解析记录列表
     *
     * @return
     */
    public String getDescribeDomainRecordsUrl() {
        Map<String, String> parameters = GetPublicParams();
        parameters.put("Action", "DescribeDomainRecords");
        parameters.put("DomainName", DOMAIN_NAME);
        parameters.put("PageNumber", "1");
        parameters.put("PageSize", "20");
        return getRequestUrl("GET", SERVER_URL, parameters);
    }


    // 修改解析记录
    public String getUpdateDomainRecord(String RecordID, String RR,
                                        String Type, String Value) {
        Map<String, String> parameters = GetPublicParams();
        // insert params
        parameters.put("Action", "UpdateDomainRecord");
        parameters.put("RecordId", RecordID);
        parameters.put("RR", RR);
        parameters.put("Type", Type);
        parameters.put("Value", Value);

        return getRequestUrl("GET", SERVER_URL, parameters);
    }
}
