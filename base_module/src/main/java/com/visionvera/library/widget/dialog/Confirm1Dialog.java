package com.visionvera.library.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.visionvera.library.R;

public class Confirm1Dialog extends AlertDialog implements View.OnClickListener {
    public static int LEFT = 0;
    public static int RIGHT = 1;
    public TextView tv_button_left;
    private TextView tv_button_right;
    private TextView tv_message;
    private OnCloseListener onCloseListener;
    private String message = "";
    private String button_left_text = "";
    private String button_right_text = "";
    public View devideLine;

    public Confirm1Dialog(Context context, String message, String button_left_text, String button_right_text, OnCloseListener onCloseListener) {
        super(context);
        this.message = message;
        this.button_left_text = button_left_text;
        this.button_right_text = button_right_text;
        this.onCloseListener = onCloseListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_module_dialog_1_confirm);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_button_left = (TextView) findViewById(R.id.tv_button_left);
        devideLine = findViewById(R.id.devide_line);
        tv_button_right = (TextView) findViewById(R.id.tv_button_right);
        tv_button_left.setOnClickListener(this);
        tv_button_right.setOnClickListener(this);
        tv_message.setText(message);
        if (!"".equals(button_left_text)) {
            tv_button_left.setText(button_left_text);
        }
        if (!"".equals(button_right_text)) {
            tv_button_right.setText(button_right_text);
        }
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