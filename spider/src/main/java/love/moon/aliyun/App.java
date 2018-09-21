package love.moon.aliyun;

import love.moon.aliyun.pojo.HomeIP;
import love.moon.aliyun.service.IDnsService;
import love.moon.aliyun.service.IIpService;
import love.moon.aliyun.service.impl.DnsService;
import love.moon.aliyun.service.impl.IpService;
import love.moon.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class App {

    public static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static Properties properties;

    static {
        try {
            properties = PropertiesUtil.load("config.properties");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
      LOG.info("Domain Update Server Start");
        IIpService ipService = new IpService();
        IDnsService dnsService = new DnsService();
        while (true) {
            LOG.info("=======Start new Loop for Domain Update=========");
            String latestIp = ipService.getLatestIp();
            if (latestIp != null) {
                HomeIP homeIP = ipService.getCurrentIp();
                if (homeIP != null && latestIp.equals(homeIP.getIp())) {
                    ipService.updateIPTime(homeIP.getId());
                } else {
                    LOG.info("!!!!!!ip is charged!!!!!!");
                    ipService.saveLatestIP(latestIp);
                    Long recordId= dnsService.getTargetRecordId();
                    dnsService.updateDomainRecord(latestIp,recordId);
                }
            }
            try {
                LOG.info("Thread sleep,times={} ms",properties.getProperty("loop_time"));
                Thread.sleep(Long.valueOf(properties.getProperty("loop_time")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
