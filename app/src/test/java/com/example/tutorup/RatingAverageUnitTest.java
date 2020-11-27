package com.example.tutorup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class RatingAverageUnitTest {

    private Hire mHire;

    /*Sets up the environment for testing.*/
    @Before
    public void setUp() {
        mHire = new Hire();
    }

    /*Tests for one rating value*/
    @Test
    public void averageOneNum() {
        ArrayList<Double> item = new ArrayList<Double>();
        item.add(3.5);
        double result = mHire.calcAverage(item);
        assertEquals(3.5, result, 0.001);
    }
    /*Tests for two rating values*/
    @Test
    public void averageTwoNum() {
        ArrayList<Double> item = new ArrayList<Double>();
        item.add(3.5);
        item.add(5.0);
        double result = mHire.calcAverage(item);
        assertEquals(4.25, result, 0.001);
    }
    /*Tests for five rating values*/
    @Test
    public void averageFiveNum() {
        ArrayList<Double> item = new ArrayList<Double>();
        item.add(1.5);
        item.add(2.0);
        item.add(3.5);
        item.add(5.0);
        item.add(2.5);
        double result = mHire.calcAverage(item);
        assertEquals(2.9, result, 0.001);
    }
}