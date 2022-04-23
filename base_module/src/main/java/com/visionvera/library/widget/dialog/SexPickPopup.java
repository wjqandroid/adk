package com.visionvera.library.widget.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * @author: 刘传政
 * @date: 1/11/21 1:56 PM
 * QQ:1052374416
 * 作用:性别选择弹窗
 * 注意事项:
 */
public class SexPickPopup extends BottomPopupView {

    private ResultListener mListener;

    public SexPickPopup(@NonNull Context context, ResultListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_sex_pick_popup;
    }


    @Override
    protected void onCreate() {
        super.onCreate();

        findViewById(R.id.xmlTvMan).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null) {
                    mListener.onPick(MAN, "男士");
                }
            }
        });
        findViewById(R.id.xmlTvWomen).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null) {
                    mListener.onPick(WOMEN, "女士");
                }
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 相册的点击事件接口
     */
    public interface ResultListener {
        void onPick(@Sex int type, String name);
    }

    public static final int MAN = 1;
    public static final int WOMEN = 2;

    /**
     * 适用方法
     */
    @IntDef({MAN, WOMEN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Sex {
    }
}
