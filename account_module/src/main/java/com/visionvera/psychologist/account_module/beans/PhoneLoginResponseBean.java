package com.visionvera.psychologist.account_module.beans;

import java.util.List;

public class PhoneLoginResponseBean {


    /**
     * code : 0
     * errMsg : 登陆成功
     * result : {"userInfo":{"areaCode":null,"deptId":0,"deptName":null,"entityId":0,"entityType":0,"groupId":0,"groupLevel":0,"hospitalCode":null,"introduction":null,"mobile":"18501343024","name":"李明","nickName":null,"orgGradeId":null,"orgId":0,"orgName":null,"parentId":0,"photo":0,"photoUrl":null,"regionCode":null,"roleCode":null,"roleName":null,"roles":[],"sex":null,"tbModule":null,"titleName":null,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODQ4NDUwOTEsInVzZXJpZCI6MTM0NCwiaWF0IjoxNTgyMjUzMDkxfQ.S6moNJu_Ez1Q9y7i-MD31cHjPYXri5yOW7KWUE00z3s","userId":1344,"userInfoId":0,"userType":0,"username":null},"isRegist":false}
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
         * userInfo : {"areaCode":null,"deptId":0,"deptName":null,"entityId":0,"entityType":0,"groupId":0,"groupLevel":0,"hospitalCode":null,"introduction":null,"mobile":"18501343024","name":"李明","nickName":null,"orgGradeId":null,"orgId":0,"orgName":null,"parentId":0,"photo":0,"photoUrl":null,"regionCode":null,"roleCode":null,"roleName":null,"roles":[],"sex":null,"tbModule":null,"titleName":null,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODQ4NDUwOTEsInVzZXJpZCI6MTM0NCwiaWF0IjoxNTgyMjUzMDkxfQ.S6moNJu_Ez1Q9y7i-MD31cHjPYXri5yOW7KWUE00z3s","userId":1344,"userInfoId":0,"userType":0,"username":null}
         * isRegist : false
         */

        private UserInfoBean userInfo;
        private boolean isRegist;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public boolean isIsRegist() {
            return isRegist;
        }

        public void setIsRegist(boolean isRegist) {
            this.isRegist = isRegist;
        }

        public static class UserInfoBean {
            /**
             * areaCode : null
             * deptId : 0
             * deptName : null
             * entityId : 0
             * entityType : 0
             * groupId : 0
             * groupLevel : 0
             * hospitalCode : null
             * introduction : null
             * mobile : 18501343024
             * name : 李明
             * nickName : null
             * orgGradeId : null
             * orgId : 0
             * orgName : null
             * parentId : 0
             * photo : 0
             * photoUrl : null
             * regionCode : null
             * roleCode : null
             * roleName : null
             * roles : []
             * sex : null
             * tbModule : null
             * titleName : null
             * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODQ4NDUwOTEsInVzZXJpZCI6MTM0NCwiaWF0IjoxNTgyMjUzMDkxfQ.S6moNJu_Ez1Q9y7i-MD31cHjPYXri5yOW7KWUE00z3s
             * userId : 1344
             * userInfoId : 0
             * userType : 0
             * username : null
             */

            private Object areaCode;
            private int deptId;
            private Object deptName;
            private int entityId;
            private int entityType;
            private int groupId;
            private int groupLevel;
            private Object hospitalCode;
            private Object introduction;
            private String mobile;
            private String name;
            private Object nickName;
            private Object orgGradeId;
            private int orgId;
            private Object orgName;
            private int parentId;
            private int photo;
            private Object photoUrl;
            private Object regionCode;
            private Object roleCode;
            private Object roleName;
            private Object sex;
            private Object tbModule;
            private Object titleName;
            private String token;
            private int userId;
            private int userInfoId;
            private int userType;
            public List<Integer> userTypeList;
            private Object username;
            private List<?> roles;

            public Object getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(Object areaCode) {
                this.areaCode = areaCode;
            }

            public int getDeptId() {
                return deptId;
            }

            public void setDeptId(int deptId) {
                this.deptId = deptId;
            }

            public Object getDeptName() {
                return deptName;
            }

            public void setDeptName(Object deptName) {
                this.deptName = deptName;
            }

            public int getEntityId() {
                return entityId;
            }

            public void setEntityId(int entityId) {
                this.entityId = entityId;
            }

            public int getEntityType() {
                return entityType;
            }

            public void setEntityType(int entityType) {
                this.entityType = entityType;
            }

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }

            public int getGroupLevel() {
                return groupLevel;
            }

            public void setGroupLevel(int groupLevel) {
                this.groupLevel = groupLevel;
            }

            public Object getHospitalCode() {
                return hospitalCode;
            }

            public void setHospitalCode(Object hospitalCode) {
                this.hospitalCode = hospitalCode;
            }

            public Object getIntroduction() {
                return introduction;
            }

            public void setIntroduction(Object introduction) {
                this.introduction = introduction;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getNickName() {
                return nickName;
            }

            public void setNickName(Object nickName) {
                this.nickName = nickName;
            }

            public Object getOrgGradeId() {
                return orgGradeId;
            }

            public void setOrgGradeId(Object orgGradeId) {
                this.orgGradeId = orgGradeId;
            }

            public int getOrgId() {
                return orgId;
            }

            public void setOrgId(int orgId) {
                this.orgId = orgId;
            }

            public Object getOrgName() {
                return orgName;
            }

            public void setOrgName(Object orgName) {
                this.orgName = orgName;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getPhoto() {
                return photo;
            }

            public void setPhoto(int photo) {
                this.photo = photo;
            }

            public Object getPhotoUrl() {
                return photoUrl;
            }

            public void setPhotoUrl(Object photoUrl) {
                this.photoUrl = photoUrl;
            }

            public Object getRegionCode() {
                return regionCode;
            }

            public void setRegionCode(Object regionCode) {
                this.regionCode = regionCode;
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

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getTbModule() {
                return tbModule;
            }

            public void setTbModule(Object tbModule) {
                this.tbModule = tbModule;
            }

            public Object getTitleName() {
                return titleName;
            }

            public void setTitleName(Object titleName) {
                this.titleName = titleName;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getUserInfoId() {
                return userInfoId;
            }

            public void setUserInfoId(int userInfoId) {
                this.userInfoId = userInfoId;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public List<?> getRoles() {
                return roles;
            }

            public void setRoles(List<?> roles) {
                this.roles = roles;
            }
        }
    }
}
