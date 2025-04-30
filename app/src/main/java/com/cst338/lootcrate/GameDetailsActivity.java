package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cst338.lootcrate.database.entities.Game;

public class GameDetailsActivity extends AppCompatActivity {
    private Game game;
    private int gameId = getIntent().getIntExtra("gameId", -1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

    }

    static Intent gameDetailsIntentFactory(Context context, int gameId){
        return new Intent(context, GameDetailsActivity.class);
    }
}