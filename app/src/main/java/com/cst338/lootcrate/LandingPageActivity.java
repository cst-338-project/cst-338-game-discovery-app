package com.cst338.lootcrate;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.User;
import com.cst338.lootcrate.databinding.ActivityLandingPageBinding;

public class LandingPageActivity extends AppCompatActivity {

    private static final String LANDING_PAGE_ACTIVITY_USER_ID = "com.cst338.lootcrate.LANDING_PAGE_ACTIVITY_USER_ID";
    private ActivityLandingPageBinding binding;

    private LootCrateRepository repository;
    private int loggedInUserId = -1;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loggedInUserId = getIntent().getIntExtra(LANDING_PAGE_ACTIVITY_USER_ID, -1);

        repository = LootCrateRepository.getRepository(getApplication());

        loginUser(savedInstanceState);
        likeButton();
        dislikeButton();
    }

    private void loginUser(Bundle savedInstanceState) {
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                profileButton();
            }
        });
    }


    private void dislikeButton() {
        binding.dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Wire dislike button
                Toast.makeText(LandingPageActivity.this, "Disliked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void likeButton() {
        binding.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Wire like button
                Toast.makeText(LandingPageActivity.this, "Liked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void profileButton() {
        if (user != null) {
            if (user.isAdmin()) {
                binding.profileButton.setText("admin");
            } else {
                binding.profileButton.setText("profile");
            }
        }

        binding.profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProfilePageActivity.profileIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });
    }
    static Intent landingIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, LandingPageActivity.class);
        intent.putExtra(LANDING_PAGE_ACTIVITY_USER_ID, userId);
        return intent;
    }

}