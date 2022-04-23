package com.visionvera.psychologist.c.module.counselling.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;


/**
 * @Desc 测评类别适配器
 * @Author yemh
 * @Date 2019-11-01 13:32
 */
public class EvaluationTypeAdapter extends BaseQuickAdapter<EvaluationTypeAdapter.EvaluationTypeBean, BaseViewHolder> {
    private Context mContext;
    private ImageView ivEvaluationType;
    private TextView tvEvaluationType;
    private RelativeLayout rl_type_bg;

    public EvaluationTypeAdapter(Context context) {
        super(R.layout.evaluation_module_home_item, null);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, EvaluationTypeAdapter.EvaluationTypeBean bean) {
        int position = holder.getLayoutPosition();
        ivEvaluationType = holder.getView(R.id.iv_evaluation_type);
        rl_type_bg = holder.getView(R.id.rl_type_bg);

        tvEvaluationType = holder.getView(R.id.tv_evaluation_type);

        ivEvaluationType.setBackground(mContext.getResources().getDrawable(bean.getEvaluationTypeImg()));
        rl_type_bg.setBackground(mContext.getResources().getDrawable(bean.evaluationTypeBg));
        tvEvaluationType.setText(bean.getEvaluationType());
    }

    public static class EvaluationTypeBean {
        public String evaluationType;
        public int evaluationTypeId;
        public int evaluationTypeImg;
        public int evaluationTypeBg;

        public String getEvaluationType() {
            return evaluationType;
        }

        public void setEvaluationType(String evaluationType) {
            this.evaluationType = evaluationType;
        }

        public int getEvaluationTypeId() {
            return evaluationTypeId;
        }

        public void setEvaluationTypeId(int evaluationTypeId) {
            this.evaluationTypeId = evaluationTypeId;
        }

        public int getEvaluationTypeImg() {
            return evaluationTypeImg;
        }

        public void setEvaluationTypeImg(int evaluationTypeImg) {
            this.evaluationTypeImg = evaluationTypeImg;
        }
    }
}
