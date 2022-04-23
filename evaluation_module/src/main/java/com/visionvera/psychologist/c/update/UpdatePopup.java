package com.visionvera.psychologist.c.update;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.psychologist.c.R;

/**
 * 提示更新弹窗
 * */
public class UpdatePopup extends CenterPopupView{
    private TextView msgTv;
    private LinearLayout bottomLayout;
    private TextView cancelTv;
    private TextView confirmTv;
    //强制升级时展示的底部单独按钮
    private TextView confirmMustTv;

    private String tips;
    private boolean isForce;
    private Context mConText;
    private ResultListener listener;

    /**
     * isForce:是否强制升级
     * */
    public UpdatePopup(@NonNull Context context, String tips, boolean isForce, ResultListener listener) {
        super(context);
        this.tips = tips;
        this.isForce = isForce;
        this.mConText = context;
        this.listener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.update_pop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        msgTv = findViewById(R.id.update_popup_msg);
        bottomLayout = findViewById(R.id.update_popup_bottom_layout);
        cancelTv = findViewById(R.id.update_popup_bottom_cancel);
        confirmTv = findViewById(R.id.update_popup_bottom_confirm);
        confirmMustTv = findViewById(R.id.update_popup_bottom_must_tv);

        if(!TextUtils.isEmpty(tips)){
            msgTv.setVisibility(VISIBLE);
            msgTv.setText(tips);
        }else {
            msgTv.setVisibility(GONE);
        }

        if(isForce){ //强制更新
            confirmMustTv.setVisibility(VISIBLE);
            bottomLayout.setVisibility(GONE);
        }else {
            confirmMustTv.setVisibility(GONE);
            bottomLayout.setVisibility(VISIBLE);
        }

        cancelTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onCancel();
                }
                dismiss();
            }
        });

        confirmTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onConfirm();
                }
                dismiss();
            }
        });

        confirmMustTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onConfirm();
                }
                dismiss();
            }
        });

    }

    public interface ResultListener {
        void onConfirm();

        void onCancel();
    }

}
