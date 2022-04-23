package com.visionvera.psychologist.c.module.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleResponseBean;
import com.visionvera.psychologist.c.module.allevaluation.contract.IContract;
import com.visionvera.psychologist.c.module.allevaluation.presenter.AllEvaluationFragmentPresenter;
import com.visionvera.psychologist.c.module.home.adapter.EmergencyRescueAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * author:haohuizhao
 * date:2022/03/25
 * 描述:紧急救助
 */


public class EmergencyRescueActivity extends BaseMVPLoadActivity<IContract.AllEvaluationFragment.View, AllEvaluationFragmentPresenter> {


    RecyclerView recyclerView;
    ImageView ivBack;
    TextView tvTitle;
    TextView tvRight;
    private EmergencyRescueAdapter emergencyRescueAdapter;
    private ArrayList<TabTitleResponseBean.ResultBean> tabTitleResponseBeanList;
    private List<TabTitleResponseBean.ResultBean> result;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_emergency_rescue;
    }

    @Override
    protected void doYourself() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);


        TabTitleRequestBean requestBean = new TabTitleRequestBean();
        requestBean.setGroupName("emergency_calling_list");
        mPresenter.getTabTitles(requestBean, this);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("救助热线");
        tvRight.setText("");

        tabTitleResponseBeanList = new ArrayList<TabTitleResponseBean.ResultBean>();
        recyclerView.setLayoutManager(new LinearLayoutManager(EmergencyRescueActivity.this, LinearLayoutManager.VERTICAL, false));
        emergencyRescueAdapter = new EmergencyRescueAdapter(EmergencyRescueActivity.this, tabTitleResponseBeanList);
        recyclerView.setAdapter(emergencyRescueAdapter);
        emergencyRescueAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                String remark = result.get(position).getRemark();

//                ImageView tv_address = view.findViewById(R.id.iv_call);
//                tv_address.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + remark));
                        startActivity(intent);
                    }
//                });
//
//            }
        });


    }

    @Override
    protected void initMVP() {
        mView = new IContract.AllEvaluationFragment.View() {
            @Override
            public void onGetTabTitles(TabTitleResponseBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        showError(getString(R.string.base_module_net_error));
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        showError(getString(R.string.base_module_data_load_error));
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        showNormal();
//                        setTabAndViewPager(responseBean.getResult());
                        result = responseBean.getResult();
                        tabTitleResponseBeanList.addAll(result);
                        emergencyRescueAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new AllEvaluationFragmentPresenter(this, mView);
    }

    @Override
    protected void onReload() {
        TabTitleRequestBean requestBean = new TabTitleRequestBean();
        requestBean.setGroupName("emergency_calling_list");
        mPresenter.getTabTitles(requestBean, this);
    }


}

