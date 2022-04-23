package com.visionvera.psychologist.c.module.search.bean;

import java.util.List;

/**
 * @author 刘传政
 * @date 2019-11-04 13:58
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class DiscoverResponseBean {


    /**
     * code : 0
     * errMsg : 获取数据成功
     * result : [{"createDate":null,"id":1,"isDelete":0,"keyId":"depressed","listGroup":"scale_search_word","remark":"抑郁","value":"抑郁"},{"createDate":null,"id":2,"isDelete":0,"keyId":"anxiety","listGroup":"scale_search_word","remark":"焦虑","value":"焦虑"},{"createDate":null,"id":3,"isDelete":0,"keyId":"pressure","listGroup":"scale_search_word","remark":"压力","value":"压力"},{"createDate":null,"id":4,"isDelete":0,"keyId":"character","listGroup":"scale_search_word","remark":"人格测试","value":"人格测试"}]
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
         * createDate : null
         * id : 1
         * isDelete : 0
         * keyId : depressed
         * listGroup : scale_search_word
         * remark : 抑郁
         * value : 抑郁
         */

        private Object createDate;
        private int id;
        private int isDelete;
        private String keyId;
        private String listGroup;
        private String remark;
        private String value;

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
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

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getListGroup() {
            return listGroup;
        }

        public void setListGroup(String listGroup) {
            this.listGroup = listGroup;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
