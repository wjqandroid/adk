package com.visionvera.psychologist.c.module.counselling.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.util.EmojiFilter;
import com.visionvera.library.util.FileUtils;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.counselling.adapter.ConsultantListAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.ConsulantListResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.EvaluationAreaBean;
import com.visionvera.psychologist.c.module.counselling.bean.ExpertiesResponseBean;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.ExpertiesPresenter;
import com.visionvera.psychologist.c.module.counselling.view.EvaluationAreaPopup;
import com.visionvera.psychologist.c.module.counselling.view.EvaluationFilterPopup;
import com.visionvera.psychologist.c.module.counselling.view.EvaluationTypePopup;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Desc ?????????????????????
 * @Author yemh
 * @Date 2019-12-23 17:48
 */

public class SearchCounselorActivity extends BaseMVPLoadActivity<OrderConsultContract.FindConsulantancy.View, ExpertiesPresenter> {
    @BindView(R2.id.ll_choice)
    LinearLayout llChoice;

    @BindView(R2.id.et_search)
    EditText etSearch;

    @BindView(R2.id.iv_service)
    ImageView ivService;

    @BindView(R2.id.tv_search)
    TextView tvSearch;

    @BindView(R2.id.iv_mental_health)
    ImageView iv_mental_health;

    @BindView(R2.id.tv_mental_health)
    TextView tvMentalHealth;

    @BindView(R2.id.iv_area)
    ImageView iv_area;

    @BindView(R2.id.tv_area)
    TextView tv_area;

    @BindView(R2.id.iv_filter)
    ImageView iv_filter;

    @BindView(R2.id.tv_filter)
    TextView tvFilter;

    @BindView(R2.id.normal_view)
    RecyclerView consultantRecyclerView;

    private ConsultantListAdapter consultantListAdapter;
    private EvaluationTypePopup evaluationTypePopup;
    private EvaluationFilterPopup evaluationFilterPopup;
    private EvaluationAreaPopup evaluationAreaPopup;
    private List<EvaluationAreaBean.ProvinceBean> provinceList = new ArrayList<>();
    private List<ExpertiesResponseBean.ResultBean> tagList = new ArrayList<>();
    private String eType = "", eSex = "", areaCode = "", labeId = "";
    private String minBirthday;
    private String maxBirthday;
    private int typeSelectedPosition = 0;
    private int sexSelectedPosition = 0;
    private int agesSelectedPosition = 0;
    //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    //??????????????????0????????????????????????????????????????????????1?????????????????????????????????????????????
    private int netType = 0;
    private MakeAnAppointmentIntentBean mIntentBean;

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_makeappointment;
    }

    public static void startActivity(Context context, MakeAnAppointmentIntentBean intentBean) {
        context.startActivity(new Intent(context, SearchCounselorActivity.class).putExtra("intentBean", intentBean));
    }

    public static class MakeAnAppointmentIntentBean implements Serializable {
        private String evaluationType;
        private int evaluationTypeId;

        public MakeAnAppointmentIntentBean(String evaluationType, int evaluationTypeId) {
            this.evaluationType = evaluationType;
            this.evaluationTypeId = evaluationTypeId;
        }
    }


    @Override
    protected void doYourself() {
        initAdapter();

        mIntentBean = (MakeAnAppointmentIntentBean) getIntent().getSerializableExtra("intentBean");

        String data = FileUtils.getFromAssets(this, "news.json");
        EvaluationAreaBean json = new Gson().fromJson(data, EvaluationAreaBean.class);
        provinceList = json.getResult();
        mPresenter.getExpertiesList(this);

        showLoading();
        labeId = mIntentBean.evaluationTypeId + "";
        requestConsultList(labeId, "", "", "", "", "");

        if (mIntentBean.evaluationTypeId != 0) {
            tvMentalHealth.setText(mIntentBean.evaluationType);
        }
        etSearch.setFilters(new InputFilter[]{new EmojiFilter()});
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //???????????????
                if (!etSearch.getText().toString().trim().isEmpty()) {
                    KeyboardUtils.hideSoftInput(this);
                    String etValue = etSearch.getText().toString().trim();
                    showLoading();
                    searchConsultList(etValue);
                    netType = 1;
                }
                return true;
            }
            return false;
        });
    }

    private void initAdapter() {
        consultantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        consultantListAdapter = new ConsultantListAdapter(this);
        consultantRecyclerView.setAdapter(consultantListAdapter);
        consultantListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                CounselorDetailActivity.CounselorDetailIntentBean intentBean
                        = new CounselorDetailActivity.CounselorDetailIntentBean(
                        consultantListAdapter.getData().get(position).getPsyInfoId(),
                        consultantListAdapter.getData().get(position).getUserId());

                CounselorDetailActivity.startActivity(activity, intentBean);

            }
        });
    }

    /**
     * ????????????????????????
     */
    private void requestConsultList(String id, String name, String code, String sex, String minBirthday, String maxBirthday) {
        Map<String, String> params = new HashMap<>();
        params.put("lableId", id);
        params.put("name", name);
        params.put("areaCode", code);
        params.put("sex", sex);
        params.put("minBirthday", minBirthday);
        params.put("maxBirthday", maxBirthday);
        mPresenter.getConsulantList(RxPartMapUtils.toRequestBodyOfStringMap(params), this);
    }

    /**
     * ???????????????
     */
    private void searchConsultList(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        mPresenter.getSearchConsulantList(RxPartMapUtils.toRequestBodyOfStringMap(params), this);
    }

    private void showTypePopup() {
        if (evaluationTypePopup != null && evaluationTypePopup.isShow()) {
            return;
        }
        evaluationTypePopup = (EvaluationTypePopup) new XPopup.Builder(this)
                .atView(llChoice)
                .setPopupCallback(new XPopupCallback() {
                    @Override
                    public void onCreated(BasePopupView basePopupView) {

                    }

                    @Override
                    public void beforeShow(BasePopupView basePopupView) {

                    }

                    @Override
                    public void onShow(BasePopupView basePopupView) {
                        tvMentalHealth.setTextColor(getResources().getColor(R.color.COLOR_BLUE_3E86FE));
                        iv_mental_health.setImageResource(R.drawable.evaluation_module_blue_up);
                    }

                    @Override
                    public void onDismiss(BasePopupView basePopupView) {
                        tvMentalHealth.setTextColor(getResources().getColor(R.color.COLOR_BLACK_333333));
                        iv_mental_health.setImageResource(R.drawable.evaluation_module_gray_down);
                    }

                    @Override
                    public void beforeDismiss(BasePopupView basePopupView) {

                    }

                    @Override
                    public boolean onBackPressed(BasePopupView basePopupView) {
                        return false;
                    }

                })
                .asCustom(new EvaluationTypePopup(this, tagList, (id, name) -> {
                    labeId = id;
                    tvMentalHealth.setText(name);
                    requestConsultList(labeId, "", areaCode, eSex, minBirthday, maxBirthday);
                    netType = 0;
                }));
        evaluationTypePopup.show();
    }

    private void showAreaPopup() {
        if (evaluationAreaPopup != null && evaluationAreaPopup.isShow()) {
            return;
        }
        evaluationAreaPopup = (EvaluationAreaPopup) new XPopup.Builder(this)
                .atView(llChoice)
                .setPopupCallback(new XPopupCallback() {
                    @Override
                    public void onCreated(BasePopupView basePopupView) {

                    }

                    @Override
                    public void beforeShow(BasePopupView basePopupView) {

                    }

                    @Override
                    public void onShow(BasePopupView basePopupView) {
                        tv_area.setTextColor(getResources().getColor(R.color.COLOR_BLUE_3E86FE));
                        iv_area.setImageResource(R.drawable.evaluation_module_blue_up);
                    }

                    @Override
                    public void onDismiss(BasePopupView basePopupView) {
                        tv_area.setTextColor(getResources().getColor(R.color.COLOR_BLACK_333333));
                        iv_area.setImageResource(R.drawable.evaluation_module_gray_down);
                    }

                    @Override
                    public void beforeDismiss(BasePopupView basePopupView) {

                    }

                    @Override
                    public boolean onBackPressed(BasePopupView basePopupView) {
                        return false;
                    }

                })
                .asCustom(new EvaluationAreaPopup(this, provinceList, (code, cityName) -> {
                    //??????code???12????????? ??????350102000000  ???????????? ?????????2????????? ???35??? ????????????????????????????????????3501??? ?????????????????????6????????????350102??? ?????????????????????9????????????350102000??? ?????????????????????????????????350102000000???
                    //????????????view???????????????,????????????
                    if (code.length() >= 4) {
                        areaCode = code.substring(0, 4);
                        requestConsultList(labeId, "", areaCode, eSex, minBirthday, maxBirthday);

                        tv_area.setText(cityName);

                        netType = 0;
                    }

                }));

        evaluationAreaPopup.show();
    }

    private void showFilterPopup() {
        if (evaluationFilterPopup != null && evaluationFilterPopup.isShow()) {
            return;
        }
        evaluationFilterPopup = (EvaluationFilterPopup) new XPopup.Builder(this)
                .atView(llChoice)
                .setPopupCallback(new XPopupCallback() {
                    @Override
                    public void onCreated(BasePopupView basePopupView) {

                    }

                    @Override
                    public void beforeShow(BasePopupView basePopupView) {

                    }

                    @Override
                    public void onShow(BasePopupView basePopupView) {
                        tvFilter.setTextColor(getResources().getColor(R.color.COLOR_BLUE_3E86FE));
                        iv_filter.setImageResource(R.drawable.evaluation_module_blue_up);
                    }

                    @Override
                    public void onDismiss(BasePopupView basePopupView) {
                        tvFilter.setTextColor(getResources().getColor(R.color.COLOR_BLACK_333333));
                        iv_filter.setImageResource(R.drawable.evaluation_module_gray_down);
                    }

                    @Override
                    public void beforeDismiss(BasePopupView basePopupView) {

                    }

                    @Override
                    public boolean onBackPressed(BasePopupView basePopupView) {
                        return false;
                    }
                })
                .asCustom(new EvaluationFilterPopup(this, typeSelectedPosition, sexSelectedPosition, agesSelectedPosition, new EvaluationFilterPopup.OnListPopupClick() {
                    @Override
                    public void onPopupClick(String type, String sex, String minBirthday, String maxBirthday, int typeSelectedPosition, int sexSelectedPosition, int agesSelectedPosition) {
                        SearchCounselorActivity.this.typeSelectedPosition = typeSelectedPosition;
                        SearchCounselorActivity.this.sexSelectedPosition = sexSelectedPosition;
                        SearchCounselorActivity.this.agesSelectedPosition = agesSelectedPosition;
                        eType = type;
                        eSex = sex;
                        SearchCounselorActivity.this.minBirthday = minBirthday;
                        SearchCounselorActivity.this.maxBirthday = maxBirthday;
                        requestConsultList(labeId, "", areaCode, eSex, minBirthday, maxBirthday);
                        netType = 0;
                    }
                }));
        evaluationFilterPopup.show();
    }

    @OnClick({R2.id.iv_back, R2.id.ll_mental_health, R2.id.ll_area, R2.id.ll_filter, R2.id.iv_service, R2.id.tv_search,
            R2.id.et_search})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.iv_back) {
            finish();
        } else if (viewId == R.id.ll_mental_health) {
            showTypePopup();
        } else if (viewId == R.id.ll_area) {
            showAreaPopup();
        } else if (viewId == R.id.ll_filter) {
            showFilterPopup();
        } else if (viewId == R.id.iv_service) {

        } else if (viewId == R.id.tv_search) {
            KeyboardUtils.hideSoftInput(this);
            String etValue = etSearch.getText().toString().trim();
            showLoading();
            searchConsultList(etValue);
            netType = 1;
        }
    }

    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.FindConsulantancy.View() {
            @Override
            public void onGetExpertiesList(ExpertiesResponseBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //??????????????????????????????????????????????????????????????????
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //?????????????????????????????????code?????????
                    case SERVER_NORMAL_DATANO:
                        //?????????result???null
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        //??????????????????????????????code?????????result??????null?????????result???????????????????????????????????????
                        tagList.clear();
                        List<ExpertiesResponseBean.ResultBean> result = responseBean.getResult();
                        ExpertiesResponseBean.ResultBean vo = new ExpertiesResponseBean.ResultBean();
                        vo.setLableName("??????");
                        vo.setId(0);
                        tagList.add(vo);
                        tagList.addAll(result);
                        boolean find = false;
                        for (int i = 0; i < tagList.size(); i++) {
                            ExpertiesResponseBean.ResultBean ivo = tagList.get(i);
                            String name = ivo.getLableName();
                            if (mIntentBean.evaluationType.equals(name)) {
                                ivo.setSelect(true);
                                find = true;
                                break;
                            } else {
                                ivo.setSelect(false);
                                find = false;
                            }
                        }
                        //??????????????????,??????????????????????????????
                        if (!find) {
                            tagList.get(0).setSelect(true);
                        }
                        break;
                }
            }

            @Override
            public void onGetConsulantList(ConsulantListResponseBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //??????????????????????????????????????????????????????????????????
                        showError(getString(R.string.base_module_net_error));
                    case SERVER_ERROR:
                        //?????????????????????????????????code?????????
                        showError(getString(R.string.base_module_data_load_error));
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                        //?????????result???null
                        showEmpty();
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        List<ConsulantListResponseBean.ResultBean> result = responseBean.getResult();
                        if (result.size() == 0) {
                            showEmpty();
                        } else {
                            showNormal();
                            consultantListAdapter.getData().clear();
                            consultantListAdapter.addData(result);
                        }

                        break;
                }
            }

            @Override
            public void onSearchConsulantList(ConsulantListResponseBean responseBean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //??????????????????????????????????????????????????????????????????
                        showError(getString(R.string.base_module_net_error));
                    case SERVER_ERROR:
                        //?????????????????????????????????code?????????
                        showError(getString(R.string.base_module_data_load_error));
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                        //?????????result???null
                        showEmpty();
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        List<ConsulantListResponseBean.ResultBean> result = responseBean.getResult();
                        if (result.size() == 0) {
                            showEmpty();
                        } else {
                            showNormal();
                            consultantListAdapter.getData().clear();
                            consultantListAdapter.addData(result);
                        }
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new ExpertiesPresenter(activity, mView);
    }

    @Override
    protected void onReload() {
        showLoading();
        if (netType == 0) {
            requestConsultList(labeId, "", areaCode, eSex, minBirthday, maxBirthday);
        } else if (netType == 1) {
            String etValue = etSearch.getText().toString().trim();
            searchConsultList(etValue);
        }
    }
}
