package com.visionvera.psychologist.c.module.usercenter.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.orhanobut.logger.Logger;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.util.EmojiFilter;
import com.visionvera.library.util.GlideImageLoader;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.dialog.CameraAlbumPopup1;
import com.visionvera.library.widget.dialog.SexPickPopup;
import com.visionvera.library.widget.dialog.bottompopup.BottomPopupBean;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.eventbus.CommitToMyEvaluationBus;
import com.visionvera.psychologist.c.module.usercenter.adapter.CityListAdapter;
import com.visionvera.psychologist.c.module.usercenter.adapter.SelecteCityAdapter;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListBean;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListRequestBean;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.SelecteCityBean;
import com.visionvera.psychologist.c.module.usercenter.bean.UploadPicResponseBean;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.module.usercenter.presenter.EditInfoPresenter;
import com.visionvera.psychologist.c.utils.OnClickUtil;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;
import com.visionvera.psychologist.c.utils.cos.TencentCosManager;
import com.visionvera.psychologist.c.utils.photo.MyTakePhotoActivity;
import com.visionvera.psychologist.c.widget.popup.JobPopup;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rxhttp.wrapper.param.RxHttp;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * author:lilongfeng
 * date:2019/11/29
 * 描述:编辑信息
 */
@Route(path = ARouterConstant.UserCenter.EditInfoActivity)
public class EditInfoActivity extends BaseMVPLoadActivity<IContract.EditInfo.EditInfoView, EditInfoPresenter> {

    private ImageView ivPersonHead;
    private TextView tv_job;
    private TextView et_email;
    private EditText et_idcard_number;
    private TextView tv_input_birthday;
    private TextView tv_nickname;
    private TextView tv_sex;
    private TextView tvAddress;
    private ImageView iv_back;

    private JobPopup jobPopup;
    private BasePopupView AdressTypePopup;
    private List<JobBean.ResultBean> jobTypeList = new ArrayList<>();

    private String regionCode = "";
    private String originalCode = "";
    private String jobId = "";
    private String photoUrl = "";
    private int photoId = 0;
    //临时保存选择的性别信息
    private int mSex = 0;
    private boolean isOrNotChangeBirtyday = false;
    private boolean isOrNotChangeSex = false;

    private BasePopupView sexPopupView;
    private List<BottomPopupBean> cameraAlbumList = new ArrayList<>();
    private BasePopupView cameraAlbumPopup;
    //已选城市列表list
    private SelecteCityAdapter selecteCityAdapter;
    //选城市列表list
    private CityListAdapter cityListAdapter;
    //已选择的地区集合
    private List<SelecteCityBean> selecteCityList = new ArrayList<>(5);
    //城市集合 接口获取
    private List<AreaListBean.ResultBean> areaList = new ArrayList<>();
    //点击标识  防止重复点击
    private int on_click;
    private int on_callback;
    private TextView tv_title;
    private int cityList_position;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, EditInfoActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_edit_info;
    }

    @Override
    protected void doYourself() {
        initView();

        initData();
    }

    private void initView() {
        iv_back = findViewById(R.id.iv_back);
        tvAddress = findViewById(R.id.tv_address);
        tv_sex = findViewById(R.id.tv_sex);
        tv_nickname = findViewById(R.id.tv_nickname);
        tv_input_birthday = findViewById(R.id.tv_input_birthday);
        et_idcard_number = findViewById(R.id.et_idcard_number);
        et_email = findViewById(R.id.et_email);
        tv_job = findViewById(R.id.tv_job);
        ivPersonHead = findViewById(R.id.ivPersonHead);
        LinearLayout ll_personHead = findViewById(R.id.ll_personHead);
        LinearLayout ll_job = findViewById(R.id.ll_job);
        LinearLayout ll_sex = findViewById(R.id.ll_sex);
        LinearLayout ll_nickname = findViewById(R.id.ll_nickname);
        TextView tvSubmit = findViewById(R.id.tvSubmit);

        iv_back.setOnClickListener(this);
        ll_personHead.setOnClickListener(this);
        ll_job.setOnClickListener(this);
        ll_sex.setOnClickListener(this);
        ll_nickname.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);

        //只允许输入0123456789x   https://www.jianshu.com/p/691b00e750c7
        et_idcard_number.setKeyListener(DigitsKeyListener.getInstance("0123456789Xx"));

        et_email.setFilters(new InputFilter[]{new EmojiFilter()});


        cameraAlbumList.add(new BottomPopupBean("相册", 1));
        cameraAlbumList.add(new BottomPopupBean("拍照", 2));
        cameraAlbumList.add(new BottomPopupBean("取消", 3));

    }

    private void initData() {

        showLoading();
        mPresenter.getInforMation(this);

    }


    //    @OnClick({R2.id.iv_back, R2.id.ll_personHead,
//            R2.id.ll_job, R2.id.tvSubmit,
//            R2.id.ll_input_birthday, R2.id.ll_sex, R2.id.ll_nickname, R.id.tv_address})
    public void onClick(View v) {
        if (OneClickUtils.isFastClick()) {
            return;
        }



        KeyboardUtils.hideSoftInput(this);
        int id = v.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.ll_personHead) {   //选择照片
            selectPhotoMethod();
        } else if (id == R.id.ll_job) {      //选择所在职业

            showLoadingDialog();
            JobRequest jobRequest = new JobRequest();
            jobRequest.setGroupName("job_class_dict");
            mPresenter.getJobList(jobRequest, this);
        } else if (id == R.id.ll_sex) {//选择性别
            if (isOrNotChangeSex) {
                sexPicker();
            } else {
                ToastUtils.showShort(getString(R.string.evaluation_module_sex_dont_change));
            }
        } else if (id == R.id.ll_nickname) {
            InputNickNameActivity.startActivityForResult(this);
        } else if (id == R.id.tv_address) {
            AreaListRequestBean areaListRequestBean = new AreaListRequestBean();
            areaListRequestBean.setId("0");
            areaListRequestBean.setLevel("0");
            on_click = (int) (Math.random() * 10 + 1);
            mPresenter.getAreaList(areaListRequestBean, EditInfoActivity.this, on_click);
            showAddressTypePopup();
        } else if (id == R.id.tvSubmit) {
            if (TextUtils.isEmpty(tv_input_birthday.getText().toString().trim())) {
                ToastUtils.showLong("出生日期未填写");
                return;
            }
            if (mSex == 0) {
                ToastUtils.showLong("性别未选择");
                return;
            }
            String cardId = "";
            /**
             * 回显的身份证号，加密了，里面包含*字符。如果有*了，提交接口cardId字段提交“”空字符串就好。
             * 如果没有回显的身份证号，那就填写。照常提交。
             */
            if (et_idcard_number.getText().toString().trim().contains("*")) {
                cardId = "";
            } else {
                cardId = et_idcard_number.getText().toString().trim();
            }
            if (TextUtils.isEmpty(tvAddress.getText().toString().trim())) {
                ToastUtils.showLong("地址未选择");
                return;
            }

            Map<String, String> params = new HashMap<>();
            params.put("email", et_email.getText().toString().trim());
            params.put("photoId", photoId + "");
            params.put("nickname", tv_nickname.getText().toString().trim());
            params.put("professionId", jobId);
            params.put("cardId", cardId);
            params.put("sex", mSex + "");
            params.put("birthday", tv_input_birthday.getText().toString().trim());
            params.put("regionCode", regionCode);//地址提交
            params.put("originalCode", originalCode);

            mPresenter.saveInforMation(RxPartMapUtils.toRequestBodyOfStringMap(params), this);

            showLoadingDialog();
        }


    }


    /**
     * 选择出生日期
     */
    private void SelectBirthday() {
        if (!isOrNotChangeBirtyday) {
            ToastUtils.showShort("出生日期不能修改");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();

        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        startDate.set(1900, 0, 1);
        endDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        new TimePickerBuilder(this, (date, v) -> {//选中事件回调

            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tv_input_birthday.setText(sdf.format(date));
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setRangDate(startDate, endDate)//起始终止日期设定
                .setDate(endDate)
                .build().show();
    }

    /**
     * 性别选择弹窗
     */
    private void sexPicker() {
        sexPopupView = new XPopup.Builder(this)
                .asCustom(new SexPickPopup(this, new SexPickPopup.ResultListener() {
                    @Override
                    public void onPick(@SexPickPopup.Sex int type, String name) {
                        mSex = type;
                        tv_sex.setText(name);
                    }
                }));
        sexPopupView.show();
    }

    /**
     * 获取照片
     */
    private void selectPhotoMethod() {
        cameraAlbumPopup = new XPopup.Builder(this)
                .asCustom(new CameraAlbumPopup1(this, () ->
                        //相册
                        MyTakePhotoActivity.getPicPath(activity, MyTakePhotoActivity.photoAlbum, picPath -> {
                            if (!TextUtils.isEmpty(picPath)) {
//                                uploadPic(picPath);
                                Glide.with(activity)
                                        .load(picPath)
                                        .dontAnimate()
                                        .into(ivPersonHead);
                                uploadPicByCos(picPath);
                            }
                        })
                        , () ->

                        //拍照
                        MyTakePhotoActivity.getPicPath(activity, MyTakePhotoActivity.takePhoto, picPath -> {
                            if (!TextUtils.isEmpty(picPath)) {
                                Glide.with(activity)
                                        .load(picPath)
                                        .dontAnimate()
                                        .into(ivPersonHead);
                                uploadPicByCos(picPath);
                            }
                        })
                ));
        cameraAlbumPopup.show();
    }

    private void uploadPicByCos(String picPath) {
        showLoadingDialog("上传中...", false);
        String houzhui = "png";
        String[] strArr = picPath.split("\\.");
        if (strArr.length > 0) {
            houzhui = strArr[strArr.length - 1];
        }

        String cosPath = System.currentTimeMillis() + "." + houzhui;
        TencentCosManager.getInstance(this).upload(cosPath, picPath, new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {

            }
        }, new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                EditInfoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        ToastUtils.showShort("上传成功");
                        SavePathRequestBean requestBean = new SavePathRequestBean();
                        requestBean.prefix = "userIcon";
                        requestBean.description = "C端头像图片Android";
                        requestBean.type = 8;
                        //后台非得要后半段的路径.那这就截取吧
                        String str1 = result.accessUrl.substring(0, result.accessUrl.indexOf("myqcloud.com"));
                        String str2 = result.accessUrl.substring(str1.length() + 12, result.accessUrl.length());
                        requestBean.filePath = str2;
                        requestBean.originalFilename = picPath;
                        mPresenter.savePath(requestBean, EditInfoActivity.this);
                    }
                });

            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {

                EditInfoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        ToastUtils.showShort("上传失败");
                    }
                });
            }
        }, new TencentCosManager.InnerListener() {
            @Override
            public void onFailed(String errMsg) {
                EditInfoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        ToastUtils.showShort("上传失败");
                    }
                });
            }
        });
    }

    /**
     * 上传图片
     */
    private void uploadPic(String picPath) {
        String spToken = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).getString(Constant.SP.UserInfo.token, "");
        //加入压缩，否则上传失败。
        Luban.with(this)
                .load(new File(picPath))
                .ignoreBy(300)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File compressedFile) {
                        showLoadingDialog("上传中...", false);
                        //设置debug模式，此模式下有日志打印
                        RxHttp.setDebug(true);
                        RxHttp.postForm("/gateway/commonapi/uploadFile/uploadImg")
                                .addHeader("Authorization", spToken)
                                .add("file", compressedFile)
                                .add("prefix", "headerImg")//生成文件名前缀,例如医生头像，这个字段就是：doctorIcon followUpImg：随访图片
                                .add("type", "8")//上传文件类型 1医生头像 6随访图片
                                .asUpload(progress -> {
                                    //上传进度回调,0-100，仅在进度有更新时才会回调,最多回调101次，最后一次回调Http执行结果
                                   /* int currentProgress = progress.getProgress(); //当前进度 0-100
                                    long currentSize = progress.getCurrentSize(); //当前已上传的字节大小
                                    long totalSize = progress.getTotalSize();     //要上传的总字节大小
                                    Logger.i("上传进度"+currentProgress);*/
                                }, AndroidSchedulers.mainThread())     //指定回调(进度/成功/失败)线程,不指定,默认在请求所在线程回调
                                .subscribe(s -> {             //这里s为String类型,可通过asUpload(Parser,Progress,Scheduler)方法指定返回类型
                                    //上传成功
                                    hideDialog();
                                    ToastUtils.showShort("上传成功");
                                    Logger.i("上传成功" + s);
                                    Gson g = new Gson();
                                    //添加到list记录
                                    UploadPicResponseBean uploadPicResponseBean = g.fromJson(s, UploadPicResponseBean.class);
                                    UploadPicResponseBean.ResultBean result = uploadPicResponseBean.getResult();
                                    photoUrl = result.getFileUrl();
                                    photoId = result.getFileId();
                                }, throwable -> {
                                    //上传失败
                                    hideDialog();
                                    ToastUtils.showShort("上传失败");
                                    Logger.i("上传失败" + throwable);
                                });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
    }

    @Override
    protected void initMVP() {
        mView = new IContract.EditInfo.EditInfoView() {

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
                        InforMationBean.ResultBean result = inforMationBean.getResult();

                        if (result.getSex() == 1) {
                            mSex = 1;
                            tv_sex.setText("男士");
                            isOrNotChangeSex = false;
                        } else if (result.getSex() == 2) {
                            mSex = 2;
                            tv_sex.setText("女士");
                            isOrNotChangeSex = false;
                        } else {
                            mSex = 0;
                            tv_sex.setHint(getString(R.string.evaluation_module_please_select_sex));
                            isOrNotChangeSex = true;
                        }

                        if (!TextUtils.isEmpty(result.getBirthday())) {
                            tv_input_birthday.setText(result.getBirthday());
                            isOrNotChangeBirtyday = false;
                        } else {
                            isOrNotChangeBirtyday = true;
                        }

                        String url = result.getPhotoUrl();
                        if (!TextUtils.isEmpty(url)) {
                            GlideImageLoader.loadImage(EditInfoActivity.this, url, ivPersonHead);
                        }
                        photoId = result.getPhotoId();
                        String email = result.getEmail();
                        if (!TextUtils.isEmpty(email)) {
                            et_email.setText(email);
                        }

                        String nickname = result.getNickname();
                        if (!TextUtils.isEmpty(nickname)) {
                            tv_nickname.setText(nickname);
                        }

                        String professionStr = result.getProfessionStr();
                        jobId = result.getProfessionId() + "";
                        if (!TextUtils.isEmpty(professionStr)) {
                            tv_job.setText(professionStr);
                        }

                        String idCardNumber = result.getCardId();
                        if (!TextUtils.isEmpty(idCardNumber)) {
                            et_idcard_number.setText(idCardNumber);

                            et_idcard_number.setFocusable(false);
                            et_idcard_number.setFocusableInTouchMode(false);
                        }
                        //地址
                        String mergerName = result.getMergerName();
                        if (!TextUtils.isEmpty(mergerName)) {
                            tvAddress.setText(mergerName);
                        }

                        break;
                    default:
                }
            }

            @Override
            public void onSavePath(SavePathResponseBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        if (responseBean.result != null) {
                            photoUrl = responseBean.result.fileUrl + "";
                            photoId = responseBean.result.fileId;
                        }

                        break;
                    default:
                }
            }

            @Override
            public void onSaveInforMation(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        ToastUtils.showShort("修改成功");
                        int code = response.getCode();
                        if (code == 0) {
                            EventBus.getDefault().postSticky(new CommitToMyEvaluationBus(10, photoUrl, tv_nickname.getText().toString().trim()));
                            finish();
                        }
                        break;
                    default:
                }
            }

            @Override
            public void onGetJobList(JobBean jobBean, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:

                        break;
                    case SERVER_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        jobTypeList = jobBean.getResult();
                        //如果已经有选中的职业，就把该职业职位true
                        if (TextUtils.equals(jobId, "")) {
                            //不做处理
                        } else {
                            for (int i = 0; i < jobTypeList.size(); i++) {
                                if (jobTypeList.get(i).getId() == Integer.parseInt(jobId)) {
                                    jobTypeList.get(i).setSelect(true);
                                } else {
                                    jobTypeList.get(i).setSelect(false);
                                }
                            }
                        }
                        showJobTypePopup();
                        break;
                    default:
                }
            }

            @Override
            public void onGetAreaList(AreaListBean areaListBean, ResultType resultType, String errorMsg, int onNext) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        areaList = areaListBean.getResult();
                        on_callback = onNext;
                        if (cityListAdapter != null) {
                            cityListAdapter.setData(areaList);
                        }
                        //组合已选城市集合
//                            setSelecteCityList(tv_title, selecteCityAdapter, cityList_position);
                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new EditInfoPresenter(this, mView);
    }

    private void showJobTypePopup() {
        if (jobPopup != null && jobPopup.isShow()) {
            return;
        }
        jobPopup = (JobPopup) new XPopup.Builder(this)
                .enableDrag(false)
                .asCustom(new JobPopup(this, jobId, jobTypeList, new JobPopup.OnListPopupClick() {
                    @Override
                    public void onPopupClick(String id, String name) {
                        tv_job.setText(name);
                        jobId = id;
                    }
                }));
        jobPopup.show();
    }

    @Override
    protected void onReload() {
        showLoading();
        mPresenter.getInforMation(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == InputNickNameActivity.REQUEST_CODE && resultCode == InputNickNameActivity.RESULT_CODE) {
            InputNickNameActivity.ResultIntentBean bean = (InputNickNameActivity.ResultIntentBean) data.getSerializableExtra(Constant.IntentKey.requestReturnCode);
            if (bean != null) {
                if (!TextUtils.isEmpty(bean.nickname)) {
                    tv_nickname.setText(bean.nickname);
                }
            }
        }
    }


    //筛选地址弹窗
    public void showAddressTypePopup() {
        if (AdressTypePopup != null && AdressTypePopup.isShow()) {
            return;
        }
        selecteCityList.clear();
        AdressTypePopup = new XPopup.Builder(EditInfoActivity.this)
                .popupAnimation(PopupAnimation.TranslateAlphaFromBottom)  //设置动画
                .dismissOnTouchOutside(true)  // 点击外面消失
                .maxWidth(ScreenUtils.getScreenWidth())
                .maxHeight((int) (ScreenUtils.getScreenHeight() * 0.7))
                .enableDrag(false)
                .asCustom(new BottomPopupView(EditInfoActivity.this) {
                    @Override
                    protected int getImplLayoutId() {
                        return R.layout.evaluation_activity_address_popup;
                    }

                    @Override
                    protected void onCreate() {
                        super.onCreate();
                        tv_title = (TextView) findViewById(R.id.tv_title);

                        RecyclerView re_selecte_list = (RecyclerView) findViewById(R.id.re_selecte_list);
                        RecyclerView re_city_list = (RecyclerView) findViewById(R.id.re_city_list);
                        selecteCityAdapter = new SelecteCityAdapter(EditInfoActivity.this, selecteCityList);
                        re_selecte_list.setLayoutManager(new LinearLayoutManager(EditInfoActivity.this));
                        re_selecte_list.setAdapter(selecteCityAdapter);


                        cityListAdapter = new CityListAdapter(EditInfoActivity.this, areaList);
                        re_city_list.setLayoutManager(new LinearLayoutManager(EditInfoActivity.this));
                        re_city_list.setAdapter(cityListAdapter);

                        //已选城市列表
                        selecteCityAdapter.setOnItemClickListener(new SelecteCityAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                if (OnClickUtil.isNotFastClick()) {
                                    setCityList(tv_title, selecteCityAdapter, position);
                                }
                            }
                        });

                        //可选列表  省 城市 县 街道  社区   共5级
                        cityListAdapter.setOnItemClickListener(new CityListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                cityList_position = position;
                                if (on_click == on_callback) {//防止重复点击 设置随机数标识
                                    on_click = (int) (Math.random() * 10 + 1);
                                    AreaListRequestBean areaListRequestBean = new AreaListRequestBean();
                                    areaListRequestBean.setId(areaList.get(position).getId());
                                    areaListRequestBean.setLevel(String.valueOf(Integer.parseInt(areaList.get(position).getLevel()) + 1));
                                    mPresenter.getAreaList(areaListRequestBean, EditInfoActivity.this, on_click);

                                    setSelecteCityList(tv_title, selecteCityAdapter, cityList_position);
                                } else {
                                    ToastUtils.showLong("请勿重复点击");
//                                    ToastUtils.showLong("请勿点击 on_callback:"+on_callback+"  on_click"+on_click);
                                }
                            }
                        });
                    }
                })
                .show();
    }

    //处理城市回选逻辑
    private void setCityList(TextView tv_title, SelecteCityAdapter selecteCityAdapter, int position) {
        if (selecteCityList.size() == position + 1) {
            return;
        }
        //设置高亮状态
        for (int i = 0; i < selecteCityList.size(); i++) {
            if (i == position) {
                selecteCityList.get(i).setSelectState("1");
            } else {
                selecteCityList.get(i).setSelectState("0");
            }
        }
        //当回选时  删除所点击元素后面的数据
        if (selecteCityList.size() - position == 5) {
            for (int i = 0; i < 4; i++) {
                selecteCityList.remove(selecteCityList.size() - 1);
            }
        } else {
            if (selecteCityList.size() - position == 1) {
                int size = selecteCityList.size();
                selecteCityList.remove(selecteCityList.get(size));
            }
            if (selecteCityList.size() - position == 2) {
                int size = selecteCityList.size();
                selecteCityList.remove(selecteCityList.get(size - 1));
            }
            if (selecteCityList.size() - position == 3) {
                for (int i = 0; i < 2; i++) {
                    selecteCityList.remove(selecteCityList.size() - 1);
                }
            }
            if (selecteCityList.size() - position == 4) {
                for (int i = 0; i < 3; i++) {
                    selecteCityList.remove(selecteCityList.size() - 1);
                }
            }
        }
        //刷新适配器
        selecteCityAdapter.setData(selecteCityList);

        //修改城市列表的提示文字
        AreaListRequestBean areaListRequestBean = new AreaListRequestBean();
        if (position == 0) {
            tv_title.setText("请选择省份");
            areaListRequestBean.setId("0");
            areaListRequestBean.setLevel("0");
        } else {
            if (position == 1) {
                tv_title.setText("请选择城市");
            }
            if (position == 2) {
                tv_title.setText("请选择区/县");
            }
            if (position == 3) {
                tv_title.setText("请选择街/道");
            }
            if (position == 4) {
                tv_title.setText("请选择社区");
            }
            areaListRequestBean.setId(selecteCityList.get(position - 1).getId());
            String level = selecteCityList.get(position).getLevel();
            int i = Integer.parseInt(level);
            areaListRequestBean.setLevel(String.valueOf(i));
        }
        on_click = (int) (Math.random() * 10 + 1);
        //重新请求城市列表数据
        mPresenter.getAreaList(areaListRequestBean, EditInfoActivity.this, on_click);
    }

    //组合已选城市集合
    //集合处理两套逻辑 1.顺序依次点击城市列表选择 5级    2.选择中途返回   重新选择
    private void setSelecteCityList(TextView tv_title, SelecteCityAdapter selecteCityAdapter, int position) {

        //当城市集合areaList的城市级别为0即第一级 （后面一次类推共5级）
        if (areaList != null) {
            if (areaList.get(position).getLevel().equals("0")) {
                tv_title.setText("请选择城市");
                if (selecteCityList.size() == 1) {//已经选择过第一级省份  处理回选数据 ————替换之前选的数据
                    SelecteCityBean selecteCityBean = selecteCityList.get(0);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");
                    for (int i = 1; i < selecteCityList.size(); i++) {
                        selecteCityList.remove(selecteCityList.get(i));
                    }
                    selecteCityList.add(new SelecteCityBean("", "", "请选择城市", "0", "1", ""));
                    selecteCityAdapter.setData(selecteCityList);
                } else {//未选择过第一级省份  添加新元素进集合
                    selecteCityList.add(new SelecteCityBean(areaList.get(position).getId(), areaList.get(position).getLevel(),
                            areaList.get(position).getName(), "1", "", areaList.get(position).getCode()));
                    selecteCityList.add(new SelecteCityBean("", "", "请选择城市", "0", "1", ""));
                    selecteCityAdapter.setData(selecteCityList);
                }
            }
            if (areaList.get(position).getLevel().equals("1")) {
                tv_title.setText("请选择区/县");
                if (selecteCityList.size() == 3) {
                    SelecteCityBean selecteCityBean = selecteCityList.get(1);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");
                    for (int i = 1; i < selecteCityList.size(); i++) {
                        selecteCityList.remove(selecteCityList.get(i));
                    }
                    selecteCityList.add(new SelecteCityBean("", "", "请选择区/县", "0", "1", ""));
                    selecteCityAdapter.setData(selecteCityList);

                } else {
                    SelecteCityBean selecteCityBean = selecteCityList.get(1);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");
                    selecteCityList.add(new SelecteCityBean("", "", "请选择区/县", "0", "1", ""));
                    selecteCityAdapter.setData(selecteCityList);
                }
            }
            if (areaList.get(position).getLevel().equals("2")) {
                tv_title.setText("请选择街道");
                if (selecteCityList.size() == 4) {
                    SelecteCityBean selecteCityBean = selecteCityList.get(2);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");
                    for (int i = 3; i < selecteCityList.size(); i++) {
                        selecteCityList.remove(selecteCityList.get(i));
                    }
                    selecteCityList.add(new SelecteCityBean("", "", "请选择街/道", "0", "1", ""));
                    selecteCityAdapter.setData(selecteCityList);
                } else {
                    SelecteCityBean selecteCityBean = selecteCityList.get(2);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");
                    selecteCityList.add(new SelecteCityBean("", "", "请选择街/道", "0", "1", ""));
                    selecteCityAdapter.setData(selecteCityList);
                }

            }
            if (areaList.get(position).getLevel().equals("3")) {
                tv_title.setText("请选社区");
                if (selecteCityList.size() == 5) {
                    SelecteCityBean selecteCityBean = selecteCityList.get(3);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");
                    for (int i = 4; i < selecteCityList.size(); i++) {
                        selecteCityList.remove(selecteCityList.get(i));
                    }
                    selecteCityList.add(new SelecteCityBean("", "", "请选择社区", "0", "1", ""));
                    selecteCityAdapter.setData(selecteCityList);
                } else {
                    SelecteCityBean selecteCityBean = selecteCityList.get(3);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");
                    selecteCityList.add(new SelecteCityBean("", "", "请选择社区", "0", "1", ""));
                    selecteCityAdapter.setData(selecteCityList);
                }
            }
            if (areaList.get(position).getLevel().equals("4")) {
                if (selecteCityList.size() == 6) {
                    SelecteCityBean selecteCityBean = selecteCityList.get(4);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");
                    for (int i = 5; i < selecteCityList.size(); i++) {
                        selecteCityList.remove(selecteCityList.get(i));
                    }
                    selecteCityAdapter.setData(selecteCityList);
                } else {
                    SelecteCityBean selecteCityBean = selecteCityList.get(4);
                    selecteCityBean.setId(areaList.get(position).getId());
                    selecteCityBean.setLevel(areaList.get(position).getLevel());
                    selecteCityBean.setName(areaList.get(position).getName());
                    selecteCityBean.setCode(areaList.get(position).getCode());
                    selecteCityBean.setState("1");
                    selecteCityBean.setSelectState("0");

                    selecteCityAdapter.setData(selecteCityList);
                    AdressTypePopup.dismiss();
                    //设置已选地址到当前页
                    String userAddress = selecteCityList.get(0).getName() + selecteCityList.get(1).getName() + selecteCityList.get(2).getName()
                            + selecteCityList.get(3).getName() + selecteCityList.get(4).getName();
                    tvAddress.setText(userAddress);
                    //地址取  筛选第五级对应的id上传服务器
                    regionCode = selecteCityList.get(4).getCode();
                    originalCode = selecteCityList.get(4).getCode();
                }
            }
        } else {
            ToastUtils.showLong("请求数据错误");
        }
    }


}
