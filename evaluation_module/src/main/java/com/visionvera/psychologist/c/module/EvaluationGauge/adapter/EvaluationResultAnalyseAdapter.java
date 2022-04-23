package com.visionvera.psychologist.c.module.EvaluationGauge.adapter;

import androidx.annotation.Nullable;

import com.visionvera.live.widget.recycler.BaseQuickAdapter;
import com.visionvera.live.widget.recycler.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.SDSResultBean;

import java.util.List;

public class EvaluationResultAnalyseAdapter extends BaseQuickAdapter<SDSResultBean.ScaleResultBean.TenDivisorBean, BaseViewHolder> {

    public EvaluationResultAnalyseAdapter(@Nullable List<SDSResultBean.ScaleResultBean.TenDivisorBean> data) {
        super(R.layout.evaluation_module_adapter_item_result_analyse,data);
    }

    @Override
    protected void convert(BaseViewHolder helper,SDSResultBean.ScaleResultBean.TenDivisorBean item) {
        helper.setText(R.id.ll_test_result5_analyse_letter,item.getSimpRemark()+":");
        helper.setText(R.id.ll_test_result5_analyse,item.getRemark());
    }
}
