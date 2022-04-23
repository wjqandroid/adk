package com.visionvera.psychologist.c.module.counselling.bean;

import java.util.List;

/**
 * @Desc
 * @Author yemh
 * @Date 2019-12-26 16:51
 */
public class SuggestListBean {
    private int code;
    private int count;
    private String errMsg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
        private int patientNum;
        private List<String> lableName;
        private String titleName;
        private int psyInfoId;
        private String dictName;
        private String hospitalName;
        private String userName;
        private String url;
        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getPatientNum() {
            return patientNum;
        }

        public void setPatientNum(int patientNum) {
            this.patientNum = patientNum;
        }

        public List<String> getLableName() {
            return lableName;
        }

        public void setLableName(List<String> lableName) {
            this.lableName = lableName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public int getPsyInfoId() {
            return psyInfoId;
        }

        public void setPsyInfoId(int psyInfoId) {
            this.psyInfoId = psyInfoId;
        }

        public String getDictName() {
            return dictName;
        }

        public void setDictName(String dictName) {
            this.dictName = dictName;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }
}
