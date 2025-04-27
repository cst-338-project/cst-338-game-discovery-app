package com.cst338.lootcrate.retroFit;

import com.google.gson.annotations.SerializedName;

public class APIGame {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("released")
    private String released;

    @SerializedName("summary")
    private String summary;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("genre")
    private String genre;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleased() {
        return released;
    }

    public String getSummary() {
        return summary;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getGenre() {
        return genre;
    }
}
