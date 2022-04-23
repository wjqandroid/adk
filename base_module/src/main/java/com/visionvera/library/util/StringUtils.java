package com.visionvera.library.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author:lilongfeng
 * date:2019/9/9
 * 描述:字符串工具
 */

public class StringUtils {


    /**
     * 包含有数字
     *
     * @return
     */
    public static boolean hasDigit(String content) {
        Pattern pattern = Pattern.compile(".*[0-9]+.*");
        Matcher m = pattern.matcher(content);
        return m.matches();
    }


    /**
     * 包含有字母
     *
     * @return
     */
    public static boolean hasLetter(String content) {
        Pattern pattern = Pattern.compile(".*[A-Za-z]+.*");
        Matcher m = pattern.matcher(content);
        return m.matches();
    }

    /**
     * 包含有大写字母
     *
     * @return
     */
    public static boolean hasUpperLetter(String content) {
        Pattern pattern = Pattern.compile(".*[A-Z]+.*");
        Matcher m = pattern.matcher(content);
        return m.matches();
    }

    /**
     * 包含有小写字母
     *
     * @return
     */
    public static boolean hasLowerLetter(String content) {
        Pattern pattern = Pattern.compile(".*[a-z]+.*");
        Matcher m = pattern.matcher(content);
        return m.matches();
    }


    /**
     * 验证手机号
     *
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


}
