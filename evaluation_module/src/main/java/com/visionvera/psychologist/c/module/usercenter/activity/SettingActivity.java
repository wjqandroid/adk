package com.visionvera.psychologist.c.module.usercenter.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.activity.ProtocolActivity;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.eventbus.commonbean.LoginEventBus;
import com.visionvera.library.util.BaseUtils;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.module.usercenter.presenter.LoginOutPresenter;
import com.visionvera.psychologist.c.update.UpdateManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

import static com.visionvera.library.base.Constant.RequestReturnCode.UserCenterFragment_To_SettingActivity_Code;

/**
 * @Desc 设置中心
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class SettingActivity extends BaseMVPActivity<IContract.LoginOut.LoginOutView, LoginOutPresenter> {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.evaluation_module_tv_title)
    TextView tvTitle;

    @BindView(R2.id.rl_setting_account)
    RelativeLayout rlSettingAccount;

    @BindView(R2.id.cb_push)
    CheckBox mCheckBox;

    @BindView(R2.id.rl_about_us)
    RelativeLayout rlAboutUs;

    @BindView(R2.id.tv_version)
    TextView tvVersion;

    @BindView(R2.id.set_version_layout)
    RelativeLayout updateLayout;

    public static void startActivityForResult(Context context) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(new Intent(context, SettingActivity.class), UserCenterFragment_To_SettingActivity_Code);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_setting;
    }

    @Override
    protected void doYourself() {
        ARouter.getInstance().inject(this);

        initView();
        initData();
    }

    private void initView() {
        String versionName = BaseUtils.getVersionName(this);
        int versionCode = getVersionCode(this);
        tvVersion.setText("V" + versionName  + "版本");
//        tvVersion.setText("V" + versionName + "." + versionCode + "版本");
    }

    public static synchronized int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    private void initData() {
        tvTitle.setText(R.string.evaluation_module_setting);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                com.blankj.utilcode.util.ToastUtils.showLong(getResources().getString(R.string.evaluation_module_not_develop));
                //新消息通知
                if (isChecked) {

                } else {

                }
            }
        });

        updateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions();
            }
        });

    }

    private void checkPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                        checkUpdate();
                    } else {
                        // Oups permission denied
                        finish();
                        ToastUtils.showShort("必须授予权限");
                    }
                });
    }

    @OnClick({R2.id.evaluation_module_iv_back, R2.id.rl_setting_account,
            R2.id.evaluation_module_exit, R2.id.rl_about_us})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_iv_back) {
            finish();
        } else if (viewId == R.id.rl_setting_account) {
            //账号安全
            AccountSafeActivity.startActivity(this);
        } else if (viewId == R.id.evaluation_module_exit) {
            //退出
            showLoadingDialog();
            mPresenter.getLoginOut(this);

        } else if (viewId == R.id.rl_about_us) {
            ProtocolActivity.startActivity(this, Constant.Url.request_base_url + Constant.WebUrl.about_us, "关于我们");

        }
    }

    @Override
    protected void initMVP() {
        mView = new IContract.LoginOut.LoginOutView() {
            @Override
            public void onLoginOut(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
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
                        ToastUtils.showShort("已退出登录");


                        if (accountService != null) {
                            accountService.clearAccountInfo();
                            //发送一个eventbus广播,把必要的信息传递过去,谁爱用谁用
                            LoginEventBus loginEventBus = new LoginEventBus();
                            loginEventBus.setAccountBean(accountService.getGetAccountInfo());
                            EventBus.getDefault().postSticky(loginEventBus);
                        }

                        setResult(UserCenterFragment_To_SettingActivity_Code);
                        finish();
                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new LoginOutPresenter(this, mView);
    }

    private void checkUpdate(){
        new UpdateManager(SettingActivity.this, new UpdateManager.UpdateListener() {
            @Override
            public void onUpdate() {
            }

            @Override
            public void onUpdateCancel() {
            }
        }).checkUpdate();
    }

}
