package bl;

public enum Category {
    LAPTOPS("Laptops"),
    SMARTPHONES("Smartphones"),
    HEADPHONES("Headphones"),
    SMARTWATCHES("Smartwatches"),
    ACCESSORIES("Accessories");

    private final String displayName;

    // Konstruktor
    Category(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}