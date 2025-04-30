package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.lootcrate.database.GameDAO;
import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.Game;
import com.cst338.lootcrate.databinding.ActivityGameDetailsBinding;

public class GameDetailsActivity extends AppCompatActivity {

    private ActivityGameDetailsBinding binding;

    private LootCrateRepository repository;
    private int gameId = 1;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = LootCrateRepository.getRepository(this.getApplication());
        gameId = getIntent().getIntExtra("GameId", gameId);
//        game = repository.getGameById(gameId);
        game = repository.getGameById(28);

        binding.gameDetailsTitleTextView.setText(game.getTitle());
        binding.gameDetailsGenreTextView.setText(game.getGenre());
//        binding.imageView.setImageDrawable(game.getImageUrl());

    }

    static Intent gameDetailsIntentFactory(Context context, int gameId){
        Intent intent = new Intent(context, GameDetailsActivity.class);
        intent.putExtra("GameId", gameId);
        return intent;
    }
}