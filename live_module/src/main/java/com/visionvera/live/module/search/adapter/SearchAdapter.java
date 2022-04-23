package com.visionvera.live.module.search.adapter;import android.content.Context;import android.text.TextUtils;import android.view.View;import android.widget.ImageView;import android.widget.TextView;import com.visionvera.library.util.GlideImageLoader;import com.visionvera.live.R;import com.visionvera.live.module.home.bean.CourseBean;import com.visionvera.live.utils.ResUtils;import com.visionvera.live.widget.recycler.BaseQuickAdapter;import com.visionvera.live.widget.recycler.BaseViewHolder;/** * @Desc 搜索列表适配器 * * @Author yemh * @Date 2019/6/17 15:12 * */public class SearchAdapter extends BaseQuickAdapter<CourseBean, BaseViewHolder> {    private Context mContext;    private ImageView ivLiveShortCut;    private TextView tvType;    private TextView tvTitle;    private TextView tvExpert;    private TextView tvLiveTime;    private TextView tvLiveType;    public SearchAdapter(Context context) {        super(R.layout.item_recy_search_layout, null);        this.mContext = context;    }    @Override    protected void convert(BaseViewHolder holder, CourseBean bean) {        String url = bean.getMasterPicUrl();        ivLiveShortCut = holder.getView(R.id.iv_live_shaortcut);        tvType = holder.getView(R.id.tv_type);        tvTitle = holder.getView(R.id.tv_title);        tvExpert = holder.getView(R.id.tv_expert);        tvLiveTime = holder.getView(R.id.tv_live_time);        tvLiveType = holder.getView(R.id.tv_live_type);        if (!TextUtils.isEmpty(url)) {            GlideImageLoader.loadImage(mContext, url, ivLiveShortCut);        }        int playMode = bean.getPlayModel();        if (playMode == 1) {            tvType.setText(mContext.getString(R.string.recording));        } else {            tvType.setText(mContext.getString(R.string.live));        }        tvTitle.setText(bean.getCourseName());        tvExpert.setText(bean.getExpertName());        tvLiveTime.setText(bean.getStartTime());        int status = bean.getLiveStatus();        switch (status) {            case -1://录播                tvLiveType.setVisibility(View.GONE);                break;            case 0://未开始                tvLiveType.setVisibility(View.VISIBLE);                tvLiveType.setBackground(ResUtils.getDrawable(R.drawable.bg_solid_ffffff_stroke_1_1dd864));                tvLiveType.setText(R.string.no_started);                break;            case 1://直播中                tvLiveType.setVisibility(View.VISIBLE);                tvLiveType.setBackground(ResUtils.getDrawable(R.drawable.bg_solid_ffffff_stroke_1_1d99ee));                tvLiveType.setText(R.string.liveing);                break;            case 2://已结束                tvLiveType.setVisibility(View.VISIBLE);                tvLiveType.setBackground(ResUtils.getDrawable(R.drawable.bg_solid_ffffff_stroke_1_ed9e1e));                tvLiveType.setText(R.string.live_over);                break;            default:                break;        }    }}