package com.example.tutorup;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void insert(Course course);

    @Query("DELETE FROM course_table")
    void deleteAll();

    @Query("SELECT * from course_table ORDER BY course ASC")
    LiveData<List<Course>> getAllCourses();
}