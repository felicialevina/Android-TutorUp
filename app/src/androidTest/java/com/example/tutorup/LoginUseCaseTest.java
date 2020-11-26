package com.example.tutorup;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
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
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginUseCaseTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(Login.class);
/*
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.tutorup", appContext.getPackageName());
    }
*/
//Login Use Case
    //When one of the field is empty
   @Test
   public void loginActivityLaunch() {

       onView(withId(R.id.txtEmail)).perform(typeText("flevina"));
       onView(withId(R.id.txtPass)).perform(typeText(""), closeSoftKeyboard());
       onView(withId(R.id.btnLogin2)).perform(click());
       onView(withText("Fill in all fields"))
               .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
               .check(matches(isDisplayed()));
   }

    //When wrong password
    @Test
    public void loginActivityLaunch2() {
        onView(withId(R.id.txtEmail)).perform(typeText("flevina"));
        onView(withId(R.id.txtPass)).perform(typeText("b"), closeSoftKeyboard());
        onView(withId(R.id.radST)).perform(click());
        onView(withId(R.id.btnLogin2)).perform(click());
        onView(withText("Incorrect password"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    //When account does not exist
    @Test
    public void loginActivityLaunch3() {
        onView(withId(R.id.txtEmail)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.txtPass)).perform(typeText("b"), closeSoftKeyboard());
        onView(withId(R.id.radST)).perform(click());
        onView(withId(R.id.btnLogin2)).perform(click());
        onView(withText("Student account not found"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


    //When student or tutor radio button not selected
    @Test
    public void loginActivityLaunch4() {
        onView(withId(R.id.txtEmail)).perform(typeText("flevina"));
        onView(withId(R.id.txtPass)).perform(typeText("a"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin2)).perform(click());
        onView(withText("Select tutor or student"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

/*
    //When student enter login properly
    @Test
    public void loginActivityLaunch5() {
        onView(withId(R.id.txtEmail)).perform(typeText("flevina"));
        onView(withId(R.id.txtPass)).perform(typeText("a"), closeSoftKeyboard());
        onView(withId(R.id.radST)).perform(click());
        onView(withId(R.id.txtSearch)).check(matches(isDisplayed()));
    }
*/
}
