package com.visionvera.psychologist.c.module.ordertreatment.bean;

import java.util.List;

/**
 * author lilongfeng
 * date 2019/8/13
 */

public class SearchDoctorBean {


    /**
     * code : 0
     * errMsg : ok
     * result : {"illnessClazzList":[{"comment":"","createtime":"0001-01-01 00:00:00","id":1,"isDelete":0,"lableName":"情感","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":2,"isDelete":0,"lableName":"婚恋","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":3,"isDelete":0,"lableName":"老年","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":9,"isDelete":0,"lableName":"抑郁","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":10,"isDelete":0,"lableName":"人格","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":11,"isDelete":0,"lableName":"分裂","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":12,"isDelete":0,"lableName":"阿尔默茨","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":13,"isDelete":0,"lableName":"双性格","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":14,"isDelete":0,"lableName":"精神分裂","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":15,"isDelete":0,"lableName":"睡眠障碍","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":16,"isDelete":0,"lableName":"惊恐","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":17,"isDelete":0,"lableName":"身份认同","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":18,"isDelete":0,"lableName":"惊恐","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":19,"isDelete":0,"lableName":"精神分裂症","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":20,"isDelete":0,"lableName":"偏执型精神病","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":21,"isDelete":0,"lableName":"分裂情感性障碍","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":22,"isDelete":0,"lableName":"双向情感障碍","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":23,"isDelete":0,"lableName":"癫痫所致精神障碍","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":24,"isDelete":0,"lableName":"精神发育迟滞(伴发精神障碍)","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":25,"isDelete":0,"lableName":"其他精神病性诊断","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":26,"isDelete":0,"lableName":"疑似精神病性诊断","updatetime":"0001-01-01 00:00:00"}],"staffInfoList":[{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1261,"labels":[{"lableName":"婚恋","id":6},{"lableName":"老年","id":7}],"scheduleId":0,"titleName":"主任医师","userId":0,"username":"心理医生"},{"departmentName":"脑科","hospitalName":"中心人民医院","id":1267,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":1022,"username":"高医生"},{"departmentName":null,"hospitalName":null,"id":1268,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":1033,"username":"医生1"},{"departmentName":null,"hospitalName":null,"id":1269,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":985,"username":"医生2"},{"departmentName":null,"hospitalName":null,"id":1270,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":986,"username":"医生3"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1271,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":0,"username":"鳗医生"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1273,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":0,"username":"李小三（医生）"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1276,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":0,"username":"李一一"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1277,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":0,"username":"李一二"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1278,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":0,"username":"张润"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1263,"labels":[],"scheduleId":0,"titleName":"副主任医师","userId":0,"username":"aa"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1265,"labels":[],"scheduleId":0,"titleName":"副主任医师","userId":0,"username":"刘医生"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1266,"labels":[],"scheduleId":0,"titleName":"副主任医师","userId":1019,"username":"心理医生T"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1274,"labels":[],"scheduleId":0,"titleName":"副主任医师","userId":0,"username":"李小四（医院管理员）"},{"departmentName":"科室1","hospitalName":"天津心理医院","id":1279,"labels":[],"scheduleId":0,"titleName":"副主任医师","userId":0,"username":"徐医生"},{"departmentName":"新可是","hospitalName":"心理咨询室1","id":1262,"labels":[],"scheduleId":0,"titleName":"主治医师","userId":0,"username":"心理李"},{"departmentName":"心理科室222","hospitalName":"心理咨询室1","id":1264,"labels":[],"scheduleId":0,"titleName":"主治医师","userId":0,"username":"111"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1275,"labels":[],"scheduleId":0,"titleName":"主治医师","userId":0,"username":"李小五（医生）"},{"departmentName":"脑科","hospitalName":"中心人民医院","id":1260,"labels":[],"scheduleId":0,"titleName":"住院医师","userId":0,"username":"羼"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":983,"labels":[{"lableName":"情感","id":1},{"lableName":"婚恋","id":2},{"lableName":"老年","id":3}],"scheduleId":0,"titleName":null,"userId":983,"username":"林医生"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":984,"labels":[{"lableName":"情感","id":4},{"lableName":"婚恋","id":5}],"scheduleId":0,"titleName":null,"userId":984,"username":"孙医生"},{"departmentName":null,"hospitalName":null,"id":1258,"labels":[],"scheduleId":0,"titleName":null,"userId":3,"username":"李医生"}],"hospitalInfoList":[{"hospitalId":0,"hospitalLevel":1,"hospitalName":"中心人民医院","hospitalNature":1,"hospitalNatureName":"综合医院","id":549,"illnessOrHospitalName":null,"lablesId":0,"levelName":"三特","name":null,"staffId":0,"staffIds":null},{"hospitalId":0,"hospitalLevel":1,"hospitalName":"心理医疗机构1","hospitalNature":1,"hospitalNatureName":"综合医院","id":550,"illnessOrHospitalName":null,"lablesId":0,"levelName":"三特","name":null,"staffId":0,"staffIds":null},{"hospitalId":0,"hospitalLevel":1,"hospitalName":"心理医疗机构","hospitalNature":1,"hospitalNatureName":"综合医院","id":551,"illnessOrHospitalName":null,"lablesId":0,"levelName":"三特","name":null,"staffId":0,"staffIds":null},{"hospitalId":0,"hospitalLevel":1,"hospitalName":"1111","hospitalNature":1,"hospitalNatureName":"综合医院","id":552,"illnessOrHospitalName":null,"lablesId":0,"levelName":"三特","name":null,"staffId":0,"staffIds":null},{"hospitalId":0,"hospitalLevel":1,"hospitalName":"心理咨询室1","hospitalNature":1,"hospitalNatureName":"综合医院","id":553,"illnessOrHospitalName":null,"lablesId":0,"levelName":"三特","name":null,"staffId":0,"staffIds":null},{"hospitalId":0,"hospitalLevel":1,"hospitalName":"中心1","hospitalNature":1,"hospitalNatureName":"综合医院","id":554,"illnessOrHospitalName":null,"lablesId":0,"levelName":"三特","name":null,"staffId":0,"staffIds":null},{"hospitalId":0,"hospitalLevel":1,"hospitalName":"心理","hospitalNature":1,"hospitalNatureName":"综合医院","id":555,"illnessOrHospitalName":null,"lablesId":0,"levelName":"三特","name":null,"staffId":0,"staffIds":null},{"hospitalId":0,"hospitalLevel":1,"hospitalName":"天津心理医院","hospitalNature":1,"hospitalNatureName":"综合医院","id":556,"illnessOrHospitalName":null,"lablesId":0,"levelName":"三特","name":null,"staffId":0,"staffIds":null}]}
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
        private List<IllnessClazzListBean> illnessClazzList;
        private List<StaffInfoListBean> staffInfoList;
        private List<HospitalInfoListBean> hospitalInfoList;

        public List<IllnessClazzListBean> getIllnessClazzList() {
            return illnessClazzList;
        }

        public void setIllnessClazzList(List<IllnessClazzListBean> illnessClazzList) {
            this.illnessClazzList = illnessClazzList;
        }

        public List<StaffInfoListBean> getStaffInfoList() {
            return staffInfoList;
        }

        public void setStaffInfoList(List<StaffInfoListBean> staffInfoList) {
            this.staffInfoList = staffInfoList;
        }

        public List<HospitalInfoListBean> getHospitalInfoList() {
            return hospitalInfoList;
        }

        public void setHospitalInfoList(List<HospitalInfoListBean> hospitalInfoList) {
            this.hospitalInfoList = hospitalInfoList;
        }

        public static class IllnessClazzListBean {
            /**
             * comment :
             * createtime : 0001-01-01 00:00:00
             * id : 1
             * isDelete : 0
             * lableName : 情感
             * updatetime : 0001-01-01 00:00:00
             */

            private String comment;
            private String createtime;
            private int id;
            private int isDelete;
            private String lableName;
            private String updatetime;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public String getLableName() {
                return lableName;
            }

            public void setLableName(String lableName) {
                this.lableName = lableName;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }

        public static class StaffInfoListBean {
            /**
             * departmentName : 心理科室
             * hospitalName : 心理医疗机构
             * id : 1261
             * labels : [{"lableName":"婚恋","id":6},{"lableName":"老年","id":7}]
             * scheduleId : 0
             * titleName : 主任医师
             * userId : 0
             * username : 心理医生
             */

            private String departmentName;
            private String hospitalName;
            private int id;
            private int scheduleId;
            private String titleName;
            private int userId;
            private String username;
            private List<LabelsBean> labels;
            private String imageUrl;
            public int patientNum;
            public String dictName;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getHospitalName() {
                return hospitalName;
            }

            public void setHospitalName(String hospitalName) {
                this.hospitalName = hospitalName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getScheduleId() {
                return scheduleId;
            }

            public void setScheduleId(int scheduleId) {
                this.scheduleId = scheduleId;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public List<LabelsBean> getLabels() {
                return labels;
            }

            public void setLabels(List<LabelsBean> labels) {
                this.labels = labels;
            }

            public static class LabelsBean {
                /**
                 * lableName : 婚恋
                 * id : 6
                 */

                private String lableName;
                private int id;

                public String getLableName() {
                    return lableName;
                }

                public void setLableName(String lableName) {
                    this.lableName = lableName;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }

        public static class HospitalInfoListBean {
            /**
             * hospitalId : 0
             * hospitalLevel : 1
             * hospitalName : 中心人民医院
             * hospitalNature : 1
             * hospitalNatureName : 综合医院
             * id : 549
             * illnessOrHospitalName : null
             * lablesId : 0
             * levelName : 三特
             * name : null
             * staffId : 0
             * staffIds : null
             */

            private int hospitalId;
            private int hospitalLevel;
            private String hospitalName;
            private int hospitalNature;
            private String hospitalNatureName;
            private int id;
            private Object illnessOrHospitalName;
            private int lablesId;
            private String levelName;
            private Object name;
            private int staffId;
            private Object staffIds;
            private String imageUrl;
            public String dictName;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getHospitalId() {
                return hospitalId;
            }

            public void setHospitalId(int hospitalId) {
                this.hospitalId = hospitalId;
            }

            public int getHospitalLevel() {
                return hospitalLevel;
            }

            public void setHospitalLevel(int hospitalLevel) {
                this.hospitalLevel = hospitalLevel;
            }

            public String getHospitalName() {
                return hospitalName;
            }

            public void setHospitalName(String hospitalName) {
                this.hospitalName = hospitalName;
            }

            public int getHospitalNature() {
                return hospitalNature;
            }

            public void setHospitalNature(int hospitalNature) {
                this.hospitalNature = hospitalNature;
            }

            public String getHospitalNatureName() {
                return hospitalNatureName;
            }

            public void setHospitalNatureName(String hospitalNatureName) {
                this.hospitalNatureName = hospitalNatureName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getIllnessOrHospitalName() {
                return illnessOrHospitalName;
            }

            public void setIllnessOrHospitalName(Object illnessOrHospitalName) {
                this.illnessOrHospitalName = illnessOrHospitalName;
            }

            public int getLablesId() {
                return lablesId;
            }

            public void setLablesId(int lablesId) {
                this.lablesId = lablesId;
            }

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public int getStaffId() {
                return staffId;
            }

            public void setStaffId(int staffId) {
                this.staffId = staffId;
            }

            public Object getStaffIds() {
                return staffIds;
            }

            public void setStaffIds(Object staffIds) {
                this.staffIds = staffIds;
            }
        }
    }
}
