package com.visionvera.psychologist.c.module.ordertreatment.bean;

import java.util.List;

public class OrderTreatmentListBean {
    /**
     * code : 0
     * errMsg : ok
     * result : {"dataList":[{"age":0,"appNum":"2019072906563","appScheduleId":86,"appStatus":15,"buttonList":[{"buttonName":"查看详情","img":"","id":"1"},{"buttonName":"驳回","img":"","id":"2"},{"buttonName":"添加会议室","img":"","id":"3"}],"comments":null,"createtime":"2019-07-29 16:06:49","diagnosisMode":1,"doctorId":1019,"doctorName":"心理医生T","endTime":"2019-08-04 09:09:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":79,"idNo":null,"insuranceCardNo":null,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"oldId":0,"oldStageId":0,"operatorId":985,"orderBy":0,"patComplaints":null,"patientId":1,"patientName":null,"patientNameInfo":"赵一鸣","phoneNumber":null,"rejectId":0,"repeatName":"初诊","sex":"男","stageName":"已完成","startTime":"2019-08-03 08:08:00","typeId":0,"typeName":"病例会诊","updatetime":"2019-07-31 17:33:53","uploadId":"7009,7010","uploadUrl":[{"id":"7009","url":"http://58.30.9.142:43150/upload/images/jpg_1561709277514.jpg"},{"id":"7010","url":"http://58.30.9.142:43150/upload/images/jpg_1561709848680.jpg"}]},{"age":0,"appNum":"2019072906563","appScheduleId":87,"appStatus":15,"buttonList":[{"$ref":"$.result.dataList[0].buttonList[0]"},{"$ref":"$.result.dataList[0].buttonList[1]"},{"$ref":"$.result.dataList[0].buttonList[2]"}],"comments":null,"createtime":"2019-07-29 16:06:49","diagnosisMode":1,"doctorId":1019,"doctorName":"心理医生T","endTime":"2019-07-30 09:09:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":80,"idNo":null,"insuranceCardNo":null,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"oldId":0,"oldStageId":0,"operatorId":985,"orderBy":0,"patComplaints":null,"patientId":2,"patientName":null,"patientNameInfo":"钱钟文","phoneNumber":null,"rejectId":0,"repeatName":"初诊","sex":"男","stageName":"已完成","startTime":"2019-07-30 08:08:00","typeId":0,"typeName":"病例会诊","updatetime":"2019-07-29 16:06:53","uploadId":"7009,7010","uploadUrl":[{"id":"7009","url":"http://58.30.9.142:43150/upload/images/jpg_1561709277514.jpg"},{"id":"7010","url":"http://58.30.9.142:43150/upload/images/jpg_1561709848680.jpg"}]},{"age":0,"appNum":"2019072906564","appScheduleId":88,"appStatus":15,"buttonList":[{"$ref":"$.result.dataList[0].buttonList[0]"},{"$ref":"$.result.dataList[0].buttonList[1]"},{"$ref":"$.result.dataList[0].buttonList[2]"}],"comments":null,"createtime":"0001-01-01 00:00:00","diagnosisMode":1,"doctorId":1019,"doctorName":"心理医生T","endTime":"2019-08-04 09:09:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":87,"idNo":null,"insuranceCardNo":null,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"oldId":0,"oldStageId":0,"operatorId":985,"orderBy":0,"patComplaints":null,"patientId":1,"patientName":null,"patientNameInfo":"赵一鸣","phoneNumber":null,"rejectId":0,"repeatName":null,"sex":"男","stageName":"已完成","startTime":"2019-08-04 08:08:00","typeId":0,"typeName":null,"updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[]},{"age":0,"appNum":"2019072906563","appScheduleId":89,"appStatus":16,"buttonList":[{"$ref":"$.result.dataList[0].buttonList[0]"},{"$ref":"$.result.dataList[0].buttonList[1]"},{"$ref":"$.result.dataList[0].buttonList[2]"}],"comments":null,"createtime":"0001-01-01 00:00:00","diagnosisMode":1,"doctorId":1019,"doctorName":"心理医生T","endTime":"2019-08-04 10:10:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":88,"idNo":null,"insuranceCardNo":null,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"oldId":0,"oldStageId":0,"operatorId":985,"orderBy":0,"patComplaints":null,"patientId":2,"patientName":null,"patientNameInfo":"钱钟文","phoneNumber":null,"rejectId":0,"repeatName":null,"sex":"男","stageName":"已作废","startTime":"2019-08-04 09:09:00","typeId":0,"typeName":null,"updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[]}],"pageIndex":1,"pageSize":20,"totalPageCnt":1,"totalRecords":4}
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
         * dataList : [{"age":0,"appNum":"2019072906563","appScheduleId":86,"appStatus":15,"buttonList":[{"buttonName":"查看详情","img":"","id":"1"},{"buttonName":"驳回","img":"","id":"2"},{"buttonName":"添加会议室","img":"","id":"3"}],"comments":null,"createtime":"2019-07-29 16:06:49","diagnosisMode":1,"doctorId":1019,"doctorName":"心理医生T","endTime":"2019-08-04 09:09:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":79,"idNo":null,"insuranceCardNo":null,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"oldId":0,"oldStageId":0,"operatorId":985,"orderBy":0,"patComplaints":null,"patientId":1,"patientName":null,"patientNameInfo":"赵一鸣","phoneNumber":null,"rejectId":0,"repeatName":"初诊","sex":"男","stageName":"已完成","startTime":"2019-08-03 08:08:00","typeId":0,"typeName":"病例会诊","updatetime":"2019-07-31 17:33:53","uploadId":"7009,7010","uploadUrl":[{"id":"7009","url":"http://58.30.9.142:43150/upload/images/jpg_1561709277514.jpg"},{"id":"7010","url":"http://58.30.9.142:43150/upload/images/jpg_1561709848680.jpg"}]},{"age":0,"appNum":"2019072906563","appScheduleId":87,"appStatus":15,"buttonList":[{"$ref":"$.result.dataList[0].buttonList[0]"},{"$ref":"$.result.dataList[0].buttonList[1]"},{"$ref":"$.result.dataList[0].buttonList[2]"}],"comments":null,"createtime":"2019-07-29 16:06:49","diagnosisMode":1,"doctorId":1019,"doctorName":"心理医生T","endTime":"2019-07-30 09:09:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":80,"idNo":null,"insuranceCardNo":null,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"oldId":0,"oldStageId":0,"operatorId":985,"orderBy":0,"patComplaints":null,"patientId":2,"patientName":null,"patientNameInfo":"钱钟文","phoneNumber":null,"rejectId":0,"repeatName":"初诊","sex":"男","stageName":"已完成","startTime":"2019-07-30 08:08:00","typeId":0,"typeName":"病例会诊","updatetime":"2019-07-29 16:06:53","uploadId":"7009,7010","uploadUrl":[{"id":"7009","url":"http://58.30.9.142:43150/upload/images/jpg_1561709277514.jpg"},{"id":"7010","url":"http://58.30.9.142:43150/upload/images/jpg_1561709848680.jpg"}]},{"age":0,"appNum":"2019072906564","appScheduleId":88,"appStatus":15,"buttonList":[{"$ref":"$.result.dataList[0].buttonList[0]"},{"$ref":"$.result.dataList[0].buttonList[1]"},{"$ref":"$.result.dataList[0].buttonList[2]"}],"comments":null,"createtime":"0001-01-01 00:00:00","diagnosisMode":1,"doctorId":1019,"doctorName":"心理医生T","endTime":"2019-08-04 09:09:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":87,"idNo":null,"insuranceCardNo":null,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"oldId":0,"oldStageId":0,"operatorId":985,"orderBy":0,"patComplaints":null,"patientId":1,"patientName":null,"patientNameInfo":"赵一鸣","phoneNumber":null,"rejectId":0,"repeatName":null,"sex":"男","stageName":"已完成","startTime":"2019-08-04 08:08:00","typeId":0,"typeName":null,"updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[]},{"age":0,"appNum":"2019072906563","appScheduleId":89,"appStatus":16,"buttonList":[{"$ref":"$.result.dataList[0].buttonList[0]"},{"$ref":"$.result.dataList[0].buttonList[1]"},{"$ref":"$.result.dataList[0].buttonList[2]"}],"comments":null,"createtime":"0001-01-01 00:00:00","diagnosisMode":1,"doctorId":1019,"doctorName":"心理医生T","endTime":"2019-08-04 10:10:00","explain":null,"hospitalDeptId":0,"hospitalId":0,"id":88,"idNo":null,"insuranceCardNo":null,"isDelete":0,"isRepeat":0,"meetingRoomId":0,"oldId":0,"oldStageId":0,"operatorId":985,"orderBy":0,"patComplaints":null,"patientId":2,"patientName":null,"patientNameInfo":"钱钟文","phoneNumber":null,"rejectId":0,"repeatName":null,"sex":"男","stageName":"已作废","startTime":"2019-08-04 09:09:00","typeId":0,"typeName":null,"updatetime":"0001-01-01 00:00:00","uploadId":"","uploadUrl":[]}]
         * pageIndex : 1
         * pageSize : 20
         * totalPageCnt : 1
         * totalRecords : 4
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
             * appNum : 2019072906563
             * appScheduleId : 86
             * appStatus : 15
             * buttonList : [{"buttonName":"查看详情","img":"","id":"1"},{"buttonName":"驳回","img":"","id":"2"},{"buttonName":"添加会议室","img":"","id":"3"}]
             * comments : null
             * createtime : 2019-07-29 16:06:49
             * diagnosisMode : 1
             * doctorId : 1019
             * doctorName : 心理医生T
             * endTime : 2019-08-04 09:09:00
             * explain : null
             * hospitalDeptId : 0
             * hospitalId : 0
             * id : 79
             * idNo : null
             * insuranceCardNo : null
             * isDelete : 0
             * isRepeat : 0
             * meetingRoomId : 0
             * oldId : 0
             * oldStageId : 0
             * operatorId : 985
             * orderBy : 0
             * patComplaints : null
             * patientId : 1
             * patientName : null
             * patientNameInfo : 赵一鸣
             * phoneNumber : null
             * rejectId : 0
             * repeatName : 初诊
             * sex : 男
             * stageName : 已完成
             * startTime : 2019-08-03 08:08:00
             * typeId : 0
             * typeName : 病例会诊
             * updatetime : 2019-07-31 17:33:53
             * uploadId : 7009,7010
             * uploadUrl : [{"id":"7009","url":"http://58.30.9.142:43150/upload/images/jpg_1561709277514.jpg"},{"id":"7010","url":"http://58.30.9.142:43150/upload/images/jpg_1561709848680.jpg"}]
             */

            private int age;
            private String appNum;
            private int appScheduleId;
            private int appStatus;
            private String comments;
            private String createtime;
            private int diagnosisMode;
            private String diagnosisModeName;

            public String getDiagnosisModeName() {
                return diagnosisModeName;
            }

            public void setDiagnosisModeName(String diagnosisModeName) {
                this.diagnosisModeName = diagnosisModeName;
            }

            private int doctorId;
            private String doctorName;
            private String endTime;
            private Object explain;
            private int hospitalDeptId;
            private int hospitalId;
            private int id;
            private Object idNo;
            private Object insuranceCardNo;
            private int isDelete;
            private int isRepeat;
            private int meetingRoomId;
            private int oldId;
            private int oldStageId;
            private int operatorId;
            private int orderBy;
            private Object patComplaints;
            private int patientId;
            private Object patientName;
            private String patientNameInfo;
            private Object phoneNumber;
            private int rejectId;
            private String repeatName;
            private String sex;
            private String stageName;
            private String startTime;
            private int typeId;
            private String typeName;
            private String updatetime;
            private String uploadId;
            private List<ButtonListBean> buttonList;
            private List<UploadUrlBean> uploadUrl;
            private int businessId;

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
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

            public int getDiagnosisMode() {
                return diagnosisMode;
            }

            public void setDiagnosisMode(int diagnosisMode) {
                this.diagnosisMode = diagnosisMode;
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

            public Object getPatientName() {
                return patientName;
            }

            public void setPatientName(Object patientName) {
                this.patientName = patientName;
            }

            public String getPatientNameInfo() {
                return patientNameInfo;
            }

            public void setPatientNameInfo(String patientNameInfo) {
                this.patientNameInfo = patientNameInfo;
            }

            public Object getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(Object phoneNumber) {
                this.phoneNumber = phoneNumber;
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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
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

            public List<ButtonListBean> getButtonList() {
                return buttonList;
            }

            public void setButtonList(List<ButtonListBean> buttonList) {
                this.buttonList = buttonList;
            }

            public List<UploadUrlBean> getUploadUrl() {
                return uploadUrl;
            }

            public void setUploadUrl(List<UploadUrlBean> uploadUrl) {
                this.uploadUrl = uploadUrl;
            }

            public static class ButtonListBean {
                /**
                 * buttonName : 查看详情
                 * img :
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
        }
    }
}
