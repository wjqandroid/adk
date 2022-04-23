package com.visionvera.psychologist.c.module.home.bean;

import java.io.Serializable;

/**
 * @Classname:DataBean
 * @author:haohuizhao
 * @Date:2021/9/26 11:09
 * @Version：v1.0
 * @describe： 本地Banner对象、    政策汇编对象
 */
public class LocalCustomBean implements Serializable {


    public String url;
    public String title;
    private String ImageUrl;
    public Integer imageRes;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Integer getImageRes() {
        return imageRes;
    }

    public void setImageRes(Integer imageRes) {
        this.imageRes = imageRes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int viewType;


    public LocalCustomBean(String title, String url, int viewType, Integer imageRes) {
        this.title = title;
        this.url = url;
        this.viewType = viewType;
        this.imageRes = imageRes;
    }
//    public DataBean(Integer imageRes, String title, int viewType) {
//        this.imageRes = imageRes;
//        this.title = title;
//        this.viewType = viewType;
//    }

}
