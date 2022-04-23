package com.visionvera.psychologist.c.module.ordertreatment.bean;


import java.io.Serializable;

/**
 * @Classname:EvaluateListRequest
 * @author:haohuizhao
 * @Date:2021/12/07 15:37
 * @Version：v1.0
 * @describe：
 * 二期  评价列表
 */

public class EvaluateListRequest implements Serializable {


    //唯一订单号
    private String id;
    //服务人员用户唯一id
    private int serviceUserId;
    //评价满意度1一星2二星3三星4四星5五星
    private int evaluateSatisfaction;
    //排序字段1正序2倒序 不传值默认正序
    private int orderBy;
    private String serviceNumber;

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getServiceUserId() {
        return serviceUserId;
    }

    public void setServiceUserId(int serviceUserId) {
        this.serviceUserId = serviceUserId;
    }

    public int getEvaluateSatisfaction() {
        return evaluateSatisfaction;
    }

    public void setEvaluateSatisfaction(int evaluateSatisfaction) {
        this.evaluateSatisfaction = evaluateSatisfaction;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }
}
