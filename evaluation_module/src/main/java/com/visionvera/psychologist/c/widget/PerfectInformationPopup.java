package com.visionvera.psychologist.c.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.psychologist.c.R;

/**
* author:lilongfeng
* date:2019/11/14
* 描述:完善个人信息dialog
*/

public class PerfectInformationPopup extends CenterPopupView {

    private ToPerfectInformationListener mToPerfectInformationListener;
    private String mContent;
    public PerfectInformationPopup(@NonNull Context context,String content,ToPerfectInformationListener toPerfectInformationListener) {
        super(context);
        mContent=content;
        mToPerfectInformationListener=toPerfectInformationListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_perfect_information_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        TextView notice_tv=findViewById(R.id.perfect_information_top_notice_tv);
        notice_tv.setText(mContent);
        findViewById(R.id.evaluation_module_perfect_information_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        findViewById(R.id.to_perfect_information_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mToPerfectInformationListener.perfectInformation();
            }
        });
    }

    public interface ToPerfectInformationListener{
        void perfectInformation();
    }
}
