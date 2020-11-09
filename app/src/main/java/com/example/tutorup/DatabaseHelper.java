package com.example.tutorup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String dbName = "Courses.db";
    private static final String TABLE_NAME = "courses_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";


    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, " +
                COL2 + " TEXT)";
        db.execSQL(createTable);
        db.execSQL("INSERT INTO " + TABLE_NAME + "(ID, NAME ) VALUES (1, 'Computer Science')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(ID, NAME ) VALUES (2, 'Chemistry')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(ID, NAME ) VALUES (3, 'Mathematics')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + "");
        onCreate(db);
    }

    public Cursor getData(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteData(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE name = '" + item + "'");
    }

    public ArrayList<Course> getList()
    {
        ArrayList<Course> coursesList = new ArrayList<Course>();
        int cID = 0;
        String cName = "";
        Cursor res = getData();

        while(res.moveToNext())
        {

            cID = res.getInt(0);
            cName = res.getString(1);
            Course course = new Course(cID, cName);
            coursesList.add(course);
        }
        return coursesList;
    }
}