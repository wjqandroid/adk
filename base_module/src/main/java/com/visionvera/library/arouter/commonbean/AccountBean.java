package com.visionvera.library.arouter.commonbean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘传政
 * @date 2019-11-07 14:10
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class AccountBean {
    public boolean isLogin = false;
    public String token = "";
    public String userName = "";
    public String password = "";
    public String userId;
    public String phoneNumber = "";
    //腾讯IM的UserSig
    public String userSig = "";
    //请求腾讯IM的接口时userId应该加前缀userIdPrefix
    //格式Prefix + userId
    public String userIdPrefix = "";
    public List<Integer> userTypeList = new ArrayList<>();
    //头像
    public String photoUrl = "";


}
