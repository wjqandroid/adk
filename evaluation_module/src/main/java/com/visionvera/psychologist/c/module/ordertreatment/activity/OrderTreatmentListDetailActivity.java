//package com.visionvera.psychologist.c.module.ordertreatment.activity;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.CountDownTimer;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.text.TextUtils;
//import android.text.style.ForegroundColorSpan;
//import android.util.Log;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.alibaba.android.arouter.facade.annotation.Autowired;
//import com.alibaba.android.arouter.launcher.ARouter;
//import com.blankj.utilcode.util.ToastUtils;
//import com.google.gson.Gson;
//import com.lxj.xpopup.XPopup;
//import com.lxj.xpopup.core.BasePopupView;
//import com.orhanobut.logger.Logger;
//import com.scwang.smartrefresh.layout.SmartRefreshLayout;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
//import com.tbruyelle.rxpermissions2.RxPermissions;
//import com.tencent.imsdk.TIMFriendshipManager;
//import com.tencent.imsdk.TIMUserProfile;
//import com.tencent.imsdk.TIMValueCallBack;
//import com.visionvera.library.arouter.ARouterConstant;
//import com.visionvera.library.arouter.service.IAccountService;
//import com.visionvera.library.base.BaseActivity;
//import com.visionvera.library.base.BaseMVPLoadActivity;
//import com.visionvera.library.base.Constant;
//import com.visionvera.library.base.bean.BaseResponseBean2;
//import com.visionvera.library.util.OneClickUtils;
//import com.visionvera.library.util.TimeFormatUtils;
//import com.visionvera.library.widget.dialog.TreatmentTypePopup;
//import com.visionvera.psychologist.c.R;
//import com.visionvera.psychologist.c.R2;
//import com.visionvera.psychologist.c.eventbus.OrderTreatmentCancelEventBus;
//import com.visionvera.psychologist.c.eventbus.OrderTreatmentListDetailToFragmentBus;
//import com.visionvera.psychologist.c.eventbus.SurfaceViewBus;
//import com.visionvera.psychologist.c.module.counselling.bean.SaveMeetingRecordRequest;
//import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentAgainConsultRequest;
//import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentCancelRequest;
//import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListDetailBean;
//import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListDetailRequest;
//import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
//import com.visionvera.psychologist.c.module.ordertreatment.presenter.OrderTreatmentListDetailPresenter;
//import com.visionvera.psychologist.c.module.shilianwang.VideoActivity;
//import com.visionvera.psychologist.c.widget.tencentIm.textchat.ChatActivity;
//import com.visionvera.psychologist.c.widget.tencentIm.utils.TIMCountDownTimer;
//import com.visionvera.zong.api.VVClient;
//import com.visionvera.zong.model.ListItemBean;
//import com.visionvera.zong.model.http.CovertBean;
//
//import org.greenrobot.eventbus.EventBus;
//import org.webrtcold.SurfaceViewRenderer;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
//import static com.visionvera.library.base.Constant.RequestReturnCode.OrderTreatmentList_To_OrderTreatmentListDetail_Code;
//
///**
// * author:lilongfeng
// * date:2020/1/7
// * 描述:预约诊疗----列表----详情
// * 12个页面共享。因为有大部分功能相同，所有公用一个页面。
// */
//
//public class OrderTreatmentListDetailActivity extends BaseMVPLoadActivity<IContract.OrderTreamentListDetail.OrderTreatmentListDetailView, OrderTreatmentListDetailPresenter> {
//
//
//    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
//    IAccountService accountService;
//
//    @BindView(R2.id.tv_title)
//    TextView tv_title;
//
//    @BindView(R2.id.evaluation_module_order_consult_number)
//    TextView order_number;
//
//    @BindView(R2.id.evaluation_module_order_consult_status)
//    TextView order_consult_status;
//
//    @BindView(R2.id.evaluation_module_counselor_tv)
//    TextView counselor_tv;
//
//    @BindView(R2.id.evaluation_module_order_consult_time_tv)
//    TextView consult_time_tv;
//
//    @BindView(R2.id.evaluation_module_order_consult_order_time_tv)
//    TextView order_time_tv;
//
//    @BindView(R2.id.evaluation_module_order_consult_type)
//    TextView order_consult_type;
//
//    @BindView(R2.id.evaluation_module_order_consult_consulttime)
//    TextView consulttime;
//
//    @BindView(R2.id.evaluation_module_order_consult_phonenumber)
//    TextView consult_phonenumber;
//
//    @BindView(R2.id.evaluation_module_order_consult_remark)
//    TextView remark;
//
//    @BindView(R2.id.evaluation_module_order_consult_detail_operation)
//    TextView detail_operation;
//
//    @BindView(R2.id.call_bg)
//    RelativeLayout call_bg;
//
//    @BindView(R2.id.evaluation_module_order_consult_type_layout)
//    LinearLayout type_layout;
//
//    @BindView(R2.id.evaluation_module_order_consult_table_type_layout)
//    LinearLayout table_type_layout;
//
//    @BindView(R2.id.evaluation_module_order_consult_type_item_tv)
//    TextView type_item_tv;
//    @BindView(R2.id.tv_cancel)
//    TextView tvCancel;
//    @BindView(R2.id.normal_view)
//    SmartRefreshLayout smartRefreshLayout;
//
//    private BasePopupView typePopup;
//
//    private OrderTreatmentListDetailIntentBean mIntentBean;
//
//    private static final String TAG = "视联网SDK";
//    //视联网初始化是否成功
//    private boolean shilianwangSDKinit;
//
//    private String tempSelectDeviceId = "00000";
//    //咨询类型
//    private int mType;
//    private int diagnosisMode;
//
//    private OrderTreatmentListDetailBean.ResultBean mResultBean;
//    private CountDownTimer countDownTimerStart;//开始进入聊天的计时器
//    private CountDownTimer countDownTimerEnd;//关闭进入聊天的计时器
//
//    public static void startActivityForResult(Context context, OrderTreatmentListDetailIntentBean intentBean) {
//        if (context instanceof BaseActivity) {
//            ((BaseActivity) context).startActivityForResult(new Intent(context,
//                            OrderTreatmentListDetailActivity.class)
//                            .putExtra("intent", intentBean),
//                    OrderTreatmentList_To_OrderTreatmentListDetail_Code);
//        }
//
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.evaluation_module_activity_order_treatment_list_detail;
//    }
//
//    @Override
//    protected void doYourself() {
//
//        ARouter.getInstance().inject(this);
//
//        initView();
//
//        getIntentData();
//
//        initRequest();
//    }
//
//    private void initView() {
//        tv_title.setText(getString(R.string.evaluation_module_order_consult_detail));
//        smartRefreshLayout.setEnableLoadMore(false);
//        smartRefreshLayout.setEnableRefresh(true);
//        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                initRequest();
//            }
//        });
//    }
//
//    private void getIntentData() {
//        mIntentBean = (OrderTreatmentListDetailIntentBean) getIntent().getSerializableExtra("intent");
//
//    }
//
//    private void initRequest() {
//        showLoading();
//
//        OrderTreatmentListDetailRequest request = new OrderTreatmentListDetailRequest(mIntentBean.id);
//
//        mPresenter.getOrderTreatmentListDetail(request, this);
//
//        /**
//         * 这里的操作是为了同步对方的昵称，以便在聊天界面中展示出来的是昵称而不是ID号。
//         */
//        List<String> users = new ArrayList<>();
//        users.add(mIntentBean.doctorId + "");
//        TIMFriendshipManager.getInstance().getUsersProfile(users, true, new TIMValueCallBack<List<TIMUserProfile>>() {
//            @Override
//            public void onError(int code, String desc) {
//                Logger.i("getUsersProfile failed: " + code + ": desc:" + desc);
//            }
//
//            @Override
//            public void onSuccess(List<TIMUserProfile> timUserProfiles) {
//            }
//        });
//    }
//
//    @Override
//    protected void initMVP() {
//        mView = new IContract.OrderTreamentListDetail.OrderTreatmentListDetailView() {
//            @Override
//            public void onOrderTreatmentListDetail(OrderTreatmentListDetailBean bean, ResultType resultType, String errorMsg) {
//                if (smartRefreshLayout != null) {
//                    smartRefreshLayout.finishRefresh();
//                    smartRefreshLayout.finishLoadMore();
//                }
//                switch (resultType) {
//                    case NET_ERROR:
//                        ToastUtils.showLong(errorMsg);
////                        //网络异常等，也就是根本没跟自己服务器正常交互
////                        showError(getString(R.string.base_module_net_error));
////                        ToastUtils.showLong(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                        ToastUtils.showLong(errorMsg);
//                        //也就是自己服务器返回的code值不对
////                        showError(getString(R.string.base_module_data_load_error));
////                        ToastUtils.showLong(getString(R.string.base_module_data_load_error));
//                        break;
//                    case SERVER_NORMAL_DATANO:
//                    case SERVER_NORMAL_DATAYES:
//                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
//                        showNormal();
//                        showData(bean.getResult());
//                        break;
//                    default:
//                }
//            }
//
//            @Override
//            public void onOrderTreatmentCancel(BaseResponseBean2 bean, ResultType resultType, String errorMsg) {
//                hideDialog();
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等，也就是根本没跟自己服务器正常交互
//                        ToastUtils.showShort(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                        //也就是自己服务器返回的code值不对
//                        ToastUtils.showShort(errorMsg);
//                        break;
//                    case SERVER_NORMAL_DATANO:
//                    case SERVER_NORMAL_DATAYES:
//                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
//                        ToastUtils.showShort("取消预约成功");
//                        setResult(OrderTreatmentList_To_OrderTreatmentListDetail_Code);
//                        finish();
//                        break;
//                    default:
//                }
//            }
//
//            @Override
//            public void onCancel(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
//                //受邀列表-待受理-拒绝
//                hideDialog();
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等，也就是根本没跟自己服务器正常交互
//                        ToastUtils.showShort(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                        //也就是自己服务器返回的code值不对
//                        ToastUtils.showShort(errorMsg);
//                        break;
//                    case SERVER_NORMAL_DATANO:
//                    case SERVER_NORMAL_DATAYES:
//                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
//                        ToastUtils.showShort("已拒绝");
//                        //发送一个eventbus广播,把必要的信息传递过去,谁爱用谁用
//                        //这里主要是通知列表刷新
//                        OrderTreatmentCancelEventBus event = new OrderTreatmentCancelEventBus();
//                        EventBus.getDefault().post(event);
//                        finish();
//                        break;
//                    default:
//                }
//            }
//
//            @Override
//            public void onSaveMeetingRecord(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
//                Log.e(TAG, "onSaveMeetingRecord: " + new Gson().toJson(response));
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等，也就是根本没跟自己服务器正常交互
//                        break;
//                    case SERVER_ERROR:
//                        //也就是自己服务器返回的code值不对
//
//                        break;
//                    case SERVER_NORMAL_DATANO:
//                    case SERVER_NORMAL_DATAYES:
//                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
//                        break;
//                }
//            }
//
//            @Override
//            public void onUserUpdateAgainConsultInfo(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
//                hideDialog();
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等，也就是根本没跟自己服务器正常交互
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                        //也就是自己服务器返回的code值不对
//                        ToastUtils.showLong(errorMsg);
//                        break;
//                    case SERVER_NORMAL_DATANO:
//                    case SERVER_NORMAL_DATAYES:
//                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
//                        ToastUtils.showShort("处理成功");
//                        EventBus.getDefault().post(new OrderTreatmentListDetailToFragmentBus());
//                        finish();
//                        break;
//                }
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        mPresenter = new OrderTreatmentListDetailPresenter(this, mView);
//    }
//
//    private void showData(OrderTreatmentListDetailBean.ResultBean bean) {
//
//        mResultBean = bean;
//
//        order_number.setText(bean.getAppNum());
//        counselor_tv.setText(TextUtils.isEmpty(bean.getDoctorName()) ? "" : bean.getDoctorName());
//        String consultTimeString = bean.getStartTime().substring(0, 16) + "-" + bean.getEndTime().substring(11, 16);
//
//        consult_time_tv.setText(consultTimeString);
//
//        order_time_tv.setText(bean.getCreatetime());
//
//        order_consult_type.setText(bean.getDiagnosisModeName());
//
//        tempSelectDeviceId = bean.getDeviceCode() + "";
//
//        consulttime.setText(bean.getCounselingNum() + "");
//        if (bean.getAppFrom() == 1) {
//            consult_phonenumber.setText(bean.getPhoneNumber());
//        } else if (bean.getAppFrom() == 2) {
//            consult_phonenumber.setText(bean.getClientPatientMobile());
//        }
//
//        remark.setText(bean.getComments());
//
//        String orderStatus = "";
//        /**
//         * （14：待接诊 15：已完成 16：已作废 5：已驳回 7：已取消 2:待受理）
//         */
//        if (TextUtils.equals(mIntentBean.sourceType, Constant.IntentKey.apply)) {
//            //申请列表详情
//            switch (mIntentBean.type) {
//                case 2://2:待受理
//                    orderStatus = getString(R.string.evaluation_module_to_be_accept);
//                    detail_operation.setVisibility(View.VISIBLE);
//
//                    detail_operation.setText(getString(R.string.evaluation_module_order_consult_cancel_order));
//                    detail_operation.setTextColor(getResources().getColor(R.color.base_module_theme));
//                    detail_operation.setBackgroundResource(R.drawable.evaluation_module_white_btn_bg_corner4);
//
//                    break;
//                case 14://14：待接诊
//                    orderStatus = getString(R.string.evaluation_module_to_be_received);
//                    detail_operation.setVisibility(View.VISIBLE);
//                    detail_operation.setTextColor(Color.WHITE);
//                    diagnosisMode = bean.getDiagnosisMode();
//                    if (bean.getDiagnosisMode() == 3) {
//                        detail_operation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
//                        detail_operation.setText(getString(R.string.evaluation_module_open_video_consult));
//
//                        checkPermissions();
//                    } else if (bean.getDiagnosisMode() == 4) {
//
//                        CuountDownUtil(bean);
//
//                    }
//
//                    break;
//                case 7://7：已取消
//                    orderStatus = getString(R.string.evaluation_module_already_cancel);
//                    detail_operation.setVisibility(View.GONE);
//                    break;
//                case 15://15：已完成
//                    orderStatus = getString(R.string.evaluation_module_already_complete);
//                    detail_operation.setVisibility(View.GONE);
//                    break;
//                case 5://5：已驳回
//                    orderStatus = getString(R.string.evaluation_module_already_reject);
//                    detail_operation.setVisibility(View.GONE);
//                    break;
//                case 16://16：已作废
//                    orderStatus = getString(R.string.evaluation_module_already_abolist);
//                    detail_operation.setVisibility(View.GONE);
//                    break;
//
//            }
//
//        } else {
//            //受邀列表详情
//            switch (mIntentBean.type) {
//                case 2:
//                    orderStatus = getString(R.string.evaluation_module_to_be_accept);
//                    detail_operation.setVisibility(View.VISIBLE);
//
//                    type_layout.setVisibility(View.VISIBLE);
//                    table_type_layout.setVisibility(View.GONE);
//
//                    detail_operation.setText(getString(R.string.evaluation_module_accept_consult));
//                    detail_operation.setTextColor(getResources().getColor(R.color.COLOR_white_ffffff));
//                    detail_operation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
//
//                    tvCancel.setVisibility(View.VISIBLE);
//
//                    break;
//                case 14:
//                    orderStatus = getString(R.string.evaluation_module_to_be_received);
//                    detail_operation.setVisibility(View.VISIBLE);
//                    detail_operation.setTextColor(Color.WHITE);
//                    diagnosisMode = bean.getDiagnosisMode();
//                    if (bean.getDiagnosisMode() == 3) {
//                        detail_operation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
//                        detail_operation.setText(getString(R.string.evaluation_module_open_video_consult));
//                        checkPermissions();
//                    } else if (bean.getDiagnosisMode() == 4) {
//                        CuountDownUtil(bean);
//                    }
//                    break;
//                case 7:
//                    orderStatus = getString(R.string.evaluation_module_already_cancel);
//                    detail_operation.setVisibility(View.GONE);
//                    break;
//                case 15:
//                    orderStatus = getString(R.string.evaluation_module_already_complete);
//                    detail_operation.setVisibility(View.GONE);
//                    break;
//                case 5:
//                    orderStatus = getString(R.string.evaluation_module_already_reject);
//                    detail_operation.setVisibility(View.GONE);
//                    break;
//                case 16:
//                    orderStatus = getString(R.string.evaluation_module_already_abolist);
//                    detail_operation.setVisibility(View.GONE);
//                    break;
//
//            }
//        }
//        order_consult_status.setText(orderStatus);
//    }
//
//    private void CuountDownUtil(OrderTreatmentListDetailBean.ResultBean bean) {
//        long startTime = TimeFormatUtils.strToDateLong(bean.getStartTime()).getTime();
//        long endTime = TimeFormatUtils.strToDateLong(bean.getEndTime()).getTime();
//
//        long currentTime = Calendar.getInstance().getTime().getTime();
//
//        if (currentTime >= startTime && currentTime >= endTime) {
//            detail_operation.setBackgroundResource(R.drawable.evaluation_module_gray_btn_bg_corner4);
//            detail_operation.setText(getString(R.string.evaluation_module_come_beyond_durtion_time));
//        } else if (currentTime >= startTime && currentTime <= endTime) {
//            //倒计时的时间的计算
//            detail_operation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
//            detail_operation.setText(getString(R.string.evaluation_module_open_word_consult));
//            if (countDownTimerEnd == null) {
//                countDownTimerEnd = new TIMCountDownTimer(endTime - currentTime) {
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                        detail_operation.setBackgroundResource(R.drawable.evaluation_module_gray_btn_bg_corner4);
//                        detail_operation.setText(getString(R.string.evaluation_module_come_beyond_durtion_time));
//                    }
//                };
//            }
//            countDownTimerEnd.start();
//        } else if (currentTime <= startTime && currentTime <= endTime) {
//            detail_operation.setBackgroundResource(R.drawable.evaluation_module_gray_btn_bg_corner4);
//            detail_operation.setText(getString(R.string.evaluation_module_not_come_durtion_time));
//            //倒计时时间的计算
//            if (countDownTimerStart == null) {
//                countDownTimerStart = new TIMCountDownTimer(startTime - currentTime) {
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                        detail_operation.setBackgroundResource(R.drawable.evaluation_module_blue_btn_bg_corner4);
//                        detail_operation.setText(getString(R.string.evaluation_module_open_word_consult));
//                    }
//                };
//            }
//            countDownTimerStart.start();
//
//            if (countDownTimerEnd == null) {
//                countDownTimerEnd = new TIMCountDownTimer(endTime - currentTime) {
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                        detail_operation.setBackgroundResource(R.drawable.evaluation_module_gray_btn_bg_corner4);
//                        detail_operation.setText(getString(R.string.evaluation_module_come_beyond_durtion_time));
//                    }
//                };
//            }
//            countDownTimerEnd.start();
//
//        }
//
//    }
//
//
//    private void initShilianwangSDKMethod() {
//
//        initShilianwangServer();
//
//        initShilianwangKeySecret();
//    }
//
//    /**
//     * 接入SDK时，SDK本身自带了4个权限，
//     * 1.	存储权限
//     * 2.	电话权限
//     * 3.	相机权限
//     * 4.	麦克风权限
//     * 这4个权限都开启后，SDK初始化才能进行。否则会崩溃。
//     */
//    private void checkPermissions() {
//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions
//                .request(
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.RECORD_AUDIO)
//                .subscribe(granted -> {
//                    if (granted) {
//                        initShilianwangSDKMethod();
//                    } else {
//                        // Oups permission denied
//                        Toast.makeText(this, "必须授予权限才能通话", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//
//
//    private void checkPermissionsAgain() {
//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions
//                .request(
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.RECORD_AUDIO)
//                .subscribe(granted -> {
//                    if (granted) {
//                        if (shilianwangSDKinit) {
//                            if (TextUtils.equals("00000", tempSelectDeviceId)) {
//                                ToastUtils.showLong("视联网终端号是00000");
//                            } else {
//                                CallShilianwang();
//                            }
//                        } else {
//                            //若初始化没有结束，则不能呼叫
//                            ToastUtils.showLong("正在初始化，请稍后");
//                        }
//                    } else {
//                        // Oups permission denied
//                        Toast.makeText(this, "必须授予权限才能通话", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//
//    /**
//     * 初始化流媒体服务器
//     */
//    private void initShilianwangKeySecret() {
//
//        VVClient.getInstance().fetchStreamServerList(Constant.ShilianwangSDK.app_key,
//                Constant.ShilianwangSDK.app_secret,
//                new VVClient.Callback<List<ListItemBean>>() {
//                    @Override
//                    public void onSuccess(List<ListItemBean> listItemBeans) {
//                        Log.e(TAG, "onSuccess: ");
//                        if (listItemBeans == null || listItemBeans.size() == 0) {
//                            ToastUtils.showShort("流媒体服务器暂无数据");
//                            return;
//                        }
//
//                        for (int i = 0; i < listItemBeans.size(); i++) {
//                            Log.e(TAG, "onSuccess: " + new Gson().toJson(listItemBeans.get(i)));
//                        }
//                        if (listItemBeans.size() > 0) {
//                            initRequestStreamServer(listItemBeans.get(0));
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailed(int i, String s) {
////                        Toast.makeText(getActivity(), "获取流媒体列表失败", Toast.LENGTH_SHORT).show();
//                        Log.e(TAG, "onFailed: " + s);
//                    }
//                });
//
//    }
//
//    /**
//     * 初始化流媒体服务器
//     */
//    private void initRequestStreamServer(ListItemBean listItemBean) {
//
//        if (listItemBean != null) {
//            VVClient.getInstance().initRequest(listItemBean.id,
//                    accountService.getGetAccountInfo().phoneNumber,
//                    accountService.getGetAccountInfo().userName,
//                    accountService.getGetAccountInfo().phoneNumber,
//                    "1",
//                    new VVClient.Callback<String>() {
//                        @Override
//                        public void onSuccess(String s) {
//                            Log.e(TAG, "onSuccess: " + s);
//                            shilianwangSDKinit = true;
//                            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailed(int i, String s) {
//                            Log.e(TAG, "onFailed: " + s);
//                            shilianwangSDKinit = false;
//                            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//    }
//
//    /**
//     * 初始化视联网sdk
//     */
//    private void initShilianwangServer() {
//        //北京的测试环境和广西的url是同一个。
//        VVClient.getInstance().setServer(Constant.ShilianwangSDK.serverUrl);
//
//    }
//
//    @Override
//    protected void onReload() {
//        initRequest();
//    }
//
//    @OnClick({R2.id.iv_back, R2.id.evaluation_module_order_consult_detail_operation, R2.id.evaluation_module_order_consult_type_item, R2.id.reject_button, R2.id.tv_cancel})
//    public void onClick(View view) {
//        if (OneClickUtils.isFastClick()) {
//            return;
//        }
//        int viewId = view.getId();
//        if (viewId == R.id.iv_back) {
//            finish();
//        } else if (viewId == R.id.evaluation_module_order_consult_detail_operation) {
//            if (TextUtils.equals(mIntentBean.sourceType, Constant.IntentKey.apply)) {
//                switch (mIntentBean.type) {
//                    case 2:
//                        OrderTreatmentCancelRequest request = new OrderTreatmentCancelRequest(mIntentBean.id, "7", "2");
//                        showLoadingDialog();
//                        mPresenter.getOrderTreatmentCancel(request, this);
//                        break;
//                    case 14:
//                        if (diagnosisMode == 3) {
//                            //呼叫视联网
//                            checkPermissions();
//                        } else if (diagnosisMode == 4) {
//                            //文字咨询
//                            WordTreatment();
//                        }
//                        break;
//                    case 7:
//                    case 15:
//                    case 5:
//                    case 16:
//                        break;
//                    default:
//
//                }
//            } else if (TextUtils.equals(mIntentBean.sourceType, Constant.IntentKey.receiver)) {
//                switch (mIntentBean.type) {
//                    case 2:
//                        if (mType == 0) {
//                            ToastUtils.showShort("请选择咨询形式");
//                        } else {
//                            showLoadingDialog();
//                            OrderTreatmentAgainConsultRequest request = new OrderTreatmentAgainConsultRequest(
//                                    mIntentBean.id,
//                                    0,
//                                    0,
//                                    mType
//                            );
//
//                            mPresenter.getUserUpdateAgainConsultInfo(request, this);
//                        }
//                        break;
//                    case 14:
//                        if (diagnosisMode == 3) {
//                            //呼叫视联网
//                            checkPermissions();
//                        } else if (diagnosisMode == 4) {
//                            //文字咨询
//                            WordTreatment();
//                        }
//                        break;
//                    case 7:
//                    case 15:
//                    case 5:
//                    case 16:
//                        break;
//                    default:
//
//                }
//            }
//
//        } else if (viewId == R.id.tv_cancel) {
//            //拒绝按钮    只会在受邀列表的待受理状态下才会显示
//            //变为已拒绝状态
//            OrderTreatmentCancelRequest request = new OrderTreatmentCancelRequest(mIntentBean.id, "8", mIntentBean.type + "");
//            showLoadingDialog();
//            mPresenter.cancel(request, this);
//
//        } else if (viewId == R.id.evaluation_module_order_consult_type_item) {
//            showTypePopup();
//        } else if (viewId == R.id.reject_button) {
//            VVClient.getInstance().cancelVideoCall(VVClient.CALL_MODE.CALL_SLW, tempSelectDeviceId, "", tempSelectDeviceId, "",
//                    new VVClient.Callback<String>() {
//                        @Override
//                        public void onSuccess(String data) {
//                            call_bg.setVisibility(View.GONE);
//                        }
//
//                        @Override
//                        public void onFailed(int errCode, String errMsg) {
//                            call_bg.setVisibility(View.GONE);
//                            ToastUtils.showLong("挂断失败");
//                        }
//                    });
//        }
//    }
//
//    /**
//     * 文字咨询的处理逻辑
//     */
//    private void WordTreatment() {
//        if (TextUtils.equals(detail_operation.getText(), getString(R.string.evaluation_module_not_come_durtion_time))) {
//            ToastUtils.showShort(getString(R.string.evaluation_module_not_come_durtion_time));
//        } else if (TextUtils.equals(detail_operation.getText(), getString(R.string.evaluation_module_open_word_consult))
//                || TextUtils.equals(detail_operation.getText(),getString(R.string.evaluation_module_come_beyond_durtion_time))) {
//            if (mResultBean == null) {
//                ToastUtils.showShort("返回数据为空");
//                return;
//            }
//            //先判断对方的id是否为空
//            if (mResultBean.getDoctorId() == 0) {
//                ToastUtils.showShort("医生的id为空");
//                return;
//            }
//            ChatActivity.ChatActivityIntentBean intentBean = new ChatActivity.ChatActivityIntentBean(
//                    mResultBean.getDoctorId() + "",
//                    mResultBean.getDoctorName(),
//                    accountService.getGetAccountInfo().userId,
//                    accountService.getGetAccountInfo().userName,
//                    mResultBean.getEndTime());
//
//            startActivity(new Intent(activity, ChatActivity.class).putExtra("intentBean", intentBean));
//
//            SaveMeetingRecordRequest saveMeetingRecordRequest = new SaveMeetingRecordRequest();
//            saveMeetingRecordRequest.setBusinessId(mIntentBean.businessId);
//            saveMeetingRecordRequest.setBusinessType(16);
//            mPresenter.getSaveMeetingRecord(saveMeetingRecordRequest, activity);
//        }
//    }
//
//    private void CallShilianwang() {
//        call_bg.setVisibility(View.VISIBLE);
//
//        tempSelectDeviceId = autoGenericCode(tempSelectDeviceId, 5);
//
//        VVClient.getInstance().makeVideoCall(VVClient.CALL_MODE.CALL_SLW, tempSelectDeviceId, "0", tempSelectDeviceId, tempSelectDeviceId, new VVClient.Callback<SurfaceViewRenderer[]>() {
//            @Override
//            public void onSuccess(SurfaceViewRenderer[] data) {
//                Log.e(TAG, "onSuccess: " + tempSelectDeviceId);
//                call_bg.setVisibility(View.GONE);
//                if (data == null) {
//
//                } else {
//                    if (data.length < 2) return;
//                    Intent intent = new Intent(activity, VideoActivity.class);
//                    EventBus.getDefault().postSticky(new SurfaceViewBus(data, new CovertBean.ItemsBean(), 6, null, tempSelectDeviceId));
//                    startActivity(intent);
//
//                    SaveMeetingRecordRequest saveMeetingRecordRequest = new SaveMeetingRecordRequest();
//                    saveMeetingRecordRequest.setBusinessId(mIntentBean.businessId);
//                    saveMeetingRecordRequest.setBusinessType(16);
//                    mPresenter.getSaveMeetingRecord(saveMeetingRecordRequest, activity);
//
//                }
//            }
//
//            @Override
//            public void onFailed(int errCode, String errMsg) {
//                call_bg.setVisibility(View.GONE);
//                Log.e(TAG, "onFailed: " + errMsg);
//
//            }
//        });
//
//    }
//
//    public static class OrderTreatmentListDetailIntentBean implements Serializable {
//        //预约id
//        private int id;
//        //业务id
//        private int businessId;
//        //（14：待接诊 15：已完成 16：已作废 5：已驳回 7：已取消 2:待受理）
//        private int type;
//        //apply 或者  receiver
//        private String sourceType;
//        //医生id
//        private int doctorId;
//
//        /**
//         * @param id
//         * @param type （14：待接诊 15：已完成 16：已作废 5：已驳回 7：已取消 2:待受理）
//         */
//        public OrderTreatmentListDetailIntentBean(int id, int businessId, int type, String sourceType, int doctorId) {
//            this.id = id;
//            this.businessId = businessId;
//            this.type = type;
//            this.sourceType = sourceType;
//            this.doctorId = doctorId;
//        }
//    }
//
//    private void showTypePopup() {
//
//
//        typePopup = new XPopup.Builder(this)
//                .asCustom(new TreatmentTypePopup(this, new TreatmentTypePopup.OnCheckListener() {
//                    @Override
//                    public void onCheck(TreatmentTypePopup.TreatmentType type, String tips) {
//                        switch (type) {
//                            case VIDEO:
//                                mType = 3;
//                                typePopup.dismiss();
//                                SpannableString mSpannableString = new SpannableString("视频咨询(推荐)");
//                                mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_BLACK_333333)), 0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                                mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_ORANGE_FF782E)), 4, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                                type_item_tv.setText(mSpannableString);
//                                break;
//                            case WORD:
//                                mType = 4;
//                                typePopup.dismiss();
//                                type_item_tv.setText("文字咨询");
//                                break;
//                            case VOICE:
//                                typePopup.dismiss();
//                                mType = 0;
//                                type_item_tv.setText("语音咨询");
//                                break;
//                            case OFFLINE:
//                                typePopup.dismiss();
//                                mType = 2;
//                                type_item_tv.setText("线下门诊");
//                                break;
//                        }
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//                }));
//        typePopup.show();
//    }
//
//    /**
//     * 不够位数的在前面补0，保留num的长度位数字
//     *
//     * @param code
//     * @return
//     */
//    private String autoGenericCode(String code, int num) {
//        try {
//            String result = "";
//            // 保留num的位数
//            // 0 代表前面补充0
//            // num 代表长度为4
//            // d 代表参数为正数型
//            result = String.format("%0" + num + "d", Integer.parseInt(code));
//            return result;
//        } catch (Exception e) {
//            Logger.e("视联网设备号不正确,默认返回00000");
//            return "00000";
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        VVClient.getInstance().cancelRequest();
//
//        if (countDownTimerStart != null) {
//            countDownTimerStart.cancel();
//            countDownTimerStart = null;
//        }
//        if (countDownTimerEnd != null) {
//            countDownTimerEnd.cancel();
//            countDownTimerEnd = null;
//        }
//    }
//}
