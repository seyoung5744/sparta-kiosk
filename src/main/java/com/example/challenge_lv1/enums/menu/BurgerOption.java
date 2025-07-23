package com.example.challenge_lv1.enums.menu;

public enum BurgerOption implements MenuOption {

    EXIT_OPTION("0"),
    SHACKBURGER("1"),
    SMOKESHACK("2"),
    CHEESEBURGER("3"),
    HAMBURGER("4");

    private final String option;

    BurgerOption(String option) {
        this.option = option;
    }

    @Override
    public String getOption() {
        return option;
    }

}
