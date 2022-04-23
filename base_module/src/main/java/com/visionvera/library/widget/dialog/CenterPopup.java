package com.visionvera.library.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.library.R;

/**
 * author:lilongfeng
 * date:2019/11/14
 * 描述:屏幕中央的弹窗
 */

public class CenterPopup extends CenterPopupView {

    private CenterPopupType centerPopupType;
    private String title;
    private String content;
    private String leftString;
    private String rightString;
    private CenterPopupLeftListener mLeftListener;
    private CenterPopupRightListener mRightListener;
    private Context mContext;


    public CenterPopup(@NonNull Context context,
                       CenterPopupType centerPopupType,
                       String title,
                       String content,
                       String leftText,
                       String rightText,
                       CenterPopupLeftListener mLeftListener,
                       CenterPopupRightListener mRightListener) {
        super(context);
        this.mContext = context;
        this.centerPopupType = centerPopupType;
        this.title = title;
        this.content = content;
        this.leftString=leftText;
        this.rightString=rightText;
        this.mLeftListener = mLeftListener;
        this.mRightListener = mRightListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_dialog_commit_confirm;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        TextView confirmTitle = findViewById(R.id.base_module_commit_confirm);
        TextView confirmNotice = findViewById(R.id.base_module_commit_confirm_notice);

        View deviceLine = findViewById(R.id.base_module_deviceline);
        View commitDevideline = findViewById(R.id.base_module_commit_devideline);


        TextView cancel = findViewById(R.id.base_module_commit_dialog_cancel);
        TextView confirm = findViewById(R.id.base_module_commit_dialog_confirm);

        confirmTitle.setText(title);
        confirmNotice.setText(content);
        cancel.setText(leftString);
        confirm.setText(rightString);
        if (centerPopupType == CenterPopupType.oneButton) {
            deviceLine.setVisibility(GONE);
            cancel.setVisibility(GONE);
            commitDevideline.setVisibility(GONE);
        } else if (centerPopupType==CenterPopupType.twoButton){
            deviceLine.setVisibility(VISIBLE);
            cancel.setVisibility(VISIBLE);
        }
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLeftListener != null) {
                    mLeftListener.onCenterPopupLeft(CenterPopup.this);
                }
            }
        });
        confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightListener != null) {
                    mRightListener.onCenterPopupRight(CenterPopup.this);
                }
            }
        });
    }

    public interface CenterPopupLeftListener {
        void onCenterPopupLeft(CenterPopup centerPopup);
    }

    public interface CenterPopupRightListener {
        void onCenterPopupRight(CenterPopup centerPopup);
    }

    public enum CenterPopupType {
        oneButton,
        twoButton
    }

}
