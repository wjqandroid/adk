package com.visionvera.psychologist.c.module.ordertreatment.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 刘传政
 * @date: 2019/4/18 09:38
 * QQ:1052374416
 * 作用:
 * 注意事项:
 */
public class DoctorResponseBean implements Serializable {

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
