package com.example.lv1;

import com.example.lv1.input.ConsoleInputProvider;
import com.example.lv1.output.ConsoleOutputWriter;

public class Main {

    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(
                new ConsoleInputProvider(),
                new ConsoleOutputWriter()
        );
        kiosk.run();
    }
}
