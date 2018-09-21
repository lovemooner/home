package love.moon.aliyun.pojo;


public class GetResult {
    private Integer PageNumber;
    private Integer TotalCount;
    private Integer PageSize;
    private String RequestId;

    private DomainRecords  DomainRecords;

    public Integer getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        PageNumber = pageNumber;
    }

    public Integer getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(Integer totalCount) {
        TotalCount = totalCount;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public love.moon.aliyun.pojo.DomainRecords getDomainRecords() {
        return DomainRecords;
    }

    public void setDomainRecords(love.moon.aliyun.pojo.DomainRecords domainRecords) {
        DomainRecords = domainRecords;
    }
}
