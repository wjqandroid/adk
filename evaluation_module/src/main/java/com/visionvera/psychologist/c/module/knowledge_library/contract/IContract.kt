package com.visionvera.psychologist.c.module.knowledge_library.contract

import com.trello.rxlifecycle2.LifecycleProvider
import com.visionvera.library.base.bean.BaseResponseBean3
import com.visionvera.library.base.mvp.view.IBaseRetrofitView
import com.visionvera.library.base.mvp.view.IBaseView
import com.visionvera.psychologist.c.module.knowledge_library.bean.*

interface IContract {
    interface KnowledgeCircleFragment {
        interface View : IBaseRetrofitView {
            fun onGetArticles(isRefresh: Boolean, responseBean: BaseResponseBean3<ArticleListResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?)
            fun onCollectOrNot(responseBean: BaseResponseBean3<Any>?, articleId: Int, collect: Boolean, position: Int, resultType: IBaseView.ResultType, errorMsg: String?)
            fun onArticleLikeOrNot(responseBean: BaseResponseBean3<Any>?, articleId: Int, like: Boolean, position: Int, resultType: IBaseView.ResultType, errorMsg: String?)
        }

        interface Presenter {
            fun getArticles(isRefresh: Boolean, requestBean: ArticleListRequestBean, lifecycleProvider: LifecycleProvider<*>?)
            fun collectOrNot(requestBean: CollectOrNotRequestBean, articleId: Int, collect: Boolean, position: Int, lifecycleProvider: LifecycleProvider<*>?)
            fun articleLikeOrNot(requestBean: ArticleLikeOrNotRequestBean, articleId: Int, like: Boolean, position: Int, lifecycleProvider: LifecycleProvider<*>?)
        }
    }

    interface ArticalDetailActivity {
        interface View : IBaseRetrofitView {
            fun onGetArticleDetail(responseBean: BaseResponseBean3<ArticleListResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?, isOnlyNeedCounts: Boolean)
            fun onGetArticalAnswers(responseBean: BaseResponseBean3<ArticleListResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?)
            fun onCollectOrNot(responseBean: BaseResponseBean3<Any>?, articleId: Int, collect: Boolean, resultType: IBaseView.ResultType, errorMsg: String?)
            fun onArticleLikeOrNot(responseBean: BaseResponseBean3<Any>?, articleId: Int, like: Boolean, resultType: IBaseView.ResultType, errorMsg: String?)
            fun onCommentLikeOrNot(responseBean: BaseResponseBean3<Any>?, commentId: Int, like: Boolean, resultType: IBaseView.ResultType, errorMsg: String?)
            fun onAddComment(responseBean: BaseResponseBean3<AddCommentResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?, id: Int, position: Int)
            fun onGetCommentList(responseBean: BaseResponseBean3<CommentListResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?, isRefresh: Boolean)
        }

        interface Presenter {
            fun getArticleDetail(requestBean: ArticleListRequestBean, isOnlyNeedCounts: Boolean, lifecycleProvider: LifecycleProvider<*>?)
            fun getArticalAnswers(requestBean: ArticleListRequestBean, lifecycleProvider: LifecycleProvider<*>?)
            fun collectOrNot(requestBean: CollectOrNotRequestBean, articleId: Int, collect: Boolean, lifecycleProvider: LifecycleProvider<*>?)
            fun articleLikeOrNot(requestBean: ArticleLikeOrNotRequestBean, articleId: Int, like: Boolean, lifecycleProvider: LifecycleProvider<*>?)
            fun commentLikeOrNot(requestBean: CommentLikeOrNotRequestBean, commnetId: Int, like: Boolean, lifecycleProvider: LifecycleProvider<*>?)
            fun addComment(requestBean: AddCommentRequestBean, id: Int, position: Int, lifecycleProvider: LifecycleProvider<*>?)
            fun getCommentList(isRefresh: Boolean, requestBean: CommentListRequestBean, lifecycleProvider: LifecycleProvider<*>?)
        }
    }

    interface WriteArticleActivity {
        interface View : IBaseRetrofitView {
            fun onAddArticle(responseBean: BaseResponseBean3<*>?, resultType: IBaseView.ResultType, errorMsg: String?)
        }

        interface Presenter {
            fun addArticle(requestBean: AddArticleRequestBean, lifecycleProvider: LifecycleProvider<*>?)
        }
    }

    interface DraftListActivity {
        interface View : IBaseRetrofitView {
        }

        interface Presenter {
        }
    }
}