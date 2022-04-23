package com.visionvera.psychologist.c.module.psychologycourse;

import com.gyf.immersionbar.ImmersionBar;
import com.visionvera.library.base.BaseFragment;
import com.visionvera.psychologist.c.R;

/**
 * @Desc 心理课程
 * @Author yemh
 * @Date 2019-10-29 15:32
 */
//public class PsychologyCourseFragment extends BaseMVPFragment<IContract.Home.View, HomePresenter> implements OnBannerListener {
public class PsychologyCourseFragment extends BaseFragment {
//    @Autowired(name = ARouterConstant.Account.accountModuleSetvice)
//    IAccountService accountService;
//    @BindView(R2.id.iv_usercenter)
//    ImageView ivUserCenter;
//
//    @BindView(R2.id.rl_evaluation_search)
//    RelativeLayout rlEvaluationSearch;
//
//    @BindView(R2.id.rv_evaluation_home)
//    RecyclerView mRecyclerView;
//
//    @BindView(R2.id.banner)
//    Banner mBanner;
//
//    @BindView(R2.id.ll_selects)
//    LinearLayout llSelects;
//
//    @BindView(R2.id.tv_selects_text)
//    TextView tvSelectsText;
//
//    @BindView(R2.id.view_selects_indic)
//    View viewSelectsIndic;
//
//    @BindView(R2.id.ll_collects)
//    LinearLayout llCollects;
//
//    @BindView(R2.id.tv_collects_text)
//    TextView tvCollectsText;
//
//    @BindView(R2.id.view_collects_indic)
//    View viewCollectsIndic;
//
//    @BindView(R2.id.tv_refresh)
//    TextView tvRefresh;
//
//    @BindView(R2.id.tv_more)
//    TextView tvMore;
//
//    @BindView(R2.id.vp_evaluation_type)
//    NoScrollViewPager mViewPager;
//
//    private EvaluationTypeAdapter mAdapter;
//    private TabPageAdapter tabAdapter;
//    private List<Fragment> fragmentList = new ArrayList<>();
//
//    private SelectedEvaluationFragment selectedEvaluationFragment;
//    private MyCollectsEvaluationFragment myCollectsEvaluationFragment;
//
//    private List<BannerBean.ResultBean> bannerList = new ArrayList<>();

    public static PsychologyCourseFragment newInstance() {
        return new PsychologyCourseFragment();
    }

    @Override
    protected void lazyLoadData() {
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_home;
    }

    @Override
    protected void initYourself() {
//        ARouter.getInstance().inject(this);
        updateStatuBar();
//        initAdapter();
//        initViewPager();
//        requestBanner();
//        requestEvaluationType();
    }

    public void updateStatuBar() {
        if (getActivity() != null) {
            ImmersionBar.with(this)
                    .statusBarColor(R.color.COLOR_BLUE_3E86FE)
                    .statusBarDarkFont(false)
                    .fitsSystemWindows(true)
                    .init();
        }
    }

//    /**
//     * 初始化adapter
//     */
//    private void initAdapter() {
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        mAdapter = new EvaluationTypeAdapter(getActivity());
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                EvaluationTypeBean.ResultBean item = mAdapter.getItem(position);
//                //todo 需要传实际的类型id
//                AllEvaluationActivity.setSelectedTitleId(item.getId());
//                MainActivity activity = (MainActivity) getActivity();
//                activity.tabAllEvaluation.setChecked(true);
//            }
//        });
//    }
//
//    /**
//     * 轮播图请求
//     */
//    private void requestBanner() {
//        Map<String, Integer> params = new HashMap<>();
//        params.put("type", 1);
//        mPresenter.getSystemBanner(RxPartMapUtils.toRequestBodyOfIntegerMap(params), this);
//    }
//
//    /**
//     * 测评分类请求
//     */
//    private void requestEvaluationType() {
//        Map<String, String> params = new HashMap<>();
//        params.put("groupName", "scale_dict_type");
//        mPresenter.getEvaluationType(RxPartMapUtils.toRequestBodyOfStringMap(params), this);
//    }

//        @Override
//        public void OnBannerClick(int position) {
//        BannerBean.ResultBean bean = bannerList.get(position);
//        SelfAssessmentGaugeActivity.GaugeIntentBean gaugeIntentBean =
//                new SelfAssessmentGaugeActivity.GaugeIntentBean(bean.getId(),2,0,0);
//        SelfAssessmentGaugeActivity.startActivity(getActivity(), gaugeIntentBean);
//        }

//    /**
//     * 初始化viewPager
//     */
//    private void initViewPager() {
//        selectedEvaluationFragment = SelectedEvaluationFragment.newInstance();
//        myCollectsEvaluationFragment = MyCollectsEvaluationFragment.newInstance();
//
//        fragmentList.add(selectedEvaluationFragment);
//        fragmentList.add(myCollectsEvaluationFragment);
//
//        tabAdapter = new TabPageAdapter(getFragmentManager());
//        mViewPager.setAdapter(tabAdapter);
//        tabAdapter.notifyDataSetChanged();
//        mViewPager.setOffscreenPageLimit(1);
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == 0) {
//                    tvRefresh.setVisibility(View.VISIBLE);
//                    tvMore.setVisibility(View.GONE);
//                    selectedEvaluationFragment.requestSelectedData(true);
//                } else {
//                    tvRefresh.setVisibility(View.GONE);
//                    tvMore.setVisibility(View.VISIBLE);
//                    myCollectsEvaluationFragment.requestCollectsData();
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }

//    @Override
//    protected void initMVP() {
//        mView = new IContract.Home.View() {
//            @Override
//            public void onGetSystemBanner(BannerBean responseBean, ResultType resultType, String errorMsg) {
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                    case SERVER_NORMAL_DATANO:
//                        ToastUtils.showShort(errorMsg);
//                        break;
//                    case SERVER_NORMAL_DATAYES:
//                        bannerList.clear();
//                        bannerList = responseBean.getResult();
//                        refreshBanner(bannerList);
//                        break;
//                }
//            }
//
//            @Override
//            public void onGetEvaluationType(EvaluationTypeBean responseBean, ResultType resultType, String errorMsg) {
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                    case SERVER_NORMAL_DATANO:
//                        ToastUtils.showShort(errorMsg);
//                        break;
//                    case SERVER_NORMAL_DATAYES:
//                        mAdapter.setNewData(responseBean.getResult());
//                        break;
//                }
//            }
//
//            @Override
//            public void onGetUserBasicInfo(UserBasicInfoBean userBasicInfoBean, ResultType resultType, String errorMsg) {
//
//                switch (resultType) {
//                    case NET_ERROR:
//                        //网络异常等
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
//                        break;
//                    case SERVER_ERROR:
//                        ToastUtils.showShort(errorMsg);
//                        break;
//                    case SERVER_NORMAL_DATANO:
//                    case SERVER_NORMAL_DATAYES:
//
//
//                        ((MainActivity) getActivity()).person_center_name.setText(userBasicInfoBean.getResult().getNickname());
//                        ((MainActivity) getActivity()).heart_bean_tv.setText(userBasicInfoBean.getResult().getBenasNums()+getString(R.string.evaluation_module_heart_bean));
//
//                        ((MainActivity) getActivity()).drawerlayout.openDrawer(Gravity.LEFT);
//                        break;
//                }
//
//
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        mPresenter = new HomePresenter(getActivity(), mView);
//    }

//    /**
//     * 更新轮播图数据
//     */
//    private void refreshBanner(List<BannerBean.ResultBean> result) {
//        ArrayList<String> images = new ArrayList<>();
//        for (int i = 0; i < result.size(); i++) {
//            BannerBean.ResultBean bean = result.get(i);
//            images.add(bean.getImageUrl());
//        }
//        mBanner.setImages(images)
//                .setImageLoader(new GlideImageLoader())
//                .setOnBannerListener(this)
//                .setBannerAnimation(StackTransformer.class)
//                .start()
//                .updateBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//
//
//    }
//
//    private class TabPageAdapter extends FragmentPagerAdapter {
//        public TabPageAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//        }
//
//        @Override
//        public long getItemId(int position) {
//            //确保返回的值唯一即可
//            return fragmentList.get(position).hashCode();
//        }
//
//        @Override
//        public int getCount() {
//            if (fragmentList != null && fragmentList.size() > 0) {
//                return fragmentList.size();
//            } else {
//                return 0;
//            }
//        }
//
//        @Override
//        public int getItemPosition(Object object) {
//            //此Item不再显示
//            return POSITION_NONE;
//        }
//    }
//
//    @OnClick({R2.id.rl_evaluation_search, R2.id.iv_usercenter, R2.id.ll_selects, R2.id.ll_collects, R2.id.tv_refresh, R2.id.tv_more})
//    public void onViewClicked(View view) {
//        if (OneClickUtils.isFastClick()) {
//            return;
//        }
//        int viewId = view.getId();
//        if (viewId == R.id.rl_evaluation_search) {
//            startActivity(new Intent(getActivity(), SearchEvaluationActivity.class));
//        } else if (viewId == R.id.iv_usercenter) {
//            //用户中心
//            if (accountService!=null && accountService.getGetAccountInfo()!=null && !accountService.getGetAccountInfo().isLogin){
//                //没有登录，跳转到登录界面
//                ARouter.getInstance()
//                        .build(ARouterConstant.Account.PhoneLoginActivity)
//                        .withInt(Constant.IntentKey.requestReturnCode, Constant.RequestReturnCode.HomeFragment_To_UserCenter)
//                        .navigation(getActivity(), Constant.RequestReturnCode.HomeFragment_To_UserCenter);
//                return;
//            }else{
//
//                mPresenter.getUserBasicInfo(this);
//
//
//            }
//        } else if (viewId == R.id.ll_selects) {
//            //精选测评
//            mViewPager.setCurrentItem(0);
//            tvSelectsText.setTextColor(getResources().getColor(R.color.COLOR_BLUE_3E86FE));
//            viewSelectsIndic.setVisibility(View.VISIBLE);
//            tvCollectsText.setTextColor(getResources().getColor(R.color.COLOR_GRAY_666666));
//            viewCollectsIndic.setVisibility(View.GONE);
//        } else if (viewId == R.id.ll_collects) {
//            //我的收藏
//            if (accountService!=null && accountService.getGetAccountInfo()!=null && !accountService.getGetAccountInfo().isLogin){
//                ARouter.getInstance()
//                        .build(ARouterConstant.Account.PhoneLoginActivity)
//                        .withInt(Constant.IntentKey.requestReturnCode, Constant.RequestReturnCode.HomeFragment_To_PhoneLogin_Code)
//                        .navigation(getActivity(), Constant.RequestReturnCode.HomeFragment_To_PhoneLogin_Code);
//                return;
//            }else{
//                mViewPager.setCurrentItem(1);
//                tvSelectsText.setTextColor(getResources().getColor(R.color.COLOR_GRAY_666666));
//                viewSelectsIndic.setVisibility(View.GONE);
//                tvCollectsText.setTextColor(getResources().getColor(R.color.COLOR_BLUE_3E86FE));
//                viewCollectsIndic.setVisibility(View.VISIBLE);
//            }
//        } else if (viewId == R.id.tv_refresh) {
//            //换一换
////            EvaluationResultActivity.EvaluationResultIntentBean bean = new EvaluationResultActivity.EvaluationResultIntentBean("20", "FZYXGCS-", "专业量表测评");
////            Intent intent = new Intent(getActivity(), EvaluationResultActivity.class);
////            intent.putExtra(EvaluationResultActivity.RESULT_INTENT_BEAN_STRING, bean);
////            startActivity(intent);
//            if (selectedEvaluationFragment != null) {
//                selectedEvaluationFragment.requestSelectedData(true);
//            }
//        } else if (viewId == R.id.tv_more) {
//            //查看更多
//            if (accountService!=null && accountService.getGetAccountInfo()!=null && !accountService.getGetAccountInfo().isLogin){
//                ARouter.getInstance()
//                        .build(ARouterConstant.Account.PhoneLoginActivity)
//                        .withInt(Constant.IntentKey.requestReturnCode, Constant.RequestReturnCode.HomeFragment_More_To_PhoneLogin_Code)
//                        .navigation(getActivity(), Constant.RequestReturnCode.HomeFragment_More_To_PhoneLogin_Code);
//                return;
//            }else{
//                startActivity(new Intent(getActivity(), MyCollectsActivity.class));
//            }
//        }
//    }
}
