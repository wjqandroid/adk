/*
package com.visionvera.library.base.mvp.model;

import android.app.Activity;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.visionvera.psychologist.base.bean.BaseResponseBean;
import com.visionvera.psychologist.base.bean.BaseResponseBean2;
import com.visionvera.psychologist.module.alarm.activity.InquiryReportStatusDetailActivity;
import com.visionvera.psychologist.module.alarm.bean.AlarmListRequestBean;
import com.visionvera.psychologist.module.alarm.bean.AlarmListResponseBean;
import com.visionvera.psychologist.module.alarm.bean.AlarmUpterInfoBean;
import com.visionvera.psychologist.module.alarm.bean.AreaDictTreeBean;
import com.visionvera.psychologist.module.alarm.bean.AreaDictTreeRequest;
import com.visionvera.psychologist.module.alarm.bean.IllnessClazzInfoListBean;
import com.visionvera.psychologist.module.alarm.bean.IllnessClazzInfoListRequest;
import com.visionvera.psychologist.module.alarm.bean.InquiryReportDetailBean;
import com.visionvera.psychologist.module.alarm.bean.NewAlarmBean;
import com.visionvera.psychologist.module.alarm.bean.NewAlarmRequest;
import com.visionvera.psychologist.module.alarm.bean.SaveMeetingRecordRequest;
import com.visionvera.psychologist.module.casecontrol.activity.TransferDeathActivity;
import com.visionvera.psychologist.module.casecontrol.bean.AddPatientRequestBean2;
import com.visionvera.psychologist.module.casecontrol.bean.FollowListRequestBean;
import com.visionvera.psychologist.module.casecontrol.bean.FollowListResponseBean;
import com.visionvera.psychologist.module.casecontrol.bean.LeaveHospitalListRequestBean;
import com.visionvera.psychologist.module.casecontrol.bean.LeaveHospitalListResponseBean;
import com.visionvera.psychologist.module.casecontrol.bean.MoveRecordListRequestBean;
import com.visionvera.psychologist.module.casecontrol.bean.MoveRecordListResponseBean;
import com.visionvera.psychologist.module.casecontrol.bean.NotManageToManageRequestBean;
import com.visionvera.psychologist.module.casecontrol.bean.PatientDetailRequestBean;
import com.visionvera.psychologist.module.casecontrol.bean.PatientDetailResponseBean;
import com.visionvera.psychologist.module.casecontrol.bean.PatientLIstRequestBean;
import com.visionvera.psychologist.module.casecontrol.bean.PatientListResponseBean;
import com.visionvera.psychologist.module.casecontrol.bean.PatientStatusStatisticsBean;
import com.visionvera.psychologist.module.casecontrol.bean.PreSelectsRequestBean;
import com.visionvera.psychologist.module.casecontrol.bean.PreSelectsResponseBean;
import com.visionvera.psychologist.module.followmanager.activity.FollowDetailActivity;
import com.visionvera.psychologist.module.followmanager.beans.FollowDetailBean;
import com.visionvera.psychologist.module.followmanager.beans.FollowDetailRequest;
import com.visionvera.psychologist.module.followmanager.beans.FollowListRequest;
import com.visionvera.psychologist.module.followmanager.beans.FollowNewRecordOptionBean;
import com.visionvera.psychologist.module.followmanager.beans.FollowNewRecordOptionRequest;
import com.visionvera.psychologist.module.followmanager.beans.FollowNewRecordRequest;
import com.visionvera.psychologist.module.followmanager.beans.FollowNewRecordSimpleRequest;
import com.visionvera.psychologist.module.followmanager.beans.NewFollowRecordBean;
import com.visionvera.psychologist.module.incidencereport.activity.IncidenceReportDetailActivity;
import com.visionvera.psychologist.module.incidencereport.bean.IllReportListRequestBean;
import com.visionvera.psychologist.module.incidencereport.bean.IllReportListResponseBean;
import com.visionvera.psychologist.module.incidencereport.bean.IncidenceReportDetailBean2;
import com.visionvera.psychologist.module.incidencereport.bean.NewIncidenceReportRequest;
import com.visionvera.psychologist.module.login.beans.GetSmsCodeRequestBean;
import com.visionvera.psychologist.module.login.beans.GetSmsCodeResponseBean;
import com.visionvera.psychologist.module.login.beans.LoginRequestBean;
import com.visionvera.psychologist.module.login.beans.LoginResponseBean;
import com.visionvera.psychologist.module.login.beans.RegisterRequestBean;
import com.visionvera.psychologist.module.message.beans.MessageDetailListRequestBean;
import com.visionvera.psychologist.module.message.beans.MessageDetailListResponseBean;
import com.visionvera.psychologist.module.message.beans.MessageTypeListRequestBean;
import com.visionvera.psychologist.module.message.beans.MessageTypeListResponseBean;
import com.visionvera.psychologist.module.move.activitys.LeaveHospitalDetailActivity;
import com.visionvera.psychologist.module.move.bean.LeaveHospitalBean;
import com.visionvera.psychologist.module.move.bean.LeaveHospitalDetailBean;
import com.visionvera.psychologist.module.move.bean.LeaveHospitalRequest;
import com.visionvera.psychologist.module.move.bean.MoveButtonOperationRequest;
import com.visionvera.psychologist.module.move.bean.MoveDetailBean;
import com.visionvera.psychologist.module.move.bean.MoveDetailRequest;
import com.visionvera.psychologist.module.move.bean.MoveOutListBean;
import com.visionvera.psychologist.module.move.bean.MoveOutListRequest;
import com.visionvera.psychologist.module.move.bean.NewLeaveHospitalBean;
import com.visionvera.psychologist.module.move.bean.NewLeaveHospitalRequest;
import com.visionvera.psychologist.module.move.bean.NewMoveOutBean;
import com.visionvera.psychologist.module.move.bean.NewMoveOutRequest;
import com.visionvera.psychologist.module.ordermanage.activitys.DoctorDetailActivity;
import com.visionvera.psychologist.module.ordermanage.activitys.OrderTimeActivity;
import com.visionvera.psychologist.module.ordermanage.activitys.SearchDoctorActivity;
import com.visionvera.psychologist.module.ordermanage.activitys.TimeTable2Activity;
import com.visionvera.psychologist.module.ordermanage.beans.DiagnosiModeListBean;
import com.visionvera.psychologist.module.ordermanage.beans.DoctorDetailBean;
import com.visionvera.psychologist.module.ordermanage.beans.DoctorListRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.DoctorListResponseBean;
import com.visionvera.psychologist.module.ordermanage.beans.HospitalListRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.HospitalListResponseBean;
import com.visionvera.psychologist.module.ordermanage.beans.OrderCancelRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.OrderConfirmRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.OrderDetailRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.OrderDetailResponseBean;
import com.visionvera.psychologist.module.ordermanage.beans.OrderListRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.OrderListResponseBean;
import com.visionvera.psychologist.module.ordermanage.beans.SearchDoctorBean;
import com.visionvera.psychologist.module.ordermanage.beans.SubjectListRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.SubjectListResponseBean;
import com.visionvera.psychologist.module.ordermanage.beans.SubmitOrderRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.TagListRequestBean;
import com.visionvera.psychologist.module.ordermanage.beans.TagListResponseBean;
import com.visionvera.psychologist.module.ordermanage.beans.TimeTableBean2;
import com.visionvera.psychologist.module.remoteconsultation.beans.RemoteConsultationConfirmRequestBean;
import com.visionvera.psychologist.module.remoteconsultation.beans.RemoteConsultationDetailRequestBean;
import com.visionvera.psychologist.module.remoteconsultation.beans.RemoteConsultationDetailResponseBean;
import com.visionvera.psychologist.module.remoteconsultation.beans.RemoteConsultationListRequestBean;
import com.visionvera.psychologist.module.remoteconsultation.beans.RemoteConsultationListResponseBean;
import com.visionvera.psychologist.module.set.bean.ChangePassRequest;
import com.visionvera.psychologist.module.set.bean.ForgetPasswordCheckNumberRequest;
import com.visionvera.psychologist.module.set.bean.ForgetPasswordCommitRequest;
import com.visionvera.psychologist.net.BaseApiService;
import com.visionvera.psychologist.net.RetrofitManager;
import com.visionvera.psychologist.util.MD5Utils;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

*/
/**
 * @author 刘传政
 * @date 2018/12/23 0023 10:39
 * QQ:1052374416
 * telephone:18501231486
 * 作用:这种model是反复斟酌定下来的. model不是每个类或每个模块有一个,而是全都放到这里.采用内部类的方式区分模块.
 * common代表一些通用的接口.考虑到一个presenter中可能用到多个model的方法,所以也不需要在presenter中强制
 * 定义到底采用那个model. 其实说白了,我这框架的model作用弱化了,有点类似于presenter和model合并的感觉.
 * 注意事项:
 * <p>
 * 患者管理模块
 * <p>
 * 添加患者之前的请求选项
 * @param lifecycleProvider
 * @param observer
 * <p>
 * 患者详情-获取流转记录
 * @return 患者状态统计
 * <p>
 * 随访管理----新增记录之前的所有options选项的数据
 * <p>
 * 预约诊疗
 * <p>
 * 随访管理---首页面的列表数据页面
 * <p>
 * 随访管理---新增随访管理
 * <p>
 * 随访管理---新增随访管理三个参数版本
 * <p>
 * 确认提交预约
 * <p>
 * 上传图片
 * <p>
 * 危险上报的填写报告单
 * <p>
 * 病情类别-获取病情类别list接口
 * <p>
 * 获取区域列表
 * <p>
 * 危险上报，查看报告状态列表界面
 * <p>
 * 患者详情页
 * <p>
 * 危险上报-获取上级手机号
 * <p>
 * 危险上报-获取上级的会议室信息
 * <p>
 * 随访管理的详情
 * <p>
 * 危险上报，查看报告状态列表界面
 * <p>
 * 流转管理的列表
 * <p>
 * 心理出院报告列表分页
 * <p>
 * 新增发病报告
 * <p>
 * 新增发病报告
 * <p>
 * 出院信息详情
 * <p>
 * 新增患者流转单
 * <p>
 * 心理-危险上报相关-详细 信 息
 * <p>
 * 发病报告详情
 * <p>
 * 医生详情
 * <p>
 * 获取医生可用日程
 * <p>
 * 预约诊疗-----获取诊疗类型
 * <p>
 * 转死亡患者页面编辑
 * <p>
 * 患者流转按钮操作
 * <p>
 * 远程会诊
 * <p>
 * 预约诊疗----搜素医生
 * <p>
 * 随访记录列表之删除按钮
 * <p>
 * 非在管患者转在管患者
 * <p>
 * 消息模块
 * <p>
 * setting的设置
 * <p>
 * 患者管理模块
 * <p>
 * 添加患者之前的请求选项
 * @param lifecycleProvider
 * @param observer
 * <p>
 * 患者详情-获取流转记录
 * @return 患者状态统计
 * <p>
 * 随访管理----新增记录之前的所有options选项的数据
 * <p>
 * 预约诊疗
 * <p>
 * 随访管理---首页面的列表数据页面
 * <p>
 * 随访管理---新增随访管理
 * <p>
 * 随访管理---新增随访管理三个参数版本
 * <p>
 * 确认提交预约
 * <p>
 * 上传图片
 * <p>
 * 危险上报的填写报告单
 * <p>
 * 病情类别-获取病情类别list接口
 * <p>
 * 获取区域列表
 * <p>
 * 危险上报，查看报告状态列表界面
 * <p>
 * 患者详情页
 * <p>
 * 危险上报-获取上级手机号
 * <p>
 * 危险上报-获取上级的会议室信息
 * <p>
 * 随访管理的详情
 * <p>
 * 危险上报，查看报告状态列表界面
 * <p>
 * 流转管理的列表
 * <p>
 * 心理出院报告列表分页
 * <p>
 * 新增发病报告
 * <p>
 * 新增发病报告
 * <p>
 * 出院信息详情
 * <p>
 * 新增患者流转单
 * <p>
 * 心理-危险上报相关-详细 信 息
 * <p>
 * 发病报告详情
 * <p>
 * 医生详情
 * <p>
 * 获取医生可用日程
 * <p>
 * 预约诊疗-----获取诊疗类型
 * <p>
 * 转死亡患者页面编辑
 * <p>
 * 患者流转按钮操作
 * <p>
 * 远程会诊
 * <p>
 * 预约诊疗----搜素医生
 * <p>
 * 随访记录列表之删除按钮
 * <p>
 * 非在管患者转在管患者
 * <p>
 * 消息模块
 * <p>
 * setting的设置
 *//*

public class BaseModel implements IBaseModel {
    public class Common {

    }

    public class Login {
        public void login(String username, String password, String umengToken, LifecycleProvider lifecycleProvider, Observer<LoginResponseBean> observer) {
            LoginRequestBean requestBean = new LoginRequestBean();
            requestBean.username = username;
            requestBean.password = password;
            requestBean.clientId = umengToken;
            requestBean.mobileOs = "android";

            RetrofitManager.create(BaseApiService.class)
                    .login(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<LoginResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }
    }

    public class Register {
        public void getSmsCode(String mobile, int source, LifecycleProvider lifecycleProvider, Observer<GetSmsCodeResponseBean> observer) {
            GetSmsCodeRequestBean requestBean = new GetSmsCodeRequestBean();
            requestBean.mobile = mobile;
            requestBean.source = source;
            RetrofitManager.create(BaseApiService.class)
                    .getSmsCode(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<GetSmsCodeResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void register(String mobile, String password, int regFrom, String code,
                             LifecycleProvider lifecycleProvider,
                             Observer<BaseResponseBean> observer) {
            RegisterRequestBean requestBean = new RegisterRequestBean();
            requestBean.mobile = mobile;
            String md5 = MD5Utils.MD5(password);
            requestBean.password = md5;
            requestBean.regFrom = regFrom;
            requestBean.code = code;
            RetrofitManager.create(BaseApiService.class)
                    .register(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<BaseResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }
    }

    */
/**
 * 患者管理模块
 *//*

    public class CaseControl {
        public void getPatientList(boolean isRefres, int pageNo, int pageSize, String startDate,
                                   String patientStatus, String diagnose,
                                   String diagnoseLevel,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<PatientListResponseBean> observer) {
            PatientLIstRequestBean requestBean = new PatientLIstRequestBean();
            requestBean.setPageNo(pageNo);
            requestBean.setPageSize(pageSize);
            requestBean.setStartDate(startDate);
            requestBean.setPatientStatus(patientStatus);
            requestBean.setDiagnose(diagnose);
            requestBean.setDiagnoseLevel(diagnoseLevel);
            RetrofitManager.create(BaseApiService.class)
                    .getPatientList(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<PatientListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        */
/**
 * 添加患者之前的请求选项
 *
 * @param lifecycleProvider
 * @param observer
 *//*



        public void getPreSelects(LifecycleProvider lifecycleProvider,
                                  Observer<PreSelectsResponseBean> observer) {
            PreSelectsRequestBean requestBean = new PreSelectsRequestBean();
            requestBean.add = "no";
            RetrofitManager.create(BaseApiService.class)
                    .getPreSelects(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<PreSelectsResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }


        public void searchPatientList(PatientLIstRequestBean requestBean,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<PatientListResponseBean> observer) {

            LifecycleTransformer lifecycleTransformer ;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            }else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .getPatientList(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        public void addPatient(AddPatientRequestBean2 requestBean, LifecycleProvider lifecycleProvider,
                               Observer<PreSelectsResponseBean> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .addPatient(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<PreSelectsResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void getLeaveHospitalList(LeaveHospitalListRequestBean requestBean, LifecycleProvider lifecycleProvider,
                                         Observer<LeaveHospitalListResponseBean> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .getLeaveHospitalList(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<LeaveHospitalListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void getIllReportList(com.visionvera.psychologist.module.casecontrol.bean.IllReportListRequestBean requestBean, LifecycleProvider lifecycleProvider,
                                     Observer<com.visionvera.psychologist.module.casecontrol.bean.IllReportListResponseBean> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .getIllReportList(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<com.visionvera.psychologist.module.casecontrol.bean.IllReportListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void getFollowList(FollowListRequestBean requestBean, LifecycleProvider lifecycleProvider,
                                  Observer<FollowListResponseBean> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .getFollowList(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<FollowListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        */
/**
 * 患者详情-获取流转记录
 *
 * @return
 *//*

        public void getMoveRecordList(MoveRecordListRequestBean requestBean, LifecycleProvider lifecycleProvider,
                                      Observer<MoveRecordListResponseBean> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .getMoveRecordList(requestBean)
                    //绑定生命周期
                    .compose(lifecycleProvider.<MoveRecordListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        */
/**
 * 患者状态统计
 *//*

        public void getPatientStatusStatistics(LifecycleProvider lifecycleProvider,
                                               Observer<PatientStatusStatisticsBean> observer){

            RetrofitManager.create(BaseApiService.class)
                    .getPatientStatusNum()
                    //绑定生命周期
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }


    }

    */
/**
 * 随访管理----新增记录之前的所有options选项的数据
 *//*

    public class FollowNewRecordOption {
        public void getFollowNewRecordOption(FollowNewRecordOptionRequest followNewRecordOptionRequest,
                                             LifecycleProvider lifecycleProvider,
                                             Observer<FollowNewRecordOptionBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getFollowNewRecordOption(followNewRecordOptionRequest)
                    .compose(lifecycleProvider.<FollowNewRecordOptionBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

    }

    */
/**
 * 预约诊疗
 *//*

    public class OrderManage {
        public void getOrderList(OrderListRequestBean requestBean,
                                 LifecycleProvider lifecycleProvider,
                                 Observer<OrderListResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .getOrderList(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void getHospitalList(HospitalListRequestBean requestBean,
                                    LifecycleProvider lifecycleProvider,
                                    Observer<HospitalListResponseBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getHospitalList(requestBean)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void getSubjectList(SubjectListRequestBean requestBean,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<SubjectListResponseBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getSubjectList(requestBean)
                    .compose(lifecycleProvider.<SubjectListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void getTagList(TagListRequestBean requestBean,
                               LifecycleProvider lifecycleProvider,
                               Observer<TagListResponseBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getTagList(requestBean)
                    .compose(lifecycleProvider.<TagListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void getDoctorList(DoctorListRequestBean requestBean,
                                  LifecycleProvider lifecycleProvider,
                                  Observer<DoctorListResponseBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getDoctorList(requestBean)
                    .compose(lifecycleProvider.<DoctorListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void getOrderDetail(OrderDetailRequestBean requestBean,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<OrderDetailResponseBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getOrderDetail(requestBean)
                    .compose(lifecycleProvider.<OrderDetailResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void cancel(OrderCancelRequestBean requestBean,
                           LifecycleProvider lifecycleProvider,
                           Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .orderCancel(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }
        public void confirm( OrderConfirmRequestBean requestBean,
                            LifecycleProvider lifecycleProvider,
                            Observer<BaseResponseBean2> observer){
            LifecycleTransformer lifecycleTransformer ;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            }else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .orderConfirm(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void saveMeetingRecord(SaveMeetingRecordRequest saveMeetingRecordRequest,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<BaseResponseBean2> observer ){
            RetrofitManager.create(BaseApiService.class)
                    .saveMeetingRecord(saveMeetingRecordRequest)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    */
/**
 * 随访管理---首页面的列表数据页面
 *//*

    public class FollowRecordList {
        public void getFollowRecordList(FollowListRequest request,
                                        LifecycleProvider lifecycleProvider,
                                        Observer<com.visionvera.psychologist.module.followmanager.beans.FollowListBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getFollowRecordList(request)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }
    }


    */
/**
 * 随访管理---新增随访管理
 *//*

    public class NewFollowRecord {
        public void getNewFollowRecord(FollowNewRecordRequest followNewRecordRequest,
                                       LifecycleProvider lifecycleProvider, Observer<NewFollowRecordBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .addFollowUpRecord(followNewRecordRequest)
                    .compose(lifecycleProvider.<NewFollowRecordBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 随访管理---新增随访管理三个参数版本
 *//*

    public class NewFollowRecordSimple {
        public void getNewFollowRecordSimple(FollowNewRecordSimpleRequest followNewRecordSimpleRequest,
                                             LifecycleProvider lifecycleProvider, Observer<NewFollowRecordBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .addFollowUpRecordSimple(followNewRecordSimpleRequest)
                    .compose(lifecycleProvider.<NewFollowRecordBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 确认提交预约
 *//*

    public class OrderConfirm {
        public void submit(SubmitOrderRequestBean requestBean,
                           LifecycleProvider lifecycleProvider,
                           Observer<BaseResponseBean2> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .orderSubmit(requestBean)
                    .compose(lifecycleProvider.<BaseResponseBean2>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

    }

    */
/**
 * 上传图片
 *//*

 */
/* public class UploadImg{
        public void uploadImgFile(File imgFile,String prefix,String type,
                                  LifecycleProvider lifecycleProvider,
                                  Observable<UploadImgBean> observable){
            RetrofitManager.create(BaseApiService.class)
                    .uploadImg().
        }
    }*//*


 */
/**
 * 危险上报的填写报告单
 *//*


    public class NewAlarm {
        public void newAlarm(NewAlarmRequest newAlarmRequest,
                             LifecycleProvider lifecycleProvider,
                             Observer<NewAlarmBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .newAlarm(newAlarmRequest)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }


    */
/**
 * 病情类别-获取病情类别list接口
 *//*

    public class IllnessClazzInfoList {
        public void IllnessClazzInfoList(IllnessClazzInfoListRequest request,
                                         LifecycleProvider lifecycleProvider,
                                         Observer<IllnessClazzInfoListBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .IllnessClazzInfoList(request)
                    .compose(lifecycleProvider.<IllnessClazzInfoListBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }
    }

    */
/**
 * 获取区域列表
 *//*

    public class AreaDictTree {
        public void AreaDictTree(AreaDictTreeRequest areaDictTreeRequest,
                                 LifecycleProvider lifecycleProvider,
                                 Observer<AreaDictTreeBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .AreaDictTree(areaDictTreeRequest)
                    .compose(lifecycleProvider.<AreaDictTreeBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 危险上报，查看报告状态列表界面
 *//*

    public class AlarmList {
        public void getAlarmList(AlarmListRequestBean requestBean,
                                 LifecycleProvider lifecycleProvider,
                                 Observer<AlarmListResponseBean> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .getAlramList(requestBean)
                    .compose(lifecycleProvider.<AlarmListResponseBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

    }

    */
/**
 * 患者详情页
 *//*

    public class PatientDetail {
        public void getPatientDetail(PatientDetailRequestBean requestBean,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<PatientDetailResponseBean> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .getPatientDetail(requestBean)
                    .compose(lifecycleProvider.<PatientDetailRequestBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 危险上报-获取上级手机号
 *//*

    public class SuperLevelPhoneNumber {
        public void getSuperLevelPhoneNumber(JSONObject object,
                                             LifecycleProvider lifecycleProvider,
                                             Observer<BaseResponseBean2> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getSuperLevelPhoneNumber(object)
                    .compose(lifecycleProvider.<BaseResponseBean2>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 危险上报-获取上级的会议室信息
 *//*

    public class UpterInfo {
        public void getUpterInfo(LifecycleProvider lifecycleProvider,
                                 Observer<AlarmUpterInfoBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getUpterInfo()
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void newLongitudeAndLatitude(NewAlarmRequest newAlarmRequest,
                               LifecycleProvider lifecycleProvider,
                               Observer<NewAlarmBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .newLongitudeAndLatitude(newAlarmRequest)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        public void saveMeetingRecord(SaveMeetingRecordRequest saveMeetingRecordRequest,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<BaseResponseBean2> observer ){
            RetrofitManager.create(BaseApiService.class)
                    .saveMeetingRecord(saveMeetingRecordRequest)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }


    }


    */
/**
 * 随访管理的详情
 *//*

    public class FollowRecordDetail {
        public void getFollowRecordDetail(FollowDetailRequest request,
                                          LifecycleProvider lifecycleProvider,
                                          Observer<FollowDetailBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getFollowRecordDetail(request)
                    .compose(lifecycleProvider.<FollowDetailBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 危险上报，查看报告状态列表界面
 *//*

    public class IllReportList {
        public void getList(IllReportListRequestBean requestBean,
                            LifecycleProvider lifecycleProvider,
                            Observer<IllReportListResponseBean> observer) {

            RetrofitManager.create(BaseApiService.class)
                    .getIllReportList(requestBean)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

    }

    */
/**
 * 流转管理的列表
 *//*

    public class MoveOutList {
        public void getMoveOutList(MoveOutListRequest moveListRequest,
                                   LifecycleProvider lifecycleProvider,
                                   Observer<MoveOutListBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getMoveList(moveListRequest)
                    .compose(lifecycleProvider.<MoveOutListBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }


    */
/**
 * 心理出院报告列表分页
 *//*

    public class LeaveHospitalList {
        public void getLeaveHospitalList(LeaveHospitalRequest request,
                                         LifecycleProvider lifecycleProvider,
                                         Observer<LeaveHospitalBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getLeaveHospitalList(request)
                    .compose(lifecycleProvider.<LeaveHospitalBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }


    */
/**
 * 新增发病报告
 *//*

    public class NewIncidenceReport {
        public void getNewIncidenceReport(NewIncidenceReportRequest reportRequest,
                                          LifecycleProvider lifecycleProvider,
                                          Observer<BaseResponseBean2> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getAddillnessReport(reportRequest)
                    .compose(lifecycleProvider.<BaseResponseBean2>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    */
/**
 * 新增发病报告
 *//*

    public class NewLeaveHospital {
        public void getNewLeaveHospital(NewLeaveHospitalRequest request,
                                        LifecycleProvider lifecycleProvider,
                                        Observer<NewLeaveHospitalBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .newLeaveHospital(request)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 出院信息详情
 *//*

    public class LeaveHospitalDetail {
        public void getLeaveHospitalDetail(LeaveHospitalDetailActivity.LeaveHospitalDetailRequest request,
                                           LifecycleProvider lifecycleProvider,
                                           Observer<LeaveHospitalDetailBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .leaveHospitalDetail(request)
                    .compose(lifecycleProvider.<LeaveHospitalDetailBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 新增患者流转单
 *//*

    public class NewMoveOut {
        public void getNewMoveOut(NewMoveOutRequest request,
                                  LifecycleProvider lifecycleProvider,
                                  Observer<NewMoveOutBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .newMoveOut(request)
                    .compose(lifecycleProvider.<NewMoveOutBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    public class MoveOutDetail {

        public void getMoveOutDetail(MoveDetailRequest request,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<MoveDetailBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getMoveDetail(request)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }



    */
/**
 * 心理-危险上报相关-详细 信 息
 *//*

    public class InquiryReportDetail {
        public void getInquiryReportDetail(InquiryReportStatusDetailActivity.InquiryReportDetailRequest request,
                                           LifecycleProvider lifecycleProvider,
                                           Observer<InquiryReportDetailBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getInquiryReportDetail(request)
                    .compose(lifecycleProvider.<InquiryReportDetailBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 发病报告详情
 *//*

    public class IncidenceReportDetail {
        public void getIncidenceReportDetail(IncidenceReportDetailActivity.IncidenceReportDetailRequest request,
                                             LifecycleProvider lifecycleProvider,
                                             Observer<IncidenceReportDetailBean2> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getIncidenceReportDetail(request)
                    .compose(lifecycleProvider.<IncidenceReportDetailBean2>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 医生详情
 *//*

    public class DoctorDetail {
        public void getDoctorDetail(DoctorDetailActivity.DoctorDetailRequest request,
                                    LifecycleProvider lifecycleProvider,
                                    Observer<DoctorDetailBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getDoctorDetail(request)
                    .compose(lifecycleProvider.<DoctorDetailBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 获取医生可用日程
 *//*

    public class TimeTable {
        public void getTimeTable(TimeTable2Activity.TimeTableRequest request,
                                 LifecycleProvider lifecycleProvider,
                                 Observer<TimeTableBean2> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getTimeTable(request)
                    .compose(lifecycleProvider.<TimeTableBean2>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 预约诊疗-----获取诊疗类型
 *//*

    public class DiagnosisMode {
        public void getDiagnosisMode(OrderTimeActivity.DiagnosisModeListRequest request,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<DiagnosiModeListBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getDiagnosiModeList(request)
                    .compose(lifecycleProvider.<DiagnosiModeListBean>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 转死亡患者页面编辑
 *//*

    public class TransferDeath {
        public void getTransferDeath(TransferDeathActivity.TransferDeathRequest request,
                                     LifecycleProvider lifecycleProvider,
                                     Observer<BaseResponseBean2> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getTransferDeath(request)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 患者流转按钮操作
 *//*

    public class MoveButtonOperation {
        public void getMoveButtonOperation(MoveButtonOperationRequest request,
                                           LifecycleProvider lifecycleProvider,
                                           Observer<BaseResponseBean2> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .getMoveButtonOperation(request)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 远程会诊
 *//*

    public class RemoteConsultation {
        public void getRemoteConsultationList(RemoteConsultationListRequestBean requestBean,
                                              LifecycleProvider lifecycleProvider,
                                              Observer<RemoteConsultationListResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .getRemoteConsultationList(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }


        public void getRemoteConsultationDetail(RemoteConsultationDetailRequestBean requestBean,
                                                LifecycleProvider lifecycleProvider,
                                                Observer<RemoteConsultationDetailResponseBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .getRemoteConsultationDetail(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

        public void confirm(RemoteConsultationConfirmRequestBean requestBean,
                            LifecycleProvider lifecycleProvider,
                            Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .remoteConsultationConfirm(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }

    }


    */
/**
 * 预约诊疗----搜素医生
 *//*

    public class SearchDoctor {
        public void getSearchDoctor(SearchDoctorActivity.SearchDoctorRequest request,
                                    LifecycleProvider lifecycleProvider,
                                    Observer<SearchDoctorBean> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .searchDoctor(request)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 随访记录列表之删除按钮
 *//*

    public class FollowDetailDelete {
        public void getFollowDetailDelete(FollowDetailActivity.FollowDetailDeleteRequest request,
                                          LifecycleProvider lifecycleProvider,
                                          Observer<BaseResponseBean2> observer) {
            RetrofitManager.create(BaseApiService.class)
                    .followDetailDelete(request)
                    .compose(lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

    */
/**
 * 非在管患者转在管患者
 *//*

    public class NotManageToManage{
        public void getNotFollowToFollow(NotManageToManageRequestBean request,
                                         LifecycleProvider lifecycleProvider,
                                         Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .notManageToManage(request)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }
    */
/**
 * 消息模块
 *//*

    public class Message{

        public void getMessageTypeList( MessageTypeListRequestBean requestBean,
                            LifecycleProvider lifecycleProvider,
                            Observer<MessageTypeListResponseBean> observer){
            LifecycleTransformer lifecycleTransformer ;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            }else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .getMessageTypeList(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        public void getMessageDetailList( MessageDetailListRequestBean requestBean,
                                        LifecycleProvider lifecycleProvider,
                                            Observer<MessageDetailListResponseBean> observer){
            LifecycleTransformer lifecycleTransformer ;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            }else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .getMessageDetailList(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);

        }
    }

    */
/**
 * setting的设置
 *//*

    public class Setting{
        public void getChangePass(ChangePassRequest requestBean,
                                  LifecycleProvider lifecycleProvider,
                                  Observer<BaseResponseBean2> observer){
            LifecycleTransformer lifecycleTransformer ;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            }else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .changePassword(requestBean)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }


    public class ForgetPassword{
        public void getForgetPasswordCheckNumber(ForgetPasswordCheckNumberRequest request,
                                                 LifecycleProvider lifecycleProvider,
                                                 Observer<BaseResponseBean2> observer){
            LifecycleTransformer lifecycleTransformer ;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            }else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .forgetPasswordCheckNumber(request)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

        public void getForgetPasswordCommit(ForgetPasswordCommitRequest request,
                                            LifecycleProvider lifecycleProvider,
                                            Observer<BaseResponseBean2> observer){

            LifecycleTransformer lifecycleTransformer ;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            }else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(BaseApiService.class)
                    .forgetPasswordCommit(request)
                    .compose(lifecycleTransformer)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);


        }


    }

}
*/
