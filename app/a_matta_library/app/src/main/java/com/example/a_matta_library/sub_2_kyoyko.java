package com.example.a_matta_library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a_matta_library.helper.Util;
import com.example.a_matta_library.manager.ApiManager;

public class sub_2_kyoyko extends AppCompatActivity {
    private CheckBox zero_w, one_w, two_w;
    private CheckBox one_e, two_e, three_e, four_e;
    private Button submit, goback_from_kyokyo, modify_settings;

    // 메인화면에서 이동하는거 액티비티 지정
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_2_kyoyko);

        init();
        bindEvents();
    }

    private void init() {
        submit = findViewById(R.id.button_kyokyo);
        modify_settings = findViewById(R.id.modify_settings);
        goback_from_kyokyo = findViewById(R.id.goback_from_kyokyo);
        zero_w = findViewById(R.id.zero_w);
        one_w = findViewById(R.id.one_w);
        two_w = findViewById(R.id.two_w);
        one_e = findViewById(R.id.one_e);
        two_e = findViewById(R.id.two_e);
        three_e = findViewById(R.id.three_e);
        four_e = findViewById(R.id.four_e);
    }

    private void bindEvents() {
        submit.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
            int num = sharedPreferences.getInt("num", 0);
            String id = sharedPreferences.getString("id", "");
            String pw = sharedPreferences.getString("password", "");
            String time = checkToNumber();

            ApiManager.reserveClass(num, id, pw, time, success -> {
                if (success) Util.toast(this, "교교가 신청되었습니다.", true);
                else Util.toast(this, "교교신청에 실패하였습니다.", true);
            });
        });

        // 설정 변경 버튼 클릭하면 액티비티 전환되는 버튼!!
        modify_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sub_2_kyokyo_settings.class);
                startActivity(intent);
            }
        });

        // 메인 화면으로 돌아가기...
        goback_from_kyokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String checkToNumber() {
        boolean checked[] = getChecked();
        String result = "";

        for(int i = 0; i < checked.length; i++) {
            result += checked[i] ?  "1" : "0";
        }

        return result;
    }

    private boolean[] getChecked() {
        boolean isWeekdays = zero_w.isChecked() || one_w.isChecked() || two_w.isChecked();
        boolean weekdaysChecked[] = { zero_w.isChecked(), one_w.isChecked(), two_w.isChecked(), false };
        boolean weekendChecked[] = { one_e.isChecked(), two_e.isChecked(), three_e.isChecked(), four_e.isChecked() };

        return isWeekdays ? weekdaysChecked : weekendChecked;
    }
}
