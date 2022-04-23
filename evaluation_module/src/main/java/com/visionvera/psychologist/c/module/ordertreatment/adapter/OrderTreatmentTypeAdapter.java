package com.visionvera.psychologist.c.module.ordertreatment.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;

/**
 * @Desc 测评类别适配器
 * @Author yemh
 * @Date 2019-11-01 13:32
 */
public class OrderTreatmentTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context mContext;
    private ImageView ivEvaluationType;
    private TextView tvEvaluationType;
    private Integer[] typeList = new Integer[]{R.drawable.evaluation_module_ordertreatment_main_1,
            R.drawable.evaluation_module_ordertreatment_main_2,
            R.drawable.evaluation_module_ordertreatment_main_3,
            R.drawable.evaluation_module_ordertreatment_main_4,
            R.drawable.evaluation_module_ordertreatment_main_5,
            R.drawable.evaluation_module_ordertreatment_main_6};


    public OrderTreatmentTypeAdapter(Context context) {
        super(R.layout.evaluation_module_treatment_type_item, null);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, String bean) {
        int position = holder.getLayoutPosition();
        ivEvaluationType = holder.getView(R.id.iv_evaluation_type);

        tvEvaluationType = holder.getView(R.id.tv_evaluation_type);

        ivEvaluationType.setBackground(mContext.getResources().getDrawable(typeList[position]));
        tvEvaluationType.setText(bean);
    }
}
