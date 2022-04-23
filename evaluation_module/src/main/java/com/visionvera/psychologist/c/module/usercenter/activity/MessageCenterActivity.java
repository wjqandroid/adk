package com.visionvera.psychologist.c.module.usercenter.activity;

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
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.usercenter.adapter.MsgAdapter;
import com.visionvera.psychologist.c.module.usercenter.bean.MessageBean;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.module.usercenter.presenter.MsgPresenter;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc 消息中心
 *
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class MessageCenterActivity extends BaseMVPActivity<IContract.Message.View, MsgPresenter> {
    @BindView(R2.id.evaluation_module_tv_title)
    TextView tvTitle;

    @BindView(R2.id.msg_refresh)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R2.id.rc_msg)
    RecyclerView mRecyclerView;

    private MsgAdapter mAdapter;
    private ArrayList<MessageBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private int page = 0;//当前页.  后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    private int pageSize = 20;

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_messagecenter;
    }

    @Override
    protected void doYourself() {
        initView();
        initData();
    }

    private void initView() {
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestMessageData(true);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requestMessageData(false);
            }
        });

        mAdapter = new MsgAdapter(this, mDataList);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(2));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                ToastUtils.showLong(getResources().getString(R.string.evaluation_module_not_develop));
            }
        });
    }

    private void initData() {
        tvTitle.setText(R.string.evaluation_module_my_message);
        smartRefreshLayout.autoRefresh();
    }

    /**
     * 请求我的收藏数据
     */
    public void requestMessageData(boolean isRefresh) {
        int requestPage = page;
        if (isRefresh) {
            //下拉刷新
            requestPage = 1;
        } else {
            //上拉加载更多
            requestPage = page + 1;
        }

        Map<String, Integer> params = new HashMap<>();
        params.put("status", 3);
        params.put("pageIndex", requestPage);
        params.put("pageSize", pageSize);
        mPresenter.getMessage(isRefresh, RxPartMapUtils.toRequestBodyOfIntegerMap(params), this);
    }

    @Override
    protected void initMVP() {
        mView = new IContract.Message.View() {
            @Override
            public void onGetMessage(boolean isRefresh, MessageBean responseBean, ResultType resultType, String errorMsg) {
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.finishLoadMore();
                }

                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        if (isRefresh) {
                            mDataList.clear();
                            page = 0;
                        } else {
                        }
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                        if (isRefresh) {
                            mDataList.clear();
                            page = 0;
                        }
                        mAdapter.notifyDataSetChanged();

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
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new MsgPresenter(MessageCenterActivity.this, mView);
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
}
