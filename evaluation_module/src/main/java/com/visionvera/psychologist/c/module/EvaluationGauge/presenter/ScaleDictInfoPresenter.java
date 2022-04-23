package com.visionvera.psychologist.c.module.EvaluationGauge.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.BaseObserver;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.BaseMvpObserver;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDIctInfoResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDictInfoRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleQuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.contract.SelfAssessmentContract;
import com.visionvera.psychologist.c.module.counselling.bean.XindouPayRequestBean;
import com.visionvera.psychologist.c.net.EvaluationModuleApi;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ScaleDictInfoPresenter extends BasePresenter<SelfAssessmentContract.ScaleDictInfo.ScaleDictInfoView>
        implements SelfAssessmentContract.ScaleDictInfo.BaseScaleDictInfoPresenter {

    EvaluationModuleModel.SelfAssessmentGauge selfAssessmentGaugeModel;

    public ScaleDictInfoPresenter(Context context, SelfAssessmentContract.ScaleDictInfo.ScaleDictInfoView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        selfAssessmentGaugeModel=new EvaluationModuleModel().new SelfAssessmentGauge();

    }

    @Override
    public void getScaleDictInfo(ScaleDictInfoRequest scaleDictInfoRequest, LifecycleProvider lifecycleProvider) {
        selfAssessmentGaugeModel.getScaleDictInfo(scaleDictInfoRequest, lifecycleProvider, new Observer<ScaleDIctInfoResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ScaleDIctInfoResponse response) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    if (response.getResult() == null) {
                        mView.onScaleDictInfo(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onScaleDictInfo(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onScaleDictInfo(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onScaleDictInfo(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
    public void getScaleQuestionOption(ScaleDictInfoRequest scaleDictInfoRequest, LifecycleProvider lifecycleProvider) {
        selfAssessmentGaugeModel.getScaleQuestionOption(scaleDictInfoRequest, lifecycleProvider, new Observer<BaseResponseBean3<QuestionOptionResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean3<QuestionOptionResponse> response) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    if (response.getResult() == null) {
                        mView.onScaleQuestionOption(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onScaleQuestionOption(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onScaleQuestionOption(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onScaleQuestionOption(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
        selfAssessmentGaugeModel.getCollectScaleDict(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
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
                    mView.onCollectScaleDict(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES,  response.getErrMsg());
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
        selfAssessmentGaugeModel.cancleCollectScaleDict(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
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


    @Override
    public void xindouPay(XindouPayRequestBean request, LifecycleProvider lifecycleProvider) {
        getData(RetrofitManager.create(EvaluationModuleApi.class).xindouPay(request),new BaseMvpObserver<BaseResponseBean3>(mView,false){
            @Override
            protected void onResult(BaseResponseBean3 responseBean, IBaseView.ResultType resultType, String errorMsg) {
                mView.onXindouPay(responseBean,resultType,errorMsg);
            }
        });
    }

    @Override
    public void getQuestionOption(Map map) {
        getData(RetrofitManager.create(EvaluationModuleApi.class).getQuestionAndOption(map),new BaseMvpObserver<BaseResponseBean3<QuestionOptionResponse>>(mView,false){
            @Override
            protected void onResult(BaseResponseBean3<QuestionOptionResponse> responseBean, IBaseView.ResultType resultType, String errorMsg) {
                mView.onQuestionAndOption(responseBean,resultType,errorMsg);
            }
        });
    }

}
