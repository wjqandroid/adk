package com.visionvera.psychologist.c.module.knowledge_library.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author 刘传政
 * @date 2020/6/24 14:52
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class MultiArticleItemBean implements MultiItemEntity {
    //类型
    private int itemType;

    public ArticleBean dataBean;

    public MultiArticleItemBean(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
