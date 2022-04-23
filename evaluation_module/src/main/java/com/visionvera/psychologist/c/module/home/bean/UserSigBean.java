package com.visionvera.psychologist.c.module.home.bean;

public class UserSigBean {


    /**
     * code : 0
     * errMsg : 获取成功
     * result : {"userSig":"eJyrVgrxCdZLrSjILEpVsjI2MwABHbBgWWqRkpWSkZ6BEoRfnJKdWFCQmaJkZWhiYGBsaGBpaAmRyUxJzSvJTMsEazCGKc9MB-LcLV3DozyTXS2NonJzotIis50CjZwMDS3dPIxL0ko8klPK9f2dC-RzEy1CbaEaSzJzgU4xNLUwNDA1tzQzrwUA6bcuew__"}
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
        /*  "sign": "eJyrVgrxCdZLrSjILEpVsjIytTQyMDDQAQuWpRYpWSkZ6RkoQfjFKdmJBQWZKUpWhiYGBsZGpqYmRhCZzJTUvJLMtEywBktzC5iGzHQgP8QiMy0nxcg-1DDKySCkMsncMko7oCwpw9EgO7I4PzPc37Mk0CCqxNEtP9AWqrEkMxfoGEMzY0sTM3NLQ8NaAGJfL6U_",
        "txPrefix": "mhsptrunkdev",
        "txLoginId": "mhsptrunkdev978"*/
        private String sign;
        private String txPrefix;
        private String txLoginId;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTxPrefix() {
            return txPrefix;
        }

        public void setTxPrefix(String txPrefix) {
            this.txPrefix = txPrefix;
        }

        public String getTxLoginId() {
            return txLoginId;
        }

        public void setTxLoginId(String txLoginId) {
            this.txLoginId = txLoginId;
        }
    }
}
