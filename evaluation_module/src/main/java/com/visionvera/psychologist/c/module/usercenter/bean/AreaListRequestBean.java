package com.visionvera.psychologist.c.module.usercenter.bean;

import java.io.Serializable;

//区域请求
//实体
public class AreaListRequestBean implements Serializable {
    private String id;
    private String level;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
