package com.example.challenge_lv1.domain;

public class CartItem {

    private final MenuItem menuItem;
    private int quantity = 1;

    public CartItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

}
