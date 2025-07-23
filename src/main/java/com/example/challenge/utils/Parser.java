package com.example.challenge.utils;

import com.example.challenge.exception.InvalidOptionException;

import java.util.regex.Pattern;

public abstract class Parser {

    /**
     * 양의 정수만을 나타내는 정규식
     */
    private static final Pattern OPTION_PATTERN = Pattern.compile("^\\d+$");

    public static Long parseLong(String input) {
        boolean isNum = OPTION_PATTERN.matcher(input).matches();
        if (!isNum) {
            throw new InvalidOptionException(input + "은 유효한 옵션이 아닙니다!");
        }

        return Long.valueOf(input);
    }
}
