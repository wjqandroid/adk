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
 * 作用: 增加懒加载方案.可用可不用
 * 注意事项:
 */
public abstract class BaseLoadFragment extends BaseFragment {
    /**
     * 处理页面加载中、页面加载失败、页面没数据
     */
    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;
    public static final int EMPTY_STATE = 3;
    public static final int EMPTY_STATE_2 = 4;
    public static final int EMPTY_STATE_3 = 5;
    /**
     * 当前状态
     */
    private int currentState = NORMAL_STATE;

    private View mErrorView;
    private View mLoadingView;
    private View mEmptyView;
    private ViewGroup mNormalView;
    public TextView tv_reload;
    private View rv_consultation_treatmentempty;
    private View rl_treatmentempty;

    @Override
    public void initInnerThing() {
        super.initInnerThing();
        initLoadUI();
    }


    protected void initLoadUI() {
        if (getView() == null) {
            return;
        }
        mNormalView = getView().findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException("The subclass of RootActivity must contain a View named 'normal_view'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("normal_view's ParentView should be a ViewGroup.");
        }
        ViewGroup parent = (ViewGroup) mNormalView.getParent();

        View.inflate(getActivity(), R.layout.base_module_view_loading, parent);
        View.inflate(getActivity(), R.layout.base_module_view_error, parent);
        View.inflate(getActivity(), R.layout.base_module_view_empty, parent);
        View.inflate(getActivity(), R.layout.base_module_view_consultation_treatmentempty, parent);//咨询诊疗无数据页面
        View.inflate(getActivity(), R.layout.base_module_view_measurement, parent);//我的测评无数据页面

        mLoadingView = parent.findViewById(R.id.loading_group);
        mErrorView = parent.findViewById(R.id.error_group);
        mEmptyView = parent.findViewById(R.id.empty_group);
        tv_reload = mErrorView.findViewById(R.id.tv_reload);
        rv_consultation_treatmentempty = parent.findViewById(R.id.consultation_treatmentempty);
        rl_treatmentempty = parent.findViewById(R.id.rl_treatmentempty);
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
        rv_consultation_treatmentempty.setVisibility(View.GONE);
        rl_treatmentempty.setVisibility(View.GONE);

        mNormalView.setVisibility(View.VISIBLE);
    }


    public void setLoadText(String text) {
        tv_reload.setText(text);
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

    public void showConsultationTreatmentemptyEmpty() {
        if (currentState == EMPTY_STATE_2) {
            return;
        }
        hideCurrentView();
        currentState = EMPTY_STATE_2;
        rv_consultation_treatmentempty.setVisibility(View.VISIBLE);

    }

    public void showMeasurement() {
        if (currentState == EMPTY_STATE_3) {
            return;
        }
        hideCurrentView();
        currentState = EMPTY_STATE_3;
        rl_treatmentempty.setVisibility(View.VISIBLE);
    }


    /**
     * 针对某些需要修改布局的情况。
     */
    public View getErrorView() {
        return mErrorView;
    }

    public View getEmptyView() {
        return mEmptyView;
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
            case EMPTY_STATE_2:
                rv_consultation_treatmentempty.setVisibility(View.GONE);
                break;
            case EMPTY_STATE_3:
                rl_treatmentempty.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }


}
