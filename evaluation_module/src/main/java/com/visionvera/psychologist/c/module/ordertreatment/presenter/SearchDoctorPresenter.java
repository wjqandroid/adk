package com.visionvera.psychologist.c.module.ordertreatment.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.ordertreatment.activity.SearchDoctorActivity;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SearchDoctorBean;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchDoctorPresenter extends BasePresenter<IContract.SearchDoctor.SearchDoctorView> implements IContract.SearchDoctor.SearchDoctorBasePresenter {

    EvaluationModuleModel.SearchDoctor searchDoctor;

    public SearchDoctorPresenter(Context context, IContract.SearchDoctor.SearchDoctorView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        searchDoctor=new EvaluationModuleModel().new SearchDoctor();
    }

    @Override
    public void getSearchDoctor(SearchDoctorActivity.SearchDoctorRequest request, LifecycleProvider lifecycleProvider) {
        searchDoctor.getSearchDoctor(request, lifecycleProvider, new Observer<SearchDoctorBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchDoctorBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onSearchDoctor(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onSearchDoctor(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, bean.getErrMsg());
                    }
                } else {
                    mView.onSearchDoctor(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView==null){
                    return;
                }
                mView.onSearchDoctor(null, IBaseView.ResultType.NET_ERROR,e.toString());

            }

            @Override
            public void onComplete() {
                if (mView==null){
                    return;
                }
                mView.onComplete();
            }
        });
    }
}
