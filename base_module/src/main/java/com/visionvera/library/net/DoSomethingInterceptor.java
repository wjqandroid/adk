package com.visionvera.library.net;


import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.orhanobut.logger.Logger;
import com.visionvera.library.BaseApplication;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.Constant;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * @author: 刘传政
 * @date: 2019-05-22 13:38
 * QQ:1052374416
 * 作用:统一处理拦截器
 * 比如处理tokenshixiao，统一处理某个错误，统一添加某个字段。
 * 注意事项:
 */
public class DoSomethingInterceptor implements Interceptor {
    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private Toast  loginMissToast;
    @Override
    public Response intercept(Chain chain) throws IOException {
        ARouter.getInstance().inject(this);

        Request original = chain.request();
        String url = original.url().toString();
        Request request;

        String spToken = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).getString(Constant.SP.UserInfo.token, "");
        String deviceId = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).getString(Constant.SP.UserInfo.deviceId, "");
        String userId=SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).getString(Constant.SP.UserInfo.userId,"");
        if (url.contains("rmcp/login")) {
            request = chain.request().newBuilder()
                    .addHeader("MacId", deviceId)
                    .build();
        } else if (url.contains("/authapi/visitorLogin") || url.contains("/authapi/regist")) {
            request = chain.request().newBuilder()
                    .addHeader("Authorization", "")
                    .addHeader("MacId", deviceId)
                    .build();
        } else if (url.contains("/rmcp/appCourse/getBanners") || url.contains("/rmcp/appCourse/getRecommendCourse")){
            if (TextUtils.isEmpty(spToken) || TextUtils.isEmpty(userId)){
                request = chain.request().newBuilder()
                        .build();
            }else{
                request = chain.request().newBuilder()
                        .addHeader("token", spToken)
                        .addHeader("userId",userId)
                        .build();
            }

        }else{
            request = chain.request().newBuilder()
                    .addHeader("Authorization", spToken)
//                    .addHeader("Connection","close")
                    .addHeader("MacId", deviceId)
                    .build();
        }


        Response response = chain.proceed(request);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        if (bodyEncoded(response.headers())) {
            //HTTP (encoded body omitted)
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    //Couldn't decode the response body; charset is likely malformed.
                    return response;
                }
            }

            if (!isPlaintext(buffer)) {
                return response;
            }

            if (contentLength != 0) {
                String result = buffer.clone().readString(charset);
                //获取到response的body的string字符串
                //do something .... <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                try {
                    //指的是http状态码，不是自定义的
                    if (401 == response.code()) {
                        //说明token失效,要跳到登录页
                        Logger.i("token失效,请重新登录");

                        Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                emitter.onNext("1");
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {

                                        if (accountService != null) {
                                            //说明已经加载了账户模块
                                            if (accountService != null) {
                                                accountService.clearAccountInfo();
                                            }
                                            //因为登录页是singleTask,所以有多个接口同时回调页不会打开多个登录页
                                            ARouter.getInstance().build(ARouterConstant.Account.AccountLoginActivity)
                                                    //因为有游客登录,所以这里的登录页面不会指定打开任何界面,我们不能清除栈.
//                                                    .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK)
                                                    .navigation();

                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    //执行在主线程
                                                    //两秒后再显示吐司，因为怕之前接口的吐司盖住这个吐司
                                                    if (loginMissToast==null){
                                                        loginMissToast =Toast.makeText(BaseApplication.getInstance(), "登录信息失效,请重新登录！", Toast.LENGTH_SHORT);
                                                    }else{
                                                        loginMissToast.setText("登录信息失效,请重新登录！");

                                                    }
                                                    loginMissToast.show();
//                                                    Toast.makeText(BaseApplication.getInstance(), "登录信息失效,请重新登录！", Toast.LENGTH_SHORT).show();
                                                }
                                            }, 1000);
                                        }

                                    }
                                });

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return response;
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

}

