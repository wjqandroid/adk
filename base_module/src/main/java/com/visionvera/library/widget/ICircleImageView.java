package com.visionvera.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.visionvera.library.R;


/**
 * @Desc 自定义圆形头像
 * @Author yemh
 * @Date 2019/4/12 17:50
 */
public class ICircleImageView extends AppCompatImageView {
    //圆角弧度
    private float[] rids = {12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f, 12.0f,};
    private int cornerNum;

    public ICircleImageView(Context context) {
        super(context);
    }

    public ICircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.base_module_i_circle_imageview);
        cornerNum = ta.getInteger(R.styleable.base_module_i_circle_imageview_base_module_circle_corner,12);

        if (ta!=null){
            ta.recycle();
        }
        float cornerFloat=(float)cornerNum;
        rids= new float[]{cornerFloat, cornerFloat, cornerFloat, cornerFloat, cornerFloat, cornerFloat, cornerFloat, cornerFloat};
    }

    public ICircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        //绘制圆角imageview
        path.addRoundRect(new RectF(0, 0, w, h), rids, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}