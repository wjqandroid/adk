package com.visionvera.psychologist.c.module.home.contract;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.psychologist.c.module.counselling.bean.SuggestListBean;
import com.visionvera.psychologist.c.module.home.bean.BannerBean;
import com.visionvera.psychologist.c.module.home.bean.EveryDaySignInBean;
import com.visionvera.psychologist.c.module.home.bean.HotEvaluationBean;
import com.visionvera.psychologist.c.module.home.bean.LiveBannerBean;
import com.visionvera.psychologist.c.module.home.bean.MeetingRoomBean;
import com.visionvera.psychologist.c.module.home.bean.RecommendArticleBean;
import com.visionvera.psychologist.c.module.home.bean.RecommendCourseBean;
import com.visionvera.psychologist.c.module.home.bean.SelectedEvaluationBean;
import com.visionvera.psychologist.c.module.home.bean.SignInBean;
import com.visionvera.psychologist.c.module.home.bean.UserSigBean;
import com.visionvera.psychologist.c.module.home.bean.UserSigRequestBean;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorResponseBean;

import java.util.List;

import okhttp3.RequestBody;

public interface IContract {
    interface Home {
        interface View extends IBaseRetrofitView {
            void onGetSystemBanner(BannerBean responseBean, ResultType resultType, String errorMsg);

//            void onGetEvaluationType(EvaluationTypeBean responseBean, ResultType resultType, String errorMsg);
            /**
             * @param isCallVideo  请求完数据是否直接呼叫
             */
            void onHeartHelpMeetingRoomInfo(boolean isCallVideo,MeetingRoomBean meetingRoomBean,ResultType resultType, String errorMsg);

            void onLiveBanner(int courseId, LiveBannerBean liveBannerBean, ResultType resultType, String errorMsg);

            void onGetHotEvaluation(BaseResponseBean3<SelectedEvaluationBean> responseBean, ResultType resultType, String errorMsg);

            void onGetArticleRecommendation(BaseResponseBean3<List<RecommendArticleBean>> responseBean, ResultType resultType, String errorMsg);

            void onGetRecommendCourse(BaseResponseBean3<List<RecommendCourseBean>> responseBean, ResultType resultType, String errorMsg);

            void onGetSuggestList(SuggestListBean responseBean, ResultType resultType, String errorMsg);

            void onGetRecommendStaffList(BaseResponseBean3<List<DoctorResponseBean>> responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getSystemBanner(RequestBody requestBody, LifecycleProvider lfecycleProvider);

//            void getEvaluationType(RequestBody requestBody, LifecycleProvider lifecycleProvider);

            /**
             * @param isCallVideo  请求完数据是否直接呼叫
             */
            void getHeartHelpMeetingRoomInfo(boolean isCallVideo,LifecycleProvider lifecycleProvider);

            void getLiveBanner(int courseId, LifecycleProvider lifecycleProvider);

            void getHotEvaluation(RequestBody requestBody, LifecycleProvider lifecycleProvider);

            //获取文章推荐
            void getArticleRecommendation(LifecycleProvider lifecycleProvider);

            //获取精彩课程
            void getRecommendCourse(LifecycleProvider lifecycleProvider);

            //推荐咨询师
            void getSuggestList(LifecycleProvider lifecycleProvider);

            //推荐医生
            void getRecommendStaffList(LifecycleProvider lifecycleProvider);
        }
    }

    interface HomeHot {
        interface View extends IBaseRetrofitView {
            void onGetHotEvaluation(HotEvaluationBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getHotEvaluation(RequestBody requestBody, LifecycleProvider lifecycleProvider);
        }
    }

    interface HomeCollect {
        interface View extends IBaseRetrofitView {
            void onGetCollectEvaluation(MyEvaluationBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getCollectEvaluation(RequestBody requestBody, LifecycleProvider lifecycleProvider);
        }
    }

    interface VisitorLogin{
        interface VisitorLoginView extends IBaseRetrofitView{
            void onVisitorLogin(BaseResponseBean2 response,ResultType resultType, String errorMsg);

            void onUserSig(UserSigBean userSigBean,ResultType resultType, String errorMsg);
        }
        interface VisitorLoginBasePresenter{
            void getVisitorLogin(LifecycleProvider lifecycleProvider);

            void getUserSig(UserSigRequestBean userSigRequestBean,LifecycleProvider lifecycleProvider);
        }
    }


    interface EveryDaySignIn{
        interface EveryDaySignInView extends IBaseRetrofitView{
            void onSignInByWeek(EveryDaySignInBean bean,ResultType resultType, String errorMsg);

            void onSaveSignIn(SignInBean response, ResultType resultType, String errorMsg);
        }

        interface EveryDaySignInBasePresenter{
            void getSignInByWeek(LifecycleProvider lifecycleProvider);

            void getSaveSignIn(LifecycleProvider lifecycleProvider);
        }
    }

}
