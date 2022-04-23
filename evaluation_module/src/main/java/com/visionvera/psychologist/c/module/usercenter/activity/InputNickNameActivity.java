package com.visionvera.psychologist.c.module.usercenter.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseMVPActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: 刘传政
 * @date: 12/29/20 4:25 PM
 * QQ:1052374416
 * 作用: 输入昵称界面
 * 注意事项:
 */
public class InputNickNameActivity extends BaseMVPActivity {
    @BindView(R2.id.et_nickname)
    EditText et_nickname;
    public static int REQUEST_CODE = 111;
    public static int RESULT_CODE = 222;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, InputNickNameActivity.class));
    }

    public static void startActivityForResult(Context context) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(new Intent(context,
                            InputNickNameActivity.class),
                    REQUEST_CODE);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_input_nick_name;
    }

    @Override
    protected void doYourself() {

        initView();
        initData();
    }

    private void initData() {

    }


    private void initView() {
        showSoftInputFromWindow(this, et_nickname);
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @OnClick({R2.id.tvCancel, R2.id.tvSubmit})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.tvCancel) {
            finish();
        } else if (id == R.id.tvSubmit) {
            Intent intentResult = new Intent();
            ResultIntentBean resultIntentBean = new ResultIntentBean();
            resultIntentBean.nickname = et_nickname.getText().toString().trim();
            intentResult.putExtra(Constant.IntentKey.requestReturnCode, resultIntentBean);
            setResult(RESULT_CODE, intentResult);
            finish();
        }

    }

    @Override
    protected void initMVP() {
    }

    public static class ResultIntentBean implements Serializable {
        public String nickname = "";
    }

}
