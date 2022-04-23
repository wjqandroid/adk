package com.visionvera.psychologist.c.module.usercenter.bean;

/**
 * @Desc 意见反馈问题列表实体类
 *
 * @Author yemh
 * @Date 2019-11-06 11:15
 *
 */
public class QuestionBean {

    private String content;
    private boolean select;
    private String problem;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
