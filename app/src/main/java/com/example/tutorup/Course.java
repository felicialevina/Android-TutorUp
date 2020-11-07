package com.example.tutorup;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "course")
    private String mCourse;

    public Course(@NonNull String course) {this.mCourse = course;}

    public String getCourse(){return this.mCourse;}


}
