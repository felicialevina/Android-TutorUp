package com.example.tutorup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TopicsPage extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    ArrayList<Topics> mTopics;
    String bName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_page);

        mTopics = databaseHelper.getTopicsList();


        RecyclerView recyclerView = findViewById(R.id.recyclerview3);
        final TopicsListAdapter adapter = new TopicsListAdapter(this, mTopics);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}