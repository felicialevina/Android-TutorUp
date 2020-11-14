package com.example.tutorup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomepageT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_t);

        TextView info = findViewById(R.id.txtInfo2);
        TextView info2 = findViewById(R.id.txtInfo3);
        TextView lbl = findViewById(R.id.lblInfo);
        TextView name = findViewById(R.id.txtTutName2);
        Button button = (Button) findViewById(R.id.btnDeactivate);

        Intent intent = getIntent();
        String tutFee = intent.getStringExtra("fee");
        String tutName = intent.getStringExtra("name");
        String tutEmail = intent.getStringExtra("email");
        String tutDegree = intent.getStringExtra("degree");
        String tutRating = intent.getStringExtra("rating");

        name.setText(tutName);
        lbl.setText("Email\n\n\n" + "Degree\n\n\n" + "Fee\n\n\n" + "Rating\n\n\n");
        String result = tutEmail + "\n\n\n\n" + tutDegree;
        String result2 = tutFee + "\n\n\n\n" + tutRating;
        info.setText(result);
        info2.setText(result2);
    }
}