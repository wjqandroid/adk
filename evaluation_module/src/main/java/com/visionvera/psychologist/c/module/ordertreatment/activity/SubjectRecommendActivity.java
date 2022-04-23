package com.visionvera.psychologist.c.module.ordertreatment.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lxj.xpopup.XPopup;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.ExpertiesResponseBean;
import com.visionvera.psychologist.c.module.counselling.view.EvaluationTypePopup;
import com.visionvera.psychologist.c.module.ordertreatment.adapter.RecommendConsultantListAdapter;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubjectListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.module.ordertreatment.presenter.SubjectRecommendActivityPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: 刘传政
 * @date: 2020-01-02 16:14
 * QQ:1052374416
 * 作用:预约诊疗-根据科室推荐咨询师
 * 注意事项:
 */

public class SubjectRecommendActivity extends BaseMVPLoadActivity<IContract.SubjectRecommendActivity.View, SubjectRecommendActivityPresenter> {

    private TextView tv_search_match;
    private RecyclerView consultantRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private ImageView ivArrow;
    private RelativeLayout rlTitle;
    private TextView tvTitle;

    private RecommendConsultantListAdapter consultantListAdapter;
    private EvaluationTypePopup evaluationTypePopup;
    private List<ExpertiesResponseBean.ResultBean> tagList = new ArrayList<>();
    int selectPosition = 0;
    List<DoctorListResponseBean.ResultBean> dataList = new ArrayList<>();
    private int page = 0;//当前页.  后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    int pageSize = 20;

    //默认创建一个，保证取值不null。所以里边的默认值很重要
    public IntentBean intentBean = new IntentBean();

    /**
     * 规定参数的开启方法
     *
     * @param context
     * @param intentBean
     */
    public static void startActivity(Context context, IntentBean intentBean) {
        Intent intent = new Intent(context, SubjectRecommendActivity.class);
        intent.putExtra(Constant.IntentKey.IntentBean, intentBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_order_treatment_type_recommend;
    }

    @Override
    protected void doYourself() {
        tv_search_match = findViewById(R.id.tv_search_match);
        consultantRecyclerView = findViewById(R.id.rc_consultant);
        smartRefreshLayout = findViewById(R.id.normal_view);
        ivArrow = findViewById(R.id.iv_arrow);
        rlTitle = findViewById(R.id.rl_title);
        tvTitle = findViewById(R.id.tv_title);
        ImageView iv_back = findViewById(R.id.iv_back);
        RelativeLayout rl_titleAndArrow = findViewById(R.id.rl_titleAndArrow);
        iv_back.setOnClickListener(this);
        rl_titleAndArrow.setOnClickListener(this);

        parseIntent();
        tvTitle.setText(intentBean.subjectName);
        initAdapter();
        if (intentBean.subjectList != null && intentBean.subjectList.size() > 0) {
            selectPosition = intentBean.selectPosition;
            for (int i = 0; i < intentBean.subjectList.size(); i++) {
                ExpertiesResponseBean.ResultBean resultBean = new ExpertiesResponseBean.ResultBean();
                resultBean.setLableName(intentBean.subjectList.get(i).getName());
                resultBean.setId(intentBean.subjectList.get(i).getId());
                if (selectPosition == i) {
                    resultBean.setSelect(true);
                }
                tagList.add(resultBean);
            }
        }
        showLoading();
        http_getList(true);

    }

    private void parseIntent() {
        //获取参数
        IntentBean bean = (IntentBean) getIntent().getSerializableExtra(Constant.IntentKey.IntentBean);
        if (bean != null) {
            intentBean = bean;
        }
        Logger.i(intentBean.toString());
    }

    private void initAdapter() {
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        smartRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setEnableRefresh(true);
        consultantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        consultantListAdapter = new RecommendConsultantListAdapter(this, dataList);
        consultantRecyclerView.setAdapter(consultantListAdapter);

        consultantListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewOrderTreatmentDetailActivity.IntentBean intentBean
                        = new NewOrderTreatmentDetailActivity.IntentBean(dataList.get(position).getId(), dataList.get(position).getUserId());

                NewOrderTreatmentDetailActivity.startActivity(activity, intentBean);
            }
        });
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                http_getList(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                http_getList(true);
            }
        });
    }


    private void showTypePopup() {
        if (evaluationTypePopup != null && evaluationTypePopup.isShow()) {
            return;
        }
        evaluationTypePopup = (EvaluationTypePopup) new XPopup.Builder(this)
                .atView(rlTitle)
                .asCustom(new EvaluationTypePopup(this, tagList, new EvaluationTypePopup.OnListPopupClick() {
                    @Override
                    public void onPopupClick(String id, String name) {
                        tvTitle.setText(name);
                        for (int i = 0; i < intentBean.subjectList.size(); i++) {
                            if ((intentBean.subjectList.get(i).getId() + "").equals(id)) {
                                selectPosition = i;
                                for (int i1 = 0; i1 < tagList.size(); i1++) {
                                    ExpertiesResponseBean.ResultBean ivo = tagList.get(i1);
                                    if (selectPosition == i1) {
                                        ivo.setSelect(true);
                                    } else {
                                        ivo.setSelect(false);
                                    }
                                }
                                break;
                            }
                        }
                        http_getList(true);

                    }
                }));
        evaluationTypePopup.show();
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.rl_titleAndArrow) {
            showTypePopup();
        }
    }

    @Override
    protected void initMVP() {
        mView = new IContract.SubjectRecommendActivity.View() {
            @Override
            public void onGetDoctorListFromHospital(boolean isRefresh, DoctorListResponseBean responseBean, ResultType resultType, String errorMsg) {
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.finishLoadMore();
                }
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        if (page == 0 && dataList.size() == 0) {
                            showError(errorMsg);
                        }
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        if (isRefresh) {
                            dataList.clear();
                            page = 0;
                            showEmpty();
                        } else {
                            if (page == 0 && dataList.size() == 0) {
                                showEmpty();
                            } else {
                                showNormal();
                            }
                        }
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                        //也就是result为null
                        if (isRefresh) {
                            dataList.clear();
                            page = 0;
                        }
                        consultantListAdapter.notifyDataSetChanged();
                        if (page == 0 && dataList.size() == 0) {
                            showEmpty();
                        } else {
                            showNormal();
                        }
                        break;
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        if (isRefresh) {
                            dataList.clear();
                            page = 0;
                        }
                        if (responseBean.getResult().size() != 0) {
                            page++;
                            dataList.addAll(responseBean.getResult());
                        }
                        consultantListAdapter.notifyDataSetChanged();
                        if (page == 0 && dataList.size() == 0) {
                            showEmpty();
                        } else {
                            showNormal();
                        }

                        SpannableStringBuilder spannableString = getSpannableString("为您推荐擅长" + tagList.get(selectPosition).getLableName() + "诊疗的医生共" + dataList.size() + "位"
                                , tagList.get(selectPosition).getLableName());

                        tv_search_match.setText(spannableString);
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new SubjectRecommendActivityPresenter(this, mView);
    }

    @Override
    protected void onReload() {
        showLoading();
        http_getList(true);
    }

    public void http_getList(boolean isRefresh) {
        int requestPage = page;
        if (isRefresh) {
            //下拉刷新
            requestPage = 1;
        } else {
            //上拉加载更多
            requestPage = page + 1;
        }

        DoctorListRequestBean requestBean = new DoctorListRequestBean();
        requestBean.departmentId = tagList.get(selectPosition).getId();
        requestBean.pageSize = pageSize;
        requestBean.pageIndex = requestPage;
        mPresenter.getDoctorListFromHospital(isRefresh, requestBean, this);
    }

    private SpannableStringBuilder getSpannableString(String wholeStr, String matchStr) {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(wholeStr);
        //匹配规则
        Pattern p = Pattern.compile(matchStr);
        //匹配字段
        Matcher m = p.matcher(spannableString);
        while (m.find()) {
            //所有都匹配
            int start = m.start();
            int end = m.end();
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#333333"));
            spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(ConvertUtils.sp2px(15));
            spannableString.setSpan(absoluteSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return spannableString;
    }

    public static class IntentBean implements Serializable {
        /**
         * 科室id
         */
        private String subjectId = "";
        /**
         * 科室名称
         */
        private String subjectName = "";
        /**
         * 选择位置
         */
        private int selectPosition;
        List<SubjectListResponseBean.ResultBean> subjectList = new ArrayList<>();

        public IntentBean() {
        }

        public IntentBean(String subjectId, String subjectName, int selectPosition, List<SubjectListResponseBean.ResultBean> subjectList) {
            this.subjectId = subjectId;
            this.subjectName = subjectName;
            this.selectPosition = selectPosition;
            this.subjectList = subjectList;
        }
    }


}
