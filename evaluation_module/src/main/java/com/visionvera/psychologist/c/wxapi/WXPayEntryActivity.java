package com.visionvera.psychologist.c.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.eventbus.PayEventBus;
import com.visionvera.psychologist.c.weixinpay.Constants;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;
    private Button btnFinish;
    private TextView tvTips;
    private int payStatus = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixin_pay_result);
        api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
        api.handleIntent(getIntent(), this);
        btnFinish = findViewById(R.id.btnFinish);
        tvTips = (TextView) findViewById(R.id.tvTips);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postEventAndFinish();
            }
        });
    }

    private void postEventAndFinish() {
        PayEventBus eventBus = new PayEventBus();
        eventBus.status = payStatus;
        eventBus.type = 1;
        EventBus.getDefault().post(eventBus);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.i("onPayFinish, errCode = " + resp.errCode + resp.errStr);
        payStatus = resp.errCode;
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            /**
             * 0	成功	展示成功页面
             * -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
             * -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
             */
            TextView tvTips = (TextView) findViewById(R.id.tvTips);
            switch (resp.errCode) {
                case 0:
                    if (tvTips != null) {
                        tvTips.setText("支付成功");
                    }

                    ToastUtils.showShort("支付成功");
                    break;
                case -1:
                    if (tvTips != null) {
                        tvTips.setText("支付失败");
                    }
                    ToastUtils.showShort("支付失败");
                    break;
                case -2:
                    if (tvTips != null) {
                        tvTips.setText("支付取消");
                    }
                    ToastUtils.showShort("支付取消");
                    break;
                default:
                    break;
            }
            postEventAndFinish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}