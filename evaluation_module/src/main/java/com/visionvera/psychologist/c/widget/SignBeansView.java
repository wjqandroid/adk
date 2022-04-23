package com.visionvera.psychologist.c.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.visionvera.psychologist.c.R;

/**
 * @author: 刘传政
 * @date: 12/22/20 1:57 PM
 * QQ:1052374416
 * 作用: 签到领心豆view
 * 注意事项:
 */
public class SignBeansView extends LinearLayout {


    public SignBeansView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.evaluation_module_view_sign_beans, this, true);
    }

}
