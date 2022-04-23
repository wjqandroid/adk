package com.tencent.qcloud.tuicore.net;

import android.content.Context;


import com.tencent.qcloud.tuicore.R;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Classname:OrderConsultDetailPresenter
 * @author:haohuizhao
 * @Date:2021/12/05 15:37
 * @Version：v1.0
 * @describe： OrderConsultDetailPresenter
 * <p>
 */

public class OrderConsultDetailPresenter extends BasePresenter<OrderConsultContract.OrderConsultDetail.OrderConsultDetailView> implements OrderConsultContract.OrderConsultDetail.OrderConsultDetailBasePresenter {

    EvaluationModuleModel.OrderConsult orderConsult;

    public OrderConsultDetailPresenter(Context context, OrderConsultContract.OrderConsultDetail.OrderConsultDetailView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        orderConsult = new EvaluationModuleModel().new OrderConsult();
    }



    //二期新
    //咨询/诊疗上报操作信息接口
    //用户发起聊天、用户接听、用户确认服务状态    用于判断按钮显示
    @Override
    public void reportInformation(ReportInformationBeanRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.reportInformation(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 bean) {
                if (mView == null) {
                    return;
                }
                if (bean.getCode() == 0) {
                    mView.onReportInformation(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                } else {
                    mView.onReportInformation(bean, IBaseView.ResultType.NET_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    return;
                }
                mView.onReportInformation(null, IBaseView.ResultType.NET_ERROR, e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    return;
                }
                mView.onComplete();
            }
        });
    }


    //订单详情
    @Override
    public void getOrderConsultDetail(OrderConsultDetailRequest request, LifecycleProvider lifecycleProvider) {

        //获取详情数据
        orderConsult.getOrderConsultDetail(request, lifecycleProvider, new Observer<OrderConsultDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderConsultDetailBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onOrderConsultDetail(bean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onOrderConsultDetail(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onOrderConsultDetail(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onOrderConsultDetail(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
