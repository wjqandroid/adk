package com.visionvera.psychologist.c.module.counselling.bean;

public class NewOrderconsultBean {


    /**
     * code : 0
     * errMsg : 成功
     * result : {"appNum":"8eaae581adf442c184d97cd6ae33c5f8","talkCureId":1067,"createTime":1600660694848}
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
         * appNum : 8eaae581adf442c184d97cd6ae33c5f8
         * talkCureId : 1067
         * createTime : 1600660694848
         */

        private String appNum;
        private int talkCureId;
        private String createTime;

        public String getAppNum() {
            return appNum;
        }

        public void setAppNum(String appNum) {
            this.appNum = appNum;
        }

        public int getTalkCureId() {
            return talkCureId;
        }

        public void setTalkCureId(int talkCureId) {
            this.talkCureId = talkCureId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
