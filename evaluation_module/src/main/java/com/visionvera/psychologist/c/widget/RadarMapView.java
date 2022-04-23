package com.visionvera.psychologist.c.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Desc 自定义蛛网图
 * @Author yemh
 * @Date 2019-11-13 15:05
 */
public class RadarMapView extends View {
    private RadarMapData radarMapData;
    private float angle;//每项数值之间夹角
    private int count = 6;//数值项个数
    private int contextX, contextY;//中心点坐标
    private float radius = 100;//网格最大半径
    private Paint mainPaint;  //雷达区画笔
    private Paint valuePaint; //数据区画笔
    private Paint textPaint;  //文本画笔
    private String[] titles;
    private Double[] values;
    private Double[] temValuses;
//    private Integer[] alphs;
//    private Integer[] textColors;
    private float maxValue = 100;//数据最大值
    private float mDuration = 1000;//动画时长
    private float mCount = 100;//分多少次运动
    private float mCurrent = 100;   // 当前已进行时长
    private float mPiece = mDuration / mCount; // 每一份的时长
    private boolean hasData = false;

    public RadarMapView(Context context) {
        this(context, null);

    }

    public RadarMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mainPaint = new Paint();
        textPaint = new Paint();
        valuePaint = new Paint();
        mainPaint.setColor(Color.BLUE);
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setAntiAlias(true);
        textPaint.setColor(Color.RED);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.STROKE);
        valuePaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        contextX = w / 2;
        contextY = h / 2;
        radius = Math.min(w, h) / 2 * 0.8f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (hasData) {
            drawPolygon(canvas);//绘制正多边形
            drawLigature(canvas);
            drawTitle(canvas);//绘制标题
            drawCover(canvas);//绘制覆盖区域
        }
    }

    /**
     * 绘制中心点到多边形点的连线
     *
     * @param canvas
     */
    private void drawLigature(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(contextX, contextY);//将起点移到多边形中心点
            float x = (float) (contextX + radius * Math.cos(angle * i));
            float y = (float) (contextY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制多边形
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 3);
        for (int i = 1; i < count - 2; i++) {//绘制的层数
            float curR = r * i;//当前层数半径
            path.reset();//使path重置
            for (int j = 0; j < count; j++) {
                if (j == 0) {//第一次绘制，将起点移到第一个将要绘制的点,即多边形中心点移动到起点
                    path.moveTo(contextX + curR, contextY);
                } else {
                    //使用三角函数计算x坐标和有坐标
                    float x = (float) (contextX + curR * Math.cos(angle * j));
                    float y = (float) (contextY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }

    private void drawTitle(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;//得到文字的高度
        for (int i = 0; i < count; i++) {
//            textPaint.setColor(textColors[i]);
            float x = (float) (contextX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (contextY + (radius + fontHeight / 2) * Math.sin(angle * i));
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {
                //第四象限
                canvas.drawText(titles[i], x, y + fontHeight / 3, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {
                //第三象限
                float drs = textPaint.measureText(titles[i]);
//                if (i == 2) {
//                    canvas.drawText(titles[i], x - drs / 2, y + fontHeight / 2, textPaint);
//                } else {
                canvas.drawText(titles[i], x - drs, y + fontHeight / 3, textPaint);
//                }
            } else if (angle * i > Math.PI && angle * i < 3 * Math.PI / 2) {
                //第二象限
                float drs = textPaint.measureText(titles[i]);
//                if (i == 4) {
//                    canvas.drawText(titles[i], x - drs, y + 10, textPaint);
//                } else {
                canvas.drawText(titles[i], x - drs, y, textPaint);
//                }
            } else if (angle * i > 3 * Math.PI / 2 && angle * i <= 2 * Math.PI) {
                //第一象限
                float drs = textPaint.measureText(titles[i]);
//                if (i == 6) {
//                    canvas.drawText(titles[i], x - drs / 2, y, textPaint);
//                } else {
                canvas.drawText(titles[i], x, y, textPaint);
//                }
            }
        }
    }

    private void drawCover(Canvas canvas) {
        for (int i = 0; i < count; i++) {
            Path path = new Path();
            path.moveTo(contextX, contextY);//原点
            if (i == 0) {
                float p0 = (float) (temValuses[i] / maxValue);
                float p1 = (float) (temValuses[i + 1] / maxValue);
                float x = (float) (contextX + radius * p1 * Math.cos(angle * (i + 1)));
                float y = (float) (contextY + radius * p1 * Math.sin(angle * (i + 1)));
                path.lineTo(contextX + radius * p0, contextY);//第一个点
                path.lineTo(x, y);//第二个点
            } else if (i == count - 1) {
                float p0 = (float) (temValuses[0] / maxValue);
                float p1 = (float) (temValuses[i] / maxValue);
                float x1 = (float) (contextX + radius * p1 * Math.cos(angle * i));
                float y1 = (float) (contextY + radius * p1 * Math.sin(angle * i));
                path.lineTo(x1, y1);//第八个点
                path.lineTo(contextX + radius * p0, contextY);//第一个点
            } else {
                float p1 = (float) (temValuses[i] / maxValue);
                float x1 = (float) (contextX + radius * p1 * Math.cos(angle * i));
                float y1 = (float) (contextY + radius * p1 * Math.sin(angle * i));

                float p2 = (float) (temValuses[i + 1] / maxValue);
                float x2 = (float) (contextX + radius * p2 * Math.cos(angle * (i + 1)));
                float y2 = (float) (contextY + radius * p2 * Math.sin(angle * (i + 1)));
                path.lineTo(x1, y1);//第二个点
                path.lineTo(x2, y2);//第三个点
            }
//            valuePaint.setAlpha(alphs[i]);//设置画笔透明度
            valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);//填充并且描边
            canvas.drawPath(path, valuePaint);
            path.reset();
        }

        mCurrent += mPiece;
        if (mCurrent < mDuration) {
            for (int i = 0; i < count; i++) {
                temValuses[i] = values[i] * mCurrent / mDuration;
            }
            postInvalidateDelayed((long) mPiece);//刷新view
        }
    }

    public void setData(RadarMapData radarMapData) {
        hasData = true;
        this.radarMapData = radarMapData;
        count = radarMapData.getCount();
        angle = (float) (Math.PI * 2 / count);
        mainPaint.setColor(radarMapData.getMainPaintColor());
        textPaint.setTextSize(radarMapData.getTextSize());
        textPaint.setColor(radarMapData.getTextPaintColor());
        valuePaint.setColor(radarMapData.getValuePaintColor());

        titles = new String[count];
        titles = radarMapData.getTitles();
        values = new Double[count];
        values = radarMapData.getValuse();
        temValuses = new Double[count];
//        alphs = new Integer[count];
//        textColors = new Integer[count];
//        textColors = radarMapData.getTextColors();
        int alph = 10;
        for (int i = 0; i < count; i++) {
            temValuses[i] = 0.0;
//            alphs[i] = 120 + alph * i;
        }
        postInvalidate();
    }
}
