package com.example.lv4;

import com.example.lv4.input.InputProvider;
import com.example.lv4.output.OutputWriter;
import com.example.lv4.utils.Parser;

import java.util.List;

public class Kiosk {

    private final InputProvider input;
    private final OutputWriter writer;

    private final List<MenuItem> menuItems;

    public Kiosk(InputProvider input, OutputWriter writer, List<MenuItem> menuItems) {
        this.input = input;
        this.writer = writer;
        this.menuItems = menuItems;
    }

    public void run() {

        while (true) {
            try {
                for (int i = 0; i < menuItems.size(); i++) {
                    MenuItem menuItem = menuItems.get(i);
                    writer.println(String.format("%s. %-15s | W %s | %s", (i + 1), menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
                }
                System.out.println("0. 종료");

                String option = Parser.parseNum(input.readInput());

                if ("0".equals(option)) {
                    writer.println("프로그램을 종료합니다.");
                    break;
                }
            } catch (RuntimeException e) {
                writer.println(e.getMessage());
            }
        }
    }
}
