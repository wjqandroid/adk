package com.visionvera.psychologist.c.module.EvaluationGauge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.CommitToMyEvaluationBus;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.myevaluation.MyEvaluationActivity;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListBean;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobBean;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.module.usercenter.presenter.EditInfoPresenter;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2019/11/12
 * 描述:提交成功后的页面
 */
public class SelfAssessmentCommitActivity extends BaseMVPLoadActivity<IContract.EditInfo.EditInfoView, EditInfoPresenter> {
    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.evaluation_module_tv_title)
    TextView tv_title;

    private static String COMMIT_INTENT_BEAN_STRING = "commitIntentBeanString";

    private CommitIntentBean commitIntentBean;
//    private BasePopupView basePopupView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);

        showLoading();
        mPresenter.getInforMation(this);

    }

    @Override
    protected void initMVP() {
        mView=new IContract.EditInfo.EditInfoView() {
            @Override
            public void onSaveInforMation(BaseResponseBean2 inforMationBean, ResultType resultType, String errorMsg) {


            }

            @Override
            public void onGetJobList(JobBean jobBean, ResultType resultType, String errorMsg) {

            }

            @Override
            public void onGetAreaList(AreaListBean areaListBean, ResultType resultType, String errorMsg, int on_click) {

            }


            @Override
            public void onGetInforMation(InforMationBean inforMationBean, ResultType resultType, String errorMsg) {
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
                        showNormal();
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                      /*  if (TextUtils.isEmpty(inforMationBean.getResult().getCardId())){
                            //弹出完善信息的弹框
                            basePopupView.show();
                        }else{
                            //不弹出完善信息的弹框
                        }*/
                        break;
                    default:
                }
            }

            @Override
            public void onSavePath(SavePathResponseBean responseBean, ResultType resultType, String errorMsg) {

            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new EditInfoPresenter(this, mView);
    }

    public static void startActivity(Context context, CommitIntentBean commitIntentBean) {
        context.startActivity(new Intent(context, SelfAssessmentCommitActivity.class).putExtra(COMMIT_INTENT_BEAN_STRING, commitIntentBean));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_self_assessment_commit;
    }

    @Override
    protected void doYourself() {
        initView();

        getIntentData();

    }

    private void getIntentData() {
        commitIntentBean = (CommitIntentBean) getIntent().getSerializableExtra(COMMIT_INTENT_BEAN_STRING);

        tv_title.setText(commitIntentBean.scaleName);
    }

    private void initView() {
        /*basePopupView = new XPopup.Builder(this)
                .asCustom(new PerfectInformationPopup(this, () -> {
                    //跳到编辑信息页
                    //todo 跳转编辑信息逻辑待商议,不完整
                    EditInfoActivity.startActivity(activity);
                    basePopupView.dismiss();
                }));
*/

    }

    @OnClick({R2.id.look_result, R2.id.back_mainpager, R2.id.evaluation_module_iv_back})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }


        int viewId = view.getId();
        if (viewId == R.id.look_result) {
            //跳转到-“我的测试”--“已测试”页面

            MyEvaluationActivity.MyEvaluationIntentBean myEvaluationIntentBean=new MyEvaluationActivity.MyEvaluationIntentBean(2);
            MyEvaluationActivity.startActivity(this,myEvaluationIntentBean);

            activityStackUtil.clearTopOfMy(this, MainActivity.class);
            EventBus.getDefault().postSticky(new CommitToMyEvaluationBus(1));



        } else if (viewId == R.id.back_mainpager) {
            activityStackUtil.clearTopOfMy(this, MainActivity.class);

        } else if (viewId == R.id.evaluation_module_iv_back) {
            //返回
            activityStackUtil.clearTopOfMy(this, SelfAssessmentGaugeActivity.class);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        activityStackUtil.clearTopOfMy(this, SelfAssessmentGaugeActivity.class);
    }

    @Override
    protected void onReload() {
        showLoading();
        mPresenter.getInforMation(this);
    }

    public static class CommitIntentBean implements Serializable {
        //量表名字
        private String scaleName;
        //量表id
        private int scaleId;

        public CommitIntentBean(String scaleName, int scaleId) {
            this.scaleName = scaleName;
            this.scaleId = scaleId;
        }
    }
}
