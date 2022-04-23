package com.visionvera.psychologist.c.module.home.bean;

import java.util.List;

/**
 * @Desc 测评类别实体类
 *
 * @Author yemh
 * @Date 2019-11-01 13:34
 */
public class EvaluationTypeBean {

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
        private String listGroup;
        private String keyId;
        private String remark;
        private int id;
        private String value;

        public String getListGroup() {
            return listGroup;
        }

        public void setListGroup(String listGroup) {
            this.listGroup = listGroup;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
