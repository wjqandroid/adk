package com.visionvera.psychologist.account_module.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.commonbean.AccountBean;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.psychologist.account_module.util.AccountManager;

// 实现接口
@Route(path = ARouterConstant.Account.accountModuleSetvice)
public class AccountService implements IAccountService {

    private Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void clearAccountInfo() {
        AccountManager.getInstence().clearAccountInfo();
    }

    @Override
    public AccountBean getGetAccountInfo() {
        return AccountManager.getInstence().getGetAccountInfo();
    }

    @Override
    public void updateAccountInfo(@NonNull AccountBean usetInfo) {
        AccountManager.getInstence().updateAccountInfo(usetInfo);
    }
}