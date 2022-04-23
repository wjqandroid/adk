package com.visionvera.psychologist.account_module.beans;

/**
 * @author 刘传政
 * @date 2019-06-04 18:00
 * QQ:1052374416
 * telephone:18501231486
 * 作用:
 * 注意事项:
 */
public class AccountLoginRequestBean {

    public String username;
    public String password;
    public String clientId;
    private String mobileOs = "android";
    private String  terminalCode="app_c_200";

    public AccountLoginRequestBean(String username, String password, String clientId) {
        this.username = username;
        this.password = password;
        this.clientId = clientId;
    }
}
