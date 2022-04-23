package com.visionvera.library.util;

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

}
