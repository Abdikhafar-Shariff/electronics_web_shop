package src;

import src.bl.Item;

import java.sql.SQLException;
import java.util.List;

public class MainItem {
    public static void main(String[] args) {
        try {
            // 1. Lägg till en ny vara
            System.out.println("Lägger till en ny vara...");
            Item.addNewItem("Laptop", "En kraftfull laptop med 16GB RAM", 12000, 10, "Laptops");

            // 2. Uppdatera kvantiteten för en vara
            System.out.println("Uppdaterar kvantiteten för Laptop...");
            Item.updateQuantity(15, "Laptop", "laptops");

            // 3. Hämta alla varor och visa dem
            System.out.println("Alla varor i databasen:");
            List<Item> items = Item.getAllItems();
            for (Item item : items) {
                System.out.println("Item Name: " + item.getItemName() + ", Category: " + item.getCategory() +
                        ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity());
            }

            // 4. Ta bort en vara
            System.out.println("Tar bort Laptop från databasen...");


            // 5. Kontrollera om varan har tagits bort
            System.out.println("Alla varor i databasen efter borttagning:");
            items = Item.getAllItems();
            for (Item item : items) {
                System.out.println("Item Name: " + item.getItemName() + ", Category: " + item.getCategory() +
                        ", Price: " + item.getPrice() + ", Quantity: " + item.getQuantity());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
