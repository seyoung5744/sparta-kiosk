package com.example.challenge_lv1.utils;

import com.example.challenge_lv1.enums.Category;
import com.example.challenge_lv1.enums.menu.BurgerOption;
import com.example.challenge_lv1.enums.menu.DessertOption;
import com.example.challenge_lv1.enums.menu.DrinkOption;
import com.example.challenge_lv1.enums.menu.MenuOption;

public class MenuItemsUtil {

    public static MenuOption findByOption(MenuOption[] items, String inputOption) {
        for (MenuOption item : items) {
            if (item.getOption().equals(inputOption)) {
                return item;
            }
        }
        throw new IllegalArgumentException(inputOption + " 은 유효한 옵션이 아닙니다");
    }

    public static MenuOption[] getMenuItemsByCategory(Category category) {
        return switch (category) {
            case BURGER -> BurgerOption.values();
            case DRINK -> DrinkOption.values();
            case DESSERT -> DessertOption.values();
        };
    }

}
