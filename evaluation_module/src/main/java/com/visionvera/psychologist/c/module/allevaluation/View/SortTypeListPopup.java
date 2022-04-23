package com.visionvera.psychologist.c.module.allevaluation.View;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.visionvera.psychologist.c.R;

import java.util.List;


/**
 * @author: 刘传政
 * @date: 2019-10-31 09:53
 * QQ:1052374416
 * 作用:全部测试的排序选项弹窗
 * 注意事项:
 */
public class SortTypeListPopup extends PartShadowPopupView implements OnItemClickListener {

    private Context mContext;
    private List<String> mList;
    private int selectedPosition;
    private OnListPopupClick mListener;
    ListPopupAdapter adapter;

    public SortTypeListPopup(@NonNull Context context, List<String> list, int selectedPosition, OnListPopupClick listener) {
        super(context);
        mContext = context;
        mList = list;
        this.selectedPosition = selectedPosition;
        mListener = listener;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.list_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        RecyclerView mRecy = findViewById(R.id.list_popup_recy);
        mRecy.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new ListPopupAdapter(mList);
        mRecy.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    /**
     * 设置弹窗的最大的高度
     */
    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .5f);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mListener.onPopupClick(position);
    }

    class ListPopupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public ListPopupAdapter(@Nullable List<String> data) {
            super(R.layout.evaluation_module_sort_list_popup_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tv = helper.getView(R.id.list_popup_item_tv);
            if (helper.getAdapterPosition() == selectedPosition) {
                tv.setTextColor(mContext.getResources().getColor(R.color.evaluation_module_theme));
            } else {
                tv.setTextColor(mContext.getResources().getColor(R.color.COLOR_BLACK_333333));
            }
            helper.setText(R.id.list_popup_item_tv, item);
        }

    }

    public interface OnListPopupClick {
        void onPopupClick(int position);
    }

}
