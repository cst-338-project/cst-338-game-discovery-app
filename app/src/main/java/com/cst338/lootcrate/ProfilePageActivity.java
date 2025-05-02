package com.cst338.lootcrate;

import static com.cst338.lootcrate.LandingPageActivity.landingIntentFactory;
import static com.cst338.lootcrate.LikedGamesActivity.likedGamesIntentFactory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.User;
import com.cst338.lootcrate.databinding.ActivityProfilePageBinding;

public class ProfilePageActivity extends AppCompatActivity {

    private static final String PROFILE_PAGE_ACTIVITY_USER_ID = "com.cst338.lootcrate.PROFILE_PAGE_ACTIVITY_USER_ID";
    private static final int LOGGED_OUT = -1;
    private ActivityProfilePageBinding binding;

    private LootCrateRepository repository;

    private User user;

    private int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loggedInUserId = getIntent().getIntExtra(PROFILE_PAGE_ACTIVITY_USER_ID, -1);
        repository = LootCrateRepository.getRepository(getApplication());

        repository.getUserByUserId(loggedInUserId).observe(this, user1 -> {
            if(user1 != null) {
                user = user1;
                viewAnalytics();
            }
        });

        likedGames();
        dislikedGames();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = landingIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogOutDialog();
            }
        });
    }

    private void viewAnalytics() {
        if(!user.isAdmin()) {
            binding.viewAnalytics.setVisibility(View.INVISIBLE);
        } else {
            binding.viewAnalytics.setVisibility(View.VISIBLE);
        }

        binding.viewAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AnalyticsActivity.analyticsIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });
    }

    private void likedGames() {
        binding.likedGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LikedGamesActivity.likedGamesIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });
    }

    private void dislikedGames() {
        binding.dislikedGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DislikedGamesActivity.dislikedGamesIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });
    }
    private void showLogOutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProfilePageActivity.this);
        final AlertDialog alertDialog = alertBuilder.create(); // instantiating memory for alert dialog, singleton make sure one alert dialog at a time

        alertBuilder.setMessage("Logout?");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }
    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userId_key), LOGGED_OUT);
        sharedPrefEditor.apply();

        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), LOGGED_OUT));
    }

    static Intent profileIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ProfilePageActivity.class);
        intent.putExtra(PROFILE_PAGE_ACTIVITY_USER_ID, userId);
        return intent;
    }
}