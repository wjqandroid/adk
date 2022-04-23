package com.visionvera.psychologist.c.widget.commonview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.visionvera.psychologist.c.R;

/**
* author:lilongfeng
* date:2020/3/20
* 描述:个人中心的点击条目
*/

public class SettingItemView extends RelativeLayout {


    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.evaluation_module_setting_item_view);
        boolean showBottomLine = ta.getBoolean(R.styleable.evaluation_module_setting_item_view_evaluation_module_showbottomline,false);
        boolean showTopLine = ta.getBoolean(R.styleable.evaluation_module_setting_item_view_evaluation_module_showtopline,false);
        int imageId = ta.getResourceId(R.styleable.evaluation_module_setting_item_view_evaluation_module_image,0);
        int title = ta.getResourceId(R.styleable.evaluation_module_setting_item_view_evaluation_module_title,0);


        if (ta!=null){
            ta.recycle();
        }


        LayoutInflater.from(context).inflate(R.layout.evaluation_module_setting_item_view,this,true);

        ImageView image=findViewById(R.id.evaluation_module_setting_item_image);
        TextView textTV=findViewById(R.id.evaluation_module_setting_item_text);
        View bottomLine=findViewById(R.id.evaluation_module_setting_item_bottomline);
        View topLine=findViewById(R.id.evaluation_module_setting_item_topline);

        if (imageId!=0){
            image.setImageResource(imageId);
        }

        textTV.setText(title);

        if (showBottomLine){
            bottomLine.setVisibility(VISIBLE);
        }else{
            bottomLine.setVisibility(GONE);
        }
        if (showTopLine){
            topLine.setVisibility(VISIBLE);
        }else{
            topLine.setVisibility(GONE);
        }

    }


}
