package com.cst338.lootcrate.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.cst338.lootcrate.database.entities.User;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LootCrateRepository {
    private final UserDAO userDAO;
    private static LootCrateRepository repository;

    public LootCrateRepository(Application application) {
        LootCrateDatabase db = LootCrateDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
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

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }
}
