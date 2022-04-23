package com.visionvera.psychologist.c.module.knowledge_library.presenter;


import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddArticleRequestBean;
import com.visionvera.psychologist.c.module.knowledge_library.contract.IContract;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WriteArticleActivityPresenter extends BasePresenter<IContract.WriteArticleActivity.View> implements IContract.WriteArticleActivity.Presenter {
    EvaluationModuleModel.KnowledgeCircle model;
    EvaluationModuleModel.OrderConsult orderConsult;

    public WriteArticleActivityPresenter(Context context, IContract.WriteArticleActivity.View mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        model = new EvaluationModuleModel().new KnowledgeCircle();
        orderConsult = new EvaluationModuleModel().new OrderConsult();
    }

    @Override
    public void addArticle(@NotNull AddArticleRequestBean requestBean, @Nullable LifecycleProvider<?> lifecycleProvider) {
        model.addArticle(requestBean, lifecycleProvider, new Observer<BaseResponseBean3<Object>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean3<Object> bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    if (bean.getResult() == null) {
                        mView.onAddArticle(null, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onAddArticle(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onAddArticle(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onAddArticle(null, IBaseView.ResultType.NET_ERROR, e.toString());
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
