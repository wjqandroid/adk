package com.visionvera.psychologist.c.module.search.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.visionvera.library.base.BaseLoadFragment;
import com.visionvera.library.base.bean.PageBean;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentGaugeActivity;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListResponseBean;
import com.visionvera.psychologist.c.module.search.activity.SearchEvaluationActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: 刘传政
 * @date: 2019-10-29 17:53
 * QQ:1052374416
 * 作用:搜索测试量表的结果部分
 * 注意事项:
 */
public class SearchResultFragment extends BaseLoadFragment {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.tv_search_match)
    TextView tv_search_match;
    MyAdapter mAdapter;
    ArrayList<EvaluationChatListResponseBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private PageBean mPageBean = new PageBean();
    private String searchName = "";

    public static SearchResultFragment newInstance() {
        SearchResultFragment fragment = new SearchResultFragment();
        return fragment;
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_search_result;
    }

    @Override
    protected void initYourself() {
        initView();
    }

    private void initView() {
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestChartList(true, searchName);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requestChartList(false, searchName);
            }
        });
        mAdapter = new MyAdapter(mDataList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity.GaugeIntentBean(mDataList.get(position).getId());
                SelfAssessmentGaugeActivity.startActivity(getActivity(), gaugeIntentBean);
            }
        });


    }

    public void showEvaluationChatList(boolean isRefresh, EvaluationChatListResponseBean responseBean, IBaseView.ResultType resultType, String errorMsg) {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadMore();
        }
        tv_search_match.setText("· 未找到相关匹配，请修改关键字");
        tv_search_match.setTextColor(Color.parseColor("#F83F3B"));

        switch (resultType) {
            case NET_ERROR:
                //网络异常等
                if (mPageBean.currentPage == 0 && mDataList.size() == 0) {
                    showError(errorMsg);
                }
                ToastUtils.showLong(getString(R.string.base_module_net_error));
                break;
            case SERVER_ERROR:
                if (isRefresh) {
                    mDataList.clear();
                    mPageBean.currentPage = 0;
                    showEmpty();
                } else {
                    if (mPageBean.currentPage == 0 && mDataList.size() == 0) {
                        showEmpty();
                    } else {
                        showNormal();
                    }
                }
                ToastUtils.showShort(errorMsg);
                break;
            case SERVER_NORMAL_DATANO:
                if (isRefresh) {
                    mDataList.clear();
                    mPageBean.currentPage = 0;
                }
                mAdapter.notifyDataSetChanged();
                if (mPageBean.currentPage == 0 && mDataList.size() == 0) {
                    showEmpty();
                } else {
                    showNormal();
                }
                break;
            case SERVER_NORMAL_DATAYES:
                if (isRefresh) {
                    mDataList.clear();
                    mPageBean.currentPage = 0;
                }
                if (responseBean.getResult().getDataList().size() != 0) {
                    mPageBean.currentPage++;
                    mDataList.addAll(responseBean.getResult().getDataList());
                }
                mAdapter.notifyDataSetChanged();
                if (mPageBean.currentPage == 0 && mDataList.size() == 0) {
                    showEmpty();
                } else {
                    showNormal();
                    tv_search_match.setText("为您找到以下跟\"" + searchName + "\"有关的内容");
                    tv_search_match.setTextColor(getResources().getColor(R.color.base_module_theme));
                }
                break;
        }
    }

    public void requestChartList(boolean isRefresh, String searchName) {
        this.searchName = searchName;
        int requestPage = mPageBean.currentPage;
        if (isRefresh) {
            //下拉刷新
            requestPage = mPageBean.serverFirstPage;
        } else {
            //上拉加载更多
            requestPage = mPageBean.currentPage + 1;
        }
        EvaluationChatListRequestBean requestBean
                = new EvaluationChatListRequestBean(searchName,
                0 + "", 2,
                2, requestPage, mPageBean.pageSize);
        ((SearchEvaluationActivity) getActivity()).mPresenter.getEvaluationChatList(isRefresh, requestBean, this);
    }

    @Override
    protected void onReload() {
        requestChartList(true, searchName);
    }

    class MyAdapter extends BaseQuickAdapter<EvaluationChatListResponseBean.ResultBean.DataListBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<EvaluationChatListResponseBean.ResultBean.DataListBean> data) {
            super(R.layout.evaluation_module_liangbiao_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, EvaluationChatListResponseBean.ResultBean.DataListBean item) {
            if (item == null) {
                return;
            }
            helper.setText(R.id.tv_title, item.getName());
            helper.setText(R.id.tv_description, item.getDescription());
            helper.setText(R.id.tv_heartBeans, item.getHeartBeans() + "个心豆");
            helper.setText(R.id.tv_usedNum, item.getUsedNum() + "人已测");
            ImageView iv_icon = helper.getView(R.id.iv_icon);
            Glide.with(getActivity()).load(item.getUrl())
                    .placeholder(R.drawable.evaluation_module_icon_empty)
                    .dontAnimate()
                    .into(iv_icon);
        }
    }

}
