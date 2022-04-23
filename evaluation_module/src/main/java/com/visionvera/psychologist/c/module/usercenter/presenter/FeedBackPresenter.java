package com.visionvera.psychologist.c.module.usercenter.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;
import com.visionvera.psychologist.c.utils.cos.SavePathRequestBean;
import com.visionvera.psychologist.c.utils.cos.SavePathResponseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;

public class FeedBackPresenter extends BasePresenter<IContract.FeedBack.FeedBackView> implements IContract.FeedBack.FeedBackBasePresenter{

    EvaluationModuleModel.Setting setting;

    public FeedBackPresenter(Context context, IContract.FeedBack.FeedBackView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        setting=new EvaluationModuleModel().new Setting();
    }

    @Override
    public void getFeedBack(RequestBody request, LifecycleProvider lifecycleProvider) {
        setting.getFeedBack(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onFeedBack(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onFeedBack(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onFeedBack(null, IBaseView.ResultType.NET_ERROR,e.toString());
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
    public void savePath(SavePathRequestBean requestBean, LifecycleProvider lifecycleProvider, int position) {
        setting.savePath(requestBean, lifecycleProvider, new Observer<SavePathResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SavePathResponseBean responseBean) {
                if (mView == null) {
                    return;
                }
                if (responseBean.code == Constant.NetCode.success2) {
                    mView.onSavePath(responseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, responseBean.errMsg, position);
                } else {
                    mView.onSavePath(responseBean, IBaseView.ResultType.SERVER_ERROR, responseBean.errMsg, position);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    return;
                }
                mView.onSavePath(null, IBaseView.ResultType.NET_ERROR, e.toString(), position);
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
