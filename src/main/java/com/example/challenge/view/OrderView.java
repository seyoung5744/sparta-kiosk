package com.example.challenge.view;

import com.example.challenge.domain.Cart;
import com.example.challenge.domain.CartItem;
import com.example.challenge.domain.MenuItem;
import com.example.challenge.output.OutputWriter;

import static com.example.challenge.enums.Discount.*;

public class OrderView {

    private final OutputWriter writer;

    public OrderView(OutputWriter writer) {
        this.writer = writer;
    }

    public void printOrderMenuWhenCartIsNotEmpty(Cart cart) {
        if (!cart.isEmpty()) {
            writer.println("");
            writer.println("[ ORDER MENU ]");
            writer.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
            writer.println("5. Cancel       | 진행중인 주문을 취소합니다.");
        }
    }

    public void printOrder(Cart cart) {
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
    }

    public void printDiscountInfo() {
        writer.println("");
        writer.println("할인 정보를 입력해주세요.");
        writer.println(String.format("1. %s : %d %%", NATIONAL_VETERAN.getDisplayName(), NATIONAL_VETERAN.getDiscountPercent()));
        writer.println(String.format("2. %s : %d %%", SOLDIER.getDisplayName(), SOLDIER.getDiscountPercent()));
        writer.println(String.format("3. %s : %d %%", STUDENT.getDisplayName(), STUDENT.getDiscountPercent()));
        writer.println(String.format("4. %s : %d %%", GENERAL.getDisplayName(), GENERAL.getDiscountPercent()));
    }

    public void printOrderCancel(Runnable onConfirm) {
        onConfirm.run();
        writer.println("진행 중인 주문이 취소되었습니다.");
        writer.println("");
    }
}
