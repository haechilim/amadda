package com.example.a_matta_library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a_matta_library.helper.Util;
import com.example.a_matta_library.manager.ApiManager;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText inputId;
    private TextInputEditText inputPassword;
    private TextInputEditText inputNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputId = findViewById(R.id.id);
        inputPassword = findViewById(R.id.password);
        inputNum = findViewById(R.id.num);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = inputId.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                int num = Integer.parseInt(inputNum.getText().toString().trim());

                if(!checkValidation(id, password)) return;

                ApiManager.checkLogin(id, password, success -> {
                    if(success) {
                        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id", id);
                        editor.putString("password", password);
                        editor.putInt("num", num);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Util.toast(LoginActivity.this, "올바른 아이디 또는 비밀번호를 입력해주세요.", false);
                    }
                });
            }
        });
    }

    private boolean checkValidation(String id, String password) {
        if(id.isEmpty()) {
            Util.toast(LoginActivity.this, "아이디를 입력해주세요.", true);
            return false;
        }
        else if(password.isEmpty()) {
            Util.toast(LoginActivity.this, "비밀번호를 입력해주세요.", true);
            return false;
        }

        return true;
    }
}