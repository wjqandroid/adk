package com.visionvera.psychologist.c.widget.tencentIm.textchat;


import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
//import com.tencent.imsdk.TIMConversationType;
//import com.tencent.imsdk.TIMFriendshipManager;
//import com.tencent.imsdk.TIMUserProfile;
//import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.v2.V2TIMFriendAddApplication;
import com.tencent.imsdk.v2.V2TIMFriendOperationResult;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.imsdk.v2.V2TIMSendCallback;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.qcloud.tim.uikit.base.ITitleBarLayout;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tuikit.tuichat.TUIChatService;
import com.tencent.qcloud.tuikit.tuichat.bean.MessageReceiptInfo;
import com.tencent.qcloud.tuikit.tuichat.bean.message.TUIMessageBean;
import com.tencent.qcloud.tuikit.tuichat.interfaces.C2CChatEventListener;
import com.tencent.qcloud.tuikit.tuichat.presenter.C2CChatPresenter;
import com.tencent.qcloud.tuikit.tuichat.presenter.ChatPresenter;
import com.tencent.qcloud.tuikit.tuicontact.util.ContactUtils;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.library.util.TimeFormatUtils;
import com.visionvera.library.widget.dialog.CenterPopup;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.SaveMeetingRecordRequest;
import com.visionvera.psychologist.c.net.EvaluationModuleApi;
import com.visionvera.psychologist.c.widget.tencentIm.utils.TIMCountDownTimer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Classname:VideoChatActivity
 * @author:haohuizhao
 * @Date:2021/11/26 15:37
 * @Version???v1.0
 * @describe??? ??????IM   ????????????
 * <p>
 * ?????????????????????https://cloud.tencent.com/document/product/269/36838
 * ??????Demo??????TIMSDK-master
 * ??????????????????????????????
 */
public class ChatActivity extends AppCompatActivity {

    private ChatActivityIntentBean chatBean;
    private TitleBarLayout mTitleBar;
    private ChatLayout mChatLayout;
    public BasePopupView loadingPopup;
    private CountDownTimer countDownTimer5Min;
    private CountDownTimer countDownTimerEnd;
    private BasePopupView time5MinPopup;
    private BasePopupView endTimePopup;

    public static class ChatActivityIntentBean implements Serializable {

        //??????userId
        String doctorId;
        //????????????
        String doctorName;
        //??????id
        String userId;
        //???????????????
        String userName;
        //????????????
        String endTime;

        public ChatActivityIntentBean(String doctorId, String doctorName, String userId, String userName) {
            this.doctorId = doctorId;
            this.doctorName = doctorName;
            this.userId = userId;
            this.userName = userName;
        }

        public ChatActivityIntentBean(String doctorId, String doctorName, String userId, String userName, String endTime) {
            this.doctorId = doctorId;
            this.doctorName = doctorName;
            this.userId = userId;
            this.userName = userName;
            this.endTime = endTime;
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_module_activity_chat);

        getIntentBean();
        //MyApplication????????????IM ??????IM
        //1.????????????
        //2.????????????????????????
        addFriends();


//        initView();
//        initData();
    }

    private void addFriends() {
        V2TIMFriendAddApplication v2TIMFriendAddApplication = new V2TIMFriendAddApplication("12607");
//        v2TIMFriendAddApplication.setAddWording(addWording);
        v2TIMFriendAddApplication.setAddSource("android");
        V2TIMManager.getFriendshipManager().addFriend(v2TIMFriendAddApplication, new V2TIMValueCallback<V2TIMFriendOperationResult>() {
            @Override
            public void onError(int code, String desc) {
                ToastUtils.showShort("??????????????????" + "addFriend err code = " + code + ", desc = " + desc);
//                TUIContactLog.e(TAG, "addFriend err code = " + code + ", desc = " + desc);
//                ContactUtils.callbackOnError(callback, TAG, code, desc);
                //?????? ??????Activity
//                ContactUtils.startChatActivity("12609",1, "12609", "");
            }

            @Override
            public void onSuccess(V2TIMFriendOperationResult v2TIMFriendOperationResult) {
                String resultInfo = v2TIMFriendOperationResult.getResultInfo();
                ToastUtils.showShort("??????????????????" + "addFriend err code = " + resultInfo.toString());
                //?????? ????????????Activity
//                    public static final int V2TIM_C2C = 1;
//    public static final int V2TIM_GROUP = 2;
                ContactUtils.startChatActivity("", "", "mhsptrunkdev12607", 1, "?????????", "", "2021-12-30 14:35:00", "0", "0");
            }
        });
    }


    private void initData() {
        if (!TextUtils.isEmpty(chatBean.endTime)) {

            long endTime = TimeFormatUtils.strToDateLong(chatBean.endTime).getTime();
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
                InputLayout inputLayout = mChatLayout.getInputLayout();
                inputLayout.setVisibility(View.GONE);
            }
        }

    }

    /**
     * ????????????????????????
     *
     * @param type       16 ???????????? 23 ????????????
     * @param businessId
     */
    public void net_saveMeetingRecord(int type, int businessId) {

        SaveMeetingRecordRequest saveMeetingRecordRequest = new SaveMeetingRecordRequest();
        saveMeetingRecordRequest.setBusinessId(businessId);
        saveMeetingRecordRequest.setBusinessType(type);
        RetrofitManager.create(EvaluationModuleApi.class)
                .saveMeetingRecord(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponseBean2>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponseBean2 baseResponseBean2) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void getIntentBean() {
        chatBean = (ChatActivityIntentBean) getIntent().getSerializableExtra("intentBean");
    }

    private void initView() {
        //???????????????????????????????????????????????????.
       /* ImmersionBar.with(this)
                //???????????????
                .statusBarColor(com.visionvera.library.R.color.base_module_white)
                //?????????????????????
                .statusBarDarkFont(true)
                //?????????????????????????????????????????????????????????????????????????????????. false??????????????????????????????true???????????????????????????
                .fitsSystemWindows(true)
                .init();*/
        //??????????????????????????????????????????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // ???????????????????????????
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(com.visionvera.library.R.color.base_module_white));

            // ???????????????????????????
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }
        mChatLayout.initDefault();

        ChatInfo chatInfo = new ChatInfo();
//        chatInfo.setType(TIMConversationType.C2C);

        chatInfo.setId(chatBean.doctorId);
        if (TextUtils.isEmpty(chatBean.doctorName)) {
            loadingPopup = new XPopup.Builder(this)
                    .dismissOnBackPressed(false)
                    .dismissOnTouchOutside(false)
                    .asLoading()
                    .show();

            List<String> users = new ArrayList<>();
            users.add(chatBean.doctorId);

            // ??????????????????
            V2TIMMessage v2TIMMessage = V2TIMManager.getMessageManager().createImageMessage("/sdcard/test.png");
// ??????????????????
            V2TIMManager.getMessageManager().sendMessage(v2TIMMessage, "toUserID", null, V2TIMMessage.V2TIM_PRIORITY_DEFAULT, false, null, new V2TIMSendCallback<V2TIMMessage>() {
                @Override
                public void onError(int code, String desc) {
                    // ????????????????????????
                }

                @Override
                public void onSuccess(V2TIMMessage v2TIMMessage) {
                    loadingPopup.dismiss();
                    // ????????????????????????
//                    chatInfo.setChatName(timUserProfiles.get(0).getNickName());
                    mChatLayout.setChatInfo(chatInfo);
                }

                @Override
                public void onProgress(int progress) {
                    // ?????????????????????0-100???
                }
            });

//            TIMFriendshipManager.getInstance().getUsersProfile(users, true, new TIMValueCallBack<List<TIMUserProfile>>() {
//                @Override
//                public void onError(int code, String desc) {
//                    loadingPopup.dismiss();
//                    Logger.i("getUsersProfile failed: " + code + ": desc:"+desc);
//                }
//
//                @Override
//                public void onSuccess(List<TIMUserProfile> timUserProfiles) {
//                    loadingPopup.dismiss();
//
//                    for (TIMUserProfile res:timUserProfiles){
//                        Logger.i("identifier: " + res.getIdentifier() + " nickName: " + res.getNickName());
//                    }
//                    chatInfo.setChatName(timUserProfiles.get(0).getNickName());
//                    mChatLayout.setChatInfo(chatInfo);
//                }
//            });


        } else {
            chatInfo.setChatName(chatBean.doctorName);
            mChatLayout.setChatInfo(chatInfo);
        }


        SetTitleBar();

        SetMessageLayout();

        SetInputLayout();

        mChatLayout.getMessageLayout().setOnItemClickListener(new MessageLayout.OnItemClickListener() {
            @Override
            public void onMessageLongClick(View view, int i, MessageInfo messageInfo) {

            }

            @Override
            public void onUserIconClick(View view, int i, MessageInfo messageInfo) {

            }
        });

        time5MinPopup = new XPopup.Builder(ChatActivity.this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(ChatActivity.this,
                        CenterPopup.CenterPopupType.oneButton,
                        "??????",
                        "????????????5?????????????????????",
                        "",
                        "??????",
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    centerPopup.dismiss();

                }));
        endTimePopup = new XPopup.Builder(ChatActivity.this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(ChatActivity.this,
                        CenterPopup.CenterPopupType.oneButton,
                        "??????",
                        "??????????????????",
                        "",
                        "??????",
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    centerPopup.dismiss();
                    InputLayout inputLayout = mChatLayout.getInputLayout();
                    inputLayout.setVisibility(View.GONE);
                }));
    }

    private void SetMessageLayout() {
        // ??? ChatLayout ????????? MessageLayout
        MessageLayout messageLayout = mChatLayout.getMessageLayout();
        messageLayout.setLeftNameVisibility(1);
        messageLayout.setRightNameVisibility(1);

    }

    private void SetInputLayout() {
        InputLayout inputLayout = mChatLayout.getInputLayout();
        // ?????????????????????
        inputLayout.disableVideoRecordAction(true);
        // ??????????????????
        inputLayout.disableSendFileAction(true);
        //??????????????????
//        inputLayout.disableAudioInput(true);
        //????????????????????????
        inputLayout.disableEmojiInput(true);

    }

    private void SetTitleBar() {
        //??????????????????????????????
        mTitleBar = mChatLayout.getTitleBar();

        mTitleBar.setTitle(chatBean.doctorName, ITitleBarLayout.POSITION.MIDDLE);
        mTitleBar.getRightIcon().setVisibility(View.GONE);
        mTitleBar.setBackgroundColor(getResources().getColor(com.visionvera.library.R.color.base_module_white));
        //?????????????????????????????????????????????????????????????????????????????????
        mTitleBar.setOnLeftClickListener(view -> finish());
    }

    @Override
    public void onPause() {
        super.onPause();
//        AudioPlayer.getInstance().stopPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mChatLayout != null) {
//            mChatLayout.exitChat();
//        }
//        if (countDownTimer5Min!=null){
//            countDownTimer5Min.cancel();
//            countDownTimer5Min=null;
//        }
//        if (countDownTimerEnd!=null){
//            countDownTimerEnd.cancel();
//            countDownTimerEnd=null;
//        }
    }
}
