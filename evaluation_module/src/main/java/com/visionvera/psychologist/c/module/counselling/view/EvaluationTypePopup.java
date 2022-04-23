package com.visionvera.psychologist.c.module.counselling.view;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.ExpertiesResponseBean;

import java.util.List;


/**
 * @Desc 咨询类别弹窗
 * @Author yemh
 * @Date 2019-12-24 14:51
 */
public class EvaluationTypePopup extends PartShadowPopupView {

    private Context mContext;
    private List<ExpertiesResponseBean.ResultBean> mList;
    private OnListPopupClick mListener;
    EvaluationTypePopupAdapter mAdapter;

    public EvaluationTypePopup(@NonNull Context context, List<ExpertiesResponseBean.ResultBean> list, OnListPopupClick listener) {
        super(context);
        mContext = context;
        mList = list;
        mListener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_pop_evaluation_type;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        RecyclerView mRecyclerView = findViewById(R.id.rc_evaluation_type);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        mAdapter = new EvaluationTypePopupAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    class EvaluationTypePopupAdapter extends BaseQuickAdapter<ExpertiesResponseBean.ResultBean, BaseViewHolder> {
        public EvaluationTypePopupAdapter(@Nullable List<ExpertiesResponseBean.ResultBean> data) {
            super(R.layout.evaluation_module_item_evaluation_type, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, ExpertiesResponseBean.ResultBean item) {
            int position = holder.getLayoutPosition();
            CheckBox checkBox = holder.getView(R.id.cb_evaluation_type);
            String lableName = item.getLableName();
            checkBox.setText(lableName);
            checkBox.setChecked(item.isSelect());

            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    parseSelect(position);
                    mListener.onPopupClick(item.getId() + "",lableName);
                }
            });
        }
    }

    public interface OnListPopupClick {
        void onPopupClick(String id,String name);
    }

    private void parseSelect(int position) {
        for (int i = 0; i < mList.size(); i++) {
            ExpertiesResponseBean.ResultBean vo = mList.get(i);
            if (i == position) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }
        mAdapter.notifyDataSetChanged();
        dismiss();
    }

    /**
     * 设置弹窗的最大的高度
     */
    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .5f);
    }
}
