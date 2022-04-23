package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

public class EvaluationResultBean<T> {
    /**
     * code : 0
     * errMsg : ok
     * result : null
     */

    private int code;
    private String errMsg;
    private T result;


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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }




}
