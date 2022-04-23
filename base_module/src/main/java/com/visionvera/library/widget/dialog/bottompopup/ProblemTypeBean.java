package com.visionvera.library.widget.dialog.bottompopup;

import android.widget.Button;

import java.io.Serializable;

/**
 * @Classname:ProblemTypeBean
 * @author:haohuizhao
 * @Date:2021/10/29 11:06
 * @Version：v1.0
 * @describe：  问题类型
 */
public class ProblemTypeBean   implements Serializable {

    public Boolean is_select;
    public String name;
    public int imageId;

    public String getName() {
        return name;
    }


    public ProblemTypeBean(Boolean is_select, String name, int imageId) {
        this.is_select = is_select;
        this.name = name;
        this.imageId = imageId;
    }


    public Boolean getIs_select() {
        return is_select;
    }

    public void setIs_select(Boolean is_select) {
        this.is_select = is_select;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
