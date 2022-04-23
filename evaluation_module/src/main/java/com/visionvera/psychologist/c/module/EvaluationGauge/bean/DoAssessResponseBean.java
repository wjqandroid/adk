package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.io.Serializable;

/**
 * 提交测评返回Bean
 */
public class DoAssessResponseBean implements Serializable {
    /*{"createTime":1638235594539,
            "endTime":1638235598000,
            "id":10837,
            "isDelete":0,
            "pushRecordId":0,
            "resultId":2781,
            "scaleId":1,
            "serialNumber":"FYYZPLB-20211130092634",
            "startTime":1638235592000,
            "status":1,
            "type":2,
            "usedTime":6,
            "userId":0}}*/

    public String createTime;
    public String endTime;
    public String id;
    public String isDelete;
    public String pushRecordId;
    public String resultId;
    /**
     * 量表id
     */
    public String scaleId;
    public String serialNumber;
    public String startTime;
    public String status;
    public String type;
    public String usedTime;
    public String userId;

    @Override
    public String toString() {
        return "DoAssessResponseBean{" +
                "createTime='" + createTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", id='" + id + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", pushRecordId='" + pushRecordId + '\'' +
                ", resultId='" + resultId + '\'' +
                ", scaleId='" + scaleId + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", startTime='" + startTime + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", usedTime='" + usedTime + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
