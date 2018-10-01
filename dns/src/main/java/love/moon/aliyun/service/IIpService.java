package love.moon.aliyun.service;

import love.moon.aliyun.pojo.HomeIP;

public interface IIpService {

    String getLatestIp();

    HomeIP getCurrentIp();

    boolean updateIPTime(Long id);

    boolean saveLatestIP(String latestIP);
}
