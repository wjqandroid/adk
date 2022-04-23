package com.visionvera.psychologist.c.widget.popup;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.psychologist.c.R;

/**
* author:lilongfeng
* date:2019/12/20
* 描述:签到弹框
*/

public class SignInPopup extends CenterPopupView {

    private Context mContext;
    private final int mSuccessImgId;
    RotateAnimation rotateAnimation;

    public SignInPopup(@NonNull Context context,int successImgId) {
        super(context);
        mContext=context;
        mSuccessImgId = successImgId;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_popup_sign_in;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.evaluation_module_sign_in_close).setOnClickListener(v -> dismiss());

        ImageView bg_iv=findViewById(R.id.evaluation_module_signin_success_bg_iv);
        ImageView success_iv=findViewById(R.id.evaluation_module_signin_success_iv);

        success_iv.setImageResource(mSuccessImgId);


        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.evaluation_module_sign_in_bg);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        bg_iv.startAnimation(rotateAnimation);

    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        //停止动画
        if (rotateAnimation != null) {
            rotateAnimation.cancel();
        }
    }
}
