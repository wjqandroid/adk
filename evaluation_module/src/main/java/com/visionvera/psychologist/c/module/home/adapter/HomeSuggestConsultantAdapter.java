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
import com.visionvera.psychologist.c.module.counselling.bean.SuggestListBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/*
* 首页推荐心理咨询师adapter
* */
public class HomeSuggestConsultantAdapter extends BaseQuickAdapter<SuggestListBean.ResultBean, BaseViewHolder> {

    private Context mContext;
    private CircleImageView ivHead;
    private TextView tvName;
    private TextView tvNumber;
    private TextView tvCity;
    private TextView tvContent;

    public HomeSuggestConsultantAdapter(@Nullable List<SuggestListBean.ResultBean> data, Context context) {
        super(R.layout.adapter_suggest_consultant_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SuggestListBean.ResultBean bean) {
        ivHead = holder.getView(R.id.iv_head);
        tvName = holder.getView(R.id.tv_name);
        tvNumber = holder.getView(R.id.tv_number);
        tvCity = holder.getView(R.id.tv_city);
        tvContent = holder.getView(R.id.tv_content);

        String url = bean.getUrl();
        GlideImageLoader.loadDoctorHeaderImage(mContext, url, ivHead);

        String username = bean.getUserName();
        if (!TextUtils.isEmpty(username)) {
            tvName.setText(username);
        }

        int patientNum = bean.getPatientNum();
        tvNumber.setText(patientNum + "");

        String dictName = bean.getDictName();
        if (!TextUtils.isEmpty(dictName)) {
            tvCity.setText(dictName);
        }

        String titleName = bean.getTitleName();
        if (!TextUtils.isEmpty(titleName)) {
            tvContent.setText(titleName);
        }

        List<String> lables = bean.getLableName();
        if (lables != null && lables.size() > 0) {
            TagFlowLayout tagFlowLayout = holder.getView(R.id.adapter_suggest_consultant_specialty);

            initFlowLayout(tagFlowLayout,lables);
        }
    }

    private List<String> tagList_find = new ArrayList<>();
    private TagAdapter<String> tagAdapter_find;
    private void initFlowLayout(TagFlowLayout flowLayout, List<String> specialty){
        if(tagList_find != null && tagList_find.size() > 0){
            tagList_find.clear();
        }

        tagList_find.addAll(specialty);

        tagAdapter_find = new TagAdapter<String>(tagList_find){
            @Override
            public View getView(FlowLayout parent, int position, String discoverResponseBean) {
                LinearLayout ll = (LinearLayout) ((Activity)mContext).getLayoutInflater().inflate(R.layout.item_tag_flow,
                        flowLayout, false);
                TextView tv_name = ll.findViewById(R.id.tag_flow_name);
                tv_name.setText(discoverResponseBean);
                return ll;
            }
        };

        flowLayout.setAdapter(tagAdapter_find);
        tagAdapter_find.notifyDataChanged();
    }

}
