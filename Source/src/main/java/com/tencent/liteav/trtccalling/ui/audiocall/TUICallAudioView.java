package com.tencent.liteav.trtccalling.ui.audiocall;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.Group;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.tencent.liteav.trtccalling.R;
import com.tencent.liteav.trtccalling.model.TRTCCalling;
import com.tencent.liteav.trtccalling.model.TUICalling;
import com.tencent.liteav.trtccalling.model.impl.UserModel;
import com.tencent.liteav.trtccalling.model.impl.base.CallingInfoManager;
import com.tencent.liteav.trtccalling.model.util.EventHandler;
import com.tencent.liteav.trtccalling.model.util.ImageLoader;
import com.tencent.liteav.trtccalling.ui.audiocall.audiolayout.TRTCAudioLayout;
import com.tencent.liteav.trtccalling.ui.audiocall.audiolayout.TRTCAudioLayoutManager;
import com.tencent.liteav.trtccalling.ui.base.BaseTUICallView;
import com.tencent.liteav.trtccalling.ui.videocall.TIMCountDownTimer;
import com.tencent.trtc.TRTCCloudDef;
import com.visionvera.library.util.SpUtil;
import com.visionvera.library.util.TimeFormatUtils;
import com.visionvera.library.widget.dialog.CenterPopup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//语音
//呼叫、接听时页面UI

//修改：服务时间到时  结束通话
public class TUICallAudioView extends BaseTUICallView {
    //计时器
    private CountDownTimer countDownTimer5Min;
    private CountDownTimer countDownTimerEnd;
    //pop弹窗
    private BasePopupView time5MinPopup;
    private BasePopupView endTimePopup;

    private static final String TAG = "TUICallAudioView";

    private static final int MAX_SHOW_INVITING_USER = 2;

    private ImageView              mImageMute;
    private ImageView              mImageHangup;
    private LinearLayout           mLayoutMute;
    private LinearLayout           mLayoutHangup;
    private ImageView              mImageHandsFree;
    private LinearLayout           mLayoutHandsFree;
    private ImageView              mImageDialing;
    private LinearLayout           mLayoutDialing;
    private TRTCAudioLayoutManager mLayoutManagerTRTC;
    private Group                  mGroupInviting;
    private LinearLayout           mLayoutImgContainer;
    private TextView               mTextTime;
    private TextView               mInvitedTag;
    private TextView               mTvHangup;

    private Runnable      mTimeRunnable;
    private int           mTimeCount;
    private Handler       mTimeHandler;
    private HandlerThread mTimeHandlerThread;

    private List<UserModel>        mCallUserInfoList = new ArrayList<>(); // 呼叫方
    private Map<String, UserModel> mCallUserModelMap = new HashMap<>();
    private UserModel              mSponsorUserInfo;                      // 被叫方
    private List<UserModel>        mOtherInvitingUserInfoList;
    private boolean                mIsHandsFree      = false;
    private boolean                mIsMuteMic        = false;

    public TUICallAudioView(Context context, TUICalling.Role role, String[] userIDs, String sponsorID, String groupID, boolean isFromGroup) {
        super(context, role, userIDs, sponsorID, groupID, isFromGroup);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initData();
        initListener();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopTimeCount();
        mTimeHandlerThread.quit();
    }

    @Override
    protected void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.trtccalling_audiocall_activity_call_main, this);
        mImageMute = (ImageView) findViewById(R.id.img_mute);
        mLayoutMute = (LinearLayout) findViewById(R.id.ll_mute);
        mImageHangup = (ImageView) findViewById(R.id.img_hangup);
        //拒绝通话
        mLayoutHangup = (LinearLayout) findViewById(R.id.ll_hangup);
        mImageHandsFree = (ImageView) findViewById(R.id.img_handsfree);
        mLayoutHandsFree = (LinearLayout) findViewById(R.id.ll_handsfree);
        mImageDialing = (ImageView) findViewById(R.id.img_dialing);
        mLayoutDialing = (LinearLayout) findViewById(R.id.ll_dialing);
        mLayoutManagerTRTC = (TRTCAudioLayoutManager) findViewById(R.id.trtc_layout_manager);
        mGroupInviting = (Group) findViewById(R.id.group_inviting);
        mLayoutImgContainer = (LinearLayout) findViewById(R.id.ll_img_container);
        mTextTime = (TextView) findViewById(R.id.tv_time);
        mInvitedTag = (TextView) findViewById(R.id.tv_inviting_tag);
        mTvHangup = (TextView) findViewById(R.id.tv_hangup);

        //服务结束时间
        SpUtil spUtil = new SpUtil(mContext, "服务结束时间");
        String  end_time = spUtil.getString("end_time", "");
        //服务结束提示框
        initPopView();
        //服务结束计时器
        initTimer(end_time);

    }

    private void initData() {
        // 初始化成员变量
        mTimeHandlerThread = new HandlerThread("time-count-thread");
        mTimeHandlerThread.start();
        mTimeHandler = new Handler(mTimeHandlerThread.getLooper());

        if (mRole == TUICalling.Role.CALLED) {
            // 作为被叫
            if (!TextUtils.isEmpty(mSponsorID)) {
                mSponsorUserInfo = new UserModel();
                mSponsorUserInfo.userId = mSponsorID;
            }
            //麦克风权限判断
            PermissionUtils.permission(PermissionConstants.MICROPHONE).callback(new PermissionUtils.FullCallback() {
                @Override
                public void onGranted(List<String> permissionsGranted) {
                    //等待接听界面
                    showWaitingResponseView();
                }

                @Override
                public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                    mTRTCCalling.reject();
                    ToastUtils.showShort(R.string.trtccalling_tips_start_audio);
                    finish();
                }
            }).request();
        } else {
            // 主叫方
            if (mSelfModel != null) {
                for (String userId : mUserIDs) {
                    UserModel userModel = new UserModel();
                    userModel.userId = userId;
                    mCallUserInfoList.add(userModel);
                    mCallUserModelMap.put(userModel.userId, userModel);
                }
                showInvitingView();
                PermissionUtils.permission(PermissionConstants.MICROPHONE).callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        startInviting();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        ToastUtils.showShort(R.string.trtccalling_tips_start_audio);
                        finish();
                    }
                }).request();
            }
        }
    }

    private void initListener() {
        mLayoutMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsMuteMic = !mIsMuteMic;
                mTRTCCalling.setMicMute(mIsMuteMic);
                mImageMute.setActivated(mIsMuteMic);
                ToastUtils.showLong(mIsMuteMic ? R.string.trtccalling_toast_enable_mute : R.string.trtccalling_toast_disable_mute);
            }
        });
        mLayoutHandsFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsHandsFree = !mIsHandsFree;
                mTRTCCalling.setHandsFree(mIsHandsFree);
                mImageHandsFree.setActivated(mIsHandsFree);
                ToastUtils.showLong(mIsHandsFree ? R.string.trtccalling_toast_use_speaker : R.string.trtccalling_toast_use_handset);
            }
        });
        mImageMute.setActivated(mIsMuteMic);
        mImageHandsFree.setActivated(mIsHandsFree);
    }

    @Override
    public void onError(int code, String msg) {
        //发生了错误，报错并退出该页面
        ToastUtils.showLong(mContext.getString(R.string.trtccalling_toast_call_error_msg, code, msg));
        finish();
    }

    @Override
    public void onUserEnter(final String userId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showCallingView();
                TRTCAudioLayout layout = mLayoutManagerTRTC.findAudioCallLayout(userId);
                if (layout != null) {
                    layout.stopLoading();
                } else {
                    UserModel model = new UserModel();
                    model.userId = userId;
                    model.userAvatar = "";
                    mCallUserInfoList.add(model);
                    mCallUserModelMap.put(model.userId, model);
                    loadUserInfo(model, addUserToManager(model));
                }
            }
        });
    }

    @Override
    public void onUserLeave(final String userId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //1. 回收界面元素
                mLayoutManagerTRTC.recyclerAudioCallLayout(userId);
                //2. 删除用户model
                UserModel userInfo = mCallUserModelMap.remove(userId);
                if (userInfo != null) {
                    mCallUserInfoList.remove(userInfo);
                }
            }
        });
    }


    //拒绝通话
    @Override
    public void onReject(final String userId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mCallUserModelMap.containsKey(userId)) {
                    // 进入拒绝环节
                    //1. 回收界面元素
                    mLayoutManagerTRTC.recyclerAudioCallLayout(userId);
                    //2. 删除用户model
                    UserModel userInfo = mCallUserModelMap.remove(userId);
                    if (userInfo != null) {
                        mCallUserInfoList.remove(userInfo);
                        ToastUtils.showLong(mContext.getString(R.string.trtccalling_toast_user_reject_call, userInfo.userName));
                    }
                }
            }
        });
    }

    @Override
    public void onNoResp(final String userId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mCallUserModelMap.containsKey(userId)) {
                    // 进入无响应环节
                    //1. 回收界面元素
                    mLayoutManagerTRTC.recyclerAudioCallLayout(userId);
                    //2. 删除用户model
                    UserModel userInfo = mCallUserModelMap.remove(userId);
                    if (userInfo != null) {
                        mCallUserInfoList.remove(userInfo);
                        ToastUtils.showLong(mContext.getString(R.string.trtccalling_toast_user_not_response, userInfo.userName));
                    }
                }
            }
        });
    }

    @Override
    public void onLineBusy(String userId) {
        if (mCallUserModelMap.containsKey(userId)) {
            // 进入无响应环节
            //1. 回收界面元素
            mLayoutManagerTRTC.recyclerAudioCallLayout(userId);
            //2. 删除用户model
            UserModel userInfo = mCallUserModelMap.remove(userId);
            if (userInfo != null) {
                mCallUserInfoList.remove(userInfo);
                ToastUtils.showLong(mContext.getString(R.string.trtccalling_toast_user_busy, userInfo.userName));
            }
        }
    }

    @Override
    public void onCallingCancel() {
        if (mSponsorUserInfo != null) {
            ToastUtils.showLong(mContext.getString(R.string.trtccalling_toast_user_cancel_call, mSponsorUserInfo.userName));
        }
        finish();
    }

    @Override
    public void onCallingTimeout() {
        if (mSponsorUserInfo != null) {
            ToastUtils.showLong(mContext.getString(R.string.trtccalling_toast_user_timeout, mSponsorUserInfo.userName));
        }
        finish();
    }

    @Override
    public void onCallEnd() {
        if (mSponsorUserInfo != null) {
            ToastUtils.showLong(mContext.getString(R.string.trtccalling_toast_user_end, mSponsorUserInfo.userName));
        }
        finish();
    }

    @Override
    public void onUserVoiceVolume(Map<String, Integer> volumeMap) {
        for (Map.Entry<String, Integer> entry : volumeMap.entrySet()) {
            String userId = entry.getKey();
            TRTCAudioLayout layout = mLayoutManagerTRTC.findAudioCallLayout(userId);
            if (layout != null) {
                layout.setAudioVolume(entry.getValue());
            }
        }
    }

    @Override
    public void onNetworkQuality(TRTCCloudDef.TRTCQuality localQuality, ArrayList<TRTCCloudDef.TRTCQuality> remoteQuality) {
        updateNetworkQuality(localQuality, remoteQuality);
    }

    private void startInviting() {
        List list = new ArrayList(mCallUserInfoList.size());
        for (UserModel userInfo : mCallUserInfoList) {
            list.add(userInfo.userId);
        }
        mTRTCCalling.call(list, TRTCCalling.TYPE_AUDIO_CALL);
        mTRTCCalling.setHandsFree(mIsHandsFree);
    }

    /**
     * 等待接听界面
     */
    public void showWaitingResponseView() {
        //1. 展示对方的画面
        TRTCAudioLayout layout = addUserToManager(mSponsorUserInfo);
        loadUserInfo(mSponsorUserInfo, layout);
        //2. 展示电话对应界面
        mLayoutHangup.setVisibility(View.VISIBLE);
        mLayoutDialing.setVisibility(View.VISIBLE);
        mLayoutHandsFree.setVisibility(View.GONE);
        mLayoutMute.setVisibility(View.GONE);
        //3. 设置对应的listener
        mLayoutHangup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTRTCCalling.reject();
                finish();
            }
        });
        mLayoutDialing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.分配自己的画面
                mLayoutManagerTRTC.setMySelfUserId(mSelfModel.userId);
//                addUserToManager(mSelfModel);
                //2.接听电话
                mTRTCCalling.accept();
                mTRTCCalling.setHandsFree(mIsHandsFree);
                showCallingView();
            }
        });
        //4. 展示其他用户界面
        showOtherInvitingUserView();
        mInvitedTag.setText(mContext.getString(R.string.trtccalling_invite_audio_call));
        mTvHangup.setText(R.string.trtccalling_text_reject);
    }

    /**
     * 展示邀请列表
     */
    public void showInvitingView() {
        //1. 展示自己的界面
        mLayoutManagerTRTC.setMySelfUserId(mSelfModel.userId);
//        addUserToManager(mSelfModel);
        //2. 展示对方的画面
        for (UserModel userInfo : mCallUserInfoList) {
            TRTCAudioLayout layout = addUserToManager(userInfo);
            layout.startLoading();
            loadUserInfo(userInfo, layout);
        }
        //3. 设置底部栏
        mLayoutHangup.setVisibility(View.VISIBLE);
        mLayoutHangup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTRTCCalling.hangup();
                finish();
                mEventHandler.sendEmptyMessage(EventHandler.EVENT_TYPE_ACTIVE_HANGUP);
            }
        });
        mLayoutDialing.setVisibility(View.GONE);
        mLayoutHandsFree.setVisibility(View.VISIBLE);
        mLayoutMute.setVisibility(View.VISIBLE);
        //4. 隐藏中间他们也在界面
//        hideOtherInvitingUserView();
        mGroupInviting.setVisibility(View.VISIBLE);
        mInvitedTag.setText(mContext.getString(R.string.trtccalling_waiting_be_accepted));
        mTvHangup.setText(R.string.trtccalling_text_hangup);
    }

    /**
     * 展示通话中的界面
     */
    public void showCallingView() {
        mLayoutHangup.setVisibility(View.VISIBLE);
        mLayoutDialing.setVisibility(View.GONE);
        mLayoutHandsFree.setVisibility(View.VISIBLE);
        mLayoutMute.setVisibility(View.VISIBLE);
        mTvHangup.setText(R.string.trtccalling_text_hangup);
        mLayoutHangup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTRTCCalling.hangup();
                finish();
            }
        });
        showTimeCount();
        hideOtherInvitingUserView();
    }

    private void showTimeCount() {
        if (mTimeRunnable != null) {
            return;
        }
        mTimeCount = 0;
        mTextTime.setText(getShowTime(mTimeCount));
        if (mTimeRunnable == null) {
            mTimeRunnable = new Runnable() {
                @Override
                public void run() {
                    mTimeCount++;
                    if (mTextTime != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!isDestroyed()) {
                                    mTextTime.setText(getShowTime(mTimeCount));
                                }
                            }
                        });
                    }
                    mTimeHandler.postDelayed(mTimeRunnable, 1000);
                }
            };
        }
        mTimeHandler.postDelayed(mTimeRunnable, 1000);
    }

    private void stopTimeCount() {
        mTimeHandler.removeCallbacks(mTimeRunnable);
        mTimeRunnable = null;
    }

    private String getShowTime(int count) {
        return mContext.getString(R.string.trtccalling_called_time_format, count / 60, count % 60);
    }

    private void showOtherInvitingUserView() {
        if (CollectionUtils.isEmpty(mOtherInvitingUserInfoList)) {
            return;
        }
        mGroupInviting.setVisibility(View.VISIBLE);
        int squareWidth = getResources().getDimensionPixelOffset(R.dimen.trtccalling_small_image_size);
        int leftMargin = getResources().getDimensionPixelOffset(R.dimen.trtccalling_small_image_left_margin);
        for (int index = 0; index < mOtherInvitingUserInfoList.size() && index < MAX_SHOW_INVITING_USER; index++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(squareWidth, squareWidth);
            if (index != 0) {
                layoutParams.leftMargin = leftMargin;
            }
            imageView.setLayoutParams(layoutParams);
            ImageLoader.loadImage(mContext, imageView, mSponsorUserInfo.userAvatar,
                    R.drawable.trtccalling_wait_background);
            mLayoutImgContainer.addView(imageView);
        }
    }

    private void hideOtherInvitingUserView() {
        mGroupInviting.setVisibility(View.GONE);
    }

    private TRTCAudioLayout addUserToManager(UserModel userInfo) {
        TRTCAudioLayout layout = mLayoutManagerTRTC.allocAudioCallLayout(userInfo.userId);
        if (layout == null) {
            return null;
        }
        layout.setUserName(userInfo.userName);
        ImageLoader.loadImage(mContext, layout.getImageView(), userInfo.userAvatar, R.drawable.trtccalling_wait_background);
        return layout;
    }

    private void loadUserInfo(final UserModel userModel, final TRTCAudioLayout layout) {
        if (null == userModel || null == layout) {
            Log.e(TAG, "loadUserInfo error: null == userModel || null == layout");
            return;
        }
        CallingInfoManager.getInstance().getUserInfoByUserId(userModel.userId, new CallingInfoManager.UserCallback() {
            @Override
            public void onSuccess(UserModel model) {
                userModel.userName = model.userName;
                userModel.userAvatar = model.userAvatar;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isDestroyed()) {
                            return;
                        }
                        layout.setUserName(userModel.userName);
                        ImageLoader.loadImage(mContext, layout.getImageView(), userModel.userAvatar, R.drawable.trtccalling_groupcall_wait_background);
                    }
                });
            }

            @Override
            public void onFailed(int code, String msg) {
                ToastUtils.showLong(mContext.getString(R.string.trtccalling_toast_search_fail, msg));
            }
        });
    }


    //服务结束提示框
    private void initPopView() {
        time5MinPopup = new XPopup.Builder(getContext())
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(getContext(),
                        CenterPopup.CenterPopupType.oneButton,
                        "提示",
                        "咨询还有5分钟就要结束了",
                        "",
                        "确定",
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    centerPopup.dismiss();

                }));
        endTimePopup = new XPopup.Builder(getContext())
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(getContext(),
                        CenterPopup.CenterPopupType.oneButton,
                        "提示",
                        "咨询已经结束",
                        "",
                        "确定",
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    centerPopup.dismiss();
                    ToastUtils.showShort("服务时间已结束");

                    //服务时间已结束   发起方挂断通话
                    mTRTCCalling.hangup();
                }));
    }

    //服务结束计时器
    private void initTimer(String end_time) {
        if (!TextUtils.isEmpty(end_time)) {

            long endTime = TimeFormatUtils.strToDateLong(end_time).getTime();
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



                        //服务时间已结束   发起方挂断通话
//                        mTRTCCalling.hangup();
//                        ToastUtils.showShort("服务时间已结束");
                    }
                };
                countDownTimerEnd.start();
            } else {
//                ToastUtils.showShort("服务时间已结束");
            }
        }
    }

}
