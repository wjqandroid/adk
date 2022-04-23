package com.visionvera.psychologist.c.utils.cos;

/**
 * @author 刘传政
 * @date 2020/3/10 17:40
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class SavePathResponseBean {

    /**
     * code : 0
     * errMsg : 文件上传成功
     * result : {"fileUrl":"http://58.30.9.142:43150/upload//images/jpg_1567392557140.jpg","fileId":7116}
     */

    public int code;
    public String errMsg;
    public ResultBean result;

    public static class ResultBean {
        /**
         * fileUrl : http://58.30.9.142:43150/upload//images/jpg_1567392557140.jpg
         * fileId : 7116
         */

        public String fileUrl;
        public int fileId;
    }

    @Override
    public String toString() {
        return "SavePathResponseBean{" +
                "code=" + code +
                ", errMsg='" + errMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
