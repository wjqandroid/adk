package com.visionvera.psychologist.c.module.myevaluation;

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
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseMVPLoadFragment;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.myevaluation.adapter.ExpiredAdapter;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.module.myevaluation.bean.ReTestRequestBean;
import com.visionvera.psychologist.c.module.myevaluation.contract.IContract;
import com.visionvera.psychologist.c.module.myevaluation.presenter.ExpiredPresenter;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;
import com.visionvera.psychologist.c.widget.CustomDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @Desc 已过期
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class ExpiredFragment extends BaseMVPLoadFragment<IContract.Expired.View, ExpiredPresenter> {
    @BindView(R2.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R2.id.rc_pending_tested)
    RecyclerView mRecyclerView;

    private ExpiredAdapter mAdapter;
    private ArrayList<MyEvaluationBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private int page = 0;//当前页.  后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    private int pageSize = 20;
    private CustomDialog reApplayDialog;
    //记录当前点击的Bean
    private MyEvaluationBean.ResultBean.DataListBean itemBean;

    public static ExpiredFragment newInstance() {
        return new ExpiredFragment();
    }

    @Override
    protected void lazyLoadData() {
        showLoading();
        requestExpired(true);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_tested;
    }

    @Override
    protected void initYourself() {
        initView();
        initDialog();

    }

    private void initView() {
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestExpired(true);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requestExpired(false);
            }
        });

        mAdapter = new ExpiredAdapter(getActivity(), this, mDataList);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                itemBean = mDataList.get(position);
                if (reApplayDialog != null && !reApplayDialog.isShowing()) {
                    reApplayDialog.show();
                }
            }
        });
    }

    @Override
    protected void initMVP() {
        mView = new IContract.Expired.View() {
            @Override
            public void onGetExpired(boolean isRefresh, MyEvaluationBean responseBean, ResultType resultType, String errorMsg) {
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
            public void onReTest(BaseResponseBean2 bean, ResultType resultType, String errorMsg) {
                ((BaseActivity) getActivity()).hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        if (page == 0 && mDataList.size() == 0) {
                            showError(errorMsg);
                        }
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        ToastUtils.showLong("重新测试申请已提交,审核通过后可在待测列表中找到该测评");
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new ExpiredPresenter(getActivity(), mView);
    }

    @Override
    protected void onReload() {
        showLoading();
        requestExpired(true);
    }

    public void requestExpired(boolean isRefresh) {
        int requestPage = page;
        if (isRefresh) {
            //下拉刷新
            requestPage = 1;
        } else {
            //上拉加载更多
            requestPage = page + 1;
        }

        Map<String, Integer> params = new HashMap<>();
        params.put("status", 4);
        params.put("pageIndex", requestPage);
        params.put("pageSize", pageSize);
        params.put("sort", 2);
        mPresenter.getExpired(isRefresh, RxPartMapUtils.toRequestBodyOfIntegerMap(params), this);
    }

    public void net_reTest(int scaleId, int pushRecordId) {
        ReTestRequestBean requestBean = new ReTestRequestBean();
        requestBean.scaleId = scaleId;
        requestBean.pushRecordId = pushRecordId;
        mPresenter.reTest(requestBean, this);
    }


    private void initDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.evaluation_module_dialog_re_applay, null);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        reApplayDialog = builder.reApplayDialog(view);

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                if (reApplayDialog != null) {
                    reApplayDialog.disMiss();
                }
                if (itemBean != null) {
                    net_reTest(itemBean.getScaleId(), itemBean.getPushRecordId());
                    ((BaseActivity) getActivity()).showLoadingDialog();
                }

            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reApplayDialog != null) {
                    reApplayDialog.disMiss();
                }
                // TODO: 1/11/21 发起重新测评的功能实现
            }
        });
    }
}
