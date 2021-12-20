package coursework.models;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private ProductType type;

    private String productName;

    private Integer amount;

    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getType() {
        return type;
    }

    public String getCategory() {
        return type.getProductName();
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product(Integer id, ProductType type, String productName, Integer amount, Long price) {
        this.id = id;
        this.type = type;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }

    public Product(ProductType type, String productName, Integer amount, Long price) {
        this.type = type;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }

    public Product() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(type, product.type) && Objects.equals(productName, product.productName) && Objects.equals(amount, product.amount) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, productName, amount, price);
    }
}