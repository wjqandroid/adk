package com.visionvera.psychologist.account_module.presenters;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.account_module.beans.AccountDentifyingCodeRequest;
import com.visionvera.psychologist.account_module.beans.AccountLoginResponseBean;
import com.visionvera.psychologist.account_module.beans.PhoneLoginRequest;
import com.visionvera.psychologist.account_module.beans.PhoneLoginResponseBean;
import com.visionvera.psychologist.account_module.contracts.IContract;
import com.visionvera.psychologist.account_module.model.AccountModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PhoneLoginPresenter extends BasePresenter<IContract.PhoneLoginActivity.PhoneLoginView> implements IContract.PhoneLoginActivity.PhoneLoginBasePresenter {

    private AccountModel accountModel;

    public PhoneLoginPresenter(Context context, IContract.PhoneLoginActivity.PhoneLoginView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        accountModel=new AccountModel();
    }

    @Override
    public void getSmsCode(AccountDentifyingCodeRequest registerRequest, LifecycleProvider lifecycleProvider) {
        accountModel.getSmsCode(registerRequest, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getCode() == Constant.NetCode.success2) {
                    mView.onSmsCode(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, responseBean.getErrMsg());
                } else {
                    mView.onSmsCode(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("onError" + e);
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onSmsCode(null, IBaseView.ResultType.NET_ERROR, e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
                Logger.i("onComplete");
            }
        });
    }

    @Override
    public void getPhoneLogin(PhoneLoginRequest request, LifecycleProvider lifecycleProvider) {
        accountModel.getPhoneLogin(request, lifecycleProvider, new Observer<PhoneLoginResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PhoneLoginResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getCode() == Constant.NetCode.success2) {
                    mView.onPhoneLogin(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, responseBean.getErrMsg());
                } else {
                    mView.onPhoneLogin(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("onError" + e);
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onPhoneLogin(null, IBaseView.ResultType.NET_ERROR, e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
                Logger.i("onComplete");
            }
        });
    }


}
