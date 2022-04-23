package com.visionvera.psychologist.c.module.home.bean;

import java.util.List;

public class MeetingRoomBean {

    /**
     * code : 0
     * errMsg : ok
     * result : [{"areaCode":"120101000000","hospitalId":577,"name":"心理咨询会议室","id":243,"deviceCode":"24","deviceId":100005},{"areaCode":"120101000000","hospitalId":556,"name":"广西会议室","id":236,"deviceCode":"25","deviceId":100006}]
     */

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
        /**
         * areaCode : 120101000000
         * hospitalId : 577
         * name : 心理咨询会议室
         * id : 243
         * deviceCode : 24
         * deviceId : 100005
         */

        private String areaCode;
        private int hospitalId;
        private String name;
        private int id;
        private String deviceCode;
        private int deviceId;

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
