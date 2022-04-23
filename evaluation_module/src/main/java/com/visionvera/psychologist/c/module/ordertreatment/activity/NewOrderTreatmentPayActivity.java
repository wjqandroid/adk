package com.visionvera.psychologist.c.module.ordertreatment.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import com.orhanobut.logger.Logger;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.visionvera.library.arouter.ARouterConstant;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.dialog.CenterPopup;
import com.visionvera.library.widget.view.CircleImageView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.PayEventBus;
import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultSuccessActivity;
import com.visionvera.psychologist.c.module.counselling.adapter.ImgAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.NewOrderConsultRequest;
import com.visionvera.psychologist.c.module.counselling.bean.NewOrderconsultBean;
import com.visionvera.psychologist.c.module.counselling.bean.PayResult;
import com.visionvera.psychologist.c.module.counselling.bean.WeixinPrePayResponseBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.OrderPayPresenter;
import com.visionvera.psychologist.c.module.counselling.view.BottomCheckOrderPayTypePopup;
import com.visionvera.psychologist.c.module.usercenter.activity.EditInfoActivity;
import com.visionvera.psychologist.c.module.usercenter.bean.FeedBackImgBean;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;
import com.visionvera.psychologist.c.utils.cos.TencentCosManager;
import com.visionvera.psychologist.c.utils.pay.PayUtil;
import com.visionvera.psychologist.c.weixinpay.Constants;
import com.visionvera.psychologist.c.widget.PerfectInformationPopup;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @Classname:NewOrderTreatmentPayActivity
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0描
 * @describe： 描述:预约诊疗————订单提交与支付页面
 */

//支付流程：    提交订单按钮——调用完善信息接口——调用上传全部图片接口——调用支付接口

public class NewOrderTreatmentPayActivity extends BaseMVPActivity<OrderConsultContract.OrderPayActivity.NewOrderConsultView, OrderPayPresenter> {


    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
    IAccountService accountService;

    TextView tv_title;
    RecyclerView recyclerViewFujian;
    TextView tvName;
    TextView tvTitle;
    TextView tvTime;
    TextView tvBeizhu;
    TextView tvPrice;
    TextView tvSubmit;
    TextView tvType;
    TextView tvTag;
    TextView tvProblemType;
    TextView tvAdvisoryBody;
    CircleImageView header;


    private IntentBean mIntentBean = new IntentBean();
    private ImgAdapter imgAdapter;
    private List<FeedBackImgBean> picPathList = new ArrayList<>();
    /**
     * 完善信息的弹窗
     */
    private BasePopupView basePopupView;
    private BottomCheckOrderPayTypePopup mPayTypePopup = null;
    private int mPayTypeCheckPosition = 0;
    //自己服务器的下单返回对象
    NewOrderconsultBean.ResultBean mOrderResultBean;
    PayUtil payUtil;

    public static void startActivity(Context context, IntentBean intentBean) {
        context.startActivity(new Intent(context, NewOrderTreatmentPayActivity.class).putExtra(Constant.IntentKey.IntentBean, intentBean));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.new_evaluation_module_activity_order_treatment_pay;
    }

    @Override
    protected void doYourself() {
        tv_title = findViewById(R.id.tv_title);
        recyclerViewFujian = findViewById(R.id.recyclerViewFujian);
        tvName = findViewById(R.id.tvName);
        tvTitle = findViewById(R.id.tvTitle);
        tvTime = findViewById(R.id.tvTime);
        tvBeizhu = findViewById(R.id.tvBeizhu);
        tvPrice = findViewById(R.id.tvPrice);
        tvSubmit = findViewById(R.id.tv_submit);
        tvType = findViewById(R.id.tvType);
        tvTag = findViewById(R.id.tvTag);
        tvProblemType = findViewById(R.id.tv_ProblemType);
        header = findViewById(R.id.evaluation_module_counselor_detail_header);
        ImageView rl_back = findViewById(R.id.rl_back);
        tvSubmit.setOnClickListener(this);
        rl_back.setOnClickListener(this);

        initIntentBean();
        ARouter.getInstance().inject(this);

        initView();
        payUtil = new PayUtil(this);

    }

    private void initIntentBean() {
        //获取参数
        IntentBean bean = (IntentBean) getIntent().getSerializableExtra(Constant.IntentKey.IntentBean);
        if (bean != null) {
            mIntentBean = bean;
        }
        Logger.i(mIntentBean.toString());
    }

    /**
     * DecimalFormat转换最简便
     */
    public String formatDouble(Double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String format = df.format(d);
        return format;
    }

    private void initView() {
        ImmersionBar.with(this)
                //状态栏颜色
                .transparentStatusBar()
                //状态栏文字颜色
                .statusBarDarkFont(true)
                //使用该属性必须指定状态栏的颜色，不然状态栏透明，很难看. false表示布局嵌入状态栏。true表示布局避开状态栏
                .fitsSystemWindows(false)
                .init();
        tv_title.setText("提交订单");
        tvTitle.setVisibility(View.GONE);

        tvName.setText(mIntentBean.zixunshiName);
        tvTitle.setText(mIntentBean.zixunshiTitle);
        tvTag.setText(mIntentBean.zixunshiTags);
        tvTime.setText(mIntentBean.zixunTime);
        //机构
        tvAdvisoryBody.setText(mIntentBean.advisoryBody);
        //诊疗类型     4文字诊疗   5语音诊疗  6 视频诊疗(推荐)
        if (mIntentBean.zixunType == 6) {
            tvType.setText("视频诊疗 ");
        } else if (mIntentBean.zixunType == 5) {
            tvType.setText("语音诊疗");
        } else if (mIntentBean.zixunType == 4) {
            tvType.setText("文字诊疗");
        }
        //问题类型
        tvProblemType.setText(mIntentBean.problemType);

        if (!TextUtils.isEmpty(mIntentBean.beizhu)) {
            tvBeizhu.setText(mIntentBean.beizhu + "");
        } else {
            tvBeizhu.setText("暂无");
        }
        tvPrice.setText("¥" + formatDouble(mIntentBean.price));

        Glide.with(this).load(mIntentBean.imgUri)
                .placeholder(R.drawable.base_module_doctor_header)
                .error(R.drawable.base_module_doctor_header)
                .into(header);

        if (mIntentBean.fujian != null && mIntentBean.fujian.size() > 0) {

            for (FeedBackImgBean feedBackImgBean : mIntentBean.fujian) {
                if (feedBackImgBean.getItemType() == FeedBackImgBean.IMG) {
                    picPathList.add(feedBackImgBean);
                }
            }
            recyclerViewFujian.setLayoutManager(new GridLayoutManager(this, 4));
            imgAdapter = new ImgAdapter(picPathList);
            imgAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });

            recyclerViewFujian.setAdapter(imgAdapter);
            imgAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ImageView imageView = view.findViewById(R.id.iv);
                    ArrayList<Object> list = new ArrayList<>();
                    for (int i = 0; i < picPathList.size(); i++) {
                        if (picPathList.get(i).getItemType() != FeedBackImgBean.CAMERA) {
                            list.add(picPathList.get(i).getPicPath());
                        }
                    }

                    //查看照片
                    // 多图片场景
                    //srcView参数表示你点击的那个ImageView，动画从它开始，结束时回到它的位置。
                    //注意：如果你自己的ImageView的scaleType是centerCrop类型的，你加载图片需要指定Original_Size，禁止Glide裁剪图片。

                    new XPopup.Builder(activity).asImageViewer(imageView, position, list, false, false, -1, -1, -1, false, new OnSrcViewUpdateListener() {
                        @Override
                        public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
                            // 作用是当Pager切换了图片，需要更新源View
                            popupView.updateSrcView((ImageView) recyclerViewFujian.getChildAt(position).findViewById(R.id.iv));
                        }
                    }, new ImageLoader())
                            .show();
                }
            });
        }

        basePopupView = new XPopup.Builder(this)
                .dismissOnBackPressed(false)
                .dismissOnTouchOutside(false)
                .asCustom(new CenterPopup(this,
                        CenterPopup.CenterPopupType.oneButton,
                        "提示",
                        "您需要补全个人信息:性别;出生日期;身份证号后才可以预约!",
                        "",
                        "补全信息",
                        centerPopup -> centerPopup.dismiss(), centerPopup -> {
                    //跳到编辑信息页
                    EditInfoActivity.startActivity(activity);
                    centerPopup.dismiss();

                }));
    }

    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.OrderPayActivity.NewOrderConsultView() {
            @Override
            public void onNewOrderConsult(NewOrderconsultBean response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showLong(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                       /* ToastUtils.showLong(getString(R.string.evaluation_module_commit_success));

                        OrderConsultSuccessActivity.OrderConsultSuccessIntentBean intentBean
                                = new OrderConsultSuccessActivity.OrderConsultSuccessIntentBean(response.getResult().getTalkCureId());
                        OrderConsultSuccessActivity.startActivity(activity, intentBean);*/
                        mOrderResultBean = response.getResult();
                        tvSubmit.setText("支付");
                        //判断需要支付金额为0元 直接跳到支付成功后页面
                        if (mIntentBean.price == 0) {
                            //金额为0元，直接跳转申请成功页面
                            startSuccessActivity("0");
                        } else {
                            showPayTypePop(NewOrderTreatmentPayActivity.this, mPayTypeCheckPosition);
                        }
                        break;
                    default:
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

                        if (TextUtils.isEmpty(result.getBirthday()) || result.getSex() == 0 || TextUtils.isEmpty(result.getCardId())) {
                            basePopupView.show();
                        } else {
                            showLoadingDialog();
                            if (checkIfUpAllPic()) {
                                http_submit();
                            } else {
                                uploadAllPicOneByOne();
                            }
                        }

                        break;
                    default:
                }

            }

            @Override
            public void onSavePath(SavePathResponseBean responseBean, ResultType resultType, String errorMsg, int position) {
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
                            //刷新图片列表
                            picPathList.get(position).setUrl(responseBean.result.fileUrl + "");
                            picPathList.get(position).setId(responseBean.result.fileId);
                            if (checkIfUpAllPic()) {
                                //如果图片都传完了
                                http_submit();
                            } else {
                                uploadAllPicOneByOne();
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
        mPresenter = new OrderPayPresenter(this, mView);
    }

    class ImageLoader implements XPopupImageLoader {
        @Override
        public void loadImage(int position, @NonNull Object url, @NonNull ImageView imageView) {
            //必须指定Target.SIZE_ORIGINAL，否则无法拿到原图，就无法享用天衣无缝的动画
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



    //    @OnClick({R2.id.tv_submit, R2.id.rl_back})

    @Override
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.tv_submit) {
            if (tvSubmit.getText().equals("支付")) {
                //判断需要支付金额为0元 直接跳到支付成功后页面
                if (mIntentBean.price == 0) {
                    //金额为0元，直接跳转申请成功页面
                    startSuccessActivity("0");
                } else {
                    showPayTypePop(NewOrderTreatmentPayActivity.this, mPayTypeCheckPosition);
                }
            } else {
                submitAllData();
            }
        } else if (id == R.id.rl_back) {
            finish();
        }

    }

    //提交所有数据
    private void submitAllData() {
        //提交
        if (accountService != null && accountService.getGetAccountInfo() != null) {
            //判断是否登录
            if (!accountService.getGetAccountInfo().isLogin) {

                new XPopup.Builder(activity)
                        .dismissOnBackPressed(false)
                        .dismissOnTouchOutside(false)
                        .asCustom(new CenterPopup(activity,
                                CenterPopup.CenterPopupType.twoButton,
                                activity.getString(R.string.evaluation_module_commit_confirm),
                                activity.getString(R.string.evaluation_module_not_login),
                                activity.getString(R.string.evaluation_module_cancle),
                                activity.getString(R.string.evaluation_module_to_login),
                                centerPopup -> centerPopup.dismiss(), centerPopup -> {
                            centerPopup.dismiss();
                            ARouter.getInstance()
                                    .build(ARouterConstant.Account.AccountLoginActivity)
                                    .navigation(activity);
                        }))

                        .show();

            } else {
                //已经登录账号,调用接口，检查信息是否完善。
                showLoadingDialog();
                mPresenter.getInforMation(this);

            }
        }
    }


    /**
     * 检查是否所有的已选图片都上传到服务器拿到了图片id
     */
    private boolean checkIfUpAllPic() {
        boolean all = true;
        for (int i = 0; i < picPathList.size(); i++) {
            FeedBackImgBean imgBean = picPathList.get(i);
            if (imgBean.getItemType() == FeedBackImgBean.IMG) {
                if (!TextUtils.isEmpty(imgBean.getPicPath())) {
                    if (TextUtils.isEmpty(imgBean.getUrl()) && imgBean.getId() == 0) {
                        all = false;
                    }
                }
            }
        }
        return all;
    }

    /**
     * 依次上传所有图片
     * 每次只传一张.因为很多方法的回调会相互影响.所以不并发上传
     */
    public void uploadAllPicOneByOne() {
        if (!checkIfUpAllPic()) {
            //如果有图片没上传
            for (int i = 0; i < picPathList.size(); i++) {
                FeedBackImgBean imgBean = picPathList.get(i);
                if (imgBean.getItemType() == FeedBackImgBean.IMG) {
                    if (!TextUtils.isEmpty(imgBean.getPicPath())) {
                        if (TextUtils.isEmpty(imgBean.getUrl()) && imgBean.getId() == 0) {
                            //证明还没上传过服务器这张图片
                            //把图片先上传cos,再想服务器换取id,id赋值给list,最后将id提交给接口
                            uploadPicByCos(imgBean.getPicPath(), i);
                            return;
                        }
                    }

                }
            }
        }
    }

    private void http_submit() {

        //诊疗形式  1会诊方式:交互式(视联网)，2离线式(线下门诊),3移动式(手机远程)',
        String uploadIds = "";
        for (int i = 0; i < picPathList.size(); i++) {
            if (picPathList.get(i).getItemType() == 1) {
                if (i == 0) {
                    uploadIds = picPathList.get(0).getId() + "";
                } else {
                    uploadIds += "," + picPathList.get(i).getId();
                }
            }
        }


        /**
         * hospitalId，meetingRoomId是视联网呼叫视联网需要传的字段。
         */
        /**
         * 心理诊疗 预约相关-新增预约申请接口
         */
//        @POST("gateway/counselingapi/consult/insertConsultInfo")
        NewOrderConsultRequest request = new NewOrderConsultRequest(
                accountService.getGetAccountInfo().userId,//用户ID
                mIntentBean.UserId, //医生UserId
                mIntentBean.zixunTimeId,//	日程ID（调取日程排班返回的ID）
                0,//申请方机构ID
                0,//申请方会议室ID
                16,//业务类型  16诊疗    23心理咨询
                mIntentBean.zixunType,  //诊疗类型     4文字诊疗   5语音诊疗  6 视频诊疗(推荐)
                2, //'预约申请单来源:1网格员（B端）|2个人用户(C端)',
                mIntentBean.UserId,//	被预约人ID（同医生ID userId）
                mIntentBean.problemType//	主诉(问题类型)
        );
        request.setUploadId(uploadIds);//上传文件返回的ID 多个用“,”分隔
        request.setComments(mIntentBean.beizhu);//备注
        mPresenter.getNewOrderConsult(request, this);

    }

    /**
     * @param picPath
     * @param position
     */
    private void uploadPicByCos(String picPath, int position) {
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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 告知服务器
                        SavePathRequestBean requestBean = new SavePathRequestBean();
                        requestBean.prefix = "order_counsel";
                        requestBean.description = "C端预约咨询Android";
                        requestBean.type = 9;
                        //后台非得要后半段的路径.那这就截取吧
                        String str1 = result.accessUrl.substring(0, result.accessUrl.indexOf("myqcloud.com"));
                        String str2 = result.accessUrl.substring(str1.length() + 12, result.accessUrl.length());
                        requestBean.filePath = str2;
                        requestBean.originalFilename = picPath;
                        mPresenter.savePath(requestBean, activity, position);
                    }
                });

            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {

                activity.runOnUiThread(new Runnable() {
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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        ToastUtils.showShort("上传失败");
                    }
                });
            }
        });
    }

    private void showPayTypePop(Context context, int checkPosition) {
        if (mPayTypePopup != null && mPayTypePopup.isShow()) {
            return;
        }
        mPayTypePopup = (BottomCheckOrderPayTypePopup) new XPopup.Builder(context).asCustom(new BottomCheckOrderPayTypePopup(context, checkPosition, "¥" + formatDouble(mIntentBean.price), new BottomCheckOrderPayTypePopup.ResultListener() {
            @Override
            public void onCkecked(int checkPosition, String name) {
                mPayTypeCheckPosition = checkPosition;

                if (mPayTypeCheckPosition == 0) {
                    //支付宝支付
//                    String orderInfo = "alipay_sdk=alipay-sdk-java-3.3.2&app_id=2021001185606251&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E6%9C%8D%E5%8A%A11%E6%AC%A1%E5%85%B10.01%E5%85%83%22%2C%22out_trade_no%22%3A%22PAY2009231702310165%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E6%94%AF%E4%BB%98%E5%AE%9DAPP%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%225m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F58.30.9.142%3A34220%2Fgateway%2Fpayapi%2Falipay%2FalipayAppCallBackNotify&sign=hn2%2FZ2qHFUW54qdS6iDXNQIXQNzivkP%2FzZyhXQ18IcWlKwf6GmlNK7tqNmx37mD4dxxQLH9R%2FneZbybsDSdB63waPG5iMwbKUIwWl1TJxMPtrZ1dO249Yi8bj9QVkDjCcMcYN%2FLsEMFzs%2BEiVZJe0rA06lE%2FZ5AAoaL92as%2FERLymaFxWwZqRGmOhVyfd%2F6g0ings03BmQo0cEibZrjbaR%2FCyh1zbhUSa0dbggqhHWaqivXmfcN2oJFK%2FJHd5p%2Bq1wi6Ptd4yfHpPyEAnm2ZUC2z0tesn2KQ5tuQXMawM6sRXVOzUg97tSar%2FJBRpajIaWbIbKvZnBACPIcNn0qEJg%3D%3D&sign_type=RSA2&timestamp=2020-09-23+17%3A02%3A31&version=1.0";
//                    aliPayV2(OrderPayActivity.this, orderInfo);
                    showLoadingDialog();
//                    net_aliPrePay();
                    PayUtil.PayBean payBean = new PayUtil.PayBean();
                    payBean.totalAmount = mIntentBean.price;
                    if (mOrderResultBean != null) {
                        payBean.goodsOrderNo = mOrderResultBean.getAppNum();
                    }
                    if (accountService != null && accountService.getGetAccountInfo() != null) {
                        payBean.payUserId = accountService.getGetAccountInfo().userId;
                        payBean.payUserName = accountService.getGetAccountInfo().userName;
                    }
                    payUtil.aliPay(payBean, new PayUtil.OnResultListener() {
                        @Override
                        public void onResult(int status, String msg) {
                            hideDialog();
                            if (status == 0) {
                                //支付成功
                                startSuccessActivity("");
                            } else if (status == -1) {
                                ToastUtils.showShort("支付失败");
                            } else if (status == -2) {
                                ToastUtils.showShort("支付取消");
                            }
                        }
                    });
                } else if (mPayTypeCheckPosition == 1) {
                    //微信支付
//                    weixinPay(OrderPayActivity.this);
                    showLoadingDialog();
//                    net_weixinPrePay();
                    PayUtil.PayBean payBean = new PayUtil.PayBean();
                    payBean.totalAmount = mIntentBean.price;
                    if (mOrderResultBean != null) {
                        payBean.goodsOrderNo = mOrderResultBean.getAppNum();
                    }
                    if (accountService != null && accountService.getGetAccountInfo() != null) {
                        payBean.payUserId = accountService.getGetAccountInfo().userId;
                        payBean.payUserName = accountService.getGetAccountInfo().userName;
                    }
                    payUtil.weixinPay(payBean, new PayUtil.OnResultListener() {
                        @Override
                        public void onResult(int status, String msg) {
                            hideDialog();
                            if (status == 0) {
                                //支付成功
                                startSuccessActivity("");
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

    //支付成功
    //跳转到预约成功页面(查询支付结果)    OrderConsultSuccessActivity
    private void startSuccessActivity(String payment_type) {
        OrderConsultSuccessActivity.IntentBean intentBean = new OrderConsultSuccessActivity.IntentBean();
        intentBean.price = mIntentBean.price;
        intentBean.from = "apply";
        //0元不支付  直接跳转
        if (payment_type.equals("0")) {
            intentBean.payment_type = "0";
        }
        intentBean.appNum = mOrderResultBean.getAppNum();
        intentBean.id = mOrderResultBean.getTalkCureId();
        intentBean.sourceType = "apply";
        intentBean.doctorId = mIntentBean.UserId;
        intentBean.fromType = "treatment";
        OrderConsultSuccessActivity.startActivity(activity, intentBean);

    }


    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //回调成功
                        Logger.i("支付成功:" + payResult);
                        PayEventBus eventBus = new PayEventBus();
                        eventBus.status = 0;
                        eventBus.type = 0;
                        EventBus.getDefault().post(eventBus);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        //回调失败
                        Logger.i("支付失败:" + payResult);
                        PayEventBus eventBus = new PayEventBus();
                        eventBus.status = -1;
                        eventBus.type = 0;
                        EventBus.getDefault().post(eventBus);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 支付宝支付业务示例
     */
    public void aliPayV2(Activity activity, String orderInfo) {
        Logger.i("支付参数:" + orderInfo);

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                //用户在商户app内部点击付款，是否需要一个 loading 做为在钱包唤起之前的过渡，这个值设置为 true，将会在调用 pay 接口的时候直接唤起一个
                // loading，直到唤起H5支付页面或者唤起外部的钱包付款页面 loading 才消失。（建议将该值设置为 true，优化点击付款到支付唤起支付页面的过渡过程。）
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void weixinPay(Activity activity, WeixinPrePayResponseBean.ResultBean result) {
        if (result == null) {
            return;
        }
        //初始化微信
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);

        //检查微信版本是否支持支付
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (!isPaySupported) {
            ToastUtils.showLong("未安装微信或当前微信版本过低不支持支付");
            return;
        }
        /**
         * 应用ID	            appid	      String(32)	是	wx8888888888888888	微信开放平台审核通过的应用APPID
         * 商户号	            partnerid	String(32)	是	1900000109	微信支付分配的商户号
         * 预支付交易会话ID	    prepayid	String(32)	是	WX1217752501201407033233368018	微信返回的支付交易会话ID
         * 扩展字段	            package	     String(128)	是	Sign=WXPay	暂填写固定值Sign=WXPay
         * 随机字符串	        noncestr	String(32)	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
         * 时间戳	            timestamp	String(10)	是	1412000000	时间戳，请见接口规则-参数规定
         * 签名	                sign	     String(32)	是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法注意：签名方式一定要与统一下单接口使用的一致
         */
        PayReq request = new PayReq();
//        request.appId = Constants.WX_APP_ID;
//        request.partnerId = "1532360841";
//        request.prepayId = "wx2416113709872309282459cc3a25b70000";
//        request.packageValue = "Sign=WXPay";
//        request.nonceStr = "65b0df23fd2d449ae1e4b2d27151d73b";
//        request.timeStamp = "1600935097";
//        request.sign = "7A45B7D080A541513B37577937C90574";
        request.appId = Constants.WX_APP_ID;
        request.partnerId = result.partnerid + "";
        request.prepayId = result.prepayid + "";
        request.packageValue = result.packageX;
        request.nonceStr = result.noncestr + "";
        request.timeStamp = result.timestamp + "";
        request.sign = result.sign + "";
        api.sendReq(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public static class IntentBean implements Serializable {
        //咨询师id
        public int id;
        public int UserId;
        public String zixunshiName = "";
        public String HospitalName = "";
        public String zixunshiTitle = "";
        public String zixunshiTags = "";
        //咨询时间
        public String zixunTime = "";
        public String zixunTimeId = "";
        //诊疗类型     4文字诊疗   5语音诊疗  6 视频诊疗(推荐)
        public int zixunType;
        public List<FeedBackImgBean> fujian = new ArrayList<>();
        public String beizhu = "";
        public Double price = 0.0;
        //问题类型
        public String problemType = "";
        //咨询机构
        public String advisoryBody = "";
        //咨询师头像
        public String imgUri = "";

        @Override
        public String toString() {
            return "IntentBean{" +
                    "id=" + id +
                    ", zixunshiName='" + zixunshiName + '\'' +
                    ", HospitalName='" + HospitalName + '\'' +
                    ", zixunshiTitle='" + zixunshiTitle + '\'' +
                    ", zixunshiTags='" + zixunshiTags + '\'' +
                    ", zixunTime='" + zixunTime + '\'' +
                    ", zixunTimeId='" + zixunTimeId + '\'' +
                    ", zixunType=" + zixunType +
                    ", fujian=" + fujian +
                    ", beizhu='" + beizhu + '\'' +
                    ", price=" + price +
                    ", problemType='" + problemType + '\'' +
                    ", advisoryBody='" + advisoryBody + '\'' +
                    '}';
        }
    }
}
