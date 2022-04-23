package com.visionvera.psychologist.c.module.counselling.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.counselling.bean.QueryPaysStatusRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.QueryPaysStatusResponseBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.OrderConsultSuccessActivityPresenter;
import com.visionvera.psychologist.c.module.ordertreatment.activity.NewOrderTreatmentDetailActivity;

import java.io.Serializable;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @Classname:OrderConsultSuccessActivity
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0描
 * @describe： 描述:预约咨询、诊疗————预约成功页面(查询支付结果)
 */

public class OrderConsultSuccessActivity extends BaseMVPActivity<OrderConsultContract.OrderConsultSuccessActivity.View, OrderConsultSuccessActivityPresenter> {

    @BindView(R2.id.tv_title)
    TextView tv_title;
    @BindView(R2.id.tvPrice)
    TextView tvPrice;
    @BindView(R2.id.ivSuccess)
    ImageView ivSuccess;
    @BindView(R2.id.tvStatus)
    TextView tvStatus;


    private IntentBean mIntentBean = new IntentBean();
    private int currentQueryCount = 0;
    private int maxQueryCount = 5;
    Handler handler = new Handler();

    public static void startActivity(Context context, IntentBean intentBean) {
        context.startActivity(new Intent(context, OrderConsultSuccessActivity.class).putExtra(Constant.IntentKey.IntentBean, intentBean));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_order_consult_success;
    }

    @Override
    protected void doYourself() {
        initIntentBean();
        initView();
    }


    private void initIntentBean() {
        //获取参数
        IntentBean bean = (IntentBean) getIntent().getSerializableExtra(Constant.IntentKey.IntentBean);
        if (bean != null) {
            mIntentBean = bean;
        }
        Logger.i(mIntentBean.toString());
    }

    private void initView() {
        if (mIntentBean.payment_type.equals("0")) {
            tvPrice.setText("¥" + formatDouble(mIntentBean.price));
            showSuccessView();
        } else {
            showLoadingDialog();
            //支付查询接口
            net_queryPayStatus(mIntentBean.appNum);
            tv_title.setText("正在查询...");
            tvPrice.setText("¥" + formatDouble(mIntentBean.price));
            showQueryingView();
        }

    }

    /**
     * DecimalFormat转换最简便
     */
    public String formatDouble(Double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d);
        return format;
    }

    @OnClick({R2.id.evaluation_module_order_consult_back_to_mainpager, R2.id.iv_back})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_order_consult_back_to_mainpager) {
            if (mIntentBean.from.equals("apply")) {
                activityStackUtil.clearTopOfMy(activity, CounselorDetailActivity.class);
//                if (mIntentBean.payment_type.equals(0)) {
//                    OrderConsultDetailActivity.OrderConsultDetailIntentBean intentBean
//                            = new OrderConsultDetailActivity.OrderConsultDetailIntentBean(mIntentBean.id, "apply", mIntentBean.id, "0");
//                    OrderConsultDetailActivity.startActivityForResult(this, intentBean);
//                }
            } else if (mIntentBean.from.equals("orderDetail")) {
                activityStackUtil.clearTopOfMy(activity, OrderConsultListActivity.class);
            }
            //fromType 来自哪个页面类型区分       咨询  consulting   诊疗  treatment
            OrderConsultDetailActivity.OrderConsultDetailIntentBean intentBean
                    = new OrderConsultDetailActivity.OrderConsultDetailIntentBean(
                    mIntentBean.id, mIntentBean.appNum,
                    mIntentBean.sourceType,
                    mIntentBean.doctorId,
                    "", 0, mIntentBean.fromType);

            OrderConsultDetailActivity.startActivityForResult(this, intentBean);
        } else if (viewId == R.id.iv_back) {
            if (mIntentBean.from.equals("apply")) {
                if (mIntentBean.fromType.equals("consulting")) {
                    //回到了咨询师详情页
                    activityStackUtil.clearTopOfMy(activity, CounselorDetailActivity.class);
                } else {
                    //回到了医生详情页
                    activityStackUtil.clearTopOfMy(activity, NewOrderTreatmentDetailActivity.class);
                }
            } else if (mIntentBean.from.equals("orderDetail")) {
                //相当于回到了列表页
                activityStackUtil.clearTopOfMyIncludeMy(activity, OrderConsultListActivity.class);
            }

        }
    }

    @Override
    public void onBackPressed() {
        activityStackUtil.clearTopOfMy(activity, MainActivity.class);
        super.onBackPressed();
    }

    private void showSuccessView() {
        ivSuccess.setVisibility(View.VISIBLE);
        tv_title.setText("支付成功");
        tvStatus.setText("支付成功");
    }

    private void showQueryingView() {
        ivSuccess.setVisibility(View.INVISIBLE);
        tv_title.setText("正在查询...");
        tvStatus.setText("正在查询...");
    }

    private void showQueryFailView() {
        ivSuccess.setVisibility(View.INVISIBLE);
        tv_title.setText("查询失败");
        tvStatus.setText("支付结果未查询到.请稍后在订单列表查看");
    }

    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.OrderConsultSuccessActivity.View() {
            @Override
            public void onQueryPayStatus(QueryPaysStatusResponseBean response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_NORMAL_DATAYES:
                        //本接口这里只代表正确返回结果了
                        if (response.code == 0) {

                            if (response.result.equals("支付成功")) {
                                //支付成功
                                ToastUtils.showLong("支付成功");
                                showSuccessView();
                            } else if (response.result.equals("支付失败")) {
                                //支付失败
                                if (currentQueryCount < maxQueryCount) {
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            showLoadingDialog();
                                            net_queryPayStatus(mIntentBean.appNum);
                                        }
                                    }, 3000);//3秒后执行Runnable中的run方法

                                } else {
                                    //达到了最多请求次数
                                    ToastUtils.showLong("支付结果未查询到.请稍后在订单列表查看");
                                    showQueryFailView();
                                }
                            }
                        } else if (response.code == 401) {
                            ToastUtils.showLong("无权限");
                        } else {
                            //本次没查询到结果
                            if (currentQueryCount < maxQueryCount) {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        showLoadingDialog();
                                        net_queryPayStatus(mIntentBean.appNum);

                                    }
                                }, 3000);//3秒后执行Runnable中的run方法

                            } else {
                                //达到了最多请求次数
                                ToastUtils.showLong("支付结果未查询到.请稍后在订单列表查看");
                                showQueryFailView();
                            }
                        }

                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new OrderConsultSuccessActivityPresenter(this, mView);
    }

    //支付结果查询接口
    //参数 订单编号 "orderNum":"fssdfsfdfsdfsdfds"
    private void net_queryPayStatus(String orderNum) {
        QueryPaysStatusRequestBean requestBean = new QueryPaysStatusRequestBean();
        requestBean.orderNum = orderNum;
        mPresenter.queryPayStatus(requestBean, this);
        currentQueryCount++;
        Logger.i("第" + currentQueryCount + "次查询");
    }

    public static class IntentBean implements Serializable {
        public String appNum = "";
        public Double price = 0.0;
        //apply 表示正常申请流程 orderDetail 表示在订单详情的待付款状态支付的
        public String from = "apply";
        //预约id
        public int id;
        //apply 患者申请的单子 或者  receiver 医生要求给患者复诊的单子
        public String sourceType = "apply";
        //咨询师的id
        public int doctorId;
        //支付类型
        // payment_type：“0”  代表支付0元时，跳转申请成功页面
        public String payment_type = "-1";
        //来自哪个页面类型区分       咨询  consulting   诊疗  treatment
        public String fromType;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }

    }


}
