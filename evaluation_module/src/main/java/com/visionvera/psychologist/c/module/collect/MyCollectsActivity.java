package com.visionvera.psychologist.c.module.collect;

import android.view.View;
import android.widget.LinearLayout;
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
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.ColletEventBus;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentGaugeActivity;
import com.visionvera.psychologist.c.module.collect.adapter.CollectsAdapter;
import com.visionvera.psychologist.c.module.collect.contract.IContract;
import com.visionvera.psychologist.c.module.collect.presenter.CollectsPresenter;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc ζηζΆθ
 *
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class MyCollectsActivity extends BaseMVPActivity<IContract.Collect.View, CollectsPresenter> {
    @BindView(R2.id.evaluation_module_tv_title)
    TextView tvTitle;

    @BindView(R2.id.collect_refresh)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R2.id.rc_collects)
    RecyclerView mRecyclerView;

    @BindView(R2.id.ll_no_collect)
    LinearLayout llNoCollect;

    private CollectsAdapter mAdapter;
    private ArrayList<MyEvaluationBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private int page = 0;//ε½ει‘΅.  εε°ζ―1εΌε§η.ζδ»₯θΏιε?δΉε½ει‘΅ζ―0,θ‘¨η€Ίη¬¬δΈι‘΅ι½ζ²‘θ―·ζ±
    private int pageSize = 20;

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_collects;
    }

    @Override
    protected void doYourself() {
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setEnableOverScrollDrag(true);//ζ―ε¦ε―η¨θΆηζε¨οΌδ»ΏθΉζζζοΌ1.0.4
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestCollectsData(true);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requestCollectsData(false);
            }
        });

        mAdapter = new CollectsAdapter(this, mDataList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity
                        .GaugeIntentBean(mDataList.get(position).getId());

                SelfAssessmentGaugeActivity.startActivity(MyCollectsActivity.this, gaugeIntentBean);
            }
        });
    }

    private void initData() {
        tvTitle.setText(R.string.evaluation_module_collects);
        smartRefreshLayout.autoRefresh();
    }

    /**
     * θ―·ζ±ζηζΆθζ°ζ?
     */
    public void requestCollectsData(boolean isRefresh) {
        int requestPage = page;
        if (isRefresh) {
            //δΈζε·ζ°
            requestPage = 1;
        } else {
            //δΈζε θ½½ζ΄ε€
            requestPage = page + 1;
        }

        Map<String, Integer> params = new HashMap<>();
        params.put("status", 3);
        params.put("pageIndex", requestPage);
        params.put("pageSize", pageSize);
        mPresenter.getCollects(isRefresh, RxPartMapUtils.toRequestBodyOfIntegerMap(params), this);
    }

    @Override
    protected void initMVP() {
        mView = new IContract.Collect.View() {
            @Override
            public void onGetCollects(boolean isRefresh, MyEvaluationBean responseBean, ResultType resultType, String errorMsg) {
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.finishLoadMore();
                }

                switch (resultType) {
                    case NET_ERROR:
                        //η½η»εΌεΈΈη­
                        if (page == 0 && mDataList.size() == 0) {
                            llNoCollect.setVisibility(View.VISIBLE);
                        }else{
                            llNoCollect.setVisibility(View.GONE);
                        }
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        if (isRefresh) {
                            mDataList.clear();
                            page = 0;
                            llNoCollect.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 0 && mDataList.size() == 0) {
                                llNoCollect.setVisibility(View.VISIBLE);
                            } else {
                                llNoCollect.setVisibility(View.GONE);
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
                            llNoCollect.setVisibility(View.VISIBLE);
                        } else {
                            llNoCollect.setVisibility(View.GONE);
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
                            llNoCollect.setVisibility(View.VISIBLE);
                        } else {
                            llNoCollect.setVisibility(View.GONE);
                        }
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new CollectsPresenter(MyCollectsActivity.this, mView);
    }

    @OnClick({R2.id.evaluation_module_iv_back})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_iv_back) {
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onCollectEventBus(ColletEventBus busBean) {
        //ζ₯ζΆε°δΊζΆθζεζΆζΆθηζΆζ―
        if (busBean != null) {
            if (busBean.collectStatus == 1) {
                //ζΆθ
                //δΉεε―δ»₯θθζηΉζ?ε€η
            } else {
                //εζΆζΆθ
            }
            //ε·ζ°εθ‘¨
            requestCollectsData(true);
        }
    }

}
