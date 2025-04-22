package com.cst338.lootcrate.database;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
    
       


}
