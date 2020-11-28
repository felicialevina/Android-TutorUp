package com.example.tutorup;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TimespanUseCaseTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(Payment.class);

//Timespan Use Case
    //When max hour exceeded
    @Test
    public void improperTimespan() {
        onView(withId(R.id.txtHours)).perform(typeText("4"), closeSoftKeyboard());
        onView(withId(R.id.btnSave)).perform(click());
        onView(withText("Maximum number of hours is 3"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    //When timespan field is empty
    @Test
    public void emptyTimespan() {
        onView(withId(R.id.txtHours)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnSave)).perform(click());
        onView(withText("Insert valid number of hour(s)"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    //When timespan input is 0
    @Test
    public void zeroTimespan() {
        onView(withId(R.id.txtHours)).perform(typeText("0"), closeSoftKeyboard());
        onView(withId(R.id.btnSave)).perform(click());
        onView(withText("Insert valid number of hour(s)"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


}
