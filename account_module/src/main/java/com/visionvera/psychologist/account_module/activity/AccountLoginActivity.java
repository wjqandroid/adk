package com.visionvera.psychologist.account_module.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.commonbean.AccountBean;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.activity.ProtocolActivity;
import com.visionvera.library.eventbus.commonbean.LoginEventBus;
import com.visionvera.library.util.MD5Utils;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.account_module.R;
import com.visionvera.psychologist.account_module.R2;
import com.visionvera.psychologist.account_module.beans.AccountLoginRequestBean;
import com.visionvera.psychologist.account_module.beans.AccountLoginResponseBean;
import com.visionvera.psychologist.account_module.contracts.IContract;
import com.visionvera.psychologist.account_module.presenters.AccountLoginActivityPresenter;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

import static com.visionvera.library.base.Constant.RequestReturnCode.PhoneLoginActivity_To_AccountLoginActivity_Code;

/**
 * @author: 刘传政
 * @date: 2019-08-19 14:27
 * QQ:1052374416
 * 作用:账号登录界面
 * 注意事项:
 */
@Route(path = ARouterConstant.Account.AccountLoginActivity)
public class AccountLoginActivity extends BaseMVPActivity<IContract.AccountLoginActivity.View, AccountLoginActivityPresenter> {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    //是否返回跳转前的界面
    @Autowired(name = Constant.IntentKey.requestReturnCode)
    public int requestReturnCode;

    @BindView(R2.id.et_password)
    EditText etPassword;
    @BindView(R2.id.et_phoneNumber)
    EditText etPhoneNumber;

    @BindView(R2.id.checkBox)
    CheckBox checkBox;

    private AccountLoginIntentBean accountLoginIntentBean;


    public static void startActivityForResult(Context context, AccountLoginIntentBean intentBean) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(
                    new Intent(context, AccountLoginActivity.class)
                            .putExtra("intentBean", intentBean), PhoneLoginActivity_To_AccountLoginActivity_Code);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.account_module_activity_account_login;
    }

    @Override
    protected void doYourself() {
        ImmersionBar.with(this)
                //状态栏颜色
                .statusBarColor(R.color.translucent_background)
                //状态栏文字颜色
                .statusBarDarkFont(false)
                //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看. false表示布局嵌入状态栏。true表示布局避开状态栏
                .fitsSystemWindows(false)
                //这里专门设置键盘模式,否则键盘弹出时,工具栏不跟随键盘
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .keyboardEnable(true)
                .init();

        initIntentBean();

    }

    /**
     * 获取从上个页面获取到的数据
     */
    private void initIntentBean() {
        accountLoginIntentBean = (AccountLoginIntentBean) getIntent().getSerializableExtra("intentBean");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }


    private void checkPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {

                    } else {
                        // Oups permission denied
                        ToastUtils.showLong("有权限未受理，部分功能可能影响正常使用！");

                    }
                });

    }


    @OnClick({R2.id.btn_login, R2.id.tv_phone_login, R2.id.forget_password, R2.id.login_user_protocol, R2.id.tvt_register})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        //点击按钮后，隐藏软键盘。因为测试中华为自带的密码键盘会遮挡toast的提示。
        KeyboardUtils.hideSoftInput(this);
        int viewId = view.getId();
        if (viewId == R.id.tv_phone_login) {

            PhoneLoginActivity.PhoneLoginIntentBean intentBean = new PhoneLoginActivity.PhoneLoginIntentBean(requestReturnCode);
            PhoneLoginActivity.startActivityForResult(this, intentBean);

        } else if (viewId == R.id.forget_password) {
            //忘记密码
            ARouter.getInstance().build(ARouterConstant.UserCenter.forgetPasswordActivity).navigation();
        } else if (viewId == R.id.btn_login) {
            if (TextUtils.isEmpty(etPhoneNumber.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_not_empty));
                return;
            }
            if (etPhoneNumber.getText().toString().trim().length() != 11) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_error));
                return;
            }
            if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_password_not_empty));
                return;
            }
            if (!checkBox.isChecked()) {
                ToastUtils.showShort(getString(R.string.account_module_please_check_protocol));
                return;
            }

            requestLogin(etPhoneNumber.getText().toString().trim(),
                    etPassword.getText().toString().trim());

        } else if (viewId == R.id.login_user_protocol) {
            //用户协议
            ProtocolActivity.startActivity(this, Constant.Url.request_base_url + Constant.WebUrl.user_protocol, "粤心安用户许可协议");

        } else if (viewId == R.id.tvt_register) {
            //注册
//            startActivity(new Intent(activity, RegisterActivity.class));
            PhoneLoginActivity.PhoneLoginIntentBean intentBean = new PhoneLoginActivity.PhoneLoginIntentBean(requestReturnCode);
            PhoneLoginActivity.startActivityForResult(this, intentBean);
        }
    }


    @Override
    protected void initMVP() {
        mView = new IContract.AccountLoginActivity.View() {
            @Override
            public void onLogin(AccountLoginResponseBean responseBean, ResultType resultType, String errorMsg) {
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

                        ToastUtils.showShort(responseBean.getErrMsg());

                        if (accountService != null) {
                            AccountBean accountBean = accountService.getGetAccountInfo();
                            accountBean.isLogin = true;
                            accountBean.token = responseBean.getResult().getToken();
                            accountBean.userName = responseBean.getResult().getName();
                            accountBean.userId = responseBean.getResult().getUserId() + "";
                            accountBean.phoneNumber = responseBean.getResult().getMobile() + "";
                            accountBean.userTypeList = responseBean.getResult().userTypeList;
                            accountService.updateAccountInfo(accountBean);

                            finish();
//                            activityStackUtil.clearTopOfMyIncludeMy(AccountLoginActivity.this, PhoneLoginActivity.class);

                            //发送一个eventbus广播,把必要的信息传递过去,谁爱用谁用
                            LoginEventBus loginEventBus = new LoginEventBus();
                            loginEventBus.setAccountBean(accountService.getGetAccountInfo());
                            //这里不应该发送粘性事件,没有使用场景.反而发送粘性事件会导致有些需要登录信息的界面上来就
                            //接到登录事件而出发用户看不懂的操作.
                            EventBus.getDefault().post(loginEventBus);
                        }
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new AccountLoginActivityPresenter(this, mView);
    }

    public void requestLogin(String account, String password) {

        String umeng_token = SPUtils.getInstance(Constant.SP.UserInfo.umeng).getString(Constant.SP.UserInfo.umeng_token);

        AccountLoginRequestBean requestBean
                = new AccountLoginRequestBean(account, MD5Utils.MD5(password), umeng_token);
        mPresenter.login(requestBean, this);
    }

    public static class AccountLoginIntentBean implements Serializable {

        public int requestReturnCode;

        public AccountLoginIntentBean(int requestReturnCode) {
            this.requestReturnCode = requestReturnCode;
        }
    }
}
