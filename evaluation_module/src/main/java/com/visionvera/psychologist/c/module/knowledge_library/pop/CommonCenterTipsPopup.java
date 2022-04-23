package com.visionvera.psychologist.c.module.knowledge_library.pop;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    TextView tv_cancel;
    TextView tv_confirm;
    TextView tv_content;
    TextView tv_title;
    ImageView iv_close;

    RelativeLayout titleLayout;

    //弹窗中间的提示文字
    private String tips = "";
    private String title;
    private String left = "取消";
    private String right = "确认";
    private ResultListener listener;

    public CommonCenterTipsPopup(@NonNull Context context, String tips, ResultListener listener) {
        super(context);
        this.tips = tips;
        this.listener = listener;
    }

    public CommonCenterTipsPopup(@NonNull Context context, String tips, String title, String left, String right, ResultListener listener) {
        super(context);
        this.tips = tips;
        this.title = title;
        this.left = left;
        this.right = right;
        this.listener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.common_center_tips_pop2;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_confirm = findViewById(R.id.tv_confirm);
        iv_close = findViewById(R.id.iv_close);
        tv_content = findViewById(R.id.tv_content);
        tv_title = findViewById(R.id.tv_title);
        titleLayout = findViewById(R.id.common_center_popup_title_layout);

        tv_content.setText(tips);
        if (TextUtils.isEmpty(title)) {
            titleLayout.setVisibility(GONE);
        } else {
            titleLayout.setVisibility(VISIBLE);
            tv_title.setText(title);
        }
        tv_cancel.setText(left);
        tv_confirm.setText(right);
        tv_cancel.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                listener.onCkecked("cancel");
            }
        });
        tv_confirm.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                listener.onCkecked("confirm");
            }
        });
        iv_close.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                listener.onCkecked("close");
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
        void onCkecked(String action);

        void onCreated();
    }

}