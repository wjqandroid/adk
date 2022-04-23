package com.visionvera.psychologist.c.module.counselling.view;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.EvaluationAreaBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @Desc 咨询地区弹窗
 * @Author yemh
 * @Date 2019-12-24 14:51
 */
public class EvaluationAreaPopup extends PartShadowPopupView {

    private Context mContext;
    private OnListPopupClick mListener;
    private List<EvaluationAreaBean.ProvinceBean> dataList = new ArrayList<>();

    private EvaluationProvincePopupAdapter provinceAdapter;
    private EvaluationCityPopupAdapter cityAdapter;

    public EvaluationAreaPopup(@NonNull Context context, List<EvaluationAreaBean.ProvinceBean> list, OnListPopupClick listener) {
        super(context);
        this.mContext = context;
        this.mListener = listener;
        this.dataList = list;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_pop_evaluation_area;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        RecyclerView provinceRecyclerView = findViewById(R.id.rc_evaluation_province);
        provinceRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        provinceAdapter = new EvaluationProvincePopupAdapter(dataList);
        provinceRecyclerView.setAdapter(provinceAdapter);

        RecyclerView sexRecyclerView = findViewById(R.id.rc_evaluation_city);
        sexRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        cityAdapter = new EvaluationCityPopupAdapter();
        sexRecyclerView.setAdapter(cityAdapter);
    }

    class EvaluationProvincePopupAdapter extends BaseQuickAdapter<EvaluationAreaBean.ProvinceBean, BaseViewHolder> {
        public EvaluationProvincePopupAdapter(@Nullable List<EvaluationAreaBean.ProvinceBean> data) {
            super(R.layout.evaluation_module_item_evaluation_area, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, EvaluationAreaBean.ProvinceBean item) {
            int position = holder.getLayoutPosition();
            CheckBox checkBox = holder.getView(R.id.cb_evaluation_type);
            checkBox.setText(item.getName());
            checkBox.setChecked(item.isSelect());

            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    parseProvinceSelect(position);
                }
            });
        }
    }

    class EvaluationCityPopupAdapter extends BaseQuickAdapter<EvaluationAreaBean.ProvinceBean.CityBean, BaseViewHolder> {
        public EvaluationCityPopupAdapter() {
            super(R.layout.evaluation_module_item_evaluation_area, null);
        }

        @Override
        protected void convert(BaseViewHolder holder, EvaluationAreaBean.ProvinceBean.CityBean item) {
            int position = holder.getLayoutPosition();
            CheckBox checkBox = holder.getView(R.id.cb_evaluation_type);
            checkBox.setText(item.getName());
            checkBox.setChecked(item.isSelect());

            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    parseCitySelect(position);
                    mListener.onPopupClick(item.getCode(),item.getName());
                }
            });
        }
    }

    public interface OnListPopupClick {
        void onPopupClick(String code,String cityName);
    }

    private void parseProvinceSelect(int position) {
        for (int i = 0; i < dataList.size(); i++) {
            EvaluationAreaBean.ProvinceBean vo = dataList.get(i);
            if (i == position) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }
        provinceAdapter.notifyDataSetChanged();

        cityAdapter.getData().clear();
        List<EvaluationAreaBean.ProvinceBean.CityBean> result = dataList.get(position).getResult();
        for (int i = 0; i < result.size(); i++) {
            EvaluationAreaBean.ProvinceBean.CityBean bean = result.get(i);
            bean.setSelect(false);
        }
        cityAdapter.addData(result);
    }

    private void parseCitySelect(int position) {
        List<EvaluationAreaBean.ProvinceBean.CityBean> data = cityAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            EvaluationAreaBean.ProvinceBean.CityBean vo = data.get(i);
            if (i == position) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }
        cityAdapter.notifyDataSetChanged();
        dismiss();
    }
}
