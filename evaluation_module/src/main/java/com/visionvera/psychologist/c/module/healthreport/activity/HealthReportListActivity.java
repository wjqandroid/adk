package com.visionvera.psychologist.c.module.healthreport.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.healthreport.adapter.HealthReportListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2019/12/3
 * 描述:健康报告列表页
 */

public class HealthReportListActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R2.id.evaluation_module_health_report_list_recyclerview)
    RecyclerView mRecy;

    @BindView(R2.id.tv_title)
    TextView tv_title;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, HealthReportListActivity.class));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_health_report_list;
    }

    @Override
    protected void doYourself() {
        initView();
    }

    private void initView() {

        tv_title.setText(getString(R.string.evaluation_module_health_report));

        mRecy.setLayoutManager(new LinearLayoutManager(this));
        mRecy.addItemDecoration(new SpaceItemDecoration(10));
        List<String> dataList=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            dataList.add(i+"");
        }
        HealthReportListAdapter adapter=new HealthReportListAdapter(dataList);
        mRecy.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @OnClick({R2.id.rl_back})
    public void onClick(View view){
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId=view.getId();
        if (viewId==R.id.rl_back){
            finish();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HealthReportDetailActivity.startActivity(this);
    }
}
