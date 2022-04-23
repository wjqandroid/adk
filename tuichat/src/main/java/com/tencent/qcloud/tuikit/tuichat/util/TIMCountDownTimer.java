package com.tencent.qcloud.tuikit.tuichat.util;

import android.os.CountDownTimer;

/**
 * 定时器，计算何时可以聊天，何时不能聊天。
 * 自定义工具类用于结束服务
 */
public class TIMCountDownTimer extends CountDownTimer {

    public TIMCountDownTimer(long millisInFuture){
        super(millisInFuture, 60 * 1000);
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }
}
