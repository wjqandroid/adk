package com.visionvera.psychologist.c.module.usercenter.activity;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
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
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.util.BaseUtils;
import com.visionvera.library.util.EmojiFilter;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.dialog.bottompopup.BottomPopup;
import com.visionvera.library.widget.dialog.bottompopup.BottomPopupBean;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.usercenter.adapter.FeedBackImgAdapter;
import com.visionvera.psychologist.c.module.usercenter.adapter.QuestionsAdapter;
import com.visionvera.psychologist.c.module.usercenter.bean.FeedBackImgBean;
import com.visionvera.psychologist.c.module.usercenter.bean.QuestionBean;
import com.visionvera.psychologist.c.module.usercenter.bean.UploadPicResponseBean;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.module.usercenter.presenter.FeedBackPresenter;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;
import com.visionvera.psychologist.c.utils.cos.TencentCosManager;
import com.visionvera.psychologist.c.utils.photo.MyTakePhotoActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import rxhttp.wrapper.param.RxHttp;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @Desc 意见反馈
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class FeedBackActivity extends BaseMVPActivity<IContract.FeedBack.FeedBackView, FeedBackPresenter> implements OnItemClickListener, OnItemChildClickListener {
    @BindView(R2.id.evaluation_module_tv_title)
    TextView tvTitle;

    @BindView(R2.id.rv_feedback_questions)
    RecyclerView questionRecyclerView;

    @BindView(R2.id.tv_input_num)
    TextView tvInputNum;

    @BindView(R2.id.et_problem)
    EditText etProblem;

    @BindView(R2.id.rv_feedback_imgs)
    RecyclerView imgRecyclerView;


    @BindView(R2.id.et_contract)
    EditText etContract;

    @BindView(R2.id.tv_commit)
    TextView tvCommit;
    @BindView(R2.id.evaluation_module_upload_appendix_notice)
    TextView upload_appendix_notice;

    private QuestionsAdapter questionsAdapter;
    private FeedBackImgAdapter feedBackImgAdapter;
    private List<QuestionBean> mDataList = new ArrayList<>();
    private String[] data = new String[]{"闪退", "卡顿", "黑/白屏", "死机", "界面显示异常", "其他"};
    private String[] problems = new String[]{"1", "2", "3", "4", "5", "0"};

    private List<FeedBackImgBean> picPathList = new ArrayList<>();
    private BasePopupView cameraAlbumPopupView;
    private List<BottomPopupBean> cameraAlbumList=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_feedback;
    }

    @Override
    protected void doYourself() {
        initView();
        initData();
    }

    private void initView() {
        questionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        questionsAdapter = new QuestionsAdapter();
        questionRecyclerView.setAdapter(questionsAdapter);
        questionsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                tvCommit.setClickable(true);
                tvCommit.setBackgroundDrawable(getResources().getDrawable(R.drawable.evaluation_module_solid_3e86fe_r4));
                QuestionBean bean = questionsAdapter.getItem(position);
                questionsAdapter.refreshSelect(bean);
            }
        });

        imgRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        picPathList.add(new FeedBackImgBean(FeedBackImgBean.CAMERA, ""));

        feedBackImgAdapter = new FeedBackImgAdapter(picPathList);
        feedBackImgAdapter.setOnItemClickListener(this);
        feedBackImgAdapter.addChildClickViewIds(R.id.evaluation_module_feedback_iv_delete);
        feedBackImgAdapter.setOnItemChildClickListener(this);
        imgRecyclerView.setAdapter(feedBackImgAdapter);
        notifyPicTips();
        etContract.setFilters(new InputFilter[]{new EmojiFilter()});
        etProblem.setFilters(new InputFilter[]{new EmojiFilter()});
        etProblem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.toString().length();
                tvInputNum.setText(length + "/200");
            }
        });
    }

    private void notifyPicTips() {
        //当一张图片也没选时,就显示提示文字,否则不显示
        if (picPathList != null) {
            if (picPathList.size() <= 1) {
                upload_appendix_notice.setVisibility(View.VISIBLE);
                return;
            }
        }
        upload_appendix_notice.setVisibility(View.GONE);
    }

    private void initData() {
        tvTitle.setText(R.string.evaluation_module_feedback);
        for (int i = 0; i < 6; i++) {
            QuestionBean bean = new QuestionBean();
            bean.setContent(data[i]);
            bean.setSelect(false);
            bean.setProblem(problems[i]);
            mDataList.add(bean);
        }
        questionsAdapter.addData(mDataList);

        cameraAlbumList.add(new BottomPopupBean("相册",1));
        cameraAlbumList.add(new BottomPopupBean("拍照",2));
        cameraAlbumList.add(new BottomPopupBean("取消",3));
    }

    @OnClick({R2.id.evaluation_module_iv_back, R2.id.tv_commit})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_iv_back) {
            finish();
        } else if (viewId == R.id.tv_commit) {
            String problem = questionsAdapter.getProblem();
            if (TextUtils.isEmpty(problem)) {
                ToastUtils.showShort("请至少选择一个类型");
                return;
            }
            //用户已完善信息
            showLoadingDialog();
            if (checkIfUpAllPic()) {
                requestFeedBack();
            } else {
                uploadAllPicOneByOne();
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


    /**
     * 获取照片
     */
    private void selectPhotoMethod(int position) {
        if (cameraAlbumPopupView==null){
            cameraAlbumPopupView=new XPopup.Builder(this)
                    .asCustom(new BottomPopup(this, "选择上传方式", cameraAlbumList, new BottomPopup.BottomPopupItemClick() {
                        @Override
                        public void BottomPopupItemClickListener(BottomPopupBean bottomPopupBean) {
                            if (bottomPopupBean.getId()==1){
                                //相册
                                MyTakePhotoActivity.getPicPath(activity, MyTakePhotoActivity.photoAlbum, new MyTakePhotoActivity.OnGetPicPathListener() {
                                    @Override
                                    public void onComplete(String picPath) {

                                        parseImgData(picPath, position);
                                    }
                                });
                            }else if (bottomPopupBean.getId()==2){
                                //拍照
                                MyTakePhotoActivity.getPicPath(activity, MyTakePhotoActivity.takePhoto, new MyTakePhotoActivity.OnGetPicPathListener() {
                                    @Override
                                    public void onComplete(String picPath) {

                                        parseImgData(picPath, position);
                                    }
                                });
                            }
                        }
                    }));

        }
        cameraAlbumPopupView.show();
    }



    /**
     * 处理添加的图片附件
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
//            uploadPic(activity, picPath, position);
//            uploadPicByCos(picPath, position);
            feedBackImgAdapter.notifyDataSetChanged();
            notifyPicTips();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FeedBackImgBean bean = picPathList.get(position);
        String picPath = bean.getPicPath();
        if (TextUtils.isEmpty(picPath)) {
            selectPhotoMethod(position);
        } else {
            ImageView imageView = view.findViewById(R.id.evaluation_module_feedback_iv);
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
            new XPopup.Builder(activity).asImageViewer(imageView, position, list, false,false, -1, -1, -1, false, new OnSrcViewUpdateListener() {
                @Override
                public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
                    // 作用是当Pager切换了图片，需要更新源View
                    popupView.updateSrcView((ImageView) imgRecyclerView.getChildAt(position).findViewById(R.id.evaluation_module_feedback_iv));
                }
            }, new ImageLoader())
                    .show();
        }
    }

    @Override
    protected void initMVP() {
        mView = new IContract.FeedBack.FeedBackView() {
            @Override
            public void onFeedBack(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:

                        break;
                    case SERVER_ERROR:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        ToastUtils.showShort("提交成功");

                        finish();
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
                                requestFeedBack();
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
        mPresenter = new FeedBackPresenter(this, mView);
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

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (view.getId() == R.id.evaluation_module_feedback_iv_delete) {
            if (picPathList.size() == 4) {
                FeedBackImgBean bean = picPathList.get(3);
                if (TextUtils.isEmpty(bean.getPicPath())) {
                    picPathList.remove(position);
                } else {
                    bean.setItemType(FeedBackImgBean.CAMERA);
                    bean.setPicPath("");
                }
            } else {
                picPathList.remove(position);
            }
            feedBackImgAdapter.notifyDataSetChanged();
            notifyPicTips();
        }
    }

    /**
     * 提交意见反馈
     */
    public void requestFeedBack() {
        String problem = questionsAdapter.getProblem();
        if (TextUtils.isEmpty(problem)){
            return;
        }
        String ids = "";
        Map<String, String> params = new HashMap<>();
        params.put("os", Build.MODEL + "");
        params.put("osVersion", BaseUtils.getVersionName(this));
        params.put("problem", problem);
        params.put("otherDesc", etProblem.getText().toString().trim());
        params.put("contactWay", etContract.getText().toString().trim());

        for (int i = 0; i < picPathList.size(); i++) {
            FeedBackImgBean bean = picPathList.get(i);
            String picPath = bean.getPicPath();
            if (!TextUtils.isEmpty(picPath)) {
                int id = bean.getId();
                ids = ids + id + ",";
            }
        }
        if (ids.endsWith(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        params.put("uploadId", ids);
        mPresenter.getFeedBack(RxPartMapUtils.toRequestBodyOfStringMap(params), this);
    }

    /**
     * 上传图片
     *
     * @param activity
     * @param picPath
     * @param position
     */
    private void uploadPic(BaseActivity activity, String picPath, int position) {
        String spToken = SPUtils.getInstance(Constant.SP.UserInfo.USER_INFO).getString(Constant.SP.UserInfo.token, "");
        //加入压缩，否则上传失败。
        Luban.with(activity)
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
                                .add("prefix", "feedbackUpImg")//生成文件名前缀,例如医生头像，这个字段就是：doctorIcon followUpImg：随访图片
                                .add("type", "7")//上传文件类型 1医生头像 6随访图片
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
                                    if (uploadPicResponseBean.getCode() == 0) {
                                        picPathList.get(position).setUrl(uploadPicResponseBean.getResult().getFileUrl());
                                        picPathList.get(position).setId(uploadPicResponseBean.getResult().getFileId());
                                        feedBackImgAdapter.notifyDataSetChanged();
                                        notifyPicTips();
                                    }
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
                FeedBackActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 告知服务器
                        SavePathRequestBean requestBean = new SavePathRequestBean();
                        requestBean.prefix = "feedback";
                        requestBean.description = "C端意见反馈Android";
                        requestBean.type = 7;
                        //后台非得要后半段的路径.那这就截取吧
                        String str1 = result.accessUrl.substring(0, result.accessUrl.indexOf("myqcloud.com"));
                        String str2 = result.accessUrl.substring(str1.length() + 12, result.accessUrl.length());
                        requestBean.filePath = str2;
                        requestBean.originalFilename = picPath;
                        mPresenter.savePath(requestBean, FeedBackActivity.this, position);
                    }
                });

            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {

                FeedBackActivity.this.runOnUiThread(new Runnable() {
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
                FeedBackActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        ToastUtils.showShort("上传失败");
                    }
                });
            }
        });
    }
}
