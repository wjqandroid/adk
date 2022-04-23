package com.visionvera.psychologist.c.module.counselling.bean;

import java.io.Serializable;
import java.util.List;

public class OrderConsultDetailBean implements Serializable {
    /**
     * code : 0
     * errMsg : 成功
     * result : {"age":62,"appFrom":2,"appId":0,"appNum":"77757358e5ec46479d2b62627330f984","appScheduleId":9290,"appStatus":2,"appType":0,"applyReason":"","businessId":1372,"buttonList":null,"clientType":0,"comments":"","consultingFee":"0.00","counselingNum":7,"createtime":"2021-11-19 14:12:47","customerAnswer":0,"customerCall":0,"customerConfirm":0,"departmentId":0,"deviceCode":"","deviceId":0,"diagnoseDesc":"","diagnoseResult":"","diagnosisMode":4,"diagnosisModeName":"文字","doctorId":12451,"doctorName":"高咨询师","doctorUserId":0,"endTime":"2021-11-19 17:00:00","evaluateId":0,"explain":"","hospitalDeptId":0,"hospitalId":0,"id":1372,"idNo":"210882195911081746","idNoInfo":"","insuranceCardNo":"","intentId":0,"isApptionment":0,"isDelete":0,"isRepeat":1,"lableNames":null,"meetingFlag":0,"meetingRoomId":0,"meetingRoomName":"","nickName":"大鹏","oldId":0,"oldStageId":0,"operatorId":12607,"orderBy":0,"passivityOperatorId":0,"patComplaints":"睡眠问题","patId":0,"patientId":12607,"patientName":"18514596167","patientNameInfo":"","payWay":0,"payWayName":"","phoneNumber":"18514596167","phoneNumberInfo":"","pid":0,"receiverHospitalId":0,"receiverMeetingRoomId":0,"receiverMeetingRoomName":"","rejectId":0,"repeatName":"复诊","scheduleId":0,"serverAnswer":0,"serverCall":0,"serverConfirm":0,"sex":"1","sexInfo":"","stageId":0,"stageName":"待受理","startTime":"2021-11-19 16:00:00","telNum":"","titleName":"","treatingResult":0,"type":"","typeId":23,"typeName":"心理咨询","updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[],"userId":""}
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


    public static class ResultBean implements Serializable {
        /**
         * age : 62
         * appFrom : 2
         * appId : 0
         * appNum : 77757358e5ec46479d2b62627330f984
         * appScheduleId : 9290
         * appStatus : 2
         * appType : 0
         * applyReason :
         * businessId : 1372
         * buttonList : null
         * clientType : 0
         * comments :
         * consultingFee : 0.00
         * counselingNum : 7
         * createtime : 2021-11-19 14:12:47
         * customerAnswer : 0
         * customerCall : 0
         * customerConfirm : 0
         * departmentId : 0
         * deviceCode :
         * deviceId : 0
         * diagnoseDesc :
         * diagnoseResult :
         * diagnosisMode : 4
         * diagnosisModeName : 文字
         * doctorId : 12451
         * doctorName : 高咨询师
         * doctorUserId : 0
         * endTime : 2021-11-19 17:00:00
         * evaluateId : 0
         * explain :
         * hospitalDeptId : 0
         * hospitalId : 0
         * id : 1372
         * idNo : 210882195911081746
         * idNoInfo :
         * insuranceCardNo :
         * intentId : 0
         * isApptionment : 0
         * isDelete : 0
         * isRepeat : 1
         * lableNames : null
         * meetingFlag : 0
         * meetingRoomId : 0
         * meetingRoomName :
         * nickName : 大鹏
         * oldId : 0
         * oldStageId : 0
         * operatorId : 12607
         * orderBy : 0
         * passivityOperatorId : 0
         * patComplaints : 睡眠问题
         * patId : 0
         * patientId : 12607
         * patientName : 18514596167
         * patientNameInfo :
         * payWay : 0
         * payWayName :
         * phoneNumber : 18514596167
         * phoneNumberInfo :
         * pid : 0
         * receiverHospitalId : 0
         * receiverMeetingRoomId : 0
         * receiverMeetingRoomName :
         * rejectId : 0
         * repeatName : 复诊
         * scheduleId : 0
         * serverAnswer : 0
         * serverCall : 0
         * serverConfirm : 0
         * sex : 1
         * sexInfo :
         * stageId : 0
         * stageName : 待受理
         * startTime : 2021-11-19 16:00:00
         * telNum :
         * titleName :
         * treatingResult : 0
         * type :
         * typeId : 23
         * typeName : 心理咨询
         * updatetime : 0001-01-01 00:00:00
         * uploadId :
         * uploadUrl : []
         * userId :
         */
//    "customerCall":1,       //用户发起聊天 0|未发起，1|已发起
//            "serverCall":0,         //服务人员发起聊天 0|未发起，1|已发起
//            "customerAnswer":0,     //用户接听 0|否，1|是
//            "serverAnswer":1,       //服务方接听 0|否，1|是
//            "customerConfirm":1,    //用户确认 0|未确认，1|已确认
//            "serverConfirm":1,      //服务人员确认 0|未确认，1|已确认
//            "evaluateId":0,         //评价id(0 表示尚未评价)
        private int age;
        private int appFrom;
        private int appId;
        private String appNum;
        private int appScheduleId;
        private int appStatus;
        private int appType;
        private String applyReason;
        private int businessId;
        private Object buttonList;
        private int clientType;
        private String comments;
        private String consultingFee;
        private int counselingNum;
        private String createtime;
        private int customerAnswer;
        private int customerCall;
        private int customerConfirm;
        private int departmentId;
        private String deviceCode;
        private int deviceId;
        private String diagnoseDesc;
        private String diagnoseResult;
        private int diagnosisMode;
        private String diagnosisModeName;
        private int doctorId;
        private String doctorName;
        private int doctorUserId;
        private String endTime;
        private int evaluateId;
        private String explain;
        private int hospitalDeptId;
        private int hospitalId;
        private int id;
        private String idNo;
        private String idNoInfo;
        private String insuranceCardNo;
        private int intentId;
        private int isApptionment;
        private int isDelete;
        private int isRepeat;
        private Object lableNames;
        private int meetingFlag;
        private int meetingRoomId;
        private String meetingRoomName;
        private String nickName;
        private int oldId;
        private int oldStageId;
        private int operatorId;
        private int orderBy;
        private int passivityOperatorId;
        private String patComplaints;
        private int patId;
        private int patientId;
        private String patientName;
        private String patientNameInfo;
        private int payWay;
        private String payWayName;
        private String phoneNumber;
        private String phoneNumberInfo;
        private int pid;
        private int receiverHospitalId;
        private int receiverMeetingRoomId;
        private String receiverMeetingRoomName;
        private int rejectId;
        private String repeatName;
        private int scheduleId;
        private int serverAnswer;
        private int serverCall;
        private int serverConfirm;
        private String sex;
        private String sexInfo;
        private int stageId;
        private String stageName;
        private String startTime;
        private String telNum;
        private String titleName;
        private int treatingResult;
        private String type;
        private int typeId;
        private String typeName;
        private String updatetime;
        private String uploadId;
        private String userId;
        private List<UploadUrlBean> uploadUrl;

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

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
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

        public int getAppType() {
            return appType;
        }

        public void setAppType(int appType) {
            this.appType = appType;
        }

        public String getApplyReason() {
            return applyReason;
        }

        public void setApplyReason(String applyReason) {
            this.applyReason = applyReason;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public Object getButtonList() {
            return buttonList;
        }

        public void setButtonList(Object buttonList) {
            this.buttonList = buttonList;
        }

        public int getClientType() {
            return clientType;
        }

        public void setClientType(int clientType) {
            this.clientType = clientType;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getConsultingFee() {
            return consultingFee;
        }

        public void setConsultingFee(String consultingFee) {
            this.consultingFee = consultingFee;
        }

        public int getCounselingNum() {
            return counselingNum;
        }

        public void setCounselingNum(int counselingNum) {
            this.counselingNum = counselingNum;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getCustomerAnswer() {
            return customerAnswer;
        }

        public void setCustomerAnswer(int customerAnswer) {
            this.customerAnswer = customerAnswer;
        }

        public int getCustomerCall() {
            return customerCall;
        }

        public void setCustomerCall(int customerCall) {
            this.customerCall = customerCall;
        }

        public int getCustomerConfirm() {
            return customerConfirm;
        }

        public void setCustomerConfirm(int customerConfirm) {
            this.customerConfirm = customerConfirm;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
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

        public String getDiagnoseDesc() {
            return diagnoseDesc;
        }

        public void setDiagnoseDesc(String diagnoseDesc) {
            this.diagnoseDesc = diagnoseDesc;
        }

        public String getDiagnoseResult() {
            return diagnoseResult;
        }

        public void setDiagnoseResult(String diagnoseResult) {
            this.diagnoseResult = diagnoseResult;
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

        public int getDoctorUserId() {
            return doctorUserId;
        }

        public void setDoctorUserId(int doctorUserId) {
            this.doctorUserId = doctorUserId;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getEvaluateId() {
            return evaluateId;
        }

        public void setEvaluateId(int evaluateId) {
            this.evaluateId = evaluateId;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
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

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getIdNoInfo() {
            return idNoInfo;
        }

        public void setIdNoInfo(String idNoInfo) {
            this.idNoInfo = idNoInfo;
        }

        public String getInsuranceCardNo() {
            return insuranceCardNo;
        }

        public void setInsuranceCardNo(String insuranceCardNo) {
            this.insuranceCardNo = insuranceCardNo;
        }

        public int getIntentId() {
            return intentId;
        }

        public void setIntentId(int intentId) {
            this.intentId = intentId;
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

        public Object getLableNames() {
            return lableNames;
        }

        public void setLableNames(Object lableNames) {
            this.lableNames = lableNames;
        }

        public int getMeetingFlag() {
            return meetingFlag;
        }

        public void setMeetingFlag(int meetingFlag) {
            this.meetingFlag = meetingFlag;
        }

        public int getMeetingRoomId() {
            return meetingRoomId;
        }

        public void setMeetingRoomId(int meetingRoomId) {
            this.meetingRoomId = meetingRoomId;
        }

        public String getMeetingRoomName() {
            return meetingRoomName;
        }

        public void setMeetingRoomName(String meetingRoomName) {
            this.meetingRoomName = meetingRoomName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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

        public String getPatComplaints() {
            return patComplaints;
        }

        public void setPatComplaints(String patComplaints) {
            this.patComplaints = patComplaints;
        }

        public int getPatId() {
            return patId;
        }

        public void setPatId(int patId) {
            this.patId = patId;
        }

        public int getPatientId() {
            return patientId;
        }

        public void setPatientId(int patientId) {
            this.patientId = patientId;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPatientNameInfo() {
            return patientNameInfo;
        }

        public void setPatientNameInfo(String patientNameInfo) {
            this.patientNameInfo = patientNameInfo;
        }

        public int getPayWay() {
            return payWay;
        }

        public void setPayWay(int payWay) {
            this.payWay = payWay;
        }

        public String getPayWayName() {
            return payWayName;
        }

        public void setPayWayName(String payWayName) {
            this.payWayName = payWayName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPhoneNumberInfo() {
            return phoneNumberInfo;
        }

        public void setPhoneNumberInfo(String phoneNumberInfo) {
            this.phoneNumberInfo = phoneNumberInfo;
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

        public String getReceiverMeetingRoomName() {
            return receiverMeetingRoomName;
        }

        public void setReceiverMeetingRoomName(String receiverMeetingRoomName) {
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

        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
        }

        public int getServerAnswer() {
            return serverAnswer;
        }

        public void setServerAnswer(int serverAnswer) {
            this.serverAnswer = serverAnswer;
        }

        public int getServerCall() {
            return serverCall;
        }

        public void setServerCall(int serverCall) {
            this.serverCall = serverCall;
        }

        public int getServerConfirm() {
            return serverConfirm;
        }

        public void setServerConfirm(int serverConfirm) {
            this.serverConfirm = serverConfirm;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSexInfo() {
            return sexInfo;
        }

        public void setSexInfo(String sexInfo) {
            this.sexInfo = sexInfo;
        }

        public int getStageId() {
            return stageId;
        }

        public void setStageId(int stageId) {
            this.stageId = stageId;
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

        public String getTelNum() {
            return telNum;
        }

        public void setTelNum(String telNum) {
            this.telNum = telNum;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public int getTreatingResult() {
            return treatingResult;
        }

        public void setTreatingResult(int treatingResult) {
            this.treatingResult = treatingResult;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }



        public static class UploadUrlBean {
            /**
             * id : 7009
             * url : http://58.30.9.142:43150/upload/images/jpg_1561709277514.jpg
             */

            private String id;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public List<UploadUrlBean> getUploadUrl() {
            return uploadUrl;
        }

        public void setUploadUrl(List<UploadUrlBean> uploadUrl) {
            this.uploadUrl = uploadUrl;
        }

//        public List<?> getUploadUrl() {
//            return uploadUrl;
//        }
//
//        public void setUploadUrl(List<?> uploadUrl) {
//            this.uploadUrl = uploadUrl;
//        }
    }


//    /**
//     * code : 0
//     * errMsg : ok
//     * result : {"age":0,"appFrom":2,"appNum":"201912270004","appScheduleId":3496,"appStatus":2,"businessId":0,"buttonList":null,"comments":null,"createtime":"2019-12-27 14:45:09","deviceCode":0,"deviceId":0,"diagnosisMode":3,"diagnosisModeName":"手机远程","doctorId":0,"doctorName":null,"endTime":"2019-12-28 09:00:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":514,"idNo":null,"insuranceCardNo":null,"isApptionment":0,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"meetingRoomName":null,"oldId":0,"oldStageId":0,"operatorId":0,"orderBy":0,"passivityOperatorId":0,"patComplaints":null,"patientId":0,"patientNameInfo":null,"payWay":1,"payWayName":"现场付费","phoneNumber":null,"pid":0,"receiverHospitalId":0,"receiverMeetingRoomId":0,"receiverMeetingRoomName":null,"rejectId":0,"repeatName":"初诊","sex":null,"stageName":"待受理","startTime":"2019-12-28 08:00:00","telNum":null,"type":null,"typeId":0,"typeName":"心理咨询","updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[],"userId":null}
//     */
//
//    private int code;
//    private String errMsg;
//    private ResultBean result;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getErrMsg() {
//        return errMsg;
//    }
//
//    public void setErrMsg(String errMsg) {
//        this.errMsg = errMsg;
//    }
//
//    public ResultBean getResult() {
//        return result;
//    }
//
//    public void setResult(ResultBean result) {
//        this.result = result;
//    }
//
//    public static class ResultBean {
//        /**
//         * age : 0
//         * appFrom : 2
//         * appNum : 201912270004
//         * appScheduleId : 3496
//         * appStatus : 2
//         * businessId : 0
//         * buttonList : null
//         * comments : null
//         * createtime : 2019-12-27 14:45:09
//         * deviceCode : 0
//         * deviceId : 0
//         * diagnosisMode : 3
//         * diagnosisModeName : 手机远程
//         * doctorId : 0
//         * doctorName : null
//         * endTime : 2019-12-28 09:00:00
//         * explain : null
//         * hospitalDeptId : 0
//         * hospitalId : 0
//         * id : 514
//         * idNo : null
//         * insuranceCardNo : null
//         * isApptionment : 0
//         * isDelete : 0
//         * isRepeat : 0
//         * meetingRoomId : 0
//         * meetingRoomName : null
//         * oldId : 0
//         * oldStageId : 0
//         * operatorId : 0
//         * orderBy : 0
//         * passivityOperatorId : 0
//         * patComplaints : null
//         * patientId : 0
//         * patientNameInfo : null
//         * payWay : 1
//         * payWayName : 现场付费
//         * phoneNumber : null
//         * pid : 0
//         * receiverHospitalId : 0
//         * receiverMeetingRoomId : 0
//         * receiverMeetingRoomName : null
//         * rejectId : 0
//         * repeatName : 初诊
//         * sex : null
//         * stageName : 待受理
//         * startTime : 2019-12-28 08:00:00
//         * telNum : null
//         * type : null
//         * typeId : 0
//         * typeName : 心理咨询
//         * updatetime : 0001-01-01 00:00:00
//         * uploadId :
//         * uploadUrl : []
//         * userId : null
//         */
//
//        private int age;
//        private int appFrom;
//        private String appNum;
//        private int appScheduleId;
//        //2 待受理 8已驳回 7已取消 14待接诊 15已完成 16已作废 18待付款
//        private int appStatus;
//        private int businessId;
////        private Object buttonList;
//        private String comments;
//        private String createtime;
//        private String deviceCode;
//        private int deviceId;
//        private int diagnosisMode;
//        private String diagnosisModeName;
//        private int doctorId;
//        private String doctorName;
//        private String endTime;
//        private String explain;
//        private int hospitalDeptId;
//        private int hospitalId;
//        private int id;
//        private String idNo;
//        private String insuranceCardNo;
//        private int isApptionment;
//        private int isDelete;
//        private int isRepeat;
//        private int meetingRoomId;
//        private String meetingRoomName;
//        private int oldId;
//        private int oldStageId;
//        private int operatorId;
//        private int orderBy;
//        private int passivityOperatorId;
//        private String patComplaints;
//        private int patientId;
//        private String patientNameInfo;
//        private int payWay;
//        private String payWayName;
//        private String phoneNumber = "";
//        private String phoneNumberInfo = "";
//        private int pid;
//        private int receiverHospitalId;
//        private int receiverMeetingRoomId;
//        private String receiverMeetingRoomName;
//        private int rejectId;
//        private String repeatName;
//        private String sex;
//        private String stageName;
//        private String startTime;
//        private String telNum;
//        private String type;
//        private int typeId;
//        private String typeName;
//        private String updatetime;
//        private String uploadId;
//        private String userId;
//        private List<UploadUrlBean> uploadUrl;
//        private int counselingNum;
//        public String consultingFee;
//        //是否拉过会状态：1拉过会或聊过天 2或空值为没有
//        public int meetingFlag;
//        //"titleName": "国家二级心理咨询师",
//        private String titleName;
//        //擅长
//        private List<String> lableNames;
//
//
//        public List<String> getLableNames() {
//            return lableNames;
//        }
//
//        public void setLableNames(List<String> lableNames) {
//            this.lableNames = lableNames;
//        }
//
//        public String getTitleName() {
//            return titleName;
//        }
//
//        public void setTitleName(String titleName) {
//            this.titleName = titleName;
//        }
//
//        public String getPhoneNumberInfo() {
//            return phoneNumberInfo;
//        }
//
//        public void setPhoneNumberInfo(String phoneNumberInfo) {
//            this.phoneNumberInfo = phoneNumberInfo;
//        }
//
//        public int getCounselingNum() {
//            return counselingNum;
//        }
//
//        public void setCounselingNum(int counselingNum) {
//            this.counselingNum = counselingNum;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public int getAppFrom() {
//            return appFrom;
//        }
//
//        public void setAppFrom(int appFrom) {
//            this.appFrom = appFrom;
//        }
//
//        public String getAppNum() {
//            return appNum;
//        }
//
//        public void setAppNum(String appNum) {
//            this.appNum = appNum;
//        }
//
//        public int getAppScheduleId() {
//            return appScheduleId;
//        }
//
//        public void setAppScheduleId(int appScheduleId) {
//            this.appScheduleId = appScheduleId;
//        }
//
//        public int getAppStatus() {
//            return appStatus;
//        }
//
//        public void setAppStatus(int appStatus) {
//            this.appStatus = appStatus;
//        }
//
//        public int getBusinessId() {
//            return businessId;
//        }
//
//        public void setBusinessId(int businessId) {
//            this.businessId = businessId;
//        }
//
////        public Object getButtonList() {
////            return buttonList;
////        }
////
////        public void setButtonList(Object buttonList) {
////            this.buttonList = buttonList;
////        }
//
//        public String getComments() {
//            return comments;
//        }
//
//        public void setComments(String comments) {
//            this.comments = comments;
//        }
//
//        public String getCreatetime() {
//            return createtime;
//        }
//
//        public void setCreatetime(String createtime) {
//            this.createtime = createtime;
//        }
//
//        public String getDeviceCode() {
//            return deviceCode;
//        }
//
//        public void setDeviceCode(String deviceCode) {
//            this.deviceCode = deviceCode;
//        }
//
//        public int getDeviceId() {
//            return deviceId;
//        }
//
//        public void setDeviceId(int deviceId) {
//            this.deviceId = deviceId;
//        }
//
//        public int getDiagnosisMode() {
//            return diagnosisMode;
//        }
//
//        public void setDiagnosisMode(int diagnosisMode) {
//            this.diagnosisMode = diagnosisMode;
//        }
//
//        public String getDiagnosisModeName() {
//            return diagnosisModeName;
//        }
//
//        public void setDiagnosisModeName(String diagnosisModeName) {
//            this.diagnosisModeName = diagnosisModeName;
//        }
//
//        public int getDoctorId() {
//            return doctorId;
//        }
//
//        public void setDoctorId(int doctorId) {
//            this.doctorId = doctorId;
//        }
//
//        public String getDoctorName() {
//            return doctorName;
//        }
//
//        public void setDoctorName(String doctorName) {
//            this.doctorName = doctorName;
//        }
//
//        public String getEndTime() {
//            return endTime;
//        }
//
//        public void setEndTime(String endTime) {
//            this.endTime = endTime;
//        }
//
//        public String getExplain() {
//            return explain;
//        }
//
//        public void setExplain(String explain) {
//            this.explain = explain;
//        }
//
//        public int getHospitalDeptId() {
//            return hospitalDeptId;
//        }
//
//        public void setHospitalDeptId(int hospitalDeptId) {
//            this.hospitalDeptId = hospitalDeptId;
//        }
//
//        public int getHospitalId() {
//            return hospitalId;
//        }
//
//        public void setHospitalId(int hospitalId) {
//            this.hospitalId = hospitalId;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getIdNo() {
//            return idNo;
//        }
//
//        public void setIdNo(String idNo) {
//            this.idNo = idNo;
//        }
//
//        public String getInsuranceCardNo() {
//            return insuranceCardNo;
//        }
//
//        public void setInsuranceCardNo(String insuranceCardNo) {
//            this.insuranceCardNo = insuranceCardNo;
//        }
//
//        public int getIsApptionment() {
//            return isApptionment;
//        }
//
//        public void setIsApptionment(int isApptionment) {
//            this.isApptionment = isApptionment;
//        }
//
//        public int getIsDelete() {
//            return isDelete;
//        }
//
//        public void setIsDelete(int isDelete) {
//            this.isDelete = isDelete;
//        }
//
//        public int getIsRepeat() {
//            return isRepeat;
//        }
//
//        public void setIsRepeat(int isRepeat) {
//            this.isRepeat = isRepeat;
//        }
//
//        public int getMeetingRoomId() {
//            return meetingRoomId;
//        }
//
//        public void setMeetingRoomId(int meetingRoomId) {
//            this.meetingRoomId = meetingRoomId;
//        }
//
//        public String getMeetingRoomName() {
//            return meetingRoomName;
//        }
//
//        public void setMeetingRoomName(String meetingRoomName) {
//            this.meetingRoomName = meetingRoomName;
//        }
//
//        public int getOldId() {
//            return oldId;
//        }
//
//        public void setOldId(int oldId) {
//            this.oldId = oldId;
//        }
//
//        public int getOldStageId() {
//            return oldStageId;
//        }
//
//        public void setOldStageId(int oldStageId) {
//            this.oldStageId = oldStageId;
//        }
//
//        public int getOperatorId() {
//            return operatorId;
//        }
//
//        public void setOperatorId(int operatorId) {
//            this.operatorId = operatorId;
//        }
//
//        public int getOrderBy() {
//            return orderBy;
//        }
//
//        public void setOrderBy(int orderBy) {
//            this.orderBy = orderBy;
//        }
//
//        public int getPassivityOperatorId() {
//            return passivityOperatorId;
//        }
//
//        public void setPassivityOperatorId(int passivityOperatorId) {
//            this.passivityOperatorId = passivityOperatorId;
//        }
//
//        public String getPatComplaints() {
//            return patComplaints;
//        }
//
//        public void setPatComplaints(String patComplaints) {
//            this.patComplaints = patComplaints;
//        }
//
//        public int getPatientId() {
//            return patientId;
//        }
//
//        public void setPatientId(int patientId) {
//            this.patientId = patientId;
//        }
//
//        public String getPatientNameInfo() {
//            return patientNameInfo;
//        }
//
//        public void setPatientNameInfo(String patientNameInfo) {
//            this.patientNameInfo = patientNameInfo;
//        }
//
//        public int getPayWay() {
//            return payWay;
//        }
//
//        public void setPayWay(int payWay) {
//            this.payWay = payWay;
//        }
//
//        public String getPayWayName() {
//            return payWayName;
//        }
//
//        public void setPayWayName(String payWayName) {
//            this.payWayName = payWayName;
//        }
//
//        public String getPhoneNumber() {
//            return phoneNumber;
//        }
//
//        public void setPhoneNumber(String phoneNumber) {
//            this.phoneNumber = phoneNumber;
//        }
//
//        public int getPid() {
//            return pid;
//        }
//
//        public void setPid(int pid) {
//            this.pid = pid;
//        }
//
//        public int getReceiverHospitalId() {
//            return receiverHospitalId;
//        }
//
//        public void setReceiverHospitalId(int receiverHospitalId) {
//            this.receiverHospitalId = receiverHospitalId;
//        }
//
//        public int getReceiverMeetingRoomId() {
//            return receiverMeetingRoomId;
//        }
//
//        public void setReceiverMeetingRoomId(int receiverMeetingRoomId) {
//            this.receiverMeetingRoomId = receiverMeetingRoomId;
//        }
//
//        public String getReceiverMeetingRoomName() {
//            return receiverMeetingRoomName;
//        }
//
//        public void setReceiverMeetingRoomName(String receiverMeetingRoomName) {
//            this.receiverMeetingRoomName = receiverMeetingRoomName;
//        }
//
//        public int getRejectId() {
//            return rejectId;
//        }
//
//        public void setRejectId(int rejectId) {
//            this.rejectId = rejectId;
//        }
//
//        public String getRepeatName() {
//            return repeatName;
//        }
//
//        public void setRepeatName(String repeatName) {
//            this.repeatName = repeatName;
//        }
//
//        public String getSex() {
//            return sex;
//        }
//
//        public void setSex(String sex) {
//            this.sex = sex;
//        }
//
//        public String getStageName() {
//            return stageName;
//        }
//
//        public void setStageName(String stageName) {
//            this.stageName = stageName;
//        }
//
//        public String getStartTime() {
//            return startTime;
//        }
//
//        public void setStartTime(String startTime) {
//            this.startTime = startTime;
//        }
//
//        public String getTelNum() {
//            return telNum;
//        }
//
//        public void setTelNum(String telNum) {
//            this.telNum = telNum;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        public void setType(String type) {
//            this.type = type;
//        }
//
//        public int getTypeId() {
//            return typeId;
//        }
//
//        public void setTypeId(int typeId) {
//            this.typeId = typeId;
//        }
//
//        public String getTypeName() {
//            return typeName;
//        }
//
//        public void setTypeName(String typeName) {
//            this.typeName = typeName;
//        }
//
//        public String getUpdatetime() {
//            return updatetime;
//        }
//
//        public void setUpdatetime(String updatetime) {
//            this.updatetime = updatetime;
//        }
//
//        public String getUploadId() {
//            return uploadId;
//        }
//
//        public void setUploadId(String uploadId) {
//            this.uploadId = uploadId;
//        }
//
//        public String getUserId() {
//            return userId;
//        }
//
//        public void setUserId(String userId) {
//            this.userId = userId;
//        }
//
//        public List<UploadUrlBean> getUploadUrl() {
//            return uploadUrl;
//        }
//
//        public void setUploadUrl(List<UploadUrlBean> uploadUrl) {
//            this.uploadUrl = uploadUrl;
//        }
//
//        public static class UploadUrlBean {
//            /**
//             * id : 7009
//             * url : http://58.30.9.142:43150/upload/images/jpg_1561709277514.jpg
//             */
//
//            private String id;
//            private String url;
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getUrl() {
//                return url;
//            }
//
//            public void setUrl(String url) {
//                this.url = url;
//            }
//        }
//    }
}
