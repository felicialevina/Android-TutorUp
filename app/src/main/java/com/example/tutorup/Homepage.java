package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Integer[] CourseImage = {R.drawable.cs, R.drawable.ch, R.drawable.mt};
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    ArrayList<Course> mCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mCourses = databaseHelper.getList();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CourseListAdapter adapter = new CourseListAdapter(this, mCourses, CourseImage);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button search = (Button) findViewById(R.id.btnSearch);
        final EditText tutor = findViewById(R.id.txtSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tSearch = tutor.getText().toString();

                db.collection("tutors")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int notif = 0;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String tName = document.getData().get("name").toString();

                                        if(tName.equals(tSearch)){
                                            String tEmail = document.getData().get("email").toString();
                                            String tDegree = document.getData().get("degree").toString();
                                            String tFee = document.getData().get("fee").toString();
                                            double tFee2 = Double.parseDouble(tFee);

                                            Intent intent = new Intent(Homepage.this, Hire.class);
                                            intent.putExtra("name", tName);
                                            intent.putExtra("email", tEmail);
                                            intent.putExtra("fee", tFee2);
                                            intent.putExtra("degree", tDegree);
                                            notif++;
                                            startActivity(intent);
                                            break;
                                        }
                                    }
                                    if(notif == 0){
                                        toastMessage("Tutor not found");
                                    }
                                } else {
                                    Log.w("TutorList", "Error getting the document", task.getException());
                                }
                            }
                        });
            }
        });

        Button syllabus = (Button) findViewById(R.id.btnSyllabus);
        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, TopicsPage.class));
            }
        });

    }
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}