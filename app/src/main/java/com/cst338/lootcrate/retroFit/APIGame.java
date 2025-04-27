package com.cst338.lootcrate.retroFit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class APIGame {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("released")
    private String released;

    @SerializedName("description")
    private String description;

    @SerializedName("background_image")
    private String background_image;

    @SerializedName("genres")
    private ArrayList<APIGenre> genres;

    @SerializedName("website")
    private String website;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleased() {
        return released;
    }

    public String getDescription() {
        return description;
    }

    public String getBackgroundImage() {
        return background_image;
    }

    public String getGenres() {
        return genres.get(0).name;
    }

    public String getWebsite() {
        return website;
    }
}
