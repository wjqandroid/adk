package com.visionvera.psychologist.c.module.search.activity;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.greendao.GreenDaoManager;
import com.visionvera.library.greendao.SearchHistoryDBBean;
import com.visionvera.library.util.EmojiFilter;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.allevaluation.bean.EvaluationChatListResponseBean;
import com.visionvera.psychologist.c.module.search.bean.DiscoverRequestBean;
import com.visionvera.psychologist.c.module.search.bean.DiscoverResponseBean;
import com.visionvera.psychologist.c.module.search.contract.IContract;
import com.visionvera.psychologist.c.module.search.fragment.SearchInputFragment;
import com.visionvera.psychologist.c.module.search.fragment.SearchResultFragment;
import com.visionvera.psychologist.c.module.search.presenter.SearchEvaluationActivityPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 刘传政
 * @date 2019-10-29 17:13
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class SearchEvaluationActivity extends BaseMVPActivity<IContract.SearchEvaluationActivity.View, SearchEvaluationActivityPresenter> {
    public SearchInputFragment searchInputFragment;
    public SearchResultFragment searchResultFragment;
    BaseFragment currentFragment;
    @BindView(R2.id.et_content)
    public EditText etContent;
    @BindView(R2.id.iv_delete)
    public ImageView iv_delete;


    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_search_evaluation;
    }

    @Override
    protected void doYourself() {
        initView();
        switchFragment(1);
        switchFragment(0);
        requestDiscover();
    }

    private void initView() {
        etContent.setFilters(new InputFilter[]{new EmojiFilter()});
        etContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //软盘搜索键
                    if (etContent.getText().toString().trim().isEmpty()) {
                        ToastUtils.showShort("请输入搜索内容");
                    } else {
                        SearchHistoryDBBean searchHistoryDBBean = new SearchHistoryDBBean();
                        searchHistoryDBBean.discoverValue = etContent.getText().toString().trim();
                        GreenDaoManager.getInstance().insert(searchHistoryDBBean);
                        switchFragment(1);
                        searchResultFragment.requestChartList(true,
                                etContent.getText().toString().trim());
                        KeyboardUtils.hideSoftInput(activity);
                    }
                    return true;
                }
                return false;
            }
        });
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0) {
                    iv_delete.setVisibility(View.INVISIBLE);
                    switchFragment(0);
                } else {
                    iv_delete.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * hide和show切换Fragment
     */
    public void switchFragment(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                if (searchInputFragment == null) {
                    searchInputFragment = SearchInputFragment.newInstance();
                }
                fragment = searchInputFragment;
                break;
            case 1:
                if (searchResultFragment == null) {
                    searchResultFragment = SearchResultFragment.newInstance();
                }
                fragment = searchResultFragment;
                break;
        }
        if (fragment == null) {
            return;
        }
        if (fragment != currentFragment) {
            if (!fragment.isAdded()) {
                if (currentFragment == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fl_content, fragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().hide(currentFragment)
                            .add(R.id.fl_content, fragment).commit();
                }

            } else {
                getSupportFragmentManager().beginTransaction().hide(currentFragment)
                        .show(fragment).commit();
            }
            currentFragment = fragment;
        }

    }

    @Override
    protected void initMVP() {
        mView = new IContract.SearchEvaluationActivity.View() {
            @Override
            public void onGetDiscover(DiscoverResponseBean responseBean, ResultType resultType, String errorMsg) {

                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        if (searchInputFragment != null) {
                            searchInputFragment.showDiscover(responseBean.getResult());
                        }

                        break;
                }
            }

            @Override
            public void onGetEvaluationChatList(boolean isRefresh, EvaluationChatListResponseBean responseBean, ResultType resultType, String errorMsg) {
                searchResultFragment.showEvaluationChatList(isRefresh, responseBean, resultType, errorMsg);
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new SearchEvaluationActivityPresenter(activity, mView);
    }

    private void requestDiscover() {
        DiscoverRequestBean requestBean = new DiscoverRequestBean();
        mPresenter.getDiscover(requestBean, this);
    }

    @OnClick({R2.id.iv_delete, R2.id.tv_search})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.iv_delete) {
            etContent.setText("");
        } else if (viewId == R.id.tv_search) {
            if (etContent.getText().toString().trim().isEmpty()) {
                ToastUtils.showShort("请输入搜索内容");
                return;
            }
            KeyboardUtils.hideSoftInput(activity);
            SearchHistoryDBBean searchHistoryDBBean = new SearchHistoryDBBean();
            searchHistoryDBBean.discoverValue = etContent.getText().toString().trim();
            GreenDaoManager.getInstance().insert(searchHistoryDBBean);
            switchFragment(1);
            searchResultFragment.requestChartList(true,
                    etContent.getText().toString().trim());
        }
    }


}
