package com.visionvera.psychologist.c.module.ordertreatment.bean;

public class OrderTreatmentListRequest {


    //客户端类型：2|用户端，3|服务端
    public Integer clientType;
    //业务类型：16|诊疗，23|咨询
    public Integer typeId;


    //"apply":申请数据列表"receiver"受邀数据列表
    public String type = "apply";
    //订单状态（14：待接诊 15：已完成 16：已作废  5：已驳回 7：已取消）
    public String appStatus;
    ///排序字段 1 创建时间正序 非1 创建时间倒序
    public String orderBy;
    //患者名搜索
    public String patientNameInfo;
    //患者ID
    public Integer patientId;
    //开始时间
    public String starTime;
    //结束时间
    public String endTime;
    //来源:1（B端)， 2(C端)
    public int appFrom;

    public int pageIndex;
    public int pageSize;

    public OrderTreatmentListRequest(String type, int appFrom) {
        this.type = type;
        this.appFrom = appFrom;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getPatientNameInfo() {
        return patientNameInfo;
    }

    public void setPatientNameInfo(String patientNameInfo) {
        this.patientNameInfo = patientNameInfo;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getAppFrom() {
        return appFrom;
    }

    public void setAppFrom(int appFrom) {
        this.appFrom = appFrom;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
