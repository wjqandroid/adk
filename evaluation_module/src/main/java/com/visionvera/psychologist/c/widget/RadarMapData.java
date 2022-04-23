package com.visionvera.psychologist.c.widget;
/**
 * @Desc 蛛网图实体类
 *
 * @Author yemh
 * @Date 2019-11-13 15:05
 *
 */
public class RadarMapData {
    private int count;//设置标题数
    private String[] titles;//设置标题
    private Double[] valuse;//设置数值
    private Float maxValue;//设置最大数值
    private int mainPaintColor;//设置蜘蛛网颜色
    private int textPaintColor;//设置标题颜色
    private int valuePaintColor;//设置覆盖局域颜色
    private int textSize;//设置字体大小
    private Integer[] textColors;//标题颜色

    public RadarMapData() {
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public Double[] getValuse() {
        return valuse;
    }

    public void setValuse(Double[] valuse) {
        this.valuse = valuse;
    }

    public Float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }

    public int getMainPaintColor() {
        return mainPaintColor;
    }

    public void setMainPaintColor(int mainPaintColor) {
        this.mainPaintColor = mainPaintColor;
    }

    public int getTextPaintColor() {
        return textPaintColor;
    }

    public void setTextPaintColor(int textPaintColor) {
        this.textPaintColor = textPaintColor;
    }

    public int getValuePaintColor() {
        return valuePaintColor;
    }

    public void setValuePaintColor(int valuePaintColor) {
        this.valuePaintColor = valuePaintColor;
    }

    public Integer[] getTextColors() {
        return textColors;
    }

    public void setTextColors(Integer[] textColors) {
        this.textColors = textColors;
    }
}
