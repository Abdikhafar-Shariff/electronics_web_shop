package bl;

public class CartItem extends Item{

    public CartItem(int cartId, String itemName, String description, int price, int quantity) {
        super(itemName, description, price,quantity);
        setCartId(cartId);
    }
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    private int cartId;

    private int quantityNr = 0;


    public void addQuantity(int nr) {
        if (quantityNr + nr < getQuantity()) {
            quantityNr = quantityNr + nr;
        }
    }

    public float getSum() {
        return getPrice() * quantityNr;
    }

    public int getQuantityNr() {
        return quantityNr;
    }

    public void setQuantityNr(int quantityNr) {
        this.quantityNr = quantityNr;
    }
}
