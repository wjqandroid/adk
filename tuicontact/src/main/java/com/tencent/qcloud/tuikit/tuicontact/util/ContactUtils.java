package com.tencent.qcloud.tuikit.tuicontact.util;

import android.os.Bundle;
import android.text.TextUtils;

import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.qcloud.tuicore.TUIConstants;
import com.tencent.qcloud.tuicore.TUICore;
import com.tencent.qcloud.tuicore.component.interfaces.IUIKitCallback;

public class ContactUtils {

    public static <T> void callbackOnError(IUIKitCallback<T> callBack, String module, int errCode, String desc) {
        if (callBack != null) {
            callBack.onError(module, errCode, desc);
        }
    }

    public static <T> void callbackOnError(IUIKitCallback<T> callBack, int errCode, String desc) {
        if (callBack != null) {
            callBack.onError(null, errCode, desc);
        }
    }

    public static <T> void callbackOnSuccess(IUIKitCallback<T> callBack, T data) {
        if (callBack != null) {
            callBack.onSuccess(data);
        }
    }

    public static boolean isC2CChat(int chatType) {
        return chatType == V2TIMConversation.V2TIM_C2C;
    }

    public static boolean isGroupChat(int chatType) {
        return chatType == V2TIMConversation.V2TIM_GROUP;
    }

    //外部可调用 跳转到聊天页面
    public static void startChatActivity(String orderId, String appNum, String chatId, int chatType, String chatName, String groupType, String endTime, String customerCall, String customerAnswer) {
        if (TextUtils.isEmpty(chatId)) {
            return;
        }
        Bundle bundle = new Bundle();
        //主键ID   orderId
        bundle.putString(TUIConstants.TUIChat.CHAT_ORDER_ID, orderId);
        //订单编号   appNum
        bundle.putString(TUIConstants.TUIChat.CHAT_APP_NUM, appNum);
        //对方ID
        bundle.putString(TUIConstants.TUIChat.CHAT_ID, chatId);
        //对方昵称
        bundle.putString(TUIConstants.TUIChat.CHAT_NAME, chatName);
        //聊天类型
        bundle.putInt(TUIConstants.TUIChat.CHAT_TYPE, chatType);
        //服务结束时间
        bundle.putString(TUIConstants.TUIChat.CHAT_END_TIMES, endTime);
        //用户发起聊天 0|未发起，1|已发起
        bundle.putString(TUIConstants.TUIChat.CHAT_CUSTOMER_CALL, customerCall);
        //用户接听 0|否，1|是
        bundle.putString(TUIConstants.TUIChat.CHAT_CUSTOMER_ANSWER, customerAnswer);
        // 单聊 群聊
        if (isC2CChat(chatType)) {//TUIC2CChatActivity
            TUICore.startActivity(TUIConstants.TUIChat.C2C_CHAT_ACTIVITY_NAME, bundle);
        } else if (isGroupChat(chatType)) {//TUIGroupChatActivity
            bundle.putString(TUIConstants.TUIChat.GROUP_TYPE, groupType);
            TUICore.startActivity(TUIConstants.TUIChat.GROUP_CHAT_ACTIVITY_NAME, bundle);
        }
    }

    public static String getLoginUser() {
        return V2TIMManager.getInstance().getLoginUser();
    }
}
