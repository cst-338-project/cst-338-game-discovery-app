package com.cst338.lootcrate.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.lootcrate.database.entities.Swipe;

import java.util.List;

@Dao
public interface SwipeDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Swipe swipe);

    @Query("SELECT * FROM " + LootCrateDatabase.SWIPE_TABLE + " WHERE userId == :userId AND gameId == :gameId LIMIT 1")
    Swipe getLikeDislikeForUserAndGame(int userId, int gameId);


    @Query("SELECT gameId FROM " + LootCrateDatabase.SWIPE_TABLE + " WHERE userId == :userId AND isLiked == 1")
    LiveData<List<Integer>> getAllLikedGameIdsForUser(int userId);

    @Query("SELECT gameId FROM " + LootCrateDatabase.SWIPE_TABLE + " WHERE userId == :userId AND isLiked == 0")
    LiveData<List<Integer>> getAllDislikedGameIdsForUser(int userId);

}
