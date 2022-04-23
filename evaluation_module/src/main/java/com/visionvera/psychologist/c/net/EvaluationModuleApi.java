package com.visionvera.psychologist.c.net;

import com.visionvera.library.base.bean.BaseResponseBean;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.bean.BaseResponseBean4;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.CommitRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.DoAssessResponseBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.EvaluationResultRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionOptionResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDIctInfoResponse;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleDictInfoRequest;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.ScaleQuestionOptionResponse;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListResponseBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.AliPrePayResponseBean;
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
import com.visionvera.psychologist.c.module.counselling.bean.WeixinPrePayResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.XindouPayRequestBean;
import com.visionvera.psychologist.c.module.home.bean.BannerBean;
import com.visionvera.psychologist.c.module.home.bean.EvaluationTypeBean;
import com.visionvera.psychologist.c.module.home.bean.EveryDaySignInBean;
import com.visionvera.psychologist.c.module.home.bean.HotEvaluationBean;
import com.visionvera.psychologist.c.module.home.bean.LiveBannerBean;
import com.visionvera.psychologist.c.module.home.bean.MeetingRoomBean;
import com.visionvera.psychologist.c.module.home.bean.RecommendArticleBean;
import com.visionvera.psychologist.c.module.home.bean.RecommendCourseBean;
import com.visionvera.psychologist.c.module.home.bean.SelectedEvaluationBean;
import com.visionvera.psychologist.c.module.home.bean.SignInBean;
import com.visionvera.psychologist.c.module.home.bean.UserSigBean;
import com.visionvera.psychologist.c.module.home.bean.UserSigRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddArticleRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddCommentRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddCommentResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleLikeOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleListRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.ArticleListResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CollectOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentLikeOrNotRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentListRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentListResponseBean;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.module.myevaluation.bean.ReTestRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.activity.SearchDoctorActivity;
import com.visionvera.psychologist.c.module.ordertreatment.bean.AddEvaluateRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListRequestBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorListResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorResponseBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateDetailBean;
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
import com.visionvera.psychologist.c.module.search.bean.DiscoverRequestBean;
import com.visionvera.psychologist.c.module.search.bean.DiscoverResponseBean;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListBean;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListRequestBean;
import com.visionvera.psychologist.c.module.usercenter.bean.ChangePassRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.ForgetPasswordCheckNumberRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.ForgetPasswordCommitRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobRequest;
import com.visionvera.psychologist.c.module.usercenter.bean.MessageBean;
import com.visionvera.psychologist.c.module.usercenter.bean.UserBasicInfoBean;
import com.visionvera.psychologist.c.update.bean.UpdateBean;
import com.visionvera.psychologist.c.utils.cos.MiYaoRequestBean;
import com.visionvera.psychologist.c.utils.cos.MiYaoResponseBean;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface EvaluationModuleApi {

    /**
     * 获取量表详情
     */
    @POST("gateway/psychometricsapi/psychomert/getScaleDictInfo")
    Observable<ScaleDIctInfoResponse> getScaleDictInfo(@Body ScaleDictInfoRequest request);

    /**
     * 心豆支付
     */
    @POST("gateway/payapi/payHeartBeans/scaleDictPayByHeartBeans")
    Observable<BaseResponseBean3> xindouPay(@Body XindouPayRequestBean request);

    /**
     * 获取量表问题及选项
     */
    @POST("gateway/psychometricsapi/psychomert/getScaleQuestionAndOption")
    Observable<BaseResponseBean3<QuestionOptionResponse>> getScaleQuestionOption(@Body ScaleDictInfoRequest request);

    /**
     * 获取量表类型
     */
    @POST("gateway/commonapi/systemDict/getSystemDictList")
    Observable<TabTitleResponseBean> getTabTitles(@Body TabTitleRequestBean request);

    /**
     * 获取量表列表
     */
    @POST("gateway/psychometricsapi/psychomert/getScaleDictList")
    Observable<EvaluationChatListResponseBean> getEvaluationChatList(@Body EvaluationChatListRequestBean request);

    /**
     * 搜索--获取发现词
     */
    @POST("gateway/commonapi/systemDict/getSystemDictList")
    Observable<DiscoverResponseBean> getDiscover(@Body DiscoverRequestBean request);

    /**
     * 提交测评结果
     */
//    @POST("gateway/mqapi/psychomert/doAssess")
    @POST("gateway/psychometricsapi/psychomert/doAssess")
    Observable<BaseResponseBean3<DoAssessResponseBean>> getDoAssess(@Body CommitRequest request);

    /**
     * 获取首页轮播图
     */
    @POST("gateway/commonapi/common/getSystemBananer")
    Observable<BannerBean> getSystemBananer(@Body RequestBody body);

    /**
     * 获取首页测评类型
     */
    @POST("gateway/commonapi/systemDict/getSystemDictList")
    Observable<EvaluationTypeBean> getEvaluationType(@Body RequestBody body);

    /**
     * 获取首页精选测评
     */
    @POST("gateway/psychometricsapi/psychomert/getHotSelectScaleDictList")
    Observable<BaseResponseBean3<SelectedEvaluationBean>> getHotEvaluation(@Body RequestBody body);

    /**
     * 获取首页精选测评
     */
    @POST("gateway/psychometricsapi/psychomert/getHotSelectScaleDictList")
    Observable<HotEvaluationBean> getHotEvaluation2(@Body RequestBody body);

    /**
     * 获取首页我的收藏
     */
    @POST("gateway/psychometricsapi/psychomert/getUserScaleDictList")
    Observable<MyEvaluationBean> getCollectEvaluation(@Body RequestBody body);

    /**
     * 获取我的测试
     */
    @POST("gateway/psychometricsapi/psychomert/getUserScaleDictList")
    Observable<MyEvaluationBean> getMyEvaluation(@Body RequestBody body);

    /**
     * c端用户申请过期再测
     */
    @POST("gateway/psychometricsapi/scalePushRecord/applyStaleAgainTest")
    Observable<BaseResponseBean2> reTest(@Body ReTestRequestBean requestBean);

    /**
     * 游客登录
     */
    @POST("gateway/authapi/visitorLogin")
    Observable<BaseResponseBean2> getVisitorLogin();

    /**
     * 获取测试结果
     */
    @POST("gateway/psychometricsapi/psychomert/getScaleResult")
    Observable<EvaluationResultBean> getEvaluationResult(@Body EvaluationResultRequest body);

    /**
     * 获取消息中心列表
     */
    @POST("gateway/psychometricsapi/psychomert/getUserScaleDictList")
    Observable<MessageBean> getMessage(@Body RequestBody body);


    /**
     * 收藏量表
     */
    @POST("gateway/psychometricsapi/psychomert/collectScaleDict")
    Observable<BaseResponseBean2> getCollectScaleDict(@Body ScaleDictInfoRequest request);

    /**
     * 取消收藏量表
     */
    @POST("gateway/psychometricsapi/psychomert/cancleCollectScaleDict")
    Observable<BaseResponseBean2> cancleCollectScaleDict(@Body ScaleDictInfoRequest request);

    /**
     * 修改密码
     */
    @POST("gateway/backsysapi/user/updatePassword")
    Observable<BaseResponseBean2> changePassword(@Body ChangePassRequest request);

    /**
     * 忘记密码的发送验证码
     */
    @POST("gateway/authapi/getSmsCode")
    Observable<BaseResponseBean2> forgetPasswordCheckNumber(@Body ForgetPasswordCheckNumberRequest request);

    /**
     * 忘记密码的提交
     */
    @POST("gateway/authapi/forgetPassword")
    Observable<BaseResponseBean2> forgetPasswordCommit(@Body ForgetPasswordCommitRequest request);

    /**
     * 意见反馈
     */
    @POST("gateway/commonapi/appFeeback/saveAppFeeback")
    Observable<BaseResponseBean2> getFeedBack(@Body RequestBody body);

    /**
     * 获取个人心豆及昵称信息
     */
    @POST("gateway/commonapi/userInfo/getUserBasicInfo")
    Observable<UserBasicInfoBean> getUserBasicInfo();

    /**
     * 完善信息前获取信息
     */
    @GET("gateway/commonapi/userInfo/getInforMation")
    Observable<InforMationBean> getInforMation();

    /**
     * 完善信息保存
     */
    @POST("gateway/commonapi/userInfo/saveInforMation")
    Observable<BaseResponseBean2> saveInforMation(@Body RequestBody requestBody);

    /**
     * 获取职业列表
     */
    @POST("gateway/commonapi/systemDict/getSystemDictList")
    Observable<JobBean> getJobList(@Body JobRequest jobRequest);

    /**
     * 获取医生可用日程
     */
    @POST("gateway/doctorapi/schedule/getAllFreeSubscribe")
    Observable<TimeCalendarBean> getTimeCalendar(@Body TimeCalendarRequest requestBean);

    /**
     * 预约咨询师(擅长领域)
     *
     * @return
     */
    @POST("gateway/counselingapi/appConsultation/getExpertFieldList")
    Observable<ExpertiesResponseBean> getExpertiesList();

    /**
     * 心理咨询 预约相关-支付宝预下单
     */
    @POST("vmhpay/alipay/app/paymentSend")
    Observable<AliPrePayResponseBean> aliPrePay(@QueryMap Map<String, String> map);

    /**
     * 心理咨询 预约相关-微信预下单
     */
    @POST("vmhpay/weChatPay/app/paymentSend")
    Observable<WeixinPrePayResponseBean> weixinPrePay(@QueryMap Map<String, String> map);


    /**
     * 心理咨询 预约相关-获取预约列表
     */
    @POST("gateway/payapi/payCommon/queryPayStatus")
    Observable<QueryPaysStatusResponseBean> queryPayStatus(@Body QueryPaysStatusRequestBean request);


    /**
     * 预约咨询师列表
     *
     * @return
     */
    @POST("gateway/counselingapi/appConsultation/getConsultantList")
    Observable<ConsulantListResponseBean> getConsulantList(@Body RequestBody requestBody);

    /**
     * 推荐咨询师列表
     *
     * @return
     */
    @GET("gateway/counselingapi/appConsultation/getRecommendConsultantList")
    Observable<SuggestListBean> getSguuestList();


    /**
     * 移动端获取咨询师详情
     */
    @GET("gateway/counselingapi/appConsultation/getConsultantDetail/{id}")
    Observable<CounselorDetailBean> getOrderConsultDetail(@Path("id") int id);

    /**
     * 移动端获取咨询师详情通过userId
     */
    @GET("gateway/counselingapi/appConsultation/getConsultantDetailByUserId/{userId}")
    Observable<CounselorDetailBean> getOrderConsultDetailByUserId(@Path("userId") int userId);


    /**
     * 废弃
     * 预约咨询模块取消按钮
     */
    @POST("gateway/counselingapi/consult/cancelConsultInfo")
    Observable<BaseResponseBean2> getOrderConsultCancel(@Body OrderConsultCancelRequest requestBean);

    /**
     * 保存会议开启记录
     */
    @POST("gateway/meetingapi/meeting/saveMeetingRecord")
    Observable<BaseResponseBean2> saveMeetingRecord(@Body SaveMeetingRecordRequest request);

    /**
     * 心理咨询 预约相关-用户编辑会议室信息接口
     * 请求地址	请求方式	说明
     */
    @POST("gateway/counselingapi/consult/userUpdateAgainConsultInfo")
    Observable<BaseResponseBean2> getUserUpdateAgainConsultInfo(@Body UserUpdateAgainConsultInfoRequest request);

    /**
     * 心理咨询 预约相关-用户咨询完后点击确认完成
     * 请求地址	请求方式	说明
     */
    @POST("gateway/counselingapi/consult/accomplishConsultInfo")
    Observable<BaseResponseBean2> confirmOrderDone(@Body ConfirmOrderDoneRequestBean request);


    /**
     * 预约诊疗----搜索医生   模糊搜索擅长类别列表、医院列表、医生列表
     */
    @POST("gateway/commonapi/hospitalInfo/getIllnessAndHospitalList")
    Observable<SearchDoctorBean> searchDoctor(@Body SearchDoctorActivity.SearchDoctorRequest request);

    /**
     * 预约诊疗模块-获取科室列表
     *
     * @return
     */
    @POST("gateway/commonapi/hospitalInfo/getHospitalDepartmentList")
    Observable<SubjectListResponseBean> getSubjectList(@Body SubjectListRequestBean requestBean);

    /**
     * 预约诊疗模块-获取推荐机构
     */
    @POST("gateway/commonapi/hospitalInfo/getRecommendHospitalList")
    Observable<RecommentHospitalsResponseBean> getRecommendHospital(@Body RecommentHospitalsRequestBean request);

    /**
     * 预约诊疗模块-根据疾病或科室推荐医生列表
     *
     * @return
     */
    @POST("gateway/commonapi/hospitalInfo/getDoctorList")
    Observable<DoctorListResponseBean> getDoctorList(@Body DoctorListRequestBean requestBean);

    /**
     * 预约诊疗模块-tag列表
     *
     * @return
     */
    @POST("gateway/commonapi/labels/getLablesInfoList")
    Observable<TagListResponseBean> getTagList(@Body TagListRequestBean requestBean);

    /**
     * 医生详情
     */
    @POST("gateway/commonapi/hospitalInfo/getDoctorDetail")
    Observable<DoctorDetailBean> getDoctorDetail(@Body DoctorDetailRequest requestBean);

    /**
     * 确认提交预约
     */
    @POST("gateway/mentalhealthapi/subscribeInfoApplication/insertSubscribelApplicationInfo")
    Observable<SubmitOrderResponseBean> orderSubmit(@Body SubmitOrderRequestBean requestBean);

    /**
     * 已废弃
     * 获取预约诊疗列表
     * counselingapi/businessApplication/pagedList
     */
    @POST("gateway/counselingapi/businessApplication/pagedList")
    Observable<OrderTreatmentListBean> getOrderTreatmentList(@Body OrderTreatmentListRequest request);


    /**
     * 已废弃
     * 心理 预约相关-app获取预约详情接口
     */
    @POST("gateway/mentalhealthapi/subscribeInfoApplication/appGetSubscribeInfoDetail")
    Observable<OrderTreatmentListDetailBean> getOrderTreatmentListDetail(@Body OrderTreatmentListDetailRequest request);

    /**
     * 预约诊疗模块取消按钮
     *
     * @return
     */
    @POST("gateway/mentalhealthapi/subscribeInfoApplication/auditSubscribeInfo")
    Observable<BaseResponseBean2> getOrderTreatmentCancel(@Body OrderTreatmentCancelRequest requestBean);

    /**
     * 预约诊疗----受邀列表待受理状态的选会议室确认受理
     */
    @POST("gateway/mentalhealthapi/subscribeInfoApplication/patientUpdateAgainSubscribeInfo")
    Observable<BaseResponseBean2> getTreatmentAgainConsultInfo(@Body OrderTreatmentAgainConsultRequest request);

    /**
     * 查询打卡签到
     */
    @POST("gateway/psychometricsapi/appSignIn/getSignInByWeek")
    Observable<EveryDaySignInBean> getSignInByWeek();

    /**
     * 提交打卡
     */
    @POST("gateway/psychometricsapi/appSignIn/saveSignIn")
    Observable<SignInBean> getSaveSignIn();

    /**
     * 首页获取心理援助的上级会议室信息
     */
    @POST("gateway/mentalhealthapi/heartHelp/getHeartHelpMeetingRoomInfo")
    Observable<MeetingRoomBean> getHeartHelpMeetingRoomInfo();

//    /**废弃
//     * 获取腾讯IM登录的userSig
//     */
//    @POST("gateway/authapi/tencentChat/getUserSig")
//    Observable<UserSigBean> getUserSig(@Body UserSigRequestBean userSigRequestBean);

    /**
     * 退出登录
     */
    @POST("gateway/authapi/loginOut")
    Observable<BaseResponseBean2> getLoginOut();

    /**
     * 获取直播的轮播图数据
     */
    @GET("rmcp/appCourse/getBanners")
    Observable<LiveBannerBean> getLiveBanner();

    /**
     * 获取cos临时秘钥
     */
    @POST("gateway/commonapi/tencentCos/getCredential")
    Observable<MiYaoResponseBean> getCosMiyao(@Body MiYaoRequestBean requestBean);

    /**
     * cos上传文件后的保存信息到自己服务器的接口
     */
    @POST("gateway/commonapi/uploadFile/savePath")
    Observable<SavePathResponseBean> savePath(@Body SavePathRequestBean requestBean);

    /**
     * 心理知识圈--文章列表&详情接口
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=32178502
     */
    @POST("gateway/psychometricsapi/artical/articalList")
    Observable<BaseResponseBean3<ArticleListResponseBean>> getArticles(@Body ArticleListRequestBean requestBean);

    /**
     * 心理知识圈--文章新增接口（发布文章）
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=32178511
     */
    @POST("gateway/psychometricsapi/artical/addArtical")
    Observable<BaseResponseBean3<Object>> addArticle(@Body AddArticleRequestBean requestBean);

    /**
     * 收藏&取消收藏接口
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=32178497
     */
    @POST("gateway/psychometricsapi/articalCollect/collectOrCancleArtical")
    Observable<BaseResponseBean3<Object>> collectOrNot(@Body CollectOrNotRequestBean requestBean);

    /**
     * 点赞&取消点赞接口
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=32178500
     */
    @POST("gateway/psychometricsapi/articalThumbsUp/thumbsUpOrCancleArtical")
    Observable<BaseResponseBean3<Object>> articleLikeOrNot(@Body ArticleLikeOrNotRequestBean requestBean);

    /**
     * 心理知识圈--评论点赞&取消点赞接口
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=32178654
     */
    @POST("gateway/psychometricsapi/articalThumbsUp/thumbsUpOrCancleArticalcComment")
    Observable<BaseResponseBean3<Object>> commentLikeOrNot(@Body CommentLikeOrNotRequestBean requestBean);

    /**
     * 心理知识圈--评论列表
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=32178515
     */
    @POST("gateway/psychometricsapi/articalComment/selectArticalCommentList")
    Observable<BaseResponseBean3<CommentListResponseBean>> getCommentList(@Body CommentListRequestBean requestBean);

    /**
     * 心理知识圈--文章评论新增接口
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=32178509
     */
    @POST("gateway/psychometricsapi/articalComment/addArticalComment")
    Observable<BaseResponseBean3<AddCommentResponseBean>> addComment(@Body AddCommentRequestBean requestBean);

    /**
     * 新版首页-推荐文章
     */
    @POST("gateway/psychometricsapi/artical/recommendArticalList")
    Observable<BaseResponseBean3<List<RecommendArticleBean>>> recommendArticalList();

    /**
     * 新版首页-精彩课程
     */
    @POST("rmcp/appCourse/getRecommendCourse")
    Observable<BaseResponseBean3<List<RecommendCourseBean>>> getRecommendCourse();

    /**
     * 新版首页-获取推荐医生列表
     */
    @POST("gateway/commonapi/hospitalInfo/getRecommendStaffList")
    Observable<BaseResponseBean3<List<DoctorResponseBean>>> getRecommendStaffList();


    /**
     * 新版个人中心  获取省、市地区
     *
     * @return /commonapi/common/getAreaDictTree
     */
    @POST("gateway/commonapi/common/getAreaDictTree")
    Observable<AreaListBean> getAreaList(@Body AreaListRequestBean requestBean);

    /**
     * 检测更新
     */
    @POST("gateway/commonapi/appVersion/checkUpdate")
    Observable<BaseResponseBean<UpdateBean>> checkUpdate(@Body Map map);

    /**
     * 未登录时获取问题与答案
     */
    @POST("gateway/psychometricsapi/psychomert/getQuestionAndOption")
    Observable<BaseResponseBean3<QuestionOptionResponse>> getQuestionAndOption(@Body Map map);

    /**
     * 心理二期
     * 预约咨询、预约诊疗支付提交订单
     * 接口文档地址    http://58.30.9.142:18082/pages/viewpage.action?pageId=43221124
     * 添加咨询/诊疗申请     /counselingapi/businessApplication/add
     */
    @POST("gateway/counselingapi/businessApplication/add")
    Observable<NewOrderconsultBean> getNewOrderConsult(@Body NewOrderConsultRequest request);


    /**
     * 心理二期
     * 预约咨询、预约诊疗订单列表
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221128
     * 获取咨询/诊疗预约列表  /counselingapi/businessApplication/pagedList
     */
    @POST("gateway/counselingapi/businessApplication/pagedList")
    Observable<OrderConsultListBean> getOrderConsultList(@Body OrderConsultListRequest request);


    /**
     * 心理二期
     * 预约咨询、预约诊疗订单列表详情
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221204
     * 获取咨询/诊疗详情  /counselingapi/businessApplication/getDetail
     */
    @POST("gateway/counselingapi/businessApplication/getDetail")
    Observable<OrderConsultDetailBean> getOrderConsultDetail(@Body OrderConsultDetailRequest request);

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
     * 取消咨询/诊疗申请
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221136
     * counselingapi/businessApplication/cancel
     */
    @POST("gateway/counselingapi/businessApplication/cancel")
    Observable<BaseResponseBean2> getOrderCancel(@Body OrderCancelRequest requestBean);

    /**
     * 心理二期
     * 受理咨询/诊疗申请
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221143
     * counselingapi/businessApplication/accept
     */
    @POST("gateway/counselingapi/businessApplication/accept")
    Observable<BaseResponseBean2> getOrderAccept(@Body OrderCancelRequest requestBean);


    /**
     * 心理二期
     * 驳回咨询/诊疗申请
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221139
     * counselingapi/businessApplication/reject
     */
    @POST("gateway/counselingapi/businessApplication/reject")
    Observable<BaseResponseBean2> getOrderReject(@Body OrderCancelRequest requestBean);


    /**
     * 心理二期
     * 查询咨询师详情
     * http://58.30.9.142:34220/gateway/counselingapi/appConsultation/getConsultantDetail/62
     */
    @GET("gateway/counselingapi/appConsultation/getConsultantDetailByUserId/{userId}")
    Observable<CounselorDetailBean> getOrderDoctorDetail(@Path("userId") int userId);


    /**
     * 心理二期
     * 查询医生详情
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221270
     */
    @POST("gateway/commonapi/hospitalInfo/getDoctorDetailByUserId")
    Observable<DoctorDetailBean> getDoctorDetailByUserId(@Body DoctorDetailRequest requestBean);


    /**
     * 心理二期
     * 获取咨询师评价列表
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221092
     */
    @POST("gateway/counselingapi/evaluate/userEvaluateList")
    Observable<EvaluateListBean> getUserEvaluateList(@Body EvaluateListRequest requestBean);

    /**
     * 心理二期
     * app新增评价
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221100
     */
    @POST("gateway/counselingapi/evaluate/addEvaluate")
    Observable<BaseResponseBean2> getAddEvaluate(@Body AddEvaluateRequest requestBean);


    /**
     * 心理二期
     * 获取评价详情
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221102
     */
    @POST("gateway/counselingapi/evaluate/evaluateDetail")
    Observable<EvaluateDetailBean> getEvaluateDetail(@Body EvaluateListRequest requestBean);


    /**
     * 心理二期
     * 获取腾讯IM登录的userSig
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221342
     * 由于公司项目多环境，为了避免腾讯Im互踢机制，在呼叫userid 加前缀
     */
    @POST("gateway/authapi/tencentIm/getSign")
    Observable<UserSigBean> getUserSig(@Body UserSigRequestBean userSigRequestBean);



    /**
     * 心理二期
     * 查询打卡签到连续天数
     * http://58.30.9.142:18082/pages/viewpage.action?pageId=43221456
     */
    @POST("gateway/psychometricsapi/appSignIn/getSignDays")
    Observable<BaseResponseBean4> getSignDays();


}
