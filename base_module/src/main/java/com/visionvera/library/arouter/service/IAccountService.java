package com.visionvera.library.arouter.service;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.visionvera.library.arouter.commonbean.AccountBean;

// 声明接口,其他组件通过接口来调用服务
public interface IAccountService extends IProvider {
    /**
     * 清除用户信息
     */
    void clearAccountInfo();

    /**
     * 获取用户信息
     */
    AccountBean getGetAccountInfo();
    /**
     * 更新用户信息
     * 最好是先调用getUserInfo方法，获得UsetInfo，再选择性的改变一个属性
     */
    void updateAccountInfo(@NonNull AccountBean usetInfo);

}
