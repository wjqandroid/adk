package com.visionvera.psychologist.account_module.beans;

public class PhoneLoginRequest {

    /**
     * username : 17600195594
     * smsCode : 257485
     */

    private String username;
    private String smsCode;
    private String  terminalCode="app_c_200";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
