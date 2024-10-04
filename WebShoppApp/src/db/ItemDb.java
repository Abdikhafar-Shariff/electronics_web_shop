package db;

import bl.Category;
import bl.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDb {


    public static void addNewItem(Item item) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO Items (item_name, description,image_url, price, quantity, category) VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement statement = null;
        try {

            statement = connection.prepareStatement(query);
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getDescription());
            statement.setString(3,item.getImage_url());
            statement.setInt(4, item.getPrice());
            statement.setInt(5, item.getQuantity());
            statement.setString(6, item.getCategory().name());
            int result = statement.executeUpdate();
            if (result == 1) {
                System.out.println("Successfully added: " + item.getItemName());
            } else {
                System.out.println("Insertion unsuccessful.");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred while inserting the item: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            // Stänga anslutningen om den är öppen
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static void deleteItem(Item item) throws SQLException {
        String query = "DELETE FROM Items WHERE item_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            int id = getItem(item);
            if (id == -1) {
                System.out.println("Item not found. Removal aborted.");
                return; // Avbryt om item inte hittas
            }
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            connection.setAutoCommit(false);
            int result = statement.executeUpdate();
            if (result == 1) {
                System.out.println("Successfully removed item with ID: " + id);
                connection.commit();
            } else {
                System.out.println("Could not delete the item.");
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateQuantity(int newQuantity, int itemId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE Items SET quantity = ? WHERE item_id = ?";
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, itemId);

            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                System.out.println("Quantity updated successfully.");
                connection.commit();
            } else {
                System.out.println("Failed to update the quantity.");
                connection.rollback();
            }
        } catch (SQLException ex) {
            if (connection != null) {
                connection.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static int getItem(Item item) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT item_id FROM Items WHERE item_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getItemName());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Stänga anslutningen om den är öppen
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return -1; // Returnera -1 om ingen rad hittades
    }
    public static Item getItemById(int itemId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT item_name, description,image_url, price, quantity, category FROM items WHERE item_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, itemId);
            ResultSet result = statement.executeQuery();
            String categoryString = result.getString("category");
            Category category = Category.valueOf(categoryString); // Convert String back to Enum
            if (result.next()) {
                return new Item(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getInt(5),
                        category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Stänga anslutningen om den är öppen
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        return null;
    }


    public static List<Item> getAllItems() throws SQLException {
        Connection connection = null;
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM Items";
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                // Skapa ett nytt Item-objekt baserat på resultatet från databasen
                String stringCategory = result.getString("category");
                Category category = Category.valueOf(stringCategory); // convert string to enum
                Item newItem = new Item(
                        result.getString("item_name"),
                        result.getString("description"),
                        result.getString("image_url"),
                        result.getInt("price"),
                        result.getInt("quantity"),
                        category
                );
                newItem.setItemId(result.getInt("item_id")); // Anta att item_id är det första fältet
                items.add(newItem);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Stänga resurser
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null && !connection.isClosed()) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return items;
    }

    public static int getItemIdByNameAndCategory(String itemName, String category) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT item_id FROM Items WHERE item_name = ? AND category = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, itemName);
            statement.setString(2, category);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt("item_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return -1; // Returnera -1 om ingen vara hittades
    }
}



