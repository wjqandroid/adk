package com.visionvera.psychologist.c.module.ordertreatment.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.activity.ProtocolActivity;
import com.visionvera.library.util.GlideImageLoader;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.counselling.activity.AllEvaluationListActivity;
import com.visionvera.psychologist.c.module.counselling.adapter.ConsultingEvaluationListAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.AvatarBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListRequest;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.module.ordertreatment.presenter.NewOrderTreatmentDetailActivityPresenter;
import com.visionvera.psychologist.c.module.share.ShareBean;
import com.visionvera.psychologist.c.module.share.ShareHandler;
import com.visionvera.psychologist.c.module.share.ShareUtils;
import com.visionvera.psychologist.c.utils.AdjustableTextView;
import com.visionvera.psychologist.c.widget.popup.SharePopup;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @Classname:NewOrderTreatmentDetailActivity
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version???v1.0
 * @describe??? ??????:???????????? ????????????    ????????????????????????????????????
 */

public class NewOrderTreatmentDetailActivity extends BaseMVPLoadActivity<IContract.OrderTreatmentDetailActivity.View, NewOrderTreatmentDetailActivityPresenter> {

    RelativeLayout rlTitleBar;
    //??????????????????
    TextView counselor_name;
    CircleImageView evaluationModuleCounselorDetailHeader;
    TextView counselor_level;
    TextView counselorAdvisoryBody;
    TextView counselor_field;
    TextView tvIntroduce;
    CheckBox checkBox;
    //??????????????????
    TextView tv_commit;
    //????????????
    TextView tvTextPrice;
    TextView tvVoicePrice;
    TextView tvVideoPrice;
    //??????????????????
    TextView btnTextMake;
    TextView btnVoiceMake;
    TextView btnVideoMake;
    //????????????
    RecyclerView reCommentList;
    LinearLayout llEvaluateLayout;
    TextView tvEvaluateNumber;
    TextView btnEvaluateLookAll;
    View llEvaluateLine;

    //??????Adapter
    private ConsultingEvaluationListAdapter consultingEvaluationListAdapter;
    //??????List
    private EvaluateListBean evaluateListBean;
    private DoctorDetailBean.ResultBean mResult;
    //????????????????????????    ??????????????????????????????
    private Boolean is_textOpenService = true;
    private Boolean is_voiceOpenService = true;
    private Boolean is_videoOpenService = true;
    //????????????????????????????????????null????????????????????????????????????
    public IntentBean intentBean = new IntentBean();
    //??????
    BasePopupView sharePop;


    public static void startActivity(Context context, IntentBean intentBean) {
        Intent intent = new Intent(context, NewOrderTreatmentDetailActivity.class);
        intent.putExtra(Constant.IntentKey.IntentBean, intentBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_order_treatment_detail;
    }

    @Override
    protected void doYourself() {
        rlTitleBar = findViewById(R.id.rlTitleBar);
        counselor_name = findViewById(R.id.evaluation_module_counselor_name);
        evaluationModuleCounselorDetailHeader = findViewById(R.id.evaluation_module_counselor_detail_header);
        counselorAdvisoryBody = findViewById(R.id.evaluation_module_counselor_level);
        counselorAdvisoryBody = findViewById(R.id.evaluation_module_advisory_body);
        counselor_field = findViewById(R.id.evaluation_module_counselor_field);
        tvIntroduce = findViewById(R.id.tv_introduce);
        checkBox = findViewById(R.id.checkBox);
        tv_commit = findViewById(R.id.evaluation_module_order_consult_btn);
        tvTextPrice = findViewById(R.id.tv_text_Price);
        tvVoicePrice = findViewById(R.id.tv_voice_Price);
        tvVideoPrice = findViewById(R.id.tv_video_Price);
        btnTextMake = findViewById(R.id.btn_text_make);
        btnVoiceMake = findViewById(R.id.btn_voice_make);
        btnVideoMake = findViewById(R.id.btn_video_make);
        reCommentList = findViewById(R.id.re_comment_list);
        llEvaluateLayout = findViewById(R.id.ll_evaluate);
        tvEvaluateNumber = findViewById(R.id.tv_evaluate_number);
        btnEvaluateLookAll = findViewById(R.id.btn_evaluate_look_all);
        llEvaluateLine = findViewById(R.id.ll_evaluate_line);
        ImageView iv_back = findViewById(R.id.evaluation_module_counselor_iv_back);
        ImageView evaluation_module_counselor_share = findViewById(R.id.evaluation_module_counselor_share);
        TextView order_treatment_protocol = findViewById(R.id.order_treatment_protocol);
        TextView order_informed_consent = findViewById(R.id.order_informed_consent);

        tv_commit.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        evaluation_module_counselor_share.setOnClickListener(this);
        order_treatment_protocol.setOnClickListener(this);
        order_informed_consent.setOnClickListener(this);
        btnVoiceMake.setOnClickListener(this);
        btnVideoMake.setOnClickListener(this);
        btnTextMake.setOnClickListener(this);
        btnEvaluateLookAll.setOnClickListener(this);

        initIntentData();
        initView();
        initRequestData();
    }

    private void initView() {
        ImmersionBar.with(this)
                //???????????????
                .transparentStatusBar()
                //?????????????????????
                .statusBarDarkFont(true)
                //?????????????????????????????????????????????????????????????????????????????????. false??????????????????????????????true???????????????????????????
                .fitsSystemWindows(false)
                .init();
        //??????????????????????????????w
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlTitleBar.getLayoutParams();
        params.setMargins(0, BarUtils.getStatusBarHeight(), 0, 0);
        rlTitleBar.setLayoutParams(params);
        //????????????
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    tv_commit.setBackground(getResources().getDrawable(R.drawable.bg_logout_unselect));
                } else {
                    tv_commit.setBackground(getResources().getDrawable(R.drawable.bg_logout));
                }
            }
        });
//        checkBox.setChecked(true);
    }

    private void initRequestData() {
        showLoading();
        //??????????????????
        DoctorDetailRequest request = new DoctorDetailRequest();
        request.setUserId(intentBean.UserId);
        mPresenter.getDoctorDetail(request, this);

        //????????????List
        EvaluateListRequest evaluateListRequest = new EvaluateListRequest();
        evaluateListRequest.setServiceUserId(intentBean.UserId);
        mPresenter.getUserEvaluateList(evaluateListRequest, this);
    }

    private void initIntentData() {
        //????????????
        IntentBean bean = (IntentBean) getIntent().getSerializableExtra(Constant.IntentKey.IntentBean);
        if (bean != null) {
            intentBean = bean;
        }
        Logger.i(intentBean.toString());
    }


    //    @OnClick({R2.id.evaluation_module_order_consult_btn, R2.id.evaluation_module_counselor_iv_back, R2.id.evaluation_module_counselor_share,
//            R2.id.order_treatment_protocol, R2.id.order_informed_consent, R2.id.btn_voice_make, R2.id.btn_text_make, R2.id.btn_video_make, R2.id.btn_evaluate_look_all})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }


        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_order_consult_btn) {
            //????????????????????????
            if (is_textOpenService || is_videoOpenService || is_voiceOpenService) {
                setJumpLogic("");
            } else {
                ToastUtils.showShort("??????????????????,???????????????");
            }
        } else if (viewId == R.id.evaluation_module_counselor_iv_back) {
            finish();
        } else if (viewId == R.id.evaluation_module_counselor_share) {
            share();
        } else if (viewId == R.id.order_treatment_protocol) {
            ProtocolActivity.startActivity(this, Constant.Url.request_base_url + Constant.WebUrl.consult, "?????????????????????????????????");
        } else if (viewId == R.id.order_informed_consent) {
            ProtocolActivity.startActivity(this, Constant.Url.request_base_url + Constant.WebUrl.consult, "???????????????");
        } else if (viewId == R.id.btn_voice_make) {
            //????????????????????????
            if (is_voiceOpenService) {
                setJumpLogic("????????????");
            } else {
                ToastUtils.showShort("????????????");
            }
        } else if (viewId == R.id.btn_video_make) {
            //????????????????????????
            if (is_videoOpenService) {
                setJumpLogic("????????????(??????)");
            } else {
                ToastUtils.showShort("????????????");
            }
        } else if (viewId == R.id.btn_text_make) {
            //????????????????????????
            if (is_textOpenService) {
                setJumpLogic("????????????");
            } else {
                ToastUtils.showShort("????????????");
            }
        } else if (viewId == R.id.btn_evaluate_look_all) {
            //??????????????????
            evaluateListBean.getResult().setImgUrl(mResult.getImageUrl());
            AllEvaluationListActivity.startActivity(this, evaluateListBean);
        }
    }

    //????????????????????????
    private void setJumpLogic(String typeName) {
        if (checkBox.isChecked()) {
            String priceCount = mResult.getConsultingFee();
            String voicePriceCount = mResult.getConsultingFeeVoice();
            String videoPriceCount = mResult.getConsultingFeeVideo();


            NewOrderTreatmentActivity.IntentBean intentBean = new NewOrderTreatmentActivity.IntentBean(this.intentBean.id,
                    this.intentBean.UserId, "", "", "", "", "", typeName, priceCount, voicePriceCount, videoPriceCount);

            //??????
            intentBean.name = counselor_name.getText().toString().trim();
            //??????
            intentBean.title = counselor_level.getText().toString().trim();
            //??????
            intentBean.tags = counselor_field.getText().toString().trim();
            //??????
            intentBean.advisoryBody = counselorAdvisoryBody.getText().toString().trim();
            //??????
            intentBean.imgUri = mResult.getImageUrl();
            NewOrderTreatmentActivity.startActivity(this, intentBean);
        } else {
            ToastUtils.showShort("??????????????????????????????");
        }
    }


    @Override
    protected void initMVP() {
        mView = new IContract.OrderTreatmentDetailActivity.View() {
            @Override
            public void onDoctorDetail(DoctorDetailBean bean, ResultType resultType, String errorMsg) {
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
                        //??????????????????
                        showData(bean.getResult());
                        break;
                    default:
                }
            }

            @Override
            public void onEvaluateList(EvaluateListBean bean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //??????????????????????????????????????????????????????????????????
//                        showError(getString(R.string.base_module_net_error));
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //?????????????????????????????????code?????????
//                        showError(getString(R.string.base_module_data_load_error));
//                        ToastUtils.showLong(getString(R.string.base_module_data_load_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //??????????????????????????????code?????????result??????null?????????result???????????????????????????????????????
                        showNormal();

                        evaluateListBean = bean;
                        //????????????
                        setEvaluateListAdapter(bean);
                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {
            }
        };
        mPresenter = new NewOrderTreatmentDetailActivityPresenter(this, mView);
    }


    //??????LIst   ????????? Adapter
    private void setEvaluateListAdapter(EvaluateListBean bean) {
        List<EvaluateListBean.ResultDTO.DataListDTO> dataList = bean.getResult().getDataList();
        if (dataList.size() > 0) {
            //????????????
            tvEvaluateNumber.setText("??????????????????" + dataList.size() + "???");
            llEvaluateLayout.setVisibility(View.VISIBLE);
            llEvaluateLine.setVisibility(View.VISIBLE);
            if (dataList.size() > 2) {
                btnEvaluateLookAll.setVisibility(View.VISIBLE);
                ArrayList<EvaluateListBean.ResultDTO.DataListDTO> list = new ArrayList<>();
                list.add(dataList.get(0));
                list.add(dataList.get(1));
                dataList = list;
            } else {
                btnEvaluateLookAll.setVisibility(View.GONE);
            }
            reCommentList.setLayoutManager(new LinearLayoutManager(NewOrderTreatmentDetailActivity.this, LinearLayoutManager.VERTICAL, false));
            //reCommentList.addItemDecoration(new SpacesItemDecoration(20));
            consultingEvaluationListAdapter = new ConsultingEvaluationListAdapter(NewOrderTreatmentDetailActivity.this, dataList);
            reCommentList.setAdapter(consultingEvaluationListAdapter);
            consultingEvaluationListAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

                }
            });
        } else {
            //??????????????????
            llEvaluateLayout.setVisibility(View.GONE);
            llEvaluateLine.setVisibility(View.GONE);
        }
    }

    //??????????????????
    private void showData(DoctorDetailBean.ResultBean result) {
        mResult = result;
        counselor_name.setText(result.getUsername());
        //??????
        if (result.getTitleName().length() > 0) {
            counselor_level.setVisibility(View.VISIBLE);
            counselor_level.setText(result.getTitleName());
        } else {
            counselor_level.setVisibility(View.GONE);
        }
        //??????
        counselorAdvisoryBody.setText(result.getHospitalName());
        //????????????
        //????????????
        String doctorIntroduction = !TextUtils.isEmpty(result.getDoctorIntroduction()) ? result.getDoctorIntroduction() : "??????";
        tvIntroduce.setText(doctorIntroduction);
        AdjustableTextView adjustableTextView = new AdjustableTextView(tvIntroduce, 2);//??????textView  ??????
        adjustableTextView.hiddenText();

        //??????
        StringBuilder allLable = new StringBuilder();
        for (DoctorDetailBean.ResultBean.LabelsBean label : result.getLabels()) {
            allLable.append("#" + label.getLableName() + "  ");
        }
        counselor_field.setText(allLable.toString());
        //??????
        if (!TextUtils.isEmpty(result.getImageUrl())) {
            GlideImageLoader.loadImage(activity, result.getImageUrl() + "", evaluationModuleCounselorDetailHeader);
        }
        //??????   ?????? ?????? ??????
        String priceCount = mResult.getConsultingFee();
        String priceStr = priceCount + "???/??????";
        SpannableString spannableString = new SpannableString(priceStr);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, priceCount.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(12, true), priceCount.length(), priceStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTextPrice.setText(spannableString);


        String voicePriceCount = mResult.getConsultingFeeVoice();
        String voicePriceStr = voicePriceCount + "???/??????";
        SpannableString voicepriceStrspannableString = new SpannableString(voicePriceStr);
        voicepriceStrspannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, voicePriceCount.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        voicepriceStrspannableString.setSpan(new AbsoluteSizeSpan(12, true), voicePriceCount.length(), voicePriceStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvVoicePrice.setText(voicepriceStrspannableString);


        String videoPriceCount = mResult.getConsultingFeeVideo();
        String videopriceStr = videoPriceCount + "???/??????";
        SpannableString videopriceStrspannableString = new SpannableString(videopriceStr);
        videopriceStrspannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, videoPriceCount.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        videopriceStrspannableString.setSpan(new AbsoluteSizeSpan(12, true), videoPriceCount.length(), videopriceStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvVideoPrice.setText(videopriceStrspannableString);

        //????????????????????????
        //??????????????????    4????????????   5????????????   6????????????     //0?????? 1??????
        if (mResult.getConsultingfeeStatus() == 0) {
            is_textOpenService = false;
            tvTextPrice.setVisibility(View.GONE);
            btnTextMake.setText("????????????");
            btnTextMake.setBackground(getResources().getDrawable(R.drawable.bg_ffcccccc_r4));
        }

        if (mResult.getConsultingfeevoiceStatus() == 0) {
            is_voiceOpenService = false;
            tvVoicePrice.setVisibility(View.GONE);
            btnVoiceMake.setText("????????????");
            btnVoiceMake.setBackground(getResources().getDrawable(R.drawable.bg_ffcccccc_r4));
        }

        if (mResult.getConsultingfeevideoStatus() == 0) {
            is_videoOpenService = false;
            tvVideoPrice.setVisibility(View.GONE);
            btnVideoMake.setText("????????????");
            btnVideoMake.setBackground(getResources().getDrawable(R.drawable.bg_ffcccccc_r4));
        }
        if (!is_textOpenService && !is_videoOpenService && !is_voiceOpenService) {
            tv_commit.setBackground(getResources().getDrawable(R.drawable.bg_ffcccccc_r4));
        }
    }


    @Override
    protected void onReload() {
        showLoading();
        initRequestData();
    }


    public static class IntentBean implements Serializable {

        private int id;
        private int UserId;

        public IntentBean() {
        }

        /**
         * @param id     ???Id???????????????????????????????????????????????????
         * @param UserId ???userId????????????????????????????????????
         */

        public IntentBean(int id, int UserId) {
            this.id = id;
            this.UserId = UserId;
        }

        @Override
        public String toString() {
            return "IntentBean{" +
                    "id=" + id +
                    ", doctorUserId=" + UserId +
                    '}';
        }
    }


    private void share() {
        //??????
        ShareBean shareBean = new ShareBean();

        shareBean.setUrl("https://fir.im/zasp");
        shareBean.setTitle("?????????");
        shareBean.setDesc("??????????????????");
//        shareBean.setThumb("http://img12.3lian.com/gaoqing02/02/93/37.jpg");


//        sharePop = new XPopup.Builder(this)
//                .asCustom(new SharePopup(this, new SharePopup.SharePopupListener() {
//                    @Override
//                    public void OnSharePopup(int shareId) {
//                        switch (shareId) {
//                            case R.id.evaluation_module_share_qq_layout:
//                                //qq??????
//                                shareBean.setShareMedia(SHARE_MEDIA.QQ);
//
//                                checkPermissionToshare(shareBean);
//
//                                break;
//                            case R.id.evaluation_module_share_wechat_layout:
//                                //????????????
//                                shareBean.setShareMedia(SHARE_MEDIA.WEIXIN);
//
//                                checkPermissionToshare(shareBean);
//                                break;
//                            case R.id.evaluation_module_share_friend_layout:
//                                //???????????????
//                                shareBean.setShareMedia(SHARE_MEDIA.WEIXIN_CIRCLE);
//
//                                checkPermissionToshare(shareBean);
//                                break;
//                            case R.id.evaluation_module_share_zone_layout:
//                                //qq??????
//                                shareBean.setShareMedia(SHARE_MEDIA.QZONE);
//
//                                checkPermissionToshare(shareBean);
//                                break;
//                            default:
//                        }
//                    }
//
//                }))
//                .show();
    }

    public void checkPermissionToshare(final ShareBean bean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //???????????????????????????SD????????????????????????????????????
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    share(bean);
                                } else {
                                    ToastUtils.showShort("????????????", activity);
                                }
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
                ToastUtils.showShort("????????????", activity);
            }

            @Override
            public void shareFailed(String msg) {

                ToastUtils.showShort("????????????:" + msg, activity);
            }

            @Override
            public void shareCancle() {

                ToastUtils.showShort("????????????", activity);
            }
        });
    }


    //??????List
    private static List<AvatarBean> avatarBeanList = new ArrayList<>();

    private void setAvatarList() {
        if (avatarBeanList.size() == 0) {
            avatarBeanList.add(new AvatarBean("??????", 4, 0, false));
            avatarBeanList.add(new AvatarBean("??????", 1, 5, false));
        }
    }


}
