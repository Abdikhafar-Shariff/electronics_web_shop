package bl;

public class Products {
    private int item_id;
    private String name;
    private String price;
    private String category_id;
    private String image_url;

    public Products() {
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public Products(int item_id, String name, String price, String category_id, String image_url) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
        this.category_id = category_id;
        this.image_url = image_url;


    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Products{" +
                "category='" + category_id + '\'' +
                ", id=" + item_id +
                ", name='" + name + '\'' +
                ", image='" + image_url + '\'' +
                '}';
    }
}
