package com.visionvera.psychologist.c.module;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
//import com.tencent.imsdk.TIMCallBack;
//import com.tencent.imsdk.TIMManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.qcloud.tuicore.TUILogin;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.commonbean.AccountBean;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.eventbus.commonbean.LoginEventBus;
import com.visionvera.live.base.bean.ResBean;
import com.visionvera.live.constant.Globe;
import com.visionvera.live.manager.UserManager;
import com.visionvera.live.module.home.contract.Contract;
import com.visionvera.live.module.login.bean.LoginBean;
import com.visionvera.live.module.login.contract.LoginContract;
import com.visionvera.live.module.login.presenter.LoginPresenter;
import com.visionvera.live.utils.SharedPreferenceUtils;
import com.visionvera.psychologist.account_module.util.AccountManager;
import com.visionvera.psychologist.c.MyApplication;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentGaugeActivity;
import com.visionvera.psychologist.c.module.counselling.CounsellingFragment;
import com.visionvera.psychologist.c.module.home.HomeFragment;
import com.visionvera.psychologist.c.module.home.bean.UserSigBean;
import com.visionvera.psychologist.c.module.home.bean.UserSigRequestBean;
import com.visionvera.psychologist.c.module.home.contract.IContract;
import com.visionvera.psychologist.c.module.home.presenter.VisitorLoginPresenter;
import com.visionvera.psychologist.c.module.knowledge_library.fragmet.KnowledgeCircleFragment;
import com.visionvera.psychologist.c.module.usercenter.UserCenterFragment;
import com.visionvera.psychologist.c.update.UpdateManager;
import com.visionvera.psychologist.c.utils.ScreenUtils;
import com.visionvera.psychologist.c.widget.NotifyRadioButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * @Desc 心理测评主页
 * @Author yemh
 * @Date 2019-10-29 15:39
 */
@Route(path = ARouterConstant.Evaluation.mainActivity)
public class MainActivity extends BaseMVPActivity<IContract.VisitorLogin.VisitorLoginView, VisitorLoginPresenter>
        implements RadioGroup.OnCheckedChangeListener, LoginContract.ILogin.IView, Contract.IUserSign.IView, HomeFragment.setChecked {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.main_content)
    FrameLayout mainContent;

    @BindView(R2.id.main_tab)
    public RadioGroup mRadioGroup;

    @BindView(R2.id.tab_home)
    NotifyRadioButton tabHome;

    @BindView(R2.id.tab_all_evaluation)
    public NotifyRadioButton tabAllEvaluation;

    @BindView(R2.id.tab_my_evluation)
    NotifyRadioButton tabMyEvluation;

    @BindView(R2.id.tab_health_report)
    NotifyRadioButton tabHealthReport;

    /**
     * 呼叫页面
     */
    @BindView(R2.id.call_bg)
    public RelativeLayout call_bg;


    public FragmentManager fragmentManager;
    //首页
    private HomeFragment homeFragment;
    //心理咨询
    public CounsellingFragment allEvaluationFragment;
    //知识库
    public KnowledgeCircleFragment knowledgeLibraryMainFragment;
    //个人中心
    private UserCenterFragment userCenterFragment;

    public FragmentTransaction transaction;
    //上一个被选中的tab
    int preSelectedTabPosition = 0;
    boolean isGoToLive = false;
    boolean dianjixinlizixun = false;
    private int chatterId = -1;
    private LoginPresenter lPresenter;
//    private UserSignPresenter uPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*防止首次安装点击home键重新实例化.这个问题很诡异，https://blog.csdn.net/yuzhiqiang_1993/article/details/77878248*/
        //以后需要添加启动页
        if (!this.isTaskRoot()) {
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    finish();
                    return;
                }
            }
        }
        checkPermissions();
        getUriData();

        //如果已登录心理APP账号，去登录腾讯IM
        if (accountService != null && accountService.getGetAccountInfo() != null && accountService.getGetAccountInfo().isLogin) {
            AccountBean getAccountInfo = AccountManager.getInstence().getGetAccountInfo();
            //此处的使用加前缀的userId，避免腾讯IM互踢    userIdPrefix+userId
            String userId = getAccountInfo.userIdPrefix + getAccountInfo.userId;
            MyApplication.getInstance().loginTencentIM(userId, getAccountInfo.userSig, getAccountInfo.userName, getAccountInfo.photoUrl);
        }

    }
    private void checkPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE)
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
    private void getUriData() {
        Intent intent = getIntent();
        if (null != intent && null != intent.getData()) {
            Uri uri = intent.getData();
            Logger.i("网页传过来的数据" + uri.toString());
            String scheme = uri.getScheme();
            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getPath();
            //获取网页传过来的量表id
            String liangbiaoId = uri.getQueryParameter("id");
            int anInt = Integer.parseInt(liangbiaoId);
            if (anInt != -1) {
                SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity
                        .GaugeIntentBean(anInt);
                SelfAssessmentGaugeActivity.startActivity(activity, gaugeIntentBean);
            }

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void doYourself() {
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);

        int wh = (int) ScreenUtils.dp2px(this, 24);
        Drawable home = getResources().getDrawable(R.drawable.evaluation_module_tab_home);
        home.setBounds(0, 0, wh, wh);
        tabHome.setCompoundDrawables(null, home, null, null);

        Drawable all = getResources().getDrawable(R.drawable.evaluation_module_tab_knowledge);
        all.setBounds(0, 0, wh, wh);
        tabAllEvaluation.setCompoundDrawables(null, all, null, null);

        Drawable my = getResources().getDrawable(R.drawable.evaluation_module_tab_live);
        my.setBounds(0, 0, wh, wh);
        tabMyEvluation.setCompoundDrawables(null, my, null, null);

        Drawable health = getResources().getDrawable(R.drawable.evaluation_module_tab_mine);
        health.setBounds(0, 0, wh, wh);
        tabHealthReport.setCompoundDrawables(null, health, null, null);

        setListener();
        loadData();


        lPresenter = new LoginPresenter(this);
//        uPresenter = new UserSignPresenter(this);


    }

    private void setListener() {
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    public void setRadioChecked(int position) {
        if (position == 1) {
            tabAllEvaluation.setChecked(true);
        }
    }

    private void loadData() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            transaction.add(R.id.main_content, homeFragment, HomeFragment.class.getSimpleName());
        } else {
            transaction.show(homeFragment);
        }
        transaction.commitAllowingStateLoss();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = fragmentManager.beginTransaction();

        if (checkedId == R.id.tab_home) {
            hideAllFragment();
            if (homeFragment == null) {
                homeFragment = HomeFragment.newInstance();
                transaction.add(R.id.main_content, homeFragment, HomeFragment.class.getSimpleName());
            } else {
                transaction.show(homeFragment);
            }
            homeFragment.updateStatuBar();
            preSelectedTabPosition = 0;
        } else if (checkedId == R.id.tab_all_evaluation) {

//            ToastUtils.showShort("敬请期待");
//            dianjixinlizixun = true;

            hideAllFragment();
           /* if (allEvaluationFragment == null) {
                allEvaluationFragment = CounsellingFragment.newInstance();
                transaction.add(R.id.main_content, allEvaluationFragment, CounsellingFragment.class.getSimpleName());
            } else {
                transaction.show(allEvaluationFragment);
            }
            allEvaluationFragment.updateStatuBar();*/

            //知识库
            if (knowledgeLibraryMainFragment == null) {
                knowledgeLibraryMainFragment = KnowledgeCircleFragment.newInstance();
                transaction.add(R.id.main_content, knowledgeLibraryMainFragment, KnowledgeCircleFragment.class.getSimpleName());
            } else {
                transaction.show(knowledgeLibraryMainFragment);
            }
            knowledgeLibraryMainFragment.updateStatuBar();
            preSelectedTabPosition = 1;
        } else if (checkedId == R.id.tab_my_evluation) {
            //心理课程

            if (accountService != null && accountService.getGetAccountInfo() != null && accountService.getGetAccountInfo().isLogin) {
                ARouter.getInstance().build(ARouterConstant.Live.liveHomeActivity).navigation();

            } else {
                ARouter.getInstance().build(ARouterConstant.Account.AccountLoginActivity).navigation();
            }
            isGoToLive = true;
        } else if (checkedId == R.id.tab_health_report) {
            hideAllFragment();
            if (userCenterFragment == null) {
                userCenterFragment = UserCenterFragment.newInstance();
                transaction.add(R.id.main_content, userCenterFragment, UserCenterFragment.class.getSimpleName());
            } else {
                transaction.show(userCenterFragment);
            }
            userCenterFragment.updateStatuBar();
            userCenterFragment.requestUserBasicInfo();
            preSelectedTabPosition = 3;
        }
        transaction.commitAllowingStateLoss();

       /* if (dianjixinlizixun) {
            //如果点击了心理咨询.再强制选回原来的状态.
            switch (preSelectedTabPosition) {
                case 0:
                    tabHome.setChecked(true);
                    break;
                case 3:
                    tabHealthReport.setChecked(true);
                    break;
            }
            dianjixinlizixun = false;
        }*/
    }


    /**
     * 隐藏所有fragment
     */
    private synchronized void hideAllFragment() {
        if (homeFragment != null && homeFragment.isVisible()) {
            transaction.hide(homeFragment);
        }
        if (allEvaluationFragment != null && allEvaluationFragment.isVisible()) {
            transaction.hide(allEvaluationFragment);
        }
        if (knowledgeLibraryMainFragment != null && knowledgeLibraryMainFragment.isVisible()) {
            transaction.hide(knowledgeLibraryMainFragment);
        }

        if (userCenterFragment != null && userCenterFragment.isVisible()) {
            transaction.hide(userCenterFragment);
        }
    }

    @Override
    protected void initMVP() {
        mView = new IContract.VisitorLogin.VisitorLoginView() {
            @Override
            public void onVisitorLogin(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                    case SERVER_ERROR:
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
//                        ToastUtils.showShort(errorMsg);
                        break;
                }
            }

            @Override
            public void onUserSig(UserSigBean userSigBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:

                        break;
                    case SERVER_NORMAL_DATAYES:

                        if (!userSigBean.getResult().getTxPrefix().isEmpty() && !userSigBean.getResult().getSign().isEmpty()) {
                            AccountBean accountBean = accountService.getGetAccountInfo();
                            Logger.i("onUserSig:设置前的用户信息 " + new Gson().toJson(accountBean));
                            accountBean.isLogin = true;
                            accountBean.userSig = userSigBean.getResult().getSign();
                            accountBean.userIdPrefix = userSigBean.getResult().getTxPrefix();
                            AccountManager.getInstence().updateAccountInfo(accountBean);

                            //新登录IM
                            AccountBean getAccountInfo = AccountManager.getInstence().getGetAccountInfo();
                            //此处的使用加前缀的userId，避免腾讯IM互踢    userIdPrefix+userId
                            String userId = getAccountInfo.userIdPrefix + getAccountInfo.userId;
                            MyApplication.getInstance().loginTencentIM(userId, userSigBean.getResult().getSign(), getAccountInfo.userName, getAccountInfo.photoUrl);
                            Logger.i("onUserSig:提交腾讯im的数据：" + accountService.getGetAccountInfo().userId + "::" + userSigBean.getResult().getSign());
                            Logger.i("onUserSig:设置后的用户信息 " + new Gson().toJson(accountBean));
                        } else {
                            ToastUtils.showShort("获取userSig为空，腾讯IM无法登录！");
                        }
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new VisitorLoginPresenter(this, mView);
        mPresenter.getVisitorLogin(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isGoToLive) {
            switch (preSelectedTabPosition) {
                case 0:
                    tabHome.setChecked(true);
                    break;
                case 1:
                    tabAllEvaluation.setChecked(true);
                    break;
                case 3:
                    tabHealthReport.setChecked(true);
                    break;
            }
            isGoToLive = false;

        }
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShort("再按一次退出");
                mExitTime = System.currentTimeMillis();
                return false;
            } else {
                finish();
                return true;
            }
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLoginEventBus(LoginEventBus busBean) {
        //接收到了登录的消息
        if (busBean != null) {
            if (busBean.getAccountBean() != null) {
                if (busBean.getAccountBean().isLogin) {//如果登录了

//                    requestLiveLogin(busBean);
                    /**
                     * 登录后就获取腾讯的userSig
                     */
                    UserSigRequestBean userSigRequestBean = new UserSigRequestBean();
                    userSigRequestBean.setAuthorization(busBean.getAccountBean().token);
                    mPresenter.getUserSig(userSigRequestBean, this);


                } else {//如果是退出登录了
                    TUILogin.logout(new V2TIMCallback() {
                        @Override
                        public void onSuccess() {
                            Log.i("TAG", "腾讯im退出登录");
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });
                }
            }
        }
    }


    /**
     * 登录操作
     *
     * @param busBean
     */
    private void requestLiveLogin(LoginEventBus busBean) {
        UserManager.getInstence().updateUserInfo(busBean.getAccountBean().userId + "", "",
                "", "", busBean.getAccountBean().token, "",
                chatterId, "", "");

        lPresenter.login(this, this);
    }


    @Override
    public void showLoginResult(ResBean<LoginBean> bean) {
        if (bean.isSuccess()) {
            SharedPreferenceUtils.putBoolean(Globe.SP_ISLOGIN, true);
            LoginBean result = bean.getResult();
            Logger.e("result==" + result.toString());
            chatterId = result.getChatterId();
            UserManager.getInstence().updateUserInfo(result.getUserId() + "", result.getMobile(),
                    result.getNickname(), result.getPictureUrl(), result.getToken(), result.getDeptName(),
                    chatterId, result.getHospitalName(), result.getTitle());
            //直播的im关闭
//            requestUserSign();

        }
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void showUserSignResult(ResBean<String> bean) {
     /*   Log.e("腾讯im", "showUserSignResult: " + new Gson().toJson(bean));
        if (bean.isSuccess()) {
            String result = bean.getResult();
            if (result != null) {
                SharedPreferenceUtils.putString(Globe.SP_USER_SIGN, result);
                ImUtils.loginTecentIm(chatterId);
            }
        }*/
    }

    @Override
    public void onSetChecked(int position) {
        switch (position) {
            case 1:
                tabAllEvaluation.setChecked(true);
                break;
            case 2:
                tabMyEvluation.setChecked(true);
                break;
            default:
                break;
        }
    }

    /**
     * 获取第三方IM用户签名
     */
   /* private void requestUserSign() {
        int chatterId = UserManager.getInstence().getChatterId();
        if (chatterId == -1) {
            return;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put(HttpInterface.ParamKeys.CHATTER_ID, chatterId);
        uPresenter.getUserSign(this, map, this);
    }*/
    private void checkUpdate() {
        new UpdateManager(MainActivity.this, new UpdateManager.UpdateListener() {
            @Override
            public void onUpdate() {
            }

            @Override
            public void onUpdateCancel() {
            }
        }).checkUpdate();
    }

}
