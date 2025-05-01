package com.cst338.lootcrate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.Game;
import com.cst338.lootcrate.databinding.ActivityLikedGamesBinding;
import com.cst338.lootcrate.viewHolders.GameRowModel;
import com.cst338.lootcrate.viewHolders.GameRowRecyclerViewAdapter;
import com.cst338.lootcrate.viewHolders.GameRowRecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class LikedGamesActivity extends AppCompatActivity implements GameRowRecyclerViewInterface {
    ActivityLikedGamesBinding binding;
    GameRowRecyclerViewAdapter adapter;
    private static final String LIKED_GAMES_ACTIVITY_USER_ID = "com.cst338.lootcrate.LIKED_GAMES_ACTIVITY_USER_ID";
    private LootCrateRepository repository;
    ArrayList<GameRowModel> gameModels = new ArrayList<GameRowModel>();
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLikedGamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = LootCrateRepository.getRepository(getApplication());
        userId = getIntent().getIntExtra(LIKED_GAMES_ACTIVITY_USER_ID, -1);

        getLikedGameModels();

        // Recycler View
        RecyclerView recyclerView = binding.likedGamesRecyclerView;
        adapter = new GameRowRecyclerViewAdapter(this, gameModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.likedGamesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // TODO: Send user to game details card when clicking on a game

        // TODO: Move game to dislikes when user clicks remove
    }

    private void getLikedGameModels() {
        if (userId == -1) {
            Log.d("LOOTCRATE", "Unable to fetch liked games. UserID is -1");
            return;
        }

        LiveData<List<Game>> likedGamesObserver = repository.getAllLikedGamesByUserId(userId);

        likedGamesObserver.observe(this, likedGames -> {
            for (Game game : likedGames) {
                GameRowModel rowModel = new GameRowModel(
                        game.getTitle(),
                        game.getImageUrl(),
                        "Move to Dislikes",
                        R.drawable.baseline_remove_circle_24
                );
                gameModels.add(rowModel);
            }

            adapter.notifyDataSetChanged();
        });
    }

    static Intent likedGamesIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, LikedGamesActivity.class);
        intent.putExtra(LIKED_GAMES_ACTIVITY_USER_ID, userId);
        return intent;
    }

    @Override
    public void onCardClick(int position) {

    }
}