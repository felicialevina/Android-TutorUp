package com.example.tutorup;

import android.content.Context;
import android.content.Intent;
import android.widget.DatePicker;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class HireUseCaseTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(Hire.class);

//Hire Use Case
    //Hire Activity display information based on intent extras
    @Test
    public void hireActivityLaunch() {
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, Hire.class);

        intent.putExtra("name", "Amy Fane");
        intent.putExtra("email", "afane@gmail.com");
        intent.putExtra("fee", 25);
        intent.putExtra("degree", "Bachelor");
        intent.putExtra("course", "Mathematics");
        mActivityRule.launchActivity(intent);
    }

    //Clicking hire button opens calendar
    @Test
    public void hireActivityLaunch2() {
        onView(withId(R.id.btnHire)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .check(matches(isDisplayed()));
    }
}
