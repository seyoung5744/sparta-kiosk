package com.example.challenge_lv1;

import com.example.challenge_lv1.domain.Cart;
import com.example.challenge_lv1.domain.Menu;
import com.example.challenge_lv1.domain.MenuItem;
import com.example.challenge_lv1.enums.KioskStatus;
import com.example.challenge_lv1.input.InputProvider;
import com.example.challenge_lv1.output.OutputWriter;
import com.example.challenge_lv1.service.MenuService;
import com.example.challenge_lv1.utils.Parser;
import com.example.challenge_lv1.view.CartView;
import com.example.challenge_lv1.view.MenuView;
import com.example.challenge_lv1.view.OrderView;

import java.util.List;

public class Kiosk {

    private static final String CONFIRM = "1";
    private static final String EXIT_OPTION = "0";
    private static final String ORDER_OPTION = "4";
    private static final String ORDER_CANCEL_OPTION = "5";

    private final InputProvider input;
    private final OutputWriter writer;
    private final MenuService menuService;
    private final Cart cart;

    private final MenuView menuView;
    private final CartView cartView;
    private final OrderView orderView;

    private KioskStatus status = KioskStatus.IN_PROGRESS;

    public Kiosk(InputProvider input, OutputWriter writer, MenuService menuService, Cart cart, MenuView menuView, CartView cartView, OrderView orderView) {
        this.input = input;
        this.writer = writer;
        this.menuService = menuService;
        this.cart = cart;
        this.menuView = menuView;
        this.cartView = cartView;
        this.orderView = orderView;
    }

    public void run() {

        List<Menu> menus = menuService.findAllMenu();

        while (status == KioskStatus.IN_PROGRESS) {
            try {
                menuView.printMainMenu(menus);
                orderView.printOrderMenuWhenCartIsNotEmpty(cart);

                String option = input.readInput();

                switch (option) {
                    case EXIT_OPTION -> exit();
                    case ORDER_OPTION -> handleOrder();
                    case ORDER_CANCEL_OPTION -> handleOrderCancel();
                    default -> {
                        Long selectMenuOption = Parser.parseLong(option);

                        if (selectMenuOption > menus.size()) {
                            throw new RuntimeException("유효한 옵션이 아닙니다.");
                        }

                        Menu menu = menuService.findById(selectMenuOption);
                        List<MenuItem> menuItems = menuService.findAllMenuItemByCategory(menu.getCategory());
                        menuView.printMenuItems(menu, menuItems);

                        String menuItemOption = input.readInput();

                        if (menuItemOption.equals(EXIT_OPTION)) {
                            continue;
                        }

                        MenuItem menuItem = menu.getMenuItem(Parser.parseLong(menuItemOption));
                        cartView.printAddCart(menuItem);
                        String cartOption = input.readInput();

                        if (cartOption.equals(CONFIRM)) {
                            writer.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.\n");
                            cart.addMenuItem(menuItem);
                        }
                    }
                }
            } catch (RuntimeException e) {
                writer.println(e.getMessage());
            }
        }
    }

    private void handleOrder() {
        if (cart.isEmpty()) {
            throw new RuntimeException("장바구니가 비어있습니다. 유효한 옵션이 아닙니다.");
        }
        orderView.printOrder(cart);

        if (input.readInput().equals(CONFIRM)) {
            writer.println("주문이 완료되었습니다. 금액은 W " + cart.totalPrice() + " 입니다.");
            cart.clear();
        }
        writer.println("");
    }

    private void handleOrderCancel() {
        if (cart.isEmpty()) {
            throw new RuntimeException("장바구니가 비어있습니다. 유효한 옵션이 아닙니다.");
        }
        orderView.printOrderCancel(cart::clear);
    }

    private void exit() {
        writer.println("프로그램을 종료합니다.");
        status = KioskStatus.FINISH;
    }
}
