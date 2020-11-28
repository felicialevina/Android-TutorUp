package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TutorList extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Tutor> mTutors = new ArrayList<Tutor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);

        Intent intent = getIntent();
        final String check = intent.getStringExtra("coursename");

        db.collection("tutors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tCourse = document.getData().get("course").toString();

                                if(tCourse.equals(check)){
                                String tDegree = document.getData().get("degree").toString();
                                String tEmail = document.getData().get("email").toString();
                                String tFee = document.getData().get("fee").toString();
                                String tName = document.getData().get("name").toString();
                                String tPass = document.getData().get("password").toString();
                                String tRating = document.getData().get("rating").toString();

                                double tFee2 = Double.parseDouble(tFee);
                                double tRating2 = Double.parseDouble(tRating);
                                DecimalFormat df = new DecimalFormat("#.#");
                                String rounding = df.format(tRating2);
                                tRating2 = Double.parseDouble(rounding);

                                Tutor tutor = new Tutor(tName, tEmail, tPass, tCourse, tDegree, tRating2, tFee2);
                                mTutors.add(tutor);
                                }
                            }
                            RecyclerView recyclerView = findViewById(R.id.recyclerview2);
                            final TutorListAdapter adapter = new TutorListAdapter(TutorList.this, mTutors);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(TutorList.this));
                        } else {
                            Log.w("TutorList", "Error getting the document", task.getException());
                        }
                    }
                });
    }
}