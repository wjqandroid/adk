package com.visionvera.psychologist.c.module.ordertreatment.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;
import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.util.EmojiFilter;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.adapter.CommonFragmentPageAdapter;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SearchDoctorBean;
import com.visionvera.psychologist.c.module.ordertreatment.contract.IContract;
import com.visionvera.psychologist.c.module.ordertreatment.fragment.SearchDoctorForDoctorFragment;
import com.visionvera.psychologist.c.module.ordertreatment.fragment.SearchDoctorForHospitalFragment;
import com.visionvera.psychologist.c.module.ordertreatment.presenter.SearchDoctorPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
* author:lilongfeng
* date:2020/1/3
* 描述:搜索医生页面，有两个tab页面
*/


public class SearchDoctorActivity extends BaseMVPActivity<IContract.SearchDoctor.SearchDoctorView, SearchDoctorPresenter> {

    @BindView(R2.id.search_doctor_tablayout)
    TabLayout mTabLayout;

    @BindView(R2.id.normal_view)
    ViewPager vp;

    @BindView(R2.id.et_content)
    public EditText et_content;

    private List<String> tabTitles = new ArrayList<>();
    private List<BaseFragment> fragments = new ArrayList<>();

    private SearchDoctorForDoctorFragment doctorFragment;
    private SearchDoctorForHospitalFragment hospitalFragment;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,SearchDoctorActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_search_doctor;
    }

    @Override
    protected void doYourself() {
        initTabViewPager();

        initEdit();
    }

    private void initEdit() {
        et_content.setFilters(new InputFilter[]{new EmojiFilter()});
        et_content.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //软盘搜索键
                SearchMethod();
                return true;
            }
            return false;
        });
    }

    /**
     * 点击搜索的操作
     */
    private void SearchMethod() {
        KeyboardUtils.hideSoftInput(this);

        showLoadingDialog();

        SearchDoctorRequest request = new SearchDoctorRequest();
        request.setIllnessOrHospitalName(et_content.getText().toString().trim());
        mPresenter.getSearchDoctor(request, this);
    }


    private void initTabViewPager() {
        tabTitles.add("医生");
        tabTitles.add("医院");

        doctorFragment = SearchDoctorForDoctorFragment.newInstance();
        hospitalFragment = SearchDoctorForHospitalFragment.newInstance();

        fragments.add(doctorFragment);
        fragments.add(hospitalFragment);

        vp.setAdapter(new CommonFragmentPageAdapter(getSupportFragmentManager(), fragments, tabTitles));

//        mTabLayout.setupWithViewPager(vp);

        //自定义tab样式
        //Viewpager的监听（这个接听是为Tablayout专门设计的）
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        for (int i = 0; i < tabTitles.size(); i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            View customView = LayoutInflater.from(this).inflate(R.layout.evaluation_module_tab_item2, null);
            tab.setCustomView(customView);
            TextView tv_name = customView.findViewById(R.id.tv_name);
            tv_name.setText(tabTitles.get(i));
            if (0 == i) {
                tv_name.setTextColor(getResources().getColor(R.color.evaluation_module_tab_text_slelect_color));
                tv_name.setBackground(getResources().getDrawable(R.drawable.evaluation_module_shape_solid_ff3ca7ff_corner3));
            } else if (0 != i) {
                tv_name.setTextColor(getResources().getColor(R.color.evaluation_module_tab_text_unslelect_color));
                tv_name.setBackground(getResources().getDrawable(R.drawable.evaluation_module_shape_solid_fff2f5fb_corner3));
            }
            mTabLayout.addTab(tab);

        }

    }

    @Override
    protected void initMVP() {
        mView=new IContract.SearchDoctor.SearchDoctorView() {
            @Override
            public void onSearchDoctor(SearchDoctorBean bean, ResultType resultType, String errorMsg) {
                KeyboardUtils.hideSoftInput(activity);
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                        //也就是result为null
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        doctorFragment.setDataFromActivity(bean.getResult().getStaffInfoList());
                        hospitalFragment.setDataFromActivity(bean.getResult().getHospitalInfoList());
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter=new SearchDoctorPresenter(this,mView);
    }

    public static class SearchDoctorRequest {
        private String illnessOrHospitalName;

        public String getIllnessOrHospitalName() {
            return illnessOrHospitalName;
        }

        public void setIllnessOrHospitalName(String illnessOrHospitalName) {
            this.illnessOrHospitalName = illnessOrHospitalName;
        }
    }

    @OnClick({R2.id.evaluation_module_search_button,R2.id.iv_delete,R2.id.search_doctor_iv_back})
    public void onClick(View view){
        if(OneClickUtils.isFastClick()){
            return;
        }
        int viewId=view.getId();
        if (viewId==R.id.evaluation_module_search_button){
            SearchMethod();
        }else if (viewId==R.id.iv_delete){
            et_content.setText("");
            doctorFragment.setDataFromActivity(null);
            hospitalFragment.setDataFromActivity(null);
        }else if (viewId==R.id.search_doctor_iv_back){
            finish();
        }
    }
}
