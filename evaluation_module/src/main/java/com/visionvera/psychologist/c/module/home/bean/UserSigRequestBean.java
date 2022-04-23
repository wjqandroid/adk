package com.visionvera.psychologist.c.module.home.bean;

public class UserSigRequestBean {

    /**
     * userId : 3
     */

    private String userId;
    private String Authorization;

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
