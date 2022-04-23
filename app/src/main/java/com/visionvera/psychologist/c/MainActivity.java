package com.visionvera.psychologist.c;

import android.widget.TextView;

import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.util.OneClickUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

//    @BindView(R.id.tv_ceping)
//    TextView tvCeping;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void doYourself() {

    }


    @OnClick(R.id.tv_ceping)
    public void onViewClicked() {
        if (OneClickUtils.isFastClick()) {
            return;
        }
    }
}
