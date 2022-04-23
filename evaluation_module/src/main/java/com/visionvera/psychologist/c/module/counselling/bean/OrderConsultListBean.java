package com.visionvera.psychologist.c.module.counselling.bean;

import java.util.List;

public class OrderConsultListBean {


    /**
     * code : 0
     * errMsg : ok
     * result : {"dataList":[{"age":0,"appFrom":2,"appNum":"201912270004","appScheduleId":3496,"appStatus":2,"businessId":544,"buttonList":[{"buttonName":"查看详情","img":"/image/shengqingxinxi.png","id":"1"}],"comments":null,"createtime":"2019-12-27 14:45:09","deviceCode":0,"deviceId":0,"diagnosisMode":3,"diagnosisModeName":null,"doctorId":983,"doctorName":null,"endTime":"2019-12-28 09:00:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":514,"idNo":null,"insuranceCardNo":null,"isApptionment":0,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"meetingRoomName":null,"oldId":0,"oldStageId":0,"operatorId":1160,"orderBy":0,"passivityOperatorId":0,"patComplaints":null,"patientId":1160,"patientNameInfo":null,"payWay":0,"payWayName":null,"phoneNumber":null,"pid":0,"receiverHospitalId":0,"receiverMeetingRoomId":0,"receiverMeetingRoomName":null,"rejectId":0,"repeatName":"初诊","sex":null,"stageName":"待受理","startTime":"2019-12-28 08:00:00","telNum":null,"type":null,"typeId":23,"typeName":"心理咨询","updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[],"userId":null},{"age":0,"appFrom":2,"appNum":"201912270002","appScheduleId":3495,"appStatus":2,"businessId":543,"buttonList":[{"buttonName":"查看详情","img":"/image/shengqingxinxi.png","id":"1"}],"comments":null,"createtime":"2019-12-27 14:39:35","deviceCode":0,"deviceId":0,"diagnosisMode":3,"diagnosisModeName":null,"doctorId":983,"doctorName":null,"endTime":"2019-12-28 10:00:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":513,"idNo":null,"insuranceCardNo":null,"isApptionment":0,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"meetingRoomName":null,"oldId":0,"oldStageId":0,"operatorId":1160,"orderBy":0,"passivityOperatorId":0,"patComplaints":null,"patientId":1160,"patientNameInfo":null,"payWay":0,"payWayName":null,"phoneNumber":null,"pid":0,"receiverHospitalId":0,"receiverMeetingRoomId":0,"receiverMeetingRoomName":null,"rejectId":0,"repeatName":"初诊","sex":null,"stageName":"待受理","startTime":"2019-12-28 09:00:00","telNum":null,"type":null,"typeId":23,"typeName":"心理咨询","updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[],"userId":null},{"age":0,"appFrom":2,"appNum":"201912260008","appScheduleId":86,"appStatus":2,"businessId":542,"buttonList":[{"buttonName":"查看详情","img":"/image/shengqingxinxi.png","id":"1"}],"comments":null,"createtime":"2019-12-26 18:19:33","deviceCode":0,"deviceId":0,"diagnosisMode":3,"diagnosisModeName":null,"doctorId":983,"doctorName":null,"endTime":"2019-08-04 09:00:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":512,"idNo":null,"insuranceCardNo":null,"isApptionment":0,"isDelete":0,"isRepeat":1,"meetingRoomId":0,"meetingRoomName":null,"oldId":0,"oldStageId":0,"operatorId":1160,"orderBy":0,"passivityOperatorId":0,"patComplaints":null,"patientId":77,"patientNameInfo":"刘传政","payWay":0,"payWayName":null,"phoneNumber":null,"pid":0,"receiverHospitalId":0,"receiverMeetingRoomId":0,"receiverMeetingRoomName":null,"rejectId":0,"repeatName":"复诊","sex":"男","stageName":"待受理","startTime":"2019-08-03 08:00:00","telNum":null,"type":null,"typeId":23,"typeName":"心理咨询","updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[],"userId":null}],"pageIndex":1,"pageSize":20,"totalPageCnt":1,"totalRecords":3}
     */

    private int code;
    private String errMsg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * dataList : [{"age":0,"appFrom":2,"appNum":"201912270004","appScheduleId":3496,"appStatus":2,"businessId":544,"buttonList":[{"buttonName":"查看详情","img":"/image/shengqingxinxi.png","id":"1"}],"comments":null,"createtime":"2019-12-27 14:45:09","deviceCode":0,"deviceId":0,"diagnosisMode":3,"diagnosisModeName":null,"doctorId":983,"doctorName":null,"endTime":"2019-12-28 09:00:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":514,"idNo":null,"insuranceCardNo":null,"isApptionment":0,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"meetingRoomName":null,"oldId":0,"oldStageId":0,"operatorId":1160,"orderBy":0,"passivityOperatorId":0,"patComplaints":null,"patientId":1160,"patientNameInfo":null,"payWay":0,"payWayName":null,"phoneNumber":null,"pid":0,"receiverHospitalId":0,"receiverMeetingRoomId":0,"receiverMeetingRoomName":null,"rejectId":0,"repeatName":"初诊","sex":null,"stageName":"待受理","startTime":"2019-12-28 08:00:00","telNum":null,"type":null,"typeId":23,"typeName":"心理咨询","updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[],"userId":null},{"age":0,"appFrom":2,"appNum":"201912270002","appScheduleId":3495,"appStatus":2,"businessId":543,"buttonList":[{"buttonName":"查看详情","img":"/image/shengqingxinxi.png","id":"1"}],"comments":null,"createtime":"2019-12-27 14:39:35","deviceCode":0,"deviceId":0,"diagnosisMode":3,"diagnosisModeName":null,"doctorId":983,"doctorName":null,"endTime":"2019-12-28 10:00:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":513,"idNo":null,"insuranceCardNo":null,"isApptionment":0,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"meetingRoomName":null,"oldId":0,"oldStageId":0,"operatorId":1160,"orderBy":0,"passivityOperatorId":0,"patComplaints":null,"patientId":1160,"patientNameInfo":null,"payWay":0,"payWayName":null,"phoneNumber":null,"pid":0,"receiverHospitalId":0,"receiverMeetingRoomId":0,"receiverMeetingRoomName":null,"rejectId":0,"repeatName":"初诊","sex":null,"stageName":"待受理","startTime":"2019-12-28 09:00:00","telNum":null,"type":null,"typeId":23,"typeName":"心理咨询","updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[],"userId":null},{"age":0,"appFrom":2,"appNum":"201912260008","appScheduleId":86,"appStatus":2,"businessId":542,"buttonList":[{"buttonName":"查看详情","img":"/image/shengqingxinxi.png","id":"1"}],"comments":null,"createtime":"2019-12-26 18:19:33","deviceCode":0,"deviceId":0,"diagnosisMode":3,"diagnosisModeName":null,"doctorId":983,"doctorName":null,"endTime":"2019-08-04 09:00:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":512,"idNo":null,"insuranceCardNo":null,"isApptionment":0,"isDelete":0,"isRepeat":1,"meetingRoomId":0,"meetingRoomName":null,"oldId":0,"oldStageId":0,"operatorId":1160,"orderBy":0,"passivityOperatorId":0,"patComplaints":null,"patientId":77,"patientNameInfo":"刘传政","payWay":0,"payWayName":null,"phoneNumber":null,"pid":0,"receiverHospitalId":0,"receiverMeetingRoomId":0,"receiverMeetingRoomName":null,"rejectId":0,"repeatName":"复诊","sex":"男","stageName":"待受理","startTime":"2019-08-03 08:00:00","telNum":null,"type":null,"typeId":23,"typeName":"心理咨询","updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[],"userId":null}]
         * pageIndex : 1
         * pageSize : 20
         * totalPageCnt : 1
         * totalRecords : 3
         */

        private int pageIndex;
        private int pageSize;
        private int totalPageCnt;
        private int totalRecords;
        private List<DataListBean> dataList;

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

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * age : 0
             * appFrom : 2
             * appNum : 201912270004
             * appScheduleId : 3496
             * appStatus : 2
             * businessId : 544
             * buttonList : [{"buttonName":"查看详情","img":"/image/shengqingxinxi.png","id":"1"}]
             * comments : null
             * createtime : 2019-12-27 14:45:09
             * deviceCode : 0
             * deviceId : 0
             * diagnosisMode : 3
             * diagnosisModeName : null
             * doctorId : 983
             * doctorName : null
             * endTime : 2019-12-28 09:00:00
             * explain : null
             * hospitalDeptId : 0
             * hospitalId : 0
             * id : 514
             * idNo : null
             * insuranceCardNo : null
             * isApptionment : 0
             * isDelete : 0
             * isRepeat : 0
             * meetingRoomId : 0
             * meetingRoomName : null
             * oldId : 0
             * oldStageId : 0
             * operatorId : 1160
             * orderBy : 0
             * passivityOperatorId : 0
             * patComplaints : null
             * patientId : 1160
             * patientNameInfo : null
             * payWay : 0
             * payWayName : null
             * phoneNumber : null
             * pid : 0
             * receiverHospitalId : 0
             * receiverMeetingRoomId : 0
             * receiverMeetingRoomName : null
             * rejectId : 0
             * repeatName : 初诊
             * sex : null
             * stageName : 待受理
             * startTime : 2019-12-28 08:00:00
             * telNum : null
             * type : null
             * typeId : 23
             * typeName : 心理咨询
             * updatetime : 0001-01-01 00:00:00
             * uploadId :
             * uploadUrl : []
             * userId : null
             */

            private int age;
            private int appFrom;
            private String appNum;
            private int appScheduleId;
            private int appStatus;
            private int businessId;
            private String comments;
            private String createtime;
            private String deviceCode;
            private int deviceId;
            private int diagnosisMode;
            private String diagnosisModeName;
            private int doctorId;
            private String doctorName;
            private String endTime;
            private Object explain;
            private int hospitalDeptId;
            private int hospitalId;
            private int id;
            private Object idNo;
            private Object insuranceCardNo;
            private int isApptionment;
            private int isDelete;
            private int isRepeat;
            private int meetingRoomId;
            private Object meetingRoomName;
            private int oldId;
            private int oldStageId;
            private int operatorId;
            private int orderBy;
            private int passivityOperatorId;
            private Object patComplaints;
            private int patientId;
            private Object patientNameInfo;
            private int payWay;
            private Object payWayName;
            private Object phoneNumber;
            private int pid;
            private int receiverHospitalId;
            private int receiverMeetingRoomId;
            private Object receiverMeetingRoomName;
            private int rejectId;
            private String repeatName;
            private Object sex;
            private String stageName;
            private String startTime;
            private Object telNum;
            private Object type;
            private int typeId;
            private String typeName;
            private String updatetime;
            private String uploadId;
            private Object userId;
            private List<ButtonListBean> buttonList;
            private List<?> uploadUrl;

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getAppFrom() {
                return appFrom;
            }

            public void setAppFrom(int appFrom) {
                this.appFrom = appFrom;
            }

            public String getAppNum() {
                return appNum;
            }

            public void setAppNum(String appNum) {
                this.appNum = appNum;
            }

            public int getAppScheduleId() {
                return appScheduleId;
            }

            public void setAppScheduleId(int appScheduleId) {
                this.appScheduleId = appScheduleId;
            }

            public int getAppStatus() {
                return appStatus;
            }

            public void setAppStatus(int appStatus) {
                this.appStatus = appStatus;
            }

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getDeviceCode() {
                return deviceCode;
            }

            public void setDeviceCode(String deviceCode) {
                this.deviceCode = deviceCode;
            }

            public int getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(int deviceId) {
                this.deviceId = deviceId;
            }

            public int getDiagnosisMode() {
                return diagnosisMode;
            }

            public void setDiagnosisMode(int diagnosisMode) {
                this.diagnosisMode = diagnosisMode;
            }

            public String getDiagnosisModeName() {
                return diagnosisModeName;
            }

            public void setDiagnosisModeName(String diagnosisModeName) {
                this.diagnosisModeName = diagnosisModeName;
            }

            public int getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(int doctorId) {
                this.doctorId = doctorId;
            }

            public String getDoctorName() {
                return doctorName;
            }

            public void setDoctorName(String doctorName) {
                this.doctorName = doctorName;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public Object getExplain() {
                return explain;
            }

            public void setExplain(Object explain) {
                this.explain = explain;
            }

            public int getHospitalDeptId() {
                return hospitalDeptId;
            }

            public void setHospitalDeptId(int hospitalDeptId) {
                this.hospitalDeptId = hospitalDeptId;
            }

            public int getHospitalId() {
                return hospitalId;
            }

            public void setHospitalId(int hospitalId) {
                this.hospitalId = hospitalId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getIdNo() {
                return idNo;
            }

            public void setIdNo(Object idNo) {
                this.idNo = idNo;
            }

            public Object getInsuranceCardNo() {
                return insuranceCardNo;
            }

            public void setInsuranceCardNo(Object insuranceCardNo) {
                this.insuranceCardNo = insuranceCardNo;
            }

            public int getIsApptionment() {
                return isApptionment;
            }

            public void setIsApptionment(int isApptionment) {
                this.isApptionment = isApptionment;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public int getIsRepeat() {
                return isRepeat;
            }

            public void setIsRepeat(int isRepeat) {
                this.isRepeat = isRepeat;
            }

            public int getMeetingRoomId() {
                return meetingRoomId;
            }

            public void setMeetingRoomId(int meetingRoomId) {
                this.meetingRoomId = meetingRoomId;
            }

            public Object getMeetingRoomName() {
                return meetingRoomName;
            }

            public void setMeetingRoomName(Object meetingRoomName) {
                this.meetingRoomName = meetingRoomName;
            }

            public int getOldId() {
                return oldId;
            }

            public void setOldId(int oldId) {
                this.oldId = oldId;
            }

            public int getOldStageId() {
                return oldStageId;
            }

            public void setOldStageId(int oldStageId) {
                this.oldStageId = oldStageId;
            }

            public int getOperatorId() {
                return operatorId;
            }

            public void setOperatorId(int operatorId) {
                this.operatorId = operatorId;
            }

            public int getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(int orderBy) {
                this.orderBy = orderBy;
            }

            public int getPassivityOperatorId() {
                return passivityOperatorId;
            }

            public void setPassivityOperatorId(int passivityOperatorId) {
                this.passivityOperatorId = passivityOperatorId;
            }

            public Object getPatComplaints() {
                return patComplaints;
            }

            public void setPatComplaints(Object patComplaints) {
                this.patComplaints = patComplaints;
            }

            public int getPatientId() {
                return patientId;
            }

            public void setPatientId(int patientId) {
                this.patientId = patientId;
            }

            public Object getPatientNameInfo() {
                return patientNameInfo;
            }

            public void setPatientNameInfo(Object patientNameInfo) {
                this.patientNameInfo = patientNameInfo;
            }

            public int getPayWay() {
                return payWay;
            }

            public void setPayWay(int payWay) {
                this.payWay = payWay;
            }

            public Object getPayWayName() {
                return payWayName;
            }

            public void setPayWayName(Object payWayName) {
                this.payWayName = payWayName;
            }

            public Object getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(Object phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getReceiverHospitalId() {
                return receiverHospitalId;
            }

            public void setReceiverHospitalId(int receiverHospitalId) {
                this.receiverHospitalId = receiverHospitalId;
            }

            public int getReceiverMeetingRoomId() {
                return receiverMeetingRoomId;
            }

            public void setReceiverMeetingRoomId(int receiverMeetingRoomId) {
                this.receiverMeetingRoomId = receiverMeetingRoomId;
            }

            public Object getReceiverMeetingRoomName() {
                return receiverMeetingRoomName;
            }

            public void setReceiverMeetingRoomName(Object receiverMeetingRoomName) {
                this.receiverMeetingRoomName = receiverMeetingRoomName;
            }

            public int getRejectId() {
                return rejectId;
            }

            public void setRejectId(int rejectId) {
                this.rejectId = rejectId;
            }

            public String getRepeatName() {
                return repeatName;
            }

            public void setRepeatName(String repeatName) {
                this.repeatName = repeatName;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public String getStageName() {
                return stageName;
            }

            public void setStageName(String stageName) {
                this.stageName = stageName;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public Object getTelNum() {
                return telNum;
            }

            public void setTelNum(Object telNum) {
                this.telNum = telNum;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getUploadId() {
                return uploadId;
            }

            public void setUploadId(String uploadId) {
                this.uploadId = uploadId;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public List<ButtonListBean> getButtonList() {
                return buttonList;
            }

            public void setButtonList(List<ButtonListBean> buttonList) {
                this.buttonList = buttonList;
            }

            public List<?> getUploadUrl() {
                return uploadUrl;
            }

            public void setUploadUrl(List<?> uploadUrl) {
                this.uploadUrl = uploadUrl;
            }

            public static class ButtonListBean {
                /**
                 * buttonName : 查看详情
                 * img : /image/shengqingxinxi.png
                 * id : 1
                 */

                private String buttonName;
                private String img;
                private String id;

                public String getButtonName() {
                    return buttonName;
                }

                public void setButtonName(String buttonName) {
                    this.buttonName = buttonName;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}
