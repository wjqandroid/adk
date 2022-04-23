package com.visionvera.psychologist.c.module.ordertreatment.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: 刘传政
 * @date: 2020-01-03 15:25
 * QQ:1052374416
 * 作用:推荐机构
 * 注意事项:
 */
public class SuggestHospitalPresenter extends BasePresenter<IContract.OrderTreatmentMainActivity.View> implements IContract.OrderTreatmentMainActivity.Presenter {

    EvaluationModuleModel.OrderTreatment model;

    public SuggestHospitalPresenter(Context context, IContract.OrderTreatmentMainActivity.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new OrderTreatment();
    }

    @Override
    public void getSuggestList(RecommentHospitalsRequestBean requestBean, LifecycleProvider lifecycleProvider) {
        model.getSuggestList(requestBean, lifecycleProvider, new Observer<RecommentHospitalsResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RecommentHospitalsResponseBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getCode() == Constant.NetCode.success2) {
                    if (responseBean.getResult() == null) {
                        mView.onGetSuggestList(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetSuggestList(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetSuggestList(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("onError" + e);
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetSuggestList(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
