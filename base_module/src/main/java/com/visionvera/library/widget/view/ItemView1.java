package com.visionvera.library.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.visionvera.library.R;


/**
 * @author: 刘传政
 * @date: 2019-06-18 14:59
 * QQ:1052374416
 * 作用:添加患者的布局2。左边是文字，右边是文字
 * 注意事项:
 */
public class ItemView1 extends RelativeLayout {

    private TextView tv_left;
    private TextView tv_right;

    public ItemView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.base_module_itemview1);
        String leftText=ta.getString(R.styleable.base_module_itemview1_base_module_leftText_itemview1);
        String rightText=ta.getString(R.styleable.base_module_itemview1_base_module_rightText_itemview1);
        int rightTextColor=ta.getColor(R.styleable.base_module_itemview1_base_module_rightTextColor_itemview1,context.getResources().getColor(R.color.common_text));
        boolean bottomLine=ta.getBoolean(R.styleable.base_module_itemview1_base_module_bottomLine_itemview1,true);
        if (ta!=null){
            ta.recycle();
        }
        LayoutInflater.from(context).inflate(R.layout.base_module_itemview1,this,true);

        tv_left = (TextView)findViewById(R.id.tv_left);
        tv_right = (TextView)findViewById(R.id.tv_right);

        View v_bottomLine= findViewById(R.id.bottomLine);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) v_bottomLine.getLayoutParams();
        v_bottomLine.setLayoutParams(lp);

        tv_left.setText(leftText);

        tv_right.setText(rightText);
        tv_right.setTextColor(rightTextColor);

        if (bottomLine) {
            v_bottomLine.setVisibility(VISIBLE);
        } else {
            v_bottomLine.setVisibility(GONE);
        }

    }

    public String getTv_left() {
        return tv_left.getText().toString().trim();
    }

    public void setTv_left(String tv_leftString) {
       tv_left.setText(tv_leftString);
    }

    public String getTv_right() {
        return tv_right.getText().toString().trim();
    }

    public void setTv_right(String tv_rightString) {
        tv_right .setText(tv_rightString);
    }



}
