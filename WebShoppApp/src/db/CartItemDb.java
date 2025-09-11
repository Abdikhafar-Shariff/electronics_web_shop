package db;

import bl.CartItem;
import bl.User;

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

    public static List<CartItem> getCartItems(List<CartItem> cartItemList) throws SQLException {
        List<CartItem> items = new ArrayList<>();

        String query = "SELECT item_id,item_name, description, quantity, price FROM items i " +
                "WHERE item_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Loop through the input CartItem list
            for (CartItem cartItem : items) {
                statement.setInt(1, cartItem.getItemId());  // Set item_id from CartItem
                ResultSet resultSet = statement.executeQuery();

                // Process the result set for each item in the list
                while (resultSet.next()) {
                    CartItem item = new CartItem(
                            resultSet.getInt("item_id") ,                   // Use cartId from the original CartItem
                            resultSet.getString("item_name"),         // Get item_name from the query result
                            resultSet.getString("description"),       // Get description from the query result
                            resultSet.getInt("quantity"),             // Get quantity from the query result
                            resultSet.getInt("price")                 // Get price from the query result
                    );
                    items.add(item);  // Add the fetched CartItem to the list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;  // Return the list of CartItem objects
    }


    public static void updateQuantity(int cartId, int itemId, int quantity) {
    }
}
