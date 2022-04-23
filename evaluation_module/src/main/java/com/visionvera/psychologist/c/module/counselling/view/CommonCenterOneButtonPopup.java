package com.visionvera.psychologist.c.module.counselling.view;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.psychologist.c.R;

/**
 * @author: 刘传政
 * @date: 2020/9/1 09:57
 * QQ:1052374416
 * 作用:单按钮通知
 * 注意事项:
 */
public class CommonCenterOneButtonPopup extends CenterPopupView {

    private String content;
    private String buttontext;
    private CenterPopupOneButtonListener mCenterPopupOneButtonListener;

    public CommonCenterOneButtonPopup(@NonNull Context context, String content, String buttontext, CenterPopupOneButtonListener mCenterPopupOneButtonListener) {
        super(context);
        this.content = content;
        this.buttontext = buttontext;
        this.mCenterPopupOneButtonListener = mCenterPopupOneButtonListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.common_center_one_button_pop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        TextView contentTV = findViewById(R.id.tv_content);
        TextView btnTV = findViewById(R.id.tv_ok);

        contentTV.setText(content);
        btnTV.setText(buttontext);

        btnTV.setOnClickListener(v -> {
            mCenterPopupOneButtonListener.OneButtonListener();
            dismiss();
        });

    }

    public interface CenterPopupOneButtonListener {
        void OneButtonListener();
    }

}
