package com.feinno.publibrary.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zzy05 zhangzhanyong@feinno.com
 * @version V1.0
 * @project: PubLibrary
 * @description: //TODO(用一句话描述该文件做什么)
 * @date 2017/2/8 10:11
 * @updateUser zzy05
 * @update 2017/2/8 10:11
 */

public class UrlUtils {

    private String url0 = "http://n3.cmsfile.pg0.cn/group2/M00/2E/CA/Cgqg2VbGgrOAU2slAABNG3-wiT0367.jpg";
    private String url1 = "http://pics.sc.chinaz.com/files/pic/pic9/201508/apic14052.jpg";
    private String url2 = "http://img05.tooopen.com/images/20150830/tooopen_sy_140703593676.jpg";
    private String url3 = "http://img.sc115.com/uploads/sc/jpgs/05/xpic6813_sc115.com.jpg";
    private String url4 = "http://pics.sc.chinaz.com/files/pic/pic9/201410/apic7065.jpg";
    private String url5 = "http://atimg.com/uploads/day_20110511/20110511_7ed03efc0cba7310e7cfde1747oIdAmH.jpg";
    private String url6 = "http://res.yangdog.com/uploadfile/allimg/160809/0T31229B-0.jpg";
    private String url7 = "http://img02.tooopen.com/images/20160114/tooopen_sy_154306579196.jpg";
    private String url8 = "http://www.tadewo.com/uploads/allimg/130417/1_130417161127_1.jpg";
    private String url9 = "http://ix.ucgps.com.cn/news/201405/10787/4aa21360e51b3fc3.gif";


    public static String getRandomImageURl() {
        UrlUtils urlUtils = new UrlUtils();
        Field[] fieldList = urlUtils.getClass().getDeclaredFields();
        Field field = fieldList[RandomUtils.getRandom(0, 9)];
        String name = field.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            Method m = UrlUtils.class.getMethod("get" + name);
            return (String) m.invoke(urlUtils);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return urlUtils.getUrl9();
    }

    public String getUrl0() {
        return url0;
    }

    public String getUrl1() {
        return url1;
    }

    public String getUrl2() {
        return url2;
    }

    public String getUrl3() {
        return url3;
    }

    public String getUrl4() {
        return url4;
    }

    public String getUrl5() {
        return url5;
    }

    public String getUrl6() {
        return url6;
    }

    public String getUrl7() {
        return url7;
    }

    public String getUrl8() {
        return url8;
    }

    public String getUrl9() {
        return url9;
    }
}
