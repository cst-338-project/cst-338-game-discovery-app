package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.Game;
import com.cst338.lootcrate.databinding.ActivityLikedGamesBinding;

import java.util.ArrayList;
import java.util.List;

public class LikedGamesActivity extends AppCompatActivity {
    ActivityLikedGamesBinding binding;
    private static final String LIKED_GAMES_ACTIVITY_USER_ID = "com.cst338.lootcrate.LIKED_GAMES_ACTIVITY_USER_ID";
    private LootCrateRepository repository;
    ArrayList<Game> gameModels = new ArrayList<Game>();
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLikedGamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = LootCrateRepository.getRepository(getApplication());
        userId = getIntent().getIntExtra(LIKED_GAMES_ACTIVITY_USER_ID, -1);
//        getGameModels();


        binding.likedGamesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ProfilePageActivity.profileIntentFactory(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
    }

    private void getGameModels() {
        if (userId == -1) {
            Log.d("LOOTCRATE", "Unable to fetch liked games. UserID is -1");
            return;
        }
        List<Game> likedGames = repository.getAllLikedGamesByUserId(userId);
        gameModels.addAll(likedGames);


    }

    static Intent likedGamesIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, LikedGamesActivity.class);
        intent.putExtra(LIKED_GAMES_ACTIVITY_USER_ID, userId);
        return intent;
    }
}