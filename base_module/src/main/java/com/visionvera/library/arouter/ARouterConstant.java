package com.visionvera.library.arouter;

/**
 * @author: 刘传政
 * @date: 2019-11-05 13:50
 * QQ:1052374416
 * 作用:
 * 注意事项:
 */
public class ARouterConstant {

    /**
     * 账户模块
     */
    public interface Account {
        String PhoneLoginActivity = "/account_module/PhoneLoginActivity";
        String accountModuleSetvice = "/account_module/service";
        String registerActivity="/account_module/registerActivity";
        String AccountLoginActivity="/account_module/AccountLoginActivity";


    }

    /**
     * 壳模块
     */
    public interface App {

    }


    /**
     * 健康测评
     */
    public interface Evaluation {
        String mainActivity = "/evaluation_module/mainActivity";
    }

    /**
     * 直播模块
     */
    public interface Live{
        String watchLiveActivity = "/live_module/watchLiveActivity";

        String collectHomeActivity = "/live_module/collectHomeActivity";

        String searchActivity = "/live_module/searchActivity";

        String liveHomeActivity = "/live_module/liveHomeActivity";
    }

    /**
     * 个人中心
     */
    public interface UserCenter{
        String forgetPasswordActivity="/usercenter/forgetPasswordActivity";
        //编辑个人信息
        String EditInfoActivity = "/usercenter/EditInfoActivity";
    }

}
