package com.visionvera.psychologist.c.module.knowledge_library.activity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import com.gyf.immersionbar.ImmersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lei.lib.java.rxcache.RxCache
import com.lei.lib.java.rxcache.entity.CacheResponse
import com.lei.lib.java.rxcache.util.RxUtil
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.visionvera.library.base.BaseMVPActivity
import com.visionvera.library.base.Constant
import com.visionvera.library.base.mvp.presenter.BasePresenter
import com.visionvera.library.base.mvp.view.IBaseRetrofitView
import com.visionvera.library.util.OneClickUtils
import com.visionvera.psychologist.c.R
import com.visionvera.psychologist.c.databinding.ActivityDraftListBinding
import com.visionvera.psychologist.c.module.knowledge_library.adapter.DraftListAdapter
import com.visionvera.psychologist.c.module.knowledge_library.bean.DraftBean
import com.visionvera.psychologist.c.module.knowledge_library.event.SaveDraftEvent
import io.reactivex.functions.Consumer
import java.io.Serializable

/**
 * @author: 刘传政
 * @date: 2020/6/29 14:19
 * QQ:1052374416
 * 作用:草稿箱
 * 注意事项:
 */
class DraftListActivity : BaseMVPActivity<IBaseRetrofitView, BasePresenter<IBaseRetrofitView>>() {


    //默认创建一个，保证取值不null。所以里边的默认值很重要
    var intentBean = IntentBean("")
    var draftList: ArrayList<DraftBean> = ArrayList()
    var mAdapter: DraftListAdapter? = null
    lateinit var viewBinding: ActivityDraftListBinding


    companion object {
        fun startActivity(context: Context, intentBean: IntentBean?) {
            val intent = Intent(context, DraftListActivity::class.java)
            intent.putExtra(Constant.IntentKey.IntentBean, intentBean)
            context.startActivity(intent)
        }

        data class IntentBean(var aaa: String) : Serializable {
        }
    }


    /**
     * 创建view，不要用实现的方式，要new一个，方便直接查看
     * 创建presenter
     * 时机是展示ui之前。因为这里只涉及到创建一些mvp类。不会影响功能。
     */
    override fun initMVP() {

    }

    override fun setContentView(): Boolean {
        viewBinding = ActivityDraftListBinding.inflate(layoutInflater)
        val view: View = viewBinding.getRoot()
        setContentView(view)
        return true
    }

    override fun getLayoutId(): Int {
        return 0
    }


    /**
     * 这里不过多区分initview与initdata等。因为他们的顺序不是固定的
     * 避免过度设计
     */
    override fun doYourself() {
        parseIntent()
        initView()
        initListener()
        initBus()
        initData()
    }

    private fun initView() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init()
        viewBinding.toolbar.tvTitle.setText("草稿箱")

        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = DraftListAdapter(draftList)
        //添加尾布局
        val footView = LayoutInflater.from(this).inflate(R.layout.foot_view_draft, null)
        mAdapter?.addFooterView(footView)
        mAdapter?.footerLayout?.setVisibility(View.VISIBLE)
        viewBinding.recyclerView.adapter = mAdapter
        mAdapter?.setOnItemClickListener { adapter, view, position ->
            draftList?.get(position)?.let {
                var intentBean = WriteArticleActivity.Companion.IntentBean()
                intentBean.title = it.title ?: ""
                intentBean.comeType = 2
                intentBean.draftId = it.id
                intentBean.content = it.content
                WriteArticleActivity.startActivity(this@DraftListActivity, intentBean)
            }

        }
        viewBinding.smartRefreshLayout.setEnableLoadMore(false)

    }

    private fun initBus() {
        //监听事件总线的消息
        LiveEventBus
                .get(SaveDraftEvent::class.java)
                .observe(this, Observer { event: SaveDraftEvent ->
                    viewBinding.smartRefreshLayout.autoRefresh()
                })

    }


    private fun initData() {
        viewBinding.multipleStatusView.showLoading()
        getSofts()
    }

    /**
     * 获取本地保存的草稿
     */
    private fun getSofts() {
        val cacheKey = Constant.Cache.draftsKey
        //获取缓存
        val type = object : TypeToken<java.util.ArrayList<DraftBean>>() {}.type
        RxCache.getInstance()
                .get<java.util.ArrayList<DraftBean>>(cacheKey, false, type) //key:缓存的key update:表示从缓存获取数据强行返回NULL
                .compose(RxUtil.io_main())
                .subscribe(Consumer<CacheResponse<java.util.ArrayList<DraftBean>>> { listCacheResponse ->
                    listCacheResponse.data?.let {
                        //非空怎么撸
                        Logger.i("获取到了缓存  ${cacheKey}:" + listCacheResponse.data.toString())
                        //时间降序排序
                        it.sortByDescending { softBean -> softBean.time }
                        onGetSofts(it)

                    } ?: let {
                        //为空又怎么撸
                        Logger.i("没获取到缓存:" + cacheKey)
                        onGetSofts(null)
                    }

                }, Consumer { throwable ->
                    throwable.printStackTrace()
                    Logger.i("没获取到缓存 ${cacheKey}:$throwable")
                    onGetSofts(null)
                })
    }

    private fun onGetSofts(list: ArrayList<DraftBean>?) {
        viewBinding.smartRefreshLayout.finishRefresh()
        if (list == null) {
            //没有获取到
            viewBinding.multipleStatusView.showEmpty()
            draftList.clear()
            mAdapter?.notifyDataSetChanged()
        } else {
            //获取到了
            viewBinding.multipleStatusView.showContent()
            draftList.clear()
            draftList.addAll(list)
            mAdapter?.notifyDataSetChanged()

        }
    }


    private fun initListener() {
        viewBinding.toolbar.ivBack.setOnClickListener {
            finish()
        }
        viewBinding.multipleStatusView.setOnRetryClickListener {
            if (OneClickUtils.isFastClick()) {
                return@setOnRetryClickListener
            }
            viewBinding.multipleStatusView.showLoading()
        }
        viewBinding.smartRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                getSofts()
            }

        })


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