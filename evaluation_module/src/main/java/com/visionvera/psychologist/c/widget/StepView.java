package com.visionvera.psychologist.c.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.visionvera.psychologist.c.R;

/**
* author:lilongfeng
* date:2020/1/13
* 描述:打卡页面
*/

public class StepView extends LinearLayout {


    private TextView day1_tv;
    private TextView day2_tv;
    private TextView day3_tv;
    private TextView day4_tv;
    private TextView day5_tv;
    private TextView day6_tv;
    private TextView day7_tv;
    private ImageView day1_circle;
    private ImageView day2_circle;
    private ImageView day3_circle;
    private ImageView day4_circle;
    private ImageView day5_circle;
    private ImageView day6_circle;
    private ImageView day7_circle;
    private View line_1;
    private View line_2;
    private View line_3;
    private View line_4;
    private View line_5;
    private View line_6;

    public StepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.evaluation_module_view_step, this, true);

        day1_tv = findViewById(R.id.evaluation_module_step_view_1day_tv);
        day2_tv = findViewById(R.id.evaluation_module_step_view_2day_tv);
        day3_tv = findViewById(R.id.evaluation_module_step_view_3day_tv);
        day4_tv = findViewById(R.id.evaluation_module_step_view_4day_tv);
        day5_tv = findViewById(R.id.evaluation_module_step_view_5day_tv);
        day6_tv = findViewById(R.id.evaluation_module_step_view_6day_tv);
        day7_tv = findViewById(R.id.evaluation_module_step_view_7day_tv);

        day1_circle = findViewById(R.id.evaluation_module_step_view_1day_circle);
        day2_circle = findViewById(R.id.evaluation_module_step_view_2day_circle);
        day3_circle = findViewById(R.id.evaluation_module_step_view_3day_circle);
        day4_circle = findViewById(R.id.evaluation_module_step_view_4day_circle);
        day5_circle = findViewById(R.id.evaluation_module_step_view_5day_circle);
        day6_circle = findViewById(R.id.evaluation_module_step_view_6day_circle);
        day7_circle = findViewById(R.id.evaluation_module_step_view_7day_circle);

        line_1 = findViewById(R.id.line_1);
        line_2 = findViewById(R.id.line_2);
        line_3 = findViewById(R.id.line_3);
        line_4 = findViewById(R.id.line_4);
        line_5 = findViewById(R.id.line_5);
        line_6 = findViewById(R.id.line_6);
    }

    public void setDay1SignIn(Context context, int type) {
        switch (type) {
            case 0:
                setTextSignIn(day1_tv, day1_circle, context);
                break;

            case 1:
                setTextNotSignIn(day1_tv, day1_circle, context);
                break;

            case 2:
                setTextNotArrive(day1_tv, day1_circle, context);
                break;

            default:
                break;
        }
    }

    public void setDay2SignIn(Context context, int type) {
        switch (type) {
            case 0:
                setTextSignIn(day2_tv, day2_circle, context);
                break;

            case 1:
                setTextNotSignIn(day2_tv, day2_circle, context);
                break;

            case 2:
                setTextNotArrive(day2_tv, day2_circle, context);
                break;

            default:
                break;
        }
    }

    public void setDay3SignIn(Context context, int type) {
        switch (type) {
            case 0:
                setTextSignIn(day3_tv, day3_circle, context);
                break;

            case 1:
                setTextNotSignIn(day3_tv, day3_circle, context);
                break;

            case 2:
                setTextNotArrive(day3_tv, day3_circle, context);
                break;

            default:
                break;
        }
    }

    public void setDay4SignIn(Context context, int type) {
        switch (type) {
            case 0:
                setTextSignIn(day4_tv, day4_circle, context);
                break;

            case 1:
                setTextNotSignIn(day4_tv, day4_circle, context);
                break;

            case 2:
                setTextNotArrive(day4_tv, day4_circle, context);
                break;

            default:
                break;
        }
    }

    public void setDay5SignIn(Context context, int type) {
        switch (type) {
            case 0:
                setTextSignIn(day5_tv, day5_circle, context);
                break;

            case 1:
                setTextNotSignIn(day5_tv, day5_circle, context);
                break;

            case 2:
                setTextNotArrive(day5_tv, day5_circle, context);
                break;

            default:
                break;
        }
    }

    public void setDay6SignIn(Context context, int type) {
        switch (type) {
            case 0:
                setTextSignIn(day6_tv, day6_circle, context);
                break;

            case 1:
                setTextNotSignIn(day6_tv, day6_circle, context);
                break;

            case 2:
                setTextNotArrive(day6_tv, day6_circle, context);
                break;

            default:
                break;
        }
    }

    public void setDay7SignIn(Context context, int type) {
        switch (type) {
            case 0:
                setTextSignIn(day7_tv, day7_circle, context);
                break;

            case 1:
                setTextNotSignIn(day7_tv, day7_circle, context);
                break;

            case 2:
                setTextNotArrive(day7_tv, day7_circle, context);
                break;

            default:
                break;
        }
    }


    public void setDay1SignIn(Context context,boolean isSignIn){
        if (isSignIn){
            setTextSignIn(day1_tv, day1_circle, context);
        }else{
            setTextNotSignIn(day1_tv, day1_circle, context);
        }
    }

    public void setDay2SignIn(Context context,boolean isSignIn){
        if (isSignIn){
            setTextSignIn(day2_tv, day2_circle, context);
        }else{
            setTextNotSignIn(day2_tv, day2_circle, context);
        }
    }

    public void setDay3SignIn(Context context,boolean isSignIn){
        if (isSignIn){
            setTextSignIn(day3_tv, day3_circle, context);
        }else{
            setTextNotSignIn(day3_tv, day3_circle, context);
        }
    }

    public void setDay4SignIn(Context context,boolean isSignIn){
        if (isSignIn){
            setTextSignIn(day4_tv, day4_circle, context);
        }else{
            setTextNotSignIn(day4_tv, day4_circle, context);
        }
    }

    public void setDay5SignIn(Context context,boolean isSignIn){
        if (isSignIn){
            setTextSignIn(day5_tv, day5_circle, context);
        }else{
            setTextNotSignIn(day5_tv, day5_circle, context);
        }
    }

    public void setDay6SignIn(Context context,boolean isSignIn){
        if (isSignIn){
            setTextSignIn(day6_tv, day6_circle, context);
        }else{
            setTextNotSignIn(day6_tv, day6_circle, context);
        }
    }

    public void setDay7SignIn(Context context,boolean isSignIn){
        if (isSignIn){
            setTextSignIn(day7_tv, day7_circle, context);
        }else{
            setTextNotSignIn(day7_tv, day7_circle, context);
        }
    }

    /**
     * 已签到的颜色
     */
    private void setTextSignIn(TextView textView, ImageView imageView, Context context) {
        textView.setTextColor(context.getResources().getColor(R.color.COLOR_BLACK_333333));
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.evaluation_module_stepview_black));
    }

    /**
     * 未签到的文字颜色
     */
    private void setTextNotSignIn(TextView textView, ImageView imageView, Context context) {
        textView.setTextColor(context.getResources().getColor(R.color.COLOR_GRAY_D1D2D6));
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.evaluation_module_stepview_gray));
    }

    /**
     * 没有到达的日期文字颜色
     */
    private void setTextNotArrive(TextView textView, ImageView imageView, Context context) {
        textView.setTextColor(context.getResources().getColor(R.color.COLOR_GRAY_999999));
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.evaluation_module_stepview_gray));
    }

}
