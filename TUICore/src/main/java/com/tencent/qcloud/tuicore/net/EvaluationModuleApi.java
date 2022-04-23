package com.tencent.qcloud.tuicore.net;

import com.visionvera.library.base.bean.BaseResponseBean2;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Classname:EvaluationModuleApi
 * @author:haohuizhao
 * @Date:2021/12/05 15:37
 * @Version：v1.0
 * @describe： api
 * <p>
 */

public interface EvaluationModuleApi {


    /**
     * 心理二期
     * 咨询/诊疗上报操作信息接口
     * 用户发起聊天、用户接听、用户确认服务状态    用于判断按钮显示
     * 文档 http://58.30.9.142:18082/pages/viewpage.action?pageId=43221141
     * 接口counselingapi/businessApplication/command
     */
    @POST("gateway/counselingapi/businessApplication/command")
    Observable<BaseResponseBean2> reportInformation(@Body ReportInformationBeanRequest request);


    /**
     * 心理二期
     * 获取咨询/诊疗详情接口
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221204
     * counselingapi/businessApplication/getDetail
     */
    @POST("gateway/counselingapi/businessApplication/getDetail")
    Observable<OrderConsultDetailBean> getOrderConsultDetail(@Body OrderConsultDetailRequest request);

}
