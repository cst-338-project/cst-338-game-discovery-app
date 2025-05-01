package com.cst338.lootcrate.viewHolders;

public class GameRowModel {
    private int id;
    private String title;
    private String image;
    private String buttonText;
    private int buttonImage;

    public GameRowModel(int id, String title, String image, String buttonText, int buttonImage) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.buttonText = buttonText;
        this.buttonImage = buttonImage;
    }

    public int getId() { return id; }

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
