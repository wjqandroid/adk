package com.visionvera.psychologist.c.module.ordertreatment.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.eventbus.OrderTreatmentCancelEventBus;
import com.visionvera.psychologist.c.module.ordertreatment.fragment.OrderTreatmentApplyFragment;
import com.visionvera.psychologist.c.module.ordertreatment.fragment.OrderTreatmentInviteFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.visionvera.library.base.Constant.RequestReturnCode.OrderConsultItemFragment_To_OrderConsultApplyFragment_Code;

/**
 * @author: 刘传政
 * @date: 2020-01-02 16:05
 * QQ:1052374416
 * 作用:预约诊疗的列表
 * 注意事项:
 */

public class OrderTreatmentListActivity extends BaseActivity {

    @BindView(R2.id.evaluation_module_order_consult_list_viewpager)
    ViewPager vp;

    @BindView(R2.id.tvTab1)
    TextView tvTab1;
    @BindView(R2.id.tvTab2)
    TextView tvTab2;
    @BindView(R2.id.ivTab1Indicator)
    ImageView ivTab1Indicator;
    @BindView(R2.id.ivTab2Indicator)
    ImageView ivTab2Indicator;
    @BindView(R2.id.rlTab1)
    RelativeLayout rlTab1;
    @BindView(R2.id.rlTab2)
    RelativeLayout rlTab2;

    private List<BaseFragment> fragmentList = new ArrayList<>();
    private OrderTreatmentApplyFragment applyFragment;
    private OrderTreatmentInviteFragment inviteFragment;
    private int checkTabPosition = 0;
    AnimatorSet animatorSet0;
    AnimatorSet animatorSet1;
    long animatorDuration = 500;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, OrderTreatmentListActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_order_consult_list;
    }

    @Override
    protected void doYourself() {
        EventBus.getDefault().register(this);
        initView();


        applyFragment = new OrderTreatmentApplyFragment();
        inviteFragment = new OrderTreatmentInviteFragment();
        fragmentList.add(applyFragment);
        fragmentList.add(inviteFragment);


        OrderConsultListPagerAdapter adapter = new OrderConsultListPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                checkTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        checkTab(0);
    }

    private void initView() {
        rlTab1.setOnClickListener(v -> {
            //申请列表
            vp.setCurrentItem(0);
        });
        rlTab2.setOnClickListener(v -> {
            //受邀列表
            vp.setCurrentItem(1);
        });
    }

    private void checkTab(int position) {
        if (checkTabPosition == position) {
            return;
        }
        checkTabPosition = position;
        switch (position) {
            case 0:
                tvTab1.setTextColor(this.getResources().getColor(R.color.COLOR_BLACK_333333));
                ivTab1Indicator.setVisibility(View.VISIBLE);

                tvTab2.setTextColor(this.getResources().getColor(R.color.COLOR_GRAY_666666));

                // 动画
                if (animatorSet0 == null) {
                    animatorSet0 = new AnimatorSet();
                    animatorSet0.playTogether(
                            ObjectAnimator.ofFloat(ivTab1Indicator, "scaleX", (float) 0, 1f)
                                    .setDuration(animatorDuration),
                            ObjectAnimator.ofFloat(ivTab2Indicator, "scaleX", (float) 1, 0f)
                                    .setDuration(animatorDuration)
                    );
                }
                animatorSet0.cancel();
                animatorSet0.start();

                break;
            case 1:


                tvTab1.setTextColor(this.getResources().getColor(R.color.COLOR_GRAY_666666));


                tvTab2.setTextColor(this.getResources().getColor(R.color.COLOR_BLACK_333333));
                ivTab2Indicator.setVisibility(View.VISIBLE);
                // 动画
                if (animatorSet1 == null) {
                    animatorSet1 = new AnimatorSet();
                    animatorSet1.playTogether(
                            ObjectAnimator.ofFloat(ivTab1Indicator, "scaleX", (float) 1, 0f)
                                    .setDuration(animatorDuration),
                            ObjectAnimator.ofFloat(ivTab2Indicator, "scaleX", (float) 0, 1f)
                                    .setDuration(animatorDuration)
                    );
                }
                animatorSet1.cancel();
                animatorSet1.start();
                break;
        }
    }
    private class OrderConsultListPagerAdapter extends FragmentPagerAdapter {

        public OrderConsultListPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OrderConsultItemFragment_To_OrderConsultApplyFragment_Code && resultCode == OrderConsultItemFragment_To_OrderConsultApplyFragment_Code) {
//            applyFragment.refreshFragment2();
        }
    }

    @OnClick({R2.id.evaluation_module_order_consult_list_back})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_order_consult_list_back) {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(OrderTreatmentCancelEventBus event) {
        //预约咨询列表中 受邀列表-待受理-拒绝按钮点击事件
        if (inviteFragment != null) {
            //刷新待受理列表
            inviteFragment.refreshFragment(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (animatorSet0 != null) {
            animatorSet0.cancel();
        }
        if (animatorSet1 != null) {
            animatorSet1.cancel();
        }
    }
}
