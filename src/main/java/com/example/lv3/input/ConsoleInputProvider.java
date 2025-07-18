package com.example.lv3.input;

import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {

    private final Scanner sc;

    public ConsoleInputProvider() {
        sc = new Scanner(System.in);
    }

    @Override
    public String readInput() {
        return sc.nextLine();
    }
}
