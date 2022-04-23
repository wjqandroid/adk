package com.visionvera.psychologist.c.module.counselling.bean;

public class AliPrePayResponseBean {

    /**
     * code : 0
     * errMsg : 支付宝APP支付下单正常...
     * result : {"result":"alipay_root_cert_sn=687b59193f3f462dd5336e5abf83c5d8_02941eef3187dddf3d3b83462e1dfcf6&alipay_sdk=alipay-sdk-java-4.8.103.ALL&app_cert_sn=cc35b267d7f3944c908243cac762598c&app_id=2021001185606251&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E6%9C%8D%E5%8A%A11%E6%AC%A1%E5%85%B10.01%E5%85%83%22%2C%22out_trade_no%22%3A%22PAY2009251734080055%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9DAPP%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%225m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F58.30.9.142%3A34220%2Fgateway%2Fpayapi%2Falipay%2FalipayAppCallBackNotify&sign=G0Ud%2B6RaD6IuW0O9s1JcshCt5vImO%2FbfBogzQ4S8jsABT5%2BXoApACfBRc%2FcoP2D38HldMJY0dT%2BRm0IxHhYYIvi45lwWl7UmM3116HEmXfVrxEslx3Ee2Sd%2Fb%2B%2FTDLBym1DAw74z5QYioleZex3qT31u5LzfpR0Xj22v8WoWc8ukiChb9IrUMdzPVmhzRYb6fe6qv%2Ff3EeVMJVMrh%2Fl9zPvf6NSm8EcfHsBms2vE%2FsNLmW6IPv294hw9J9RLSuBJCP3hHh89T%2BmEfPCyL8xaeOB310qdLEn0yAGNeAYbJ%2BgiQKXfNxECXpgsu5YdEqBLyl9I6mK%2FA45uulySMV6onQ%3D%3D&sign_type=RSA2&timestamp=2020-09-25+17%3A34%3A08&version=1.0","code":"0000","message":"支付宝APP支付下单正常...","outOrderNo":"PAY2009251734080055"}
     */

    public int code;
    public String errMsg;
    public ResultBean result;

    public static class ResultBean {
        /**
         * result : alipay_root_cert_sn=687b59193f3f462dd5336e5abf83c5d8_02941eef3187dddf3d3b83462e1dfcf6&alipay_sdk=alipay-sdk-java-4.8.103.ALL&app_cert_sn=cc35b267d7f3944c908243cac762598c&app_id=2021001185606251&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E6%9C%8D%E5%8A%A11%E6%AC%A1%E5%85%B10.01%E5%85%83%22%2C%22out_trade_no%22%3A%22PAY2009251734080055%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9DAPP%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%225m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F58.30.9.142%3A34220%2Fgateway%2Fpayapi%2Falipay%2FalipayAppCallBackNotify&sign=G0Ud%2B6RaD6IuW0O9s1JcshCt5vImO%2FbfBogzQ4S8jsABT5%2BXoApACfBRc%2FcoP2D38HldMJY0dT%2BRm0IxHhYYIvi45lwWl7UmM3116HEmXfVrxEslx3Ee2Sd%2Fb%2B%2FTDLBym1DAw74z5QYioleZex3qT31u5LzfpR0Xj22v8WoWc8ukiChb9IrUMdzPVmhzRYb6fe6qv%2Ff3EeVMJVMrh%2Fl9zPvf6NSm8EcfHsBms2vE%2FsNLmW6IPv294hw9J9RLSuBJCP3hHh89T%2BmEfPCyL8xaeOB310qdLEn0yAGNeAYbJ%2BgiQKXfNxECXpgsu5YdEqBLyl9I6mK%2FA45uulySMV6onQ%3D%3D&sign_type=RSA2&timestamp=2020-09-25+17%3A34%3A08&version=1.0
         * code : 0000
         * message : 支付宝APP支付下单正常...
         * outOrderNo : PAY2009251734080055
         */

        public String result;
        public String code;
        public String message;
        public String outOrderNo;
    }
}
