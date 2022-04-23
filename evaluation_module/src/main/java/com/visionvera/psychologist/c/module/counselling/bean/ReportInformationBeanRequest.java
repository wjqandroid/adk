package com.visionvera.psychologist.c.module.counselling.bean;

/**
 * @Classname:ReportInformationBean
 * @author:haohuizhao
 * @Date:2021/11/17 15:37
 * @Version：v1.0
 * @describe： 咨询/诊疗上报操作信息
 * <p>
 * counselingapi/businessApplication/command
 * 请求参数
 */


public class ReportInformationBeanRequest {


    //    id	Integer	Y	主键ID
    public int id;
    //appNum String	y	订单编号
    public String appNum;
    //customerCall  int	N  用户发起聊天 0|未发起，1|已发起
    public String customerCall;
    //customerAnswer  int	n用户接听 0|否，1|是
    public String customerAnswer;
    //customerConfirm int	n 用户确认 0|未确认，1|已确认
    public String customerConfirm;

    public String serverCall;
    public String serverAnswer;

    public String getServerCall() {
        return serverCall;
    }

    public void setServerCall(String serverCall) {
        this.serverCall = serverCall;
    }

    public String getServerAnswer() {
        return serverAnswer;
    }

    public void setServerAnswer(String serverAnswer) {
        this.serverAnswer = serverAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }

    public String getCustomerCall() {
        return customerCall;
    }

    public void setCustomerCall(String customerCall) {
        this.customerCall = customerCall;
    }

    public String getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(String customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    public String getCustomerConfirm() {
        return customerConfirm;
    }

    public void setCustomerConfirm(String customerConfirm) {
        this.customerConfirm = customerConfirm;
    }
}
