package com.visionvera.psychologist.c.module.ordertreatment.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubjectListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubjectListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.module.ordertreatment.presenter.SubJectListActivityPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 刘传政
 * @date 2019-06-25 11:31
 * QQ:1052374416
 * 电话:18501231486
 * 作用:科室列表
 * 注意事项:
 */
public class SubjectListActivity extends BaseMVPLoadActivity<IContract.SubjectListActivity.View, SubJectListActivityPresenter> {
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.normal_view)
    SmartRefreshLayout smartRefreshLayout;
    MyAdapter adapter;
    List<SubjectListResponseBean.ResultBean> dataList = new ArrayList<>();
    //默认创建一个，保证取值不null。所以里边的默认值很重要
    public IntentBean intentBean = new IntentBean();
    private ImageView iv_back;

    /**
     * 规定参数的开启方法
     *
     * @param context
     * @param intentBean
     */
    public static void startActivity(Context context, IntentBean intentBean) {
        Intent intent = new Intent(context, SubjectListActivity.class);
        intent.putExtra(Constant.IntentKey.IntentBean, intentBean);
        context.startActivity(intent);
    }

    @Override
    protected void onReload() {
        http_getSubjects();
        showLoading();
    }

    private void http_getSubjects() {
        SubjectListRequestBean requestBean = new SubjectListRequestBean(Integer.parseInt(intentBean.id));
        mPresenter.getSubjectList(requestBean, this);
    }

    @Override
    protected void initMVP() {
        mView = new IContract.SubjectListActivity.View() {
            @Override
            public void onGetSubjectList(SubjectListResponseBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        showError(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                        //也就是result为null
                        ToastUtils.showShort(errorMsg);
                        showEmpty();
                        break;
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        dataList.clear();
                        dataList.addAll(responseBean.getResult());
                        adapter.notifyDataSetChanged();
                        if (dataList.size() <= 0) {
                            showEmpty();
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
        mPresenter = new SubJectListActivityPresenter(activity, mView);
    }

    public static class IntentBean implements Serializable {

        private String id;
        private String name;

        public IntentBean() {
        }

        public IntentBean(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_subject_list;
    }

    @Override
    protected void doYourself() {
        parseIntent();
        initView();
        http_getSubjects();
        showLoading();
    }

    private void parseIntent() {
        //获取参数
        IntentBean bean = (IntentBean) getIntent().getSerializableExtra(Constant.IntentKey.IntentBean);
        if (bean != null) {
            intentBean = bean;
        }
        Logger.i(intentBean.toString());
    }

    private void initView() {
        tvTitle.setText(intentBean.name);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SubjectRecommendActivity.IntentBean intentBean = new SubjectRecommendActivity.IntentBean(dataList.get(position).getId() + "",
                        dataList.get(position).getName(), position, dataList);
                SubjectRecommendActivity.startActivity(activity, intentBean);
            }
        });
    }


    class MyAdapter extends BaseQuickAdapter<SubjectListResponseBean.ResultBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<SubjectListResponseBean.ResultBean> data) {
            super(R.layout.evaluation_module_item_subject_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SubjectListResponseBean.ResultBean item) {
            helper.setText(R.id.tv_left, item.getName());
        }
    }
}
