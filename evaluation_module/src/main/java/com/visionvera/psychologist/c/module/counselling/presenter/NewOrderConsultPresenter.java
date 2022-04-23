package com.visionvera.psychologist.c.module.counselling.presenter;

import android.content.Context;

import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

public class NewOrderConsultPresenter extends BasePresenter<OrderConsultContract.NewOrderConsult.NewOrderConsultView> implements OrderConsultContract.NewOrderConsult.NewOrderConsultBasePresenter {

    EvaluationModuleModel.OrderConsult orderConsult;

    public NewOrderConsultPresenter(Context context, OrderConsultContract.NewOrderConsult.NewOrderConsultView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        orderConsult=new EvaluationModuleModel().new OrderConsult();
    }

}
