package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.util.List;

public class MBTIResultBean {


    /**
     * result : [{"val":11,"name":"J（判断）"},{"val":12,"name":"N（直觉）"},{"val":12,"name":"S（感觉）"},{"val":12,"name":"T（思考）"},{"val":11,"name":"P（感知）"},{"val":10,"name":"F（情感）"},{"val":13,"name":"I（内向）"},{"val":8,"name":"E（外向）"}]
     * gradeName : INTP
     * serialNumber : RZYXGCS-20200316134623293
     * code : MBTI
     * pdpRemark : []
     * gradeRemark : 1.安静、自持、弹性及具适应力。
     2.特别喜爱追求理论与科学事理。
     3.习于以逻辑及分析来解决问题—问题解决者。
     4.最有兴趣于创意事务及特定工作，对聚会与闲聊无　大兴趣。
     5.追求可发挥个人强烈兴趣的生涯。
     6.追求发展对有兴趣事务之逻辑解释。
     * resultScore : 0.0
     * startTime : 2020-03-16 11:23:42
     * dictName : 职业性格测试
     * endTime : 2020-03-16 11:24:07
     * url : https://slyl-mhsp-1301295327.cos.ap-beijing.myqcloud.com//images/default_1574390648756.png
     * scaleResult : [{"val":11,"name":"J（判断）"},{"val":12,"name":"N（直觉）"},{"val":12,"name":"S（感觉）"},{"val":12,"name":"T（思考）"},{"val":11,"name":"P（感知）"},{"val":10,"name":"F（情感）"},{"val":13,"name":"I（内向）"},{"val":8,"name":"E（外向）"}]
     */

    private String gradeName;
    private String serialNumber;
    private String code;
    private String gradeRemark;
    private double resultScore;
    private String startTime;
    private String dictName;
    private String endTime;
    private String url;
    private List<ResultBean> result;
    private List<?> pdpRemark;
    private List<ScaleResultBean> scaleResult;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGradeRemark() {
        return gradeRemark;
    }

    public void setGradeRemark(String gradeRemark) {
        this.gradeRemark = gradeRemark;
    }

    public double getResultScore() {
        return resultScore;
    }

    public void setResultScore(double resultScore) {
        this.resultScore = resultScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public List<?> getPdpRemark() {
        return pdpRemark;
    }

    public void setPdpRemark(List<?> pdpRemark) {
        this.pdpRemark = pdpRemark;
    }

    public List<ScaleResultBean> getScaleResult() {
        return scaleResult;
    }

    public void setScaleResult(List<ScaleResultBean> scaleResult) {
        this.scaleResult = scaleResult;
    }

    public static class ResultBean {
        /**
         * val : 11
         * name : J（判断）
         */

        private int val;
        private String name;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ScaleResultBean {
        /**
         * val : 11
         * name : J（判断）
         */

        private int val;
        private String name;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
