package com.visionvera.psychologist.c.module.EvaluationGauge.fragment;

import android.os.Bundle;

import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionTypeIntentData;
import com.visionvera.psychologist.c.widget.BubbleSeekBar;

import butterknife.BindView;

/**
* author:lilongfeng
* date:2019/11/21
* 描述:单选题，横向滑动选择的题型。
*/

public class SelfAssessmentQuestionFragment4 extends QuestionBaseFragment {


    @BindView(R2.id.evaluation_module_seekbar)
    BubbleSeekBar seekbar;



    private static String QUESTION_TYPE4_STRING = "QUESTION_TYPE4_STRING";

    public static SelfAssessmentQuestionFragment4 getInstance(QuestionTypeIntentData intentData) {
        SelfAssessmentQuestionFragment4 fragment = new SelfAssessmentQuestionFragment4();
        Bundle bundle=new Bundle();
        bundle.putSerializable(QUESTION_TYPE4_STRING,intentData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_question_4;
    }

    @Override
    protected void initYourself() {
        getIntentData();
    }

    private void getIntentData() {
        mIntentData = (QuestionTypeIntentData) getArguments().getSerializable(QUESTION_TYPE4_STRING);

        mCurrentQuestionNum = mIntentData.getScaleNum();


        seekbar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                mAnswerId=mIntentData.getOptionList().get(progress).getId();
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

            }
        });


    }
}
