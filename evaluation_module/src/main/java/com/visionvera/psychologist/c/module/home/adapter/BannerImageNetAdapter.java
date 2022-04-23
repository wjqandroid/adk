package com.visionvera.psychologist.c.module.home.adapter;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.visionvera.psychologist.c.MyApplication;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.home.bean.LocalCustomBean;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.io.File;
import java.util.List;

/**
 * 自定义布局，网络图片
 */
public class BannerImageNetAdapter extends BannerAdapter<LocalCustomBean, BannerImageNetAdapter.ImageHolder> {

    public BannerImageNetAdapter(List<LocalCustomBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);
        //通过裁剪实现圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView, 20);
        }
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, LocalCustomBean data, int position, int size) {
        // 加载应用资源
        Drawable icon = ContextCompat.getDrawable(MyApplication.getInstance(), data.imageRes);
        //网络图片
        //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
        holder.imageView.setBackground(icon);
//        Glide.with(holder.itemView)
//                .load(data.viewType)
////                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
////                .placeholder(R.drawable.default_banner)
//                .into();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageHolder(@NonNull View view) {
            super(view);
            this.imageView = (ImageView) view;
        }
    }


}
