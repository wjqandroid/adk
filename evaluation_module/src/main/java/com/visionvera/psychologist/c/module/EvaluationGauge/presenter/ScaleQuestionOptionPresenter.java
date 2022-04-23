package com.visionvera.psychologist.c.module.EvaluationGauge.presenter;

import android.content.Context;
import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.BaseMvpObserver;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.CommitRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.DoAssessResponseBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDictInfoRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.contract.SelfAssessmentContract;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.net.EvaluationModuleApi;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ScaleQuestionOptionPresenter
        extends BasePresenter<SelfAssessmentContract.ScaleQuestionOption.ScaleQuestionOptionView>
        implements SelfAssessmentContract.ScaleQuestionOption.BaseScaleQuestionOptionPresenter {

    EvaluationModuleModel.SelfAssessmentGauge selfAssessmentGauge;

    public ScaleQuestionOptionPresenter(Context context, SelfAssessmentContract.ScaleQuestionOption.ScaleQuestionOptionView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        selfAssessmentGauge=new EvaluationModuleModel().new SelfAssessmentGauge();
    }


    @Override
    public void getDoAssess(CommitRequest commitRequest, LifecycleProvider lifecycleProvider) {
        getData(RetrofitManager.create(EvaluationModuleApi.class).getDoAssess(commitRequest),new BaseMvpObserver<BaseResponseBean3<DoAssessResponseBean>>(mView,false){
            @Override
            protected void onResult(BaseResponseBean3<DoAssessResponseBean> responseBean, IBaseView.ResultType resultType, String errorMsg) {
                mView.onDoAssess(responseBean,resultType,errorMsg);
            }
        });
    }

    @Override
    public void getInforMation(LifecycleProvider lifecycleProvider) {
        selfAssessmentGauge.getInforMation(lifecycleProvider, new Observer<InforMationBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(InforMationBean response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onGetInforMation(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onGetInforMation(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag", "onError: "+e );
                if (mView==null){
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

    @Override
    public void collectScaleDict(ScaleDictInfoRequest request, LifecycleProvider lifecycleProvider) {
        selfAssessmentGauge.getCollectScaleDict(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 response) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onCollectScaleDict(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onCollectScaleDict(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onCollectScaleDict(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
    public void cancleCollectScaleDict(ScaleDictInfoRequest request, LifecycleProvider lifecycleProvider) {
        selfAssessmentGauge.cancleCollectScaleDict(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 response) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onCancleCollectScaleDict(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onCancleCollectScaleDict(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onCancleCollectScaleDict(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
