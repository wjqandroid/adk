package com.visionvera.psychologist.c.module.usercenter.bean;

public class InforMationBean {


    /**
     * code : 0
     * errMsg : ok
     * result : {"age":29,"benasNums":null,"birthday":"1990-03-07","cardId":"110101199003075736","description":null,"email":"Li","name":"刘芳宇","nickname":"小元","photoId":0,"photoUrl":"http://58.30.9.142:34220/upload/images/headerImg_1579245290824.png","professionId":32,"professionStr":"在岗工人","sex":"男","userId":1267}
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
         * age : 29
         * benasNums : null
         * birthday : 1990-03-07
         * cardId : 110101199003075736
         * description : null
         * email : Li
         * name : 刘芳宇
         * nickname : 小元
         * photoId : 0
         * photoUrl : http://58.30.9.142:34220/upload/images/headerImg_1579245290824.png
         * professionId : 32
         * professionStr : 在岗工人
         * sex : 男
         * userId : 1267
         * "originalCode": "18143",
         * "regionCode": "18143",
         */

        private int age;
        private Object benasNums;
        private String birthday;
        private String cardId;
        private Object description;
        private String email;
        private String name;
        private String nickname;
        private int photoId;
        private String photoUrl;
        private int professionId;
        private String professionStr;
        private String sexStr;
        private int sex;//1男2女0未知
        private int userId;
        private String originalCode;//地址筛选 第五级对应的id
        private String regionCode; //地址筛选 第五级对应的id
        private String mergerName; //地址回写

        public String getMergerName() {
            return mergerName;
        }

        public void setMergerName(String mergerName) {
            this.mergerName = mergerName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Object getBenasNums() {
            return benasNums;
        }

        public void setBenasNums(Object benasNums) {
            this.benasNums = benasNums;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getPhotoId() {
            return photoId;
        }

        public void setPhotoId(int photoId) {
            this.photoId = photoId;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public int getProfessionId() {
            return professionId;
        }

        public void setProfessionId(int professionId) {
            this.professionId = professionId;
        }

        public String getProfessionStr() {
            return professionStr;
        }

        public void setProfessionStr(String professionStr) {
            this.professionStr = professionStr;
        }

        public String getSexStr() {
            return sexStr;
        }

        public void setSexStr(String sexStr) {
            this.sexStr = sexStr;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getOriginalCode() {
            return originalCode;
        }

        public void setOriginalCode(String originalCode) {
            this.originalCode = originalCode;
        }

        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }

    }
}
