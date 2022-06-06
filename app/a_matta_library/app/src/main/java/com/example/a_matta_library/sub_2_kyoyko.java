package com.example.a_matta_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;



public class sub_2_kyoyko extends AppCompatActivity {

    // 메인화면에서 이동하는거 액티비티 지정
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_2_kyoyko);

        // 설정 변경 버튼 클릭하면 액티비티 전환되는 버튼!!
        Button modify_settings = (Button) findViewById(R.id.modify_settings);
        modify_settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sub_2_kyokyo_settings.class);
                startActivity(intent);
            }

        });

        // 메인 화면으로 돌아가기...
        Button goback_from_kyokyo = (Button) findViewById(R.id.goback_from_kyokyo);
        goback_from_kyokyo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });
    }

}
