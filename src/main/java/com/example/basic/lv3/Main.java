package com.example.basic.lv3;

import com.example.basic.lv3.input.ConsoleInputProvider;
import com.example.basic.lv3.output.ConsoleOutputWriter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", new BigDecimal("8.9"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", new BigDecimal("6.9"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", new BigDecimal("5.4"), "비프패티를 기반으로 야채가 들어간 기본버거"));

        Kiosk kiosk = new Kiosk(
                new ConsoleInputProvider(),
                new ConsoleOutputWriter(),
                menuItems
        );
        kiosk.run();
    }
}
