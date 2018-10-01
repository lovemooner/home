package love.moon.spider.rule.maomi;

import love.moon.RegUtil;
import love.moon.spider.HttpUtil;
import love.moon.spider.entity.Resource;
import love.moon.spider.entity.Subject;
import love.moon.util.PropertiesUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaomiParser implements IParser {

    public static final Logger LOG = LoggerFactory.getLogger(MaomiParser.class);

    String regEx_resource = "data-original=\".*?\"";

    String regEx_subject = "<a href=\"/tupian/(\\d*)(.html).+?</a>";


    public List<Subject> parse() {
        LOG.info("maomi Parser start parse");
        Properties properties = null;
        try {
            properties = PropertiesUtil.loadFromClassPath("spider.properties");
            String url = properties.getProperty("url.maomiav");
            try {
                List<Subject> subjects = new ArrayList<Subject>();
                String content = HttpUtil.sendGet1(url);
                content=content.replace("\n","");

                List<String> results=RegUtil.matchers(content,regEx_subject);
                for (String result:results) {
                    Subject subject = new Subject(result);
                    subjects.add(subject);
                }
                if (CollectionUtils.isEmpty(subjects)) return null;
                LOG.info("get subject ,size :{}",subjects.size());
                String domain= properties.getProperty("url.maomiav.domain");
                for (Subject subject : subjects) {
                    String page = HttpUtil.sendGet1(domain+subject.getPageLink());
                    List<String> pic_links= RegUtil.matchers(page,regEx_resource);
                    List<Resource> resources = new ArrayList<Resource>();
                    for (String pic_link:pic_links) {
                        Resource resource = new Resource();
                        resource.setAddress(pic_link.replace("data-original=","").replace("\"",""));
                        resources.add(resource);
                    }
                    subject.setResources(resources);
                }
                return subjects;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
