package com.visionvera.library.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.visionvera.library.R;
import com.visionvera.library.util.OneClickUtils;


/**
 * @author 刘传政
 * @date 2018/12/16 0016 11:55
 * QQ:1052374416
 * telephone:18501231486
 * 作用:
 * 注意事项:
 */
public abstract class BaseLoadActivity extends BaseActivity {
    public int page = 0;//当前页数. 因为此项目接口页数都是从1开始
    /**
     * 处理页面加载中、页面加载失败、页面没数据
     */
    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;
    public static final int EMPTY_STATE = 3;
    /**
     * 当前状态
     */
    private int currentState = NORMAL_STATE;

    private View mErrorView;
    private View mLoadingView;
    private View mEmptyView;
    private ViewGroup mNormalView;


    @Override
    public void initInnerThing() {
        super.initInnerThing();
        initLoadUI();
    }

    protected void initLoadUI() {

        mNormalView = findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException("The subclass of RootActivity must contain a View named 'normal_view'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("normal_view's ParentView should be a ViewGroup.");
        }
        ViewGroup parent = (ViewGroup) mNormalView.getParent();

        View.inflate(activity, R.layout.base_module_view_loading, parent);
        View.inflate(activity, R.layout.base_module_view_error, parent);
        View.inflate(activity, R.layout.base_module_view_empty, parent);

        mLoadingView = parent.findViewById(R.id.loading_group);
        mErrorView = parent.findViewById(R.id.error_group);
        mEmptyView = parent.findViewById(R.id.empty_group);
        TextView tv_reload = mErrorView.findViewById(R.id.tv_reload);
        TextView tv_reload_empty = mEmptyView.findViewById(R.id.tv_reload);



        tv_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                onReload();
            }
        });
        tv_reload_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                onReload();
            }
        });

        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);

        mNormalView.setVisibility(View.VISIBLE);
    }

    /**
     * mErrorView的重新加载点击事件
     */
    protected abstract void onReload();

    public void showNormal() {
        if (currentState == NORMAL_STATE) {
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    public void showError(String err) {
        if (currentState == ERROR_STATE) {
            return;
        }
        hideCurrentView();
        currentState = ERROR_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        if (currentState == LOADING_STATE) {
            return;
        }
        hideCurrentView();
        currentState = LOADING_STATE;
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void showEmpty() {
        if (currentState == EMPTY_STATE) {
            return;
        }
        hideCurrentView();
        currentState = EMPTY_STATE;
        mEmptyView.setVisibility(View.VISIBLE);
    }


    private void hideCurrentView() {
        switch (currentState) {
            case NORMAL_STATE:
                if (mNormalView == null) {
                    return;
                }
                mNormalView.setVisibility(View.GONE);
                break;
            case LOADING_STATE:
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                mErrorView.setVisibility(View.GONE);
                break;
            case EMPTY_STATE:
                mEmptyView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

}
