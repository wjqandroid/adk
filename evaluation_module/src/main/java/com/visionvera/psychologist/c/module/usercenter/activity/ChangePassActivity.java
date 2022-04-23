package com.visionvera.psychologist.c.module.usercenter.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.commonbean.AccountBean;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.util.CheckPasswordUtil;
import com.visionvera.library.util.MD5Utils;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.usercenter.bean.ChangePassRequest;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.module.usercenter.presenter.ChangePassPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2019/8/27
 * 描述:修改密码
 */

public class ChangePassActivity extends BaseMVPActivity<IContract.ChangePass.ChangePassView, ChangePassPresenter> {


    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.tvTitle)
    TextView tvTitle;

    @BindView(R2.id.tvSubmit)
    TextView tvSubmit;

    @BindView(R2.id.user_phonenumber)
    TextView user_phonenumber;

    @BindView(R2.id.old_password)
    EditText old_password;

    @BindView(R2.id.new_password)
    EditText new_password;

    @BindView(R2.id.again_new_password)
    EditText again_new_password;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ChangePassActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_change_pass;
    }

    @Override
    protected void doYourself() {
        ARouter.getInstance().inject(this);

        initView();
        initToolBar();
        initData();
    }

    private void initData() {
        if (accountService != null) {
            AccountBean accountBean = accountService.getGetAccountInfo();

            user_phonenumber.setText(accountBean.phoneNumber);

        }
    }

    private void initToolBar() {
    }

    private void initView() {
    }

    @OnClick({R2.id.tvCancel, R2.id.tvSubmit, R2.id.tv_forget_password})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.tvCancel) {
            finish();
        } else if (id == R.id.tvSubmit) {
            if (CheckPasswordUtil.checkPasswordRule(new_password.getText().toString().trim())) {
                //规则匹配
                if (TextUtils.equals(new_password.getText().toString().trim()
                        , again_new_password.getText().toString().trim())) {
                    showLoadingDialog();
                    ChangePassRequest changePassRequest = new ChangePassRequest();
                    changePassRequest.setOldPassword(MD5Utils.MD5(old_password.getText().toString().trim()));
                    changePassRequest.setNewPassword(MD5Utils.MD5(new_password.getText().toString().trim()));
                    mPresenter.getChangePass(changePassRequest, this);


                  /*  if (StringUtils.hasUpperLetter(new_password.getText().toString().trim())&& StringUtils.hasLowerLetter(new_password.getText().toString().trim())){
                        showLoadingDialog();
                        ChangePassRequest changePassRequest = new ChangePassRequest();
                        changePassRequest.setOldPassword(MD5Utils.MD5(old_password.getText().toString().trim()));
                        changePassRequest.setNewPassword(MD5Utils.MD5(new_password.getText().toString().trim()));
                        mPresenter.getChangePass(changePassRequest, this);
                    }else{
                        ToastUtils.showLong(getString(R.string.evaluation_module_password_notice));
                    }*/
                } else {
                    ToastUtils.showLong(getString(R.string.evaluation_module_password_not_sample));
                }
            } else {
                ToastUtils.showLong("密码必须为6-12位，同时包含大写和小写字母");
            }
        } else if (id == R.id.tv_forget_password) {//忘记密码
            ForgetPasswordActivity.startActivity(this, "");
        }

    }

    @Override
    protected void initMVP() {
        mView=new IContract.ChangePass.ChangePassView() {
            @Override
            public void onChangePass(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        ToastUtils.showShort(errorMsg);


                        //修改完密码后不退出登录
                        activityStackUtil.clearTopOfMy(activity, MainActivity.class);
//                        activityStackUtil.clearTopOfMy(activity, MainActivity.class);
//
//                        if (accountService!=null){
//                            accountService.clearAccountInfo();
//                        }
//
//                        ARouter.getInstance()
//                                .build(ARouterConstant.Account.AccountLoginActivity)
//                                .navigation(activity);
//
//                        EventBus.getDefault().postSticky(new LoginEventBus());
                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter=new ChangePassPresenter(this,mView);
    }


}
