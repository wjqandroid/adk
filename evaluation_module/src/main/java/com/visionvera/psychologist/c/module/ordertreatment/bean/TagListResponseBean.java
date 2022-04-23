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
public class TagListResponseBean {

    /**
     * code : 0
     * errMsg : ok
     * result : [{"comment":"","createtime":"0001-01-01 00:00:00","id":1,"isDelete":0,"lableName":"情感","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":2,"isDelete":0,"lableName":"婚恋","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":3,"isDelete":0,"lableName":"老年","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":9,"isDelete":0,"lableName":"抑郁","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":10,"isDelete":0,"lableName":"人格","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":11,"isDelete":0,"lableName":"分裂","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":12,"isDelete":0,"lableName":"阿尔默茨","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":13,"isDelete":0,"lableName":"双性格","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":14,"isDelete":0,"lableName":"精神分裂","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":15,"isDelete":0,"lableName":"睡眠障碍","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":16,"isDelete":0,"lableName":"惊恐","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":17,"isDelete":0,"lableName":"身份认同","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":18,"isDelete":0,"lableName":"惊恐","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":19,"isDelete":0,"lableName":"精神分裂症","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":20,"isDelete":0,"lableName":"偏执型精神病","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":21,"isDelete":0,"lableName":"分裂情感性障碍","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":22,"isDelete":0,"lableName":"双向情感障碍","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":23,"isDelete":0,"lableName":"癫痫所致精神障碍","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":24,"isDelete":0,"lableName":"精神发育迟滞(伴发精神障碍)","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":25,"isDelete":0,"lableName":"其他精神病性诊断","updatetime":"0001-01-01 00:00:00"},{"comment":"","createtime":"0001-01-01 00:00:00","id":26,"isDelete":0,"lableName":"疑似精神病性诊断","updatetime":"0001-01-01 00:00:00"}]
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
         * comment :
         * createtime : 0001-01-01 00:00:00
         * id : 1
         * isDelete : 0
         * lableName : 情感
         * updatetime : 0001-01-01 00:00:00
         */

        private String comment;
        private String createtime;
        private int id;
        private int isDelete;
        private String lableName;
        private String updatetime;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
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

        public String getLableName() {
            return lableName;
        }

        public void setLableName(String lableName) {
            this.lableName = lableName;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
