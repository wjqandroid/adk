package com.visionvera.psychologist.c.utils.photo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.visionvera.library.util.BaseUtils;

import java.io.File;
import java.util.Date;

/**
 * @author 刘传政
 * @date 2019/4/15 16:54
 * QQ:1052374416
 * telephone:18501231486
 * 作用:图片选择activity.无界面,只是一个统一的入口
 * 注意事项:
 */
public class MyTakePhotoActivity extends Activity {
    public static File mTmpFile;
    public static Uri uri;//照片uri
    private static int type;
    private static OnGetPicPathListener listener;
    public static final int photoAlbum = 1;
    public static final int takePhoto = 2;
    public static String photoPath = "";

    public static void getPicPath(Context context, int type, OnGetPicPathListener listener) {
        MyTakePhotoActivity.type = type;
        MyTakePhotoActivity.listener = listener;
        mTmpFile = null;
        uri = null;
        photoPath = "";
        Intent intent = new Intent(context, MyTakePhotoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions();
    }

    private void checkPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                        if (type == photoAlbum) {
                            photoAlbum();
                        } else if (type == takePhoto) {
                            takePhoto();
                        }
                    } else {
                        // Oups permission denied
                        finish();
                        ToastUtils.showShort("必须授予权限");
                        if (null != listener) {
                            listener.onComplete(photoPath);
                        }
                    }
                });

       /* rxPermissions
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        // `permission.name` is granted !
                        if (TYPE == photoAlbum) {
                            photoAlbum();
                        } else if (TYPE == takePhoto) {
                            takePhoto();
                        }
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                        finish();
                        ToastUtils.showShort(this, "必须授予权限");
                        if (null != listener) {
                            listener.onComplete(photoPath);
                        }
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
                        finish();
                        ToastUtils.showShort(this, "您以勾选不在提示,请在设置中开启权限");
                        if (null != listener) {
                            listener.onComplete(photoPath);
                        }
                    }
                });*/
    }

    /**
     * 相册中选图片
     */
    private void photoAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, photoAlbum);
    }

    ;

    /**
     * 相机取图片
     */
    private void takePhoto() {
        // 跳转到系统照相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // 设置系统相机拍照后的输出路径
            // 创建临时文件
            mTmpFile = createFile(getApplicationContext());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //第二个参数为 包名.fileprovider

                uri = FileProvider.getUriForFile(this, BaseUtils.getPackageName(this)+".fileprovider", mTmpFile);
                cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                uri = Uri.fromFile(mTmpFile);
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(cameraIntent, takePhoto);
        } else {
            ToastUtils.showShort("无相机");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case photoAlbum:

                    photoPath = GetPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());

                    break;
                case takePhoto:
                    //请求相机
                    if (mTmpFile != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            photoPath = String.valueOf(mTmpFile);
                        } else {
                            photoPath = uri.getEncodedPath();
                        }

                    }
                    break;

            }
        }
        finish();
        Logger.i("拍照返回图片路径:" + photoPath);
        if (null != listener) {
            listener.onComplete(photoPath);
        }
    }

    /**
     * 获取拍照相片存储文件
     *
     * @param context
     * @return
     */
    public static File createFile(Context context) {
        File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String timeStamp = String.valueOf(new Date().getTime());
            file = new File(Environment.getExternalStorageDirectory() +
                    File.separator + timeStamp + ".jpg");
        } else {
            File cacheDir = context.getCacheDir();
            String timeStamp = String.valueOf(new Date().getTime());
            file = new File(cacheDir, timeStamp + ".jpg");
        }
        return file;
    }

    public interface OnGetPicPathListener {
        void onComplete(String picPath);
    }
}
