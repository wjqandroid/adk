package com.visionvera.psychologist.c.module.myevaluation.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.module.myevaluation.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

public class TestedPresenter extends BasePresenter<IContract.Tested.View> implements IContract.Tested.Presenter {

    EvaluationModuleModel.MyEvaluation model;

    public TestedPresenter(Context context, IContract.Tested.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new MyEvaluation();
    }

    @Override
    public void getTested(boolean isRefresh, RequestBody requestBody, LifecycleProvider lifecycleProvider) {
        model.getMyEvaluation(requestBody, lifecycleProvider, new Observer<MyEvaluationBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyEvaluationBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onGetTested(isRefresh, null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetTested(isRefresh, bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetTested(isRefresh, bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetTested(isRefresh, null, IBaseView.ResultType.NET_ERROR, e.toString());
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
