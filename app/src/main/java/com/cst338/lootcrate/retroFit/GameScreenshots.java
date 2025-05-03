package com.cst338.lootcrate.retroFit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameScreenshots {
    @SerializedName("results")
    List<APIScreenshot> results;

    public List<String> getScreenshots() {
        List<String> screenshots = new ArrayList<>();

        // Push each screenshot url in the screenshots
        for (APIScreenshot screenshot : results) {
            screenshots.add(screenshot.getImage());
        }

        return screenshots;
    }
}
