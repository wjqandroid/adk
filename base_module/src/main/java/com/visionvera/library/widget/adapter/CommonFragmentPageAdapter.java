package com.visionvera.library.widget.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.visionvera.library.base.BaseFragment;

import java.util.List;

/**
* author:lilongfeng
* date:2019/12/25
* 描述:公共的viewpager的fragment适配器
*/

public class CommonFragmentPageAdapter extends FragmentPagerAdapter {

    List<BaseFragment> mFragmentList;
    List<String> mTitleList;
    public CommonFragmentPageAdapter(@NonNull FragmentManager fm, List<BaseFragment> fragmentList,List<String> titleList) {
        super(fm);
        mFragmentList=fragmentList;
        mTitleList=titleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
