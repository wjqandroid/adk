package com.visionvera.psychologist.c.module.counselling.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.QueryPaysStatusRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.QueryPaysStatusResponseBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Classname:OrderConsultSuccessActivityPresenter
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0描
 * @describe： 描述:预约咨询、诊疗————预约成功页面(查询支付结果)
 */

public class OrderConsultSuccessActivityPresenter extends BasePresenter<OrderConsultContract.OrderConsultSuccessActivity.View> implements OrderConsultContract.OrderConsultSuccessActivity.Presenter {

    EvaluationModuleModel.OrderConsult orderConsult;

    public OrderConsultSuccessActivityPresenter(Context context, OrderConsultContract.OrderConsultSuccessActivity.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        orderConsult = new EvaluationModuleModel().new OrderConsult();
    }


    //心理咨询、诊疗 预约相关-查询真正的支付结果
    @Override
    public void queryPayStatus(QueryPaysStatusRequestBean request, LifecycleProvider lifecycleProvider) {
        orderConsult.queryPayStatus(request, lifecycleProvider, new Observer<QueryPaysStatusResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(QueryPaysStatusResponseBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                //这个接口的返回值特殊
                mView.onQueryPayStatus(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, context.getString(R.string.base_module_data_null));

            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onQueryPayStatus(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
