package com.visionvera.psychologist.c.module.search.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author 刘传政
 * @date 2019-11-04 14:30
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
@Entity
public class SearchHistoryDBBean {
    @Id(autoincrement = true)
    private Long id;
    public int discoverId;
    public String discoverValue;
}
