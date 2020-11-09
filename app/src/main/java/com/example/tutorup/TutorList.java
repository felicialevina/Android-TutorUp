package com.example.tutorup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TutorList extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Tutor> mTutors;
    TextView cName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_list);

        ActionBar actionBar = getSupportActionBar();
        Intent intent = getIntent();
        String check = intent.getStringExtra("coursename");




        cName = findViewById(R.id.courseName);
        cName.setText(check);








        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TutorListAdapter adapter = new TutorListAdapter(this, mTutors);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}