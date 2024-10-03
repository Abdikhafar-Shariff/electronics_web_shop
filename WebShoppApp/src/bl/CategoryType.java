package bl;

public enum CategoryType {
    LAPTOPS("Laptops"),
    SMARTPHONES("Smartphones"),
    HEADPHONES("Headphones"),
    SMARTWATCHES("Smartwatches"),
    ACCESSORIES("Accessories");

    private final String displayName;

    // Konstruktor
    CategoryType(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}