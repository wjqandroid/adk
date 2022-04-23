package com.visionvera.psychologist.c.module.counselling.bean;

/**
 * @Desc 咨询师类别实体类
 *
 * @Author yemh
 * @Date 2019-12-24 15:46
 */
public class EvaluationTypeVo {

    private String name;
    private boolean select;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
