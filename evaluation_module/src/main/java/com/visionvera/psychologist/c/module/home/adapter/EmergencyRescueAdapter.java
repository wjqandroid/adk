package com.visionvera.psychologist.c.module.home.adapter;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleResponseBean;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @Classname:EmergencyRescueAdapter
 * @author:haohuizhao
 * @Date:2022/03/25 11:06
 * @Version：v1.0
 * @describe：：紧急救助
 */

public class EmergencyRescueAdapter extends BaseQuickAdapter<TabTitleResponseBean.ResultBean, BaseViewHolder> {
    private Context mContext;
    private String type;


    public EmergencyRescueAdapter(Context context, @Nullable List<TabTitleResponseBean.ResultBean> data) {
        super(R.layout.evaluation_module_item_emergency_rescuet, data);
        this.mContext = context;
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, TabTitleResponseBean.ResultBean item) {
        TextView tv_institutions_name = helper.getView(R.id.tv_institutions_name);
        TextView tv_work_time = helper.getView(R.id.tv_work_time);
        TextView tv_work_phone = helper.getView(R.id.tv_work_phone);
        ImageView iv_call = helper.getView(R.id.iv_call);
        tv_institutions_name.setText(item.getKeyId());
        tv_work_time.setText(item.getValue());
        tv_work_phone.setText(item.getRemark());

    }
}