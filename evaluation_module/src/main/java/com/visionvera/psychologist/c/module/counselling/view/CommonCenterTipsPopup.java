package com.visionvera.psychologist.c.module.counselling.view;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.psychologist.c.R;

/**
 * @author: 刘传政
 * @date: 2019-06-19 16:55
 * QQ:1052374416
 * 作用:通用的中间提示确认提示框.中间的提示文字自定义
 * 注意事项:
 */
public class CommonCenterTipsPopup extends CenterPopupView {
    TextView tv_left;
    TextView tv_right;
    TextView tv_content;
    //弹窗中间的提示文字
    private String tips = "";
    private String left = "取消";
    private String right = "确认";
    private ResultListener listener;

    public CommonCenterTipsPopup(@NonNull Context context, String tips, ResultListener listener) {
        super(context);
        this.tips = tips;
        this.listener = listener;
    }

    public CommonCenterTipsPopup(@NonNull Context context, String tips, String left, String right, ResultListener listener) {
        super(context);
        this.tips = tips;
        this.left = left;
        this.right = right;
        this.listener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.common_center_tips_pop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);
        tv_content = findViewById(R.id.tv_content);
        tv_content.setText(tips);
        tv_left.setText(left);
        tv_right.setText(right);
        tv_left.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                //左边
                listener.onCkecked("left");
            }
        });
        tv_right.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                //右边
                listener.onCkecked("right");
            }
        });
        if (listener != null) {
            listener.onCreated();
        }
    }

    public TextView getTv_content() {
        return tv_content;
    }

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {

    }


    public interface ResultListener {
        /**
         * @param action left 或 right
         */
        void onCkecked(String action);

        void onCreated();
    }

}