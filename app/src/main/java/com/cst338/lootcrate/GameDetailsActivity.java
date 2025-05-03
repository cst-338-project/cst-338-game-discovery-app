package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.cst338.lootcrate.database.GameDAO;
import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.Game;
import com.cst338.lootcrate.databinding.ActivityGameDetailsBinding;

public class GameDetailsActivity extends AppCompatActivity {

    private ActivityGameDetailsBinding binding;

    private LootCrateRepository repository;
    private int gameId = 1;
    private int userId = 1;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = LootCrateRepository.getRepository(this.getApplication());
        gameId = getIntent().getIntExtra("GameId", gameId);
        game = repository.getGameById(gameId);

        Log.d("GAME SCREENSHOTS", game.getScreenshot1() + game.getScreenshot2() + game.getScreenshot3());

       setInfo();
       //TODO: Use userId for back button
       binding.gameDetailsBackButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }

    public void setInfo(){
        binding.gameDetailsTitleTextView.setText(game.getTitle());
        binding.gameDetailsGenreTextView.setText(game.getGenre());
        binding.gameDetailsDescriptionTextView.setText(game.getDescription());
        Glide.with(this)
                .load(game.getImageUrl())
                .into(binding.gameDetailsCoverImageView);
        binding.gameDetailsMetacriticScoreTextView.setText(String.valueOf("Metacritic: " + game.getMetacritic()));
        binding.gameDetailsLinksTextView.setText(game.getWebsite());

    }

    static Intent gameDetailsIntentFactory(Context context, int gameId, int userId){
        Intent intent = new Intent(context, GameDetailsActivity.class);
        intent.putExtra("GameId", gameId);
        intent.putExtra("UserId", userId);
        return intent;
    }
}