package com.visionvera.psychologist.c.module.counselling.bean;

public class NewOrderConsultRequest {

    /**
     * patientId : 77
     * doctorId : 983
     * appScheduleId : 86
     * hospitalId : 549
     * hospitalDeptId : 1
     * meetingRoomId : 1
     * typeId : 16
     */

    //患者ID
    private String patientId;
    //申请方机构ID
    private int hospitalId;
    //医生ID
    private int doctorId;
    //被预约人ID（同医生ID userId）
    private int passivityOperatorId;
    //申请方会议室ID
    private int meetingRoomId;
    //日程ID（调取日程排班返回的ID）
    private String appScheduleId;
    //科室ID
    private int hospitalDeptId;
    //1 会诊 16诊疗 19案例督导 20 团体治疗 23心理咨询
    private int typeId;
    //备注
    private String comments;
    //patComplaints 	String	N	主诉(问题类型)
    private String patComplaints;
    //上传文件返回的ID 多个用“,”分隔
    private String uploadId;
    //诊疗形式 1会诊方式:交互式(视联网)，2离线式(线下门诊),3移动式(手机远程)' 4文字咨询,5语音，6视频
    private int diagnosisMode;
    //预约申请单来源1网格员（B端）|2个人用户(C端)
    private int appFrom;
    private int consultQuestionType;



    public NewOrderConsultRequest(String patientId, int doctorId, String appScheduleId, int hospitalId, int meetingRoomId, int typeId, int diagnosisMode, int appFrom, int passivityOperatorId, String patComplaints) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appScheduleId = appScheduleId;
        this.hospitalId = hospitalId;
        this.meetingRoomId = meetingRoomId;
        this.typeId = typeId;
        this.diagnosisMode = diagnosisMode;
        this.appFrom = appFrom;
        this.passivityOperatorId = passivityOperatorId;
        this.patComplaints = patComplaints;
        this.consultQuestionType = consultQuestionType;
    }
    public int getConsultQuestionType() {
        return consultQuestionType;
    }

    public void setConsultQuestionType(int consultQuestionType) {
        this.consultQuestionType = consultQuestionType;
    }
    public String getProblemType() {
        return patComplaints;
    }

    public void setProblemType(String problemType) {
        this.patComplaints = problemType;
    }

    public int getDiagnosisMode() {
        return diagnosisMode;
    }

    public void setDiagnosisMode(int diagnosisMode) {
        this.diagnosisMode = diagnosisMode;
    }

    public int getAppFrom() {
        return appFrom;
    }

    public void setAppFrom(int appFrom) {
        this.appFrom = appFrom;
    }

    public int getPassivityOperatorId() {
        return passivityOperatorId;
    }

    public void setPassivityOperatorId(int passivityOperatorId) {
        this.passivityOperatorId = passivityOperatorId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPatComplaints() {
        return patComplaints;
    }

    public void setPatComplaints(String patComplaints) {
        this.patComplaints = patComplaints;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }



    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppScheduleId() {
        return appScheduleId;
    }

    public void setAppScheduleId(String appScheduleId) {
        this.appScheduleId = appScheduleId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getHospitalDeptId() {
        return hospitalDeptId;
    }

    public void setHospitalDeptId(int hospitalDeptId) {
        this.hospitalDeptId = hospitalDeptId;
    }

    public int getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(int meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
