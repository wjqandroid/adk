package com.visionvera.psychologist.c.module.ordertreatment.bean;

import java.util.List;

/**
 * @author: 刘传政
 * @date: 2019/4/18 09:38
 * QQ:1052374416
 * 作用:
 * 注意事项:
 */
public class DoctorListResponseBean {

    /**
     * code : 0
     * errMsg : ok
     * result : [{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1261,"labels":[{"lableName":"婚恋","id":6},{"lableName":"老年","id":7}],"scheduleId":0,"titleName":"主任医师","userId":0,"username":"心理医生"},{"departmentName":"脑科","hospitalName":"中心人民医院","id":1267,"labels":[],"scheduleId":0,"titleName":"主任医师","userId":1022,"username":"高医生"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1263,"labels":[],"scheduleId":0,"titleName":"副主任医师","userId":0,"username":"aa"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1265,"labels":[],"scheduleId":0,"titleName":"副主任医师","userId":0,"username":"刘医生"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":1266,"labels":[],"scheduleId":0,"titleName":"副主任医师","userId":1019,"username":"心理医生T"},{"departmentName":"新可是","hospitalName":"心理咨询室1","id":1262,"labels":[],"scheduleId":0,"titleName":"主治医师","userId":0,"username":"心理李"},{"departmentName":"心理科室222","hospitalName":"心理咨询室1","id":1264,"labels":[],"scheduleId":0,"titleName":"主治医师","userId":0,"username":"111"},{"departmentName":"脑科","hospitalName":"中心人民医院","id":1260,"labels":[],"scheduleId":0,"titleName":"住院医师","userId":0,"username":"羼"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":983,"labels":[{"lableName":"情感","id":1},{"lableName":"婚恋","id":2},{"lableName":"老年","id":3}],"scheduleId":0,"titleName":null,"userId":983,"username":"林医生"},{"departmentName":"心理科室","hospitalName":"心理医疗机构","id":984,"labels":[{"lableName":"情感","id":4},{"lableName":"婚恋","id":5}],"scheduleId":0,"titleName":null,"userId":984,"username":"孙医生"}]
     */

    private int code;
    private String errMsg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
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
        private String imageUrl;
        private List<LabelsBean> labels;

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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
}
