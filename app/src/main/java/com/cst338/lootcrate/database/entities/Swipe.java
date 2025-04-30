package com.cst338.lootcrate.database.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.cst338.lootcrate.database.LootCrateDatabase;

import java.util.Objects;

@Entity(tableName = LootCrateDatabase.SWIPE_TABLE,
        indices = {@Index(value = {"userId", "gameId"}, unique = true)})
public class Swipe {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private int gameId;
    private boolean isLiked;

    public Swipe(int gameId, int userId, boolean isLiked) {
        this.gameId = gameId;
        this.userId = userId;
        this.isLiked = isLiked;
    }

    @Override
    public String toString() {
        return "Swipe{" +
                "id=" + id +
                ", userId=" + userId +
                ", gameId=" + gameId +
                ", isLiked=" + isLiked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Swipe)) return false;
        Swipe swipe = (Swipe) o;
        return id == swipe.id && userId == swipe.userId && gameId == swipe.gameId && isLiked == swipe.isLiked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, gameId, isLiked);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
