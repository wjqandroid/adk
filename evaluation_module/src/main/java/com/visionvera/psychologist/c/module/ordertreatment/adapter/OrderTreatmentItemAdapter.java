package com.visionvera.psychologist.c.module.ordertreatment.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListBean;

import java.util.List;

public class OrderTreatmentItemAdapter extends BaseQuickAdapter<OrderTreatmentListBean.ResultBean.DataListBean, BaseViewHolder> {



    public OrderTreatmentItemAdapter(@Nullable List<OrderTreatmentListBean.ResultBean.DataListBean> data) {
        super(R.layout.evaluation_module_fragment_order_treatment_adapter_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderTreatmentListBean.ResultBean.DataListBean item) {
        helper.setText(R.id.evaluation_module_order_number,item.getAppNum());
        if (item.getDoctorName()!=null){
            helper.setText(R.id.evaluation_module_counselor,item.getDoctorName());
        }else{
            helper.setText(R.id.evaluation_module_counselor,"");
        }

        String consultTime= item.getStartTime().substring(0,16)+"-"+ item.getEndTime().substring(11,16);
        helper.setText(R.id.evaluation_module_order_consult_time,consultTime);
        helper.setText(R.id.evaluation_module_order_consult_type,item.getDiagnosisModeName());

        helper.setText(R.id.evaluation_module_order_consult_remark, TextUtils.isEmpty(item.getComments())?"无":item.getComments());

    }
}
