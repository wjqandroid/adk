package com.visionvera.psychologist.c.module.ordertreatment.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListRequest;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @Classname:NewOrderTreatmentDetailActivityPresenter
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0
 * @describe： 描述:1.预约诊疗  医生详情    价格、评价列表
 */

public class NewOrderTreatmentDetailActivityPresenter extends BasePresenter<IContract.OrderTreatmentDetailActivity.View> implements IContract.OrderTreatmentDetailActivity.Presenter {

    EvaluationModuleModel.OrderTreatment model;

    public NewOrderTreatmentDetailActivityPresenter(Context context, IContract.OrderTreatmentDetailActivity.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new OrderTreatment();
    }



    //评价List
    @Override
    public void getUserEvaluateList(EvaluateListRequest request, LifecycleProvider lifecycleProvider) {
        model.getUserEvaluateList(request, lifecycleProvider, new Observer<EvaluateListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EvaluateListBean detailBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (detailBean.getCode() == Constant.NetCode.success2) {
                    if (detailBean.getResult() == null) {
                        mView.onEvaluateList(detailBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onEvaluateList(detailBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onEvaluateList(detailBean, IBaseView.ResultType.SERVER_ERROR, detailBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onEvaluateList(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
    public void getDoctorDetail(DoctorDetailRequest request, LifecycleProvider lifecycleProvider) {
        model.getDoctorDetailByUserId(request, lifecycleProvider, new Observer<DoctorDetailBean>() {
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


}
