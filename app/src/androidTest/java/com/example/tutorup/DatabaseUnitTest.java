package com.example.tutorup;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.platform.app.InstrumentationRegistry ;

import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class DatabaseUnitTest {

    public DatabaseHelper mDatabase;

    /*Sets up the environment for testing.*/
    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDatabase = new DatabaseHelper(appContext);
    }

    /*Tests for getting correct Course List*/
    @Test
    public void testCourseList() {
        ArrayList<Course> courses = new ArrayList<Course>();
        Course cs = new Course(1, "Computer Science");
        Course chem = new Course(2, "Chemistry");
        Course math = new Course(3, "Mathematics");
        courses.add(cs);
        courses.add(chem);
        courses.add(math);
        ArrayList<Course> result = mDatabase.getList();
        assertEquals(courses.get(0).getName(), result.get(0).getName());
        assertEquals(courses.get(1).getName(), result.get(1).getName());
        assertEquals(courses.get(2).getName(), result.get(2).getName());
    }
    @Test
    public void testTopicList() {
        ArrayList<Topics> topics = new ArrayList<Topics>();
        Topics cs1 = new Topics(1, "Computer Science", "CPS109", "Computer Science I");
        Topics cs2 = new Topics(4, "Computer Science", "CPS209", "Computer Science II");
        topics.add(cs1);
        topics.add(cs2);
        ArrayList<Topics> result = mDatabase.getTopicsList();
        assertEquals(topics.get(0).getTopicName(), result.get(0).getTopicName());
        assertEquals(topics.get(1).getTopicName(), result.get(1).getTopicName());
    }
}