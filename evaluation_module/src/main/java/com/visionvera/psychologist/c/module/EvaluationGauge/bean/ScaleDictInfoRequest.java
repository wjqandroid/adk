package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

public class ScaleDictInfoRequest {

    /**
     * scaleId : 1
     * pushRecordId : null
     */

    private int scaleId;
    private String pushRecordId;
    public String orderNum;

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    public String getPushRecordId() {
        return pushRecordId;
    }

    public void setPushRecordId(String pushRecordId) {
        this.pushRecordId = pushRecordId;
    }
}
