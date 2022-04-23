package com.visionvera.library.base.mvp.presenter;

import android.content.Context;

import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.mvp.BaseMvpObserver;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public abstract class BasePresenter<T extends IBaseRetrofitView> implements IBasePresenter {
    public Context context;
    public T mView;

    private CompositeDisposable compositeDisposable;

    public BasePresenter(Context context, T mView) {
        this.context = context;
        this.mView = mView;
        initModel();
    }

    public abstract void initModel();

    /**
     * 返回 view
     */
    public T getmView() {
        return mView;
    }

    /**
     * 将被观察者和观察者建立订阅,并将此次请求加入管理列表,以便于在ondestory等中断开连接
     *
     * @param observable
     * @param observer
     */
    public <T extends BaseResponseBean3> void getData(Observable<T> observable, BaseMvpObserver<T> observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable
                .add(observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer));
    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    /**
     * 记得要在销毁时调用。防止内存泄漏
     */
    public void detachView() {
        if (mView != null) {
            mView = null;
            removeDisposable();
        }
    }
}
