package com.visionvera.psychologist.c.module.ordertreatment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.util.GlideImageLoader;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListResponseBean;

import java.util.List;

/**
 * @author: 刘传政
 * @date: 2020-01-03 18:03
 * QQ:1052374416
 * 作用:预约诊疗-推荐咨询师列表
 * 注意事项:
 */
public class RecommendConsultantListAdapter extends BaseQuickAdapter<DoctorListResponseBean.ResultBean, BaseViewHolder> {
    private Context mContext;
    private CircleImageView ivHead;

    private TextView tvName;

    private TextView tvTitle;

    private TextView tvContent;

    private TextView tvType;

    public RecommendConsultantListAdapter(Context context, List<DoctorListResponseBean.ResultBean> dataList) {
        super(R.layout.evaluation_module_recommend_consultant_item, dataList);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, DoctorListResponseBean.ResultBean bean) {

        ivHead = holder.getView(R.id.iv_head);

        tvName = holder.getView(R.id.tv_name);

        tvTitle = holder.getView(R.id.tv_title);

        tvContent = holder.getView(R.id.tv_content);

        tvType = holder.getView(R.id.tv_type);

        String url = bean.getImageUrl();
        if (!TextUtils.isEmpty(url)) {
            GlideImageLoader.loadDoctorHeaderImage(mContext, url, ivHead);
        }

        String username = bean.getUsername();
        if (!TextUtils.isEmpty(username)) {
            tvName.setText(username);
        }


        String dictName = bean.getTitleName();
        if (!TextUtils.isEmpty(dictName)) {
            tvTitle.setText(dictName);
        }

        String titleName = bean.getHospitalName();
        if (!TextUtils.isEmpty(titleName)) {
            tvContent.setText(titleName);
        }

        String tags = "擅长：";
        if (bean.getLabels() != null) {
            for (int i = 0; i < bean.getLabels().size(); i++) {
                if (i != bean.getLabels().size() - 1) {
                    tags += bean.getLabels().get(i).getLableName() + "#";
                } else {
                    tags += bean.getLabels().get(i).getLableName();
                }
            }
        }
        tvType.setText(tags);
    }
}
