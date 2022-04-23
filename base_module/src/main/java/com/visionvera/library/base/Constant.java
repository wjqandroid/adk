package com.visionvera.library.base;

import com.visionvera.library.BuildConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 所有的常量类。这里每个常量不用大写表示，因为个人不喜欢，看大写字母一眼看不明白
 */
public class Constant {
    /**
     * sp相关的常量
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface SP {
        public @interface UserInfo {
            /**
             * sp名字
             * 用户信息相关
             */
            String USER_INFO = "USER_INFO";

            //....字段名.....
            //token
            String token = "token";
            // 设备唯一识别码
            String deviceId = "deviceId";

            //是否是登录状态
            String isLogin = "isLogin";
            //用户名
            String userName = "userName";
            //用户id
            String userId = "userId";
            //用户手机号
            String phoneNumber = "phoneNumber";

            //密码
            String password = "password";
            //友盟
            String umeng = "umeng";
            //友盟token
            String umeng_token = "umeng_token";

            //腾讯IM的UserSig
            String userSig = "userSig";
            //腾讯IM使用    自己服务器接口获取前缀userIdPrefix
            String userIdPrefix = "userIdPrefix";
            //用户头像
            String photoUrl = "photoUrl";
            //用户身份 1医生 2本级用户 3基层工作人员/网格员 4管理员 5c端用户/来访者 6心理咨询师 7护士
            String userTypeList = "userTypeList";

        }

        interface Update {
            /**
             * sp名字
             * 用户订单推送相关
             */
            String spName = "update";

            /**
             * 数据的key
             */
            interface Key {
                /**
                 * 忽略的版本号
                 */
                String version = "";


            }
        }

    }

    /**
     * url
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Url {

        /**
         * 这个url是gradle中配置的。可以通过指定打包方式选择不同的值
         * 网络请求的baseUrl
         */
        String request_base_url = BuildConfig.BaseUrl;
        String bugly_appid = BuildConfig.bugly_appid;
        String WX_APP_ID = BuildConfig.weixin_app_id;


        //开发地址
//        String request_base_url = "http://58.30.9.142:34220/";
//        String bugly_appid="2417c71f42";//李龙丰的qq账号，测试使用。


        //测试环境
//        String request_base_url = "http://58.30.9.142:43160/";
//        String bugly_appid="2417c71f42";//李龙丰的qq账号，测试使用。

        //Beta
//        String request_base_url = "http://103.139.212.98:11505/";
//        String bugly_appid="2417c71f42";//李龙丰的qq账号，测试使用。

//        String request_base_url = "http://49.233.84.74:11505/";
//        String bugly_appid="2417c71f42";//李龙丰的qq账号，测试使用。


        //正式环境
//        String request_base_url = "http://49.233.84.74:11505/";
//        String bugly_appid="e4e326411d";//刘传政的qq账号，正式使用。

    }

    /**
     * webview的url
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface WebUrl {

        //登录注册的用户协议
//        String user_protocol = "appView/yonghu.html";
        String user_protocol = "mobile/appView/yonghu.html";

        //咨询协议
        String consult = "mobile/appView/zixun.html";

        //关于我们
        String about_us = "mobile/appView/about.html";

        //心豆规则
        String xindouRule = "mobile/appView/xindouRule.html";

        //咨询须知weburl
        String advisory_guidelines = "mobile/appView/appointmentNotice.html";

        //服务端下载地址
        String download_server = "appDownload/downloadServer.html";
    }


    /**
     * Tag
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Tag {


        /**
         * 打印log的tag
         */
        String tag = "心理c端";
    }

    /**
     * 网络code值定义。
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetCode {
        //李虎最开始定义的
        String success = "1";
        //远程医疗平台的
        int success2 = 0;
    }


    /**
     * greendao数据库的字段
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface GreenDao {
        String DBName = "xinlic.db";

    }

    /**
     * intent传递字段
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface IntentKey {
        /**
         * 默认activity传递对象时的key
         */
        String IntentBean = "IntentBean";

        //从其他地方跳转到账户模块，需要返回与否。
        String requestReturnCode = "requestReturnCode";

        String commitActivityTitle = "commitActivityTitle";

        String apply = "apply";

        String receiver = "receiver";

    }

    /**
     * activity传递的code
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestReturnCode {

        int SelfAssessmentQuestion_To_PhoneLogin_Code = 100;

        int MainActivity_To_PhoneLogin_Code = 101;

        int HomeFragment_To_PhoneLogin_Code = 102;

        int HomeFragment_More_To_PhoneLogin_Code = 103;

        int HomeFragment_To_UserCenter = 104;

        int OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code = 105;

        int OrderConsultItemFragment_To_OrderConsultApplyFragment_Code = 106;


        int UserCenterFragment_To_PhoneLoginActivity_Code = 107;

        int PhoneLoginActivity_To_AccountLoginActivity_Code = 108;

        int UserCenterFragment_To_SettingActivity_Code = 109;

        int OrderTreatmentList_To_OrderTreatmentListDetail_Code = 110;

        int MyCollectsEvaluation_To_PhoneLogin_Code = 111;
        int OrderSelectTimeActivity_To_UserCenter = 112;

        int AccountLoginActivity_To_PhoneLoginActivity_Code = 113;

        int UserCenterFragment_To_AccountLoginActivity_Code = 114;

        int SelfAssessmentQuestion_To_AccountLogin_Code = 115;

        int OrderSelectTimeActivity_To_AccountLoginActivity = 116;

        int MyCollectsEvaluation_To_AccountLoginActivity_Code = 111;
        int OrderConsultItemFragment_To_OrderConsultReceiveFragment_Code = 112;
    }

    /**
     * 视联网sdk的appkey和appsecret
     */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShilianwangSDK {
        //北京的测试环境和广西的url是同一个。
        String serverUrl = "58.30.9.142:9099";

        //北京的测试环境  可以呼叫24,25,26设备
        String app_key = "dae71154-36c4-4cc2-b63d-4ce017544d49";
        String app_secret = "4a462cfd-82f1-492f-9af2-4ddce768e706";

        //广西的app_key  可以呼叫燕山等会议室设备
        //String app_key="c35ff0a9-3067-4947-8b3d-b185b5275cd2";
        //String app_secret="122e1d1f-e8c8-453c-90a3-f765a88047d9"
    }

    /**
     * Cache字段
     */

    public interface Cache {

        String draftsKey = "drafts";

    }
}
