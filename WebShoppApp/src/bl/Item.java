package bl;

import db.ItemDb;

import java.sql.SQLException;
import java.util.List;

public class Item {
    private int itemId;
    private String itemName;
    private String description;
    private String image_url;
    private int price;
    private int quantity;
    private Category category;


    public Item() {
    }

    public Item( String itemName, String description, String image_url, int price,
                int quantity, Category category) {

        this.itemName = itemName;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Item( String itemName, String description, int price, int quantity) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public static void addNewItem(String itemName, String description, String image_url, int price, int quantity, String category) throws SQLException {
        Item newItem = new Item(itemName, description, image_url,price, quantity, Category.valueOf(category.toUpperCase()));
        ItemDb.addNewItem(newItem);
    }

    public static void deleteItem(String itemName, String category) throws SQLException {
        int itemId = ItemDb.getItemIdByNameAndCategory(itemName, category);
        if (itemId == -1) {
            System.out.println("Item not found.");
            return;
        }
        Item item = new Item(itemName, null,null, 0, 0, Category.valueOf(category.toUpperCase()));
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getPrice() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) quantity--;
    }

    public void increaseQuantity(int number) {
        this.quantity += number;
    }
}
