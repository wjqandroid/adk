package com.visionvera.psychologist.account_module.presenters;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.account_module.R;
import com.visionvera.psychologist.account_module.beans.AccountLoginRequestBean;
import com.visionvera.psychologist.account_module.beans.AccountLoginResponseBean;
import com.visionvera.psychologist.account_module.contracts.IContract;
import com.visionvera.psychologist.account_module.model.AccountModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AccountLoginActivityPresenter extends BasePresenter<IContract.AccountLoginActivity.View> implements IContract.AccountLoginActivity.Presenter {

    AccountModel model;

    public AccountLoginActivityPresenter(Context context, IContract.AccountLoginActivity.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new AccountModel();
    }

    @Override
    public void login(AccountLoginRequestBean requestBean, LifecycleProvider lifecycleProvider) {
        model.accountLogin(requestBean, lifecycleProvider, new Observer<AccountLoginResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AccountLoginResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }

                if (responseBean.getCode() == Constant.NetCode.success2) {
                    if (responseBean.getResult() == null) {
                        mView.onLogin(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onLogin(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onLogin(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("onError" + e);
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onLogin(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
