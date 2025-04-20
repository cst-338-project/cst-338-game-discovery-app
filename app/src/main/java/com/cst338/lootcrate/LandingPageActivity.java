package com.cst338.lootcrate;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.User;
import com.cst338.lootcrate.databinding.ActivityLandingPageBinding;

public class LandingPageActivity extends AppCompatActivity {

    private ActivityLandingPageBinding binding;

    private LootCrateRepository repository;
    private int loggedInUserId = -1;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        profileButton();
        likeButton();
        dislikeButton();
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
                Intent intent = ProfilePageActivity.profileIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }
    static Intent landingIntentFactory(Context context) {
        return new Intent(context, LandingPageActivity.class);
    }

}