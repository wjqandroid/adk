package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.widget.ICircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.bean.HotEvaluationBean;

import java.util.List;

public class HotCepingAdapter extends BaseQuickAdapter<HotEvaluationBean.ResultBean.ScaleDictListBean, BaseViewHolder> {

    private Context mContext;

    public HotCepingAdapter(@Nullable List<HotEvaluationBean.ResultBean.ScaleDictListBean> data, Context context) {
        super(R.layout.evaluation_module_hot_ceping_item, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HotEvaluationBean.ResultBean.ScaleDictListBean item) {
        helper.setText(R.id.tv_name, item.getName());

        ICircleImageView iv = helper.getView(R.id.evaluation_module_place_holder1_iv);
        Glide.with(mContext).load(item.getDefaultIconStr()).into(iv);

    }


}
