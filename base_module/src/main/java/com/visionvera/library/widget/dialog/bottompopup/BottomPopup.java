package com.visionvera.library.widget.dialog.bottompopup;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.R;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;

import java.util.List;

/**
* author:lilongfeng
* date:2020/3/12
* 描述:底部弹窗
*/

public class BottomPopup extends BottomPopupView {

    private Context mContext;
    private String mTitle;
    private List<BottomPopupBean> mDataList;
    private BottomPopupItemClick mBottomPopupItemClick;
    public BottomPopup(@NonNull Context context,List<BottomPopupBean> dataList,BottomPopupItemClick bottomPopupItemClick) {
        super(context);
        mContext=context;
        mDataList=dataList;
        mBottomPopupItemClick=bottomPopupItemClick;
    }

    public BottomPopup(@NonNull Context context,String title,List<BottomPopupBean> dataList,BottomPopupItemClick bottomPopupItemClick) {
        super(context);
        mContext=context;
        mTitle=title;
        mDataList=dataList;
        mBottomPopupItemClick=bottomPopupItemClick;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_bottom_popup;
    }



    @Override
    protected void onCreate() {
        super.onCreate();
        TextView title=findViewById(R.id.base_module_bottom_popup_title);

        if (TextUtils.isEmpty(mTitle)){
            title.setVisibility(GONE);
        }else{
            title.setVisibility(VISIBLE);
            title.setText(mTitle);
        }

        RecyclerView recy=findViewById(R.id.base_module_bottom_popup_recycler);
        recy.addItemDecoration(new SpaceItemDecoration(1,getResources().getColor(R.color.COLOR_GRAY_CCCCCC)));

        recy.setLayoutManager(new LinearLayoutManager(mContext));

        BottomPopupAdapter bottomPopupAdapter=new BottomPopupAdapter(mDataList);
        recy.setAdapter(bottomPopupAdapter);

        bottomPopupAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mBottomPopupItemClick != null) {
                    mBottomPopupItemClick.BottomPopupItemClickListener(mDataList.get(position));
                }
                dismiss();
            }
        });

    }

    class BottomPopupAdapter extends BaseQuickAdapter<BottomPopupBean, BaseViewHolder>{

        public BottomPopupAdapter(@Nullable List<BottomPopupBean> data) {
            super(R.layout.base_module_bottom_popup_text,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BottomPopupBean item) {
            helper.setText(R.id.base_module_bottom_popup_tv,item.getText());
        }
    }


    public interface BottomPopupItemClick{
        void BottomPopupItemClickListener(BottomPopupBean bottomPopupBean);
    }

}
