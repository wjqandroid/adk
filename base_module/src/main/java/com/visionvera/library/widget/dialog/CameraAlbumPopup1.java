package com.visionvera.library.widget.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.R;


/**
 * author lilongfeng
 * date 2019/6/21
 * 相册或者拍照的弹窗
 */

public class CameraAlbumPopup1 extends BottomPopupView {

    private AlbumListener mAlbumListener;
    private CameraListener mCameraListener;
    public CameraAlbumPopup1(@NonNull Context context, AlbumListener albumListener, CameraListener cameraListener) {
        super(context);
        mAlbumListener=albumListener;
        mCameraListener=cameraListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_camera_album_popup1;
    }


    @Override
    protected void onCreate() {
        super.onCreate();

        findViewById(R.id.album).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlbumListener.albumClickListener();
            }
        });
        findViewById(R.id.camera).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraListener.cameraClickListener();
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 相册的点击事件接口
     */
    public interface AlbumListener{
        void albumClickListener();
    }

    /**
     * 相册的点击事件接口
     */
    public interface CameraListener{
        void cameraClickListener();
    }

}
