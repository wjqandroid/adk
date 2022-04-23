package com.visionvera.psychologist.c.widget.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.lxj.xpopup.core.BottomPopupView;
import com.visionvera.psychologist.c.R;

import java.util.HashMap;
import java.util.Map;

/**
 * author:lilongfeng
 * date:2019/12/11
 * 描述:从底部弹出的日历控件
 */

public class CalendarPopup extends BottomPopupView implements CalendarView.OnCalendarRangeSelectListener, CalendarView.OnCalendarInterceptListener,CalendarView.OnMonthChangeListener {


    private final Context mContext;
    private CalendarView mCalendarView;

    private TextView date;


    public CalendarPopup(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.evaluation_module_calendar_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        mCalendarView = findViewById(R.id.calendar_view);

        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();

        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "第三").toString(),
                getSchemeCalendar(year, month, 3, 0xFF40db25, "第三"));
        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "第六").toString(),
                getSchemeCalendar(year, month, 6, 0xFFe69138, "第六"));
        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "第九").toString(),
                getSchemeCalendar(year, month, 9, 0xFFdf1356, "第九"));
        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "十三").toString(),
                getSchemeCalendar(year, month, 13, 0xFFedc56d, "十三"));
        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "十四").toString(),
                getSchemeCalendar(year, month, 14, 0xFFedc56d, "十四"));
        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "十五").toString(),
                getSchemeCalendar(year, month, 15, 0xFFaacc44, "十五"));
        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "十八").toString(),
                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "十八"));
        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "二五").toString(),
                getSchemeCalendar(year, month, 25, 0xFF13acf0, "二五"));
        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "二七").toString(),
                getSchemeCalendar(year, month, 27, 0xFF13acf0, "二七"));



        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);

        date = findViewById(R.id.evaluation_module_current_date_tv);

        mCalendarView.setOnCalendarRangeSelectListener(this);

        //设置日期拦截事件，当前有效
        mCalendarView.setOnCalendarInterceptListener(this);

        mCalendarView.setOnMonthChangeListener(this);
        mCalendarView.setRange(mCalendarView.getCurYear(),1, 1,
                mCalendarView.getCurYear(), mCalendarView.getCurMonth(), mCalendarView.getCurDay());

        mCalendarView.scrollToCalendar( mCalendarView.getCurYear() , mCalendarView.getCurMonth(), mCalendarView.getCurDay());


        findViewById(R.id.evaluation_module_calendar_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {

    }
    @Override
    public void onCalendarSelectOutOfRange(Calendar calendar) {

    }

    @Override
    public void onSelectOutOfRange(Calendar calendar, boolean isOutOfMinRange) {
        Toast.makeText(mContext,
                calendar.toString() + (isOutOfMinRange ? "小于最小选择范围" : "超过最大选择范围"),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCalendarRangeSelect(Calendar calendar, boolean isEnd) {

    }

    @Override
    public boolean onCalendarIntercept(Calendar calendar) {
        //Log.e("onCalendarIntercept", calendar.toString());
        int day = calendar.getDay();
      /*  return day == 1 || day == 3 || day == 6 || day == 11 ||
                day == 12 || day == 15 || day == 20 || day == 26;*/
        return false;
    }

    @Override
    public void onCalendarInterceptClick(Calendar calendar, boolean isClick) {
        Toast.makeText(mContext,
                calendar.toString() + (isClick ? "拦截不可点击" : "拦截滚动到无效日期"),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMonthChange(int year, int month) {
        date.setText(year+"年"+month+"月");
    }
}
