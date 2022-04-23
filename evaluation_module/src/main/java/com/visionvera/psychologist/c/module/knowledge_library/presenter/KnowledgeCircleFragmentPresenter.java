package com.visionvera.psychologist.c.module.knowledge_library.presenter;


import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleLikeOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleListRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleListResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CollectOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class KnowledgeCircleFragmentPresenter extends BasePresenter<IContract.KnowledgeCircleFragment.View> implements IContract.KnowledgeCircleFragment.Presenter {

    EvaluationModuleModel.KnowledgeCircle model;

    public KnowledgeCircleFragmentPresenter(Context context, IContract.KnowledgeCircleFragment.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new KnowledgeCircle();
    }


    @Override
    public void getArticles(boolean isRefresh, @NotNull ArticleListRequestBean requestBean, @Nullable LifecycleProvider<?> lifecycleProvider) {
        model.getArticleList(requestBean, lifecycleProvider, new Observer<BaseResponseBean3<ArticleListResponseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean3<ArticleListResponseBean> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onGetArticles(isRefresh, null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetArticles(isRefresh, bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetArticles(isRefresh, bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetArticles(isRefresh, null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void articleLikeOrNot(@NotNull ArticleLikeOrNotRequestBean requestBean, int articleId, boolean like, int position, @Nullable LifecycleProvider<?> lifecycleProvider) {
        model.articleLikeOrNot(requestBean, lifecycleProvider, new Observer<BaseResponseBean3<Object>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean3<Object> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onArticleLikeOrNot(null, articleId, like, position, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onArticleLikeOrNot(bean, articleId, like, position, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onArticleLikeOrNot(bean, articleId, like, position, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onArticleLikeOrNot(null, articleId, like, position, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void collectOrNot(@NotNull CollectOrNotRequestBean requestBean, int articleId, boolean collect, int position, @Nullable LifecycleProvider<?> lifecycleProvider) {
        model.collectOrNot(requestBean, lifecycleProvider, new Observer<BaseResponseBean3<Object>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean3<Object> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onCollectOrNot(null, articleId, collect, position, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onCollectOrNot(bean, articleId, collect, position, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onCollectOrNot(bean, articleId, collect, position, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onCollectOrNot(null, articleId, collect, position, IBaseView.ResultType.NET_ERROR, e.toString());
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
