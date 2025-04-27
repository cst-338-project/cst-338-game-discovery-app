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
}
