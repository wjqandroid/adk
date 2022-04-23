package com.visionvera.library.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

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

    @Generated(hash = 65423634)
    public SearchHistoryDBBean(Long id, int discoverId, String discoverValue) {
        this.id = id;
        this.discoverId = discoverId;
        this.discoverValue = discoverValue;
    }

    @Generated(hash = 1428862754)
    public SearchHistoryDBBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiscoverId() {
        return this.discoverId;
    }

    public void setDiscoverId(int discoverId) {
        this.discoverId = discoverId;
    }

    public String getDiscoverValue() {
        return this.discoverValue;
    }

    public void setDiscoverValue(String discoverValue) {
        this.discoverValue = discoverValue;
    }
}
