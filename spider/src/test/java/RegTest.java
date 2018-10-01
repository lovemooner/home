import love.moon.RegUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

    static void test1() {
        String regEx_subject = "<a href=\"/tupian/(\\d*)(.html).+?</a>";
        String content = "test<li>\n" +
                "                                <a href=\"/tupian/16266.html\" title=\"女大学生绝对尤物，而且还露脸\" target=\"_blank\">\n" +
                "                                    <img class=\"videopic lazy\" data-original=\"https://mmtp1.com/jjtq/zipai/03/01.jpg\" data-prefix=\"\" src=\"https://mmtp1.com/jjtq/zipai/03/01.jpg\" style=\"\">\n" +
                "                                    <h3 title=\"女大学生绝对尤物，而且还露脸\" class=\"text-ellipsis\">女大学生绝对尤物，而且还露脸</h3>\n" +
                "                                    <span class=\"down_date c_red\">2018-09-22 </span>\n" +
                "                                </a>\n<a href=\"/tupian/list-自拍偷拍.html\" target=\"_blank\"> 自拍偷拍 </a>" +
                "                            </li>aaaooooo";
        content=content+content;
        content = content.replace("\n", "");
        Matcher m_subject = Pattern.compile(regEx_subject).matcher(content);
        while (m_subject.find()) {
            String str = m_subject.group();
            System.out.println(str);
        }
    }

    static void test2(String str) {
        str = str.replace("\n", "");
        String reg = "<a href=\"/tupian/(\\d*)(.html).+?</a>";
        String reg1= "(\\d*)(.html)";
        Matcher matcher = Pattern.compile(reg).matcher(str);
        while (matcher.find()) {
             str=matcher.group();
//            System.out.println(str);
            System.out.println(RegUtil.matcher(str,"(\\d*)(.html)"));
            System.out.println(RegUtil.matcher(str,"title=\".*?\"").replace("title=",""));
            System.out.println(RegUtil.matcher(str,"\\d{4}-\\d{2}-\\d{2}"));
        }
    }

    static void test3() {
        String str1 = "sssabcssabcsssabcss";
        str1 = str1.replace("\n", "");
        String reg = "a.*?c";

        Matcher matcher = Pattern.compile(reg).matcher(str1);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }


    public static void main(String[] args) {
        test2(str);
    }

    static String str="<html lang=\"cn\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "<meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width\">\n" +
            "<link rel=\"stylesheet\" href=\"/assets/css/common/style.css\"/>\n" +
            "<link rel=\"stylesheet\" href=\"/assets/css/custom/main.css\"/>\n" +
            "<link rel=\"stylesheet\" href=\"/assets/css/custom/banner.css\"/>\n" +
            "<link rel=\"stylesheet\" href=\"/assets/css/custom/header.css\"/>\n" +
            "<link rel=\"stylesheet\" href=\"/assets/css/custom/menu.css\"/>\n" +
            "<link rel=\"stylesheet\" href=\"/assets/css/custom/footer.css\"/>\n" +
            "<link rel=\"stylesheet\" href=\"../assets/css/custom/banner.css\"/>\n" +
            "<link rel=\"stylesheet\" href=\"../assets/css/common/flickity.min.css\"/>\n" +
            "<link rel=\"stylesheet\" id=\"themeLink\" href=\"/assets/css/theme/default.css\"/>\n" +
            "<link rel=\"icon\" type=\"image/x-icon\" class=\"js-site-favicon\" href=\"/assets/images/theme/default/maomi_32x32.png\">\n" +
            "<script src=\"/assets/js/common/juqery/jquery.js\"></script>\n" +
            "<script src=\"/assets/js/common/art-template.js\"></script>\n" +
            "<script src=\"/assets/js/common/common.js\"></script>\n" +
            "<script src=\"/assets/js/common/repaint.js\"></script>\n" +
            "<script src=\"/assets/js/common/LazyLoad.js\"></script>\n" +
            "<script src=\"/assets/js/common/config.js\"></script>\n" +
            "<script src=\"/discor_list.js\"></script>\n" +
            "<title>\n" +
            "    福利图片-自拍偷拍-猫咪AV</title>\n" +
            "<meta name=\"keywords\" content=\"福利图片-自拍偷拍-猫咪AV\"/>\n" +
            "<meta name=\"description\" content=\"福利图片-自拍偷拍-猫咪AV\"/>\n" +
            "<script src=\"../assets/js/common/flickity.pkgd.min.js\"></script>\n" +
            "<script src=\"/assets/js/common/shareNative_ec.js\"></script>\n" +
            "<script src=\"/assets/js/common/discor/discor.js\"></script>\n" +
            "<script src=\"/assets/js/common/discor.js\"></script>\n" +
            "<!--统计-->\n" +
            "<!-- Global site tag (gtag.js) - Google Analytics -->\n" +
            "<script async src=\"https://www.googletagmanager.com/gtag/js?id=UA-126205200-1\"></script>\n" +
            "<script>\n" +
            "    window.dataLayer = window.dataLayer || [];\n" +
            "\n" +
            "    function gtag() {\n" +
            "        dataLayer.push(arguments);\n" +
            "    }\n" +
            "\n" +
            "    gtag('js', new Date());\n" +
            "    gtag('config', 'UA-126205200-1');\n" +
            "\n" +
            "\n" +
            "    // 百度统计\n" +
            "    var _hmt = _hmt || [];\n" +
            "    (function () {\n" +
            "        var hm = document.createElement(\"script\");\n" +
            "        hm.src = \"https://hm.baidu.com/hm.js?427f72ce75b0677eb10f24419484eb80\";\n" +
            "        var s  = document.getElementsByTagName(\"script\")[0];\n" +
            "        s.parentNode.insertBefore(hm, s);\n" +
            "    })();\n" +
            "</script>\n" +
            "\n" +
            "    <link rel=\"stylesheet\" href=\"/assets/css/custom/img_list.css\"/>\n" +
            "</head>\n" +
            "<body>\n" +
            "    \n" +
            "    <div class=\"maomi-content\"> \n" +
            "        <section class=\"section section-banner\">\n" +
            "\n" +
            "</section>\n" +
            "<script id=\"tpl-user\" type=\"text/html\">\n" +
            "    <div class=\"row {{bannerInfo.key}}\">\n" +
            "        <div class=\"content-group flickity-gallery\">\n" +
            "            {{each bannerInfo.content}}\n" +
            "            <a href=\"{{$value.url}}\" target=\"_blank\" class=\"content-item content-img bg-default banner\">\n" +
            "                <div class=\"content-item content-img bg-default banner\" style=\"background-image:url({{$value.img}})\">\n" +
            "\n" +
            "                </div>\n" +
            "            </a>\n" +
            "            {{/each}}\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</script>\n" +
            "<script src=\"/banner_list.js\"></script>\n" +
            "<script>\n" +
            "    // document.domain = \"maomiav.com/banner.html\";\n" +
            "    $(function () {\n" +
            "        function loadFlickity() {\n" +
            "            $('.flickity-gallery').flickity({\n" +
            "                contain        : true,\n" +
            "                prevNextButtons: true,\n" +
            "                pageDots       : true,\n" +
            "                accessibility  : true,\n" +
            "                autoPlay       : 5000,\n" +
            "                fullscreen     : true,\n" +
            "                wrapAround     : true,\n" +
            "                lazyLoad       : 1\n" +
            "            });\n" +
            "        }\n" +
            "\n" +
            "        var isMobile = function () {\n" +
            "            var check = false;\n" +
            "            (function (a) {\n" +
            "                if (/(android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino|android|ipad|playbook|silk/i.test(a) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-/i.test(a.substr(0, 4))) check = true;\n" +
            "            })(navigator.userAgent || navigator.vendor || window.opera);\n" +
            "            return check;\n" +
            "        };\n" +
            "\n" +
            "        function bindBannerData() {\n" +
            "            let bannerInfo     = {\n" +
            "                key    : \"banner\",\n" +
            "                content: []\n" +
            "            }\n" +
            "\n" +
            "            if (isMobile()) {\n" +
            "                bannerInfo.content = wap_banner;\n" +
            "            }else{\n" +
            "                bannerInfo.content = pc_banner;\n" +
            "            }\n" +
            "\n" +
            "\n" +
            "            let html = template('tpl-user', {\n" +
            "                bannerInfo: bannerInfo\n" +
            "            });\n" +
            "            $(\".section-banner\").html(html);\n" +
            "        }\n" +
            "\n" +
            "        function toAudPages(i) {\n" +
            "            console.log(i);\n" +
            "        }\n" +
            "\n" +
            "        bindBannerData();\n" +
            "\n" +
            "        loadFlickity();\n" +
            "        resetParentFrame('banner_iframe');\n" +
            "    })\n" +
            "</script>\n" +
            "\n" +
            "    <header class=\"container header-container border_bootom\">\n" +
            "        <div class=\"row border_bootom\">\n" +
            "            <div class=\"text-right\">\n" +
            "                <p class=\"c_red\">男人不识本站，上遍色站也枉然</p>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"header_desc size_m\">\n" +
            "                <a href=\"/index/home.html\">\n" +
            "                    <div class=\"header_title size_xxxl c_pink\">\n" +
            "                        MAOMIAV.COM\n" +
            "                    </div>\n" +
            "                </a>\n" +
            "                <p class=\"c_red\">\n" +
            "                    <a href=\"/help/help.html\" class=\"c_red\" target=\"_blank\">提示：点击查看永久收藏和无法观看电影说明</a>\n" +
            "                    <!--<a href=\"javascript:toHeightScreen()\" class=\"c_red\">【HD】高清电影下载</a></p>-->\n" +
            "            </div>\n" +
            "            <div class=\"share_box\" onclick=\"configShare();\">\n" +
            "                <div class=\"share_img\"></div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </header>\n" +
            "        <section class=\"container section section-menu\" id=\"section-menu\">\n" +
            "    <div class=\"row <{menuInfo.key}>-row\">\n" +
            "                <div class=\"row-item odd\">\n" +
            "            <div class=\"row-item-title bg_red\"><a href=\"/index/home.html\" class='c_white' target=\"_blank\">在线电影</a></div>\n" +
            "            <ul class=\"row-item-content\">\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/shipin/index.html\" target=\"_blank\"> 手机播放 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/shipin/index.html\" target=\"_blank\"> 在线视频 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/shipin/index.html\" target=\"_blank\"> 中文配音 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/shipin/index.html\" target=\"_blank\"> 中文电影 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/shipin/index.html\" target=\"_blank\"> 丝袜诱惑 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/shipin/index.html\" target=\"_blank\"> 美臀美颜 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/shipin/index.html\" target=\"_blank\"> 萝莉写真 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/shipin/index.html\" target=\"_blank\"> 长腿丝袜 </a>\n" +
            "                </li>\n" +
            "                            </ul>\n" +
            "        </div>\n" +
            "                <div class=\"row-item even\">\n" +
            "            <div class=\"row-item-title bg_red\"><a href=\"/index/home.html\" class='c_white' target=\"_blank\">手机下载</a></div>\n" +
            "            <ul class=\"row-item-content\">\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiazai/list-亚洲电影.html\" target=\"_blank\"> 亚洲电影 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiazai/list-欧美电影.html\" target=\"_blank\"> 欧美电影 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiazai/list-制服丝袜.html\" target=\"_blank\"> 制服丝袜 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiazai/list-强奸乱伦.html\" target=\"_blank\"> 强奸乱伦 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiazai/list-国产自拍.html\" target=\"_blank\"> 国产自拍 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiazai/list-变态另类.html\" target=\"_blank\"> 变态另类 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiazai/list-经典三级.html\" target=\"_blank\"> 经典三级 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiazai/list-成人动漫.html\" target=\"_blank\"> 成人动漫 </a>\n" +
            "                </li>\n" +
            "                            </ul>\n" +
            "        </div>\n" +
            "                <div class=\"row-item odd\">\n" +
            "            <div class=\"row-item-title bg_red\"><a href=\"/index/home.html\" class='c_white' target=\"_blank\">激情图区</a></div>\n" +
            "            <ul class=\"row-item-content\">\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/tupian/list-自拍偷拍.html\" target=\"_blank\"> 自拍偷拍 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/tupian/list-亚洲色图.html\" target=\"_blank\"> 亚洲色图 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/tupian/list-欧美色图.html\" target=\"_blank\"> 欧美色图 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/tupian/list-美腿丝袜.html\" target=\"_blank\"> 美腿丝袜 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/tupian/list-清纯唯美.html\" target=\"_blank\"> 清纯唯美 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/tupian/list-乱伦熟女.html\" target=\"_blank\"> 乱伦熟女 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/tupian/list-卡通动漫.html\" target=\"_blank\"> 卡通动漫 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 极品美女 </a>\n" +
            "                </li>\n" +
            "                            </ul>\n" +
            "        </div>\n" +
            "                <div class=\"row-item even\">\n" +
            "            <div class=\"row-item-title bg_red\"><a href=\"/index/home.html\" class='c_white' target=\"_blank\">撸撸图区</a></div>\n" +
            "            <ul class=\"row-item-content\">\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 高清美女 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 美女诱惑 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 推女郎图 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 极品诱惑 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 巨乳诱惑 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 撸管图片 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 打飞机区 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/meinv/index.html\" target=\"_blank\"> 极品美女 </a>\n" +
            "                </li>\n" +
            "                            </ul>\n" +
            "        </div>\n" +
            "                <div class=\"row-item odd\">\n" +
            "            <div class=\"row-item-title bg_red\"><a href=\"/index/home.html\" class='c_white' target=\"_blank\">情色小说</a></div>\n" +
            "            <ul class=\"row-item-content\">\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiaoshuo/list-都市激情.html\" target=\"_blank\"> 都市激情 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiaoshuo/list-人妻交换.html\" target=\"_blank\"> 人妻交换 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiaoshuo/list-校园春色.html\" target=\"_blank\"> 校园春色 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiaoshuo/list-家庭乱伦.html\" target=\"_blank\"> 家庭乱伦 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiaoshuo/list-情色笑话.html\" target=\"_blank\"> 情色笑话 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiaoshuo/list-性爱技巧.html\" target=\"_blank\"> 性爱技巧 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiaoshuo/list-武侠古典.html\" target=\"_blank\"> 武侠古典 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/xiaoshuo/list-另类小说.html\" target=\"_blank\"> 另类小说 </a>\n" +
            "                </li>\n" +
            "                            </ul>\n" +
            "        </div>\n" +
            "                <div class=\"row-item even\">\n" +
            "            <div class=\"row-item-title bg_red\"><a href=\"/index/home.html\" class='c_white' target=\"_blank\">有声小说</a></div>\n" +
            "            <ul class=\"row-item-content\">\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/yousheng/index.html\" target=\"_blank\"> 有声小说 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/yousheng/index.html\" target=\"_blank\"> 性爱录音 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/yousheng/index.html\" target=\"_blank\"> 有声连载 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/yousheng/index.html\" target=\"_blank\"> 色情小说 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/yousheng/index.html\" target=\"_blank\"> 金鳞全集 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/yousheng/index.html\" target=\"_blank\"> 少妇白洁 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/yousheng/index.html\" target=\"_blank\"> 冰峰魔恋 </a>\n" +
            "                </li>\n" +
            "                                <li class=\"item\">\n" +
            "                    <a href=\"/yousheng/index.html\" target=\"_blank\"> 天地之间 </a>\n" +
            "                </li>\n" +
            "                            </ul>\n" +
            "        </div>\n" +
            "                <div class=\"row-item <{(count($menus)+1)%2?'odd':'even'}>\">\n" +
            "            <div class=\"row-item-title bg_red\"><a href=\"/index/home.html\" class='c_white' target=\"_blank\">精品内容</a></div>\n" +
            "            <ul class=\"row-item-content\" id=\"section-menu-jingcai\">\n" +
            "\n" +
            "            </ul>\n" +
            "\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</section>\n" +
            "<script>\n" +
            "    $(function(){\n" +
            "        setTTjs(\"#tpl-user\");\n" +
            "        setJingCai();\n" +
            "    })\n" +
            "</script>\n" +
            "\n" +
            "        <div class=\"container top-ad-container\" id=\"photo-header-auxdter\"></div>\n" +
            "        <main class=\"container main-container img-list-index\" id=\"main-container\">\n" +
            "            <script>\n" +
            "                createFloatAd();\n" +
            "            </script>\n" +
            "            <div id=\"photo-audver-main\" class=\"photo-audver-main\"></div>\n" +
            "            <div class=\"text-list-html \">\n" +
            "                <div class=\"row nav-row\">\n" +
            "    <div class=\"box cat_pos clearfix\">\n" +
            "        <span class=\"cat_pos_l\">您的位置：\n" +
            "            <a href=\"/index/home.html\">首页</a>&nbsp;&nbsp;»&nbsp;&nbsp;\n" +
            "            <a href=\"javascript:;\">福利图片</a>&nbsp;&nbsp;»&nbsp;&nbsp;\n" +
            "            <a href=\"/tupian/list-自拍偷拍.html\">自拍偷拍</a>&nbsp;&nbsp;\n" +
            "                    </span>\n" +
            "    </div>\n" +
            "</div>\n" +
            "                <div class=\"box movie_list\">\n" +
            "                    <ul class=\"img-list-data\">\n" +
            "                        <div id=\"tpl-img-content\">\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/16461.html\" title=\"妲己熱辣身姿神情嫵媚,摸最大的奶，肏最肥的逼，撕最骚的丝袜\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmtp1.com/jjtq/zipai/07/01.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"妲己熱辣身姿神情嫵媚,摸最大的奶，肏最肥的逼，撕最骚的丝袜\" class=\"text-ellipsis\">妲己熱辣身姿神情嫵媚,摸最大的奶，肏最肥的逼，撕最骚的丝袜</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-24                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/16460.html\" title=\"车震卖房女经理，穿着白丝来给我草还真骚\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmtp1.com/jjtq/zipai/06/01.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"车震卖房女经理，穿着白丝来给我草还真骚\" class=\"text-ellipsis\">车震卖房女经理，穿着白丝来给我草还真骚</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-24                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/16267.html\" title=\"骚骚是不是你要的做爱类型\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmtp1.com/jjtq/zipai/04/01.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"骚骚是不是你要的做爱类型\" class=\"text-ellipsis\">骚骚是不是你要的做爱类型</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-22                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/16266.html\" title=\"女大学生绝对尤物，而且还露脸\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmtp1.com/jjtq/zipai/03/01.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"女大学生绝对尤物，而且还露脸\" class=\"text-ellipsis\">女大学生绝对尤物，而且还露脸</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-22                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/16110.html\" title=\"极品网友的美乳诱惑\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmtp1.com/jjtq/zipai/02/01.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"极品网友的美乳诱惑\" class=\"text-ellipsis\">极品网友的美乳诱惑</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-19                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/16109.html\" title=\"白嫩挺拔的少妇\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmtp1.com/jjtq/zipai/01/01.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"白嫩挺拔的少妇\" class=\"text-ellipsis\">白嫩挺拔的少妇</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-19                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14455.html\" title=\"小情妇越来越骚每天都想要[22P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785981372.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"小情妇越来越骚每天都想要[22P]\" class=\"text-ellipsis\">小情妇越来越骚每天都想要[22P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14454.html\" title=\"[原创首发][ID认证]与骚货白嫩情人在宾馆XO[25P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785975304.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"[原创首发][ID认证]与骚货白嫩情人在宾馆XO[25P]\" class=\"text-ellipsis\">[原创首发][ID认证]与骚货白嫩情人在宾馆XO[25P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14453.html\" title=\"[原创认证]肥臀丰乳大屁股女网友肥美一线比包子逼被我狂干[40P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785979698.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"[原创认证]肥臀丰乳大屁股女网友肥美一线比包子逼被我狂干[40P]\" class=\"text-ellipsis\">[原创认证]肥臀丰乳大屁股女网友肥美一线比包子逼被我狂干[40P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14452.html\" title=\"[原创投稿][鸽子]第五期：带风骚老婆游山玩水，丝袜高跟小B微露，丰满诱人[31P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785979721.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"[原创投稿][鸽子]第五期：带风骚老婆游山玩水，丝袜高跟小B微露，丰满诱人[31P]\" class=\"text-ellipsis\">[原创投稿][鸽子]第五期：带风骚老婆游山玩水，丝袜高跟小B微露，丰满诱人[31P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14451.html\" title=\"一天之计在于晨，满足骚妹子的淫荡胃口[13P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785976130.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"一天之计在于晨，满足骚妹子的淫荡胃口[13P]\" class=\"text-ellipsis\">一天之计在于晨，满足骚妹子的淫荡胃口[13P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14450.html\" title=\"自家情人拿出来溜溜[21P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785974663.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"自家情人拿出来溜溜[21P]\" class=\"text-ellipsis\">自家情人拿出来溜溜[21P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14449.html\" title=\"快點進來啦！熟女户外露出很喜歡穿著鞋子愛愛&hellip;[16P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785975010.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"快點進來啦！熟女户外露出很喜歡穿著鞋子愛愛&hellip;[16P]\" class=\"text-ellipsis\">快點進來啦！熟女户外露出很喜歡穿著鞋子愛愛&hellip;[16P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14448.html\" title=\"95後粉嫩女友，身材好到爆![26P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785979424.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"95後粉嫩女友，身材好到爆![26P]\" class=\"text-ellipsis\">95後粉嫩女友，身材好到爆![26P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14447.html\" title=\"骚妇爱吃哥哥的大鸡巴射出来的白色液体[12P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785972114.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"骚妇爱吃哥哥的大鸡巴射出来的白色液体[12P]\" class=\"text-ellipsis\">骚妇爱吃哥哥的大鸡巴射出来的白色液体[12P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14446.html\" title=\"還沒春天就發春了~該怎麼辦？小痘痘被主人舔到濕答答[20P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785975818.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"還沒春天就發春了~該怎麼辦？小痘痘被主人舔到濕答答[20P]\" class=\"text-ellipsis\">還沒春天就發春了~該怎麼辦？小痘痘被主人舔到濕答答[20P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14445.html\" title=\"爱你么么哒求哥哥摸我小穴刚刮净毛毛，求哥哥操我[20P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785976021.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"爱你么么哒求哥哥摸我小穴刚刮净毛毛，求哥哥操我[20P]\" class=\"text-ellipsis\">爱你么么哒求哥哥摸我小穴刚刮净毛毛，求哥哥操我[20P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14444.html\" title=\"夫妻性生活的重要...男人为什么喜欢少妇，因为你一拍屁股，她就知道换个姿势。[13P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785974984.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"夫妻性生活的重要...男人为什么喜欢少妇，因为你一拍屁股，她就知道换个姿势。[13P]\" class=\"text-ellipsis\">夫妻性生活的重要...男人为什么喜欢少妇，因为你一拍屁股，她就知道换个姿势。[13P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14443.html\" title=\"[原创]怀孕2个月的小情人来找我[14P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785971441.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"[原创]怀孕2个月的小情人来找我[14P]\" class=\"text-ellipsis\">[原创]怀孕2个月的小情人来找我[14P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                        <li>\n" +
            "                                <a href=\"/tupian/14442.html\" title=\"酒店双飞穿情趣内衣的小荡妇[13P]\" target=\"_blank\">\n" +
            "                                    <img class=\"videopic lazy\" data-original=\"https://mmslt1.com/uploads/thumb/20180916/15370785972136.jpg\" data-prefix=\"\" src=\"/assets/images/default/loading/248x355.jpg\">\n" +
            "                                    <h3 title=\"酒店双飞穿情趣内衣的小荡妇[13P]\" class=\"text-ellipsis\">酒店双飞穿情趣内衣的小荡妇[13P]</h3>\n" +
            "                                    <span class=\"down_date c_red\">\n" +
            "                                        2018-09-16                                </span>\n" +
            "                                </a>\n" +
            "                            </li>\n" +
            "                                                    </div>\n" +
            "                    </ul>\n" +
            "                    <div class=\"clear\"></div>\n" +
            "                    <div class=\"pagination\">\n" +
            "                        <a href=\"javascript:;\" title=\"首页\">首页</a> <a href=\"javascript:;\" title=\"上一页\">上一页</a> <strong class=\"hidden-xs\" href=\"javascript:;\">1</strong><a class=\"hidden-xs\" href=\"/tupian/list-自拍偷拍-2.html\" title=\"第\"2\"页\" >2</a><a class=\"hidden-xs\" href=\"/tupian/list-自拍偷拍-3.html\" title=\"第\"3\"页\" >3</a><a class=\"hidden-xs\" href=\"/tupian/list-自拍偷拍-4.html\" title=\"第\"4\"页\" >4</a><a class=\"hidden-xs\" href=\"/tupian/list-自拍偷拍-5.html\" title=\"第\"5\"页\" >5</a><a class=\"hidden-xs\" href=\"javascript:;\">...</a><a class=\"hidden-xs\" href=\"/tupian/list-自拍偷拍-21.html\" title=\"第\"21\"页\" >21</a> <a class=\"visible-xs\">1/21</a> <a href=\"/tupian/list-自拍偷拍-2.html\" title=\"下一页\">下一页</a> <a href='/tupian/list-自拍偷拍-21.html' title='尾页'>尾页</a>                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"clear-class\"></div>\n" +
            "            </div>\n" +
            "            <div id=\"photo-audver-main-foot\" class=\"photo-audver-main\"></div>\n" +
            "            <div class=\"photo-auxdver-foot\" id=\"photo-auxdver-foot\"></div>\n" +
            "            <script>\n" +
            "                createFootAd();\n" +
            "            </script>\n" +
            "            <div class=\"clear-class\"></div>\n" +
            "        </main>\n" +
            "        <footer class=\"container footer-container c_default\">\n" +
            "    <div class=\"row\">\n" +
            "      <a href=\"/help/help.html\"> <span class=\"c_red\">提示：点击查看永久收藏和无法观看电影说明</span> &nbsp;|&nbsp;<a href=\"javascript:close_discor();\">关闭飘浮</a></a>\n" +
            "    </div>\n" +
            "    <div class=\"row\">警告：如果您未滿18歲或您當地法律許可之法定年齡、或是對情色反感或是衛道人士建議您離開本站！ 本站歸類為限制級、限定為成年者已具有完整行為能力且願接受本站內影音內容、及各項條款之網友才可瀏覽，未滿18歲謝絕進入。</div>\n" +
            "    <div class=\"row\"> 本站已遵照「iWIN網路內容防護機構」進行分類，如有家長發現未成年兒童／少年瀏覽此站、請按照此方法過濾本站內容 >>『網站分級制度』</div>\n" +
            "</footer>\n" +
            "\n" +
            "<script>\n" +
            "    if ($('.lazy').length > 0) {\n" +
            "        $(\".lazy\").lazyload({\n" +
            "            effect: \"fadeIn\"\n" +
            "        });\n" +
            "    }\n" +
            "    var host = document.domain.replace('www.','');\n" +
            "    $('.header_title').html(host);\n" +
            "    function close_discor() {\n" +
            "        $('.close_discor').hide();\n" +
            "    }\n" +
            "</script>\n" +
            "    </div>\n" +
            "</body>\n" +
            "<script>\n" +
            "    createHeaderAd(0);\n" +
            "    createContentAd();\n" +
            "</script>\n" +
            "</html>";

}
