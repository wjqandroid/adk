package com.visionvera.library.base;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.visionvera.library.R;
import com.visionvera.library.util.activity.ActivityStackUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 可控制网络请求的生命周期
 */
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {

    public ActivityStackUtil activityStackUtil = ActivityStackUtil.getScreenManager();

    protected BaseActivity activity;

    private Unbinder unbinder;

    public BasePopupView loadingPopup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!setContentView()) {
            setContentView(getLayoutId());
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        unbinder = ButterKnife.bind(this);
        activityStackUtil.addActivity(this);
        activity = this;
        initStatusColor();
        initInnerThing();
        doYourself();
    }

    /**
     * 可以自己设置view,不用layoutid的方式.这设置了,默认的就不走了
     *
     * @return
     */
    public boolean setContentView() {

        return false;
    }

    /**
     * 在doYourself之前做一些特殊的事
     */
    public void initInnerThing() {
    }


    private void initStatusColor() {
        ImmersionBar.with(this)
                //状态栏颜色
                .statusBarColor(R.color.base_module_white)
                //状态栏文字颜色
                .statusBarDarkFont(true)
                //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看. false表示布局嵌入状态栏。true表示布局避开状态栏
                .fitsSystemWindows(true)
                .init();
    }

    protected abstract int getLayoutId();

    /**
     * 这里不过多区分initview与initdata等。因为他们的顺序不是固定的
     * 避免过度设计
     */
    protected abstract void doYourself();


    @Override
    protected void onDestroy() {
        hideDialog();
        activityStackUtil.removeActivity(this);
        unbinder.unbind();
        super.onDestroy();
    }

    /**
     * 创建并show加载进度框
     *
     * @param msg
     * @param cancelAble
     */
    public void showLoadingDialog(String msg, boolean cancelAble) {
        hideDialog();
        loadingPopup = new XPopup.Builder(activity)
                .dismissOnBackPressed(cancelAble)
                .dismissOnTouchOutside(cancelAble)
                .asLoading(msg)
                .show();
    }

    public void showLoadingDialog() {
        showLoadingDialog("正在加载中", false);

    }

    /**
     * 隐藏加载进度框
     */
    public void hideDialog() {
        if (loadingPopup != null) {
            loadingPopup.dismiss();
        }
        loadingPopup = null;
    }

    @Override
    public void onClick(View v) {

    }
}
