package com.visionvera.psychologist.c.module.counselling.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.imsdk.v2.V2TIMFriendAddApplication;
import com.tencent.imsdk.v2.V2TIMFriendOperationResult;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.liteav.basic.UserModel;
import com.tencent.liteav.trtccalling.model.TRTCCalling;
import com.tencent.liteav.trtccalling.model.TUICalling;
import com.tencent.liteav.trtccalling.model.impl.TUICallingManager;
import com.tencent.liteav.trtccalling.model.impl.base.CallingInfoManager;
import com.tencent.liteav.trtccalling.model.util.TUICallingConstants;
import com.tencent.qcloud.tuicore.net.MessageEvent;
import com.tencent.qcloud.tuikit.tuicontact.util.ContactUtils;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.util.SpUtil;
import com.visionvera.library.util.TimeFormatUtils;
import com.visionvera.library.widget.dialog.CounselorTypePopup;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.account_module.R2;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.adapter.ImgAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.CounselorDetailBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderCancelRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultDetailBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultDetailRequest;
import com.visionvera.psychologist.c.module.counselling.bean.ReportInformationBeanRequest;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.OrderConsultDetailPresenter;
import com.visionvera.psychologist.c.module.counselling.view.BottomCheckOrderPayTypePopup;
import com.visionvera.psychologist.c.module.counselling.view.CommonCenterTipsPopup;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.FeedBackImgBean;
import com.visionvera.psychologist.c.utils.CommonUtils;
import com.visionvera.psychologist.c.utils.pay.PayUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.visionvera.library.base.Constant.RequestReturnCode.OrderConsultItemFragment_To_OrderConsultApplyFragment_Code;
import static com.visionvera.psychologist.c.module.usercenter.bean.FeedBackImgBean.IMG;


/**
 * @Classname:OrderConsultDetailActivity
 * @author:haohuizhao
 * @Date:2021/11/17 15:37
 * @Version：v1.0
 * @describe： 描述:咨询/诊疗详情的页面  12个页面共享。因为有大部分功能相同，所有公用一个页面。
 * 二期
 * <p>
 * 腾讯sdk
 * 文字聊天 TUIC2CChatActivity
 * 语音 TUICallAudioView
 * 视频 TUICallVideoView
 * 信令监听器被邀请人  语视、频接听监听类  TRTCCallingImpl
 */


public class OrderConsultDetailActivity extends BaseMVPLoadActivity<OrderConsultContract.OrderConsultDetail.OrderConsultDetailView, OrderConsultDetailPresenter> {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;


    //挂断按钮
    @BindView(R2.id.call_bg)
    RelativeLayout call_bg;

    //顶部标题
    TextView tv_title;
    //刷新布局
    SmartRefreshLayout smartRefreshLayout;
    //头像
    CircleImageView imgHeader;
    //咨询师、医生姓名
    TextView tvName;
    //一级咨询师
    TextView tvTitle;
    //擅长类型
    TextView tvTag;
    //预约咨询、诊疗类型     4文字咨询  5语音  6视频咨询
    TextView tvType;
    //咨询时间
    TextView tvTime;
    //付款金额
    TextView tvPrice;
    //订单编号
    TextView tvOrderNumber;
    //订单生成时间
    TextView tvOrderCreateTime;
    //附件图片
    RecyclerView recyclerViewFujian;
    //备注
    TextView tvBeizhu;
    //订单状态
    TextView tvStatus;
    //状态描述
    TextView tvRejectReason;
    //状态切图
    AppCompatImageView ivStatus;
    //咨询机构
    TextView tvAdvisoryBody;
    //问题类型
    TextView tvProblemType;
    //底部左右按钮布局
    RelativeLayout rlBottom;
    //底部左侧按钮  取消订单
    TextView tvCancel;
    //底部右侧按钮  再次预约
    TextView tvOperation;


    //附件图片适配器
    private ImgAdapter imgAdapter;
    private List<FeedBackImgBean> picPathList = new ArrayList<>();
    ////预约咨询、诊疗类型pop    4文字咨询  5语音  6视频咨询
    private BasePopupView typePopup;
    //支付pop
    private BottomCheckOrderPayTypePopup mPayTypePopup = null;
    private int mPayTypeCheckPosition = 0;
    PayUtil payUtil;
    //确认、取消pop
    private CommonCenterTipsPopup centerTipsPopup;
    private CommonCenterTipsPopup confirmOrderFinishPopup;
    //开始咨询 开始诊疗变量
    private String startContent;

    private CountDownTimer countDownTimerStart;//开始进入聊天的计时器
    private CountDownTimer countDownTimerEnd;//关闭进入聊天的计时器
    //待付款状态  验证码相关的观察者流
    private Disposable d;
    int liveMinute = 15;
    private Observable<Long> mObservable;


    //腾讯视频语音呼叫   表示当前搜索的usermodel
    private UserModel mSearchModel;

    //呼叫类型       callType = TRTCCalling.TYPE_VIDEO_CALL;   callType = TRTCCalling.TYPE_AUDIO_CALL;
    private int callType;
    //预约咨询、诊疗类型     4文字咨询  5语音  6视频咨询
    private int mType;
    private int diagnosisMode;
    //订单详情bean
    private OrderConsultDetailBean.ResultBean mResultBean;
    //intent
    private OrderConsultDetailIntentBean mIntentBean = new OrderConsultDetailIntentBean(0, "", "apply", 0, "", 0, "");

    //咨询师详情Bean
    private CounselorDetailBean counselorDetailBean = new CounselorDetailBean();
    //医生详情Bean
    private DoctorDetailBean doctorDetailBean = new DoctorDetailBean();
    //userId前缀
    private String userIdPrefix;
    private String userId;
    private String userName;
    //付款类型   1.受邀列表待受理
    private String paymentType;
    private ImageView iv_back;


    //跳转传递对象    mIntentBean
    public static void startActivityForResult(Context context, OrderConsultDetailIntentBean intentBean) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(new Intent(context,
                            OrderConsultDetailActivity.class)
                            .putExtra(Constant.IntentKey.IntentBean, intentBean),
                    1);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_order_consult_detail;
    }

    @Override
    protected void doYourself() {
        ARouter.getInstance().inject(this);
        initView();
        //获取intent
        getIntentData();
        //初始化请求接口
        initRequest();
        payUtil = new PayUtil(this);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.normal_view);
        imgHeader = (CircleImageView) findViewById(R.id.evaluation_module_counselor_detail_header);
        tvName = (TextView) findViewById(R.id.tvName);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTag = (TextView) findViewById(R.id.tvTag);
        tvType = (TextView) findViewById(R.id.tvType);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvOrderNumber = (TextView) findViewById(R.id.tvOrderNumber);
        tvOrderCreateTime = (TextView) findViewById(R.id.tvOrderCreateTime);
        recyclerViewFujian = (RecyclerView) findViewById(R.id.recyclerViewFujian);
        tvBeizhu = (TextView) findViewById(R.id.tvBeizhu);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvRejectReason = (TextView) findViewById(R.id.tvRejectReason);
        ivStatus = (AppCompatImageView) findViewById(R.id.ivStatus);
        tvAdvisoryBody = (TextView) findViewById(R.id.tv_advisory_body);
        tvProblemType = (TextView) findViewById(R.id.tv_problem_type);
        rlBottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        tvCancel = (TextView) findViewById(R.id.tvCancel);
        tvOperation = (TextView) findViewById(R.id.tv_operation);
        iv_back = (ImageView) findViewById(R.id.iv_back);


        tv_title.setText("预约详情");
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initRequest();
            }
        });
        //获取与userId前缀，与腾讯服务器交互使用
        if (accountService != null && accountService.getGetAccountInfo() != null) {
            userIdPrefix = accountService.getGetAccountInfo().userIdPrefix;
            userId = accountService.getGetAccountInfo().userId;
            userName = accountService.getGetAccountInfo().userName;
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIntentBean.payment_type.equals("0")) {
                    //0元跳过支付进入的  返回当前咨询师页面
                    activityStackUtil.clearTopOfMy(activity, CounselorDetailActivity.class);
                } else {
                    finish();
                }
            }
        });
        tvOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //底部的操作按钮.目前最多两个.状态不同,点击作用不同
                //右边的按钮(或者就一个按钮) 一般是同意,付款等有效操作
                if (tvOperation.getText().equals("取消订单")) {
                    cancelOrder();
                } else if (tvOperation.getText().equals("再次预约")) {
                    CounselorDetailActivity.CounselorDetailIntentBean intentBean
                            = new CounselorDetailActivity.CounselorDetailIntentBean(
                            0,
                            mResultBean.getDoctorId());
                    CounselorDetailActivity.startActivity(OrderConsultDetailActivity.this, intentBean);
                    finish();
                } else if (tvOperation.getText().equals("立即支付")) {
                    showPayTypePop(OrderConsultDetailActivity.this, mPayTypeCheckPosition);
                } else if (tvOperation.getText().equals("确认服务完成")) {
                    confirmOrderFinish();
                } else if (tvOperation.getText().equals("开始咨询")) {
                    if (mResultBean != null) {
                        long startTime = TimeFormatUtils.strToDateLong(mResultBean.getStartTime()).getTime();
                        long endTime = TimeFormatUtils.strToDateLong(mResultBean.getEndTime()).getTime();
                        long currentTime = Calendar.getInstance().getTime().getTime();
                        if (currentTime >= startTime && currentTime >= endTime) {
                            //后
                            ToastUtils.showShort("已超过咨询时间");
                        } else if (currentTime >= startTime && currentTime <= endTime) {
                            //中
                            //预约类型     4文字咨 5语音  6视频
                            if (mResultBean.getDiagnosisMode() == 4) {
                                //文字
                                //进入腾讯聊天页面
                                if (checkCallUserId(mIntentBean.doctorId)) {
                                    goTextChat();
                                }

                            } else if (mResultBean.getDiagnosisMode() == 5) {
                                //语音
                                if (checkCallUserId(mIntentBean.doctorId)) {
                                    goVoiceCall();
                                }

                            } else if (mResultBean.getDiagnosisMode() == 6) {
                                //视频
                                if (checkCallUserId(mIntentBean.doctorId)) {
                                    goVideoCall();
                                }
                            }

                        } else if (currentTime <= startTime && currentTime <= endTime) {
                            //前
                            ToastUtils.showShort("还未到咨询时间");
                        }
                    }

                } else if (tvOperation.getText().equals("接受并付款")) {
                    paymentType = "1";
                    double consultingFee = Double.parseDouble(mResultBean.getConsultingFee());
                    if (consultingFee == 0) {
                        //金额0时直接 请求接受接口
                        net_acceptOrder();
                    } else {
                        //付款
                        showPayTypePop(OrderConsultDetailActivity.this, mPayTypeCheckPosition);
                    }

                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //底部的操作按钮.目前最多两个.状态不同,点击作用不同
                //左边的按钮 一般是取消拒绝等操作
                if (tvCancel.getText().equals("取消订单")) {
                    cancelOrder();
                } else if (tvCancel.getText().equals("确认服务完成")) {
                    confirmOrderFinish();
                } else if (tvCancel.getText().equals("评价")) {
                    //打分 五星评价
                    if (mIntentBean.fromType.equals("consulting")) {//咨询订单
                        Intent intent = new Intent(OrderConsultDetailActivity.this, LookEvaluationActivity.class);
                        LookEvaluationActivity.LookEvaluationIntentBean intentBean = new LookEvaluationActivity.LookEvaluationIntentBean(
                                mResultBean.getAppNum(), mResultBean.getBusinessId(), mResultBean.getDoctorId(), counselorDetailBean.getResult().getUsername(), counselorDetailBean.getResult().getPhotoUrl(),
                                mResultBean.getDiagnosisMode(), "1", "", 1, "", userId, mResultBean.getNickName());
                        intent.putExtra("intentBean", intentBean);
                        startActivityForResult(intent, 1);
//                    LookEvaluationActivity.startActivity(this, intentBean);
                    } else if (mIntentBean.fromType.equals("treatment")) {//诊疗订单
                        Intent intent = new Intent(OrderConsultDetailActivity.this, LookEvaluationActivity.class);
                        LookEvaluationActivity.LookEvaluationIntentBean intentBean = new LookEvaluationActivity.LookEvaluationIntentBean(
                                mResultBean.getAppNum(), mResultBean.getBusinessId(), mResultBean.getDoctorId(), doctorDetailBean.getResult().getUsername(), doctorDetailBean.getResult().getImageUrl(),
                                mResultBean.getDiagnosisMode(), "1", "", 1, "", userId, mResultBean.getNickName());
                        intent.putExtra("intentBean", intentBean);
                        startActivityForResult(intent, 1);

//                    LookEvaluationActivity.startActivity(this, intentBean);
                    }
                } else if (tvCancel.getText().equals("查看评价")) {
                    //评论查看
                    //打分 五星评价
                    if (mIntentBean.fromType.equals("consulting")) {
                        LookEvaluationActivity.LookEvaluationIntentBean intentBean = new LookEvaluationActivity.LookEvaluationIntentBean(
                                mResultBean.getAppNum(), mResultBean.getBusinessId(), mResultBean.getDoctorId(), counselorDetailBean.getResult().getUsername(), counselorDetailBean.getResult().getPhotoUrl(),
                                mResultBean.getDiagnosisMode(), "3", "", 1, "", "", "");
                        LookEvaluationActivity.startActivity(OrderConsultDetailActivity.this, intentBean);
                    } else if (mIntentBean.fromType.equals("treatment")) {
                        LookEvaluationActivity.LookEvaluationIntentBean intentBean = new LookEvaluationActivity.LookEvaluationIntentBean(
                                mResultBean.getAppNum(), mResultBean.getBusinessId(), mResultBean.getDoctorId(), doctorDetailBean.getResult().getUsername(), doctorDetailBean.getResult().getImageUrl(),
                                mResultBean.getDiagnosisMode(), "3", "", 1, "", "", "");
                        intentBean.name = tvName.getText().toString().trim();
                        LookEvaluationActivity.startActivity(OrderConsultDetailActivity.this, intentBean);
                    }
                } else if (tvCancel.getText().equals("驳回")) {
                    net_rejectOrder();
                }
            }
        });

    }

    private void initRequest() {
        showLoading();
        //来自哪个页面类型区分       咨询  consulting   诊疗  treatment
        if (mIntentBean.fromType.equals("consulting")) {
            startContent = "开始咨询";
            //查询咨询师详情接口
            mPresenter.onOrderDoctorDetail(mIntentBean.doctorId, this);

        } else if (mIntentBean.fromType.equals("treatment")) {
            startContent = "开始诊疗";
            DoctorDetailRequest doctorDetailRequest = new DoctorDetailRequest();
//            doctorDetailRequest.setUserId(1283);
            doctorDetailRequest.setUserId(mIntentBean.doctorId);
            //查询医生详情接口
            mPresenter.onDoctorDetailByUserId(doctorDetailRequest, this);
        }


        //预约订单详情接口
        OrderConsultDetailRequest request = new OrderConsultDetailRequest(mIntentBean.id);
        mPresenter.getOrderConsultDetail(request, this);
    }

    private void getIntentData() {
        //获取参数
        OrderConsultDetailIntentBean bean = (OrderConsultDetailIntentBean) getIntent().getSerializableExtra(Constant.IntentKey.IntentBean);
        if (bean != null) {
            mIntentBean = bean;
        }
        Logger.i(mIntentBean.toString());
    }


    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.OrderConsultDetail.OrderConsultDetailView() {
            //查询咨询师详情
            @Override
            public void onOrderDoctorDetail(CounselorDetailBean bean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        //咨询师、详情
                        showConsultantDetail(bean);
                        counselorDetailBean = bean;
                        break;
                    default:
                }
            }

            //查询医生详情
            @Override
            public void onDoctorDetail(DoctorDetailBean bean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        showDoctorDetail(bean);
                        doctorDetailBean = bean;
                        break;
                    default:
                }
            }

            //二期
            //心理咨询 预约相关-app获取预约详情接口
            @Override
            public void onOrderConsultDetail(OrderConsultDetailBean bean, ResultType resultType, String errorMsg) {
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.finishLoadMore();
                }
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
                        showData(bean.getResult());
                        break;
                    default:
                }
            }


            //二期
            //咨询/诊疗上报操作信息
            @Override
            public void onReportInformation(BaseResponseBean2 response, ResultType resultType, String errorMsg, String state) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showLong(errorMsg);
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showLong(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        if (state.equals("customerCall")) {
                            ToastUtils.showShort("呼叫成功");
                        } else if (state.equals("serverAnswer")) {
                            ToastUtils.showShort("接听成功");
                        } else if (state.equals("customerConfirm")) {
                            ToastUtils.showShort("确认成功");
                            EventBus.getDefault().post(new MessageEvent("refresh"));
                            finish();
                        }
                        break;
                }
            }

            //二期
            //取消咨询/诊疗申请
            //接口说明：已经付款的订单1天之内无法取消，必传参数不为空校验，订单已完成、已取消、已作废取消检验。
            @Override
            public void onOrderCancel(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        ToastUtils.showShort("取消预约成功");
                        EventBus.getDefault().post(new MessageEvent("refresh"));
                        finish();
                        break;
                    default:
                }
            }

            //二期
            //驳回咨询/诊疗申请
            @Override
            public void onOrderReject(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        ToastUtils.showShort("驳回成功");
                        EventBus.getDefault().post(new MessageEvent("refresh"));
                        finish();
                        break;
                    default:
                }
            }

            //二期
            //受理咨询/诊疗申请
            @Override
            public void onOrderAccept(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        ToastUtils.showShort("受理成功");
                        EventBus.getDefault().post(new MessageEvent("refresh"));
                        finish();
                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new OrderConsultDetailPresenter(this, mView);
    }

    //咨询师详情
    private void showConsultantDetail(CounselorDetailBean bean) {
        CounselorDetailBean.ResultDTO resultBean = bean.getResult();

        //咨询师
        tvName.setText(resultBean.getUsername());
        //头像
        Glide.with(this).load(resultBean.getPhotoUrl())
                .placeholder(R.drawable.base_module_doctor_header)
                .error(R.drawable.base_module_doctor_header)
                .into(imgHeader);
        //一级咨询师
        tvTitle.setText(resultBean.getTitleName());
        //机构
        tvAdvisoryBody.setText(resultBean.getHospitalName());
        //擅长
        StringBuilder allLable = new StringBuilder();
        for (String lable : resultBean.getLableName()) {
            allLable.append("#" + lable + "  ");
        }
        tvTag.setText(allLable.toString());
    }

    //医生详情
    private void showDoctorDetail(DoctorDetailBean bean) {
        DoctorDetailBean.ResultBean result = bean.getResult();

        //医生
        tvName.setText(result.getUsername());
        //头像
        Glide.with(this).load(bean.getResult().getImageUrl())
                .placeholder(R.drawable.base_module_doctor_header)
                .error(R.drawable.base_module_doctor_header)
                .into(imgHeader);
        //一级
        tvTitle.setVisibility(View.GONE);
        //机构
        tvAdvisoryBody.setText(bean.getResult().getHospitalName());
        //擅长
        StringBuilder allLable = new StringBuilder();

        for (int i = 0; i < bean.getResult().getLabels().size(); i++) {
            String lableName = bean.getResult().getLabels().get(i).getLableName();
            allLable.append("#" + lableName + "  ");
        }

        tvTag.setText(allLable.toString());
    }


    //订单详情数据
    private void showData(OrderConsultDetailBean.ResultBean bean) {
        if (bean == null) {
            return;
        }
        //可以模拟数据
//        bean.setAppStatus(14);
//        bean.setStartTime("2020-10-27 13:30:00");
//        bean.setEndTime("2020-10-27 14:00:00");
        mResultBean = bean;

        //预约咨询类型     4文字咨询  5语音  6视频咨询
        if (bean.getDiagnosisMode() == 4) {
            tvType.setText("文字咨询");
        } else if (bean.getDiagnosisMode() == 5) {
            tvType.setText("语音咨询");
        } else if (bean.getDiagnosisMode() == 6) {
            tvType.setText("视频咨询");
        }
        //咨询时间
        if (!TextUtils.isEmpty(bean.getStartTime()) && !TextUtils.isEmpty(bean.getEndTime())) {
            String formatTime = TimeFormatUtils.convertDuringTime(bean.getStartTime(), bean.getEndTime());
            tvTime.setText(formatTime);
        }
        //问题类型
        tvProblemType.setText(bean.getPatComplaints());
        //附件图片
        recyclerViewFujian.setLayoutManager(new GridLayoutManager(this, 4));
        imgAdapter = new ImgAdapter(picPathList);
        imgAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        recyclerViewFujian.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView imageView = view.findViewById(R.id.iv);
                ArrayList<Object> list = new ArrayList<>();
                for (int i = 0; i < picPathList.size(); i++) {
                    if (picPathList.get(i).getItemType() != FeedBackImgBean.CAMERA) {
                        list.add(picPathList.get(i).getPicPath());
                    }
                }
                //查看照片
                // 多图片场景
                //srcView参数表示你点击的那个ImageView，动画从它开始，结束时回到它的位置。
                //注意：如果你自己的ImageView的scaleType是centerCrop类型的，你加载图片需要指定Original_Size，禁止Glide裁剪图片。

                new XPopup.Builder(activity).asImageViewer(imageView, position, list, false, false, -1, -1, -1, false, new OnSrcViewUpdateListener() {
                    @Override
                    public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
                        // 作用是当Pager切换了图片，需要更新源View
                        popupView.updateSrcView((ImageView) recyclerViewFujian.getChildAt(position).findViewById(R.id.iv));
                    }
                }, new ImageLoader())
                        .show();
            }
        });

        //备注
        if (!TextUtils.isEmpty(bean.getComments())) {
            tvBeizhu.setText(bean.getComments() + "");
        } else {
            tvBeizhu.setText("暂无");
        }
        //付款金额
        tvPrice.setText("¥" + formatDouble(Double.parseDouble(mResultBean.getConsultingFee())));
        //订单编号
        tvOrderNumber.setText(bean.getAppNum() + "");
        //订单生成时间
        tvOrderCreateTime.setText(bean.getCreatetime() + "");
        if (bean.getUploadUrl() != null) {
            picPathList.clear();
            for (OrderConsultDetailBean.ResultBean.UploadUrlBean uploadUrlBean : bean.getUploadUrl()) {
                picPathList.add(new FeedBackImgBean(IMG, uploadUrlBean.getUrl()));
            }
        }


        String orderStatus = "";
        /**
         * 订单状态（18 ：待付款    14：待接诊    15：已完成    16：已作废    8：已驳回    7：已取消    2:待受理    100：已关闭（取消加作废两种状态）
         * （14：待接诊 15：已完成 16：已作废 8：已驳回 7：已取消 2:待受理 18:待付款）
         */
        //顶部 订单状态图片设置
        chanStatusImg(bean);
        if (TextUtils.equals(mIntentBean.sourceType, Constant.IntentKey.apply)) {
            //申请列表详情 也就是患者申请向医生咨询
            switch (bean.getAppStatus()) {
                case 2://2:待受理
                    orderStatus = getString(R.string.evaluation_module_to_be_accept);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvOperation.setText("取消订单");
                    break;
                case 14://14：待接诊
                    orderStatus = getString(R.string.evaluation_module_to_be_received);
                    rlBottom.setVisibility(View.VISIBLE);
                    try {
                        //判断在预约时间24小时外  内
                        //服务时间段60分钟内
                        //超出服务时间（60分内）
                        toBeConsulted_24(bean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //预约类型     4文字咨询  5语音  6视频
//                    diagnosisMode = bean.getDiagnosisMode();
//                    if (bean.getDiagnosisMode() == 4) {
//                        //文字咨询
//                        CuountDownUtil2(bean);
//                    } else if (bean.getDiagnosisMode() == 5) {
//                        //语音咨询
//                    } else if (bean.getDiagnosisMode() == 6) {
//                        //视频咨询
//                    }
                    break;
                case 7://7：已取消
                    orderStatus = getString(R.string.evaluation_module_already_cancel);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvOperation.setText("再次预约");
                    break;
                case 15://15：已完成
                    orderStatus = getString(R.string.evaluation_module_already_complete);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.VISIBLE);
                    tvOperation.setVisibility(View.VISIBLE);


                    //新增逻辑  已完成显示评价、查看评价
                    //此处需要加时候评价过字段判断      "evaluateId":0,         //评价id(0 表示尚未评价)
                    if ((mResultBean.getEvaluateId() == 0)) {
                        tvCancel.setText("评价");
                        tvCancel.setTextColor(Color.WHITE);
                        tvCancel.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
                    } else {
                        tvCancel.setText("查看评价");
                        tvCancel.setTextColor(Color.WHITE);
                        tvCancel.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
                    }
                    tvOperation.setText("再次预约");
                    tvOperation.setTextColor(Color.WHITE);
                    tvOperation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
                    break;
                case 8://8：已驳回
                    orderStatus = getString(R.string.evaluation_module_already_reject);
                    tvRejectReason.setVisibility(View.VISIBLE);
                    tvRejectReason.setText(bean.getExplain() + "");
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvOperation.setText("再次预约");
                    break;
                case 16://16：已作废
                    orderStatus = getString(R.string.evaluation_module_already_abolist);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvOperation.setText("再次预约");
                    break;
                case 18:
                    //待付款
                    orderStatus = getString(R.string.evaluation_module_daifukuan);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.VISIBLE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvCancel.setText("取消订单");
                    tvOperation.setText("立即支付");
                    countDownDaifukuanTime2(bean);
                    break;
            }
        } else {
            //受邀列表详情  也就是医生想患者提出复诊
            switch (bean.getAppStatus()) {
                case 2:
                    //待受理
                    orderStatus = getString(R.string.evaluation_module_to_be_accept);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.VISIBLE);
                    tvCancel.setText("驳回");
                    tvOperation.setVisibility(View.VISIBLE);
                    tvOperation.setText("接受并付款");
                    break;
                case 14:
                    //待接诊
                    orderStatus = getString(R.string.evaluation_module_to_be_received);
                    rlBottom.setVisibility(View.VISIBLE);
                    try {
                        //判断在预约时间24小时外  内
                        //服务时间段60分钟内
                        //超出服务时间（60分内）
                        toBeConsulted_24(bean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //预约类型     4文字咨询  5语音  6视频
                    diagnosisMode = bean.getDiagnosisMode();
//                    if (bean.getDiagnosisMode() == 4) {
//                        //文字咨询
//                        CuountDownUtil2(bean);
//                    } else if (bean.getDiagnosisMode() == 5) {
//                        //语音咨询
//                    } else if (bean.getDiagnosisMode() == 6) {
//                        //视频咨询
//                    }
                    break;
                case 7:
                    //已取消
                    orderStatus = getString(R.string.evaluation_module_already_cancel);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvOperation.setText("再次预约");
                    break;
                case 15:
                    //已完成
                    orderStatus = getString(R.string.evaluation_module_already_complete);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.VISIBLE);
                    tvOperation.setVisibility(View.VISIBLE);


                    //新增逻辑  已完成显示评价、查看评价
                    //此处需要加时候评价过字段判断  "evaluateId":0,         //评价id(0 表示尚未评价)
                    if ((mResultBean.getEvaluateId() == 0)) {
                        tvCancel.setText("评价");
                        tvCancel.setTextColor(Color.WHITE);
                        tvCancel.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
                    } else {
                        tvCancel.setText("查看评价");
                        tvCancel.setTextColor(Color.WHITE);
                        tvCancel.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
                    }
                    tvOperation.setText("再次预约");
                    tvOperation.setTextColor(Color.WHITE);
                    tvOperation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
                    break;
                case 8:
                    //已驳回
                    orderStatus = getString(R.string.evaluation_module_already_reject);
                    tvRejectReason.setVisibility(View.VISIBLE);
                    tvRejectReason.setText(bean.getExplain() + "");
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvOperation.setText("再次预约");
                    break;
                case 16:
                    //已作废
                    orderStatus = getString(R.string.evaluation_module_already_abolist);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.GONE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvOperation.setText("再次预约");
                    break;
                case 18:
                    //待付款
                    orderStatus = getString(R.string.evaluation_module_daifukuan);
                    rlBottom.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.VISIBLE);
                    tvOperation.setVisibility(View.VISIBLE);
                    tvCancel.setText("取消订单");
                    tvOperation.setText("立即支付");
                    countDownDaifukuanTime2(bean);
                    break;

            }

        }

    }

    //顶部 订单状态图片设置
    private void chanStatusImg(OrderConsultDetailBean.ResultBean bean) {
        int statusId = bean.getAppStatus();
        String statusName = bean.getStageName();
        //订单状态
        tvStatus.setText(statusName + "");
        //2 待受理 8已驳回 7已取消 14待接诊 15已完成 16已作废 18待付款
        switch (statusId) {
            case 2:
                //待受理
                if (TextUtils.equals(mIntentBean.sourceType, Constant.IntentKey.apply)) {
                    //申请方
                    ivStatus.setImageResource(R.drawable.evaluation_module_status_daishouli_doctor);
                } else {
                    ivStatus.setImageResource(R.drawable.evaluation_module_status_daishouli_patient);
                }
                break;
            case 8:
                //已驳回
                ivStatus.setImageResource(R.drawable.evaluation_module_status_yibohui);
                break;
            case 7:
                //已取消
                ivStatus.setImageResource(R.drawable.evaluation_module_status_yiquxiao);
                break;
            case 14:
                //待接诊
                ivStatus.setImageResource(R.drawable.evaluation_module_status_daijiezhen);
                break;

            case 15:
                //已完成
                ivStatus.setImageResource(R.drawable.evaluation_module_status_yiwancheng);
                break;

            case 16:
                //已作废
                ivStatus.setImageResource(R.drawable.evaluation_module_status_yizuofei);
                break;
            case 18:
                //待付款
                ivStatus.setImageResource(R.drawable.evaluation_module_status_daifukuan);
                break;

        }

    }


    class ImageLoader implements XPopupImageLoader {
        @Override
        public void loadImage(int position, @NonNull Object url, @NonNull ImageView imageView) {
            //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
            Glide.with(imageView).load(url).apply(new RequestOptions().placeholder(R.mipmap.logo).override(Target.SIZE_ORIGINAL)).into(imageView);
        }

        @Override
        public File getImageFile(@NonNull Context context, @NonNull Object uri) {
            try {
                return Glide.with(context).downloadOnly().load(uri).submit().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 待咨询状态 倒计时   24小时
     * 按钮显示逻辑
     * （到达预约服务时间的24小时前与24小时内）
     */
    private void toBeConsulted_24(OrderConsultDetailBean.ResultBean bean) throws Exception {
        //预约咨询时间
        String startTime = bean.getStartTime();//2021-11-19 09:00:00
        //当前时间
        String currentTime = CommonUtils.getCurrentTime();

        //小在前面  大的在后面
        if (jisuan(currentTime, startTime, 24)) {
//            ToastUtils.showShort("24小时内");
            //服务人员已受理，且处于约定时间到达 24小时内
            tvCancel.setVisibility(View.GONE);
            tvOperation.setText("开始咨询");
            tvOperation.setTextColor(Color.WHITE);
            tvOperation.setBackgroundResource(R.drawable.evaluation_module_gray_btn_bg_corner4);
//            tvOperation.setClickable(false);//不可点击

            //判断到达 约定预约咨询开始时间+60分钟以内
            //此一小时内可以进行咨询
            toBeConsulted_60(bean);
            //判断超过了 约定的预约咨询结束时间
            //超过预约的咨询时间段
            toBeConsultedOutside_60(bean);
        } else {
//            ToastUtils.showShort("24小时外");
            //24小时外
            //服务人员已受理，且处于约定时间到达 24小时前
            tvCancel.setText("取消订单");
            tvCancel.setTextColor(Color.WHITE);
            tvCancel.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
            tvOperation.setText("开始咨询");
            tvOperation.setTextColor(Color.WHITE);
            tvOperation.setBackgroundResource(R.drawable.evaluation_module_gray_btn_bg_corner4);
//            tvOperation.setClickable(false);//不可点击
        }
    }

    /**
     * 待咨询状态 倒计时   60分钟内
     * 按钮显示逻辑
     * (在预约服务时间的60分钟内)
     */
    private void toBeConsulted_60(OrderConsultDetailBean.ResultBean bean) throws Exception {
        //预约咨询开始时间
        String startTime = bean.getStartTime();//2019-12-28 08:00:00
        //当前时间
        String currentTime = CommonUtils.getCurrentTime();
//        String currentTime = "2021-11-18 16:10:00";

        //开始时间
        long startTime1 = TimeFormatUtils.strToDateLong(bean.getStartTime()).getTime();
        //当前时间
        long currentTime1 = Calendar.getInstance().getTime().getTime();

        if (currentTime1 > startTime1) {
            if (jisuan(startTime, currentTime, 1)) {

                //"customerAnswer":0,     //用户接听 0|否，1|是
                //"serverAnswer":1,       //服务方接听 0|否，1|是
                //双方有无沟通记录？？？？    如果有一方接听则判断有沟通记录
                if ((mResultBean.getCustomerAnswer() == 1 || mResultBean.getServerAnswer() == 1)) {
                    //服务人员已受理，到达约定时间+60分钟以内,且有双方沟通记录
                    tvCancel.setVisibility(View.VISIBLE);
                    tvCancel.setText("确认服务完成");
                    tvCancel.setTextColor(Color.WHITE);
                    tvCancel.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
                    tvOperation.setText("开始咨询");
                    tvOperation.setTextColor(Color.WHITE);
                    tvOperation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
                } else {
                    //服务人员已受理，到达约定时间+60分钟以内,且无双方沟通记录
                    tvCancel.setVisibility(View.GONE);
                    tvOperation.setText("开始咨询");
                    tvOperation.setTextColor(Color.WHITE);
                    tvOperation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);

                }
            }
        }

    }

    /**
     * 待咨询状态  超出服务时间段
     * 按钮显示逻辑
     * (当前时间超出预约服务时间)
     */
    private void toBeConsultedOutside_60(OrderConsultDetailBean.ResultBean bean) throws Exception {
        //预约咨询结束时间
        long endTime = TimeFormatUtils.strToDateLong(bean.getEndTime()).getTime();
        //当前时间
        long currentTime = Calendar.getInstance().getTime().getTime();
//        long currentTime = TimeFormatUtils.strToDateLong("  2021-11-18 17:10:00").getTime();
        if (currentTime > endTime) {
            //服务人员已受理，到达约定时间+60分钟以外,且有双方沟通记录
            tvCancel.setVisibility(View.VISIBLE);
            tvCancel.setText("确认服务完成");
            tvCancel.setTextColor(Color.WHITE);
            tvCancel.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
            tvOperation.setVisibility(View.GONE);
        }
    }

    //判断是否超过24小时
    public static boolean jisuan(String date1, String date2, int timeType) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        Date start = sdf.parse(date1);
        Date end = sdf.parse(date2);
        long cha = end.getTime() - start.getTime();
        double result = cha * 1.0 / (1000 * 60 * 60);
        if (result <= timeType) {
            //System.out.println("可用");
            return true;
        } else {
            //System.out.println("已过期");
            return false;
        }
    }


    /**
     * 待付款状态 倒计时
     */
    private void countDownDaifukuanTime2(OrderConsultDetailBean.ResultBean bean) {
//        bean.setCreatetime("2020-09-29 9:40:16");
        //订单创建时间  十五分钟后订单自动取消.不可以支付了
        long createTime = TimeFormatUtils.strToDateLong(bean.getCreatetime()).getTime();
        //当前时间
        long currentTime = Calendar.getInstance().getTime().getTime();
        long enableDuration = 15 * 60 * 1000;
        long endTime = createTime + enableDuration;
        if (currentTime >= endTime) {
            rlBottom.setVisibility(View.GONE);
            tvRejectReason.setVisibility(View.VISIBLE);
            tvRejectReason.setText("订单超时未付款,已自动取消");
        } else if (currentTime <= endTime) {
            long duration = endTime - currentTime;
            liveMinute = (int) (duration / 60 / 1000);
            //倒计时的时间的计算
            rlBottom.setVisibility(View.VISIBLE);
            tvCancel.setVisibility(View.VISIBLE);
            tvOperation.setVisibility(View.VISIBLE);
            tvRejectReason.setVisibility(View.VISIBLE);
            tvCancel.setText("取消订单");
            tvOperation.setText("立即支付");
            tvRejectReason.setText("还剩15分钟自动取消");
            mObservable = Observable.interval(0, 1, TimeUnit.MINUTES);
            mObservable.take(liveMinute + 1)//设置重复次数
                    .map(new Function<Long, Long>() {
                        @Override
                        public Long apply(Long aLong) throws Exception {
                            return liveMinute - aLong;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {

                        }
                    }).subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(Disposable d) {
                    OrderConsultDetailActivity.this.d = d;
                }

                @Override
                public void onNext(Long num) {
                    tvRejectReason.setText("还剩" + num + "分钟自动取消");
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    //回复原来初始状态
                    rlBottom.setVisibility(View.GONE);
                    tvRejectReason.setText("订单超时未付款,已自动取消");
                }
            });
        }
    }


    @Override
    protected void onReload() {
        initRequest();
    }


    public static class OrderConsultDetailIntentBean implements Serializable {
        //预约id
        public int id;
        //订单编号
        public String appNum;
        //apply 或者  receiver
        public String sourceType = "apply";
        //咨询师的id
        public int doctorId;
        //支付类型
        // payment_type：“0”  代表支付0元时，跳转申请成功页面
        public String payment_type = "-1";
        //订单状态
        public int mType;
        //来自哪个页面类型区分       咨询  consulting   诊疗  treatment
        public String fromType;


        public OrderConsultDetailIntentBean(int id, String appNum, String sourceType, int doctorId, String payment_type, int mType, String fromType) {

            this.id = id;
            this.appNum = appNum;
            this.sourceType = sourceType;
            this.doctorId = doctorId;
            this.payment_type = payment_type;
            this.mType = mType;
            this.fromType = fromType;
        }

        @Override
        public String toString() {
            return "OrderConsultDetailIntentBean{" +
                    "id=" + id +
                    ", appNum='" + appNum + '\'' +
                    ", sourceType='" + sourceType + '\'' +
                    ", doctorId=" + doctorId +
                    ", payment_type='" + payment_type + '\'' +
                    ", mType=" + mType +
                    ", fromType='" + fromType + '\'' +
                    '}';
        }
    }



//    待咨询/待诊疗
//    服务时间前24小时以外	取消服务 /开始咨询	置灰，点击后提示：服务时间未到
//    服务时间前24小时以内	开始咨询置灰，点击后提示：服务时间未到
//    服务时间到——马上咨询	开始咨询
//    待咨询/待诊疗
//    到达约定时并在服务时间段60分钟以内，且双方已沟通————开始咨询/确认服务完成 (二次确认后变为“服务完成”)
//    到达约定时间+60已到,且双方已沟通	—————确认服务完成

    /**
     * 所有状态的总结
     * - 患者申请的订单
     * 1. 14 待接诊   （开始咨询/开起视频咨询/不在咨询时段内）
     * 2. 15 已完成   （评价/再次预约） （查看评价/再次预约）
     * 3. 16 已作废   （再次预约）
     * 4. 8  已驳回   （再次预约）
     * 5. 7  已取消   （再次预约）
     * 6. 2  待受理   （取消订单）
     * 7. 18 待付款   （取消订单/ 立即支付）
     * - 医生点击复诊时的订单
     * 1. 14 待接诊   （开始咨询/开起视频咨询）
     * 2. 15 已完成   （评价/再次预约） （查看评价/再次预约）
     * 3. 16 已作废   （再次预约）
     * 4. 8  已驳回   （再次预约）
     * 5. 7  已取消   （再次预约）
     * 6. 2  待受理   （驳回/接受并付款）
     * 7. 18 待付款   （取消订单/ 立即支付）
     */


    //"取消订单"  二次确认弹框
    private void cancelOrder() {
        centerTipsPopup = new CommonCenterTipsPopup(this, "是否取消订单？", new CommonCenterTipsPopup.ResultListener() {
            @Override
            public void onCkecked(String action) {
                if ("right".equals(action)) {
                    net_cancelOrder();
                }
            }

            @Override
            public void onCreated() {
            }
        });
        new XPopup.Builder(this)
                //如果不加这个，评论弹窗会移动到软键盘上面
                .moveUpToKeyboard(false)
                .asCustom(centerTipsPopup)
                .show();
    }

    //"确认服务完成"  二次确认弹框
    private void confirmOrderFinish() {
        confirmOrderFinishPopup = new CommonCenterTipsPopup(this, "确认服务完成？", new CommonCenterTipsPopup.ResultListener() {
            @Override
            public void onCkecked(String action) {
                if ("right".equals(action)) {
//                    net_confirmDone();
                    //用户确认 0|未确认，1|已确认
                    //请求接口
                    //咨询/诊疗上报操作信息接口    customerConfirm 1  已确认
                    net_reportInformation(mIntentBean.id, mIntentBean.appNum, "", "", "1", "", "customerConfirm");
                }
            }

            @Override
            public void onCreated() {
            }
        });
        new XPopup.Builder(this)
                //如果不加这个，评论弹窗会移动到软键盘上面
                .moveUpToKeyboard(false)
                .asCustom(confirmOrderFinishPopup)
                .show();
    }

    /**
     * DecimalFormat转换最简便
     */
    public String formatDouble(Double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d);
        return format;
    }

    private void showPayTypePop(Context context, int checkPosition) {
        if (mPayTypePopup != null && mPayTypePopup.isShow()) {
            return;
        }
        if (mResultBean == null) {
            return;
        }
        mPayTypePopup = (BottomCheckOrderPayTypePopup) new XPopup.Builder(context).asCustom(new BottomCheckOrderPayTypePopup(context, checkPosition, "¥" + formatDouble(Double.parseDouble(mResultBean.getConsultingFee())), new BottomCheckOrderPayTypePopup.ResultListener() {
            @Override
            public void onCkecked(int checkPosition, String name) {
                mPayTypeCheckPosition = checkPosition;

                if (mPayTypeCheckPosition == 0) {
                    //支付宝支付
//                    String orderInfo = "alipay_sdk=alipay-sdk-java-3.3.2&app_id=2021001185606251&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E6%9C%8D%E5%8A%A11%E6%AC%A1%E5%85%B10.01%E5%85%83%22%2C%22out_trade_no%22%3A%22PAY2009231702310165%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9DAPP%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%225m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F58.30.9.142%3A34220%2Fgateway%2Fpayapi%2Falipay%2FalipayAppCallBackNotify&sign=hn2%2FZ2qHFUW54qdS6iDXNQIXQNzivkP%2FzZyhXQ18IcWlKwf6GmlNK7tqNmx37mD4dxxQLH9R%2FneZbybsDSdB63waPG5iMwbKUIwWl1TJxMPtrZ1dO249Yi8bj9QVkDjCcMcYN%2FLsEMFzs%2BEiVZJe0rA06lE%2FZ5AAoaL92as%2FERLymaFxWwZqRGmOhVyfd%2F6g0ings03BmQo0cEibZrjbaR%2FCyh1zbhUSa0dbggqhHWaqivXmfcN2oJFK%2FJHd5p%2Bq1wi6Ptd4yfHpPyEAnm2ZUC2z0tesn2KQ5tuQXMawM6sRXVOzUg97tSar%2FJBRpajIaWbIbKvZnBACPIcNn0qEJg%3D%3D&sign_type=RSA2&timestamp=2020-09-23+17%3A02%3A31&version=1.0";
//                    aliPayV2(OrderPayActivity.this, orderInfo);
                    showLoadingDialog();
//                    net_aliPrePay();
                    PayUtil.PayBean payBean = new PayUtil.PayBean();
                    payBean.totalAmount = Double.parseDouble(mResultBean.getConsultingFee());
                    if (mResultBean != null) {
                        payBean.goodsOrderNo = mResultBean.getAppNum();
                    }
                    if (accountService != null && accountService.getGetAccountInfo() != null) {
                        payBean.payUserId = accountService.getGetAccountInfo().userId;
                        payBean.payUserName = accountService.getGetAccountInfo().userName;
                    }
                    payUtil.aliPay(payBean, new PayUtil.OnResultListener() {
                        @Override
                        public void onResult(int status, String msg) {
                            hideDialog();
                            if (status == 0) {
                                //支付成功
                                if (paymentType.equals("1")) { //受邀列表 待受理 付款成功
                                    ToastUtils.showShort("支付成功");
                                    //请求接口
                                    net_acceptOrder();
                                } else {
                                    startSuccessActivity();
                                }
                            } else if (status == -1) {
                                ToastUtils.showShort("支付失败");
                            } else if (status == -2) {
                                ToastUtils.showShort("支付取消");
                            }
                        }
                    });
                } else if (mPayTypeCheckPosition == 1) {
                    //微信支付
//                    weixinPay(OrderPayActivity.this);
                    showLoadingDialog();
//                    net_weixinPrePay();
                    PayUtil.PayBean payBean = new PayUtil.PayBean();
                    payBean.totalAmount = Double.parseDouble(mResultBean.getConsultingFee());
                    if (mResultBean != null) {
                        payBean.goodsOrderNo = mResultBean.getAppNum();
                    }
                    if (accountService != null && accountService.getGetAccountInfo() != null) {
                        payBean.payUserId = accountService.getGetAccountInfo().userId;
                        payBean.payUserName = accountService.getGetAccountInfo().userName;
                    }
                    payUtil.weixinPay(payBean, new PayUtil.OnResultListener() {
                        @Override
                        public void onResult(int status, String msg) {
                            hideDialog();
                            if (status == 0) {
                                //支付成功
                                if (paymentType.equals("1")) { //受邀列表 待受理 付款成功
                                    ToastUtils.showShort("支付成功");
                                    //请求接口
                                    net_acceptOrder();
                                } else {
                                    startSuccessActivity();
                                }

                            } else if (status == -1) {
                                ToastUtils.showShort("支付失败");
                            } else if (status == -2) {
                                ToastUtils.showShort("支付取消");
                            }
                        }
                    });
                }


            }
        }));
        mPayTypePopup.show();
    }

    //跳转支付成功页面
    private void startSuccessActivity() {
        OrderConsultSuccessActivity.IntentBean intentBean
                = new OrderConsultSuccessActivity.IntentBean();
        intentBean.appNum = mResultBean.getAppNum();
        intentBean.price = Double.parseDouble(mResultBean.getConsultingFee());
        intentBean.from = "orderDetail";
        intentBean.id = mIntentBean.id;
        intentBean.sourceType = "apply";
        intentBean.doctorId = mIntentBean.id;
        OrderConsultSuccessActivity.startActivity(activity, intentBean);

    }

    /**
     * 驳回接口
     * 预约咨询模块驳回按钮
     */
    private void net_rejectOrder() {
        OrderCancelRequest request = new OrderCancelRequest();
        request.id = mIntentBean.id;
        request.appNum = mResultBean.getAppNum();
        request.explain = "";
        showLoadingDialog();
        mPresenter.getOrderReject(request, this);
    }

    /**
     * 取消接口
     * 预约咨询模块取消按钮
     */
    private void net_cancelOrder() {
        OrderCancelRequest request = new OrderCancelRequest();
        request.id = mIntentBean.id;
        request.appNum = mResultBean.getAppNum();
//        request.oldStageId = resultBean.getAppStatus();
        showLoadingDialog();
        mPresenter.getOrderCancel(request, this);
    }

    /**
     * 受理咨询/诊疗申请
     * 预约咨询模块受理并付款
     */
    private void net_acceptOrder() {
        OrderCancelRequest request = new OrderCancelRequest();
        request.id = mIntentBean.id;
        request.appNum = mResultBean.getAppNum();
        showLoadingDialog();
        mPresenter.getOrderAccept(request, this);
    }

    /**
     * 新增接口
     * 咨询/诊疗上报操作信息接口
     * 用户发起聊天、用户接听、用户确认服务状态    用于判断按钮显示
     * customerCall  int	N  用户发起聊天 0|未发起，1|已发起
     * customerAnswer  int	n用户接听 0|否，1|是
     * customerConfirm int	n 用户确认 0|未确认，1|已确认
     */
    private void net_reportInformation(int id, String appNum, String customerCall, String customerAnswer, String customerConfirm, String serverAnswer, String state) {
        ReportInformationBeanRequest reportInformationBeanRequest = new ReportInformationBeanRequest();
        reportInformationBeanRequest.setId(id);//主键ID
        reportInformationBeanRequest.setAppNum(appNum);//订单编号
        reportInformationBeanRequest.setCustomerCall(customerCall);
        reportInformationBeanRequest.setCustomerAnswer(customerAnswer);
        reportInformationBeanRequest.setCustomerConfirm(customerConfirm);
        reportInformationBeanRequest.setServerAnswer(serverAnswer);
        mPresenter.reportInformation(reportInformationBeanRequest, activity, state);
    }


    //老接口废弃
//    /**
//     * 心理咨询 预约相关-用户咨询完后点击确认服务完成
//     * 请求地址	请求方式	说明
//     */
//    private void net_confirmDone() {
//        ConfirmOrderDoneRequestBean request = new ConfirmOrderDoneRequestBean();
//        request.appNum = mResultBean.getAppNum();
//        request.id = mResultBean.getId() + "";
//        showLoadingDialog();
//        mPresenter.confirmDone(request, this);
//    }


    //检测被呼用户ID
    private boolean checkCallUserId(int userId) {
        if (mResultBean == null) {
            ToastUtils.showShort("请刷新再试");
            return false;
        }
        //先判断对方的id是否为空
        if (mIntentBean.doctorId == 0) {
            ToastUtils.showShort("医生的id为空");
            return false;
        }
        return true;
    }

    /**
     * 开始咨询、诊疗
     * 腾讯IM的文字聊天页面
     */
    private void goTextChat() {
        startCallText(userIdPrefix + String.valueOf(mIntentBean.doctorId));
//        searchContactsByUserId(1, userIdPrefix + String.valueOf(mIntentBean.doctorId));
    }


    /**
     * 开始咨询、诊疗
     * 腾讯双人视频沟通
     */
    private void goVideoCall() {
        //视频
        callType = TRTCCalling.TYPE_VIDEO_CALL;
        startCall(userIdPrefix + String.valueOf(mIntentBean.doctorId));
//        searchContactsByUserId(2, userIdPrefix + String.valueOf(mIntentBean.doctorId));
    }

    /**
     * 开始咨询、诊疗
     * 腾讯双人语音沟通
     */
    private void goVoiceCall() {
        //语音
        callType = TRTCCalling.TYPE_AUDIO_CALL;
        startCall(userIdPrefix + String.valueOf(mIntentBean.doctorId));
//        searchContactsByUserId(2, userIdPrefix + String.valueOf(mIntentBean.doctorId));
    }


//    //腾讯 视频语音呼叫
//    //1.搜索要呼叫的用户  判断是否在IM服务器登录了  查询存储在腾讯的昵称、头像
//    private void searchContactsByUserId(int type, String doctorId) {
//        if (TextUtils.isEmpty(doctorId)) {
//            return;
//        }
//        CallingInfoManager.getInstance().getUserInfoByUserId(doctorId, new CallingInfoManager.UserCallback() {
//            @Override
//            public void onSuccess(com.tencent.liteav.trtccalling.model.impl.UserModel model) {
//                mSearchModel = new UserModel();
//                mSearchModel.userId = model.userId;
//                mSearchModel.userName = TextUtils.isEmpty(model.userName) ? model.userId : model.userName;
//                mSearchModel.userAvatar = model.userAvatar;
//                ToastUtils.showLong("查询医生成功");
//                if (type == 1) {
//                    //文字聊天
////                    startCallText(doctorId);
//                } else if (type == 2) {
//                    //呼叫   语音、视频
//                    startCall();
//                }
//
//            }
//
//            @Override
//            public void onFailed(int code, String msg) {
//                ToastUtils.showLong("查询医生失败:" + msg);
//            }
//        });
//    }

    //进入文字聊天页面
    //跳转 腾讯的聊天Activity
    //id         主键ID
    //appNum     订单编号
    // chatId    医生ID
    // chatType  1单聊 2群聊
    // chatName  医生名字
    // groupType 应该是群聊用的
    // endTime   服务结束时间（暂时无法传递）
    //CustomerCall 0|未发起，1|已发起
    //CustomerAnswer 用户接听 0|否，1|是
    // ContactUtils.startChatActivity("", "", "mhsptrunkdev12608", 1, "张医生", "", "2021-12-30 14:35:00");
    private void startCallText(String doctorId) {
        ContactUtils.startChatActivity(
                String.valueOf(mIntentBean.id),
                mIntentBean.appNum,
                doctorId,
                1,
                mResultBean.getDoctorName(),
                "",
                mResultBean.getEndTime(),
                String.valueOf(mResultBean.getCustomerCall()),
                String.valueOf(mResultBean.getCustomerAnswer()));
    }


    //腾讯 视频语音呼叫
    //开始呼叫医生
    //role 	TUICallingRole 	用户角色类型：主叫/被叫
    //viewController 	UIViewController 	通话视图 ViewController
    private void startCall(String userId) {
        if (callType == TRTCCalling.TYPE_VIDEO_CALL) {//视频呼叫
//            ToastUtils.showShort("视频呼叫" + userId);
            //此处一人呼叫多人
//            String[] userIDs = {"501","401"};
            String[] userIDs = {userId};//呼叫 医生id
            Bundle bundle = new Bundle();
            bundle.putString(TUICallingConstants.PARAM_NAME_TYPE, TUICallingConstants.TYPE_VIDEO);  //type 通话类型：音频/视频  audio  video
            bundle.putStringArray(TUICallingConstants.PARAM_NAME_USERIDS, userIDs);//userIDs 通话用户ID:医生id
            bundle.putString(TUICallingConstants.PARAM_NAME_GROUPID, "");
//            TUICore.callService(TUIConstants.Service.TUI_CALLING, TUICallingConstants.METHOD_NAME_CALL, bundle);
            // 1. 初始化组件
            TUICallingManager tuiCallingManager = TUICallingManager.sharedInstance();

            // 2. 注册监听器
            tuiCallingManager.setCallingListener(new TUICalling.TUICallingListener() {
                @Override
                public boolean shouldShowOnCallView() {
//                    ToastUtils.showLong("被叫时请求拉起接听页面");
                    return true;
                }

                @Override
                public void onCallStart(String[] userIDs, TUICalling.Type type, TUICalling.Role role, View tuiCallingView) {
//                    ToastUtils.showLong("开始呼叫:" + userIDs[0]);
                    if (mResultBean.getCustomerCall() == 0) {
                        //用户发起聊天 0|未发起，1|已发起
                        //请求接口
                        //咨询/诊疗上报操作信息接口    customerCall 1  已发起
                        net_reportInformation(mIntentBean.id, mIntentBean.appNum, "1", "", "", "", "customerCall");
                    }
                }

                @Override
                public void onCallEnd(String[] userIDs, TUICalling.Type type, TUICalling.Role role, long totalTime) {
//                    ToastUtils.showLong("挂断结束通话:" + userIDs[0]);
                }

                @Override
                public void onCallEvent(TUICalling.Event event, TUICalling.Type type, TUICalling.Role role, String message) {
//                    ToastUtils.showLong("通话事件回调:" + message);
                    //通话接通成功
                    if (event.equals(TUICalling.Event.CALL_SUCCEED)) {
                        if (mResultBean.getCustomerAnswer() == 0) {
                            //用户接听 0|否，1|是
                            //请求接口
                            //咨询/诊疗上报操作信息接口    customerAnswer 1  用户接听
                            net_reportInformation(mIntentBean.id, mIntentBean.appNum, "", "", "", "1", "serverAnswer");
                        }
                    }
                }
            });
            // 3.拨打电话
            tuiCallingManager.call(userIDs, TUICalling.Type.VIDEO);//呼叫类型:音频视频
            //保存服务结束时间  在TUICallVideoView中获取处理挂断
            SpUtil spUtil = new SpUtil(OrderConsultDetailActivity.this, "服务结束时间");
            spUtil.putString("end_time", mResultBean.getEndTime());
        } else if (callType == TRTCCalling.TYPE_AUDIO_CALL) {//语音呼叫
//            ToastUtils.showShort("语音呼叫", userId);
            String[] userIDs = {userId};
            Bundle bundle = new Bundle();
            bundle.putString(TUICallingConstants.PARAM_NAME_TYPE, TUICallingConstants.TYPE_AUDIO);
            bundle.putStringArray(TUICallingConstants.PARAM_NAME_USERIDS, userIDs);
            bundle.putString(TUICallingConstants.PARAM_NAME_GROUPID, "");
//            TUICore.callService(TUIConstants.Service.TUI_CALLING, TUICallingConstants.METHOD_NAME_CALL, bundle);
            // 1. 初始化组件
            TUICallingManager tuiCallingManager = TUICallingManager.sharedInstance();
            // 2. 注册监听器
            tuiCallingManager.setCallingListener(new TUICalling.TUICallingListener() {
                @Override
                public boolean shouldShowOnCallView() {
//                    ToastUtils.showLong("被叫时请求拉起接听页面");
                    return true;
                }

                @Override
                public void onCallStart(String[] userIDs, TUICalling.Type type, TUICalling.Role role, View tuiCallingView) {
//                    ToastUtils.showLong("开始呼叫:" + userIDs[0]);
                    if (mResultBean.getCustomerCall() == 0) {
                        //用户发起聊天 0|未发起，1|已发起
                        //请求接口
                        //咨询/诊疗上报操作信息接口    customerCall 1  已发起
                        net_reportInformation(mIntentBean.id, mIntentBean.appNum, "1", "", "", "", "customerCall");
                    }
                }

                @Override
                public void onCallEnd(String[] userIDs, TUICalling.Type type, TUICalling.Role role, long totalTime) {
//                    ToastUtils.showLong("挂断结束通话:" + userIDs[0]);
                }

                @Override
                public void onCallEvent(TUICalling.Event event, TUICalling.Type type, TUICalling.Role role, String message) {
//                    ToastUtils.showLong("通话事件回调:" + message);
                    //通话接通成功
                    if (event.equals(TUICalling.Event.CALL_SUCCEED)) {
                        if (mResultBean.getCustomerAnswer() == 0) {
                            //用户接听 0|否，1|是
                            //请求接口
                            //咨询/诊疗上报操作信息接口    customerAnswer 1  用户接听
                            net_reportInformation(mIntentBean.id, mIntentBean.appNum, "", "", "", "1", "serverAnswer");
                        }
                    }
                }
            });
            // 3.拨打电话
            tuiCallingManager.call(userIDs, TUICalling.Type.AUDIO);
            //保存服务结束时间  在TUICallVideoView中获取处理挂断
            SpUtil spUtil = new SpUtil(OrderConsultDetailActivity.this, "服务结束时间");
            spUtil.putString("end_time", mResultBean.getEndTime());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimerStart != null) {
            countDownTimerStart.cancel();
            countDownTimerStart = null;
        }
        if (countDownTimerEnd != null) {
            countDownTimerEnd.cancel();
            countDownTimerEnd = null;
        }
        //切断水管,防止空指针
        if (d != null) {
            d.dispose();
        }
    }

    @Override
    public void onBackPressed() {
        if (mIntentBean.payment_type.equals("0")) {
            //0元跳过支付进入的  返回当前咨询师页面
            activityStackUtil.clearTopOfMy(activity, CounselorDetailActivity.class);
        } else {
            finish();
        }
    }


    //跳转评价回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            //刷新
            smartRefreshLayout.autoRefresh();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //刷新数据
        initRequest();
    }
}
