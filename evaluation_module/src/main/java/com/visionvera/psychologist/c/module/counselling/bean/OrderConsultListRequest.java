package com.visionvera.psychologist.c.module.counselling.bean;

public class OrderConsultListRequest {

    /**
     * appStatus :
     * orderBy : 2
     * pageIndex : 1
     * pageSize : 20
     * type : apply
     * appFrom : 2
     */
    //客户端类型：2|用户端，3|服务端
    public Integer clientType;
    //业务类型：16|诊疗，23|咨询
    public Integer typeId;

    private String appStatus;
    private String orderBy;
    private int pageIndex;
    private int pageSize;
    private String type;
    private String appFrom;

    public OrderConsultListRequest(String type, String appFrom) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppFrom() {
        return appFrom;
    }

    public void setAppFrom(String appFrom) {
        this.appFrom = appFrom;
    }
}
