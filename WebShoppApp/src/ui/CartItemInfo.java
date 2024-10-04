package ui;

public class CartItemInfo {
    private int itemId;
    private String itemName;
    private String description;
    private int price;
    private int quantity;
    private float totalSum;

    // Constructor
    public CartItemInfo(int itemId, String itemName, String description, int price, int quantity ) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.totalSum = calculateTotalSum(price, quantity);
    }

    // Method to calculate the total sum for this item
    private float calculateTotalSum(int price, int quantity) {
        return price * quantity;
    }

    // Getters
    public int getItemId() {
        return itemId;
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

    public float getTotalSum() {
        return totalSum;
    }
}
