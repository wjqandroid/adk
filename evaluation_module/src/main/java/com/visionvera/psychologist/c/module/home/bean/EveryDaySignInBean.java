package com.visionvera.psychologist.c.module.home.bean;

import java.util.List;

public class EveryDaySignInBean {


    /**
     * code : 0
     * errMsg : 查询成功!
     * result : [{"flag":"false","weekDate":"1"},{"flag":"false","weekDate":"2"},{"flag":"false","weekDate":"3"},{"flag":"false","weekDate":"4"},{"flag":"false","weekDate":"5"},{"flag":"false","weekDate":"6"},{"flag":"false","weekDate":"7"}]
     */

    private int code;
    private String errMsg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * flag : false
         * weekDate : 1
         */

        private String flag;
        private String weekDate;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getWeekDate() {
            return weekDate;
        }

        public void setWeekDate(String weekDate) {
            this.weekDate = weekDate;
        }
    }
}
