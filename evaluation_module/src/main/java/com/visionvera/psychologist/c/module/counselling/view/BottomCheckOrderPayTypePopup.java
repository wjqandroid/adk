package com.visionvera.psychologist.c.module.counselling.view;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.visionvera.psychologist.c.R;

/**
 * @author: 刘传政
 * @date: 2019-06-19 16:55
 * QQ:1052374416
 * 作用:选择支付方式弹窗
 * 注意事项:
 */
public class BottomCheckOrderPayTypePopup extends BottomPopupView {
    private int checkPosition;
    private ResultListener listener;
    private String price;

    public BottomCheckOrderPayTypePopup(@NonNull Context context, int checkPosition, String price, ResultListener listener) {
        super(context);
        this.checkPosition = checkPosition;
        this.listener = listener;


        this.price = price;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.bottom_check_order_pay_type;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        ImageView iv_close = findViewById(R.id.iv_close);
        RelativeLayout rl_ok = findViewById(R.id.rl_ok);
        RadioButton rb_zhifubao = findViewById(R.id.rb_zhifubao);
        RadioButton rb_weixin = findViewById(R.id.rb_weixin);
        TextView tvPrice = findViewById(R.id.tvPrice);
        rb_zhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkPosition = 0;
                    rb_weixin.setChecked(false);
                }
            }
        });
        rb_weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkPosition = 1;
                    rb_zhifubao.setChecked(false);
                }
            }
        });
        if (checkPosition == 0) {
            rb_zhifubao.setChecked(true);
        } else if (checkPosition == 1) {
            rb_weixin.setChecked(true);
        }
        tvPrice.setText(price);

        iv_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rl_ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    dismiss();
                    if (checkPosition == 0) {
                        listener.onCkecked(checkPosition, "支付宝快捷支付");
                    } else if (checkPosition == 1) {
                        listener.onCkecked(checkPosition, "微信支付");
                    }

                }
            }
        });
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

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .7f);
    }

    @Override
    public int getMinimumHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .2f);
    }


    public interface ResultListener {
        void onCkecked(int checkPosition, String name);

    }


}