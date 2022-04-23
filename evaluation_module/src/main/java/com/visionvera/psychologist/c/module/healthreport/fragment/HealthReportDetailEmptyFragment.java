package com.visionvera.psychologist.c.module.healthreport.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.visionvera.library.base.BaseFragment;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;

import butterknife.BindView;

/**
* author:lilongfeng
* date:2019/12/9
* 描述:健康报告结果页----空数据
*/

public class HealthReportDetailEmptyFragment extends BaseFragment {

    @BindView(R2.id.evaluation_module_health_report_detail_empty_title)
    TextView title;
    @BindView(R2.id.evaluation_module_health_report_detail_empty_image)
    ImageView image;
    @BindView(R2.id.evaluation_module_health_report_detail_empty_content)
    TextView content;

    public static HealthReportDetailEmptyFragment newInstance(int page) {
        HealthReportDetailEmptyFragment fragment=new HealthReportDetailEmptyFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("page",page);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_health_report_detail_empty;
    }

    @Override
    protected void initYourself() {
        int page=getArguments().getInt("page");
        if (page==1){
            title.setText("【 抑郁小王子 】");
            image.setImageResource(R.drawable.evaluation_module_health_report_detail_empty_1);
            content.setText("       抑郁只是我们众多情绪中的一种，它会自然地\n" +
                    "出现，也会自然地离开。只不过有时它的一些影响\n" +
                    "会让我们“舍不得”让它离开，例如：当处于抑郁情\n" +
                    "绪中时，我们可能会得到更多人的关心和爱护，也\n" +
                    "就不想摆脱这种抑郁的情绪。\n" +
                    "       但我们真的不需要通过这样的方式去寻求照顾，\n" +
                    "我们可以主动去跟别人建立关系，去跟好朋友诉说\n" +
                    "情绪，或者跟家人外出旅游玩耍一下。摆脱抑郁情\n" +
                    "绪的方式有很多，我们不需要自己一个人单打独斗，\n" +
                    "像个“孤胆英雄”一样。");
        }else if (page==2){
            title.setText("【  写给刚刚到来的11月  】");
            image.setImageResource(R.drawable.evaluation_module_health_report_detail_empty_2);
            content.setText("       抑郁只是我们众多情绪中的一种，它会自然地\n" +
                    "出现，也会自然地离开。只不过有时它的一些影响\n" +
                    "会让我们“舍不得”让它离开，例如：当处于抑郁情\n" +
                    "绪中时，我们可能会得到更多人的关心和爱护，也\n" +
                    "就不想摆脱这种抑郁的情绪。\n" +
                    "       但我们真的不需要通过这样的方式去寻求照顾，\n" +
                    "我们可以主动去跟别人建立关系，去跟好朋友诉说\n" +
                    "情绪，或者跟家人外出旅游玩耍一下。摆脱抑郁情\n" +
                    "绪的方式有很多，我们不需要自己一个人单打独斗，\n" +
                    "像个“孤胆英雄”一样。");
        }
    }
}
