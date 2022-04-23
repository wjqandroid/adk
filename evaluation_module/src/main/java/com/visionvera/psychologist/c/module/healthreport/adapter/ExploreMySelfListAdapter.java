package com.visionvera.psychologist.c.module.healthreport.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.visionvera.psychologist.c.R;

import java.util.List;
/**
* author:lilongfeng
* date:2019/12/3
* 描述:探索自我列表页面的适配器
*/
public class ExploreMySelfListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    private final Context mContext;

    public ExploreMySelfListAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.evaluation_module_explore_myself_list_item,data);

        mContext = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.evaluation_module_explore_myself_attribute,"测试属性");
        helper.setText(R.id.evaluation_module_explore_myself_scale_name,"ENFP");

        helper.setImageDrawable(R.id.evaluation_module_explore_myself_img,mContext.getResources().getDrawable(R.drawable.evaluation_module_tiger));
        helper.setText(R.id.evaluation_module_explore_myself_item_time,"2019-10-10");

        LinearLayout tagContainer=helper.getView(R.id.evaluation_module_explore_myself_tag_layout);

        int random=(int)(1+Math.random()*(10-1+1));


        int layoutWidth=tagContainer.getWidth();
        int addTextWidth=0;

        for (int i = 0; i < random; i++) {
            TextView tagtext=new TextView(mContext);
            tagtext.setText("标签"+i);
            tagtext.setTextColor(Color.parseColor("#3E86FE"));
            tagtext.setBackgroundResource(R.drawable.bg_logout_unselect);

            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,10,0);
            tagtext.setLayoutParams(lp);
            tagtext.setPadding(10,0,10,0);

            addTextWidth+=tagtext.getWidth();

            if (addTextWidth<layoutWidth){

            }
            tagContainer.addView(tagtext,i);

        }

    }



}
