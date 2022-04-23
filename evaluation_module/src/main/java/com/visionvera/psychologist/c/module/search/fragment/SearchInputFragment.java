package com.visionvera.psychologist.c.module.search.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visionvera.library.base.BaseFragment;
import com.visionvera.library.greendao.GreenDaoManager;
import com.visionvera.library.greendao.SearchHistoryDBBean;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.search.activity.SearchEvaluationActivity;
import com.visionvera.psychologist.c.module.search.bean.DiscoverResponseBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 刘传政
 * @date: 2019-10-29 17:53
 * QQ:1052374416
 * 作用:搜索测试量表的输入部分
 * 注意事项:
 */
public class SearchInputFragment extends BaseFragment {
    @BindView(R2.id.tagFlowLayout_find)
    TagFlowLayout tagFlowLayout_find;
    @BindView(R2.id.tagFlowLayout_history)
    TagFlowLayout tagFlowLayout_history;
    @BindView(R2.id.ll_history)
    LinearLayout ll_history;
    private TagAdapter<DiscoverResponseBean.ResultBean> tagAdapter_find;
    List<DiscoverResponseBean.ResultBean> tagList_find = new ArrayList<>();
    private TagAdapter<SearchHistoryDBBean> tagAdapter_history;
    List<SearchHistoryDBBean> tagList_history = new ArrayList<>();

    public static SearchInputFragment newInstance() {
        SearchInputFragment fragment = new SearchInputFragment();
        return fragment;
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_search_input;
    }

    @Override
    protected void initYourself() {
        initView();

    }

    private void initView() {
        showSearchHistory();
    }

    @OnClick({R2.id.iv_delete_history})
    public void onViewClicked(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.iv_delete_history) {
            GreenDaoManager.getInstance().clearAllSearchHistory();
            showSearchHistory();
        }

    }


    private void showSearchHistory() {
        List<SearchHistoryDBBean> searchHistoryDBBeans = GreenDaoManager.getInstance().query10SearchHistory();
        tagList_history.clear();
        tagList_history.addAll(searchHistoryDBBeans);
        if (tagList_history.size() == 0) {
            ll_history.setVisibility(View.GONE);
        } else {
            ll_history.setVisibility(View.VISIBLE);
        }

        tagAdapter_history = new TagAdapter<SearchHistoryDBBean>(tagList_history) {
            @Override
            public View getView(FlowLayout parent, int position, SearchHistoryDBBean s) {
                TextView tv = (TextView) getActivity().getLayoutInflater().inflate(R.layout.evaluation_module_item_tag_flow,
                        tagFlowLayout_history, false);
                tv.setText(s.getDiscoverValue());
                return tv;
            }
        };

        tagFlowLayout_history.setAdapter(tagAdapter_history);
        tagAdapter_history.notifyDataChanged();
        tagFlowLayout_history.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ((SearchEvaluationActivity) getActivity()).etContent.setText(tagList_history.get(position).getDiscoverValue());
                tagAdapter_find.notifyDataChanged();
                SearchHistoryDBBean searchHistoryDBBean = new SearchHistoryDBBean();
                searchHistoryDBBean.discoverValue = tagList_history.get(position).getDiscoverValue();
                GreenDaoManager.getInstance().insert(searchHistoryDBBean);
                ((SearchEvaluationActivity) getActivity()).switchFragment(1);
                ((SearchEvaluationActivity) getActivity()).searchResultFragment.requestChartList(true,
                        tagList_history.get(position).getDiscoverValue());
                showSearchHistory();
                return false;
            }
        });
    }


    //展示发现词
    public void showDiscover(List<DiscoverResponseBean.ResultBean> result) {
        if (result == null) {
            return;
        }
        tagList_find.clear();
        tagList_find.addAll(result);
        tagAdapter_find = new TagAdapter<DiscoverResponseBean.ResultBean>(tagList_find) {
            @Override
            public View getView(FlowLayout parent, int position, DiscoverResponseBean.ResultBean s) {
                TextView tv = (TextView) getActivity().getLayoutInflater().inflate(R.layout.evaluation_module_item_tag_flow,
                        tagFlowLayout_find, false);
                tv.setText(s.getValue());
                return tv;
            }
        };

        tagFlowLayout_find.setAdapter(tagAdapter_find);
        tagFlowLayout_find.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ((SearchEvaluationActivity) getActivity()).etContent.setText(tagList_find.get(position).getValue());
                SearchHistoryDBBean searchHistoryDBBean = new SearchHistoryDBBean();
                searchHistoryDBBean.discoverValue = tagList_find.get(position).getValue();
                GreenDaoManager.getInstance().insert(searchHistoryDBBean);
                showSearchHistory();
                ((SearchEvaluationActivity) getActivity()).switchFragment(1);
                ((SearchEvaluationActivity) getActivity()).searchResultFragment.requestChartList(true,
                        tagList_find.get(position).getValue());
                return false;
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
        } else {
            showSearchHistory();
        }
    }

}
