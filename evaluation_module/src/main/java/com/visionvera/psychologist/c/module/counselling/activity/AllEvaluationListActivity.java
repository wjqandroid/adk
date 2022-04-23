package com.visionvera.psychologist.c.module.counselling.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.adapter.AllEvaluationListAdapter;
import com.visionvera.psychologist.c.module.counselling.adapter.ConsultingEvaluationListAdapter;
import com.visionvera.psychologist.c.module.counselling.adapter.StarsEvaluationAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.AvatarBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.CounselorDetailPresenter;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.utils.SpacesItemDecoration;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Classname:LookEvaluationActivity
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0
 * @describe： 查看 全部评价
 */
public class AllEvaluationListActivity extends BaseMVPLoadActivity<OrderConsultContract.CounselorDetail.CounselorDetailView, CounselorDetailPresenter> {


    ImageView ivBack;
    RelativeLayout rlBack;
    TextView tvLeft;
    TextView tvTitle;
    RecyclerView allRecyclerView;

    private EvaluateListBean mIntentBean=new EvaluateListBean();
    private AllEvaluationListAdapter allEvaluationListAdapter;

    public static void startActivity(Context context, EvaluateListBean intentBean) {
        Intent intent = new Intent(context, AllEvaluationListActivity.class);
        intent.putExtra("intentBean", intentBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_all_evaluation_list;
    }

    @Override
    protected void doYourself() {
        initIntentBean();

        initView();
        initAdapter();
    }

    private void initIntentBean() {
        mIntentBean = (EvaluateListBean) getIntent().getSerializableExtra("intentBean");

    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        allRecyclerView = (RecyclerView) findViewById(R.id.all_evaluation_recyclerView);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //医生、咨询师名字
        tvTitle.setText("收到" + mIntentBean.getResult().getDataList().size() + "条评价");
    }


    @Override
    protected void initMVP() {

    }

    @Override
    protected void onReload() {

    }


    //评价LIst   初始化 Adapter
    private void initAdapter() {
        List<EvaluateListBean.ResultDTO.DataListDTO> dataList = mIntentBean.getResult().getDataList();
        allRecyclerView.setLayoutManager(new LinearLayoutManager(AllEvaluationListActivity.this, LinearLayoutManager.VERTICAL, false));
        allRecyclerView.addItemDecoration(new SpacesItemDecoration(60));
        allEvaluationListAdapter = new AllEvaluationListAdapter(AllEvaluationListActivity.this, dataList);
        allRecyclerView.setAdapter(allEvaluationListAdapter);
        allEvaluationListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                //跳转评价详情
                EvaluateListBean.ResultDTO.DataListDTO dataListDTO = dataList.get(position);
                LookEvaluationActivity.LookEvaluationIntentBean lookEvaluationIntentBean = new LookEvaluationActivity.LookEvaluationIntentBean(
                        "",
                        0,
                        1,
                        dataListDTO.getServiceName(),
                        mIntentBean.getResult().getImgUrl(),
                        1,
                        "2",//1 给医生评价   2.查看评价
                        dataListDTO.getCreatetime(),
                        dataListDTO.getEvaluateSatisfaction(),
                        dataListDTO.getEvaluateContent(),
                        "", ""
                );
                LookEvaluationActivity.startActivity(AllEvaluationListActivity.this, lookEvaluationIntentBean);

            }
        });

    }


}