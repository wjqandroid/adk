package com.tencent.qcloud.tuikit.tuichat.ui.page;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.tencent.qcloud.tuikit.tuichat.presenter.C2CChatPresenter;
import com.tencent.qcloud.tuikit.tuichat.bean.ChatInfo;
import com.tencent.qcloud.tuikit.tuichat.TUIChatConstants;
import com.tencent.qcloud.tuikit.tuichat.util.TIMCountDownTimer;
import com.tencent.qcloud.tuikit.tuichat.util.TUIChatLog;
import com.tencent.qcloud.tuicore.TUICore;
import com.visionvera.library.util.SpUtil;
import com.visionvera.library.util.TimeFormatUtils;
import com.visionvera.library.widget.dialog.CenterPopup;

import java.util.Calendar;


//2.单聊Fragment   对话页面
//修改：服务时间到时  结束通话

public class TUIC2CChatFragment extends TUIBaseChatFragment {

    //计时器
    private CountDownTimer countDownTimer5Min;
    private CountDownTimer countDownTimerEnd;
    //pop弹窗
    private BasePopupView time5MinPopup;
    private BasePopupView endTimePopup;

    private static final String TAG = TUIC2CChatFragment.class.getSimpleName();

    private ChatInfo chatInfo;
    private C2CChatPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TUIChatLog.i(TAG, "oncreate view " + this);

        baseView = super.onCreateView(inflater, container, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return baseView;
        }
        chatInfo = (ChatInfo) bundle.getSerializable(TUIChatConstants.CHAT_INFO);


        if (chatInfo == null) {
            return baseView;
        }

        initView();

        //服务结束时间
        //服务结束提示框
        initPopView();
        //ToastUtils.showShort("服务结束时间:"+ chatInfo.getEntTime(), getActivity());
        //服务结束计时器
        initTimer(chatInfo.getEntTime());


        return baseView;
    }

    @Override
    protected void initView() {
        super.initView();

        titleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("chatId", chatInfo.getId());
                TUICore.startActivity("FriendProfileActivity", bundle);
            }
        });

        chatView.setPresenter(presenter);
        presenter.setChatInfo(chatInfo);
        chatView.setChatInfo(chatInfo);
    }

    public void setPresenter(C2CChatPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public C2CChatPresenter getPresenter() {
        return presenter;
    }

    @Override
    public ChatInfo getChatInfo() {
        return chatInfo;
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

                    //服务时间已结束  关闭聊天页面
                    getActivity().finish();
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
