package com.example.challenge.domain;

import com.example.challenge.exception.InvalidOptionException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getMenuItem().equals(item)) {
                cartItem.increaseQuantity();
                return;
            }
        }
        cartItems.add(new CartItem(item));
    }

    public void removeMenuItem(int idx) {
        if (idx >= cartItems.size()) {
            throw new InvalidOptionException(idx + "은 유효한 옵션이 아닙니다.");
        }
        cartItems.remove(idx);
    }

    /**
     * 각 cartItem 별 (수량 * 가격)
     *
     * @return 총 가격
     */
    public BigDecimal totalPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            MenuItem menuItem = cartItem.getMenuItem();
            BigDecimal quantity = BigDecimal.valueOf(cartItem.getQuantity());
            BigDecimal menuTotalPrice = menuItem.getPrice().multiply(quantity);
            sum = sum.add(menuTotalPrice);
        }
        return sum;
    }

    /**
     * 장바구니가 비었음 경우,
     * 콘솔 UI 를 선택적으로 출력한다.
     */
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    /**
     * 1. 주문을 취소할 경우
     * 2. 주문을 요청할 경우
     * 장바구니를 비워준다.
     */
    public void clear() {
        cartItems.clear();
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

}
