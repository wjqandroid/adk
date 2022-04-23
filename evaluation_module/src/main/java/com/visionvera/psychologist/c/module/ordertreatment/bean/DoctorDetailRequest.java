package com.visionvera.psychologist.c.module.ordertreatment.bean;

/**
 * @author 刘传政
 * @date 2020-01-06 16:03
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class DoctorDetailRequest {
    /**
     * staffId : 1261
     */

    private int staffId;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
