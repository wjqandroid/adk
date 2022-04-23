package com.visionvera.psychologist.c.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;


/**
 * @Desc 屏幕相关工具类
 * @Author yemh
 * @Date 2019/4/12 14:58
 */
public class ScreenUtils {
    private static int screenW;
    private static int screenH;

    /**
     * dp转px
     *
     * @param context
     * @param val
     * @return
     */
    public static float dp2px(Context context, float val) {
        DisplayMetrics mMetrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, val, mMetrics);
    }

    /**
     * px转dp
     *
     * @param context
     * @param val
     * @return
     */
    public static float px2dp(Context context, float val) {
        DisplayMetrics mMetrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, val, mMetrics);
    }

    /**
     * sp转px
     *
     * @param context
     * @param val
     * @return
     */
    public static float sp2px(Context context, float val) {
        DisplayMetrics mMetrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, val, mMetrics);
    }

    /**
     * px转sp
     *
     * @param context
     * @param val
     * @return
     */
    public static float px2sp(Context context, float val) {
        DisplayMetrics mMetrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, val, mMetrics);
    }

    /**
     * 计算文本的长度
     *
     * @param paint
     * @param demoText
     * @return
     */
    public static int calcTextWidth(Paint paint, String demoText) {
        return (int) paint.measureText(demoText);
    }

    /**
     * 计算文本的高度
     *
     * @param paint
     * @param demoText
     * @return
     */
    public static int calcTextHeight(Paint paint, String demoText) {
        Rect r = new Rect();
        paint.getTextBounds(demoText, 0, demoText.length(), r);
        return r.height();
    }

    public static int getStatusBarHeight(Activity paramActivity) {
        Rect localRect = new Rect();
        paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        return localRect.top;
    }
}
