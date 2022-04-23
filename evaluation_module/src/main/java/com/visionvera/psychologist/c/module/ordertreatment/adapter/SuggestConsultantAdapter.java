package com.visionvera.psychologist.c.module.ordertreatment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.util.GlideImageLoader;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsResponseBean;

/**
 * @author: 刘传政
 * @date: 2020-01-02 15:35
 * QQ:1052374416
 * 作用:预约诊疗的机构推荐适配器
 * 注意事项:
 */
public class SuggestConsultantAdapter extends BaseQuickAdapter<RecommentHospitalsResponseBean.ResultBean, BaseViewHolder> {
    private Context mContext;
    private CircleImageView ivHead;

    private TextView tvName;
    private TextView tv_hospital_level;
    private TextView tv_hospital_quality;


    public SuggestConsultantAdapter(Context context) {
        super(R.layout.evaluation_module_order_treatement_suggest_item, null);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, RecommentHospitalsResponseBean.ResultBean bean) {
        int position = holder.getLayoutPosition();
        ivHead = holder.getView(R.id.iv_head);

        tvName = holder.getView(R.id.tv_name);
        tv_hospital_level = holder.getView(R.id.tv_hospital_level);
        tv_hospital_quality = holder.getView(R.id.tv_hospital_quality);


        String url = bean.getImageUrl();
        if (!TextUtils.isEmpty(url)) {
            GlideImageLoader.loadHospitalIconImage(mContext, url, ivHead);
        }
        String hospitalName = bean.getName();
        if (!TextUtils.isEmpty(hospitalName)) {
            tvName.setText(hospitalName);
        }
        if (!TextUtils.isEmpty(bean.getLevelName())) {
            tv_hospital_level.setText(bean.getLevelName());
        }
        if (!TextUtils.isEmpty(bean.getNatureName())) {
            tv_hospital_quality.setText(bean.getNatureName());
        }

    }
}
