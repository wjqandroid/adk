package com.visionvera.psychologist.c.module.ordertreatment.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListRequest;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OrderTreatmentListPresenter extends BasePresenter<IContract.OrderTreatmentList.OrderTreatmentListView> implements IContract.OrderTreatmentList.OrderTreatmentListBasePresenter {

    EvaluationModuleModel.OrderTreatmentList orderTreatmentList;

    public OrderTreatmentListPresenter(Context context, IContract.OrderTreatmentList.OrderTreatmentListView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        orderTreatmentList=new EvaluationModuleModel().new OrderTreatmentList();
    }

    @Override
    public void getOrderTreatmentList(boolean isRefresh, OrderTreatmentListRequest request, LifecycleProvider lifecycleProvider) {
        orderTreatmentList.getOrderTreatmentList(request, lifecycleProvider, new Observer<OrderTreatmentListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderTreatmentListBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onOrderTreatmentList(isRefresh,null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onOrderTreatmentList(isRefresh,bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onOrderTreatmentList(isRefresh,bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onOrderTreatmentList(isRefresh,null, IBaseView.ResultType.NET_ERROR, e.toString());

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
