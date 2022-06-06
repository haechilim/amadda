package com.example.a_matta_library;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a_matta_library.manager.ApiManager;

import java.util.ArrayList;
import java.util.Arrays;

public class sub_2_kyokyo_settings extends AppCompatActivity {
    private Spinner spinner1, spinner2, spinner3, spinner4;
    ArrayList<String> object, classroom, loud;
    ArrayAdapter<String> arrayAdapter1, arrayAdapter2, arrayAdapter3, arrayAdapter4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_2_kyokyo_settings);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);

        object = new ArrayList<String>(Arrays.asList("스터디", "동아리", "대회준비", "수행평가", "특별수업", "기타"));
        classroom = new ArrayList<String>();
        loud = new ArrayList<String>(Arrays.asList("시끄러움", "보통", "조용함"));

        arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, object);
        arrayAdapter3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, loud);

        spinner1.setAdapter(arrayAdapter1);
        spinner3.setAdapter(arrayAdapter3);

        ApiManager.initClassrooms(success -> {
            for(int i = 0; i < ApiManager.classrooms.size(); i++) {
                classroom.add(ApiManager.classrooms.get(i).getName());
            }

            arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, classroom);
            spinner2.setAdapter(arrayAdapter2);
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
}
