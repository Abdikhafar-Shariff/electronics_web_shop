package bl;

import db.CartDb;
import db.CartItemDb;

import java.sql.SQLException;
import java.util.List;

public class Cart {
    private int cartId;
    private float totalSum = 0;
    private static List<CartItem> cartItemsList;

    public Cart() {

    }

    public static void addNewCart(User user) throws SQLException {
        CartDb.createNewCart(user);
    }

    public static Cart getCart(int userId) throws SQLException {
        return CartDb.getCart(userId);
    }

    public static List<CartItem> addItemToCart(int itemId, int userId) throws SQLException {
        Item item = Item.getItemById(itemId);
        if (item.getQuantity() <= 0) {
            System.out.println("The selected item is currently out of stock.");
            return cartItemsList;
        }

        int cartId = Cart.getCart(userId).getCartId();
        cartItemsList = getAllCartItems(cartId);

        // Leta upp om varan redan finns i varukorgen
        CartItem existingCartItem = FindCartItem(itemId);
        if (existingCartItem == null) {
            CartItem newCartItem = new CartItem(cartId, item.getItemName(), item.getDescription(), item.getPrice(), 1);
            CartItemDb.addItemToCart(cartId, itemId, 1);
            cartItemsList.add(newCartItem);
        } else {
            existingCartItem.addQuantity(1);
            CartItemDb.updateQuantity(cartId, itemId, existingCartItem.getQuantityNr());
        }

        return cartItemsList;
    }

    static CartItem FindCartItem(int itemId) {
        for (CartItem cartItem : cartItemsList) {
            if (cartItem.getItemId() == itemId) {
                return cartItem;
            }
        }

        return null;
    }

    public static List<CartItem> getAllCartItems(int cartId) throws SQLException {
        return CartItemDb.getCartItems(cartId);
    }

    public  float calculateTotalSum() {
        totalSum = 0;
        for (CartItem item : cartItemsList) {
            totalSum += item.getSum();
        }
        return totalSum;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setSum(float sum) {
        this.totalSum = sum;
    }
}
