package com.visionvera.psychologist.c.module.counselling.presenter;

import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.module.counselling.bean.NewOrderConsultRequest;
import com.visionvera.psychologist.c.module.counselling.bean.NewOrderconsultBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @Classname:OrderPayActivity
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0描
 * @describe： 描述:预约咨询、诊疗————咨询订单提交与支付页面
 */


public class OrderPayPresenter extends BasePresenter<OrderConsultContract.OrderPayActivity.NewOrderConsultView> implements OrderConsultContract.OrderPayActivity.NewOrderConsultBasePresenter {

    EvaluationModuleModel.OrderConsult orderConsult;

    public OrderPayPresenter(Context context, OrderConsultContract.OrderPayActivity.NewOrderConsultView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        orderConsult = new EvaluationModuleModel().new OrderConsult();
    }

    //心理咨询、诊疗 预约相关-新增预约申请接口
    @Override
    public void getNewOrderConsult(NewOrderConsultRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getNewOrderConsult(request, lifecycleProvider, new Observer<NewOrderconsultBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewOrderconsultBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    mView.onNewOrderConsult(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                } else {
                    mView.onNewOrderConsult(null, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onNewOrderConsult(null, IBaseView.ResultType.NET_ERROR, e.toString());

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

    //完善信息前获取信息
    @Override
    public void getInforMation(LifecycleProvider lifecycleProvider) {
        orderConsult.getInforMation(lifecycleProvider, new Observer<InforMationBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(InforMationBean response) {
                if (mView == null) {
                    return;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onGetInforMation(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onGetInforMation(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag", "onError: " + e);
                if (mView == null) {
                    return;
                }
                mView.onGetInforMation(null, IBaseView.ResultType.NET_ERROR, e.toString());
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

    //cos上传文件后的保存信息到自己服务器的接口
    @Override
    public void savePath(SavePathRequestBean requestBean, LifecycleProvider lifecycleProvider, int position) {
        orderConsult.savePath(requestBean, lifecycleProvider, new Observer<SavePathResponseBean>() {
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
