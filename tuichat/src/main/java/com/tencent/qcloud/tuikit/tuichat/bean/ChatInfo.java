package com.tencent.qcloud.tuikit.tuichat.bean;

import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.imsdk.v2.V2TIMGroupAtInfo;
import com.tencent.qcloud.tuikit.tuichat.bean.message.TUIMessageBean;

import java.io.Serializable;
import java.util.List;

/**
 * 聊天信息基本类
 */
public class ChatInfo implements Serializable {

    public static final int TYPE_C2C = V2TIMConversation.V2TIM_C2C;
    public static final int TYPE_GROUP = V2TIMConversation.V2TIM_GROUP;
    public static final int TYPE_INVALID = V2TIMConversation.CONVERSATION_TYPE_INVALID;

    private static List<V2TIMGroupAtInfo> atInfoList;

    private String chatName;

    private int type = V2TIMConversation.V2TIM_C2C;
    private String id;
    private String groupType;
    private boolean isTopChat;
    private TUIMessageBean locateMessage;


    //主键ID   orderId
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    // 订单编号   appNum
    private String appNum;

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }

    //服务结束时间entTime
    private String entTime;

    public String getEntTime() {
        return entTime;
    }

    public void setEntTime(String entTime) {
        this.entTime = entTime;
    }

    //用户发起聊天 0|未发起，1|已发起
    private String customerCall;

    public String getCustomerCall() {
        return customerCall;
    }

    public void setCustomerCall(String customerCall) {
        this.customerCall = customerCall;
    }

    //用户接听 0|否，1|是
    private String customerAnswer;

    public String getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(String customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    /**
     * 草稿
     */
    private DraftInfo draft;

    public ChatInfo() {

    }

    /**
     * 获取聊天的标题，单聊一般为对方名称，群聊为群名字
     *
     * @return
     */
    public String getChatName() {
        return chatName;
    }

    /**
     * 设置聊天的标题，单聊一般为对方名称，群聊为群名字
     *
     * @param chatName
     */
    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    /**
     * 获取聊天类型，C2C为单聊，Group为群聊
     *
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * 设置聊天类型，C2C为单聊，Group为群聊
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取聊天唯一标识
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置聊天唯一标识
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取群组类型
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * 设置群组类型
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    /**
     * 是否为置顶的会话
     *
     * @return
     */
    public boolean isTopChat() {
        return isTopChat;
    }

    /**
     * 设置会话是否置顶
     *
     * @param topChat
     */
    public void setTopChat(boolean topChat) {
        isTopChat = topChat;
    }

    public List<V2TIMGroupAtInfo> getAtInfoList() {
        return atInfoList;
    }

    public void setAtInfoList(List<V2TIMGroupAtInfo> atInfoList) {
        this.atInfoList = atInfoList;
    }

    public void setLocateMessage(TUIMessageBean locateMessage) {
        this.locateMessage = locateMessage;
    }

    public TUIMessageBean getLocateMessage() {
        return locateMessage;
    }

    public void setDraft(DraftInfo draft) {
        this.draft = draft;
    }

    public DraftInfo getDraft() {
        return this.draft;
    }

}
