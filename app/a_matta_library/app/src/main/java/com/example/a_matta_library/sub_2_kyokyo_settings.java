package com.example.a_matta_library;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a_matta_library.helper.Util;
import com.example.a_matta_library.manager.ApiManager;

import java.util.ArrayList;
import java.util.Arrays;

public class sub_2_kyokyo_settings extends AppCompatActivity {
    private Spinner spinner1, spinner2, spinner3, spinner4;
    private ArrayList<String> object, classroom, loud;
    private ArrayAdapter<String> arrayAdapter1, arrayAdapter2, arrayAdapter3, arrayAdapter4;
    private EditText inputTitle;
    private int num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_2_kyokyo_settings);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        inputTitle = findViewById(R.id.input_title);

        object = new ArrayList<String>(Arrays.asList("스터디", "동아리", "대회준비", "수행평가", "특별수업", "기타"));
        classroom = new ArrayList<String>();
        loud = new ArrayList<String>(Arrays.asList("시끄러움", "보통", "조용함"));

        arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, object);
        arrayAdapter3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, loud);

        spinner1.setAdapter(arrayAdapter1);
        spinner3.setAdapter(arrayAdapter3);

        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        num = sharedPreferences.getInt("num", 0);

        bindEvents();
        initClassrooms();
    }

    private void bindEvents() {
        findViewById(R.id.button_kyokyo_setting).setOnClickListener(v -> {
            String title = inputTitle.getText().toString().trim();
            String object = spinner1.getSelectedItem().toString();
            String loud = spinner3.getSelectedItem().toString();
            String teacher = spinner4.getSelectedItem().toString();
            String classroomName = spinner2.getSelectedItem().toString();
            int selectedIndex = classroom.indexOf(classroomName) + 1;

            int numbers[] = { 32, 5, 17, 7 };
            int i = 0;

            for(i = 0; i < numbers.length; i++) {
                if(selectedIndex - numbers[i] <= 0) break;

                selectedIndex -= numbers[i];
            }

            ApiManager.applicationFormat(num, i + 1, selectedIndex, title, object, loud, teacher, success -> {
                Util.toast(this, "교과교실 설정이 변경되었습니다.", false);
                finish();
            });
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String teachers[] = ApiManager.classrooms.get(position).getTeacherNames();

                arrayAdapter4 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, teachers);
                spinner4.setAdapter(arrayAdapter4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initClassrooms() {
        ApiManager.initClassrooms(success -> {
            for(int i = 0; i < ApiManager.classrooms.size(); i++) {
                classroom.add(ApiManager.classrooms.get(i).getName());
            }

            arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, classroom);
            spinner2.setAdapter(arrayAdapter2);
        });
    }
}
