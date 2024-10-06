package db;

import bl.Cart;
import bl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDb {
    public CartDb() {

    }

    public static void createNewCart(User user) throws SQLException {
        if (doesUserHaveActiveCart(user)) {
            return;
        }
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO Cart (user_id) VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getUserId());
            int result = statement.executeUpdate();
            if (result == 1) {
                System.out.println("The cart has been created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Metod för att hämta en varukorg för en användare
    public static Cart getCart(int userId) throws SQLException {
        Cart cart = new Cart();
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Cart WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cart = new Cart();
                cart.setCartId(resultSet.getInt(1));
                cart.setSum(resultSet.getFloat(2));
            }
            return cart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCart(User user) throws SQLException {
        String query = "DELETE FROM Cart WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getUserId());
            statement.executeUpdate();
        }
    }

    public static boolean doesUserHaveActiveCart(User user) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT COUNT(*) FROM Cart WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Returnerar true om det finns en aktiv varukorg
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }
}
