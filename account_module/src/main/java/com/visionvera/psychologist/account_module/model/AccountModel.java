package com.visionvera.psychologist.account_module.model;

import android.app.Activity;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.model.IBaseModel;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.psychologist.account_module.beans.AccountLoginRequestBean;
import com.visionvera.psychologist.account_module.beans.AccountLoginResponseBean;
import com.visionvera.psychologist.account_module.beans.AccountDentifyingCodeRequest;
import com.visionvera.psychologist.account_module.beans.AccountRegisterRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginResponseBean;
import com.visionvera.psychologist.account_module.net.AccountApiService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AccountModel implements IBaseModel {

    public void accountLogin(AccountLoginRequestBean request,
                             LifecycleProvider lifecycleProvider,
                             Observer<AccountLoginResponseBean> observer) {
        LifecycleTransformer lifecycleTransformer;
        if (lifecycleProvider instanceof Activity) {
            lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
        } else {
            lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
        }
        RetrofitManager.create(AccountApiService.class)
                .accountLogin(request)
                .compose(lifecycleTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void getSmsCode(AccountDentifyingCodeRequest request,
                           LifecycleProvider lifecycleProvider,
                           Observer<BaseResponseBean2> observer){
        LifecycleTransformer lifecycleTransformer;
        if (lifecycleProvider instanceof Activity) {
            lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
        } else {
            lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
        }
        RetrofitManager.create(AccountApiService.class)
                .getSmsCode(request)
                .compose(lifecycleTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRegister(AccountRegisterRequest request,
                            LifecycleProvider lifecycleProvider,
                            Observer<BaseResponseBean2> observer){

        LifecycleTransformer lifecycleTransformer;
        if (lifecycleProvider instanceof Activity) {
            lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
        } else {
            lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
        }
        RetrofitManager.create(AccountApiService.class)
                .getRegister(request)
                .compose(lifecycleTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getPhoneLogin(PhoneLoginRequest request,
                              LifecycleProvider lifecycleProvider,
                              Observer<PhoneLoginResponseBean> observer) {
        LifecycleTransformer lifecycleTransformer;
        if (lifecycleProvider instanceof Activity) {
            lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
        } else {
            lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
        }
        RetrofitManager.create(AccountApiService.class)
                .getPhoneLogin(request)
                .compose(lifecycleTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }



}
