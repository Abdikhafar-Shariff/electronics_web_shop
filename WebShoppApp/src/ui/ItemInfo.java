package ui;

import bl.Category;

public class ItemInfo {
    private int itemId;
    private String itemName;
    private String description;
    private String image_url;
    private int price;
    private int quantity;
    private Category category;

    public ItemInfo(String itemName, String description, String image_url, int price, int quantity, Category category) {
        this.itemName = itemName;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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
}
