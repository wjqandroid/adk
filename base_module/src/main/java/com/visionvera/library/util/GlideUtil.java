package com.visionvera.library.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * Glide公共类
 * */
public class GlideUtil {

    /**
     * 实现图片圆角
     * 加载网络图片
     * */
    public static void pictureRoundedCorners(Context context, String url, int roundRadius, ImageView imageView){
        RequestOptions options = new RequestOptions();
        options.centerCrop()
//                        .priority(Priority.IMMEDIATE) //优先级
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//设置缓存策略
                .transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(roundRadius)));//设置四角圆角

        Glide.with(context)
                .load(url)
                .apply(options)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 实现图片圆角
     * 加载本地图片
     * */
    public static void localPictureRoundedCorners(Context context, int resId, int roundRadius, ImageView imageView){
        RequestOptions options = new RequestOptions();
        options.centerCrop()
//                        .priority(Priority.IMMEDIATE) //优先级
//                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//设置缓存策略
                .transform(new MultiTransformation<>(new CenterCrop(),new RoundedCorners(roundRadius)));//设置四角圆角

        Glide.with(context)
                .load(resId)
                .apply(options)
                .dontAnimate()
                .into(imageView);


    }

}
