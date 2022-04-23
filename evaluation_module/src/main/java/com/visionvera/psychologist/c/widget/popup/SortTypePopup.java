package com.visionvera.psychologist.c.widget.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.impl.PartShadowPopupView;
import com.visionvera.psychologist.c.R;

/**
 * author:lilongfeng
 * date:2019/11/27
 * 描述:欢迎度，价格，时间
 */

public class SortTypePopup extends PartShadowPopupView {

    private int mSortedType;
    private SortTypePopupListener mSortTypePopupListener;
    private Context mContext;
//    排序规则  1正序  2倒序（默认）
    private int sort;
    public SortTypePopup(@NonNull Context context, int sortedType, int sort,SortTypePopupListener sortTypePopupListener) {
        super(context);
        mContext=context;
        this.sort=sort;
        mSortedType = sortedType;
        mSortTypePopupListener = sortTypePopupListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_sort_type_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        TextView welcome = findViewById(R.id.evaluation_module_welcome_degree);
        TextView priceTv = findViewById(R.id.evaluation_module_price);
        RelativeLayout priceLayout = findViewById(R.id.evaluation_module_price_layout);
        ImageView priceIv = findViewById(R.id.evaluation_module_price_iv);
        TextView time = findViewById(R.id.evaluation_module_time);



        switch (mSortedType) {
            case 1:
                welcome.setTextColor(mContext.getResources().getColor(R.color.evaluation_module_theme));
                priceTv.setTextColor(mContext.getResources().getColor(R.color.COLOR_BLACK_333333));
                time.setTextColor(mContext.getResources().getColor(R.color.COLOR_BLACK_333333));
                break;
            case 2:
                welcome.setTextColor(mContext.getResources().getColor(R.color.COLOR_BLACK_333333));
                priceTv.setTextColor(mContext.getResources().getColor(R.color.evaluation_module_theme));
                time.setTextColor(mContext.getResources().getColor(R.color.COLOR_BLACK_333333));

                if (sort==1){
                    priceIv.setImageDrawable(getResources().getDrawable(R.drawable.evaluation_module_price_up));
                }else if(sort==2){
                    priceIv.setImageDrawable(getResources().getDrawable(R.drawable.evaluation_module_price_down));
                }

                break;
            case 3:
                welcome.setTextColor(mContext.getResources().getColor(R.color.COLOR_BLACK_333333));
                priceTv.setTextColor(mContext.getResources().getColor(R.color.COLOR_BLACK_333333));
                time.setTextColor(mContext.getResources().getColor(R.color.evaluation_module_theme));
                break;
            default:
        }

        welcome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortTypePopupListener.setSortTypePopupListener(1,2);
            }
        });
        priceLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mSortTypePopupListener.setSortTypePopupListener(2,sort);
            }
        });

        time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortTypePopupListener.setSortTypePopupListener(3,2);
            }
        });


    }

    public interface SortTypePopupListener {
        void setSortTypePopupListener(int sortedType,int sort);
    }

}
