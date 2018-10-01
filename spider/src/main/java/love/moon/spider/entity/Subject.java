package love.moon.spider.entity;

import love.moon.RegUtil;

import java.util.List;

public class Subject {
    private Long id;
    private String name;
    private String pageLink;
    private String publicTime;

    List<Resource> resources;

    public Subject(String result){
        this.name= RegUtil.matcher(result,"title=\".*?\"").replace("title=","").replace("\"","");
        this.pageLink= RegUtil.matcher(result,"/tupian/(\\d*)(.html)");
        this.publicTime= RegUtil.matcher(result,RegUtil.matcher(result,"\\d{4}-\\d{2}-\\d{2}"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageLink() {
        return pageLink;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
