//package com.visionvera.psychologist.c.eventbus;
//
//import com.visionvera.zong.model.http.CovertBean;
//import com.visionvera.zong.model.socket.LiveListModel;
//
//import org.webrtcold.SurfaceViewRenderer;
//
///**
//* author:lilongfeng
//* date:2019/8/9
//* 描述:
// *
//*/
//public class SurfaceViewBus {
//    public SurfaceViewRenderer[] mData;
//    public CovertBean.ItemsBean mItemBean;
//    public LiveListModel model;
//    private int surfaceMode;
//    private String deviceId;
//
//    public SurfaceViewBus(SurfaceViewRenderer[] data, CovertBean.ItemsBean itemsBean, int surfaceMode, LiveListModel model,String deviceId) {
//        this.mData=data;
//        this.mItemBean=itemsBean;
//        this.surfaceMode=surfaceMode;
//        this.model=model;
//        this.deviceId=deviceId;
//    }
//
//
//    public String getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(String deviceId) {
//        this.deviceId = deviceId;
//    }
//
//    public SurfaceViewRenderer[] getmData() {
//        return mData;
//    }
//
//    public CovertBean.ItemsBean getmItemBean(){
//        return mItemBean;
//    }
//
//    public int getSurfaceMode() {
//        return surfaceMode;
//    }
//
//    public LiveListModel getModel(){
//        return model;
//    }
//}
