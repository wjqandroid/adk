package com.visionvera.psychologist.c.module.ordertreatment.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.activity.ProtocolActivity;
import com.visionvera.library.util.EmojiFilter;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.dialog.CameraAlbumPopup1;
import com.visionvera.library.widget.dialog.CounselorTypePopup;
import com.visionvera.library.widget.dialog.ProblemTypePopup;
import com.visionvera.library.widget.dialog.TreatmentTypePopup;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultSelectTimeActivity;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.NewOrderConsultPresenter;
import com.visionvera.psychologist.c.module.usercenter.adapter.FeedBackImgAdapter;
import com.visionvera.psychologist.c.module.usercenter.bean.FeedBackImgBean;
import com.visionvera.psychologist.c.utils.photo.MyTakePhotoActivity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.visionvera.library.base.Constant.RequestReturnCode.OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code;


/**
 * @Classname:NewOrderTreatmentActivity
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version???v1.0???
 * @describe??? ??????:??????????????????????????????????????????????????????????????????????????????
 */


public class NewOrderTreatmentActivity extends BaseMVPActivity<OrderConsultContract.NewOrderConsult.NewOrderConsultView, NewOrderConsultPresenter> implements OnItemClickListener, OnItemChildClickListener {

    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;


    TextView tv_title;
    TextView tvRight;
    //????????????
    TextView select_time;
    //????????????
    TextView select_type_tv;
    //????????????
    TextView select_problem_type_tv;
    //??????
    RecyclerView mRecy;
    //??????
    EditText et_content;
    //0/200
    TextView tvTextCount;

    private List<FeedBackImgBean> picPathList = new ArrayList<>();
    private FeedBackImgAdapter feedBackImgAdapter;
    private BasePopupView cameraAlbumPopup;
    private BasePopupView typePopup;

    //?????????????????????
    private TimeCalendarBean.ResultBean.DateListBean returnTimeBean;
    //????????????????????????????????????null????????????????????????????????????
    private IntentBean mIntentBean;
    //?????????????????? 4????????????   5????????????   6????????????
    private int mType;
    //????????????????????????
    private Boolean isTextMake = true;
    private Boolean isVoiceMake = true;
    private Boolean isVideoMake = true;
    private LinearLayout evaluation_module_order_consult_select_time;
    private LinearLayout evaluation_module_order_consult_select_type;
    private LinearLayout evaluation_module_order_consult_problem_type;
    private TextView evaluation_module_order_consult_commit;
    private RelativeLayout rl_back;


    public static void startActivity(Context context, IntentBean intentBean) {
        Intent intent = new Intent(context, NewOrderTreatmentActivity.class);
        intent.putExtra("intentBean", intentBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.new_evaluation_module_activity_order_treatment;
    }

    @Override
    protected void doYourself() {
        ARouter.getInstance().inject(this);
        initIntentBean();
        initView();
    }

    private void initIntentBean() {
        mIntentBean = (IntentBean) getIntent().getSerializableExtra("intentBean");

    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);
        select_time = (TextView) findViewById(R.id.evaluation_module_select_time);
        select_type_tv = (TextView) findViewById(R.id.evaluation_module_select_type_tv);
        select_problem_type_tv = (TextView) findViewById(R.id.evaluation_module_select_problem_type_tv);
        mRecy = (RecyclerView) findViewById(R.id.evaluation_module_upload_appendix_recyclerview);
        et_content = (EditText) findViewById(R.id.et_content);
        tvTextCount = (TextView) findViewById(R.id.tv_text_count);
        evaluation_module_order_consult_select_time = (LinearLayout) findViewById(R.id.evaluation_module_order_consult_select_time);
        evaluation_module_order_consult_select_type = (LinearLayout) findViewById(R.id.evaluation_module_order_consult_select_type);
        evaluation_module_order_consult_problem_type = (LinearLayout) findViewById(R.id.evaluation_module_order_consult_problem_type);
        evaluation_module_order_consult_commit = (TextView) findViewById(R.id.evaluation_module_order_consult_commit);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        evaluation_module_order_consult_select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderConsultSelectTimeActivity.OrderConsultSelectTimeIntentBean intentBean
                        = new OrderConsultSelectTimeActivity.OrderConsultSelectTimeIntentBean(mIntentBean.UserId);
                OrderConsultSelectTimeActivity.startActivityForResult(NewOrderTreatmentActivity.this, intentBean);
            }
        });
        evaluation_module_order_consult_select_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //????????????????????????
                //??????????????????    4????????????   5????????????   6????????????   ??????
                if (Double.parseDouble(mIntentBean.consultingFee) < 0) {
                    isTextMake = false;
                }
                //??????
                if (Double.parseDouble(mIntentBean.consultingFeeVoice) < 0) {
                    isVoiceMake = false;
                }
                //??????
                if (Double.parseDouble(mIntentBean.consultingFeeVideo) < 0) {
                    isVideoMake = false;
                }
                showTypePopup(isTextMake, isVoiceMake, isVideoMake);
            }
        });
        evaluation_module_order_consult_problem_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //????????????   pop
                showProblemTypePopup();
            }
        });
        evaluation_module_order_consult_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //??????
                //???????????????????????????????????????
                if (!checkSelectEnouth()) {
                    return;
                }
                NewOrderTreatmentPayActivity.IntentBean intentBean = new NewOrderTreatmentPayActivity.IntentBean();
                intentBean.id = mIntentBean.id;
                //?????? UserId
                intentBean.UserId = mIntentBean.UserId;
                intentBean.zixunshiName = mIntentBean.name;
                intentBean.zixunshiTitle = mIntentBean.title;
                intentBean.zixunshiTags = mIntentBean.tags;
                intentBean.advisoryBody = mIntentBean.advisoryBody;
                intentBean.imgUri = mIntentBean.imgUri;
                //??????????????????
                intentBean.zixunTime = select_time.getText().toString();
                intentBean.zixunTimeId = returnTimeBean.getId() + "";
                //????????????
                intentBean.problemType = select_problem_type_tv.getText().toString();
                //????????????
                intentBean.zixunType = mType;
                //????????????
                intentBean.fujian = picPathList;
                //??????
                intentBean.beizhu = et_content.getText().toString().trim();
                //??????       //????????????     4????????????  5????????????  6 ????????????(??????)
                if (mType == 4) {
                    intentBean.price = Double.valueOf(mIntentBean.consultingFee);
                } else if (mType == 5) {
                    intentBean.price = Double.valueOf(mIntentBean.consultingFeeVoice);
                } else if (mType == 6) {
                    intentBean.price = Double.valueOf(mIntentBean.consultingFeeVideo);
                }
                NewOrderTreatmentPayActivity.startActivity(NewOrderTreatmentActivity.this, intentBean);
            }
        });
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //????????????   pop
//            showAdvisoryInstructionsPopup();

                //????????????????????????
                ProtocolActivity.startActivity(NewOrderTreatmentActivity.this, Constant.Url.request_base_url + Constant.WebUrl.advisory_guidelines, "????????????");
            }
        });
        //??????????????????????????????,???????????????????????????xml??????
        et_content.setFilters(new InputFilter[]{new EmojiFilter(), new InputFilter.LengthFilter(200)});
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvTextCount.setText(s.length() + "/200");
            }
        });
        tv_title.setText("????????????");

        mRecy.setLayoutManager(new GridLayoutManager(this, 4));

        picPathList.add(new FeedBackImgBean(FeedBackImgBean.CAMERA, ""));

        feedBackImgAdapter = new FeedBackImgAdapter(picPathList);
        feedBackImgAdapter.setOnItemClickListener(this);
        feedBackImgAdapter.addChildClickViewIds(R.id.evaluation_module_feedback_iv_delete);
        feedBackImgAdapter.setOnItemChildClickListener(this);
        mRecy.setAdapter(feedBackImgAdapter);

        //????????????     4????????????  5????????????  6 ????????????(??????)
        //???????????? ????????????????????????
        if (mIntentBean.selecTypeName.equals("????????????(??????)")) {
            SpannableString mSpannableString = new SpannableString(mIntentBean.selecTypeName);
            mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_BLACK_333333)), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_ORANGE_FF782E)), 4, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            select_type_tv.setText(mSpannableString);
            mType = 6;
        } else if (mIntentBean.selecTypeName.equals("????????????")) {
            mType = 4;
            select_type_tv.setText(mIntentBean.selecTypeName);
        } else if (mIntentBean.selecTypeName.equals("????????????")) {
            mType = 5;
            select_type_tv.setText(mIntentBean.selecTypeName);
        }

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FeedBackImgBean bean = picPathList.get(position);
        String picPath = bean.getPicPath();
        if (TextUtils.isEmpty(picPath)) {
            selectPhotoMethod(adapter, position);
        } else {
            ImageView imageView = view.findViewById(R.id.evaluation_module_feedback_iv);
            ArrayList<Object> list = new ArrayList<>();
            for (int i = 0; i < picPathList.size(); i++) {
                if (picPathList.get(i).getItemType() != FeedBackImgBean.CAMERA) {
                    list.add(picPathList.get(i).getPicPath());
                }
            }

            //????????????
            // ???????????????
            //srcView??????????????????????????????ImageView??????????????????????????????????????????????????????
            //???????????????????????????ImageView???scaleType???centerCrop???????????????????????????????????????Original_Size?????????Glide???????????????

            new XPopup.Builder(activity).asImageViewer(imageView, position, list, false, false, -1, -1, -1, false, new OnSrcViewUpdateListener() {
                @Override
                public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
                    // ????????????Pager?????????????????????????????????View
                    popupView.updateSrcView((ImageView) mRecy.getChildAt(position).findViewById(R.id.evaluation_module_feedback_iv));
                }
            }, new ImageLoader())
                    .show();
        }
    }

    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.NewOrderConsult.NewOrderConsultView() {

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new NewOrderConsultPresenter(this, mView);
    }


    class ImageLoader implements XPopupImageLoader {
        @Override
        public void loadImage(int position, @NonNull Object url, @NonNull ImageView imageView) {
            //????????????Target.SIZE_ORIGINAL??????????????????????????????????????????????????????????????????
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

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.evaluation_module_feedback_iv_delete) {
            if (picPathList.size() == 4) {
                FeedBackImgBean bean = picPathList.get(3);
                //getPicPath?????????????????????????????????+"
                if (TextUtils.isEmpty(bean.getPicPath())) {
                    picPathList.remove(position);
                } else {
                    picPathList.remove(position);
                    picPathList.add(new FeedBackImgBean(FeedBackImgBean.CAMERA, ""));
//                    bean.setItemType(FeedBackImgBean.CAMERA);
//                    bean.setPicPath("");
                }
            } else {
                picPathList.remove(position);
            }
            feedBackImgAdapter.notifyDataSetChanged();
        }
    }


    /**
     * ????????????
     */
    private void selectPhotoMethod(BaseQuickAdapter adapter, int position) {
        cameraAlbumPopup = new XPopup.Builder(this)
                .asCustom(new CameraAlbumPopup1(this,
                        () -> MyTakePhotoActivity.getPicPath(activity, MyTakePhotoActivity.photoAlbum, new MyTakePhotoActivity.OnGetPicPathListener() {
                            @Override
                            public void onComplete(String picPath) {
                                cameraAlbumPopup.dismiss();
                                parseImgData(picPath, position);
                            }
                        }), () -> MyTakePhotoActivity.getPicPath(activity, MyTakePhotoActivity.takePhoto, new MyTakePhotoActivity.OnGetPicPathListener() {
                    @Override
                    public void onComplete(String picPath) {
                        cameraAlbumPopup.dismiss();
                        parseImgData(picPath, position);
                    }
                })));
        cameraAlbumPopup.show();
    }

    /**
     * ???????????????????????????
     *
     * @param picPath
     */
    private void parseImgData(String picPath, int position) {
        if (!TextUtils.isEmpty(picPath)) {
            int size = picPathList.size();
            if (size == 4) {
                FeedBackImgBean bean = picPathList.get(3);
                bean.setItemType(FeedBackImgBean.IMG);
                bean.setPicPath(picPath);
            } else {
                picPathList.add(size - 1, new FeedBackImgBean(FeedBackImgBean.IMG, picPath));
            }
            feedBackImgAdapter.notifyDataSetChanged();
        }
    }




    private boolean checkSelectEnouth() {
        //??????
        if (returnTimeBean == null) {
            ToastUtils.showShort("???????????????");
            return false;
        }
        if (mType == 0) {
            ToastUtils.showShort("???????????????");
            return false;
        }
        if (select_problem_type_tv.getText().toString().isEmpty()) {
            ToastUtils.showShort("?????????????????????");
            return false;
        }
        return true;
    }


    //????????????   pop
    private void showAdvisoryInstructionsPopup() {
        TextView title = new TextView(this);
        title.setText("????????????");
        title.setPadding(0, 25, 0, 0);
        title.setGravity(Gravity.CENTER);
        title.getPaint().setFakeBoldText(true);
        title.setTextSize(18);
        title.setTextColor(Color.BLACK);
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setCustomTitle(title)
                .setMessage("?????????\" +\n" +
                        "                                    \"???????????????????????????????????????????????????????????????????????????????????????????????????\" +\n" +
                        "                                    \"????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????")//???
                .create();
        alertDialog1.show();
    }


    //????????????
    private void showProblemTypePopup() {
        typePopup = new XPopup.Builder(this)
                .asCustom(new ProblemTypePopup(this, new ProblemTypePopup.ResultListener() {
                    @Override
                    public void onPick(String name) {
                        select_problem_type_tv.setText(name);
                    }
                }));
        typePopup.show();
    }

    private void showTypePopup(Boolean isTextMake, Boolean isVoiceMake, Boolean isVideoMake) {
        //????????????     4???????????? 5????????????  6 ????????????(??????)
        typePopup = new XPopup.Builder(this)
                .asCustom(new CounselorTypePopup(this, () -> {
                    mType = 6;
                    typePopup.dismiss();
                    String content = "????????????????????????";
                    SpannableString mSpannableString = new SpannableString(content);
                    mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_BLACK_333333)), 0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_ORANGE_FF782E)), 4, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    select_type_tv.setText(mSpannableString);
                }, () -> {
                    mType = 4;
                    typePopup.dismiss();
                    String content = "????????????";
                    select_type_tv.setText(content);
                }, () -> {
                    mType = 5;
                    typePopup.dismiss();
                    String content = "????????????";
                    select_type_tv.setText(content);
                }, isTextMake, isVoiceMake, isVideoMake));
        typePopup.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code && resultCode == OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code) {
            returnTimeBean = (TimeCalendarBean.ResultBean.DateListBean) data.getSerializableExtra("data");
            String time = returnTimeBean.getStart().substring(0, 16) + "-" + returnTimeBean.getEnd().substring(11, 16);
            select_time.setText(time);
        }
    }

    public static class IntentBean implements Serializable {
        private int id;
        //??????  UserId
        private int UserId;
        //??????
        public String name = "";
        //??????
        public String title = "";
        //??????
        public String tags = "";
        //??????
        public String advisoryBody = "";
        //??????
        public String imgUri = "";
        //????????????     4???????????? 5??????  6??????
        public String selecTypeName = "";
        //??????      ????????????  ??????  ??????
        public String consultingFee = "";
        public String consultingFeeVoice = "";
        public String consultingFeeVideo = "";

        public IntentBean(int id, int UserId, String name, String title, String tags, String advisoryBody, String imgUri, String selecTypeName,
                          String consultingFee, String consultingFeeVoice, String consultingFeeVideo) {
            this.id = id;
            this.UserId = UserId;
            this.name = name;
            this.title = title;
            this.tags = tags;
            this.advisoryBody = advisoryBody;
            this.imgUri = imgUri;
            this.selecTypeName = selecTypeName;
            this.consultingFee = consultingFee;
            this.consultingFeeVoice = consultingFeeVoice;
            this.consultingFeeVideo = consultingFeeVideo;
        }

        @Override
        public String toString() {
            return "IntentBean{" +
                    "id=" + id +
                    ", UserId=" + UserId +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", tags='" + tags + '\'' +
                    ", advisoryBody='" + advisoryBody + '\'' +
                    ", imgUri='" + imgUri + '\'' +
                    ", selecTypeName='" + selecTypeName + '\'' +
                    ", consultingFee='" + consultingFee + '\'' +
                    ", consultingFeeVoice='" + consultingFeeVoice + '\'' +
                    ", consultingFeeVideo='" + consultingFeeVideo + '\'' +
                    '}';
        }
    }
}

