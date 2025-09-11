package ui;

public class CartItemInfo {
    private int itemId;
    private String itemName;
    private String description;
    private int price;
    private int quantity;
    private float totalSum;

    // Constructor
    public CartItemInfo( int itemId,String itemName, String description, int price, int quantity ) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.totalSum = calculateTotalSum(price, quantity);
    }

    public CartItemInfo() {
    }

    // Method to calculate the total sum for this item
    private float calculateTotalSum(int price, int quantity) {
        return price * quantity;
    }

    // Getters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }

    public float getTotalSum() {
        return totalSum;
    }
}
