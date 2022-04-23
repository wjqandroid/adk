package com.visionvera.psychologist.c.module.usercenter.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.commonbean.AccountBean;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.view.ItemView1;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2019/8/27
 * 描述:账户安全设置
 */

public class AccountSafeActivity extends BaseActivity {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.tv_title)
    TextView tv_title;

    @BindView(R2.id.account_phonenumber)
    ItemView1 account_phonenumber;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,AccountSafeActivity.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_account_safe;
    }

    @Override
    protected void doYourself() {
        ARouter.getInstance().inject(this);
        initToolbar();
        initData();
    }



    private void initToolbar() {
        tv_title.setText(getString(R.string.evaluation_module_account_safety));
    }

    private void initData() {
        if (accountService != null) {

            AccountBean accountBean = accountService.getGetAccountInfo();

            account_phonenumber.setTv_right(accountBean.phoneNumber);

        }

    }

    @OnClick({R2.id.account_password, R2.id.iv_back, R2.id.rl_user_info})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.account_password) {
            ChangePassActivity.startActivity(this);
        } else if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.rl_user_info) {
            EditInfoActivity.startActivity(this);
        }
    }
}
