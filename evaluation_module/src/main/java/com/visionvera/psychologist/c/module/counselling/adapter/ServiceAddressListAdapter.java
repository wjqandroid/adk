package com.visionvera.psychologist.c.module.counselling.adapter;

import android.content.Context;
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
 * @Classname:ServiceAddressListAdapter
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0
 * @describe： 选择视联网网点
 */

public class ServiceAddressListAdapter extends BaseQuickAdapter<AvatarBean, BaseViewHolder> {
    private Context mContext;
    private List<AvatarBean> ConsultingEvaluationList = new ArrayList<>();

    public ServiceAddressListAdapter(Context context, @Nullable List<AvatarBean> data) {
        super(R.layout.item_service_address, data);
        this.mContext = context;
    }


    @Override
    protected void convert(@NotNull BaseViewHolder helper, AvatarBean item) {


        TextView tv_room = helper.getView(R.id.tv_service_room);
        TextView tv_address = helper.getView(R.id.tv_service_address);
        ImageView iv_selected = helper.getView(R.id.iv_service_address_select);


        if (item.is_selected) {
            iv_selected.setImageResource(R.mipmap.checkbox_checked);
        }
        if (item.is_selected == false) {
            iv_selected.setImageResource(R.mipmap.checkbox_normal);
        }

    }

}