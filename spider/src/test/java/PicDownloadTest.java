import love.moon.spider.Constants;
import love.moon.spider.HttpUtil;

import java.io.IOException;

public class PicDownloadTest {

    public static void main(String[] args) throws IOException {
        String url="https://mmtp1.com/jjtq/zipai/07/01.jpg";
//        String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538154970&di=4ec93cd028984cee6996fbf0b56be0d6&imgtype=jpg&er=1&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F150911%2F139-150911103203.jpg";
        String path = "C:\\Users\\ndong\\Desktop\\";
        String name="test.jpg";
        HttpUtil.downloadPicture1(url,path+ name );
    }
}

