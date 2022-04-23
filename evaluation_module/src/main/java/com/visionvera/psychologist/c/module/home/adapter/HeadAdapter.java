package com.visionvera.psychologist.c.module.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.HomeFragment;

import java.util.List;

public class HeadAdapter extends BaseQuickAdapter<HomeFragment.HeadItem, BaseViewHolder> {

    private Context mContext;
    int itemWidth;

    public HeadAdapter(@Nullable List<HomeFragment.HeadItem> data, Context context) {
        super(R.layout.evaluation_module_head_item, data);
        mContext = context;
        int screenWidth = ScreenUtils.getScreenWidth();
        itemWidth = screenWidth / 4;

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeFragment.HeadItem item) {

        //横向的列表.等分4份.其余的可滑动出来显示
        helper.itemView.getLayoutParams().width = itemWidth;

        helper.setText(R.id.tv_name, item.name);
        ImageView iv_icon = helper.itemView.findViewById(R.id.iv_icon);
        iv_icon.setImageDrawable(mContext.getResources().getDrawable(item.iconId));

    }


}
