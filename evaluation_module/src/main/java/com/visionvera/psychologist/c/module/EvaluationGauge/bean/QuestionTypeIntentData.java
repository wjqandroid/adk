package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.io.Serializable;
import java.util.List;


/**
 * 答题fragment的页面传递进入的intent 的bean。
 */
public class QuestionTypeIntentData implements Serializable {
    //题目id
    private int id;
    //题目内容
    private String question;
    //题目序号
    private int scaleNum;
    //题目总量
    private int totalQuestion;

    private int isForward;

    public int getIsForward() {
        return isForward;
    }

    public void setIsForward(int isForward) {
        this.isForward = isForward;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getScaleNum() {
        return scaleNum;
    }

    public void setScaleNum(int scaleNum) {
        this.scaleNum = scaleNum;
    }

    public List<QuestionTypeIntentData.Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<QuestionTypeIntentData.Option> optionList) {
        this.optionList = optionList;
    }

    private List<QuestionTypeIntentData.Option> optionList;

    public class Option implements Serializable {
        //选项id
        private int id;
        //选项内容
        private String optionalValue;
        //是否选中
        private boolean isSelect;

        private int forwardPoint;

        private int backwardPoint;

        public int getForwardPoint() {
            return forwardPoint;
        }

        public void setForwardPoint(int forwardPoint) {
            this.forwardPoint = forwardPoint;
        }

        public int getBackwardPoint() {
            return backwardPoint;
        }

        public void setBackwardPoint(int backwardPoint) {
            this.backwardPoint = backwardPoint;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOptionalValue() {
            return optionalValue;
        }

        public void setOptionalValue(String optionalValue) {
            this.optionalValue = optionalValue;
        }
    }
}
