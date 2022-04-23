package com.visionvera.psychologist.c.module.ordertreatment.fragment;

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
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.OrderTreatmentListDetailToFragmentBus;
import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultDetailActivity;
import com.visionvera.psychologist.c.module.ordertreatment.adapter.OrderTreatmentItemAdapter;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListRequest;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.module.ordertreatment.presenter.OrderTreatmentListPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author:lilongfeng
 * date:2020/1/6
 * 描述:预约诊疗-----申请列表和受邀列表的子fragment,该fragment被12个页面所复用。不过展示都是一模一样。
 */

public class OrderTreatmentItemFragment extends BaseMVPLoadFragment<IContract.OrderTreatmentList.OrderTreatmentListView, OrderTreatmentListPresenter> implements OnRefreshLoadMoreListener, OnItemClickListener {

    @BindView(R2.id.evaluation_module_order_consult_item_recyclerview)
    RecyclerView mRecy;

    @BindView(R2.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;

    private int mType;
    private String mSourceType;
    private int page = 0;//当前页.  后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    private List<OrderTreatmentListBean.ResultBean.DataListBean> mDataList = new ArrayList<>();
    private OrderTreatmentItemAdapter mAdapter;

    public OrderTreatmentItemFragment() {
    }

    /**
     * @param mType        //二期页面顺序 18：待付款   2：待受理      14：待诊疗     15：已完成    7：已取消    8：已驳回   100：已关闭（取消、作废两种状态）
     * @param sourceType apply 或者  receiver
     */
    public OrderTreatmentItemFragment(int mType, String sourceType) {
        this.mType = mType;
        this.mSourceType = sourceType;
    }

    @Override
    protected void lazyLoadData() {
        showLoading();
        initRequest(true);
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

        OrderTreatmentListRequest request = new OrderTreatmentListRequest(mSourceType, 2);  //"apply":申请数据列表"receiver"受邀数据列表
        request.setClientType(2);//客户端类型：2|用户端，3|服务端
        request.setTypeId(16);//业务类型：16|诊疗，23|咨询
        request.setAppStatus(mType + "");
        request.setOrderBy("2");
        request.setPageIndex(requestPage);
        request.setPageSize(20);
        mPresenter.getOrderTreatmentList(isRefresh, request, this);

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

    private void initView() {
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);

        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new OrderTreatmentItemAdapter(mDataList);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initMVP() {
        mView = new IContract.OrderTreatmentList.OrderTreatmentListView() {
            @Override
            public void onOrderTreatmentList(boolean isRefresh, OrderTreatmentListBean response, ResultType resultType, String errorMsg) {
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
                            showConsultationTreatmentemptyEmpty();
                        } else {
                            if (page == 0 && mDataList.size() == 0) {
//                                showEmpty();
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
                        if (page == 0 && mDataList.size() == 0) {
//                            showEmpty();
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
                        if (response.getResult().getDataList().size() != 0) {
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
        mPresenter = new OrderTreatmentListPresenter(getActivity(), mView);
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        //咨询与诊疗详情页面共用
        //      int id, String sourceType, int doctorId, String payment_type, int mType
        OrderConsultDetailActivity.OrderConsultDetailIntentBean intentBean
                = new OrderConsultDetailActivity.OrderConsultDetailIntentBean(
                mDataList.get(position).getId(),
                mDataList.get(position).getAppNum(),
                mSourceType,
                mDataList.get(position).getDoctorId(),
//                mDataList.get(position).getId(),
                "",
                mType, "treatment");
        OrderConsultDetailActivity.startActivityForResult(getActivity(), intentBean);

//        NewOrderTreatmentListDetailActivity.OrderTreatmentListDetailIntentBean intentBean
//                = new NewOrderTreatmentListDetailActivity.OrderTreatmentListDetailIntentBean(
//                mDataList.get(position).getId(),
//                mDataList.get(position).getBusinessId(),
//                mType,
//                mSourceType,
//                mDataList.get(position).getDoctorId());
//
//        NewOrderTreatmentListDetailActivity.startActivityForResult(getActivity(), intentBean);
    }

    /**
     * 刷新当前列表
     */
    public void refreshRecyclerView() {
        smartRefreshLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OrderTreatmentListDetailToFragmentBus bus) {
        refreshRecyclerView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
