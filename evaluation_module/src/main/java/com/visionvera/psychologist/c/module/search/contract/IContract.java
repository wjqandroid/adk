package com.visionvera.psychologist.c.module.search.contract;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListResponseBean;
import com.visionvera.psychologist.c.module.search.bean.DiscoverRequestBean;
import com.visionvera.psychologist.c.module.search.bean.DiscoverResponseBean;

public interface IContract {
    interface SearchEvaluationActivity {
        interface View extends IBaseRetrofitView {
            void onGetDiscover(DiscoverResponseBean responseBean, ResultType resultType, String errorMsg);

            void onGetEvaluationChatList(boolean isRefresh, EvaluationChatListResponseBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getDiscover(DiscoverRequestBean requestBean, LifecycleProvider lifecycleProvider);
            void getEvaluationChatList(boolean isRefresh, EvaluationChatListRequestBean requestBean, LifecycleProvider lifecycleProvider);
        }

    }

}
