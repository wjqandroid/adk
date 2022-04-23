package com.visionvera.psychologist.c.module.usercenter.contract;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.bean.BaseResponseBean4;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
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
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import okhttp3.RequestBody;

public interface IContract {
    interface Message {
        interface View extends IBaseRetrofitView {
            void onGetMessage(boolean isRefresh, MessageBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getMessage(boolean isRefresh, RequestBody requestBody, LifecycleProvider lifecycleProvider);
        }
    }

    interface ChangePass{
        interface ChangePassView extends IBaseRetrofitView{
            void onChangePass(BaseResponseBean2 response, ResultType resultType, String errorMsg);
        }
        interface ChangePassBasePresenter{
            void getChangePass(ChangePassRequest request, LifecycleProvider lifecycleProvider);
        }
    }

    interface ForgetPassword{
        interface ForgetPasswordView extends IBaseRetrofitView{
            void onForgetPasswordCheckNumber(BaseResponseBean2 response, ResultType resultType, String errorMsg);

            void onForgetPasswordCommit(BaseResponseBean2 response, ResultType resultType, String errorMsg);
        }
        interface ForgetPasswordBasePresenter{
            void getForgetPasswordCheckNumber(ForgetPasswordCheckNumberRequest request, LifecycleProvider lifecycleProvider);

            void getForgetPasswordCommit(ForgetPasswordCommitRequest request, LifecycleProvider lifecycleProvider);
        }
    }

    interface EditInfo{
        interface EditInfoView extends IBaseRetrofitView{
            void onSaveInforMation(BaseResponseBean2 inforMationBean, ResultType resultType, String errorMsg);
            void onGetJobList(JobBean jobBean, ResultType resultType, String errorMsg);
            void onGetAreaList(AreaListBean areaListBean, ResultType resultType, String errorMsg,int on_click);
            void onGetInforMation(InforMationBean inforMationBean,ResultType resultType, String errorMsg);

            void onSavePath(SavePathResponseBean responseBean, ResultType resultType, String errorMsg);
        }

        interface EditInfoBasePresenter{
            void getInforMation(LifecycleProvider lifecycleProvider);
            void getJobList(JobRequest jobRequest, LifecycleProvider lifecycleProvider);
            void getAreaList(AreaListRequestBean areaListRequestBean, LifecycleProvider lifecycleProvider,int on_click);
            void saveInforMation(RequestBody requestBody, LifecycleProvider lifecycleProvider);

            void savePath(SavePathRequestBean requestBean, LifecycleProvider lifecycleProvider);
        }
    }

    interface FeedBack{
        interface FeedBackView extends IBaseRetrofitView{
            void onFeedBack(BaseResponseBean2 response, ResultType resultType, String errorMsg);

            void onSavePath(SavePathResponseBean responseBean, ResultType resultType, String errorMsg, int position);
        }
        interface FeedBackBasePresenter{
            void getFeedBack(RequestBody requestBody, LifecycleProvider lifecycleProvider);

            void savePath(SavePathRequestBean requestBean, LifecycleProvider lifecycleProvider, int position);
        }
    }

    interface UserInfo {
        interface View extends IBaseRetrofitView {
            void onGetUserBasicInfo(UserBasicInfoBean userBasicInfoBean, ResultType resultType, String errorMsg);

            void onGetSignDays(BaseResponseBean4 baseResponseBean4, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getUserBasicInfo(LifecycleProvider lifecycleProvider);
            void getSignDays(LifecycleProvider lifecycleProvider);
        }
    }

    interface LoginOut{
        interface LoginOutView extends IBaseRetrofitView{
            void onLoginOut(BaseResponseBean2 response,ResultType resultType, String errorMsg);
        }

        interface LoginOutBasePresenter{
            void getLoginOut(LifecycleProvider lifecycleProvider);
        }
    }




}
