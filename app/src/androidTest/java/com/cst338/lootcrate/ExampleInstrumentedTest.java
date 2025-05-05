package com.cst338.lootcrate;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.cst338.lootcrate", appContext.getPackageName());
    }

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }


    @Test
    public void testLandingPageToProfilePage() {
        //Sets userId because LandingPage checks loggedInUser onCreate
        Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
        intent.putExtra("com.cst338.lootcrate.LANDING_PAGE_ACTIVITY_USER_ID", 1);
        ActivityScenario.launch(intent);

        //Clicks profile button and the intended is the ProfilePage
        onView(withId(R.id.profileButton)).perform(click());
        intended(hasComponent(ProfilePageActivity.class.getName()));
    }

    @Test
    public void testProfileBack() {
        //Starts at ProfilePage and backButton intends to take you to LandingPage
        ActivityScenario<ProfilePageActivity> scenario = ActivityScenario.launch(ProfilePageActivity.class);
        onView(withId(R.id.backButton)).perform(click());
        intended(hasComponent(LandingPageActivity.class.getName()));
    }





    @Test
    public void testAdminCanAccessAnalytics() {

        //Creating an intent to launch ProfilePageActivity
        // @Author: Alberto
        Intent intent = new Intent(getApplicationContext(), ProfilePageActivity.class);
        intent.putExtra("com.cst338.lootcrate.PROFILE_PAGE_ACTIVITY_USER_ID", 1); // admin
        ActivityScenario.launch(intent);

        // Click on viewAnalytics button
        onView(withId(R.id.viewAnalytics)).perform(click());

        // This should case us to end up at AnalyticsActivity
        intended(hasComponent(AnalyticsActivity.class.getName()));

        // And so we can check that the activity is not null, as we should be in it
        assertNotNull(AnalyticsActivity.class);
    }



    @Test
    public void testSignUpPageToLoginPageOnSuccess() {
        // Launch SignUpActivity
        // author: Alberto
        ActivityScenario.launch(SignUpActivity.class);

        // Fill in valid info
        onView(withId(R.id.usernameEditText)).perform(typeText("newTestUser"), closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("test1234"), closeSoftKeyboard());
        onView(withId(R.id.confirmPasswordEditText)).perform(typeText("test1234"), closeSoftKeyboard());

        // Click the Sign Up button
        onView(withId(R.id.signUpButton)).perform(click());

        // This should cause us to end up at LoginActivity
        intended(hasComponent(LoginActivity.class.getName()));

        // Check that the activity is not null
        assertNotNull(LoginActivity.class);
    }


}