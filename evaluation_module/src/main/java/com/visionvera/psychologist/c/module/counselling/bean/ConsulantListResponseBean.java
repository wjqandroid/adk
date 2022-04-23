package com.visionvera.psychologist.c.module.counselling.bean;

import java.util.List;

/**
 * @Desc
 *
 * @Author yemh
 * @Date 2019-12-26 16:51
 *
 */
public class ConsulantListResponseBean {

    private int code;
    private String errMsg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private String isExpert;
        private String titleName;
        private List<String> lableName;
        private String hospitalName;
        private int psyInfoId;
        private int scheduleId;
        private int userId;
        private String username;
        private String sex;
        private String cardId;
        private String mobile;
        private int patientNum;
        private String dictName;
        private String url;

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public int getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
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

        public List<String> getLableName() {
            return lableName;
        }

        public void setLableName(List<String> lableName) {
            this.lableName = lableName;
        }

        public String getIsExpert() {
            return isExpert;
        }

        public void setIsExpert(String isExpert) {
            this.isExpert = isExpert;
        }

        public int getPsyInfoId() {
            return psyInfoId;
        }

        public void setPsyInfoId(int psyInfoId) {
            this.psyInfoId = psyInfoId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getPatientNum() {
            return patientNum;
        }

        public void setPatientNum(int patientNum) {
            this.patientNum = patientNum;
        }

        public String getDictName() {
            return dictName;
        }

        public void setDictName(String dictName) {
            this.dictName = dictName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
