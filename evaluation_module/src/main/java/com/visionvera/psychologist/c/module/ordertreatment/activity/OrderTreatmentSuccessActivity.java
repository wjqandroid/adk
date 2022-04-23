//package com.visionvera.psychologist.c.module.ordertreatment.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.TextView;
//
//import com.orhanobut.logger.Logger;
//import com.visionvera.library.base.BaseActivity;
//import com.visionvera.library.base.Constant;
//import com.visionvera.library.util.OneClickUtils;
//import com.visionvera.psychologist.c.R;
//import com.visionvera.psychologist.c.R2;
//import com.visionvera.psychologist.c.module.MainActivity;
//import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderResponseBean;
//
//import java.io.Serializable;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * @author: 刘传政
// * @date: 2020-01-07 09:13
// * QQ:1052374416
// * 作用:预约诊疗成功界面
// * 注意事项:
// */
//
//public class OrderTreatmentSuccessActivity extends BaseActivity {
//
//    @BindView(R2.id.tv_title)
//    TextView tv_title;
//
//    @BindView(R2.id.evaluation_module_order_number)
//    TextView order_number;
//
//    @BindView(R2.id.evaluation_module_counselor)
//    TextView counselor;
//
//    @BindView(R2.id.evaluation_module_order_consult_time)
//    TextView consult_time;
//
//    @BindView(R2.id.evaluation_module_order_consult_type)
//    TextView consult_type;
//
//    @BindView(R2.id.evaluation_module_order_consult_remark)
//    TextView consult_remark;
//
//    //默认创建一个，保证取值不null。所以里边的默认值很重要
//    public IntentBean intentBean = new IntentBean(new SubmitOrderResponseBean.ResultBean());
//
//
//    public static void startActivity(Context context, IntentBean intentBean) {
//        Intent intent = new Intent(context, OrderTreatmentSuccessActivity.class);
//        intent.putExtra(Constant.IntentKey.IntentBean, intentBean);
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.evaluation_module_activity_order_treatment_success;
//    }
//
//    @Override
//    protected void doYourself() {
//        parseIntent();
//        initView();
//
//    }
//
//
//
//
//    private void parseIntent() {
//        //获取参数
//        IntentBean bean = (IntentBean) getIntent().getSerializableExtra(Constant.IntentKey.IntentBean);
//        if (bean != null) {
//            intentBean = bean;
//        }
//        Logger.i(intentBean.toString());
//    }
//
//
//    private void initView() {
//        tv_title.setText(getString(R.string.evaluation_module_order_success));
//        showData(intentBean.resultBean);
//    }
//
//    @OnClick({R.id.evaluation_module_order_consult_back_to_mainpager, R.id.iv_back})
//    public void onClick(View view) {
//        if (OneClickUtils.isFastClick()) {
//            return;
//        }
//        int viewId = view.getId();
//        if (viewId == R.id.evaluation_module_order_consult_back_to_mainpager) {
//            activityStackUtil.clearTopOfMy(activity, MainActivity.class);
//        } else if (viewId == R.id.iv_back) {
//            activityStackUtil.clearTopOfMy(activity, MainActivity.class);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        activityStackUtil.clearTopOfMy(activity, MainActivity.class);
//        super.onBackPressed();
//    }
//
//
//    public static class IntentBean implements Serializable {
//
//        private SubmitOrderResponseBean.ResultBean resultBean;
//
//        public IntentBean(SubmitOrderResponseBean.ResultBean resultBean) {
//            this.resultBean = resultBean;
//        }
//    }
//
//    private void showData(SubmitOrderResponseBean.ResultBean bean) {
//        order_number.setText(bean.getAppNum() + "");
//        counselor.setText(bean.getDoctorName() + "");
//        String startTime = bean.getStartTime();
//        String endTime = bean.getEndTime();
//        if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
//            if (startTime.length() >= 16 && endTime.length() >= 16) {
//                String consultTimeString = bean.getStartTime().substring(0, 16) + "-" + bean.getEndTime().substring(11, 16);
//                consult_time.setText(consultTimeString);
//            }
//        }
//        consult_type.setText(bean.getDiagnosisModeName() + "");
//        consult_remark.setText(bean.getComments() + "");
//    }
//}
