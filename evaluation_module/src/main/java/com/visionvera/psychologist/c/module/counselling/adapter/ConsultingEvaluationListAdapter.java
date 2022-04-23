package com.visionvera.psychologist.c.module.counselling.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.AvatarBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.utils.FiveStarView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * @Classname:ConsultingEvaluationListAdapter
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0
 * @describe： 咨询师与医生评价
 */

public class ConsultingEvaluationListAdapter extends BaseQuickAdapter<EvaluateListBean.ResultDTO.DataListDTO, BaseViewHolder> {
    private Context mContext;
    private List<AvatarBean> ConsultingEvaluationList = new ArrayList<>();
    List<EvaluateListBean.ResultDTO.DataListDTO> dataList = new ArrayList<>();
    //    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    public static final String REGEX_MOBILE = "^1\\d{10}$";

    public ConsultingEvaluationListAdapter(Context context, @Nullable List<EvaluateListBean.ResultDTO.DataListDTO> data) {
        super(R.layout.item_consulting_evaluation, data);
        this.mContext = context;
    }


    @Override
    protected void convert(@NotNull BaseViewHolder helper, EvaluateListBean.ResultDTO.DataListDTO item) {
        TextView tvName = helper.getView(R.id.consulting_evaluation_name);
        FiveStarView fiveStarView = helper.getView(R.id.consulting_evaluation_fiveStarView);
        TextView tvSatisfied = helper.getView(R.id.consulting_evaluation_satisfied);//非常满意
        TextView tvDate = helper.getView(R.id.consulting_evaluation_date);
        TextView tvContent = helper.getView(R.id.consulting_evaluation_content);


        //评价人姓名 receiverServiceName    如果是手机号隐藏4位
        String receiverServiceName = item.getReceiverServiceName();
        if (receiverServiceName.matches(REGEX_MOBILE)) {
            String phoneNumber = receiverServiceName.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            tvName.setText(phoneNumber);
        } else {
            tvName.setText(item.getReceiverServiceName());
        }

        //评价
        tvContent.setText(item.getEvaluateContent());
        //满意
        int evaluateSatisfaction = item.getEvaluateSatisfaction();
        if (evaluateSatisfaction == 1) {
            tvSatisfied.setText("不满意");
        } else if (evaluateSatisfaction == 2) {
            tvSatisfied.setText("有点不满意");
        } else if (evaluateSatisfaction == 3) {
            tvSatisfied.setText("比较满意");
        } else if (evaluateSatisfaction == 4) {
            tvSatisfied.setText("满意");
        } else if (evaluateSatisfaction == 5) {
            tvSatisfied.setText("非常满意");
        }
        //星级
        fiveStarView.setStar(item.getEvaluateSatisfaction());
        //评价日期
        tvDate.setText(item.getCreatetime());
    }
}