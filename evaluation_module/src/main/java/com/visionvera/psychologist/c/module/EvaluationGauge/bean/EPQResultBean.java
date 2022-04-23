package com.visionvera.psychologist.c.module.EvaluationGauge.bean;

import java.util.List;

public class EPQResultBean {


    /**
     * code : EPQ
     * dictName : 艾森克人格问卷
     * endTime : 2020-03-18 17:07:41
     * gradeName : E+N+P+L
     * gradeRemark : 您的人格内向，如好静，富于内省，不喜欢刺激，喜欢有秩序的生活方式，情绪比较稳定，保守，与人保持一定的距离（除非挚友）倾向与事前有计划，做事瞻前顾后，不凭一时冲动，不喜欢兴奋的事，日常生活有规律，严谨。很少有进攻行为，多少有些悲观。塌实可靠，价值观念一是非伦理做标准。+情绪稳定，倾向与情绪反映缓慢、弱，即使激起了情绪，也很快平复下来，通常是平静的，即使生点气也是有节制的，而且不紧张。+易于与人相处，较好地适应环境，态度温和，不粗暴，善从人意。+反映诚实，幼稚，不能随机应变。
     * pdpRemark : []
     * recordId : 0
     * result : [{"P":45.20000076293945,"E":41.029998779296875,"L":35.83000183105469,"N":37.97999954223633}]
     * resultGrades : null
     * resultId : 0
     * resultScore : 0
     * resultStr : {"P":45.20000076293945,"E":41.029998779296875,"L":35.83000183105469,"N":37.97999954223633}
     * scaleResult : {"P":45.20000076293945,"E":41.029998779296875,"L":35.83000183105469,"N":37.97999954223633}
     * serialNumber : RASKRGWJ-20200318170739340
     * startTime : 2020-03-18 17:07:22
     * url : http://mhsp-cdn.51vision.com//images/default_1574390675838.png
     * username : null
     */

    private String code;
    private String dictName;
    private String endTime;
    private String gradeName;
    private String gradeRemark;
    private int recordId;
    private Object resultGrades;
    private int resultId;
    private int resultScore;
    private String resultStr;
    private ScaleResultBean scaleResult;
    private String serialNumber;
    private String startTime;
    private String url;
    private Object username;
    private List<?> pdpRemark;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeRemark() {
        return gradeRemark;
    }

    public void setGradeRemark(String gradeRemark) {
        this.gradeRemark = gradeRemark;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Object getResultGrades() {
        return resultGrades;
    }

    public void setResultGrades(Object resultGrades) {
        this.resultGrades = resultGrades;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getResultScore() {
        return resultScore;
    }

    public void setResultScore(int resultScore) {
        this.resultScore = resultScore;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public ScaleResultBean getScaleResult() {
        return scaleResult;
    }

    public void setScaleResult(ScaleResultBean scaleResult) {
        this.scaleResult = scaleResult;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public List<?> getPdpRemark() {
        return pdpRemark;
    }

    public void setPdpRemark(List<?> pdpRemark) {
        this.pdpRemark = pdpRemark;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ScaleResultBean {
        /**
         * P : 45.20000076293945
         * E : 41.029998779296875
         * L : 35.83000183105469
         * N : 37.97999954223633
         */

        private double P;
        private double E;
        private double L;
        private double N;

        public double getP() {
            return P;
        }

        public void setP(double P) {
            this.P = P;
        }

        public double getE() {
            return E;
        }

        public void setE(double E) {
            this.E = E;
        }

        public double getL() {
            return L;
        }

        public void setL(double L) {
            this.L = L;
        }

        public double getN() {
            return N;
        }

        public void setN(double N) {
            this.N = N;
        }
    }

    public static class ResultBean {
        /**
         * P : 45.20000076293945
         * E : 41.029998779296875
         * L : 35.83000183105469
         * N : 37.97999954223633
         */

        private double P;
        private double E;
        private double L;
        private double N;

        public double getP() {
            return P;
        }

        public void setP(double P) {
            this.P = P;
        }

        public double getE() {
            return E;
        }

        public void setE(double E) {
            this.E = E;
        }

        public double getL() {
            return L;
        }

        public void setL(double L) {
            this.L = L;
        }

        public double getN() {
            return N;
        }

        public void setN(double N) {
            this.N = N;
        }
    }
}
