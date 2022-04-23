package com.visionvera.psychologist.c.module.usercenter.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.module.usercenter.bean.ForgetPasswordCheckNumberRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.ForgetPasswordCommitRequest;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ForgetPasswordPresenter extends BasePresenter<IContract.ForgetPassword.ForgetPasswordView> implements IContract.ForgetPassword.ForgetPasswordBasePresenter{


    EvaluationModuleModel.Setting setting;


    public ForgetPasswordPresenter(Context context, IContract.ForgetPassword.ForgetPasswordView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        setting=new EvaluationModuleModel().new Setting();
    }

    @Override
    public void getForgetPasswordCheckNumber(ForgetPasswordCheckNumberRequest request, LifecycleProvider lifecycleProvider) {
        setting.getForgetPasswordCheckNumber(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onForgetPasswordCheckNumber(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                } else {
                    mView.onForgetPasswordCheckNumber(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onForgetPasswordCheckNumber(null, IBaseView.ResultType.NET_ERROR,e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });
    }

    @Override
    public void getForgetPasswordCommit(ForgetPasswordCommitRequest request, LifecycleProvider lifecycleProvider) {
        setting.getForgetPasswordCommit(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onForgetPasswordCommit(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                } else {
                    mView.onForgetPasswordCommit(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onForgetPasswordCommit(null, IBaseView.ResultType.NET_ERROR,e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });
    }
}
