package com.visionvera.psychologist.c.update.bean;

import java.io.Serializable;

public class UpdateBean implements Serializable {
    /*
    * 版本类型 1：iOS 2：安卓
    * */
    public String versionType;
    /*
     * 更新内容
     * */
    public String updateInfo;
    /*
     * 发布时间
     * */
    public String publishTime;
    /*
     * 是否需要更新
     * */
    public boolean needUpdate;
    /*
     * 下载地址
     * */
    public String downloadUrl;
    /*
     * 版本名
     * */
    public String versionName;
    /*
     * 是否强制更新  true：是
     * */
    public boolean mandatory;
    /*
     * 版本号
     * */
    public String versionNumber;

    @Override
    public String toString() {
        return "UpdateBean{" +
                "versionType='" + versionType + '\'' +
                ", updateInfo='" + updateInfo + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", needUpdate=" + needUpdate +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", versionName='" + versionName + '\'' +
                ", mandatory=" + mandatory +
                ", versionNumber='" + versionNumber + '\'' +
                '}';
    }
}
