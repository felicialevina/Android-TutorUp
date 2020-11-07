package com.example.tutorup;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository {

    private CourseDao mCourseDao;
    private LiveData<List<Course>> mAllCourses;

    CourseRepository(Application application) {
        CourseRoomDatabase db = CourseRoomDatabase.getDatabase(application);
        mCourseDao = db.courseDao();
        mAllCourses = mCourseDao.getAllCourses();
    }

    LiveData<List<Course>> getAllCourses() {
        return mAllCourses;
    }

    public void insert (Course course) {
        new insertAsyncTask(mCourseDao).execute(course);
    }

    private static class insertAsyncTask extends AsyncTask<Course, Void, Void> {

        private CourseDao mAsyncTaskDao;

        insertAsyncTask(CourseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Course... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}