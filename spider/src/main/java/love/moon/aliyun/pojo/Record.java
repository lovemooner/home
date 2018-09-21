package love.moon.aliyun.pojo;

public class Record {
    private String RR;
    private String DomainName;
    private String Status;
    private String TTL;
    private Long RecordId;

    public String getRR() {
        return RR;
    }

    public void setRR(String RR) {
        this.RR = RR;
    }

    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String domainName) {
        DomainName = domainName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTTL() {
        return TTL;
    }

    public void setTTL(String TTL) {
        this.TTL = TTL;
    }

    public Long getRecordId() {
        return RecordId;
    }

    public void setRecordId(Long recordId) {
        RecordId = recordId;
    }
}
