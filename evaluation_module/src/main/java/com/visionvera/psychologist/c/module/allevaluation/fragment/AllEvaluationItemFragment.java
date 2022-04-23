package com.visionvera.psychologist.c.module.allevaluation.fragment;

import android.view.View;
import android.widget.ImageView;

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
import com.visionvera.library.base.BaseMVPLoadFragment;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentGaugeActivity;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListResponseBean;
import com.visionvera.psychologist.c.module.allevaluation.contract.IContract;
import com.visionvera.psychologist.c.module.allevaluation.presenter.AllEvaluationItemFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 刘传政
 * @date 2019-10-30 17:26
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class AllEvaluationItemFragment extends BaseMVPLoadFragment<IContract.AllEvaluationItemFragment.View, AllEvaluationItemFragmentPresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;
    MyAdapter mAdapter;
    ArrayList<EvaluationChatListResponseBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private int page = 0;//当前页.  后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    int pageSize = 20;
    private int id;//传递过来的类型id

    public int mSort=2;
    public int mSortedType;

    public AllEvaluationItemFragment() {
    }

    public AllEvaluationItemFragment(int id) {
        this.id = id;
    }

    @Override
    protected void lazyLoadData() {
        showLoading();
        requestChartList(true,mSortedType,mSort);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_item_allevaluation;
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
                requestChartList(true,mSortedType,mSort);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requestChartList(false,mSortedType,mSort);
            }
        });
        mAdapter = new MyAdapter(mDataList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity
                        .GaugeIntentBean(mDataList.get(position).getId(), 2, 0, 0);

                SelfAssessmentGaugeActivity.startActivity(getActivity(), gaugeIntentBean);
            }
        });
    }

    @Override
    protected void initMVP() {
        mView = new IContract.AllEvaluationItemFragment.View() {
            @Override
            public void onGetEvaluationChatList(boolean isRefresh, EvaluationChatListResponseBean responseBean, ResultType resultType, String errorMsg) {
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.finishLoadMore();
                }
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        if (page == 0 && mDataList.size() == 0) {
                            showError(errorMsg);
                        }
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        if (isRefresh) {
                            mDataList.clear();
                            page = 0;
                            showEmpty();
                        } else {
                            if (page == 0 && mDataList.size() == 0) {
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
                            page = 0;
                        }
                        mAdapter.notifyDataSetChanged();
                        if (page == 0 && mDataList.size() == 0) {
                            showEmpty();
                        } else {
                            showNormal();
                        }
                        break;
                    case SERVER_NORMAL_DATAYES:
                        if (isRefresh) {
                            mDataList.clear();
                            page = 0;
                        }
                        if (responseBean.getResult().getDataList().size() != 0) {
                            page++;
                            mDataList.addAll(responseBean.getResult().getDataList());
                        }
                        mAdapter.notifyDataSetChanged();
                        if (page == 0 && mDataList.size() == 0) {
                            showEmpty();
                        } else {
                            showNormal();
                        }
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new AllEvaluationItemFragmentPresenter(getActivity(), mView);
    }

    @Override
    protected void onReload() {
        requestChartList(true,mSortedType,mSort);
    }

    public void requestChartList(boolean isRefresh,int sortedType,int sort) {
        mSort=sort;
        mSortedType=sortedType;
        int requestPage = page;
        if (isRefresh) {
            //下拉刷新
            requestPage = 1;
        } else {
            //上拉加载更多
            requestPage = page + 1;
        }
        EvaluationChatListRequestBean requestBean
                = new EvaluationChatListRequestBean("",
                id + "", sortedType,
                sort, requestPage, pageSize);
        mPresenter.getEvaluationChatList(isRefresh, requestBean, this);
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
