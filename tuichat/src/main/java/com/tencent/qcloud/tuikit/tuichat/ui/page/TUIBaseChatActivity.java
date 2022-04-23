package com.tencent.qcloud.tuikit.tuichat.ui.page;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.tencent.imsdk.v2.V2TIMGroupAtInfo;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.qcloud.tuicore.TUIConstants;
import com.tencent.qcloud.tuicore.TUICore;
import com.tencent.qcloud.tuicore.TUILogin;
import com.tencent.qcloud.tuicore.component.activities.BaseLightActivity;
import com.tencent.qcloud.tuicore.util.ToastUtil;
import com.tencent.qcloud.tuikit.tuichat.R;
import com.tencent.qcloud.tuikit.tuichat.bean.ChatInfo;
import com.tencent.qcloud.tuikit.tuichat.bean.DraftInfo;
import com.tencent.qcloud.tuikit.tuichat.bean.GroupInfo;
import com.tencent.qcloud.tuikit.tuichat.bean.GroupMemberInfo;
import com.tencent.qcloud.tuikit.tuichat.bean.MessageEvent;
import com.tencent.qcloud.tuikit.tuichat.bean.message.TUIMessageBean;
import com.tencent.qcloud.tuikit.tuichat.util.ChatMessageBuilder;
import com.tencent.qcloud.tuikit.tuichat.util.TUIChatLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

public abstract class TUIBaseChatActivity extends BaseLightActivity {

    private static final String TAG = TUIBaseChatActivity.class.getSimpleName();

//源代碼
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        TUIChatLog.i(TAG, "onCreate " + this);
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.chat_activity);
//
//
//    }


    //修改后
    //base必须含有ID为 “normal_view” 布局
    @Override
    protected int getLayoutId() {
        return R.layout.chat_activity;

    }

    @Override
    protected void doYourself() {
        chat(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        TUIChatLog.i(TAG, "onNewIntent");
        super.onNewIntent(intent);
        chat(intent);
    }

    @Override
    protected void onResume() {
        TUIChatLog.i(TAG, "onResume");
        super.onResume();
    }

    private void chat(Intent intent) {
        Bundle bundle = intent.getExtras();
        TUIChatLog.i(TAG, "bundle: " + bundle + " intent: " + intent);
        if (bundle == null || !TUILogin.isUserLogined()) {
            startSplashActivity(bundle);
            finish();
            return;
        }

        ChatInfo chatInfo = getChatInfo(intent);
        TUIChatLog.i(TAG, "start chatActivity chatInfo: " + chatInfo);

        if (chatInfo != null) {
            initChat(chatInfo);
        } else {
            ToastUtil.toastShortMessage("init chat failed , chatInfo is empty.");
            TUIChatLog.e(TAG, "init chat failed , chatInfo is empty.");
            finish();
        }
    }

    public abstract void initChat(ChatInfo chatInfo);

    private void startSplashActivity(Bundle bundle) {
        TUICore.startActivity(this, "SplashActivity", bundle);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 3 && data != null) {
            if (requestCode == 11) {
                List<String> stringList = data.getStringArrayListExtra("list");
                if (stringList != null && !stringList.isEmpty()) {
                    String[] stringArray = stringList.toArray(new String[]{});
                    data.putExtra(TUIConstants.TUICalling.PARAM_NAME_USERIDS, stringArray);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    for (String key : data.getExtras().keySet()) {
                        hashMap.put(key, data.getExtras().get(key));
                    }
                    TUICore.callService(TUIConstants.TUICalling.SERVICE_NAME,
                            TUIConstants.TUICalling.METHOD_NAME_CALL, hashMap);
                }
            }
        }

    }

    private ChatInfo getChatInfo(Intent intent) {
        int chatType = intent.getIntExtra(TUIConstants.TUIChat.CHAT_TYPE, ChatInfo.TYPE_INVALID);
        ChatInfo chatInfo;
        if (chatType == ChatInfo.TYPE_C2C) {
            chatInfo = new ChatInfo();
        } else if (chatType == ChatInfo.TYPE_GROUP) {
            chatInfo = new GroupInfo();
        } else {
            return null;
        }
        chatInfo.setType(chatType);
        //chatInfo 设置数据
        //主键ID   orderId
        chatInfo.setOrderId(intent.getStringExtra(TUIConstants.TUIChat.CHAT_ORDER_ID));
        //订单编号   appNum
        chatInfo.setAppNum(intent.getStringExtra(TUIConstants.TUIChat.CHAT_APP_NUM));
        chatInfo.setId(intent.getStringExtra(TUIConstants.TUIChat.CHAT_ID));
        chatInfo.setChatName(intent.getStringExtra(TUIConstants.TUIChat.CHAT_NAME));
        //服务结束时间  endTime
        chatInfo.setEntTime(intent.getStringExtra(TUIConstants.TUIChat.CHAT_END_TIMES));
        //用户发起聊天 0|未发起，1|已发起
        chatInfo.setCustomerCall(intent.getStringExtra(TUIConstants.TUIChat.CHAT_CUSTOMER_CALL));
        //用户接听 0|否，1|是
        chatInfo.setCustomerAnswer(intent.getStringExtra(TUIConstants.TUIChat.CHAT_CUSTOMER_ANSWER));
        DraftInfo draftInfo = new DraftInfo();
        draftInfo.setDraftText(intent.getStringExtra(TUIConstants.TUIChat.DRAFT_TEXT));
        draftInfo.setDraftTime(intent.getLongExtra(TUIConstants.TUIChat.DRAFT_TIME, 0));
        chatInfo.setDraft(draftInfo);
        chatInfo.setTopChat(intent.getBooleanExtra(TUIConstants.TUIChat.IS_TOP_CHAT, false));
        V2TIMMessage v2TIMMessage = (V2TIMMessage) intent.getSerializableExtra(TUIConstants.TUIChat.LOCATE_MESSAGE);
        TUIMessageBean messageInfo = ChatMessageBuilder.buildMessage(v2TIMMessage);
        chatInfo.setLocateMessage(messageInfo);
        chatInfo.setAtInfoList((List<V2TIMGroupAtInfo>) intent.getSerializableExtra(TUIConstants.TUIChat.AT_INFO_LIST));

        if (chatType == ChatInfo.TYPE_GROUP) {
            GroupInfo groupInfo = (GroupInfo) chatInfo;
            groupInfo.setFaceUrl(intent.getStringExtra(TUIConstants.TUIChat.FACE_URL));
            groupInfo.setGroupName(intent.getStringExtra(TUIConstants.TUIChat.GROUP_NAME));
            groupInfo.setGroupType(intent.getStringExtra(TUIConstants.TUIChat.GROUP_TYPE));
            groupInfo.setJoinType(intent.getIntExtra(TUIConstants.TUIChat.JOIN_TYPE, 0));
            groupInfo.setMemberCount(intent.getIntExtra(TUIConstants.TUIChat.MEMBER_COUNT, 0));
            groupInfo.setMessageReceiveOption(intent.getBooleanExtra(TUIConstants.TUIChat.RECEIVE_OPTION, false));
            groupInfo.setNotice(intent.getStringExtra(TUIConstants.TUIChat.NOTICE));
            groupInfo.setOwner(intent.getStringExtra(TUIConstants.TUIChat.OWNER));
            groupInfo.setMemberDetails((List<GroupMemberInfo>) intent.getSerializableExtra(TUIConstants.TUIChat.MEMBER_DETAILS));
        }

        if (TextUtils.isEmpty(chatInfo.getId())) {
            return null;
        }
        return chatInfo;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }
}
