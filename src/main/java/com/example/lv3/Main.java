package com.example.lv3;

import com.example.lv3.input.ConsoleInputProvider;
import com.example.lv3.output.ConsoleOutputWriter;

public class Main {

    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(
                new ConsoleInputProvider(),
                new ConsoleOutputWriter()
        );
        kiosk.run();
    }
}
