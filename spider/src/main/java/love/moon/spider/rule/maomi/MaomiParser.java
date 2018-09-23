package love.moon.spider.rule.maomi;

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

  static   String regEx_subject = "<a[^>]*>([^<]*)</a>";
    String regEx_resouce = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";


    public static void main(String[] args) {
        String content="<li>\n" +
                "                                <a href=\"/tupian/16266.html\" title=\"女大学生绝对尤物，而且还露脸\" target=\"_blank\">\n" +
                "                                    <img class=\"videopic lazy\" data-original=\"https://mmtp1.com/jjtq/zipai/03/01.jpg\" data-prefix=\"\" src=\"https://mmtp1.com/jjtq/zipai/03/01.jpg\" style=\"\">\n" +
                "                                    <h3 title=\"女大学生绝对尤物，而且还露脸\" class=\"text-ellipsis\">女大学生绝对尤物，而且还露脸</h3>\n" +
                "                                    <span class=\"down_date c_red\">2018-09-22 </span>\n" +
                "                                </a>\n" +
                "                            </li>";
        Matcher m_subject = Pattern.compile(regEx_subject).matcher(content);
        while (m_subject.find()) {
            String str=m_subject.group();
            System.out.println(str);
        }
    }


    public List<Subject> parse() {
        Properties properties = null;
        try {
            properties = PropertiesUtil.loadFromClassPath("spider.properties");
            String url = properties.getProperty("url.maomiav");
            try {
                List<Subject> subjects = new ArrayList<Subject>();
                String content = HttpUtil.sendGet1(url);
                System.out.println(content);
                Matcher m_subject = Pattern.compile(regEx_subject).matcher(content);
                while (m_subject.find()) {
                    String link = m_subject.group();
                    Subject subject = new Subject();
                    subjects.add(subject);
                }
                if (CollectionUtils.isEmpty(subjects)) return null;
                Matcher m_resource = Pattern.compile(regEx_subject).matcher(content);
                for (Subject subject : subjects) {
                    content = HttpUtil.sendGet1(subject.getPageLink());
                    List<Resource> resources = new ArrayList<Resource>();
                    subject.setResources(resources);
                    while (m_resource.find()) {
                        String link = m_subject.group();
                        Resource resource = new Resource();
                        //TODO
                        resources.add(resource);
                    }
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
