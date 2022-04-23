package com.visionvera.psychologist.account_module.app;

import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.account_module.R;
import com.visionvera.psychologist.account_module.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class DemoMainActivity extends BaseActivity {

    @BindView(R2.id.tv_ceping)
    TextView tvCeping;

    @Override
    protected int getLayoutId() {
        return R.layout.account_module_activity_demomain;
    }

    @Override
    protected void doYourself() {

    }


    @OnClick(R2.id.tv_ceping)
    public void onViewClicked() {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        ARouter.getInstance().build(ARouterConstant.Account.PhoneLoginActivity).navigation();
    }
}
