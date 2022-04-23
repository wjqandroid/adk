package com.visionvera.library.util;

/**
 * @Desc 防重复点击
 */
public class OneClickUtils {

    /**
     * 按钮是否快速点击
     */
    private static long lastClickTime = 0;
    public static final int MIN_CLICK_DELAY_TIME = 500;

    public synchronized static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime < MIN_CLICK_DELAY_TIME) {
            return true;
        }

        lastClickTime = currentTime;
        return false;
    }

}
