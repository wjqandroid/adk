package com.visionvera.psychologist.c.utils.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.visionvera.library.base.Constant;
import com.visionvera.psychologist.c.eventbus.PayEventBus;
import com.visionvera.psychologist.c.module.counselling.bean.AliPrePayRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.AliPrePayResponseBean;
import com.visionvera.psychologist.c.module.counselling.bean.PayResult;
import com.visionvera.psychologist.c.module.counselling.bean.WeixinPrePayRequestBean;
import com.visionvera.psychologist.c.module.counselling.bean.WeixinPrePayResponseBean;
import com.visionvera.psychologist.c.net.EvaluationModuleModel;
import com.visionvera.psychologist.c.weixinpay.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author 刘传政
 * @date 2020/9/28 08:55
 * QQ:1052374416
 * 电话:18501231486
 * 作用:封装了支付宝和微信的预下单到支付的流程.不是单例.
 * 注意事项:
 */
public class PayUtil implements LifecycleObserver {
    RxAppCompatActivity activity;
    private OnResultListener aliListener;
    private OnResultListener weixinListener;

    public PayUtil(RxAppCompatActivity activity) {
        if (activity == null) {
            throw new IllegalStateException("activity不能为空");
        }
        this.activity = activity;
        activity.getLifecycle().addObserver(this);
        EventBus.getDefault().register(this);
    }

    public void aliPay(PayBean payBean, OnResultListener listener) {

        if (listener == null) {
            throw new IllegalStateException("监听不能为空");
        }
        if (payBean == null) {
            throw new IllegalStateException("payBean不能为空");
        }
        aliListener = listener;
        net_aliPrePay(payBean);
    }

    /**
     * 支付宝预下单
     */
    private void net_aliPrePay(PayBean payBean) {
        AliPrePayRequestBean requestBean = new AliPrePayRequestBean();
        requestBean.payMethod = 4;
        requestBean.totalAmount = payBean.totalAmount;
        requestBean.goodsOrderNo = payBean.goodsOrderNo;

        requestBean.safe = "ignore";
        requestBean.payUserId = payBean.payUserId;
        requestBean.payUserName = payBean.payUserName;

        Logger.i("支付宝支付预下单请求参数" + requestBean.toString());
        EvaluationModuleModel.OrderConsult model = new EvaluationModuleModel().new OrderConsult();
        model.aliPrePay(requestBean, activity, new Observer<AliPrePayResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AliPrePayResponseBean bean) {

                if (bean.code == Constant.NetCode.success2) {
                    String preOrderInfo = bean.result.result;
                    aliPayV2(activity, preOrderInfo);
                } else {
                    aliListener.onResult(-1, "预下单失败");
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                aliListener.onResult(-2, "预下单失败");

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 支付宝支付业务示例
     */
    public void aliPayV2(Activity activity, String orderInfo) {
        Logger.i("支付参数:" + orderInfo);

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                //用户在商户app内部点击付款，是否需要一个 loading 做为在钱包唤起之前的过渡，这个值设置为 true，将会在调用 pay 接口的时候直接唤起一个
                // loading，直到唤起H5支付页面或者唤起外部的钱包付款页面 loading 才消失。（建议将该值设置为 true，优化点击付款到支付唤起支付页面的过渡过程。）
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //回调成功
                        Logger.i("支付成功:" + payResult);
                        PayEventBus eventBus = new PayEventBus();
                        eventBus.status = 0;
                        eventBus.type = 0;
                        EventBus.getDefault().post(eventBus);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        //回调失败
                        Logger.i("支付失败:" + payResult);
                        PayEventBus eventBus = new PayEventBus();
                        eventBus.status = -1;
                        eventBus.type = 0;
                        EventBus.getDefault().post(eventBus);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    public void weixinPay(PayBean payBean, OnResultListener listener) {

        if (listener == null) {
            throw new IllegalStateException("监听不能为空");
        }
        if (payBean == null) {
            throw new IllegalStateException("payBean不能为空");
        }
        weixinListener = listener;
        net_weixinPrePay(payBean);
    }

    /**
     * 微信预下单
     */
    private void net_weixinPrePay(PayBean payBean) {
        WeixinPrePayRequestBean requestBean = new WeixinPrePayRequestBean();
        requestBean.payMethod = 3;
        requestBean.totalAmount = payBean.totalAmount;
        requestBean.goodsOrderNo = payBean.goodsOrderNo;

        requestBean.safe = "ignore";
        requestBean.payUserId = payBean.payUserId;
        requestBean.payUserName = payBean.payUserName;
        Logger.i("微信支付预下单请求参数" + requestBean.toString());
        EvaluationModuleModel.OrderConsult model = new EvaluationModuleModel().new OrderConsult();
        model.weixinPrePay(requestBean, activity, new Observer<WeixinPrePayResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeixinPrePayResponseBean bean) {

                if (bean.code == Constant.NetCode.success2) {
                    weixinPay(activity, bean.result);
                } else {
                    weixinListener.onResult(-1, "预下单失败");
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                weixinListener.onResult(-2, "预下单失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void weixinPay(Activity activity, WeixinPrePayResponseBean.ResultBean result) {
        if (result == null) {
            return;
        }
        //初始化微信
        IWXAPI api = WXAPIFactory.createWXAPI(activity, Constants.WX_APP_ID);

        //检查微信版本是否支持支付
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (!isPaySupported) {
            ToastUtils.showLong("未安装微信或当前微信版本过低不支持支付");
            return;
        }
        /**
         * 应用ID	            appid	      String(32)	是	wx8888888888888888	微信开放平台审核通过的应用APPID
         * 商户号	            partnerid	String(32)	是	1900000109	微信支付分配的商户号
         * 预支付交易会话ID	    prepayid	String(32)	是	WX1217752501201407033233368018	微信返回的支付交易会话ID
         * 扩展字段	            package	     String(128)	是	Sign=WXPay	暂填写固定值Sign=WXPay
         * 随机字符串	        noncestr	String(32)	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
         * 时间戳	            timestamp	String(10)	是	1412000000	时间戳，请见接口规则-参数规定
         * 签名	                sign	     String(32)	是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法注意：签名方式一定要与统一下单接口使用的一致
         */
        PayReq request = new PayReq();
//        request.appId = Constants.WX_APP_ID;
//        request.partnerId = "1532360841";
//        request.prepayId = "wx2416113709872309282459cc3a25b70000";
//        request.packageValue = "Sign=WXPay";
//        request.nonceStr = "65b0df23fd2d449ae1e4b2d27151d73b";
//        request.timeStamp = "1600935097";
//        request.sign = "7A45B7D080A541513B37577937C90574";
        request.appId = Constants.WX_APP_ID;
        request.partnerId = result.partnerid + "";
        request.prepayId = result.prepayid + "";
        request.packageValue = result.packageX;
        request.nonceStr = result.noncestr + "";
        request.timeStamp = result.timestamp + "";
        request.sign = result.sign + "";
        api.sendReq(request);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onEventBus(PayEventBus busBean) {
        //接收到了支付状态消息
        if (busBean.type == 0) {
            //支付宝支付
            String tips = "";
            if (busBean.status == 0) {
                //支付成功
                tips = "支付成功";
            } else if (busBean.status == -1) {
                tips = "支付失败";
            } else if (busBean.status == -2) {
                tips = "支付取消";
            }
            aliListener.onResult(busBean.status, tips);
        } else if (busBean.type == 1) {
            //微信支付
            String tips = "";
            if (busBean.status == 0) {
                //支付成功
                tips = "支付成功";
            } else if (busBean.status == -1) {
                tips = "支付失败";
            } else if (busBean.status == -2) {
                tips = "支付取消";
            }
            weixinListener.onResult(busBean.status, tips);
        }


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        Logger.i("监听到使用者onDestroy");
        EventBus.getDefault().unregister(this);
    }

    public static class PayBean implements Serializable {
        //1-微信扫码支付 2-支付宝扫码支付 3-微信APP支付 4- 支付宝APP支付
        public int payMethod;
        //支付金额
        public Double totalAmount;
        //商品订单号
        public String goodsOrderNo;
        //接口安全参数值ignore | false
        public String safe;
        //若safe=ignore 可选
        public String sign;
        //付款人userId
        public String payUserId;
        //付款人姓名
        public String payUserName;

    }

    public interface OnResultListener {
        /**
         * @param status //支付状态  0完成 -1 失败   -2 取消
         * @param msg
         */
        void onResult(int status, String msg);
    }
}
