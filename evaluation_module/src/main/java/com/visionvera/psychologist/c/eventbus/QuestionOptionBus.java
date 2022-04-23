package com.visionvera.psychologist.c.eventbus;

import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleQuestionOptionResponse;

/**
* author:lilongfeng
* date:2020/3/24
* 描述:获取题目和题目选项的接口数据，要传到做题页面。
*/
public class QuestionOptionBus {

    private QuestionOptionResponse response;

    public QuestionOptionBus(QuestionOptionResponse response) {
        this.response = response;
    }

    public QuestionOptionResponse getResponse() {
        return response;
    }

    public void setResponse(QuestionOptionResponse response) {
        this.response = response;
    }
}
