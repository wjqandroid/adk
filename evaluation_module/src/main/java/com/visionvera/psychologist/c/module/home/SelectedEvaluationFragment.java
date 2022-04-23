package com.visionvera.psychologist.c.module.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.visionvera.library.base.BaseMVPLoadFragment;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.activity.SelfAssessmentGaugeActivity;
import com.visionvera.psychologist.c.module.MainActivity;
import com.visionvera.psychologist.c.module.home.adapter.SelectedEvaluationAdapter;
import com.visionvera.psychologist.c.module.home.bean.HotEvaluationBean;
import com.visionvera.psychologist.c.module.home.contract.IContract;
import com.visionvera.psychologist.c.module.home.presenter.HotEvaluationPresenter;
import com.visionvera.psychologist.c.utils.RxPartMapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @Desc 精选测评
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
public class SelectedEvaluationFragment extends BaseMVPLoadFragment<IContract.HomeHot.View, HotEvaluationPresenter> implements OnItemClickListener {

    @BindView(R2.id.normal_view)
    RecyclerView mRecy;

    private List<HotEvaluationBean.ResultBean.ScaleDictListBean> dataList = new ArrayList<>();
    private int pageIndex = 1;
    private SelectedEvaluationAdapter mAdapter;

    public static SelectedEvaluationFragment newInstance() {
        return new SelectedEvaluationFragment();
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_selected;
    }

    @Override
    protected void initYourself() {
        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecy.addItemDecoration(new SpaceItemDecoration(10, SpaceItemDecoration.SpaceType.Top_bottom));
        mAdapter = new SelectedEvaluationAdapter(dataList,getActivity());
        mRecy.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);


        View errorView=getErrorView().findViewById(R.id.base_module_net_error_imageview);
        errorView.setVisibility(View.GONE);

        View emptyView=getEmptyView().findViewById(R.id.base_empty_imageview);
        emptyView.setVisibility(View.GONE);

        requestSelectedData();
    }

    /**
     * 请求精选测评数据
     */
    public void requestSelectedData() {

        showLoading();
        Map<String, Integer> params = new HashMap<>();
        params.put("pageIndex", pageIndex);
        mPresenter.getHotEvaluation(RxPartMapUtils.toRequestBodyOfIntegerMap(params), this);
        if (pageIndex==1){
            pageIndex=2;
        }else{
            pageIndex=1;
        }
    }

    @Override
    protected void initMVP() {
        mView = new IContract.HomeHot.View() {
            @Override
            public void onGetHotEvaluation(HotEvaluationBean responseBean, ResultType resultType, String errorMsg) {

                ((MainActivity)getActivity()).hideDialog();

                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等
                        showError("");
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                    case SERVER_NORMAL_DATANO:
                        showError("");
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATAYES:
                        showNormal();
                        refreshUI(responseBean.getResult());
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new HotEvaluationPresenter(getActivity(), mView);
    }

    /**
     * 刷新UI
     */
    private void refreshUI(HotEvaluationBean.ResultBean bean) {
        if (bean!=null && bean.getScaleDictList()!=null){
            dataList.clear();
            dataList.addAll(bean.getScaleDictList());
            mAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        HotEvaluationBean.ResultBean.ScaleDictListBean voFromIndex0 =
                (HotEvaluationBean.ResultBean.ScaleDictListBean) adapter.getData().get(position);
        SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean =
                new SelfAssessmentGaugeActivity.GaugeIntentBean(voFromIndex0.getId(),2,0,0);
        SelfAssessmentGaugeActivity.startActivity(getActivity(), gaugeIntentBean);
    }

    @Override
    protected void onReload() {
        requestSelectedData();
    }
}
