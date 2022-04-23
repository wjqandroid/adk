package com.visionvera.psychologist.c.module.home.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.BaseMvpObserver;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.SuggestListBean;
import com.visionvera.psychologist.c.module.home.bean.BannerBean;
import com.visionvera.psychologist.c.module.home.bean.LiveBannerBean;
import com.visionvera.psychologist.c.module.home.bean.MeetingRoomBean;
import com.visionvera.psychologist.c.module.home.bean.RecommendArticleBean;
import com.visionvera.psychologist.c.module.home.bean.RecommendCourseBean;
import com.visionvera.psychologist.c.module.home.bean.SelectedEvaluationBean;
import com.visionvera.psychologist.c.module.home.contract.IContract;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorResponseBean;
import com.visionvera.psychologist.c.net.EvaluationModuleApi;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

public class HomePresenter extends BasePresenter<IContract.Home.View> implements IContract.Home.Presenter {

    EvaluationModuleModel.Home model;
    EvaluationModuleModel.HomeHot homeHotModel;

    public HomePresenter(Context context, IContract.Home.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new Home();
        homeHotModel = new EvaluationModuleModel().new HomeHot();
    }

    @Override
    public void getSystemBanner(RequestBody requestBody, LifecycleProvider lifecycleProvider) {
        model.getSystemBananer(requestBody, lifecycleProvider, new Observer<BannerBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BannerBean bannerBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bannerBean.getCode() == Constant.NetCode.success2) {
                    if (bannerBean.getResult() == null) {
                        mView.onGetSystemBanner(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetSystemBanner(bannerBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetSystemBanner(bannerBean, IBaseView.ResultType.SERVER_ERROR, bannerBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetSystemBanner(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void getHeartHelpMeetingRoomInfo(boolean isCallVideo, LifecycleProvider lifecycleProvider) {
        model.getHeartHelpMeetingRoomInfo(lifecycleProvider, new Observer<MeetingRoomBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MeetingRoomBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onHeartHelpMeetingRoomInfo(isCallVideo, null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onHeartHelpMeetingRoomInfo(isCallVideo, bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                    }
                } else {
                    mView.onHeartHelpMeetingRoomInfo(isCallVideo, bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onHeartHelpMeetingRoomInfo(isCallVideo, null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void getLiveBanner(int courseId, LifecycleProvider lifecycleProvider) {
        model.getLiveBanner(lifecycleProvider, new Observer<LiveBannerBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LiveBannerBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onLiveBanner(courseId, null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onLiveBanner(courseId, bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getMsg());
                    }
                } else {
                    mView.onLiveBanner(courseId, bean, IBaseView.ResultType.SERVER_ERROR, bean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onLiveBanner(courseId, null, IBaseView.ResultType.NET_ERROR, e.toString());
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

    //获取精选测评数据
    @Override
    public void getHotEvaluation(RequestBody requestBody, LifecycleProvider lifecycleProvider) {
        homeHotModel.getHotEvaluation(requestBody, lifecycleProvider, new Observer<BaseResponseBean3<SelectedEvaluationBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean3<SelectedEvaluationBean> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onGetHotEvaluation(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetHotEvaluation(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetHotEvaluation(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetHotEvaluation(null, IBaseView.ResultType.NET_ERROR, e.toString());
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

    //新版首页-文章推荐数据请求
    @Override
    public void getArticleRecommendation(LifecycleProvider lifecycleProvider) {
        model.getArticleRecommendation(lifecycleProvider, new Observer<BaseResponseBean3<List<RecommendArticleBean>>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull BaseResponseBean3<List<RecommendArticleBean>> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onGetArticleRecommendation(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetArticleRecommendation(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetArticleRecommendation(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetArticleRecommendation(null, IBaseView.ResultType.NET_ERROR, e.toString());
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

    //新版首页-精彩课程数据请求
    @Override
    public void getRecommendCourse(LifecycleProvider lifecycleProvider) {
        model.getRecommendCourse(lifecycleProvider, new Observer<BaseResponseBean3<List<RecommendCourseBean>>>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull BaseResponseBean3<List<RecommendCourseBean>> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onGetRecommendCourse(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetRecommendCourse(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetRecommendCourse(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetRecommendCourse(null, IBaseView.ResultType.NET_ERROR, e.toString());
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

    //推荐咨询师
    @Override
    public void getSuggestList(LifecycleProvider lifecycleProvider) {
        model.getSuggestList(lifecycleProvider, new Observer<SuggestListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SuggestListBean responseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (responseBean.getCode() == Constant.NetCode.success2) {
                    if (responseBean.getResult() == null) {
                        mView.onGetSuggestList(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetSuggestList(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetSuggestList(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("onError"+e);
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

    //推荐医生列表
    @Override
    public void getRecommendStaffList(LifecycleProvider lifecycleProvider) {
        getData(RetrofitManager.create(EvaluationModuleApi.class).getRecommendStaffList(),
                new BaseMvpObserver<BaseResponseBean3<List<DoctorResponseBean>>>(mView,false){

                    @Override
                    protected void onResult(BaseResponseBean3<List<DoctorResponseBean>> responseBean, IBaseView.ResultType resultType, String errorMsg) {
                        mView.onGetRecommendStaffList(responseBean,resultType,errorMsg);
                    }
                });

    }

}
