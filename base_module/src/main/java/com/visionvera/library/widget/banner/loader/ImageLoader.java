package com.visionvera.library.widget.banner.loader;

import android.content.Context;
import android.widget.ImageView;

import com.visionvera.library.widget.ICircleImageView;


public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public ICircleImageView createImageView(Context context) {
        ICircleImageView imageView = new ICircleImageView(context);
        imageView.setAdjustViewBounds(true);
        return imageView;
    }
}
