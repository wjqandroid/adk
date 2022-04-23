package com.visionvera.psychologist.c.module.home.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.home.bean.EveryDaySignInBean;
import com.visionvera.psychologist.c.module.home.bean.SignInBean;
import com.visionvera.psychologist.c.module.home.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class EveryDaySignInPresenter extends BasePresenter<IContract.EveryDaySignIn.EveryDaySignInView> implements IContract.EveryDaySignIn.EveryDaySignInBasePresenter {

    EvaluationModuleModel.EveryDaySignIn everyDaySignIn;

    public EveryDaySignInPresenter(Context context, IContract.EveryDaySignIn.EveryDaySignInView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        everyDaySignIn=new EvaluationModuleModel().new EveryDaySignIn();
    }


    @Override
    public void getSignInByWeek(LifecycleProvider lifecycleProvider) {
        everyDaySignIn.getSignInByWeek(lifecycleProvider, new Observer<EveryDaySignInBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EveryDaySignInBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onSignInByWeek(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onSignInByWeek(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                    }
                } else {
                    mView.onSignInByWeek(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onSignInByWeek(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
    public void getSaveSignIn(LifecycleProvider lifecycleProvider) {
        everyDaySignIn.getSaveSignIn(lifecycleProvider, new Observer<SignInBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SignInBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    mView.onSaveSignIn(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                } else {
                    mView.onSaveSignIn(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onSaveSignIn(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
