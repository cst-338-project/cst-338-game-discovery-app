package com.cst338.lootcrate.viewHolders;

public class GameRowModel {
    private String title;
    private String image;
    private String buttonText;
    private int buttonImage;

    public GameRowModel(String title, String image, String buttonText, int buttonImage) {
        this.title = title;
        this.image = image;
        this.buttonText = buttonText;
        this.buttonImage = buttonImage;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getButtonText() {
        return buttonText;
    }

    public int getButtonImage() {
        return buttonImage;
    }
}
