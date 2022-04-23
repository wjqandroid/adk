package com.visionvera.psychologist.c.module.EvaluationGauge.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.adapter.SelfAssessmentAdapter1;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionTypeIntentData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author:lilongfeng
 * date:2019/11/7
 * 描述:自评量表的题目的fragment.多选题
 */


public class SelfAssessmentQuestionFragment3 extends QuestionBaseFragment implements OnItemClickListener {

    @BindView(R2.id.evaluation_module_fragment_recyclerview)
    RecyclerView mRecy;

    @BindView(R2.id.evaluation_module_fragment_question_title)
    TextView question_title;

    private List<QuestionTypeIntentData.Option> mList = new ArrayList<>();
    private SelfAssessmentAdapter1 mAdapter;

    private static String QUESTION_TYPE3_STRING = "QUESTION_TYPE3_STRING";

    public static SelfAssessmentQuestionFragment3 getInstance(QuestionTypeIntentData intentData) {
        SelfAssessmentQuestionFragment3 fragment = new SelfAssessmentQuestionFragment3();
        Bundle bundle = new Bundle();
        bundle.putSerializable(QUESTION_TYPE3_STRING, intentData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_question_1;
    }

    @Override
    protected void initYourself() {
        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SelfAssessmentAdapter1(mList);
        mAdapter.setOnItemClickListener(this);
        mRecy.setAdapter(mAdapter);

        getIntentData();
    }

    //获取从父activity中得到的单个题目的信息。
    private void getIntentData() {
        mIntentData = (QuestionTypeIntentData) getArguments().getSerializable(QUESTION_TYPE3_STRING);

        mCurrentQuestionNum = mIntentData.getScaleNum();

        question_title.setText(mIntentData.getQuestion());
        SpannableMethod((mIntentData.getScaleNum() + "/" + mIntentData.getTotalQuestion()).length()
                ,mIntentData.getScaleNum() + "/" + mIntentData.getTotalQuestion()+"  "+mIntentData.getQuestion());
        if (mIntentData.getOptionList() != null) {
            mList.addAll(mIntentData.getOptionList());
        }

        mAdapter.notifyDataSetChanged();

    }

    private void SpannableMethod(int scaleNumLength,String allText) {
        //在textview中设置不同的字体颜色  https://blog.csdn.net/mq2553299/article/details/78033581
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(allText);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#3E86FE"));
        builder.setSpan(foregroundColorSpan, 0,scaleNumLength , Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        question_title.setMovementMethod(LinkMovementMethod.getInstance());
        question_title.setText(builder);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        mList.get(position).setSelect(!mList.get(position).isSelect());

        mAdapter.notifyDataSetChanged();

        for (int i = 0; i < mList.size(); i++) {

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //fragment销毁后重新载入，mlist会重复添加。所以需要及时清空。
        if (mList != null) {
            mList.clear();
        }
    }

}
