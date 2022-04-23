package com.visionvera.psychologist.c.module.EvaluationGauge.presenter;

import android.content.Context;
import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultType;
import com.visionvera.psychologist.c.module.EvaluationGauge.contract.SelfAssessmentContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class EvaluationResultPresenter extends BasePresenter<SelfAssessmentContract.EvaluationResult.View> implements SelfAssessmentContract.EvaluationResult.Presenter {

    EvaluationModuleModel.EvaluationResult model;

    private static final String TAG = "EvaluationResultPresent";

    public EvaluationResultPresenter(Context context, SelfAssessmentContract.EvaluationResult.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new EvaluationResult();
    }

    @Override
    public void getEvaluationResult(EvaluationResultRequest evaluationResult, EvaluationResultType type, LifecycleProvider lifecycleProvider) {
        model.getEvaluationResult(evaluationResult, lifecycleProvider, new Observer<EvaluationResultBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EvaluationResultBean bean) {
                Log.e(TAG, "onNext: " );
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onGetEvaluationResult(null,type, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetEvaluationResult(bean,type, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetEvaluationResult(bean,type, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e.toString());
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetEvaluationResult(null, type,IBaseView.ResultType.SERVER_ERROR, e.toString());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: " );
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });

    }
}
