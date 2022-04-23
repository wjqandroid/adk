package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.bean.SelectedEvaluationBean;

import java.util.List;

/*
* 首页精选测评adapter
* */
public class HomeSelectedEvaluationAdapter extends BaseQuickAdapter<SelectedEvaluationBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public HomeSelectedEvaluationAdapter(@Nullable List<SelectedEvaluationBean.DataBean> data, Context context) {
        super(R.layout.adapter_selected_evaluation_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectedEvaluationBean.DataBean item) {
        helper.setText(R.id.adapter_home_selected_evaluation_name,item.name);

        LinearLayout layout = helper.getView(R.id.adapter_home_selected_evaluation_root);
        Glide.with(mContext)
                .asBitmap()
                .load(item.defaultIconStr)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(resource);
                        layout.setBackground(drawable);
                    }

                });
    }


}
