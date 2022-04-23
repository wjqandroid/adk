package com.visionvera.psychologist.c.module.ordertreatment.bean;


import java.io.Serializable;

/**
 * @Classname:EvaluateListRequest
 * @author:haohuizhao
 * @Date:2021/12/07 15:37
 * @Version：v1.0
 * @describe： 二期  app新增评价
 */

public class AddEvaluateRequest implements Serializable {
    /**
     * 创建人关联tb-user表id
     */
    private Integer createUid;

    /**
     * 订单号
     */
    private String serviceNumber;

    /**
     * 接受服务方姓名
     */
    private String receiverServiceName;

    /**
     * 接受服务方账号
     */
    private String receiverServiceAccount;

    /**
     * 1男2女
     */
    private Integer receiverSex;

    /**
     * 接受服务方user-id
     */
    private Integer receiverUserId;

    /**
     * 服务方user-id
     */
    private Integer serviceUserId;

    /**
     * 服务方姓名
     */
    private String serviceName;

    /**
     * 1图文2语音3视频
     */
    private Integer serviceType;

    /**
     * 服务方账号
     */
    private String serviceAccount;

    /**
     * 评价满意度1一星2二星3三星4四星5五星
     */
    private Integer evaluateSatisfaction;

    /**
     * 关联业务表 mhsp_business_application表id
     */
    private Integer businessId;

    /**
     * 评价内容
     */
    private String evaluateContent;

    /**
     * 1显示2隐藏
     */
    private Integer status;

    /**
     * 关联staff-schedule表id 服务时间使用
     *     @JSONField(format="yyyy-MM-dd HH:mm:ss")
     */
    private Integer scheduleId;
    private String startTime;

    private String endTime;

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getReceiverServiceName() {
        return receiverServiceName;
    }

    public void setReceiverServiceName(String receiverServiceName) {
        this.receiverServiceName = receiverServiceName;
    }

    public String getReceiverServiceAccount() {
        return receiverServiceAccount;
    }

    public void setReceiverServiceAccount(String receiverServiceAccount) {
        this.receiverServiceAccount = receiverServiceAccount;
    }

    public Integer getReceiverSex() {
        return receiverSex;
    }

    public void setReceiverSex(Integer receiverSex) {
        this.receiverSex = receiverSex;
    }

    public Integer getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Integer receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public Integer getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(Integer serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    public Integer getEvaluateSatisfaction() {
        return evaluateSatisfaction;
    }

    public void setEvaluateSatisfaction(Integer evaluateSatisfaction) {
        this.evaluateSatisfaction = evaluateSatisfaction;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
