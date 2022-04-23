package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.util.List;

public class CommitRequest {

    /**
     * scaleId : 1
     * startTime : 2019-6-5-11:19:20
     * endTime : 2019-6-5-11:29:20
     * group : SDSAS
     * type : 2
     * dataArray : [{"aid":1,"qid":1,"isForward":1,"answerStr":""},{"aid":2,"qid":2,"isForward":2,"answerStr":""},{"aid":3,"qid":3,"isForward":1,"answerStr":""},{"aid":4,"qid":4,"isForward":2,"answerStr":""}]
     */

    private int scaleId;
    private String startTime;
    private String endTime;
    private String group;

    private List<DataArrayBean> dataArray;


    /**
     * 量表类型:1必答|2自选
     * 当为必答题，则pushRecordId 和 answerId 必传。
     * 当为自选题，则pushRecordId 和 answerId 不必传。
     */
    private int type;
    private int pushRecordId;
    private int answerId;
    /**
     * MacId 用于后台统计游客模式下提交的次数
     */
    public String userMacId;
    /**
     * 获取详情时得到的订单id 答完题提交时要传给后台
     */
    public String orderNum;

    public int getPushRecordId() {
        return pushRecordId;
    }

    public void setPushRecordId(int pushRecordId) {
        this.pushRecordId = pushRecordId;
    }



    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<DataArrayBean> getDataArray() {
        return dataArray;
    }

    public void setDataArray(List<DataArrayBean> dataArray) {
        this.dataArray = dataArray;
    }

    public static class DataArrayBean {
        /**
         * aid : 1
         * qid : 1
         * isForward : 1
         * answerStr :
         */

        private int aid;
        private int qid;
        private int isForward;
        private String answerStr;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getQid() {
            return qid;
        }

        public void setQid(int qid) {
            this.qid = qid;
        }

        public int getIsForward() {
            return isForward;
        }

        public void setIsForward(int isForward) {
            this.isForward = isForward;
        }

        public String getAnswerStr() {
            return answerStr;
        }

        public void setAnswerStr(String answerStr) {
            this.answerStr = answerStr;
        }
    }
}
