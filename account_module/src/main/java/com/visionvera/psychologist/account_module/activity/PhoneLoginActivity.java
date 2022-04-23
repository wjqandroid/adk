package com.visionvera.psychologist.account_module.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.KeyboardUtils;
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
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.eventbus.commonbean.LoginEventBus;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.dialog.ConfirmDialog;
import com.visionvera.psychologist.account_module.R;
import com.visionvera.psychologist.account_module.R2;
import com.visionvera.psychologist.account_module.beans.AccountDentifyingCodeRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginResponseBean;
import com.visionvera.psychologist.account_module.contracts.IContract;
import com.visionvera.psychologist.account_module.presenters.PhoneLoginPresenter;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.visionvera.library.base.Constant.RequestReturnCode.AccountLoginActivity_To_PhoneLoginActivity_Code;

/**
 * @author: 刘传政
 * @date: 2019-08-19 14:27
 * QQ:1052374416
 * 作用:手机号登录界面
 * 注意事项:
 */
// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = ARouterConstant.Account.PhoneLoginActivity)
public class PhoneLoginActivity extends BaseMVPActivity<IContract.PhoneLoginActivity.PhoneLoginView, PhoneLoginPresenter> {

    //是否返回跳转前的界面
    @Autowired(name = Constant.IntentKey.requestReturnCode)
    public int requestReturnCode;

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.et_password)
    EditText etPassword;
    @BindView(R2.id.et_phoneNumber)
    EditText etPhoneNumber;

    @BindView(R2.id.phone_login_get_verification_code)
    TextView verification_code;


    @BindView(R2.id.checkBox)
    CheckBox checkBox;

    private Observable<Long> mObservable;

    //验证码相关的观察者流
    private Disposable d;
    private ConfirmDialog confirmDialog;

    public static void startActivityForResult(Context context,PhoneLoginIntentBean intentBean) {
        if (context instanceof BaseActivity){
            ((BaseActivity)context).startActivityForResult(
                    new Intent(context,PhoneLoginActivity.class)
                            .putExtra("intentBean",intentBean),AccountLoginActivity_To_PhoneLoginActivity_Code);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.account_module_activity_phone_login;
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


    @OnClick({R2.id.btn_login, R2.id.tv_account_login, R2.id.tv_account_login2
            , R2.id.phone_login_get_verification_code, R2.id.user_protocol})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        KeyboardUtils.hideSoftInput(this);
        int viewId = view.getId();
        if (viewId == R.id.btn_login) {

            if (TextUtils.isEmpty(etPhoneNumber.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_not_empty));
                return;
            }
            if (etPhoneNumber.getText().toString().trim().length() != 11) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_error));
                return;
            }
            if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_verification_code_not_empty));
                return;
            }

            if (etPassword.getText().toString().trim().length() != 6) {
                ToastUtils.showShort(getString(R.string.account_module_get_verification_code_error));
                return;
            }

            if(!checkBox.isChecked()){
                ToastUtils.showShort(getString(R.string.account_module_please_check_protocol));
                return;
            }

            PhoneLoginRequest phoneLoginRequest = new PhoneLoginRequest();
            phoneLoginRequest.setUsername(etPhoneNumber.getText().toString().trim());
            phoneLoginRequest.setSmsCode(etPassword.getText().toString().trim());
            showLoadingDialog();
            mPresenter.getPhoneLogin(phoneLoginRequest, this);


        } else if (viewId == R.id.tv_account_login || viewId == R.id.tv_account_login2) {
            //账号登录
//            AccountLoginActivity.AccountLoginIntentBean intentBean=new AccountLoginActivity.AccountLoginIntentBean(requestReturnCode);
//            AccountLoginActivity.startActivityForResult(this,intentBean);
            finish();
        } else if (viewId == R.id.phone_login_get_verification_code) {

            //获取验证码
            if (TextUtils.isEmpty(etPhoneNumber.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_not_empty));
                return;
            }
            if (etPhoneNumber.getText().toString().trim().length() != 11) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_error));
                return;
            }
            getAuthCode();
        }else if (viewId==R.id.user_protocol){
            //用户协议
            ProtocolActivity.startActivity(this, Constant.Url.request_base_url + Constant.WebUrl.user_protocol, "粤心安用户许可协议");
        }
    }

    private void getAuthCode() {
        showLoadingDialog();
        AccountDentifyingCodeRequest request = new AccountDentifyingCodeRequest();
        request.setMobile(etPhoneNumber.getText().toString().trim());
        request.setType(4);
        mPresenter.getSmsCode(request, this);

    }


    @Override
    protected void initMVP() {
        mView = new IContract.PhoneLoginActivity.PhoneLoginView() {
            @Override
            public void onSmsCode(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
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


                        ToastUtils.showShort(getString(R.string.account_module_verification_send_success));
                        Countdown();
                        break;
                }
            }

            @Override
            public void onPhoneLogin(PhoneLoginResponseBean responseBean, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        ToastUtils.showLong(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:


                        /*if (requestReturnCode == 0) {
                            //正常登陆跳转

                        } else {
                            //可以进一步通过requestReturnCode判断是哪里跳转过来的,进而有特殊处理

                        }*/

                        if (responseBean.getResult().isIsRegist()){
                            //注册

                            showRegisterSuccessPopup(responseBean);

                        }else{
                            //登录
                            ToastUtils.showShort("登录成功");

                            LoginSuccessNext(responseBean);

                        }

                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new PhoneLoginPresenter(this, mView);
    }

    private void LoginSuccessNext(PhoneLoginResponseBean responseBean) {
        if (accountService != null) {
            AccountBean accountBean = accountService.getGetAccountInfo();
            accountBean.isLogin = true;
            accountBean.token = responseBean.getResult().getUserInfo().getToken() + "";
            accountBean.userName = responseBean.getResult().getUserInfo().getName() + "";
            accountBean.userId = responseBean.getResult().getUserInfo().getUserId() + "";
            accountBean.phoneNumber = responseBean.getResult().getUserInfo().getMobile() + "";
            accountBean.userTypeList = responseBean.getResult().getUserInfo().userTypeList;
            accountService.updateAccountInfo(accountBean);
        }

//                        finish();
        activityStackUtil.clearTopOfMyIncludeMy(PhoneLoginActivity.this, AccountLoginActivity.class);
        //发送一个eventbus广播,把必要的信息传递过去,谁爱用谁用
        if (accountService != null) {
            AccountBean accountBean = accountService.getGetAccountInfo();
            LoginEventBus loginEventBus = new LoginEventBus();
            loginEventBus.setAccountBean(accountBean);
            //这里不应该发送粘性事件,没有使用场景.反而发送粘性事件会导致有些需要登录信息的界面上来就
            //接到登录事件而出发用户看不懂的操作.
            EventBus.getDefault().post(loginEventBus);
        }
    }

    /**
     * 倒计时
     */
    private void Countdown() {
        final int count = 60;//倒计时10秒
        mObservable = Observable.interval(0, 1, TimeUnit.SECONDS);
        mObservable.take(count + 1)//设置重复次数
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        verification_code.setEnabled(false);
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                PhoneLoginActivity.this.d = d;
            }

            @Override
            public void onNext(Long num) {
                verification_code.setText(num + "S");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                //回复原来初始状态
                verification_code.setEnabled(true);
                verification_code.setText(getString(R.string.account_module_get_verification_code));
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //切断水管,防止空指针
        if (d != null) {
            d.dispose();
        }
    }

    public static class PhoneLoginIntentBean implements Serializable {

        public int requestReturnCode;

        public PhoneLoginIntentBean(int requestReturnCode) {
            this.requestReturnCode = requestReturnCode;
        }
    }

    private void showRegisterSuccessPopup(PhoneLoginResponseBean responseBean) {


        confirmDialog = new ConfirmDialog(this,
                "温馨提示",
                "默认密码是123456，请登录及时修改",
                "",
                "确定",
                new ConfirmDialog.OnCloseListener() {
                    @Override
                    public void onClick(AlertDialog dialog, int buttonType) {
                        confirmDialog.dismiss();
                        LoginSuccessNext(responseBean);
                    }
                });
        //外部点击可消失
        confirmDialog.setOutCancel(false);
        confirmDialog.show();

       /* new XPopup.Builder(PhoneLoginActivity.this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(PhoneLoginActivity.this,
                        CenterPopup.CenterPopupType.oneButton,
                        getString(R.string.account_module_warm_notice),
                        getString(R.string.account_module_default_passsword),
                        getString(R.string.account_module_cancle),
                        getString(R.string.account_module_ok) ,
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    centerPopup.dismiss();
//                            activity.finish();
                    LoginSuccessNext(responseBean);
                }))

                .show();*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();



    }
}
