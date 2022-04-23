package com.visionvera.psychologist.c.module.EvaluationGauge.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionTypeIntentData;

import java.util.List;


public class SelfAssessmentAdapter1 extends BaseQuickAdapter<QuestionTypeIntentData.Option, BaseViewHolder>{


    public SelfAssessmentAdapter1(@Nullable List<QuestionTypeIntentData.Option> data) {
        super(R.layout.evaluation_module_question_fragment_1_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionTypeIntentData.Option item) {
        String[] letterArray={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        helper.setText(R.id.evaluation_module_question_fragment_1_item_tv,letterArray[helper.getAdapterPosition()%26]+"  "+item.getOptionalValue());

        TextView textView=helper.getView(R.id.evaluation_module_question_fragment_1_item_tv);
        if (item.isSelect()){
            textView.setSelected(true);
        }else{
            textView.setSelected(false);
        }

    }

}
