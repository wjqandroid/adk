package com.visionvera.psychologist.c.module.usercenter.bean;

public class UserBasicInfoBean {

    /**
     * code : 0
     * errMsg : ok
     * result : {"nickname":"","benasNums":""}
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
         * nickname :
         * benasNums :
         */

        private String nickname;
        private String benasNums;
        private String photoUrl;

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getBenasNums() {
            return benasNums;
        }

        public void setBenasNums(String benasNums) {
            this.benasNums = benasNums;
        }
    }
}
