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
    private ActivityProfilePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        binding = ActivityProfilePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = landingIntentFactory(getApplicationContext());
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

            }
        });
    }

    static Intent profileIntentFactory(Context context) {
        return new Intent(context, ProfilePageActivity.class);
    }
}