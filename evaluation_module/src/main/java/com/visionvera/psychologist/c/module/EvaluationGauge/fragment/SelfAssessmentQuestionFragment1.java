package com.visionvera.psychologist.c.module.EvaluationGauge.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentQuestionActivity;
import com.visionvera.psychologist.c.module.EvaluationGauge.adapter.SelfAssessmentAdapter1;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionTypeIntentData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 自评量表的题目的fragment.单选题
 */
public class SelfAssessmentQuestionFragment1 extends QuestionBaseFragment implements OnItemClickListener {

    @BindView(R2.id.evaluation_module_fragment_recyclerview)
    RecyclerView mRecy;


    private static String QUESTION_TYPE1_STRING = "QUESTION_TYPE1_STRING";

    private List<QuestionTypeIntentData.Option> mList = new ArrayList<>();
    private SelfAssessmentAdapter1 mAdapter;

    public static SelfAssessmentQuestionFragment1 getInstance(QuestionTypeIntentData intentData) {
        SelfAssessmentQuestionFragment1 fragment = new SelfAssessmentQuestionFragment1();
        Bundle bundle = new Bundle();
        bundle.putSerializable(QUESTION_TYPE1_STRING, intentData);
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
        mRecy.addItemDecoration(new SpaceItemDecoration(10));
        getIntentData();

    }


    //获取从父activity中得到的单个题目的信息。
    private void getIntentData() {
        mIntentData = (QuestionTypeIntentData) getArguments().getSerializable(QUESTION_TYPE1_STRING);

        mCurrentQuestionNum = mIntentData.getScaleNum();

        mList.addAll(mIntentData.getOptionList());
        mAdapter.notifyDataSetChanged();

    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        for (int i = 0; i < mList.size(); i++) {
            if (i == position) {
                mList.get(i).setSelect(true);
            } else {
                mList.get(i).setSelect(false);
            }
        }
        mAnswerId=mList.get(position).getId();
        mAdapter.notifyDataSetChanged();

        ((SelfAssessmentQuestionActivity)getActivity()).nextQuestionMethod();

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
