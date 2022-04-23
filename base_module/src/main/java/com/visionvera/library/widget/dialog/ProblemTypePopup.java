package com.visionvera.library.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.R;
import com.visionvera.library.widget.dialog.bottompopup.ProblemTypeBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * @author: 作用: 预约咨询的问题类型
 */

public class ProblemTypePopup extends BottomPopupView {

    private ResultListener mListener;

    private ArrayList<ProblemTypeBean> problemTypeBeans;
    private RecyclerView recyclerView;
    private ProblemTypeListAdapter problemTypeListAdapter;
    //选中索引
    private int selectPosition;
    //选中的对象
    private ProblemTypeBean problemTypeBean;

//    public ProblemTypePopup(@NonNull Context context, ResultListener listener, ProblemTypebean problemTypebean) {
    public ProblemTypePopup(@NonNull Context context, ResultListener listener) {
        super(context);
        mListener = listener;

    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_problem_popup;
    }


    @Override
    protected void onCreate() {
        super.onCreate();


        initView();
        initAdapter();
    }

    private void initView() {
        recyclerView = findViewById(R.id.pop_recyclerView);
        TextView btn_determine = findViewById(R.id.evaluation_module_consult_determine);
        btn_determine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // ToastUtils.showShort(problemTypeBean.getName());
                mListener.onPick(problemTypeBean.getName());
                dismiss();
            }
        });
//        findViewById(R.id.evaluation_module_consult_determine).setOnClickListener(v -> dismiss());

        problemTypeBeans = new ArrayList<ProblemTypeBean>();
        problemTypeBeans.add(new ProblemTypeBean(false, "压力管理", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "抑郁焦虑", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "睡眠问题", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "家庭亲子", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "情感情绪", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "心理健康", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "人际关系", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "个人成长", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "精神疾病", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "儿童青年", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "老人心理", 2));
        problemTypeBeans.add(new ProblemTypeBean(false, "其他    ", 2));
    }

    //初始化 Adapter
    private void initAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        problemTypeListAdapter = new ProblemTypeListAdapter(getContext(), problemTypeBeans);
        recyclerView.setAdapter(problemTypeListAdapter);
        problemTypeListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                //选中逻辑处理
                if (selectPosition == position) {
                    problemTypeBeans.get(position).is_select = !problemTypeBeans.get(position).is_select;
                } else {
                    //获取选中的position
                    selectPosition = position;
                    for (int i = 0; i < problemTypeBeans.size(); i++) {
                        problemTypeBeans.get(i).is_select = false;
                    }
                    problemTypeBeans.get(position).is_select = true;
                }
                adapter.notifyDataSetChanged();

                problemTypeBean = problemTypeBeans.get(position);
            }
        });
    }


    /**
     * 相册的点击事件接口
     */
    public interface ResultListener {
        void onPick(String name);
    }


}
