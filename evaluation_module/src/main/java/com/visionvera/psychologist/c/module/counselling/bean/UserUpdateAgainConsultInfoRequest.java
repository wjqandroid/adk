package com.visionvera.psychologist.c.module.counselling.bean;

public class UserUpdateAgainConsultInfoRequest {

    /**
     * id : 126
     * receiverMeetingRoomId : 1
     * receiverHospitalId : 1
     */

    private int id;
    private int receiverMeetingRoomId;
    private int receiverHospitalId;
    private int diagnosisMode;

    public int getDiagnosisMode() {
        return diagnosisMode;
    }

    public void setDiagnosisMode(int diagnosisMode) {
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

    public int getReceiverHospitalId() {
        return receiverHospitalId;
    }

    public void setReceiverHospitalId(int receiverHospitalId) {
        this.receiverHospitalId = receiverHospitalId;
    }
}
