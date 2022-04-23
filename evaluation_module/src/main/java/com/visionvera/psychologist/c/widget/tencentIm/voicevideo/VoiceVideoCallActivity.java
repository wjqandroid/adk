package com.visionvera.psychologist.c.widget.tencentIm.voicevideo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.tencent.liteav.basic.UserModel;
import com.tencent.liteav.trtccalling.model.TRTCCalling;
import com.tencent.liteav.trtccalling.model.TUICalling;
import com.tencent.liteav.trtccalling.model.impl.TUICallingManager;
import com.tencent.liteav.trtccalling.model.impl.base.CallingInfoManager;
import com.tencent.liteav.trtccalling.model.util.TUICallingConstants;
import com.visionvera.library.util.SpUtil;
import com.visionvera.library.util.TimeFormatUtils;
import com.visionvera.library.widget.dialog.CenterPopup;
import com.visionvera.psychologist.c.R;
//import com.visionvera.psychologist.c.widget.tencentIm.textchat.ChatActivity;
import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultDetailActivity;
import com.visionvera.psychologist.c.widget.tencentIm.utils.TIMCountDownTimer;

import java.io.Serializable;
import java.util.Calendar;


/**
 * @Classname:VideoChatActivity
 * @author:haohuizhao
 * @Date:2021/11/26 15:37
 * @Version：v1.0
 * @describe： 腾讯  视频语音聊天
 * <p>
 * 腾讯文档地址：https://cloud.tencent.com/document/product/647/42045
 * 腾讯Demo项目TUICalling-main
 * 腾讯音视频呼叫测试页面
 *
 * 音视频被邀请人接听的监听在 TRTCCallingImpl类中
 *
 */

public class VoiceVideoCallActivity extends AppCompatActivity {

    private VideoActivityIntentBean intentBean;
    private CountDownTimer countDownTimer5Min;
    private CountDownTimer countDownTimerEnd;
    private BasePopupView time5MinPopup;
    private BasePopupView endTimePopup;

    private UserModel mSearchModel;  //表示当前搜索的usermodel
    private int mType; //表示当前是 videocall/audiocall

    private boolean mIsKickedOffline = false;
    private boolean mIsUserSigExpired = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_video_call);


        getIntentBean();
        //1初始化IM
//        initData();
        //2.登录IM
//        login(intentBean.userId, intentBean.userName);
        //3搜索医生是否在线
        //4.在线则呼叫
        searchContactsByUserId("mhsptrunkdev12608");


        //初始化pop
//        initView();
        //计时
//        initData();
    }

    private void getIntentBean() {
        intentBean = (VideoActivityIntentBean) getIntent().getSerializableExtra("intentBean");
        if (intentBean.callType.equals("0")) {
            //語音
            mType = TRTCCalling.TYPE_AUDIO_CALL;
        } else if (intentBean.callType.equals("1")) {
            //视频
            mType = TRTCCalling.TYPE_VIDEO_CALL;
        }
    }


    //3.搜索要呼叫的用户
    private void searchContactsByUserId(String doctorId) {
        if (TextUtils.isEmpty(doctorId)) {
            return;
        }
        CallingInfoManager.getInstance().getUserInfoByUserId(doctorId, new CallingInfoManager.UserCallback() {
            @Override
            public void onSuccess(com.tencent.liteav.trtccalling.model.impl.UserModel model) {
                mSearchModel = new UserModel();
                mSearchModel.userId = model.userId;
                mSearchModel.userName = model.userName;
                mSearchModel.userAvatar =model.userAvatar;

                ToastUtils.showLong("查询医生成功");

                //呼叫
                startCallSomeone();
            }

            @Override
            public void onFailed(int code, String msg) {
                ToastUtils.showLong("搜索失败:" + msg);
            }
        });
    }


    //    userIDs 	NSArray 	通话用户 ID 列表
//    type 	TUICallingType 	通话类型：音频/视频
//    role 	TUICallingRole 	用户角色类型：主叫/被叫
//    viewController 	UIViewController 	通话视图 ViewController
    //4.开始呼叫某人
    private void startCallSomeone() {
        if (mType == TRTCCalling.TYPE_VIDEO_CALL) {
            ToastUtils.showShort("视频呼叫" + mSearchModel.userId);
            //此处一人呼叫多人
//            String[] userIDs = {"501","401"};
            String[] userIDs = {mSearchModel.userId};//呼叫 医生id
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
            SpUtil spUtil = new SpUtil(VoiceVideoCallActivity.this, "服务结束时间");
            spUtil.putString("end_time", "2021-12-02 15:30:00");
            // 3.拨打电话   userIDs
            tuiCallingManager.call(userIDs, TUICalling.Type.VIDEO);
            //结束通话
//            tuiCallingManager.onCallEnd();
        } else if (mType == TRTCCalling.TYPE_AUDIO_CALL) {
            ToastUtils.showShort("语音呼叫", mSearchModel.userName);
            String[] userIDs = {mSearchModel.userId};
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
                    Log.i("TAG","通话接通成功");
                }
            });
            SpUtil spUtil = new SpUtil(VoiceVideoCallActivity.this, "服务结束时间");
            spUtil.putString("end_time", "2021-12-02 15:27:00");
            // 3.拨打电话
            tuiCallingManager.call(userIDs, TUICalling.Type.AUDIO);
        }
    }


    public static class VideoActivityIntentBean implements Serializable {

        //医生userId
        String doctorId;
        //医生姓名
        String doctorName;
        //用户id
        String userId;
        //用户的名字
        String userName;
        //结束时间
        String endTime;
        //呼叫类型 0语音 1视频
        String callType;

        public VideoActivityIntentBean(String doctorId, String doctorName, String userId, String userName, String endTime, String callType) {
            this.doctorId = doctorId;
            this.doctorName = doctorName;
            this.userId = userId;
            this.userName = userName;
            this.endTime = endTime;
            this.callType = callType;
        }

    }


    //   服务结束提示框
    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        time5MinPopup = new XPopup.Builder(VoiceVideoCallActivity.this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(VoiceVideoCallActivity.this,
                        CenterPopup.CenterPopupType.oneButton,
                        "提示",
                        "咨询还有5分钟就要结束了",
                        "",
                        "确定",
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    centerPopup.dismiss();

                }));
        endTimePopup = new XPopup.Builder(VoiceVideoCallActivity.this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(VoiceVideoCallActivity.this,
                        CenterPopup.CenterPopupType.oneButton,
                        "提示",
                        "咨询已经结束",
                        "",
                        "确定",
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    centerPopup.dismiss();
                    ToastUtils.showShort("服务时间已结束");
//                    InputLayout inputLayout = mChatLayout.getInputLayout();
//                    inputLayout.setVisibility(View.GONE);
                }));
    }

    private void initData() {
        if (!TextUtils.isEmpty(intentBean.endTime)) {

            long endTime = TimeFormatUtils.strToDateLong(intentBean.endTime).getTime();
//            long endTime=TimeFormatUtils.strToDateLong("2020-03-17 14:35:00").getTime();
            long currentTime = Calendar.getInstance().getTime().getTime();

            if (currentTime < endTime) {
                long time5Min = endTime - currentTime - 1000 * 5 * 60;
//                long time5Min=endTime-currentTime-1000*10;
                long endTimeMin = endTime - currentTime;

                countDownTimer5Min = new TIMCountDownTimer(time5Min) {

                    @Override
                    public void onFinish() {
                        super.onFinish();

                        time5MinPopup.show();
                    }
                };
                countDownTimer5Min.start();


                countDownTimerEnd = new TIMCountDownTimer(endTimeMin) {
                    @Override
                    public void onFinish() {
                        super.onFinish();

                        if (time5MinPopup != null && time5MinPopup.isShow()) {
                            time5MinPopup.dismiss();
                        }
                        endTimePopup.show();
                    }
                };
                countDownTimerEnd.start();
            } else {
//                ToastUtils.showShort("服务时间已结束");
            }
        }

    }


    //腾讯部分
    //1.初始化IM
//    private void initData() {
//        final UserModel userModel = UserModelManager.getInstance().getUserModel();
//        V2TIMSDKConfig config = new V2TIMSDKConfig();
//        config.setLogLevel(V2TIMSDKConfig.V2TIM_LOG_DEBUG);
//        TUILogin.init(this, GenerateTestUserSig.SDKAPPID, null, new V2TIMSDKListener() {
//
//            @Override
//            public void onKickedOffline() {
//                mIsKickedOffline = true;
//                checkUserStatus();
//            }
//
//            @Override
//            public void onUserSigExpired() {
//                mIsUserSigExpired = true;
//                checkUserStatus();
//            }
//        });
//        TUILogin.login(userModel.userId, userModel.userSig, new V2TIMCallback() {
//            @Override
//            public void onError(int code, String msg) {
//                Log.d("TAG", "code: " + code + " msg:" + msg);
//            }
//
//            @Override
//            public void onSuccess() {
//                Log.d("TAG", "onSuccess");
//            }
//        });
//    }
//
//    private boolean checkUserStatus() {
//        if (mIsKickedOffline) {
//            ToastUtils.showShort(getString(com.tencent.liteav.trtccalling.R.string.trtccalling_user_kicked_offline));
//        }
//        if (mIsUserSigExpired) {
//            ToastUtils.showShort(getString(com.tencent.liteav.trtccalling.R.string.trtccalling_user_sig_expired));
//        }
//        return !mIsKickedOffline && !mIsUserSigExpired;
//    }


    //2.登录IM
//    private void login(String userId, String userName) {
//        final UserModel userModel = new UserModel();
//
//        userModel.userId = userId;
//        userModel.userName = userName;
//        userModel.phone = userId;
//        int index = new Random().nextInt(AvatarConstant.USER_AVATAR_ARRAY.length);
//        String coverUrl = AvatarConstant.USER_AVATAR_ARRAY[index];
//        userModel.userAvatar = coverUrl;
//        userModel.userSig = GenerateTestUserSig.genTestUserSig(userId);
//        final UserModelManager manager = UserModelManager.getInstance();
//        manager.setUserModel(userModel);
//        ToastUtils.showShort("注册成功" + userId);
//    }


}