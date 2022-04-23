package com.visionvera.psychologist.c.module.healthreport.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.ordertreatment.activity.OrderTreatmentMainActivity;
import com.visionvera.psychologist.c.utils.chart.XAxisFormatter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2019/12/6
 * 描述:健康报告结果页-----有数据
 */

public class HealthReportDetailActivity extends BaseActivity {

    @BindView(R2.id.happiness_index_linechart)
    LineChart happiness_index_linechart;

    @BindView(R2.id.anxious_index_linechart)
    LineChart anxious_index_linechart;

    @BindView(R2.id.tv_title)
    TextView tv_title;


    private String[] dayArray = {"1/11", "2/11", "3/11", "4/11", "5/11", "6/11", "7/11", "8/11", "9/11", "10/11", "11/11", "12/11", "13/11", "14/11", "15/11", "16/11"};
    private Button evaluation_module_explore_myself_detail_consult;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, HealthReportDetailActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_health_report_detail;
    }

    @Override
    protected void doYourself() {
        evaluation_module_explore_myself_detail_consult = (Button) findViewById(R.id.evaluation_module_explore_myself_detail_consult);
        evaluation_module_explore_myself_detail_consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthReportDetailActivity.this, OrderTreatmentMainActivity.class));
            }
        });
        tv_title.setText("10月健康报告");

        initData2(happiness_index_linechart, R.drawable.evaluation_module_gradient_red, getResources().getColor(R.color.base_module_color_FF4E00));

        initData2(anxious_index_linechart, R.drawable.evaluation_module_gradient_ching, getResources().getColor(R.color.base_module_color_00D4C5));
    }

    private void initData2(LineChart chart, int fillDrawable, int color) {
        chart.setBackgroundColor(Color.WHITE);
        chart.getDescription().setEnabled(false);
        /**
         * x，y轴是否可以缩放
         */
        chart.setScaleXEnabled(true);
        chart.setScaleYEnabled(false);

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

        initLineChart(16, 100, chart, fillDrawable, color);
        /**
         * 设置动画
         */
        chart.animateX(650);


        /**
         * 画基准线
         */
        LimitLine ll1 = new LimitLine(60f, "");
        ll1.setLineWidth(1f);
        ll1.setLineColor(Color.parseColor("#FFA95EFF"));
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        yAxis.addLimitLine(ll1);


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

            set1.setCircleColor(color);
            // draw points as solid circles
            set1.setDrawCircleHole(true);

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
        //屏幕上最少显示两条数据,必须设置这两行。否则初始化展示有问题。
        chart.setVisibleXRangeMinimum(2);
        chart.setVisibleXRangeMaximum(10);
    }


    @OnClick({R2.id.rl_back})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R2.id.rl_back) {
            finish();
        }

    }


}
