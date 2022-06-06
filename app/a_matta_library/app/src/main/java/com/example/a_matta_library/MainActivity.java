package com.example.a_matta_library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 공지글 화면 이동
        Button main_button_notice = (Button) findViewById(R.id.main_button_notice);
        main_button_notice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sub_1_notice.class);
                startActivity(intent);
            }

        });

        // 2. 교교 화면 이동
        Button main_button_kyokyo = (Button) findViewById(R.id.main_button_kyokyo);
        main_button_kyokyo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sub_2_kyoyko.class);
                startActivity(intent);
            }

        });

        // 3. 도서관 화면 이동
        Button main_button_library = (Button) findViewById(R.id.main_button_library);
        main_button_library.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sub_3_library.class);
                startActivity(intent);
            }

        });

        // 4. 급식 화면 이동
        Button main_button_yumyum = (Button) findViewById(R.id.main_button_yumyum);
        main_button_yumyum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sub_4_yumyum.class);
                startActivity(intent);
            }

        });

        // 5. 도움말 화면 이동
        Button main_button_howto = (Button) findViewById(R.id.main_button_howto);
        main_button_howto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sub_5_howto.class);
                startActivity(intent);
            }

        });


    }


}