package bl;

import db.ItemDb;
import ui.ItemInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    // The parameters are passed from the UI (ItemInfo) layer.

    public static void addNewItem(String itemName, String description, String image_url,int price, int quantity, String category) throws SQLException {
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

    // This method fetches all Items from the database and transforms them into ItemInfo objects for UI usage
public static List<ItemInfo>getAllItems() throws SQLException {
    // Fetch the list of Items from the database
    List<Item> itemList = ItemDb.getAllItems();
    List<ItemInfo> itemInfoList = new ArrayList<>();
    // Loop through each Item and convert it into an ItemInfo object
    for (Item item : itemList) {
        ItemInfo itemInfo = new ItemInfo(
                item.getItemName(),
                item.getDescription(),
                item.getImage_url(),
                item.getPrice(),
                item.getQuantity(),
                item.getCategory()
        );

        // Add the converted ItemInfo to the list
        itemInfoList.add(itemInfo);
    }

   return itemInfoList;
}
}
