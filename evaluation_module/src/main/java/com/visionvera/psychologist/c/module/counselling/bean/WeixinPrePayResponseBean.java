package com.visionvera.psychologist.c.module.counselling.bean;

import com.google.gson.annotations.SerializedName;

public class WeixinPrePayResponseBean {

    /**
     * code : 0
     * errMsg : 微信APP支付下单成功...
     * result : {"package":"Sign=WXPay","code":"0000","appid":"wx0289f4899915a7de","sign":"7C9CA0DA2EAC78F49B3E20133512ACEE","partnerid":"1532360841","prepayid":"wx271032031007148ffff16cfd73f3fc0000","message":"微信APP支付下单成功...","outOrderNo":"PAY2009271032020083","noncestr":"1f2b325dcdaf12a68ed498273206d263","timestamp":"1601173923"}
     */

    public int code;
    public String errMsg;
    public ResultBean result;

    public static class ResultBean {
        /**
         * package : Sign=WXPay
         * code : 0000
         * appid : wx0289f4899915a7de
         * sign : 7C9CA0DA2EAC78F49B3E20133512ACEE
         * partnerid : 1532360841
         * prepayid : wx271032031007148ffff16cfd73f3fc0000
         * message : 微信APP支付下单成功...
         * outOrderNo : PAY2009271032020083
         * noncestr : 1f2b325dcdaf12a68ed498273206d263
         * timestamp : 1601173923
         */

        @SerializedName("package")
        public String packageX;
        public String code;
        public String appid;
        public String sign;
        public String partnerid;
        public String prepayid;
        public String message;
        public String outOrderNo;
        public String noncestr;
        public String timestamp;
    }
}
