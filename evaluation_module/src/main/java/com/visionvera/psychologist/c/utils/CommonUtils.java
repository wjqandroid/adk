package com.visionvera.psychologist.c.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;


import com.visionvera.psychologist.account_module.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 */
public class CommonUtils {

    public static boolean compareDigit(String input, String base) {
        boolean flag = false;
        try {
            if (Integer.valueOf(input) < Integer.valueOf(base)) {
                flag = true;
            }
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }

    public static boolean isTopActivity(Activity activity) {
        String packageName = activity.getPackageName();
        ActivityManager activityManager = (ActivityManager) activity
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            if (packageName.equals(tasksInfo.get(0).topActivity
                    .getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static int getScreenOrientation() {
        Configuration config = MyApplication.getInstance().getResources()
                .getConfiguration();
        return config.orientation;
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyyMMdd");
        String name = sdfDateFormat.format(System.currentTimeMillis());
        return name;
    }

    /**
     * "yyyy-MM-dd"
     *
     * @return
     */
    public static String getCurrentDate1() {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String name = sdfDateFormat.format(System.currentTimeMillis());
        return name;
    }

    public static String getCurrentDate2() {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("MM-dd");
        String name = sdfDateFormat.format(System.currentTimeMillis());
        return name;
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String name = sdfDateFormat.format(System.currentTimeMillis());
        return name;
    }

    public static String getCurrentTime1() {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("HH:mm");
        String name = sdfDateFormat.format(System.currentTimeMillis());
        return name;
    }

    public static int getCurrentYears() {
        Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        return year;
    }

    public static int getCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH);
    }

    public static int getCurrentDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前秒
     */
    public static String getCurrentSeconds() {
        Calendar c = Calendar.getInstance();
        int second = c.get(Calendar.SECOND);
        if (1 == String.valueOf(second).length()) {
            return "0" + second;
        }
        return String.valueOf(second);
    }

    public static String getCurrentTimeToLocal() {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        String name = sdfDateFormat.format(System.currentTimeMillis());
        return name;
    }

    public static String getCurrentTimeToLocal02() {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat(
                "MM/dd");
        String name = sdfDateFormat.format(System.currentTimeMillis());
        return name;
    }

    /**
     * 得到对应的日期
     *
     * @param currentTime yyyy-MM-dd HH:mm
     * @return
     */
    public static String getDate1(String currentTime) {
        if (currentTime != null && !"".equals(currentTime)) {
            return currentTime.trim().split(" ")[0];
        }
        return "";
    }

    /**
     * format time yyyy-MM-dd HH:mm:ss.SSS-->MM-dd HH:mm
     *
     * @param input time with pattern "yyyy-MM-dd HH:mm:ss.SSS"
     * @return
     */
    public static String formatTime(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input.trim(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm"));

        } catch (Exception e) {
            res = "";
        }
        return res;
    }


    /**
     * format time yyyy-MM-dd HH:mm:ss.SSS-->MM-dd HH:mm
     *
     * @param input time with pattern "yyyy-MM-dd HH:mm:ss.SSS"
     * @return
     */
    public static String formatTime1(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input.trim(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm"));

        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    /**
     * format time yyyy-MM-dd HH:mm:ss.SSS-->MM-dd HH:mm
     *
     * @param input time with pattern "yyyy-MM-dd HH:mm:ss.SSS"
     * @return
     */
    public static String formateTime1(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("MM-dd HH:mm"));
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    // String str = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static String formateTime(String input) {
        String res = null;
        try {
            if (input.contains(" ")) {
                res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd HH:mm"));
            } else {
                res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                        "yyyy-MM-dd'T'HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd HH:mm"));
            }
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    public static String formatDate6(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SSS"), new SimpleDateFormat(
                    "MM-dd HH:mm:ss"));
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    public static String formatDate7(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat(
                    "MM-dd HH:mm:ss"));
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    public static String formatDate(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SSS"), new SimpleDateFormat("MM-dd"));
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"-->"yyyy-MM-dd HH:mm"
     *
     * @param input
     * @return
     */
    public static String formatTime5(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"-->"HH:mm"
     *
     * @param input
     * @return
     */
    public static String formatTime3(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("HH"));
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    /**
     * 得到yy-MM-dd HH:mm
     *
     * @param input yyyy-MM-dd HH:mm.ss.SSS 或者 yyyy-MM-dd HH:mm
     * @return
     */
    public static String formatTime4(String input) {
        String res = "";
        try {
            String[] strs = input.trim().split(" ");
            if (strs.length > 1) {
                res = strs[0].substring(2) + " " + strs[1].substring(0, 5);
            }
        } catch (Exception e) {
        }
        return res;
    }

    /**
     * 判断查询日期是否为当天
     *
     * @param verifyDate 格式： yyyy-MM-dd
     * @return
     */
    public static boolean isCurrentDay(String verifyDate) {
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        String frmDate = sdfDateFormat.format(date);
        return frmDate.equals(verifyDate);
    }

    public static int dip2px(float dipValue) {
        final float scale = MyApplication.getInstance().getResources()
                .getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = MyApplication.getInstance().getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenWidth() {
        return MyApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return MyApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
    }

    public static boolean isTextEmpty(String input) {
        return null == input || input.trim().length() == 0;
    }

    public static void showToast(String msgContent) {
        if (!TextUtils.isEmpty(msgContent)) {
            showToast(Gravity.CENTER, 5000, msgContent);
        }
    }

    public static void showToast(String msgContent, int period) {
        if (!TextUtils.isEmpty(msgContent)) {
            showToast(Gravity.CENTER, period, msgContent);
        }
    }

    private static void showToast(int pos, int duration, CharSequence text) {
        Toast mToast = Toast.makeText(MyApplication.getInstance(), text,
                duration);
        mToast.setGravity(pos, 0, 0);
        mToast.show();
    }

    public static int getScreenWidth(Activity context) {
        return context.getWindowManager().getDefaultDisplay().getWidth();
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static void readScreen(Activity context) {
        // 获取屏幕密度（方法1）
        int screenWidth = context.getWindowManager().getDefaultDisplay()
                .getWidth(); // 屏幕宽（像素，如：480px）
        int screenHeight = context.getWindowManager().getDefaultDisplay()
                .getHeight(); // 屏幕高（像素，如：800p）

        String TAG = "hello";
//		Log.e(TAG + "  getDefaultDisplay", "screenWidth=" + screenWidth
//				+ "; screenHeight=" + screenHeight);

        // 获取屏幕密度（方法2）
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();

        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        Log.e(TAG + "  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e(TAG + "  DisplayMetrics", "density=" + density + "; densityDPI="
                + densityDPI);

        screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
        screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）

//		Log.e(TAG + "  DisplayMetrics(111)", "screenWidth=" + screenWidth
//				+ "; screenHeight=" + screenHeight);

        // 获取屏幕密度（方法3）
        dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        Log.e(TAG + "  DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e(TAG + "  DisplayMetrics", "density=" + density + "; densityDPI="
                + densityDPI);

        int screenWidthDip = dm.widthPixels; // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels; // 屏幕宽（dip，如：533dip）

//		Log.e(TAG + "  DisplayMetrics(222)", "screenWidthDip=" + screenWidthDip
//				+ "; screenHeightDip=" + screenHeightDip);

        screenWidth = (int) (dm.widthPixels * density + 0.5f); // 屏幕宽（px，如：480px）
        screenHeight = (int) (dm.heightPixels * density + 0.5f); // 屏幕高（px，如：800px）

//		Log.e(TAG + "  DisplayMetrics(222)", "screenWidth=" + screenWidth
//				+ "; screenHeight=" + screenHeight);

    }

    public static int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = MyApplication.getInstance().getResources()
                    .getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 压缩
     */
    public static byte[] compress(byte[] input) {
        if (input == null || input.length == 0) {
            return input;
        }
        byte[] output;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(input);
            gzip.close();
            output = out.toByteArray();
        } catch (IOException e) {
            output = new byte[0];
        } finally {
            try {
                gzip.close();
                out.close();
            } catch (IOException e) {
            }
        }
        return output;
    }

    /**
     * 解压缩
     */
    public static byte[] uncompress(byte[] input) {
        if (input == null || input.length == 0) {
            return input;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input);
        GZIPInputStream gunzip = null;

        byte[] output;
        try {
            gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            output = out.toByteArray();
        } catch (IOException e) {
            output = new byte[0];
        } finally {
            try {
                gunzip.close();
                in.close();
                out.close();
            } catch (IOException e) {
            }
        }
        return output;
    }

    /**
     * 将对象序列化成byte数组
     *
     * @param obj
     * @return
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 将byte数组反序列化
     *
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();

            ois.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将日期时间字符串转化为long类型
     *
     * @param s
     * @return
     * @throws ParseException
     * @author 吴志敏 研发中心 2012-3-22上午9:50:20
     */
    public static Date getDateTimeFromSring(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date mdate = null;
        mdate = sdf.parse(s);
        calendar.setTime(mdate);
        return calendar.getTime();
    }

    public static Date getDateTimeFromSring02(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date mdate = null;
        mdate = sdf.parse(s);
        calendar.setTime(mdate);
        return calendar.getTime();
    }

    /**
     * 将日期时间字符串转化为long类型
     *
     * @param s
     * @return
     * @throws ParseException
     * @author 吴志敏 研发中心 2012-3-22上午9:50:20
     */
    public static Date getDateTimeFromSring(String s, SimpleDateFormat sdf)
            throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date mdate = null;
        mdate = sdf.parse(s);
        calendar.setTime(mdate);
        return calendar.getTime();
    }

    /**
     * 将字符串的日期时间转化成指定的日期时间字符串
     *
     * @param s
     * @param oldSdf 原字符串的格式，必须和字符串的长度和格式一致
     * @param sdf
     * @return
     * @throws ParseException
     * @author 吴志敏 研发中心 2012-6-18下午2:42:48
     */
    public static String convertStrToSpecifiedDateTime(String s,
                                                       SimpleDateFormat oldSdf, SimpleDateFormat sdf)
            throws ParseException {
        if ("".equals(s))
            return "";
        Date date = oldSdf.parse(s);
        return sdf.format(date);
    }

    public static void printOutToConsole(String msg) {
        printOutToConsole("zgc-debug", msg);
    }

    public static void printOutToConsole(String tag, String msg) {
        boolean debug = true;
        if (debug) {
            Log.i(tag, msg);
        }
    }

    public static Drawable getDrawableByResourceById(int resourceId) {
        return MyApplication.getInstance().getResources()
                .getDrawable(resourceId);
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

    public static <T> boolean isListEmpty(List<T> inputs) {
        return null == inputs || inputs.isEmpty();
    }

    /**
     * 根据所给的日期 YYYY-MM-DD 得到当前的起始日期字符串 精确到毫秒
     *
     * @param dateStr
     * @return
     */
    public static String[] getBeginAndOverDate(String dateStr) {
        String[] str = new String[2];
        String[] cals = dateStr.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(cals[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(cals[1]) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(cals[2]));
        // BEGIN
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 000);
        Date date = cal.getTime();
        str[0] = sdf.format(date);
        // OVER
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date date1 = cal.getTime();
        str[1] = sdf.format(date1);
        return str;
    }

    /**
     * 判断是否是正整数和正实数
     *
     * @param str
     * @return
     */
    public static boolean isIntegerOrPositiveDecial(String str) {
        if (CommonUtils.isTextEmpty(str)) {
            return false;
        }
        Pattern pattern1 = Pattern.compile("^\\+{0,1}[1-9]\\d*");
        Pattern pattern2 = Pattern
                .compile("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*");
        Matcher matcher1 = pattern1.matcher(str.trim());
        Matcher matcher2 = pattern2.matcher(str.trim());
        if (matcher1.matches()) {
            return true;
        } else return matcher2.matches();
    }

    /**
     * 计算字符串的长度
     *
     * @param text 要计算的字符串
     * @param Size 字体大小
     * @return
     */
    public static float GetTextWidth(String text, float Size) {
        TextPaint FontPaint = new TextPaint();
        FontPaint.setTextSize(Size);
        return FontPaint.measureText(text);
    }

    public static boolean isNetworkAvailabe(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        /*************************************测试网络start*****************************************/
//        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();//获取网络的连接情况
//        // 如果连接的是模拟器，即使桥接的电脑是wifi连接，那么type依然等于0，0是移动网络
//        int type = activeNetInfo.getType();
//        Log.i("abc", "type="+type);
        /*************************************测试网络end*****************************************/
        NetworkInfo wifiInfo = connManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifiInfo != null) {
            return wifiInfo.isAvailable();
        }
        return false;
    }

    /**
     * 自动开启wifi
     *
     * @param context
     */
    public static void enableWifi(Context context) {
        WifiManager mWm = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        if (!mWm.isWifiEnabled()) {
            mWm.setWifiEnabled(true);
        }
    }

    public static String getTomorrowDate() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sf.format(date);

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sf.parse(nowDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_YEAR, +1);
        String nextDate_1 = sf.format(cal.getTime());
        return nextDate_1;
    }

    /**
     * 数组拼接成字符串
     *
     * @param array
     * @param separator
     * @return
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].toString().length()) + 1) * arraySize);
        StringBuffer buf = new StringBuffer(bufSize);
        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * @author qiaojianfeng
     * <p>
     * 将服务端提供的日期yyyy/MM/dd HH:mm:ss格式化成yyyy-MM-dd HH:mm:ss
     * 然后以DateTime存入数据库
     */
    public static String ChangeDateStyle(String needChangeDate) {
        if (CommonUtils.isTextEmpty(needChangeDate)) {
            //医嘱列表数据的STOP_DATE_TIME()可能为空
            return needChangeDate;
        } else if (needChangeDate.contains("-")) {
            //添加评估单的EXEC_TIME()已经是yyyy-MM-dd HH:mm:ss格式
            return needChangeDate;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat trueDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(needChangeDate);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String format = trueDate.format(date);
            return format;
        }
    }

    /**
     * 修改服务器时间格式，2014/1/1 8:00:00为2014-1-1 8:00:00
     */
    public static String changeTimeFromat(String time) {
        StringBuilder builder = new StringBuilder();
        String[] arr = time.split(" ");
        // 年月日
        String[] arr_ymd = arr[0].split("/");
        // 存入年
        builder.append(arr_ymd[0]);
        builder.append("-");

        // 存入月
        String month = arr_ymd[1];
        if (month.length() == 1) {
            builder.append("0");
        }
        builder.append(month);
        builder.append("-");

        // 存入日
        String day = arr_ymd[2];
        if (day.length() == 1) {
            builder.append("0");
        }
        builder.append(day);
        builder.append(" ");

        // 存入时分秒
        builder.append(arr[1]);
        return builder.toString();
    }

    /**
     * 获取当前日期的后一天
     * 格式：2015-08-07
     */
    public static String getTomorrowDay() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getTomorrowDay02() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getTomorrowDay03() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取指定日期的后一天
     * 时间格式：yyyy-MM-dd
     */
    public static String getTomorrowDay04(String dateStr) {
        String[] cals = dateStr.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(cals[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(cals[1]) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(cals[2]));
        Date date = cal.getTime();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 获取当前日期的前一天
     * 格式：2015-08-07
     */
    public static String getYesterdayDay() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取当前日期的多少天以前
     */
    public static String getYesterdayDay(int days) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -days);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 转换时间格式：
     * 2013-03-14T15:58:35 --> 2013-03-14 15:58:35
     *
     * @return
     */
    public static String changeT2CommonTime(String time) {
        String newTime = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newTime = newFormat.format(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }

    /**
     * 转换时间格式：
     * 2013/03/14 15:58:35 --> 2013-03-14 15:58:35
     *
     * @return
     */
    public static String changeLine2CommonTime(String time) {
        String newTime = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newTime = newFormat.format(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }

    public static String changeLine2CommonTime02(String time) {
        String newTime = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd");
            newTime = newFormat.format(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }

    /**
     * 去掉秒的显示
     * 2015-11-19 13:41:15格式，去掉秒
     *
     * @return
     */
    public static String deleteSecondShow(String time) {
        int index = time.lastIndexOf(":");
        String newTime = time.substring(0, index);
        return newTime;
    }

    /**
     * 根据Wifi信息获取本地Mac
     *
     * @param context
     * @return
     * @author 杨美静 研发中心 2013年10月12日10:57:57
     */
    public static String getLocalMacAddressFromWifiInfo(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo mac_information = wifi.getConnectionInfo();
        return mac_information.getMacAddress();
    }

    /**
     * 获取屏幕密度
     */
    public static int getScreenDensity(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        return metric.densityDpi;
    }

    /**
     * 获取内存地址下的file文件夹路径
     * 内存地址=/data/data/com.bjgoodwill.care/files
     *
     * @param context
     * @return
     */
    public static String getInner(Context context) {
        File file = context.getFilesDir();
        String path = file.getAbsolutePath();
        return path;
    }

    public static List<String> strArr2List(String[] adviceUsage) {
        List<String> list = Arrays.asList(adviceUsage);
        return list;
    }

    /**
     * SP保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";

    public static void saveString(Context mActivity, String key,
                                  String value) {
        SharedPreferences sp = mActivity.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context mActivity, String key,
                                   String defValue) {
        SharedPreferences sp = mActivity.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    /**
     * 单数月份，单数日期加0
     *
     * @param time
     * @return
     */
    public static String plusZero(int time) {
        if (1 == String.valueOf(time).length()) {
            return "0" + time;
        }
        return String.valueOf(time);
    }

    /**
     * 是否为正确的时间顺序，开始时间小于结束时间
     *
     * @param startTime 2017-11-01
     * @param endTime   2017-11-02
     */
    public static boolean isTimeOrder(String startTime, String endTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            Date dateBegin = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
            calendar.setTime(dateBegin);
            long timeInMillis = calendar.getTimeInMillis();
            Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
            calendar.setTime(dateEnd);
            long timeInMillis1 = calendar.getTimeInMillis();
            // 判断开始时间大于结束时间
            long intervalTime = timeInMillis1 - timeInMillis;
            if (intervalTime > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否为正确的时间顺序，开始时间小于结束时间
     *
     * @param startTime 2017-11-01 00:00
     * @param endTime   2017-11-02 00:00
     */
    public static boolean isTimeOrder02(String startTime, String endTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            Date dateBegin = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime);
            calendar.setTime(dateBegin);
            long timeInMillis = calendar.getTimeInMillis();
            Date dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endTime);
            calendar.setTime(dateEnd);
            long timeInMillis1 = calendar.getTimeInMillis();
            // 判断开始时间大于结束时间
            long intervalTime = timeInMillis1 - timeInMillis;
            if (intervalTime > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除文件夹和文件夹里面的文件
     */
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }


    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    public static int compare_date(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 传入具体日期 ，返回具体日期减一个月。
     *
     * @param date
     *            日期(2014-04-20)
     * @return 2014-03-20
     * @throws ParseException
     */
    public static String subMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);

        rightNow.add(Calendar.MONTH, -1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);

        return reStr;
    }


    /**
     * @param str
     * @return 在一个字符串中提取整数和小数部分，如果字符串中没有整数和小数部分，则设为空 0
     */
    public static String extractNumber(String str) {
        // 需要取整数和小数的字符串
//        String str = "需要提取的字符串1.111";
        // 控制正则表达式的匹配行为的参数(小数)
        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
        //Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
        Matcher m = p.matcher(str);
        //m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
        if (m.find()) {
            //如果有相匹配的,则判断是否为null操作
            //group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
            str = m.group(1) == null ? "" : m.group(1);
        } else {
            //如果匹配不到小数，就进行整数匹配
            p = Pattern.compile("(\\d+)");
            m = p.matcher(str);
            if (m.find()) {
                //如果有整数相匹配
                str = m.group(1) == null ? "" : m.group(1);
            } else {
                //如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
                str = "";
            }
        }
        return str;
    }

    /**
     * @param a 判断数组全部是0
     * @return
     */
    public static boolean isZero(String[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != "0") {
                return false;
            }
        }
        return true;
    }

    /**
     * 更具数组元素 查找该元素索引
     * @param arr
     * @param value
     * @return
     */
    public static int getIndex(String[] arr, String value){
        int index = -1;
        for(int x=0; x<arr.length; x++){
            if(arr[x] == value){
                index = x;
                break;
            }
        }
        return index;
    }

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(isTextEmpty(str)){
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 判断是否为空,为空返回 " "
     */
    public static String isTextNull(String input){
        if (null == input || input.trim().length() == 0) {
            return " ";
        }else{
            return input;
        }
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"-->"HH:mm"
     *
     * @param input
     * @return
     */
    public static String formatTime6(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("HH:mm"));
        } catch (Exception e) {
            res = "";
        }
        return res;
    }
    /**
     * "yyyy-MM-dd HH:mm:ss"-->"yyyy-MM-dd HH:mm"
     *
     * @param input
     * @return
     */
    public static String formatTime7(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input, new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    public static String formatTime8(String input) {
        String res = null;
        try {
            res = convertStrToSpecifiedDateTime(input.trim(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
                    new SimpleDateFormat("yyyy-MM-dd"));

        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    /**
     * 判断Activity是否已销毁
     * a.isDestroyed()
     * @param c
     * @return
     */
    public static boolean isValidContext (Context c){
        Activity a = (Activity)c;
        if (a.isFinishing()){
            return false;
        }else{
            return true;
        }
    }


    /**
     * 设置Pop可通过点击空白区域|返回键|menu键触发消失
     *
     */
    public static void SetPopCancle(final PopupWindow popupWindow){
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0
                        || event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                }
                return false;
            }
        });
        /**
         * 1.解决再次点击MENU键无反应问题 2.sub_view是PopupWindow的子View
         */
        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                    return true;
                }
                if(keyCode == KeyEvent.KEYCODE_MENU&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void makeText(String pName) {
        Toast.makeText(MyApplication.getInstance(), pName, Toast.LENGTH_SHORT).show();
    }

    public static void makeTextLong(String pName) {
        Toast.makeText(MyApplication.getInstance(), pName, Toast.LENGTH_LONG).show();
    }

    /**
     * 获取当前日期的前几小时
     */
    public static String getBeforeHour(int count) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -count);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }

    /**
     * 获取当前日期的后几分钟
     */
    public static String getAfterMinute(int count) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, count);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }

    public static String getTime(Date date) { // 可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getTime02(Date date) { // 可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String getTime03(Date date) { // 可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    /**
     * 判断当前设备是否有NavigationBar
     *
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
//            Log.w("abc", e);
        }
        return hasNavigationBar;
    }

    /**
     * 获取底部状态栏的高度
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
//        Log.v("dbw", "Navi height:" + height);
        return height;
    }
}
