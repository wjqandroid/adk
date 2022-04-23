package com.visionvera.psychologist.c.module.EvaluationGauge.contract;


import android.app.MediaRouteActionProvider;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.CommitRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.DoAssessResponseBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultType;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDIctInfoResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDictInfoRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleQuestionOptionResponse;
import com.visionvera.psychologist.c.module.counselling.bean.XindouPayRequestBean;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;

import java.util.Map;

public interface SelfAssessmentContract {
     interface ScaleDictInfo{
        interface ScaleDictInfoView extends IBaseRetrofitView {
            void onScaleDictInfo(ScaleDIctInfoResponse response, ResultType resultType, String errorMsg);

            void onScaleQuestionOption(BaseResponseBean3<QuestionOptionResponse> response, ResultType resultType, String errorMsg);

            void onCollectScaleDict(BaseResponseBean2 response, ResultType resultType, String errorMsg);

            void onCancleCollectScaleDict(BaseResponseBean2 response, ResultType resultType, String errorMsg);

            void onXindouPay(BaseResponseBean3 response, ResultType resultType, String errorMsg);

            /**
             * 未登录情况下获取问题与答案回调
             * @param response
             * @param resultType
             * @param errorMsg
             */
            void onQuestionAndOption(BaseResponseBean3<QuestionOptionResponse> response, ResultType resultType, String errorMsg);

        }

        interface BaseScaleDictInfoPresenter{
            void getScaleDictInfo(ScaleDictInfoRequest scaleDictInfoRequest, LifecycleProvider lifecycleProvider);

            void getScaleQuestionOption(ScaleDictInfoRequest scaleDictInfoRequest, LifecycleProvider lifecycleProvider);

            void collectScaleDict(ScaleDictInfoRequest request, LifecycleProvider lifecycleProvider);

            void cancleCollectScaleDict(ScaleDictInfoRequest request, LifecycleProvider lifecycleProvider);

            void xindouPay(XindouPayRequestBean request, LifecycleProvider lifecycleProvider);

            /**
             * 未登录时获取两边问题与答案
             * @param map
             */
            void getQuestionOption(Map map);
        }
     }

     interface ScaleQuestionOption{
         interface ScaleQuestionOptionView extends IBaseRetrofitView {

             void onDoAssess(BaseResponseBean3<DoAssessResponseBean> response, ResultType resultType, String errorMsg);

             void onGetInforMation(InforMationBean inforMationBean, ResultType resultType, String errorMsg);

             void onCollectScaleDict(BaseResponseBean2 response, ResultType resultType, String errorMsg);

             void onCancleCollectScaleDict(BaseResponseBean2 response, ResultType resultType, String errorMsg);
         }

         interface BaseScaleQuestionOptionPresenter {

             void getDoAssess(CommitRequest commitRequest, LifecycleProvider lifecycleProvider);

             void getInforMation(LifecycleProvider lifecycleProvider);

             void collectScaleDict(ScaleDictInfoRequest request, LifecycleProvider lifecycleProvider);

             void cancleCollectScaleDict(ScaleDictInfoRequest request, LifecycleProvider lifecycleProvider);
         }
     }

    interface EvaluationResult {
        interface View extends IBaseRetrofitView {
            void onGetEvaluationResult(EvaluationResultBean responseBean, EvaluationResultType type, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getEvaluationResult(EvaluationResultRequest evaluationResultRequest,EvaluationResultType type, LifecycleProvider lifecycleProvider);
        }
    }
}
