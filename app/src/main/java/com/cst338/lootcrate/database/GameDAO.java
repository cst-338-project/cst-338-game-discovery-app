package com.cst338.lootcrate.database;

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
}
