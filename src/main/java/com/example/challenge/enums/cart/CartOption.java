package com.example.challenge.enums.cart;

public enum CartOption {

    CONFIRM("1"),
    EXIT_OPTION("2"),
    CANCEL("3");

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

    public static boolean isCancel(String op) {
        return CANCEL.getOption().equals(op);
    }

}
