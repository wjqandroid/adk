package com.visionvera.library.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 刘传政
 * @date 2018/12/16 0016 11:48
 * QQ:1052374416
 * telephone:18501231486
 * 作用:
 * 注意事项:
 */
public abstract class BaseFragment extends RxFragment implements View.OnClickListener {
    private Unbinder unbinder;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initInnerThing();
        initYourself();
        isViewCreated = true;
        lazyLoad();
    }

    /**
     * 在doYourself之前做一些特殊的事
     */
    public void initInnerThing() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResID(), container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            lazyLoadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;

        }
    }

    /**
     * 每次对用户可见（create之后）都会触发懒加载
     * 使用者可以重写它等待触发。也可以什么都不做，主动调用别的请求方法
     */
    protected abstract void lazyLoadData();

    /**
     * 获取 布局信息
     *
     * @return
     */
    protected abstract int getLayoutResID();

    protected abstract void initYourself();

    @Override
    public void onClick(View v) {
        
    }

    
}
