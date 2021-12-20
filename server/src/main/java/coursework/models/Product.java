package coursework.models;

import javax.persistence.*;
import java.io.Serializable;

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
}