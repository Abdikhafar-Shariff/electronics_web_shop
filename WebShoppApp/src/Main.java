import bl.Item;
import db.ItemDb;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        int itemId =2;  // Example item ID
        // int quantity = 8;  // Example quantity*

        try {
            // Call the addItemToCart method to insert a cart item into the database
            // CartItemDb.addItemToCart(cartId, itemId, quantity);
            Item item = ItemDb.getItemById(itemId);
            System.out.println(item);

            // Print success message
            // System.out.println("Item added to cart successfully!");
        } catch (SQLException e) {
            // Handle exceptions (e.g., SQL errors)
            System.out.println("Error adding item to cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
