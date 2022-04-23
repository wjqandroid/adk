package com.visionvera.psychologist.c.module.knowledge_library.pop;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;

/**
 * Description: 自定义带有输入框的Bottom弹窗
 * Create by dance, at 2019/2/27
 */
public class CustomEditTextBottomPopup extends BottomPopupView {
    private String content;
    private String personName;
    private int personId;
    private ZhihuCommentPopup zhihuCommentPopup;
    private TextView tvPersonName;
    public EditText et_comment;
    private TextView tv_publish;

    public CustomEditTextBottomPopup(@NonNull Context context, String content, String personName, int personId, ZhihuCommentPopup zhihuCommentPopup) {
        super(context);
        this.content = content;
        this.personName = personName;
        this.personId = personId;
        this.zhihuCommentPopup = zhihuCommentPopup;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_edittext_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        tvPersonName = findViewById(R.id.tvPersonName);
        et_comment = findViewById(R.id.et_comment);
        tv_publish = findViewById(R.id.tv_publish);
        tvPersonName.setText("" + personName);
        if (!TextUtils.isEmpty(content)) {
            et_comment.setText(content);
            et_comment.setSelection(et_comment.length());//将光标移至文字末尾
        }
        tv_publish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OneClickUtils.isFastClick()) {
                    return;
                }
                if (TextUtils.isEmpty(et_comment.getText().toString().trim())) {
                    ToastUtils.showShort("请输入你要发表的评论");
                    return;
                }
                //不要直接这样做，会导致消失动画未执行完就跳转界面，不流畅。
//                dismiss();
//                getContext().startActivity(new Intent(getContext(), DemoActivity.class))
                //可以等消失动画执行完毕再开启新界面
                dismissWith(new Runnable() {
                    @Override
                    public void run() {
                        zhihuCommentPopup.net_addComment();
                    }
                });
            }
        });
    }

    @Override
    protected void onShow() {
        super.onShow();

    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
//        Log.e("tag", "CustomEditTextBottomPopup  onDismiss");
    }

    public String getComment() {
        EditText et = findViewById(R.id.et_comment);
        return et.getText().toString();
    }

//    @Override
//    protected int getMaxHeight() {
//        return (int) (XPopupUtils.getWindowHeight(getContext())*0.75);
//    }
}
