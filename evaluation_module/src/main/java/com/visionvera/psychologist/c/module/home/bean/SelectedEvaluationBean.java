package com.visionvera.psychologist.c.module.home.bean;

import java.io.Serializable;
import java.util.List;

/*
* 首页精选测评bean
* */
public class SelectedEvaluationBean implements Serializable {
    public int pageIndex;
    public List<DataBean> scaleDictList;

    public class DataBean implements Serializable{
        //    {
//        "smallImgUrl": "//upload/images/dev/1584415029139_SCL90-3.png",
//            "defaultBigImg": "//upload/images/dev/1584415029083_SCL90-3-bb.png",
//            "costPrice": 3,
//            "description": "90项症状清单(Symptom Check-list 90)，又名症状自评量表（Self-reporting Inventory)，有时也叫做Hopkin’s症状清单，是当前使用最为广泛的精神障碍和心理疾病门诊检查量表之一。于1975年编制，其作者是德若伽提斯（L.R.Derogatis)。该量表将协助您从十个方面来了解自己的心理健康程度。",
//            "scaleNum": "XLJK-2019-05-31-",
//            "source": 13,
//            "defaultSmallImg": "http://mhsp-cdn.51vision.com///upload/images/dev/1584415029139_SCL90-3.png",
//            "heartBeans": 10,
//            "limitTime": 20,
//            "dictStatus": 1,
//            "createTime": "2020-07-03 14:38:48.0",
//            "defaultIconStr": "http://mhsp-cdn.51vision.com///upload/images/dev/1584415027744_SCL90-3-b.png",
//            "name": "心理健康症状自评量表",
//            "createUser": 0,
//            "id": 3,
//            "usedNum": 55,
//            "status": 1
//    }

        public String smallImgUrl;
        public String defaultBigImg;
        public int costPrice;
        public String description;
        public String scaleNum;
        public int source;
        public String defaultSmallImg;
        public int heartBeans;
        public int limitTime;
        public int dictStatus;
        public String createTime;
        public String defaultIconStr;
        public String name;
        public int createUser;
        public int id;
        public int usedNum;
        public int status;

        @Override
        public String toString() {
            return "SelectedEvaluationBean{" +
                    "smallImgUrl='" + smallImgUrl + '\'' +
                    ", defaultBigImg='" + defaultBigImg + '\'' +
                    ", costPrice=" + costPrice +
                    ", description='" + description + '\'' +
                    ", scaleNum='" + scaleNum + '\'' +
                    ", source=" + source +
                    ", defaultSmallImg='" + defaultSmallImg + '\'' +
                    ", heartBeans=" + heartBeans +
                    ", limitTime=" + limitTime +
                    ", dictStatus=" + dictStatus +
                    ", createTime='" + createTime + '\'' +
                    ", defaultIconStr='" + defaultIconStr + '\'' +
                    ", name='" + name + '\'' +
                    ", createUser=" + createUser +
                    ", id=" + id +
                    ", usedNum=" + usedNum +
                    ", status=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SelectedEvaluationBean{" +
                "pageIndex=" + pageIndex +
                ", scaleDictList=" + scaleDictList +
                '}';
    }
}
