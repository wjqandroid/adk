package com.visionvera.psychologist.c.module.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Desc 首页轮播图实体类
 *
 * @Author yemh
 * @Date 2019-11-06 11:15
 *
 */
public class BannerBean implements Serializable {

    /**
     * code : 0
     * errMsg : 获取轮播图成功
     * result : [{"id":3,"imageId":7797,"imageUrl":"http://58.30.9.142:34220/upload/images/jpg_1581668822053.png","jumpUrl":"/psychometricsapi/psychomert/getScaleDictInfo","params":"{\"scaleId\":1}","paramsObj":[{"paramsValue":1,"paramskey":"scaleId"}],"type":1},{"id":4,"imageId":7798,"imageUrl":"http://58.30.9.142:34220/upload/images/jpg_1581668852344.png","jumpUrl":"/psychometricsapi/psychomert/getScaleDictInfo","params":"{\"scaleId\":2}","paramsObj":[{"paramsValue":2,"paramskey":"scaleId"}],"type":1},{"id":5,"imageId":7335,"imageUrl":"http://58.30.9.142:34220/upload/images/kangyi1.png","jumpUrl":"/rmcp/test","params":"{\"courseId\":20}","paramsObj":[{"paramsValue":20,"paramskey":"courseId"}],"type":2}]
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

    public static class ResultBean implements Serializable{
        /**
         * id : 3
         * imageId : 7797
         * imageUrl : http://58.30.9.142:34220/upload/images/jpg_1581668822053.png
         * jumpUrl : /psychometricsapi/psychomert/getScaleDictInfo
         * params : {"scaleId":1}
         * paramsObj : [{"paramsValue":1,"paramskey":"scaleId"}]
         * type : 1
         */

        private int id;
        private int imageId;
        private String imageUrl;
        private String jumpUrl;
        private String params;
        private int type;
        private List<ParamsObjBean> paramsObj;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ParamsObjBean> getParamsObj() {
            return paramsObj;
        }

        public void setParamsObj(List<ParamsObjBean> paramsObj) {
            this.paramsObj = paramsObj;
        }

        public static class ParamsObjBean implements Serializable{
            /**
             * paramsValue : 1
             * paramskey : scaleId
             */

            private int paramsValue;
            private String paramskey;

            public int getParamsValue() {
                return paramsValue;
            }

            public void setParamsValue(int paramsValue) {
                this.paramsValue = paramsValue;
            }

            public String getParamskey() {
                return paramskey;
            }

            public void setParamskey(String paramskey) {
                this.paramskey = paramskey;
            }
        }
    }
}
