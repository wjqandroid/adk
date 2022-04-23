package com.visionvera.library.arouter.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.visionvera.library.arouter.service.IAccountService;


// 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
// 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
//@Interceptor(priority = 1, name = "登录拦截")
public class AccountInterceptor implements IInterceptor {
    @Autowired
    IAccountService accountService;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        boolean isLogin = true;
        if (!isLogin) {
            //todo 登录拦截
            Logger.i("你需要登录");
            ToastUtils.showShort("需要登录");
            callback.onInterrupt(null);

        } else {
            // 处理完成，交还控制权
            callback.onContinue(postcard);
        }
        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
        Logger.i("登录拦截器初始化");
        ARouter.getInstance().inject(this);
    }
}
