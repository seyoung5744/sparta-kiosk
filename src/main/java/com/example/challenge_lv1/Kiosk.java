package com.example.challenge_lv1;

import com.example.challenge_lv1.domain.Cart;
import com.example.challenge_lv1.domain.CartItem;
import com.example.challenge_lv1.domain.Menu;
import com.example.challenge_lv1.domain.MenuItem;
import com.example.challenge_lv1.enums.KioskStatus;
import com.example.challenge_lv1.input.InputProvider;
import com.example.challenge_lv1.output.OutputWriter;
import com.example.challenge_lv1.service.MenuService;
import com.example.challenge_lv1.utils.Parser;

import java.util.List;
import java.util.Map;

public class Kiosk {

    private final InputProvider input;
    private final OutputWriter writer;
    private final MenuService menuService;
    private final Cart cart;

    private KioskStatus status = KioskStatus.IN_PROGRESS;

    public Kiosk(InputProvider input, OutputWriter writer, MenuService menuService, Cart cart) {
        this.input = input;
        this.writer = writer;
        this.menuService = menuService;
        this.cart = cart;
    }

    public void run() {

        List<Menu> menus = menuService.findAllMenu();

        while (status == KioskStatus.IN_PROGRESS) {
            try {
                printMainMenu(menus);
                long option = Parser.parseLong(input.readInput());

                if (option == 0) {
                    writer.println("프로그램을 종료합니다.");
                    status = KioskStatus.FINISH;
                } else if (option == 4) {
                    if (cart.isEmpty()) {
                        throw new RuntimeException("장바구니가 비어있습니다. 유효한 옵션이 아닙니다.");
                    }
                    printOrder();
                } else if (option == 5) {
                    if (cart.isEmpty()) {
                        throw new RuntimeException("장바구니가 비어있습니다. 유효한 옵션이 아닙니다.");
                    }
                    cart.clear();
                } else if (option <= menus.size()) {
                    printMenuItems(option);
                } else {
                    throw new RuntimeException("유효한 옵션이 아닙니다.");
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

        if (!cart.isEmpty()) {
            writer.println("");
            writer.println("[ ORDER MENU ]");
            writer.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
            writer.println("5. Cancel       | 진행중인 주문을 취소합니다.");
        }
    }

    private void printMenuItems(Long categoryOption) {
        Menu menu = menuService.findById(categoryOption);
        List<MenuItem> menuItems = menuService.findAllMenuItemByCategory(menu.getCategory());

        writer.println("[ " + menu.getCategory().getDescription().toUpperCase() + " MENU ]");
        for (MenuItem menuItem : menuItems) {
            writer.println(String.format("%s. %-15s | W %s | %s", menuItem.getId(), menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
        }
        writer.println("0. 뒤로가기");
        Long option = Parser.parseLong(input.readInput());

        if (option == 0) {
            return;
        }

        printCart(menu, option);
    }

    private void printCart(Menu menu, Long option) {
        Map<Long, MenuItem> menuItemMap = menu.getMenuItemMap();
        MenuItem selectedMenuItem = menuItemMap.get(option);
        writer.println("선택한 메뉴 : " + String.format("%-15s | W %s | %s", selectedMenuItem.getName(), selectedMenuItem.getPrice(), selectedMenuItem.getDescription()));
        writer.println("");

        writer.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        writer.println("1. 확인        2. 취소");
        Long cartOption = Parser.parseLong(input.readInput());

        if (cartOption == 1) {
            writer.println(selectedMenuItem.getName() + " 이 장바구니에 추가되었습니다.");
            writer.println("");
            cart.addMenuItem(selectedMenuItem);
        }
    }

    private void printOrder() {
        writer.println("[ Orders ]");
        for (CartItem cartItem : cart.getCartItems()) {
            MenuItem menuItem = cartItem.getMenuItem();
            writer.println(String.format("수량 : %-2d | %-15s | W %s | %s", cartItem.getQuantity(), menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
        }
        writer.println("");
        writer.println("[ Total  ]");
        writer.println("W " + cart.totalPrice());
        writer.println("");

        writer.println("1. 주문      2. 메뉴판");
        Long orderOption = Parser.parseLong(input.readInput());

        if (orderOption == 1) {
            writer.println("주문이 완료되었습니다. 금액은 W " + cart.totalPrice() + " 입니다.");
            cart.clear();
        }
        writer.println("");
    }
}
