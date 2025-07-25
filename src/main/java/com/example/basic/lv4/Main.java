package com.example.basic.lv4;

import com.example.basic.lv4.domain.Menu;
import com.example.basic.lv4.domain.MenuItem;
import com.example.basic.lv4.enums.Category;
import com.example.basic.lv4.input.ConsoleInputProvider;
import com.example.basic.lv4.output.ConsoleOutputWriter;
import com.example.basic.lv4.repository.MemoryMenuRepository;
import com.example.basic.lv4.service.MenuService;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MenuService menuService = new MenuService(new MemoryMenuRepository());

        Menu burger = new Menu(1L, Category.BURGER);
        Menu drink = new Menu(2L, Category.DRINK);
        Menu dessert = new Menu(3L, Category.DESSERT);
        burger.addMenuItems(
                List.of(
                        new MenuItem(1L, "ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거", Category.BURGER),
                        new MenuItem(2L, "SmokeShack", new BigDecimal("8.9"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", Category.BURGER),
                        new MenuItem(3L, "Cheeseburger", new BigDecimal("6.9"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", Category.BURGER),
                        new MenuItem(4L, "Hamburger", new BigDecimal("5.4"), "비프패티를 기반으로 야채가 들어간 기본버거", Category.BURGER)
                )
        );
        menuService.save(burger);
        menuService.save(drink);
        menuService.save(dessert);


        Kiosk kiosk = new Kiosk(
                new ConsoleInputProvider(),
                new ConsoleOutputWriter(),
                menuService
        );
        kiosk.run();
    }


}
