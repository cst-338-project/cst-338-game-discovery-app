package com.cst338.lootcrate;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.bumptech.glide.Glide;
import com.cst338.lootcrate.database.LootCrateDatabase;
import com.cst338.lootcrate.database.LootCrateRepository;
import com.cst338.lootcrate.database.entities.Game;
import com.cst338.lootcrate.database.entities.Swipe;
import com.cst338.lootcrate.database.entities.User;
import com.cst338.lootcrate.databinding.ActivityLandingPageBinding;
import com.cst338.lootcrate.retroFit.APIClient;
import com.cst338.lootcrate.retroFit.APIGame;
import com.cst338.lootcrate.retroFit.GameDetails;
import com.cst338.lootcrate.retroFit.GamesResponse;
import com.cst338.lootcrate.retroFit.RAWGApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingPageActivity extends AppCompatActivity {
    private static final String RAWG_API_KEY = BuildConfig.RAWG_API_KEY;
    RAWGApiService apiService = null;
    private static final String LANDING_PAGE_ACTIVITY_USER_ID = "com.cst338.lootcrate.LANDING_PAGE_ACTIVITY_USER_ID";
    private static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.cst338.lootcrate.SAVED_INSTANCE_STATE_USERID_KEY";
    private ActivityLandingPageBinding binding;
    private LootCrateRepository repository;
    private int loggedInUserId = -1;
    private final int LOGGED_OUT = -1;
    private User user;
    private ArrayList<Game> gameList = new ArrayList<>();
    private int currIndex = 1;
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = LootCrateRepository.getRepository(getApplication());
        
        // API
        apiService = APIClient.getClient().create(RAWGApiService.class);
        fetchGamesList();

        loggedInUserId = getIntent().getIntExtra(LANDING_PAGE_ACTIVITY_USER_ID, -1);


        loginUser(savedInstanceState);
        likeButton();
        dislikeButton();
    }

    /**
     * Method to fetch list of games.
     */
    private void fetchGamesList() {
        Call<GamesResponse> call = apiService.getGames(RAWG_API_KEY, page, 10);
        call.enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<APIGame> games = response.body().getResults();
                    for (APIGame game : games) {
                        int id = game.getId();
                        if(!containsGame(id)) {
                            fetchGameDetails(id);
                        }
                    }
                } else {
                    Log.e("API Error", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable throwable) {
                if (throwable.getMessage() != null) {
                    Log.e("API ERROR", throwable.getMessage());
                }
            }
        });
    }

    /**
     * Method to fetch game details by gameID
     */
    private void fetchGameDetails(int gameId) {
        Call<GameDetails> call = apiService.getGameDetails(gameId, RAWG_API_KEY);
        call.enqueue(new Callback<GameDetails>() {
            @Override
            public void onResponse(Call<GameDetails> call, Response<GameDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GameDetails details = response.body();

                    if(!containsGame(details.getId())) {
                        Game currentGame = new Game(
                                details.getId(),
                                details.getWebsite(),
                                details.getReleased(),
                                details.getBackgroundImage(),
                                details.getGenre(),
                                details.getDescription(),
                                details.getName()
                        );
                        gameList.add(currentGame);
                        repository.insertGame(currentGame);
                        if (gameList.size() == 1) {
                            displayGame(currentGame);
                        }
                        Log.d("LOOTCRATE", currentGame.toString());
                    }

                } else {
                    Log.e("LOOTCRATE", "Game details fetch failed for ID: " + gameId);
                }
            }

            @Override
            public void onFailure(Call<GameDetails> call, Throwable throwable) {
                Log.e("LOOTCRATE", "Game details fetch error: " + throwable.getMessage());
            }
        });
    }

    private boolean containsGame(int id) {
        for(Game game: gameList) {
            if(game.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private void displayGame(Game game) {
        //Loads game image into gameImage
        Glide.with(this)
                .load(game.getImageUrl())
                .into(binding.gameImage);
        binding.gameTitle.setText(game.getTitle());
        binding.gamePrice.setText(game.getReleased());
    }

    private void displayNextGame() {
        //Runs on background thread so Activity doesn't crash grabbing swipe
        LootCrateDatabase.getDatabaseWriteExecutor().execute(() -> {
            while (currIndex < gameList.size()) {
                Game next = gameList.get(currIndex);
                Swipe swipe = repository.getLikeDislikeForUserAndGame(user.getId(), next.getId());

                if (swipe == null) {
                    currIndex++;
                    runOnUiThread(() -> displayGame(next));
                    return;
                } else {
                    currIndex++; // move forward if already swiped
                }

                if(gameList.size() - currIndex <= 5) {
                    loadGames();
                }
            }

            loadGames();
            runOnUiThread(() -> Toast.makeText(this, "Loading more games...", Toast.LENGTH_SHORT).show());
        });

    }

    private void loadGames() {
        page++;
        fetchGamesList();
    }


    private void loginUser(Bundle savedInstanceState) {
        //checked shared pref for logged in user
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);

        loggedInUserId = sharedPreferences.getInt(getString(R.string.preference_userId_key), LOGGED_OUT);
        if(loggedInUserId == LOGGED_OUT & savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)) {
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY, LOGGED_OUT);
        }
        if(loggedInUserId == LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(LANDING_PAGE_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if(loggedInUserId == LOGGED_OUT) {
            Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), LOGGED_OUT);
            startActivity(intent);
        }
        updateSharedPreferences();
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (user != null) {
                profileButton();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INSTANCE_STATE_USERID_KEY, loggedInUserId);
        updateSharedPreferences();
    }
    private void updateSharedPreferences() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userId_key), loggedInUserId);
        sharedPrefEditor.apply();
    }


    private void dislikeButton() {
        binding.dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Wire dislike button
                Toast.makeText(LandingPageActivity.this, "Disliked", Toast.LENGTH_SHORT).show();
                repository.insertSwipe(new Swipe(gameList.get(currIndex).getId(), user.getId(),false));
                displayNextGame();
            }
        });
    }

    private void likeButton() {
        binding.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Wire like button
                Toast.makeText(LandingPageActivity.this, "Liked", Toast.LENGTH_SHORT).show();
                repository.insertSwipe(new Swipe(gameList.get(currIndex).getId(), user.getId(),true));
                displayNextGame();

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