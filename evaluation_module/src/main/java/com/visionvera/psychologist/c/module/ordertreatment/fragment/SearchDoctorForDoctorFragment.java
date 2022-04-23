package com.visionvera.psychologist.c.module.ordertreatment.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.ordertreatment.activity.NewOrderTreatmentDetailActivity;
import com.visionvera.psychologist.c.module.ordertreatment.adapter.SearchDoctorAdapter;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SearchDoctorBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
* author:lilongfeng
* date:2020/1/3
* 描述:搜索医生的医生fragment页面
*/

public class SearchDoctorForDoctorFragment extends BaseFragment {

    @BindView(R2.id.evaluation_module_search_doctor_recyclerview)
    RecyclerView mRecy;

    List<SearchDoctorBean.ResultBean.StaffInfoListBean> mDoctorList=new ArrayList<>();
    private SearchDoctorAdapter mAdapter;

    public static SearchDoctorForDoctorFragment newInstance(){
        SearchDoctorForDoctorFragment fragment=new SearchDoctorForDoctorFragment();
        return fragment;
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_search_doctor;
    }

    @Override
    protected void initYourself() {
        initView();
    }

    private void initView() {
        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SearchDoctorAdapter(mDoctorList,getActivity());
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (OneClickUtils.isFastClick()) {
                return;
            }
            NewOrderTreatmentDetailActivity.IntentBean intentBean
                    = new NewOrderTreatmentDetailActivity.IntentBean(mDoctorList.get(position).getId(), mDoctorList.get(position).getUserId());

            NewOrderTreatmentDetailActivity.startActivity(getActivity(), intentBean);
        });
    }

    /**
     *
     * @param doctorList     搜索返回的医生列表信息
     */
    public void setDataFromActivity(List<SearchDoctorBean.ResultBean.StaffInfoListBean> doctorList){
        mDoctorList.clear();
        if (doctorList!=null && doctorList.size()>0){
            mDoctorList.addAll(doctorList);
        }
        mAdapter.notifyDataSetChanged();
    }
}
