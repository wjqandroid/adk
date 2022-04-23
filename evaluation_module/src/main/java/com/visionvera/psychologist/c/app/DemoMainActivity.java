package com.visionvera.psychologist.c.app;

import android.content.Intent;
import android.widget.TextView;

import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DemoMainActivity extends BaseActivity {

    @BindView(R2.id.tv_ceping)
    TextView tvCeping;

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_demomain;
    }

    @Override
    protected void doYourself() {

    }


    @OnClick(R2.id.tv_ceping)
    public void onViewClicked() {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        startActivity(new Intent(this, MainActivity.class));
//        ARouter.getInstance()
//                .build(ARouterConstant.Account.PhoneLoginActivity)
//                .withBoolean(Constant.IntentKey.isReturn,true)
//                .navigation(DemoMainActivity.this,Constant.RequestReturnCode.SelfAssessmentQuestion_To_PhoneLogin_Code);
    }
}
