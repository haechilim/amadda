package com.example.a_matta_library;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.a_matta_library.helper.Util;
import com.example.a_matta_library.manager.ApiManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class sub_4_yumyum extends AppCompatActivity {
    private Button button_yumyum, goback_from_yumyum;
    private CheckBox cb_breakfast, cb_lunch, cb_supper, cb_snack;
    private GregorianCalendar mCalender;
    private AlarmManager alarmManager;
    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_4_yumyum);

        init();
        bindEvents();
    }

    private void init() {
        button_yumyum = findViewById(R.id.button_yumyum);
        cb_breakfast = findViewById(R.id.cb_breakfast);
        cb_lunch = findViewById(R.id.cb_lunch);
        cb_supper = findViewById(R.id.cb_supper);
        cb_snack = findViewById(R.id.cb_snack);
        goback_from_yumyum = findViewById(R.id.goback_from_yumyum);
        mCalender = new GregorianCalendar();
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    }

    private void bindEvents() {
        button_yumyum.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            ApiManager.getMenu(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH) - 1, menus -> setAlarm(menus));
        });

        goback_from_yumyum.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    private void setAlarm(String[] menus) {
        Intent receiverIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(!setAlarmMessage(menus)) {
            Util.toast(this, "최소 1개 이상의 알림을 선택해주세요.", false);
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private boolean setAlarmMessage(String[] menus) {
        String message = "";

        if(cb_breakfast.isChecked()) message += "아침\n" + menus[0];
        if(cb_lunch.isChecked()) message += "\n점심\n" + menus[1];
        if(cb_supper.isChecked()) message += "\n저녁\n" + menus[2];
        if(cb_snack.isChecked()) message += "\n간식\n" + menus[3];

        AlarmReceiver.message = message;

        return !AlarmReceiver.message.isEmpty();
    }
}
