package com.visionvera.psychologist.c.module.ordertreatment.bean;


import java.io.Serializable;

/**
 * @Classname:EvaluateDetailBean
 * @author:haohuizhao
 * @Date:2021/12/07 15:37
 * @Version：v1.0
 * @describe：
 * 二期  获取评价详情
 */

public class EvaluateDetailBean implements Serializable {


    /**
     * code : 0
     * errMsg : 获取成功
     * result : {"businessId":0,"createUid":978,"createtime":null,"endTime":null,"evaluateContent":"","evaluateSatisfaction":0,"id":1,"isDelete":0,"orderBy":0,"receiverServiceAccount":"","receiverServiceName":"","receiverSex":0,"receiverUserId":0,"scheduleId":86,"serviceAccount":"","serviceName":"","serviceNumber":"","serviceType":0,"serviceUserId":12510,"startTime":null,"status":1}
     */

    private int code;
    private String errMsg;
    private ResultDTO result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public static class ResultDTO implements Serializable {
        /**
         * businessId : 0
         * createUid : 978
         * createtime : null
         * endTime : null
         * evaluateContent :
         * evaluateSatisfaction : 0
         * id : 1
         * isDelete : 0
         * orderBy : 0
         * receiverServiceAccount :
         * receiverServiceName :
         * receiverSex : 0
         * receiverUserId : 0
         * scheduleId : 86
         * serviceAccount :
         * serviceName :
         * serviceNumber :
         * serviceType : 0
         * serviceUserId : 12510
         * startTime : null
         * status : 1
         */

        private int businessId;
        private int createUid;
        private String createtime;
        private Object endTime;
        private String evaluateContent;
        private int evaluateSatisfaction;
        private int id;
        private int isDelete;
        private int orderBy;
        private String receiverServiceAccount;
        private String receiverServiceName;
        private int receiverSex;
        private int receiverUserId;
        private int scheduleId;
        private String serviceAccount;
        private String serviceName;
        private String serviceNumber;
        private int serviceType;
        private int serviceUserId;
        private Object startTime;
        private int status;

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public int getCreateUid() {
            return createUid;
        }

        public void setCreateUid(int createUid) {
            this.createUid = createUid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getEvaluateContent() {
            return evaluateContent;
        }

        public void setEvaluateContent(String evaluateContent) {
            this.evaluateContent = evaluateContent;
        }

        public int getEvaluateSatisfaction() {
            return evaluateSatisfaction;
        }

        public void setEvaluateSatisfaction(int evaluateSatisfaction) {
            this.evaluateSatisfaction = evaluateSatisfaction;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public int getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
        }

        public String getReceiverServiceAccount() {
            return receiverServiceAccount;
        }

        public void setReceiverServiceAccount(String receiverServiceAccount) {
            this.receiverServiceAccount = receiverServiceAccount;
        }

        public String getReceiverServiceName() {
            return receiverServiceName;
        }

        public void setReceiverServiceName(String receiverServiceName) {
            this.receiverServiceName = receiverServiceName;
        }

        public int getReceiverSex() {
            return receiverSex;
        }

        public void setReceiverSex(int receiverSex) {
            this.receiverSex = receiverSex;
        }

        public int getReceiverUserId() {
            return receiverUserId;
        }

        public void setReceiverUserId(int receiverUserId) {
            this.receiverUserId = receiverUserId;
        }

        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
        }

        public String getServiceAccount() {
            return serviceAccount;
        }

        public void setServiceAccount(String serviceAccount) {
            this.serviceAccount = serviceAccount;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceNumber() {
            return serviceNumber;
        }

        public void setServiceNumber(String serviceNumber) {
            this.serviceNumber = serviceNumber;
        }

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public int getServiceUserId() {
            return serviceUserId;
        }

        public void setServiceUserId(int serviceUserId) {
            this.serviceUserId = serviceUserId;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
