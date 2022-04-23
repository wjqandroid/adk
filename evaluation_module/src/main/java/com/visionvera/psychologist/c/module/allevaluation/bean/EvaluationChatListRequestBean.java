package com.visionvera.psychologist.c.module.allevaluation.bean;

/**
 * @author 刘传政
 * @date 2019-11-04 09:26
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class EvaluationChatListRequestBean {
    /*
    * searchName	string
否
搜索名称

typeId	int
否
类型id

sortedType	int
否
排序类型 1欢迎度  2价格 3时间

sort	int
否
排序规则  1正序  2倒序（默认）

pageIndex
int
否
起始页，从1开始

pageSize
int
否
每页条数
    * */

    /**
     * searchName : 表
     * typeId : 0
     * sortedType : 2
     * sort : 2
     * pageIndex : 1
     * pageSize : 10
     */

    private String searchName;
    private String typeId;
    private int sortedType;
    private int sort;
    private int pageIndex;
    private int pageSize;

    public EvaluationChatListRequestBean(String searchName, String typeId, int sortedType, int sort, int pageIndex, int pageSize) {
        this.searchName = searchName;
        this.typeId = typeId;
        this.sortedType = sortedType;
        this.sort = sort;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public int getSortedType() {
        return sortedType;
    }

    public void setSortedType(int sortedType) {
        this.sortedType = sortedType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
