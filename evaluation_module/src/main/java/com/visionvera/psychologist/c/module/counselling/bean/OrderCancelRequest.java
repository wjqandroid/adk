package com.visionvera.psychologist.c.module.counselling.bean;

/**
 * @Classname:OrderCancelRequest
 * @author:haohuizhao
 * @Date:2021/11/17 15:37
 * @Version：v1.0
 * @describe： 取消咨询/诊疗申请
 * <p>
 *counselingapi/businessApplication/cancel
 * 请求参数
 */

public class OrderCancelRequest {
    /**
     * appNum
     * String	y	订单编号
     * id	Integer	Y	主键ID
     */
    public Integer id;
    public String appNum;
    public String explain;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }
}
