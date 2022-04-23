package com.visionvera.psychologist.c.module.usercenter.bean;

public class FeedBackRequest {


    /**
     * os : 型号
     * osVersion : 版本
     * problem : 1
     * otherDesc : 详情描述
     * uploadId : 7960,7961
     * contactWay : 13191630000
     */

    private String os;
    private String osVersion;
    private int problem;
    private String otherDesc;
    private String uploadId;
    private String contactWay;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public int getProblem() {
        return problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }
}
