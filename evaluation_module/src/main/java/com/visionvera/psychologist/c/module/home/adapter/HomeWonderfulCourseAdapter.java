package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.bean.RecommendCourseBean;

import java.util.List;

/*
* 首页精彩课程adapter
* */
public class HomeWonderfulCourseAdapter extends BaseQuickAdapter<RecommendCourseBean, BaseViewHolder> {
    private Context mContext;

    public HomeWonderfulCourseAdapter(@Nullable List<RecommendCourseBean> data, Context context) {
        super(R.layout.adapter_wonderful_course_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendCourseBean item) {
        ImageView imageView = helper.getView(R.id.adapter_home_wonderful_course_img);

        RequestOptions options = new RequestOptions();
        options.centerCrop()
//                        .priority(Priority.IMMEDIATE) //优先级
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//设置缓存策略
                .transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(8)));//设置四角圆角

        Glide.with(mContext)
                .load(item.masterPicUrl)
                .apply(options)
                .dontAnimate()
                .placeholder(com.visionvera.library.R.drawable.no_banner)
                .error(com.visionvera.library.R.drawable.no_banner)
                .into(imageView);

        helper.setText(R.id.adapter_home_wonderful_course_title,item.description);
    }


}
