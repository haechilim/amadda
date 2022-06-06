package com.example.a_matta_library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

public class sub_1_notice extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_1_notice);

        // 메인 화면으로 돌아가기...
        Button goback_from_notice = (Button) findViewById(R.id.goback_from_notice);
        goback_from_notice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });
    }


}
