package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;

import java.util.List;

public class HotArticleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;


    public HotArticleAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.evaluation_module_hot_article_item, data);
        mContext = context;


    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }


}
