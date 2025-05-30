package com.cst338.lootcrate;


import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import static com.cst338.lootcrate.DislikedGamesActivity.DISLIKED_GAMES_ACTIVITY_USER_ID;
import static com.cst338.lootcrate.LandingPageActivity.LANDING_PAGE_ACTIVITY_USER_ID;
import static com.cst338.lootcrate.LikedGamesActivity.LIKED_GAMES_ACTIVITY_USER_ID;
import static com.cst338.lootcrate.ProfilePageActivity.PROFILE_PAGE_ACTIVITY_USER_ID;
import static com.cst338.lootcrate.GameDetailsActivity.GAME_DETAILS_ACTIVITY_GAME_ID;
import static com.cst338.lootcrate.GameDetailsActivity.GAME_DETAILS_ACTIVITY_USER_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

    @Test
    public void testLikedGamesIntentFactory() {
        Context context = getApplicationContext();
        int testUserID = 2;

        Intent intent = LikedGamesActivity.likedGamesIntentFactory(context, testUserID);

        assertEquals(LikedGamesActivity.class.getName(), intent.getComponent().getClassName());
        assertTrue(intent.hasExtra(LIKED_GAMES_ACTIVITY_USER_ID));
        assertEquals(2, intent.getIntExtra(LIKED_GAMES_ACTIVITY_USER_ID, -1));
    }

    @Test
    public void testDislikedGamesIntentFactory() {
        Context context = getApplicationContext();
        int testUserID = 2;

        Intent intent = DislikedGamesActivity.dislikedGamesIntentFactory(context, testUserID);

        assertEquals(DislikedGamesActivity.class.getName(), intent.getComponent().getClassName());
        assertTrue(intent.hasExtra(DISLIKED_GAMES_ACTIVITY_USER_ID));
        assertEquals(2, intent.getIntExtra(DISLIKED_GAMES_ACTIVITY_USER_ID, -1));
    }

    @Test
    public void testLoginIntentFactory() {
        Context context = getApplicationContext();

        Intent intent = LoginActivity.loginIntentFactory(context);

        assertNotNull(intent);
        assertEquals(LoginActivity.class.getName(), intent.getComponent().getClassName());

    }

    @Test
    public void testGameDetailsIntentFactory() {
        Context context = getApplicationContext();
        int testUserID = 3;
        int testGameID = 4;

        Intent intent = GameDetailsActivity.gameDetailsIntentFactory(context, testGameID, testUserID);

        assertEquals(GameDetailsActivity.class.getName(), intent.getComponent().getClassName());
        assertTrue(intent.hasExtra("GameId"));
        assertTrue(intent.hasExtra("UserId"));
        assertEquals(3, intent.getIntExtra("UserId", -1));
        assertEquals(4, intent.getIntExtra("GameId", -1));
    }
}
