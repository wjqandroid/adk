package com.visionvera.psychologist.c.widget.tencentIm.utils;

import android.os.CountDownTimer;

/**
 * 定时器，计算何时可以聊天，何时不能聊天。
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
