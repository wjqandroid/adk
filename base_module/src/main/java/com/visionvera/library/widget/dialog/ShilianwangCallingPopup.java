package com.visionvera.library.widget.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.lxj.xpopup.impl.FullScreenPopupView;
import com.visionvera.library.R;

/**
 * 视联网呼叫页面。
 */
public class ShilianwangCallingPopup extends FullScreenPopupView {

    private CloseCallInterface closeCallInterface;

    public ShilianwangCallingPopup(@NonNull Context context,CloseCallInterface closeCallInterface) {
        super(context);
        this.closeCallInterface=closeCallInterface;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.base_module_shilianwang_calling_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        findViewById(R.id.reject_button).setOnClickListener(v -> {
            if (closeCallInterface!=null){
                closeCallInterface.claoseCalling();
            }
        });
    }


    /**
     * 挂断
     */
    public interface  CloseCallInterface{
        void claoseCalling();
    }
}
