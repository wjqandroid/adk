package com.visionvera.psychologist.c.module.ordertreatment.bean;


import java.io.Serializable;
import java.util.List;

/**
 * @Classname:EvaluateListBean
 * @author:haohuizhao
 * @Date:2021/12/07 15:37
 * @Version：v1.0
 * @describe：
 * 二期  评价列表
 */

public class EvaluateListBean implements Serializable {

    @Override
    public String toString() {
        return "EvaluateListBean{" +
                "code=" + code +
                ", errMsg='" + errMsg + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * code : 0
     * errMsg :
     * result : {"dataList":[{"businessId":0,"createUid":978,"createtime":null,"endTime":1564880400000,"evaluateContent":"","evaluateSatisfaction":0,"id":1,"isDelete":0,"orderBy":0,"receiverServiceAccount":"","receiverServiceName":"","receiverSex":0,"receiverUserId":0,"scheduleId":0,"serviceAccount":"","serviceName":"","serviceNumber":"","serviceType":0,"serviceUserId":0,"startTime":1564790400000,"status":1}],"pageIndex":1,"pageSize":10,"totalPageCnt":1,"totalRecords":1}
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
         * dataList : [{"businessId":0,"createUid":978,"createtime":null,"endTime":1564880400000,"evaluateContent":"","evaluateSatisfaction":0,"id":1,"isDelete":0,"orderBy":0,"receiverServiceAccount":"","receiverServiceName":"","receiverSex":0,"receiverUserId":0,"scheduleId":0,"serviceAccount":"","serviceName":"","serviceNumber":"","serviceType":0,"serviceUserId":0,"startTime":1564790400000,"status":1}]
         * pageIndex : 1
         * pageSize : 10
         * totalPageCnt : 1
         * totalRecords : 1
         */

        private int pageIndex;
        private int pageSize;
        private int totalPageCnt;
        private int totalRecords;

        private List<DataListDTO> dataList;
        //头像
        private String imgUrl;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPageCnt() {
            return totalPageCnt;
        }

        public void setTotalPageCnt(int totalPageCnt) {
            this.totalPageCnt = totalPageCnt;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public List<DataListDTO> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListDTO> dataList) {
            this.dataList = dataList;
        }

        public static class DataListDTO implements Serializable {
            /**
             * businessId : 0
             * createUid : 978
             * createtime : null
             * endTime : 1564880400000
             * evaluateContent :
             * evaluateSatisfaction : 0
             * id : 1
             * isDelete : 0
             * orderBy : 0
             * receiverServiceAccount :
             * receiverServiceName :
             * receiverSex : 0
             * receiverUserId : 0
             * scheduleId : 0
             * serviceAccount :
             * serviceName :
             * serviceNumber :
             * serviceType : 0
             * serviceUserId : 0
             * startTime : 1564790400000
             * status : 1
             */
            /**
             * 关联业务表 mhsp_business_application表id
             */
            private int businessId;
            private int createUid;
            private String createtime;
            private long endTime;
            /**
             * 评价内容
             */
            private String evaluateContent;
            private int evaluateSatisfaction;
            private int id;
            private int isDelete;
            private int orderBy;
            private String receiverServiceAccount;
            private String receiverServiceName;
            private int receiverSex;
            private int receiverUserId;
            /**
             * 关联staff-schedule表id 服务时间使用
             */
            private int scheduleId;
            private String serviceAccount;
            private String serviceName;
            private String serviceNumber;
            private int serviceType;
            private int serviceUserId;
            //@JSONField(format="yyyy-MM-dd HH:mm:ss")
            private String startTime;
            /**
             * 1显示2隐藏
             */
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

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
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

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
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
}
