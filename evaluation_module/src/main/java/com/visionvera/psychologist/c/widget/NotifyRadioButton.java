package com.visionvera.psychologist.c.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatRadioButton;

/**
 * 自定义RadioButton
 * 重写onDraw方法,实现小红点功能
 *
 * @author yemh
 * @date 2017/7/20 16:31
 */

public class NotifyRadioButton extends AppCompatRadioButton {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float radius;
    boolean notify;
    private Context context;
    public NotifyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        paint.setColor(Color.RED);
        radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 12.0f, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (notify) {
            Drawable drawable = getCompoundDrawables()[1];
            Rect bounds = drawable.getBounds();
            float cx = getMeasuredWidth() / 2 + bounds.width() / 2 - radius / 2;
            float cy = getPaddingTop() + bounds.height() / 4;
            canvas.drawCircle(cx, cy, radius, paint);
        }
    }

    /**
     * 新消息提醒
     *
     * @param notify
     */
    public void notify(boolean notify) {
        this.notify = notify;
        invalidate();
    }

}
