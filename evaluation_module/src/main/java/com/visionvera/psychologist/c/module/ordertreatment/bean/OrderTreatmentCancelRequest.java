package com.visionvera.psychologist.c.module.ordertreatment.bean;

public class OrderTreatmentCancelRequest {

    //id	Integer	Y	主键ID
    //appStatus
    //Byte	Y	修改后的状态
    //oldStageId
    //Byte	y	修改前的状态
    //explain
    //String	N	驳回原因
    public Integer id;
    public String appStatus;
    public String oldStageId;
    public String explain;

    public OrderTreatmentCancelRequest(Integer id, String appStatus, String oldStageId) {
        this.id = id;
        this.appStatus = appStatus;
        this.oldStageId = oldStageId;
    }
}
