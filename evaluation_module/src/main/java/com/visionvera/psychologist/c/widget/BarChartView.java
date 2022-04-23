package com.visionvera.psychologist.c.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.visionvera.psychologist.c.utils.ScreenUtils;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class BarChartView extends View {
    private BarChartData barChartData;
    private String[] upTitles ;//设置上半边标题
    private String[] downTitles ;//设置下半边标题
    private int[] upValuse;//设置上半边数值
    private int[] downValuse;//设置下半边数值
    private Paint linePaint;//轴线画笔
    private Paint lablePaint;//坐标轴lable画笔
    private Paint textPaint;//文字画笔
    private Paint shadowPaint;//阴影画笔
    private Paint upValuePaint;//上半边value画笔
    private Paint downValuePaint;//下半边value画笔
    private float barWidth;//柱子的宽度

    private int width, height;
    private String[] lables = new String[]{"30", "25", "20", "15", "10", "5", "0", "5", "10", "15", "20","25","30"};
    float[] upRids = {45.0f, 45.0f, 45.0f, 45.0f, 0.0f, 0.0f, 0.0f, 0.0f,};
    float[] downRids = {0.0f, 0.0f, 0.0f, 0.0f, 45.0f, 45.0f, 45.0f, 45.0f};
    //X轴线距离最右侧距离
    private float right;
    //X轴距离最左侧距离
    private float startX;
    //X轴方向终点位置
    private float endX;
    //Y轴线末尾距离图表最底部的距离
    private float yBottom;
    //Y轴线起始位置距离图表顶部的距离
    private float yTop;
    //Y轴线终点位置
    private float endY;
    //Y轴线起始位置
    private float startY;
    //Y轴标签距离Y轴线的距离
    private float rightMargin;
    //占比
    private float proportion;

    public BarChartView(Context context) {
        this(context, null);
    }

    public BarChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        linePaint = new Paint();//轴线画笔
        linePaint.setStrokeWidth(2);
        linePaint.setColor(0xFFEEEEEE);
        linePaint.setAntiAlias(true);

        lablePaint = new Paint();//坐标轴lable画笔
        lablePaint.setColor(0xFF999999);
        lablePaint.setTextSize(ScreenUtils.sp2px(getContext(), 8));
        lablePaint.setAntiAlias(true);

        textPaint = new Paint();//文字画笔
        textPaint.setColor(0xFF666666);
        textPaint.setTextSize(ScreenUtils.sp2px(getContext(), 12));
        textPaint.setAntiAlias(true);

        shadowPaint = new Paint();//阴影画笔
        shadowPaint.setColor(0xFFF1F4FB);
        shadowPaint.setAntiAlias(true);

        upValuePaint = new Paint();//上半边value画笔
        upValuePaint.setColor(0xFFFF7072);
        upValuePaint.setAntiAlias(true);

        downValuePaint = new Paint();//下半边value画笔
        downValuePaint.setColor(0xFF4BAFFF);
        downValuePaint.setAntiAlias(true);

        barWidth = ScreenUtils.dp2px(getContext(), 10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXY(canvas);
        drawYLable(canvas);
        drawValue(canvas);
    }

    /**
     * 绘制XY轴
     *
     * @param c
     */
    private void drawXY(Canvas c) {
        right = ScreenUtils.dp2px(getContext(), 25);
        startX = ScreenUtils.dp2px(getContext(), 37);
        endX = Float.valueOf(width) - right;
        yBottom = ScreenUtils.dp2px(getContext(), 20);
        yTop = ScreenUtils.dp2px(getContext(), 25);
        endY = Float.valueOf(height) - yBottom;
        startY = (endY - yTop) / 2 + yTop;
        proportion = (endY - yTop) / 60;

        //绘制X轴线
        c.drawLine(startX, startY, endX, startY, linePaint);
        //绘制Y轴线
        c.drawLine(startX, yTop, startX, endY, linePaint);
    }

    /**
     * 绘制Y轴Lable
     *
     * @param c
     */
    private void drawYLable(Canvas c) {
        rightMargin = ScreenUtils.dp2px(getContext(), 5);

        //Y轴线总长度
        float totalHeight = height - yBottom - yTop - 2 * rightMargin;
        //Y轴线标签间距
        float space = totalHeight / 12;

        for (int i = 0; i < lables.length; i++) {
            String text = lables[i];
            int textWidth = ScreenUtils.calcTextWidth(lablePaint, text);
            int textHeight = ScreenUtils.calcTextHeight(lablePaint, text);

            //绘制Y轴线标签
            c.drawText(text, startX - textWidth - rightMargin, textHeight / 2 + i * space + rightMargin + yTop, lablePaint);
        }
    }

    /**
     * 绘制柱子
     *
     * @param c
     */
    private void drawValue(Canvas c) {
        //第一根柱子距离图表最左侧的距离
        float left = ScreenUtils.dp2px(getContext(), 71);
        //最后一根柱子距离图表最右侧的距离
        float right = ScreenUtils.dp2px(getContext(), 52);

        //柱子之间的间距
        float space = (width - left - right) / 3;
        for (int i = 0; i < 4; i++) {
            RectF shadowRectF = new RectF();
            shadowRectF.left = left + i * space;
            shadowRectF.right = shadowRectF.left + barWidth;
            shadowRectF.top = yTop;
            shadowRectF.bottom = endY;

            c.drawRoundRect(shadowRectF, 45.0f, 45.0f, shadowPaint);

            RectF upRectF = new RectF();
            upRectF.left = left + i * space;
            upRectF.right = upRectF.left + barWidth;
            upRectF.top = startY - upValuse[i] * proportion;
            upRectF.bottom = startY;

            LinearGradient upShader = new LinearGradient(upRectF.left, upRectF.bottom, upRectF.right, upRectF.top, new int[]{0xFFFCB291, 0xFFFF7072}, new float[]{0.0f, 1.0f}, Shader.TileMode.MIRROR);
            upValuePaint.setShader(upShader);

            Path upPath = new Path();
            upPath.addRoundRect(upRectF, upRids, Path.Direction.CCW);
            c.drawPath(upPath, upValuePaint);
            upPath.reset();

            String upText = upTitles[i];
            float upTitleW = ScreenUtils.calcTextWidth(textPaint, upText);
            float upTitleH = ScreenUtils.calcTextHeight(textPaint, upText);

            float x = upRectF.left - upTitleW / 2 + barWidth / 2;
            //绘制上半部分X轴标签
            c.drawText(upTitles[i], x, upTitleH, textPaint);

            RectF downRectF = new RectF();
            downRectF.left = left + i * space;
            downRectF.right = downRectF.left + barWidth;
            downRectF.top = startY;
            downRectF.bottom = startY + downValuse[i] * proportion;

            LinearGradient downShader = new LinearGradient(downRectF.left, downRectF.bottom, downRectF.right, downRectF.top, new int[]{0xFF4BAFFF, 0xFF86C6FE}, new float[]{0.0f, 1.0f}, Shader.TileMode.MIRROR);
            upValuePaint.setShader(downShader);

            Path downPath = new Path();
            downPath.addRoundRect(downRectF, downRids, Path.Direction.CCW);
            c.drawPath(downPath, upValuePaint);
            downPath.reset();

            String downText = upTitles[i];
            float downTitleW = ScreenUtils.calcTextWidth(textPaint, downText);
            float downTitleH = ScreenUtils.calcTextHeight(textPaint, downText);

            float x1 = downRectF.left - downTitleW / 2 + barWidth / 2;
            float y = shadowRectF.bottom + downTitleH + 10;

            //绘制下半部分X轴标签
            c.drawText(downTitles[i], x1, y, textPaint);
        }
    }

    public void setData(BarChartData data){
        upTitles = new String[4];
        upTitles = data.getUpTitles();

        upValuse = new int[4];
        upValuse = data.getUpValuse();

        downTitles = new String[4];
        downTitles = data.getDownTitles();

        downValuse = new int[4];
        downValuse = data.getDownValuse();

        postInvalidate();
    }

}
