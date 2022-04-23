package com.visionvera.psychologist.c.module.knowledge_library.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.*
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.just.agentweb.AgentWeb
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.orhanobut.logger.Logger
import com.visionvera.library.arouter.ARouterConstant
import com.visionvera.library.arouter.service.IAccountService
import com.visionvera.library.base.BaseMVPActivity
import com.visionvera.library.base.Constant
import com.visionvera.library.base.bean.BaseResponseBean3
import com.visionvera.library.base.bean.PageBean
import com.visionvera.library.base.mvp.view.IBaseView
import com.visionvera.psychologist.c.R
import com.visionvera.psychologist.c.databinding.ActivityArticalDetailBinding
import com.visionvera.psychologist.c.module.knowledge_library.adapter.ArticleRvAdapter
import com.visionvera.psychologist.c.module.knowledge_library.bean.*
import com.visionvera.psychologist.c.module.knowledge_library.contract.IContract
import com.visionvera.psychologist.c.module.knowledge_library.event.ArticleStatusEvent
import com.visionvera.psychologist.c.module.knowledge_library.pop.ZhihuCommentPopup
import com.visionvera.psychologist.c.module.knowledge_library.presenter.ArticalDetailActivityPresenter
import java.io.Serializable
import java.util.*

/**
 * @author: 刘传政
 * @date: 2020/6/29 14:19
 * QQ:1052374416
 * 作用:文章详情
 * 注意事项:
 */
class ArticleDetailActivity : BaseMVPActivity<IContract.ArticalDetailActivity.View, ArticalDetailActivityPresenter>() {
    @JvmField
    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    var accountService: IAccountService? = null

    var mArticalDetailResponseBean: ArticleBean? = null
    var mAgentWeb: AgentWeb? = null
    val articleList: MutableList<ArticleBean> = mutableListOf()

    //默认创建一个，保证取值不null。所以里边的默认值很重要
    var intentBean = IntentBean()
    var articleRvAdapter: ArticleRvAdapter? = null
    var dx = 0
    var dy = 0
    var currentItemPosition = 0
    var newState = 0
    var currentCommentPopPosition = -1
    lateinit var viewBinding: ActivityArticalDetailBinding

    /**
     * 是否是点击下一篇.关系到滑动监听的逻辑
     */
    var isClickToNext = false

    var evaluatePop: BasePopupView? = null
    var zhihuCommentPopup: ZhihuCommentPopup? = null

    companion object {
        fun startActivity(context: Context, intentBean: IntentBean) {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(Constant.IntentKey.IntentBean, intentBean)
            context.startActivity(intent)
        }

        /*
        * comeType
        * 1:Home_SkinFragment
        * 2:MedicalCircleActivity
        * 3:DoctorDetailActivity(患者端医生详情)
        * 4:MedicalCircleFragment(患者端医疗圈)
        * */
        data class IntentBean(/*文章id*/var id: Int = -1, var comeType: String? = null) : Serializable {
        }
    }

    override fun setContentView(): Boolean {
        viewBinding = ActivityArticalDetailBinding.inflate(layoutInflater)
        val view: View = viewBinding.getRoot()
        setContentView(view)
        return true
    }

    override fun getLayoutId(): Int {
        return 0
    }

    fun getMyAccountService(): IAccountService? {
        return accountService
    }

    /**
     * 这里不过多区分initview与initdata等。因为他们的顺序不是固定的
     * 避免过度设计
     */
    override fun doYourself() {
        parseIntent()
        ARouter.getInstance().inject(this)
        initView()
        initData()
        initListener()

    }

    override fun onResume() {
        super.onResume()
        refreshAnswerButton()
    }

    private fun initData() {
        showLoadingDialog()
        //查询自己的详情
        net_getArticalDetail(false, intentBean.id)
    }

    private fun initListener() {
        viewBinding.ivBack.setOnClickListener {
            finish()
        }

        viewBinding.ibNext.setOnClickListener {
            var toPosition = currentItemPosition + 1

            if (toPosition >= articleList.size) {
                ToastUtils.showShort("已经是最后一篇文章了")
                return@setOnClickListener
            }
            Logger.i("toPosition${toPosition}currentItemPosition${currentItemPosition}")
            isClickToNext = true
            smoothMoveToPosition(viewBinding.recyclerView, toPosition)

        }
        viewBinding.llToEvaluate.setOnClickListener {
            showCommentListPop(currentItemPosition, articleList)
        }
        viewBinding.rlEvaluateList.setOnClickListener {
            showCommentListPop(currentItemPosition, articleList)
        }
        viewBinding.rlCollect.setOnClickListener {
            //收藏
            articleList.get(currentItemPosition)?.let {
                if (it.collectStatus == 0) {
                    //当前是未收藏状态
                    net_collectOrNot(it.id!!, true)
                } else {
                    //当前是已收藏状态
                    net_collectOrNot(it.id!!, false)
                }
            }
        }
        viewBinding.rlLike.setOnClickListener {
            //点赞
            articleList.get(currentItemPosition)?.let {
                if (it.thumbsUpStatus == 0) {
                    //当前是未点赞状态
                    net_articleLikeOrNot(it.id!!, true)
                } else {
                    //当前是已点赞状态
                    net_articleLikeOrNot(it.id!!, false)
                }
            }
        }
        viewBinding.llAnswer.setOnClickListener {
            mArticalDetailResponseBean?.let {
                var writeIntentBean = WriteArticleActivity.Companion.IntentBean()
                writeIntentBean.id = intentBean.id
                writeIntentBean.title = it.title ?: ""
                writeIntentBean.comeType = 1
                WriteArticleActivity.startActivity(this@ArticleDetailActivity, writeIntentBean)
            }

        }
    }

    //控制回答按钮是否显示
    private fun refreshAnswerButton() {
        accountService?.let {
            //1医生 2本级用户 3基层工作人员/网格员 4管理员 5c端用户/来访者 6心理咨询师 7护士
            var userTypeList = it.getAccountInfo.userTypeList
            if (userTypeList.contains(1) || userTypeList.contains(4) || userTypeList.contains(6)) {
                //可以发布文章
                viewBinding.llAnswer.visibility = View.VISIBLE
            } else {
                //不可以发布文章
                viewBinding.llAnswer.visibility = View.GONE
            }

        }
    }


    private fun net_collectOrNot(articalId: Int, collect: Boolean) {
        var requestBean = CollectOrNotRequestBean()
        requestBean.articalId = articalId
        mPresenter?.collectOrNot(requestBean, articalId, collect, this)
    }

    private fun net_articleLikeOrNot(articalId: Int, like: Boolean) {
        var requestBean = ArticleLikeOrNotRequestBean()
        requestBean.articalId = articalId
        mPresenter?.articleLikeOrNot(requestBean, articalId, like, this)
    }

    fun net_commentLikeOrNot(commentId: Int, like: Boolean) {
        var requestBean = CommentLikeOrNotRequestBean()
        requestBean.articalId = commentId
        requestBean.articalCommentId = commentId
        mPresenter?.commentLikeOrNot(requestBean, commentId, like, this)
    }

    /**
     * 刷新数字相关
     */
    fun net_refreshCounts() {
        net_getArticalDetail(true, articleList.get(currentItemPosition).id!!)
    }

    private fun showCommentListPop(position: Int, articleList: MutableList<ArticleBean>) {
        if (mArticalDetailResponseBean == null) {
            ToastUtils.showShort("网络异常,请刷新文章再试")
            return
        }
        if (articleList == null) {
            return
        }
        val articleBean = articleList.get(position)
        if (currentCommentPopPosition == position) {
            //当前显示的pop是要显示pop.直接把原来的pop弹出来
            if (evaluatePop == null) {
                if (zhihuCommentPopup == null) {
                    zhihuCommentPopup = ZhihuCommentPopup(this@ArticleDetailActivity, articleBean.id!!, "" + articleBean?.authorName, articleBean?.author!!)
                }

                evaluatePop = XPopup.Builder(this@ArticleDetailActivity)
                        .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                        .enableDrag(true)
                        .isDestroyOnDismiss(false) //对于只使用一次的弹窗，推荐设置这个
                        //                        .isThreeDrag(true) //是否开启三阶拖拽，如果设置enableDrag(false)则无效
                        .asCustom(zhihuCommentPopup /*.enableDrag(false)*/)
            }
        } else {
            //全新创建pop
            zhihuCommentPopup = ZhihuCommentPopup(this@ArticleDetailActivity, articleBean.id!!, "" + articleBean?.authorName, articleBean?.author!!)
            evaluatePop = XPopup.Builder(this@ArticleDetailActivity)
                    .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                    .enableDrag(true)
                    .isDestroyOnDismiss(false) //对于只使用一次的弹窗，推荐设置这个
                    //                        .isThreeDrag(true) //是否开启三阶拖拽，如果设置enableDrag(false)则无效
                    .asCustom(zhihuCommentPopup /*.enableDrag(false)*/)
        }

        evaluatePop?.show()
        currentCommentPopPosition = position
    }

    fun net_addComment(articleId: Int, commentId: Int, position: Int, content: String, atUserId: Int, atName: String) {
        var requestBean = AddCommentRequestBean()
        requestBean.articalId = articleId
        requestBean.content = content
        requestBean.parentId = if (commentId == -1) 0 else commentId
        requestBean.parentUserId = atUserId
        requestBean.parentName = atName
        mPresenter?.addComment(requestBean, commentId, position, this)
    }

    private fun net_getArticalDetail(isOnlyNeedCounts: Boolean, id: Int) {
        val requestBean = ArticleListRequestBean()
        requestBean.pageSize = 1
        requestBean.pageIndex = 1
        requestBean.id = id
        mPresenter!!.getArticleDetail(requestBean, isOnlyNeedCounts, this)
    }

    fun net_getArticalAnswers(parentId: Int) {
        val requestBean = ArticleListRequestBean()
        requestBean.pageSize = Int.MAX_VALUE
        requestBean.pageIndex = 1

        requestBean.parentId = parentId
        mPresenter!!.getArticalAnswers(requestBean, this)
    }

    fun net_getCommentList(isRefresh: Boolean, itemId: Int, pageBean: PageBean) {
        val requestBean = CommentListRequestBean()
        requestBean.pageSize = pageBean.pageSize
        requestBean.articalId = itemId
        if (isRefresh) {
            requestBean.pageIndex = pageBean.serverFirstPage
        } else {
            requestBean.pageIndex = pageBean.currentPage + 1
        }
        mPresenter!!.getCommentList(isRefresh, requestBean, this)
    }

    /**
     * 创建view，不要用实现的方式，要new一个，方便直接查看
     * 创建presenter
     * 时机是展示ui之前。因为这里只涉及到创建一些mvp类。不会影响功能。
     */
    override fun initMVP() {
        mView = object : IContract.ArticalDetailActivity.View {
            override fun onGetArticleDetail(responseBean: BaseResponseBean3<ArticleListResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?, isOnlyNeedCounts: Boolean) {
                hideDialog()
                when (resultType) {
                    IBaseView.ResultType.NET_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATANO -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATAYES -> {
                        if (responseBean?.result != null) {
                            if (responseBean.result.dataList != null && responseBean?.result?.dataList?.size!! > 0) {
                                if (isOnlyNeedCounts) {
                                    //只是为了刷新评论数量
                                    articleList.get(currentItemPosition).commentNumber = responseBean?.result?.dataList!!.get(0).commentNumber
                                } else {
                                    articleList.clear()
                                    mArticalDetailResponseBean = responseBean.result.dataList!!.get(0)
                                    articleList.add(0, responseBean.result.dataList!!.get(0)!!)
                                    articleRvAdapter?.notifyDataSetChanged()
                                    net_getArticalAnswers(intentBean.id)
                                }
                                refreshCommentUi(articleList, currentItemPosition, activity)
                            }


                        }
                    }
                }
            }

            override fun onGetArticalAnswers(responseBean: BaseResponseBean3<ArticleListResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?) {
                hideDialog()
                when (resultType) {
                    IBaseView.ResultType.NET_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATANO -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATAYES -> {
                        if (responseBean?.result != null) {
                            if (responseBean.result.dataList != null && responseBean?.result?.dataList?.size!! > 0) {
                                articleList.addAll(responseBean.result.dataList!!)
                                articleRvAdapter?.notifyDataSetChanged()

                            }
                        }
                    }
                }
            }

            override fun onCollectOrNot(responseBean: BaseResponseBean3<Any>?, articleId: Int, collect: Boolean, resultType: IBaseView.ResultType, errorMsg: String?) {
                when (resultType) {
                    IBaseView.ResultType.NET_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATANO -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATAYES -> {
                        if (collect) {
                            //收藏
                            if (articleList.get(currentItemPosition).id == articleId && articleList.get(currentItemPosition)?.collectStatus == 0) {
                                //确认是我之前收藏的那条
                                articleList.get(currentItemPosition)?.let {
                                    it.collectStatus = 1
                                    it.collectNumber = it.collectNumber!! + 1
                                    viewBinding.ivCollect.background = activity.getDrawable(R.drawable.ic_article_collect_little)
                                    viewBinding.tvCollectCount.text = "收藏" + it.collectNumber.toString()
                                    postStatus(articleList.get(currentItemPosition).id!!, 1, it.collectNumber!!)
                                }


                            }
                        } else {
                            //取消收藏
                            if (articleList.get(currentItemPosition).id == articleId && articleList.get(currentItemPosition)?.collectStatus == 1) {
                                //确认是我之前收藏的那条
                                articleList.get(currentItemPosition)?.let {
                                    it.collectStatus = 0
                                    it.collectNumber = it.collectNumber!! - 1
                                    viewBinding.ivCollect.background = activity.getDrawable(R.drawable.ic_article_uncollect_little)
                                    viewBinding.tvCollectCount.text = "收藏" + it.collectNumber.toString()
                                    postStatus(articleList.get(currentItemPosition).id!!, 2, it.collectNumber!!)
                                }

                            }
                        }

                    }
                }
            }

            override fun onArticleLikeOrNot(responseBean: BaseResponseBean3<Any>?, articleId: Int, like: Boolean, resultType: IBaseView.ResultType, errorMsg: String?) {
                when (resultType) {
                    IBaseView.ResultType.NET_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATANO -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATAYES -> {
                        if (like) {
                            //点赞
                            if (articleList.get(currentItemPosition).id == articleId && articleList.get(currentItemPosition)?.thumbsUpStatus == 0) {
                                //确认是我之前点赞的那条
                                articleList.get(currentItemPosition)?.let {
                                    it.thumbsUpStatus = 1
                                    it.thumbsUpNumber = it.thumbsUpNumber!! + 1
                                    viewBinding.ivLike.background = activity.getDrawable(R.drawable.ic_like_little)
                                    viewBinding.tvLikeCount.text = "点赞" + it.thumbsUpNumber.toString()
                                    postStatus(articleList.get(currentItemPosition).id!!, 3, it.thumbsUpNumber!!)
                                }

                            }
                        } else {
                            //取消点赞
                            if (articleList.get(currentItemPosition).id == articleId && articleList.get(currentItemPosition)?.thumbsUpStatus == 1) {
                                //确认是我之前点赞的那条
                                articleList.get(currentItemPosition)?.let {
                                    it.thumbsUpStatus = 0
                                    it.thumbsUpNumber = it.thumbsUpNumber!! - 1
                                    viewBinding.ivLike.background = activity.getDrawable(R.drawable.ic_unlike_little)
                                    viewBinding.tvLikeCount.text = "点赞" + it.thumbsUpNumber.toString()
                                    postStatus(articleList.get(currentItemPosition).id!!, 4, it.thumbsUpNumber!!)
                                }

                            }
                        }

                    }
                }
            }

            override fun onCommentLikeOrNot(responseBean: BaseResponseBean3<Any>?, commentId: Int, like: Boolean, resultType: IBaseView.ResultType, errorMsg: String?) {
                zhihuCommentPopup?.let {
                    it.onCommentLikeOrNot(responseBean, resultType, errorMsg, commentId, like)
                }
            }

            override fun onAddComment(responseBean: BaseResponseBean3<AddCommentResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?, id: Int, position: Int) {
                zhihuCommentPopup?.let {
                    it.onAddComment(responseBean, resultType, errorMsg, id, position)
                }
            }

            override fun onGetCommentList(responseBean: BaseResponseBean3<CommentListResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?, isRefresh: Boolean) {
                zhihuCommentPopup?.let {
                    it.onGetcommentList(responseBean, resultType, errorMsg, isRefresh)
                }
            }


            override fun onComplete() {}
        }
        mPresenter = ArticalDetailActivityPresenter(activity, mView)
    }

    private fun initView() {
        ImmersionBar.with(this@ArticleDetailActivity)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init()

        val pagerLayoutManager = LinearLayoutManager(this)
        viewBinding.recyclerView.layoutManager = pagerLayoutManager

        articleRvAdapter = ArticleRvAdapter(articleList)
        viewBinding.recyclerView.adapter = articleRvAdapter
        articleRvAdapter?.addChildClickViewIds(R.id.rlAddFocus)
        articleRvAdapter?.setOnItemChildClickListener { adapter, view, position ->

        }
        //检测recylerview的滚动事件
        viewBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                /*
                我这里通过的是停止滚动后屏幕上可见view。如果滚动过程中的可见view也要统计，你可以根据newState去做区分
                SCROLL_STATE_IDLE:停止滚动
                SCROLL_STATE_DRAGGING: 用户慢慢拖动
                SCROLL_STATE_SETTLING：惯性滚动
                */
                this@ArticleDetailActivity.newState = newState
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //这里我们用一个数组来记录起始位置
                    var range: IntArray = IntArray(2)
                    val manager: RecyclerView.LayoutManager = recyclerView.getLayoutManager()!!
                    if (manager is LinearLayoutManager) {
                        range = findRangeLinear(manager)
                    }
                    var firstVisiblePosition = range[0]
                    var lastVisiblePosition = range[1]
                    if (firstVisiblePosition != lastVisiblePosition) {

                        if (dy > 0) {
                            //手指向上拖动,也就是列表下边的内容向上走
                            var lastItemView = recyclerView.layoutManager!!.findViewByPosition(lastVisiblePosition)!!
                            val lastItemViewTop = lastItemView.top
                            if (lastItemViewTop < (recyclerView.height / 5 * 4)) {
                                smoothMoveToPosition(recyclerView, lastVisiblePosition)
                                Logger.i("smoothMoveToPosition:${currentItemPosition}")
                            } else {
                                var bottomDistance = recyclerView.height - lastItemViewTop
                                recyclerView.scrollBy(0, -bottomDistance)
                            }
                        } else {
                            //手指向下拖动
                            var firstItemView = recyclerView.layoutManager!!.findViewByPosition(firstVisiblePosition)!!
                            val firstItemViewBottom = firstItemView.bottom
                            if (firstItemViewBottom > (recyclerView.height / 5 * 1)) {
                                smoothMoveToPosition(recyclerView, firstVisiblePosition)
                                Logger.i("smoothMoveToPosition:${currentItemPosition}")
                            } else {
                                smoothMoveToPosition(recyclerView, lastVisiblePosition)
                                Logger.i("smoothMoveToPosition:${currentItemPosition}")
                            }
                        }
                    }
                    isClickToNext = false

                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //这里我们用一个数组来记录起始位置
                    /* var range: IntArray = IntArray(2)
                     val manager: RecyclerView.LayoutManager = recyclerView.getLayoutManager()!!
                     if (manager is LinearLayoutManager) {
                         range = findRangeLinear(manager)
                     }
                     var firstVisiblePosition = range[0]
                     var lastVisiblePosition = range[1]
                     if (firstVisiblePosition != lastVisiblePosition) {

                         if (dy > 0) {
                             //手指向上拖动,也就是列表下边的内容向上走
                             var firstItemView = recyclerView.layoutManager!!.findViewByPosition(firstVisiblePosition)!!
                             val firstItemViewBottom = firstItemView.bottom
                             if (firstItemViewBottom == (recyclerView.height) && firstVisiblePosition == currentItemPosition) {
                                 LogUtil.i("强制停止RecyclerView滑动方法")
                                 forceStopRecyclerViewScroll(recyclerView)
                             }
                         } else {
                             //手指向下拖动
                             var lastItemView = recyclerView.layoutManager!!.findViewByPosition(lastVisiblePosition)!!
                             val lastItemViewTop = lastItemView.top
                             if (lastItemViewTop == 0 && lastVisiblePosition == currentItemPosition) {
                                 LogUtil.i("强制停止RecyclerView滑动方法")
                                 forceStopRecyclerViewScroll(recyclerView)
                             }
                         }
                     }*/


                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                this@ArticleDetailActivity.dx = dx
                this@ArticleDetailActivity.dy = dy
                //这里我们用一个数组来记录起始位置
                var range: IntArray = IntArray(2)
                val manager: RecyclerView.LayoutManager = recyclerView.getLayoutManager()!!
                if (manager is LinearLayoutManager) {
                    range = findRangeLinear(manager)
                }
                var firstVisiblePosition = range[0]
                var lastVisiblePosition = range[1]
                if (firstVisiblePosition != lastVisiblePosition) {
                    if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                        //惯性滑动的时候,适当强制停止惯性,防止一次滑很多页
                        if (!isClickToNext) {
                            Logger.i("强制停止RecyclerView滑动方法")
                            forceStopRecyclerViewScroll(recyclerView)
                        }

                    } else {
                        if (dy > 0) {
                            //手指向上拖动,也就是列表下边的内容向上走
                            var lastItemView = recyclerView.layoutManager!!.findViewByPosition(lastVisiblePosition)!!
                            val lastItemViewTop = lastItemView.top
                            if (lastItemViewTop < (recyclerView.height / 5 * 4)) {
                                if (lastVisiblePosition != currentItemPosition) {
//                                forceStopRecyclerViewScroll(recyclerView)
                                    smoothMoveToPosition(recyclerView, lastVisiblePosition)
                                    Logger.i("smoothMoveToPosition:${currentItemPosition}")
                                }
                            }
                        } else {
                            //手指向下拖动
                            var firstItemView = recyclerView.layoutManager!!.findViewByPosition(firstVisiblePosition)!!
                            val firstItemViewBottom = firstItemView.bottom
                            if (firstItemViewBottom > (recyclerView.height / 5 * 1)) {
                                if (firstVisiblePosition != currentItemPosition) {
//                                forceStopRecyclerViewScroll(recyclerView)
                                    smoothMoveToPosition(recyclerView, firstVisiblePosition)
                                    Logger.i("smoothMoveToPosition:${currentItemPosition}")
                                }
                            }
                        }
                    }


                } else {

                }
            }
        })
    }

    /**
     * 发出状态更新通知.谁爱用谁用
     */
    private fun postStatus(id: Int, type: Int, count: Int) {
        //发送状态更新事件总线.因为有的界面需要刷新列表
        val busEvent = ArticleStatusEvent()
        busEvent.id = id
        busEvent.type = type
        busEvent.currentCount = count
        LiveEventBus
                .get(ArticleStatusEvent::class.java)
                .post(busEvent)
    }

    private fun findRangeLinear(manager: LinearLayoutManager): IntArray {
        val range = IntArray(2)
        range[0] = manager.findFirstVisibleItemPosition()
        range[1] = manager.findLastVisibleItemPosition()
        return range
    }

    /**
     * 滑动到指定位置
     */
    private fun smoothMoveToPosition(mRecyclerView: RecyclerView, position: Int) {
        // 第一个可见位置
        val firstItem: Int = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0))
        // 最后一个可见位置
        val lastItem: Int = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1))
        if (position < firstItem) {
            // 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.smoothScrollToPosition(position)
        } else if (position <= lastItem) {
            // 跳转位置在第一个可见项之后，最后一个可见项之前
            // smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            val movePosition = position - firstItem
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                val top: Int = mRecyclerView.getChildAt(movePosition).getTop()
                mRecyclerView.smoothScrollBy(0, top)
            }
        } else {
            // 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position)
        }
        currentItemPosition = position
        refreshCommentUi(articleList, position, this)
    }

    //强制停止RecyclerView滑动方法
    fun forceStopRecyclerViewScroll(mRecyclerView: RecyclerView) {
        mRecyclerView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0f, 0f, 0))
    }

    private fun refreshCommentUi(articleList: MutableList<ArticleBean>, i: Int, activity: Activity) {
        if (articleList == null || articleList.size <= i) {
            return
        }
        articleList.get(i)?.let {
            viewBinding.tvEvaluateCount.text = "评论" + it.commentNumber
            if (it.collectStatus == 0) {
                viewBinding.ivCollect.background = activity.getDrawable(R.drawable.ic_article_uncollect_little)
            } else {
                viewBinding.ivCollect.background = activity.getDrawable(R.drawable.ic_article_collect_little)
            }
            viewBinding.tvCollectCount.text = "收藏" + it.collectNumber

            if (it.thumbsUpStatus == 0) {
                viewBinding.ivLike.background = activity.getDrawable(R.drawable.ic_unlike_little)
            } else {
                viewBinding.ivLike.background = activity.getDrawable(R.drawable.ic_like_little)
            }
            viewBinding.tvLikeCount.text = "点赞" + it.thumbsUpNumber.toString()
        }

    }

    private fun parseIntent() {
        //获取参数
        val bean = intent.getSerializableExtra(Constant.IntentKey.IntentBean) as IntentBean?
        if (bean != null) {
            intentBean = bean

        }
        Logger.i(intentBean.toString())
    }
}


















