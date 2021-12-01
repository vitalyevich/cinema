package Server.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "products", indexes = {
        @Index(name = "UQ__products__2B5A6A5F09A3CFA9", columnList = "product_name", unique = true)
})
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private ProductType type;

    @Column(name = "product_name", nullable = false, length = 30)
    private String productName;

    @Column(name = "image_link", nullable = false, length = 30)
    private String imageLink;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "price", nullable = false, precision = 10, scale = 4)
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    public Product(int id, ProductType type, String productName, String imageLink, int amount, Long price) {
        this.id = id;
        this.type = type;
        this.productName = productName;
        this.imageLink = imageLink;
        this.amount = amount;
        this.price = price;
    }

    public Product() {

    }

    public Product(ProductType type, String productName, String imageLink, int amount, Long price) {
        this.type = type;
        this.productName = productName;
        this.imageLink = imageLink;
        this.amount = amount;
        this.price = price;
    }
}