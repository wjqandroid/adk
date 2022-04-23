package com.visionvera.psychologist.c.widget;

/**
 * @Desc 柱状图实体类
 *
 * @Author yemh
 * @Date 2019-11-18 17:10
 */
public class BarChartData {
    private String[] upTitles;//设置上半边标题
    private String[] downTitles;//设置下半边标题
    private int[] upValuse;//设置上半边数值
    private int[] downValuse;//设置下半边数值
    private float maxValue;//设置最大数值
    private float barWidth;//柱子的宽度
    private int linePaintColor;//设置轴线颜色
    private int lablePaintColor;//设置坐标轴lable颜色
    private int textPaintColor;//设置文字颜色
    private int shadowPaintColor;//设置阴影颜色
    private int upPaintColor;//设置上半边value颜色
    private int downPaintColor;//设置下半边value颜色
    private int lableTextSize;//设置lable文字大小
    private int valueTextSize;//设置标题文字大小

    public BarChartData() {
    }

    public String[] getUpTitles() {
        return upTitles;
    }

    public void setUpTitles(String[] upTitles) {
        this.upTitles = upTitles;
    }

    public String[] getDownTitles() {
        return downTitles;
    }

    public void setDownTitles(String[] downTitles) {
        this.downTitles = downTitles;
    }

    public int[] getUpValuse() {
        return upValuse;
    }

    public void setUpValuse(int[] upValuse) {
        this.upValuse = upValuse;
    }

    public int[] getDownValuse() {
        return downValuse;
    }

    public void setDownValuse(int[] downValuse) {
        this.downValuse = downValuse;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(float barWidth) {
        this.barWidth = barWidth;
    }

    public int getLinePaintColor() {
        return linePaintColor;
    }

    public void setLinePaintColor(int linePaintColor) {
        this.linePaintColor = linePaintColor;
    }

    public int getLablePaintColor() {
        return lablePaintColor;
    }

    public void setLablePaintColor(int lablePaintColor) {
        this.lablePaintColor = lablePaintColor;
    }

    public int getTextPaintColor() {
        return textPaintColor;
    }

    public void setTextPaintColor(int textPaintColor) {
        this.textPaintColor = textPaintColor;
    }

    public int getShadowPaintColor() {
        return shadowPaintColor;
    }

    public void setShadowPaintColor(int shadowPaintColor) {
        this.shadowPaintColor = shadowPaintColor;
    }

    public int getUpPaintColor() {
        return upPaintColor;
    }

    public void setUpPaintColor(int upPaintColor) {
        this.upPaintColor = upPaintColor;
    }

    public int getDownPaintColor() {
        return downPaintColor;
    }

    public void setDownPaintColor(int downPaintColor) {
        this.downPaintColor = downPaintColor;
    }

    public int getLableTextSize() {
        return lableTextSize;
    }

    public void setLableTextSize(int lableTextSize) {
        this.lableTextSize = lableTextSize;
    }

    public int getValueTextSize() {
        return valueTextSize;
    }

    public void setValueTextSize(int valueTextSize) {
        this.valueTextSize = valueTextSize;
    }
}
