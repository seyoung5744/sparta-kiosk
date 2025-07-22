package com.example.challenge_lv1.view;

import com.example.challenge_lv1.domain.Cart;
import com.example.challenge_lv1.domain.CartItem;
import com.example.challenge_lv1.domain.MenuItem;
import com.example.challenge_lv1.output.OutputWriter;

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

    public void printOrderCancel(Runnable onConfirm) {
        onConfirm.run();
        writer.println("진행 중인 주문이 취소되었습니다.");
        writer.println("");
    }
}
