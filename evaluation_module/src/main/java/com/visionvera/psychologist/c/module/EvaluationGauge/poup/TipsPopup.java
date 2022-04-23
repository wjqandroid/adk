package com.visionvera.psychologist.c.module.EvaluationGauge.poup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.psychologist.c.R;

import org.jetbrains.annotations.NotNull;

/**
 * 登录/完善信息提示框(用于量表测试时提示)
 */
public class TipsPopup extends CenterPopupView {
    /**
     * 提示去登录标识
     */
    public static String TIP_TYPE_LOGIN = "tipLogin";
    /**
     * 提示去完善信息标识
     */
    public static String TIP_TYPE_INFORMATION = "tipInformation";
    /**
     * 未登录且必须完善信息的量表标识，必须先去登录
     */
    public static String TIP_TYPE_LOGIN_EVPI = "tipLogin_evpi";
    /**
     * 必须完善信息的量表标识，必须先完善信息(判断该标识前必须先登录)
     */
    public static String TIP_TYPE_INFORMATION_EVPI = "tipInformation_evpi";

    private String tipType;
    public OnTipListener tipListener;

    public interface OnTipListener {
        /**
         * @param type 1:去登录 2：去完善信息  3：点击取消
         */
        void OnTipListener(String type);
    }

    private ImageView topImg;
    private TextView titleTv;
    private TextView msgTv;
    private TextView submitTv;
    private ImageView cancelImg;
    private ImageView ysImg;

    /**
     * @param context
     * @param type TIP_TYPE_LOGIN/TIP_TYPE_INFORMATION
     */
    public TipsPopup(@NonNull @NotNull Context context,String type,OnTipListener listener) {
        super(context);
        this.tipType = type;
        this.tipListener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_tip;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        topImg = findViewById(R.id.tip_top_img);
        titleTv = findViewById(R.id.tip_title);
        msgTv = findViewById(R.id.tip_msg);
        submitTv = findViewById(R.id.tip_submit);
        cancelImg = findViewById(R.id.tip_bottom_img);
        ysImg = findViewById(R.id.tip_top_img_ys);

        if(TIP_TYPE_LOGIN.equals(tipType) || TIP_TYPE_LOGIN_EVPI.equals(tipType)){
            if(TIP_TYPE_LOGIN_EVPI.equals(tipType)){
                cancelImg.setVisibility(GONE);
            }
            topImg.setImageResource(R.mipmap.bg_tip_login);
            titleTv.setText("登录您的账号");
            msgTv.setText("登录账号后再测评 \n" +
                    "可以更好的保留您的测评报告记录");
            submitTv.setText("现在登录");
        }else {
            if(TIP_TYPE_INFORMATION_EVPI.equals(tipType)){
                cancelImg.setVisibility(GONE);
            }
            ysImg.setVisibility(VISIBLE);
            topImg.setImageResource(R.mipmap.bg_tip_information);
            titleTv.setText("完善个人信息");
            msgTv.setText("请您先完善您的个人信息");
            submitTv.setText("去完善信息");
        }

        submitTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TIP_TYPE_LOGIN.equals(tipType) || TIP_TYPE_LOGIN_EVPI.equals(tipType)){
                    tipListener.OnTipListener("1");
                }else {
                    tipListener.OnTipListener("2");
                }
                dismiss();
            }
        });

        cancelImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tipListener.OnTipListener("3");
                dismiss();
            }
        });

    }
}
