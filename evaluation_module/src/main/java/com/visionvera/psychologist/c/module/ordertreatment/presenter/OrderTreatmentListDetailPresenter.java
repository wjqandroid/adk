package com.visionvera.psychologist.c.module.ordertreatment.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.SaveMeetingRecordRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentAgainConsultRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentCancelRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OrderTreatmentListDetailPresenter extends BasePresenter<IContract.OrderTreamentListDetail.OrderTreatmentListDetailView> implements IContract.OrderTreamentListDetail.OrderTreatmentListDetailBasePresenter {

    EvaluationModuleModel.OrderTreatmentListDetail orderTreatmentListDetail ;

    public OrderTreatmentListDetailPresenter(Context context, IContract.OrderTreamentListDetail.OrderTreatmentListDetailView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        orderTreatmentListDetail=new EvaluationModuleModel().new OrderTreatmentListDetail();
    }

    @Override
    public void getOrderTreatmentListDetail(OrderTreatmentListDetailRequest request, LifecycleProvider lifecycleProvider) {
        orderTreatmentListDetail.getOrderTreatmentListDetail(request, lifecycleProvider, new Observer<OrderTreatmentListDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OrderTreatmentListDetailBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onOrderTreatmentListDetail(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onOrderTreatmentListDetail(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onOrderTreatmentListDetail(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onOrderTreatmentListDetail(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
    public void getOrderTreatmentCancel(OrderTreatmentCancelRequest request, LifecycleProvider lifecycleProvider) {
        orderTreatmentListDetail.getOrderTreatmentCancel(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
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
                    mView.onOrderTreatmentCancel(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                } else {
                    mView.onOrderTreatmentCancel(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onOrderTreatmentCancel(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void cancel(OrderTreatmentCancelRequest request, LifecycleProvider lifecycleProvider) {
        orderTreatmentListDetail.getOrderTreatmentCancel(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
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
                    mView.onCancel(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                } else {
                    mView.onCancel(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onCancel(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void getSaveMeetingRecord(SaveMeetingRecordRequest saveMeetingRecordRequest, LifecycleProvider lifecycleProvider) {
        orderTreatmentListDetail.getSaveMeetingRecord(saveMeetingRecordRequest, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 newAlarmBean) {
                if (mView==null){
                    return ;
                }
                if (newAlarmBean.getCode()== 0){
                    mView.onSaveMeetingRecord(newAlarmBean,IBaseView.ResultType.SERVER_NORMAL_DATAYES, newAlarmBean.getErrMsg());
                }else{
                    mView.onSaveMeetingRecord(newAlarmBean,IBaseView.ResultType.NET_ERROR,newAlarmBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onSaveMeetingRecord(null, IBaseView.ResultType.NET_ERROR,e.toString());
            }

            @Override
            public void onComplete() {
                if (mView==null){
                    return;
                }
                mView.onComplete();
            }
        });
    }

    @Override
    public void getUserUpdateAgainConsultInfo(OrderTreatmentAgainConsultRequest request, LifecycleProvider lifecycleProvider) {
        orderTreatmentListDetail.getUserUpdateAgainConsultInfo(request,lifecycleProvider,new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 bean) {
                if (mView==null){
                    return ;
                }
                if (bean.getCode()== 0){
                    mView.onUserUpdateAgainConsultInfo(bean,IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                }else{
                    mView.onUserUpdateAgainConsultInfo(bean,IBaseView.ResultType.NET_ERROR,bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onUserUpdateAgainConsultInfo(null, IBaseView.ResultType.NET_ERROR,e.toString());
            }

            @Override
            public void onComplete() {
                if (mView==null){
                    return;
                }
                mView.onComplete();
            }
        });
    }
}
