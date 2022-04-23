package com.visionvera.psychologist.c.module.counselling.presenter;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarBean;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarRequest;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TimeCalendarPresenter extends BasePresenter<OrderConsultContract.TimeCalendar.TimeCalendarView> implements OrderConsultContract.TimeCalendar.TimeCalendarBasePersenter {

    EvaluationModuleModel.OrderConsult orderConsult;

    public TimeCalendarPresenter(Context context, OrderConsultContract.TimeCalendar.TimeCalendarView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        orderConsult=new EvaluationModuleModel().new OrderConsult();
    }

    @Override
    public void getTimeCalendar(TimeCalendarRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getTimeCalendar(request, lifecycleProvider, new Observer<TimeCalendarBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TimeCalendarBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onTimeCalendar(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onTimeCalendar(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onTimeCalendar(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onTimeCalendar(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
