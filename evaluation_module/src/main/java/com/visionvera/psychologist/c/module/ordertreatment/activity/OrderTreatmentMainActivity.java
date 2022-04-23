package com.visionvera.psychologist.c.module.ordertreatment.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.ordertreatment.adapter.OrderTreatmentTypeAdapter;
import com.visionvera.psychologist.c.module.ordertreatment.adapter.SuggestConsultantAdapter;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.TagListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.module.ordertreatment.presenter.SuggestHospitalPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 刘传政
 * @date 2020-01-02 14:49
 * QQ:1052374416
 * 电话:18501231486
 * 作用: 预约诊疗主页面
 * 注意事项:
 */
public class OrderTreatmentMainActivity extends BaseMVPLoadActivity<IContract.OrderTreatmentMainActivity.View, SuggestHospitalPresenter> {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.normal_view)
    RecyclerView suggestRecyclerView;

    @BindView(R2.id.iv_message)
    ImageView ivMessage;

    @BindView(R2.id.rv_evaluation_home)
    RecyclerView mRecyclerView;

    private OrderTreatmentTypeAdapter mAdapter;
    private SuggestConsultantAdapter suggestConsultantAdapter;
    private String[] types = new String[]{"焦虑障碍", "抑郁障碍", "物质依赖", "人格障碍", "老年心理", "儿童心理"};
    private Integer[] typeIds = new Integer[]{32, 35, 38, 43, 36, 34};
    private List<String> typeList = new ArrayList<>();
    private List<TagListResponseBean.ResultBean> tagBeanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_order_treatment;
    }

    @Override
    protected void doYourself() {

        initView();
    }


    private void initView() {
        for (int i = 0; i < types.length; i++) {
            typeList.add(types[i]);
            TagListResponseBean.ResultBean bean = new TagListResponseBean.ResultBean();
            bean.setId(typeIds[i]);
            bean.setLableName(types[i]);
            tagBeanList.add(bean);
        }

        mRecyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        mAdapter = new OrderTreatmentTypeAdapter(activity);
        mAdapter.addData(typeList);
        mRecyclerView.setAdapter(mAdapter);

        suggestRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        suggestConsultantAdapter = new SuggestConsultantAdapter(activity);
        suggestRecyclerView.setAdapter(suggestConsultantAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                TypeRecommendActivity.IntentBean intentBean = new TypeRecommendActivity.IntentBean(tagBeanList.get(position).getId() + "",
                        tagBeanList.get(position).getLableName(), position);
                TypeRecommendActivity.startActivity(activity, intentBean);
            }
        });


        suggestConsultantAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                SubjectListActivity.IntentBean intentBean = new SubjectListActivity.IntentBean(suggestConsultantAdapter.getData().get(position).getId() + "",
                        suggestConsultantAdapter.getData().get(position).getName());

                SubjectListActivity.startActivity(activity, intentBean);
            }
        });
        http_requestSuggestList();
    }

    @OnClick({R2.id.iv_back,R2.id.evaluation_module_search_layout,R2.id.tv_refresh,R2.id.iv_message})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            finish();
        }else if (viewId==R.id.evaluation_module_search_layout){
            SearchDoctorActivity.startActivity(this);

        }else if (viewId==R.id.tv_refresh){
            http_requestSuggestList();
        }else if (viewId==R.id.iv_message){
            if (accountService!=null && accountService.getGetAccountInfo()!=null) {
                //判断是否登录
                if (accountService.getGetAccountInfo().isLogin) {
                    OrderTreatmentListActivity.startActivity(activity);
                } else {
                    //登录账号
                    ARouter.getInstance()
                            .build(ARouterConstant.Account.AccountLoginActivity)
                            .navigation(activity);
                }

            }
        }

    }

    @Override
    protected void initMVP() {
        mView = new IContract.OrderTreatmentMainActivity.View() {
            @Override
            public void onGetSuggestList(RecommentHospitalsResponseBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        showError(getString(R.string.base_module_net_error));

                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        showError(getString(R.string.base_module_net_error));

                        break;
                    case SERVER_NORMAL_DATANO:
                        //也就是result为null
                        ToastUtils.showShort(errorMsg);
                        showError(getString(R.string.base_module_no_more_data));

                        break;
                    case SERVER_NORMAL_DATAYES:
                        List<RecommentHospitalsResponseBean.ResultBean> result = responseBean.getResult();
                        if (result.size()==0){
                            showEmpty();
                        }else{
                            showNormal();
                            suggestConsultantAdapter.getData().clear();
                            suggestConsultantAdapter.addData(result);
                        }
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new SuggestHospitalPresenter(this, mView);
    }

    /**
     * 获取推荐机构
     */
    private void http_requestSuggestList() {
        showLoading();
        RecommentHospitalsRequestBean requestBean = new RecommentHospitalsRequestBean();
        mPresenter.getSuggestList(requestBean, this);
    }

    @Override
    protected void onReload() {
        http_requestSuggestList();
    }
}
