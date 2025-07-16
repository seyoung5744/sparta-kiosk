package com.example.lv1;

import com.example.lv1.input.InputProvider;
import com.example.lv1.output.OutputWriter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Kiosk {

    private static final List<MenuItem> menuItems = new ArrayList<>();

    static {
        menuItems.add(new MenuItem("ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", new BigDecimal("8.9"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", new BigDecimal("6.9"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", new BigDecimal("5.4"), "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    private final InputProvider input;
    private final OutputWriter writer;

    public Kiosk(InputProvider input, OutputWriter writer) {
        this.input = input;
        this.writer = writer;
    }

    public void run() {

        while (true) {

            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem menuItem = menuItems.get(i);
                writer.println(String.format("%s. %-15s | W %s | %s", (i + 1), menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
            }
            System.out.println("0. 종료");

            String option = input.readInput();
            if ("0".equals(option)) {
                writer.println("프로그램을 종료합니다.");
                break;
            }
        }
    }
}
