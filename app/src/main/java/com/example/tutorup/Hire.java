package com.example.tutorup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class Hire extends AppCompatActivity {
    RatingBar ratingBar;
    int rating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);

        TextView info = findViewById(R.id.txtInfo);
        TextView setName = findViewById(R.id.txtTutName);
        ratingBar = findViewById(R.id.ratingBar);

        Intent intent = getIntent();
        String tutName = intent.getStringExtra("name");
        String tutDegree = intent.getStringExtra("degree");
        Double tutFees = intent.getDoubleExtra("fee", 0.0);
        String tutEmail = intent.getStringExtra("email");

        String result = "Degree: " + tutDegree + "\n\nFees: " + tutFees + "\n\nContact: " + tutEmail;
        setName.setText(tutName);
        info.setText(result);

        rating = ratingBar.getNumStars();
    }
}