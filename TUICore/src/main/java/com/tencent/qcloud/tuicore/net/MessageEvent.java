package com.tencent.qcloud.tuicore.net;

/**
 * @Classname:MessageEvent
 * @author:haohuizhao
 * @Date:2021/12/5 13:42
 * @Version：v1.0
 * @describe：
 */
public class MessageEvent {
    private String message;


    public MessageEvent(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}