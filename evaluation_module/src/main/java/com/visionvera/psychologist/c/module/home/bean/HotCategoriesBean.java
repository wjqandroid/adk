package com.visionvera.psychologist.c.module.home.bean;

import java.io.Serializable;

/*
* 新版首页热门分类bean
* */
public class HotCategoriesBean implements Serializable {
    public String name;
    public int id;

    @Override
    public String toString() {
        return "HotCategoriesBean{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
