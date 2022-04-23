package com.visionvera.psychologist.c.module.usercenter.adapter;import android.view.View;import android.widget.ImageView;import android.widget.TextView;import com.chad.library.adapter.base.BaseQuickAdapter;import com.chad.library.adapter.base.viewholder.BaseViewHolder;import com.visionvera.psychologist.c.R;import com.visionvera.psychologist.c.module.usercenter.bean.QuestionBean;import java.util.List;/** * @Desc 意见反馈条目适配器 * @Author yemh * @Date 2019-11-01 13:32 */public class QuestionsAdapter extends BaseQuickAdapter<QuestionBean, BaseViewHolder> {    private TextView tvFeedbackTitle;    private ImageView ivFeedbackSelect;    public QuestionsAdapter() {        super(R.layout.evaluation_module_feedback_item, null);    }    @Override    protected void convert(BaseViewHolder holder, QuestionBean bean) {        tvFeedbackTitle = holder.getView(R.id.tv_feedback_title);        ivFeedbackSelect = holder.getView(R.id.iv_feedback_select);        tvFeedbackTitle.setText(bean.getContent());        if (bean.isSelect()) {            ivFeedbackSelect.setVisibility(View.VISIBLE);        } else {            ivFeedbackSelect.setVisibility(View.GONE);        }    }    /**     * 处理点击选择     */    public void refreshSelect(QuestionBean vo) {        List<QuestionBean> data = getData();        for (int i = 0; i < data.size(); i++) {            QuestionBean bean = data.get(i);            if (bean.getContent().equals(vo.getContent())) {                bean.setSelect(true);            } else {                bean.setSelect(false);            }        }        notifyDataSetChanged();    }    /**     * 获取选中的问题id     * @return     */    public String getProblem() {        List<QuestionBean> data = getData();        for (int i = 0; i < data.size(); i++) {            QuestionBean bean = data.get(i);            if (bean.isSelect()) {                return bean.getProblem();            }        }        return "";    }}