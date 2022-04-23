package com.visionvera.library.util;

/**
 * @author 刘传政
 * @date 2020/2/16 0016 16:47
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class CheckPasswordUtil {
    //数字
    public static final String REG_NUMBER = ".*\\d+.*";
    //大写字母
    public static final String REG_UPPERCASE = ".*[A-Z]+.*";
    //小写字母
    public static final String REG_LOWERCASE = ".*[a-z]+.*";
    //特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

    //心理项目的密码规则
    // 6-12位字符需含大小写字母
    public static boolean checkPasswordRule(String password) {

        if (password == null || password.length() < 6 || password.length() > 12) return false;

        int i = 0;

        if (password.matches(REG_LOWERCASE)) i++;
        if (password.matches(REG_UPPERCASE)) i++;

        if (i < 2) return false;

        return true;
    }

}
