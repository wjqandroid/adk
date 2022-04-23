package com.visionvera.psychologist.c.module.myevaluation;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ConvertUtils;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc 我的测试
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class MyEvaluationActivity extends BaseActivity {
    @BindView(R2.id.evaluation_module_tv_title)
    TextView tvTitle;

    @BindView(R2.id.tab_evaluation_test)
    TabLayout mTabLayout;

    @BindView(R2.id.vp_evaluation_test)
    ViewPager mViewPager;

    private List<BaseFragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private TabPageAdapter tabAdapter;

    private PendingTestFragment pendingTestFragment;
    private TestedFragment testedFragment;
    private ExpiredFragment expiredFragment;
    private MyEvaluationIntentBean myEvaluationIntentBean;

    /**
     *
     * @param context
     * @param intentBean   根据type值做不同的操作判断
     */
    public static void startActivity(Context context,MyEvaluationIntentBean intentBean){
        context.startActivity(new Intent(context,MyEvaluationActivity.class).putExtra("intentBean",intentBean));
    }

    public static class MyEvaluationIntentBean implements Serializable {
        /**
         * 1.跳转待测试
         * 2.跳转已测试
         * 3.跳转已过期
         */
        private int type;

        public MyEvaluationIntentBean(int type) {
            this.type=type;
        }
    }

    /**
     * tab点击监听
     */
    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (OneClickUtils.isFastClick()) {
                return;
            }
            int pos = (int) view.getTag();
            TabLayout.Tab tab = mTabLayout.getTabAt(pos);
            if (tab != null) {
                tab.select();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_fragment_my;
    }

    @Override
    protected void doYourself() {
        tvTitle.setText(R.string.evaluation_module_mytest);
        initIntentBean();
        updateStatuBar();
        initViewPager();

    }

    private void initIntentBean() {
        myEvaluationIntentBean = (MyEvaluationIntentBean) getIntent().getSerializableExtra("intentBean");
    }

    public void updateStatuBar(){
            ImmersionBar.with(this)
                    .statusBarColor(R.color.COLOR_WHITE_FFFEFE)
                    .statusBarDarkFont(true)
                    .fitsSystemWindows(true)
                    .init();
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        Resources rs = getResources();
        titles.add(rs.getString(R.string.evaluation_module_pending_test));
        titles.add(rs.getString(R.string.evaluation_module_tested));
        titles.add(rs.getString(R.string.evaluation_module_expired));


        pendingTestFragment = PendingTestFragment.newInstance();
        testedFragment = TestedFragment.newInstance();
        expiredFragment = ExpiredFragment.newInstance();

        fragmentList.add(pendingTestFragment);
        fragmentList.add(testedFragment);
        fragmentList.add(expiredFragment);

        tabAdapter = new TabPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabAdapter);
        tabAdapter.notifyDataSetChanged();
        mViewPager.setOffscreenPageLimit(1);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                mViewPager.setCurrentItem(position);

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
            TabLayout.Tab tab = mTabLayout.newTab();
            View customView = LayoutInflater.from(this).inflate(R.layout.evaluation_module_tab_item, null);
            tab.setCustomView(customView);
            TextView tv_name = customView.findViewById(R.id.tv_name);
            tv_name.setMinWidth(ConvertUtils.dp2px(80));
            tv_name.setText(titles.get(i));
            if (0 == i) {
                tv_name.setTextColor(getResources().getColor(R.color.evaluation_module_tab_text_slelect_color));
                tv_name.setBackground(getResources().getDrawable(R.drawable.evaluation_module_shape_solid_ff3ca7ff_corner3));
            } else if (0 != i) {
                tv_name.setTextColor(getResources().getColor(R.color.evaluation_module_tab_text_unslelect_color));
                tv_name.setBackground(getResources().getDrawable(R.drawable.evaluation_module_shape_solid_fff2f5fb_corner3));
            }
            mTabLayout.addTab(tab);

        }

        if (myEvaluationIntentBean.type == 2) {
            mViewPager.setCurrentItem(1);
        }

    }



    private class TabPageAdapter extends FragmentPagerAdapter {
        public TabPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public long getItemId(int position) {
            //确保返回的值唯一即可
            return fragmentList.get(position).hashCode();
        }

        @Override
        public int getCount() {
            if (fragmentList != null && fragmentList.size() > 0) {
                return fragmentList.size();
            } else {
                return 0;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            //此Item不再显示
            return POSITION_NONE;
        }
    }


    /**
     * 请求数据
     * 重复请求了两次数据。删除掉。
     */
    /*private void refreshData(int position) {
        switch (position) {
            case 0:
                if (pendingTestFragment != null) {
                    pendingTestFragment.requestPendingTest(true);
                }
                break;

            case 1:
                if (testedFragment != null) {
                    testedFragment.requestTested(true);
                }
                break;

            case 2:
                if (expiredFragment != null) {
                    expiredFragment.requestExpired(true);
                }
                break;

            default:
                break;
        }
    }*/

    @OnClick({R2.id.evaluation_module_iv_back})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_iv_back) {
            finish();
        }
    }

}
