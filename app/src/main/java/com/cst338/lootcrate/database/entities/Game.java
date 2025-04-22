package com.cst338.lootcrate.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cst338.lootcrate.database.LootCrateDatabase;

import java.util.Objects;


@Entity(tableName = LootCrateDatabase.GAME_TABLE)
public class Game {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String author;
    private String genre;

    private String imageURL;

    public Game(String title, String author, String genre, String imageURL) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + genre +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return id == game.id && Objects.equals(title, game.title) && Objects.equals(author, game.author) && Objects.equals(genre, game.genre) && Objects.equals(imageURL, game.imageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, imageURL);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
