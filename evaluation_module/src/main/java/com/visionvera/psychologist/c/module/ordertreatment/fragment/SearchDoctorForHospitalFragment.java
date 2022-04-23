package com.visionvera.psychologist.c.module.ordertreatment.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.ordertreatment.activity.SubjectListActivity;
import com.visionvera.psychologist.c.module.ordertreatment.adapter.SearchHospitalAdapter;
import com.visionvera.psychologist.c.module.ordertreatment.bean.SearchDoctorBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author:lilongfeng
 * date:2020/1/3
 * 描述:搜索医生的医院fragment页面
 */

public class SearchDoctorForHospitalFragment extends BaseFragment {

    @BindView(R2.id.evaluation_module_search_doctor_recyclerview)
    RecyclerView mRecy;


    private List<SearchDoctorBean.ResultBean.HospitalInfoListBean> mDataList=new ArrayList<>();
    private SearchHospitalAdapter mAdapter;

    public static SearchDoctorForHospitalFragment newInstance() {
        SearchDoctorForHospitalFragment fragment = new SearchDoctorForHospitalFragment();

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

        mAdapter = new SearchHospitalAdapter(mDataList,getActivity());

        mRecy.setAdapter(mAdapter);
    }

    public void setDataFromActivity(List<SearchDoctorBean.ResultBean.HospitalInfoListBean> hospitalList){
        mDataList.clear();
        if (hospitalList!=null && hospitalList.size()>0){
            mDataList.addAll(hospitalList);
        }
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (OneClickUtils.isFastClick()) {
                return;
            }

            SubjectListActivity.IntentBean intentBean = new SubjectListActivity.IntentBean(mAdapter.getData().get(position).getId() + "",
                    mAdapter.getData().get(position).getHospitalName());

            SubjectListActivity.startActivity(getActivity(), intentBean);
        });
    }
}
