package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.util.List;

public class ScaleDIctInfoResponse {


    /**
     * code : 0
     * errMsg : 获取详情成功
     * result : {"applicationScope":"学生","buttonList":null,"code":"SDS","costPrice":"1.00","createUser":0,"createUserName":null,"defalutSmallImgId":0,"defaultIconId":0,"defaultIconStr":null,"defaultSmallImg":"0","description":"SDS是由仲克（Zung）于1965年编制，是用于心理咨询、抑郁症状筛查及严重程度评定和精神药理学研究的量表之一。其特点是使用简便，并能相当直观地反映抑郁患者的主观感受。主要适用于具有抑郁症状的成年人，包括门诊及住院患者。只是对严重迟缓症状的抑郁，评定有困难。同时，SDS对于文化程度较低或智力水平稍差的人使用效果不佳。","dictStatus":0,"dictStatusStr":null,"endTime":null,"heartBeans":0,"hotNum":1,"id":1,"limitTime":10,"name":"抑郁自评量表","oldStatus":0,"pushUserName":null,"questionNums":20,"reportTypeStr":"","requirement":"(1)表格由受试者自行填写,作出独立的、不受任何人影响的自我评定。(2)评定的时间范围，应强调是\u201c现在或过去一周\u201d。(3)一次评定，一般在十分钟内填完。","roleCode":null,"sameOptionalFlag":1,"scaleNum":null,"scaleType":0,"searchName":null,"smallImgUrl":"http://58.30.9.142:34220/uploadnull","sort":0,"sortedType":0,"source":0,"sourceStr":null,"startTime":null,"status":1,"typeId":0,"typeList":[],"typeStr":null,"types":null,"url":null,"usedNum":0,"userIds":null}
     */

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
        /**
         * applicationScope : 学生
         * buttonList : null
         * code : SDS
         * costPrice : 1.00
         * createUser : 0
         * createUserName : null
         * defalutSmallImgId : 0
         * defaultIconId : 0
         * defaultIconStr : null
         * defaultSmallImg : 0
         * description : SDS是由仲克（Zung）于1965年编制，是用于心理咨询、抑郁症状筛查及严重程度评定和精神药理学研究的量表之一。其特点是使用简便，并能相当直观地反映抑郁患者的主观感受。主要适用于具有抑郁症状的成年人，包括门诊及住院患者。只是对严重迟缓症状的抑郁，评定有困难。同时，SDS对于文化程度较低或智力水平稍差的人使用效果不佳。
         * dictStatus : 0
         * dictStatusStr : null
         * endTime : null
         * heartBeans : 0
         * hotNum : 1
         * id : 1
         * limitTime : 10
         * name : 抑郁自评量表
         * oldStatus : 0
         * pushUserName : null
         * questionNums : 20
         * reportTypeStr :
         * requirement : (1)表格由受试者自行填写,作出独立的、不受任何人影响的自我评定。(2)评定的时间范围，应强调是“现在或过去一周”。(3)一次评定，一般在十分钟内填完。
         * roleCode : null
         * sameOptionalFlag : 1
         * scaleNum : null
         * scaleType : 0
         * searchName : null
         * smallImgUrl : http://58.30.9.142:34220/uploadnull
         * sort : 0
         * sortedType : 0
         * source : 0
         * sourceStr : null
         * startTime : null
         * status : 1
         * typeId : 0
         * typeList : []
         * typeStr : null
         * types : null
         * url : null
         * usedNum : 0
         * userIds : null
         */

        private String applicationScope;
        private Object buttonList;
        private String code;
        private String costPrice;
        private int createUser;
        private Object createUserName;
        private int defalutSmallImgId;
        private int defaultIconId;
        private Object defaultIconStr;
        private String defaultSmallImg;
        private String description;
        private int dictStatus;
        private Object dictStatusStr;
        private Object endTime;
        private int heartBeans;
        private int hotNum;
        private int id;
        private int limitTime;
        private String name;
        private int oldStatus;
        private Object pushUserName;
        private int questionNums;
        private String reportTypeStr;
        private String requirement;
        private Object roleCode;
        private int sameOptionalFlag;
        private Object scaleNum;
        private int scaleType;
        //1是收藏0是未收藏
        private int collectStatus;
        private Object searchName;
        private String smallImgUrl;
        private int sort;
        private int sortedType;
        private int source;
        private Object sourceStr;
        private Object startTime;
        private int status;
        private int typeId;
        private Object typeStr;
        private Object types;
        private Object url;
        private int usedNum;
        private Object userIds;
        private List<?> typeList;
        private String groupName;
        /**
         * 奖励心豆数量，系统派发量表时显示
         */
        public int awardBeansNum;
        /**
         * 用户心豆数量，用户和答题心豆数量比较，如果用户心豆数量小于答题心豆数量，前端需要将心豆选择那块置灰，不可选
         */
        public int userHeartBeans;
        /**
         * 量表价格，需要付款金额
         */
        public String costPriceStr;
        /**
         * 订单编号
         */
        public String orderNum;
        /**
         * 是否支持心豆支付 1支持 0不支持，不支持，前端需要将心豆选择那块置灰，不可选
         */
        public int isPayHeartBeans;
        /**
         * 用来判断是否需要支付，
         * payStatus=19时表示之前支付过但是没答完题就退出了，此时不需要在支付
         */
        public String payStatus;
        /**
         * 是否需要完善信息 1:需要完善 0:不需要
         */
        public String infoStatus;
        /**
         * 游客模式下测评次数
         */
        public int exerciseNum;

        public int getCollectStatus() {
            return collectStatus;
        }

        public void setCollectStatus(int collectStatus) {
            this.collectStatus = collectStatus;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getApplicationScope() {
            return applicationScope;
        }

        public void setApplicationScope(String applicationScope) {
            this.applicationScope = applicationScope;
        }

        public Object getButtonList() {
            return buttonList;
        }

        public void setButtonList(Object buttonList) {
            this.buttonList = buttonList;
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

        public int getCreateUser() {
            return createUser;
        }

        public void setCreateUser(int createUser) {
            this.createUser = createUser;
        }

        public Object getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(Object createUserName) {
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

        public Object getDefaultIconStr() {
            return defaultIconStr;
        }

        public void setDefaultIconStr(Object defaultIconStr) {
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

        public Object getDictStatusStr() {
            return dictStatusStr;
        }

        public void setDictStatusStr(Object dictStatusStr) {
            this.dictStatusStr = dictStatusStr;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
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

        public Object getPushUserName() {
            return pushUserName;
        }

        public void setPushUserName(Object pushUserName) {
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

        public Object getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(Object roleCode) {
            this.roleCode = roleCode;
        }

        public int getSameOptionalFlag() {
            return sameOptionalFlag;
        }

        public void setSameOptionalFlag(int sameOptionalFlag) {
            this.sameOptionalFlag = sameOptionalFlag;
        }

        public Object getScaleNum() {
            return scaleNum;
        }

        public void setScaleNum(Object scaleNum) {
            this.scaleNum = scaleNum;
        }

        public int getScaleType() {
            return scaleType;
        }

        public void setScaleType(int scaleType) {
            this.scaleType = scaleType;
        }

        public Object getSearchName() {
            return searchName;
        }

        public void setSearchName(Object searchName) {
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

        public Object getSourceStr() {
            return sourceStr;
        }

        public void setSourceStr(Object sourceStr) {
            this.sourceStr = sourceStr;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
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

        public Object getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(Object typeStr) {
            this.typeStr = typeStr;
        }

        public Object getTypes() {
            return types;
        }

        public void setTypes(Object types) {
            this.types = types;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public int getUsedNum() {
            return usedNum;
        }

        public void setUsedNum(int usedNum) {
            this.usedNum = usedNum;
        }

        public Object getUserIds() {
            return userIds;
        }

        public void setUserIds(Object userIds) {
            this.userIds = userIds;
        }

        public List<?> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<?> typeList) {
            this.typeList = typeList;
        }
    }
}
