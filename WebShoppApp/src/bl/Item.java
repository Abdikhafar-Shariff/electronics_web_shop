package src.bl;

import src.db.ItemDb;

import java.sql.SQLException;
import java.util.List;

public class Item {
    private int itemId, quantity;
    private float price;
    private String itemName, description;
    private Category category;

    public Item(String itemName, String description, float price, int quantity, String category) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = Category.valueOf(category.toUpperCase());
    }

    public Item(String itemName, String description, float price, int quantity) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public static void addNewItem(String itemName, String description, int price, int quantity, String category) throws SQLException {
        Item newItem = new Item(itemName, description, price, quantity, category);
        ItemDb.addNewItem(newItem);
    }

    public static void deleteItem(String itemName, String category) throws SQLException {
        int itemId = ItemDb.getItemIdByNameAndCategory(itemName, category);
        if (itemId == -1) {
            System.out.println("Item not found.");
            return;
        }
        Item item = new Item(itemName, null, 0, 0, category);
        ItemDb.deleteItem(item);
    }

    public static void updateQuantity(int newQuantity, String itemName, String category) throws SQLException {
        // Försök hämta itemId baserat på itemName och category
        int itemId = ItemDb.getItemIdByNameAndCategory(itemName, category);
        // Kontrollera om itemId är giltigt
        if (itemId == -1) {
            System.out.println("Item not found. Update aborted.");
            return; // Avbryt om item inte hittas
        }

        // Uppdatera kvantiteten för den hittade varan
        ItemDb.updateQuantity(newQuantity, itemId);
    }


    public static List<Item> getAllItems() throws SQLException {
        return ItemDb.getAllItems();
    }

    public static Item getItemById(int itemId) throws SQLException {
        return ItemDb.getItemById(itemId);
    }


    public void decreaseQuantity() {
        if (this.quantity > 0) quantity--;
    }

    public void increaseQuantity(int number) {
        this.quantity += number;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category.name();
    }

    public void setCategory(String category) {
        this.category = Category.valueOf(category.toUpperCase());
    }

}
