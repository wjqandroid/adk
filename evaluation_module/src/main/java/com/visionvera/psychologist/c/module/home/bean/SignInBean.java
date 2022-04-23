package com.visionvera.psychologist.c.module.home.bean;

public class SignInBean {

    /**
     * code : 0
     * errMsg : 打卡成功！
     * result : {"updateNum":3}
     */

    private int code;
    private String errMsg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * updateNum : 3
         */

        private int updateNum;

        public int getUpdateNum() {
            return updateNum;
        }

        public void setUpdateNum(int updateNum) {
            this.updateNum = updateNum;
        }
    }
}
