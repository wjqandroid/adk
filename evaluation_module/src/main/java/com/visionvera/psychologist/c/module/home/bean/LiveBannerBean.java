package com.visionvera.psychologist.c.module.home.bean;

import java.util.List;

public class LiveBannerBean {

    /**
     * code : 0
     * msg : 查询成功!
     * result : [{"courseId":505,"courseName":"直播互通测试1","courseDeptId":1,"courseDeptName":"康复科","courseTypeId":3,"courseTypeName":"专题讲座","courseTypeType":2,"description":"疫情介绍。","expertId":0,"expertName":"","expertHospital":"","masterPicUrl":"http://58.30.9.142:34220/upload/temp/2020/02/10/8f0163e9f6d44c65bf9184103fbd644f.jpg","hotStatus":1,"isCollectCourse":false,"isCollectExpert":false,"playModel":2,"liveStatus":2,"videoUrl":"rtmp://haoyisheng.51vision.com/live/liveCourseId505","startTime":"2020-02-10 13:00","endTime":"2020-02-29 00:00","duration":"26580"},{"courseId":476,"courseName":"测试可","courseDeptId":1,"courseDeptName":"康复科","courseTypeId":1,"courseTypeName":"","courseTypeType":0,"description":"课程简介课程简介课程简介","expertId":0,"expertName":"","expertHospital":"","masterPicUrl":"http://58.30.9.142:34220/upload/temp/2019/09/10/436bff08bbd343909356ff3da4fc8de1.jpg","hotStatus":1,"isCollectCourse":false,"isCollectExpert":false,"playModel":1,"liveStatus":-1,"videoUrl":"http://58.30.9.142:34220/upload/temp/2019/09/10/29ebe496bdf8494ca89b17bb81802bb7.mp4","startTime":"2019-09-10 13:45","endTime":"","duration":"0"},{"courseId":475,"courseName":"测试课程","courseDeptId":1,"courseDeptName":"康复科","courseTypeId":1,"courseTypeName":"","courseTypeType":0,"description":"课程简介课程简介课程简介课程简介","expertId":0,"expertName":"","expertHospital":"","masterPicUrl":"http://58.30.9.142:34220/upload//temp/2019/09/10/7a6abf75f1bd4d86abb6107fba32c3d3.jpg","hotStatus":1,"isCollectCourse":false,"isCollectExpert":false,"playModel":1,"liveStatus":-1,"videoUrl":"http://58.30.9.142:34220/upload//temp/2019/09/10/42ad50631eb546ec95c2757c80688194.mp4","startTime":"2019-09-10 11:08","endTime":"","duration":"0"},{"courseId":460,"courseName":"卢本伟","courseDeptId":1,"courseDeptName":"康复科","courseTypeId":2,"courseTypeName":"","courseTypeType":0,"description":"录播","expertId":0,"expertName":"","expertHospital":"","masterPicUrl":"http://58.30.9.142:34220/upload//temp/2019/08/23/f56bc2c2b5714ac6ace25b2cce82106c.png","hotStatus":1,"isCollectCourse":false,"isCollectExpert":false,"playModel":1,"liveStatus":-1,"videoUrl":"http://58.30.9.142:34220/upload//temp/2019/08/23/c373d06140ac4e6a944655ba43fae30c.mp4","startTime":"2019-08-23 17:17","endTime":"","duration":"0"},{"courseId":459,"courseName":"刘传政","courseDeptId":1,"courseDeptName":"康复科","courseTypeId":2,"courseTypeName":"","courseTypeType":0,"description":"刘传政","expertId":0,"expertName":"","expertHospital":"","masterPicUrl":"http://58.30.9.142:34220/upload//temp/2019/08/23/3b6be8e4f5f243b0adda00a12e654f1f.png","hotStatus":1,"isCollectCourse":false,"isCollectExpert":false,"playModel":2,"liveStatus":2,"videoUrl":"rtmp://haoyisheng.51vision.com/live/190916","startTime":"2019-09-16 09:50","endTime":"2019-10-31 00:00","duration":"64210"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * courseId : 505
         * courseName : 直播互通测试1
         * courseDeptId : 1
         * courseDeptName : 康复科
         * courseTypeId : 3
         * courseTypeName : 专题讲座
         * courseTypeType : 2
         * description : 疫情介绍。
         * expertId : 0
         * expertName :
         * expertHospital :
         * masterPicUrl : http://58.30.9.142:34220/upload/temp/2020/02/10/8f0163e9f6d44c65bf9184103fbd644f.jpg
         * hotStatus : 1
         * isCollectCourse : false
         * isCollectExpert : false
         * playModel : 2
         * liveStatus : 2
         * videoUrl : rtmp://haoyisheng.51vision.com/live/liveCourseId505
         * startTime : 2020-02-10 13:00
         * endTime : 2020-02-29 00:00
         * duration : 26580
         */

        private int courseId;
        private String courseName;
        private int courseDeptId;
        private String courseDeptName;
        private int courseTypeId;
        private String courseTypeName;
        private int courseTypeType;
        private String description;
        private int expertId;
        private String expertName;
        private String expertHospital;
        private String masterPicUrl;
        private int hotStatus;
        private boolean isCollectCourse;
        private boolean isCollectExpert;
        private int playModel;
        private int liveStatus;
        private String videoUrl;
        private String startTime;
        private String endTime;
        private String duration;

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getCourseDeptId() {
            return courseDeptId;
        }

        public void setCourseDeptId(int courseDeptId) {
            this.courseDeptId = courseDeptId;
        }

        public String getCourseDeptName() {
            return courseDeptName;
        }

        public void setCourseDeptName(String courseDeptName) {
            this.courseDeptName = courseDeptName;
        }

        public int getCourseTypeId() {
            return courseTypeId;
        }

        public void setCourseTypeId(int courseTypeId) {
            this.courseTypeId = courseTypeId;
        }

        public String getCourseTypeName() {
            return courseTypeName;
        }

        public void setCourseTypeName(String courseTypeName) {
            this.courseTypeName = courseTypeName;
        }

        public int getCourseTypeType() {
            return courseTypeType;
        }

        public void setCourseTypeType(int courseTypeType) {
            this.courseTypeType = courseTypeType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getExpertId() {
            return expertId;
        }

        public void setExpertId(int expertId) {
            this.expertId = expertId;
        }

        public String getExpertName() {
            return expertName;
        }

        public void setExpertName(String expertName) {
            this.expertName = expertName;
        }

        public String getExpertHospital() {
            return expertHospital;
        }

        public void setExpertHospital(String expertHospital) {
            this.expertHospital = expertHospital;
        }

        public String getMasterPicUrl() {
            return masterPicUrl;
        }

        public void setMasterPicUrl(String masterPicUrl) {
            this.masterPicUrl = masterPicUrl;
        }

        public int getHotStatus() {
            return hotStatus;
        }

        public void setHotStatus(int hotStatus) {
            this.hotStatus = hotStatus;
        }

        public boolean isIsCollectCourse() {
            return isCollectCourse;
        }

        public void setIsCollectCourse(boolean isCollectCourse) {
            this.isCollectCourse = isCollectCourse;
        }

        public boolean isIsCollectExpert() {
            return isCollectExpert;
        }

        public void setIsCollectExpert(boolean isCollectExpert) {
            this.isCollectExpert = isCollectExpert;
        }

        public int getPlayModel() {
            return playModel;
        }

        public void setPlayModel(int playModel) {
            this.playModel = playModel;
        }

        public int getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(int liveStatus) {
            this.liveStatus = liveStatus;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }
}
