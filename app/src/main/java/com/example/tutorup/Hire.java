package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.Calendar;

public class Hire extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
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

        final Calendar c = Calendar.getInstance();
        final DateFormat frmtDate = DateFormat.getDateInstance();
        final DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };

        Button button = (Button) findViewById(R.id.btnHire);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Hire.this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /*frmtDate.format(c.getTime())*/




    }
}