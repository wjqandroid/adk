package com.visionvera.psychologist.c.module.counselling.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.ConfirmOrderDoneRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.CounselorDetailBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderCancelRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultCancelRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultDetailBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultDetailRequest;
import com.visionvera.psychologist.c.module.counselling.bean.ReportInformationBeanRequest;
import com.visionvera.psychologist.c.module.counselling.bean.SaveMeetingRecordRequest;
import com.visionvera.psychologist.c.module.counselling.bean.UserUpdateAgainConsultInfoRequest;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Classname:OrderConsultDetailPresenter
 * @author:haohuizhao
 * @Date:2021/11/17 15:37
 * @Version：v1.0
 * @describe： 描述:咨询/诊疗详情的页面  12个页面共享。因为有大部分功能相同，所有公用一个页面。
 * 二期
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


    //查询咨询师详情
    @Override
    public void onOrderDoctorDetail(int id, LifecycleProvider lifecycleProvider) {
        //获取详情数据
        orderConsult.getOrderDoctorDetail(id, lifecycleProvider, new Observer<CounselorDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CounselorDetailBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onOrderDoctorDetail(bean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onOrderDoctorDetail(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onOrderDoctorDetail(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onOrderDoctorDetail(null, IBaseView.ResultType.NET_ERROR, e.toString());

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

    //查医生详情
    @Override
    public void onDoctorDetailByUserId(DoctorDetailRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getDoctorDetailByUserId(request, lifecycleProvider, new Observer<DoctorDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DoctorDetailBean detailBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (detailBean.getCode() == Constant.NetCode.success2) {
                    if (detailBean.getResult() == null) {
                        mView.onDoctorDetail(detailBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onDoctorDetail(detailBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onDoctorDetail(detailBean, IBaseView.ResultType.SERVER_ERROR, detailBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onDoctorDetail(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
    //预约咨询、预约诊疗订单列表详情接口
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


    //二期新
    //咨询/诊疗上报操作信息接口
    //用户发起聊天、用户接听、用户确认服务状态    用于判断按钮显示
    @Override
    public void reportInformation(ReportInformationBeanRequest request, LifecycleProvider lifecycleProvider,String state) {
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
                    mView.onReportInformation(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg(),state);
                } else {
                    mView.onReportInformation(bean, IBaseView.ResultType.NET_ERROR, bean.getErrMsg(),state);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    return;
                }
                mView.onReportInformation(null, IBaseView.ResultType.NET_ERROR, e.toString(),state);
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


    //二期新
    //取消咨询/诊疗申请
    @Override
    public void getOrderCancel(OrderCancelRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getOrderCancel(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
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
                    mView.onOrderCancel(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                } else {
                    mView.onOrderCancel(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onOrderCancel(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    //驳回咨询/诊疗申请
    @Override
    public void getOrderReject(OrderCancelRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getOrderReject(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
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
                    mView.onOrderReject(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                } else {
                    mView.onOrderReject(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onOrderCancel(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    //受理咨询/诊疗申请
    @Override
    public void getOrderAccept(OrderCancelRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getOrderAccept(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
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
                    mView.onOrderAccept(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                } else {
                    mView.onOrderAccept(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onOrderAccept(null, IBaseView.ResultType.NET_ERROR, e.toString());
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




//    //废弃
//    //确认服务完成
//    @Override
//    public void confirmDone(ConfirmOrderDoneRequestBean request, LifecycleProvider lifecycleProvider) {
//        orderConsult.confirmDone(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(BaseResponseBean2 bean) {
//                if (mView == null) {
//                    return;
//                }
//                if (bean.getCode() == 0) {
//                    mView.onConfirmDone(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
//                } else {
//                    mView.onConfirmDone(bean, IBaseView.ResultType.NET_ERROR, bean.getErrMsg());
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                if (mView == null) {
//                    return;
//                }
//                mView.onConfirmDone(null, IBaseView.ResultType.NET_ERROR, e.toString());
//            }
//
//            @Override
//            public void onComplete() {
//                if (mView == null) {
//                    return;
//                }
//                mView.onComplete();
//            }
//        });
//    }
}
