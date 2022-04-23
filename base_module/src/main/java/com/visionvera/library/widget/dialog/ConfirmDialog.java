package com.visionvera.library.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.visionvera.library.R;


/**
 * @author 刘传政
 * @date 2018/12/25 0025 16:41
 * QQ:1052374416
 * 电话:18501231486
 * 作用:通用的确认对话框
 * 注意事项:
 */
public class ConfirmDialog extends AlertDialog implements View.OnClickListener {
    public static int LEFT = 0;
    public static int RIGHT = 1;
    private TextView tv_button_left;
    private TextView tv_button_right;
    private TextView tv_title;
    private TextView tv_message;
    private OnCloseListener onCloseListener;
    private String title = "";
    private String message = "";
    private String button_left_text = "";
    private String button_right_text = "";
    private Context mContext;
    private int mLeft_text_color=R.color.COLOR_BLACK_333333;
    private int mRight_text_color=R.color.base_module_theme;

    public ConfirmDialog(Context context, String title, String message, String button_left_text, String button_right_text, OnCloseListener onCloseListener) {
        super(context);
        mContext=context;
        this.title = title;
        this.message = message;
        this.button_left_text = button_left_text;
        this.button_right_text = button_right_text;
        this.onCloseListener = onCloseListener;
    }

    public ConfirmDialog(Context context, String title, String message, String button_left_text, String button_right_text,
                         int left_text_color,int right_text_color,OnCloseListener onCloseListener) {
        super(context);
        mContext=context;
        this.title = title;
        this.message = message;
        this.button_left_text = button_left_text;
        this.button_right_text = button_right_text;
        this.mLeft_text_color=left_text_color;
        this.mRight_text_color=right_text_color;
        this.onCloseListener = onCloseListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        initView();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_button_left = (TextView) findViewById(R.id.tv_button_left);
        tv_button_right = (TextView) findViewById(R.id.tv_button_right);
        tv_button_left.setOnClickListener(this);
        tv_button_right.setOnClickListener(this);
        tv_title.setText(title);
        tv_message.setText(message);
        if (!"".equals(button_left_text)) {
            tv_button_left.setText(button_left_text);
        }
        if (!"".equals(button_right_text)) {
            tv_button_right.setText(button_right_text);
        }
        tv_button_left.setTextColor(mContext.getResources().getColor(mLeft_text_color));
        tv_button_right.setTextColor(mContext.getResources().getColor(mRight_text_color));

        tv_button_right.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_button_left) {
            //左
            this.dismiss();
            if (onCloseListener != null) {
                onCloseListener.onClick(this, LEFT);
            }
        } else if (id == R.id.tv_button_right) {
            //右
            this.dismiss();
            if (onCloseListener != null) {
                onCloseListener.onClick(this, RIGHT);
            }
        }

    }

    public interface OnCloseListener {
        void onClick(AlertDialog dialog, int buttonType);
    }

    public void setOutCancel(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
    }

}
