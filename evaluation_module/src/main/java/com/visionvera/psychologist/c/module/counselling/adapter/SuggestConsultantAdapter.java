package com.visionvera.psychologist.c.module.counselling.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.util.GlideImageLoader;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.SuggestListBean;

import java.util.List;

/**
 * @Desc 推荐咨询师列表适配器
 *
 * @Author yemh
 * @Date 2019-11-01 13:32
 */
public class SuggestConsultantAdapter extends BaseQuickAdapter<SuggestListBean.ResultBean, BaseViewHolder> {
    private Context mContext;
    private CircleImageView ivHead;
    private TextView tvName;
    private TextView tvNumber;
    private TextView tvCity;
    private TextView tvContent;
    private TextView tvType;


    public SuggestConsultantAdapter(Context context, List<SuggestListBean.ResultBean> mlist) {
        super(R.layout.evaluation_module_suggest_item, mlist);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SuggestListBean.ResultBean bean) {
        int position = holder.getLayoutPosition();
        ivHead = holder.getView(R.id.iv_head);

        tvName = holder.getView(R.id.tv_name);

        tvNumber = holder.getView(R.id.tv_number);

        tvCity = holder.getView(R.id.tv_city);


        tvContent = holder.getView(R.id.tv_content);

        tvType = holder.getView(R.id.tv_type);

        String url = bean.getUrl();
        if (!TextUtils.isEmpty(url)) {
            GlideImageLoader.loadDoctorHeaderImage(mContext, url, ivHead);
        }

        String username = bean.getUserName();
        if (!TextUtils.isEmpty(username)) {
            tvName.setText(username);
        }

        int patientNum = bean.getPatientNum();
        tvNumber.setText(patientNum + "人咨询");

        String dictName = bean.getDictName();
        if (!TextUtils.isEmpty(dictName)) {
            tvCity.setText(dictName);
        }

        String titleName = bean.getTitleName();
        if (!TextUtils.isEmpty(titleName)) {
            tvContent.setText(titleName);
        }

        String tags = "";
        List<String> lables = bean.getLableName();
        if (lables != null) {
            for (int i = 0; i < lables.size(); i++) {
                tags = tags + "#" + lables.get(i) + "  ";
            }
        }
        tvType.setText(tags);
    }
}
