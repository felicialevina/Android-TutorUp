package com.example.tutorup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.List;

public class Homepage extends AppCompatActivity {
  //  private CourseViewModel mCourseViewModel;
    Integer[] CourseImage = {R.drawable.cs,R.drawable.ch,R.drawable.mt};
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    Cursor res = databaseHelper.getData();
    List<Course> mCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //int cID = 0;
        //String cName = "";
       // while(res.moveToNext())
        //{
            //cID = res.getInt(0);
            //cName = res.getString(1);
            //Course course = new Course(cID, cName);
            //mCourses.add(course);
        //}

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        final CourseListAdapter adapter = new CourseListAdapter(this, mCourses, CourseImage);
  //      recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

      /*  mCourseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);

        mCourseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(@Nullable final List<Course> courses) {
                // Update the cached copy of the words in the adapter.
                adapter.setCourses(courses);
            }
        });*/
    }
}