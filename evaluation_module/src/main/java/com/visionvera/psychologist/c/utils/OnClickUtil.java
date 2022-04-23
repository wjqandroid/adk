package com.visionvera.psychologist.c.utils;

/**
 * @Classname:OnClickUtil
 * @author:haohuizhao
 * @Date:2021/7/30 14:27
 * @Version
 *  防止用户 重复点击
 */
public class OnClickUtil {
        public static final int DELAY = 500;
        private static long lastClickTime = 0;

        public static boolean isNotFastClick() {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime > DELAY) {
                lastClickTime = currentTime;
                return true;
            } else {
                return false;
            }
        }

}
