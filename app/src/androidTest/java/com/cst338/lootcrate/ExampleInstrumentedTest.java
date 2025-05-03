package com.cst338.lootcrate;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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

}