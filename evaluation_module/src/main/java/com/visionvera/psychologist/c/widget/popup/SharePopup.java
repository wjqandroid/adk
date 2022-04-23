package com.visionvera.psychologist.c.widget.popup;

import android.content.Context;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.psychologist.c.R;

public class SharePopup extends BottomPopupView {

    private SharePopupListener mListener;

    public SharePopup(@NonNull Context context, SharePopupListener listener) {
        super(context);
        mListener=listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_share_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        RelativeLayout qqLayout=findViewById(R.id.evaluation_module_share_qq_layout);
        RelativeLayout wechatLayout=findViewById(R.id.evaluation_module_share_wechat_layout);
        RelativeLayout friendLayout=findViewById(R.id.evaluation_module_share_friend_layout);
        RelativeLayout zoneLayout=findViewById(R.id.evaluation_module_share_zone_layout);
        RelativeLayout sinaLayout=findViewById(R.id.evaluation_module_share_sina_layout);
        RelativeLayout cancelLayout=findViewById(R.id.evaluation_module_share_popup_cancel);

        if (mListener!=null){
            qqLayout.setOnClickListener(v -> mListener.OnSharePopup(R.id.evaluation_module_share_qq_layout));
            wechatLayout.setOnClickListener(v -> mListener.OnSharePopup(R.id.evaluation_module_share_wechat_layout));
            friendLayout.setOnClickListener(v -> mListener.OnSharePopup(R.id.evaluation_module_share_friend_layout));
            zoneLayout.setOnClickListener(v -> mListener.OnSharePopup(R.id.evaluation_module_share_zone_layout));
            sinaLayout.setOnClickListener(v -> mListener.OnSharePopup(R.id.evaluation_module_share_sina_layout));
            cancelLayout.setOnClickListener(v -> dismiss());
        }


    }

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {

    }



    public interface SharePopupListener{
       void OnSharePopup(int shareId);
    }


}
