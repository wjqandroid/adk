package com.visionvera.psychologist.c.module.counselling;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPLoadFragment;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.counselling.activity.CounselorDetailActivity;
import com.visionvera.psychologist.c.module.counselling.activity.SearchCounselorActivity;
import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultListActivity;
import com.visionvera.psychologist.c.module.counselling.adapter.EvaluationTypeAdapter;
import com.visionvera.psychologist.c.module.counselling.adapter.SuggestConsultantAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.SuggestListBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.SuggestPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc 心理咨询首页面
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class CounsellingFragment extends BaseMVPLoadFragment<OrderConsultContract.Suggest.View, SuggestPresenter> {
    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.normal_view)
    RecyclerView suggestRecyclerView;

    @BindView(R2.id.iv_message)
    ImageView ivMessage;

    @BindView(R2.id.rl_evaluation_search)
    RelativeLayout rlEvaluationSearch;

    @BindView(R2.id.rv_evaluation_home)
    RecyclerView mRecyclerView;
    @BindView(R2.id.smartrefresh)
    SmartRefreshLayout smartrefresh;

    private EvaluationTypeAdapter mAdapter;
    private SuggestConsultantAdapter suggestConsultantAdapter;
    private List<SuggestListBean.ResultBean> mList = new ArrayList<>();
    private Integer[] typeImgList = new Integer[]{R.drawable.evaluation_module_zixun_type_geren,
            R.drawable.evaluation_module_zixun_type_yanli,
            R.drawable.evaluation_module_zixun_type_renji,
            R.drawable.evaluation_module_zixun_type_qinggan,
            R.drawable.evaluation_module_zixun_type_xinli,
            R.drawable.evaluation_module_zixun_type_jiating};
    private Integer[] typeBgList = new Integer[]{R.drawable.evaluation_module_bg_zixun_type_geren,
            R.drawable.evaluation_module_bg_zixun_type_yali,
            R.drawable.evaluation_module_bg_zixun_type_renji,
            R.drawable.evaluation_module_bg_zixun_type_qinggan,
            R.drawable.evaluation_module_bg_zixun_type_xinli,
            R.drawable.evaluation_module_bg_zixun_type_jiating};

    private String[] types = {"个人成长", "压力管理", "人际关系", "情绪情感", "心理健康", "家庭亲子"};

    private int[] typeId = {27, 28, 29, 53, 30, 31};

    private List<EvaluationTypeAdapter.EvaluationTypeBean> evaluationTypeList = new ArrayList<>();

    public static CounsellingFragment newInstance() {
        return new CounsellingFragment();
    }

    @Override
    protected void lazyLoadData() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_assessment;
    }

    @Override
    protected void initYourself() {
        ARouter.getInstance().inject(this);
        updateStatuBar();
        initView();
        initData();

    }

    public void updateStatuBar() {
        if (getActivity() != null) {
            ImmersionBar.with(this)
                    .statusBarColor(R.color.white)
                    .statusBarDarkFont(true)
                    .fitsSystemWindows(true)
                    .init();
        }
    }

    private void initData() {
        requestSuggestList();
    }

    /**
     * 按条件请求咨询师
     */
    private void requestSuggestList() {
        mPresenter.getSuggestList(this);
    }

    /**
     * 初始化adapter
     */
    private void initView() {
        smartrefresh.setEnableLoadMore(false);
        smartrefresh.setOnRefreshListener(refreshLayout -> {
            requestSuggestList();
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter = new EvaluationTypeAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        for (int i = 0; i < typeImgList.length; i++) {
            EvaluationTypeAdapter.EvaluationTypeBean evaluationTypeBean = new EvaluationTypeAdapter.EvaluationTypeBean();
            evaluationTypeBean.setEvaluationType(types[i]);
            evaluationTypeBean.setEvaluationTypeId(typeId[i]);
            evaluationTypeBean.setEvaluationTypeImg(typeImgList[i]);
            evaluationTypeBean.evaluationTypeBg = typeBgList[i];

            evaluationTypeList.add(evaluationTypeBean);
        }

        mAdapter.addData(evaluationTypeList);

        suggestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        suggestConsultantAdapter = new SuggestConsultantAdapter(getActivity(),mList);
        suggestRecyclerView.setAdapter(suggestConsultantAdapter);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            EvaluationTypeAdapter.EvaluationTypeBean evaluationTypeBean = mAdapter.getItem(position);
            SearchCounselorActivity.MakeAnAppointmentIntentBean intentBean = new SearchCounselorActivity.MakeAnAppointmentIntentBean(evaluationTypeBean.evaluationType, evaluationTypeBean.evaluationTypeId);
            SearchCounselorActivity.startActivity(getActivity(), intentBean);
        });


        suggestConsultantAdapter.setOnItemClickListener((adapter, view, position) -> {
            CounselorDetailActivity.CounselorDetailIntentBean intentBean
                    = new CounselorDetailActivity.CounselorDetailIntentBean(
                    suggestConsultantAdapter.getData().get(position).getPsyInfoId(),
                    suggestConsultantAdapter.getData().get(position).getUserId());

            CounselorDetailActivity.startActivity(getActivity(), intentBean);
        });
    }

    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.Suggest.View() {
            @Override
            public void onGetSuggestList(SuggestListBean responseBean, ResultType resultType, String errorMsg) {
                smartrefresh.finishRefresh();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        showError(getString(R.string.base_module_net_error));
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        showError(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                        //也就是result为null
                        ToastUtils.showShort(errorMsg);
                        showError(getString(R.string.base_module_no_more_data));
                        break;
                    case SERVER_NORMAL_DATAYES:
                        List<SuggestListBean.ResultBean> result = responseBean.getResult();
                        if (result.size()==0){
                            showEmpty();
                        }else{
                            showNormal();
                            mList.clear();
                            mList.addAll(result);
//                            suggestConsultantAdapter.getData().clear();
//                            suggestConsultantAdapter.addData(result);

                            suggestConsultantAdapter.notifyDataSetChanged();

                        }
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new SuggestPresenter(getActivity(), mView);
    }


    @OnClick({R2.id.iv_back, R2.id.rl_evaluation_search, R2.id.iv_message})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            getActivity().finish();
        } else if (viewId == R.id.rl_evaluation_search) {
            SearchCounselorActivity.MakeAnAppointmentIntentBean intentBean = new SearchCounselorActivity.MakeAnAppointmentIntentBean("", 0);
            SearchCounselorActivity.startActivity(getActivity(), intentBean);
        } else if (viewId == R.id.iv_message) {
            if (accountService != null && accountService.getGetAccountInfo() != null && !accountService.getGetAccountInfo().isLogin) {
                ARouter.getInstance()
                        .build(ARouterConstant.Account.AccountLoginActivity)
                        .navigation(getActivity());

            } else {
                OrderConsultListActivity.startActivity(getActivity());
            }

        }
    }

    @Override
    protected void onReload() {
        requestSuggestList();
    }

}
