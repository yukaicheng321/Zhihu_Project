package com.lenovo.example.zhihu_project.fragment.zhihu.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.utils.DateUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity {

    @BindView(R.id.calendar)
    MaterialCalendarView calendar;
    @BindView(R.id.tv_calendar)
    TextView tvCalendar;
    private CalendarDay mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        calendar.state().edit()
//                设置显示一周的第一天是周几 下面显示的是周一
                .setFirstDayOfWeek(Calendar.MONDAY)
//                设置最小点击日期
                .setMinimumDate(CalendarDay.from(2018,01,01))
//                设置最大点击日期
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(),DateUtil.getCurrentMonth(),DateUtil.getCurrentDay()))
//               设置日历显示模式
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

//        点击监听获取当前点击的日期
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay
                    date, boolean selected) {
                mDate=date;
            }
        });


    }

    @OnClick(R.id.tv_calendar)
    public void onViewClicked() {
        Calendar calendar = mDate.getCalendar();

        long timeInMillis = calendar.getTimeInMillis();

        String formatDate = DateUtil.getFormatDate(timeInMillis);
        Intent intent = getIntent();
        intent.putExtra("date",formatDate);
        setResult(100,intent);

        finish();

    }
}
