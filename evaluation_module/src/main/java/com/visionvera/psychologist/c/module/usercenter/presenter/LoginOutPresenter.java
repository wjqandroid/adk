package com.visionvera.psychologist.c.module.usercenter.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginOutPresenter extends BasePresenter<IContract.LoginOut.LoginOutView> implements IContract.LoginOut.LoginOutBasePresenter {

    EvaluationModuleModel.LoginOut loginOut;

    public LoginOutPresenter(Context context, IContract.LoginOut.LoginOutView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        loginOut=new EvaluationModuleModel().new LoginOut();
    }

    @Override
    public void getLoginOut(LifecycleProvider lifecycleProvider) {
        loginOut.getLoginOut(lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onLoginOut(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onLoginOut(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onLoginOut(null, IBaseView.ResultType.NET_ERROR,e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //??????view???????????????,????????????????????????.?????????????????????.
                    return;
                }
                mView.onComplete();
            }
        });
    }
}
