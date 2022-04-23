package com.visionvera.library.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.visionvera.library.R;
import com.visionvera.library.widget.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .placeholder(R.drawable.no_banner)
                .error(R.drawable.no_banner)
                .centerCrop()
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.priority(Priority.HIGH);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.no_banner)
                .error(R.drawable.no_banner)
                .apply(options)
                .into(imageView);//发表图片
    }

    public static void loadHospitalIconImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.priority(Priority.HIGH);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.base_module_default_hospital)
                .apply(options)
                .into(imageView);//发表图片
    }

    public static void loadDoctorHeaderImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.priority(Priority.HIGH);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.base_module_default_man)
                .apply(options)
                .into(imageView);//发表图片
    }

    public static void loadRoundImage(Context context, String url, ImageView imageView) {
        RoundedCorners roundedCorners = new RoundedCorners(6);
        RequestOptions options = new RequestOptions();
        options.bitmapTransform(roundedCorners);
        options.priority(Priority.HIGH);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.no_banner)
                .apply(options)
                .into(imageView);//发表图片
    }
}
