package love.moon;

import love.moon.aliyun.App;
import love.moon.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class Constants {

    public static  String SHARE_PATH;







    public static final Logger LOG = LoggerFactory.getLogger(Constants.class);

    static {
        try {
            Properties properties = PropertiesUtil.load("config.properties");
            SHARE_PATH=properties.getProperty("share.path");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
