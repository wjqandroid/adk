package com.visionvera.psychologist.account_module.net;

import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.psychologist.account_module.beans.AccountLoginRequestBean;
import com.visionvera.psychologist.account_module.beans.AccountLoginResponseBean;
import com.visionvera.psychologist.account_module.beans.AccountDentifyingCodeRequest;
import com.visionvera.psychologist.account_module.beans.AccountRegisterRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApiService {

    /**
     * 登录
     *
     * @return
     */
    @POST("rmcp/login")
    Observable<AccountLoginResponseBean> accountLogin(@Body AccountLoginRequestBean requestBean);


    /**
     * 发送验证码
     */
//    @POST("/gateway/authapi/getSmsCode")
    @POST("/gateway/mqapi/activeMq/getSmsCodeByMq")
    Observable<BaseResponseBean2> getSmsCode(@Body AccountDentifyingCodeRequest request);

    /**
     * 注册
     */
    @POST("/gateway/authapi/regist")
    Observable<BaseResponseBean2> getRegister(@Body AccountRegisterRequest request);


    /**
     * 手机号验证码登录
     */
    @POST("/gateway/authapi/login")
    Observable<PhoneLoginResponseBean> getPhoneLogin(@Body PhoneLoginRequest request);

}
