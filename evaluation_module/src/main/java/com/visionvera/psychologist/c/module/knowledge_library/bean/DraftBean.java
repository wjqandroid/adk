package com.visionvera.psychologist.c.module.knowledge_library.bean;

/**
 * @author 刘传政
 * @date 2020/11/12 15:17
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class DraftBean {
    public int id = -1;
    public String title = "";
    public String content = "";
    public String summary = "";
    public long time;

    @Override
    public String toString() {
        return "DraftBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", time=" + time +
                '}';
    }
}
