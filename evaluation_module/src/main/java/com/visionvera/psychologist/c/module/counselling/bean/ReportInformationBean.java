package com.visionvera.psychologist.c.module.counselling.bean;

/**
 * @Classname:ReportInformationBean
 * @author:haohuizhao
 * @Date:2021/11/17 15:37
 * @Version：v1.0
 * @describe： 咨询/诊疗上报操作信息
 * <p>
 * counselingapi/businessApplication/command
 *返回的对象
 *
 */


public class ReportInformationBean {


    //    id	Integer	Y	主键ID
    public int id;
    //appNum String	y	订单编号
    public String appNum;
    //customerCall  int	N  用户发起聊天 0|未发起，1|已发起
    public int customerCall;
    //serverCall  int	n  服务人员发起聊天 0|未发起，1|已发起
    public int serverCall;
    //customerAnswer  int	n用户接听 0|否，1|是
    public int customerAnswer;
    //serverAnswer  int	n服务方接听 0|否，1|是
    public int serverAnswer;
    //customerConfirm int	n 用户确认 0|未确认，1|已确认
    public int customerConfirm;
    //serverConfirm int	n 服务人员确认 0|未确认，1|已确认
    public int serverConfirm;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }

    public int getCustomerCall() {
        return customerCall;
    }

    public void setCustomerCall(int customerCall) {
        this.customerCall = customerCall;
    }

    public int getServerCall() {
        return serverCall;
    }

    public void setServerCall(int serverCall) {
        this.serverCall = serverCall;
    }

    public int getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(int customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    public int getServerAnswer() {
        return serverAnswer;
    }

    public void setServerAnswer(int serverAnswer) {
        this.serverAnswer = serverAnswer;
    }

    public int getCustomerConfirm() {
        return customerConfirm;
    }

    public void setCustomerConfirm(int customerConfirm) {
        this.customerConfirm = customerConfirm;
    }

    public int getServerConfirm() {
        return serverConfirm;
    }

    public void setServerConfirm(int serverConfirm) {
        this.serverConfirm = serverConfirm;
    }
}
