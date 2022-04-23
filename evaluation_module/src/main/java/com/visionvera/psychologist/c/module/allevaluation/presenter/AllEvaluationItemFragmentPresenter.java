package com.visionvera.psychologist.c.module.allevaluation.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListResponseBean;
import com.visionvera.psychologist.c.module.allevaluation.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AllEvaluationItemFragmentPresenter
        extends BasePresenter<IContract.AllEvaluationItemFragment.View>
        implements IContract.AllEvaluationItemFragment.Presenter {

    EvaluationModuleModel.AllEvaluation model;

    public AllEvaluationItemFragmentPresenter(Context context, IContract.AllEvaluationItemFragment.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new AllEvaluation();
    }

    @Override
    public void getEvaluationChatList(boolean isRefresh, EvaluationChatListRequestBean requestBean, LifecycleProvider lifecycleProvider) {
        model.getEvaluationChatList(requestBean, lifecycleProvider, new Observer<EvaluationChatListResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EvaluationChatListResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getCode() == Constant.NetCode.success2) {
                    if (responseBean.getResult() == null) {
                        mView.onGetEvaluationChatList(isRefresh, null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetEvaluationChatList(isRefresh, responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetEvaluationChatList(isRefresh, responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetEvaluationChatList(isRefresh, null, IBaseView.ResultType.NET_ERROR, e.toString());
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
