package com.example.basic.lv4;

import com.example.basic.lv4.domain.Menu;
import com.example.basic.lv4.domain.MenuItem;
import com.example.basic.lv4.enums.KioskStatus;
import com.example.basic.lv4.input.InputProvider;
import com.example.basic.lv4.output.OutputWriter;
import com.example.basic.lv4.service.MenuService;
import com.example.basic.lv4.utils.Parser;

import java.util.List;

public class Kiosk {

    private final InputProvider input;
    private final OutputWriter writer;
    private final MenuService menuService;

    private KioskStatus status = KioskStatus.IN_PROGRESS;

    public Kiosk(InputProvider input, OutputWriter writer, MenuService menuService) {
        this.input = input;
        this.writer = writer;
        this.menuService = menuService;
    }

    public void run() {

        List<Menu> menus = menuService.findAllMenu();

        while (status == KioskStatus.IN_PROGRESS) {
            try {
                printMainMenu(menus);
                Long option = Parser.parseLong(input.readInput());

                if (option == 0L) {
                    writer.println("프로그램을 종료합니다.");
                    status = KioskStatus.FINISH;
                } else {
                    printMenuItems(option);
                }
            } catch (RuntimeException e) {
                writer.println(e.getMessage());
            }
        }
    }

    private void printMainMenu(List<Menu> menus) {
        writer.println("[ MAIN MENU ]");
        for (Menu menu : menus) {
            writer.println(String.format("%d. %s", menu.getId(), menu.getCategory().getDescription()));
        }
        writer.println("0. 종료");
    }

    private void printMenuItems(Long categoryOption) {
        Menu menu = menuService.findById(categoryOption);
        List<MenuItem> menuItems = menuService.findAllMenuItemByCategory(menu.getCategory());

        while (true) {
            writer.println("[ " + menu.getCategory().getDescription().toUpperCase() + " MENU ]");
            for (MenuItem menuItem : menuItems) {
                writer.println(String.format("%s. %-15s | W %s | %s", menuItem.getId(), menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
            }
            writer.println("0. 뒤로가기");
            Long option = Parser.parseLong(input.readInput());

            if (option == 0L) {
                return;
            }

            for (MenuItem menuItem : menuItems) {
                if (menuItem.getId().equals(option)) {
                    writer.println("선택한 메뉴 : " + String.format("%-15s | W %s | %s", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()) + "\n");
                }
            }
        }
    }

    private void printShoppingBasket() {

    }
}
