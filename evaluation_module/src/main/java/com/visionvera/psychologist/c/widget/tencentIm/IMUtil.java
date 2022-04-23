package com.visionvera.psychologist.c.widget.tencentIm;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.imsdk.v2.V2TIMSDKListener;
import com.tencent.liteav.basic.UserModel;
import com.tencent.liteav.basic.UserModelManager;
import com.tencent.liteav.trtccalling.model.TRTCCalling;
import com.tencent.liteav.trtccalling.model.TRTCCallingCallback;
import com.tencent.liteav.trtccalling.model.TUICalling;
import com.tencent.liteav.trtccalling.model.impl.TRTCCallingImpl;
import com.tencent.liteav.trtccalling.model.impl.TUICallingManager;
import com.tencent.liteav.trtccalling.model.impl.base.CallingInfoManager;
import com.tencent.liteav.trtccalling.model.util.TUICallingConstants;
import com.tencent.qcloud.tuicore.TUILogin;
import com.tencent.qcloud.tuikit.tuicontact.util.ContactUtils;
import com.visionvera.library.util.SpUtil;
import com.visionvera.psychologist.c.MyApplication;

import rxhttp.wrapper.utils.LogUtil;


/**
 * @Classname:IMUtility
 * @author:haohuizhao
 * @Date:2021/12/16 18:00
 * @Version：v1.0
 * @describe： 腾讯IM工具类
 * <p>
 * 腾讯IM   文字聊天
 * 腾讯文档地址：https://cloud.tencent.com/document/product/269/36838
 * 腾讯Demo项目TIMSDK-master
 * <p>
 * 腾讯  视频语音聊天
 * 腾讯文档地址：https://cloud.tencent.com/document/product/647/42045
 * 腾讯Demo项目TUICalling-main
 * 音视频被邀请人接听的监听在 TRTCCallingImpl类中
 */


public class IMUtil {

    private String TAG = "IMUtil";
    private boolean mIsKickedOffline = false;
    private boolean mIsUserSigExpired = false;


    private static volatile IMUtil imUtil;

    private IMUtil() {
    }

    /**
     * 单例模式 获取实例方法
     *
     * @return
     */
    public static IMUtil getInstance() {
        if (imUtil == null) {
            synchronized (LogUtil.class) {
                if (imUtil == null) {
                    imUtil = new IMUtil();
                }
            }
        }
        return imUtil;
    }


    //腾讯IM初始化
    //上下文、腾讯云 SDKAppId
    public void initIM(Context context,int SDKAppId,InitIMCallBack  callBack) {
        V2TIMSDKConfig config = new V2TIMSDKConfig();
        config.setLogLevel(V2TIMSDKConfig.V2TIM_LOG_DEBUG);
        //腾讯云 SDKAppId
        TUILogin.init(context,SDKAppId , null, new V2TIMSDKListener() {

            @Override
            public void onKickedOffline() {
                mIsKickedOffline = true;
                checkUserStatus();
                //同时登录后的强制下线弹窗
//                ToastUtils.showShort("您的帐号在其它地方登录，您已下线");

                callBack.onKickedOffline();
            }

            @Override
            public void onUserSigExpired() {
                mIsUserSigExpired = true;
                checkUserStatus();

                callBack.onUserSigExpired();
            }
        });
    }





    //检查登录状态
    private boolean checkUserStatus() {
        if (mIsKickedOffline) {
            ToastUtils.showShort("你被踢下线了,请重新登录！");
        }
        if (mIsUserSigExpired) {
            ToastUtils.showShort("您的用户签名信息已过期！");
        }
        return !mIsKickedOffline && !mIsUserSigExpired;
    }


    //腾讯IM登录
    //id   签名   名字  头像地址
    public void loginIM(String userId, String userSig, String userName, String photoUrl, LoginCallBack callBack) {
        final UserModel userModel = new UserModel();
        userModel.userId = userId;  //id
        userModel.userSig = userSig;//sig     正式签名 从自己服务器接口获取的
        final UserModelManager manager = UserModelManager.getInstance();
        manager.setUserModel(userModel);
        TUILogin.login(userModel.userId, userModel.userSig, new V2TIMCallback() {
            @Override
            public void onError(int code, String msg) {
                Log.i(TAG, "腾讯im登录onError: " + "code: " + code + " msg:" + msg);
                callBack.loginUserIdFail(code,  msg);
            }

            @Override
            public void onSuccess() {
                Log.i(TAG, "腾讯im登录成功" + userId);
                callBack.loginSuccess(1, "腾讯im登录成功");

                //设置腾讯Im存储 头像、昵称
                setUserInfo(userName, photoUrl);
            }
        });
    }

    //设置腾讯IM存储 头像、昵称
    private void setUserInfo(String userName, String photoUrl) {
        TRTCCallingImpl trtcCalling = new TRTCCallingImpl(MyApplication.getInstance());
        trtcCalling.setSelfProfile(userName, photoUrl, new TRTCCallingCallback.ActionCallback() {
            @Override
            public void onCallback(int code, String msg) {
                Log.i(TAG, "腾讯_修改个人信息" + msg);
            }
        });
    }


    //搜索用户
    //从im后台获取真正的用户信息
    public void searchUserId(String doctorId, SearchUserIdCallBack callBack) {
        if (TextUtils.isEmpty(doctorId)) {
            return;
        }
        CallingInfoManager.getInstance().getUserInfoByUserId(doctorId, new CallingInfoManager.UserCallback() {
            @Override
            public void onSuccess(com.tencent.liteav.trtccalling.model.impl.UserModel model) {
                callBack.searchUserIdSuccess(1, "搜索用户成功");
            }

            @Override
            public void onFailed(int code, String msg) {
                callBack.searchUserIdFail(0, "搜索用户失败");
            }
        });
    }




    //文字聊天
    //userId 对方id
    public void EnterChat(String userId) {
        ContactUtils.startChatActivity("", "", userId, 1, userId + "name", "", "2050-12-30 14:35:00", "0", "0");

    }

    //语音、视频呼叫
    //userId 被呼叫用户id
    //type  	TUICallingType 通话类型：音频/视频
    //role 	TUICallingRole 用户角色类型：主叫/被叫
    //viewController 	UIViewController 通话视图 ViewController
    public void startCallSomeone(String userId, int type) {
        if (type == TRTCCalling.TYPE_VIDEO_CALL) {
            ToastUtils.showShort("视频呼叫" + userId);
            //此处一人呼叫多人
//            String[] userIDs = {"501","401"};
            String[] userIDs = {userId};//呼叫 医生id
            Bundle bundle = new Bundle();
            bundle.putString(TUICallingConstants.PARAM_NAME_TYPE, TUICallingConstants.TYPE_VIDEO);
            bundle.putStringArray(TUICallingConstants.PARAM_NAME_USERIDS, userIDs);
            bundle.putString(TUICallingConstants.PARAM_NAME_GROUPID, "");
//            TUICore.callService(TUIConstants.Service.TUI_CALLING, TUICallingConstants.METHOD_NAME_CALL, bundle);
            // 1. 初始化组件
            TUICallingManager tuiCallingManager = TUICallingManager.sharedInstance();

            // 2. 注册监听器
            tuiCallingManager.setCallingListener(new TUICalling.TUICallingListener() {
                @Override
                public boolean shouldShowOnCallView() {
                    ToastUtils.showLong("被叫时请求拉起接听页面");
                    return false;
                }

                @Override
                public void onCallStart(String[] userIDs, TUICalling.Type type, TUICalling.Role role, View tuiCallingView) {
                    ToastUtils.showLong("开始呼叫:" + userIDs[0]);
                }

                @Override
                public void onCallEnd(String[] userIDs, TUICalling.Type type, TUICalling.Role role, long totalTime) {
                    ToastUtils.showLong("挂断结束通话:" + userIDs[0]);
                }

                @Override
                public void onCallEvent(TUICalling.Event event, TUICalling.Type type, TUICalling.Role role, String message) {
                    ToastUtils.showLong("通话接通成功:" + message);
                }
            });
            SpUtil spUtil = new SpUtil(MyApplication.getInstance(), "服务结束时间");
            spUtil.putString("end_time", "2050-12-02 15:30:00");
            // 3.拨打电话   userIDs
            tuiCallingManager.call(userIDs, TUICalling.Type.VIDEO);
            //结束通话
//            tuiCallingManager.onCallEnd();
        } else if (type == TRTCCalling.TYPE_AUDIO_CALL) {
            ToastUtils.showShort("语音呼叫", userId);
            String[] userIDs = {userId};
            Bundle bundle = new Bundle();
            bundle.putString(TUICallingConstants.PARAM_NAME_TYPE, TUICallingConstants.TYPE_AUDIO);
            bundle.putStringArray(TUICallingConstants.PARAM_NAME_USERIDS, userIDs);
            bundle.putString(TUICallingConstants.PARAM_NAME_GROUPID, "");
//            TUICore.callService(TUIConstants.Service.TUI_CALLING, TUICallingConstants.METHOD_NAME_CALL, bundle);
            // 1. 初始化组件
            TUICallingManager tuiCallingManager = TUICallingManager.sharedInstance();
            // 2. 注册监听器
            tuiCallingManager.setCallingListener(new TUICalling.TUICallingListener() {
                @Override
                public boolean shouldShowOnCallView() {
                    return false;
                }

                @Override
                public void onCallStart(String[] userIDs, TUICalling.Type type, TUICalling.Role role, View tuiCallingView) {

                }

                @Override
                public void onCallEnd(String[] userIDs, TUICalling.Type type, TUICalling.Role role, long totalTime) {

                }

                @Override
                public void onCallEvent(TUICalling.Event event, TUICalling.Type type, TUICalling.Role role, String message) {
                    ToastUtils.showLong("通话接通成功:" + message);
                    Log.i("TAG", "通话接通成功");
                }
            });
            SpUtil spUtil = new SpUtil(MyApplication.getInstance(), "服务结束时间");
            spUtil.putString("end_time", "2050-12-02 15:27:00");
            // 3.拨打电话
            tuiCallingManager.call(userIDs, TUICalling.Type.AUDIO);
        }
    }


    //初始化回调
    public interface InitIMCallBack {

        //当前用户被踢下线回调  此时可以 UI 提示用户“您已经在其他端登录了当前帐号，是否重新登录？”
        void onKickedOffline();

        //登录票据已经过期回调  请使用新签发的 UserSig 进行登录。
        void onUserSigExpired();
    }
    //登录回调
    public interface LoginCallBack {

        //搜索用户回调成功回调
        void loginSuccess(int code, String msg);

        //搜索用户回调失败回调
        void loginUserIdFail(int code, String msg);
    }

    //搜索用户回调
    public interface SearchUserIdCallBack {

        //搜索用户回调成功回调
        void searchUserIdSuccess(int i, String s);

        //搜索用户回调失败回调
        void searchUserIdFail(int i, String s);
    }

}
