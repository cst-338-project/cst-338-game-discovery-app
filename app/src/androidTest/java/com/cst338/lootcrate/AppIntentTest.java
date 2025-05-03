package com.cst338.lootcrate;


import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import static com.cst338.lootcrate.LandingPageActivity.LANDING_PAGE_ACTIVITY_USER_ID;
import static com.cst338.lootcrate.ProfilePageActivity.PROFILE_PAGE_ACTIVITY_USER_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppIntentTest {

    @Test
    public void testLandingPageIntentFactory() {
        Context context = getApplicationContext();
        int testUserID = 69;

        Intent intent = LandingPageActivity.landingIntentFactory(context, testUserID);

        assertEquals(LandingPageActivity.class.getName(), intent.getComponent().getClassName());

        assertTrue(intent.hasExtra(LANDING_PAGE_ACTIVITY_USER_ID));
        assertEquals(69, intent.getIntExtra(LANDING_PAGE_ACTIVITY_USER_ID, -1));
    }

    @Test
    public void testProfilePageIntentFactory() {
        Context context = getApplicationContext();
        int testUserID = 69;

        Intent intent = ProfilePageActivity.profileIntentFactory(context, testUserID);

        assertEquals(ProfilePageActivity.class.getName(), intent.getComponent().getClassName());

        assertTrue(intent.hasExtra(PROFILE_PAGE_ACTIVITY_USER_ID));
        assertEquals(69, intent.getIntExtra(PROFILE_PAGE_ACTIVITY_USER_ID, -1));
    }
}
