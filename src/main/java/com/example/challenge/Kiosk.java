package com.example.challenge;

import com.example.challenge.domain.Cart;
import com.example.challenge.domain.Menu;
import com.example.challenge.domain.MenuItem;
import com.example.challenge.enums.Category;
import com.example.challenge.enums.Discount;
import com.example.challenge.enums.KioskOption;
import com.example.challenge.enums.KioskStatus;
import com.example.challenge.enums.cart.CartOption;
import com.example.challenge.enums.menu.MenuItemOption;
import com.example.challenge.enums.menu.MenuOption;
import com.example.challenge.exception.EmptyCartException;
import com.example.challenge.input.InputProvider;
import com.example.challenge.output.OutputWriter;
import com.example.challenge.service.MenuService;
import com.example.challenge.utils.MenuItemsUtil;
import com.example.challenge.utils.Parser;
import com.example.challenge.view.CartView;
import com.example.challenge.view.MenuView;
import com.example.challenge.view.OrderView;

import java.util.List;

public class Kiosk {

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

                KioskOption kioskOption = KioskOption.getOption(input.readInput());

                switch (kioskOption) {
                    case EXIT_OPTION -> exit();
                    case ORDER_OPTION -> handleOrder();
                    case ORDER_CANCEL_OPTION -> handleOrderCancel();
                    default -> handleMenuSelection(kioskOption);
                }
            } catch (RuntimeException e) {
                writer.println(e.getMessage());
            }
        }
    }

    private void exit() {
        writer.println("프로그램을 종료합니다.");
        status = KioskStatus.FINISH;
    }

    private void handleOrder() {
        if (cart.isEmpty()) {
            throw new EmptyCartException("장바구니가 비어있습니다. 유효한 옵션이 아닙니다.");
        }
        orderView.printOrder(cart);

        String inputOption = input.readInput();
        if (CartOption.isExit(inputOption)) {
            return;
        }

        if (CartOption.isCancel(inputOption)) {
            writer.println("취소할 메뉴 번호를 입력하세요.");
            String cancelCartItemOption = input.readInput();
            cart.removeMenuItem(Integer.parseInt(cancelCartItemOption) - 1);
            return;
        }

        orderView.printDiscountInfo();

        Discount discount = Discount.select(input.readInput());
        writer.println("주문이 완료되었습니다. 금액은 W " + discount.applyDiscount(cart.totalPrice()) + " 입니다.");
        writer.println("");

        cart.clear();
    }

    private void handleOrderCancel() {
        if (cart.isEmpty()) {
            throw new EmptyCartException("장바구니가 비어있습니다. 유효한 옵션이 아닙니다.");
        }
        orderView.printOrderCancel(cart::clear);
    }

    private void handleMenuSelection(KioskOption kioskOption) {
        Menu menu = menuService.findById(Parser.parseLong(kioskOption.getOption()));
        Category category = menu.getCategory();

        List<MenuItem> menuItems = menuService.findAllMenuItemByCategory(category);
        menuView.printMenuItems(menu, menuItems);

        String inputOption = input.readInput();

        if (MenuItemOption.isExit(inputOption)) {
            return;
        }

        MenuOption[] menuItemByCategory = MenuItemsUtil.getMenuItemsByCategory(category);
        MenuOption menuOption = MenuItemsUtil.findByOption(menuItemByCategory, inputOption);

        MenuItem menuItem = menu.getMenuItem(Parser.parseLong(menuOption.getOption()));
        cartView.printAddCart(menuItem);

        if (CartOption.isExit(input.readInput())) {
            return;
        }
        writer.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.\n");
        cart.addMenuItem(menuItem);
    }
}
