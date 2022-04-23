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
 * @Version：v1.0
 * @describe： 此页面包含三个类型 1.给医生评价——五星评价
 * 2.全部评价——查看评价详情
 * 3.咨询、诊疗订单详情——查看评价详情
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
    //五星Adapter
    private StarsEvaluationAdapter starsEvaluationAdapter;
    private LookEvaluationIntentBean mIntentBean;
    //登录用户
    private AccountBean accountInfo;
    //打分
    private int score;
    //服务类型
    private int evaluationType;
    private String evaluation_type;
    //当前用户
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
                    ToastUtils.showShort("请打分");
                }
            }
        });

        if (mIntentBean!=null){
            //打分 五星评价
            if (mIntentBean.type.equals("1")) {
                //评价内容
                tvTitle.setText("服务评价");
                tvEvaluate.setText("评价及建议");
                lookEvaluationEt.setVisibility(View.VISIBLE);
                lookEvaluationTv.setVisibility(View.GONE);
                lookEvaluationBtn.setVisibility(View.VISIBLE);
                //0/200
                tvTextSize.setVisibility(View.VISIBLE);
                //如满意，请点亮五星哦
                tvSatisfactionTips.setVisibility(View.VISIBLE);

                //类型转换赋值     4文字咨询  5语音  6视频
                if (mIntentBean.selecTypeName == 4) {
                    evaluationType = 1;
                    evaluation_type = "文字服务";
                } else if (mIntentBean.selecTypeName == 5) {
                    evaluationType = 2;
                    evaluation_type = "语音服务";
                } else if (mIntentBean.selecTypeName == 6) {
                    evaluationType = 3;
                    evaluation_type = "视频服务";
                }
                //评价星星设置
                lookEvaluationFiveStarView.setStar(0);
                setAvatarList();
                initAdapter();
                //头像
                Glide.with(this).load(mIntentBean.imgUrl)
                        .placeholder(R.drawable.base_module_doctor_header)
                        .error(R.drawable.base_module_doctor_header)
                        .into(lookEvaluationDetailHeader);
                //医生name
                lookEvaluationName.setText(mIntentBean.name);
                //当前时间
                String currentTime = CommonUtils.getCurrentTime();
                lookEvaluationTime.setText(currentTime);
                //服务类型
                lookEvaluationType.setText(evaluation_type);
            }
            //评论查看
            else if (mIntentBean.type.equals("2")) {

                //类型  1图文2语音3视频
                if (mIntentBean.selecTypeName == 1) {
                    evaluationType = 1;
                    evaluation_type = "文字服务";
                } else if (mIntentBean.selecTypeName == 2) {
                    evaluationType = 2;
                    evaluation_type = "语音服务";
                } else if (mIntentBean.selecTypeName == 3) {
                    evaluationType = 3;
                    evaluation_type = "视频服务";
                }
                tvTitle.setText("评价详情");
                tvEvaluate.setText("评价");
                //评价内容
                lookEvaluationEt.setVisibility(View.GONE);
                lookEvaluationTv.setVisibility(View.VISIBLE);
                reFiveStars.setVisibility(View.GONE);
                lookEvaluationFiveStarView.setVisibility(View.VISIBLE);
                lookEvaluationBtn.setVisibility(View.GONE);


                //评价星星设置
                int evaluateSatisfaction = mIntentBean.evaluateSatisfaction;
                lookEvaluationFiveStarView.setStar(evaluateSatisfaction);
                if (evaluateSatisfaction == 1) {
                    lookEvaluationState.setText("不满意");
                } else if (evaluateSatisfaction == 2) {
                    lookEvaluationState.setText("有点不满意");
                } else if (evaluateSatisfaction == 3) {
                    lookEvaluationState.setText("比较满意");
                } else if (evaluateSatisfaction == 4) {
                    lookEvaluationState.setText("满意");
                } else if (evaluateSatisfaction == 5) {
                    lookEvaluationState.setText("非常满意");
                }
                //头像
                Glide.with(this).load(mIntentBean.imgUrl)
                        .placeholder(R.drawable.base_module_doctor_header)
                        .error(R.drawable.base_module_doctor_header)
                        .into(lookEvaluationDetailHeader);
                //医生name
                lookEvaluationName.setText(mIntentBean.name);
                //评价时间
                lookEvaluationTime.setText(mIntentBean.evaluationTime);
                //服务类型
                lookEvaluationType.setText(evaluation_type);
                //评价内容
                lookEvaluationTv.setText(mIntentBean.evaluateContent);
            }
            //订单详情——评论查看
            else if (mIntentBean.type.equals("3")) {
                if (mIntentBean.serviceNumber.length() > 0) {
                    EvaluateListRequest evaluateListRequest = new EvaluateListRequest();
                    //evaluateListRequest.setServiceNumber("4750a2643e6d4b3b8d2f564b40af5beb");
                    evaluateListRequest.setServiceNumber(mIntentBean.serviceNumber);//订单号
                    mPresenter.getEvaluateDetail(evaluateListRequest, this);
                } else {
                    ToastUtils.showLong("暂无评价");
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

            //评价list
            @Override
            public void onEvaluateList(EvaluateListBean bean, ResultType resultType, String errorMsg) {

            }

            //add 评价
            @Override
            public void onAddEvaluate(BaseResponseBean2 bean, ResultType resultType, String errorMsg) {
                if (bean != null) {
                    parseNetAddEvaluate(bean, resultType);
                } else {
                    ToastUtils.showShort(errorMsg);
                }
            }

            //评价详情
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

    //评价详情
    private void setEvaluateDetail(EvaluateDetailBean bean) {
        EvaluateDetailBean.ResultDTO result = bean.getResult();
        //类型  1图文2语音3视频
        if (result.getServiceType() == 1) {
            evaluation_type = "文字服务";
        } else if (result.getServiceType() == 2) {
            evaluation_type = "语音服务";
        } else if (result.getServiceType() == 3) {
            evaluation_type = "视频服务";
        }
        tvTitle.setText("评价详情");
        tvEvaluate.setText("评价");
        //评价内容
        lookEvaluationEt.setVisibility(View.GONE);
        lookEvaluationTv.setVisibility(View.VISIBLE);
        reFiveStars.setVisibility(View.GONE);
        lookEvaluationFiveStarView.setVisibility(View.VISIBLE);
        lookEvaluationBtn.setVisibility(View.GONE);
        //评价星星设置
        int evaluateSatisfaction = result.getEvaluateSatisfaction();
        lookEvaluationFiveStarView.setStar(evaluateSatisfaction);
        if (evaluateSatisfaction == 1) {
            lookEvaluationState.setText("不满意");
        } else if (evaluateSatisfaction == 2) {
            lookEvaluationState.setText("有点不满意");
        } else if (evaluateSatisfaction == 3) {
            lookEvaluationState.setText("比较满意");
        } else if (evaluateSatisfaction == 4) {
            lookEvaluationState.setText("满意");
        } else if (evaluateSatisfaction == 5) {
            lookEvaluationState.setText("非常满意");
        }
        //头像
        Glide.with(this).load(mIntentBean.imgUrl)
                .placeholder(R.drawable.base_module_doctor_header)
                .error(R.drawable.base_module_doctor_header)
                .into(lookEvaluationDetailHeader);
        //医生name
        lookEvaluationName.setText(result.getServiceName());
        //评价时间
        lookEvaluationTime.setText(result.getCreatetime());
        //服务类型
        lookEvaluationType.setText(evaluation_type);
        //评价内容
        lookEvaluationTv.setText(result.getEvaluateContent());
    }

    //add 评价
    private void parseNetAddEvaluate(BaseResponseBean2 bean, IBaseView.ResultType resultType) {
        switch (resultType) {
            case NET_ERROR:
                //网络异常等，也就是根本没跟自己服务器正常交互
                showError(getString(R.string.base_module_net_error));
                ToastUtils.showLong(getString(R.string.base_module_net_error));
                break;
            case SERVER_ERROR:
                //也就是自己服务器返回的code值不对
                showError(getString(R.string.base_module_data_load_error));
                ToastUtils.showLong(getString(R.string.base_module_data_load_error));
                break;
            case SERVER_NORMAL_DATANO:
            case SERVER_NORMAL_DATAYES:
                //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
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


    //头像List
    private static List<AvatarBean> avatarBeanList = new ArrayList<>();

    private void setAvatarList() {
        if (avatarBeanList.size() == 0) {
            avatarBeanList.add(new AvatarBean("星星", 1, 0, false));
            avatarBeanList.add(new AvatarBean("星星", 2, 5, false));
            avatarBeanList.add(new AvatarBean("星星", 3, 8, false));
            avatarBeanList.add(new AvatarBean("星星", 4, 5, false));
            avatarBeanList.add(new AvatarBean("星星", 5, 5, false));

        }
    }

    //不满意
    //有点不满
    //比较满意
    //满意
    //非常满意
    //评价LIst   初始化 Adapter
    private void initAdapter() {
        reFiveStars.setLayoutManager(new LinearLayoutManager(LookEvaluationActivity.this, LinearLayoutManager.HORIZONTAL, false));
        //reCommentList.addItemDecoration(new SpacesItemDecoration(20));
        starsEvaluationAdapter = new StarsEvaluationAdapter(LookEvaluationActivity.this, avatarBeanList);
        reFiveStars.setAdapter(starsEvaluationAdapter);
        starsEvaluationAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

                //服务评价
                if (mIntentBean.type.equals("1")) {
                    if (position == 0) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = false;
                        }
                        avatarBeanList.get(position).is_selected = true;
                        lookEvaluationState.setText("不满意");
                        score = 1;
                    } else if (position == 1) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = false;
                        }
                        avatarBeanList.get(position).is_selected = true;
                        avatarBeanList.get(position - 1).is_selected = true;
                        lookEvaluationState.setText("有点不满意");
                        score = 2;
                    } else if (position == 2) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = false;
                        }
                        avatarBeanList.get(position).is_selected = true;
                        avatarBeanList.get(position - 1).is_selected = true;
                        avatarBeanList.get(position - 2).is_selected = true;
                        lookEvaluationState.setText("比较满意");
                        score = 3;
                    } else if (position == 3) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = false;
                        }
                        avatarBeanList.get(position).is_selected = true;
                        avatarBeanList.get(position - 1).is_selected = true;
                        avatarBeanList.get(position - 2).is_selected = true;
                        avatarBeanList.get(position - 3).is_selected = true;
                        lookEvaluationState.setText("满意");
                        score = 4;
                    } else if (position == 4) {
                        for (int i = 0; i < avatarBeanList.size(); i++) {
                            avatarBeanList.get(i).is_selected = true;
                        }
                        lookEvaluationState.setText("非常满意");
                        score = 5;
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    //查看评价
                }
            }
        });
    }




    private void request() {
        //添加评价接口
        AddEvaluateRequest addEvaluateRequest = new AddEvaluateRequest();
        //订单号
        addEvaluateRequest.setServiceNumber(mIntentBean.serviceNumber);
        //关联业务表 mhsp_business_application表id
        addEvaluateRequest.setBusinessId(mIntentBean.businessId);
        //接受服务方姓名
        addEvaluateRequest.setReceiverServiceName(mIntentBean.userName);
        //接受服务方user-id
        addEvaluateRequest.setReceiverUserId(Integer.parseInt(mIntentBean.userId));
        //接受服务方账号
//        addEvaluateRequest.setReceiverServiceAccount();
        //服务方user-id
        addEvaluateRequest.setServiceUserId(mIntentBean.id);
//        addEvaluateRequest.setServiceUserId(12375);
        //服务方姓名
        addEvaluateRequest.setServiceName(mIntentBean.name);
        //服务方账号
        addEvaluateRequest.setServiceAccount("");

        //1图文2语音3视频
        addEvaluateRequest.setServiceType(evaluationType);
        //评价满意度1一星2二星3三星4四星5五星
        addEvaluateRequest.setEvaluateSatisfaction(score);
        //评价内容
        addEvaluateRequest.setEvaluateContent(lookEvaluationEt.getText().toString().trim());
//        //评价时间
//        String currentTime = CommonUtils.getCurrentTime();
//        addEvaluateRequest.setStartTime(currentTime);


        mPresenter.getAddEvaluate(addEvaluateRequest, this);
    }


    public static class LookEvaluationIntentBean implements Serializable {
        //订单号
        private String serviceNumber;
        //业务id  必填
        private int businessId;
        //医生id
        private int id;
        //医生name
        public String name = "";
        //医生头像
        public String imgUrl;
        //咨询类型  //预约类型     4文字咨询  5语音  6视频
        public int selecTypeName;
        //1 给医生评价   2.查看评价  3.订单详情————查看评价————在此页面请求接口
        public String type;
        //评价时间
        public String evaluationTime;
        //星级
        public int evaluateSatisfaction;
        //评价内容
        public String evaluateContent;
        //评价人id
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