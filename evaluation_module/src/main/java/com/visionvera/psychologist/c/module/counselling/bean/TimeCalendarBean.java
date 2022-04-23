package com.visionvera.psychologist.c.module.counselling.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author lilongfeng
 * date 2019/8/6
 */

public class TimeCalendarBean {

    /**
     * code : 0
     * errMsg : ok
     * result : [{"date":"2019-08-09","dateList":[{"day":5,"end":"2019-08-09 11:00:00","id":94,"name":"可预约","start":"2019-08-09 10:00:00"}]}]
     */

    private int code;
    private String errMsg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * date : 2019-08-09
         * dateList : [{"day":5,"end":"2019-08-09 11:00:00","id":94,"name":"可预约","start":"2019-08-09 10:00:00"}]
         */

        private String date;
        private List<DateListBean> dateList;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<DateListBean> getDateList() {
            return dateList;
        }

        public void setDateList(List<DateListBean> dateList) {
            this.dateList = dateList;
        }

        public static class DateListBean implements Serializable {
            /**
             * day : 5
             * end : 2019-08-09 11:00:00
             * id : 94
             * name : 可预约
             * start : 2019-08-09 10:00:00
             */

            private int day;
            private String end;
            private int id;
            private String name;
            private String start;

            //另外单独加入的字段，识别是否被选中
            private boolean selected;
            //另外单独加入的字段，识别是否是默认不可选
            private boolean isDefault = true;
            //默认的显示时段,展示默认方格时显示用
            private String detaultTime = "";

            public String getDetaultTime() {
                return detaultTime;
            }

            public void setDetaultTime(String detaultTime) {
                this.detaultTime = detaultTime;
            }

            public boolean isDefault() {
                return isDefault;
            }

            public void setDefault(boolean aDefault) {
                isDefault = aDefault;
            }

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }
        }
    }
}
