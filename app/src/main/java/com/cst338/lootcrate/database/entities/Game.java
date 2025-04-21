package com.cst338.lootcrate.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cst338.lootcrate.database.LootCrateDatabase;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity(tableName = LootCrateDatabase.GAME_TABLE)
public class Game {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String author;
    private double price;

    private String imageURL;

    public Game(int id, String title, String author, double price, String imageURL) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
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
                ", price=" + price +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return id == game.id && Double.compare(price, game.price) == 0 && Objects.equals(title, game.title) && Objects.equals(author, game.author) && Objects.equals(imageURL, game.imageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, price, imageURL);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
