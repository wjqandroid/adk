package com.visionvera.psychologist.c.module.EvaluationGauge.fragment;

import com.visionvera.library.base.BaseFragment;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionTypeIntentData;

/**
* author:lilongfeng
* date:2019/11/11
* 描述:答题fragment的基类
*/

public abstract class QuestionBaseFragment extends BaseFragment {

    //当前的题目是第几道题 从1开始
    public int mCurrentQuestionNum;
    //单选，多选，mAnswerId大于0；     语音输入，mAnswerId=-1；    没有选择或者没有填写，mAnswerId=0；
    public int mAnswerId;
    public QuestionTypeIntentData mIntentData;

}
