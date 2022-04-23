package com.tencent.liteav.trtccalling.model.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.liteav.trtccalling.model.TRTCCalling;
import com.tencent.liteav.trtccalling.model.TRTCCallingDelegate;
import com.tencent.liteav.trtccalling.model.TUICalling;
import com.tencent.liteav.trtccalling.model.util.EventHandler;
import com.tencent.liteav.trtccalling.model.util.TUICallingConstants;
import com.tencent.liteav.trtccalling.ui.audiocall.TRTCAudioCallActivity;
import com.tencent.liteav.trtccalling.ui.audiocall.TUICallAudioView;
import com.tencent.liteav.trtccalling.ui.audiocall.TUIGroupCallAudioView;
import com.tencent.liteav.trtccalling.ui.base.BaseTUICallView;
import com.tencent.liteav.trtccalling.ui.videocall.TRTCVideoCallActivity;
import com.tencent.liteav.trtccalling.ui.videocall.TUICallVideoView;
import com.tencent.liteav.trtccalling.ui.videocall.TUIGroupCallVideoView;
import com.tencent.trtc.TRTCCloudDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * TUICalling模块对外接口
 */
public final class TUICallingManager implements TUICalling, TRTCCallingDelegate {

    private static final String TAG = "TUICallingManager";

    private static TUICallingManager INSTANCE = new TUICallingManager();

    private Context mContext;
    private TUICallingListener mTUICallingListener;
    protected final Handler mMainHandler = new Handler(Looper.getMainLooper());

    private boolean mEnableFloatWindow = false;  // 是否开启悬浮窗
    private boolean mEnableCustomViewRoute = false;  // 是否开启自定义视图
    private String[] mUserIDs;
    private Type mType;
    private Role mRole;
    private long mStartTime;

    private BaseTUICallView mCallView;

    private CallingManagerListener mCallingManagerListener;

    public static final TUICallingManager sharedInstance() {
        return INSTANCE;
    }

    private TUICallingManager() {

    }

    void init(Context context) {
        mContext = context;
        TRTCCalling.sharedInstance(mContext).addDelegate(this);
        EventHandler.sharedInstance().addHandler(new CallingHandler());
    }

    void setCallingManagerListener(CallingManagerListener listener) {
        mCallingManagerListener = listener;
    }

    //呼叫时 携带被呼人数据
    @Override
    public void call(final String[] userIDs, final Type type) {
        internalCall(userIDs, "", "", false, type, Role.CALL);
    }

    //作为被邀请方接听来电
    //userIDs 	String[] 	通话用户 ID 列表
    //type 	TUICalling.Type 	通话类型：音频/视频
    @Override
    public void receiveAPNSCalled(final String[] userIDs, final Type type) {
        internalCall(userIDs, "", "", false, type, Role.CALLED);
    }

    void internalCall(final String[] userIDs, String groupID, final Type type, final Role role) {
        internalCall(userIDs, "", groupID, !TextUtils.isEmpty(groupID), type, role);
    }

    void internalCall(final String[] userIDs, final String sponsorID, final String groupID, final boolean isFromGroup, final Type type, final Role role) {
        if (null == type || null == role) {
            Log.e(TAG, "param is error!!!");
            return;
        }
        Log.d(TAG, String.format("internalCall, userIDs=%s, sponsorID=%s, groupID=%s, type=%s, role=%s", Arrays.toString(userIDs), sponsorID, groupID, type, role));
        mStartTime = SystemClock.uptimeMillis();
        mUserIDs = null == userIDs ? new String[0] : userIDs;
        mType = type;
        mRole = role;
        if (mEnableCustomViewRoute) {
            if (Type.AUDIO == type) {
                if (isGroupCall(groupID, userIDs, role, isFromGroup)) {
                    //群语音
                    mCallView = new TUIGroupCallAudioView(mContext, role, userIDs, sponsorID, groupID, isFromGroup);
                } else {
                    //双人语音
                    mCallView = new TUICallAudioView(mContext, role, userIDs, sponsorID, groupID, isFromGroup);
                }
            } else if (Type.VIDEO == type) {
                if (isGroupCall(groupID, userIDs, role, isFromGroup)) {
                    //群视频
                    mCallView = new TUIGroupCallVideoView(mContext, role, userIDs, sponsorID, groupID, isFromGroup);
                } else {
                    //双人视频
                    mCallView = new TUICallVideoView(mContext, role, userIDs, sponsorID, groupID, isFromGroup);
                }
            }
            if (null != mTUICallingListener) {
                //onCallStart 	呼叫开始回调。主叫、被叫均会触发
                mTUICallingListener.onCallStart(userIDs, type, role, mCallView);
            }
        } else {
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Intent intent = Type.AUDIO == type ? new Intent(mContext, TRTCAudioCallActivity.class) : new Intent(mContext, TRTCVideoCallActivity.class);
                    intent.putExtra(TUICallingConstants.PARAM_NAME_ROLE, role);
                    if (Role.CALLED == role) {
                        intent.putExtra(TUICallingConstants.PARAM_NAME_SPONSORID, sponsorID);
                        intent.putExtra(TUICallingConstants.PARAM_NAME_ISFROMGROUP, isFromGroup);
                    }
                    intent.putExtra(TUICallingConstants.PARAM_NAME_USERIDS, userIDs);
                    intent.putExtra(TUICallingConstants.PARAM_NAME_GROUPID, groupID);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
            if (null != mTUICallingListener) {
                //onCallStart 	呼叫开始回调。主叫、被叫均会触发
                mTUICallingListener.onCallStart(userIDs, type, role, null);
            }
        }
    }

    void receiveOfflineCalled(String sender, String content) {
        TRTCCalling.sharedInstance(mContext).receiveNewInvitation(sender, content);
    }

    private boolean isGroupCall(String groupID, String[] userIDs, TUICalling.Role role, boolean isFromGroup) {
        if (!TextUtils.isEmpty(groupID)) {
            return true;
        }
        if (TUICalling.Role.CALL == role) {
            return userIDs.length >= 2;
        } else {
            return userIDs.length >= 1 || isFromGroup;
        }
    }

    @Override
    public void setCallingListener(TUICallingListener listener) {
        mTUICallingListener = listener;
    }

    //设置铃声（建议在30s以内）
    @Override
    public void setCallingBell(String filePath) {
        TRTCCalling.sharedInstance(mContext).setCallingBell(filePath);
    }

    //开启静音模式
    @Override
    public void enableMuteMode(boolean enable) {
        TRTCCalling.sharedInstance(mContext).enableMuteMode(enable);
    }

    @Override
    public void enableFloatWindow(boolean enable) {
        mEnableFloatWindow = enable;
    }

    //开启自定义视图
    @Override
    public void enableCustomViewRoute(boolean enable) {
        mEnableCustomViewRoute = enable;
    }

    private class CallingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            final int type = msg.what;
            if (EventHandler.EVENT_TYPE_ACTIVE_HANGUP == type) {
                if (null != mTUICallingListener) {
                    //onCallEnd 	结束通话回调。主叫、被叫均会触发
                    mTUICallingListener.onCallEnd(mUserIDs, mType, mRole, 0);
                }
                if (mCallingManagerListener != null) {
                    mCallingManagerListener.onEvent(TUICallingConstants.EVENT_ACTIVE_HANGUP, new Bundle());
                }
            }
        }
    }

    @Override
    public void onError(int code, String msg) {

    }

    //邀请
    @Override
    public void onInvited(String sponsor, List<String> userIdList, boolean isFromGroup, final int callType) {
        Log.d(TAG, String.format("onInvited enter, sponsor=%s, userIdList=%s, isFromGroup=%s, callType=%s", sponsor, Arrays.toString(userIdList.toArray()), isFromGroup, callType));
        if (TRTCCalling.TYPE_VIDEO_CALL != callType && TRTCCalling.TYPE_AUDIO_CALL != callType) {
            return;
        }
        final Type type = TRTCCalling.TYPE_AUDIO_CALL == callType ? Type.AUDIO : Type.VIDEO;
        String[] userIDs = userIdList.toArray(new String[userIdList.size()]);
        if (null != mTUICallingListener && !mTUICallingListener.shouldShowOnCallView()) {
            // 直接挂断
            TRTCCalling.sharedInstance(mContext).hangup();
            //onCallEnd 	结束通话回调。主叫、被叫均会触发
            mTUICallingListener.onCallEnd(userIDs, type, Role.CALLED, 0);
        } else {
            internalCall(userIDs, sponsor, "", isFromGroup, type, Role.CALLED);
        }
    }

    //on群组call受邀者列表更新输入
    @Override
    public void onGroupCallInviteeListUpdate(List<String> userIdList) {
        Log.d(TAG, "onGroupCallInviteeListUpdate enter");

    }

    //通用回调
    //用户进入通话回调
    @Override
    public void onUserEnter(String userId) {
        Log.d(TAG, "onUserEnter enter");
        //onCallEvent 	 呼叫接通的回调 主叫、被叫均会触发
        if(mTUICallingListener!=null){
            mTUICallingListener.onCallEvent(Event.CALL_SUCCEED, Type.VIDEO, Role.CALL, "通话接通了");
        }

    }

    //通用回调
    //用户离开通话回调
    @Override
    public void onUserLeave(String userId) {
        Log.d(TAG, "onUserLeave enter");
    }

    //邀请方回调
    //拒绝通话回调
    @Override
    public void onReject(String userId) {
        Log.d(TAG, "onReject enter");
    }

    //邀请方回调
    //对方无回应回调
    @Override
    public void onNoResp(String userId) {
        Log.d(TAG, "onNoResp enter");
    }

    //邀请方回调
    //通话忙线回调
    @Override
    public void onLineBusy(String userId) {
        Log.d(TAG, "onLineBusy enter");
    }

    //被邀请方回调
    //当前通话被取消回调
    @Override
    public void onCallingCancel() {
        Log.d(TAG, "onCallingCancel enter");
    }

    //被邀请方回调
    //当前通话超时回调
    @Override
    public void onCallingTimeout() {
        Log.d(TAG, "onCallingTimeout enter");
    }

    //通用回调
    //通话结束回调
    @Override
    public void onCallEnd() {
        Log.d(TAG, "onCallEnd enter");
        if (null != mTUICallingListener) {
            //onCallEnd 	结束通话回调。主叫、被叫均会触发
            mTUICallingListener.onCallEnd(mUserIDs, mType, mRole, SystemClock.uptimeMillis() - mStartTime);
        }
    }

    //当房间中有其他用户在上行视频数据时，您会收到 onUserVideoAvailable() 事件通知
    @Override
    public void onUserVideoAvailable(String userId, boolean isVideoAvailable) {
        Log.d(TAG, "onUserVideoAvailable enter");
    }

    //当房间中有其他用户在上行音频数据时，您会收到 onUserAudioAvailable() 事件通知，SDK 会自动播放远端用户的声音
    @Override
    public void onUserAudioAvailable(String userId, boolean isVideoAvailable) {
        Log.d(TAG, "onUserAudioAvailable enter");
    }

    //音量大小的反馈回调
    @Override
    public void onUserVoiceVolume(Map<String, Integer> volumeMap) {
//        Log.d(TAG, "onUserVoiceVolume enter");
    }

    //网络质量的实时统计回调
    @Override
    public void onNetworkQuality(TRTCCloudDef.TRTCQuality localQuality, ArrayList<TRTCCloudDef.TRTCQuality> remoteQuality) {
        Log.d(TAG, "onNetworkQuality enter");
    }

    @Override
    public void onSwitchToAudio(boolean success, String message) {
        Log.d(TAG, "onSwitchToAudio enter");
    }

    public interface CallingManagerListener {
        void onEvent(String key, Bundle bundle);
    }


}
