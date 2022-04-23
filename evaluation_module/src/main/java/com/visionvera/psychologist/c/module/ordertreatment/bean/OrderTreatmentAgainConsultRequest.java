package com.visionvera.psychologist.c.module.ordertreatment.bean;

/**
 * @author 刘传政
 * @date 2019-06-24 14:18
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class OrderTreatmentAgainConsultRequest {

    /**
     * id : 126
     * receiverMeetingRoomId : 1
     * receicerOrgId : 1
     */

    private int id;
    private int receiverMeetingRoomId;
    private int receiverHospitalId;
    //模式
    private int diagnosisMode;

    public int getReceiverHospitalId() {
        return receiverHospitalId;
    }

    public void setReceiverHospitalId(int receiverHospitalId) {
        this.receiverHospitalId = receiverHospitalId;
    }

    public int getDiagnosisMode() {
        return diagnosisMode;
    }

    public void setDiagnosisMode(int diagnosisMode) {
        this.diagnosisMode = diagnosisMode;
    }

    public OrderTreatmentAgainConsultRequest(int id, int receiverMeetingRoomId, int receiverHospitalId, int diagnosisMode) {
        this.id = id;
        this.receiverMeetingRoomId = receiverMeetingRoomId;
        this.receiverHospitalId = receiverHospitalId;
        this.diagnosisMode = diagnosisMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverMeetingRoomId() {
        return receiverMeetingRoomId;
    }

    public void setReceiverMeetingRoomId(int receiverMeetingRoomId) {
        this.receiverMeetingRoomId = receiverMeetingRoomId;
    }


}
