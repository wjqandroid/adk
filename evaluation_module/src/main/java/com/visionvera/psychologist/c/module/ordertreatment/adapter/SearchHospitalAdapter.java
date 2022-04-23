package com.visionvera.psychologist.c.module.ordertreatment.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.activity.SearchDoctorActivity;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SearchDoctorBean;

import java.util.List;

public class SearchHospitalAdapter extends BaseQuickAdapter<SearchDoctorBean.ResultBean.HospitalInfoListBean, BaseViewHolder> {

    private Context mContext;
    private String mSearchText;

    public SearchHospitalAdapter(@Nullable List<SearchDoctorBean.ResultBean.HospitalInfoListBean> data, Context context) {
        super(R.layout.evaluation_module_item_search_doctor_for_hospital,data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchDoctorBean.ResultBean.HospitalInfoListBean item) {
        ImageView headIv=helper.getView(R.id.hospital_iv_head);

        Glide.with(mContext).load(item.getImageUrl())
                .placeholder(R.drawable.base_module_default_hospital)
                .dontAnimate()
                .into(headIv);

        helper.setText(R.id.evaluation_module_hospital_level,item.getLevelName());
        helper.setText(R.id.evaluation_module_hospital_type,item.getHospitalNatureName());


        if (mContext != null && mContext instanceof SearchDoctorActivity) {
            mSearchText = ((SearchDoctorActivity) mContext).et_content.getText().toString();
        }


        ForegroundColorSpan redSpan = new ForegroundColorSpan(mContext.getResources().getColor(R.color.COLOR_BLUE_3E86FE));

        SpannableStringBuilder builder = new SpannableStringBuilder(item.getHospitalName());
        int chageTextColor = item.getHospitalName().indexOf(mSearchText);
        if (chageTextColor != -1) {
            builder.setSpan(redSpan, chageTextColor, chageTextColor
                            + mSearchText.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.evaluation_module_hospital_name, TextUtils.isEmpty(item.getHospitalName()) ? "" : builder);
        } else {
            helper.setText(R.id.evaluation_module_hospital_name, item.getHospitalName());
        }

        TextView tvCity = helper.getView(R.id.tv_city);
        String dictName = item.dictName;
        if (!TextUtils.isEmpty(dictName)) {
            tvCity.setText(dictName);
        }

    }
}
