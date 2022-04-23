package com.visionvera.psychologist.c.module.usercenter.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.usercenter.bean.SelecteCityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname:SelecteCityAdapter
 * @author:haohuizhao
 * @Date:2021/7/28 16:56
 * @Version 选择地址弹框  已选城市列表list
 */
public class SelecteCityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<SelecteCityBean> selecteCityList = new ArrayList<>(1);
    private static final int TYPE_TOP = 0x0000;
    private static final int TYPE_NORMAL = 0x0001;

    public SelecteCityAdapter(Context context, List<SelecteCityBean> selecteCityList) {
        inflater = LayoutInflater.from(context);
        this.selecteCityList = selecteCityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.evaluation_module_item_selecte_city, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;


        if (selecteCityList.get(position).getState().equals("1")) {
            if (position == 0) {
                // 第一行头的竖线不显示
                itemHolder.tvTopLine.setVisibility(View.INVISIBLE);
                // 字体颜色加深
                itemHolder.tvAcceptTime.setTextColor(0xff555555);
                itemHolder.tvDot.setBackgroundResource(R.drawable.timelline_dot_first);
            } else {
                itemHolder.tvTopLine.setVisibility(View.VISIBLE);
                // 字体颜色加深
                itemHolder.tvAcceptTime.setTextColor(0xff555555);
                itemHolder.tvDot.setBackgroundResource(R.drawable.timelline_dot_first);
            }
            itemHolder.tvBottomLine.setVisibility(View.VISIBLE);
        } else if (selecteCityList.get(position).getState().equals("0")) {
            itemHolder.tvTopLine.setVisibility(View.VISIBLE);
            itemHolder.tvAcceptTime.setTextColor(0xff999999);
            itemHolder.tvDot.setBackgroundResource(R.drawable.timelline_dot_normal);
        }
        if (selecteCityList.size() - 1 == position) {
            itemHolder.tvBottomLine.setVisibility(View.INVISIBLE);
        }

        itemHolder.tvAcceptTime.setText(selecteCityList.get(position).getName());
        if ((selecteCityList.get(position).getSelectState().equals("1"))) {
            itemHolder.tvAcceptTime.setTextColor(Color.RED);
        } else {
            itemHolder.tvAcceptTime.setTextColor(Color.BLACK);
        }

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
        return selecteCityList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP;
        }
        return TYPE_NORMAL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAcceptTime;
        private TextView tvTopLine, tvDot;
        private TextView tvBottomLine;

        public ViewHolder(View itemView) {
            super(itemView);
            tvBottomLine = (TextView) itemView.findViewById(R.id.tvBottomLine);
            tvAcceptTime = (TextView) itemView.findViewById(R.id.tvAcceptTime);
            tvTopLine = (TextView) itemView.findViewById(R.id.tvTopLine);
            tvDot = (TextView) itemView.findViewById(R.id.tvDot);
        }

        public void bindHolder(SelecteCityBean trace) {
//                tvAcceptTime.setText(trace.getAcceptTime());
        }
    }

    public void setData(List<SelecteCityBean> selecteCityList) {
        if (selecteCityList != null) {
            this.selecteCityList = selecteCityList;
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}