package com.cst338.lootcrate.retroFit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GameDetails {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description_raw")
    private String description;

    @SerializedName("released")
    private String released;

    @SerializedName("background_image")
    private String backgroundImage;

    @SerializedName("website")
    private String website;

    @SerializedName("genres")
    private ArrayList<APIGenre> genres;

    @SerializedName("metacritic")
    private int metacritic;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getReleased() {
        return released;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getWebsite() {
        return website;
    }

    public String getGenre() {
        return genres.get(0).name;
    }

    public int getMetacritic() { return metacritic; }
}
