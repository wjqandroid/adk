package com.visionvera.library.widget.dialog;


import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.library.R;
import com.visionvera.library.widget.dialog.bottompopup.ProblemTypeBean;


import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @Classname:ProblemTypeListAdapter
 * @author:haohuizhao
 * @Date:2021/10/29 15:59
 * @Version：v1.0
 * @describe： 预约咨询的问题类型
 */

public class ProblemTypeListAdapter extends BaseQuickAdapter<ProblemTypeBean, BaseViewHolder> {
    private Context mContext;


    public ProblemTypeListAdapter(Context context, @Nullable List<ProblemTypeBean> data) {
        super(R.layout.item_problem_type_pop, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProblemTypeBean item) {
        TextView problem_type_text = helper.getView(R.id.problem_type_text);
        problem_type_text.setText(item.name);


        if (item.is_select) {
            problem_type_text.setBackgroundResource(R.drawable.bg_ebf3ff_r34_3e86fe);
            problem_type_text.setTextColor(Color.parseColor("#3E86FE"));
        }
        if (item.is_select == false) {
            problem_type_text.setBackgroundResource(R.drawable.bg_fff0f0f0_r34_fff0f0f0);
            problem_type_text.setTextColor(Color.parseColor("#ff666666"));
        }


    }
}