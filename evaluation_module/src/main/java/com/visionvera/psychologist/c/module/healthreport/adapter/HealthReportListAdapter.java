package com.visionvera.psychologist.c.module.healthreport.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;

import java.util.List;

/**
* author:lilongfeng
* date:2019/12/3
* 描述:健康报告列表页的适配器
*/


public class HealthReportListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {




    public HealthReportListAdapter( @Nullable List<String> data) {
        super(R.layout.evaluation_module_health_report_list_item,data);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {




    }
}
