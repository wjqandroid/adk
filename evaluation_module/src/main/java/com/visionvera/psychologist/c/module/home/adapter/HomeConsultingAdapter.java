package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.bean.HomeConsultingBean;

import java.util.List;

/*
* 首页我要咨询adapter
* */
public class HomeConsultingAdapter extends BaseQuickAdapter<HomeConsultingBean, BaseViewHolder> {

    private Context mContext;

    public HomeConsultingAdapter(@Nullable List<HomeConsultingBean> data, Context context) {
        super(R.layout.tab_item, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeConsultingBean item) {
        TextView nameTv = helper.getView(R.id.tab_item_name);
        nameTv.setText(item.name);

        if(item.isSelect){
            nameTv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            helper.setGone(R.id.tab_item_indicator,false);
        }else {
            nameTv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            helper.setGone(R.id.tab_item_indicator,true);
        }

    }


}
