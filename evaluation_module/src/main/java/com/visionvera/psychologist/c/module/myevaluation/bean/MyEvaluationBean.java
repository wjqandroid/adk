package com.visionvera.psychologist.c.module.myevaluation.bean;

import java.util.List;

/**
 * @Desc 我的测试实体类
 *
 * @Author yemh
 * @Date 2019-11-06 11:15
 *
 */
public class MyEvaluationBean {

    private int code;
    private String errMsg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int pageIndex;
        private int pageSize;
        private int totalPageCnt;
        private int totalRecords;
        private List<DataListBean> dataList;

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

        public int getTotalPageCnt() {
            return totalPageCnt;
        }

        public void setTotalPageCnt(int totalPageCnt) {
            this.totalPageCnt = totalPageCnt;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {

            private String applicationScope;
            private String code;
            private String costPrice;
            private String createTime;
            private int createUser;
            private String createUserName;
            private int defalutSmallImgId;
            private int defaultIconId;
            private String defaultIconStr;
            private String defaultSmallImg;
            private String description;
            private int dictStatus;
            private String dictStatusStr;
            private int endLimitTime;
            private String endTime;
            private String groupName;
            private int heartBeans;
            private int hotNum;
            private int id;
            private int limitTime;
            private String name;
            private int oldStatus;
            private int pushRecordId;
            private String pushUserName;
            private int questionNums;
            private String reportTypeStr;
            private String requirement;
            private String roleCode;
            private int sameOptionalFlag;
            private String scaleAnotherName;
            private String scaleNum;
            private int scaleType;
            private String serialNumber;
            private String searchName;
            private String smallImgUrl;
            private int sort;
            private int sortedType;
            private int source;
            private String sourceStr;
            private int startLimitTime;
            private String startTime;
            private int status;
            private int typeId;
            private String typeStr;
            private String url;
            private int usedNum;
            private String userIds;
            private int scaleId;
            private int answerId;

            public int getAnswerId() {
                return answerId;
            }

            public void setAnswerId(int answerId) {
                this.answerId = answerId;
            }

            public int getScaleId() {
                return scaleId;
            }

            public void setScaleId(int scaleId) {
                this.scaleId = scaleId;
            }

            public String getApplicationScope() {
                return applicationScope;
            }

            public void setApplicationScope(String applicationScope) {
                this.applicationScope = applicationScope;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(String costPrice) {
                this.costPrice = costPrice;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getCreateUser() {
                return createUser;
            }

            public void setCreateUser(int createUser) {
                this.createUser = createUser;
            }

            public String getCreateUserName() {
                return createUserName;
            }

            public void setCreateUserName(String createUserName) {
                this.createUserName = createUserName;
            }

            public int getDefalutSmallImgId() {
                return defalutSmallImgId;
            }

            public void setDefalutSmallImgId(int defalutSmallImgId) {
                this.defalutSmallImgId = defalutSmallImgId;
            }

            public int getDefaultIconId() {
                return defaultIconId;
            }

            public void setDefaultIconId(int defaultIconId) {
                this.defaultIconId = defaultIconId;
            }

            public String getDefaultIconStr() {
                return defaultIconStr;
            }

            public void setDefaultIconStr(String defaultIconStr) {
                this.defaultIconStr = defaultIconStr;
            }

            public String getDefaultSmallImg() {
                return defaultSmallImg;
            }

            public void setDefaultSmallImg(String defaultSmallImg) {
                this.defaultSmallImg = defaultSmallImg;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getDictStatus() {
                return dictStatus;
            }

            public void setDictStatus(int dictStatus) {
                this.dictStatus = dictStatus;
            }

            public String getDictStatusStr() {
                return dictStatusStr;
            }

            public void setDictStatusStr(String dictStatusStr) {
                this.dictStatusStr = dictStatusStr;
            }

            public int getEndLimitTime() {
                return endLimitTime;
            }

            public void setEndLimitTime(int endLimitTime) {
                this.endLimitTime = endLimitTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public int getHeartBeans() {
                return heartBeans;
            }

            public void setHeartBeans(int heartBeans) {
                this.heartBeans = heartBeans;
            }

            public int getHotNum() {
                return hotNum;
            }

            public void setHotNum(int hotNum) {
                this.hotNum = hotNum;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLimitTime() {
                return limitTime;
            }

            public void setLimitTime(int limitTime) {
                this.limitTime = limitTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOldStatus() {
                return oldStatus;
            }

            public void setOldStatus(int oldStatus) {
                this.oldStatus = oldStatus;
            }

            public int getPushRecordId() {
                return pushRecordId;
            }

            public void setPushRecordId(int pushRecordId) {
                this.pushRecordId = pushRecordId;
            }

            public String getPushUserName() {
                return pushUserName;
            }

            public void setPushUserName(String pushUserName) {
                this.pushUserName = pushUserName;
            }

            public int getQuestionNums() {
                return questionNums;
            }

            public void setQuestionNums(int questionNums) {
                this.questionNums = questionNums;
            }

            public String getReportTypeStr() {
                return reportTypeStr;
            }

            public void setReportTypeStr(String reportTypeStr) {
                this.reportTypeStr = reportTypeStr;
            }

            public String getRequirement() {
                return requirement;
            }

            public void setRequirement(String requirement) {
                this.requirement = requirement;
            }

            public String getRoleCode() {
                return roleCode;
            }

            public void setRoleCode(String roleCode) {
                this.roleCode = roleCode;
            }

            public int getSameOptionalFlag() {
                return sameOptionalFlag;
            }

            public void setSameOptionalFlag(int sameOptionalFlag) {
                this.sameOptionalFlag = sameOptionalFlag;
            }

            public String getScaleAnotherName() {
                return scaleAnotherName;
            }

            public void setScaleAnotherName(String scaleAnotherName) {
                this.scaleAnotherName = scaleAnotherName;
            }

            public String getScaleNum() {
                return scaleNum;
            }

            public void setScaleNum(String scaleNum) {
                this.scaleNum = scaleNum;
            }

            public int getScaleType() {
                return scaleType;
            }

            public void setScaleType(int scaleType) {
                this.scaleType = scaleType;
            }

            public String getSerialNumber() {
                return serialNumber;
            }

            public void setSerialNumber(String serialNumber) {
                this.serialNumber = serialNumber;
            }

            public String getSearchName() {
                return searchName;
            }

            public void setSearchName(String searchName) {
                this.searchName = searchName;
            }

            public String getSmallImgUrl() {
                return smallImgUrl;
            }

            public void setSmallImgUrl(String smallImgUrl) {
                this.smallImgUrl = smallImgUrl;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getSortedType() {
                return sortedType;
            }

            public void setSortedType(int sortedType) {
                this.sortedType = sortedType;
            }

            public int getSource() {
                return source;
            }

            public void setSource(int source) {
                this.source = source;
            }

            public String getSourceStr() {
                return sourceStr;
            }

            public void setSourceStr(String sourceStr) {
                this.sourceStr = sourceStr;
            }

            public int getStartLimitTime() {
                return startLimitTime;
            }

            public void setStartLimitTime(int startLimitTime) {
                this.startLimitTime = startLimitTime;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getTypeStr() {
                return typeStr;
            }

            public void setTypeStr(String typeStr) {
                this.typeStr = typeStr;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getUsedNum() {
                return usedNum;
            }

            public void setUsedNum(int usedNum) {
                this.usedNum = usedNum;
            }

            public String getUserIds() {
                return userIds;
            }

            public void setUserIds(String userIds) {
                this.userIds = userIds;
            }
        }
    }
}
