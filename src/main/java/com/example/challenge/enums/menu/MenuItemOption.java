package com.example.challenge.enums.menu;

public enum MenuItemOption implements MenuOption {

    EXIT_OPTION("0");

    private final String option;

    MenuItemOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public static boolean isExit(String op) {
        return EXIT_OPTION.getOption().equals(op);
    }

}
