package com.visionvera.library.widget.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.R;


/**
 * author lilongfeng
 * date 2019/6/21
 * 视频诊疗，文字诊疗，线下诊疗
 */

public class TreatmentTypePopups extends BottomPopupView {

    private OnCheckListener mOnCheckListener;

    public TreatmentTypePopups(@NonNull Context context, OnCheckListener onCheckListener) {
        super(context);
        mOnCheckListener = onCheckListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_video_xianxia_popup;
    }


    @Override
    protected void onCreate() {
        super.onCreate();


        findViewById(R.id.evaluation_module_video_consult).setOnClickListener(v ->

                mOnCheckListener.onCheck(TreatmentType.VIDEO, "视频")
        );

        findViewById(R.id.evaluation_module_word_pic).setOnClickListener(v -> {
            mOnCheckListener.onCheck(TreatmentType.WORD, "文字");
        });

        findViewById(R.id.evaluation_module_voice_consult).setOnClickListener(v -> mOnCheckListener.onCheck(TreatmentType.VOICE, "语音"));
        findViewById(R.id.evaluation_module_offline_consult).setOnClickListener(v -> mOnCheckListener.onCheck(TreatmentType.OFFLINE, "线下门诊"));

        findViewById(R.id.evaluation_module_consult_cancel).setOnClickListener(v -> {
                    dismiss();
                    mOnCheckListener.onCancel();
                }
        );
    }


    /**
     * 诊疗形式
     */
    public enum TreatmentType {
        VIDEO, WORD, VOICE, OFFLINE
    }

    public interface OnCheckListener {
        void onCheck(TreatmentType type, String tips);

        void onCancel();
    }

}
