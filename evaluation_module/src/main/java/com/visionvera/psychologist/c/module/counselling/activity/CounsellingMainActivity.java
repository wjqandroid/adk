package com.visionvera.psychologist.c.module.counselling.activity;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.visionvera.library.base.BaseActivity;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.CounsellingFragment;

/**
 * @author 刘传政
 * @date 2020-01-02 14:49
 * QQ:1052374416
 * 电话:18501231486
 * 作用: 预约诊疗主页面
 * 注意事项:
 */
public class CounsellingMainActivity extends BaseActivity {

    //心理咨询
    public CounsellingFragment counsellingFragment;
    public FragmentTransaction transaction;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_counselling_main;
    }

    @Override
    protected void doYourself() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        initView();
    }


    private void initView() {
        transaction = fragmentManager.beginTransaction();
        if (counsellingFragment == null) {
            counsellingFragment = CounsellingFragment.newInstance();
            transaction.add(R.id.rl_container, counsellingFragment, CounsellingFragment.class.getSimpleName());
        } else {
            transaction.show(counsellingFragment);
        }
        transaction.commitAllowingStateLoss();
    }

}
