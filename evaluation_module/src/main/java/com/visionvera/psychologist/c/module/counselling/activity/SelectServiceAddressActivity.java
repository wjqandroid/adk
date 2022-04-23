package com.visionvera.psychologist.c.module.counselling.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.adapter.ServiceAddressListAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.AvatarBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lxj.xpopup.enums.PopupPosition.Bottom;

/**
 * @Classname:SelectServiceNetworkActivity
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0
 * @describe： 选择视联网网点
 */


public class SelectServiceAddressActivity extends BaseMVPLoadActivity {

//
//    @BindView(R.id.tv_search)
//    EditText tvSearch;
//    @BindView(R.id.rv_serviceNetwork)
//    RecyclerView recyclerView;
//    @BindView(R.id.btn_nearby)
//    TextView btnNearby;

    //百度定位
    private LocationClient locationClient;
    //是否定位成功
    private boolean isFixPosition = false;
    private String tempSelectDeviceId = "00000";
    private ServiceAddressListAdapter serviceAddressListAdapter;
    //选中索引
    private int selectPosition;
    //头像List
    private static List<AvatarBean> avatarBeanList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_select_service_network;
    }


    @Override
    protected void doYourself() {
        setAvatarList();
        initAdapter();
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void onReload() {

    }


    private void setAvatarList() {
        if (avatarBeanList.size() == 0) {
            avatarBeanList.add(new AvatarBean("用户1", 4, 0, false));
            avatarBeanList.add(new AvatarBean("用户2", 1, 5, false));
            avatarBeanList.add(new AvatarBean("用户3", 2, 8, false));
            avatarBeanList.add(new AvatarBean("用户4", 3, 5, false));
            avatarBeanList.add(new AvatarBean("用户5", 4, 5, false));
            avatarBeanList.add(new AvatarBean("用户6", 5, 5, false));
        }
    }


    /**
     * 定位方法
     */
    private void FixedPosition() {
        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        locationClient = new LocationClient(this);
        MyLocationListener myLocationListener = new MyLocationListener();
        //注册监听函数
        locationClient.registerLocationListener(myLocationListener);
        //声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        //可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
        locationOption.setLocationNotify(false);
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);
        //开始定位
        locationClient.start();

    }

//
//    @OnClick({R.id.iv_back, R.id.tv_search, R.id.btn_serviceNetwork, R.id.btn_nearby})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_back:
//                break;
//            case R.id.tv_search:
//                break;
//            case R.id.btn_serviceNetwork:
//                break;
//            case R.id.btn_nearby:
//                setPop();
//
//
//                break;
//        }
//    }

    private void setPop() {
//        new XPopup.Builder(SelectServiceAddressActivity.this)
//                .hasShadowBg(false)
//                .popupPosition(Bottom)
//                .customAnimator(null) // 设置自定义的动画器
//                .atView(btnNearby)  // 依附于所点击的 View，内部会自动判断在上方或者下方显示
//                .asAttachList( new String[]{"附近500m", "附近1km内", "附近2km内", "附近5km内"},null,
//                        new OnSelectListener() {
//                            @Override
//                            public void onSelect(int position, String text) {
//                                btnNearby.setText(text);
////                                ToastUtils.showShort(text);
//                            }
//                        })
//                .show();
    }


    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            hideDialog();
            if (!TextUtils.isEmpty(location.getAdCode())) {
                isFixPosition = true;
            }
        }
    }

    //初始化 Adapter
    private void initAdapter() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(SelectServiceAddressActivity.this, LinearLayoutManager.VERTICAL, false));
////        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
//        serviceAddressListAdapter = new ServiceAddressListAdapter(SelectServiceAddressActivity.this, avatarBeanList);
//        recyclerView.setAdapter(serviceAddressListAdapter);
//        serviceAddressListAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
//
//                //选中逻辑处理
//                if (selectPosition == position) {
//                    avatarBeanList.get(position).is_selected = !avatarBeanList.get(position).is_selected;
//                } else {
//                    //获取选中的position
//                    selectPosition = position;
//                    for (int i = 0; i < avatarBeanList.size(); i++) {
//                        avatarBeanList.get(i).is_selected = false;
//                    }
//                    avatarBeanList.get(position).is_selected = true;
//                }
//                adapter.notifyDataSetChanged();
//
//                Intent intent = getIntent();
//                AvatarBean avatarBean = avatarBeanList.get(position);
//                Bundle bundle = new Bundle();
//                bundle.putString("name", avatarBean.getName());
//                intent.putExtras(bundle);
//
//                setResult(RESULT_OK, intent);
//                finish();
//
//            }
//        });
    }

}