package com.visionvera.psychologist.c.module.healthreport.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.ordertreatment.activity.OrderTreatmentMainActivity;

import butterknife.OnClick;

/**
* author:lilongfeng
* date:2019/12/4
* 描述:自我探索的详情页
*/

public class ExploreMySelfDetailActivity extends BaseActivity {

    public static void startActivity(Context context){
//        ToastUtils.showShort("");
        context.startActivity(new Intent(context,ExploreMySelfDetailActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_explore_myself_detail;
    }

    @Override
    protected void doYourself() {

    }

    @OnClick({R2.id.rl_back, R2.id.evaluation_module_explore_myself_detail_consult})
    public void onClick(View view){
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId=view.getId();
        if (viewId == R.id.rl_back) {
            finish();
        } else if (viewId == R.id.evaluation_module_explore_myself_detail_consult) {
            startActivity(new Intent(ExploreMySelfDetailActivity.this, OrderTreatmentMainActivity.class));
//            ToastUtils.showLong(getResources().getString(R.string.evaluation_module_not_develop));
        }
    }
}
