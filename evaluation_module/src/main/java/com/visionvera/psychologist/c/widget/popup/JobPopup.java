package com.visionvera.psychologist.c.widget.popup;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.usercenter.bean.JobBean;

import java.util.List;

/**
 * @Desc
 * @Author yemh
 * @Date 2019-12-29 16:08
 */
public class JobPopup extends BottomPopupView {

    private Context mContext;
    private List<JobBean.ResultBean> mList;
    private OnListPopupClick mListener;
    private String mSelectedJobId;
    JobTypePopupAdapter mAdapter;

    public JobPopup(@NonNull Context context, String selectedJobId, List<JobBean.ResultBean> list, OnListPopupClick listener) {
        super(context);
        mContext = context;
        mSelectedJobId = selectedJobId;
        mList = list;
        mListener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_popup_job;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        TextView tvCancel = findViewById(R.id.tvCancel);

        RecyclerView mRecyclerView = findViewById(R.id.evaluation_module_rc_job);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new JobTypePopupAdapter(mList, mSelectedJobId);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(1));
        mRecyclerView.setAdapter(mAdapter);

        tvCancel.setOnClickListener(v -> {
            dismiss();
        });
    }


    class JobTypePopupAdapter extends BaseQuickAdapter<JobBean.ResultBean, BaseViewHolder> {

        private String mSelectedJobId;

        public JobTypePopupAdapter(@Nullable List<JobBean.ResultBean> data, String selectedJobId) {
            super(R.layout.evaluation_module_item_job_type, data);
            mSelectedJobId = selectedJobId;
        }

        @Override
        protected void convert(BaseViewHolder holder, JobBean.ResultBean item) {
            int position = holder.getLayoutPosition();
            CheckBox checkBox = holder.getView(R.id.cb_evaluation_type);
            String lableName = item.getRemark();
            checkBox.setText(lableName);

            checkBox.setChecked(item.isSelect());
            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    parseSelect(position);
                    mListener.onPopupClick(item.getId() + "", lableName);
                }
            });
        }

    }
    public interface OnListPopupClick {
        void onPopupClick(String id, String name);
    }

    private void parseSelect(int position) {
        for (int i = 0; i < mList.size(); i++) {
            JobBean.ResultBean vo = mList.get(i);
            if (i == position) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
