package com.example.challenge.domain;

import com.example.challenge.enums.Category;
import com.example.challenge.exception.InvalidCategoryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private final Long id;
    private final Category category;
    private final List<MenuItem> menuItems;
    private final Map<Long, MenuItem> menuItemMap;

    public Menu(Long id, Category category) {
        this.id = id;
        this.category = category;
        this.menuItems = new ArrayList<>();
        this.menuItemMap = new HashMap<>();
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

    public MenuItem getMenuItem(Long id) {
        return menuItemMap.get(id);
    }

    public void addMenuItem(MenuItem menuItem) {
        if (!this.category.equals(menuItem.getCategory())) {
            throw new InvalidCategoryException("유효하지 않은 카테고리입니다.");
        }
        menuItems.add(menuItem);
        menuItemMap.put(menuItem.getId(), menuItem);
    }

    public void addMenuItems(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
    }
}
