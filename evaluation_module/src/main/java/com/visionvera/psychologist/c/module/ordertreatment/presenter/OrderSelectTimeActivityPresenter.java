package com.visionvera.psychologist.c.module.ordertreatment.presenter;

import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OrderSelectTimeActivityPresenter extends BasePresenter<IContract.OrderSelectTimeActivity.View> implements IContract.OrderSelectTimeActivity.Presenter {

    EvaluationModuleModel.OrderTreatment model;

    public OrderSelectTimeActivityPresenter(Context context, IContract.OrderSelectTimeActivity.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new OrderTreatment();
    }


    @Override
    public void submit(SubmitOrderRequestBean requestBean, LifecycleProvider lifecycleProvider) {
        model.orderTreatmentSubmit(requestBean, lifecycleProvider, new Observer<SubmitOrderResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SubmitOrderResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getCode() == Constant.NetCode.success2) {
                    if (responseBean.getResult() == null) {
                        mView.onSubmit(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onSubmit(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onSubmit(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("onError" + e);
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onSubmit(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void getInforMation(LifecycleProvider lifecycleProvider) {
        model.getInforMation(lifecycleProvider, new Observer<InforMationBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(InforMationBean response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onGetInforMation(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onGetInforMation(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag", "onError: "+e );
                if (mView==null){
                    return;
                }
                mView.onGetInforMation(null, IBaseView.ResultType.NET_ERROR,e.toString());
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
    public void savePath(SavePathRequestBean requestBean, LifecycleProvider lifecycleProvider, int position) {
        model.savePath(requestBean, lifecycleProvider, new Observer<SavePathResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SavePathResponseBean responseBean) {
                if (mView == null) {
                    return;
                }
                if (responseBean.code == Constant.NetCode.success2) {
                    mView.onSavePath(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, responseBean.errMsg, position);
                } else {
                    mView.onSavePath(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.errMsg, position);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    return;
                }
                mView.onSavePath(null, IBaseView.ResultType.NET_ERROR, e.toString(), position);
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
