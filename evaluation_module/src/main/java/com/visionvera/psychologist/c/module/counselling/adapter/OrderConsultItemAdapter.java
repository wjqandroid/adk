package com.visionvera.psychologist.c.module.counselling.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultListBean;

import java.util.List;

public class OrderConsultItemAdapter extends BaseQuickAdapter<OrderConsultListBean.ResultBean.DataListBean, BaseViewHolder> {

    public OrderConsultItemAdapter(@Nullable List<OrderConsultListBean.ResultBean.DataListBean> data) {
        super(R.layout.evaluation_module_fragment_order_consult_adapter_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderConsultListBean.ResultBean.DataListBean item) {
        helper.setText(R.id.evaluation_module_order_number, item.getAppNum());
        helper.setText(R.id.tvOrderStatus, item.getStageName());
        helper.setText(R.id.evaluation_module_counselor, TextUtils.isEmpty(item.getDoctorName()) ? "" : item.getDoctorName());
        String consultTime="";
        if (item.getStartTime()!=null && item.getStartTime().length()>=16 && item.getEndTime()!=null && item.getEndTime().length()>=16){
            consultTime= item.getStartTime().substring(0,16)+"-"+ item.getEndTime().substring(11,16);
        }
        helper.setText(R.id.evaluation_module_order_consult_time,consultTime);

        helper.setText(R.id.evaluation_module_order_consult_type,item.getDiagnosisModeName());
        helper.setText(R.id.evaluation_module_order_consult_remark, TextUtils.isEmpty(item.getComments())?"无":item.getComments());

    }
}
