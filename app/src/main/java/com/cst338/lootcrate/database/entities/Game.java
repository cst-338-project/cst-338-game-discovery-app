package com.cst338.lootcrate.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cst338.lootcrate.database.LootCrateDatabase;

import java.util.List;
import java.util.Objects;


@Entity(tableName = LootCrateDatabase.GAME_TABLE)
public class Game {
    @PrimaryKey
    private int id;
    private String title;
    private String description;
    private String genre;
    private String imageUrl;
    private String released;
    private String website;
    private int metacritic;
    private List<String> screenshots;

    public Game(int id, String website, String released, String imageUrl, String genre, String description, String title, int metacritic) {
        this.id = id;
        this.website = website;
        this.released = released;
        this.imageUrl = imageUrl;
        this.genre = genre;
        this.description = description;
        this.title = title;
        this.metacritic = metacritic;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", imageURL='" + imageUrl + '\'' +
                ", website=" + website + '\'' +
                ", metacritic=" + metacritic + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && metacritic == game.metacritic && Objects.equals(title, game.title) && Objects.equals(description, game.description) && Objects.equals(genre, game.genre) && Objects.equals(imageUrl, game.imageUrl) && Objects.equals(released, game.released) && Objects.equals(website, game.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, genre, imageUrl, released, website, metacritic);
    }

    public int getMetacritic() {
        return metacritic;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleased() {
        return released;
    }

    public String getWebsite() {
        return website;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }
}
