package com.visionvera.psychologist.c.module.usercenter.bean;

import java.util.List;

/**
 * @Classname:AreaListBean
 * @author:haohuizhao
 * @Date:2021/7/29 10:05
 * @Version
 *
 * 城市集合 实体
 */
public class AreaListBean {

//接口文档 http://58.30.9.142:18082/pages/viewpage.action?pageId=15532564
//    {"code":0,"errMsg":"成功","result":[{"code":"110000000000","id":1,"level":0,"mergerName":"北京市","name":"北京市"},{"code":"120000000000","id":7362,"level":0,"mergerName":"天津市","name":"天津市"},{"code":"130000000000","id":13267,"level":0,"mergerName":"河北省","name":"河北省"},{"code":"140000000000","id":67747,"level":0,"mergerName":"山西省","name":"山西省"},{"code":"150000000000","id":99537,"level":0,"mergerName":"内蒙古自治区","name":"内蒙古自治区"},{"code":"210000000000","id":115224,"level":0,"mergerName":"辽宁省","name":"辽宁省"},{"code":"220000000000","id":133208,"level":0,"mergerName":"吉林省","name":"吉林省"},{"code":"230000000000","id":145665,"level":0,"mergerName":"黑龙江省","name":"黑龙江省"},{"code":"310000000000","id":161792,"level":0,"mergerName":"上海市","name":"上海市"},{"code":"320000000000","id":167904,"level":0,"mergerName":"江苏省","name":"江苏省"},{"code":"330000000000","id":191019,"level":0,"mergerName":"浙江省","name":"浙江省"},{"code":"340000000000","id":224649,"level":0,"mergerName":"安徽省","name":"安徽省"},{"code":"350000000000","id":244377,"level":0,"mergerName":"福建省","name":"福建省"},{"code":"360000000000","id":262698,"level":0,"mergerName":"江西省","name":"江西省"},{"code":"370000000000","id":285860,"level":0,"mergerName":"山东省","name":"山东省"},{"code":"410000000000","id":367395,"level":0,"mergerName":"河南省","name":"河南省"},{"code":"420000000000","id":420824,"level":0,"mergerName":"湖北省","name":"湖北省"},{"code":"430000000000","id":452123,"level":0,"mergerName":"湖南省","name":"湖南省"},{"code":"440000000000","id":483250,"level":0,"mergerName":"广东省","name":"广东省"},{"code":"450000000000","id":511462,"level":0,"mergerName":"广西壮族自治区","name":"广西壮族自治区"},{"code":"460000000000","id":529344,"level":0,"mergerName":"海南省","name":"海南省"},{"code":"500000000000","id":533328,"level":0,"mergerName":"重庆市","name":"重庆市"},{"code":"510000000000","id":545532,"level":0,"mergerName":"四川省","name":"四川省"},{"code":"520000000000","id":604131,"level":0,"mergerName":"贵州省","name":"贵州省"},{"code":"530000000000","id":624372,"level":0,"mergerName":"云南省","name":"云南省"},{"code":"540000000000","id":640332,"level":0,"mergerName":"西藏自治区","name":"西藏自治区"},{"code":"610000000000","id":646578,"level":0,"mergerName":"陕西省","name":"陕西省"},{"code":"620000000000","id":671119,"level":0,"mergerName":"甘肃省","name":"甘肃省"},{"code":"630000000000","id":690094,"level":0,"mergerName":"青海省","name":"青海省"},{"code":"640000000000","id":695259,"level":0,"mergerName":"宁夏回族自治区","name":"宁夏回族自治区"},{"code":"650000000000","id":698440,"level":0,"mergerName":"新疆维吾尔自治区","name":"新疆维吾尔自治区"}]}
    private int code;
    private String errMsg;
    private List<ResultBean> result;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }


    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private String id;
        private String code;
        private String name;
        private String level;
        private String  mergerName;



        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel() {
            return level;
        }
        public String getMergerName() {
            return mergerName;
        }

        public void setMergerName(String mergerName) {
            this.mergerName = mergerName;
        }

    }

}
