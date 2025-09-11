package bl;

import db.CartDb;
import db.CartItemDb;
import ui.CartItemInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartHandler {

    public static void addNewCart(User user) throws SQLException {
        CartDb.createNewCart(user);
    }

    public static Cart getCart(int user_id) throws SQLException {
        return CartDb.getCart(user_id);
    }

    public static List<CartItem> getAllCartItem(List<CartItem> cartItemList) throws SQLException {
        // Fetch detailed item information from the database using getCartItems
        List<CartItem> detailedCartItems = CartItemDb.getCartItems(cartItemList);
        List<CartItem> cartItemInfoList = new ArrayList<>();

        // Convert detailed CartItem objects to CartItemInfo objects
        for (CartItem cartItem : detailedCartItems) {
            CartItem cartItemInfo = new CartItem(
                    cartItem.getItemId(),
                    cartItem.getItemName(),
                    cartItem.getDescription(),
                    cartItem.getPrice(),
                    cartItem.getQuantityNr()
            );
            cartItemInfoList.add(cartItemInfo);
        }

        return cartItemInfoList;
    }


    public static List<CartItemInfo> addItemToCart(int itemId, int userId) throws SQLException {
        // Initialize a list to hold CartItemInfo objects
        List<CartItemInfo> cartItemInfoList = new ArrayList<>();
        List<CartItem> cartItemList = new ArrayList<>();
        // Fetch the item from the database
        Item item = Item.getItemById(itemId);
        if (item.getQuantity() <= 0) {
            System.out.println("The selected item is currently out of stock.");
            return cartItemInfoList; // Return empty list if item is out of stock
        }

        // Get the user's cart ID
        int cartId = Cart.getCart(userId).getCartId();
        List<CartItem> cartItemsList = Cart.getAllCartItems(cartItemList);

        // Look for an existing item in the cart
        CartItem existingCartItem = Cart.FindCartItem(itemId);
        if (existingCartItem == null) {
            // Create a new CartItemInfo object
            CartItemInfo newCartItemInfo = new CartItemInfo( item.getItemId(),item.getItemName(), item.getDescription(), item.getPrice(), 1);
            // Add new cart item to the database and the list
            CartItemDb.addItemToCart(cartId, itemId, 1);
            cartItemInfoList.add(newCartItemInfo);
        } else {
            // Update the quantity of the existing cart item
            existingCartItem.addQuantity(1);
            CartItemDb.updateQuantity(cartId, itemId, existingCartItem.getQuantityNr());
        }

        // get the list with current cart items
        for (CartItem cartItem : cartItemsList) {
            CartItemInfo cartItemInfo = new CartItemInfo(
                    cartItem.getItemId(),
                    cartItem.getItemName(),
                    cartItem.getDescription(),
                    cartItem.getPrice(),
                    cartItem.getQuantityNr()
            );
            cartItemInfoList.add(cartItemInfo);
        }

        return cartItemInfoList; // Return the list of CartItemInfo objects
    }



}
