package com.visionvera.psychologist.c.module.EvaluationGauge.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.activity.ProtocolActivity;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.ColletEventBus;
import com.visionvera.psychologist.c.eventbus.QuestionOptionBus;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDIctInfoResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDictInfoRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleQuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.contract.SelfAssessmentContract;
import com.visionvera.psychologist.c.module.EvaluationGauge.poup.TipsPopup;
import com.visionvera.psychologist.c.module.EvaluationGauge.presenter.ScaleDictInfoPresenter;
import com.visionvera.psychologist.c.module.counselling.bean.XindouPayRequestBean;
import com.visionvera.psychologist.c.module.counselling.view.BottomCheckOrderPayTypePopup;
import com.visionvera.psychologist.c.module.counselling.view.CommonCenterOneButtonPopup;
import com.visionvera.psychologist.c.module.share.ShareBean;
import com.visionvera.psychologist.c.module.share.ShareHandler;
import com.visionvera.psychologist.c.module.share.ShareUtils;
import com.visionvera.psychologist.c.utils.pay.PayUtil;
import com.visionvera.psychologist.c.widget.popup.SharePopup;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 自评量表首页
 * 目前是一个量表没测一次都需要支付一次
 */
public class SelfAssessmentGaugeActivity extends BaseMVPLoadActivity<SelfAssessmentContract.ScaleDictInfo.ScaleDictInfoView, ScaleDictInfoPresenter> {
    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.evaluation_module_tv_title)
    TextView tv_title;

    @BindView(R2.id.evaluation_module_self_assessment_title)
    TextView title;

    @BindView(R2.id.evaluation_module_self_assessment_introduction)
    TextView introduction;

    @BindView(R2.id.evaluation_module_self_assessment_all_questions_num)
    TextView assessment_all_questions_num;

    @BindView(R2.id.evaluation_module_self_assessment_account_expense)
    TextView account_expense;

    @BindView(R2.id.evaluation_module_self_assessment_continue_time)
    TextView continue_time;

    @BindView(R2.id.evaluation_module_self_assessment_suit_object)
    TextView suit_object;

    @BindView(R2.id.evaluation_module_self_assessment_report_type)
    TextView report_type;

    @BindView(R2.id.evaluation_module_start_evaluation_btn)
    Button evaluation_btn;

    @BindView(R2.id.evaluation_module_self_assessment_mainiv)
    ImageView img;

//    @BindView(R2.id.checkBox)
//    CheckBox checkBox;

    @BindView(R2.id.evaluation_module_collection)
    ImageView mCollection;

    @BindView(R2.id.evaluation_module_share)
    ImageView mShare;

    @BindView(R2.id.tvCash)
    TextView tvCash;
    @BindView(R2.id.tvCashPrice)
    TextView tvCashPrice;
    @BindView(R2.id.tvXindou)
    TextView tvXindou;
    @BindView(R2.id.tvXindouPrice)
    TextView tvXindouPrice;
    @BindView(R2.id.rlCash)
    RelativeLayout rlCash;
    @BindView(R2.id.rlXindou)
    RelativeLayout rlXindou;
    @BindView(R2.id.normal_view)
    ScrollView normal_view;
    @BindView(R2.id.assessment_gauge_pay_tip_title)
    TextView payTipTv;
    @BindView(R2.id.assessment_gauge_pay_layout)
    LinearLayout payLayout;

    private ScaleDIctInfoResponse.ResultBean resultBean;

    private static String GAUGE_INTENT_BEAN_STRING = "gaugeIntentBeanString";

    private GaugeIntentBean gaugeIntentBean;
    private Drawable.ConstantState notCollection;
    private Drawable.ConstantState collection;
    BasePopupView sharePop;
    private int mCheckPayType = -1;
    private BottomCheckOrderPayTypePopup mPayTypePopup = null;
    private int mPayTypeCheckPosition = 0;
    PayUtil payUtil;
    /**
     * 是否支持心豆支付
     */
    private boolean mIsCanXindouPay = true;
    /**
     * 是否已经收藏
     */
    public int collectStatus = 0;
    /**
     * 该值等于19时标识该订单已经支付过但是未答完题
     */
    private String payStatus;
    /**
     * 是否需要完善信息 1:需要完善 0:不需要
     */
    private String infoStatus;
    /**
     * 游客模式下测评次数
     */
    private int exerciseNum;


    public static void startActivity(Context context, GaugeIntentBean gaugeIntentBean) {
        context.startActivity(new Intent(context, SelfAssessmentGaugeActivity.class).putExtra(GAUGE_INTENT_BEAN_STRING, gaugeIntentBean));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_self_assessment_gauge;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void doYourself() {
        getIntentData();
        ARouter.getInstance().inject(this);
        initView();

        payUtil = new PayUtil(this);
        //获取量表详情
        getScaleDictInfo();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        mCollection.setVisibility(View.VISIBLE);
        mShare.setVisibility(View.VISIBLE);

        notCollection = getDrawable(R.drawable.evaluation_module_collection).getConstantState();
        collection = getDrawable(R.drawable.evaluation_module_collection_yellow).getConstantState();

    }

    /**
     * 从上一个页面传递过来的参数
     */
    private void getIntentData() {
        gaugeIntentBean = (GaugeIntentBean) getIntent().getSerializableExtra(GAUGE_INTENT_BEAN_STRING);
    }

    @OnClick({R2.id.evaluation_module_start_evaluation_btn, R2.id.evaluation_module_iv_back,
            R2.id.evaluation_module_collection, R2.id.evaluation_module_share, R2.id.evaluation_module_assessment_protocol, R2.id.rlCash, R2.id.rlXindou})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        //点击提交
        if (view.getId() == R.id.evaluation_module_start_evaluation_btn) {
            //已经登录
            if (accountService.getGetAccountInfo().isLogin) {
                if ("19".equals(payStatus)) {
                    net_getScaleQuestionOption();
                } else {
                    checkPayTtype();
                }
            } else { //未登录
                //未登录时必须完善信息的量或者已经免费做过三次测量的得先登录
                if ("1".equals(infoStatus) || exerciseNum >= 3) {
                    showPopup(TipsPopup.TIP_TYPE_LOGIN_EVPI);
                } else {
                    showPopup(TipsPopup.TIP_TYPE_LOGIN);
                }
            }
        } else if (view.getId() == R.id.evaluation_module_iv_back) {
            finish();
        } else if (view.getId() == R.id.evaluation_module_collection) {
            //收藏
            showLoadingDialog();
            ScaleDictInfoRequest scaleDictInfoRequest = new ScaleDictInfoRequest();
            scaleDictInfoRequest.setScaleId(gaugeIntentBean.scaleId);
            if (mCollection.getDrawable().getConstantState() == notCollection) {
                mPresenter.collectScaleDict(scaleDictInfoRequest, this);
            } else if (mCollection.getDrawable().getConstantState() == collection) {
                mPresenter.cancleCollectScaleDict(scaleDictInfoRequest, this);
            }

        } else if (view.getId() == R.id.evaluation_module_share) {
            //分享
            ShareBean shareBean = new ShareBean();

            shareBean.setUrl("https://slyl-mhsp-1301295327.cos.ap-beijing.myqcloud.com/mobile/appDownload/downloadAppsscale.html"); //粤心安 图片
//            shareBean.setUrl("https://slyl-mhsp-1301295327.cos.ap-beijing.myqcloud.com/apps/download/downloadApps.html");//心视联-抗疫特别版  图片
            shareBean.setTitle("下载链接");
            shareBean.setDesc("粤心安下载链接");
            shareBean.setThumb("http://show.psychol.visionveramh.com/img/aletailogo.png");

//            sharePop = new XPopup.Builder(this)
//                    .asCustom(new SharePopup(this, shareId -> {
//                        switch (shareId) {
//                            case R.id.evaluation_module_share_qq_layout:
//                                //qq好友
//                                shareBean.setShareMedia(SHARE_MEDIA.QQ);
//                                break;
//                            case R.id.evaluation_module_share_wechat_layout:
//                                //微信好友
//                                shareBean.setShareMedia(SHARE_MEDIA.WEIXIN);
//                                break;
//                            case R.id.evaluation_module_share_friend_layout:
//                                //微信朋友圈
//                                shareBean.setShareMedia(SHARE_MEDIA.WEIXIN_CIRCLE);
//                                break;
//                            case R.id.evaluation_module_share_zone_layout:
//                                //qq空间
//                                shareBean.setShareMedia(SHARE_MEDIA.QZONE);
//                                break;
//                            case R.id.evaluation_module_share_sina_layout:
//                                //新浪微博
//                                shareBean.setShareMedia(SHARE_MEDIA.SINA);
//                                break;
//                            default:
//                        }
//                        checkPermissionToshare(shareBean);
//                    }))
//                    .show();

        } else if (view.getId() == R.id.evaluation_module_assessment_protocol) {
            ProtocolActivity.startActivity(this, Constant.Url.request_base_url + Constant.WebUrl.consult, "粤心安心理咨询服务协议");

        } else if (view.getId() == R.id.rlCash) {
            checkPayType(0);
        } else if (view.getId() == R.id.rlXindou) {
            if (canXindouPay()) {
                checkPayType(1);
            } else {
                ToastUtils.showShort("本量表不支持心豆支付");
            }

        }
    }

    private void submit() {
        //判断需要支付金额为0元 直接跳到支付成功后页面
        double money = Double.parseDouble(resultBean.costPriceStr);
        //money=0/已支付未完成答题/未登录
        if (money == 0) {
            checkPayTtype();
        } else if ("19".equals(payStatus) || !accountService.getGetAccountInfo().isLogin) {
            showLoadingDialog();
            Map map = new HashMap();
            map.put("scaleId", gaugeIntentBean.scaleId);
            mPresenter.getQuestionOption(map);
        } else {
            //支付成功/已支付未完成答题  后页面
            if ("19".equals(payStatus)) {
                net_getScaleQuestionOption();
            } else {
                checkPayTtype();
            }
        }
    }

    /**
     * 区分支付方式
     */
    private void checkPayTtype() {
        if (getCheckPayType() == -1) {
            ToastUtils.showShort("请选择支付类型");
            return;
        }
        if (getCheckPayType() == 0) {
            //现金支付

            double money = Double.parseDouble(resultBean.costPriceStr);
            if (money == 0) {  //判断需要支付金额为0元 直接跳到支付成功后页面
                //支付成功  后页面
                showLoadingDialog();
                net_getScaleQuestionOption();
            } else {
                showPayTypePop(SelfAssessmentGaugeActivity.this, mPayTypeCheckPosition);
            }
        } else if (getCheckPayType() == 1) {
            //心豆支付
            if (resultBean.userHeartBeans < resultBean.getHeartBeans()) {
                //用户剩余心豆数量不足以支付本量表
                new XPopup.Builder(activity)
                        .dismissOnTouchOutside(false)
                        .dismissOnBackPressed(false)
                        .asCustom(new CommonCenterOneButtonPopup(activity, "您的心豆余额不足", "我知道了", () -> {

                        })).show();
            } else {
                //用户剩余心豆数量足以支付本量表
                showLoadingDialog();
                net_xindouPay();
            }
        }
    }

    private void showPopup(String flag) {
        TipsPopup timePopup = new TipsPopup(SelfAssessmentGaugeActivity.this, flag, new TipsPopup.OnTipListener() {
            @Override
            public void OnTipListener(String type) {
                if ("1".equals(type)) {
                    ARouter.getInstance()
                            .build(ARouterConstant.Account.AccountLoginActivity)
                            .navigation();
                } else if ("2".equals(type)) {
                    ARouter.getInstance()
                            .build(ARouterConstant.UserCenter.EditInfoActivity)
                            .navigation();
                } else if ("3".equals(type)) {
                    //非强制登录时点击取消 直接调用未登录获取问题与答案的接口
                    if (TipsPopup.TIP_TYPE_LOGIN.equals(flag)) {
                        showLoadingDialog();
                        Map map = new HashMap();
                        map.put("scaleId", gaugeIntentBean.scaleId);
                        mPresenter.getQuestionOption(map);
                    }
                }
            }
        });

        new XPopup.Builder(SelfAssessmentGaugeActivity.this)
                //如果不加这个，评论弹窗会移动到软键盘上面
                .moveUpToKeyboard(false)
                .asCustom(timePopup)
                .show();
    }

    /**
     * 登录状况下获取问题跟答案
     */
    private void net_getScaleQuestionOption() {
        ScaleDictInfoRequest request = new ScaleDictInfoRequest();
        request.setScaleId(gaugeIntentBean.scaleId);
        request.setPushRecordId(gaugeIntentBean.pushRecordId + "");
        if (resultBean != null) {
            request.orderNum = resultBean.orderNum;
        }
        mPresenter.getScaleQuestionOption(request, this);
    }

    private void net_xindouPay() {
        XindouPayRequestBean requestBean = new XindouPayRequestBean();
        requestBean.orderNum = resultBean.orderNum;
        requestBean.scaleId = gaugeIntentBean.scaleId;
        mPresenter.xindouPay(requestBean, this);
    }

    private int getCheckPayType() {
        return mCheckPayType;
    }

    private void checkPayType(int i) {
        normal_view.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
        if (i == 0) {
            if (rlCash.isActivated()) {
                checkCash(false);
                checkXindou(false);
                mCheckPayType = -1;
            } else {
                checkCash(true);
                checkXindou(false);
                mCheckPayType = 0;
            }

        } else if (i == 1) {
            if (rlXindou.isActivated()) {
                checkCash(false);
                checkXindou(false);
                mCheckPayType = -1;
            } else {
                checkCash(false);
                checkXindou(true);
                mCheckPayType = 1;
            }

        } else if (i == -1) {
            checkCash(false);
            checkXindou(false);
            mCheckPayType = -1;
        }
        if (getCheckPayType() == -1) {
            evaluation_btn.setBackground(getResources().getDrawable(R.drawable.evaluation_module_solid_a1c1f7_r6));
        } else {
            evaluation_btn.setBackground(getResources().getDrawable(R.drawable.evaluation_module_blue_btn_bg_corner6));
        }
    }

    private void checkCash(boolean check) {
        if (check) {
            rlCash.setActivated(true);
            tvCash.setTextColor(getResources().getColor(R.color.base_module_theme));
            tvCashPrice.setTextColor(getResources().getColor(R.color.base_module_theme));
        } else {
            rlCash.setActivated(false);
            tvCash.setTextColor(Color.parseColor("#666666"));
            tvCashPrice.setTextColor(Color.parseColor("#333333"));
        }

    }

    private void checkXindou(boolean check) {
        if (check) {
            rlXindou.setActivated(true);
            tvXindou.setTextColor(getResources().getColor(R.color.base_module_theme));
            tvXindouPrice.setTextColor(getResources().getColor(R.color.base_module_theme));
        } else {
            rlXindou.setActivated(false);
            tvXindou.setTextColor(Color.parseColor("#666666"));
            tvXindouPrice.setTextColor(Color.parseColor("#333333"));
        }

    }

    private void grayXindou() {
        rlXindou.setActivated(false);
        tvXindou.setTextColor(Color.parseColor("#666666"));
        tvXindouPrice.setTextColor(Color.parseColor("#666666"));
        mIsCanXindouPay = false;
    }

    private boolean canXindouPay() {
        return mIsCanXindouPay;
    }

    /**
     * 选择支付宝还是微信
     *
     * @param context
     * @param checkPosition
     */
    private void showPayTypePop(Context context, int checkPosition) {
        if (mPayTypePopup != null && mPayTypePopup.isShow()) {
            return;
        }
        if (resultBean == null) {
            ToastUtils.showShort("数据异常,请重新打开本页面");
            return;
        }
        mPayTypePopup = (BottomCheckOrderPayTypePopup) new XPopup.Builder(context).asCustom(new BottomCheckOrderPayTypePopup(context, checkPosition, "¥" + formatDouble(Double.parseDouble(resultBean.costPriceStr)), new BottomCheckOrderPayTypePopup.ResultListener() {
            @Override
            public void onCkecked(int checkPosition, String name) {
                mPayTypeCheckPosition = checkPosition;

                if (mPayTypeCheckPosition == 0) {
                    //支付宝支付
                    showLoadingDialog();
                    PayUtil.PayBean payBean = new PayUtil.PayBean();
                    payBean.totalAmount = Double.parseDouble(resultBean.costPriceStr);
                    payBean.goodsOrderNo = resultBean.orderNum;

                    if (accountService != null && accountService.getGetAccountInfo() != null) {
                        payBean.payUserId = accountService.getGetAccountInfo().userId;
                        payBean.payUserName = accountService.getGetAccountInfo().userName;
                        if (TextUtils.isEmpty(payBean.payUserId)) {
                            payBean.payUserId = "-1";
                        }
                        if (TextUtils.isEmpty(payBean.payUserName)) {
                            payBean.payUserName = "默认用户名";
                        }
                    }
                    payUtil.aliPay(payBean, new PayUtil.OnResultListener() {
                        @Override
                        public void onResult(int status, String msg) {
                            hideDialog();
                            if (status == 0) {
                                //支付成功
                                showLoadingDialog();
                                net_getScaleQuestionOption();
                            } else if (status == -1) {
                                ToastUtils.showShort("支付失败");
                            } else if (status == -2) {
                                ToastUtils.showShort("支付取消");
                            }
                        }
                    });
                } else if (mPayTypeCheckPosition == 1) {
                    //微信支付
                    showLoadingDialog();
                    PayUtil.PayBean payBean = new PayUtil.PayBean();
                    payBean.totalAmount = Double.parseDouble(resultBean.costPriceStr);
                    payBean.goodsOrderNo = resultBean.orderNum;
                    if (accountService != null && accountService.getGetAccountInfo() != null) {
                        payBean.payUserId = accountService.getGetAccountInfo().userId;
                        payBean.payUserName = accountService.getGetAccountInfo().userName;
                        if (TextUtils.isEmpty(payBean.payUserId)) {
                            payBean.payUserId = "-1";
                        }
                        if (TextUtils.isEmpty(payBean.payUserName)) {
                            payBean.payUserName = "默认用户名";
                        }
                    }
                    payUtil.weixinPay(payBean, new PayUtil.OnResultListener() {
                        @Override
                        public void onResult(int status, String msg) {
                            hideDialog();
                            if (status == 0) {
                                //支付成功
                                showLoadingDialog();
                                net_getScaleQuestionOption();
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

    /**
     * DecimalFormat转换最简便
     */
    public String formatDouble(Double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d);
        return format;
    }

    public void checkPermissionToshare(final ShareBean bean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //判断该应用是否有写SD卡权限，如果没有再去申请
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                share(bean);
                            } else {
                                ToastUtils.showShort("分享取消", SelfAssessmentGaugeActivity.this);
                            }
                        });
            } else {
                share(bean);
            }
        }
    }

    private void share(ShareBean bean) {
        ShareUtils shareUtils = new ShareUtils(this);
        shareUtils.beginShare(bean, new ShareHandler() {
            @Override
            public void shareSuccess() {
                if (sharePop != null && sharePop.isShow()) {
                    sharePop.dismiss();
                }
                ToastUtils.showShort("分享成功", SelfAssessmentGaugeActivity.this);
            }

            @Override
            public void shareFailed(String msg) {

                ToastUtils.showShort("分享失败:" + msg, SelfAssessmentGaugeActivity.this);
            }

            @Override
            public void shareCancle() {

                ToastUtils.showShort("分享取消", SelfAssessmentGaugeActivity.this);
            }
        });
    }


    @Override
    protected void initMVP() {
        mView = new SelfAssessmentContract.ScaleDictInfo.ScaleDictInfoView() {
            @Override
            public void onScaleDictInfo(ScaleDIctInfoResponse response, IBaseView.ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        showError("网络错误");
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        showError("出现错误");
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        showNormal();
                        showData(response);
                        break;
                }
            }

            @Override
            public void onScaleQuestionOption(BaseResponseBean3<QuestionOptionResponse> response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
//                        EventBus.getDefault().postSticky(new QuestionOptionBus(response.getResult()));

                        SelfAssessmentQuestionActivity.QuestionIntentBean questionIntentBean =
                                new SelfAssessmentQuestionActivity.QuestionIntentBean(
                                        resultBean.getRequirement(),
                                        resultBean.getName(),
                                        resultBean.getId(),
                                        resultBean.getGroupName(),
                                        gaugeIntentBean.type,
                                        gaugeIntentBean.pushRecordId,
                                        gaugeIntentBean.answerId,
                                        infoStatus,
                                        exerciseNum,
                                        resultBean.getCode(),
                                        resultBean.orderNum);
                        questionIntentBean.collectStatus = collectStatus;
                        SelfAssessmentQuestionActivity.startActivity(SelfAssessmentGaugeActivity.this, questionIntentBean, response.getResult());
                        //关闭本页.因为一次支付只能测一次
                        finish();
                        break;
                }
            }

            @Override
            public void onCollectScaleDict(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        ToastUtils.showShort(getString(R.string.evaluation_module_collection_success));
                        collectStatus = 1;
                        mCollection.setImageResource(R.drawable.evaluation_module_collection_yellow);
                        ColletEventBus colletEventBus = new ColletEventBus();
                        colletEventBus.collectStatus = 1;
                        colletEventBus.id = gaugeIntentBean.scaleId;
                        EventBus.getDefault().post(colletEventBus);
                        break;
                }
            }

            @Override
            public void onCancleCollectScaleDict(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        ToastUtils.showShort(errorMsg);
                        collectStatus = 0;
                        mCollection.setImageResource(R.drawable.evaluation_module_collection);
                        ColletEventBus colletEventBus = new ColletEventBus();
                        colletEventBus.collectStatus = 0;
                        colletEventBus.id = gaugeIntentBean.scaleId;
                        EventBus.getDefault().post(colletEventBus);
                        break;
                }
            }

            @Override
            public void onXindouPay(BaseResponseBean3 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        showLoadingDialog();
                        net_getScaleQuestionOption();
                        break;
                }
            }

            @Override
            public void onQuestionAndOption(BaseResponseBean3<QuestionOptionResponse> response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
//                        EventBus.getDefault().postSticky(new QuestionOptionBus(bean));

                        SelfAssessmentQuestionActivity.QuestionIntentBean questionIntentBean =
                                new SelfAssessmentQuestionActivity.QuestionIntentBean(
                                        resultBean.getRequirement(),
                                        resultBean.getName(),
                                        resultBean.getId(),
                                        resultBean.getGroupName(),
                                        gaugeIntentBean.type,
                                        gaugeIntentBean.pushRecordId,
                                        gaugeIntentBean.answerId,
                                        infoStatus,
                                        exerciseNum,
                                        resultBean.getCode(),
                                        resultBean.orderNum);
                        questionIntentBean.collectStatus = collectStatus;
                        SelfAssessmentQuestionActivity.startActivity(SelfAssessmentGaugeActivity.this, questionIntentBean, response.getResult());
                        //关闭本页.因为一次支付只能测一次
                        finish();
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new ScaleDictInfoPresenter(this, mView);
    }

    private void showData(ScaleDIctInfoResponse response) {
        resultBean = response.getResult();
        if (resultBean == null) {
            return;
        }
        assessment_all_questions_num.setText("共" + resultBean.getQuestionNums() + "道题目");
        if (resultBean.getApplicationScope() != null) {
            suit_object.setText(resultBean.getApplicationScope());
        } else {
            suit_object.setText("");
        }
        account_expense.setText("扣除" + resultBean.getHeartBeans() + "个心豆");
        if (resultBean.getDescription() != null) {
            introduction.setText(resultBean.getDescription());
        } else {
            introduction.setText("");
        }
        if (resultBean.getName() != null) {
            tv_title.setText(resultBean.getName());
            title.setText(resultBean.getName());
        } else {
            tv_title.setText("");
            title.setText("");
        }

        continue_time.setText("用时" + resultBean.getLimitTime() + "分钟");

//        humen_num.setText(resultBean.getHotNum() + "人已测");

        Glide.with(this).load(resultBean.getSmallImgUrl())
                .placeholder(R.drawable.evaluation_module_icon_empty)
                .dontAnimate()
                .into(img);

        if (resultBean.getReportTypeStr() != null) {
            report_type.setText(resultBean.getReportTypeStr());
        } else {
            report_type.setText("");
        }
        if (response.getResult().getCollectStatus() == 1) {
            //已收藏
            mCollection.setImageResource(R.drawable.evaluation_module_collection_yellow);
        } else {
            //未收藏
            mCollection.setImageResource(R.drawable.evaluation_module_collection);
        }
        collectStatus = resultBean.getCollectStatus();
        payStatus = resultBean.payStatus;
        infoStatus = resultBean.infoStatus;
        exerciseNum = resultBean.exerciseNum;

        judgeView();
    }

    @Override
    protected void onReload() {
//        getScaleDictInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        getScaleDictInfo();
    }

    /**
     * 根据状态展示底部view
     */
    private void judgeView() {
        //判断是否登录
        if (!accountService.getGetAccountInfo().isLogin) {
            payLayout.setVisibility(View.GONE);
            payTipTv.setVisibility(View.GONE);
            evaluation_btn.setText("开始测评");
        } else {
            if ("19".equals(payStatus)) {
                payLayout.setVisibility(View.GONE);
                payTipTv.setVisibility(View.VISIBLE);
                evaluation_btn.setText("开始测评");
            } else {
                payLayout.setVisibility(View.VISIBLE);
                payTipTv.setVisibility(View.GONE);
                evaluation_btn.setText("提交订单");

                tvCashPrice.setText("¥" + formatDouble(Double.parseDouble(resultBean.costPriceStr)));
                tvXindouPrice.setText(resultBean.getHeartBeans() + "");
                if (resultBean.isPayHeartBeans == 1) {
                    //本量表支持心豆支付
                } else {
                    //本量表不支持心豆支付
                    grayXindou();
                }
            }
        }
    }

    /**
     * 获取量表详情
     */
    private void getScaleDictInfo() {
        showLoading();
        ScaleDictInfoRequest request = new ScaleDictInfoRequest();
        request.setScaleId(gaugeIntentBean.scaleId);
        request.setPushRecordId(null);
        mPresenter.getScaleDictInfo(request, this);
    }

    public static class GaugeIntentBean implements Serializable {
        //量表id
        private int scaleId;

        /**
         * 量表类型:1必答|2自选
         * 当为必答题，则pushRecordId 和 answerId 必传。
         * 当为自选题，则pushRecordId 和 answerId 不必传。
         */
        private int type;
        //推送id
        private int pushRecordId;

        private int answerId;

        public GaugeIntentBean(int scaleId) {
            this.scaleId = scaleId;
        }

        public GaugeIntentBean(int scaleId, int type, int pushRecordId, int answerId) {
            this.scaleId = scaleId;
            this.type = type;
            this.pushRecordId = pushRecordId;
            this.answerId = answerId;
        }

    }


}
