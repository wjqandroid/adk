package com.visionvera.psychologist.c.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.core.content.FileProvider;
import androidx.lifecycle.LifecycleObserver;

import com.blankj.utilcode.util.SPUtils;
import com.lxj.xpopup.XPopup;
import com.visionvera.library.base.BaseObserver;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.psychologist.c.BuildConfig;
import com.visionvera.psychologist.c.net.EvaluationModuleApi;
import com.visionvera.psychologist.c.update.bean.UpdateBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * APP升级管理类
 * */
public class UpdateManager implements LifecycleObserver, IBaseView {
    //网络请求的保存
    private CompositeDisposable compositeDisposable;

    private Context mContext;
    //下载地址
    private String downLoadUrl;
    //服务器版本号
    private String netVersion;
    //更新信息
    private String updateMessage;
    //更新提示框
    private UpdatePopup updatePopup;

    private UpdateListener listener;

    private boolean cancelUpdate = false;//取消更新
    private DownLoadDialog dialog;
    private String mSavePath;
    private int progress;//进度值
    private static final int DOWNLOAD = 0;//下载
    private static final int DOWNLOAD_FINISH = 1;//下载完成

    public UpdateManager(Context context, UpdateListener listener) {
        this.mContext = context;
        this.listener = listener;
    }

    /**
     * 将被观察者和观察者建立订阅,并将此次请求加入管理列表,以便于在ondestory等中断开连接
     *
     * @param observable
     * @param observer
     */
    public <T extends BaseResponseBean> void goToNet(Observable<T> observable, BaseObserver<T> observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable
                .add(observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer));
    }

    public void checkUpdate(){
        //获取当前版本号
        String localVersion = BuildConfig.VERSION_NAME;
//        String localVersion = "3.0.2.2";

        Map map = new HashMap();
        map.put("currentVersionNumber",localVersion);
        map.put("appVersionType","2");//版本类型 1：iOS 2：安卓
        map.put("mobileClient","2");//客户端类型：1基层工作人员端2用户端3服务端

        goToNet(RetrofitManager.create(EvaluationModuleApi.class).checkUpdate(map),
                new BaseObserver<BaseResponseBean<UpdateBean>>(this,false) {
                    @Override
                    protected void onResult(BaseResponseBean<UpdateBean> responseBean, ResultType resultType, String errorMsg) {
                        switch (resultType){
                            case NET_ERROR:
                            case SERVER_ERROR:
                            case SERVER_NORMAL_DATANO:
//                                ToastUtil.showShort("匿名电话拨打太频繁，请30秒后重试！");
                                listener.onUpdateCancel();
                                break;
                            case SERVER_NORMAL_DATAYES:
                                UpdateBean bean = responseBean.getResult();
                                boolean needUpdate = bean.needUpdate;
                                if(needUpdate){
                                    downLoadUrl = bean.downloadUrl;
                                    netVersion = bean.versionNumber;
                                    updateMessage = bean.updateInfo;
                                    //是否强制更新
                                    boolean mandatory = bean.mandatory;
                                    if(mandatory){ //强制更新
                                        showUpdatePopup(updateMessage, true, downLoadUrl);
                                    }else {
                                        showUpdatePopup(updateMessage, false, downLoadUrl);
                                        //取出之前被忽略的升级版本号
                                        /*String v= SPUtils.getInstance(Constant.SP.Update.spName).getString(Constant.SP.Update.Key.version);
                                        if(!TextUtils.isEmpty(v) && v.equals(netVersion)){
                                            //如果被忽略的版本号跟此次升级的netVersion相等则不显示升级提示
                                            listener.onUpdateCancel();
                                        }else {
                                            showUpdatePopup(updateMessage, false, downLoadUrl);
                                        }*/
                                    }
                                }
                                break;
                        }
                    }
                });

    }

    private void showUpdatePopup(String updateMessage,boolean isForce,String downLoadUrl){
        updatePopup = new UpdatePopup(mContext, updateMessage, isForce, new UpdatePopup.ResultListener() {
            @Override
            public void onConfirm() {
                showDownloadDialog(isForce);
//                listener.onUpdate();
//                downloadApk();
            }

            @Override
            public void onCancel() {
                //缓存忽略升级的版本号
                SPUtils.getInstance(Constant.SP.Update.spName).put(Constant.SP.Update.Key.version, netVersion);
                listener.onUpdateCancel();
            }
        });

        if(isForce){
            new XPopup.Builder(mContext)
                    //如果不加这个，评论弹窗会移动到软键盘上面
                    .moveUpToKeyboard(false)
                    .dismissOnBackPressed(false)
                    .dismissOnTouchOutside(false)
                    .asCustom(updatePopup)
                    .show();
        }else {
            new XPopup.Builder(mContext)
                    //如果不加这个，评论弹窗会移动到软键盘上面
                    .moveUpToKeyboard(false)
                    .asCustom(updatePopup)
                    .show();
        }

    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog(boolean isForce) {
        //取消更新标志充值
        cancelUpdate = false;
        // 构造软件下载对话框
        if(isForce){
            dialog = new DownLoadDialog((Activity) mContext, "软件更新", "");
        }else {
            dialog = new DownLoadDialog((Activity) mContext, "软件更新", "取消");
        }
        //调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
        dialog.setCancelable(false);
        dialog.setDownLoadListener(new DownLoadDialog.DownLoadListener() {
            @Override
            public void onDissmiss() {
                //缓存忽略升级的版本号
                SPUtils.getInstance(Constant.SP.Update.spName).put(Constant.SP.Update.Key.version, netVersion);

                // 设置取消状态
                cancelUpdate = true;
                listener.onUpdateCancel();
            }
        });

        // 下载文件
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    public void downloadApk() {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    //                    mSavePath = sdpath + "download";
                    mSavePath = sdpath + mContext.getPackageName();
//                    LogUtil.e("mSavePath==" + mSavePath);
                    // 创建连接 URL url = new URL("https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/apk/sample-debug.apk");
                    URL url = new URL(downLoadUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();
                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    //"xxx.apk"等于软件名字
                    File apkFile = new File(mSavePath, "app_xinli.apk");
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
//                    mProgress.setMax(100);
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
//                        LogUtil.e("进度" + progress);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void installApk() {
        File apkfile = new File(mSavePath, "app_xinli.apk");
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(mContext, "com.visionvera.psychologist.c.fileprovider", apkfile);

            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            i.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            i.setDataAndType(Uri.fromFile(apkfile),
                    "application/vnd.android.package-archive");
        }
        mContext.startActivity(i);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    dialog.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    // 取消下载对话框显示
                    dialog.dismiss();
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };

    public interface UpdateListener {
        void onUpdate();

        void onUpdateCancel();
    }

}
