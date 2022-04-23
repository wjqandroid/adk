package com.visionvera.psychologist.c.module.home.bean;

import java.io.Serializable;

/*
* 新版首页-文章推荐bean
* */
public class RecommendArticleBean implements Serializable {
    public int author;
    public String authorName;
    public String authorPhoto;
    public int collectNumber;
    public int collectStatus;
    public String content;
    // 文章首图路径
    public String coverImageUrls;
    public String createTime;
    //HTML文件地址
    public String fileUrl;
    public int id;
    public int isAuthentication;
    public String isDelete;
    public int orderBy;
    public int parentId;
    public int status;
    //文章摘要
    public String summary;
    public int thumbsUpNumber;
    public String thumbsUpStatus;
    //文章标题
    public String title;
    public String titleStr;
    public String updateTime;

    @Override
    public String toString() {
        return "RecommendArticleBean{" +
                "author=" + author +
                ", authorName='" + authorName + '\'' +
                ", authorPhoto='" + authorPhoto + '\'' +
                ", collectNumber=" + collectNumber +
                ", collectStatus=" + collectStatus +
                ", content='" + content + '\'' +
                ", coverImageUrls='" + coverImageUrls + '\'' +
                ", createTime='" + createTime + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", id=" + id +
                ", isAuthentication=" + isAuthentication +
                ", isDelete='" + isDelete + '\'' +
                ", orderBy=" + orderBy +
                ", parentId=" + parentId +
                ", status=" + status +
                ", summary='" + summary + '\'' +
                ", thumbsUpNumber=" + thumbsUpNumber +
                ", thumbsUpStatus='" + thumbsUpStatus + '\'' +
                ", title='" + title + '\'' +
                ", titleStr='" + titleStr + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
