package com.visionvera.psychologist.c.update;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.visionvera.psychologist.c.R;


public class DownLoadDialog extends Dialog {
    private DownLoadListener downLoadListener;
    //    private ProgressBar progressBar;
    private NumberProgressView progressBar;

    public void setDownLoadListener(DownLoadListener downLoadListener) {
        this.downLoadListener = downLoadListener;
    }

    public interface DownLoadListener {
        void onDissmiss();
    }

    public DownLoadDialog(Activity activity, String title, String bottom) {
        super(activity);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(activity).inflate(R.layout.softupdate_progress, null);

        TextView titleTv = view.findViewById(R.id.tv_soft_update);
        progressBar = view.findViewById(R.id.update_progress);
        TextView bottomTv = view.findViewById(R.id.tv_download_cancle);

        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
        }

        if (!TextUtils.isEmpty(bottom)) {
            bottomTv.setVisibility(View.VISIBLE);
            bottomTv.setText(bottom);
        }

        bottomTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadListener.onDissmiss();
                cancel();
            }
        });

        setContentView(view);
//        setCanceledOnTouchOutside(false);
//        WindowManager m = activity.getWindowManager();
//        Window dialogWindow = getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        Display d = m.getDefaultDisplay();
//        lp.width = (int) (d.getWidth() * 0.8);
//        lp.height = (int) (d.getHeight() * 0.2);
//        dialogWindow.setAttributes(lp);
//        dialogWindow.setContainer(null);
//		dialogWindow.setBackgroundDrawable(null);
//        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);//去掉dialog默认黑色背景
        show();
    }

    //设置progress进度
    public void setProgress(int progress) {
        progressBar.setProgress(progress);

    }

}
