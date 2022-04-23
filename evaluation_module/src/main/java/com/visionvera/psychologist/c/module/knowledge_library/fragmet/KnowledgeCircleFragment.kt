package com.visionvera.psychologist.c.module.knowledge_library.fragmet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.KeyboardUtils
import com.gyf.immersionbar.ImmersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.visionvera.library.arouter.ARouterConstant
import com.visionvera.library.arouter.service.IAccountService
import com.visionvera.library.base.BaseMVPFragment
import com.visionvera.library.base.bean.BaseResponseBean3
import com.visionvera.library.base.bean.PageBean
import com.visionvera.library.base.mvp.view.IBaseView
import com.visionvera.library.util.OneClickUtils
import com.visionvera.psychologist.c.R
import com.visionvera.psychologist.c.databinding.EvaluationModuleFragmentKnowledgeCircleBinding
import com.visionvera.psychologist.c.module.knowledge_library.activity.ArticleDetailActivity
import com.visionvera.psychologist.c.module.knowledge_library.activity.DraftListActivity
import com.visionvera.psychologist.c.module.knowledge_library.activity.WriteArticleActivity
import com.visionvera.psychologist.c.module.knowledge_library.adapter.KnowledgeCircleListAdapter
import com.visionvera.psychologist.c.module.knowledge_library.bean.*
import com.visionvera.psychologist.c.module.knowledge_library.contract.IContract
import com.visionvera.psychologist.c.module.knowledge_library.event.ArticleStatusEvent
import com.visionvera.psychologist.c.module.knowledge_library.event.PublishArticleEvent
import com.visionvera.psychologist.c.module.knowledge_library.pop.BottomCheckArticleTypePopup
import com.visionvera.psychologist.c.module.knowledge_library.presenter.KnowledgeCircleFragmentPresenter
import java.util.*

/**
 * @author: 刘传政
 * @date: 2020/5/18 13:50
 * QQ:1052374416
 * 作用:知识库
 * 注意事项:
 */
class KnowledgeCircleFragment : BaseMVPFragment<IContract.KnowledgeCircleFragment.View, KnowledgeCircleFragmentPresenter>() {
    @JvmField
    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    var accountService: IAccountService? = null
    lateinit var mAdapter: KnowledgeCircleListAdapter
    var mDataList: ArrayList<MultiArticleItemBean> = ArrayList()
    private val mPageBean = PageBean()
    lateinit var viewBinding: EvaluationModuleFragmentKnowledgeCircleBinding
    override fun lazyLoadData() {}
    override fun getLayoutResID(): Int {
        return 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = EvaluationModuleFragmentKnowledgeCircleBinding.inflate(inflater, container, false)
        val view: View = viewBinding.getRoot()
        return view
    }

    override fun initYourself() {
        ARouter.getInstance().inject(this)
        updateStatuBar()
        initView()
        initData()
        initListener()
        initBus()
    }

    override fun onResume() {
        super.onResume()
        //2020.0413 做限制,用户端是没有权限的直接进行隐藏
//        refreshAddArticleButton()
    }

    private fun initListener() {
        viewBinding.multipleStatusView.setOnRetryClickListener {
            if (OneClickUtils.isFastClick()) {
                return@setOnRetryClickListener
            }
            viewBinding.multipleStatusView.showLoading()
            net_getArticleList(true)
        }
        viewBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                net_getArticleList(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                net_getArticleList(true)
            }

        })
        viewBinding.ivAddArticle.setOnClickListener {

            showArticleTypePop()
        }
    }

    private fun initBus() {
        //监听事件总线的消息
        LiveEventBus
                .get(ArticleStatusEvent::class.java)
                .observe(this, Observer { event: ArticleStatusEvent ->
                    mDataList?.let {
                        it.forEachIndexed() { i, multiMedicalCircletItemBean ->
                            if (multiMedicalCircletItemBean.dataBean.id == event.id) {
                                //证明我有这篇文章
                                if (event.type == 1) {
                                    //收藏
                                    if (multiMedicalCircletItemBean.dataBean.collectStatus == 0) {
                                        multiMedicalCircletItemBean.dataBean.collectStatus = 1
                                        multiMedicalCircletItemBean.dataBean.collectNumber = event.currentCount
                                        mAdapter?.notifyItemChanged(i)
                                    }
                                } else if (event.type == 2) {
                                    //取消收藏
                                    if (multiMedicalCircletItemBean.dataBean.collectStatus == 1) {
                                        multiMedicalCircletItemBean.dataBean.collectStatus = 0
                                        multiMedicalCircletItemBean.dataBean.collectNumber = event.currentCount
                                        mAdapter?.notifyItemChanged(i)
                                    }
                                } else if (event.type == 3) {
                                    //点赞
                                    if (multiMedicalCircletItemBean.dataBean.thumbsUpStatus == 0) {
                                        multiMedicalCircletItemBean.dataBean.thumbsUpStatus = 1
                                        multiMedicalCircletItemBean.dataBean.thumbsUpNumber = event.currentCount
                                        mAdapter?.notifyItemChanged(i)
                                    }
                                } else if (event.type == 4) {
                                    //取消点赞
                                    if (multiMedicalCircletItemBean.dataBean.thumbsUpStatus == 1) {
                                        multiMedicalCircletItemBean.dataBean.thumbsUpStatus = 0
                                        multiMedicalCircletItemBean.dataBean.thumbsUpNumber = event.currentCount
                                        mAdapter?.notifyItemChanged(i)
                                    }
                                }
                            }
                        }
                    }
                })

        LiveEventBus
                .get(PublishArticleEvent::class.java)
                .observe(this, Observer { event: PublishArticleEvent ->
                    viewBinding.smartRefreshLayout.autoRefresh()
                })
    }

    private fun showArticleTypePop() {
        KeyboardUtils.hideSoftInput(requireActivity())
        var payTypePop = BottomCheckArticleTypePopup(requireActivity(), object : BottomCheckArticleTypePopup.ResultListener {

            override fun onCkecked(event: String) {
                when (event) {
                    BottomCheckArticleTypePopup.CLICK_WRITE -> {
                        //写文章
                        var intentBean = WriteArticleActivity.Companion.IntentBean()
                        WriteArticleActivity.startActivity(context!!, intentBean)
                    }

                    BottomCheckArticleTypePopup.CLICK_DRAFT -> {
                        //草稿箱
                        var intentBean = DraftListActivity.Companion.IntentBean("")
                        DraftListActivity.startActivity(activity!!, intentBean)
                    }

                    else -> {
                    }
                }
            }


        })

        var handlePopup: BasePopupView = XPopup.Builder(requireActivity())
                .moveUpToKeyboard(false)//如果不加这个，评论弹窗会移动到软键盘上面
                .enableDrag(true)
                .asCustom(payTypePop)
        handlePopup.show()


    }
    private fun initView() {
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = KnowledgeCircleListAdapter(mDataList)
        //添加尾布局
        val footView = LayoutInflater.from(activity).inflate(R.layout.foot_view, null)
        mAdapter.addFooterView(footView)
        mAdapter.footerLayout?.setVisibility(View.GONE)
        viewBinding.recyclerView.adapter = mAdapter
        mAdapter.setOnItemClickListener { adapter, view, position ->
            var intentBean = ArticleDetailActivity.Companion.IntentBean(mDataList.get(position).dataBean.id!!, "4")
            ArticleDetailActivity.startActivity(requireContext(), intentBean)
        }
        mAdapter.addChildClickViewIds(R.id.llLike, R.id.llCollect)
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.llLike) {
                //点赞
                if (mDataList.get(position).dataBean.thumbsUpStatus == 0) {
                    //当前是未点赞状态
                    net_articleLikeOrNot(mDataList.get(position).dataBean.id!!, true, position)

                } else {
                    //当前是已点赞状态
                    net_articleLikeOrNot(mDataList.get(position).dataBean.id!!, false, position)
                }
            } else if (view.id == R.id.llCollect) {
                //收藏
                if (mDataList.get(position).dataBean.collectStatus == 0) {
                    //当前是未收藏状态
                    net_collectOrNot(mDataList.get(position).dataBean.id!!, true, position)
                } else {
                    //当前是已收藏状态
                    net_collectOrNot(mDataList.get(position).dataBean.id!!, false, position)
                }
            }
        }

//        refreshAddArticleButton()

    }

    //控制发布文章按钮是否显示
    private fun refreshAddArticleButton() {

//        viewBinding.ivAddArticle.visibility = View.GONE
//        accountService?.let {
//            //1医生 2本级用户 3基层工作人员/网格员 4管理员 5c端用户/来访者 6心理咨询师 7护士
//            var userTypeList = it.getAccountInfo.userTypeList
//            if (userTypeList.contains(1) || userTypeList.contains(4) || userTypeList.contains(6)) {
//                //可以发布文章
//                viewBinding.ivAddArticle.visibility = View.VISIBLE
//            } else {
//                //不可以发布文章
//                viewBinding.ivAddArticle.visibility = View.GONE
//            }
//
//        }
    }

    private fun initData() {
        net_getArticleList(true)
    }

    private fun net_getArticleList(isRefresh: Boolean) {
        val requestBean = ArticleListRequestBean()
        requestBean.pageSize = mPageBean.pageSize
        if (isRefresh) {
            requestBean.pageIndex = mPageBean.serverFirstPage
        } else {
            requestBean.pageIndex = mPageBean.currentPage + 1
        }
        requestBean.parentId = 0
        mPresenter!!.getArticles(isRefresh, requestBean, this)
    }

    private fun net_collectOrNot(articalId: Int, collect: Boolean, position: Int) {
        var requestBean = CollectOrNotRequestBean()
        requestBean.articalId = articalId
        mPresenter?.collectOrNot(requestBean, articalId, collect, position, this)
    }

    private fun net_articleLikeOrNot(articalId: Int, like: Boolean, position: Int) {
        var requestBean = ArticleLikeOrNotRequestBean()
        requestBean.articalId = articalId
        mPresenter?.articleLikeOrNot(requestBean, articalId, like, position, this)
    }

    fun updateStatuBar() {
        if (activity != null) {
            ImmersionBar.with(this)
                    .statusBarColor(R.color.white)
                    .statusBarDarkFont(true)
                    .fitsSystemWindows(true)
                    .init()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): KnowledgeCircleFragment {
            return KnowledgeCircleFragment()
        }
    }

    /**
     * 创建view，不要用实现的方式，要new一个，方便直接查看
     * 创建presenter
     */
    override fun initMVP() {
        mView = object : IContract.KnowledgeCircleFragment.View {

            override fun onGetArticles(isRefresh: Boolean, responseBean: BaseResponseBean3<ArticleListResponseBean>?, resultType: IBaseView.ResultType, errorMsg: String?) {
                if (viewBinding.smartRefreshLayout != null) {
                    viewBinding.smartRefreshLayout.finishRefresh()
                    viewBinding.smartRefreshLayout.finishLoadMore()
                }
                when (resultType) {
                    IBaseView.ResultType.NET_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATANO -> {
                        if (isRefresh) {
                            mDataList.clear()
                            mPageBean.currentPage = 0
                        }
                        mAdapter.notifyDataSetChanged()
                        if (mPageBean.currentPage == 0 && mDataList.size == 0) {
                            viewBinding.multipleStatusView.showEmpty()
                        } else {
                            viewBinding.multipleStatusView.showContent()
                        }
                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATAYES -> {
                        if (isRefresh) {
                            mDataList.clear()
                            mPageBean.currentPage = 0
                        }

                        if (responseBean?.result != null) {
                            if (responseBean.result.dataList != null && responseBean?.result?.dataList?.size!! > 0) {
                                responseBean.result.dataList!!.forEachIndexed { i, dataBean ->
                                    var myItemBean = MultiArticleItemBean(KnowledgeCircleListAdapter.MedicalCircleType.TYPE1)
                                    //设置不同的状态
                                    myItemBean.dataBean = dataBean
                                    mDataList.add(myItemBean)
                                }
                                mPageBean.currentPage++
                            }
                        }
                        mAdapter.notifyDataSetChanged()
                        if (mPageBean.currentPage == 0 && mDataList.size == 0) {
                            viewBinding.multipleStatusView.showEmpty()
                        } else {
                            viewBinding.multipleStatusView.showContent()
                        }
                    }
                }
            }

            override fun onCollectOrNot(responseBean: BaseResponseBean3<Any>?, articleId: Int, collect: Boolean, position: Int, resultType: IBaseView.ResultType, errorMsg: String?) {
                when (resultType) {
                    IBaseView.ResultType.NET_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATANO, IBaseView.ResultType.SERVER_NORMAL_DATAYES -> {
                        if (mDataList.size > position && mDataList.get(position).dataBean.id == articleId) {
                            if (collect) {
                                mDataList.get(position).dataBean.collectStatus = 1
                                mDataList.get(position).dataBean.collectNumber = mDataList.get(position).dataBean.collectNumber!! + 1
                                mAdapter?.notifyItemChanged(position)
                            } else {
                                mDataList.get(position).dataBean.collectStatus = 0
                                mDataList.get(position).dataBean.collectNumber = mDataList.get(position).dataBean.collectNumber!! - 1
                                mAdapter?.notifyItemChanged(position)
                            }
                        }
                    }

                }
            }

            override fun onArticleLikeOrNot(responseBean: BaseResponseBean3<Any>?, articleId: Int, like: Boolean, position: Int, resultType: IBaseView.ResultType, errorMsg: String?) {
                when (resultType) {
                    IBaseView.ResultType.NET_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_ERROR -> {

                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATANO, IBaseView.ResultType.SERVER_NORMAL_DATAYES -> {
                        if (mDataList.size > position && mDataList.get(position).dataBean.id == articleId) {
                            if (like) {
                                mDataList.get(position).dataBean.thumbsUpStatus = 1
                                mDataList.get(position).dataBean.thumbsUpNumber = mDataList.get(position).dataBean.thumbsUpNumber!! + 1
                                mAdapter?.notifyItemChanged(position)
                            } else {
                                mDataList.get(position).dataBean.thumbsUpStatus = 0
                                mDataList.get(position).dataBean.thumbsUpNumber = mDataList.get(position).dataBean.thumbsUpNumber!! - 1
                                mAdapter?.notifyItemChanged(position)
                            }
                        }
                    }

                }

            }

            override fun onComplete() {}
        }
        mPresenter = KnowledgeCircleFragmentPresenter(activity, mView)
    }
}