package com.visionvera.psychologist.c.net;

import android.app.Activity;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.bean.BaseResponseBean4;
import com.visionvera.library.base.mvp.model.IBaseModel;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.CommitRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDIctInfoResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDictInfoRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleQuestionOptionResponse;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListResponseBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.AliPrePayRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.AliPrePayResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.ConfirmOrderDoneRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.ConsulantListResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.CounselorDetailBean;
import com.visionvera.psychologist.c.module.counselling.bean.ExpertiesResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.NewOrderConsultRequest;
import com.visionvera.psychologist.c.module.counselling.bean.NewOrderconsultBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderCancelRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultCancelRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultDetailBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultDetailRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultListBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultListRequest;
import com.visionvera.psychologist.c.module.counselling.bean.QueryPaysStatusRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.QueryPaysStatusResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.ReportInformationBeanRequest;
import com.visionvera.psychologist.c.module.counselling.bean.SaveMeetingRecordRequest;
import com.visionvera.psychologist.c.module.counselling.bean.SuggestListBean;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarBean;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarRequest;
import com.visionvera.psychologist.c.module.counselling.bean.UserUpdateAgainConsultInfoRequest;
import com.visionvera.psychologist.c.module.counselling.bean.WeixinPrePayRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.WeixinPrePayResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.XindouPayRequestBean;
import com.visionvera.psychologist.c.module.home.bean.BannerBean;
import com.visionvera.psychologist.c.module.home.bean.EvaluationTypeBean;
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
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddArticleRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddCommentRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddCommentResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleLikeOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleListRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleListResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CollectOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentLikeOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentListRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentListResponseBean;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.module.myevaluation.bean.ReTestRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.activity.SearchDoctorActivity;
import com.visionvera.psychologist.c.module.ordertreatment.bean.AddEvaluateRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentAgainConsultRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentCancelRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SearchDoctorBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubjectListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubjectListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.TagListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.TagListResponseBean;
import com.visionvera.psychologist.c.module.search.bean.DiscoverRequestBean;
import com.visionvera.psychologist.c.module.search.bean.DiscoverResponseBean;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListBean;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListRequestBean;
import com.visionvera.psychologist.c.module.usercenter.bean.ChangePassRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.ForgetPasswordCheckNumberRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.ForgetPasswordCommitRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.MessageBean;
import com.visionvera.psychologist.c.module.usercenter.bean.UserBasicInfoBean;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class EvaluationModuleModel implements IBaseModel {
    /**
     * 对原始的observable进行线程操作.不破坏原始的链式调用
     *
     * @return
     */
    public ObservableTransformer getThreadTransformer() {
        ObservableTransformer observableTransformer = new ObservableTransformer() {

            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
        return observableTransformer;
    }

    public class SelfAssessmentGauge {
        public void xindouPay(XindouPayRequestBean request,
                              LifecycleProvider lifecycleProvider,
                              Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .xindouPay(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getScaleDictInfo(ScaleDictInfoRequest request,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<ScaleDIctInfoResponse> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getScaleDictInfo(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getScaleQuestionOption(ScaleDictInfoRequest request,
                                           LifecycleProvider lifecycleProvider,
                                           Observer<BaseResponseBean3<QuestionOptionResponse>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getScaleQuestionOption(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        public void getDoAssess(CommitRequest commitRequest,
                                LifecycleProvider lifecycleProvider,
                                Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getDoAssess(commitRequest)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);


        }

        public void getCollectScaleDict(ScaleDictInfoRequest request,
                                        LifecycleProvider lifecycleProvider,
                                        Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getCollectScaleDict(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void cancleCollectScaleDict(ScaleDictInfoRequest request,
                                           LifecycleProvider lifecycleProvider,
                                           Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .cancleCollectScaleDict(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getInforMation(LifecycleProvider lifecycleProvider,
                                   Observer<InforMationBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getInforMation()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


    }

    //所有测试
    public class AllEvaluation {
        public void getTabTitles(TabTitleRequestBean request,
                                 LifecycleProvider lifecycleProvider,
                                 Observer<TabTitleResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getTabTitles(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getEvaluationChatList(EvaluationChatListRequestBean request,
                                          LifecycleProvider lifecycleProvider,
                                          Observer<EvaluationChatListResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getEvaluationChatList(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }

    //搜索
    public class Search {
        public void getDiscover(DiscoverRequestBean request,
                                LifecycleProvider lifecycleProvider,
                                Observer<DiscoverResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getDiscover(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }

    //首页
    public class Home {
        /**
         * 获取轮播图
         *
         * @param request
         * @param lifecycleProvider
         * @param observer
         */
        public void getSystemBananer(RequestBody request,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<BannerBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getSystemBananer(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 获取轮播图
         *
         * @param request
         * @param lifecycleProvider
         * @param observer
         */
        public void getEvaluationType(RequestBody request,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<EvaluationTypeBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getEvaluationType(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 首页获取心理援助的上级会议室信息
         */
        public void getHeartHelpMeetingRoomInfo(LifecycleProvider lifecycleProvider,
                                                Observer<MeetingRoomBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getHeartHelpMeetingRoomInfo()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        /**
         * 获取直播轮播图的数据
         */
        public void getLiveBanner(LifecycleProvider lifecycleProvider,
                                  Observer<LiveBannerBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getLiveBanner()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 新版首页获取文章推荐列表
         */
        public void getArticleRecommendation(LifecycleProvider lifecycleProvider,
                                             Observer<BaseResponseBean3<List<RecommendArticleBean>>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .recommendArticalList()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /*
         *新版首页精彩课程列表
         * */
        public void getRecommendCourse(LifecycleProvider lifecycleProvider,
                                       Observer<BaseResponseBean3<List<RecommendCourseBean>>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getRecommendCourse()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 获取推荐咨询师列表
         *
         * @param lifecycleProvider
         * @param observer
         */
        public void getSuggestList(LifecycleProvider lifecycleProvider,
                                   Observer<SuggestListBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getSguuestList()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 获取推荐医生列表
         *
         * @param lifecycleProvider
         * @param observer
         */
        public void getRecommendStaffList(LifecycleProvider lifecycleProvider,
                                          Observer<DoctorListResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getRecommendStaffList()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }

    //首页精选测评
    public class HomeHot {
        /**
         * 获取精选测评
         *
         * @param request
         * @param lifecycleProvider
         * @param observer
         */
        public void getHotEvaluation(RequestBody request,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<BaseResponseBean3<SelectedEvaluationBean>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getHotEvaluation(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getHotEvaluation2(RequestBody request,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<HotEvaluationBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getHotEvaluation2(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }

    //首页我的收藏
    public class HomeCollect {
        /**
         * 获取我的收藏
         *
         * @param request
         * @param lifecycleProvider
         * @param observer
         */
        public void getCollectEvaluation(RequestBody request,
                                         LifecycleProvider lifecycleProvider,
                                         Observer<MyEvaluationBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getCollectEvaluation(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    //我的测试
    public class MyEvaluation {
        public void getMyEvaluation(RequestBody request,
                                    LifecycleProvider lifecycleProvider,
                                    Observer<MyEvaluationBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getMyEvaluation(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void reTest(ReTestRequestBean request,
                           LifecycleProvider lifecycleProvider,
                           Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .reTest(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    //知识圈
    public class KnowledgeCircle {
        public void getArticleList(ArticleListRequestBean request,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<BaseResponseBean3<ArticleListResponseBean>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getArticles(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void addArticle(AddArticleRequestBean request,
                               LifecycleProvider lifecycleProvider,
                               Observer<BaseResponseBean3<Object>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .addArticle(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void collectOrNot(CollectOrNotRequestBean request,
                                 LifecycleProvider lifecycleProvider,
                                 Observer<BaseResponseBean3<Object>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .collectOrNot(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void articleLikeOrNot(ArticleLikeOrNotRequestBean request,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<BaseResponseBean3<Object>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .articleLikeOrNot(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void commentLikeOrNot(CommentLikeOrNotRequestBean request,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<BaseResponseBean3<Object>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .commentLikeOrNot(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getCommentList(CommentListRequestBean request,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<BaseResponseBean3<CommentListResponseBean>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getCommentList(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void addComment(AddCommentRequestBean request,
                               LifecycleProvider lifecycleProvider,
                               Observer<BaseResponseBean3<AddCommentResponseBean>> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .addComment(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    //游客登录
    public class VisitorLogin {
        public void getVisitorLogin(LifecycleProvider lifecycleProvider,
                                    Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getVisitorLogin()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        //二期新
        public void getUserSig(UserSigRequestBean userSigRequestBean,
                               LifecycleProvider lifecycleProvider,
                               Observer<UserSigBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getUserSig(userSigRequestBean)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    //测试结果
    public class EvaluationResult {
        public void getEvaluationResult(EvaluationResultRequest request,
                                        LifecycleProvider lifecycleProvider,
                                        Observer<EvaluationResultBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getEvaluationResult(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    //消息中心
    public class MessageCenter {
        public void getMessage(RequestBody request,
                               LifecycleProvider lifecycleProvider,
                               Observer<MessageBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getMessage(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    /**
     * 设置中心
     */
    public class Setting {
        /**
         * 修改密码
         */
        public void getChangePass(ChangePassRequest requestBean,
                                  LifecycleProvider lifecycleProvider,
                                  Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .changePassword(requestBean)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 获取忘记密码的验证码
         */
        public void getForgetPasswordCheckNumber(ForgetPasswordCheckNumberRequest request,
                                                 LifecycleProvider lifecycleProvider,
                                                 Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .forgetPasswordCheckNumber(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 忘记密码提交
         */
        public void getForgetPasswordCommit(ForgetPasswordCommitRequest request,
                                            LifecycleProvider lifecycleProvider,
                                            Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .forgetPasswordCommit(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 意见反馈
         */
        public void getFeedBack(RequestBody request,
                                LifecycleProvider lifecycleProvider,
                                Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getFeedBack(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void savePath(SavePathRequestBean requestBody,
                             LifecycleProvider lifecycleProvider,
                             Observer<SavePathResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .savePath(requestBody)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        /**
         * 获取个人心豆及昵称信息
         */
        public void getUserBasicInfo(LifecycleProvider lifecycleProvider,
                                     Observer<UserBasicInfoBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getUserBasicInfo()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
        /**
         * 获取连续签到天数
         */
        public void getSignDays(LifecycleProvider lifecycleProvider,
                                Observer<BaseResponseBean4> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getSignDays()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }


    /**
     * 个人中心
     */
    public class EditInfo {
        public void getInforMation(LifecycleProvider lifecycleProvider,
                                   Observer<InforMationBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getInforMation()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getJobList(JobRequest jobRequest,
                               LifecycleProvider lifecycleProvider,
                               Observer<JobBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getJobList(jobRequest)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void saveInforMation(RequestBody requestBody,
                                    LifecycleProvider lifecycleProvider,
                                    Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .saveInforMation(requestBody)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void savePath(SavePathRequestBean requestBody,
                             LifecycleProvider lifecycleProvider,
                             Observer<SavePathResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .savePath(requestBody)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getAreaList(AreaListRequestBean areaListRequest,
                                int onNext, LifecycleProvider lifecycleProvider,
                                Observer<AreaListBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getAreaList(areaListRequest)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }


    /**
     * 预约咨询
     */
    public class OrderConsult {
        /**
         * 获取医生可用日程
         */
        public void getTimeCalendar(TimeCalendarRequest request,
                                    LifecycleProvider lifecycleProvider,
                                    Observer<TimeCalendarBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getTimeCalendar(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 心理咨询、诊疗 预约相关-新增预约申请接口
         */

        public void getNewOrderConsult(NewOrderConsultRequest request,
                                       LifecycleProvider lifecycleProvider,
                                       Observer<NewOrderconsultBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getNewOrderConsult(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 心理咨询 预约相关-支付宝预下单
         */

        public void aliPrePay(AliPrePayRequestBean request,
                              LifecycleProvider lifecycleProvider,
                              Observer<AliPrePayResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("payMethod", request.payMethod + "");
            queryMap.put("totalAmount", request.totalAmount + "");
            queryMap.put("goodsOrderNo", request.goodsOrderNo + "");
            queryMap.put("safe", request.safe + "");
            queryMap.put("sign", request.sign + "");
            queryMap.put("payUserId", request.payUserId + "");
            queryMap.put("payUserName", request.payUserName + "");
            RetrofitManager.create(EvaluationModuleApi.class)
                    .aliPrePay(queryMap)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 心理咨询 预约相关-微信预下单
         */

        public void weixinPrePay(WeixinPrePayRequestBean request,
                                 LifecycleProvider lifecycleProvider,
                                 Observer<WeixinPrePayResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("payMethod", request.payMethod + "");
            queryMap.put("totalAmount", request.totalAmount + "");
            queryMap.put("goodsOrderNo", request.goodsOrderNo + "");
            queryMap.put("safe", request.safe + "");
            queryMap.put("sign", request.sign + "");
            queryMap.put("payUserId", request.payUserId + "");
            queryMap.put("payUserName", request.payUserName + "");
            RetrofitManager.create(EvaluationModuleApi.class)
                    .weixinPrePay(queryMap)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        /**
         * 心理咨询 预约相关-获取预约列表
         */
        public void getOrderConsultList(OrderConsultListRequest request,
                                        LifecycleProvider lifecycleProvider,
                                        Observer<OrderConsultListBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderConsultList(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 心理咨询、诊疗 预约相关-查询真正的支付结果
         */
        public void queryPayStatus(QueryPaysStatusRequestBean request,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<QueryPaysStatusResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .queryPayStatus(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        /**
         * 心理咨询 预约咨询、预约诊疗订单列表详情接口
         */
        public void getOrderConsultDetail(OrderConsultDetailRequest request,
                                          LifecycleProvider lifecycleProvider,
                                          Observer<OrderConsultDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderConsultDetail(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 移动端获取咨询师详情
         */
        public void getCounselorDetail(int id,
                                       LifecycleProvider lifecycleProvider,
                                       Observer<CounselorDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderConsultDetail(id)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 移动端获取咨询师详情通过userId
         */
        public void getCounselorDetailByUserId(int id,
                                               LifecycleProvider lifecycleProvider,
                                               Observer<CounselorDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderConsultDetailByUserId(id)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 废弃
         * 预约模块取消按钮
         */
        public void getOrderConsultCancel(OrderConsultCancelRequest request,
                                          LifecycleProvider lifecycleProvider,
                                          Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderConsultCancel(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        //废弃
        //可能是视联网用的
        public void saveMeetingRecord(SaveMeetingRecordRequest saveMeetingRecordRequest,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .saveMeetingRecord(saveMeetingRecordRequest)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        //废弃
        //心理咨询 预约相关-用户编辑会议室信息接口
        public void getUserUpdateAgainConsultInfo(UserUpdateAgainConsultInfoRequest request,
                                                  LifecycleProvider lifecycleProvider,
                                                  Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getUserUpdateAgainConsultInfo(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void confirmDone(ConfirmOrderDoneRequestBean request,
                                LifecycleProvider lifecycleProvider,
                                Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .confirmOrderDone(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        //完善信息前获取信息
        public void getInforMation(LifecycleProvider lifecycleProvider,
                                   Observer<InforMationBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getInforMation()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        //cos上传文件后的保存信息到自己服务器的接口
        public void savePath(SavePathRequestBean requestBody,
                             LifecycleProvider lifecycleProvider,
                             Observer<SavePathResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .savePath(requestBody)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        //二期新
        //咨询/诊疗上报操作信息接口
        //用户发起聊天、用户接听、用户确认服务状态    用于判断按钮显示
        public void reportInformation(ReportInformationBeanRequest request,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .reportInformation(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 二期新
         * 取消咨询/诊疗申请   订单
         * 接口说明：已经付款的订单1天之内无法取消，必传参数不为空校验，订单已完成、已取消、已作废取消检验。
         */
        public void getOrderCancel(OrderCancelRequest request,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderCancel(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 二期新
         *驳回咨询/诊疗申请
         */
        public void getOrderReject(OrderCancelRequest request,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderReject(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 二期新
         *受理咨询/诊疗申请
         */
        public void getOrderAccept(OrderCancelRequest request,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderAccept(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 二期新
         * 查询咨询师详情
         */
        public void getOrderDoctorDetail(int id,
                                         LifecycleProvider lifecycleProvider,
                                         Observer<CounselorDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderDoctorDetail(id)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 二期新
         * 查询医生详情
         */
        public void getDoctorDetailByUserId(DoctorDetailRequest request,
                                            LifecycleProvider lifecycleProvider,
                                            Observer<DoctorDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getDoctorDetailByUserId(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 二期新
         * 获取咨询师评价列表
         */
        public void getUserEvaluateList(EvaluateListRequest request,
                                        LifecycleProvider lifecycleProvider,
                                        Observer<EvaluateListBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getUserEvaluateList(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 二期新
         * app新增评价
         */
        public void getAddEvaluate(AddEvaluateRequest request,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getAddEvaluate(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
        /**
         * 二期新
         * 获取评价详情
         */
        public void getEvaluateDetail(EvaluateListRequest request,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<EvaluateDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getEvaluateDetail(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }

    /**
     * 预约咨询(咨询师)
     */
    public class MakeAppoint {
        /**
         * 获取擅长领域
         *
         * @param lifecycleProvider
         * @param observer
         */
        public void getExpertiesList(LifecycleProvider lifecycleProvider,
                                     Observer<ExpertiesResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getExpertiesList()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 获取咨询师列表
         *
         * @param requestBean
         * @param lifecycleProvider
         * @param observer
         */
        public void getConsulantList(RequestBody requestBean,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<ConsulantListResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getConsulantList(requestBean)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 获取推荐咨询师列表
         *
         * @param lifecycleProvider
         * @param observer
         */
        public void getSuggestList(LifecycleProvider lifecycleProvider,
                                   Observer<SuggestListBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getSguuestList()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }

    //预约诊疗模块
    public class OrderTreatment {
        public void getSubjectList(SubjectListRequestBean requestBean,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<SubjectListResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getSubjectList(requestBean)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);

        }
        /**
         * 二期新
         * 查询医生详情
         */
        public void getDoctorDetailByUserId(DoctorDetailRequest request,
                                            LifecycleProvider lifecycleProvider,
                                            Observer<DoctorDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getDoctorDetailByUserId(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        /**
         * 获取推荐机构
         *
         * @param lifecycleProvider
         * @param observer
         */
        public void getSuggestList(RecommentHospitalsRequestBean requestBean, LifecycleProvider lifecycleProvider,
                                   Observer<RecommentHospitalsResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getRecommendHospital(requestBean)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getDoctorList(DoctorListRequestBean requestBean,
                                  LifecycleProvider lifecycleProvider,
                                  Observer<DoctorListResponseBean> observer) {
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getDoctorList(requestBean)
                    .compose(lifecycleProvider.<DoctorListResponseBean>bindToLifecycle())
                    .compose(getThreadTransformer())
                    .subscribe(observer);

        }

        /**
         * 获取擅长类型tag
         *
         * @param requestBean
         * @param lifecycleProvider
         * @param observer
         */
        public void getTagList(TagListRequestBean requestBean,
                               LifecycleProvider lifecycleProvider,
                               Observer<TagListResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getTagList(requestBean)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);

        }

        public void getDoctorDetail(DoctorDetailRequest request,
                                    LifecycleProvider lifecycleProvider,
                                    Observer<DoctorDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getDoctorDetail(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void orderTreatmentSubmit(SubmitOrderRequestBean requestBean,
                                         LifecycleProvider lifecycleProvider,
                                         Observer<SubmitOrderResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .orderSubmit(requestBean)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);

        }

        public void getInforMation(LifecycleProvider lifecycleProvider,
                                   Observer<InforMationBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getInforMation()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void savePath(SavePathRequestBean requestBody,
                             LifecycleProvider lifecycleProvider,
                             Observer<SavePathResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .savePath(requestBody)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 二期新
         * 获取咨询师评价列表
         */
        public void getUserEvaluateList(EvaluateListRequest request,
                                        LifecycleProvider lifecycleProvider,
                                        Observer<EvaluateListBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getUserEvaluateList(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }


    /**
     * 预约诊疗---搜索医生
     */
    public class SearchDoctor {

        public void getSearchDoctor(SearchDoctorActivity.SearchDoctorRequest request,
                                    LifecycleProvider lifecycleProvider,
                                    Observer<SearchDoctorBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .searchDoctor(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    /**
     * 预约诊疗----订单列表
     */
    public class OrderTreatmentList {
        public void getOrderTreatmentList(OrderTreatmentListRequest request,
                                          LifecycleProvider lifecycleProvider,
                                          Observer<OrderTreatmentListBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderTreatmentList(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    /**
     * 心理 预约相关-app获取预约详情接口
     */
    public class OrderTreatmentListDetail {
        //获取app的预约诊疗详情接口
        public void getOrderTreatmentListDetail(OrderTreatmentListDetailRequest request,
                                                LifecycleProvider lifecycleProvider,
                                                Observer<OrderTreatmentListDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderTreatmentListDetail(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        //预约诊疗的详情页面的取消操作
        public void getOrderTreatmentCancel(OrderTreatmentCancelRequest request,
                                            LifecycleProvider lifecycleProvider,
                                            Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderTreatmentCancel(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }


        //保存会议开启记录
        public void getSaveMeetingRecord(SaveMeetingRecordRequest request,
                                         LifecycleProvider lifecycleProvider,
                                         Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .saveMeetingRecord(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        public void getUserUpdateAgainConsultInfo(OrderTreatmentAgainConsultRequest request,
                                                  LifecycleProvider lifecycleProvider,
                                                  Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getTreatmentAgainConsultInfo(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }


    public class EveryDaySignIn {
        /**
         * 查询打卡签到
         */
        public void getSignInByWeek(LifecycleProvider lifecycleProvider,
                                    Observer<EveryDaySignInBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getSignInByWeek()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 提交打卡
         */
        public void getSaveSignIn(LifecycleProvider lifecycleProvider,
                                  Observer<SignInBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getSaveSignIn()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }

    public class LoginOut {
        /**
         * 退出登录
         */
        public void getLoginOut(LifecycleProvider lifecycleProvider,
                                Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getLoginOut()
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }
    }
}
