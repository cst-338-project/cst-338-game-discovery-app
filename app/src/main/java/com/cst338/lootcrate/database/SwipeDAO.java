package com.cst338.lootcrate.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.lootcrate.database.entities.GameAnalytics;
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



    @Query("SELECT g.title AS title, s.gameId AS gameId, " +
            "SUM(CASE WHEN s.isLiked = 1 THEN 1 ELSE 0 END) AS likeCount, " +
            "SUM(CASE WHEN s.isLiked = 0 THEN 1 ELSE 0 END) AS dislikeCount " +
            "FROM " + LootCrateDatabase.SWIPE_TABLE + " s " +
            "JOIN " + LootCrateDatabase.GAME_TABLE + " g ON s.gameId = g.id " +
            "GROUP BY s.gameId")
    LiveData<List<GameAnalytics>> getGameAnalyticsWithTitle();


}
