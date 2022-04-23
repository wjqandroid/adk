package com.visionvera.psychologist.c.module.counselling.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarBean;

import java.util.List;

public class OrderConsultTimeAdapter extends BaseQuickAdapter<TimeCalendarBean.ResultBean.DateListBean, BaseViewHolder> {


    private final Context mContext;

    public OrderConsultTimeAdapter(Context context, @Nullable List<TimeCalendarBean.ResultBean.DateListBean> data) {
        super(R.layout.evaluation_module_item_orderconsult_time_adapter,data);
        mContext = context;

    }


    @Override
    protected void convert(BaseViewHolder helper, TimeCalendarBean.ResultBean.DateListBean item) {
        helper.setText(R.id.evaluation_module_item_order_consult_time_tv, item.getDetaultTime());
        if (item.isDefault()) {
            //默认的时段,不可点击
            helper.setTextColor(R.id.evaluation_module_item_order_consult_time_tv, mContext.getResources().getColor(R.color.COLOR_GRAY_999999));
            helper.setBackgroundResource(R.id.evaluation_module_item_order_consult_time_tv, R.drawable.evaluation_module_calendar_btn_gray_corner_bg);
        }else{
            if (item.isSelected()) {
                helper.setTextColor(R.id.evaluation_module_item_order_consult_time_tv, mContext.getResources().getColor(R.color.COLOR_BLUE_3E86FE));
                helper.setBackgroundResource(R.id.evaluation_module_item_order_consult_time_tv, R.drawable.evaluation_module_calendar_btn_blue_corner_linebg);
            } else {
                helper.setTextColor(R.id.evaluation_module_item_order_consult_time_tv, mContext.getResources().getColor(R.color.COLOR_BLACK_333333));
                helper.setBackgroundResource(R.id.evaluation_module_item_order_consult_time_tv, R.drawable.evaluation_module_calendar_btn_gray_corner_linebg);
            }
        }


    }
}
