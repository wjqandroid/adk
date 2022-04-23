package com.visionvera.psychologist.c.module.EvaluationGauge.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.util.TimeUtils;
import com.visionvera.library.widget.NoScrollViewPager1;
import com.visionvera.library.widget.dialog.CenterPopup;
import com.visionvera.library.widget.dialog.Confirm1Dialog;
import com.visionvera.library.widget.dialog.ConfirmDialog;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.ColletEventBus;
import com.visionvera.psychologist.c.eventbus.QuestionOptionBus;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.CommitRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.DoAssessResponseBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionTypeIntentData;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDictInfoRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleQuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.contract.SelfAssessmentContract;
import com.visionvera.psychologist.c.module.EvaluationGauge.fragment.QuestionBaseFragment;
import com.visionvera.psychologist.c.module.EvaluationGauge.fragment.SelfAssessmentQuestionFragment1;
import com.visionvera.psychologist.c.module.EvaluationGauge.fragment.SelfAssessmentQuestionFragment2;
import com.visionvera.psychologist.c.module.EvaluationGauge.fragment.SelfAssessmentQuestionFragment4;
import com.visionvera.psychologist.c.module.EvaluationGauge.poup.TipsPopup;
import com.visionvera.psychologist.c.module.EvaluationGauge.presenter.ScaleQuestionOptionPresenter;
import com.visionvera.psychologist.c.module.share.ShareBean;
import com.visionvera.psychologist.c.module.share.ShareHandler;
import com.visionvera.psychologist.c.module.share.ShareUtils;
import com.visionvera.psychologist.c.module.usercenter.activity.EditInfoActivity;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.widget.PerfectInformationPopup;
import com.visionvera.psychologist.c.widget.TagTextView;
import com.visionvera.psychologist.c.widget.popup.SharePopup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2019/11/14
 * 描述:自测量表的题目
 */
public class SelfAssessmentQuestionActivity extends BaseMVPLoadActivity<SelfAssessmentContract.ScaleQuestionOption.ScaleQuestionOptionView,
        ScaleQuestionOptionPresenter> {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    @BindView(R2.id.evaluation_module_self_assessment_question_viewpager)
    NoScrollViewPager1 question_vp;


    @BindView(R2.id.point_out_layout_long)
    LinearLayout point_out_layout_long;


    @BindView(R2.id.point_out_content_long)
    TagTextView point_out_content_long;

    @BindView(R2.id.point_out_fold_iv)
    ImageView point_out_fold_iv;

    @BindView(R2.id.evaluation_module_progress_bar_step)
    ProgressBar evaluation_module_progress_bar_step;


    @BindView(R2.id.evaluation_module_fragment_last_question_btn)
    Button last_question_btn;

    @BindView(R2.id.evaluation_module_fragment_next_question_btn)
    Button next_question_btn;
    @BindView(R2.id.tv_question_title)
    TextView tv_question_title;

    @BindView(R2.id.evaluation_module_tv_title)
    TextView tv_title;
    @BindView(R2.id.evaluation_module_collection)
    ImageView mCollection;
    @BindView(R2.id.evaluation_module_share)
    ImageView mShare;

    private static String QUESTION_INTENT_BEAN_STRING = "QUESTION_INTENT_BEAN_STRING";
    private static String QUESTION_INTENT_BEAN_STRING_ALL_QUESTION = "QUESTION_INTENT_BEAN_STRING_ALL_QUESTION";
    //题目的fragment的集合
    private List<BaseFragment> questionFragmentList = new ArrayList<>();
    //题目的总数量
    private int allQuestionNumber = 0;

    //activity之间传递数据的bean类
    private QuestionIntentBean questionIntentBean;

    private ViewPagerAdapter vpAdapter;

    /**
     * 需要提交的数据bean。
     */
    public CommitRequest commitRequest = new CommitRequest();
    /**
     * commitRequest的二级集合，存储选项答案bean。
     */
    public List<CommitRequest.DataArrayBean> commitAnswerList = new ArrayList<>();
//    private ScaleQuestionOptionResponse scaleQuestionOptionResponse;
    private QuestionOptionResponse scaleQuestionOptionResponse;
    /**
     * 完善信息的弹窗
     */
    private BasePopupView basePopupView;
    boolean isLongTips = true;
    private Drawable.ConstantState notCollection;
    private Drawable.ConstantState collection;
    BasePopupView sharePop;

    public static void startActivity(Context context, QuestionIntentBean questionIntentBean,QuestionOptionResponse scaleQuestionOptionResponse) {
        context.startActivity(new Intent(context, SelfAssessmentQuestionActivity.class)
                .putExtra(QUESTION_INTENT_BEAN_STRING_ALL_QUESTION, scaleQuestionOptionResponse)
                .putExtra(QUESTION_INTENT_BEAN_STRING, questionIntentBean));

    }

    public static void startActivity(Context context, QuestionIntentBean questionIntentBean, ScaleQuestionOptionResponse scaleQuestionOptionResponse) {
        context.startActivity(new Intent(context, SelfAssessmentQuestionActivity.class)
                .putExtra(QUESTION_INTENT_BEAN_STRING, questionIntentBean)
                .putExtra(QUESTION_INTENT_BEAN_STRING_ALL_QUESTION, scaleQuestionOptionResponse));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_self_assessment_question;
    }

    @OnClick({R2.id.point_out_layout,
            R2.id.evaluation_module_iv_back,
            R2.id.evaluation_module_fragment_next_question_btn,
            R2.id.evaluation_module_fragment_last_question_btn,
            R2.id.evaluation_module_collection, R2.id.evaluation_module_share})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }

        int id = view.getId();

        if (id == R.id.point_out_layout) {
            pointOutLayoutVisible();
        } else if (id == R.id.evaluation_module_iv_back) {
            existQuestion();
        } else if (id == R.id.evaluation_module_fragment_next_question_btn) {
            if(question_vp.getCurrentItem() == allQuestionNumber - 1){ //最后一题时点击就提交
                submitAnswer();
            }else {
                nextQuestionMethod();
            }

        } else if (id == R.id.evaluation_module_fragment_last_question_btn) {
            //上一题
            if (questionFragmentList.get(question_vp.getCurrentItem()) instanceof QuestionBaseFragment) {
                int currentItemPosition = question_vp.getCurrentItem();
                QuestionBaseFragment fragment = (QuestionBaseFragment) questionFragmentList.get(currentItemPosition - 1);
                //因为接口的页数开始是1
                int position = fragment.mCurrentQuestionNum - 1;
                switchLastQuestion(position);
            }
            lastButtonLayout();
        } else if (view.getId() == R.id.evaluation_module_collection) {
            //收藏
            showLoadingDialog();
            ScaleDictInfoRequest scaleDictInfoRequest = new ScaleDictInfoRequest();
            scaleDictInfoRequest.setScaleId(questionIntentBean.scaleId);
            if (mCollection.getDrawable().getConstantState() == notCollection) {
                mPresenter.collectScaleDict(scaleDictInfoRequest, this);
            } else if (mCollection.getDrawable().getConstantState() == collection) {
                mPresenter.cancleCollectScaleDict(scaleDictInfoRequest, this);
            }

        } else if (view.getId() == R.id.evaluation_module_share) {
            //分享
            ShareBean shareBean = new ShareBean();

            shareBean.setUrl("https://slyl-mhsp-1301295327.cos.ap-beijing.myqcloud.com/apps/download/downloadApps.html");
            shareBean.setTitle("下载链接");
            shareBean.setDesc("粤心安下载链接");
            shareBean.setThumb("http://m.qpic.cn/psc?/V11QdL8K2me4rD/FuOlPseFkXy6zf1*h9xoNv8zAPMsrr5P0OjyMm.lBkM8amUHFKX3YI.WdpaZ3mtNDELwJjU4Stmmer86FbJ2v50oVuyW5MZw3feMGDvysU0!/anull&bo=AAIAAgACAAIDCSw!&rf=photolist&t=5");

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

        }
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
                                ToastUtils.showShort("分享取消", SelfAssessmentQuestionActivity.this);
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
                ToastUtils.showShort("分享成功", SelfAssessmentQuestionActivity.this);
            }

            @Override
            public void shareFailed(String msg) {

                ToastUtils.showShort("分享失败:" + msg, SelfAssessmentQuestionActivity.this);
            }

            @Override
            public void shareCancle() {

                ToastUtils.showShort("分享取消", SelfAssessmentQuestionActivity.this);
            }
        });
    }

    //当前最后一题时点击提交
    private void submitAnswer(){
        commitRequest.setEndTime(TimeUtils.getYearMonthDayHourMinuteSecond(new Date()));

        commitRequest.setGroup(questionIntentBean.groupName);

        /**
         * type 量表类型:1必答|2自选
         * 当为必答题，则pushRecordId 和 answerId 必传。
         * 当为自选题，则pushRecordId 和 answerId 不必传。
         */
        commitRequest.setType(questionIntentBean.type);
        commitRequest.setPushRecordId(questionIntentBean.pushRecordId);
        commitRequest.setAnswerId(questionIntentBean.answerId);
        commitRequest.userMacId = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).getString(Constant.SP.UserInfo.deviceId, "");
        commitRequest.orderNum = questionIntentBean.orderNum;

        if (accountService!=null && accountService.getGetAccountInfo()!=null){
            //判断是否登录
            if (!accountService.getGetAccountInfo().isLogin) {
                showLoadingDialog();
                commitRequest();

                /*new XPopup.Builder(activity)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new CenterPopup(activity,
                                CenterPopup.CenterPopupType.twoButton,
                                activity.getString(R.string.evaluation_module_commit_confirm),
                                activity.getString(R.string.evaluation_module_commit_confirm_notice),
                                activity.getString(R.string.evaluation_module_cancle),
                                activity.getString(R.string.evaluation_module_to_login),
                                centerPopup -> {
                                    centerPopup.dismiss();
                                    showLoadingDialog();
                                    commitRequest();

                            },
                                centerPopup -> {
                            centerPopup.dismiss();
                            ARouter.getInstance()
                                    .build(ARouterConstant.Account.AccountLoginActivity)
                                    .withInt(Constant.IntentKey.requestReturnCode,Constant.RequestReturnCode.SelfAssessmentQuestion_To_AccountLogin_Code)
                                    .navigation(SelfAssessmentQuestionActivity.this,Constant.RequestReturnCode.SelfAssessmentQuestion_To_AccountLogin_Code);
                        }))

                        .show();*/

            } else {
                //已经登录账号,调用接口，看信息是否完善。
                showLoadingDialog();
                mPresenter.getInforMation(this);

            }
        }
    }

    public void nextQuestionMethod() {
        if (questionFragmentList.get(question_vp.getCurrentItem()) instanceof QuestionBaseFragment) {
            QuestionBaseFragment fragment = ((QuestionBaseFragment) questionFragmentList.get(question_vp.getCurrentItem()));

            int mAnswerId = fragment.mAnswerId;
//                int mQuestionId = fragment.mIntentData.getId();
            int mQuestionId = fragment.mIntentData.getScaleNum();
            int mIsForword=fragment.mIntentData.getIsForward();

            // TODO: 2019/11/11  要根据不同题型进行判断，如果题型为语音输入，则没有这个mAnswerId字段。
            if (fragment.mAnswerId == 0) {
                ToastUtils.showShort(" 请选择一项答案");
            } else {
                //保存信息到提交的bean里面
                CommitRequest.DataArrayBean dataArrayBean = new CommitRequest.DataArrayBean();

                dataArrayBean.setQid(mQuestionId);
                dataArrayBean.setAid(mAnswerId);
                dataArrayBean.setIsForward(mIsForword);
                if (commitAnswerList.size() >= fragment.mIntentData.getScaleNum()) {
                    //当已经填写过后，要修改。
                    commitAnswerList.set(fragment.mIntentData.getScaleNum() - 1, dataArrayBean);
                } else {
                    //没有填写过，直接添加进list中。
                    commitAnswerList.add(dataArrayBean);
                }
                commitRequest.setDataArray(commitAnswerList);

//                if (fragment.mCurrentQuestionNum == fragment.mIntentData.getTotalQuestion()) {
//
//                } else {
//
//                }
                //下一题
                switchNextQuestion((((QuestionBaseFragment) questionFragmentList.get(question_vp.getCurrentItem()))).mCurrentQuestionNum);
                nextButtonLayout();
            }
        }
    }


    /**
     * 下一题动态更改底部按钮的布局和按钮文字。
     */
    private void nextButtonLayout() {
        if (question_vp.getCurrentItem() == 0) {
            //当前是第一题
            last_question_btn.setVisibility(View.GONE);

        } else if (question_vp.getCurrentItem() == allQuestionNumber - 1) {
            //当前最后一题
            next_question_btn.setText("提交");
            last_question_btn.setVisibility(View.VISIBLE);

        } else {
            last_question_btn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 上一题动态更改底部按钮的布局和按钮文字。
     */
    private void lastButtonLayout() {
        if (question_vp.getCurrentItem() == 0) {
            last_question_btn.setVisibility(View.GONE);
        } else if (question_vp.getCurrentItem() == allQuestionNumber - 1) {
            //当前最后一题
            next_question_btn.setText("提交");
            last_question_btn.setVisibility(View.VISIBLE);
        } else {
            next_question_btn.setText("下一题");
            last_question_btn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 退出答题
     */
    private void existQuestion() {
        Confirm1Dialog confirmDialog = new Confirm1Dialog(this, "您确定退出答题吗？", "", "", new Confirm1Dialog.OnCloseListener() {
            @Override
            public void onClick(AlertDialog dialog, int buttonType) {
                if (buttonType == ConfirmDialog.LEFT) {
                    //取消
                } else if (buttonType == ConfirmDialog.RIGHT) {
                    finish();
                }
            }
        });
        //外部点击可消失
        confirmDialog.setOutCancel(true);
        confirmDialog.show();
    }


    /**
     * 提示语是完整还是缩写。
     */
    private void pointOutLayoutVisible() {

        if (isLongTips) {
            point_out_content_long.setMaxLines(1);
            point_out_content_long.setEllipsize(TextUtils.TruncateAt.END);
            point_out_fold_iv.setImageResource(R.drawable.evaluation_module_point_out_down);
        } else {
            point_out_content_long.setMaxLines(Integer.MAX_VALUE);
            point_out_content_long.setEllipsize(null);
            point_out_fold_iv.setImageResource(R.drawable.evaluation_module_point_out_up);
        }
        isLongTips = !isLongTips;
    }

    private void showPopup(String type){
        TipsPopup timePopup = new TipsPopup(SelfAssessmentQuestionActivity.this, type, new TipsPopup.OnTipListener() {
            @Override
            public void OnTipListener(String type) {
                if("1".equals(type)){
                    ARouter.getInstance()
                            .build(ARouterConstant.Account.AccountLoginActivity)
                            .navigation();
                }else if("2".equals(type)){
                    ARouter.getInstance()
                            .build(ARouterConstant.UserCenter.EditInfoActivity)
                            .navigation();
                }else {
                    commitRequest();
                }
            }
        });

        new XPopup.Builder(SelfAssessmentQuestionActivity.this)
                //如果不加这个，评论弹窗会移动到软键盘上面
                .moveUpToKeyboard(false)
                .asCustom(timePopup)
                .show();
    }

    @Override
    protected void doYourself() {
        getIntentData();

        initData();

//        initPermission();

        initView();


        showAllQuestion(scaleQuestionOptionResponse);
    }

    private void initView() {

        //填入量表开始时间
        commitRequest.setStartTime(TimeUtils.getYearMonthDayHourMinuteSecond(new Date()));

        //填入量表id
        if (questionIntentBean != null) {
            commitRequest.setScaleId(questionIntentBean.scaleId);
        }


        vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        question_vp.setAdapter(vpAdapter);

        question_vp.setOffscreenPageLimit(3);

        question_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                evaluation_module_progress_bar_step.setProgress(position + 1);
                String question = scaleQuestionOptionResponse.getQuestionList().get(position).getQuestion();
                tv_question_title.setText(position + 1 + "");
                SpannableMethod((position + 1 + "/" + allQuestionNumber).length(),
                        position + 1 + "/" + allQuestionNumber + "  " + question,
                        tv_question_title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        basePopupView = new XPopup.Builder(this)
                .asCustom(new PerfectInformationPopup(this, getString(R.string.evaluation_module_perfect_test_information_notice), () -> {
                    //跳到编辑信息页
                    //todo 跳转编辑信息逻辑待商议,不完整
                    EditInfoActivity.startActivity(activity);
                    basePopupView.dismiss();
                }));
    }

    private void SpannableMethod(int scaleNumLength, String allText, TextView textView) {
        //在textview中设置不同的字体颜色  https://blog.csdn.net/mq2553299/article/details/78033581
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(allText);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.base_module_theme));
        builder.setSpan(foregroundColorSpan, 0, scaleNumLength, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(builder);
    }

    private void initData() {
        if (questionIntentBean != null) {

            point_out_content_long.setText(questionIntentBean.requirement);
            List<String> tags = new ArrayList<>();
            tags.add("Tips:");
            point_out_content_long.setContentAndTag(questionIntentBean.requirement, tags);
            tv_title.setText(questionIntentBean.assessmentName);
            mCollection.setVisibility(View.VISIBLE);
            mShare.setVisibility(View.VISIBLE);
            notCollection = getDrawable(R.drawable.evaluation_module_collection).getConstantState();
            collection = getDrawable(R.drawable.evaluation_module_collection_yellow).getConstantState();
            if (questionIntentBean.collectStatus == 1) {
                //已收藏
                mCollection.setImageResource(R.drawable.evaluation_module_collection_yellow);
            } else {
                //未收藏
                mCollection.setImageResource(R.drawable.evaluation_module_collection);
            }
        }

    }
    /**
     * 从上一个页面获取提示语。（按道理，应该从本页面请求的接口中获取该数据，不过接口已经写完，就暂时先这样传递过来数值）
     */
    private void getIntentData() {
        questionIntentBean = (QuestionIntentBean) getIntent().getSerializableExtra(QUESTION_INTENT_BEAN_STRING);
        scaleQuestionOptionResponse = (QuestionOptionResponse) getIntent().getSerializableExtra(QUESTION_INTENT_BEAN_STRING_ALL_QUESTION);
//        scaleQuestionOptionResponse = (ScaleQuestionOptionResponse) getIntent().getSerializableExtra(QUESTION_INTENT_BEAN_STRING_ALL_QUESTION);
    }

    @Override
    protected void initMVP() {
        mView = new SelfAssessmentContract.ScaleQuestionOption.ScaleQuestionOptionView() {
            @Override
            public void onDoAssess(BaseResponseBean3<DoAssessResponseBean> response, IBaseView.ResultType resultType, String errorMsg) {
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

//                        ToastUtils.showShort(errorMsg);
//                        SelfAssessmentCommitActivity.CommitIntentBean commitIntentBean
//                                =new SelfAssessmentCommitActivity.CommitIntentBean(questionIntentBean.assessmentName,questionIntentBean.scaleId);
//                        SelfAssessmentCommitActivity.startActivity(activity,commitIntentBean);

                        DoAssessResponseBean doAssessResponseBean = response.getResult();
                        EvaluationResultActivity.EvaluationResultIntentBean intentBean
                                = new EvaluationResultActivity.EvaluationResultIntentBean(
                                doAssessResponseBean.scaleId,
                                doAssessResponseBean.serialNumber,
                                questionIntentBean.assessmentName,
                                questionIntentBean.orderCode);
                        EvaluationResultActivity.startActivity(SelfAssessmentQuestionActivity.this, intentBean);
                        finish();

                        break;
                }
            }

            @Override
            public void onGetInforMation(InforMationBean inforMationBean, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showLong(getString(R.string.base_module_data_load_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        InforMationBean.ResultBean result = inforMationBean.getResult();

                        if (TextUtils.isEmpty(result.getBirthday()) || result.getSex() == 0) {
                            //提示完善信息
//                            basePopupView.show();
                            //游客模式做题超过三次之后或者必须完善信息的量表 完善信息是强制性的
                            if("1".equals(questionIntentBean.infoStatus)){
                                showPopup(TipsPopup.TIP_TYPE_INFORMATION_EVPI);
                            }else {
                                showPopup(TipsPopup.TIP_TYPE_INFORMATION);
                            }
                        } else {
                            commitRequest();
                        }

                        break;
                    default:
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
                        mCollection.setImageResource(R.drawable.evaluation_module_collection_yellow);
                        ColletEventBus colletEventBus = new ColletEventBus();
                        colletEventBus.collectStatus = 1;
                        colletEventBus.id = questionIntentBean.scaleId;
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
                        mCollection.setImageResource(R.drawable.evaluation_module_collection);
                        ColletEventBus colletEventBus = new ColletEventBus();
                        colletEventBus.collectStatus = 0;
                        colletEventBus.id = questionIntentBean.scaleId;
                        EventBus.getDefault().post(colletEventBus);
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new ScaleQuestionOptionPresenter(this, mView);
    }

    /**
     * 设置所有题目和答案（在主activity中获取到所有的题目和选项，再设置到每一个答题卡片的fragment中）
     *
     * @param result
     */
    private void showAllQuestion(QuestionOptionResponse result) {
        allQuestionNumber = result.getTotalQuestion();
        if (allQuestionNumber > 0) {
            evaluation_module_progress_bar_step.setMax(allQuestionNumber);
            //默认第一道题
            evaluation_module_progress_bar_step.setProgress(1);
            //首选展示第一个pager
            String question = scaleQuestionOptionResponse.getQuestionList().get(0).getQuestion();
            tv_question_title.setText(1 + "");
            SpannableMethod((1 + "/" + allQuestionNumber).length(),
                    1 + "/" + allQuestionNumber + "  " + question,
                    tv_question_title);


            for (int i = 0; i < allQuestionNumber; i++) {
                QuestionOptionResponse.QuestionListBean questionBean = result.getQuestionList().get(i);

                QuestionTypeIntentData questionTypeIntentData = new QuestionTypeIntentData();
                questionTypeIntentData.setId(questionBean.getId());
                questionTypeIntentData.setQuestion(questionBean.getQuestion());
                questionTypeIntentData.setScaleNum(questionBean.getScaleNum());
                questionTypeIntentData.setTotalQuestion(result.getTotalQuestion());
                questionTypeIntentData.setIsForward(questionBean.getIsForward());

                if (questionBean.getOptionalListInfo()!=null && questionBean.getOptionalListInfo().size()>0){
                    //单选或者多选（有选项的题目）
                    List<QuestionTypeIntentData.Option> optionList = new ArrayList<>();

                    for (int j = 0; j < questionBean.getOptionalListInfo().size(); j++) {

                        QuestionOptionResponse.QuestionListBean.OptionalListInfoBean optionalListInfoBean = questionBean.getOptionalListInfo().get(j);

                        QuestionTypeIntentData.Option option = questionTypeIntentData.new Option();
                        option.setId(optionalListInfoBean.getId());
                        option.setOptionalValue(optionalListInfoBean.getOptionalValue());
                        option.setForwardPoint(optionalListInfoBean.getForwardPoint());
                        option.setBackwardPoint(optionalListInfoBean.getBackwardPoint());
                        optionList.add(option);

                    }

                    questionTypeIntentData.setOptionList(optionList);

                    if (questionBean.getType()==1){//单选题型
                        questionFragmentList.add(SelfAssessmentQuestionFragment1.getInstance(questionTypeIntentData));
                    }else if (questionBean.getType()==2){//填空题
                        questionFragmentList.add(SelfAssessmentQuestionFragment2.getInstance(questionTypeIntentData));
                    }else if (questionBean.getType()==3){//多选题型

                    }else if (questionBean.getType()==4){//滑块单选
                        questionFragmentList.add(SelfAssessmentQuestionFragment4.getInstance(questionTypeIntentData));
                    }

                }else{
                    //填空题（没有选项的题目）
                    questionFragmentList.add(SelfAssessmentQuestionFragment2.getInstance(questionTypeIntentData));
                }
            }
            vpAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.showShort("题目数量为空");
        }


    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }


    @Override
    protected void onReload() {

    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return questionFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return questionFragmentList.size();
        }


    }


    public static class QuestionIntentBean implements Serializable {
        //提示语
        public String requirement;
        //量表名称
        public String assessmentName;
        //量表id
        public int scaleId;
        //分组名称
        public String groupName;
        /**
         * 是否已经收藏
         */
        public int collectStatus = 0;

        /**
         * 量表类型:1必答|2自选
         * 当为必答题，则pushRecordId 和 answerId 必传。
         * 当为自选题，则pushRecordId 和 answerId 不必传。
         */
        private int type;
        //推送id
        private int pushRecordId;

        private int answerId;

        /**
         * 是否需要完善信息 1:需要完善 0:不需要
         */
        private String infoStatus;
        /**
         * 游客模式下测评次数
         */
        private int exerciseNum;
        /**
         * 订单code 来自前面详情页 后面测量结果页要用
         */
        private String orderCode;
        /**
         * 获取详情时得到的订单id 答完题提交时要传给后台
         */
        public String orderNum;

        public QuestionIntentBean(String requirement, String assessmentName, int scaleId, String groupName,
                                  int type, int pushRecordId, int answerId,String infoStatus,int exerciseNum,String code,String orderNum) {
            this.requirement = requirement;
            this.assessmentName = assessmentName;
            this.scaleId = scaleId;
            this.groupName = groupName;
            this.type = type;
            this.pushRecordId = pushRecordId;
            this.answerId = answerId;
            this.infoStatus = infoStatus;
            this.exerciseNum = exerciseNum;
            this.orderCode = code;
            this.orderNum = orderNum;
        }
    }

    /**
     * 下一题
     */
    public void switchNextQuestion(int position) {
        if (position < allQuestionNumber) {
            question_vp.setCurrentItem(position);
        }
    }

    /**
     * 上一题
     */
    public void switchLastQuestion(int position) {
        if (position <= allQuestionNumber) {
            question_vp.setCurrentItem(position);
        }
    }

    /**
     * 提交量表
     */
    public void commitRequest() {
        showLoadingDialog();
        mPresenter.getDoAssess(commitRequest, this);
    }


    @Override
    public void onBackPressed() {
        existQuestion();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.RequestReturnCode.SelfAssessmentQuestion_To_AccountLogin_Code:

//                commitRequest();

                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onQuestionOptionBus(QuestionOptionBus message) {
        scaleQuestionOptionResponse = message.getResponse();
        showAllQuestion(scaleQuestionOptionResponse);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
