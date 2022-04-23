package com.visionvera.psychologist.c.module.ordertreatment.bean;

import java.io.Serializable;

/**
 * @author 刘传政
 * @date 2019-07-01 11:25
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class SubmitOrderResponseBean {

    /**
     * code : 0
     * errMsg : ok
     * result : {"appNum":"202001070008","doctorName":"林医生","startTime":"2019-08-03 08:00:00","endTime":"2019-08-03 10:00:00","diagnosisModeName":"手机远程","comments":"备注"}
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
         * appNum : 202001070008
         * doctorName : 林医生
         * startTime : 2019-08-03 08:00:00
         * endTime : 2019-08-03 10:00:00
         * diagnosisModeName : 手机远程
         * comments : 备注
         */

        private String appNum;
        private String doctorName;
        private String startTime;
        private String endTime;
        private String diagnosisModeName;
        private String comments;

        public String getAppNum() {
            return appNum;
        }

        public void setAppNum(String appNum) {
            this.appNum = appNum;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
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

        public String getDiagnosisModeName() {
            return diagnosisModeName;
        }

        public void setDiagnosisModeName(String diagnosisModeName) {
            this.diagnosisModeName = diagnosisModeName;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }
    }
}
