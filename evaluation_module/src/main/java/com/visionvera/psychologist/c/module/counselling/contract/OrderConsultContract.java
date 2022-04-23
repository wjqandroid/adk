package com.visionvera.psychologist.c.module.counselling.contract;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.ConfirmOrderDoneRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.ConsulantListResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.CounselorDetailBean;
import com.visionvera.psychologist.c.module.counselling.bean.ExpertiesResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.NewOrderConsultRequest;
import com.visionvera.psychologist.c.module.counselling.bean.NewOrderconsultBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderCancelRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultCancelRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultDetailBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultDetailRequest;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultListBean;
import com.visionvera.psychologist.c.module.counselling.bean.OrderConsultListRequest;
import com.visionvera.psychologist.c.module.counselling.bean.QueryPaysStatusRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.QueryPaysStatusResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.ReportInformationBeanRequest;
import com.visionvera.psychologist.c.module.counselling.bean.SaveMeetingRecordRequest;
import com.visionvera.psychologist.c.module.counselling.bean.SuggestListBean;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarBean;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarRequest;
import com.visionvera.psychologist.c.module.counselling.bean.UserUpdateAgainConsultInfoRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.AddEvaluateRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import okhttp3.RequestBody;

/**
 * author:lilongfeng
 * date:2019/12/25
 * 描述:咨询师预约的所有contract
 */

public interface OrderConsultContract {

    /**
     * 获取可预约时间段
     */
    interface TimeCalendar {
        interface TimeCalendarView extends IBaseRetrofitView {
            void onTimeCalendar(TimeCalendarBean bean, ResultType resultType, String errorMsg);
        }

        interface TimeCalendarBasePersenter {
            void getTimeCalendar(TimeCalendarRequest request, LifecycleProvider lifecycleProvider);
        }
    }


    /**
     * 心理咨询 预约相关-新增预约申请接口
     */
    interface NewOrderConsult {
        interface NewOrderConsultView extends IBaseRetrofitView {

        }

        interface NewOrderConsultBasePresenter {

        }
    }

    /**
     * 预约咨询、诊疗支付
     */
    interface OrderPayActivity {
        interface NewOrderConsultView extends IBaseRetrofitView {
            void onNewOrderConsult(NewOrderconsultBean response, ResultType resultType, String errorMsg);

            void onGetInforMation(InforMationBean inforMationBean, ResultType resultType, String errorMsg);

            void onSavePath(SavePathResponseBean responseBean, ResultType resultType, String errorMsg, int position);

        }

        interface NewOrderConsultBasePresenter {
            void getNewOrderConsult(NewOrderConsultRequest request, LifecycleProvider lifecycleProvider);

            void getInforMation(LifecycleProvider lifecycleProvider);

            void savePath(SavePathRequestBean requestBean, LifecycleProvider lifecycleProvider, int position);

        }
    }


    /**
     * 心理 预约相关-网格员获取预约列表
     */
    interface OrderConsultList {
        interface OrderConsultListView extends IBaseRetrofitView {
            void onOrderConsultList(boolean isRefresh, OrderConsultListBean response, ResultType resultType, String errorMsg);
        }

        interface OrderConsultListBasePresenter {
            void getOrderConsultList(boolean isRefresh, OrderConsultListRequest request, LifecycleProvider lifecycleProvider);
        }
    }

    /**
     * 心理 预约咨询、诊疗-支付成功页
     */
    interface OrderConsultSuccessActivity {
        interface View extends IBaseRetrofitView {
            void onQueryPayStatus(QueryPaysStatusResponseBean response, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void queryPayStatus(QueryPaysStatusRequestBean request, LifecycleProvider lifecycleProvider);
        }
    }


    //预约咨询、预约诊疗
    //OrderConsultDetailActivity
    interface OrderConsultDetail {

        interface OrderConsultDetailView extends IBaseRetrofitView {


            //咨询师
            void onOrderDoctorDetail(CounselorDetailBean bean, ResultType resultType, String errorMsg);

            //医生
            void onDoctorDetail(DoctorDetailBean detailBean, ResultType resultType, String errorMsg);


            //二期新
            //预约咨询、预约诊疗订单列表详情接口
            void onOrderConsultDetail(OrderConsultDetailBean bean, ResultType resultType, String errorMsg);

            //二期新
            //咨询/诊疗上报操作信息
            void onReportInformation(BaseResponseBean2 response, ResultType resultType, String errorMsg,String state);

            //二期新
            //取消咨询/诊疗申请
            void onOrderCancel(BaseResponseBean2 response, ResultType resultType, String errorMsg);

            //二期新
            //驳回咨询/诊疗申请
            void onOrderReject(BaseResponseBean2 response, ResultType resultType, String errorMsg);

            //二期新
            //受理咨询/诊疗申请
            void onOrderAccept(BaseResponseBean2 response, ResultType resultType, String errorMsg);
        }

        interface OrderConsultDetailBasePresenter {
            //咨询师
            void onOrderDoctorDetail(int id, LifecycleProvider lifecycleProvider);

            //医生
            void onDoctorDetailByUserId(DoctorDetailRequest request, LifecycleProvider lifecycleProvider);

            //二期新
            //预约咨询、预约诊疗订单列表详情接口
            void getOrderConsultDetail(OrderConsultDetailRequest request, LifecycleProvider lifecycleProvider);

            //二期新
            //咨询/诊疗 上报操作信息
            void reportInformation(ReportInformationBeanRequest request, LifecycleProvider lifecycleProvider,String state);

            //二期新
            //取消咨询/诊疗申请
            void getOrderCancel(OrderCancelRequest request, LifecycleProvider lifecycleProvider);


            //二期新
            //驳回咨询/诊疗申请
            void getOrderReject(OrderCancelRequest request, LifecycleProvider lifecycleProvider);

            //二期新
            //受理咨询/诊疗申请
            void getOrderAccept(OrderCancelRequest request, LifecycleProvider lifecycleProvider);

        }
    }

    /**
     * 预约咨询(咨询师)
     */
    interface FindConsulantancy {
        interface View extends IBaseRetrofitView {
            void onGetExpertiesList(ExpertiesResponseBean responseBean, ResultType resultType, String errorMsg);

            void onGetConsulantList(ConsulantListResponseBean responseBean, ResultType resultType, String errorMsg);

            void onSearchConsulantList(ConsulantListResponseBean responseBean, ResultType resultType, String errorMsg);

        }

        interface Presenter {
            void getExpertiesList(LifecycleProvider lifecycleProvider);

            void getConsulantList(RequestBody requestBean, LifecycleProvider lifecycleProvider);

            void getSearchConsulantList(RequestBody requestBean, LifecycleProvider lifecycleProvider);
        }
    }

    /**
     * 推荐咨询师
     */
    interface Suggest {
        interface View extends IBaseRetrofitView {
            void onGetSuggestList(SuggestListBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getSuggestList(LifecycleProvider lifecycleProvider);
        }
    }

    /**
     * 移动端获取咨询师详情
     */
    interface CounselorDetail {
        interface CounselorDetailView extends IBaseRetrofitView {
            void onCounselorDetailByPsyInfoId(CounselorDetailBean bean, ResultType resultType, String errorMsg);

            void onCounselorDetailByUserId(CounselorDetailBean bean, ResultType resultType, String errorMsg);

            //评价List
            void onEvaluateList(EvaluateListBean bean, ResultType resultType, String errorMsg);

            //add评价
            void onAddEvaluate(BaseResponseBean2 bean, ResultType resultType, String errorMsg);

            //评价详情
            void onEvaluateDetail (EvaluateDetailBean bean, ResultType resultType, String errorMsg);


        }

        interface CounselorDetailBasePresenter {
            void getCounselorDetailByPsyInfoId(int id, LifecycleProvider lifecycleProvider);

            void getCounselorDetailByUserId(int id, LifecycleProvider lifecycleProvider);

            void getUserEvaluateList(EvaluateListRequest request, LifecycleProvider lifecycleProvider);

            void getAddEvaluate(AddEvaluateRequest request, LifecycleProvider lifecycleProvider);

            void getEvaluateDetail(EvaluateListRequest request, LifecycleProvider lifecycleProvider);
        }
    }


}
