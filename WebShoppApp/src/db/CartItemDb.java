package src.db;

import src.bl.CartItem;
import src.bl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDb {
    public CartItemDb() {
    }

    public static void addItemToCart(int cartId, int itemId, int quantity) throws SQLException {
        String query = "INSERT INTO CartItems (cart_id, item_id, quantity) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteItemFromCart(int cart_id, int item_id) throws SQLException {
        String query = "DELETE FROM caritems WHERE cart_id = ? AND item_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cart_id);
            statement.setInt(2, item_id);
            statement.executeUpdate();
        }
    }

    public void updateCartItemQuantity(int cartId, int itemId, int newQuantity) throws SQLException {
        String query = "UPDATE CartItems SET quantity = ? WHERE cart_id = ? AND item_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, cartId);
            preparedStatement.setInt(3, itemId);
            preparedStatement.executeUpdate();
        }
    }


    public static List<CartItem> getCartItems(int cartId) throws SQLException {
        List<CartItem> items = new ArrayList<CartItem>();
        String query = "SELECT i.item_name, i.description, ci.quantity, i.price FROM cart c" +
                "JOIN cartitems ci ON ci.cart_id = c.cart_id" +
                "JOIN items i ON i.item_id = ci.item_id" +
                "WHERE c.cart_id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CartItem item = new CartItem(
                        cartId,
                        resultSet.getString("item_name"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price")
                );
                items.add(item);
            }
        }
        return items;
    }

    public static void updateQuantity(int cartId, int itemId, int quantity) {
    }

    public static void placeOrder(List<CartItem> allCartItems, User user) {
    }

//    public static List<CartItem> getAllCartItems(int cartId) {
//    }
}
