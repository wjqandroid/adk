package com.visionvera.psychologist.c.module.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPLoadFragment;
import com.visionvera.library.base.Constant;
import com.visionvera.library.eventbus.commonbean.LoginEventBus;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.ColletEventBus;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentGaugeActivity;
import com.visionvera.psychologist.c.module.home.adapter.MyCollectsEvaluationAdapter;
import com.visionvera.psychologist.c.module.home.contract.IContract;
import com.visionvera.psychologist.c.module.home.presenter.CollectEvaluationPresenter;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @Desc 我的收藏
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class MyCollectsEvaluationFragment extends BaseMVPLoadFragment<IContract.HomeCollect.View, CollectEvaluationPresenter> implements OnItemClickListener {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.normal_view)
    RecyclerView mRecy;

    private List<MyEvaluationBean.ResultBean.DataListBean> dataList = new ArrayList<>();
    private MyCollectsEvaluationAdapter mAdapter;

    public static MyCollectsEvaluationFragment newInstance() {
        return new MyCollectsEvaluationFragment();
    }

    @Override
    protected void lazyLoadData() {
        if (accountService!=null && accountService.getGetAccountInfo()!=null && accountService.getGetAccountInfo().isLogin) {
            requestCollectsData();
        }

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_selected;
    }

    @Override
    protected void initYourself() {
        EventBus.getDefault().register(this);
        ARouter.getInstance().inject(this);

        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecy.addItemDecoration(new SpaceItemDecoration(10, SpaceItemDecoration.SpaceType.Top_bottom));
        mAdapter = new MyCollectsEvaluationAdapter(dataList,getActivity());
        mRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        View errorView=getErrorView().findViewById(R.id.base_module_net_error_imageview);
        errorView.setVisibility(View.GONE);

        View emptyView=getEmptyView().findViewById(R.id.base_empty_imageview);
        emptyView.setVisibility(View.GONE);

       /* if (accountService!=null && accountService.getGetAccountInfo()!=null && accountService.getGetAccountInfo().isLogin) {
            requestCollectsData();
        }
*/
    }

    /**
     * 请求我的收藏数据
     */
    public void requestCollectsData() {

        showLoading();

        Map<String, Integer> params = new HashMap<>();
        params.put("status", 3);
        params.put("pageIndex", 1);
        params.put("pageSize", 2);
        if (mPresenter == null){
            return;
        }
        mPresenter.getCollectEvaluation(RxPartMapUtils.toRequestBodyOfIntegerMap(params), this);
    }

    @Override
    protected void initMVP() {
        mView = new IContract.HomeCollect.View() {
            @Override
            public void onGetCollectEvaluation(MyEvaluationBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        showError("");
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        showError("");
                        if (responseBean.getCode() != 1401) {
                            ToastUtils.showShort(errorMsg);
                        }
                        break;
                    case SERVER_NORMAL_DATAYES:
                        if (responseBean.getResult().getDataList().size()==0){
                            showEmpty();
                        }else{
                            showNormal();
                            refreshUI(responseBean.getResult());
                        }
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new CollectEvaluationPresenter(getActivity(), mView);
    }

    /**
     * 刷新UI
     *
     * @param bean
     */
    private void refreshUI(MyEvaluationBean.ResultBean bean) {
        dataList.clear();
        dataList.addAll(bean.getDataList());

        mAdapter.notifyDataSetChanged();

    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLoginEventBus(LoginEventBus busBean) {
        //接收到了登录的消息
        if (busBean != null) {
            if (busBean.getAccountBean() != null) {
                if (busBean.getAccountBean().isLogin) {
                    //如果登录了

                } else {
                    //如果是退出登录了
                    dataList.clear();
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (accountService != null && accountService.getGetAccountInfo() != null && !accountService.getGetAccountInfo().isLogin) {
            ARouter.getInstance()
                    .build(ARouterConstant.Account.AccountLoginActivity)
                    .withInt(Constant.IntentKey.requestReturnCode, Constant.RequestReturnCode.MyCollectsEvaluation_To_AccountLoginActivity_Code)
                    .navigation(getActivity(), Constant.RequestReturnCode.MyCollectsEvaluation_To_AccountLoginActivity_Code);
        } else {
            MyEvaluationBean.ResultBean.DataListBean voFromIndex0 = (MyEvaluationBean.ResultBean.DataListBean) adapter.getData().get(position);
            SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity.GaugeIntentBean(voFromIndex0.getId());
            SelfAssessmentGaugeActivity.startActivity(getActivity(), gaugeIntentBean);
        }
    }

    @Override
    protected void onReload() {
        requestCollectsData();
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onCollectEventBus(ColletEventBus busBean) {
        //接收到了收藏或取消收藏的消息
        if (busBean != null) {
            requestCollectsData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
