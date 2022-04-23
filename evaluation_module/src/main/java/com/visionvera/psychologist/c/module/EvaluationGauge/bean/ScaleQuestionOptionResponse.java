package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.io.Serializable;
import java.util.List;

public class ScaleQuestionOptionResponse implements Serializable {


    /**
     * code : 0
     * errMsg : 获取量表问题以及选项
     * result : {"optionList":null,"questionList":[{"dimensionId":2,"dimensionList":null,"id":1,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得闷闷不乐，情绪低沉。","questionList":null,"scaleId":0,"scaleNum":1,"type":1,"updatetime":null},{"dimensionId":2,"dimensionList":null,"id":2,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得一天之中早晨最好。","questionList":null,"scaleId":0,"scaleNum":2,"type":1,"updatetime":null},{"dimensionId":2,"dimensionList":null,"id":3,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我一阵阵哭出来或觉得想哭。","questionList":null,"scaleId":0,"scaleNum":3,"type":1,"updatetime":null},{"dimensionId":2,"dimensionList":null,"id":4,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我晚上睡眠不好。","questionList":null,"scaleId":0,"scaleNum":4,"type":1,"updatetime":null},{"dimensionId":2,"dimensionList":null,"id":5,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我吃得跟平常一样多。","questionList":null,"scaleId":0,"scaleNum":5,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":6,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我与异性密切接触时和以往一样感到愉快。","questionList":null,"scaleId":0,"scaleNum":6,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":7,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我发觉我的体重在下降。","questionList":null,"scaleId":0,"scaleNum":7,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":8,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我有便秘的苦恼。","questionList":null,"scaleId":0,"scaleNum":8,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":9,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我心跳比平时快。","questionList":null,"scaleId":0,"scaleNum":9,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":10,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我无缘无故地感到疲乏。","questionList":null,"scaleId":0,"scaleNum":10,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":11,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我的头脑跟平常一样清楚。","questionList":null,"scaleId":0,"scaleNum":11,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":12,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得做经常做的事情并没有困难。","questionList":null,"scaleId":0,"scaleNum":12,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":13,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得不安而平静不下来。","questionList":null,"scaleId":0,"scaleNum":13,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":14,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我对将来抱有希望。","questionList":null,"scaleId":0,"scaleNum":14,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":15,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我比平常容易生气激动。","questionList":null,"scaleId":0,"scaleNum":15,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":16,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得做出决定是容易的。","questionList":null,"scaleId":0,"scaleNum":16,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":17,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得自己是个有用的人，有人需要我。","questionList":null,"scaleId":0,"scaleNum":17,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":18,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我的生活过得很有意思。","questionList":null,"scaleId":0,"scaleNum":18,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":19,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我认为如果我死了，别人会生活得好些。","questionList":null,"scaleId":0,"scaleNum":19,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":20,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"平常感兴趣的事我仍然感兴趣。","questionList":null,"scaleId":0,"scaleNum":20,"type":1,"updatetime":null}],"sameOptionalFlag":1,"scaleId":1,"totalQuestion":20}
     */

    private int code;
    private String errMsg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * optionList : null
         * questionList : [{"dimensionId":2,"dimensionList":null,"id":1,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得闷闷不乐，情绪低沉。","questionList":null,"scaleId":0,"scaleNum":1,"type":1,"updatetime":null},{"dimensionId":2,"dimensionList":null,"id":2,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得一天之中早晨最好。","questionList":null,"scaleId":0,"scaleNum":2,"type":1,"updatetime":null},{"dimensionId":2,"dimensionList":null,"id":3,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我一阵阵哭出来或觉得想哭。","questionList":null,"scaleId":0,"scaleNum":3,"type":1,"updatetime":null},{"dimensionId":2,"dimensionList":null,"id":4,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我晚上睡眠不好。","questionList":null,"scaleId":0,"scaleNum":4,"type":1,"updatetime":null},{"dimensionId":2,"dimensionList":null,"id":5,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我吃得跟平常一样多。","questionList":null,"scaleId":0,"scaleNum":5,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":6,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我与异性密切接触时和以往一样感到愉快。","questionList":null,"scaleId":0,"scaleNum":6,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":7,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我发觉我的体重在下降。","questionList":null,"scaleId":0,"scaleNum":7,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":8,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我有便秘的苦恼。","questionList":null,"scaleId":0,"scaleNum":8,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":9,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我心跳比平时快。","questionList":null,"scaleId":0,"scaleNum":9,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":10,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我无缘无故地感到疲乏。","questionList":null,"scaleId":0,"scaleNum":10,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":11,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我的头脑跟平常一样清楚。","questionList":null,"scaleId":0,"scaleNum":11,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":12,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得做经常做的事情并没有困难。","questionList":null,"scaleId":0,"scaleNum":12,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":13,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得不安而平静不下来。","questionList":null,"scaleId":0,"scaleNum":13,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":14,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我对将来抱有希望。","questionList":null,"scaleId":0,"scaleNum":14,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":15,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我比平常容易生气激动。","questionList":null,"scaleId":0,"scaleNum":15,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":16,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得做出决定是容易的。","questionList":null,"scaleId":0,"scaleNum":16,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":17,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我觉得自己是个有用的人，有人需要我。","questionList":null,"scaleId":0,"scaleNum":17,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":18,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我的生活过得很有意思。","questionList":null,"scaleId":0,"scaleNum":18,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":19,"isForward":1,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"我认为如果我死了，别人会生活得好些。","questionList":null,"scaleId":0,"scaleNum":19,"type":1,"updatetime":null},{"dimensionId":0,"dimensionList":null,"id":20,"isForward":0,"optionalList":null,"optionalListInfo":[{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}],"question":"平常感兴趣的事我仍然感兴趣。","questionList":null,"scaleId":0,"scaleNum":20,"type":1,"updatetime":null}]
         * sameOptionalFlag : 1
         * scaleId : 1
         * totalQuestion : 20
         */

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
            /**
             * dimensionId : 2
             * dimensionList : null
             * id : 1
             * isForward : 1
             * optionalList : null
             * optionalListInfo : [{"forwardPoint":1,"optionalValue":"没有或很少时间","id":1,"backwardPoint":4},{"forwardPoint":2,"optionalValue":"小部分时间","id":2,"backwardPoint":3},{"forwardPoint":3,"optionalValue":"相当多时间","id":3,"backwardPoint":2},{"forwardPoint":4,"optionalValue":"绝大部分或全部时间","id":4,"backwardPoint":1}]
             * question : 我觉得闷闷不乐，情绪低沉。
             * questionList : null
             * scaleId : 0
             * scaleNum : 1
             * type : 1
             * updatetime : null
             */

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
            private List<OptionalListInfoBean> optionalListInfo;

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

            public List<OptionalListInfoBean> getOptionalListInfo() {
                return optionalListInfo;
            }

            public void setOptionalListInfo(List<OptionalListInfoBean> optionalListInfo) {
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
            }
        }
    }
}
