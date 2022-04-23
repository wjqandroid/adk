package com.visionvera.psychologist.c.module.usercenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.usercenter.bean.AreaListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname:CityListAdapter
 * @author:haohuizhao
 * @Date:2021/7/28 17:02
 * @Version 选择地址弹框  可选城市列表list
 */
public class CityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<AreaListBean.ResultBean> areaList = new ArrayList<>();
    private static final int TYPE_TOP = 0x0000;
    private static final int TYPE_NORMAL = 0x0001;

    public CityListAdapter(Context context, List<AreaListBean.ResultBean> areaList) {
        inflater = LayoutInflater.from(context);
        this.areaList = areaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.evaluation_module_item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;

        itemHolder.bindHolder(areaList.get(position));
        itemHolder.tv_city_name.setText(areaList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        }
        return TYPE_NORMAL;
    }

    public void setData(List<AreaListBean.ResultBean> areaList) {
        if (areaList != null) {
            this.areaList = areaList;
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_city_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_city_name = (TextView) itemView.findViewById(R.id.tv_city_name);
        }

        public void bindHolder(AreaListBean.ResultBean areaList) {
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}