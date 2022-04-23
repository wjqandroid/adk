package com.visionvera.psychologist.c.module.usercenter.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.eventbus.commonbean.LoginEventBus;
import com.visionvera.library.util.CheckPasswordUtil;
import com.visionvera.library.util.MD5Utils;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.usercenter.bean.ForgetPasswordCheckNumberRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.ForgetPasswordCommitRequest;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.module.usercenter.presenter.ForgetPasswordPresenter;

import org.greenrobot.eventbus.EventBus;

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
 * author:lilongfeng
 * date:2019/9/2
 * 描述:忘记密码的页面
 */
@Route(path = ARouterConstant.UserCenter.forgetPasswordActivity)
public class ForgetPasswordActivity extends BaseMVPActivity<IContract.ForgetPassword.ForgetPasswordView, ForgetPasswordPresenter> {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.tv_title)
    TextView tv_title;

    @BindView(R2.id.input_phoneNumber)
    EditText input_phoneNumber;

    @BindView(R2.id.send_check_message)
    TextView send_check_message;

    @BindView(R2.id.input_check_number)
    EditText input_check_number;

    @BindView(R2.id.input_setting_password)
    EditText input_setting_password;

    @BindView(R2.id.input_confirm_password)
    EditText input_confirm_password;

    @BindView(R2.id.forget_password_btn)
    TextView forget_password_btn;
    private Observable<Long> mObservable;
    //验证码相关的观察者流
    Disposable d;
    private String checkNumber;

    public static void startActivity(Context context, String type) {
        context.startActivity(new Intent(context, ForgetPasswordActivity.class)
                .putExtra("type", type));
    }



    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_forget_password;
    }



    @Override
    protected void doYourself() {
        ARouter.getInstance().inject(this);
        initView();
        initToolbar();
        initParseData();

    }

    private void initToolbar() {
        tv_title.setText("忘记密码");
    }

    private void initParseData() {
        if (getIntent().getSerializableExtra("type")==null){
            //登录之前的忘记密码

        }else{
            //登录之后跳转的忘记密码
            if (accountService!=null){
//                input_phoneNumber.setText(SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).getString(Constant.SP.UserInfo.userName));
                input_phoneNumber.setText(accountService.getGetAccountInfo().phoneNumber);
                Log.e("tag", "initParseData: "+accountService.getGetAccountInfo().phoneNumber);

            }
            input_phoneNumber.setFocusable(false);
            input_phoneNumber.setFocusableInTouchMode(false);
        }

    }

    private void initView() {

    }

    @OnClick({R2.id.iv_back, R2.id.send_check_message, R2.id.forget_password_btn})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.send_check_message) {//发送验证码

            if (input_phoneNumber.getText().toString().length() != 11) {
                ToastUtils.showShort("请输入正确的手机号");
                return;
            }
            sendCheckMessage();
        } else if (id == R.id.forget_password_btn) {//忘记密码的提交
            if (CheckPasswordUtil.checkPasswordRule(input_setting_password.getText().toString().trim())) {
                //规则匹配
                if (!TextUtils.equals(input_setting_password.getText().toString(), input_confirm_password.getText().toString())) {
                    ToastUtils.showShort("两次密码不一致");
                } else {
                    showLoadingDialog();

                    ForgetPasswordCommitRequest commitRequest = new ForgetPasswordCommitRequest();
                    commitRequest.setCode(input_check_number.getText().toString());
                    commitRequest.setUsername(input_phoneNumber.getText().toString());
                    commitRequest.setNewPassword(MD5Utils.MD5(input_setting_password.getText().toString()));
                    mPresenter.getForgetPasswordCommit(commitRequest, this);
                }
            } else {
                ToastUtils.showLong("密码必须为6-12位，同时包含大写和小写字母");
            }
        }
    }

    private void sendCheckMessage() {
        showLoadingDialog();
        ForgetPasswordCheckNumberRequest request = new ForgetPasswordCheckNumberRequest();
        request.setMobile(input_phoneNumber.getText().toString().trim());
        request.setType(2);
        mPresenter.getForgetPasswordCheckNumber(request, this);

    }

    private void Countdown(){
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
                        send_check_message.setEnabled(false);
                    }
                }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                ForgetPasswordActivity.this.d = d;
            }

            @Override
            public void onNext(Long num) {
                send_check_message.setText(num + "s");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                //回复原来初始状态
                send_check_message.setEnabled(true);
                send_check_message.setText("获取验证码");
            }
        });
    }

    @Override
    protected void initMVP() {
        mView=new IContract.ForgetPassword.ForgetPasswordView() {
            @Override
            public void onForgetPasswordCheckNumber(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
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
                        ToastUtils.showShort(getString(R.string.evaluation_module_checknumber_already_send));
                        if (response.getResult() instanceof String) {
                            checkNumber = (String) response.getResult();
                        }
                        Countdown();
                        break;
                    default:
                }
            }

            @Override
            public void onForgetPasswordCommit(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
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
                        ToastUtils.showShort(response.getErrMsg());

                        activityStackUtil.clearTopOfMy(activity, MainActivity.class);


                        if (getIntent().getSerializableExtra("type")==null){
                            //登录之前的忘记密码
                            if (accountService!=null){
                                accountService.clearAccountInfo();
                            }

                            ARouter.getInstance()
                                    .build(ARouterConstant.Account.AccountLoginActivity)
                                    .navigation(activity);

                            EventBus.getDefault().postSticky(new LoginEventBus());

                        }else{
                            //登录之后跳转的忘记密码

                        }

                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter=new ForgetPasswordPresenter(this,mView);
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
