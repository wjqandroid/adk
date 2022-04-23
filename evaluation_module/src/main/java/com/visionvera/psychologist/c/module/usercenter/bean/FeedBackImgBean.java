package com.visionvera.psychologist.c.module.usercenter.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class FeedBackImgBean implements MultiItemEntity, Serializable {

    public static final int IMG = 1;//上传的图片
    public static final int CAMERA = 2;//拍照条目
    //类型
    private int itemType;
    //本地图片路径
    private String picPath;
    //服务器接口换回的图片对应id
    private int id;
    //服务器换回的图片url
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FeedBackImgBean(int itemType, String picPath) {
        this.itemType = itemType;
        this.picPath = picPath;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
