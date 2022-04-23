package com.visionvera.psychologist.c.module.counselling.bean;

import java.io.Serializable;

/**
 * @Classname:AvatarBean
 * @author:haohuizhao
 * @Date:2021/10/13 14:24
 * @Version：v1.0
 * @describe： 更换头像
 */
public class AvatarBean implements Serializable {
    public String name;
    public int id;
    public int ic_launcher;
    public boolean is_selected;

    public AvatarBean(String name, int id, int ic_launcher, boolean is_selected) {
        this.name = name;
        this.id = id;
        this.ic_launcher = ic_launcher;
        this.is_selected = is_selected;
    }

    public boolean isIs_selected() {
        return is_selected;
    }

    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIc_launcher() {
        return ic_launcher;
    }

    public void setIc_launcher(int ic_launcher) {
        this.ic_launcher = ic_launcher;
    }



}
