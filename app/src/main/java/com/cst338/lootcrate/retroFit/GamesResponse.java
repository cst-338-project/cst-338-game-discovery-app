package com.cst338.lootcrate.retroFit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GamesResponse {
    @SerializedName("results")
    private List<APIGame> results;

    public List<APIGame>  getResults() {


        return results;
    }
}
