package com.visionvera.library.base.bean;

/**
 * @author 刘传政
 * @date 2019-06-03 13:42
 * QQ:1052374416
 * telephone:18501231486
 * 作用:医视联平台风格的bean
 * 注意事项:
 */
public class BaseResponseBean2 {

    /**
     * code : 0
     * errMsg : ok
     * result : null
     */

    private int code;
    private String errMsg;
    private Object result;

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
