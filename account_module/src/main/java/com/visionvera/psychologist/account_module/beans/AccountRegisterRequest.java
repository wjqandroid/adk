package com.visionvera.psychologist.account_module.beans;

public class AccountRegisterRequest {

    /**
     * mobile : 13171728960
     * code : 123456
     * source : 2
     * name : 田亮量
     * cardId : 411481199202010586
     */

    private String mobile;
    private String code;
    private int source;
    private String name;
    private String cardId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
