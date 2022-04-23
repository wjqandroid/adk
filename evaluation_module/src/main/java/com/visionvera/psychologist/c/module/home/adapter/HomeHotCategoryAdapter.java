package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.bean.HotCategoriesBean;

import java.util.List;

/*
* 首页热门分类adapter
* */
public class HomeHotCategoryAdapter extends BaseQuickAdapter<HotCategoriesBean, BaseViewHolder> {

    private Context mContext;

    public HomeHotCategoryAdapter(@Nullable List<HotCategoriesBean> data, Context context) {
        super(R.layout.adapter_hot_category_item, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HotCategoriesBean item) {
        helper.setText(R.id.adapter_home_hot_category_name,item.name);
    }


}
