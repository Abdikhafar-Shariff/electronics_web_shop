package ui;

import bl.CartItem;

import java.util.List;

public class CartInfo {
    private int cartId;
    private float totalSum = 0;
    private static List<CartItem> cartItemsList;

    public CartInfo(int cartId, float totalSum) {
        this.cartId = cartId;
        this.totalSum = totalSum;
    }

    public CartInfo() {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }

    public static List<CartItem> getCartItemsList() {
        return cartItemsList;
    }

    public static void setCartItemsList(List<CartItem> cartItemsList) {
        CartInfo.cartItemsList = cartItemsList;
    }
}
