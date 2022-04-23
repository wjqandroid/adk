package com.visionvera.library.eventbus.commonbean;

import com.visionvera.library.arouter.commonbean.AccountBean;

public class LoginEventBus {

    AccountBean accountBean = new AccountBean();

    public AccountBean getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(AccountBean accountBean) {
        this.accountBean = accountBean;
    }
}
