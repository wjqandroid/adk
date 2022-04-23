package com.visionvera.psychologist.c.module.knowledge_library.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class EvaluateItemBean implements MultiItemEntity, Serializable {

    //类型
    public int itemType;

    public CommentListResponseBean.Children dataBean;

    public EvaluateItemBean(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
