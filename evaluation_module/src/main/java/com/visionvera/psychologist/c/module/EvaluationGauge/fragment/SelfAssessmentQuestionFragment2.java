package com.visionvera.psychologist.c.module.EvaluationGauge.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.google.gson.Gson;
import com.visionvera.library.util.EmojiFilter;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.BaiduVoiceBean;
import com.visionvera.psychologist.c.module.EvaluationGauge.bean.QuestionTypeIntentData;
import com.visionvera.psychologist.c.utils.baiduvoice.AutoCheck;
import com.visionvera.psychologist.c.utils.baiduvoice.core.recog.MyRecognizer;
import com.visionvera.psychologist.c.utils.baiduvoice.core.recog.listener.ChainRecogListener;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 自评量表的题目的fragment，语音输入
 */
public class SelfAssessmentQuestionFragment2 extends QuestionBaseFragment implements EventListener {

    @BindView(R2.id.input_text)
    EditText input_text;

    private static String QUESTION_TYPE2_STRING = "QUESTION_TYPE2_STRING";

    private EventManager asr;
//    private DigitalDialogInput input;
//    protected int textViewLines = 0; // 防止textView中文本过多
//    protected Handler handler;
//    protected boolean running = false;
    /**
     * 识别控制器，使用MyRecognizer控制识别的流程
     */
    protected MyRecognizer myRecognizer;

    private ChainRecogListener chainRecogListener;

    public static SelfAssessmentQuestionFragment2 getInstance(QuestionTypeIntentData intentData){
        SelfAssessmentQuestionFragment2 fragment=new SelfAssessmentQuestionFragment2();
        Bundle bundle = new Bundle();
        bundle.putSerializable(QUESTION_TYPE2_STRING, intentData);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.evaluation_module_fragment_question_2;
    }

    @Override
    protected void initYourself() {

        initBaiduVoice();

        getIntentData();

        initView();


//        initBaiduDialogVoice();

    }

    private void initView() {
        input_text.setFilters(new InputFilter[]{new EmojiFilter()});
        input_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    mAnswerId=0;
                }else{
                    mAnswerId=-1;
                }
            }
        });
    }

    private void getIntentData() {
        mIntentData = (QuestionTypeIntentData) getArguments().getSerializable(QUESTION_TYPE2_STRING);

        mCurrentQuestionNum=mIntentData.getScaleNum();





    }

   /* private void initBaiduDialogVoice() {

        handler = new Handler() {

            *//*
             * @param msg
             *//*
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMsg(msg);
            }

        };


        *//**
         * 有2个listner，一个是用户自己的业务逻辑，如MessageStatusRecogListener。另一个是UI对话框的。
         * 使用这个ChainRecogListener把两个listener和并在一起
         *//*
        chainRecogListener = new ChainRecogListener();
        // DigitalDialogInput 输入 ，MessageStatusRecogListener可替换为用户自己业务逻辑的listener
        chainRecogListener.addListener(new MessageStatusRecogListener(handler));
        myRecognizer.setEventListener(chainRecogListener); // 替换掉原来的listener
    }*/

    private void initBaiduVoice() {
        // 基于sdk集成1.1 初始化EventManager对象
        asr = EventManagerFactory.create(getActivity(), "asr");
        // 基于sdk集成1.3 注册自己的输出事件类
        asr.registerListener(this); //  EventListener 中 onEvent方法



    }

   /* protected void handleMsg(Message msg) {
        if (input_text != null && msg.obj != null) {
            textViewLines++;
            if (textViewLines > 100) {
                textViewLines = 0;
                input_text.setText("");
            }
            input_text.append(msg.obj.toString() + "\n");
        }
    }
*/
    @OnClick(R2.id.evaluation_module_voice_iv)
    public void onClick(View view){
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int id=view.getId();
        if (id==R.id.evaluation_module_voice_iv){
            Map<String, Object> params = new LinkedHashMap<String, Object>();
            String event = null;
            event = SpeechConstant.ASR_START; // 替换成测试的event
            // 基于SDK集成2.1 设置识别参数
            params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
            (new AutoCheck(getActivity().getApplicationContext(), new Handler() {
                public void handleMessage(Message msg) {
                    if (msg.what == 100) {
                        AutoCheck autoCheck = (AutoCheck) msg.obj;
                        synchronized (autoCheck) {
                            String message = autoCheck.obtainErrorMessage(); // autoCheck.obtainAllMessage();
                            // input_text.append(message + "\n");
                            ; // 可以用下面一行替代，在logcat中查看代码
                            // Log.w("AutoCheckMessage", message);
                        }
                    }
                }
            },false)).checkAsr(params);
            String json = null; // 可以替换成自己的json
            json = new JSONObject(params).toString(); // 这里可以替换成你需要测试的json
            asr.send(event, json, null, 0, 0);


          /*  // 此处params可以打印出来，直接写到你的代码里去，最终的json一致即可。
            final Map<String, Object> params=new HashMap<>();


            // BaiduASRDigitalDialog的输入参数
            input = new DigitalDialogInput(myRecognizer, chainRecogListener, params);
            BaiduASRDigitalDialog.setInput(input); // 传递input信息，在BaiduASRDialog中读取,
            Intent intent = new Intent(getActivity(), BaiduASRDigitalDialog.class);

            // 修改对话框样式
            // intent.putExtra(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_ORANGE_DEEPBG);

            running = true;
            startActivityForResult(intent, 2);*/


        }
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        if (params!=null){
            BaiduVoiceBean baiduVoiceBean=new Gson().fromJson(params,BaiduVoiceBean.class);
            if (baiduVoiceBean.getBest_result()!=null){
                input_text.setText(baiduVoiceBean.getBest_result());
            }
        }


    }


   /* @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            running = false;
            Log.i(TAG, "requestCode" + requestCode);
            if (requestCode == 2) {
                String message = "对话框的识别结果：";
                if (resultCode == RESULT_OK) {
                    ArrayList results = data.getStringArrayListExtra("results");
                    if (results != null && results.size() > 0) {
                        message += results.get(0);
                    }
                } else {
                    message += "没有结果";
                }
                MyLogger.info(message);
            }

    }
*/


    @Override
    public void onPause() {
        super.onPause();
        asr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 基于SDK集成4.2 发送取消事件
        asr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);

        // 基于SDK集成5.2 退出事件管理器
        // 必须与registerListener成对出现，否则可能造成内存泄露
        asr.unregisterListener(this);
    }


}
