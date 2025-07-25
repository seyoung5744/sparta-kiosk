package com.example.challenge.domain;

import com.example.challenge.enums.Category;

import java.math.BigDecimal;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(id, menuItem.id) && Objects.equals(name, menuItem.name) && Objects.equals(price, menuItem.price) && category == menuItem.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }
}
