package com.visionvera.psychologist.c.module.ordertreatment.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SubjectRecommendActivityPresenter extends BasePresenter<IContract.SubjectRecommendActivity.View> implements IContract.SubjectRecommendActivity.Presenter {

    EvaluationModuleModel.OrderTreatment model;

    public SubjectRecommendActivityPresenter(Context context, IContract.SubjectRecommendActivity.View mView) {
        super(context, mView);

    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new OrderTreatment();
    }


    @Override
    public void getDoctorListFromHospital(boolean isRefresh, DoctorListRequestBean requestBean, LifecycleProvider lifecycleProvider) {
        model.getDoctorList(requestBean, lifecycleProvider, new Observer<DoctorListResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DoctorListResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getCode() == Constant.NetCode.success2) {
                    if (responseBean.getResult() == null) {
                        mView.onGetDoctorListFromHospital(isRefresh, responseBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetDoctorListFromHospital(isRefresh, responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetDoctorListFromHospital(isRefresh, responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("onError" + e);
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetDoctorListFromHospital(isRefresh, null, IBaseView.ResultType.NET_ERROR, e.toString());
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
}
