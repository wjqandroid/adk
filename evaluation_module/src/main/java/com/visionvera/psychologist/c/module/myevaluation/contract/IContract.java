package com.visionvera.psychologist.c.module.myevaluation.contract;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.psychologist.c.module.myevaluation.bean.MyEvaluationBean;
import com.visionvera.psychologist.c.module.myevaluation.bean.ReTestRequestBean;

import okhttp3.RequestBody;

/**
 * @Desc 我的测试
 * @Author yemh
 * @Date 2019-11-07 16:08
 */
public interface IContract {
    interface PendingTest {
        interface View extends IBaseRetrofitView {
            void onGetPendingTest(boolean isRefresh, MyEvaluationBean bean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getPendingTest(boolean isRefresh, RequestBody requestBody, LifecycleProvider lifecycleProvider);
        }
    }

    interface Tested {
        interface View extends IBaseRetrofitView {
            void onGetTested(boolean isRefresh, MyEvaluationBean bean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getTested(boolean isRefresh, RequestBody requestBody, LifecycleProvider lifecycleProvider);
        }
    }

    interface Expired {
        interface View extends IBaseRetrofitView {
            void onGetExpired(boolean isRefresh, MyEvaluationBean bean, ResultType resultType, String errorMsg);

            void onReTest(BaseResponseBean2 bean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getExpired(boolean isRefresh, RequestBody requestBody, LifecycleProvider lifecycleProvider);

            void reTest(ReTestRequestBean requestBean, LifecycleProvider lifecycleProvider);
        }
    }

}
