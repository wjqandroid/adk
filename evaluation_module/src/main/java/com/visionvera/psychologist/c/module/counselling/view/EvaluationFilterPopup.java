package com.visionvera.psychologist.c.module.counselling.view;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.impl.PartShadowPopupView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.counselling.bean.EvaluationTypeVo;

import java.util.ArrayList;
import java.util.List;


/**
 * @Desc 咨询筛选弹窗
 * @Author yemh
 * @Date 2019-12-24 14:51
 */
public class EvaluationFilterPopup extends PartShadowPopupView {

    private Context mContext;
    private int typeSelectedPosition = 0;
    private int sexSelectedPosition = 0;
    private int agesSelectedPosition = 0;
    private OnListPopupClick mListener;
    private String[] types = new String[]{"不限", "视频", "音频"};
    private String[] sexs = new String[]{"不限", "男性", "女性"};
    private String[] ages = new String[]{"不限", "60后", "70后", "80后", "90后", "00后"};
    private List<EvaluationTypeVo> typeList = new ArrayList<>();
    private List<EvaluationTypeVo> sexList = new ArrayList<>();
    private List<EvaluationTypeVo> ageList = new ArrayList<>();

    private EvaluationTypePopupAdapter typeAdapter;
    private EvaluationSexPopupAdapter sexAdapter;
    private EvaluationAgePopupAdapter ageAdapter;
    private String type = "", sex = "", minBirthday = "", maxBirthday = "";

    public EvaluationFilterPopup(@NonNull Context context, int typeSelectedPosition, int sexSelectedPosition, int agesSelectedPosition, OnListPopupClick listener) {
        super(context);
        mContext = context;
        //告诉我默认选中哪几个选项
        this.typeSelectedPosition = typeSelectedPosition;
        this.sexSelectedPosition = sexSelectedPosition;
        this.agesSelectedPosition = agesSelectedPosition;
        mListener = listener;

        initData();
    }

    private void initData() {
        for (int i = 0; i < types.length; i++) {
            EvaluationTypeVo vo = new EvaluationTypeVo();
            vo.setName(types[i]);
            if (i == typeSelectedPosition) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
            typeList.add(vo);
        }

        for (int i = 0; i < sexs.length; i++) {
            EvaluationTypeVo vo = new EvaluationTypeVo();
            vo.setName(sexs[i]);
            if (i == sexSelectedPosition) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
            sexList.add(vo);
        }

        for (int i = 0; i < ages.length; i++) {
            EvaluationTypeVo vo = new EvaluationTypeVo();
            vo.setName(ages[i]);
            if (i == agesSelectedPosition) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
            ageList.add(vo);
        }
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_pop_evaluation_filter;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        RecyclerView typeRecyclerView = findViewById(R.id.rc_evaluation_type);
        typeRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        typeAdapter = new EvaluationTypePopupAdapter(typeList);
        typeRecyclerView.setAdapter(typeAdapter);

        RecyclerView sexRecyclerView = findViewById(R.id.rc_evaluation_sex);
        sexRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        sexAdapter = new EvaluationSexPopupAdapter(sexList);
        sexRecyclerView.setAdapter(sexAdapter);

        RecyclerView ageRecyclerView = findViewById(R.id.rc_evaluation_age);
        ageRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        ageAdapter = new EvaluationAgePopupAdapter(ageList);
        ageRecyclerView.setAdapter(ageAdapter);

        TextView tvReset = findViewById(R.id.tv_reset);
        tvReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        TextView tvOk = findViewById(R.id.tv_ok);
        tvOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPopupClick(type, sex, minBirthday, maxBirthday, typeSelectedPosition, sexSelectedPosition, agesSelectedPosition);
                dismiss();
            }
        });
    }

    class EvaluationTypePopupAdapter extends BaseQuickAdapter<EvaluationTypeVo, BaseViewHolder> {
        public EvaluationTypePopupAdapter(@Nullable List<EvaluationTypeVo> data) {
            super(R.layout.evaluation_module_item_evaluation_type, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, EvaluationTypeVo item) {
            int position = holder.getLayoutPosition();
            CheckBox checkBox = holder.getView(R.id.cb_evaluation_type);
            checkBox.setText(item.getName());
            checkBox.setChecked(item.isSelect());

            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    parseTypeSelect(position);
                }
            });
        }
    }

    class EvaluationSexPopupAdapter extends BaseQuickAdapter<EvaluationTypeVo, BaseViewHolder> {
        public EvaluationSexPopupAdapter(@Nullable List<EvaluationTypeVo> data) {
            super(R.layout.evaluation_module_item_evaluation_type, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, EvaluationTypeVo item) {
            int position = holder.getLayoutPosition();
            CheckBox checkBox = holder.getView(R.id.cb_evaluation_type);
            checkBox.setText(item.getName());
            checkBox.setChecked(item.isSelect());

            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    parseSexSelect(position);
                }
            });
        }
    }

    class EvaluationAgePopupAdapter extends BaseQuickAdapter<EvaluationTypeVo, BaseViewHolder> {
        public EvaluationAgePopupAdapter(@Nullable List<EvaluationTypeVo> data) {
            super(R.layout.evaluation_module_item_evaluation_type, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, EvaluationTypeVo item) {
            int position = holder.getLayoutPosition();
            CheckBox checkBox = holder.getView(R.id.cb_evaluation_type);
            checkBox.setText(item.getName());
            checkBox.setChecked(item.isSelect());

            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    parseAgeSelect(position);
                }
            });
        }
    }

    public interface OnListPopupClick {
        void onPopupClick(String type, String sex, String minBirthday, String maxBirthday, int typeSelectedPosition, int sexSelectedPosition, int agesSelectedPosition);
    }

    private void parseTypeSelect(int position) {
        typeSelectedPosition = position;
        switch (position) {
            case 0:
                type = "";
                break;

            case 1:
                type = "0";
                break;

            case 2:
                type = "1";
                break;

            default:
                break;
        }
        for (int i = 0; i < typeList.size(); i++) {
            EvaluationTypeVo vo = typeList.get(i);
            if (i == position) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }
        typeAdapter.notifyDataSetChanged();
    }

    private void parseSexSelect(int position) {
        sexSelectedPosition = position;
        switch (position) {
            case 0:
                sex = "";
                break;

            case 1:
                sex = "1";
                break;

            case 2:
                sex = "2";
                break;

            default:
                break;
        }
        for (int i = 0; i < sexList.size(); i++) {
            EvaluationTypeVo vo = sexList.get(i);
            if (i == position) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }
        sexAdapter.notifyDataSetChanged();
    }

    private void parseAgeSelect(int position) {
        agesSelectedPosition = position;
        switch (position) {
            case 0:
                minBirthday = "";
                maxBirthday = "";
                break;

            case 1:
                minBirthday = "1960-01-01";
                maxBirthday = "1970-01-01";
                break;

            case 2:
                minBirthday = "1970-01-01";
                maxBirthday = "1980-01-01";
                break;

            case 3:
                minBirthday = "1980-01-01";
                maxBirthday = "1990-01-01";
                break;

            case 4:
                minBirthday = "1990-01-01";
                maxBirthday = "2000-01-01";
                break;
            case 5:
                minBirthday = "2000-01-01";
                maxBirthday = "2010-01-01";
                break;
            default:
                break;
        }
        for (int i = 0; i < ageList.size(); i++) {
            EvaluationTypeVo vo = ageList.get(i);
            if (i == position) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }
        ageAdapter.notifyDataSetChanged();
    }

    private void reset() {
        for (int i = 0; i < typeList.size(); i++) {
            EvaluationTypeVo vo = typeList.get(i);
            if (i == 0) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }

        for (int i = 0; i < sexList.size(); i++) {
            EvaluationTypeVo vo = sexList.get(i);
            if (i == 0) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }

        for (int i = 0; i < ageList.size(); i++) {
            EvaluationTypeVo vo = ageList.get(i);
            if (i == 0) {
                vo.setSelect(true);
            } else {
                vo.setSelect(false);
            }
        }
        type = "";
        sex = "";
        minBirthday = "";
        maxBirthday = "";
        typeSelectedPosition = 0;
        sexSelectedPosition = 0;
        agesSelectedPosition = 0;
        typeAdapter.notifyDataSetChanged();
        sexAdapter.notifyDataSetChanged();
        ageAdapter.notifyDataSetChanged();
    }
}
