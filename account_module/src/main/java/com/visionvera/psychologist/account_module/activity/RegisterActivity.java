package com.visionvera.psychologist.account_module.activity;

import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.activity.ProtocolActivity;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.util.IdCardUtils;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.dialog.CenterPopup;
import com.visionvera.psychologist.account_module.R;
import com.visionvera.psychologist.account_module.R2;
import com.visionvera.psychologist.account_module.beans.AccountDentifyingCodeRequest;
import com.visionvera.psychologist.account_module.beans.AccountRegisterRequest;
import com.visionvera.psychologist.account_module.contracts.IContract;
import com.visionvera.psychologist.account_module.presenters.AccountRegisterPresenter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author: 刘传政
 * @date: 2019-08-19 14:27
 * QQ:1052374416
 * 作用:账号登录界面
 * 注意事项:
 */
// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = ARouterConstant.Account.registerActivity)
public class RegisterActivity extends BaseMVPActivity<IContract.AccountRegisterActivity.AccountRegisterView, AccountRegisterPresenter> {

    @BindView(R2.id.et_password)
    EditText etPassword;
    @BindView(R2.id.et_phoneNumber)
    EditText etPhoneNumber;
    @BindView(R2.id.tv_title)
    TextView tv_title;

    @BindView(R2.id.iv_back)
    ImageView iv_back;

    @BindView(R2.id.get_dentifying_code_tv)
    TextView get_dentifying_code_tv;

    @BindView(R2.id.input_name)
    TextView input_name;

    @BindView(R2.id.input_idcardnumber)
    TextView input_idcardnumber;

    @BindView(R2.id.checkBox)
    CheckBox checkBox;
    private Observable<Long> mObservable;

    //验证码相关的观察者流
    private Disposable d;
    private String smsCode;


    @Override
    protected int getLayoutId() {
        return R.layout.account_module_activity_register;
    }

    @Override
    protected void doYourself() {
        //设置view嵌入状态栏
        ImmersionBar.with(this)
                .statusBarColor(R.color.COLOR_WHITE_FFFFFF)
                .fitsSystemWindows(true)
                .init();
        initToolBar();

        //只允许输入0123456789x   https://www.jianshu.com/p/691b00e750c7
        input_idcardnumber.setKeyListener(DigitsKeyListener.getInstance("0123456789Xx"));
    }

    private void initToolBar() {
        iv_back.setImageResource(R.drawable.base_module_close_title);
    }


    @OnClick({R2.id.btn_login, R2.id.rl_back, R2.id.get_dentifying_code_tv, R2.id.register_user_protocol})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }

        int viewId = view.getId();
        if (viewId == R.id.rl_back) {
            finish();
        } else if (viewId == R.id.btn_login) {
            if (TextUtils.isEmpty(etPhoneNumber.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_not_empty));
                return;
            }
            if (etPhoneNumber.getText().toString().trim().length() != 11) {
                ToastUtils.showShort("手机号位数不对");
                return;
            }
            if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
                ToastUtils.showShort("验证码不能为空");
                return;
            }
            if (etPassword.getText().toString().trim().length() != 6) {
                ToastUtils.showShort(getString(R.string.account_module_get_verification_code_error));
                return;
            }
            if (TextUtils.isEmpty(input_name.getText().toString().trim())) {
                ToastUtils.showShort("姓名不能为空");
                return;
            }
            if (TextUtils.isEmpty(input_idcardnumber.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_identity_id_not_empty));
                return;
            }

            if (!IdCardUtils.isIdentityCode(input_idcardnumber.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_identity_id_error));
                return;
            }

            if (!checkBox.isChecked()) {
                ToastUtils.showShort(getString(R.string.account_module_please_check_protocol));
                return;
            }

            showLoadingDialog();

            AccountRegisterRequest registerRequest = new AccountRegisterRequest();
            registerRequest.setMobile(etPhoneNumber.getText().toString().trim());
            registerRequest.setCode(etPassword.getText().toString().trim());
            registerRequest.setName(input_name.getText().toString().trim());
            registerRequest.setCardId(input_idcardnumber.getText().toString().trim());
            registerRequest.setSource(2);

            mPresenter.getRegister(registerRequest, this);

        } else if (viewId == R.id.get_dentifying_code_tv) {
            if (TextUtils.isEmpty(etPhoneNumber.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_not_empty));
                return;
            }
            if (etPhoneNumber.getText().toString().trim().length() != 11) {
                ToastUtils.showShort(getString(R.string.account_module_phone_num_error));
                return;
            }
            getAuthCode();
        } else if (viewId == R.id.register_user_protocol) {
            //用户协议
            ProtocolActivity.startActivity(this, Constant.Url.request_base_url + Constant.WebUrl.user_protocol, "粤心安用户许可协议");

        }
    }

    private void getAuthCode() {
        showLoadingDialog();
        AccountDentifyingCodeRequest request = new AccountDentifyingCodeRequest();
        request.setMobile(etPhoneNumber.getText().toString().trim());
        request.setType(1);
        mPresenter.getSmsCode(request, this);

    }


    @Override
    protected void initMVP() {
        mView = new IContract.AccountRegisterActivity.AccountRegisterView() {
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
                        if (response.getResult() instanceof String) {
                            smsCode = (String) response.getResult();
                        }
                        ToastUtils.showShort(getString(R.string.account_module_verification_send_success));

                        Countdown();
                        break;
                }
            }

            @Override
            public void onRegister(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //这个吐司的时间必须要长些，因为文字很多。时间太短用户看不完。
                        ToastUtils.showLong(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //这个吐司的时间必须要长些，因为文字很多。时间太短用户看不完。
                        ToastUtils.showLong(errorMsg);

                        showRegisterSuccessPopup();
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new AccountRegisterPresenter(this, mView);
    }

    private void showRegisterSuccessPopup() {

        new XPopup.Builder(this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(this,
                        CenterPopup.CenterPopupType.oneButton,
                        getString(R.string.account_module_warm_notice),
                        getString(R.string.account_module_default_passsword),
                        getString(R.string.account_module_cancle),
                        getString(R.string.account_module_ok),
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    centerPopup.dismiss();
//                            activity.finish();
                    activityStackUtil.clearTopOfMy(RegisterActivity.this, AccountLoginActivity.class);
                }))

                .show();
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
                        get_dentifying_code_tv.setEnabled(false);
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                RegisterActivity.this.d = d;
            }

            @Override
            public void onNext(Long num) {
                get_dentifying_code_tv.setText(num + "S");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                //回复原来初始状态
                get_dentifying_code_tv.setEnabled(true);
                get_dentifying_code_tv.setText(getString(R.string.account_module_get_verification_code));
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
}
