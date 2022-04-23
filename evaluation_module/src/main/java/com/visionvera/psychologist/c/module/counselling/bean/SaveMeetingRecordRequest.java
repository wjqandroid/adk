package com.visionvera.psychologist.c.module.counselling.bean;

public class SaveMeetingRecordRequest {


    /**
     * businessId : 249
     * businessType : 1
     * meetingId : 1
     */

    private int businessId;
    private int businessType;
    private int meetingId;

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }
}
