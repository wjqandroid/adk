package com.visionvera.psychologist.c.module.counselling.fragment;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tencent.qcloud.tuicore.net.MessageEvent;
import com.visionvera.library.base.BaseMVPLoadFragment;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.OrderConsultDetailToFragmentBus;
import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultDetailActivity;
import com.visionvera.psychologist.c.module.counselling.adapter.OrderConsultItemAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultListBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultListRequest;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.OrderConsultListPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author:lilongfeng
 * date:2019/12/25
 * 描述:预约咨询----申请列表和受邀列表的子fragment,该fragment被12个页面所复用。不过展示都是一模一样。
 */

public class OrderConsultItemFragment extends BaseMVPLoadFragment<OrderConsultContract.OrderConsultList.OrderConsultListView, OrderConsultListPresenter> implements OnItemClickListener, OnRefreshLoadMoreListener {

    @BindView(R2.id.evaluation_module_order_consult_item_recyclerview)
    RecyclerView mRecy;

    @BindView(R2.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;

    private int mType;
    private String mSourceType;
    private int page = 0;//当前页.  后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    private List<OrderConsultListBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private OrderConsultItemAdapter mAdapter;

    /**
     * @param mType      二期页面顺序  2：待受理   14：待咨询    15：已完成   7：已取消   8：已驳回    100：已关闭（取消、作废两种状态）
     * @param sourceType apply 或者  receiver     申请、受邀
     */
    public OrderConsultItemFragment(int mType, String sourceType) {
        this.mType = mType;
        this.mSourceType = sourceType;
    }

    @Override
    protected void lazyLoadData() {
        showLoading();
        initRequest(true);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_order_consult_item;
    }

    @Override
    protected void initYourself() {
        EventBus.getDefault().register(this);

        initView();

    }

    private void initRequest(boolean isRefresh) {

        int requestPage;
        if (isRefresh) {
            //下拉刷新
            requestPage = 1;
        } else {
            //上拉加载更多
            requestPage = page + 1;
        }

        OrderConsultListRequest request = new OrderConsultListRequest(mSourceType, "2");

        request.setClientType(2);//客户端类型：2|用户端，3|服务端
        request.setTypeId(23);//业务类型：16|诊疗，23|咨询
        request.setAppStatus(mType + "");
        request.setOrderBy("2");
        request.setPageIndex(requestPage);
        request.setPageSize(20);
        mPresenter.getOrderConsultList(isRefresh, request, this);
    }

    private void initView() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(this);

        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new OrderConsultItemAdapter(mDataList);
        mRecy.setAdapter(mAdapter);
        mRecy.addItemDecoration(new SpaceItemDecoration(10));

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        OrderConsultDetailActivity.OrderConsultDetailIntentBean intentBean
                = new OrderConsultDetailActivity.OrderConsultDetailIntentBean(
                mDataList.get(position).getId(), mDataList.get(position).getAppNum(),
                mSourceType,
                mDataList.get(position).getDoctorId(), "", mType, "consulting");

        OrderConsultDetailActivity.startActivityForResult(getActivity(), intentBean);
    }

    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.OrderConsultList.OrderConsultListView() {
            @Override
            public void onOrderConsultList(boolean isRefresh, OrderConsultListBean response, ResultType resultType, String errorMsg) {
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
                           // showEmpty();
                            showConsultationTreatmentemptyEmpty();
                        } else {
                            if (page == 0 && mDataList.size() == 0) {
                               // showEmpty();
                                showConsultationTreatmentemptyEmpty();
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
                        showConsultationTreatmentemptyEmpty();
                        if (page == 0 && mDataList.size() == 0) {
                            //showEmpty();
                            showConsultationTreatmentemptyEmpty();
                        } else {
                            showNormal();
                        }
                        break;
                    case SERVER_NORMAL_DATAYES:
                        if (isRefresh) {
                            mDataList.clear();
                            page = 0;
                        }
                        if (response.getResult().getDataList() != null && response.getResult().getDataList().size() != 0) {
                            page++;
                            mDataList.addAll(response.getResult().getDataList());
                        }
                        mAdapter.notifyDataSetChanged();

                        if (page == 0 && mDataList.size() == 0) {
//                            showEmpty();
                            showConsultationTreatmentemptyEmpty();
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
        mPresenter = new OrderConsultListPresenter(getActivity(), mView);
    }

    @Override
    protected void onReload() {
        showLoading();
        initRequest(true);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        initRequest(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        initRequest(true);
    }

    /**
     * 刷新当前列表
     */
    public void refreshRecyclerView() {
        smartRefreshLayout.autoRefresh();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OrderConsultDetailToFragmentBus bus) {
        refreshRecyclerView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    //回调刷新页面
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        String message = event.getMessage();
        //发送消息成功
        if (message.equals("refresh")) {
            initRequest(true);

        }
    }
}
