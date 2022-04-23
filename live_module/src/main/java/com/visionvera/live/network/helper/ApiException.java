package com.visionvera.live.network.helper;/** * @Desc 自定义异常 * * @Author yemh * @Date 2019/4/15 17:10 */public class ApiException extends Exception {    private int code;//错误码    private String msg;//错误信息    public ApiException(Throwable throwable, int code) {        super(throwable);        this.code = code;    }    public ApiException(int code, String msg) {        this.code = code;        this.msg = msg;    }    public int getCode() {        return code;    }    public void setCode(int code) {        this.code = code;    }    public String getMsg() {        return msg;    }    public void setMsg(String msg) {        this.msg = msg;    }    // TODO: 2018/7/6 重写getMessage方法   与现有MVP架构  IBaseView 中showError对应    @Override    public String getMessage() {        return msg;    }}