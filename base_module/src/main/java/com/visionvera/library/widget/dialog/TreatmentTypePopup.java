package com.visionvera.library.widget.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.R;



/**
 * @Classname:TreatmentTypePopup
 * @author:haohuizhao
 * @Date:2021/10/28 15:59
 * @Version：v1.0
 * @describe： 预约诊疗的类型选择框
 */


public class TreatmentTypePopup extends BottomPopupView {

    private VideoListener mVideoListener;
    private VoiceListener mVoiceListener;
    private WordListener mWordListener;

    public TreatmentTypePopup(@NonNull Context context, VideoListener videoListener, WordListener wordListener, VoiceListener voiceListener) {
        super(context);
        mVideoListener = videoListener;
        mVoiceListener = voiceListener;
        mWordListener = wordListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_treatment_popup;
    }


    @Override
    protected void onCreate() {
        super.onCreate();

        findViewById(R.id.evaluation_module_video_consult).setOnClickListener(v -> mVideoListener.videoClickListener());
        findViewById(R.id.evaluation_module_word_consult).setOnClickListener(v -> mWordListener.wordClickListener());

        findViewById(R.id.evaluation_module_voice_consult).setOnClickListener(v -> mVoiceListener.voiceClickListener());
        findViewById(R.id.evaluation_module_consult_cancel).setOnClickListener(v -> dismiss());
    }

    /**
     * 视频的点击事件接口
     */
    public interface VideoListener {
        void videoClickListener();
    }

    /**
     * 文字咨询的点击
     */
    public interface WordListener {
        void wordClickListener();
    }

    /**
     * 语音的点击事件接口
     */
    public interface VoiceListener {
        void voiceClickListener();
    }

}
