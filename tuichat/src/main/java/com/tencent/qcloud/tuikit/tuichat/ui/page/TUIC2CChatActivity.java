package com.tencent.qcloud.tuikit.tuichat.ui.page;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.tencent.qcloud.tuicore.net.OrderConsultContract;
import com.tencent.qcloud.tuicore.net.OrderConsultDetailBean;
import com.tencent.qcloud.tuicore.net.OrderConsultDetailPresenter;
import com.tencent.qcloud.tuicore.net.OrderConsultDetailRequest;
import com.tencent.qcloud.tuicore.net.ReportInformationBeanRequest;
import com.tencent.qcloud.tuicore.util.ToastUtil;
import com.tencent.qcloud.tuikit.tuichat.R;
import com.tencent.qcloud.tuikit.tuichat.TUIChatConstants;
import com.tencent.qcloud.tuikit.tuichat.bean.ChatInfo;
import com.tencent.qcloud.tuikit.tuichat.bean.MessageEvent;
import com.tencent.qcloud.tuikit.tuichat.presenter.C2CChatPresenter;
import com.tencent.qcloud.tuikit.tuichat.util.TUIChatLog;
import com.tencent.qcloud.tuikit.tuichat.util.TUIChatUtils;
import com.visionvera.library.base.bean.BaseResponseBean2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Classname:TUIC2CChatActivity
 * @author:haohuizhao
 * @Date:2021/12/05
 * @Version：v1.0
 * @describe： 图文单聊Activity
 * <p>
 *     文字聊天
 * 1.继承base_module中的BaseMVPLoadActivity修改
 * 2.实现网络请求代码 （上报聊天状态等）
 */
public class TUIC2CChatActivity extends TUIBaseChatActivity {
    private static final String TAG = TUIC2CChatActivity.class.getSimpleName();

    private TUIC2CChatFragment chatFragment;
    private C2CChatPresenter presenter;
    private ChatInfo mChatInfo;
    //订单bean
    private OrderConsultDetailBean orderConsultDetailBean = new OrderConsultDetailBean();


    @Override
    public void initChat(ChatInfo chatInfo) {
        TUIChatLog.i(TAG, "inti chat " + chatInfo);
        mChatInfo = chatInfo;


        if (!TUIChatUtils.isC2CChat(chatInfo.getType())) {
            TUIChatLog.e(TAG, "初始化C2C聊天失败，聊天信息" + chatInfo);
            ToastUtil.toastShortMessage("初始化c2c聊天失败");
        }

        chatFragment = new TUIC2CChatFragment();
        Bundle bundle = new Bundle();
        //聊天信息chatInfo对象在TUIBaseChatActivity获取
        bundle.putSerializable(TUIChatConstants.CHAT_INFO, chatInfo);
        chatFragment.setArguments(bundle);
        presenter = new C2CChatPresenter();
        chatFragment.setPresenter(presenter);
        getSupportFragmentManager().beginTransaction().replace(R.id.empty_view, chatFragment).commitAllowingStateLoss();

        //注册订阅者
        EventBus.getDefault().register(TUIC2CChatActivity.this);
        if (!mChatInfo.getOrderId().isEmpty()) {
            //预约订单详情接口    参数订单id
            OrderConsultDetailRequest request = new OrderConsultDetailRequest(Integer.parseInt(mChatInfo.getOrderId()));
            mPresenter.getOrderConsultDetail(request, this);
        }

    }


    //event_bus接受消息
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        String message = event.getMessage();
        //发送消息成功
        if (message.equals("sendSuccess")) {

            if (orderConsultDetailBean.getResult().getCustomerCall() == 0) {

                //用户发起聊天 0|未发起，1|已发起   customerCall
                //请求接口
                //咨询/诊疗上报操作信息接口
                net_reportInformation(mChatInfo.getOrderId(), mChatInfo.getAppNum(), "1", "", "");
            }
        }
        //收到消息成功

        if (orderConsultDetailBean.getResult().getCustomerAnswer() == 0) {

            if (message.equals("receivedSuccess")) {
                //用户接听 0|否，1|是   customerAnswer
                //请求接口
                //咨询/诊疗上报操作信息接口
                net_reportInformation(mChatInfo.getOrderId(), mChatInfo.getAppNum(), "", "1", "");
            }
        }
//            if (mChatInfo.getCustomerAnswer().equals("0")) {
//
        //        }
    }


    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.OrderConsultDetail.OrderConsultDetailView() {
            //二期
            //咨询/诊疗上报操作信息
            @Override
            public void onReportInformation(BaseResponseBean2 response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        ToastUtils.showLong(errorMsg);
//                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        ToastUtils.showLong(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
//                        ToastUtils.showShort("上报成功");
                        break;
                }
            }

            //心理咨询 预约相关-app获取预约详情接口
            @Override
            public void onOrderConsultDetail(OrderConsultDetailBean bean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        //网络异常等，也就是根本没跟自己服务器正常交互
                        showError(getString(R.string.base_module_net_error));
                        ToastUtils.showLong(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        //也就是自己服务器返回的code值不对
                        showError(getString(R.string.base_module_data_load_error));
                        ToastUtils.showLong(getString(R.string.base_module_data_load_error));
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        //也就是自己服务器返回code正常，result不为null，至于result里边数据空不空等情况不保证
                        orderConsultDetailBean = bean;
                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new OrderConsultDetailPresenter(this, mView);
    }

    /**
     * 新增接口
     * 咨询/诊疗上报操作信息接口
     * 用户发起聊天、用户接听、用户确认服务状态    用于判断按钮显示
     * customerCall  int	N  用户发起聊天 0|未发起，1|已发起
     * customerAnswer  int	n用户接听 0|否，1|是
     * customerConfirm int	n 用户确认 0|未确认，1|已确认
     */
    private void net_reportInformation(String id, String appNum, String customerCall, String customerAnswer, String customerConfirm) {
        ReportInformationBeanRequest reportInformationBeanRequest = new ReportInformationBeanRequest();
        reportInformationBeanRequest.setId(id);//主键ID
        reportInformationBeanRequest.setAppNum(appNum);//订单编号
        reportInformationBeanRequest.setCustomerCall(customerCall);//订单编号
        reportInformationBeanRequest.setCustomerAnswer(customerAnswer);//订单编号
        reportInformationBeanRequest.setCustomerConfirm(customerConfirm);//订单编号
        mPresenter.reportInformation(reportInformationBeanRequest, activity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        EventBus.getDefault().unregister(this);
    }
}
