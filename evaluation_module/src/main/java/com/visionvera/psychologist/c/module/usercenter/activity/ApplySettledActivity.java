package com.visionvera.psychologist.c.module.usercenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.base.activity.ProtocolActivity;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.activity.OrderConsultListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * author:haohuizhao
 * date:2020/11/09
 * 描述:申请入驻
 */

public class ApplySettledActivity extends BaseMVPActivity {

    private ImageView iv_back;
    private LinearLayout btn_apply;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ApplySettledActivity.class));
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_settled;
    }

    @Override
    protected void doYourself() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        btn_apply = (LinearLayout) findViewById(R.id.btn_apply);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户下载APP的链接
//                ToastUtils.showShort("暂未未开放");
                //服务端下载
                ProtocolActivity.startActivity(ApplySettledActivity.this, Constant.Url.request_base_url + Constant.WebUrl.download_server, "心理服务端下载");

            }
        });
    }

    @Override
    protected void initMVP() {

    }




}