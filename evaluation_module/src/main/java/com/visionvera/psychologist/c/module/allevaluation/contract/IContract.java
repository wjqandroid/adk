package com.visionvera.psychologist.c.module.allevaluation.contract;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListResponseBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleResponseBean;

public interface IContract {
    interface AllEvaluationFragment {
        interface View extends IBaseRetrofitView {
            void onGetTabTitles(TabTitleResponseBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getTabTitles(TabTitleRequestBean requestBean, LifecycleProvider lifecycleProvider);
        }
    }

    interface AllEvaluationItemFragment {
        interface View extends IBaseRetrofitView {
            void onGetEvaluationChatList(boolean isRefresh, EvaluationChatListResponseBean responseBean, ResultType resultType, String errorMsg);
        }

        interface Presenter {
            void getEvaluationChatList(boolean isRefresh, EvaluationChatListRequestBean requestBean, LifecycleProvider lifecycleProvider);
        }
    }

}
