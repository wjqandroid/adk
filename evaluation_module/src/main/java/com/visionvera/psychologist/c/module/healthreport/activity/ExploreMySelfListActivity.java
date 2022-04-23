package com.visionvera.psychologist.c.module.healthreport.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.healthreport.adapter.ExploreMySelfListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2019/12/3
 * 描述:探索自我的列表页面
 */

public class ExploreMySelfListActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R2.id.tv_title)
    TextView tv_title;


    RecyclerView mRecy;
    private ImageView rl_back;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ExploreMySelfListActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_explore_myself_list;
    }

    @Override
    protected void doYourself() {
        initView();
    }

    private void initView() {
        mRecy = (RecyclerView) findViewById(R.id.evaluation_module_explore_myself_list_recyclerview);
        rl_back = (ImageView) findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText(getString(R.string.evaluation_module_explore_myself));

        mRecy.setLayoutManager(new LinearLayoutManager(this));
        mRecy.addItemDecoration(new SpaceItemDecoration(10));
        List<String> dataList=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add(i+"");
        }

        ExploreMySelfListAdapter mAdapter=new ExploreMySelfListAdapter(activity,dataList);
        mRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ExploreMySelfDetailActivity.startActivity(this);
    }
}
