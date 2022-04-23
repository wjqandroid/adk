//package com.visionvera.psychologist.c.utils;
//
//import android.Manifest;
//import android.app.Activity;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.gson.Gson;
//import com.tbruyelle.rxpermissions2.RxPermissions;
//import com.visionvera.library.arouter.commonbean.AccountBean;
//import com.visionvera.library.base.Constant;
//import com.visionvera.zong.api.VVClient;
//import com.visionvera.zong.model.ListItemBean;
//
//import java.util.List;
//
//public class ShilianwangSDKManger {
//    private static final String TAG = "ShilianwangSDKManger";
//
//    private volatile static ShilianwangSDKManger singleton;
//    boolean initShilianwang = false;
//    boolean initRequestStreamServer = false;
//
//    private ShilianwangSDKManger() {
//    }
//
//    public static ShilianwangSDKManger getInstance() {
//        if (singleton == null) {
//            synchronized (ShilianwangSDKManger.class) {
//                if (singleton == null) {
//                    singleton = new ShilianwangSDKManger();
//                }
//            }
//        }
//        return singleton;
//    }
//
//    public boolean initShilianwang(AccountBean accountBean) {
//        //北京的测试环境和广西的url是同一个。
//        VVClient.getInstance().setServer(Constant.ShilianwangSDK.serverUrl);
//
//        VVClient.getInstance().fetchStreamServerList(Constant.ShilianwangSDK.app_key,
//                Constant.ShilianwangSDK.app_secret,
//                new VVClient.Callback<List<ListItemBean>>() {
//                    @Override
//                    public void onSuccess(final List<ListItemBean> listItemBeans) {
//
////                        Toast.makeText(getActivity(), "获取流媒体列表成功", Toast.LENGTH_SHORT).show();
//                        for (int i = 0; i < listItemBeans.size(); i++) {
//                            Log.e(TAG, "onSuccess: " + new Gson().toJson(listItemBeans.get(i)));
//                        }
//                        if (listItemBeans.size() > 0) {
//                            initShilianwang = initRequestStreamServer(accountBean, listItemBeans.get(0));
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailed(int i, String s) {
////                        Toast.makeText(getActivity(), "获取流媒体列表失败", Toast.LENGTH_SHORT).show();
//                        Log.e(TAG, "onFailed: " + s);
//                        initShilianwang = false;
//                    }
//                });
//        return initShilianwang;
//    }
//
//    /**
//     * 初始化流媒体服务器
//     */
//    private boolean initRequestStreamServer(AccountBean accountBean, ListItemBean listItemBean) {
//
//        if (listItemBean != null) {
//            VVClient.getInstance().initRequest(listItemBean.id,
//                    accountBean.userId,
//                    accountBean.userName,
//                    accountBean.phoneNumber,
//                    "1",
//                    new VVClient.Callback<String>() {
//                        @Override
//                        public void onSuccess(String s) {
//                            initRequestStreamServer = true;
//
//                        }
//
//                        @Override
//                        public void onFailed(int i, String s) {
//                            initRequestStreamServer = false;
//                        }
//                    });
//        }
//        return initRequestStreamServer;
//    }
//    private void checkPermissionsAgain(Activity activity) {
//
//        RxPermissions rxPermissions = new RxPermissions(activity);
//        rxPermissions
//                .request(
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.RECORD_AUDIO)
//                .subscribe(granted -> {
//                    if (granted) {
//                     /*   if (shilianwangSDKinit) {
//                            if (TextUtils.equals("00000", tempSelectDeviceId)) {
//                                ToastUtils.showLong("视联网终端号是00000");
//                            } else {
//                                CallShilianwang();
//                            }
//                        } else {
//                            //若初始化没有结束，则不能呼叫
//                            ToastUtils.showLong("正在初始化，请稍后");
//                        }*/
//                    } else {
//                        // Oups permission denied
//                        Toast.makeText(activity, "必须授予权限才能通话", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
//
//}
