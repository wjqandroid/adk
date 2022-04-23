package com.visionvera.psychologist.c;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lei.lib.java.rxcache.RxCache;
import com.lei.lib.java.rxcache.converter.GsonConverter;
import com.lei.lib.java.rxcache.mode.CacheMode;
import com.lxj.xpopup.XPopup;
import com.orhanobut.logger.Logger;
//import com.tencent.android.tpush.XGIOperateCallback;
//import com.tencent.android.tpush.XGPushConfig;
//import com.tencent.android.tpush.XGPushManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMConversationListener;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.umeng.commonsdk.UMConfigure;
//import com.umeng.message.IUmengRegisterCallback;
//import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;
import com.visionvera.library.BaseApplication;
import com.visionvera.library.BuildConfig;
import com.visionvera.library.base.Constant;
import com.visionvera.library.eventbus.commonbean.LoginEventBus;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.library.util.SpUtil;
import com.visionvera.library.widget.dialog.CenterOneButtonPopup;
import com.visionvera.psychologist.account_module.util.AccountManager;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultDetailActivity;
import com.visionvera.psychologist.c.utils.CommonUtils;
import com.visionvera.psychologist.c.utils.DeviceIdUtil;
import com.visionvera.psychologist.c.widget.tencentIm.IMUtil;
import com.visionvera.psychologist.c.widget.tencentIm.utils.TencentIMManager;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MyApplication extends BaseApplication {


    private String TAG = "MyApplication";
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    private AppCompatActivity curActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;


        SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).put(Constant.SP.UserInfo.deviceId, DeviceIdUtil.getDeviceId(this));

        initUMShare();

        initUMPush();

        //腾讯IM
        initIM();

        //监听应用中所有Activity的生命周期的调用情、检测APP是否处于前台
        registerActivityLifecycleCallbacks(new StatisticActivityLifecycleCallback());

        //初始化腾讯bugly
        initBugly();

        //缓存
        initCache();

        initPush();
    }

    private void initPush() {

////        1. 在XGPushManager.registerPush前，开启Debug日志数据（注意：上线时请设置为false）
////        TPNS register push success with token : 6ed8af8d7b18049d9fed116a9db9c71ab44d5565
//        XGPushConfig.enableDebug(this, true);
//        XGPushConfig.enableOtherPush(this, true);
//        XGPushManager.registerPush(this, new XGIOperateCallback() {
//            @Override
//            public void onSuccess(Object data, int flag) {
//                //token在设备卸载重装的时候有可能会变
//                Log.e("TPush", "注册成功，设备token为：" + data);
//            }
//
//            @Override
//            public void onFail(Object data, int errCode, String msg) {
//                Log.e("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
//            }
//        });
//        XGPushConfig.getToken(getApplicationContext());//获取 Token  TPNS Token
    }

    //记录 APP启动时间
    private void saveStartTime() {
        //当前时间
        String currentTime = CommonUtils.getCurrentTime();
        SpUtil spUtil = new SpUtil(MyApplication.getInstance(), "APP启动时间");
        spUtil.putString("app_start_time", currentTime);
    }


    //腾讯IM、音视频聊天初始化
    private void initIM() {
        IMUtil.getInstance().initIM(MyApplication.getInstance(), TencentIMManager.SDKAPPID, new IMUtil.InitIMCallBack() {
            @Override
            public void onKickedOffline() {
                //同时登录后的强制下线弹窗
                ShowOffLinePopup();
            }

            @Override
            public void onUserSigExpired() {
                //登录票据已经过期回调

            }
        });
    }

    //登录腾讯IM
    public void loginTencentIM(String userId, String userSig, String userName, String photoUrl) {
        IMUtil.getInstance().loginIM(userId, userSig, userName, photoUrl,
                new IMUtil.LoginCallBack() {
                    @Override
                    public void loginSuccess(int code, String msg) {
//                        ToastUtils.showShort("腾讯im登录成功" + userId);
//                        Log.e("TAG", "腾讯im登录成功" + userId);
                        saveStartTime();
                    }

                    @Override
                    public void loginUserIdFail(int code, String msg) {
                        ToastUtils.showShort("腾讯IM登录失败:" + code + msg);
                        if (code == 6208) {//其他终端登录同一个帐号，引起已登录的帐号被踢，需重新登录。
                            ShowOffLinePopup();
                        } else if (code == 70001) {//UserSig 已过期，请重新生成。建议 UserSig 有效期设置不小于24小时。
                            if (AccountManager.getInstence().getGetAccountInfo().isLogin) {
                                EventBus.getDefault().postSticky(new LoginEventBus());
                            }
                        }
                    }
                });
    }


    private void initCache() {
        //简单初始化
        RxCache.init(this);//为RxCache提供Context
        //高级初始化
        new RxCache.Builder()
                .setDebug(true)   //开启debug，开启后会打印缓存相关日志，默认为true
                .setConverter(new GsonConverter())  //设置转换方式，默认为Gson转换
                .setCacheMode(CacheMode.BOTH)   //设置缓存模式，默认为二级缓存
//                .setMemoryCacheSizeByMB(50)   //设置内存缓存的大小，单位是MB
                .setDiskCacheSizeByMB(100)    //设置磁盘缓存的大小，单位是MB
                .setDiskDirName("RxCache")    //设置磁盘缓存的文件夹名称
                .build();
    }

    private void initBugly() {
        Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                //因为bugly的更新对话框,点击更新时不给提示就在后台通知栏下载了,用户不知道.
                //我又无法给确定按钮设置点击事件
                //所以就拿到更新说明内容,追加一行说明,并显示红色.最大限度提示用户再通知栏查看.
                TextView beta_upgrade_feature = view.findViewWithTag("beta_upgrade_feature");
                String originStr = beta_upgrade_feature.getText().toString();
                String appendStr = "\n确定后可在通知栏查看下载进度";
                String finalStr = originStr + appendStr;
                beta_upgrade_feature.setText(finalStr);
                ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor("#FF0000"));

                SpannableStringBuilder builder = new SpannableStringBuilder(finalStr);
                int start = originStr.length();
                builder.setSpan(redSpan, start, finalStr.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                beta_upgrade_feature.setText(builder);


            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {

            }
        };
//        CrashReport.initCrashReport(getApplicationContext(), "ae0fb2fb9c", false);
        //用到了bugly的应用升级功能
        //测试升级
        Bugly.init(getApplicationContext(), Constant.Url.bugly_appid, BuildConfig.DEBUG);
    }


    /**
     * 初始化友盟推送
     */
    private void initUMPush() {
        UMConfigure.init(this,
                "5cf09dc20cafb211c000057e",
                "Umeng",
                UMConfigure.DEVICE_TYPE_PHONE,
                "bbe8cbd1acfcf285a8e22f5fdf481d5d");

        PlatformConfig.setWeixin("wx0bb4497d688245fe", "f3ea3e840f337d8a1578ba35de9517fa");
        PlatformConfig.setQQZone("101847855", "a5b0bf7408ef6c539332b9a1a027ee53");
        PlatformConfig.setSinaWeibo("4249861537", "76dab7078de78a7c624cf77737047c09", "http://sns.whalecloud.com");

        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */

//        UMConfigure.setLogEnabled(BuildConfig.LOG);
//        //获取消息推送代理示例
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
//                if (BuildConfig.LOG) {
//                    Logger.i("注册成功：deviceToken：-------->  " + deviceToken);
//                }
//
//                //记录友盟token
//                SPUtils spUtils = SPUtils.getInstance(Constant.SP.UserInfo.umeng);
//                spUtils.put(Constant.SP.UserInfo.umeng_token, deviceToken);
//
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                Logger.i("注册失败：-------->  " + "s:" + s + ",s1:" + s1);
//            }
//        });

    }

    /**
     * 初始化友盟分享
     */
    private void initUMShare() {
//        PlatformConfig.setQQZone("101580611", "f8d10ef098339fbac60c40a8c7536888");
    }


    /**
     * 同时登录后的强制下线弹窗
     */
    private void ShowOffLinePopup() {
        if (curActivity == null) {
            return;
        }
        new XPopup.Builder(curActivity)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .asCustom(new CenterOneButtonPopup(curActivity, "提醒", "您的帐号在其它地方登录，您已下线", "确定", () -> {

                    ToastUtils.showShort("已退出登录");

                    AccountManager accountService = AccountManager.getInstence();
                    accountService.clearAccountInfo();

                    LoginEventBus loginEventBus = new LoginEventBus();
                    loginEventBus.setAccountBean(accountService.getGetAccountInfo());
                    EventBus.getDefault().postSticky(loginEventBus);

//                    loginOut();

                })).show();
    }

    //退出登录
    private void loginOut() {
        OkHttpClient mOkHttpClient = RetrofitManager.getOkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(Constant.Url.request_base_url + "gateway/authapi/loginOut")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                openMainActivity();
            }

            @Override
            public void onResponse(Call call, Response response) {

                openMainActivity();

            }
        });
    }


    class StatisticActivityLifecycleCallback implements ActivityLifecycleCallbacks {
        private int foregroundActivities = 0;
        private boolean isChangingConfiguration;

        private final V2TIMConversationListener unreadListener = new V2TIMConversationListener() {
            @Override
            public void onTotalUnreadMessageCountChanged(long totalUnreadCount) {
//                HUAWEIHmsMessageService.updateBadge(myApplication, (int) totalUnreadCount);
            }
        };

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
//            DemoLog.i("TAG", "onActivityCreated bundle: " + bundle);
            if (bundle != null) { // 若bundle不为空则程序异常结束
                // 重启整个程序
                Intent intent = new Intent(activity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            foregroundActivities++;
            if (foregroundActivities == 1 && !isChangingConfiguration) {
                // 应用切到前台
//                DemoLog.i("TAG", "application enter foreground");
                V2TIMManager.getOfflinePushManager().doForeground(new V2TIMCallback() {
                    @Override
                    public void onError(int code, String desc) {
//                        DemoLog.e("TAG", "doForeground err = " + code + ", desc = " + ErrorMessageConverter.convertIMError(code, desc));
//                        DemoLog.e("TAG", "doForeground err = " + code + ", desc = " + desc);
                    }

                    @Override
                    public void onSuccess() {
//                        DemoLog.i("TAG", "doForeground success");
                    }
                });

                V2TIMManager.getConversationManager().removeConversationListener(unreadListener);
            }
            isChangingConfiguration = false;
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            foregroundActivities--;
            if (foregroundActivities == 0) {
                // 应用切到后台
//                DemoLog.i("TAG", "application enter background");
                V2TIMManager.getConversationManager().getTotalUnreadMessageCount(new V2TIMValueCallback<Long>() {
                    @Override
                    public void onSuccess(Long aLong) {
                        int totalCount = aLong.intValue();
                        V2TIMManager.getOfflinePushManager().doBackground(totalCount, new V2TIMCallback() {
                            @Override
                            public void onError(int code, String desc) {
//                                DemoLog.e(TAG, "doBackground err = " + code + ", desc = " + ErrorMessageConverter.convertIMError(code, desc));
//                                DemoLog.e("TAG", "doBackground err = " + code + ", desc = " +desc);
                            }

                            @Override
                            public void onSuccess() {
//                                DemoLog.i("TAG", "doBackground success");
                            }
                        });
                    }

                    @Override
                    public void onError(int code, String desc) {

                    }
                });

                V2TIMManager.getConversationManager().addConversationListener(unreadListener);
            }
            isChangingConfiguration = activity.isChangingConfigurations();
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }

    /**
     * 检测腾讯IM的用户是否登录
     *
     * @param
     */
//    private void checkTencentIMLogin(AccountManager accountService) {
//        if (TIMManager.getInstance().getLoginStatus() == TIMManager.TIM_STATUS_LOGINED) {
//            Logger.i("onActivityStarted:腾讯im已经登录账号 ");
//        } else {
//            Logger.i("oonActivityStarted:腾讯im没有登录账号，需要登录账号 ");
//            //此处的使用加前缀的userId，避免腾讯IM互踢    userIdPrefix+userId
//            String userId =accountService.getGetAccountInfo().userIdPrefix + accountService.getGetAccountInfo().userId;
//            loginTencentIM(userId, accountService.getGetAccountInfo().userSig, accountService.getGetAccountInfo().userName,accountService.getGetAccountInfo().photoUrl);
//        }
//    }
    private void openMainActivity() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        //清空所有之前的activity.
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
