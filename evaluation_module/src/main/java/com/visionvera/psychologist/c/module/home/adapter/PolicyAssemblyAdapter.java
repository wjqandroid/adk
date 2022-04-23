package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.bean.LocalCustomBean;

import java.util.List;

//政策汇编列表
//跳转浏览word、pdf
//2021.09.26


public class PolicyAssemblyAdapter extends BaseQuickAdapter<LocalCustomBean, BaseViewHolder> {

    private Context mContext;
    int itemWidth;

    public PolicyAssemblyAdapter(@Nullable List<LocalCustomBean> data, Context context) {
        super(R.layout.evaluation_module_policy_assembly_item, data);
        mContext = context;


    }

    @Override
    protected void convert(BaseViewHolder helper, LocalCustomBean item) {

        helper.setText(R.id.tv_name, item.title);
//        ImageView iv_icon = helper.itemView.findViewById(R.id.iv_icon);
//        iv_icon.setImageDrawable(mContext.getResources().getDrawable(item.iconId));

    }


}
