package com.visionvera.psychologist.c.module.knowledge_library.adapterimport android.widget.TextViewimport com.chad.library.adapter.base.BaseQuickAdapterimport com.chad.library.adapter.base.viewholder.BaseViewHolderimport com.visionvera.psychologist.c.Rimport com.visionvera.psychologist.c.module.knowledge_library.bean.DraftBeanimport com.visionvera.psychologist.c.module.knowledge_library.util.TimeUtils/** * 草稿 */class DraftListAdapter(data: MutableList<DraftBean>) : BaseQuickAdapter<DraftBean, BaseViewHolder>(R.layout.rv_item_draft, data) {    /**     * Implement this method and use the helper to adapt the view to the given item.     *     * @param helper A fully initialized helper.     * @param item   The item that needs to be displayed.     */    override fun convert(helper: BaseViewHolder, item: DraftBean) {        var tvTitle = helper.getView<TextView>(R.id.tvTitle)        var tvSummary = helper.getView<TextView>(R.id.tvSummary)        var tvTime = helper.getView<TextView>(R.id.tvTime)        tvTitle.text = item.title?.let { it1 -> translation(it1) }        tvSummary.text = item.summary?.let { it1 -> translation(it1) }        var stringTime = TimeUtils.getStringTime(item.time, "MM-dd")        tvTime.text = stringTime    }    private fun translation(content: String): String {        val replace = content.replace("&lt;", "<");        val replace1 = replace.replace("&gt;", ">");        val replace2 = replace1.replace("&amp;", "&");        val replace3 = replace2.replace("&quot;", "\"");        val replace4 = replace3.replace("&nbsp;", "");        val replace5 = replace4.replace("￼", "");        val replace6 = replace5.replace("&reg;", "®");        val replace7 = replace6.replace("&times;", "×");        val replace8 = replace7.replace("&divide;", "÷");        return replace8.replace("&copy;", "©");    }}