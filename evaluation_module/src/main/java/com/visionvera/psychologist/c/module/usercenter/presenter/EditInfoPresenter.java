package com.visionvera.psychologist.c.module.usercenter.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListBean;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListRequestBean;
import com.visionvera.psychologist.c.module.usercenter.bean.InforMationBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobBean;
import com.visionvera.psychologist.c.module.usercenter.bean.JobRequest;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

public class EditInfoPresenter extends BasePresenter<IContract.EditInfo.EditInfoView> implements IContract.EditInfo.EditInfoBasePresenter{

    EvaluationModuleModel.EditInfo editInfo;

    public EditInfoPresenter(Context context, IContract.EditInfo.EditInfoView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        editInfo=new EvaluationModuleModel().new EditInfo();
    }

    @Override
    public void getInforMation(LifecycleProvider lifecycleProvider) {
        editInfo.getInforMation(lifecycleProvider, new Observer<InforMationBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(InforMationBean response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onGetInforMation(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onGetInforMation(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onGetInforMation(null, IBaseView.ResultType.NET_ERROR,e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });
    }

    @Override
    public void getJobList(JobRequest jobRequest, LifecycleProvider lifecycleProvider) {
        editInfo.getJobList(jobRequest, lifecycleProvider, new Observer<JobBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JobBean response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onGetJobList(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onGetJobList(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onGetJobList(null, IBaseView.ResultType.NET_ERROR,e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });
    }


    @Override
    public void saveInforMation(RequestBody requestBody, LifecycleProvider lifecycleProvider) {
        editInfo.saveInforMation(requestBody,lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onSaveInforMation(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onSaveInforMation(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onSaveInforMation(null, IBaseView.ResultType.NET_ERROR,e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });
    }

    @Override
    public void savePath(SavePathRequestBean requestBean, LifecycleProvider lifecycleProvider) {
        editInfo.savePath(requestBean, lifecycleProvider, new Observer<SavePathResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SavePathResponseBean responseBean) {
                if (mView == null) {
                    return;
                }
                if (responseBean.code == Constant.NetCode.success2) {
                    mView.onSavePath(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, responseBean.errMsg);
                } else {
                    mView.onSavePath(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.errMsg);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    return;
                }
                mView.onSavePath(null, IBaseView.ResultType.NET_ERROR, e.toString());
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });
    }


    @Override
    public void getAreaList(AreaListRequestBean areaListRequestBean, LifecycleProvider lifecycleProvider, int on_click) {
        editInfo.getAreaList(areaListRequestBean,on_click, lifecycleProvider, new Observer<AreaListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AreaListBean response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onGetAreaList(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg(),on_click);
                } else {
                    mView.onGetAreaList(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg(),on_click);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onGetAreaList(null, IBaseView.ResultType.NET_ERROR,e.toString(),on_click);
            }

            @Override
            public void onComplete() {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onComplete();
            }
        });
    }




}
