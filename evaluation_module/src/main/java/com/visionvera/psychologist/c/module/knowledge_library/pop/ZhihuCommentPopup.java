package com.visionvera.psychologist.c.module.knowledge_library.pop;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.lxj.xpopup.util.XPopupUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.visionvera.library.arouter.service.IAccountService;
import com.visionvera.library.base.bean.BaseResponseBean3;
import com.visionvera.library.base.bean.PageBean;
import com.visionvera.library.base.mvp.view.IBaseView;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.util.StringUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.knowledge_library.activity.ArticleDetailActivity;
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddCommentResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.CommentListResponseBean;
import com.visionvera.psychologist.c.module.knowledge_library.bean.EvaluateItemBean;
import com.visionvera.psychologist.c.module.knowledge_library.util.TimeUtils;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 仿知乎底部评论弹窗
 * Create by dance, at 2018/12/25
 */
public class ZhihuCommentPopup extends BottomPopupView {
    private ArrayList<EvaluateItemBean> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private LinearLayout llToEvaluate;
    private TextView tvTotalCount;
    private ImageView iv_close;
    private TextView tvImputedComment;
    private RelativeLayout rlPublish;
    private RelativeLayout custom_empty_view_evaluate;
    private RelativeLayout custom_loading_view;
    private RelativeLayout custom_error_view;
    private RelativeLayout custom_no_network_view;
    private ImageView empty_retry_view;
    private ImageView error_retry_view;
    private EvaluateListAdapter mAdapter;
    ArticleDetailActivity activity;
    //当前文章id
    private int mArticleId;
    //文章的作者姓名
    private String mArticleUserName;
    //文章的作者id
    private int mArticleUserId;
    //当前操作的评论的id
    private int commentId = -1;
    //当前操作的评论的position
    private int position = -1;

    private int atUserId = -1;

    private String atName = "";
    PageBean pageBean = new PageBean();
    private int totalCount = 0;

    public ZhihuCommentPopup(@NonNull Context context, int itemId, String personName, int personId) {
        super(context);
        activity = (ArticleDetailActivity) context;
        this.mArticleId = itemId;
        this.mArticleUserName = personName;
        this.mArticleUserId = personId;
        commentId = -1;
        atUserId = personId;
        atName = personName;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluate_pop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        recyclerView = findViewById(R.id.recyclerView);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        llToEvaluate = findViewById(R.id.llToEvaluate);
        tvTotalCount = findViewById(R.id.tvTotalCount);
        iv_close = findViewById(R.id.iv_close);
        tvImputedComment = findViewById(R.id.tvImputedComment);
        rlPublish = findViewById(R.id.rlPublish);

        custom_empty_view_evaluate = findViewById(R.id.custom_empty_view_evaluate);
        custom_loading_view = findViewById(R.id.custom_loading_view);
        custom_error_view = findViewById(R.id.custom_error_view);
        custom_no_network_view = findViewById(R.id.custom_no_network_view);
        empty_retry_view = findViewById(R.id.empty_retry_view);
        error_retry_view = findViewById(R.id.error_retry_view);

        iv_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //不要直接这样做，会导致消失动画未执行完就跳转界面，不流畅。
//                dismiss();
//                getContext().startActivity(new Intent(getContext(), DemoActivity.class))
                //可以等消失动画执行完毕再开启新界面
                dismissWith(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
        llToEvaluate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                showEditCommentPop(tvImputedComment.getText().toString().trim(), mArticleUserName + " (文章作者)", mArticleUserId);
            }
        });

        rlPublish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                if (TextUtils.isEmpty(tvImputedComment.getText().toString().trim())) {
                    ToastUtils.showShort("请输入你要发表的评论");
                    return;
                }
                net_addComment();
            }
        });
        mAdapter = new EvaluateListAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        View footView = LayoutInflater.from(getContext()).inflate(R.layout.foot_view, null);
        mAdapter.addFooterView(footView);
        mAdapter.getFooterLayout().setVisibility(View.GONE);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

                CommentListResponseBean.Children dataBean = dataList.get(position).dataBean;
                String userName = dataBean.getUserName();
                if (StringUtils.isMobileNO(userName)) {
                    String name = userName.substring(0, 3) + "****" + userName.substring(7, userName.length());
                    showEditCommentPop(tvImputedComment.getText().toString().trim(), name, dataBean.getUserId());
                } else {
                    showEditCommentPop(tvImputedComment.getText().toString().trim(), dataBean.getUserName(), dataBean.getUserId());
                }

//                showEditCommentPop(tvImputedComment.getText().toString().trim(), dataBean.getUserName(), dataBean.getUserId());
                commentId = dataBean.getId();
                ZhihuCommentPopup.this.position = position;
                atUserId = dataBean.getUserId();
                atName = dataBean.getUserName();
            }
        });
        mAdapter.addChildClickViewIds(R.id.ivLike);
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (view.getId() == R.id.ivLike) {
                    if (dataList.get(position).dataBean.getThumbsUpStatus() == 0) {
                        //当前是未点赞状态
                        activity.net_commentLikeOrNot(dataList.get(position).dataBean.getId(), true);
                    } else {
                        //当前是已点赞状态
                        activity.net_commentLikeOrNot(dataList.get(position).dataBean.getId(), false);
                    }
                }
            }
        });
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                activity.net_getCommentList(false, mArticleId, pageBean);
            }
        });
        empty_retry_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                activity.net_getCommentList(true, mArticleId, pageBean);
            }
        });
        error_retry_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                activity.net_getCommentList(true, mArticleId, pageBean);
            }
        });

        showLoading();
        activity.net_getCommentList(true, mArticleId, pageBean);


    }

    public void net_addComment() {
        activity.net_addComment(mArticleId, commentId, position, tvImputedComment.getText().toString().trim(), atUserId, atName);
    }

    private void showEditCommentPop(String content, String personName, int personId) {
        XPopup.setAnimationDuration(0);
        //弹出新的弹窗用来输入
        final CustomEditTextBottomPopup textBottomPopup = new CustomEditTextBottomPopup(getContext(), content, personName, personId, ZhihuCommentPopup.this);
        new XPopup.Builder(getContext())
                .autoOpenSoftInput(true)
//                        .hasShadowBg(false)
                .setPopupCallback(new SimpleCallback() {
                    @Override
                    public void onShow(BasePopupView popupView) {
                    }

                    @Override
                    public void onDismiss(BasePopupView popupView) {
                        String s = textBottomPopup.et_comment.getText().toString().trim();
                        tvImputedComment.setText(s);
                        XPopup.setAnimationDuration(350);
                    }
                })
                .asCustom(textBottomPopup)
                .show();
    }

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "知乎评论 onShow");
    }

    //完全消失执行
    @Override
    protected void onDismiss() {
        Log.e("tag", "知乎评论 onDismiss");

    }

    @Override
    protected int getMaxHeight() {
//        return XPopupUtils.getWindowHeight(getContext());
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .9f);
    }

    private void showLoading() {
        custom_empty_view_evaluate.setVisibility(GONE);
        custom_loading_view.setVisibility(VISIBLE);
        custom_error_view.setVisibility(GONE);
        custom_no_network_view.setVisibility(GONE);
        smartRefreshLayout.setVisibility(GONE);
    }

    private void showContent() {
        custom_empty_view_evaluate.setVisibility(GONE);
        custom_loading_view.setVisibility(GONE);
        custom_error_view.setVisibility(GONE);
        custom_no_network_view.setVisibility(GONE);
        smartRefreshLayout.setVisibility(VISIBLE);
    }

    private void showError() {
        custom_empty_view_evaluate.setVisibility(GONE);
        custom_loading_view.setVisibility(GONE);
        custom_error_view.setVisibility(VISIBLE);
        custom_no_network_view.setVisibility(GONE);
        smartRefreshLayout.setVisibility(GONE);
    }

    private void showEmpty() {
        custom_empty_view_evaluate.setVisibility(VISIBLE);
        custom_loading_view.setVisibility(GONE);
        custom_error_view.setVisibility(GONE);
        custom_no_network_view.setVisibility(GONE);
        smartRefreshLayout.setVisibility(GONE);
    }

    public void onCommentLikeOrNot(BaseResponseBean3<Object> responseBean, IBaseView.ResultType resultType, String errorMsg, int id, boolean isAddLike) {

        switch (resultType) {
            case NET_ERROR:
                //网络异常等
                ToastUtils.showLong("网络异常");
                break;
            case SERVER_ERROR:
                break;
            case SERVER_NORMAL_DATANO:
            case SERVER_NORMAL_DATAYES:
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).dataBean.getId() == id) {

                        if (isAddLike) {
                            dataList.get(i).dataBean.setThumbsUpStatus(1);
                            dataList.get(i).dataBean.setThumbsUpNumber(dataList.get(i).dataBean.getThumbsUpNumber() + 1);
                        } else {
                            dataList.get(i).dataBean.setThumbsUpStatus(0);
                            dataList.get(i).dataBean.setThumbsUpNumber(dataList.get(i).dataBean.getThumbsUpNumber() - 1);
                            if (dataList.get(i).dataBean.getThumbsUpNumber() <= 0) {
                                dataList.get(i).dataBean.setThumbsUpNumber(0);
                            }
                        }
                        mAdapter.notifyItemChanged(i);
                        activity.net_refreshCounts();
                    }
                }
                break;
        }
    }

    public void onGetcommentList(BaseResponseBean3<CommentListResponseBean> responseBean, IBaseView.ResultType resultType, String errorMsg, Boolean isRefresh) {

        try {
            int size = 0;
            size = responseBean.getResult().getTotalRecords();

            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadMore();
            if (size < pageBean.pageSize) {
                //全部加载完成<已经没有数据>
                smartRefreshLayout.finishLoadMoreWithNoMoreData();
                mAdapter.getFooterLayout().setVisibility(View.VISIBLE);
            } else {
                //加载完成<还有数据>
                smartRefreshLayout.setNoMoreData(false);
                mAdapter.getFooterLayout().setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showContent();
        switch (resultType) {
            case NET_ERROR:
                //网络异常等
                ToastUtils.showLong("网络异常");
                break;
            case SERVER_ERROR:
            case SERVER_NORMAL_DATANO:
                ToastUtils.showShort(errorMsg);
                break;
            case SERVER_NORMAL_DATAYES:
                if (isRefresh) {
                    dataList.clear();
                    pageBean.currentPage = pageBean.defaltPage;
                }
                if (responseBean.getResult() != null) {

                    if (responseBean.getResult().getDataList() != null && responseBean.getResult().getDataList().size() > 0) {
                        addData(responseBean.getResult().getDataList());
                        pageBean.currentPage++;
                    }
                    //总条数
//                    totalCount = responseBean.getResult().getTotalRecords();
                    totalCount = dataList.size();
                    tvTotalCount.setText("全部" + totalCount + "条评论");
                    if (totalCount == 0) {
                        showEmpty();
                    }
                }
                mAdapter.notifyDataSetChanged();

                break;
        }

    }

    private void addData(List<CommentListResponseBean.Children> list) {
        if (list == null) {
            return;
        }
        for (CommentListResponseBean.Children children : list) {
            EvaluateItemBean evaluateItemBean1 = new EvaluateItemBean(EvaluateListAdapter.EVALUATE_ARTICLE);
            evaluateItemBean1.dataBean = children;
            dataList.add(evaluateItemBean1);
            addData(children.getChildrenList());
        }
    }

    public void onAddComment(BaseResponseBean3<AddCommentResponseBean> responseBean, IBaseView.ResultType resultType, String errorMsg, int commentId, int position) {

        switch (resultType) {
            case NET_ERROR:
                //网络异常等
                ToastUtils.showLong("网络异常");
                break;
            case SERVER_ERROR:
            case SERVER_NORMAL_DATANO:
                ToastUtils.showShort(errorMsg);
                break;
            case SERVER_NORMAL_DATAYES:
                if (position == -1 && commentId == -1) {
                    //也就是直接评论给了文章作者
                    ToastUtils.showShort("评论成功");
                    insertOneComment(responseBean, position);
                } else {
                    //评论给某一条评论的作者
                    if (dataList.get(position).dataBean.getId() == commentId) {
                        //确认是刚才那条
                        ToastUtils.showShort("评论成功");
                        insertOneComment(responseBean, position);
                    }
                }

                break;
        }
    }

    private void insertOneComment(BaseResponseBean3<AddCommentResponseBean> responseBean, int position) {
        if (dataList.size() == 0) {
            activity.net_getCommentList(true, mArticleId, pageBean);
        } else {
            EvaluateItemBean evaluateItemBean = new EvaluateItemBean(EvaluateListAdapter.EVALUATE_ARTICLE);
            CommentListResponseBean.Children dataBean = new CommentListResponseBean.Children();
            dataBean.setArticalId(responseBean.getResult().getArticalId());
            dataBean.setChildrenList(null);
            dataBean.setContent(responseBean.getResult().getContent());
            dataBean.setCreateTime(responseBean.getResult().getCreateTime());
            dataBean.setId(responseBean.getResult().getId());
            dataBean.setDelete(responseBean.getResult().isDelete());
            dataBean.setParentId(responseBean.getResult().getParentId());
            dataBean.setParentUserId(responseBean.getResult().getParentUserId());
            dataBean.setParentName(responseBean.getResult().getParentName());
            dataBean.setUserId(responseBean.getResult().getUserId());
            dataBean.setUserName(responseBean.getResult().getUserName());
            dataBean.setThumbsUpNumber(responseBean.getResult().getThumbsUpNumber());
            dataBean.setThumbsUpStatus(responseBean.getResult().getThumbsUpStatus());
            dataBean.setUserImgUrl(responseBean.getResult().getUserImgUrl());
            evaluateItemBean.dataBean = dataBean;
            dataList.add(position + 1, evaluateItemBean);
            mAdapter.notifyItemInserted(position + 1);
            recyclerView.smoothScrollToPosition(position + 1);
            //总条数
            totalCount = dataList.size();
            tvTotalCount.setText("全部" + totalCount + "条评论");
        }

        commentId = -1;
        ZhihuCommentPopup.this.position = -1;
        atUserId = mArticleUserId;
        atName = mArticleUserName;
        tvImputedComment.setText("");
        activity.net_refreshCounts();
    }

    /*public void onRemoveComment(BaseResponseBean<Object> responseBean, BaseView.ResultType resultType, String errorMsg, int id, int position) {

    }*/

    /*public void showEmptyOrContent(Context context, PageBean pageBean, List dataList) {
        try {
            if (pageBean.currentPage == pageBean.defaltPage && dataList.size() == 0) {
                showEmpty();
                ToastUtil.showShort(context.getString(R.string.net_empty));
            } else {
                showContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public class EvaluateListAdapter extends BaseMultiItemQuickAdapter<EvaluateItemBean, BaseViewHolder> {
        public static final int EVALUATE_ARTICLE = 1;//评论文章
        public static final int EVALUATE_EVALUATION = 2;//评论别人的评论

        public EvaluateListAdapter(List<EvaluateItemBean> data) {
            super(data);
            addItemType(EVALUATE_ARTICLE, R.layout.evaluate_article_item);

        }

        @Override
        protected void convert(@NotNull BaseViewHolder helper, EvaluateItemBean item) {
            switch (helper.getItemViewType()) {
                case EVALUATE_ARTICLE:
                    ImageView ivPersonHead = helper.getView(R.id.ivPersonHead);
                    TextView tvPersonName = helper.getView(R.id.tvPersonName);
                    TextView tvComment = helper.getView(R.id.tvComment);
                    TextView tvTime = helper.getView(R.id.tvTime);
                    ImageView ivLike = helper.getView(R.id.ivLike);
                    Glide.with(ivPersonHead)
                            .load(item.dataBean.getUserImgUrl())
                            .placeholder(R.drawable.default_head)
                            .into(ivPersonHead);

                    String nameInfo = "";
                    String fromName = item.dataBean.getUserName();
                    String atName = "";
                    IAccountService accountService = activity.getMyAccountService();
                    if (accountService != null && accountService.getGetAccountInfo() != null && accountService.getGetAccountInfo().isLogin) {
                        if (accountService.getGetAccountInfo().userId.equals(item.dataBean.getUserId() + "")) {
                            //是我自己
                            fromName = "我";
                        }

                        if (accountService.getGetAccountInfo().userId.equals(item.dataBean.getParentUserId() + "")) {
                            //是我自己
                            atName = "我";
                        } else {
                            atName = item.dataBean.getParentName();
                        }
                        if (!TextUtils.isEmpty(item.dataBean.getParentName()) && item.dataBean.getParentId() != 0 && item.dataBean.getParentUserId() != mArticleUserId) {
                            nameInfo = fromName + "  ➞  " + atName;
                        } else {
                            nameInfo = fromName + "";
                        }
                        tvPersonName.setText(nameInfo);
                    }

                    tvComment.setText(item.dataBean.getContent() + "");
                    /**
                     * 1分钟内            刚刚
                     * 1分钟以上,2分钟内   1分钟前
                     * 2分钟以上,3分钟内   2分钟前
                     * 1小时以上,2小时内   1小时前
                     * 2小时以上,3小时内   2小时前
                     * 24小时以上,48小时内   1天前
                     * 48小时以上,72小时内   2天前
                     * 72小时以上          显示发布评论的日期
                     *
                     */
                    if (!item.dataBean.getCreateTime().equals("")) {
                        try {

                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String showTime = TimeUtils.showTime(df.parse(item.dataBean.getCreateTime()), "yyyy-MM-dd HH:mm:ss");
                            tvTime.setText(showTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            tvTime.setText(item.dataBean.getCreateTime());
                        }
                    } else {
                        tvTime.setText("");
                    }


                    if (item.dataBean.getThumbsUpStatus() == 0) {
                        //没点赞
                        ivLike.setImageDrawable(getContext().getDrawable(R.drawable.ic_unlike));
                    } else {
                        //点赞了
                        ivLike.setImageDrawable(getContext().getDrawable(R.drawable.ic_like));
                    }
                    break;
                case EVALUATE_EVALUATION:

                    break;
                default:
                    break;
            }
        }
    }
}