package com.visionvera.psychologist.account_module.beans;

import java.util.List;

/**
 * @author: 刘传政
 * @date: 2019/4/18 09:38
 * QQ:1052374416
 * 作用:登录接口类
 * 注意事项:
 */
public class AccountLoginResponseBean {

    /**
     * code : 0
     * errMsg : 登录成功
     * result : {"userId":985,"userInfoId":null,"entityId":0,"username":null,"userType":3,"name":"tiger","orgName":null,"deptName":null,"titleName":null,"orgId":0,"deptId":0,"entityType":0,"roleCode":null,"roleName":null,"mobile":"18401596647","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE3NDcxNzAsInVzZXJpZCI6OTg1LCJpYXQiOjE1NjE3MDM5NzB9.fWljWZcDTTsGNkEavQzHmiRx8WXc_L2QToydfiaA9k8","hospitalCode":null,"areaCode":null,"parentId":null,"photoUrl":null,"groupId":null,"groupLevel":null,"nickName":null,"introduction":null,"sex":null,"orgGradeId":null,"roles":[],"tbModule":null,"regionCode":"1101","photo":1}
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
         * userId : 985
         * userInfoId : null
         * entityId : 0
         * username : null
         * userType : 3
         * name : tiger
         * orgName : null
         * deptName : null
         * titleName : null
         * orgId : 0
         * deptId : 0
         * entityType : 0
         * roleCode : null
         * roleName : null
         * mobile : 18401596647
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjE3NDcxNzAsInVzZXJpZCI6OTg1LCJpYXQiOjE1NjE3MDM5NzB9.fWljWZcDTTsGNkEavQzHmiRx8WXc_L2QToydfiaA9k8
         * hospitalCode : null
         * areaCode : null
         * parentId : null
         * photoUrl : null
         * groupId : null
         * groupLevel : null
         * nickName : null
         * introduction : null
         * sex : null
         * orgGradeId : null
         * roles : []
         * tbModule : null
         * regionCode : 1101
         * photo : 1
         */

        private int userId;
        private Object userInfoId;
        private int entityId;
        private Object username;
        private int userType;
        public List<Integer> userTypeList;
        private String name;
        private Object orgName;
        private Object deptName;
        private Object titleName;
        private int orgId;
        private int deptId;
        private int entityType;
        private Object roleCode;
        private Object roleName;
        private String mobile;
        private String token;
        private Object hospitalCode;
        private Object areaCode;
        private Object parentId;
        private Object photoUrl;
        private Object groupId;
        private Object groupLevel;
        private Object nickName;
        private Object introduction;
        private Object sex;
        private Object orgGradeId;
        private Object tbModule;
        private String regionCode;
        private int photo;
        private List<?> roles;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getUserInfoId() {
            return userInfoId;
        }

        public void setUserInfoId(Object userInfoId) {
            this.userInfoId = userInfoId;
        }

        public int getEntityId() {
            return entityId;
        }

        public void setEntityId(int entityId) {
            this.entityId = entityId;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getOrgName() {
            return orgName;
        }

        public void setOrgName(Object orgName) {
            this.orgName = orgName;
        }

        public Object getDeptName() {
            return deptName;
        }

        public void setDeptName(Object deptName) {
            this.deptName = deptName;
        }

        public Object getTitleName() {
            return titleName;
        }

        public void setTitleName(Object titleName) {
            this.titleName = titleName;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public int getEntityType() {
            return entityType;
        }

        public void setEntityType(int entityType) {
            this.entityType = entityType;
        }

        public Object getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(Object roleCode) {
            this.roleCode = roleCode;
        }

        public Object getRoleName() {
            return roleName;
        }

        public void setRoleName(Object roleName) {
            this.roleName = roleName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getHospitalCode() {
            return hospitalCode;
        }

        public void setHospitalCode(Object hospitalCode) {
            this.hospitalCode = hospitalCode;
        }

        public Object getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(Object areaCode) {
            this.areaCode = areaCode;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(Object photoUrl) {
            this.photoUrl = photoUrl;
        }

        public Object getGroupId() {
            return groupId;
        }

        public void setGroupId(Object groupId) {
            this.groupId = groupId;
        }

        public Object getGroupLevel() {
            return groupLevel;
        }

        public void setGroupLevel(Object groupLevel) {
            this.groupLevel = groupLevel;
        }

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public Object getIntroduction() {
            return introduction;
        }

        public void setIntroduction(Object introduction) {
            this.introduction = introduction;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getOrgGradeId() {
            return orgGradeId;
        }

        public void setOrgGradeId(Object orgGradeId) {
            this.orgGradeId = orgGradeId;
        }

        public Object getTbModule() {
            return tbModule;
        }

        public void setTbModule(Object tbModule) {
            this.tbModule = tbModule;
        }

        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }

        public int getPhoto() {
            return photo;
        }

        public void setPhoto(int photo) {
            this.photo = photo;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }
    }
}
