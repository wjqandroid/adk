package com.visionvera.psychologist.c.module.knowledge_library.pop;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.reflect.TypeToken;
import com.lei.lib.java.rxcache.RxCache;
import com.lei.lib.java.rxcache.entity.CacheResponse;
import com.lei.lib.java.rxcache.util.RxUtil;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.orhanobut.logger.Logger;
import com.visionvera.library.base.Constant;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.module.knowledge_library.bean.DraftBean;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.reactivex.functions.Consumer;

/**
 * @author: 刘传政
 * @date: 2019-06-19 16:55
 * QQ:1052374416
 * 作用:选支付方式的弹窗
 * 注意事项:
 */
public class BottomCheckArticleTypePopup extends BottomPopupView {


    private ResultListener listener;
    public static final String CLICK_WRITE = "CLICK_WRITE";
    public static final String CLICK_DRAFT = "CLICK_DRAFT";
    private TextView tvDraftCount;

    public BottomCheckArticleTypePopup(@NonNull Context context, ResultListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.bottom_check_pay_article_pop;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        LinearLayout llWriteArticle = findViewById(R.id.llWriteArticle);
        LinearLayout llDraft = findViewById(R.id.llDraft);
        tvDraftCount = findViewById(R.id.tvDraftCount);
        llWriteArticle.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCkecked(CLICK_WRITE);
            }
            dismiss();
        });

        llDraft.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCkecked(CLICK_DRAFT);
            }
            dismiss();
        });


    }


    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
        //获取缓存
        Type type = new TypeToken<ArrayList<DraftBean>>() {
        }.getType();
        RxCache.getInstance()
                .<ArrayList<DraftBean>>get(Constant.Cache.draftsKey, false, type)   //key:缓存的key update:表示从缓存获取数据强行返回NULL
                .compose(RxUtil.<CacheResponse<ArrayList<DraftBean>>>io_main())
                .subscribe(new Consumer<CacheResponse<ArrayList<DraftBean>>>() {
                    @Override
                    public void accept(CacheResponse<ArrayList<DraftBean>> listCacheResponse) throws Exception {
                        if (listCacheResponse.getData() != null) {
                            Logger.i(Constant.Cache.draftsKey + "获取到了缓存:" + listCacheResponse.getData().toString());
                            int size = listCacheResponse.getData().size();
                            tvDraftCount.setText("草稿箱(" + size + ")");

                        } else {
                            ArrayList<DraftBean> dataList = new ArrayList<>();

                            Logger.i(Constant.Cache.draftsKey + "没获取到缓存 :");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        Logger.i(Constant.Cache.draftsKey + "没获取到缓存" + throwable);
                    }
                });

    }

    //完全消失执行
    @Override
    protected void onDismiss() {

    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .7f);
    }

    @Override
    public int getMinimumHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .2f);
    }


    public interface ResultListener {
        void onCkecked(String event);

    }


}