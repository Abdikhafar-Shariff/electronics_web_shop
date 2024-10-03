package src.bl;

import src.db.CartDb;
import src.db.CartItemDb;

import java.sql.SQLException;
import java.util.List;

public class Cart {
    private int cartId;
    private float totalSum = 0;
    private List<CartItem> cartItemsList;

    public Cart() {

    }

    public void addNewCart(User user) throws SQLException {
        CartDb.createNewCart(user);
    }

    public static Cart getCart(int userId) throws SQLException {
        return CartDb.getCart(userId);
    }
    // Tar bort en vara från kundvagnen
//    public boolean deleteItemFromCart(CartItem itemToRemove, User user) throws SQLException {
//        if (cartItemsList.contains(itemToRemove)) {
//            if (itemToRemove.getQuantity() == 1) {
//                cartItemsList.remove(itemToRemove);
//                CartItemDb.deleteItemFromCart(itemToRemove.getCartId(), itemToRemove.getItemId());
//            } else {
//                int position = cartItemsList.indexOf(itemToRemove);
//                CartItem currentItem = cartItemsList.get(position);
//                currentItem.setQuantity(currentItem.getQuantity() - 1);
//                CartItemDb.updateQuantity(currentItem.getQuantity(), currentItem, user);
//            }
//            return true;
//        }
//        return false;
//    }

    public List<CartItem> addItemToCart(int itemId, int userId) throws SQLException {
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

    CartItem FindCartItem(int itemId) {
        for (CartItem cartItem : cartItemsList) {
            if (cartItem.getItemId() == itemId) {
                return cartItem;
            }
        }

        return null;
    }

    /*
    public static void updateSum(float newSum, User user) {
        // Kontrollera att summan inte är negativ
        if (newSum < 0) {
            System.out.println("The sum cannot be negative.");
            return; // Avbryt om summan är ogiltig
        }

        // Utför uppdateringen i databasen
        CartDb.updateSum(newSum, user);
        System.out.println("The total has been updated to: " + newSum);
    }*/

    public List<CartItem> getAllCartItems(int cartId) throws SQLException {
        return CartItemDb.getCartItems(cartId);
    }

    //    public void placeOrder(User user) throws SQLException{
//        CartItemDb.placeOrder(getAllCartItems(user), user);
//    }
    public float calculateTotalSum() {
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

//        public static List<CartItem> getCartItemsList () {
//            return cartItemsList;
//        }
//
//        public static void setCartItemsList (List < CartItem > cartItemsList) {
//            Cart.cartItemsList = cartItemsList;
//        }

    public float getSum() {
        return totalSum;
    }

    public void setSum(float sum) {
        this.totalSum = sum;
    }
}
