package com.example.tutorup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String dbName = "Courses.db";
    private static final String TABLE_COURSES = "courses_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "topic_name";
    private static final String COL4 = "book_name";
    private static final String TABLE_TOPICS = "topics_table";
    private static final String TABLE_CHOSEN = "chosen_table";



    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_COURSES + " (ID INTEGER PRIMARY KEY, " +
                COL2 + " TEXT)";
        db.execSQL(createTable);
        db.execSQL("INSERT INTO " + TABLE_COURSES + "(ID, NAME ) VALUES (1, 'Computer Science')");
        db.execSQL("INSERT INTO " + TABLE_COURSES + "(ID, NAME ) VALUES (2, 'Chemistry')");
        db.execSQL("INSERT INTO " + TABLE_COURSES + "(ID, NAME ) VALUES (3, 'Mathematics')");

        String createTable2 = "CREATE TABLE " + TABLE_TOPICS + " (ID INTEGER PRIMARY KEY, " +
                COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT)";
        db.execSQL(createTable2);
        db.execSQL("INSERT INTO " + TABLE_TOPICS + "(ID, NAME, TOPIC_NAME, BOOK_NAME ) VALUES (1, 'Computer Science', 'CPS109', 'Computer Science I')");
        db.execSQL("INSERT INTO " + TABLE_TOPICS + "(ID, NAME, TOPIC_NAME, BOOK_NAME ) VALUES (2, 'Chemistry', 'CHY103', 'General Chemistry I')");
        db.execSQL("INSERT INTO " + TABLE_TOPICS + "(ID, NAME, TOPIC_NAME, BOOK_NAME ) VALUES (3, 'Mathematics', 'MTH310', 'Calculus II')");
        db.execSQL("INSERT INTO " + TABLE_TOPICS + "(ID, NAME, TOPIC_NAME, BOOK_NAME ) VALUES (4, 'Computer Science', 'CPS209', 'Computer Science II')");
        db.execSQL("INSERT INTO " + TABLE_TOPICS + "(ID, NAME, TOPIC_NAME, BOOK_NAME ) VALUES (5, 'Chemistry', 'CHY599', 'The Business of Chemistry')");
        db.execSQL("INSERT INTO " + TABLE_TOPICS + "(ID, NAME, TOPIC_NAME, BOOK_NAME ) VALUES (6, 'Mathematics', 'MTH330', 'Advanced Engineering Calculus')");

        String createTable3 = "CREATE TABLE " + TABLE_CHOSEN + " ( " + COL2 + " TEXT)";
        db.execSQL(createTable3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES + "");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPICS + "");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHOSEN + "");
        onCreate(db);
    }

    public void addData(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        Log.w(TAG, "addData: Adding " + item + " to " + TABLE_CHOSEN);

        db.insert(TABLE_CHOSEN, null, contentValues);
    }

    public Cursor getData(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_COURSES;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTopicsData(){
        Cursor data = null;
            SQLiteDatabase db = this.getWritableDatabase();
            String query_chosen = "SELECT * FROM " + TABLE_CHOSEN;
            Cursor data_chosen = db.rawQuery(query_chosen, null);
            String cName = "";
            while (data_chosen.moveToNext()) {
                cName = data_chosen.getString(0);
            }
            String query = "SELECT * FROM " + TABLE_TOPICS + " WHERE NAME = '" + cName + "'";
            data = db.rawQuery(query, null);
            return data;
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

    public ArrayList<Topics> getTopicsList()
    {
        ArrayList<Topics> topicsList = new ArrayList<Topics>();
        int tID = 0;
        String cName = "";
        String tName = "";
        String bName = "";
        Cursor res = getTopicsData();

        while(res.moveToNext())
        {
            tID = res.getInt(0);
            cName = res.getString(1);
            tName = res.getString(2);
            bName = res.getString(3);
            Topics topics = new Topics(tID, cName, tName, bName);

            topicsList.add(topics);
        }
        return topicsList;
    }

}