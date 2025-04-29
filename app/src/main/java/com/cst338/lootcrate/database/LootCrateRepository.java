package com.cst338.lootcrate.database;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cst338.lootcrate.database.entities.Game;
import com.cst338.lootcrate.database.entities.Swipe;
import com.cst338.lootcrate.database.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LootCrateRepository {
    private final UserDAO userDAO;
    private final GameDAO gameDAO;
    private final SwipeDAO swipeDAO;
    private static LootCrateRepository repository;

    public LootCrateRepository(Application application) {
        LootCrateDatabase db = LootCrateDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
        this.gameDAO = db.gameDAO();
        this.swipeDAO = db.swipeDAO();
    }

    public static LootCrateRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }

        Future<LootCrateRepository> future = LootCrateDatabase.databaseWriteExecutor.submit(
                new Callable<LootCrateRepository>() {
                    @Override
                    public LootCrateRepository call() throws Exception {
                        repository = new LootCrateRepository(application);
                        return repository;
                    }
                }
        );

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d("LOOTCRATE", "Problem getting LootCrateRepository, thread error.");
        }
        return null;
    }


    public void insertUser(User... user){
        LootCrateDatabase.databaseWriteExecutor.execute(()->
        {
            userDAO.insert(user);
        });
    }

    public void insertGame(Game... game) {
        LootCrateDatabase.databaseWriteExecutor.execute(()->
        {
            gameDAO.insert(game);
        });
    }

    public void insertSwipe(Swipe swipe) {
        LootCrateDatabase.databaseWriteExecutor.execute(()->
        {
            swipeDAO.insert(swipe);
        });
    }


    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }



    public boolean alrHasUser(String username){
        boolean hasUser = false;
        SupportSQLiteDatabase db = LootCrateDatabase.getDatabase(null).getOpenHelper().getWritableDatabase();
        String query = "SELECT * FROM " + LootCrateDatabase.USER_TABLE + " WHERE username = ?";
        Cursor cursor = db.query(query, new String[]{username});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                hasUser = true;
            }
            cursor.close();
        }
        return hasUser;
    }


    public Swipe getLikeDislikeForUserAndGame(int userId, int gameId) {
        return swipeDAO.getLikeDislikeForUserAndGame(userId,gameId);
    }

    public Game getGameById(int gameId) {
        Future<Game> future = LootCrateDatabase.databaseWriteExecutor.submit(() ->
                gameDAO.getGameById(gameId)
        );
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Log.i("LOOT", "Problem when getting game by id in repo");
        }
        return null;
    }

    public List<Game> getAllGames() {
        return gameDAO.getAllGames();
    }
}
