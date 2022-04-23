package com.visionvera.psychologist.c.module.counselling.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;

import java.util.ArrayList;

/**
 * @author: 刘传政
 * @date: 2019-06-19 16:55
 * QQ:1052374416
 * 作用:优惠券选择弹窗
 * 注意事项:
 */
public class DiscountTicketBottomPopup extends BottomPopupView {
    private int checkPosition;
    private TextView tvAvailable;
    private ImageView ivTab1Indicator;
    private TextView tvNotAvailable;
    private ImageView ivTab2Indicator;

    private RelativeLayout rlTab1;
    private RelativeLayout rlTab2;
    private TextView tv_ok;
    private RecyclerView rv_availableTicket;
    private RecyclerView rv_not_availableTicket;

    AnimatorSet animatorSet0;
    AnimatorSet animatorSet1;
    long animatorDuration = 500;
    private int checkTabPosition = 0;


    public DiscountTicketBottomPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_discount_ticket_pop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        rlTab1 = findViewById(R.id.rlTab1);
        rlTab2 = findViewById(R.id.rlTab2);
        tvAvailable = findViewById(R.id.tvAvailable);
        ivTab1Indicator = findViewById(R.id.ivTab1Indicator);
        tvNotAvailable = findViewById(R.id.tvNotAvailable);
        ivTab2Indicator = findViewById(R.id.ivTab2Indicator);
        rv_availableTicket = findViewById(R.id.rv_availableTicket);
        rv_not_availableTicket = findViewById(R.id.rv_not_availableTicket);
        tv_ok = findViewById(R.id.tv_ok);


        rlTab1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTab(0);
            }
        });
        rlTab2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTab(1);
            }
        });

        checkTab(0);
        initRvAvailableTicket();
        tv_ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }

            }
        });
    }

    private void initRvAvailableTicket() {
        rv_availableTicket.setLayoutManager(new LinearLayoutManager(getContext()));
        Adapter1 adapter1 = new Adapter1(getContext());
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            strings.add(i + "");
        }
        rv_availableTicket.setAdapter(adapter1);
        rv_availableTicket.addItemDecoration(new SpaceItemDecoration(10));
        adapter1.addData(strings);
        adapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    class Adapter1 extends BaseQuickAdapter<String, BaseViewHolder> {
        private Context mContext;

        public Adapter1(Context context) {
            super(R.layout.evaluation_module_discount_ticket_item, null);
            this.mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder holder, String bean) {
        }
    }

    private void checkTab(int position) {
        if (checkTabPosition == position) {
            return;
        }
        checkTabPosition = position;
        switch (position) {
            case 0:
                tvAvailable.setTextColor(getContext().getResources().getColor(R.color.COLOR_BLACK_333333));
                ivTab1Indicator.setVisibility(View.VISIBLE);

                tvNotAvailable.setTextColor(getContext().getResources().getColor(R.color.COLOR_GRAY_666666));

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


                tvAvailable.setTextColor(getContext().getResources().getColor(R.color.COLOR_GRAY_666666));


                tvNotAvailable.setTextColor(getContext().getResources().getColor(R.color.COLOR_BLACK_333333));
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

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {

    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .7f);
    }

    @Override
    public int getMinimumHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .2f);
    }


}