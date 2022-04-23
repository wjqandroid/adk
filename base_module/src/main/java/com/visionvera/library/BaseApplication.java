package com.visionvera.library;

import android.content.Context;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.visionvera.library.base.Constant;
import com.visionvera.library.greendao.GreenDaoManager;

/**
 * application
 */

public class BaseApplication extends MultiDexApplication {
    private static BaseApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initLog();
        initToastUtil();
        initArouter();

        initGreenDao();



//        CrashReport.initCrashReport(this, "1238107505", false);
    }


    private void initArouter() {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private boolean isDebug() {
        return true;
    }


    /**
     * 初始化greendao的数据库
     */
    private void initGreenDao() {
        GreenDaoManager.init(this);
        GreenDaoManager.getInstance().getDaoMaster();
        GreenDaoManager.getInstance().getDaoSession();
    }


    public static synchronized BaseApplication getInstance() {
        return myApplication;
    }

    /**
     * 配置toast样式等
     */
    private void initToastUtil() {
        ToastUtils.setBgColor(getResources().getColor(R.color.base_module_theme));
//        ToastUtils.setGravity(Gravity.BOTTOM, 0, AdaptScreenUtils.pt2Px(20));
        //因为有些时候toast会盖住键盘.所以放到中间位置
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
    }

    /**
     * 初始化logger库
     */
    private void initLog() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(Constant.Tag.tag)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
    }


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.base_module_theme, android.R.color.white);//全局设置主题颜色
                //是否启用列表惯性滑动到底部时自动加载更多
                layout.setEnableAutoLoadMore(false);
                layout.setEnableLoadMoreWhenContentNotFull(false);
                return new MaterialHeader(context);//google官方下拉效果。本项目中有个总是露个小黑边bug
//                return new WaveSwipeHeader(context);//降落的水滴效果。
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
