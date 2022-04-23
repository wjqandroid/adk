package com.visionvera.psychologist.c.module.allevaluation.adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.visionvera.library.base.BaseFragment;
import com.visionvera.psychologist.c.module.allevaluation.bean.TabTitleResponseBean;

import java.util.List;

/**
 * @author 刘传政
 * @date 2018/8/13 0013 16:32
 * QQ:1052374416
 * telephone:18501231486
 * 作用:
 * 注意事项:
 */
public class AllEvaluationPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragmentList;
    private List<TabTitleResponseBean.ResultBean> titles;

    public AllEvaluationPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<TabTitleResponseBean.ResultBean> titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getValue();
    }
}
