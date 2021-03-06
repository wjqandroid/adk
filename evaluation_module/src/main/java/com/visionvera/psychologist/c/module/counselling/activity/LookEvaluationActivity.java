package com.visionvera.psychologist.c.module.counselling.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.commonbean.AccountBean;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.adapter.StarsEvaluationAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.AvatarBean;
import com.visionvera.psychologist.c.module.counselling.bean.CounselorDetailBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.CounselorDetailPresenter;
import com.visionvera.psychologist.c.module.ordertreatment.bean.AddEvaluateRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListRequest;
import com.visionvera.psychologist.c.utils.CommonUtils;
import com.visionvera.psychologist.c.utils.FiveStarView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Classname:LookEvaluationActivity
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version???v1.0
 * @describe??? ??????????????????????????? 1.?????????????????????????????????
 * 2.????????????????????????????????????
 * 3.???????????????????????????????????????????????????
 */

public class LookEvaluationActivity extends BaseMVPLoadActivity<OrderConsultContract.CounselorDetail.CounselorDetailView, CounselorDetailPresenter> {
    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;


    ImageView ivBack;
    RelativeLayout rlBack;
    TextView tvLeft;
    TextView tvTitle;
    TextView lookEvaluationTime;
    CircleImageView lookEvaluationDetailHeader;
    TextView lookEvaluationName;
    TextView lookEvaluationType;
    RecyclerView reFiveStars;
    TextView lookEvaluationState;
    EditText lookEvaluationEt;
    LinearLayout normalView;
    TextView lookEvaluationBtn;
    FiveStarView lookEvaluationFiveStarView;
    TextView tvTextSize;
    TextView tvSatisfactionTips;
    TextView tvEvaluate;
    TextView lookEvaluationTv;
    //??????Adapter
    private StarsEvaluationAdapter starsEvaluationAdapter;
    private LookEvaluationIntentBean mIntentBean;
    //????????????
    private AccountBean accountInfo;
    //??????
    private int score;
    //????????????
    private int evaluationType;
    private String evaluation_type;
    //????????????
    public String userId;
    public String userName;
    private ImageView iv_back;


    public static void startActivity(Context context, LookEvaluationIntentBean intentBean) {
        Intent intent = new Intent(context, LookEvaluationActivity.class);
        intent.putExtra("intentBean", intentBean);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_look_evaluation;
    }

    @Override
    protected void doYourself() {
        initIntentBean();
        initView();
    }


    private void initIntentBean() {
        mIntentBean = (LookEvaluationIntentBean) getIntent().getSerializableExtra("intentBean");
    }


    private void initView() {
        lookEvaluationTime = (TextView) findViewById(R.id.look_evaluation_time);
        lookEvaluationDetailHeader = (CircleImageView) findViewById(R.id.look_evaluation_detail_header);
        lookEvaluationName = (TextView) findViewById(R.id.look_evaluation_name);
        lookEvaluationType = (TextView) findViewById(R.id.look_evaluation_type);
        lookEvaluationType = (TextView) findViewById(R.id.look_evaluation_type);
        reFiveStars = (RecyclerView) findViewById(R.id.re_five_stars);
        lookEvaluationState = (TextView) findViewById(R.id.look_evaluation_state);
        lookEvaluationEt = (EditText) findViewById(R.id.look_evaluation_et);
        normalView = (LinearLayout) findViewById(R.id.normal_view);
        lookEvaluationBtn = (TextView) findViewById(R.id.look_evaluation_btn);
        lookEvaluationBtn = (TextView) findViewById(R.id.look_evaluation_state);
        lookEvaluationFiveStarView = (FiveStarView) findViewById(R.id.look_evaluation_fiveStarView);
        tvTextSize = (TextView) findViewById(R.id.tv_text_size);
        tvSatisfactionTips = (TextView) findViewById(R.id.tv_satisfaction_tips);
        tvEvaluate = (TextView) findViewById(R.id.tv_evaluate);
        lookEvaluationTv = (TextView) findViewById(R.id.look_evaluation_tv);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatarBeanList.clear();
                finish();
            }
        });
        lookEvaluationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score != 0) {
                    request();
                } else {
                    ToastUtils.showShort("?????????");
                }
            }
        });

        if (mIntentBean!=null){
            //?????? ????????????
            if (mIntentBean.type.equals("1")) {
                //????????????
                tvTitle.setText("????????????");
                tvEvaluate.setText("???????????????");
                lookEvaluationEt.setVisibility(View.VISIBLE);
                lookEvaluationTv.setVisibility(View.GONE);
                lookEvaluationBtn.setVisibility(View.VISIBLE);
                //0/200
                tvTextSize.setVisibility(View.VISIBLE);
                //??????????????????????????????
                tvSatisfactionTips.setVisibility(View.VISIBLE);

                //??????????????????     4????????????  5??????  6??????
                if (mIntentBean.selecTypeName == 4) {
                    evaluationType = 1;
                    evaluation_type = "????????????";
                } else if (mIntentBean.selecTypeName == 5) {
                    evaluationType = 2;
                    evaluation_type = "????????????";
                } else if (mIntentBean.selecTypeName == 6) {
                    evaluationType = 3;
                    evaluation_type = "????????????";
                }
                //??????????????????
                lookEvaluationFiveStarView.setStar(0);
                setAvatarList();
                initAdapter();
                //??????
                Glide.with(this).load(mIntentBean.imgUrl)
                        .placeholder(R.drawable.base_module_doctor_header)
                        .error(R.drawable.base_module_doctor_header)
                        .into(lookEvaluationDetailHeader);
                //??????name
                lookEvaluationName.setText(mIntentBean.name);
                //????????????
                String currentTime = CommonUtils.getCurrentTime();
                lookEvaluationTime.setText(currentTime);
                //????????????
                lookEvaluationType.setText(evaluation_type);
            }
            //????????????
            else if (mIntentBean.type.equals("2")) {

                //??????  1??????2??????3??????
                if (mIntentBean.selecTypeName == 1) {
                    evaluationType = 1;
                    evaluation_type = "????????????";
                } else if (mIntentBean.selecTypeName == 2) {
                    evaluationType = 2;
                    evaluation_type = "????????????";
                } else if (mIntentBean.selecTypeName == 3) {
                    evaluationType = 3;
                    evaluation_type = "????????????";
                }
                tvTitle.setText("????????????");
                tvEvaluate.setText("??????");
                //????????????
                lookEvaluationEt.setVisibility(View.GONE);
                lookEvaluationTv.setVisibility(View.VISIBLE);
                reFiveStars.setVisibility(View.GONE);
                lookEvaluationFiveStarView.setVisibility(View.VISIBLE);
                lookEvaluationBtn.setVisibility(View.GONE);


                //??????????????????
                int evaluateSatisfaction = mIntentBean.evaluateSatisfaction;
                lookEvaluationFiveStarView.setStar(evaluateSatisfaction);
                if (evaluateSatisfaction == 1) {
                    lookEvaluationState.setText("?????????");
                } else if (evaluateSatisfaction == 2) {
                    lookEvaluationState.setText("???????????????");
                } else if (evaluateSatisfaction == 3) {
                    lookEvaluationState.setText("????????????");
                } else if (evaluateSatisfaction == 4) {
                    lookEvaluationState.setText("??????");
                } else if (evaluateSatisfaction == 5) {
                    lookEvaluationState.setText("????????????");
                }
                //??????
                Glide.with(this).load(mIntentBean.imgUrl)
                        .placeholder(R.drawable.base_module_doctor_header)
                        .error(R.drawable.base_module_doctor_header)
                        .into(lookEvaluationDetailHeader);
                //??????name
                lookEvaluationName.setText(mIntentBean.name);
                //????????????
                lookEvaluationTime.setText(mIntentBean.evaluationTime);
                //????????????
                lookEvaluationType.setText(evaluation_type);
                //????????????
                lookEvaluationTv.setText(mIntentBean.evaluateContent);
            }
            //??????????????????????????????
            else if (mIntentBean.type.equals("3")) {
                if (mIntentBean.serviceNumber.length() > 0) {
                    EvaluateListRequest evaluateListRequest = new EvaluateListRequest();
                    //evaluateListRequest.setServiceNumber("4750a2643e6d4b3b8d2f564b40af5beb");
                    evaluateListRequest.setServiceNumber(mIntentBean.serviceNumber);//?????????
                    mPresenter.getEvaluateDetail(evaluateListRequest, this);
                } else {
                    ToastUtils.showLong("????????????");
                }
            }
        }
    }


    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.CounselorDetail.CounselorDetailView() {
            @Override
            public void onCounselorDetailByPsyInfoId(CounselorDetailBean bean, ResultType resultType, String errorMsg) {
//                parseNetDetailResponse(bean, resultType);
            }

            @Override
            public void onCounselorDetailByUserId(CounselorDetailBean bean, ResultType resultType, String errorMsg) {
//                parseNetDetailResponse(bean, resultType);
            }

            //??????list
            @Override
            public void onEvaluateList(EvaluateListBean bean, ResultType resultType, String errorMsg) {

            }

            //add ??????
            @Override
            public void onAddEvaluate(BaseResponseBean2 bean, ResultType resultType, String errorMsg) {
                if (bean != null) {
                    parseNetAddEvaluate(bean, resultType);
                } else {
                    ToastUtils.showShort(errorMsg);
                }
            }

            //????????????
            @Override
            public void onEvaluateDetail(EvaluateDetailBean bean, ResultType resultType, String errorMsg) {
                if (bean != null) {
                    setEvaluateDetail(bean);
                } else {
                    ToastUtils.showShort(errorMsg);
                }

            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new CounselorDetailPresenter(this, mView);
    }

    //????????????
    private void setEvaluateDetail(EvaluateDetailBean bean) {
        EvaluateDetailBean.ResultDTO result = bean.getResult();
        //??????  1??????2??????3??????
        if (result.getServiceType() == 1) {
            evaluation_type = "????????????";
        } else if (result.getServiceType() == 2) {
            evaluation_type = "????????????";
        } else if (result.getServiceType() == 3) {
            evaluation_type = "????????????";
        }
        tvTitle.setText("????????????");
        tvEvaluate.setText("??????");
        //????????????
        lookEvaluationEt.setVisibility(View.GONE);
        lookEvaluationTv.setVisibility(View.VISIBLE);
        reFiveStars.setVisibility(View.GONE);
        lookEvaluationFiveStarView.setVisibility(View.VISIBLE);
        lookEvaluationBtn.setVisibility(View.GONE);
        //??????????????????
        int evaluateSatisfaction = result.getEvaluateSatisfaction();
        lookEvaluationFiveStarView.setStar(evaluateSatisfaction);
        if (evaluateSatisfaction == 1) {
            lookEvaluationState.setText("?????????");
        } else if (evaluateSatisfaction == 2) {
            lookEvaluationState.setText("???????????????");
        } else if (evaluateSatisfaction == 3) {
            lookEvaluationState.setText("????????????");
        } else if (evaluateSatisfaction == 4) {
            lookEvaluationState.setText("??????");
        } else if (evaluateSatisfaction == 5) {
            lookEvaluationState.setText("????????????");
        }
        //??????
        Glide.with(this).load(mIntentBean.imgUrl)
                .placeholder(R.drawable.base_module_doctor_header)
                .error(R.drawable.base_module_doctor_header)
                .into(lookEvaluationDetailHeader);
        //??????name
        lookEvaluationName.setText(result.getServiceName());
        //????????????
        lookEvaluationTime.setText(result.getCreatetime());
        //????????????
        lookEvaluationType.setText(evaluation_type);
        //????????????
        lookEvaluationTv.setText(result.getEvaluateContent());
    }

    //add ??????
    private void parseNetAddEvaluate(BaseResponseBean2 bean, IBaseView.ResultType resultType) {
        switch (resultType) {
            case NET_ERROR:
                //??????????????????????????????????????????????????????????????????
                showError(getString(R.string.base_module_net_error));
                ToastUtils.showLong(getString(R.string.base_module_net_error));
                break;
            case SERVER_ERROR:
                //?????????????????????????????????code?????????
                showError(getString(R.string.base_module_data_load_error));
                ToastUtils.showLong(getString(R.string.base_module_data_load_error));
                break;
            case SERVER_NORMAL_DATANO:
            case SERVER_NORMAL_DATAYES:
                //??????????????????????????????code?????????result??????null?????????result???????????????????????????????????????
                showNormal();
                ToastUtils.showLong(bean.getErrMsg());
                avatarBeanList.clear();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
        }
    }

    @Override
    protected void onReload() {

    }


    //??????List
    private static List<AvatarBean> avatarBeanList = new ArrayList<>();

    private void setAvatarList() {
        if (avatarBeanList.size() == 0) {
            avatarBeanList.add(new AvatarBean("??????", 1, 0, false));
            avatarBeanList.add(new AvatarBean("??????", 2, 5, false));
            avatarBeanList.add(new AvatarBean("??????", 3, 8, false));
            avatarBeanList.add(new AvatarBean("??????", 4, 5, false));
            avatarBeanList.add(new AvatarBean("??????", 5, 5, false));

        }
    }

    //?????????
    //????????????
    //????????????
    //??????
    //????????????
    //??????LIst   ????????? Adapter
    private void initAdapter() {
        reFiveStars.setLayoutManager(new LinearLayoutManager(LookEvaluationActivity.this, LinearLayoutManager.HORIZONTAL, false));
        //reCommentList.addItemDecoration(new SpacesItemDecoration(20));
        starsEvaluationAdapter = new StarsEvaluationAdapter(LookEvaluationActivity.this, avatarBeanList);
        reFiveStars.setAdapter(starsEvaluationAdapter);
        starsEvaluationAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

                //????????????
                if (mIntentBean.type.equals("1")) {
                    if (position == 0) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = false;
                        }
                        avatarBeanList.get(position).is_selected = true;
                        lookEvaluationState.setText("?????????");
                        score = 1;
                    } else if (position == 1) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = false;
                        }
                        avatarBeanList.get(position).is_selected = true;
                        avatarBeanList.get(position - 1).is_selected = true;
                        lookEvaluationState.setText("???????????????");
                        score = 2;
                    } else if (position == 2) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = false;
                        }
                        avatarBeanList.get(position).is_selected = true;
                        avatarBeanList.get(position - 1).is_selected = true;
                        avatarBeanList.get(position - 2).is_selected = true;
                        lookEvaluationState.setText("????????????");
                        score = 3;
                    } else if (position == 3) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = false;
                        }
                        avatarBeanList.get(position).is_selected = true;
                        avatarBeanList.get(position - 1).is_selected = true;
                        avatarBeanList.get(position - 2).is_selected = true;
                        avatarBeanList.get(position - 3).is_selected = true;
                        lookEvaluationState.setText("??????");
                        score = 4;
                    } else if (position == 4) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = true;
                        }
                        lookEvaluationState.setText("????????????");
                        score = 5;
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    //????????????
                }
            }
        });
    }




    private void request() {
        //??????????????????
        AddEvaluateRequest addEvaluateRequest = new AddEvaluateRequest();
        //?????????
        addEvaluateRequest.setServiceNumber(mIntentBean.serviceNumber);
        //??????????????? mhsp_business_application???id
        addEvaluateRequest.setBusinessId(mIntentBean.businessId);
        //?????????????????????
        addEvaluateRequest.setReceiverServiceName(mIntentBean.userName);
        //???????????????user-id
        addEvaluateRequest.setReceiverUserId(Integer.parseInt(mIntentBean.userId));
        //?????????????????????
//        addEvaluateRequest.setReceiverServiceAccount();
        //?????????user-id
        addEvaluateRequest.setServiceUserId(mIntentBean.id);
//        addEvaluateRequest.setServiceUserId(12375);
        //???????????????
        addEvaluateRequest.setServiceName(mIntentBean.name);
        //???????????????
        addEvaluateRequest.setServiceAccount("");

        //1??????2??????3??????
        addEvaluateRequest.setServiceType(evaluationType);
        //???????????????1??????2??????3??????4??????5??????
        addEvaluateRequest.setEvaluateSatisfaction(score);
        //????????????
        addEvaluateRequest.setEvaluateContent(lookEvaluationEt.getText().toString().trim());
//        //????????????
//        String currentTime = CommonUtils.getCurrentTime();
//        addEvaluateRequest.setStartTime(currentTime);


        mPresenter.getAddEvaluate(addEvaluateRequest, this);
    }


    public static class LookEvaluationIntentBean implements Serializable {
        //?????????
        private String serviceNumber;
        //??????id  ??????
        private int businessId;
        //??????id
        private int id;
        //??????name
        public String name = "";
        //????????????
        public String imgUrl;
        //????????????  //????????????     4????????????  5??????  6??????
        public int selecTypeName;
        //1 ???????????????   2.????????????  3.????????????????????????????????????????????????????????????????????????
        public String type;
        //????????????
        public String evaluationTime;
        //??????
        public int evaluateSatisfaction;
        //????????????
        public String evaluateContent;
        //?????????id
        public String userId;
        public String userName;


        public LookEvaluationIntentBean(String serviceNumber,int businessId, int id, String name, String imgUrl, int selecTypeName, String type,
                                        String evaluationTime, int evaluateSatisfaction, String evaluateContent, String userId, String userName) {
            this.serviceNumber = serviceNumber;
            this.businessId = businessId;
            this.id = id;
            this.name = name;
            this.imgUrl = imgUrl;
            this.selecTypeName = selecTypeName;
            this.type = type;
            this.evaluationTime = evaluationTime;
            this.evaluateSatisfaction = evaluateSatisfaction;
            this.evaluateContent = evaluateContent;
            this.userId = userId;
            this.userName = userName;

        }

        @Override
        public String toString() {
            return "LookEvaluationIntentBean{" +
                    "serviceNumber='" + serviceNumber + '\'' +
                    ", businessId=" + businessId +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", selecTypeName=" + selecTypeName +
                    ", type='" + type + '\'' +
                    ", evaluationTime='" + evaluationTime + '\'' +
                    ", evaluateSatisfaction=" + evaluateSatisfaction +
                    ", evaluateContent='" + evaluateContent + '\'' +
                    ", userId='" + userId + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        avatarBeanList.clear();
        finish();
    }

}