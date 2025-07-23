package com.example.challenge.enums;

public enum Category {

    BURGER("Burgers"),
    DRINK("Drinks"),
    DESSERT("Desserts");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
