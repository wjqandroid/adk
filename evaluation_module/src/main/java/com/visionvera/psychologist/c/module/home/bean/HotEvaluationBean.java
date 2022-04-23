package com.visionvera.psychologist.c.module.home.bean;

import java.util.List;

/**
 * @Desc 首页精选测评实体类
 *
 * @Author yemh
 * @Date 2019-11-06 11:15
 *
 */
public class HotEvaluationBean {

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
        private int pageIndex;
        private List<ScaleDictListBean> scaleDictList;

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public List<ScaleDictListBean> getScaleDictList() {
            return scaleDictList;
        }

        public void setScaleDictList(List<ScaleDictListBean> scaleDictList) {
            this.scaleDictList = scaleDictList;
        }

        public static class ScaleDictListBean {
            private String costPrice;
            private String name;
            private String description;
            private int id;
            private int heartBeans;
            private int usedNum;
            private String defaultSmallImg;
            private String defaultIconStr;

            public String getDefaultIconStr() {
                return defaultIconStr;
            }

            public void setDefaultIconStr(String defaultIconStr) {
                this.defaultIconStr = defaultIconStr;
            }

            public String getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(String costPrice) {
                this.costPrice = costPrice;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getHeartBeans() {
                return heartBeans;
            }

            public void setHeartBeans(int heartBeans) {
                this.heartBeans = heartBeans;
            }

            public int getUsedNum() {
                return usedNum;
            }

            public void setUsedNum(int usedNum) {
                this.usedNum = usedNum;
            }

            public String getDefaultSmallImg() {
                return defaultSmallImg;
            }

            public void setDefaultSmallImg(String defaultSmallImg) {
                this.defaultSmallImg = defaultSmallImg;
            }
        }
    }
}
