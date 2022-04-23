package com.tencent.qcloud.tuicore.net;

import android.app.Activity;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.model.IBaseModel;
import com.visionvera.library.net.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @Classname:EvaluationModuleModel
 * @author:haohuizhao
 * @Date:2021/12/05 15:37
 * @Version：v1.0
 * @describe： <p>
 */
public class EvaluationModuleModel implements IBaseModel {
    /**
     * 对原始的observable进行线程操作.不破坏原始的链式调用
     *
     * @return
     */
    public ObservableTransformer getThreadTransformer() {
        ObservableTransformer observableTransformer = new ObservableTransformer() {

            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
        return observableTransformer;
    }


    /**
     * 预约咨询
     */
    public class OrderConsult {

        //咨询/诊疗上报操作信息接口
        //用户发起聊天、用户接听、用户确认服务状态    用于判断按钮显示
        public void reportInformation(ReportInformationBeanRequest request,
                                      LifecycleProvider lifecycleProvider,
                                      Observer<BaseResponseBean2> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .reportInformation(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

        /**
         * 心理咨询 预约相关-app获取预约详情接口
         */
        public void getOrderConsultDetail(OrderConsultDetailRequest request,
                                          LifecycleProvider lifecycleProvider,
                                          Observer<OrderConsultDetailBean> observer) {
            LifecycleTransformer lifecycleTransformer;
            if (lifecycleProvider instanceof Activity) {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(ActivityEvent.DESTROY);
            } else {
                lifecycleTransformer = lifecycleProvider.bindUntilEvent(FragmentEvent.DESTROY);
            }
            RetrofitManager.create(EvaluationModuleApi.class)
                    .getOrderConsultDetail(request)
                    .compose(lifecycleTransformer)
                    .compose(getThreadTransformer())
                    .subscribe(observer);
        }

    }


}
