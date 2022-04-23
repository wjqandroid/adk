package com.visionvera.library.base.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebViewClient;
import com.orhanobut.logger.Logger;
import com.visionvera.library.R;
import com.visionvera.library.base.BaseActivity;


/**
* author:lilongfeng
* date:2020/2/15
* 描述:协议页面
*/

public class ProtocolActivity extends BaseActivity {


    private AgentWeb mAgentWeb;

    public static void startActivity(Context context,String webUrl,String title){
        context.startActivity(new Intent(context,ProtocolActivity.class).putExtra("url",webUrl).putExtra("title",title));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_module_activity_protocol;
    }

    @Override
    protected void doYourself() {

        String webUrl=getIntent().getStringExtra("url");
        String title=getIntent().getStringExtra("title");
        Logger.i("url:" + webUrl + "\ntitle:" + title);

        LinearLayout  agent_webview_container= findViewById(R.id.agent_webview_container);
        RelativeLayout rl_back=findViewById(R.id.rl_back);
        TextView tv_title=findViewById(R.id.tv_title);
        TextView tv_right=findViewById(R.id.tv_right);

        tv_right.setVisibility(View.GONE);
        tv_title.setText(title);
        rl_back.setOnClickListener(v -> finish());

        /**
         * https://github.com/Justson/AgentWeb
         */
        WebViewClient webViewClient=new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //去掉网页自有的h1标签的标题.
//                view.loadUrl("javascript:(function() { document.getElementsByTagName('h1')[0].style.display='none'; })()");
            }

        };

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) agent_webview_container, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setWebViewClient(webViewClient)
                .createAgentWeb()
                .ready()
                .go(webUrl);





    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }


    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
