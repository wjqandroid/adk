package com.visionvera.library.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.visionvera.library.base.mvp.presenter.BasePresenter;
import com.visionvera.library.base.mvp.view.IBaseRetrofitView;


/**
 * 有网络请求的基类。封装一些mvp的处理
 *
 * @param <T> 关联哪个view
 * @param <K> 关联哪个presenter
 */
public abstract class BaseMVPActivity<T extends IBaseRetrofitView, K extends BasePresenter> extends BaseActivity {
    public T mView;
    public K mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        activity = this;
        initMVP();
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建view，不要用实现的方式，要new一个，方便直接查看
     * 创建presenter
     * 时机是展示ui之前。因为这里只涉及到创建一些mvp类。不会影响功能。
     */
    protected abstract void initMVP();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
