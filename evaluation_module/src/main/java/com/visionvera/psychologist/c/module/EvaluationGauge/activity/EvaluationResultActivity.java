package com.visionvera.psychologist.c.module.EvaluationGauge.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.util.GlideImageLoader;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.adapter.EvaluationResultAnalyseAdapter;
import com.visionvera.psychologist.c.module.EvaluationGauge.adapter.EvaluationResultScoreAdapter;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EPQResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultType;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.MBTIResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.PDPResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.SDSResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.contract.SelfAssessmentContract;
import com.visionvera.psychologist.c.module.EvaluationGauge.presenter.EvaluationResultPresenter;
import com.visionvera.psychologist.c.module.counselling.activity.CounsellingMainActivity;
import com.visionvera.psychologist.c.widget.BarChartData;
import com.visionvera.psychologist.c.widget.BarChartView;
import com.visionvera.psychologist.c.widget.CustomDialog;
import com.visionvera.psychologist.c.widget.RadarMapView;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc 测评结果页面
 * @Author yemh
 * @Date 2019-11-13 14:49
 */
public class EvaluationResultActivity extends BaseMVPLoadActivity<SelfAssessmentContract.EvaluationResult.View, EvaluationResultPresenter> {

    @BindView(R2.id.evaluation_module_tv_title)
    TextView tvTitle;

    @BindView(R2.id.tv_right_text)
    TextView tvRightText;

    @BindView(R2.id.iv_evaluation_result)
    ImageView ivEvaluationResult;

    @BindView(R2.id.tv_evalutaion_result_time)
    TextView tvEvalutaionResultTime;

    @BindView(R2.id.tv_evalutaion_result_number)
    TextView tvEvalutaionResultNumber;

    @BindView(R2.id.evaluation_radarMap)
    RadarMapView evaluationRadarMap;

    @BindView(R2.id.ll_test_result0)
    LinearLayout llTestResult0;

    @BindView(R2.id.tv_test_score)
    TextView tvTestScore;

    @BindView(R2.id.ll_test_result1)
    LinearLayout llTestResult1;

    @BindView(R2.id.iv_test_type)
    ImageView ivTestType;

    @BindView(R2.id.iv_test_type_1)
    ImageView ivTestType1;

    @BindView(R2.id.ll_test_result2)
    LinearLayout llTestResult2;

    @BindView(R2.id.barchartview)
    BarChartView barChartView;

    @BindView(R2.id.tv_test_result)
    TextView tvTestResult;

    @BindView(R2.id.tv_result_content)
    TextView tvResultContent;

    @BindView(R2.id.ll_result1_container)
    LinearLayout ll_result1_container;

    @BindView(R2.id.ll_test_result5)
    LinearLayout ll_test_result5;

    @BindView(R2.id.ll_test_result5_all_score)
    TextView ll_test_result5_all_score;

    @BindView(R2.id.result5_recyclerview)
    RecyclerView result5_recyclerview;

    //    @BindView(R2.id.ll_test_result5_score_flowlayout)
//    TagFlowLayout flowlayout;
    @BindView(R2.id.ll_test_result5_score_recyclerview)
    RecyclerView ll_test_result5_score_recyclerview;

    @BindView(R2.id.ll_test_result5_isSun_number)
    TextView isSun_number;

    @BindView(R2.id.ll_test_result5_isSun_score)
    TextView isSun_score;

    @BindView(R2.id.all_score_explain)
    TextView all_score_explain;

    @BindView(R2.id.sun_explain_layout)
    LinearLayout sun_explain_layout;

    @BindView(R2.id.sun_explain)
    TextView sun_explain;

    @BindView(R2.id.ll_test_result6)
    LinearLayout ll_test_result6;

    @BindView(R2.id.result6_result)
    TextView result6_result_tv;

    @BindView(R2.id.result6_analysis)
    ImageView result6_analysis;

    public static String RESULT_INTENT_BEAN_STRING = "RESULT_INTENT_BEAN_STRING";
    private CustomDialog testAgainDialog;
    private EvaluationResultIntentBean intentBean;
    private List<SDSResultBean.ScaleResultBean.TenDivisorBean> mScoreList = new ArrayList<>();
    private EvaluationResultAnalyseAdapter evaluationResultAnalyseAdapter;
    private EvaluationResultScoreAdapter evaluationResultScoreAdapter;
    private Gson gson = new Gson();

    public static void startActivity(Context context, EvaluationResultIntentBean evaluationResultIntentBean) {
        context.startActivity(new Intent(context, EvaluationResultActivity.class).putExtra(RESULT_INTENT_BEAN_STRING, evaluationResultIntentBean));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_evaluation_result;
    }

    @Override
    protected void doYourself() {
        initView();
        initDialog();
        initData();
    }

    private void initView() {
        tvRightText.setVisibility(View.VISIBLE);

        ll_test_result5_score_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        result5_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        ll_test_result5_score_recyclerview.addItemDecoration(new SpaceItemDecoration(10));
        result5_recyclerview.addItemDecoration(new SpaceItemDecoration(5));

        evaluationResultScoreAdapter = new EvaluationResultScoreAdapter(mScoreList);
        evaluationResultAnalyseAdapter = new EvaluationResultAnalyseAdapter(mScoreList);

        ll_test_result5_score_recyclerview.setAdapter(evaluationResultScoreAdapter);
        result5_recyclerview.setAdapter(evaluationResultAnalyseAdapter);

    }

    private void initData() {
        intentBean = (EvaluationResultIntentBean) getIntent().getSerializableExtra(RESULT_INTENT_BEAN_STRING);
        tvRightText.setText(getResources().getString(R.string.evaluation_module_test_again));
        tvTitle.setText(intentBean.name);

        requestEvaluationResult();

    }

    /**
     * 请求测试结果数据
     */
    public void requestEvaluationResult() {
        EvaluationResultType type=null;
        if (TextUtils.equals(intentBean.code, "SDS")) {
            type=EvaluationResultType.SDS;
        } else if (TextUtils.equals(intentBean.code, "SAS")) {
            type=EvaluationResultType.SAS;
        } else if (TextUtils.equals(intentBean.code, "GWB")) {
            type=EvaluationResultType.GWB;
        } else if (TextUtils.equals(intentBean.code, "PSTR")) {
            type=EvaluationResultType.PSTR;
        } else if (TextUtils.equals(intentBean.code, "MBTI")) {
            //职业性格测试MBTI
            type=EvaluationResultType.MBTI;
        } else if (TextUtils.equals(intentBean.code, "SCL-90")) {
            //心理健康状态自评
            type=EvaluationResultType.SCL_90;
        } else if (TextUtils.equals(intentBean.code, "EPQ")) {
            //艾森克人格问卷EPQ
            type=EvaluationResultType.EPQ;
        } else if (TextUtils.equals(intentBean.code, "PDP")) {
            //行为特质动态衡量系统PDP
            type = EvaluationResultType.PDP;
        } else if (TextUtils.equals(intentBean.code, "DQ")) {
            type = EvaluationResultType.DQ;
        }

        showLoading();
        EvaluationResultRequest evaluationResultRequest = new EvaluationResultRequest();
        evaluationResultRequest.setScaleId(intentBean.scaleId);
        evaluationResultRequest.setSerialNumber(intentBean.serialNumber);
        mPresenter.getEvaluationResult(evaluationResultRequest, type, this);

    }

    @Override
    protected void onReload() {
        requestEvaluationResult();
    }

    public static class EvaluationResultIntentBean implements Serializable {
        //测评类型ID
        public String scaleId;
        //测评编号
        public String serialNumber;
        //量表名称
        public String name;
        //量表对应的类型，比如是MBTI，或者总体幸福感量表GWB.....
        public String code;

        public EvaluationResultIntentBean(String scaleId, String serialNumber, String name, String code) {
            this.scaleId = scaleId;
            this.serialNumber = serialNumber;
            this.name = name;
            this.code = code;
        }
    }

    private void initDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.evaluation_module_dialog_test_again, null);
        TextView tvCancle = view.findViewById(R.id.tv_cancle);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        testAgainDialog = builder.testAgainDialog(view);
        tvCancle.setOnClickListener(v -> {
            if (OneClickUtils.isFastClick()) {
                return;
            }
            if (testAgainDialog != null) {
                testAgainDialog.disMiss();
            }
        });
        tvConfirm.setOnClickListener(v -> {
            if (OneClickUtils.isFastClick()) {
                return;
            }
            if (testAgainDialog != null) {
                testAgainDialog.disMiss();
            }
            SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity
                    .GaugeIntentBean(Integer.parseInt(intentBean.scaleId));

            SelfAssessmentGaugeActivity.startActivity(activity, gaugeIntentBean);
        });
    }

    @Override
    protected void initMVP() {
        mView = new SelfAssessmentContract.EvaluationResult.View() {
            @Override
            public void onGetEvaluationResult(EvaluationResultBean responseBean, EvaluationResultType type, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
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
                        refreshUI(responseBean, type);
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new EvaluationResultPresenter(EvaluationResultActivity.this, mView);
    }

    private void refreshUI(EvaluationResultBean allBean, EvaluationResultType eType) {

        if (eType == EvaluationResultType.MBTI) {

            showMBTIMethod(allBean);

        } else if (eType == EvaluationResultType.SDS
                || eType == EvaluationResultType.SAS
                || eType == EvaluationResultType.GWB
                || eType == EvaluationResultType.PSTR
                || eType == EvaluationResultType.DQ) {

            showSDSMethod(allBean);

        } else if (eType == EvaluationResultType.SCL_90) {

            showSCL90Method(allBean);

        } else if (eType == EvaluationResultType.EPQ) {

            showEPQMethod(allBean);
        } else if (eType == EvaluationResultType.PDP) {

            showPDPMethod(allBean);
        }

    }


    /**
     * 分辨该动物是什么类型
     */
    private int selectAnimal(List<PDPResultBean.PdpRemarkBean> listPDP) {
        String animalName = listPDP.get(0).getName();
        int animalImageId = 0;
        if (animalName.contains("老虎")) {
            animalImageId = R.drawable.evaluation_module_tiger;
        } else if (animalName.contains("孔雀")) {
            animalImageId = R.drawable.evaluation_module_peacock;
        } else if (animalName.contains("考拉")) {
            animalImageId = R.drawable.evaluation_module_panda;
        } else if (animalName.contains("猫头鹰")) {
            animalImageId = R.drawable.evaluation_module_owl;
        } else if (animalName.contains("变色龙")) {
            animalImageId = R.drawable.evaluation_module_lizard;
        }
        return animalImageId;
    }


    /**
     * 动态往布局中添加小动物的解释信息
     */
    private View inflaterView(List<PDPResultBean.PdpRemarkBean> pdpBean) {
        View pdpview = LayoutInflater.from(this).inflate(R.layout.evaluation_module_child_result_1, null);

        TextView title1 = pdpview.findViewById(R.id.tv_result_title1);
        TextView title2 = pdpview.findViewById(R.id.tv_result_title2);
        TextView title3 = pdpview.findViewById(R.id.tv_result_title3);
        TextView title4 = pdpview.findViewById(R.id.tv_result_title4);
        TextView title5 = pdpview.findViewById(R.id.tv_result_title5);
        TextView content2 = pdpview.findViewById(R.id.tv_result_content2);
        TextView content3 = pdpview.findViewById(R.id.tv_result_content3);
        TextView content4 = pdpview.findViewById(R.id.tv_result_content4);
        TextView content5 = pdpview.findViewById(R.id.tv_result_content5);

        if (pdpBean.size() >= 5) {
            if (TextUtils.isEmpty(pdpBean.get(0).getValue())) {
                title1.setText(pdpBean.get(0).getName());
            } else {
                title1.setText(pdpBean.get(0).getName() + "、" + pdpBean.get(0).getValue());
            }
            title2.setText(pdpBean.get(1).getName());
            title3.setText(pdpBean.get(2).getName());
            title4.setText(pdpBean.get(3).getName());
            title5.setText(pdpBean.get(4).getName());

            content2.setText(pdpBean.get(1).getValue());
            content3.setText(pdpBean.get(2).getValue());
            content4.setText(pdpBean.get(3).getValue());
            content5.setText(pdpBean.get(4).getValue());
        }
        return pdpview;
    }

    /**
     * 行为特质动态衡量系统
     */
    private void showPDPMethod(EvaluationResultBean allBean) {
        Type type = new TypeToken<EvaluationResultBean<PDPResultBean>>(){
        }.getType();
        EvaluationResultBean<PDPResultBean> resultBean = gson.fromJson(gson.toJson(allBean), type);
        PDPResultBean bean = resultBean.getResult();

        try {
            showCommonUIView(bean.getUrl(), bean.getEndTime(), bean.getSerialNumber());

            if (bean.getScaleResult() != null && bean.getScaleResult().getErrcode() == 0) {
                //有结果返回，一个小动物或者两个的综合体
                llTestResult1.setVisibility(View.VISIBLE);
                ll_result1_container.setVisibility(View.VISIBLE);

                if (bean.getPdpRemark().size() == 1) {
                    //只有一种动物
                    View pdpView1 = inflaterView(bean.getPdpRemark().get(0));
                    ll_result1_container.addView(pdpView1);

                    int animalImageId = selectAnimal(bean.getPdpRemark().get(0));
                    ivTestType.setImageResource(animalImageId);

                } else if (bean.getPdpRemark().size() == 2) {
                    //两种动物的综合体

                    View pdpView1 = inflaterView(bean.getPdpRemark().get(0));
                    ll_result1_container.addView(pdpView1);

                    View pdpView2 = inflaterView(bean.getPdpRemark().get(1));
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(0, 50, 0, 0);
                    pdpView2.setLayoutParams(lp);
                    ll_result1_container.addView(pdpView2);


                    int animalImageId1 = selectAnimal(bean.getPdpRemark().get(0));
                    ivTestType.setImageResource(animalImageId1);

                    int animalImageId2 = selectAnimal(bean.getPdpRemark().get(1));
                    ivTestType1.setImageResource(animalImageId2);

                }

            } else if (bean.getScaleResult() != null && bean.getScaleResult().getErrcode() == 1) {
                //没有结果返回
                tvResultContent.setVisibility(View.VISIBLE);
                tvResultContent.setText(bean.getScaleResult().getErrmsg());
            }
        } catch (Exception e) {

        }

    }


    /**
     * 艾森克人格问卷EPQ
     */
    private void showEPQMethod(EvaluationResultBean allBean) {

        Type type = new TypeToken<EvaluationResultBean<EPQResultBean>>() {
        }.getType();
        EvaluationResultBean<EPQResultBean> resultBean = gson.fromJson(gson.toJson(allBean), type);
        EPQResultBean bean = resultBean.getResult();

        try {
            showCommonUIView(bean.getUrl(), bean.getEndTime(), bean.getSerialNumber());

            ll_test_result6.setVisibility(View.VISIBLE);
            result6_result_tv.setVisibility(View.VISIBLE);
            result6_analysis.setVisibility(View.VISIBLE);

            result6_result_tv.setText("本次测试结果:"+bean.getGradeRemark());
            double eValue = bean.getScaleResult().getE();
            double nValue = bean.getScaleResult().getN();

            if (eValue > 50) {
                if (nValue > 50) {
                    //右上角:第一象限
                    result6_analysis.setImageResource(R.drawable.evaluation_module_epq_1);
                } else {
                    //右下角:第四象限
                    result6_analysis.setImageResource(R.drawable.evaluation_module_epq_4);
                }
            } else {
                if (nValue > 50) {
                    //左上角:第二象限
                    result6_analysis.setImageResource(R.drawable.evaluation_module_epq_2);
                } else {
                    //左下角:第三象限
                    result6_analysis.setImageResource(R.drawable.evaluation_module_epq_3);
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * 职业性格测试MBTI
     */
    private void showMBTIMethod(EvaluationResultBean allBean) {
        Type type = new TypeToken<EvaluationResultBean<MBTIResultBean>>() {
        }.getType();
        EvaluationResultBean<MBTIResultBean> resultBean = gson.fromJson(gson.toJson(allBean), type);

        try {
            MBTIResultBean bean = resultBean.getResult();
            showCommonUIView(bean.getUrl(), bean.getEndTime(), bean.getSerialNumber());
            llTestResult2.setVisibility(View.VISIBLE);

            String gradeName = bean.getGradeName();
            if (!TextUtils.isEmpty(gradeName)) {
                tvTestResult.setText(gradeName);
            }
            tvResultContent.setVisibility(View.VISIBLE);
            tvResultContent.setText(bean.getGradeRemark());

            List<MBTIResultBean.ResultBean> dataList = bean.getResult();
            int size = dataList.size();
            if (size > 0) {
                BarChartData barChartData = new BarChartData();

                String[] upTitles = new String[4];//设置上半边标题
                String[] downTitles = new String[4];//设置下半边标题
                int[] upValuse = new int[size];//设置上半边数值
                int[] downValuse = new int[size];//设置下半边数值

                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).getName().contains("外向")) {
                        //E  (外向)
                        upTitles[0] = dataList.get(i).getName();
                        upValuse[0] = dataList.get(i).getVal();
                    } else if (dataList.get(i).getName().contains("内向")) {
                        //I（内向）
                        downTitles[0] = dataList.get(i).getName();
                        downValuse[0] = dataList.get(i).getVal();

                    } else if (dataList.get(i).getName().contains("直觉")) {
                        //N（直觉）
                        upTitles[1] = dataList.get(i).getName();
                        upValuse[1] = dataList.get(i).getVal();

                    } else if (dataList.get(i).getName().contains("感觉")) {
                        //S（感觉）
                        downTitles[1] = dataList.get(i).getName();
                        downValuse[1] = dataList.get(i).getVal();

                    } else if (dataList.get(i).getName().contains("情感")) {
                        //F（情感）
                        upTitles[2] = dataList.get(i).getName();
                        upValuse[2] = dataList.get(i).getVal();

                    } else if (dataList.get(i).getName().contains("思考")) {
                        //T（思考）
                        downTitles[2] = dataList.get(i).getName();
                        downValuse[2] = dataList.get(i).getVal();

                    } else if (dataList.get(i).getName().contains("感知")) {
                        //P（感知）
                        upTitles[3] = dataList.get(i).getName();
                        upValuse[3] = dataList.get(i).getVal();


                    } else if (dataList.get(i).getName().contains("判断")) {
                        //J（判断）
                        downTitles[3] = dataList.get(i).getName();
                        downValuse[3] = dataList.get(i).getVal();
                    }
                }

                barChartData.setUpTitles(upTitles);
                barChartData.setDownTitles(downTitles);
                barChartData.setUpValuse(upValuse);
                barChartData.setDownValuse(downValuse);

                barChartView.setData(barChartData);
            }
        } catch (Exception e) {

        }

    }

    /**
     * 心理健康症状自评量表SCL-90
     */
    @SuppressLint("SetTextI18n")
    private void showSCL90Method(EvaluationResultBean allBean) {
        Type type = new TypeToken<EvaluationResultBean<SDSResultBean>>() {
        }.getType();
        EvaluationResultBean<SDSResultBean> beanTemp = gson.fromJson(gson.toJson(allBean), type);
        SDSResultBean bean = beanTemp.getResult();

        try {
            showCommonUIView(bean.getUrl(), bean.getEndTime(), bean.getSerialNumber());

            ll_test_result5.setVisibility(View.VISIBLE);
            result5_recyclerview.setVisibility(View.VISIBLE);

            if (bean.getScaleResult() != null && bean.getScaleResult().getCountAndSunDivisor() != null) {
                if (TextUtils.equals(bean.getScaleResult().getCountAndSunDivisor().getIsSun(), "true")) {
                    //阳性
                    sun_explain_layout.setVisibility(View.VISIBLE);
                    if (bean.getScaleResult().getCountAndSunDivisor().getSunRemark() != null) {
                        sun_explain.setText(bean.getScaleResult().getCountAndSunDivisor().getSunRemark());
                    }
                } else {
                    //阴性
                    sun_explain_layout.setVisibility(View.GONE);
                }
                DecimalFormat df = new DecimalFormat("#0.00");


                isSun_number.setText(bean.getScaleResult().getCountAndSunDivisor().getSunProject() + "");
                isSun_score.setText(df.format(bean.getScaleResult().getCountAndSunDivisor().getSeptSunScore()) + "");

                all_score_explain.setText(bean.getScaleResult().getCountAndSunDivisor().getTotalRemark());

                ll_test_result5_all_score.setText(bean.getResultScore() + "");

                mScoreList.addAll(bean.getScaleResult().getTenDivisor());

                evaluationResultScoreAdapter.notifyDataSetChanged();
                evaluationResultAnalyseAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {

        }

    }

    /**
     * 包含SDS,SAS,GWB,PSTR
     */
    private void showSDSMethod(EvaluationResultBean allBean) {
        Type type = new TypeToken<EvaluationResultBean<SDSResultBean>>() {
        }.getType();
        EvaluationResultBean<SDSResultBean> beanTemp = gson.fromJson(gson.toJson(allBean), type);
        SDSResultBean bean = beanTemp.getResult();

        try {
            showCommonUIView(bean.getUrl(), bean.getEndTime(), bean.getSerialNumber());
            //PSTR类型暂时加到这里了。不知道对不对，但是现象显示可以
            llTestResult0.setVisibility(View.VISIBLE);

            tvTestScore.setText(bean.getResultScore() + "");
            tvResultContent.setVisibility(View.VISIBLE);
            String content = "";
            String gradeName = bean.getGradeName();
            if (!TextUtils.isEmpty(gradeName)) {
                content = content + gradeName + "\n";
            }

            String gradeRemark = bean.getGradeRemark();
            if (!TextUtils.isEmpty(gradeRemark)) {
                content = content + gradeRemark;
            }

            tvResultContent.setText(content);
        } catch (Exception e) {

        }
    }


    private void showCommonUIView(String imageUrl, String endTime, String serialNumber) {
        if (!TextUtils.isEmpty(imageUrl)) {
            GlideImageLoader.loadImage(this, imageUrl, ivEvaluationResult);
        }

        if (!TextUtils.isEmpty(endTime)) {
            tvEvalutaionResultTime.setText("测试时间: " + endTime);
        }

        if (!TextUtils.isEmpty(serialNumber)) {
            tvEvalutaionResultNumber.setText("测试编号: " + serialNumber);
        }
    }

    @OnClick({R2.id.evaluation_module_iv_back, R2.id.tv_right_text, R2.id.tv_zixun})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_iv_back) {
            finish();
        } else if (viewId == R.id.tv_right_text) {
           /* if (testAgainDialog != null && !testAgainDialog.isShowing()) {
                testAgainDialog.show();
            }*/
            SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean = new SelfAssessmentGaugeActivity
                    .GaugeIntentBean(Integer.parseInt(intentBean.scaleId));

            SelfAssessmentGaugeActivity.startActivity(activity, gaugeIntentBean);

        } else if (viewId == R.id.tv_zixun) {
            //立即咨询
            startActivity(new Intent(activity, CounsellingMainActivity.class));
        }
    }
}
