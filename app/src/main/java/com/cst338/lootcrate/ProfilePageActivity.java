package com.cst338.lootcrate;

import static com.cst338.lootcrate.LandingPageActivity.landingIntentFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cst338.lootcrate.databinding.ActivityProfilePageBinding;

public class ProfilePageActivity extends AppCompatActivity {

    private static final String PROFILE_PAGE_ACTIVITY_USER_ID = "com.cst338.lootcrate.PROFILE_PAGE_ACTIVITY_USER_ID";
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

        logout();
    }

    private void logout() {
        //TODO: Add logout
        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
            }
        });
    }

    static Intent profileIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, ProfilePageActivity.class);
        intent.putExtra(PROFILE_PAGE_ACTIVITY_USER_ID, userId);
        return intent;
    }
}