package com.visionvera.psychologist.c.module.healthreport.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.lxj.xpopup.XPopup;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.ordertreatment.activity.OrderTreatmentMainActivity;
import com.visionvera.psychologist.c.utils.chart.XAxisFormatter;
import com.visionvera.psychologist.c.widget.RelativeRadioGroup;
import com.visionvera.psychologist.c.widget.popup.CalendarPopup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2019/12/9
 * 描述:趋势分析3个tab页面
 */

public class TrendAnalysisActivity extends BaseActivity {

    @BindView(R2.id.evaluation_module_calendar_tv)
    TextView calendar_tv;

    @BindView(R2.id.index_linechart)
    LineChart chart;

    @BindView(R2.id.tv_title)
    TextView tv_title;

    @BindView(R2.id.evaluation_module_health_report_relativeradiogroup)
    RelativeRadioGroup radioGroup;

    private String[] dayArray = {"1/11", "2/11", "3/11", "4/11", "5/11", "6/11", "7/11", "8/11", "9/11", "10/11", "11/11", "12/11", "13/11", "14/11", "15/11", "16/11"};


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TrendAnalysisActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_trend_analysis;
    }

    @Override
    protected void doYourself() {
        initView();

        initData(chart, R.drawable.evaluation_module_gradient_red, getResources().getColor(R.color.base_module_color_FF4E00));
    }

    private void initData(LineChart chart, int fillDrawable, int color) {

        initLineChart(16, 100, chart, fillDrawable, color);

        chart.getDescription().setEnabled(false);
        /**
         * x，y轴是否可以缩放
         */
        chart.setScaleXEnabled(true);
        chart.setScaleYEnabled(false);
        //设置图标背景色
        chart.setBackgroundColor(getResources().getColor(R.color.COLOR_WHITE_F5F5F5));




        /**
         * 关闭左下角图例
         */
        chart.getLegend().setEnabled(false);
        /**
         * x轴设置
         */
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(getResources().getColor(R.color.COLOR_GRAY_666666));
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);

        xAxis.setValueFormatter(new XAxisFormatter(dayArray));

        /**
         * 设置x轴放大后，不会出现重复的数据
         */
        xAxis.setGranularity(1f);
//        xAxis.setAxisMinimum(7);
//        xAxis.enableGridDashedLine(10f,10f,0f);
//        xAxis.setAxisLineWidth(5f);
        xAxis.setLabelCount(8);
//        ViewPortHandler viewPortHandler=happiness_index_linechart.getViewPortHandler();
//        viewPortHandler.setMaximumScaleX(2f);


        /**
         * 点击标点的弹出框的设置
         */
//        XinliMarkerView mv = new XinliMarkerView(this, R.layout.evaluation_module_marker_view);
//        mv.setChartView(happiness_index_linechart);
//        chart.setMarker(mv);

        /**
         * y轴的数据设置
         */
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setDrawZeroLine(true);
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(getResources().getColor(R.color.COLOR_GRAY_999999));
        yAxis.enableGridDashedLine(10f, 10f, 0f);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(100);
        yAxis.setLabelCount(10);
        chart.getAxisRight().setEnabled(false);

        /**
         * 设置动画
         */
        chart.animateY(650);


        /**
         * 画基准线
         */
        LimitLine ll1 = new LimitLine(60f, "");
        ll1.setLineWidth(1f);
        ll1.setLineColor(Color.parseColor("#FFA95EFF"));
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        yAxis.addLimitLine(ll1);

        //屏幕上最少显示两条数据,必须设置这两行。否则初始化展示有问题。
        chart.setVisibleXRangeMinimum(2);
        chart.setVisibleXRangeMaximum(10);
        /**
         * 设置描述
         */
       /* Description description=chart.getDescription();
        description.setText("日期");
        description.setTextSize(40);
        description.setTextColor(Color.RED);

        description.setPosition(chart.getWidth(),300);

        chart.getDescription().setEnabled(true);*/
    }

    private void initLineChart(int count, float range, LineChart chart, int fillDrawable, int color) {

        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int val = (int) (Math.random() * range) + 1;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;


        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);

            set1.setColor(color);
            set1.setCircleColor(color);
            set1.setDrawCircleHole(true);
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, fillDrawable);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            set1 = new LineDataSet(values, "");
            // draw dashed line
//            set1.enableDashedLine(10f, 5f, 0f);
            set1.setColor(color);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            // 设置点击某个点时，横竖两条线的颜色
            set1.setHighLightColor(getResources().getColor(R.color.COLOR_WHITE_F5F5F5));
            set1.setCircleColor(color);
            // draw points as solid circles
            set1.setDrawCircleHole(true);
            set1.setDrawValues(true);// 是否在点上绘制Value
            set1.setDrawFilled(true);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, fillDrawable);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            LineData data = new LineData(dataSets);

            chart.setData(data);
        }

    }


    private void initView() {
        calendar_tv.setText("10月09日 - 11月07日");
        tv_title.setText(getString(R.string.evaluation_module_trend_analysis));
        radioGroup.check(R.id.evaluation_module_trend_analysis_happiness_index_tv);

        radioGroup.setOnCheckedChangeListener(new RelativeRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RelativeRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R2.id.evaluation_module_trend_analysis_happiness_index_tv:

                        initData(chart, R.drawable.evaluation_module_gradient_red, getResources().getColor(R.color.base_module_color_FF4E00));
                        break;
                    case R2.id.evaluation_module_trend_analysis_depressed_index_tv:

//                        initLineChart(16, 100, chart, R.drawable.evaluation_module_gradient_ching, getResources().getColor(R.color.base_module_color_00D4C5));

                        initData(chart, R.drawable.evaluation_module_gradient_ching, getResources().getColor(R.color.base_module_color_00D4C5));

                        break;
                    case R2.id.evaluation_module_trend_analysis_anxious_index_tv:
//                        initLineChart(16, 100, chart, R.drawable.evaluation_module_gradient_pink,  getResources().getColor(R.color.base_module_color_FF7884));

                        initData(chart, R.drawable.evaluation_module_gradient_pink, getResources().getColor(R.color.base_module_color_FF7884));

                        break;
                    default:
                }
            }
        });
    }

    @OnClick({R2.id.rl_back, R2.id.evaluation_module_calendar_tv, R2.id.evaluation_module_explore_myself_detail_consult})
    public void onClick(View view){
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId=view.getId();
        if (viewId==R.id.rl_back){
            finish();
        }else if (viewId == R.id.evaluation_module_calendar_tv) {
            openCalendar();
        } else if (viewId == R.id.evaluation_module_explore_myself_detail_consult) {
            startActivity(new Intent(TrendAnalysisActivity.this, OrderTreatmentMainActivity.class));
//            ToastUtils.showLong(getResources().getString(R.string.evaluation_module_not_develop));
        }
    }

    private void openCalendar() {
        new XPopup.Builder(this)
                .asCustom(new CalendarPopup(this))
                .show();
    }
}
