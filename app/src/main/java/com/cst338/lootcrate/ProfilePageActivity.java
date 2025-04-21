package com.cst338.lootcrate;

import static com.cst338.lootcrate.LandingPageActivity.landingIntentFactory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.lootcrate.databinding.ActivityProfilePageBinding;

public class ProfilePageActivity extends AppCompatActivity {

    private static final String PROFILE_PAGE_ACTIVITY_USER_ID = "com.cst338.lootcrate.PROFILE_PAGE_ACTIVITY_USER_ID";
    private static final int LOGGED_OUT = -1;
    private ActivityProfilePageBinding binding;

    private int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loggedInUserId = getIntent().getIntExtra(PROFILE_PAGE_ACTIVITY_USER_ID, -1);

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
        startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
    }

    static Intent profileIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ProfilePageActivity.class);
        intent.putExtra(PROFILE_PAGE_ACTIVITY_USER_ID, userId);
        return intent;
    }
}