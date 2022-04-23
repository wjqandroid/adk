package com.visionvera.psychologist.c.module.counselling.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.AvatarBean;
import com.visionvera.psychologist.c.utils.FiveStarView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * @Classname:setStarsEvaluationAdapter
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0
 * @describe： 设置五星评价
 */

public class StarsEvaluationAdapter extends BaseQuickAdapter<AvatarBean, BaseViewHolder> {
    private Context mContext;
    private List<AvatarBean> ConsultingEvaluationList = new ArrayList<>();

    public StarsEvaluationAdapter(Context context, @Nullable List<AvatarBean> data) {
        super(R.layout.evaluation_module_item_set_stars_svaluation, data);
        this.mContext = context;
    }


    @Override
    protected void convert(@NotNull BaseViewHolder helper, AvatarBean item) {

        //
        ImageView iv = helper.getView(R.id.iv_five_stars);
        iv.setVisibility(View.VISIBLE);
        if (item.is_selected) {
            iv.setImageResource(R.mipmap.img_five_stars);
//            iv.setImageResource(R.mipmap.sytem_collect);
        }
        if (item.is_selected == false) {
            iv.setImageResource(R.mipmap.img_five_stars_grey);
//            iv.setImageResource(R.mipmap.sytem_collect_grey);
        }


    }
}