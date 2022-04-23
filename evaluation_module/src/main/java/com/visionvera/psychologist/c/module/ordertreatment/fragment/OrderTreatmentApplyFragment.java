package com.visionvera.psychologist.c.module.ordertreatment.fragment;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.base.Constant;
import com.visionvera.library.widget.adapter.CommonFragmentPageAdapter;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author:lilongfeng
 * date:2020/1/6
 * 描述:预约诊疗的申请列表
 */

public class OrderTreatmentApplyFragment extends BaseFragment {

    @BindView(R2.id.evaluation_module_fragment_order_consult_tab)
    TabLayout tabLayout;

    @BindView(R2.id.evaluation_module_fragment_order_consult_viewpager)
    ViewPager vp;

    //顶部tdb 状态对应
    //二期页面顺序 18：待付款   2：待受理   14：待诊疗    15：已完成    7：已取消    8：已驳回   100：已关闭（取消、作废两种状态）
    private int[] appStatusArray = {18, 2, 14, 15, 7, 8, 100};
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_order_consult;
    }

    @Override
    protected void initYourself() {
        initView();
    }

    private void initView() {
        //二期页面顺序 18：待付款   2：待受理      14：待诊疗     15：已完成    7：已取消    8：已驳回   100：已关闭（取消、作废两种状态）
        titleList.add("待付款");
        titleList.add("待受理");
        titleList.add("待诊疗");
        titleList.add("已完成");
        titleList.add("已取消");
        titleList.add("已驳回");
        titleList.add("已关闭");

        for (int i = 0; i < titleList.size(); i++) {
            fragmentList.add(new OrderTreatmentItemFragment(appStatusArray[i], Constant.IntentKey.apply));
        }

        CommonFragmentPageAdapter mAdapter = new CommonFragmentPageAdapter(getChildFragmentManager(), fragmentList, titleList);
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(7);


        //自定义tab样式
        //Viewpager的监听（这个接听是为Tablayout专门设计的）
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                vp.setCurrentItem(position);

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
        for (int i = 0; i < titleList.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View customView = LayoutInflater.from(this.getContext()).inflate(R.layout.evaluation_module_tab_item, null);
            tab.setCustomView(customView);
            TextView tv_name = customView.findViewById(R.id.tv_name);
            tv_name.setText(titleList.get(i));
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
}
