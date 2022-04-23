package com.visionvera.psychologist.c.module.home.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.module.home.bean.UserSigBean;
import com.visionvera.psychologist.c.module.home.bean.UserSigRequestBean;
import com.visionvera.psychologist.c.module.home.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class VisitorLoginPresenter extends BasePresenter<IContract.VisitorLogin.VisitorLoginView> implements IContract.VisitorLogin.VisitorLoginBasePresenter {

    EvaluationModuleModel.VisitorLogin visitorLogin;

    public VisitorLoginPresenter(Context context, IContract.VisitorLogin.VisitorLoginView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        visitorLogin=new EvaluationModuleModel().new VisitorLogin();
    }

    @Override
    public void getVisitorLogin(LifecycleProvider lifecycleProvider) {
        visitorLogin.getVisitorLogin(lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    mView.onVisitorLogin(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES,bean.getErrMsg() );
                } else {
                    mView.onVisitorLogin(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onVisitorLogin(null, IBaseView.ResultType.NET_ERROR, e.toString());
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

    //二期新
    @Override
    public void getUserSig(UserSigRequestBean userSigRequestBean, LifecycleProvider lifecycleProvider) {
        visitorLogin.getUserSig(userSigRequestBean,lifecycleProvider, new Observer<UserSigBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserSigBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    mView.onUserSig(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES,bean.getErrMsg() );
                } else {
                    mView.onUserSig(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {

                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onUserSig(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
