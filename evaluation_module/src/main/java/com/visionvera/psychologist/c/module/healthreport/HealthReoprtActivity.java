package com.visionvera.psychologist.c.module.healthreport;

import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.healthreport.activity.ExploreMySelfListActivity;
import com.visionvera.psychologist.c.module.healthreport.activity.HealthReportDetailEmptyActivity;
import com.visionvera.psychologist.c.module.healthreport.activity.HealthReportListActivity;
import com.visionvera.psychologist.c.module.healthreport.activity.TrendAnalysisActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc 健康报告
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class HealthReoprtActivity extends BaseActivity {
    @BindView(R2.id.evaluation_module_tv_title)
    TextView tvTitle;

    @BindView(R2.id.evaluation_module_score_time)
    TextView score_time;

    @BindView(R2.id.evaluation_module_score_tv)
    TextView score_tv;

    @BindView(R2.id.evaluation_module_sweet_advice)
    TextView sweet_advice;

    @BindView(R2.id.evaluation_module_health_report_month)
    TextView report_month;

    @BindView(R2.id.evaluation_module_psychological_evaluation_health_report_notice)
    TextView health_report_notice;

    @BindView(R2.id.evaluation_module_trend_analysis_notice)
    TextView trend_analysis_notice;

    @BindView(R2.id.evaluation_module_explore_myself_notice)
    TextView explore_myself_notice;

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_fragment_health_report;
    }

    @Override
    protected void doYourself() {
        updateStatuBar();
        initData();
    }


    private void initData() {
        tvTitle.setText(R.string.evaluation_module_health_report);
        score_time.setText("3天前");
        score_tv.setText("75");
        sweet_advice.setText("贴心建议：您比大多数人感觉自己幸福，真是个开心的结果，继续保持，向阳而生");

        report_month.setText("十月份");
        health_report_notice.setText("十月份心理测评健康报告。");
        trend_analysis_notice.setText("心理测评健康趋势分析。");
        explore_myself_notice.setText("探索自我，发现内心深处的我。");
    }

    public void updateStatuBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.COLOR_WHITE_FFFEFE)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }

    @OnClick({R2.id.evaluation_module_more_health_report,
            R2.id.evaluation_module_health_report_layout,
            R2.id.evaluation_module_more_trend_analysis_tv,
            R2.id.evaluation_module_more_explore_tv,
            R2.id.evaluation_module_iv_back,})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_more_health_report) {
            HealthReportListActivity.startActivity(this);
        } else if (viewId == R.id.evaluation_module_health_report_layout) {
            HealthReportDetailEmptyActivity.startActivity(this);
        } else if (viewId == R.id.evaluation_module_more_trend_analysis_tv) {
            TrendAnalysisActivity.startActivity(this);
        } else if (viewId == R.id.evaluation_module_more_explore_tv) {
            ExploreMySelfListActivity.startActivity(this);
        } else if (viewId == R.id.evaluation_module_iv_back) {
            finish();
        }
    }


}
