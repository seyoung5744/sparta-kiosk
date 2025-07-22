package com.example.challenge_lv1.view;

import com.example.challenge_lv1.domain.Menu;
import com.example.challenge_lv1.domain.MenuItem;
import com.example.challenge_lv1.output.OutputWriter;

import java.util.List;

public class MenuView {

    private final OutputWriter writer;

    public MenuView(OutputWriter writer) {
        this.writer = writer;
    }

    public void printMainMenu(List<Menu> menus) {
        writer.println("[ MAIN MENU ]");
        for (Menu menu : menus) {
            writer.println(String.format("%d. %s", menu.getId(), menu.getCategory().getDescription()));
        }
        writer.println("0. 종료");
    }

    public void printMenuItems(Menu menu, List<MenuItem> menuItems) {
        writer.println("[ " + menu.getCategory().getDescription().toUpperCase() + " MENU ]");
        for (MenuItem menuItem : menuItems) {
            writer.println(String.format("%s. %-15s | W %s | %s", menuItem.getId(), menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
        }
        writer.println("0. 뒤로가기");
    }
}
