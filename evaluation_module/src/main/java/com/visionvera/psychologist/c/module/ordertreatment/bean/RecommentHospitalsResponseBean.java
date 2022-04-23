package com.visionvera.psychologist.c.module.ordertreatment.bean;

import java.util.List;

/**
 * @author 刘传政
 * @date 2020-01-03 15:56
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class RecommentHospitalsResponseBean {

    /**
     * code : 0
     * errMsg : 获取医院列表成功
     * result : [{"id":549,"name":"中心人民医院","levelName":"三级甲等","natureName":"综合医院","imageUrl":"http://www.1235.com/549.jpg"},{"id":550,"name":"心理医疗机构","levelName":"二级乙等","natureName":"专科医院","imageUrl":"http://www.1235.com/550.jpg"}]
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
         * id : 549
         * name : 中心人民医院
         * levelName : 三级甲等
         * natureName : 综合医院
         * imageUrl : http://www.1235.com/549.jpg
         */

        private int id;
        private String name;
        private String levelName;
        private String natureName;
        private String imageUrl;

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

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getNatureName() {
            return natureName;
        }

        public void setNatureName(String natureName) {
            this.natureName = natureName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
