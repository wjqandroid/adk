package com.visionvera.psychologist.c.module.knowledge_library.presenter;


import android.content.Context;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddCommentRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddCommentResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleLikeOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleListRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleListResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CollectOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentLikeOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentListRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentListResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ArticalDetailActivityPresenter extends BasePresenter<IContract.ArticalDetailActivity.View> implements IContract.ArticalDetailActivity.Presenter {
    EvaluationModuleModel.KnowledgeCircle model;

    public ArticalDetailActivityPresenter(Context context, IContract.ArticalDetailActivity.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new KnowledgeCircle();
    }

    @Override
    public void getArticleDetail(@NotNull ArticleListRequestBean requestBean, boolean isOnlyNeedCounts, @Nullable LifecycleProvider<?> lifecycleProvider) {
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
                        mView.onGetArticleDetail(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null), isOnlyNeedCounts);
                    } else {
                        mView.onGetArticleDetail(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "", isOnlyNeedCounts);
                    }
                } else {
                    mView.onGetArticleDetail(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg(), isOnlyNeedCounts);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                Logger.e("请求出错" + e.toString());
                mView.onGetArticleDetail(null, IBaseView.ResultType.NET_ERROR, e.toString(), isOnlyNeedCounts);
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
    public void getArticalAnswers(@NotNull ArticleListRequestBean requestBean, @Nullable LifecycleProvider<?> lifecycleProvider) {
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
                        mView.onGetArticalAnswers(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onGetArticalAnswers(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onGetArticalAnswers(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                Logger.e("请求出错" + e.toString());
                mView.onGetArticalAnswers(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void articleLikeOrNot(@NotNull ArticleLikeOrNotRequestBean requestBean, int articleId, boolean like, @Nullable LifecycleProvider<?> lifecycleProvider) {
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
                        mView.onArticleLikeOrNot(null, articleId, like, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onArticleLikeOrNot(bean, articleId, like, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onArticleLikeOrNot(bean, articleId, like, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onArticleLikeOrNot(null, articleId, like, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void collectOrNot(@NotNull CollectOrNotRequestBean requestBean, int articleId, boolean collect, @Nullable LifecycleProvider<?> lifecycleProvider) {
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
                        mView.onCollectOrNot(null, articleId, collect, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onCollectOrNot(bean, articleId, collect, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onCollectOrNot(bean, articleId, collect, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onCollectOrNot(null, articleId, collect, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void commentLikeOrNot(@NotNull CommentLikeOrNotRequestBean requestBean, int commentId, boolean like, @Nullable LifecycleProvider<?> lifecycleProvider) {
        model.commentLikeOrNot(requestBean, lifecycleProvider, new Observer<BaseResponseBean3<Object>>() {
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
                        mView.onCommentLikeOrNot(null, commentId, like, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onCommentLikeOrNot(bean, commentId, like, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onCommentLikeOrNot(bean, commentId, like, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onCommentLikeOrNot(null, commentId, like, IBaseView.ResultType.NET_ERROR, e.toString());
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
    public void addComment(@NotNull AddCommentRequestBean requestBean, int id, int position, @Nullable LifecycleProvider<?> lifecycleProvider) {
        model.addComment(requestBean, lifecycleProvider, new Observer<BaseResponseBean3<AddCommentResponseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean3<AddCommentResponseBean> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onAddComment(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null), id, position);
                    } else {
                        mView.onAddComment(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "", id, position);
                    }
                } else {
                    mView.onAddComment(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg(), id, position);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onAddComment(null, IBaseView.ResultType.NET_ERROR, e.toString(), id, position);
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
    public void getCommentList(boolean isRefresh, @NotNull CommentListRequestBean requestBean, @Nullable LifecycleProvider<?> lifecycleProvider) {
        model.getCommentList(requestBean, lifecycleProvider, new Observer<BaseResponseBean3<CommentListResponseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean3<CommentListResponseBean> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onGetCommentList(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null), isRefresh);
                    } else {
                        mView.onGetCommentList(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "", isRefresh);
                    }
                } else {
                    mView.onGetCommentList(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg(), isRefresh);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onGetCommentList(null, IBaseView.ResultType.NET_ERROR, e.toString(), isRefresh);
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
