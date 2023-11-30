package futurewomen;

import com.google.gson.Gson;

import java.util.Objects;

public class Product {
    private int productId;
    private String name;
    private float price;
    private String category;
    private int stockQty;

    public Product(int id, String name, float price, String category, int stockQty) {
        this.productId = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQty = stockQty;
    }

    public Product() {
    }

    public int getId() {
        return productId;
    }

    public void setId(int id) {
        this.productId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return productId == product.productId || name.equalsIgnoreCase(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name);
    }
}
