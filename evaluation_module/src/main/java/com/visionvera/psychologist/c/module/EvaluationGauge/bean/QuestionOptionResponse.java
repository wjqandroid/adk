package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 未登录获取量表问题与答案Bean
 */
public class QuestionOptionResponse implements Serializable {
    private Object optionList;
    private int sameOptionalFlag;
    private int scaleId;
    private int totalQuestion;
    private List<QuestionListBean> questionList;

    public Object getOptionList() {
        return optionList;
    }

    public void setOptionList(Object optionList) {
        this.optionList = optionList;
    }

    public int getSameOptionalFlag() {
        return sameOptionalFlag;
    }

    public void setSameOptionalFlag(int sameOptionalFlag) {
        this.sameOptionalFlag = sameOptionalFlag;
    }

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public List<QuestionListBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionListBean> questionList) {
        this.questionList = questionList;
    }

    public static class QuestionListBean implements Serializable{
        private int dimensionId;
        private Object dimensionList;
        private int id;
        private int isForward;
        private Object optionalList;
        private String question;
        private Object questionList;
        private int scaleId;
        private int scaleNum;
        private int type;
        private Object updatetime;
        private List<QuestionListBean.OptionalListInfoBean> optionalListInfo;

        public int getDimensionId() {
            return dimensionId;
        }

        public void setDimensionId(int dimensionId) {
            this.dimensionId = dimensionId;
        }

        public Object getDimensionList() {
            return dimensionList;
        }

        public void setDimensionList(Object dimensionList) {
            this.dimensionList = dimensionList;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsForward() {
            return isForward;
        }

        public void setIsForward(int isForward) {
            this.isForward = isForward;
        }

        public Object getOptionalList() {
            return optionalList;
        }

        public void setOptionalList(Object optionalList) {
            this.optionalList = optionalList;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public Object getQuestionList() {
            return questionList;
        }

        public void setQuestionList(Object questionList) {
            this.questionList = questionList;
        }

        public int getScaleId() {
            return scaleId;
        }

        public void setScaleId(int scaleId) {
            this.scaleId = scaleId;
        }

        public int getScaleNum() {
            return scaleNum;
        }

        public void setScaleNum(int scaleNum) {
            this.scaleNum = scaleNum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
            this.updatetime = updatetime;
        }

        public List<QuestionListBean.OptionalListInfoBean> getOptionalListInfo() {
            return optionalListInfo;
        }

        public void setOptionalListInfo(List<QuestionListBean.OptionalListInfoBean> optionalListInfo) {
            this.optionalListInfo = optionalListInfo;
        }

        public static class OptionalListInfoBean implements Serializable{
            /**
             * forwardPoint : 1
             * optionalValue : 没有或很少时间
             * id : 1
             * backwardPoint : 4
             */

            private int forwardPoint;
            private String optionalValue;
            private int id;
            private int backwardPoint;

            public int getForwardPoint() {
                return forwardPoint;
            }

            public void setForwardPoint(int forwardPoint) {
                this.forwardPoint = forwardPoint;
            }

            public String getOptionalValue() {
                return optionalValue;
            }

            public void setOptionalValue(String optionalValue) {
                this.optionalValue = optionalValue;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBackwardPoint() {
                return backwardPoint;
            }

            public void setBackwardPoint(int backwardPoint) {
                this.backwardPoint = backwardPoint;
            }

            @Override
            public String toString() {
                return "OptionalListInfoBean{" +
                        "forwardPoint=" + forwardPoint +
                        ", optionalValue='" + optionalValue + '\'' +
                        ", id=" + id +
                        ", backwardPoint=" + backwardPoint +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "QuestionListBean{" +
                    "dimensionId=" + dimensionId +
                    ", dimensionList=" + dimensionList +
                    ", id=" + id +
                    ", isForward=" + isForward +
                    ", optionalList=" + optionalList +
                    ", question='" + question + '\'' +
                    ", questionList=" + questionList +
                    ", scaleId=" + scaleId +
                    ", scaleNum=" + scaleNum +
                    ", type=" + type +
                    ", updatetime=" + updatetime +
                    ", optionalListInfo=" + optionalListInfo +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "QuestionOptionResponse{" +
                "optionList=" + optionList +
                ", sameOptionalFlag=" + sameOptionalFlag +
                ", scaleId=" + scaleId +
                ", totalQuestion=" + totalQuestion +
                ", questionList=" + questionList +
                '}';
    }
}
