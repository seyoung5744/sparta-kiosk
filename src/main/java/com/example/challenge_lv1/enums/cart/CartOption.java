package com.example.challenge_lv1.enums.cart;

public enum CartOption {

    CONFIRM("1"),
    EXIT_OPTION("2");

    private final String option;

    CartOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public static boolean isExit(String op) {
        return EXIT_OPTION.getOption().equals(op);
    }

}
