package com.tencent.qcloud.tuicore.component.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.tencent.qcloud.tuicore.R;
import com.tencent.qcloud.tuicore.net.MessageEvent;
import com.tencent.qcloud.tuicore.net.OrderConsultContract;
import com.tencent.qcloud.tuicore.net.OrderConsultDetailPresenter;
import com.visionvera.library.base.BaseMVPLoadActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


//在聊天模块中使用网络请求上传状态，此处修改集成BaseMVPLoadActivity


//public class BaseLightActivity extends AppCompatActivity {
public class BaseLightActivity extends BaseMVPLoadActivity<OrderConsultContract.OrderConsultDetail.OrderConsultDetailView, OrderConsultDetailPresenter> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决聊天软键盘不顶起输入框BUG
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(false)
                .init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_color));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navigation_bar_color));
            int vis = getWindow().getDecorView().getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            getWindow().getDecorView().setSystemUiVisibility(vis);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void doYourself() {

    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void onReload() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }
}
