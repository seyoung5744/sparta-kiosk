package com.example.lv4.domain;

import com.example.lv4.enums.Category;

import java.math.BigDecimal;

public class MenuItem {

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final String description;
    private final Category category;

    public MenuItem(Long id, String name, BigDecimal price, String description, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
