package com.visionvera.psychologist.c.module.ordertreatment.bean;

/**
 * @author 刘传政
 * @date 2019-07-01 11:25
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class SubmitOrderRequestBean {
    //患者ID
    public Integer patientId;
    //申请方机构ID
    public Integer hospitalId;
    //医生ID
    public Integer doctorId;
    //申请方会议室ID
    public Integer meetingRoomId;
    //日程ID（调取日程排班返回的ID）
    public Integer appScheduleId;
    //科室ID
    public Integer hospitalDeptId;
    //1 会诊 16诊疗 19案例督导 20 团体治疗
    public Integer typeId;
    //备注
    public String comments;
    //主诉
    public String patComplaints;
    //上传文件返回的ID 多个用“,”分隔
    public String uploadId;
    //被预约人ID（同医生ID）
    public String passivityOperatorId;
    //诊疗形式  1会诊方式:交互式(视联网)，2离线式(线下门诊),3移动式(手机远程)',
    public String diagnosisMode;
    //'预约申请单来源:1网格员（B端）|2个人用户(C端)',
    public int appFrom = 2;
    //问题类型
    public String problemType;


    public SubmitOrderRequestBean(Integer patientId, Integer hospitalId, Integer doctorId,
                                  Integer meetingRoomId, Integer appScheduleId, Integer typeId,
                                  String passivityOperatorId, String diagnosisMode, String uploadId,
                                  String comments,String problemType) {
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
        this.meetingRoomId = meetingRoomId;
        this.appScheduleId = appScheduleId;
        this.typeId = typeId;
        this.passivityOperatorId = passivityOperatorId;
        this.diagnosisMode = diagnosisMode;
        this.uploadId = uploadId;
        this.comments = comments;
        this.problemType = problemType;
    }
}
