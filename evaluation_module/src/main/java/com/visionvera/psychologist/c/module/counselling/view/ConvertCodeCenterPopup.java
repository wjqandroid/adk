package com.visionvera.psychologist.c.module.counselling.view;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.visionvera.psychologist.c.R;

/**
 * @author: 刘传政
 * @date: 12/24/20 1:31 PM
 * QQ:1052374416
 * 作用:兑换码输入弹窗
 * 注意事项:
 */
public class ConvertCodeCenterPopup extends CenterPopupView {

    private String content;


    public ConvertCodeCenterPopup(@NonNull Context context) {
        super(context);
        this.content = content;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_convert_codecenter_pop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();


    }


}
