package com.visionvera.psychologist.c.module.ordertreatment.contract;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.psychologist.c.module.counselling.bean.SaveMeetingRecordRequest;
import com.visionvera.psychologist.c.module.ordertreatment.activity.SearchDoctorActivity;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentAgainConsultRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentCancelRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.OrderTreatmentListRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.RecommentHospitalsResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SearchDoctorBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubjectListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubjectListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SubmitOrderResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.TagListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.TagListResponseBean;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

public interface IContract {
    /**
     * 预约诊疗首页
     */
    interface OrderTreatmentMainActivity {
        interface View extends IBaseRetrofitView {
            //获取推荐机构
            void onGetSuggestList(RecommentHospitalsResponseBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getSuggestList(RecommentHospitalsRequestBean requestBean, LifecycleProvider lifecycleProvider);
        }
    }
    interface SubjectListActivity {
        interface View extends IBaseRetrofitView {
            void onGetSubjectList(SubjectListResponseBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getSubjectList(SubjectListRequestBean requestBean,
                                LifecycleProvider lifecycleProvider);
        }
    }

    interface SubjectRecommendActivity {
        interface View extends IBaseRetrofitView {
            void onGetDoctorListFromHospital(boolean isRefresh, DoctorListResponseBean responseBean, ResultType resultType, String errorMsg);

        }

        interface Presenter {
            void getDoctorListFromHospital(boolean isRefresh, DoctorListRequestBean requestBean,
                                           LifecycleProvider lifecycleProvider);

        }
    }

    interface TypeRecommendActivity {
        interface View extends IBaseRetrofitView {
            void onGetDoctorListFromHospital(boolean isRefresh, DoctorListResponseBean responseBean, ResultType resultType, String errorMsg);

            void onGetTagList(TagListResponseBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getDoctorListFromHospital(boolean isRefresh, DoctorListRequestBean requestBean,
                                           LifecycleProvider lifecycleProvider);

            void getTagList(TagListRequestBean requestBean, LifecycleProvider lifecycleProvider);
        }
    }

    /**
     * 医生详情
     */
    interface OrderTreatmentDetailActivity {
        interface View extends IBaseRetrofitView {
            void onDoctorDetail(DoctorDetailBean detailBean, ResultType resultType, String errorMsg);

            //评价List
            void onEvaluateList(EvaluateListBean bean, ResultType resultType, String errorMsg);

        }

        interface Presenter {
            void getDoctorDetail(DoctorDetailRequest request, LifecycleProvider lifecycleProvider);

            void getUserEvaluateList(EvaluateListRequest request, LifecycleProvider lifecycleProvider);
        }
    }

    /**
     * 预约确认
     */
    interface OrderSelectTimeActivity {
        interface View extends IBaseRetrofitView {
            void onSubmit(SubmitOrderResponseBean responseBean, ResultType resultType, String errorMsg);
            void onGetInforMation(InforMationBean inforMationBean, ResultType resultType, String errorMsg);
            void onSavePath(SavePathResponseBean responseBean, ResultType resultType, String errorMsg, int position);

        }

        interface Presenter {
            void submit(SubmitOrderRequestBean requestBean,
                        LifecycleProvider lifecycleProvider);
            void getInforMation(LifecycleProvider lifecycleProvider);
            void savePath(SavePathRequestBean requestBean, LifecycleProvider lifecycleProvider, int position);

        }
    }

    /**
     * 搜索医生
     */
    interface SearchDoctor{
        interface SearchDoctorView extends IBaseRetrofitView{
            void onSearchDoctor(SearchDoctorBean bean, ResultType resultType, String errorMsg);
        }

        interface SearchDoctorBasePresenter {
            void getSearchDoctor(SearchDoctorActivity.SearchDoctorRequest request, LifecycleProvider lifecycleProvider);
        }
    }

    /**
     *获取预约诊疗列表
     */
    interface OrderTreatmentList{
        interface OrderTreatmentListView extends IBaseRetrofitView{
            void onOrderTreatmentList(boolean isRefresh, OrderTreatmentListBean bean,ResultType resultType, String errorMsg);
        }
        interface OrderTreatmentListBasePresenter{
            void getOrderTreatmentList(boolean isRefresh, OrderTreatmentListRequest request,LifecycleProvider lifecycleProvider);
        }
    }

    /**
     * 心理 预约相关-app获取预约详情接口
     */
    interface OrderTreamentListDetail{
        interface OrderTreatmentListDetailView extends IBaseRetrofitView{
            //预约诊疗-app获取预约详情接口
            void onOrderTreatmentListDetail(OrderTreatmentListDetailBean bean,ResultType resultType, String errorMsg);
            //预约诊疗--模块取消按钮
            void onOrderTreatmentCancel(BaseResponseBean2 bean,ResultType resultType, String errorMsg);

            //受邀列表-待受理-拒绝
            void onCancel(BaseResponseBean2 response, ResultType resultType, String errorMsg);
            //保存会议开启记录
            void onSaveMeetingRecord(BaseResponseBean2 response, ResultType resultType, String errorMsg);
            //受邀列表----选择完会议室后的提交
            void onUserUpdateAgainConsultInfo(BaseResponseBean2 response,ResultType resultType, String errorMsg);


        }
        interface OrderTreatmentListDetailBasePresenter{
            void getOrderTreatmentListDetail(OrderTreatmentListDetailRequest request,LifecycleProvider lifecycleProvider);

            void getOrderTreatmentCancel(OrderTreatmentCancelRequest request,LifecycleProvider lifecycleProvider);

            //受邀列表-待受理-拒绝
            void cancel(OrderTreatmentCancelRequest request, LifecycleProvider lifecycleProvider);
            //保存会议开启记录
            void getSaveMeetingRecord(SaveMeetingRecordRequest saveMeetingRecordRequest, LifecycleProvider lifecycleProvider);
            //受邀列表----选择完会议室后的提交
            void getUserUpdateAgainConsultInfo(OrderTreatmentAgainConsultRequest request, LifecycleProvider lifecycleProvider);

        }
    }

}
