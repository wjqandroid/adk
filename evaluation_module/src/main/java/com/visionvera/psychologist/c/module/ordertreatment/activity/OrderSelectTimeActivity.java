//package com.visionvera.psychologist.c.module.ordertreatment.activity;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.text.Editable;
//import android.text.InputFilter;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.text.style.ForegroundColorSpan;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.alibaba.android.arouter.facade.annotation.Autowired;
//import com.alibaba.android.arouter.launcher.ARouter;
//import com.blankj.utilcode.util.SPUtils;
//import com.blankj.utilcode.util.ToastUtils;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.Target;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.listener.OnItemChildClickListener;
//import com.chad.library.adapter.base.listener.OnItemClickListener;
//import com.google.gson.Gson;
//import com.lxj.xpopup.XPopup;
//import com.lxj.xpopup.core.BasePopupView;
//import com.lxj.xpopup.core.ImageViewerPopupView;
//import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
//import com.lxj.xpopup.interfaces.XPopupImageLoader;
//import com.orhanobut.logger.Logger;
//import com.tencent.cos.xml.exception.CosXmlClientException;
//import com.tencent.cos.xml.exception.CosXmlServiceException;
//import com.tencent.cos.xml.listener.CosXmlProgressListener;
//import com.tencent.cos.xml.listener.CosXmlResultListener;
//import com.tencent.cos.xml.model.CosXmlRequest;
//import com.tencent.cos.xml.model.CosXmlResult;
//import com.visionvera.library.arouter.ARouterConstant;
//import com.visionvera.library.arouter.service.IAccountService;
//import com.visionvera.library.base.BaseActivity;
//import com.visionvera.library.base.BaseMVPActivity;
//import com.visionvera.library.base.Constant;
//import com.visionvera.library.eventbus.commonbean.LoginEventBus;
//import com.visionvera.library.util.EmojiFilter;
//import com.visionvera.library.util.OneClickUtils;
//import com.visionvera.library.widget.dialog.CameraAlbumPopup1;
//import com.visionvera.library.widget.dialog.CenterPopup;
//import com.visionvera.library.widget.dialog.ProblemTypePopup;
//import com.visionvera.library.widget.dialog.TreatmentTypePopup;
//import com.visionvera.psychologist.c.R;
//import com.visionvera.psychologist.c.R2;
//import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultSelectTimeActivity;
//import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarBean;
//import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderRequestBean;
//import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderResponseBean;
//import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
//import com.visionvera.psychologist.c.module.ordertreatment.presenter.OrderSelectTimeActivityPresenter;
//import com.visionvera.psychologist.c.module.usercenter.activity.EditInfoActivity;
//import com.visionvera.psychologist.c.module.usercenter.adapter.FeedBackImgAdapter;
//import com.visionvera.psychologist.c.module.usercenter.bean.FeedBackImgBean;
//import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
//import com.visionvera.psychologist.c.module.usercenter.bean.UploadPicResponseBean;
//import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
//import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;
//import com.visionvera.psychologist.c.utils.cos.TencentCosManager;
//import com.visionvera.psychologist.c.utils.photo.MyTakePhotoActivity;
//import com.visionvera.psychologist.c.widget.PerfectInformationPopup;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.io.File;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import rxhttp.wrapper.param.RxHttp;
//import top.zibin.luban.Luban;
//import top.zibin.luban.OnCompressListener;
//
//import static com.visionvera.library.base.Constant.RequestReturnCode.OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code;
//
///**
// * @author: 刘传政
// * @date: 2020-01-03 09:43
// * QQ:1052374416
// * 作用:预约诊疗----选择时间，类型和附件的页面
// * 注意事项:
// */
//public class OrderSelectTimeActivity extends BaseMVPActivity<IContract.OrderSelectTimeActivity.View, OrderSelectTimeActivityPresenter> implements OnItemClickListener, OnItemChildClickListener {
//
//    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
//    IAccountService accountService;
//
//    @BindView(R2.id.evaluation_module_upload_appendix_recyclerview)
//    RecyclerView mRecy;
//
//    @BindView(R2.id.evaluation_module_upload_appendix_notice)
//    TextView upload_appendix_notice;
//
//    @BindView(R2.id.evaluation_module_select_time)
//    TextView select_time;
//
//    @BindView(R2.id.evaluation_module_select_type_tv)
//    TextView select_type_tv;
//
//    @BindView(R2.id.tv_title)
//    TextView tv_title;
//    @BindView(R.id.et_content)
//    EditText etContent;
//    @BindView(R.id.tv_text_count)
//    TextView tvTextCount;
//    @BindView(R.id.evaluation_module_select_problem_type_tv)
//    TextView select_problem_type_tv;
//    private List<FeedBackImgBean> picPathList = new ArrayList<>();
//    private FeedBackImgAdapter feedBackImgAdapter;
//    private BasePopupView cameraAlbumPopup;
//    private BasePopupView typePopup;
//    /**
//     * 完善信息的弹窗
//     */
//    private BasePopupView basePopupView;
//
//    private TimeCalendarBean.ResultBean.DateListBean returnTimeBean;
//    /**
//     * 预约诊疗类型
//     */
//    private int mType;
//
//    //默认创建一个，保证取值不null。所以里边的默认值很重要
//    public IntentBean intentBean = new IntentBean(0, 0, "文字咨询(推荐)", "", "");
//
//
//    public static void startActivity(Context context, IntentBean intentBean) {
//
//        Intent intent = new Intent(context, OrderSelectTimeActivity.class);
//        intent.putExtra(Constant.IntentKey.IntentBean, intentBean);
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.evaluation_module_activity_order_consult;
//    }
//
//    @Override
//    protected void doYourself() {
//        ARouter.getInstance().inject(this);
//        EventBus.getDefault().register(this);
//        parseIntent();
//
//        initView();
//
//    }
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
//        tv_title.setText("预约诊疗");
//        //预约诊疗类型      视频诊疗(推荐) 3   文字诊疗  4   语音诊疗 2
//        //默认什么 选择类型都不选中
//        if (intentBean.selecTypeName.equals("视频诊疗(推荐)")) {
//            SpannableString mSpannableString = new SpannableString(intentBean.selecTypeName);
//            mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_BLACK_333333)), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//            mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_ORANGE_FF782E)), 4, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//            select_type_tv.setText(mSpannableString);
//            mType = 3;
//        } else if (intentBean.selecTypeName.equals("文字诊疗")) {
//            mType = 4;
//            select_type_tv.setText(intentBean.selecTypeName);
//        } else if (intentBean.selecTypeName.equals("语音诊疗")) {
//            mType = 2;
//            select_type_tv.setText(intentBean.selecTypeName);
//        }
//
//
//        mRecy.setLayoutManager(new GridLayoutManager(this, 4));
//        picPathList.add(new FeedBackImgBean(FeedBackImgBean.CAMERA, ""));
//
//        feedBackImgAdapter = new FeedBackImgAdapter(picPathList);
//        feedBackImgAdapter.setOnItemClickListener(this);
//        feedBackImgAdapter.addChildClickViewIds(R.id.evaluation_module_feedback_iv_delete);
//        feedBackImgAdapter.setOnItemChildClickListener(this);
//        mRecy.setAdapter(feedBackImgAdapter);
//        notifyPicTips();
//
//        basePopupView = new XPopup.Builder(this)
//                .asCustom(new PerfectInformationPopup(this, getString(R.string.evaluation_module_input_sex_birthday_idcard), () -> {
//                    //跳到编辑信息页
//                    EditInfoActivity.startActivity(activity);
//                    basePopupView.dismiss();
//                }));
//        etContent.setFilters(new InputFilter[]{new EmojiFilter(), new InputFilter.LengthFilter(200)});
//        etContent.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                tvTextCount.setText(s.length() + "/200");
//            }
//        });
//
//    }
//
//    private void notifyPicTips() {
//        //当一张图片也没选时,就显示提示文字,否则不显示
//        if (picPathList != null) {
//            if (picPathList.size() <= 1) {
//                upload_appendix_notice.setVisibility(View.VISIBLE);
//                return;
//            }
//        }
//        upload_appendix_notice.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        FeedBackImgBean bean = picPathList.get(position);
//        String picPath = bean.getPicPath();
//        if (TextUtils.isEmpty(picPath)) {
//            //如果没有图片就去选择一张
//            selectPhotoMethod(adapter, position);
//        } else {
//            //如果有图片就去大图预览
//            ImageView imageView = view.findViewById(R.id.evaluation_module_feedback_iv);
//            ArrayList<Object> list = new ArrayList<>();
//            for (int i = 0; i < picPathList.size(); i++) {
//                if (picPathList.get(i).getItemType() != FeedBackImgBean.CAMERA) {
//                    list.add(picPathList.get(i).getPicPath());
//                }
//            }
//
//            //查看照片
//            // 多图片场景
//            //srcView参数表示你点击的那个ImageView，动画从它开始，结束时回到它的位置。
//            //注意：如果你自己的ImageView的scaleType是centerCrop类型的，你加载图片需要指定Original_Size，禁止Glide裁剪图片。
//
//            new XPopup.Builder(activity).asImageViewer(imageView, position, list, false, false, -1, -1, -1, false, new OnSrcViewUpdateListener() {
//                @Override
//                public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
//                    // 作用是当Pager切换了图片，需要更新源View
//                    popupView.updateSrcView((ImageView) mRecy.getChildAt(position).findViewById(R.id.evaluation_module_feedback_iv));
//                }
//            }, new ImageLoader())
//                    .show();
//        }
//    }
//
//    @Override
//    protected void initMVP() {
//        mView = new IContract.OrderSelectTimeActivity.View() {
//            @Override
//            public void onSubmit(SubmitOrderResponseBean responseBean, ResultType resultType, String errorMsg) {
//                hideDialog();
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                        ToastUtils.showShort(errorMsg);
//                        break;
//                    case SERVER_NORMAL_DATANO:
//                    case SERVER_NORMAL_DATAYES:
//                        ToastUtils.showShort("预约成功");
//
//                        OrderTreatmentSuccessActivity.IntentBean intentBean
//                                = new OrderTreatmentSuccessActivity.IntentBean(responseBean.getResult());
//                        OrderTreatmentSuccessActivity.startActivity(activity, intentBean);
//                        break;
//                }
//            }
//
//            @Override
//            public void onGetInforMation(InforMationBean inforMationBean, ResultType resultType, String errorMsg) {
//                hideDialog();
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等，也就是根本没跟自己服务器正常交互
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                        //也就是自己服务器返回的code值不对
//                        ToastUtils.showLong(getString(R.string.base_module_data_load_error));
//                        break;
//                    case SERVER_NORMAL_DATANO:
//                    case SERVER_NORMAL_DATAYES:
//                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
//                        InforMationBean.ResultBean result = inforMationBean.getResult();
//
//                        if (TextUtils.isEmpty(result.getBirthday()) || result.getSex() == 0 || TextUtils.isEmpty(result.getCardId())) {
//                            basePopupView.show();
//                        } else {
//                            //用户已完善信息
//                            showLoadingDialog();
//                            if (checkIfUpAllPic()) {
//                                http_submit();
//                            } else {
//                                uploadAllPicOneByOne();
//                            }
//                        }
//                        break;
//                    default:
//                }
//            }
//
//            @Override
//            public void onSavePath(SavePathResponseBean responseBean, ResultType resultType, String errorMsg, int position) {
//                Logger.i("onSavePath" + responseBean + "position" + position);
//                switch (resultType) {
//                    case NET_ERROR:
//                    case SERVER_ERROR:
//                    case SERVER_NORMAL_DATANO:
//                        ToastUtils.showShort(errorMsg);
//                        break;
//                    case SERVER_NORMAL_DATAYES:
//                        if (responseBean.result != null) {
//                            //刷新图片列表
//                            picPathList.get(position).setUrl(responseBean.result.fileUrl + "");
//                            picPathList.get(position).setId(responseBean.result.fileId);
//                            if (checkIfUpAllPic()) {
//                                //如果图片都传完了
//                                http_submit();
//                            } else {
//                                uploadAllPicOneByOne();
//                            }
//                        }
//                        break;
//                    default:
//                }
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        mPresenter = new OrderSelectTimeActivityPresenter(this, mView);
//    }
//
//    /**
//     * 检查是否所有的已选图片都上传到服务器拿到了图片id
//     */
//    private boolean checkIfUpAllPic() {
//        boolean all = true;
//        for (int i = 0; i < picPathList.size(); i++) {
//            FeedBackImgBean imgBean = picPathList.get(i);
//            if (imgBean.getItemType() == FeedBackImgBean.IMG) {
//                if (!TextUtils.isEmpty(imgBean.getPicPath())) {
//                    if (TextUtils.isEmpty(imgBean.getUrl()) && imgBean.getId() == 0) {
//                        all = false;
//                    }
//                }
//            }
//        }
//        return all;
//    }
//
//
//    class ImageLoader implements XPopupImageLoader {
//        @Override
//        public void loadImage(int position, @NonNull Object url, @NonNull ImageView imageView) {
//            //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
//            Glide.with(imageView).load(url).apply(new RequestOptions().placeholder(R.mipmap.logo).override(Target.SIZE_ORIGINAL)).into(imageView);
//        }
//
//        @Override
//        public File getImageFile(@NonNull Context context, @NonNull Object uri) {
//            try {
//                return Glide.with(context).downloadOnly().load(uri).submit().get();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//    @Override
//    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        if (view.getId() == R.id.evaluation_module_feedback_iv_delete) {
//            if (picPathList.size() == 4) {
//                FeedBackImgBean bean = picPathList.get(3);
//                if (TextUtils.isEmpty(bean.getPicPath())) {
//                    picPathList.remove(position);
//                } else {
//                    bean.setItemType(FeedBackImgBean.CAMERA);
//                    bean.setPicPath("");
//                }
//            } else {
//                picPathList.remove(position);
//            }
//            feedBackImgAdapter.notifyDataSetChanged();
//            notifyPicTips();
//        }
//    }
//
//
//    /**
//     * 获取照片
//     */
//    private void selectPhotoMethod(BaseQuickAdapter adapter, int position) {
//        cameraAlbumPopup = new XPopup.Builder(this)
//                .asCustom(new CameraAlbumPopup1(this,
//                        () -> MyTakePhotoActivity.getPicPath(activity, MyTakePhotoActivity.photoAlbum, picPath -> {
//                            cameraAlbumPopup.dismiss();
//                            parseImgData(picPath, position);
//                        }), new CameraAlbumPopup1.CameraListener() {
//                    @Override
//                    public void cameraClickListener() {
//                        MyTakePhotoActivity.getPicPath(activity, MyTakePhotoActivity.takePhoto, picPath -> {
//                            cameraAlbumPopup.dismiss();
//                            parseImgData(picPath, position);
//                        });
//                    }
//                }));
//        cameraAlbumPopup.show();
//    }
//
//    /**
//     * 处理添加的图片附件
//     *
//     * @param picPath
//     */
//    private void parseImgData(String picPath, int position) {
//        //先本地展示.不上传服务器,最后提交时一次性提交服务器
//        if (!TextUtils.isEmpty(picPath)) {
//            int size = picPathList.size();
//            if (size == 4) {
//                //如果是最后一张添加,把最后一个加号替换掉
//                FeedBackImgBean bean = picPathList.get(3);
//                bean.setItemType(FeedBackImgBean.IMG);
//                bean.setPicPath(picPath);
//            } else {
//                //如果不是最后一张添加.直接加
//                picPathList.add(size - 1, new FeedBackImgBean(FeedBackImgBean.IMG, picPath));
//            }
////            uploadPic(activity, picPath, position);
//
////            uploadPicByCos(picPath,position);
//            feedBackImgAdapter.notifyDataSetChanged();
//            notifyPicTips();
//        }
//    }
//
//    //上传到腾讯
//    private void uploadPicByCos(String picPath, int position) {
//        showLoadingDialog("上传中...", false);
//        String houzhui = "png";
//        String[] strArr = picPath.split("\\.");
//        if (strArr.length > 0) {
//            houzhui = strArr[strArr.length - 1];
//        }
//
//        String cosPath = System.currentTimeMillis() + "." + houzhui;
//        TencentCosManager.getInstance(this).upload(cosPath, picPath, new CosXmlProgressListener() {
//            @Override
//            public void onProgress(long complete, long target) {
//
//            }
//        }, new CosXmlResultListener() {
//            @Override
//            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // 告知服务器
//                        SavePathRequestBean requestBean = new SavePathRequestBean();
//                        requestBean.prefix = "ordertreatment";
//                        requestBean.description = "C端预约诊疗Android";
//                        requestBean.type = 10;
//                        //后台非得要后半段的路径.那这就截取吧
//                        String str1 = result.accessUrl.substring(0, result.accessUrl.indexOf("myqcloud.com"));
//                        String str2 = result.accessUrl.substring(str1.length() + 12, result.accessUrl.length());
//                        requestBean.filePath = str2;
//                        requestBean.originalFilename = picPath;
//                        mPresenter.savePath(requestBean, activity, position);
//                        Logger.i("uploadPicByCosSuccess" + str2);
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
//
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        hideDialog();
//                        ToastUtils.showShort("上传失败");
//                    }
//                });
//            }
//        }, new TencentCosManager.InnerListener() {
//            @Override
//            public void onFailed(String errMsg) {
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        hideDialog();
//                        ToastUtils.showShort("上传失败");
//                    }
//                });
//            }
//        });
//    }
//
//    /**
//     * 上传图片到自己服务器
//     *
//     * @param activity
//     * @param picPath
//     * @param position
//     */
//    private void uploadPic(BaseActivity activity, String picPath, int position) {
//        String spToken = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).getString(Constant.SP.UserInfo.token, "");
//        //加入压缩，否则上传失败。
//        Luban.with(activity)
//                .load(new File(picPath))
//                .ignoreBy(300)
//                .setCompressListener(new OnCompressListener() {
//                    @Override
//                    public void onStart() {
//
//                    }
//
//
//                    // TODO: 2019/12/20 需要和胡禹对接type值
//                    @Override
//                    public void onSuccess(File compressedFile) {
//                        showLoadingDialog("上传中...", false);
//                        //设置debug模式，此模式下有日志打印
//                        RxHttp.setDebug(true);
//                        RxHttp.postForm("/gateway/commonapi/uploadFile/uploadImg")
//                                .addHeader("Authorization", spToken)
//                                .add("file", compressedFile)
//                                .add("prefix", "feedbackUpImg")//生成文件名前缀,例如医生头像，这个字段就是：doctorIcon followUpImg：随访图片
//                                .add("type", "9")//上传文件类型  1医生头像 6随访图片7意见反馈8用户头像 9 预约咨询图片
//                                .asUpload(progress -> {
//                                    //上传进度回调,0-100，仅在进度有更新时才会回调,最多回调101次，最后一次回调Http执行结果
//                                   /* int currentProgress = progress.getProgress(); //当前进度 0-100
//                                    long currentSize = progress.getCurrentSize(); //当前已上传的字节大小
//                                    long totalSize = progress.getTotalSize();     //要上传的总字节大小
//                                    Logger.i("上传进度"+currentProgress);*/
//                                }, AndroidSchedulers.mainThread())     //指定回调(进度/成功/失败)线程,不指定,默认在请求所在线程回调
//                                .subscribe(s -> {             //这里s为String类型,可通过asUpload(Parser,Progress,Scheduler)方法指定返回类型
//                                    //上传成功
//                                    hideDialog();
//                                    ToastUtils.showShort("上传成功");
//                                    Logger.i("上传成功" + s);
//                                    Gson g = new Gson();
//                                    //添加到list记录
//                                    UploadPicResponseBean uploadPicResponseBean = g.fromJson(s, UploadPicResponseBean.class);
//                                    if (uploadPicResponseBean.getCode() == 0) {
//                                        picPathList.get(position).setUrl(uploadPicResponseBean.getResult().getFileUrl());
//                                        picPathList.get(position).setId(uploadPicResponseBean.getResult().getFileId());
//                                        feedBackImgAdapter.notifyDataSetChanged();
//                                        notifyPicTips();
//                                    }
//
//
//                                }, throwable -> {
//                                    //上传失败
//                                    hideDialog();
//                                    ToastUtils.showShort("上传失败");
//                                    Logger.i("上传失败" + throwable);
//                                });
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                }).launch();
//    }
//
//    @OnClick({R2.id.evaluation_module_order_consult_select_time,
//            R2.id.evaluation_module_order_consult_select_type,
//            R2.id.evaluation_module_order_consult_commit,
//            R2.id.evaluation_module_order_consult_problem_type,
//            R2.id.rl_back, R2.id.tv_right})
//    public void onClick(View view) {
//        if (OneClickUtils.isFastClick()) {
//            return;
//        }
//
//        int viewId = view.getId();
//        if (viewId == R.id.evaluation_module_order_consult_select_time) {
//            //预约时间选择
//            OrderConsultSelectTimeActivity.OrderConsultSelectTimeIntentBean bean
//                    = new OrderConsultSelectTimeActivity.OrderConsultSelectTimeIntentBean(intentBean.doctorUserId);
//            OrderConsultSelectTimeActivity.startActivityForResult(this, bean);
//        } else if (viewId == R.id.evaluation_module_order_consult_select_type) {
//            showTypePopup();
//        } else if (viewId == R.id.evaluation_module_order_consult_commit) {
//            //提交
//            submitAllData();
//
//        } else if (viewId == R.id.rl_back) {
//            finish();
//        } else if (viewId == R.id.tv_right) {
//            //咨询须知   pop
//            showAdvisoryInstructionsPopup();
//        } else if (viewId == R.id.evaluation_module_order_consult_problem_type) {
//            //问题类型   pop
//            showProblemTypePopup();
//        }
//    }
//
//    //提交所有数据
//    private void submitAllData() {
//        //提交
//        if (returnTimeBean == null) {
//            ToastUtils.showShort("请选择时间");
//            return;
//        }
//        if (mType == 0) {
//            ToastUtils.showShort("请选择类型");
//            return;
//        }
//        if (select_problem_type_tv.getText().toString().isEmpty()) {
//            ToastUtils.showShort("请选择问题类型");
//            return;
//        }
//        boolean hasPic = false;
//        for (int i = 0; i < picPathList.size(); i++) {
//            FeedBackImgBean imgBean = picPathList.get(i);
//            if (imgBean.getItemType() == FeedBackImgBean.IMG) {
//                hasPic = true;
//                break;
//            }
//        }
//
//        if (accountService != null && accountService.getGetAccountInfo() != null) {
//            //判断是否登录
//            if (!accountService.getGetAccountInfo().isLogin) {
//
//                new XPopup.Builder(activity)
//                        .dismissOnBackPressed(false)
//                        .dismissOnTouchOutside(false)
//                        .asCustom(new CenterPopup(activity,
//                                CenterPopup.CenterPopupType.twoButton,
//                                activity.getString(R.string.evaluation_module_commit_confirm),
//                                activity.getString(R.string.evaluation_module_not_login),
//                                activity.getString(R.string.evaluation_module_cancle),
//                                activity.getString(R.string.evaluation_module_to_login),
//                                centerPopup -> centerPopup.dismiss(), centerPopup -> {
//                            centerPopup.dismiss();
//                            ARouter.getInstance()
//                                    .build(ARouterConstant.Account.AccountLoginActivity)
//                                    .navigation(activity);
//                        }))
//
//                        .show();
//
//            } else {
//                //已经登录账号,调用接口，检查信息是否完善。
//                showLoadingDialog();
//                mPresenter.getInforMation(this);
//
//            }
//        }
//    }
//
//    /**
//     * 依次上传所有图片
//     * 每次只传一张.因为很多方法的回调会相互影响.所以不并发上传
//     */
//    public void uploadAllPicOneByOne() {
//        if (!checkIfUpAllPic()) {
//            //如果有图片没上传
//            for (int i = 0; i < picPathList.size(); i++) {
//                FeedBackImgBean imgBean = picPathList.get(i);
//                if (imgBean.getItemType() == FeedBackImgBean.IMG) {
//                    if (!TextUtils.isEmpty(imgBean.getPicPath())) {
//                        if (TextUtils.isEmpty(imgBean.getUrl()) && imgBean.getId() == 0) {
//                            //证明还没上传过服务器这张图片
//                            //把图片先上传cos,再想服务器换取id,id赋值给list,最后将id提交给接口
//                            uploadPicByCos(imgBean.getPicPath(), i);
//                            return;
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//
//
//    //提交数据
//    public void http_submit() {
//        //诊疗形式  1会诊方式:交互式(视联网)，2离线式(线下门诊),3移动式(手机远程)',
//        String uploadIds = "";
//        for (int i = 0; i < picPathList.size(); i++) {
//            if (picPathList.get(i).getItemType() == FeedBackImgBean.IMG) {
//                if (i == 0) {
//                    uploadIds = picPathList.get(0).getId() + "";
//                } else {
//                    uploadIds += "," + picPathList.get(i).getId();
//                }
//            }
//        }
//        SubmitOrderRequestBean requestBean = new SubmitOrderRequestBean(
//                Integer.parseInt(accountService.getGetAccountInfo().userId),
//                0,
//                intentBean.doctorUserId,
//                0,
//                returnTimeBean.getId(),
//                16,
//                intentBean.doctorUserId + "",
//                mType + "",
//                uploadIds,
//                etContent.getText().toString().trim(),
//                select_problem_type_tv.getText().toString().trim()//问题类型字段 需求新增  等待后台确认
//        );
//        mPresenter.submit(requestBean, this);
//    }
//
//    private void showTypePopup() {
//
//        typePopup = new XPopup.Builder(this)
//                .asCustom(new TreatmentTypePopup(this, new TreatmentTypePopup.OnCheckListener() {
//                    @Override
//                    public void onCheck(TreatmentTypePopup.TreatmentType type, String tips) {
//                        switch (type) {
//                            case VIDEO:
//                                mType = 3;
//                                typePopup.dismiss();
//                                SpannableString mSpannableString = new SpannableString("视频诊疗(推荐)");
//                                mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_BLACK_333333)), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                                mSpannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.COLOR_ORANGE_FF782E)), 4, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                                select_type_tv.setText(mSpannableString);
//                                break;
//                            case WORD:
//                                mType = 4;
//                                typePopup.dismiss();
//                                select_type_tv.setText("文字诊疗");
//                                break;
//                            case VOICE:
//                                break;
//                            case OFFLINE:
//                                mType = 2;
//                                typePopup.dismiss();
//                                select_type_tv.setText("语音诊疗");
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
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code && resultCode == OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code) {
//            returnTimeBean = (TimeCalendarBean.ResultBean.DateListBean) data.getSerializableExtra("data");
//            String time = returnTimeBean.getStart().substring(0, 16) + "-" + returnTimeBean.getEnd().substring(11, 16);
//            select_time.setText(time);
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
//    public void onLoginEventBus(LoginEventBus busBean) {
//        //接收到了登录的消息
//       /* if (busBean != null) {
//            if (busBean.getAccountBean() != null) {
//                if (busBean.getAccountBean().isLogin) {
//                    //如果登录了
//                    //点提交按钮的时候提示登录,登录后直接提交
//                    showLoadingDialog();
//                    http_submit();
//                } else {
//                    //如果是退出登录了
//                }
//            }
//        }*/
//    }
//
//    public static class IntentBean implements Serializable {
//        private int doctorId;
//        private int doctorUserId;
//        //诊疗类型
//        private String selecTypeName = "";
//        //机构
//        public String advisoryBody = "";
//        //头像
//        public String imgUri = "";
//
//        public IntentBean(int doctorId, int doctorUserId,  String selecTypeName,String advisoryBody, String imgUri) {
//            this.doctorId = doctorId;
//            this.doctorUserId = doctorUserId;
//            this.selecTypeName = selecTypeName;
//            this.advisoryBody = advisoryBody;
//            this.imgUri = imgUri;
//        }
//
//        @Override
//        public String toString() {
//            return "IntentBean{" +
//                    "doctorId=" + doctorId +
//                    ", doctorUserId=" + doctorUserId +
//                    ", advisoryBody='" + advisoryBody + '\'' +
//                    ", selecTypeName='" + selecTypeName + '\'' +
//                    ", imgUri='" + imgUri + '\'' +
//                    '}';
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//
//
//    //咨询须知   pop
//    private void showAdvisoryInstructionsPopup() {
//        TextView title = new TextView(this);
//        title.setText("咨询须知");
//        title.setPadding(0, 25, 0, 0);
//        title.setGravity(Gravity.CENTER);
//        title.getPaint().setFakeBoldText(true);
//        title.setTextSize(18);
//        title.setTextColor(Color.BLACK);
//        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
//                .setCustomTitle(title)
//                .setMessage("我是我\" +\n" +
//                        "                                    \"我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容是\" +\n" +
//                        "                                    \"我是内容我是内容我是内容我是内容我是内容我是内容我是内容内容我是内容我是内容我是内容我是内容我是内容我是内容内容")//内
//                .create();
//        alertDialog1.show();
//    }
//
//    //问题类型
//    private void showProblemTypePopup() {
//        typePopup = new XPopup.Builder(this)
//                .asCustom(new ProblemTypePopup(this, new ProblemTypePopup.ResultListener() {
//                    @Override
//                    public void onPick(String name) {
//                        select_problem_type_tv.setText(name);
//                    }
//                }));
//        typePopup.show();
//    }
//
//}
