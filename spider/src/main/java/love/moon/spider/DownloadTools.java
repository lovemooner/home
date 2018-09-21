package love.moon.spider;

import love.moon.Constants;

import java.io.*;
import java.net.URL;

public class DownloadTools {


    //链接url下载图片
    private static void downloadPicture(String url, String filename) throws IOException {

        FileOutputStream fileOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new URL(url).openStream());
            fileOutputStream = new FileOutputStream(new File(Constants.SHARE_PATH + filename));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context = output.toByteArray();
            fileOutputStream.write(output.toByteArray());
        } finally {
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }



    }


    public static void main(String[] args) {
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538154970&di=4ec93cd028984cee6996fbf0b56be0d6&imgtype=jpg&er=1&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F150911%2F139-150911103203.jpg";
        String fileName = "test.jpg";
        try {
            downloadPicture(url, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
