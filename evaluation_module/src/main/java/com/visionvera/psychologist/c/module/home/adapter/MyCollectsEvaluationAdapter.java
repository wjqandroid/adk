package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.widget.ICircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;

import java.util.List;

public class MyCollectsEvaluationAdapter extends BaseQuickAdapter<MyEvaluationBean.ResultBean.DataListBean, BaseViewHolder> {

    private Context mContext;
    public MyCollectsEvaluationAdapter(@Nullable List<MyEvaluationBean.ResultBean.DataListBean> data,Context context) {
        super(R.layout.evaluation_module_fragment_selected_item,data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyEvaluationBean.ResultBean.DataListBean item) {

        helper.setText(R.id.tv_depression_name,item.getName())
                .setText(R.id.tv_depression_number,item.getUsedNum() + "人已测");

        ICircleImageView iv=helper.getView(R.id.evaluation_module_place_holder1_iv);
        Glide.with(mContext).load(item.getDefaultIconStr()).into(iv);


    }
}
