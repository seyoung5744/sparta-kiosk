package com.example.challenge.utils;

import com.example.challenge.enums.Category;
import com.example.challenge.enums.menu.BurgerOption;
import com.example.challenge.enums.menu.DessertOption;
import com.example.challenge.enums.menu.DrinkOption;
import com.example.challenge.enums.menu.MenuOption;
import com.example.challenge.exception.InvalidOptionException;

public class MenuItemsUtil {

    public static MenuOption findByOption(MenuOption[] items, String inputOption) {
        for (MenuOption item : items) {
            if (item.getOption().equals(inputOption)) {
                return item;
            }
        }
        throw new InvalidOptionException(inputOption + " 은 유효한 옵션이 아닙니다");
    }

    public static MenuOption[] getMenuItemsByCategory(Category category) {
        return switch (category) {
            case BURGER -> BurgerOption.values();
            case DRINK -> DrinkOption.values();
            case DESSERT -> DessertOption.values();
        };
    }

}
