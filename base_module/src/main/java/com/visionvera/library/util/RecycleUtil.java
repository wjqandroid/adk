package com.visionvera.library.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.GridLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleUtil {

    /**
     * @param recyclerView
     * @param context
     * @param isHorizontal 是否水平滑动
     * @param spanCount    规定一行显示几列的参数常量
     */
    @SuppressLint("WrongConstant")
    public static void initRecycle(RecyclerView recyclerView, Context context, boolean isHorizontal, int spanCount) {
        //布局管理器对象
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, spanCount);
        if (!isHorizontal) { //垂直滑动
            //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
            gridLayoutManager.setOrientation(GridLayout.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else { //水平滑动
            //设置布局管理器
            /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);*/
            gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
    }

    /**
     * @param recyclerView
     * @param context
     * @param isHorizontal 是否水平滑动
     * @param spanCount    规定一行显示几列的参数常量
     */
    @SuppressLint("WrongConstant")
    public static void initGridRecycleNoScroll(RecyclerView recyclerView, Context context, boolean isHorizontal, int spanCount) {
        //布局管理器对象
        GridLayoutManager gridLayoutManager;

        if (!isHorizontal) { //垂直滑动
            gridLayoutManager = new GridLayoutManager(context, spanCount) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            gridLayoutManager.setOrientation(GridLayout.VERTICAL);

        } else { //水平滑动
            gridLayoutManager = new GridLayoutManager(context, spanCount) {
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            };
            gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    /**
     * 初始化不可滑动的recycleview
     *
     * @param recyclerView
     * @param context
     * @param isHorizontal 是否水平滑动
     */
    public static void initNoScrollRecycle(RecyclerView recyclerView, Context context, boolean isHorizontal) {
        //布局管理器对象
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        if (isHorizontal) { //水平滑动
            linearLayoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false) {
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            };
        } else {//垂直滑动
            linearLayoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }
        recyclerView.setLayoutManager(linearLayoutManager);

    }

}
