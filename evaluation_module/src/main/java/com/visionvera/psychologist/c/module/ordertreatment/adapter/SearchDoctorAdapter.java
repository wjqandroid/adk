package com.visionvera.psychologist.c.module.ordertreatment.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.util.GlideImageLoader;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.activity.SearchDoctorActivity;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SearchDoctorBean;

import java.util.List;

/**
 * author:lilongfeng
 * date:2020/1/3
 * 描述:搜索医生的fragment的适配器
 */

public class SearchDoctorAdapter extends BaseQuickAdapter<SearchDoctorBean.ResultBean.StaffInfoListBean, BaseViewHolder> {

    private Context mContext;
    private String mSearchText;

    public SearchDoctorAdapter(@Nullable List<SearchDoctorBean.ResultBean.StaffInfoListBean> data, Context context) {
        super(R.layout.evaluation_module_suggest_item, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchDoctorBean.ResultBean.StaffInfoListBean item) {


        CircleImageView ivHead;

        TextView tvNumber;
        TextView tvCity;
        TextView tvContent;
        TextView tvType;
        ivHead = helper.getView(R.id.iv_head);
        tvNumber = helper.getView(R.id.tv_number);
        tvCity = helper.getView(R.id.tv_city);
        tvContent = helper.getView(R.id.tv_content);
        tvType = helper.getView(R.id.tv_type);
        String url = item.getImageUrl();
        if (!TextUtils.isEmpty(url)) {
            GlideImageLoader.loadDoctorHeaderImage(mContext, url, ivHead);
        }

        if (mContext != null && mContext instanceof SearchDoctorActivity) {
            mSearchText = ((SearchDoctorActivity) mContext).et_content.getText().toString();
        }
        ForegroundColorSpan redSpan = new ForegroundColorSpan(mContext.getResources().getColor(R.color.COLOR_BLUE_3E86FE));

        SpannableStringBuilder builder = new SpannableStringBuilder(item.getUsername());
        int chageTextColor = item.getUsername().indexOf(mSearchText);
        if (chageTextColor != -1) {
            builder.setSpan(redSpan, chageTextColor, chageTextColor + mSearchText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.tv_name, TextUtils.isEmpty(item.getUsername()) ? "" : builder);
        } else {
            helper.setText(R.id.tv_name, item.getUsername());
        }

        int patientNum = item.patientNum;
        tvNumber.setText(patientNum + "人咨询");

        String dictName = item.dictName;
        if (!TextUtils.isEmpty(dictName)) {
            tvCity.setText(dictName);
        }

        String titleName = item.getTitleName();
        if (!TextUtils.isEmpty(titleName)) {
            tvContent.setText(titleName);
        }

        String tags = "";
        List<SearchDoctorBean.ResultBean.StaffInfoListBean.LabelsBean> lables = item.getLabels();
        if (lables != null) {
            for (int i = 0; i < lables.size(); i++) {
                tags = tags + "#" + lables.get(i).getLableName() + "  ";
            }
        }
        tvType.setText(tags);
    }
}
