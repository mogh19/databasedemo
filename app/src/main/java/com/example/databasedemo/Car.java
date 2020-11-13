package com.example.databasedemo;

public class Car {
     private int id;
     private String model;
     private String color;
     private Double dpl;
     private  String image;
     private  String description;

    public Car(int id, String model, String color, Double dpl, String image, String description) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.dpl = dpl;
        this.image = image;
        this.description = description;
    }

    public Car(String model, String color, Double dpl, String image, String description) {
        this.model = model;
        this.color = color;
        this.dpl = dpl;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getDpl() {
        return dpl;
    }

    public void setDpl(Double dpl) {
        this.dpl = dpl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
