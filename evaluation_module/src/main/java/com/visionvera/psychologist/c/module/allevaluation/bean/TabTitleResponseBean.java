package com.visionvera.psychologist.c.module.allevaluation.bean;

import java.util.List;

/**
 * @author 刘传政
 * @date 2019-11-01 16:45
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class TabTitleResponseBean {

    /**
     * code : 0
     * errMsg : 获取数据成功
     * result : [{"createDate":null,"id":5,"isDelete":0,"keyId":"xinlijiankang","listGroup":"scale_dict_type","remark":"心理健康","value":"心理健康"},{"createDate":null,"id":6,"isDelete":0,"keyId":"zhiyenengli","listGroup":"scale_dict_type","remark":"职业能力","value":"职业能力"},{"createDate":null,"id":7,"isDelete":0,"keyId":"zhuangyeliangbiao","listGroup":"scale_dict_type","remark":"专业量表","value":"专业量表"},{"createDate":null,"id":8,"isDelete":0,"keyId":"xinggeleixing","listGroup":"scale_dict_type","remark":"性格类型","value":"性格类型"},{"createDate":null,"id":9,"isDelete":0,"keyId":"qingxuyali","listGroup":"scale_dict_type","remark":"情绪压力","value":"情绪压力"},{"createDate":null,"id":10,"isDelete":0,"keyId":"quweiceshi","listGroup":"scale_dict_type","remark":"趣味测试","value":"趣味测试"}]
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
         * id : 5
         * isDelete : 0
         * keyId : xinlijiankang
         * listGroup : scale_dict_type
         * remark : 心理健康
         * value : 心理健康
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
