package com.visionvera.psychologist.c.module.counselling.bean;

public class AliPrePayRequestBean {
    //1-微信扫码支付 2-支付宝扫码支付 3-微信APP支付 4- 支付宝APP支付
    public int payMethod;
    //支付金额
    public Double totalAmount;
    //商品订单号
    public String goodsOrderNo;
    //接口安全参数值ignore | false
    public String safe;
    //若safe=ignore 可选
    public String sign;
    //付款人userId
    public String payUserId;
    //付款人姓名
    public String payUserName;

    @Override
    public String toString() {
        return "AliPrePayRequestBean{" +
                "payMethod=" + payMethod +
                ", totalAmount=" + totalAmount +
                ", goodsOrderNo='" + goodsOrderNo + '\'' +
                ", safe='" + safe + '\'' +
                ", sign='" + sign + '\'' +
                ", payUserId='" + payUserId + '\'' +
                ", payUserName='" + payUserName + '\'' +
                '}';
    }
}
