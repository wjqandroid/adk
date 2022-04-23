package com.visionvera.psychologist.c.module.knowledge_library.event;

import com.jeremyliao.liveeventbus.core.LiveEvent;

/**
 * @author 刘传政
 * @date 2020/6/22 13:30
 * QQ:1052374416
 * 电话:18501231486
 * 作用:文章的状态有变化,比如收藏,点赞的更新
 * 注意事项:
 */
public class ArticleStatusEvent implements LiveEvent {
    //-1默认 1 收藏 2 取消收藏 3 点赞 4取消点赞
    public int type = -1;
    public int id;//文章id
    public int currentCount;

}
