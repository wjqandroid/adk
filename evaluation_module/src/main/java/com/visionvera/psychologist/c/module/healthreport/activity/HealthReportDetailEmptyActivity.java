package com.visionvera.psychologist.c.module.healthreport.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.healthreport.fragment.HealthReportDetailEmptyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
* author:lilongfeng
* date:2019/12/6
* 描述:健康报告结果页-----无数据
*/

public class HealthReportDetailEmptyActivity extends BaseActivity {

    @BindView(R2.id.evaluation_module_health_report_detail_empty_viewpager)
    ViewPager mVP;

    @BindView(R2.id.tv_title)
    TextView tv_title;

    @BindView(R2.id.indicator_select)
    ImageView indicator_select;

    @BindView(R2.id.indicator_unselect)
    ImageView indicator_unselect;

    private List<HealthReportDetailEmptyFragment> mList=new ArrayList<>();

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,HealthReportDetailEmptyActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_health_report_detail_empty;
    }

    @Override
    protected void doYourself() {



        initView();

    }

    private void initView() {
        tv_title.setText("10月健康报告");

        HealthReportDetailEmptyFragment fragment1=HealthReportDetailEmptyFragment.newInstance(1);
        HealthReportDetailEmptyFragment fragment2=HealthReportDetailEmptyFragment.newInstance(2);

        mList.add(fragment1);
        mList.add(fragment2);

        mVP.setAdapter(new EmptyVPAdapter(getSupportFragmentManager()));
        mVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    indicator_select.setImageResource(R.drawable.evaluation_module_health_report_detail_indicator_select);
                    indicator_unselect.setImageResource(R.drawable.evaluation_module_health_report_detail_indicator_unselect);
                }else if (position==1){
                    indicator_select.setImageResource(R.drawable.evaluation_module_health_report_detail_indicator_unselect);
                    indicator_unselect.setImageResource(R.drawable.evaluation_module_health_report_detail_indicator_select);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class EmptyVPAdapter extends FragmentPagerAdapter {


        public EmptyVPAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

    @OnClick({R2.id.rl_back})
    public void onClick(View view){
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId=view.getId();
        if (viewId==R.id.rl_back){
            finish();
        }
    }


}
