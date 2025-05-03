package com.cst338.lootcrate.database;

import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cst338.lootcrate.database.entities.User;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Query("DELETE FROM " + LootCrateDatabase.USER_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + LootCrateDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUserName(String username);

    @Query("SELECT * FROM " + LootCrateDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);


    @Update
    void updateUser(User user);
}
