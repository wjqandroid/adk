package com.visionvera.psychologist.c.module.home.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.home.adapter.PolicyAssemblyAdapter;
import com.visionvera.psychologist.c.module.home.bean.LocalCustomBean;
import com.visionvera.psychologist.c.utils.SpacesItemDecoration;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


//政策汇编列表
//跳转浏览word、pdf
//2021.09.26

public class PolicyAssemblyActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    private PolicyAssemblyAdapter policyAssemblyAdapter;
    private TextView evaluation_module_tv_title;
    private ImageView btn_back;
    //列表
    private ArrayList<LocalCustomBean> customBannerList = new ArrayList<>();
    //当前
    private int mPosition;
    private LocalCustomBean localCustomBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_assembly);

        ininView();
        initData();
    }

    private void ininView() {
        customBannerList.add(new LocalCustomBean("中共中央国务院印发《“健康中国2030”规划纲要》", "https://slyl-mhsp-1301295327.cos.ap-beijing.myqcloud.com/fronted/huizhou/中共中央%20国务院印发《“健康中国2030”规划纲要》.pdf", 0, 0));
        customBannerList.add(new LocalCustomBean("10部委关于印发全国社会心理服务体系建设试点工作方案的通知", "http://mhsp-cdn.51vision.com//fronted/huizhou/10部委关于印发全国社会心理服务体系建设试点工作方案的通知2018.11.19.pdf", 0, 0));
        customBannerList.add(new LocalCustomBean("关于印发《广东省基层综治中心社会心理服务站（室）名称和标识》的通知", "http://103.78.124.74/2Q2W001800DE75BD1C29842F614237C28D287823584A_unknown_4EA096C5EEEC07647FBA590688FEC32CC4AFB7D8_4/mhsp-cdn.51vision.com/fronted/huizhou/关于印发《广东省基层综治中心社会心理服务站（室）名称和标识》的通知0.pdf", 3, 0));
        customBannerList.add(new LocalCustomBean("关于在全省基层综治中心建立社会心理服务站（室）的指导意见（粤政发（2020）4号）", "http://mhsp-cdn.51vision.com/fronted/huizhou/关于在全省基层综治中心建立社会心理服务站（室）的指导意见（粤政发（2020）4号）2020.4.9.pdf", 0, 0));
        customBannerList.add(new LocalCustomBean("惠市卫〔2020〕164号++关于印发惠州市加强社会心理服务体系建设实施意见的通知", "http://mhsp-cdn.51vision.com/fronted/huizhou/惠市卫〔2020〕164号%2B%2B关于印发惠州市加强社会心理服务体系建设实施意见的通知0.pdf", 0, 0));
        customBannerList.add(new LocalCustomBean("惠财行函[2020]128号+（市委政法委）关于征求《“平安惠州”社会心理服务体系建设之健康服务社项目试点方案》（征求意见稿）意见的复函", "http://mhsp-cdn.51vision.com/fronted/huizhou/惠财行函%5B2020%5D128号%2B（市委政法委）关于征求《“平安惠州”社会心理服务体系建设之健康服务社项目试点方案》（征求意见稿）意见的复函0.pdf", 0, 0));
        customBannerList.add(new LocalCustomBean("联合办理《广东省卫生健康委等9部门转发关于印发全国社会心理服务体系建设试点2021年重点工作任务》的函", "http://mhsp-cdn.51vision.com/fronted/huizhou/联合办理《广东省卫生健康委等9部门转发关于印发全国社会心理服务体系建设试点2021年重点工作任务》的函0.pdf", 0, 0));


        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        evaluation_module_tv_title = (TextView) findViewById(R.id.evaluation_module_tv_title);
        btn_back = (ImageView) findViewById(R.id.evaluation_module_iv_back);
        evaluation_module_tv_title.setText("政策文件汇编");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(25));
        policyAssemblyAdapter = new PolicyAssemblyAdapter(customBannerList, PolicyAssemblyActivity.this);
        mRecyclerView.setAdapter(policyAssemblyAdapter);
    }


    private void initData() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        policyAssemblyAdapter.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

                mPosition = position;
                localCustomBean = customBannerList.get(mPosition);
                //动态权限申请
                if (ContextCompat.checkSelfPermission(PolicyAssemblyActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PolicyAssemblyActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //FileDisplayActivity.actionStart(PolicyAssemblyActivity.this,"http://www.cztouch.com/upfiles/soft/testpdf.pdf","testpdf.pdf");
                    FileDisplayActivity.actionStart(PolicyAssemblyActivity.this, localCustomBean.getUrl(), localCustomBean.title);
                }

            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    FileDisplayActivity.actionStart(PolicyAssemblyActivity.this, localCustomBean.getUrl(), localCustomBean.title);
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法下载文件到本地哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}