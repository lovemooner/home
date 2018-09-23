import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {

    public static void main(String[] args) {
        String content="<a href=\"http://www.baidu.com\">链接111111111</a> ";
        String regEx = "<a[^>]*>([^<]*)</a>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(content);
        while (m.find()) {
            String link = m.group();
            System.out.println(link);
        }
    }
}
