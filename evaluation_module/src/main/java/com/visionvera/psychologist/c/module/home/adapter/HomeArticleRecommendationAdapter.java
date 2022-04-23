package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.util.GlideUtil;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.bean.RecommendArticleBean;

import java.util.List;

/*
* 首页文章推荐adapter
* */
public class HomeArticleRecommendationAdapter extends BaseQuickAdapter<RecommendArticleBean, BaseViewHolder> {

    private Context mContext;

    public HomeArticleRecommendationAdapter(@Nullable List<RecommendArticleBean> data, Context context) {
        super(R.layout.adapter_article_recommendation_item, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendArticleBean item) {
        helper.setText(R.id.adapter_article_recommendation_title,item.title);
        helper.setText(R.id.adapter_article_recommendation_author,"作者：" + item.authorName);

        ImageView imageView = helper.getView(R.id.adapter_article_recommendation_img);
        GlideUtil.pictureRoundedCorners(mContext,item.coverImageUrls,4,imageView);

    }


}
