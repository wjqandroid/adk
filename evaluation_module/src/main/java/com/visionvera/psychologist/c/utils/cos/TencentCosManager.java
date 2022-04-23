package com.visionvera.psychologist.c.utils.cos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.transfer.COSXMLUploadTask;
import com.tencent.cos.xml.transfer.TransferConfig;
import com.tencent.cos.xml.transfer.TransferManager;
import com.tencent.cos.xml.transfer.TransferState;
import com.tencent.cos.xml.transfer.TransferStateListener;
import com.tencent.qcloud.core.auth.BasicLifecycleCredentialProvider;
import com.tencent.qcloud.core.auth.QCloudLifecycleCredentials;
import com.tencent.qcloud.core.auth.SessionQCloudCredentials;
import com.tencent.qcloud.core.common.QCloudClientException;
import com.visionvera.library.base.Constant;
import com.visionvera.library.net.RetrofitManager;
import com.visionvera.psychologist.c.net.EvaluationModuleApi;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author 刘传政
 * @date 2020/3/10 15:36
 * QQ:1052374416
 * 电话:18501231486
 * 作用:腾讯云cos对象存储使用管理类
 * 注意事项:
 */
public class TencentCosManager {
    private static TencentCosManager instance;
    private static Context context;
    private String bucket = "slyl-mhsp-1301295327";//cos后台的存储桶名称.本项目默认填写一个,如需调试别的桶,请自行更换
    private CosXmlService cosXmlService;
    private TransferManager transferManager;
    private CosXmlProgressListener progressListener;
    private CosXmlResultListener resultListener;
    private InnerListener innerListener;

    private TencentCosManager() {
    }

    public static TencentCosManager getInstance(Context context) {
        TencentCosManager.context = context;
        if (instance == null) {
            synchronized (TencentCosManager.class) {
                if (instance == null) {
                    instance = new TencentCosManager();
                }
            }
        }
        return instance;
    }

    /**
     * @param cosPath          上传到 COS 的路径,只作为参考,因为服务器会加前缀.所以不是最终cos路径
     * @param localPath        本地文件路径
     * @param progressListener 进度
     * @param resultListener   cos结果
     * @param innerListener    内部业务失败时通知调用者
     *                         这里的listener都不能为null,我不一一判断了,传null出错自己负责
     */
    public void upload(String cosPath, String localPath, CosXmlProgressListener progressListener, CosXmlResultListener resultListener, InnerListener innerListener) {
        this.progressListener = progressListener;
        this.resultListener = resultListener;
        this.innerListener = innerListener;
        checkPermissions(cosPath, localPath);

    }

    /**
     * 接入SDK时，SDK本身自带了4个权限，
     * 1.	存储权限
     * 2.	电话权限
     * 3.	相机权限
     * 4.	麦克风权限
     * 这4个权限都开启后，SDK初始化才能进行。否则会崩溃。
     *
     * @param cosPath
     * @param localPath
     */
    private void checkPermissions(String cosPath, String localPath) {
        RxPermissions rxPermissions = new RxPermissions((Activity) context);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
//                        initCos(cosPath, localPath);
                        compressOne(cosPath, localPath);
                    } else {
                        // Oups permission denied
                        innerListener.onFailed("用户拒绝授予权限");
                    }
                });

    }

    private void compressOne(String cosPath, String localPath) {
        Luban.with(context)
                .load(new File(localPath))
                .ignoreBy(300)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File compressedFile) {
                        Logger.i("压缩成功:" + localPath);
                        initCos(cosPath, compressedFile.getAbsolutePath());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
    }

    /**
     * 在自己服务器后台后去临时密钥,然后获取cos sdk的实体类
     */
    private void initCos(String cosPath, String localPath) {
        //想后台请求临时密钥,和一些其他信息
        MiYaoRequestBean requestBean = new MiYaoRequestBean();
        RetrofitManager.create(EvaluationModuleApi.class)
                .getCosMiyao(requestBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MiYaoResponseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MiYaoResponseBean responseBean) {
                        realInitCos(responseBean, cosPath, localPath);
                    }

                    @Override
                    public void onError(Throwable e) {
                        innerListener.onFailed("获取临时密钥失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 已经拿到了临时密钥,去调cos sdk初始化
     *
     * @param responseBean
     */
    private void realInitCos(MiYaoResponseBean responseBean, String cosPath, String localPath) {
        if (responseBean.code != Constant.NetCode.success2) {
            innerListener.onFailed("获取临时密钥失败");
            return;
        }
        String region = "ap-beijing"; // 设置 Bucket 的 region，后续上传下载的 bucket 的 region 都默认为 ap-beijing
        CosXmlServiceConfig cosXmlServiceConfig = new CosXmlServiceConfig.Builder()
                .setRegion(region)
                .setDebuggable(true)
                .isHttps(true)
                .builder();

        /**
         * 以下需要您自己搭建一个临时密钥服务来生成客户端所需的签名，搭建文档请参考：
         *
         * https://cloud.tencent.com/document/product/436/9068
         */
        MyCredentialProvider myCredentialProvider = new MyCredentialProvider(responseBean.result);
        cosXmlService = new CosXmlService(context, cosXmlServiceConfig, myCredentialProvider);

        TransferConfig transferConfig = new TransferConfig.Builder().build();
        transferManager = new TransferManager(cosXmlService, transferConfig);
        //后台规定了cos的路径前缀
        String finalCosPath = responseBean.result.path + "/" + cosPath;
        realUpload(finalCosPath, localPath);
    }

    /**
     * 真正调用cos方法上传
     *
     * @param finalCosPath
     * @param localPath
     */
    private void realUpload(String finalCosPath, String localPath) {
        // 开始上传，并返回生成的 COSXMLUploadTask
        COSXMLUploadTask cosxmlUploadTask = transferManager.upload(bucket, finalCosPath,
                localPath, null);

        // 设置上传状态监听
        cosxmlUploadTask.setTransferStateListener(new TransferStateListener() {
            @Override
            public void onStateChanged(final TransferState state) {
            }
        });

        /*// 设置上传进度监听
        cosxmlUploadTask.setCosXmlProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(final long complete, final long target) {
            }
        });

        // 设置结果监听
        cosxmlUploadTask.setCosXmlResultListener(new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                String accessUrl = result.accessUrl;
                String requestURL = request.getRequestURL();
                Logger.i(accessUrl+requestURL);
                Toast.makeText(context,"上传成功",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {
                Logger.i("onFail"+exception+serviceException);
            }
        });*/
        // 设置上传进度监听
        cosxmlUploadTask.setCosXmlProgressListener(progressListener);

        // 设置结果监听
        cosxmlUploadTask.setCosXmlResultListener(resultListener);
    }

    class MyCredentialProvider extends BasicLifecycleCredentialProvider {

        private MiYaoResponseBean.ResultBean resultBean;

        public MyCredentialProvider(MiYaoResponseBean.ResultBean resultBean) {
            this.resultBean = resultBean;
        }

        @Override
        protected QCloudLifecycleCredentials fetchNewCredentials() throws QCloudClientException {

            // 首先从您的临时密钥服务器获取包含了签名信息的响应

            // 然后解析响应，获取密钥信息
            String tmpSecretId = resultBean.credentials.tmpSecretId; //临时密钥 secretId
            String tmpSecretKey = resultBean.credentials.tmpSecretKey; //临时密钥 secretKey
            String sessionToken = resultBean.credentials.sessionToken; //临时密钥 Token
            long expiredTime = resultBean.expiredTime;//临时密钥有效截止时间戳，单位是秒

            /*强烈建议返回服务器时间作为签名的开始时间，用来避免由于用户手机本地时间偏差过大导致的签名不正确 */
            // 返回服务器时间作为签名的起始时间
            long startTime = resultBean.startTime; //临时密钥有效起始时间，单位是秒

            // 最后返回临时密钥信息对象
            return new SessionQCloudCredentials(tmpSecretId, tmpSecretKey, sessionToken, startTime, expiredTime);
        }
    }

    /**
     * 上传时要走我们自己很多业务,所以内部业务失败了可能导致loading无法消失
     * 这里在失败时通知一下,调用者注意在回调时做一些ui处理即可
     */
    public interface InnerListener {
        void onFailed(String errMsg);
    }
}
