package com.cst338.lootcrate.database;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cst338.lootcrate.database.entities.Game;

import java.util.List;

public interface GameDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Game game);

    @Query("DELETE FROM " + LootCrateDatabase.GAME_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + LootCrateDatabase.GAME_TABLE + " ORDER BY price DESC")
    List<Game> getAllGames();
}
