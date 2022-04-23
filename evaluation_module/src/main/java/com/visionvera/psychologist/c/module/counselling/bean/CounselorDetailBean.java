package com.visionvera.psychologist.c.module.counselling.bean;

import java.io.Serializable;
import java.util.List;

public class CounselorDetailBean implements Serializable {
    /**
     * result : {"hospitalNature":"综合医院","lableIds":"18,10,9","titleId":42,"consultingFee":0,"hospitalCode":"510403000000","photoUrl":"http://mhsp-cdn.51vision.com////upload/images/show/1596527424864_佘永军.jpg","consultingFeeVoiceStatus":0,"email":"","isExpert":"否","consultingFeeStatus":0,"createtime":1596525040000,"lableName":["惊恐","人格","抑郁"],"psyInfoId":12,"introduce":"造成每个人心理上的困惑和我们自身的某些习惯有关、而任何习惯都是学习的结果，树立什么样的新的行为之前，要弄清楚自己此时需要什么、能做什么!这需要我们了解自身拥有什么样的能力、拥有什么样的潜能\u2026\u2026","sex":1,"mobile":"18502153698","photo":8422,"consultingFeeVideoStatus":0,"consultingFeeVoice":"-0.01","hospitalName":"攀枝花市西区政府服务中心","credentialList":[],"userId":9290,"consultingFeeVideo":"-0.01","titleName":"国家二级心理咨询师","drawProportion":-1,"cardId":"510402197608055197","username":"刘青玄"}
     * code : 0
     * errMsg : 查询成功!
     */

    private ResultDTO result;
    private int code;
    private String errMsg;

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

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

    public static class ResultDTO implements Serializable {
        /**
         * hospitalNature : 综合医院
         * lableIds : 18,10,9
         * titleId : 42
         * consultingFee : 0
         * hospitalCode : 510403000000
         * photoUrl : http://mhsp-cdn.51vision.com////upload/images/show/1596527424864_佘永军.jpg
         * consultingFeeVoiceStatus : 0
         * email :
         * isExpert : 否
         * consultingFeeStatus : 0
         * createtime : 1596525040000
         * lableName : ["惊恐","人格","抑郁"]
         * psyInfoId : 12
         * introduce : 造成每个人心理上的困惑和我们自身的某些习惯有关、而任何习惯都是学习的结果，树立什么样的新的行为之前，要弄清楚自己此时需要什么、能做什么!这需要我们了解自身拥有什么样的能力、拥有什么样的潜能……
         * sex : 1
         * mobile : 18502153698
         * photo : 8422
         * consultingFeeVideoStatus : 0
         * consultingFeeVoice : -0.01
         * hospitalName : 攀枝花市西区政府服务中心
         * credentialList : []
         * userId : 9290
         * consultingFeeVideo : -0.01
         * titleName : 国家二级心理咨询师
         * drawProportion : -1
         * cardId : 510402197608055197
         * username : 刘青玄
         */

        private String hospitalNature;
        private String lableIds;
        private int titleId;
        private String consultingFee;
        private String hospitalCode;
        private String photoUrl;
        private int consultingFeeVoiceStatus;
        private String email;
        private String isExpert;
        private int consultingFeeStatus;
        private long createtime;
        private int psyInfoId;
        private String introduce;
        private int sex;
        private String mobile;
        private int photo;
        private int consultingFeeVideoStatus;
        private String consultingFeeVoice;
        private String hospitalName;
        private int userId;
        private String consultingFeeVideo;
        private String titleName;
        private int drawProportion;
        private String cardId;
        private String username;
        private List<String> lableName;
        private List<?> credentialList;


//        "consultingfeeStatus": 0, //0关闭 1开始 图文
//        "consultingfeevideoStatus": 0,//0关闭 1开始 视频
//        "consultingfeevoiceStatus": 0,//0关闭 1开始 语音


        public String getHospitalNature() {
            return hospitalNature;
        }

        public void setHospitalNature(String hospitalNature) {
            this.hospitalNature = hospitalNature;
        }

        public String getLableIds() {
            return lableIds;
        }

        public void setLableIds(String lableIds) {
            this.lableIds = lableIds;
        }

        public int getTitleId() {
            return titleId;
        }

        public void setTitleId(int titleId) {
            this.titleId = titleId;
        }

        public String getConsultingFee() {
            return consultingFee;
        }

        public void setConsultingFee(String consultingFee) {
            this.consultingFee = consultingFee;
        }

        public String getHospitalCode() {
            return hospitalCode;
        }

        public void setHospitalCode(String hospitalCode) {
            this.hospitalCode = hospitalCode;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public int getConsultingFeeVoiceStatus() {
            return consultingFeeVoiceStatus;
        }

        public void setConsultingFeeVoiceStatus(int consultingFeeVoiceStatus) {
            this.consultingFeeVoiceStatus = consultingFeeVoiceStatus;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIsExpert() {
            return isExpert;
        }

        public void setIsExpert(String isExpert) {
            this.isExpert = isExpert;
        }

        public int getConsultingFeeStatus() {
            return consultingFeeStatus;
        }

        public void setConsultingFeeStatus(int consultingFeeStatus) {
            this.consultingFeeStatus = consultingFeeStatus;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public int getPsyInfoId() {
            return psyInfoId;
        }

        public void setPsyInfoId(int psyInfoId) {
            this.psyInfoId = psyInfoId;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getPhoto() {
            return photo;
        }

        public void setPhoto(int photo) {
            this.photo = photo;
        }

        public int getConsultingFeeVideoStatus() {
            return consultingFeeVideoStatus;
        }

        public void setConsultingFeeVideoStatus(int consultingFeeVideoStatus) {
            this.consultingFeeVideoStatus = consultingFeeVideoStatus;
        }

        public String getConsultingFeeVoice() {
            return consultingFeeVoice;
        }

        public void setConsultingFeeVoice(String consultingFeeVoice) {
            this.consultingFeeVoice = consultingFeeVoice;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getConsultingFeeVideo() {
            return consultingFeeVideo;
        }

        public void setConsultingFeeVideo(String consultingFeeVideo) {
            this.consultingFeeVideo = consultingFeeVideo;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public int getDrawProportion() {
            return drawProportion;
        }

        public void setDrawProportion(int drawProportion) {
            this.drawProportion = drawProportion;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getLableName() {
            return lableName;
        }

        public void setLableName(List<String> lableName) {
            this.lableName = lableName;
        }

        public List<?> getCredentialList() {
            return credentialList;
        }

        public void setCredentialList(List<?> credentialList) {
            this.credentialList = credentialList;
        }
    }

}
