package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.util.List;
/**
* author:lilongfeng
* date:2020/3/16
* 描述:包含SDS,SAS,GWB,PSTR,SCL_90
*/

public class SDSResultBean {

    /**
     * result : []
     * serialNumber : RXLJKCSPFB-20200205114741233
     * code : SCL-90
     * pdpRemark : []
     * gradeRemark : null
     * resultScore : 256.0
     * startTime : 2020-02-05 11:46:11
     * dictName : 心理健康测试评分表
     * endTime : 2020-02-05 11:47:39
     * url : http://58.30.9.142:34220/upload/images/default_1574390648756.png
     * scaleResult : {"overAllDivisor":2.84,"countAndSunDivisor":{"sunProject":90,"septSunScore":2.84,"totalRemark":"亚健康心理状态","sumNum":256,"sunNum":256,"septScore":2.84,"isSun":"true"},"tenDivisor":[{"simpRemark":"躯体化","score":27,"name":"F1","standScore":2.25,"remark":"轻度"},{"simpRemark":"强迫","score":40,"name":"F2","standScore":4,"remark":"重度"},{"simpRemark":"人际关系敏感","score":22,"name":"F3","standScore":2.44,"remark":"轻度"},{"simpRemark":"抑郁","score":44,"name":"F4","standScore":3.38,"remark":"中度"},{"simpRemark":"焦虑","score":25,"name":"F5","standScore":2.5,"remark":"轻度"},{"simpRemark":"敌意","score":14,"name":"F6","standScore":2.33,"remark":"轻度"},{"simpRemark":"恐怖","score":18,"name":"F7","standScore":2.57,"remark":"轻度"},{"simpRemark":"妄想","score":18,"name":"F8","standScore":3,"remark":"中度"},{"simpRemark":"精神病性","score":26,"name":"F9","standScore":2.6,"remark":"轻度"},{"simpRemark":"其他","score":22,"name":"F10","standScore":3.14,"remark":"中度"}]}
     */
    private String gradeName;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    private String serialNumber;
    private String code;
    private String gradeRemark;
    private int resultScore;
    private String startTime;
    private String dictName;
    private String endTime;
    private String url;
    private ScaleResultBean scaleResult;
    private List<ResultBean> result;
    private List<PdpBean> pdpRemark;

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

    public int getResultScore() {
        return resultScore;
    }

    public void setResultScore(int resultScore) {
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

    public ScaleResultBean getScaleResult() {
        return scaleResult;
    }

    public void setScaleResult(ScaleResultBean scaleResult) {
        this.scaleResult = scaleResult;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public List<PdpBean> getPdpRemark() {
        return pdpRemark;
    }

    public void setPdpRemark(List<PdpBean> pdpRemark) {
        this.pdpRemark = pdpRemark;
    }

    public static class ScaleResultBean {
        /**
         * overAllDivisor : 2.84
         * countAndSunDivisor : {"sunProject":90,"septSunScore":2.84,"totalRemark":"亚健康心理状态","sumNum":256,"sunNum":256,"septScore":2.84,"isSun":"true"}
         * tenDivisor : [{"simpRemark":"躯体化","score":27,"name":"F1","standScore":2.25,"remark":"轻度"},{"simpRemark":"强迫","score":40,"name":"F2","standScore":4,"remark":"重度"},{"simpRemark":"人际关系敏感","score":22,"name":"F3","standScore":2.44,"remark":"轻度"},{"simpRemark":"抑郁","score":44,"name":"F4","standScore":3.38,"remark":"中度"},{"simpRemark":"焦虑","score":25,"name":"F5","standScore":2.5,"remark":"轻度"},{"simpRemark":"敌意","score":14,"name":"F6","standScore":2.33,"remark":"轻度"},{"simpRemark":"恐怖","score":18,"name":"F7","standScore":2.57,"remark":"轻度"},{"simpRemark":"妄想","score":18,"name":"F8","standScore":3,"remark":"中度"},{"simpRemark":"精神病性","score":26,"name":"F9","standScore":2.6,"remark":"轻度"},{"simpRemark":"其他","score":22,"name":"F10","standScore":3.14,"remark":"中度"}]
         */

        private double overAllDivisor;
        private CountAndSunDivisorBean countAndSunDivisor;
        private List<TenDivisorBean> tenDivisor;

        public double getOverAllDivisor() {
            return overAllDivisor;
        }

        public void setOverAllDivisor(double overAllDivisor) {
            this.overAllDivisor = overAllDivisor;
        }

        public CountAndSunDivisorBean getCountAndSunDivisor() {
            return countAndSunDivisor;
        }

        public void setCountAndSunDivisor(CountAndSunDivisorBean countAndSunDivisor) {
            this.countAndSunDivisor = countAndSunDivisor;
        }

        public List<TenDivisorBean> getTenDivisor() {
            return tenDivisor;
        }

        public void setTenDivisor(List<TenDivisorBean> tenDivisor) {
            this.tenDivisor = tenDivisor;
        }

        public static class CountAndSunDivisorBean {
            /**
             * sunProject : 90
             * septSunScore : 2.84
             * totalRemark : 亚健康心理状态
             * sumNum : 256
             * sunNum : 256
             * septScore : 2.84
             * isSun : true
             */

            private int sunProject;
            private double septSunScore;
            private String totalRemark;
            private int sumNum;
            private int sunNum;
            private double septScore;
            private String isSun;
            private String sunRemark;

            public String getSunRemark() {
                return sunRemark;
            }

            public void setSunRemark(String sunRemark) {
                this.sunRemark = sunRemark;
            }

            public int getSunProject() {
                return sunProject;
            }

            public void setSunProject(int sunProject) {
                this.sunProject = sunProject;
            }

            public double getSeptSunScore() {
                return septSunScore;
            }

            public void setSeptSunScore(double septSunScore) {
                this.septSunScore = septSunScore;
            }

            public String getTotalRemark() {
                return totalRemark;
            }

            public void setTotalRemark(String totalRemark) {
                this.totalRemark = totalRemark;
            }

            public int getSumNum() {
                return sumNum;
            }

            public void setSumNum(int sumNum) {
                this.sumNum = sumNum;
            }

            public int getSunNum() {
                return sunNum;
            }

            public void setSunNum(int sunNum) {
                this.sunNum = sunNum;
            }

            public double getSeptScore() {
                return septScore;
            }

            public void setSeptScore(double septScore) {
                this.septScore = septScore;
            }

            public String getIsSun() {
                return isSun;
            }

            public void setIsSun(String isSun) {
                this.isSun = isSun;
            }
        }

        public static class TenDivisorBean {
            /**
             * simpRemark : 躯体化
             * score : 27
             * name : F1
             * standScore : 2.25
             * remark : 轻度
             */

            private String simpRemark;
            private int score;
            private String name;
            private double standScore;
            private String remark;

            public String getSimpRemark() {
                return simpRemark;
            }

            public void setSimpRemark(String simpRemark) {
                this.simpRemark = simpRemark;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getStandScore() {
                return standScore;
            }

            public void setStandScore(double standScore) {
                this.standScore = standScore;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }

    public static class ResultBean {
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

    public static class PdpBean {
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
