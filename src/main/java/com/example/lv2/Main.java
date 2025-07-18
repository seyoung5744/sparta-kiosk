package com.example.lv2;

import com.example.lv2.input.ConsoleInputProvider;
import com.example.lv2.output.ConsoleOutputWriter;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(
                new ConsoleInputProvider(),
                new ConsoleOutputWriter()
        );
        kiosk.run();
    }
}
