package com.example.databasedemo;

public class Showrooms {
    private String showroom;
    private int image;


    public String getShowroom() {
        return showroom;
    }

    public void setShowroom(String showroom) {
        this.showroom = showroom;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Showrooms(String showroom, int image) {
        this.showroom = showroom;
        this.image = image;
    }
}
