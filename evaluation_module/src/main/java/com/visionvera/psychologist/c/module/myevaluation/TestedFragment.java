package com.visionvera.psychologist.c.module.myevaluation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.CommitToMyEvaluationBus;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.EvaluationResultActivity;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentGaugeActivity;
import com.visionvera.psychologist.c.module.myevaluation.adapter.TestedAdapter;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.module.myevaluation.contract.IContract;
import com.visionvera.psychologist.c.module.myevaluation.presenter.TestedPresenter;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;
import com.visionvera.psychologist.c.widget.CustomDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @Desc 已测试
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class TestedFragment extends BaseMVPLoadFragment<IContract.Tested.View, TestedPresenter> {
    @BindView(R2.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R2.id.rc_pending_tested)
    RecyclerView mRecyclerView;

    private TestedAdapter mAdapter;
    private ArrayList<MyEvaluationBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private int page = 0;//当前页.  后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    private int pageSize = 20;

    private CustomDialog testAgainDialog;
    private MyEvaluationBean.ResultBean.DataListBean itemBean;

    public static TestedFragment newInstance() {
        return new TestedFragment();
    }

    @Override
    protected void lazyLoadData() {
        showLoading();
        requestTested(true);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_tested;
    }

    @Override
    protected void initYourself() {
        initView();
        initDialog();

        EventBus.getDefault().register(this);
    }

    private void initView() {
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestTested(true);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requestTested(false);
            }
        });

        mAdapter = new TestedAdapter(getActivity(), mDataList);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                itemBean = mDataList.get(position);
               /*
                if (testAgainDialog != null && !testAgainDialog.isShowing()){
                    testAgainDialog.show();
                }*/
                EvaluationResultActivity.EvaluationResultIntentBean intentBean
                        = new EvaluationResultActivity.EvaluationResultIntentBean(
                        itemBean.getId() + "",
                        itemBean.getSerialNumber(),
                        itemBean.getName(),
                        itemBean.getCode());
                EvaluationResultActivity.startActivity(getActivity(), intentBean);


            }
        });
    }

    @Override
    protected void initMVP() {
        mView = new IContract.Tested.View() {
            @Override
            public void onGetTested(boolean isRefresh, MyEvaluationBean responseBean, ResultType resultType, String errorMsg) {
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
        mPresenter = new TestedPresenter(getActivity(), mView);
    }

    @Override
    protected void onReload() {
        showLoading();
        requestTested(true);
    }

    public void requestTested(boolean isRefresh) {

        if (mPresenter != null) {
            int requestPage = page;
            if (isRefresh) {
                //下拉刷新
                requestPage = 1;
            } else {
                //上拉加载更多
                requestPage = page + 1;
            }

            Map<String, Integer> params = new HashMap<>();
            params.put("status", 2);
            params.put("pageIndex", requestPage);
            params.put("pageSize", pageSize);
            params.put("sort", 2);
            Log.e("Tag", "requestTested: =======");
            mPresenter.getTested(isRefresh, RxPartMapUtils.toRequestBodyOfIntegerMap(params), this);
        }
    }

    private void initDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.evaluation_module_dialog_test_again, null);
        TextView tvCancle = view.findViewById(R.id.tv_cancle);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        testAgainDialog = builder.testAgainDialog(view);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                if (testAgainDialog != null) {
                    testAgainDialog.disMiss();
                }
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                if (testAgainDialog != null) {
                    testAgainDialog.disMiss();
                }
                SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity
                        .GaugeIntentBean(itemBean.getId());

                SelfAssessmentGaugeActivity.startActivity(getActivity(), gaugeIntentBean);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void fromMyEvaluaionToTested(CommitToMyEvaluationBus evaluationBus) {
        if (evaluationBus.type == 3) {
            smartRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
