package com.example.challenge.enums;

import java.util.HashMap;
import java.util.Map;

public enum KioskOption {

    EXIT_OPTION("0"),
    BURGER("1"),
    DRINK("2"),
    DESSERT("3"),
    ORDER_OPTION("4"),
    ORDER_CANCEL_OPTION("5");

    private final String option;

    KioskOption(String option) {
        this.option = option;
    }

    private static final Map<String, KioskOption> options = new HashMap<>();

    static {
        for (KioskOption option : KioskOption.values()) {
            options.put(option.getOption(), option);
        }
    }

    public static KioskOption getOption(String op) {
        KioskOption kioskOption = options.get(op);
        if (kioskOption == null) {
            throw new IllegalArgumentException(op + " 는 유효한 옵션이 아닙니다.");
        }
        return kioskOption;
    }

    public String getOption() {
        return option;
    }
}
