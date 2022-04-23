package com.visionvera.psychologist.c.module.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.util.GlideImageLoader;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorResponseBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/*
* 首页推荐医生专家adapter
* */
public class HomeSuggestStaffAdapter extends BaseQuickAdapter<DoctorResponseBean, BaseViewHolder> {

    private Context mContext;
    private CircleImageView ivHead;
    private TextView tvName;
    private TextView tvNumber;
    private TextView tvCity;
    private TextView tvContent;

    public HomeSuggestStaffAdapter(@Nullable List<DoctorResponseBean> data, Context context) {
        super(R.layout.adapter_suggest_consultant_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, DoctorResponseBean bean) {
        ivHead = holder.getView(R.id.iv_head);
        tvName = holder.getView(R.id.tv_name);
//        tvNumber = holder.getView(R.id.tv_number);
//        tvCity = holder.getView(R.id.tv_city);
        tvContent = holder.getView(R.id.tv_content);

        holder.setGone(R.id.adapter_consultant_line,true);

        String url = bean.getImageUrl();
        GlideImageLoader.loadDoctorHeaderImage(mContext, url, ivHead);

        String username = bean.getUsername();
        if (!TextUtils.isEmpty(username)) {
            tvName.setText(username);
        }

        String hospitalName = bean.getHospitalName();
        if (!TextUtils.isEmpty(hospitalName)) {
            tvContent.setText(hospitalName);
        }

        List<DoctorResponseBean.LabelsBean> lables = bean.getLabels();
        if (lables != null && lables.size() > 0) {
            TagFlowLayout tagFlowLayout = holder.getView(R.id.adapter_suggest_consultant_specialty);

            initFlowLayout(tagFlowLayout,lables);
        }
    }

    private List<DoctorResponseBean.LabelsBean> tagList_find = new ArrayList<>();
    private TagAdapter<DoctorResponseBean.LabelsBean> tagAdapter_find;
    private void initFlowLayout(TagFlowLayout flowLayout, List<DoctorResponseBean.LabelsBean> specialty){
        if(tagList_find != null && tagList_find.size() > 0){
            tagList_find.clear();
        }

        tagList_find.addAll(specialty);

        tagAdapter_find = new TagAdapter<DoctorResponseBean.LabelsBean>(tagList_find){
            @Override
            public View getView(FlowLayout parent, int position, DoctorResponseBean.LabelsBean discoverResponseBean) {
                LinearLayout ll = (LinearLayout) ((Activity)mContext).getLayoutInflater().inflate(R.layout.item_tag_flow,
                        flowLayout, false);
                TextView tv_name = ll.findViewById(R.id.tag_flow_name);
                tv_name.setText(discoverResponseBean.getLableName());
                return ll;
            }
        };

        flowLayout.setAdapter(tagAdapter_find);
        tagAdapter_find.notifyDataChanged();
    }

}
