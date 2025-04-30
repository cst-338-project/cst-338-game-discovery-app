package com.cst338.lootcrate.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.lootcrate.database.entities.Game;

import java.util.List;

@Dao
public interface GameDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Game... game);

    @Query("DELETE FROM " + LootCrateDatabase.GAME_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + LootCrateDatabase.GAME_TABLE + " ORDER BY genre DESC")
    List<Game> getAllGames();

    @Query("SELECT * FROM " + LootCrateDatabase.GAME_TABLE + " WHERE id = :gameId LIMIT 1")
    Game getGameById(int gameId);

    @Query("SELECT " + LootCrateDatabase.GAME_TABLE + ".* FROM " + LootCrateDatabase.SWIPE_TABLE +
            " JOIN " + LootCrateDatabase.GAME_TABLE + " ON " + LootCrateDatabase.GAME_TABLE + ".id = "
            + LootCrateDatabase.SWIPE_TABLE + ".gameId WHERE " + LootCrateDatabase.SWIPE_TABLE +
            ".userId = :userId AND " + LootCrateDatabase.SWIPE_TABLE + ".isLiked = 1")
    LiveData<List<Game>> getAllLikedGamesByUserId(int userId);

    @Query("SELECT " + LootCrateDatabase.GAME_TABLE + ".* FROM " + LootCrateDatabase.SWIPE_TABLE +
            " JOIN " + LootCrateDatabase.GAME_TABLE + " ON " + LootCrateDatabase.GAME_TABLE + ".id = "
            + LootCrateDatabase.SWIPE_TABLE + ".gameId WHERE " + LootCrateDatabase.SWIPE_TABLE +
            ".userId = :userId AND " + LootCrateDatabase.SWIPE_TABLE + ".isLiked = 0")
    LiveData<List<Game>> getAllDislikedGamesByUserId(int userId);
}
