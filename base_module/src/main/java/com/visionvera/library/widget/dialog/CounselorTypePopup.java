package com.visionvera.library.widget.dialog;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.library.R;


/**
 * @author: 刘传政
 * @date: 2020/3/30 15:06
 * QQ:1052374416
 * 作用: 预约咨询的类型选择框
 * 虽然写了3个类型,但目前只有一个类型可选
 * 注意事项:
 */

public class CounselorTypePopup extends BottomPopupView {

    private VideoListener mVideoListener;
    private VoiceListener mVoiceListener;
    private WordListener mWordListener;
    private Boolean mIsTextMake;
    private Boolean mIsVoiceMake;
    private Boolean mIsVideoMake;

    public CounselorTypePopup(@NonNull Context context, VideoListener videoListener, WordListener wordListener, VoiceListener voiceListener,Boolean isTextMake, Boolean isVoiceMake, Boolean isVideoMake) {
        super(context);
        mVideoListener = videoListener;
        mVoiceListener = voiceListener;
        mWordListener = wordListener;
        mIsTextMake = isTextMake;
        mIsVoiceMake = isVoiceMake;
        mIsVideoMake = isVideoMake;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_video_voice_popup;
    }


    @Override
    protected void onCreate() {
        super.onCreate();

        TextView evaluation_module_word_consult = findViewById(R.id.evaluation_module_word_consult);
        evaluation_module_word_consult.setOnClickListener(v -> mWordListener.wordClickListener());
        if (!mIsTextMake) {
            evaluation_module_word_consult.setVisibility(GONE);
        }


        TextView evaluation_module_voice_consult = findViewById(R.id.evaluation_module_voice_consult);
        evaluation_module_voice_consult.setOnClickListener(v -> mVoiceListener.voiceClickListener());
        if (!mIsVoiceMake) {
            evaluation_module_voice_consult.setVisibility(GONE);
        }

        LinearLayout evaluation_module_video_consult = findViewById(R.id.evaluation_module_video_consult);
        evaluation_module_video_consult.setOnClickListener(v -> mVideoListener.videoClickListener());
        if (!mIsVideoMake) {
            evaluation_module_video_consult.setVisibility(GONE);
        }
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
