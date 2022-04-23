package com.visionvera.psychologist.c.module.home.bean;

import java.io.Serializable;

/*
* 新版首页精彩课程bean
* */
public class RecommendCourseBean implements Serializable {
    public int courseId;
    /*
    * 课程名称
    * */
    public String courseName;
    public int courseDeptId;
    /*
     * 专业名称
     * */
    public String courseDeptName;
    public int courseTypeId;
    /*
    * 类型名称
    * */
    public String courseTypeName;
    /*
    * 1：课程类，2：会议类
    * */
    public int courseTypeType;
    /*
    * 课程描述
    * */
    public String description;
    /*
    * 专家id
    * */
    public int expertId;
    /*
    * 专家名称
    * */
    public String expertName;
    /*
    * 专家所属医院
    * */
    public String expertHospital;
    /*
    * 课程主图
    * */
    public String masterPicUrl;
    /*
    * 热推状态：0非热推，1热推
    * */
    public int hotStatus;
    /*
    * 用户是否收藏课程 false:否
    * */
    public boolean isCollectCourse;
    /*
    * 用户是否关注专家 false:否
    * */
    public boolean isCollectExpert;
    /*
    * 视频类型：1录播，2直播
    * */
    public int playModel;
    /*
    * 直播状态，-1录播，0：未开播，1：开播，2：暂停
    * */
    public int liveStatus;
    /*
    * 视频播放地址
    * */
    public String videoUrl;
    /*
    * 视频开始时间（录播为课程创建时间）
    * */
    public String startTime;
    /*
    * 视频开始时间
    * */
    public String endTime;
    /*
    * 视频时长（分钟）
    * */
    public String duration;

    @Override
    public String toString() {
        return "RecommendCourseBean{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDeptId=" + courseDeptId +
                ", courseDeptName='" + courseDeptName + '\'' +
                ", courseTypeId=" + courseTypeId +
                ", courseTypeName='" + courseTypeName + '\'' +
                ", courseTypeType=" + courseTypeType +
                ", description='" + description + '\'' +
                ", expertId=" + expertId +
                ", expertName='" + expertName + '\'' +
                ", expertHospital='" + expertHospital + '\'' +
                ", masterPicUrl='" + masterPicUrl + '\'' +
                ", hotStatus=" + hotStatus +
                ", isCollectCourse='" + isCollectCourse + '\'' +
                ", isCollectExpert='" + isCollectExpert + '\'' +
                ", playModel=" + playModel +
                ", liveStatus=" + liveStatus +
                ", videoUrl=" + videoUrl +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
