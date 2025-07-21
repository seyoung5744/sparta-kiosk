package com.example.challenge_lv1.domain;

import com.example.challenge_lv1.enums.Category;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final Long id;
    private final Category category;
    private final List<MenuItem> menuItems;

    public Menu(Long id, Category category) {
        this.id = id;
        this.category = category;
        this.menuItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems);
    }

    public void addMenuItem(MenuItem menuItem) {
        if (!this.category.equals(menuItem.getCategory())) {
            throw new IllegalArgumentException("유효하지 않은 카테고리입니다.");
        }
        menuItems.add(menuItem);
    }

    public void addMenuItems(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
    }
}
