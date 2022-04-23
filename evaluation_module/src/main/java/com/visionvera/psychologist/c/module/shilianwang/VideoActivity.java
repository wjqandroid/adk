//package com.visionvera.psychologist.c.module.shilianwang;
//
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.Nullable;
//
//import com.visionvera.library.base.BaseActivity;
//import com.visionvera.library.util.OneClickUtils;
//import com.visionvera.psychologist.c.R;
//import com.visionvera.psychologist.c.R2;
//import com.visionvera.psychologist.c.eventbus.SurfaceViewBus;
//import com.visionvera.zong.api.VVClient;
//import com.visionvera.zong.model.http.CovertBean;
//import com.visionvera.zong.model.socket.LiveListModel;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//import org.webrtcold.SurfaceViewRenderer;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * author:lilongfeng
// * date:2019/8/9
// * 描述:视联网sdk视频通话页面
// */
//
//public class VideoActivity extends BaseActivity {
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.evaluation_module_activity_video;
//    }
//
//    @Override
//    protected void doYourself() {
//
//    }
//
//    @BindView(R2.id.container)
//    RelativeLayout container;
//
//    private CovertBean.ItemsBean callingUser;
//    private int surfaceMode;
//    private LiveListModel liveListModel;
//    private String deviceId;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
//    }
//
//
//    @OnClick({R2.id.cancel_video_button})
//    void onClick(View view) {
//        if (OneClickUtils.isFastClick()) {
//            return;
//        }
//        int viewId = view.getId();
//        if (viewId == R.id.cancel_video_button) {
//            closeLive();
//        }
//    }
//
//
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void onEvent(SurfaceViewBus event) {
//
//        if (event.getmData() != null && event.getmData().length > 1) {
//            SurfaceViewRenderer[] data = event.getmData();
//            container.addView(data[0], 0);
//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) data[1].getLayoutParams();
//            params.addRule(RelativeLayout.ALIGN_PARENT_TOP | RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
//            data[1].setLayoutParams(params);
//            container.addView(data[1], 1);
//            if (event.getmItemBean() != null) {
//                callingUser = event.getmItemBean();
//            }
////        channel_tv.setText("频道号:" +VVClient.getInstance().getLiveStreamChannel());
//
//            surfaceMode = event.getSurfaceMode();
//
//            if (event.getModel() != null) {
//                liveListModel = event.getModel();
//            }
//
//            deviceId = event.getDeviceId();
//
//        } else {
//            finish();
//        }
//
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
////         * 监听收看直播  视频通话的关闭
//        VVClient.getInstance().observeVideoCallClose(new VVClient.Callback<String>() {
//            @Override
//            public void onSuccess(String data) {
//                container.removeAllViews();
//                finish();
//            }
//
//            @Override
//            public void onFailed(int errCode, String errMsg) {
//
//            }
//        });
//    }
//
//    private void closeLive() {
//        switch (surfaceMode) {
//            case 5:
//                VVClient.getInstance().stopLiveStream(new VVClient.Callback<String>() {
//                    @Override
//                    public void onSuccess(String data) {
//                        container.removeAllViews();
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailed(int i, String s) {
//
//                    }
//                });
//                break;
//            case 6:
//                if (VVClient.getInstance().isOnCalling()) {
//                    VVClient.getInstance().closeVideoCall(new VVClient.Callback<String>() {
//                        @Override
//                        public void onSuccess(String data) {
//                            container.removeAllViews();
//                            finish();
//                        }
//
//                        @Override
//                        public void onFailed(int errCode, String errMsg) {
//
//                        }
//                    });
//                } else {
//                    if (VVClient.getInstance().isVideoCallAvailable()) return;
//
//                    closeVideoCall(VVClient.CALL_MODE.CALL_SLW, deviceId, deviceId, "0", null);
//
//                }
//                break;
//            case 7:
//                if (liveListModel != null) {
//                    VVClient.getInstance().stopWatchingLiveStream(String.valueOf(liveListModel.UserID),
//                            liveListModel.Account, liveListModel.ChannelNumber, new VVClient.Callback<String>() {
//                                @Override
//                                public void onSuccess(String s) {
//                                    container.removeAllViews();
//                                    finish();
//                                }
//
//                                @Override
//                                public void onFailed(int i, String s) {
//
//                                }
//                            });
//                }
//
//                break;
//        }
//
//    }
//
//    private void closeVideoCall(VVClient.CALL_MODE callMode, String channelNum, String account, String userId, String userName) {
//        VVClient.getInstance().closeVideoCall(callMode, channelNum, userId, account, userName, new VVClient.Callback<String>() {
//            @Override
//            public void onSuccess(String data) {
//                container.removeAllViews();
//                finish();
//            }
//
//            @Override
//            public void onFailed(int errCode, String errMsg) {
//
//            }
//        });
//    }
//
//    @Override
//    protected void onDestroy() {
//        container.removeAllViews();
//        super.onDestroy();
//        EventBus.getDefault().removeAllStickyEvents();
//        EventBus.getDefault().unregister(this);
//
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//}
