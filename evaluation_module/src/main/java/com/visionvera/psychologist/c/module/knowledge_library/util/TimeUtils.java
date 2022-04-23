package com.visionvera.psychologist.c.module.knowledge_library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author lilongfeng
 * date 2019/6/28
 */

public class TimeUtils {

    public static String YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static String YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm";

    public static String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取年月日
     */
    public static String getYearMonthDay(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(YEAR_MONTH_DAY);
        return format.format(date);
    }

    /**
     * 获取年月日时分
     */
    public static String getYearMonthDayHourMinute(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE);
        return format.format(date);
    }

    /**
     * 获取年月日时分秒
     */
    public static String getYearMonthDayHourMinuteSecond(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
        return format.format(date);
    }

    /**
     * 获取年月日时分
     */
    public static String getYearMonthDayHourMinute(String dateStr) {//可根据需要自行截取数据显示

        try {
            SimpleDateFormat format = new SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE);
            Date parse = format.parse(dateStr);
            return format.format(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     */
    public static String getTimeStr(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * @param time
     * @desc 字符串转时间戳
     * @example time="2019-04-19 00:00:00"
     **/
    public static Long getTimestamp(String time) {
        Long timestamp = null;
        try {
            timestamp = new SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * @param timestamp
     * @desc 时间戳转字符串
     * @example timestamp=1558322327000
     **/
    public static String getStringTime(Long timestamp) {
        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);  // 获取年月日时分秒
        return datetime;
    }

    /**
     * @param timestamp
     * @desc 时间戳转字符串
     * @example timestamp=1558322327000
     **/
    public static String getStringTime(Long timestamp, String pattern) {
        String datetime = new SimpleDateFormat(pattern).format(timestamp);  // 获取年月日时分秒
        return datetime;
    }

    /**
     * 显示时间，如果与当前时间差别小于一天，则自动用**秒(分，小时)前，如果大于一天则用format规定的格式显示
     * * 1分钟内            刚刚
     * * 1分钟以上,2分钟内   1分钟前
     * * 2分钟以上,3分钟内   2分钟前
     * *
     * * 1小时以上,2小时内   1小时前
     * * 2小时以上,3小时内   2小时前
     * *
     * * 24小时以上,48小时内   1天前
     * * 48小时以上,72小时内   2天前
     * * 72小时以上          显示发布评论的日期
     *
     * @param ctime  时间
     * @param format 格式 格式描述:例如:yyyy-MM-dd yyyy-MM-dd HH:mm:ss
     * @return SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     * System.out.println(showTime(df.parse("2015-02-27 11:31:00"),"yyyy-MM-dd HH:mm:ss"));
     * @author wxy
     */
    public static String showTime(Date ctime, String format) {
        String r = "";
        if (ctime == null) return r;
        if (format == null) format = "MM-dd HH:mm";

        long nowtimelong = System.currentTimeMillis();

        long ctimelong = ctime.getTime();
        long result = Math.abs(nowtimelong - ctimelong);

        if (result < 60000) {// 一分钟内
            r = "刚刚";
        } else if (result >= 60000 && result < 3600000) {// 一小时内
            long seconds = result / 60000;
            r = seconds + "分钟前";
        } else if (result >= 3600000 && result < 1000 * 60 * 60 * 24) {// 一天内
            long seconds = result / 3600000;
            r = seconds + "小时前";
        } else if (result >= 1000 * 60 * 60 * 24 && result < 1000 * 60 * 60 * 24 * 2) {// 2天内
            long seconds = result / (1000 * 60 * 60 * 24);
            r = seconds + "天前";
        } else {// 日期格式
            format = "yyyy-MM-dd";
            SimpleDateFormat df = new SimpleDateFormat(format);
            r = df.format(ctime).toString();
        }
        return r;
    }

}
