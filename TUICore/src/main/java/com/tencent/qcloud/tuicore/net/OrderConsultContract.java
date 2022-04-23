package com.tencent.qcloud.tuicore.net;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;

/**
 * @Classname:OrderConsultContract
 * @author:haohuizhao
 * @Date:2021/12/05 15:37
 * @Version：v1.0
 * @describe：  咨询/诊疗上报操作信息
 * <p>
 */

public interface OrderConsultContract {


    interface OrderConsultDetail {
        interface OrderConsultDetailView extends IBaseRetrofitView {
            //二期新
            //咨询/诊疗上报操作信息
            void onReportInformation(BaseResponseBean2 response, ResultType resultType, String errorMsg);
            //心理咨询 预约相关-app获取预约详情接口
            void onOrderConsultDetail(OrderConsultDetailBean bean, ResultType resultType, String errorMsg);
        }

        interface OrderConsultDetailBasePresenter {
            //二期新
            //咨询/诊疗 上报操作信息
            void reportInformation(ReportInformationBeanRequest request, LifecycleProvider lifecycleProvider);

            //心理咨询 预约相关-app获取预约详情接口
            void getOrderConsultDetail(OrderConsultDetailRequest request, LifecycleProvider lifecycleProvider);

        }
    }
}
