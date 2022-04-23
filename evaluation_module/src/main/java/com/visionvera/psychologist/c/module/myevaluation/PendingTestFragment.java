package com.visionvera.psychologist.c.module.myevaluation;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.visionvera.library.base.BaseMVPLoadFragment;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.library.util.TimeFormatUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentGaugeActivity;
import com.visionvera.psychologist.c.module.myevaluation.adapter.PendingTestAdapter;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.module.myevaluation.contract.IContract;
import com.visionvera.psychologist.c.module.myevaluation.presenter.PendingTestPresenter;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @Desc 待测试
 *
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class PendingTestFragment extends BaseMVPLoadFragment<IContract.PendingTest.View, PendingTestPresenter> {
    @BindView(R2.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R2.id.rc_pending_test)
    RecyclerView mRecyclerView;

    private PendingTestAdapter mAdapter;
    private ArrayList<MyEvaluationBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private int page = 0;//当前页.  后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    private int pageSize = 20;

    public static PendingTestFragment newInstance() {
        return new PendingTestFragment();
    }

    @Override
    protected void lazyLoadData() {
        showLoading();
        requestPendingTest(true);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_pending_test;
    }

    @Override
    protected void initYourself() {
        initView();

    }

    private void initView() {
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestPendingTest(true);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requestPendingTest(false);
            }
        });

        mAdapter = new PendingTestAdapter(getActivity(), mDataList);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                MyEvaluationBean.ResultBean.DataListBean bean = mDataList.get(position);
                Long startTimestamp = TimeFormatUtils.getTimestamp(bean.getStartTime());
                long currentTimeMillis = System.currentTimeMillis();

                long duration = currentTimeMillis - startTimestamp;
                if (duration >= 0) {
                    SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity
                            .GaugeIntentBean(mDataList.get(position).getScaleId(), 1, mDataList.get(position).getPushRecordId(), mDataList.get(position).getAnswerId());


                    SelfAssessmentGaugeActivity.startActivity(getActivity(), gaugeIntentBean);
                } else {

                    ToastUtils.showShort("未到开始时间！开始时间为:"+TimeFormatUtils.parseLongToDate(startTimestamp));//bean.getStartTime()
                }

            }
        });
    }

    @Override
    protected void initMVP() {
        mView = new IContract.PendingTest.View() {
            @Override
            public void onGetPendingTest(boolean isRefresh, MyEvaluationBean responseBean, ResultType resultType, String errorMsg) {
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
//                            showEmpty();
                            showMeasurement();
                        } else {
                            if (page == 0 && mDataList.size() == 0) {
//                                showEmpty();
                                showMeasurement();
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
//                            showEmpty();
                            showMeasurement();
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
//                            showEmpty();
                            showMeasurement();
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
        mPresenter = new PendingTestPresenter(getActivity(), mView);
    }

    @Override
    protected void onReload() {
        showLoading();
        requestPendingTest(true);
    }

    public void requestPendingTest(boolean isRefresh) {
        int requestPage = page;
        if (isRefresh) {
            //下拉刷新
            requestPage = 1;
        } else {
            //上拉加载更多
            requestPage = page + 1;
        }

        Map<String, Integer> params = new HashMap<>();
        params.put("status", 1);
        params.put("pageIndex", requestPage);
        params.put("pageSize", pageSize);
        params.put("sort",2);
        mPresenter.getPendingTest(isRefresh, RxPartMapUtils.toRequestBodyOfIntegerMap(params), this);
    }
}
