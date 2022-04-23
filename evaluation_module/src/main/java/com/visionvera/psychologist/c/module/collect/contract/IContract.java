package com.visionvera.psychologist.c.module.collect.contract;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;

import okhttp3.RequestBody;

public interface IContract {
    interface Collect {
        interface View extends IBaseRetrofitView {
            void onGetCollects(boolean isRefresh, MyEvaluationBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getCollects(boolean isRefresh, RequestBody requestBody, LifecycleProvider lifecycleProvider);
        }
    }
}
