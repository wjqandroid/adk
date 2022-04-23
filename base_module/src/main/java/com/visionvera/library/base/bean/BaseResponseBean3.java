package com.visionvera.library.base.bean;

/**
 * @author 刘传政
 * @date 2019-06-03 13:42
 * QQ:1052374416
 * telephone:18501231486
 * 作用:
 * 注意事项:
 */
public class BaseResponseBean3<T> {
    /**
     * {
     * "code": 0,
     * "errMsg": "ok",
     * "result": "验证码已发送"
     * }
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

    public boolean checkDataNotNull() {
        if (result == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "BaseResponseBean3{" +
                "code=" + code +
                ", errMsg='" + errMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
