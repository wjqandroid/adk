package com.visionvera.psychologist.c.eventbus;

public class PayEventBus {
    //支付状态  0完成 -1 失败   -2 取消
    public int status = 0;
    //支付类型  0支付宝  1 微信
    public int type = 0;
}
