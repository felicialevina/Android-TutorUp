package com.example.tutorup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TutorList extends AppCompatActivity {
    TextView cName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);

        ActionBar actionBar = getSupportActionBar();

        cName = findViewById(R.id.courseName);

        Intent intent = getIntent();
        String input = intent.getStringExtra("name");
        cName.setText(input);
    }
}