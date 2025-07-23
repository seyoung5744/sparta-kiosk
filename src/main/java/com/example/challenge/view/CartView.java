package com.example.challenge.view;

import com.example.challenge.domain.MenuItem;
import com.example.challenge.output.OutputWriter;

public class CartView {

    private final OutputWriter writer;

    public CartView(OutputWriter writer) {
        this.writer = writer;
    }

    public void printAddCart(MenuItem menuItem) {
        writer.println("선택한 메뉴 : " + String.format("%-15s | W %s | %s", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
        writer.println("");

        writer.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        writer.println("1. 확인        2. 취소");
    }
}
