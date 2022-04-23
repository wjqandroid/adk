package com.visionvera.library.base.bean;

/**
 * @author 刘传政
 * @date 2020/6/23 09:30
 * QQ:1052374416
 * 电话:18501231486
 * 作用:默认的页数相关数值的类
 * 注意事项:
 */
public class PageBean {
    //后台是1开始的.所以这里定义当前页是0,表示第一页都没请求
    public int defaltPage = 0;
    public int serverFirstPage = 1;
    public int currentPage = defaltPage;//当前页
    public int pageSize = 20;
}
