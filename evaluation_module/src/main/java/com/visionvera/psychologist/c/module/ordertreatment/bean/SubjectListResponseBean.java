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
public class SubjectListResponseBean {

    /**
     * code : 0
     * errMsg : 获取医院科室列表成功
     * result : [{"id":3595,"name":"脑科"}]
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

    public static class ResultBean implements Serializable {
        /**
         * id : 3595
         * name : 脑科
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
