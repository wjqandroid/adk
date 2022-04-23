package com.visionvera.psychologist.account_module.util;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.visionvera.library.arouter.commonbean.AccountBean;
import com.visionvera.library.base.Constant;

import java.lang.reflect.Type;
import java.util.List;


/**
 * @author 刘传政
 * @date 2019-09-16 17:03
 * QQ:1052374416
 * 电话:18501231486
 * 作用:用户管理类
 * 注意事项:
 */
public class AccountManager {
    private static AccountManager mUserManage;

    public static AccountManager getInstence() {
        if (mUserManage == null) {
            synchronized (AccountManager.class) {
                if (mUserManage == null) {
                    mUserManage = new AccountManager();
                }
            }
        }
        return mUserManage;
    }

    /**
     * 清除用户信息
     */
    public void clearAccountInfo() {
        SPUtils spUtils = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO);
        spUtils.put(Constant.SP.UserInfo.isLogin, false);
        spUtils.put(Constant.SP.UserInfo.token, "");
        spUtils.put(Constant.SP.UserInfo.userName, "");
        spUtils.put(Constant.SP.UserInfo.password, "");
        spUtils.put(Constant.SP.UserInfo.userId,"");
        spUtils.put(Constant.SP.UserInfo.phoneNumber, "");
        spUtils.put(Constant.SP.UserInfo.userSig, "");
        spUtils.put(Constant.SP.UserInfo.userIdPrefix,"");
        spUtils.put(Constant.SP.UserInfo.userTypeList, "");
    }

    /**
     * 更新用户信息
     * 最好是先调用getUserInfo方法，获得UsetInfo，再选择性的改变一个属性
     */
    @SuppressLint("RestrictedApi")
    public void updateAccountInfo(@NonNull AccountBean usetInfo) {
        Preconditions.checkNotNull(usetInfo, "刘传政提醒你：usetInfo不能为空啊");
        SPUtils spUtils = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO);
        spUtils.put(Constant.SP.UserInfo.isLogin, usetInfo.isLogin);
        spUtils.put(Constant.SP.UserInfo.token, usetInfo.token);
        spUtils.put(Constant.SP.UserInfo.userName, usetInfo.userName);
        spUtils.put(Constant.SP.UserInfo.password, usetInfo.password);
        spUtils.put(Constant.SP.UserInfo.userId, usetInfo.userId);
        spUtils.put(Constant.SP.UserInfo.phoneNumber, usetInfo.phoneNumber);
        spUtils.put(Constant.SP.UserInfo.userSig, usetInfo.userSig);
        spUtils.put(Constant.SP.UserInfo.userIdPrefix, usetInfo.userIdPrefix);
        spUtils.put(Constant.SP.UserInfo.photoUrl, usetInfo.photoUrl);
        List<Integer> list = usetInfo.userTypeList;
        Gson gson = new Gson();
        String data = gson.toJson(list);
        spUtils.put(Constant.SP.UserInfo.userTypeList, data);
    }

    /**
     * 获取用户信息
     */
    public AccountBean getGetAccountInfo() {
        AccountBean accountBean = new AccountBean();
        SPUtils spUtils = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO);
        accountBean.isLogin = spUtils.getBoolean(Constant.SP.UserInfo.isLogin, false);
        accountBean.token = spUtils.getString(Constant.SP.UserInfo.token, "");
        accountBean.userName = spUtils.getString(Constant.SP.UserInfo.userName, "");
        accountBean.password = spUtils.getString(Constant.SP.UserInfo.password, "");
        accountBean.userId = spUtils.getString(Constant.SP.UserInfo.userId, "");
        accountBean.phoneNumber = spUtils.getString(Constant.SP.UserInfo.phoneNumber, "");
        accountBean.userSig = spUtils.getString(Constant.SP.UserInfo.userSig, "");
        accountBean.userIdPrefix = spUtils.getString(Constant.SP.UserInfo.userIdPrefix, "");
        accountBean.photoUrl = spUtils.getString(Constant.SP.UserInfo.photoUrl, "");
        String data = spUtils.getString(Constant.SP.UserInfo.userTypeList, "");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Integer>>() {
        }.getType();
        List<Integer> list = gson.fromJson(data, listType);
        if (list != null) {
            accountBean.userTypeList = list;
        }
        return accountBean;
    }
}
