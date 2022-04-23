package com.visionvera.psychologist.c.module.counselling.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.orhanobut.logger.Logger;
import com.visionvera.library.base.BaseActivity;
import com.visionvera.library.base.BaseMVPLoadActivity;
import com.visionvera.library.util.OneClickUtils;
import com.visionvera.library.widget.decoration.SpaceItemDecoration;
import com.visionvera.psychologist.c.R;
import com.visionvera.psychologist.c.R2;
import com.visionvera.psychologist.c.module.counselling.adapter.OrderConsultTimeAdapter;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarBean;
import com.visionvera.psychologist.c.module.counselling.bean.TimeCalendarRequest;
import com.visionvera.psychologist.c.module.counselling.contract.OrderConsultContract;
import com.visionvera.psychologist.c.module.counselling.presenter.TimeCalendarPresenter;
import com.visionvera.psychologist.c.widget.calendar.CustomWeekBar;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.visionvera.library.base.Constant.RequestReturnCode.OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code;

/**
 * author:lilongfeng
 * date:2019/12/20
 * 描述:预约咨询----选择时间
 */

public class OrderConsultSelectTimeActivity extends BaseMVPLoadActivity<OrderConsultContract.TimeCalendar.TimeCalendarView, TimeCalendarPresenter> implements CalendarView.OnCalendarSelectListener {

    @BindView(R2.id.evaluation_module_order_consult_select_time_calendarview)
    CalendarView calendarView;

    @BindView(R2.id.evaluation_module_current_month)
    TextView current_month;

    @BindView(R2.id.evaluation_module_order_consult_morning_recyclerview)
    RecyclerView mMorningRecy;

    @BindView(R2.id.evaluation_module_order_consult_afternoon_recyclerview)
    RecyclerView mAfterRecy;

    @BindView(R2.id.tv_right)
    TextView tv_right;

    @BindView(R2.id.tv_title)
    TextView tv_title;

    private List<TimeCalendarBean.ResultBean> calendarBeanList;
    private List<TimeCalendarBean.ResultBean.DateListBean> mMorningList=new ArrayList<>();
    private List<TimeCalendarBean.ResultBean.DateListBean> mAfterList=new ArrayList<>();
    private OrderConsultTimeAdapter morningAdapter;
    private OrderConsultTimeAdapter afterAdapter;

    /**
     * 当前选择的时间段
     */
    private TimeCalendarBean.ResultBean.DateListBean mCurrentTimeBean;
    private OrderConsultSelectTimeIntentBean mIntentBean;
    //所有上午的可选时间
    String[] morningTimes = {"08:00-09:00", "09:00-10:00", "10:00-11:00", "11:00-12:00"};
    //所有下午的可选时间
    String[] afterTimes = {"12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00", "18:00-19:00", "19:00-20:00", "20:00-21:00"};


    public static void startActivityForResult(Context context, OrderConsultSelectTimeIntentBean intentBean) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(new Intent(context, OrderConsultSelectTimeActivity.class)
                            .putExtra("intentBean", intentBean),
                    OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code);
        }

    }


    @Override
    protected int getLayoutId() {
        return R.layout.evaluation_module_activity_order_consult_select_time;
    }

    @Override
    protected void doYourself() {
        initView();

        initIntentData();

        requestData();
    }

    private void initIntentData() {
        mIntentBean = (OrderConsultSelectTimeIntentBean) getIntent().getSerializableExtra("intentBean");
    }

    /**
     * 请求数据
     */
    private void requestData() {
        showLoading();
        TimeCalendarRequest request = new TimeCalendarRequest();
        request.setDoctorUserId(mIntentBean.id);
//        ToastUtils.showShort(mIntentBean.id);
        mPresenter.getTimeCalendar(request, this);
    }

    private void initView() {

        tv_title.setText(getString(R.string.evaluation_module_select_time));

        //设置日期拦截事件，当前有效
        calendarView.setWeekBar(CustomWeekBar.class);


        //这行代码会导致有一周会展示数据为空，要给calendarview的作者提问题。
//        calendarView.setOnWeekChangeListener(this);

        current_month.setText(calendarView.getCurMonth() + "月");

        //设置日历范围  本app可选择后两周
        java.util.Calendar strDate = java.util.Calendar.getInstance();
        int currentYear = strDate.get(java.util.Calendar.YEAR);
        int currentMonth = strDate.get(java.util.Calendar.MONTH) + 1;//获取月份（因为在格里高利历和罗马儒略历一年中第一个月为JANUARY，它为0，最后一个月取决于一年中的月份数，所以这个值初始为0，所以需要加1）
        int currentDay = strDate.get(java.util.Calendar.DATE);//获取日
        //定位到两周后
        strDate.add(java.util.Calendar.DAY_OF_MONTH, +13);
        int maxYear = strDate.get(java.util.Calendar.YEAR);
        int maxMonth = strDate.get(java.util.Calendar.MONTH) + 1;//获取月份（因为在格里高利历和罗马儒略历一年中第一个月为JANUARY，它为0，最后一个月取决于一年中的月份数，所以这个值初始为0，所以需要加1）
        int maxDay = strDate.get(java.util.Calendar.DATE);//获取日
        calendarView.setRange(currentYear, currentMonth, currentDay, maxYear, maxMonth, maxDay);
        //设置日期拦截事件
        calendarView.setOnCalendarInterceptListener(new CalendarView.OnCalendarInterceptListener() {
            @Override
            public boolean onCalendarIntercept(Calendar calendar) {
                //这里写拦截条件，返回true代表拦截，尽量以最高效的代码执行
//                return calendar.isWeekend();
                return false;
            }

            @Override
            public void onCalendarInterceptClick(Calendar calendar, boolean isClick) {
                //todo 点击拦截的日期回调
//                ToastUtils.showShort("点击日期" + calendar.toString());
            }
        });

        calendarView.setOnCalendarSelectListener(this);

        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {

                current_month.setText(month + "月");
            }
        });


        tv_right.setText("确定");
        tv_right.setTextColor(getResources().getColor(R.color.COLOR_BLUE_3E86FE));
        defaultMorningList();
        defaultAfterList();
        mMorningRecy.setLayoutManager(new GridLayoutManager(this, 3));
        mAfterRecy.setLayoutManager(new GridLayoutManager(this, 3));

        mMorningRecy.addItemDecoration(new SpaceItemDecoration(10));
        mAfterRecy.addItemDecoration(new SpaceItemDecoration(10));

        morningAdapter = new OrderConsultTimeAdapter(this, mMorningList);
        afterAdapter = new OrderConsultTimeAdapter(this, mAfterList);

        mMorningRecy.setAdapter(morningAdapter);
        mAfterRecy.setAdapter(afterAdapter);
        //所有时段,只能选一个时段.且每个时段都是1个小时
        morningAdapter.setOnItemClickListener((adapter, view, position) -> {

            for (int i = 0; i < mMorningList.size(); i++) {
                mMorningList.get(i).setSelected(false);
                if (mMorningList.get(i).isDefault()) {
                    if (position == i) {
                        ToastUtils.showShort("此时段不可预约");
                        mCurrentTimeBean = null;
                    }
                } else {
                    if (position == i) {
                        mMorningList.get(i).setSelected(true);
                        mCurrentTimeBean = mMorningList.get(i);
                    }
                }

            }

            for (int i = 0; i < mAfterList.size(); i++) {
                mAfterList.get(i).setSelected(false);
            }


            morningAdapter.setNewData(mMorningList);
            afterAdapter.setNewData(mAfterList);
            morningAdapter.notifyDataSetChanged();
            afterAdapter.notifyDataSetChanged();
        });


        afterAdapter.setOnItemClickListener((adapter, view, position) -> {
            for (int i = 0; i < mMorningList.size(); i++) {
                mMorningList.get(i).setSelected(false);
            }

            for (int i = 0; i < mAfterList.size(); i++) {
                mAfterList.get(i).setSelected(false);
                if (mAfterList.get(i).isDefault()) {
                    if (position == i) {
                        ToastUtils.showShort("此时段不可预约");
                        mCurrentTimeBean = null;
                    }

                } else {
                    if (position == i) {
                        mAfterList.get(i).setSelected(true);
                        mCurrentTimeBean = mAfterList.get(i);
                    }
                }

            }
            morningAdapter.setNewData(mMorningList);
            afterAdapter.setNewData(mAfterList);
            morningAdapter.notifyDataSetChanged();
            afterAdapter.notifyDataSetChanged();
        });

    }

    @Override
    protected void initMVP() {
        mView = new OrderConsultContract.TimeCalendar.TimeCalendarView() {
            @Override
            public void onTimeCalendar(TimeCalendarBean bean, ResultType resultType, String errorMsg) {

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
                        showNormal();

                        calendarBeanList = bean.getResult();
                        //选中当天. 自动铺展当天的数据
                        calendarView.scrollToCurrent();

                        setCalendarBottomPoint();

                        break;
                    default:
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new TimeCalendarPresenter(this, mView);

    }

    /**
     * 设置日期底下的小圆点
     */
    private void setCalendarBottomPoint() {
        //有小圆点代表这天有数据
        Map<String, Calendar> map = new HashMap<>();
        for (int i = 0; i < calendarBeanList.size(); i++) {

            String exitDate = calendarBeanList.get(i).getDate();
            String exitYear = exitDate.substring(0, 4);
            String exitMonth = exitDate.substring(5, 7);
            String exitDay = exitDate.substring(8);

            Logger.i("年：" + Integer.parseInt(exitYear));
            Logger.i("月：" + Integer.parseInt(exitMonth));
            Logger.i("日：" + Integer.parseInt(exitDay));

            map.put(getSchemeCalendar(Integer.parseInt(exitYear), Integer.parseInt(exitMonth), Integer.parseInt(exitDay), getResources().getColor(R.color.base_module_theme), "").toString(),
                    getSchemeCalendar(Integer.parseInt(exitYear), Integer.parseInt(exitMonth), Integer.parseInt(exitDay), getResources().getColor(R.color.base_module_theme), ""));

        }
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        calendarView.setSchemeDate(map);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());
        calendar.addScheme(0xFFFF0000, "假");
        return calendar;
    }

    @Override
    protected void onReload() {
        requestData();
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        defaultMorningList();
        defaultAfterList();

        String currentTime = calendar.getYear() + "-" + repairZero(calendar.getMonth() + "") + "-" + repairZero(calendar.getDay() + "");


        for (int i = 0; i < calendarBeanList.size(); i++) {
            if (TextUtils.equals(currentTime, calendarBeanList.get(i).getDate())) {
                //是所选的这天
                for (int j = 0; j < calendarBeanList.get(i).getDateList().size(); j++) {
                    TimeCalendarBean.ResultBean.DateListBean dateBean = calendarBeanList.get(i).getDateList().get(j);


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        //设置当前时间 24小时内的时间段不可以点击
                        if (sdf.parse(dateBean.getStart()).getTime() > sdf.parse(tomorrowDateStr()).getTime()) {
                            //截取结束的小时数
                            String endDateHour = dateBean.getEnd().substring(11, 13);
                            String startDateHour = dateBean.getStart().substring(11, 13);
                            if (Integer.parseInt(endDateHour) <= 12) {
                                //上午
                                for (int i1 = 0; i1 < mMorningList.size(); i1++) {
                                    if (mMorningList.get(i1).getDetaultTime().startsWith(startDateHour)) {
                                        //找到对应的时段
                                        mMorningList.get(i1).setSelected(false);
                                        mMorningList.get(i1).setId(dateBean.getId());
                                        mMorningList.get(i1).setDefault(false);
                                        mMorningList.get(i1).setDay(dateBean.getDay());
                                        mMorningList.get(i1).setName(dateBean.getName());
                                        mMorningList.get(i1).setStart(dateBean.getStart());
                                        mMorningList.get(i1).setEnd(dateBean.getEnd());
                                        String startTime = dateBean.getStart().substring(11, 16);
                                        String endTime = dateBean.getEnd().substring(11, 16);
                                        mMorningList.get(i1).setDetaultTime(startTime + "-" + endTime);

                                    }
                                }
                            } else {
                                //下午
                                for (int i1 = 0; i1 < mAfterList.size(); i1++) {
                                    if (mAfterList.get(i1).getDetaultTime().startsWith(startDateHour)) {
                                        //找到对应的时段
                                        mAfterList.get(i1).setSelected(false);
                                        mAfterList.get(i1).setId(dateBean.getId());
                                        mAfterList.get(i1).setDefault(false);
                                        mAfterList.get(i1).setDay(dateBean.getDay());
                                        mAfterList.get(i1).setName(dateBean.getName());
                                        mAfterList.get(i1).setStart(dateBean.getStart());
                                        mAfterList.get(i1).setEnd(dateBean.getEnd());
                                        String startTime = dateBean.getStart().substring(11, 16);
                                        String endTime = dateBean.getEnd().substring(11, 16);
                                        mAfterList.get(i1).setDetaultTime(startTime + "-" + endTime);

                                    }
                                }
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
        morningAdapter.setNewData(mMorningList);
        afterAdapter.setNewData(mAfterList);
        morningAdapter.notifyDataSetChanged();
        afterAdapter.notifyDataSetChanged();
        if (calendarBeanList == null || calendarBeanList.size() <= 0) {
            ToastUtils.showLong("抱歉:此人暂无可预约时间,暂时无法预约,请稍后再试");
        } else {
            if (mMorningList != null && mAfterList != null) {
                boolean hasTime = false;
                for (TimeCalendarBean.ResultBean.DateListBean dateListBean : mMorningList) {
                    if (dateListBean.isDefault() == false) {
                        hasTime = true;
                        break;
                    }
                }
                for (TimeCalendarBean.ResultBean.DateListBean dateListBean : mAfterList) {
                    if (dateListBean.isDefault() == false) {
                        hasTime = true;
                        break;
                    }
                }
                if (!hasTime) {
                    //如果所选那天什么数据都没有,提提醒一下,否则用户可能以为没反应
                    ToastUtils.showLong("所选日期(" + currentTime + ")" +
                            "\n没有可供选择的时间" +
                            "\n请切换日期");
                }

            }
        }

    }

    //将上午列表置为默认所有,不可点击
    private void defaultMorningList() {
        mMorningList.clear();
        for (int i = 0; i < morningTimes.length; i++) {
            TimeCalendarBean.ResultBean.DateListBean dateBean = new TimeCalendarBean.ResultBean.DateListBean();
            dateBean.setDetaultTime(morningTimes[i]);
            dateBean.setSelected(false);
            mMorningList.add(dateBean);
        }

    }

    //将下午列表置为默认所有,不可点击
    private void defaultAfterList() {
        mAfterList.clear();
        for (int i = 0; i < afterTimes.length; i++) {
            TimeCalendarBean.ResultBean.DateListBean dateBean = new TimeCalendarBean.ResultBean.DateListBean();
            dateBean.setDetaultTime(afterTimes[i]);
            dateBean.setSelected(false);
            mAfterList.add(dateBean);
        }


    }

    public String repairZero(String number) {
        if (number.length() == 1) {
            return "0" + number;
        } else if (number.length() == 2) {
            return number;
        } else {
            return "00";
        }
    }


    @OnClick({R2.id.tv_right, R2.id.rl_back, R2.id.rl_nextWeek})
    public void onClick(View view) {
        if (OneClickUtils.isFastClick()) {
            return;
        }
        int viewId = view.getId();
        if (viewId == R.id.tv_right) {
            if (mCurrentTimeBean == null) {
                ToastUtils.showShort("请选择时间段");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("data", mCurrentTimeBean);
            setResult(OrderConsultActivity_To_OrderConsultSelectTimeActivity_Code, intent);
            finish();
        } else if (viewId == R.id.rl_back) {
            finish();
        } else if (viewId == R.id.rl_nextWeek) {
            //日历切换到下周
            calendarView.scrollToNext(true);
        }
    }

    public static class OrderConsultSelectTimeIntentBean implements Serializable {

        private int id;

        public OrderConsultSelectTimeIntentBean(int id) {
            this.id = id;
        }
    }

    /**
     * 获取明天的日期字符串
     *
     * @return
     */
    public static String tomorrowDateStr() {
        Date date = new Date();//取时间
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        //把日期往后增加一天.整数往后推,负数往前移动(1:表示明天、-1：表示昨天，0：表示今天)
        calendar.add(java.util.Calendar.DATE, 1);

        //这个时间就是日期往后推一天的结果
        date = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tomorrowStr = formatter.format(date);
        return tomorrowStr;
    }
}
