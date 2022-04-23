package com.visionvera.psychologist.c.module.home.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.home.bean.EveryDaySignInBean;
import com.visionvera.psychologist.c.module.home.bean.SignInBean;
import com.visionvera.psychologist.c.module.home.contract.IContract;
import com.visionvera.psychologist.c.module.home.presenter.EveryDaySignInPresenter;
import com.visionvera.psychologist.c.widget.StepView;
import com.visionvera.psychologist.c.widget.popup.SignInPopup;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:lilongfeng
 * date:2020/1/13
 * 描述:打卡页面
 */

public class EveryDaySignInActivity extends BaseMVPLoadActivity<IContract.EveryDaySignIn.EveryDaySignInView, EveryDaySignInPresenter> {

    @BindView(R2.id.evaluation_module_sign_in_view)
    StepView sign_in_view;

    @BindView(R2.id.evaluation_module_sign_in_commit)
    TextView sign_in_commit;

    @BindView(R2.id.current_day)
    TextView current_day;

    @BindView(R2.id.current_weekday)
    TextView current_weekday;

    @BindView(R2.id.current_month_year)
    TextView current_month_year;
    private Calendar calendar;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, EveryDaySignInActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_everyday_signin;
    }

    @Override
    protected void doYourself() {

        getSignInByWeek();

        calendar = Calendar.getInstance();

        current_day.setText(fillUpTwoNumber(calendar.get(Calendar.DAY_OF_MONTH) + ""));

        current_weekday.setText(getCurrentWeekDay() + "");

        current_month_year.setText(getCurrentMonth() + "月" + calendar.get(Calendar.YEAR));


    }

    public String getCurrentWeekDay() {
        String currentWeek = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                currentWeek = "周日";
                break;
            case 2:
                currentWeek = "周一";
                break;
            case 3:
                currentWeek = "周二";
                break;
            case 4:
                currentWeek = "周三";
                break;
            case 5:
                currentWeek = "周四";
                break;
            case 6:
                currentWeek = "周五";
                break;
            case 7:
                currentWeek = "周六";
                break;
        }
        return currentWeek;
    }

    public String getCurrentMonth() {
        String currentMonth = "";
        switch (calendar.get(Calendar.MONTH) + 1) {
            case 1:
                currentMonth = "一";
                break;
            case 2:
                currentMonth = "二";
                break;
            case 3:
                currentMonth = "三";
                break;
            case 4:
                currentMonth = "四";
                break;
            case 5:
                currentMonth = "五";
                break;
            case 6:
                currentMonth = "六";
                break;
            case 7:
                currentMonth = "七";
                break;
            case 8:
                currentMonth = "八";
                break;
            case 9:
                currentMonth = "九";
                break;
            case 10:
                currentMonth = "十";
                break;
            case 11:
                currentMonth = "十一";
                break;
            case 12:
                currentMonth = "十二";
                break;
        }
        return currentMonth;
    }

    /**
     * 补齐两位
     */
    public String fillUpTwoNumber(String number) {
        if (number.length() == 1) {
            return "0" + number;
        } else if (number.length() == 2) {
            return number;
        } else {
            return "00";
        }
    }


    @OnClick({R2.id.evaluation_module_sign_in_commit, R2.id.evaluation_module_back_white_iv})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.evaluation_module_sign_in_commit) {

            showLoadingDialog();
            mPresenter.getSaveSignIn(this);

        } else if (viewId == R.id.evaluation_module_back_white_iv) {
            finish();
        }
    }

    @Override
    protected void initMVP() {
        mView = new IContract.EveryDaySignIn.EveryDaySignInView() {
            @Override
            public void onSignInByWeek(EveryDaySignInBean bean, ResultType resultType, String errorMsg) {
                switch (resultType) {
                    case NET_ERROR:
                        showError(errorMsg);
                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:
                        showError(errorMsg);
                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        showNormal();

                        showSevenDay(bean.getResult());
                        break;
                    default:
                }
            }

            @Override
            public void onSaveSignIn(SignInBean response, ResultType resultType, String errorMsg) {
                hideDialog();
                switch (resultType) {
                    case NET_ERROR:

                        ToastUtils.showShort(getString(R.string.base_module_net_error));
                        break;
                    case SERVER_ERROR:

                        ToastUtils.showShort(errorMsg);
                        break;
                    case SERVER_NORMAL_DATANO:
                    case SERVER_NORMAL_DATAYES:
                        int successImgId=R.drawable.evaluation_module_signin_success_3;
                        if (response.getResult().getUpdateNum()==3){
                            successImgId=R.drawable.evaluation_module_signin_success_3;
                        }else if (response.getResult().getUpdateNum()==30){
                            successImgId=R.drawable.evaluation_module_signin_success_30;
                        }else{

                        }
                        new XPopup.Builder(activity)
                                .asCustom(new SignInPopup(activity,successImgId))
                                .show();

//                        updateSignUpDay();
                        mPresenter.getSignInByWeek(activity);
                        break;
                    default:
                }


            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new EveryDaySignInPresenter(this, mView);
    }

    /**
     * 打卡成功后的更改打卡状态
     */
   /* private void updateSignUpDay() {
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        switch (currentDay) {
            case 1:
                sign_in_view.setDay1SignIn(this, true);
                break;
            case 2:
                sign_in_view.setDay2SignIn(this, true);
                break;
            case 3:
                sign_in_view.setDay3SignIn(this, true);
                break;
            case 4:
                sign_in_view.setDay4SignIn(this, true);
                break;
            case 5:
                sign_in_view.setDay5SignIn(this, true);
                break;
            case 6:
                sign_in_view.setDay6SignIn(this, true);
                break;
            case 7:
                sign_in_view.setDay7SignIn(this, true);
                break;
        }


    }*/
    private void showSevenDay(List<EveryDaySignInBean.ResultBean> result) {

        if (result.size() == 7) {
            sign_in_view.setDay1SignIn(this, Boolean.parseBoolean(result.get(0).getFlag()));
            sign_in_view.setDay2SignIn(this, Boolean.parseBoolean(result.get(1).getFlag()));
            sign_in_view.setDay3SignIn(this, Boolean.parseBoolean(result.get(2).getFlag()));
            sign_in_view.setDay4SignIn(this, Boolean.parseBoolean(result.get(3).getFlag()));
            sign_in_view.setDay5SignIn(this, Boolean.parseBoolean(result.get(4).getFlag()));
            sign_in_view.setDay6SignIn(this, Boolean.parseBoolean(result.get(5).getFlag()));
            sign_in_view.setDay7SignIn(this, Boolean.parseBoolean(result.get(6).getFlag()));
        }
        boolean weekday1 = calendar.get(Calendar.DAY_OF_WEEK) == 2 && Boolean.parseBoolean(result.get(0).getFlag());
        boolean weekday2 = calendar.get(Calendar.DAY_OF_WEEK) == 3 && Boolean.parseBoolean(result.get(1).getFlag());
        boolean weekday3 = calendar.get(Calendar.DAY_OF_WEEK) == 4 && Boolean.parseBoolean(result.get(2).getFlag());
        boolean weekday4 = calendar.get(Calendar.DAY_OF_WEEK) == 5 && Boolean.parseBoolean(result.get(3).getFlag());
        boolean weekday5 = calendar.get(Calendar.DAY_OF_WEEK) == 6 && Boolean.parseBoolean(result.get(4).getFlag());
        boolean weekday6 = calendar.get(Calendar.DAY_OF_WEEK) == 7 && Boolean.parseBoolean(result.get(5).getFlag());
        boolean weekday7 = calendar.get(Calendar.DAY_OF_WEEK) == 1 && Boolean.parseBoolean(result.get(6).getFlag());
        int signAllDays = 0;
        for (int i = 0; i < result.size(); i++) {
            if (Boolean.parseBoolean(result.get(i).getFlag())) {
                signAllDays++;
            }
        }
        if (weekday1 || weekday2 || weekday3 || weekday4 || weekday5 || weekday6 || weekday7) {
            sign_in_commit.setEnabled(false);
            sign_in_commit.setText("已签到" + signAllDays + "天");
        }


    }

    @Override
    protected void onReload() {
        getSignInByWeek();
    }

    public void getSignInByWeek() {
        showLoading();
        mPresenter.getSignInByWeek(this);
    }
}
