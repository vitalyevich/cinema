package coursework.models;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "product_types", indexes = {
        @Index(name = "UQ__product___2B5A6A5FD05F8CE6", columnList = "product_name", unique = true)
})
@Entity
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 20)
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}