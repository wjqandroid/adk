package com.visionvera.psychologist.c.module.counselling.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.bean.BaseResponseBean2;
import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.CounselorDetailBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.ordertreatment.bean.AddEvaluateRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.DoctorDetailRequest;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateDetailBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListBean;
import com.visionvera.psychologist.c.module.ordertreatment.bean.EvaluateListRequest;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Classname:CounselorDetailPresenter
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0
 * @describe： 描述:预约咨询 咨询师详情    个人信息、价格、评价列表
 */
public class CounselorDetailPresenter extends BasePresenter<OrderConsultContract.CounselorDetail.CounselorDetailView> implements OrderConsultContract.CounselorDetail.CounselorDetailBasePresenter {

    EvaluationModuleModel.OrderConsult orderConsult;

    public CounselorDetailPresenter(Context context, OrderConsultContract.CounselorDetail.CounselorDetailView mView) {
        super(context, mView);
    }

    @Override
    public void initModel() {
        orderConsult = new EvaluationModuleModel().new OrderConsult();
    }


    //咨询师详情
    @Override
    public void getCounselorDetailByPsyInfoId(int id, LifecycleProvider lifecycleProvider) {
        orderConsult.getCounselorDetail(id, lifecycleProvider, new Observer<CounselorDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CounselorDetailBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    mView.onCounselorDetailByPsyInfoId(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                } else {
                    mView.onCounselorDetailByPsyInfoId(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onCounselorDetailByPsyInfoId(null, IBaseView.ResultType.NET_ERROR, e.toString());

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

    //咨询师详情
    @Override
    public void getCounselorDetailByUserId(int id, LifecycleProvider lifecycleProvider) {
        orderConsult.getCounselorDetailByUserId(id, lifecycleProvider, new Observer<CounselorDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CounselorDetailBean bean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (bean.getCode() == Constant.NetCode.success2) {
                    mView.onCounselorDetailByUserId(bean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                } else {
                    mView.onCounselorDetailByUserId(bean, IBaseView.ResultType.SERVER_ERROR, bean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onCounselorDetailByUserId(null, IBaseView.ResultType.NET_ERROR, e.toString());

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


    //评价List
    @Override
    public void getUserEvaluateList(EvaluateListRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getUserEvaluateList(request, lifecycleProvider, new Observer<EvaluateListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EvaluateListBean detailBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (detailBean.getCode() == Constant.NetCode.success2) {
                    if (detailBean.getResult() == null) {
                        mView.onEvaluateList(detailBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onEvaluateList(detailBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onEvaluateList(detailBean, IBaseView.ResultType.SERVER_ERROR, detailBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onEvaluateList(null, IBaseView.ResultType.NET_ERROR, e.toString());

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


    //app新增评价
    @Override
    public void getAddEvaluate(AddEvaluateRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getAddEvaluate(request, lifecycleProvider, new Observer<BaseResponseBean2>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponseBean2 baseBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (baseBean.getCode() == Constant.NetCode.success2) {
                    if (baseBean.getResult() == null) {
                        mView.onAddEvaluate(baseBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onAddEvaluate(baseBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onAddEvaluate(baseBean, IBaseView.ResultType.SERVER_ERROR, baseBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onAddEvaluate(null, IBaseView.ResultType.NET_ERROR, e.toString());

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


    //评价详情
    @Override
    public void getEvaluateDetail(EvaluateListRequest request, LifecycleProvider lifecycleProvider) {
        orderConsult.getEvaluateDetail(request, lifecycleProvider, new Observer<EvaluateDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EvaluateDetailBean detailBean) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                if (detailBean.getCode() == Constant.NetCode.success2) {
                    if (detailBean.getResult() == null) {
                        mView.onEvaluateDetail(detailBean, IBaseView.ResultType.SERVER_NORMAL_DATANO, context.getString(R.string.base_module_data_null));
                    } else {
                        mView.onEvaluateDetail(detailBean, IBaseView.ResultType.SERVER_NORMAL_DATAYES, "");
                    }
                } else {
                    mView.onEvaluateDetail(detailBean, IBaseView.ResultType.SERVER_ERROR, detailBean.getErrMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null) {
                    //说明view已经销毁了,没必要再去显示了.也防止了空指针.
                    return;
                }
                mView.onEvaluateDetail(null, IBaseView.ResultType.NET_ERROR, e.toString());

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
