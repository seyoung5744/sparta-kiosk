package com.example.basic.lv2;

import com.example.basic.lv2.input.ConsoleInputProvider;
import com.example.basic.lv2.output.ConsoleOutputWriter;

public class Main {

    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(
                new ConsoleInputProvider(),
                new ConsoleOutputWriter()
        );
        kiosk.run();
    }
}
