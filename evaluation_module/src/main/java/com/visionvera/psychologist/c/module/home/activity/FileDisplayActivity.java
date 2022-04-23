package com.visionvera.psychologist.c.module.home.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.utils.DownloadUtil;

import java.io.File;

//政策汇编文件
//下载PDF文件并展示
public class FileDisplayActivity extends Activity {


    public static PDFView pdfView;


    private static ProgressBar progressBar_download;
    private ImageView btn_back;
    private TextView evaluation_module_tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.evaluation_module_activity_file_display);


        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        String name = intent.getStringExtra("fileName");

        btn_back = (ImageView) findViewById(R.id.evaluation_module_iv_back);
        evaluation_module_tv_title = (TextView) findViewById(R.id.evaluation_module_tv_title);
        //标题
        evaluation_module_tv_title.setText(name);
        pdfView = (PDFView) findViewById(R.id.pdf_view);
        progressBar_download = (ProgressBar) findViewById(R.id.progressBar_download);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 跳转页面
     *
     * @param context
     * @param fileUrl  文件url
     * @param fileName 文件名
     */
    public static void actionStart(Context context, String fileUrl, String fileName) {
        Intent intent = new Intent(context, FileDisplayActivity.class);
        intent.putExtra("fileUrl", fileUrl);
        intent.putExtra("fileName", fileName);
        context.startActivity(intent);
        //"http://www.cztouch.com/upfiles/soft/testpdf.pdf"
        DownloadUtil.get().download(fileUrl, getDiskCacheDir(context).toString(), "file." + fileName, new DownloadUtil.OnDownloadListener() {

            @Override
            public void onDownloadSuccess(File file) {//下载成功
                Log.e("TAG", "" + file.toString());
//                /storage/emulated/0/Android/data/com.visionvera.psychologist.c/cache/file.testpdf.pdf

                pdfView.fromFile(file)
                        .defaultPage(0)
                        .enableAnnotationRendering(true)
                        .scrollHandle(null)
                        .load();
                pdfView.resetZoom();


            }

            @Override
            public void onDownloading(int progress) {//下载进度
//                Log.e("progress:===", progress + "");
                progressBar_download.setProgress(progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {//下载异常
//                Log.e("progress:===", "Exception" + e);
                ToastUtils.showShort("加载失败！");
//                if (!Storage.getFilePath().equals("")) {
//                    openFile(Storage.getFilePath());
//                }
            }
        });

    }


    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

}
