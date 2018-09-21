package love.moon.aliyun.service;

import java.io.IOException;

public interface IDnsService {

    String describeDomains();

    String getDescribeDomainRecordsUrl();

    boolean updateDomainRecord(String newIP,Long recordId);

    Long getTargetRecordId() ;

    String getUpdateDomainRecordUrl(String newIP, Long recordId);
}
