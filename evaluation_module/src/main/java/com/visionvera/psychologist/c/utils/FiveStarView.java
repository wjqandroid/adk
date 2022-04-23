package com.visionvera.psychologist.c.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.visionvera.psychologist.c.R;

/**
 * @Classname:FiveStarView
 * @author:haohuizhao
 * @Date:2021/10/28 13:45
 * @Version：v1.0
 * @describe：Android五星评价控件封装
 */
public class FiveStarView extends LinearLayout {

    private LinearLayout ll_star;

    public FiveStarView(Context context) {
        super(context);
        init(context);
    }

    public FiveStarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FiveStarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluation_module_five_star_evaluate, null);
        ll_star = view.findViewById(R.id.ll_star);
        this.addView(view);
    }

    public void setStar(int num) {
        if (num <= 0) return;
        for (int i = 0; i < num && i < 5; i++) {
            ((ImageView) ll_star.getChildAt(i)).setImageResource(R.mipmap.img_five_stars);
        }
    }
}
