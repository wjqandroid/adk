package com.visionvera.psychologist.c.module.usercenter.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.module.usercenter.bean.ChangePassRequest;
import com.visionvera.psychologist.c.module.usercenter.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ChangePassPresenter extends BasePresenter<IContract.ChangePass.ChangePassView> implements IContract.ChangePass.ChangePassBasePresenter{

    EvaluationModuleModel.Setting setting;

    public ChangePassPresenter(Context context, IContract.ChangePass.ChangePassView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        setting=new EvaluationModuleModel().new Setting();
    }

    @Override
    public void getChangePass(ChangePassRequest request, LifecycleProvider lifecycleProvider) {
        setting.getChangePass(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 response) {
                if (mView==null){
                    return ;
                }
                if (response.getCode() == Constant.NetCode.success2) {
                    mView.onChangePass(response, IBaseView.ResultType.SERVER_NORMAL_DATAYES, response.getErrMsg());
                } else {
                    mView.onChangePass(response, IBaseView.ResultType.SERVER_ERROR, response.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onChangePass(null, IBaseView.ResultType.NET_ERROR,e.toString());
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
