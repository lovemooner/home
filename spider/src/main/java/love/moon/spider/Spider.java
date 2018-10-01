package love.moon.spider;

import love.moon.spider.entity.Resource;
import love.moon.spider.entity.Subject;
import love.moon.spider.rule.maomi.IParser;
import love.moon.spider.rule.maomi.MaomiParser;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Spider {
    public static final Logger LOG = LoggerFactory.getLogger(Spider.class);

    public static void main(String[] args) {
        LOG.info("start Spider...");
        new Thread(() -> {
            IParser parser = new MaomiParser();
            List<Subject> subjects = parser.parse();
            if (CollectionUtils.isEmpty(subjects)) {
                return;
            }
            String path = Constants.SHARE_PATH;
            for (Subject subject : subjects) {
                List<Resource> resources = subject.getResources();
                String name = path + subject.getName();
                if (!CollectionUtils.isEmpty(resources)) {
                    File file = new File(name);
                    file.mkdir();
                    for (int i = 0; i < resources.size(); i++) {
                        try {
                            LOG.info("downloadPicture,url={}",resources.get(i).getAddress());
                            HttpUtil.downloadPicture(resources.get(i).getAddress(), name + "\\" + i+".jpg");
                        } catch (IOException e) {
                            LOG.error(e.getMessage(), e);
                        }

                    }

                }
            }
        }).start();

    }

}
