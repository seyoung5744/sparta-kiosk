package com.example.challenge_lv1.enums;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum Discount {

    NATIONAL_VETERAN("1", "국가유공자", 10),
    SOLDIER("2", "군인", 5),
    STUDENT("3", "학생", 3),
    GENERAL("4", "일반", 0);

    private final String option;
    private final String displayName;
    private final int discountPercent;

    Discount(String option, String displayName, int discountPercent) {
        this.option = option;
        this.displayName = displayName;
        this.discountPercent = discountPercent;
    }

    public BigDecimal applyDiscount(BigDecimal totalPrice) {
        BigDecimal discountRate = BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100));
        BigDecimal discountAmount = totalPrice.multiply(discountRate);
        return totalPrice.subtract(discountAmount);
    }

    private static final Map<String, Discount> OPTION_MAP = new HashMap<>();

    static {
        for (Discount discount : Discount.values()) {
            OPTION_MAP.put(discount.option, discount);
        }
    }

    public static Discount select(String option) {
        Discount discount = OPTION_MAP.get(option);

        if (discount == null) {
            throw new IllegalArgumentException("잘못된 할인 옵션입니다: " + option);
        }
        return discount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
}
