package com.visionvera.psychologist.c.module.ordertreatment.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author lilongfeng
 * date 2019/8/6
 * 医生详情页面的返回数据
 */

public class DoctorDetailBean implements Serializable {
    /**
     * code : 0
     * errMsg : 成功
     * result : {"certificateUrl":"","consultingFee":"-0.01","consultingFeeVideo":"-0.01","consultingFeeVoice":"-0.01","consultingfeeStatus":0,"consultingfeevideoStatus":0,"consultingfeevoiceStatus":0,"departmentId":3648,"departmentName":"银行科","doctorIntroduction":"","drawProportion":"","email":"","frontOfCardUrl":"","hospitalId":577,"hospitalName":"新格林耐特网络医院","id":1462,"identityNo":"","imageUrl":"","introduction":"","isExpert":0,"isExpertStr":"","labelIds":null,"labels":[],"mobile":"","qualificationUrl":"","scheduleId":0,"staffId":0,"staffIntroduction":"","titleName":"副主任医师","userId":2488,"username":"陈博"}
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
         * certificateUrl :
         * consultingFee : -0.01
         * consultingFeeVideo : -0.01
         * consultingFeeVoice : -0.01
         * consultingfeeStatus : 0
         * consultingfeevideoStatus : 0
         * consultingfeevoiceStatus : 0
         * departmentId : 3648
         * departmentName : 银行科
         * doctorIntroduction :
         * drawProportion :
         * email :
         * frontOfCardUrl :
         * hospitalId : 577
         * hospitalName : 新格林耐特网络医院
         * id : 1462
         * identityNo :
         * imageUrl :
         * introduction :
         * isExpert : 0
         * isExpertStr :
         * labelIds : null
         * labels : []
         * mobile :
         * qualificationUrl :
         * scheduleId : 0
         * staffId : 0
         * staffIntroduction :
         * titleName : 副主任医师
         * userId : 2488
         * username : 陈博
         */

        private String certificateUrl;
        public String consultingFee = "";
        public String consultingFeeVideo = "";
        public String consultingFeeVoice = "";
        private int consultingfeeStatus;
        private int consultingfeevideoStatus;
        private int consultingfeevoiceStatus;
        private int departmentId;
        private String departmentName;
        private String doctorIntroduction;
        private String drawProportion;
        private String email;
        private String frontOfCardUrl;
        private int hospitalId;
        private String hospitalName;
        private int id;
        private String identityNo;
        private String imageUrl;
        private String introduction;
        private int isExpert;
        private String isExpertStr;
        private Object labelIds;
        private String mobile;
        private String qualificationUrl;
        private int scheduleId;
        private int staffId;
        private String staffIntroduction;
        private String titleName;
        private int userId;
        private String username;

        private List<LabelsBean> labels;

        public String getCertificateUrl() {
            return certificateUrl;
        }

        public void setCertificateUrl(String certificateUrl) {
            this.certificateUrl = certificateUrl;
        }

        public String getConsultingFee() {
            return consultingFee;
        }

        public void setConsultingFee(String consultingFee) {
            this.consultingFee = consultingFee;
        }

        public String getConsultingFeeVideo() {
            return consultingFeeVideo;
        }

        public void setConsultingFeeVideo(String consultingFeeVideo) {
            this.consultingFeeVideo = consultingFeeVideo;
        }

        public String getConsultingFeeVoice() {
            return consultingFeeVoice;
        }

        public void setConsultingFeeVoice(String consultingFeeVoice) {
            this.consultingFeeVoice = consultingFeeVoice;
        }

        public int getConsultingfeeStatus() {
            return consultingfeeStatus;
        }

        public void setConsultingfeeStatus(int consultingfeeStatus) {
            this.consultingfeeStatus = consultingfeeStatus;
        }

        public int getConsultingfeevideoStatus() {
            return consultingfeevideoStatus;
        }

        public void setConsultingfeevideoStatus(int consultingfeevideoStatus) {
            this.consultingfeevideoStatus = consultingfeevideoStatus;
        }

        public int getConsultingfeevoiceStatus() {
            return consultingfeevoiceStatus;
        }

        public void setConsultingfeevoiceStatus(int consultingfeevoiceStatus) {
            this.consultingfeevoiceStatus = consultingfeevoiceStatus;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getDoctorIntroduction() {
            return doctorIntroduction;
        }

        public void setDoctorIntroduction(String doctorIntroduction) {
            this.doctorIntroduction = doctorIntroduction;
        }

        public String getDrawProportion() {
            return drawProportion;
        }

        public void setDrawProportion(String drawProportion) {
            this.drawProportion = drawProportion;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFrontOfCardUrl() {
            return frontOfCardUrl;
        }

        public void setFrontOfCardUrl(String frontOfCardUrl) {
            this.frontOfCardUrl = frontOfCardUrl;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdentityNo() {
            return identityNo;
        }

        public void setIdentityNo(String identityNo) {
            this.identityNo = identityNo;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getIsExpert() {
            return isExpert;
        }

        public void setIsExpert(int isExpert) {
            this.isExpert = isExpert;
        }

        public String getIsExpertStr() {
            return isExpertStr;
        }

        public void setIsExpertStr(String isExpertStr) {
            this.isExpertStr = isExpertStr;
        }

        public Object getLabelIds() {
            return labelIds;
        }

        public void setLabelIds(Object labelIds) {
            this.labelIds = labelIds;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getQualificationUrl() {
            return qualificationUrl;
        }

        public void setQualificationUrl(String qualificationUrl) {
            this.qualificationUrl = qualificationUrl;
        }

        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public String getStaffIntroduction() {
            return staffIntroduction;
        }

        public void setStaffIntroduction(String staffIntroduction) {
            this.staffIntroduction = staffIntroduction;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }


        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public class LabelsBean {
            /**
             * lableName : 婚恋
             * id : 6
             */

            private String lableName;
            private int id;

            public String getLableName() {
                return lableName;
            }

            public void setLableName(String lableName) {
                this.lableName = lableName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }


}
