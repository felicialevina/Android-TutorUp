package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Hire extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RatingBar ratingBar;
    double ratingInput = 0;

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
        final String tutCourse = intent.getStringExtra("course");
        final Double tutFees = intent.getDoubleExtra("fee", 0.0);
        final String tutEmail = intent.getStringExtra("email");

        String result = "Degree: " + tutDegree + "\n\nFees: " + tutFees + "\n\nContact: " + tutEmail;
        setName.setText(tutName);
        info.setText(result);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(Hire.this);
        dialog.setIcon(R.drawable.ic_baseline_star_24);
        dialog.setCancelable(false);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingInput = rating;
                dialog.setTitle("Do you want to give this tutor " + ratingInput + " stars?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.collection("tutors")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                String tEmail = document.getData().get("email").toString();

                                                if(tEmail.equals(tutEmail)) {
                                                    ArrayList<Double> list = (ArrayList<Double>) document.getData().get("ratingList");
                                                    list.add(ratingInput);
                                                    double ratingTotal = calcAverage(list);

                                                    Map<String, Object> map = new HashMap<>();
                                                    map.put("rating", ratingTotal);
                                                    map.put("ratingList", list);
                                                    db.collection("tutors")
                                                            .document(document.getId())
                                                            .update(map);
                                                }
                                            }
                                        } else {
                                            Log.w("TutorList", "Error getting the document", task.getException());
                                        }
                                    }
                                });
                        startActivity(new Intent(Hire.this, Homepage.class));
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });

        final Calendar c = Calendar.getInstance();
        final DateFormat frmtDate = DateFormat.getDateInstance();
        final DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                databaseHelper.addData(tutCourse);

                Intent intent = new Intent(Hire.this, Payment.class);
                intent.putExtra("fee", tutFees);
                intent.putExtra("email", tutEmail);
                intent.putExtra("date", frmtDate.format(c.getTime()));
                startActivity(intent);
            }
        };

        Button button = (Button) findViewById(R.id.btnHire);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Hire.this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public double calcAverage(ArrayList<Double> list){
        double ratingTotal = 0.0;

        for (int i = 0; i < list.size(); i++) {
            String convert = "" + list.get(i);
            ratingTotal += Double.parseDouble(convert);}

        ratingTotal = ratingTotal / list.size();
        return ratingTotal;
    }
}