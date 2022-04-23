package com.visionvera.library.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.library.R;

/**
* author:lilongfeng
* date:2020/2/12
* 描述:一个按钮的中间弹框
*/
public class CenterOneButtonPopup extends CenterPopupView {

    private String title;
    private String content;
    private String buttontext;
    private CenterPopupOneButtonListener mCenterPopupOneButtonListener;

    public CenterOneButtonPopup(@NonNull Context context, String title, String content, String buttontext, CenterPopupOneButtonListener mCenterPopupOneButtonListener) {
        super(context);
        this.title = title;
        this.content = content;
        this.buttontext = buttontext;
        this.mCenterPopupOneButtonListener = mCenterPopupOneButtonListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_center_popup_one_button;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        TextView titleTV=findViewById(R.id.base_module_center_popup_one_button_title);
        TextView contentTV=findViewById(R.id.base_module_center_popup_one_button_content);
        TextView btnTV=findViewById(R.id.base_module_center_popup_one_button_btn);

        titleTV.setText(title);
        contentTV.setText(content);
        btnTV.setText(buttontext);

        btnTV.setOnClickListener(v -> {
            mCenterPopupOneButtonListener.OneButtonListener();
            dismiss();
        });

    }

    public interface CenterPopupOneButtonListener{
        void OneButtonListener();
    }

}
