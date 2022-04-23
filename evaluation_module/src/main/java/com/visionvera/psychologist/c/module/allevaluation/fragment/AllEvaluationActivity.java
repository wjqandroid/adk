package com.visionvera.psychologist.c.module.allevaluation.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.allevaluation.adapter.AllEvaluationPagerAdapter;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleRequestBean;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleResponseBean;
import com.visionvera.psychologist.c.module.allevaluation.contract.IContract;
import com.visionvera.psychologist.c.module.allevaluation.presenter.AllEvaluationFragmentPresenter;
import com.visionvera.psychologist.c.module.search.activity.SearchEvaluationActivity;
import com.visionvera.psychologist.c.widget.popup.SortTypePopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc 全部测试
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class AllEvaluationActivity extends BaseMVPLoadActivity<IContract.AllEvaluationFragment.View, AllEvaluationFragmentPresenter> {

    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R2.id.ll_evaluation_search)
    LinearLayout ll_evaluation_search;

    private List<TabTitleResponseBean.ResultBean> titles = new ArrayList<>();
    List<BaseFragment> fragmentList = new ArrayList<>();

    public int mSortedType = 1;
    public int mSort = 2;
    private SortTypePopup sortPopup;

    private void requestTabType() {
        showLoading();
        TabTitleRequestBean requestBean = new TabTitleRequestBean();
        mPresenter.getTabTitles(requestBean, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_fragment_all_evaluation;
    }

    @Override
    protected void doYourself() {
        updateStatuBar();
        requestTabType();
    }

    public void updateStatuBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.COLOR_WHITE_FFFEFE)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }


    private void setTabAndViewPager(List<TabTitleResponseBean.ResultBean> result) {
        if (result == null || result.size() == 0) {
            return;
        }
        titles.clear();
        fragmentList.clear();
        titles.addAll(result);

        for (int i = 0; i < titles.size(); i++) {
            fragmentList.add(new AllEvaluationItemFragment(titles.get(i).getId()));
        }

        AllEvaluationPagerAdapter adapter = new AllEvaluationPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(result.size());

        //自定义tab样式
        //Viewpager的监听（这个接听是为Tablayout专门设计的）
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);

                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView tv_name = customView.findViewById(R.id.tv_name);
                    tv_name.setTextColor(getResources().getColor(R.color.white));
                    tv_name.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    tv_name.setBackground(getResources().getDrawable(R.drawable.evaluation_module_shape_solid_ff3ca7ff_corner3));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView tv_name = customView.findViewById(R.id.tv_name);
                    tv_name.setTextColor(getResources().getColor(R.color.COLOR_GRAY_666666));
                    //设置不为加粗
                    tv_name.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_name.setBackground(getResources().getDrawable(R.drawable.evaluation_module_shape_solid_fff2f5fb_corner3));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < titles.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View customView = LayoutInflater.from(this).inflate(R.layout.evaluation_module_tab_item, null);
            tab.setCustomView(customView);
            TextView tv_name = customView.findViewById(R.id.tv_name);
            tv_name.setText(titles.get(i).getValue());
            if (0 == i) {
                tv_name.setTextColor(getResources().getColor(R.color.evaluation_module_tab_text_slelect_color));
                tv_name.setBackground(getResources().getDrawable(R.drawable.evaluation_module_shape_solid_ff3ca7ff_corner3));
            } else if (0 != i) {
                tv_name.setTextColor(getResources().getColor(R.color.evaluation_module_tab_text_unslelect_color));
                tv_name.setBackground(getResources().getDrawable(R.drawable.evaluation_module_shape_solid_fff2f5fb_corner3));
            }
            tabLayout.addTab(tab);

        }
    }

    private void showPopup() {
        if (sortPopup != null && sortPopup.isShow()) {
            return;
        }
        sortPopup = (SortTypePopup) new XPopup.Builder(this)
                .atView(ll_evaluation_search)
                .asCustom(new SortTypePopup(this, mSortedType, mSort, new SortTypePopup.SortTypePopupListener() {
                    @Override
                    public void setSortTypePopupListener(int sortedType, int sort) {
                        sortPopup.dismiss();
                        mSortedType = sortedType;

                        if (sortedType == 2) {
                            mSort = (mSort == 2 ? 1 : 2);
                        } else {
                            mSort = 2;
                        }

                        ((AllEvaluationItemFragment) fragmentList.get(tabLayout.getSelectedTabPosition()))
                                .requestChartList(true, mSortedType, mSort);
                    }
                }));


        sortPopup.show();
    }

    @OnClick({R2.id.sort_layout, R2.id.ll_evaluation_search, R2.id.evaluation_module_iv_back})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();

        if (viewId == R.id.sort_layout) {
            showPopup();
        } else if (viewId == R.id.ll_evaluation_search) {
            startActivity(new Intent(this, SearchEvaluationActivity.class));
        } else if (viewId == R.id.evaluation_module_iv_back) {
            finish();
        }
    }

    @Override
    protected void initMVP() {
        mView = new IContract.AllEvaluationFragment.View() {
            @Override
            public void onGetTabTitles(TabTitleResponseBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        showError(getString(R.string.base_module_net_error));
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        showError(getString(R.string.base_module_data_load_error));
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        showNormal();
                        setTabAndViewPager(responseBean.getResult());
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new AllEvaluationFragmentPresenter(this, mView);
    }

    @Override
    protected void onReload() {
        requestTabType();
    }
}
