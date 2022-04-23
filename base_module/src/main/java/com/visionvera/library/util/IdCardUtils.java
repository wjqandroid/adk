package com.visionvera.library.util;

import com.blankj.utilcode.util.StringUtils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 刘传政
 * @date 2019-07-29 16:54
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class IdCardUtils {
    public static final int IDENTITYCODE_OLD = 15; // 老身份证15位
    public static final int IDENTITYCODE_NEW = 18; // 新身份证18位
    public static int[] Wi = new int[17];

    /**
     * 判断身份证号码是否正确。
     *
     * @param code 身份证号码。
     * @return 如果身份证号码正确，则返回true，否则返回false。
     */
    public static boolean isIdentityCode(String code) {

        if (StringUtils.isEmpty(code)) {
            return false;
        }

        String birthDay = "";
        code = code.trim().toUpperCase();

        // 长度只有15和18两种情况
        if ((code.length() != IDENTITYCODE_OLD)
                && (code.length() != IDENTITYCODE_NEW)) {
            return false;
        }

        // 身份证号码必须为数字(18位的新身份证最后一位可以是x)
        Pattern pt = Pattern.compile("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)");
        Matcher mt = pt.matcher(code);
        if (!mt.find()) {
            return false;
        }

        // 验证生日
        if (code.length() == IDENTITYCODE_OLD) {
            birthDay = "19" + code.substring(6, 12);
        } else {
            birthDay = code.substring(6, 14);
        }
        if (checkValidDate(birthDay) == false) {
            return false;
        }

        // 最后一位校验码验证


        return true;
    }

    public static String getBirthDay(String code) {
        String resultBirthDay = "";
        // 验证生日
        String birthDay = "";
        if (code.length() == IDENTITYCODE_OLD) {
            birthDay = "19" + code.substring(6, 12);
        } else {
            birthDay = code.substring(6, 14);
        }
        if (checkValidDate(birthDay) == true) {
            String year = birthDay.substring(0, 4);
            String month = birthDay.substring(4, 6);
            String day = birthDay.substring(6);
            resultBirthDay = year + "-" + month + "-" + day;
        }
        return resultBirthDay;

    }

    /**
     * 功能：判断字符串是否为日期格式
     *  
     */


    private static boolean checkValidDate(String pDateObj) {
        boolean ret = true;
        if (pDateObj == null || pDateObj.length() != 8) {
            ret = false;
        }
        try {
            int year = new Integer(pDateObj.substring(0, 4)).intValue();
            int month = new Integer(pDateObj.substring(4, 6)).intValue();
            int day = new Integer(pDateObj.substring(6)).intValue();
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false); // 允许严格检查日期格式
            cal.set(year, month - 1, day);
            cal.getTime();// 该方法调用就会抛出异常
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }

}
