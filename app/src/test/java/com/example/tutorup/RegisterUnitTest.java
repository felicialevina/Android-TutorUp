package com.example.tutorup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class RegisterUnitTest {

    private Register mRegister;

    /*Sets up the environment for testing.*/
    @Before
    public void setUp() {
        mRegister = new Register();
    }

    /*Tests for correct email form*/
    @Test
    public void correctForm() {
        String password = "password123";
        boolean result = mRegister.isValidPassword(password);
        assertEquals(true, result);
    }
    /*Tests for password less than 8 characters*/
    @Test
    public void incorrectForm() {
        String password = "fane";
        boolean result = mRegister.isValidPassword(password);
        assertEquals(false, result);
    }
    /*Tests for password less than no character*/
    @Test
    public void incorrectForm2() {
        String password = "12345678";
        boolean result = mRegister.isValidPassword(password);
        assertEquals(false, result);
    }
    /*Tests for password less than no number*/
    @Test
    public void incorrectForm3() {
        String password = "passwordtest";
        boolean result = mRegister.isValidPassword(password);
        assertEquals(false, result);
    }
}